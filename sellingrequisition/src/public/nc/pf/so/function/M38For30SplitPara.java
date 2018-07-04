package nc.pf.so.function;

import nc.pubitf.so.m30.split.ISaleOrderSplitPara;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderVO;
/**
 * 预订单实现的销售订单按照业务委托关系分单参数接口
 * 
 * @since 6.0
 * @version 2011-6-30 下午03:53:34
 * @author fengjb
 */
public class M38For30SplitPara implements ISaleOrderSplitPara {
  
  PreOrderVO preordervo;
  public M38For30SplitPara(PreOrderVO preordervo){
    this.preordervo = preordervo;
  }
  @Override
  public int getBodyCount() {
    PreOrderBVO[] bvos = this.preordervo.getChildrenVO();
    if(null == bvos || bvos.length ==0){
      return 0;
    }
    return bvos.length;
  }

  @Override
  public String getSaleOrgid() {
    return this.preordervo.getParentVO().getPk_org();
  }

  @Override
  public String getCustomerid() {
    return this.preordervo.getParentVO().getCcustomerid();
  }

  @Override
  public String getMaterialid(int row) {
    PreOrderBVO[] bvos = this.preordervo.getChildrenVO();
    return bvos[row].getCmaterialid();
  }

  @Override
  public String getSendStockOrgid(int row) {
    PreOrderBVO[] bvos = this.preordervo.getChildrenVO();
    return bvos[row].getCsendstockorgid();
  }

  @Override
  public String getTrafficOrgid(int row) {

    PreOrderBVO[] bvos = this.preordervo.getChildrenVO();
    return bvos[row].getCtrafficorgid();
  
  }

  @Override
  public String getSettleOrgid(int row) {
    PreOrderBVO[] bvos = this.preordervo.getChildrenVO();
    return bvos[row].getCsettleorgid();
  }

  @Override
  public String getArOrgid(int row) {

    PreOrderBVO[] bvos = this.preordervo.getChildrenVO();
    return bvos[row].getCarorgid();
  
  }

}
