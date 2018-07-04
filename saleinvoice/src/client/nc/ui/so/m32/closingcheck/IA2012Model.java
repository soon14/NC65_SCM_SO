package nc.ui.so.m32.closingcheck;

import java.util.Map;

import nc.bd.accperiod.AccperiodmonthAccessor;
import nc.itf.scmpub.reference.uap.org.CostRegionPubService;
import nc.ui.org.closeaccbook.check.ICloseCheckModel;
import nc.ui.so.pub.closingcheck.ClosingCheckModelService;
import nc.ui.pubapp.uif2app.model.BatchBillTableModel;
import nc.vo.bd.period2.AccperiodmonthVO;
import nc.vo.org.closeaccbook.check.CloseParam;
import nc.vo.org.closeaccbook.check.ParamType;
import nc.vo.pub.lang.UFDate;
import nc.vo.scmpub.parameter.ia.QueryParaVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

/**
 * 未审批销售发票（关账相关）
 * @since 6.36
 * @version 2015-4-14 下午3:20:04
 * @author Legend_Han(hanlja@yonyou.com)
 */
@SuppressWarnings("restriction")
public class IA2012Model extends  BatchBillTableModel implements ICloseCheckModel {
	private ClosingCheckModelService service = new ClosingCheckModelService();
	
	@Override
	public void checkClose(CloseParam param) {
		String pk_period = (String)param.getAttributeValue(ParamType.PERIOD);
		String[] pk_orgs = (String[])param.getAttributeValue(ParamType.PK_COSTORG);
		Map<String, String> financeOrgs = CostRegionPubService.getFinanceOrgMapByCostRegionIDS(pk_orgs);
		String[] financeOrg = financeOrgs.values().toArray(new String[0]);
		UFDate period_begin = null;
		UFDate period_end = null;
		AccperiodmonthVO formalPeriod = AccperiodmonthAccessor.getInstance().queryAccperiodmonthVOByPk(pk_period);
		if( formalPeriod != null){
			period_begin = formalPeriod.getBegindate();
			period_end = formalPeriod.getEnddate();
		}
	    QueryParaVO paraVO =
	          new QueryParaVO(financeOrg,period_begin ,
	        		  period_end);
	    SaleInvoiceHVO[] vo = this.service.getUnapproveSoInvoiceVO(paraVO);
		this.initModel(vo);
	}
}
