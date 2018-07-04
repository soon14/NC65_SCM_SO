package nc.impl.so.m4331.action.maintain.rule.pushwrite;

import nc.bs.so.m4331.maintain.rule.insert.RewriteBillInsertRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

public class RewriteSrcRule {
  public void rewriteSrc(DeliveryViewVO[] views) {
    DeliveryVO[] vos = new DeliveryVO[views.length];
    for (int i = 0; i < vos.length; i++) {
      vos[i] = new DeliveryVO();
      // 视图VO主元数据为子表，界面冗余字段会向子表设值。需要将子表的值付给主表。
      DeliveryHVO head = views[i].getHead();
      DeliveryBVO body = views[i].getItem();
      head.setPk_org(body.getPk_org());
      head.setDbilldate(body.getDbilldate());
      vos[i].setParent(head);
      vos[i].setChildrenVO(new DeliveryBVO[] {
        body
      });
    }
    IRule<DeliveryVO> rule = new RewriteBillInsertRule();
    rule.process(vos);
  }
}
