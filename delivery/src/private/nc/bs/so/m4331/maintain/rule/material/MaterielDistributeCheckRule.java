package nc.bs.so.m4331.maintain.rule.material;

import nc.bs.so.pub.rule.MaterielDistributeCheck;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * @description
 * 销售发货单保存前检查物料是否分配到对应的库存组织
 * @scene
 * 销售发货单新增、修改保存前
 * @param
 * 无
 * @since 6.0
 * @version 2011-5-17 上午11:50:40
 * @author 祝会征
 */
public class MaterielDistributeCheckRule implements IRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] vos) {
    for (DeliveryVO bill : vos) {
      int len = bill.getChildrenVO().length;
      String[][] materIDStoreIDs = new String[len][2];
      int i = 0;
      for (DeliveryBVO bvo : bill.getChildrenVO()) {
        materIDStoreIDs[i] = new String[2];
        materIDStoreIDs[i][0] = bvo.getCmaterialvid();
        materIDStoreIDs[i][1] = bvo.getCsendstockorgid();
        i++;
      }
      new MaterielDistributeCheck().check(materIDStoreIDs);
    }
  }
}
