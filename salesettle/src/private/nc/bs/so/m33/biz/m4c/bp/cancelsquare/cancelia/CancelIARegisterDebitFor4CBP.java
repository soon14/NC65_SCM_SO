package nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelia;

import java.util.List;

import nc.bs.so.m33.maintain.m4c.DeleteSquareOutDetailBP;
import nc.bs.so.m33.maintain.m4c.rule.detail.RewriteIARegsiterFor4CRule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.so.m33.biz.canclesquare.ICancelSquareAction;
import nc.itf.so.m33.ref.ia.mi5.IAI5For4CServicesUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.pub.votools.SoVoTools;

public class CancelIARegisterDebitFor4CBP implements
    ICancelSquareAction<SquareOutDetailVO> {

  @Override
  public void cancelSquare(List<SquareOutDetailVO> list) {
    if (list == null || list.size() == 0) {
      return;
    }

    try {
      SquareOutDetailVO[] vos = list.toArray(new SquareOutDetailVO[0]);

      AroundProcesser<SquareOutDetailVO> processer =
          new AroundProcesser<SquareOutDetailVO>(
              BPPlugInPoint.CancelIARegisterFor4CBP);

      // 增加执行前业务规则
      this.addBeforeRule(processer);

      processer.before(vos);

      // 取消传发出商品
      IAI5For4CServicesUtil.deleteI5ForSO4CUnIntransit(
          SoVoTools.getVOsOnlyValues(vos, SquareOutDetailVO.CSQUAREBILLID),
          SoVoTools.getVOPKValues(vos));

      // 取消结算明细
      new DeleteSquareOutDetailBP().delete(vos);

      processer.after(vos);

    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

  }

  private void addBeforeRule(AroundProcesser<SquareOutDetailVO> processer) {
    // 回写累计发出商品数据
    IRule<SquareOutDetailVO> rule = new RewriteIARegsiterFor4CRule();
    processer.addAfterRule(rule);
  }

}
