package nc.bs.so.m30.state.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m30.ref.so.m33.SOm33ServicesUtil;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * @description
 * 销售订单成本结算打开处理发出商品，将生成对应的贷方发出商品取消
 * @scene
 * 销售订单下游出库单做过发出商品成本结算打开后
 * @param 
 * 无
 */
public class IAOpenProcessRule implements IRule<SaleOrderViewVO> {

  @Override
  public void process(SaleOrderViewVO[] vos) {
    String[] ordBids =
        SoVoTools.getVOsOnlyValues(vos, SaleOrderBVO.CSALEORDERBID);
    SOm33ServicesUtil.unProcess4CReg(ordBids);
  }
}
