package nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelar;

import java.util.List;

import nc.bs.so.m33.biz.m4c.rule.toar.SquareAROpenFor4CRule;
import nc.bs.so.m33.maintain.m4c.DeleteSquareOutDetailBP;
import nc.bs.so.m33.maintain.m4c.rule.detail.RewriteARIncomeFor4CRule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.arap.ArapServicesForSOUtil;
import nc.itf.so.m33.biz.canclesquare.ICancelSquareAction;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.pub.votools.SoVoTools;

public class CancelARIncomeFor4CBP implements
ICancelSquareAction<SquareOutDetailVO> {

  @Override
  public void cancelSquare(List<SquareOutDetailVO> list) {

    if (list == null || list.size() == 0) {
      return;
    }

    SquareOutDetailVO[] vos = list.toArray(new SquareOutDetailVO[0]);

    AroundProcesser<SquareOutVO> processer =
        new AroundProcesser<SquareOutVO>(BPPlugInPoint.CancelARIncomeFor4CBP);

    // 增加执行后业务规则
    this.addAfterRule(processer);

    SquareOutVO[] svos =
        (SquareOutVO[]) BSContext.getInstance().getSession(
            SquareOutVO.class.getName());

    processer.before(svos);

    // 取消传财务确认应收
    ArapServicesForSOUtil.delApBillBySOSquareDid(SoVoTools.getVOPKValues(vos));

    this.cancelDetail(vos);

    processer.after(svos);

  }

  private void addAfterRule(AroundProcesser<SquareOutVO> processer) {
    IRule<SquareOutVO> rule = new SquareAROpenFor4CRule();
    processer.addAfterRule(rule);
  }

  private void cancelDetail(SquareOutDetailVO[] bills) {
    AroundProcesser<SquareOutDetailVO> processer =
        new AroundProcesser<SquareOutDetailVO>(
            BPPlugInPoint.CancelARIncomeFor4CDetailBP);
    IRule<SquareOutDetailVO> rule = null;

    // 取消结算明细
    new DeleteSquareOutDetailBP().delete(bills);

    // 回写累计应收结算数量
    rule = new RewriteARIncomeFor4CRule();
    processer.addAfterRule(rule);
    processer.after(bills);
  }

}
