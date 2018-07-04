package nc.bs.so.m30.state.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m30.ref.so.m33.SOm33ServicesUtil;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * @description
 * 销售订单成本结算关闭处理发出商品，生成贷方发出商品 
 * @scene
 * 销售订单下游出库单做过发出商品成本结算关闭后
 * @param 
 * 无
 */
public class IACloseProcessRule implements IRule<SaleOrderViewVO> {

  @Override
  public void process(SaleOrderViewVO[] vos) {
    String[] ordBids =
        SoVoTools.getVOsOnlyValues(vos, SaleOrderBVO.CSALEORDERBID);
    SOm33ServicesUtil.process4CReg(ordBids);
  }

}
