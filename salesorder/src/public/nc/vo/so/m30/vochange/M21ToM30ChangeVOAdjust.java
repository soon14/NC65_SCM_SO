package nc.vo.so.m30.vochange;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.pub.SaleOrderVOCalculator;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.SOProfitCenterValueRule;

public class M21ToM30ChangeVOAdjust extends AbstractSaleOrderChangeVOAdjust {

  @Override
  protected String getSrcBillTypeCode() {
    return POBillType.Order.getCode();
  }

  @Override
  protected void fillRefAddValue(SaleOrderVO[] vos) {
    super.fillRefAddValue(vos);
    for (SaleOrderVO vo : vos) {
    IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(vo);
    // add by zhangby5 for 利润中心取值规则
    SOProfitCenterValueRule profitRule = new SOProfitCenterValueRule(keyValue);
    profitRule.setProfitCenterValue(SaleOrderBVO.CSPROFITCENTERVID,
        SaleOrderBVO.CSPROFITCENTERID);
    }
  }

  @Override
  protected void processNumNoChangeOrder(SaleOrderVO[] nonumchgvos) {
    for (SaleOrderVO ordervo : nonumchgvos) {
      SaleOrderVOCalculator calcultor = new SaleOrderVOCalculator(ordervo);
      int ilength = ordervo.getChildrenVO().length;
      int[] rows = new int[ilength];
      for (int i = 0; i < ilength; i++) {
        rows[i] = i;
      }
      calcultor.setForceFixUnitRate(UFBoolean.TRUE);
      calcultor.calculateDiscountmny(rows, SaleOrderBVO.NORIGTAXMNY);
    }
  }
}
