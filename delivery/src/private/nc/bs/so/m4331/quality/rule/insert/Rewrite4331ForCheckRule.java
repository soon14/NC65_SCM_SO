package nc.bs.so.m4331.quality.rule.insert;

import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryCheckVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * @description
 * 质检回写发货单子表 回写发货单表体累计合格数量、不合格数量是否质检结束
 * @scene
 * 质检回写发货单子表保存前
 * @param
 * 无
 * @author 祝会征
 * @since 6.0
 * @time 2010-01-28 下午13:49:07
 */
public class Rewrite4331ForCheckRule {

  public void rewrite4331(DeliveryViewVO[] views, DeliveryCheckVO[] checkvos) {
    // 把累计合格数量、累计不合格数量设置到发货单表体
    this.setNum(checkvos, views);
    this.rewrite(views);
  }

  /*
   * 更新发货单子表中累计合格数量和累计不合格数量
   */
  private void rewrite(DeliveryViewVO[] views) {
    String[] names =
        new String[] {
          DeliveryBVO.NTOTALELIGNUM, DeliveryBVO.NTOTALUNELIGNUM,
          DeliveryBVO.BQUALITYFLAG
        };
    ViewUpdate<DeliveryViewVO> bo = new ViewUpdate<DeliveryViewVO>();
    bo.update(views, DeliveryBVO.class, names);
  }

  /*
   * 设置发货单子表中的累计合格和累计不合格数量
   */
  private void setNum(DeliveryCheckVO[] checkvos, DeliveryViewVO[] views) {
    for (DeliveryViewVO view : views) {
      String id = view.getItem().getCdeliverybid();
      // 合格数量
      UFDouble elignum = null;
      // 不合格数量
      UFDouble unelignum = null;
      for (DeliveryCheckVO vo : checkvos) {
        String id1 = vo.getCdeliverybid();
        if (id1.equals(id)) {
          // 是否合格标记
          if (vo.getBeligflag().booleanValue()) {
            elignum = MathTool.add(elignum, vo.getNnum());
          }
          else {
            unelignum = MathTool.add(unelignum, vo.getNnum());
          }
        }
      }
      view.getItem().setNtotalelignum(MathTool.oppose(elignum));
      view.getItem().setNtotalunelignum(MathTool.oppose(unelignum));
      view.getItem().setBqualityflag(UFBoolean.TRUE);
    }
  }
}
