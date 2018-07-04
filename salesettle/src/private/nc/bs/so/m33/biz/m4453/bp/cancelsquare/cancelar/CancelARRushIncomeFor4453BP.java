package nc.bs.so.m33.biz.m4453.bp.cancelsquare.cancelar;

import java.util.List;

import nc.bs.so.m33.maintain.m4453.DeleteSquareWasDetailBP;
import nc.bs.so.m33.maintain.m4453.rule.detail.RewriteARRushIncomeFor4453Rule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.arap.ArapServicesForSOUtil;
import nc.itf.so.m33.biz.canclesquare.ICancelSquareAction;
import nc.vo.so.m33.m4453.entity.SquareWasDetailVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;
import nc.vo.so.pub.util.AggVOUtil;

public class CancelARRushIncomeFor4453BP implements
ICancelSquareAction<SquareWasDetailVO> {

  @Override
  public void cancelSquare(List<SquareWasDetailVO> list) {
    if (list == null || list.size() == 0) {
      return;
    }

    SquareWasDetailVO[] vos = list.toArray(new SquareWasDetailVO[0]);

    AroundProcesser<SquareWasVO> processer =
        new AroundProcesser<SquareWasVO>(
            BPPlugInPoint.CancelARRushIncomeFor4453BP);

    SquareWasVO[] svos =
        (SquareWasVO[]) BSContext.getInstance().getSession(
            SquareWasVO.class.getName());

    processer.before(svos);

    // 取消传财务确认应收
    ArapServicesForSOUtil.unEstVerify(AggVOUtil.getDistinctFieldArray(vos,
        SquareWasDetailVO.PROCESSEID, String.class));

    this.cancelDetail(vos);

    processer.after(svos);
  }

  private void cancelDetail(SquareWasDetailVO[] vos) {
    AroundProcesser<SquareWasDetailVO> processer =
        new AroundProcesser<SquareWasDetailVO>(
            BPPlugInPoint.CancelARRushIncomeFor4453DetailBP);

    // 取消结算明细
    new DeleteSquareWasDetailBP().delete(vos);

    IRule<SquareWasDetailVO> rule = null;
    // 回写累计应收结算数量
    rule = new RewriteARRushIncomeFor4453Rule();
    processer.addAfterRule(rule);
    processer.after(vos);
  }

}
