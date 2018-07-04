package nc.bs.so.m33.biz.m4453.bp.cancelsquare.cancelia;

import java.util.List;

import nc.bs.so.m33.maintain.m4453.DeleteSquareWasDetailBP;
import nc.bs.so.m33.maintain.m4453.rule.detail.RewriteIARegsiterFor4453Rule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.so.m33.biz.canclesquare.ICancelSquareAction;
import nc.itf.so.m33.ref.ia.mi5.IAI5For4453ServicesUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m33.m4453.entity.SquareWasDetailVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;
import nc.vo.so.pub.votools.SoVoTools;

public class CancelIARegisterFor4453BP implements
ICancelSquareAction<SquareWasDetailVO> {

  @Override
  public void cancelSquare(List<SquareWasDetailVO> list) {

    if (list == null || list.size() == 0) {
      return;
    }

    try {
      SquareWasDetailVO[] vos = list.toArray(new SquareWasDetailVO[0]);

      AroundProcesser<SquareWasVO> processer =
          new AroundProcesser<SquareWasVO>(
              BPPlugInPoint.CancelIARegisterFor4453BP);

      SquareWasVO[] svos =
          (SquareWasVO[]) BSContext.getInstance().getSession(
              SquareWasVO.class.getName());

      processer.before(svos);

      // 取消传发出商品
      IAI5For4453ServicesUtil.deleteI5ForSO4453UnIntransit(
          SoVoTools.getVOsOnlyValues(vos, SquareWasDetailVO.CSQUAREBILLID),
          SoVoTools.getVOPKValues(vos));

      this.cancelDetail(vos);

      processer.after(svos);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void cancelDetail(SquareWasDetailVO[] vos) {
    AroundProcesser<SquareWasDetailVO> processer =
        new AroundProcesser<SquareWasDetailVO>(
            BPPlugInPoint.CancelIARegisterFor4453DetailBP);

    // 取消结算明细
    new DeleteSquareWasDetailBP().delete(vos);

    IRule<SquareWasDetailVO> rule = null;
    // 回写累计应收结算数量
    rule = new RewriteIARegsiterFor4453Rule();
    processer.addAfterRule(rule);
    processer.after(vos);
  }

}
