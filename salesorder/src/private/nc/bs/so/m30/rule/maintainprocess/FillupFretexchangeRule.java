package nc.bs.so.m30.rule.maintainprocess;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.enumeration.Fretexchange;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.FretexchangeRule;

/**
 * @description 补全表体退换货标记
 * @scene 销售订单新增、修改保存前
 * @param 无
 * 
 * 
 * @since 6.36
 */
public class FillupFretexchangeRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] bills) {
    for (SaleOrderVO bill : bills) {
      IKeyValue keyvalue = new VOKeyValue<SaleOrderVO>(bill);
      FretexchangeRule fretexrule = new FretexchangeRule(keyvalue);
      SaleOrderBVO[] bodyvos = bill.getChildrenVO();
      int i = 0;
      for (SaleOrderBVO bodyvo : bodyvos) {
        int vostatus = bodyvo.getStatus();
        // 删除的行不补充冗余信息
        if (vostatus != VOStatus.DELETED) {
          bodyvo.setFretexchange(fretexrule.getFretexchange(i));
          i++;
        }
      }
    }
  }
}
