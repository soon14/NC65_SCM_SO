package nc.bs.so.m30.rule.maintaincheck;

import java.util.HashSet;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDQueryBuilder;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.CTBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.util.AppTool;

/**
 * @description 发货日期和到货日期校验
 * @scene 销售订单新增、修改保存前
 * @param 无
 * 
 * 
 * @since 6.3
 * @version 2013-8-19 下午03:04:26
 * @author tianft
 */
public class CheckDateRule implements IRule<SaleOrderVO> {

	@Override
	public void process(SaleOrderVO[] vos) {
		for (SaleOrderVO vo : vos) {
			this.checkDate(vo);
		}
	}

	private void checkDate(SaleOrderVO bill) {
		SaleOrderHVO header = bill.getParentVO();
		UFDate dbilldate = header.getDbilldate();
		SaleOrderBVO[] items = bill.getChildrenVO();
		// 合同实际生效日期 add by quyt 20150127
		UFDate billBeginDate = null;

		Set<String> csrcIds = new HashSet<String>();
		for (SaleOrderBVO item : items) {
			int vostatus = item.getStatus();
			if (vostatus == VOStatus.DELETED || vostatus == VOStatus.UNCHANGED) {
				// 不检查删除、没变化的行，
				continue;
			}
			// 计划发货日、计划到货日
			UFDate dsenddate = item.getDsenddate();
			UFDate dreceivedate = item.getDreceivedate();
			if (AppTool.getInstance().compareDate(dsenddate, dbilldate) < 0) {

				ExceptionUtils
						.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
								.getNCLangRes().getStrByID("4006011_0",
										"04006011-0088")/*
														 * @res "发货日期应大于等于单据日期!"
														 */);
			}
			if (AppTool.getInstance().compareDate(dreceivedate, dsenddate) < 0) {

				ExceptionUtils
						.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
								.getNCLangRes().getStrByID("4006011_0",
										"04006011-0089")/*
														 * @res
														 * "要求收货日期不能小于计划发货日期!"
														 */);
			}
			// begin 单据日期应在有效日期内 add by quyt 20141216
			String fromtype = item.getVsrctype();
			if (PubAppTool.isEqual(CTBillType.SaleDaily.getCode(), fromtype)) {
				String csrcId = item.getCsrcid();
				csrcIds.add(csrcId);
			}
		}
		// add by wangshu6 2015-1-28 判空
		if(csrcIds.size()==0){
		  return;
		}
		// end 
		// 由于可能同时添加大量数据，这里使用临时表
		StringBuilder whereSql = new StringBuilder();
		whereSql.append(" select max(a.ACTUALVALIDATE) from CT_SALE a where a.dr = 0 and ");
		String name = "a.pk_ct_sale";
		String[] ids = csrcIds.toArray(new String[csrcIds.size()]);
		IDQueryBuilder inSql = new IDQueryBuilder();
		String sql = inSql.buildSQL(name, ids);
		whereSql.append(sql);
		DataAccessUtils utils = new DataAccessUtils();
		IRowSet rs = utils.query(whereSql.toString());
		String[][] result = rs.toTwoDimensionStringArray();
		if(result != null && result[0][0] != null){			
			// 获取最大实际生效日期
			billBeginDate = new UFDate(result[0][0]);
		}else{
			ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0495")/*获取合同最大实际生效日期时失败！可能的原因是：通过合同拉单时出现了并发，请重新查询合同信息！*/);
		}

		if (null != billBeginDate
				&& AppTool.getInstance().compareDate(dbilldate, billBeginDate) < 0) {
			ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0496", null, new String[]{dbilldate.toString().substring(0,10),billBeginDate.toString().substring(0,10)})/* 当前单据日期【 {0} 】不在最晚合同生效日期【{1}】之后 */);
		}
		// end

	}
}
