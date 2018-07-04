package nc.bs.so.m4331.quality.rule.delete;

import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * @description
 * 销售发货单质检信息删除前，清空发货单累计合格数量和累计不合格数量
 * @scene
 * 销售发货单质检信息删除前
 * @param
 * 无
 * @since 6.0
 * @version 2010-12-30 下午07:47:15
 * @author 祝会征
 */
public class Rewrite4331OnDeleteRule {
  /**
   * 清空累计合格数量和累计不合格数量
   * 
   * @param views
   */
  public void rewrite4331(DeliveryViewVO[] views) {
    for (DeliveryViewVO view : views) {
      view.getItem().setNtotalelignum(null);
      view.getItem().setNtotalunelignum(null);
      view.getItem().setBqualityflag(UFBoolean.FALSE);
    }
    String[] names =
        new String[] {
          DeliveryBVO.NTOTALELIGNUM, DeliveryBVO.NTOTALUNELIGNUM,
          DeliveryBVO.BQUALITYFLAG
        };
    ViewUpdate<DeliveryViewVO> bo = new ViewUpdate<DeliveryViewVO>();
    bo.update(views, DeliveryBVO.class, names);
  }
}
