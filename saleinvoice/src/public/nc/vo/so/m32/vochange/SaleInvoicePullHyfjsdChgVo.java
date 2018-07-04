package nc.vo.so.m32.vochange;

import nc.itf.ecpubapp.pattern.database.DataAccessUtils;
import nc.itf.scmpub.reference.uap.bd.currency.CurrencyRate;
import nc.vo.ecpubapp.pattern.data.IRowSet;
import nc.vo.ftpub.rule.FTCurrencyRule;
import nc.vo.pf.change.ChangeVOAdjustContext;
import nc.vo.pf.change.IChangeVOAdjust;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.scmf.pub.keyvalue.IKeyValue;
import nc.vo.scmf.pub.keyvalue.VOKeyValue;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

import org.apache.commons.lang.ArrayUtils;

public class SaleInvoicePullHyfjsdChgVo implements IChangeVOAdjust{

	
	 
	@Override
	public AggregatedValueObject adjustBeforeChange(
			AggregatedValueObject srcVO, ChangeVOAdjustContext adjustContext)
			throws BusinessException {
		// 调用批处理方法
		return this.batchAdjustBeforeChange(
				new AggregatedValueObject[] { srcVO }, adjustContext)[0];
	}

	@Override
	public AggregatedValueObject adjustAfterChange(AggregatedValueObject srcVO,
			AggregatedValueObject destVO, ChangeVOAdjustContext adjustContext)
			throws BusinessException {
		AggregatedValueObject[] srcVOs = new AggregatedValueObject[] { srcVO };
		AggregatedValueObject[] destVOs = new AggregatedValueObject[] { destVO };
		return this.batchAdjustAfterChange(srcVOs, destVOs, adjustContext)[0];
	}

	@Override
	public AggregatedValueObject[] batchAdjustBeforeChange(
			AggregatedValueObject[] srcVOs, ChangeVOAdjustContext adjustContext)
			throws BusinessException {
		 return srcVOs;
	}

	@Override
	public AggregatedValueObject[] batchAdjustAfterChange(
			AggregatedValueObject[] srcVOs, AggregatedValueObject[] destVOs,
			ChangeVOAdjustContext adjustContext) throws BusinessException {
		// TODO 自动生成的方法存根
		if (ArrayUtils.isEmpty(srcVOs) || ArrayUtils.isEmpty(destVOs)) {
		      return null;
		    }
		SaleInvoiceVO[] vos = (SaleInvoiceVO[])destVOs;
		// 设置本币币种
	    this.fillValue(vos);
	    this.sumValues(vos);
	    return vos;
	}
	
	private void sumValues(SaleInvoiceVO[] vos){
		//norigtaxmny 价税合计
		for (SaleInvoiceVO vo : vos){
			SaleInvoiceBVO[] bvos = vo.getChildrenVO();
			if(null == bvos || bvos.length == 0) continue;
			UFDouble sum = UFDouble.ZERO_DBL;
			UFDouble nexchangerate = null == vo.getParentVO().getNexchangerate() ? UFDouble.ZERO_DBL : vo.getParentVO().getNexchangerate();
			for(SaleInvoiceBVO bvo : bvos){
				sum = sum.add(bvo.getNorigtaxmny());
				bvo.setNorigtaxmny(bvo.getNorigtaxmny().setScale(2, UFDouble.ROUND_HALF_UP));
				//ntaxmny 本币价税合计 = nexchangerate 折本汇率*norigtaxmny 价税合计
				bvo.setNtaxmny((nexchangerate.multiply(null == bvo.getNorigtaxmny() ? UFDouble.ZERO_DBL : bvo.getNorigtaxmny())).setScale(2, UFDouble.ROUND_HALF_UP));
				bvo.setNorigmny(bvo.getNorigmny().setScale(2, UFDouble.ROUND_HALF_UP));
				//nmny 本币无税金额 = (ntaxmny 本币价税合计/(1+ntaxrate/100)
				bvo.setNmny((nexchangerate.multiply(null == bvo.getNorigmny() ? UFDouble.ZERO_DBL : bvo.getNorigmny())).setScale(2, UFDouble.ROUND_HALF_UP));
				//税额ntax=ntaxmny 本币价税合计-nmny 本币无税金额 
				bvo.setNtax(null == bvo.getNtaxmny() ? UFDouble.ZERO_DBL : bvo.getNtaxmny().add(-((null == bvo.getNmny() ? UFDouble.ZERO_DBL : bvo.getNmny()).doubleValue())));
				//nnetprice 主本币无税净价 = nexchangerate 折本汇率*norignetprice 主无税净价/100
				bvo.setNnetprice(nexchangerate.multiply(null == bvo.getNorignetprice() ? UFDouble.ZERO_DBL : bvo.getNorignetprice()));
			}//ntotalorigmny 价税合计
			vo.getParentVO().setNtotalorigmny(sum);
		}
	}
	
