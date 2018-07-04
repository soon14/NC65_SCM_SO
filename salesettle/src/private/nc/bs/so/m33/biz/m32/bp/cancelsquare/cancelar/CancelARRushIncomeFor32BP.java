package nc.bs.so.m33.biz.m32.bp.cancelsquare.cancelar;

import java.util.List;

import nc.bs.so.m33.maintain.m32.DeleteSquare32DetailBP;
import nc.bs.so.m33.maintain.m32.rule.detail.RewriteARRushIncomeFor32Rule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.arap.ArapServicesForSOUtil;
import nc.itf.so.m33.biz.canclesquare.ICancelSquareAction;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.tool.performance.DeepCloneTool;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvDetailVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.pub.util.AggVOUtil;

public class CancelARRushIncomeFor32BP implements
ICancelSquareAction<SquareInvDetailVO> {

  @Override
  public void cancelSquare(List<SquareInvDetailVO> list) {
    if (list == null || list.size() == 0) {
      return;
    }

    SquareInvDetailVO[] vos = list.toArray(new SquareInvDetailVO[0]);

    AroundProcesser<SquareInvDetailVO> processer =
        new AroundProcesser<SquareInvDetailVO>(
            BPPlugInPoint.CancelARRushIncomeFor32BP);

    // 增加执行前业务规则
    this.addAfterRule(processer);

    SquareInvVO[] newsvos =
        (SquareInvVO[]) BSContext.getInstance().getSession(
            SquareInvVO.class.getName());

    SquareInvVO[] svos = (SquareInvVO[]) new DeepCloneTool().deepClone(newsvos);

    // 设置本次取消结算数量,由于回冲数量和其他结算数量反向，这里再次取反
    for (SquareInvVO svo : svos) {
      for (SquareInvBVO bvo : svo.getChildrenVO()) {
        bvo.setNthisnum(MathTool.oppose(bvo.getNthisnum()));
        bvo.setNorigtaxmny(MathTool.oppose(bvo.getNorigtaxmny()));
      }
    }

    // 取消传财务确认应收
    ArapServicesForSOUtil.unEstVerify(AggVOUtil.getDistinctFieldArray(vos,
        SquareInvDetailVO.PROCESSEID, String.class));

    // 取消结算明细
    new DeleteSquare32DetailBP().delete(vos);

    processer.after(vos);
  }

  private void addAfterRule(AroundProcesser<SquareInvDetailVO> processer) {
    // 回写累计应收结算数据
    IRule<SquareInvDetailVO> rule = new RewriteARRushIncomeFor32Rule();
    processer.addAfterRule(rule);
  }

}
