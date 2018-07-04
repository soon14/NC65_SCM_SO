package nc.bs.so.m4331.maintain.rule.insert;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.util.VORowNoUtils;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryVO;

public class FillRowNoRule implements IRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] vos) {
    for (DeliveryVO bill : vos) {
      // 这个是补全VO，处理时需要区分行状态
      this.fillupRowNo(bill);
    }
  }

  private void fillupRowNo(DeliveryVO bill) {

    // 为行号为空的行补充行号。
    DeliveryBVO[] items = bill.getChildrenVO();
    List<DeliveryBVO> bvos = new ArrayList<DeliveryBVO>();
    for (DeliveryBVO item : items) {
      int vostatus = item.getStatus();
      if (vostatus == VOStatus.DELETED) {
        // 不包含删除的行
        continue;
      }
      bvos.add(item);
    }
    items = bvos.toArray(new DeliveryBVO[0]);
    VORowNoUtils.setVOsRowNoByRule(items, DeliveryBVO.CROWNO);

  }

}