	private void fillValue(SaleInvoiceVO[] vos) {
		try {
			for (SaleInvoiceVO vo : vos) {
			  this.fillFinanceOrg(vo);
			  IKeyValue keyValue = new VOKeyValue<SaleInvoiceVO>(vo);
		      // 1.设置本币币种
		      FTCurrencyRule cr = new FTCurrencyRule(keyValue);
		      cr.setCsettleorg("pk_org");
		      cr.setHeadCurrency();
			  String ccurrencyid = (String) vo.getParent().getAttributeValue("ccurrencyid");
		      //币种
			  String corigcurrencyid =  (String) vo.getParent().getAttributeValue("corigcurrencyid");
		      vo.getParentVO().setCorigcurrencyid(corigcurrencyid);
		      //单据日期
		      UFDate dbilldate = (UFDate) vo.getParent().getAttributeValue("dmakedate");
		      //折本汇率
		      /*UFDouble nexchangerate = CurrencyRate.getCurrencySellRateByOrg(pk_org, corigcurrencyid,ccurrencyid, dbilldate);
		      vo.getParentVO().setNexchangerate(nexchangerate);*/
		      
		      DataAccessUtils queryUtil = new DataAccessUtils();
			  String sql = "SELECT RATE FROM bd_currrate WHERE ratedate=(SELECT MAX(ratedate) FROM bd_currrate WHERE ratedate<='"+dbilldate+"'"
                           +" AND dr=0 AND pk_currinfo=(SELECT pk_currinfo FROM bd_currinfo WHERE oppcurrtype='"+ccurrencyid+"' AND pk_currtype  ='"+corigcurrencyid+"'"
                           +" AND dr=0)) AND pk_currinfo=(SELECT pk_currinfo FROM bd_currinfo WHERE oppcurrtype='"+ccurrencyid+"'"
                           +"AND pk_currtype  ='"+corigcurrencyid+"' AND dr=0)";
			  IRowSet rowSet = queryUtil.query(sql);
			  //String[][] cemployeeid = rowSet.toTwoDimensionStringArray();
			  String[] nexchangerate = rowSet.toOneDimensionStringArray();
			  if(nexchangerate.length>0){
				  UFDouble ufdouble =new UFDouble();
				  vo.getParentVO().setNexchangerate(ufdouble.add(Double.parseDouble(nexchangerate[0]))); 
			  }

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void fillFinanceOrg(SaleInvoiceVO vo){
		try {
			String pk_org = vo.getParentVO().getPk_org();
			if (null != pk_org || !"".equals(pk_org)) {
				DataAccessUtils queryUtil = new DataAccessUtils();
				String sql = "select PK_FINANCEORG from org_trafficorg where PK_TRAFFICORG = '"
						+ pk_org + "'";
				IRowSet rowSet = queryUtil.query(sql);
				String[] finorgs = rowSet.toOneDimensionStringArray();
				if (null == finorgs || finorgs.length == 0)
					return;
				vo.getParentVO().setPk_org(finorgs[0]);
				sql = "select pk_vid from org_financeorg where pk_financeorg = '"
						+ finorgs[0] + "'";
				rowSet = queryUtil.query(sql);
				finorgs = rowSet.toOneDimensionStringArray();
				if (null == finorgs || finorgs.length == 0)
					return;
				vo.getParentVO().setPk_org_v(finorgs[0]);
				SaleInvoiceBVO[] sub = vo.getChildrenVO();
				if (null != sub && sub.length > 0) {
					for (SaleInvoiceBVO bvo : sub) {
						bvo.setPk_org(finorgs[0]);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}
