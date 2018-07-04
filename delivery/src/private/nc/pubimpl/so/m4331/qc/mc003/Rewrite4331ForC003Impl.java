package nc.pubimpl.so.m4331.qc.mc003;

import nc.vo.pub.BusinessException;
import nc.vo.so.m4331.entity.DeliveryCheckVO;

import nc.pubitf.so.m4331.qc.mc003.IRewrite4331ForC003;

import nc.bs.so.m4331.quality.DeleteDeliverycheckBP;
import nc.bs.so.m4331.quality.InsertDeliverycheckBP;

import nc.pubimpl.so.m4331.qc.mc003.rule.FullDeliveryCheckRule;

public class Rewrite4331ForC003Impl implements IRewrite4331ForC003 {

  @Override
  public void rewriteOnApprove(DeliveryCheckVO[] vos) throws BusinessException {
    // 质检单回写，直接调用质检表的新增方法
    InsertDeliverycheckBP bp = new InsertDeliverycheckBP();
    bp.insert(vos);
  }

  @Override
  public void rewriteOnUnApprove(DeliveryCheckVO[] vos)
      throws BusinessException {
    // 根据发货单表体id
    FullDeliveryCheckRule rule = new FullDeliveryCheckRule();
    DeliveryCheckVO[] newvos = rule.getFullInfo(vos);
    DeleteDeliverycheckBP delete = new DeleteDeliverycheckBP();
    delete.delete(newvos, false);
  }
}
