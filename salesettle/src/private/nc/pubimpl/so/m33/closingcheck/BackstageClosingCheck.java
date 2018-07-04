package nc.pubimpl.so.m33.closingcheck;

import java.util.Map;

import nc.bd.accperiod.AccperiodmonthAccessor;
import nc.itf.org.check.ICloseCheck;
import nc.itf.scmpub.reference.uap.org.CostRegionPubService;
import nc.ui.so.pub.closingcheck.ClosingCheckModelService;
import nc.vo.bd.period2.AccperiodmonthVO;
import nc.vo.ic.m4c.entity.SaleOutHeadVO;
import nc.vo.org.closeaccbook.check.CheckResultEnum;
import nc.vo.org.closeaccbook.check.CloseParam;
import nc.vo.org.closeaccbook.check.ParamType;
import nc.vo.pub.lang.UFDate;
import nc.vo.scmpub.parameter.ia.QueryParaVO;

/**
 * ºó¶Ë¼ì²éÂß¼­¡£
 * @since 6.36
 * @version 2015-4-14 ÏÂÎç3:20:04
 * @author Legend_Han(hanlja@yonyou.com)
 */
public class BackstageClosingCheck implements ICloseCheck {
  private ClosingCheckModelService service = new ClosingCheckModelService();

  @Override
  public CheckResultEnum checkClose(CloseParam param) {
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
        
    SaleOutHeadVO[] vo = this.service.getUnsettledSaleOut(paraVO);
    CheckResultEnum returntype = new CheckResultEnum();
    int resultType = 1;
    if (vo != null && vo.length >= 1) {
      resultType = -1;
    }
    returntype.setResultType(resultType);
    return returntype;
  }
}
