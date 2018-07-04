package nc.pubimpl.so.m4331.ic.m4480.rule;

import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.pubitf.so.m4331.ic.m4480.RewritePara4331For4480;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * 预留回写发货单前规则
 * 
 * @since 6.0
 * @version 2011-3-25 下午05:57:02
 * @author 祝会征
 */
public class RewriteBeforeRule {

  private Map<String, RewritePara4331For4480> index;

  public void process(DeliveryViewVO[] views,
      Map<String, RewritePara4331For4480> index2) {
    this.index = index2;
    for (DeliveryViewVO view : views) {
      this.checkRedBill(view);
      // 预留的出库单修改的时候 先回写预留数量 此时还没回写出库数量 所以在这里校验没有作用 出错
      // this.checkReqrsNumRange(view);
      this.setReqrsNum(view);
    }
  }

  private void checkRedBill(DeliveryViewVO view) {
    DeliveryBVO body = view.getItem();
    if (MathTool.compareTo(body.getNnum(), UFDouble.ZERO_DBL) < 0) {
      String message =
          NCLangResOnserver.getInstance().getStrByID("4006002_0",
              "04006002-0146")/*数量 < 0：红字发货单不能被预留*/;
      ExceptionUtils.wrappBusinessException(message);
    }
  }

  private void checkReqrsNumRange(DeliveryViewVO vo) {
    DeliveryHVO head = vo.getHead();
    DeliveryBVO body = vo.getItem();
    RewritePara4331For4480 para = this.index.get(body.getCdeliverybid());

    UFDouble icNum =
        MathTool.add(MathTool.add(body.getNreqrsnum(), para.getNreqrsnum()),
            body.getNtotaloutnum());

    if (MathTool.compareTo(body.getNnum(), icNum) < 0) {
      String message =
          NCLangResOnserver.getInstance().getStrByID("4006002_0",
              "04006002-0147")/*数量 < 累计出库数量 + 预留数量*/;
      String location =
          NCLangResOnserver.getInstance().getStrByID("4006002_0",
              "04006002-0148", null, new String[] {
                head.getVbillcode(), body.getCrowno()
              })/*发货单{0}第{1}行*/;

      ExceptionUtils.wrappBusinessException(message, location);
    }
  }

  private void setReqrsNum(DeliveryViewVO vo) {
    DeliveryBVO body = vo.getItem();
    RewritePara4331For4480 para = this.index.get(body.getCdeliverybid());
    // 设置预留数量
    UFDouble nreqrsnum = body.getNreqrsnum();

    nreqrsnum = MathTool.add(nreqrsnum, para.getNreqrsnum());
    body.setNreqrsnum(nreqrsnum);
  }

}
