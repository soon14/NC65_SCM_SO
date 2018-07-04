/**
 * 
 */
package nc.vo.so.m30.sobalance.util;

import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * @author gdsjw
 * 
 */
public class SoBalanceUtil {

  private static SoBalanceUtil instance = new SoBalanceUtil();

  private SoBalanceUtil() {
    //
  }

  public static SoBalanceUtil getInstance() {
    return SoBalanceUtil.instance;
  }

  public SoBalanceHVO createSoBalanceHVOBySaleOrderVO(SaleOrderVO saleordervo) {
    SoBalanceHVO headvo = new SoBalanceHVO();
    SaleOrderHVO soheadvo = saleordervo.getParentVO();
    SaleOrderBVO[] sobodyvos = saleordervo.getChildrenVO();

    headvo.setCsaleorderid(soheadvo.getPrimaryKey());
    headvo.setPk_org(soheadvo.getPk_org());
    headvo.setPk_org_v(soheadvo.getPk_org_v());
    headvo.setPk_group(soheadvo.getPk_group());
    headvo.setCcustomerid(soheadvo.getCcustomerid());
    headvo.setCinvoicecustid(soheadvo.getCinvoicecustid());
    headvo.setVbillcode(soheadvo.getVbillcode());
    headvo.setDbilldate(soheadvo.getDbilldate());
    headvo.setCorigcurrencyid(soheadvo.getCorigcurrencyid());
    headvo.setNtotalorigtaxmny(soheadvo.getNtotalorigmny());
    headvo.setCpaytermid(soheadvo.getCpaytermid());
    headvo.setCemployeeid(soheadvo.getCemployeeid());
    headvo.setCdeptid(soheadvo.getCdeptid());
    headvo.setCarorgid(sobodyvos[0].getCarorgid());
    headvo.setVtrantypecode(soheadvo.getCtrantypeid());
    headvo.setCchanneltypeid(soheadvo.getCchanneltypeid());

    // headvo.setDr(Integer.valueOf(0));
    // headvo.setStatus(VOStatus.NEW);

    // DBTool tool = new DBTool();
    // String[] ids = tool.getOIDs(1);
    // headvo.setCsobalanceid(ids[0]);
    return headvo;
  }

  public void synSoBalance(SoBalanceVO bill, SaleOrderVO saleordervo) {
    //
    SoBalanceHVO headvo = bill.getParentVO();
    SaleOrderHVO soheadvo = saleordervo.getParentVO();
    SaleOrderBVO[] sobodyvos = saleordervo.getChildrenVO();

    headvo.setCsaleorderid(soheadvo.getPrimaryKey());
    headvo.setPk_org(soheadvo.getPk_org());
    headvo.setPk_org_v(soheadvo.getPk_org_v());
    headvo.setPk_group(soheadvo.getPk_group());
    headvo.setCcustomerid(soheadvo.getCcustomerid());
    headvo.setCinvoicecustid(soheadvo.getCinvoicecustid());
    headvo.setVbillcode(soheadvo.getVbillcode());
    headvo.setDbilldate(soheadvo.getDbilldate());
    headvo.setCorigcurrencyid(soheadvo.getCorigcurrencyid());
    headvo.setNtotalorigtaxmny(soheadvo.getNtotalorigmny());
    headvo.setCpaytermid(soheadvo.getCpaytermid());
    headvo.setCemployeeid(soheadvo.getCemployeeid());
    headvo.setCdeptid(soheadvo.getCdeptid());
    headvo.setCarorgid(sobodyvos[0].getCarorgid());
    headvo.setVtrantypecode(soheadvo.getCtrantypeid());
    headvo.setCchanneltypeid(soheadvo.getCchanneltypeid());
  }

  /**
   * 计算表体合计金额
   * 
   * @param sobalancevo
   * @param name 计算和的字段
   */
  public UFDouble caculateSoBalanceTotalBodymny(SoBalanceVO sobalancevo,
      String name) {
    SoBalanceBVO[] oldbodyvos = sobalancevo.getChildrenVO();
    UFDouble totalbodymny = UFDouble.ZERO_DBL;
    if (oldbodyvos == null || oldbodyvos.length == 0) {
      return totalbodymny;
    }
    for (SoBalanceBVO bodyvo : oldbodyvos) {
      int vostatus = bodyvo.getStatus();
      if (vostatus != VOStatus.DELETED) {
        UFDouble mny =
            bodyvo.getAttributeValue(name) == null ? UFDouble.ZERO_DBL
                : (UFDouble) bodyvo.getAttributeValue(name);
        totalbodymny = totalbodymny.add(mny);
      }
    }
    return totalbodymny;
  }

  public UFDouble caculateSoBalanceTotalBodymnyForManual(SoBalanceVO sobalancevo) {
    SoBalanceBVO[] oldbodyvos = sobalancevo.getChildrenVO();
    UFDouble totalbodymny = UFDouble.ZERO_DBL;
    if (oldbodyvos == null || oldbodyvos.length == 0) {
      return totalbodymny;
    }
    for (SoBalanceBVO bodyvo : oldbodyvos) {
      int vostatus = bodyvo.getStatus();
      if (vostatus != VOStatus.DELETED) {
        UFDouble norigordbalmny = bodyvo.getNorigordbalmny();
        UFDouble norigaccbalmny = bodyvo.getNorigaccbalmny();
        UFDouble norigthisbalmny = bodyvo.getNorigthisbalmny();

        if (MathTool.absCompareTo(norigordbalmny, norigaccbalmny) > 0) {

          UFDouble adjustmny = MathTool.add(norigordbalmny, norigthisbalmny);
          totalbodymny = totalbodymny.add(adjustmny);
        }
        else {

          UFDouble adjustmny = MathTool.add(norigaccbalmny, norigthisbalmny);
          totalbodymny = totalbodymny.add(adjustmny);
        }
      }
    }
    return totalbodymny;
  }

}
