package nc.pf.so.function;

import nc.pubitf.so.m30.split.ISaleOrderSplitPara;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.SalequotationBVO;

/**
 * 报价单实现的销售订单按照业务委托关系分单参数接口
 * 
 * @since 6.0
 * @version 2011-6-30 下午03:53:34
 * @author fengjb
 */
public class M4310For30SplitPara implements ISaleOrderSplitPara {

  AggSalequotationHVO salequotavo;

  public M4310For30SplitPara(AggSalequotationHVO salequotavo) {
    this.salequotavo = salequotavo;
  }

  @Override
  public int getBodyCount() {
    SalequotationBVO[] bvos = this.salequotavo.getChildrenVO();
    if (null == bvos || bvos.length == 0) {
      return 0;
    }
    return bvos.length;
  }

  @Override
  public String getSaleOrgid() {
    return this.salequotavo.getParentVO().getPk_org();
  }

  @Override
  public String getCustomerid() {
    return this.salequotavo.getParentVO().getPk_customer();
  }

  @Override
  public String getMaterialid(int row) {
    SalequotationBVO[] bvos = this.salequotavo.getChildrenVO();
    return bvos[row].getPk_material();
  }

  @Override
  public String getSendStockOrgid(int row) {
    return null;
  }

  @Override
  public String getTrafficOrgid(int row) {
    return null;
  }

  @Override
  public String getSettleOrgid(int row) {
    return null;
  }

  @Override
  public String getArOrgid(int row) {
    return null;
  }

}
