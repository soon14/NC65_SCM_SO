package nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelia;

import java.util.List;

import nc.bs.so.m33.biz.m4c.rule.toia.SquareIAOpenFor4CRule;
import nc.bs.so.m33.maintain.m4c.DeleteSquareOutDetailBP;
import nc.bs.so.m33.maintain.m4c.rule.detail.RewriteIACostFor4CRule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.so.m33.biz.canclesquare.ICancelSquareAction;
import nc.itf.so.m33.ref.ia.mi5.IAI5For4CServicesUtil;
import nc.itf.so.m33.ref.pcia.m4635.PCIA4635For4CServicesUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.pub.votools.SoVoTools;

public class CancelIACostFor4CBP implements
ICancelSquareAction<SquareOutDetailVO> {

  @Override
  public void cancelSquare(List<SquareOutDetailVO> list) {

    if (list == null || list.size() == 0) {
      return;
    }

    try {

      SquareOutDetailVO[] vos = list.toArray(new SquareOutDetailVO[0]);

      AroundProcesser<SquareOutVO> processer =
          new AroundProcesser<SquareOutVO>(BPPlugInPoint.CancelIACostFor4CBP);

      // 增加执行后业务规则
      this.addAfterRule(processer);

      SquareOutVO[] svos =
          (SquareOutVO[]) BSContext.getInstance().getSession(
              SquareOutVO.class.getName());

      processer.before(svos);

      // 取消传成本结算
      IAI5For4CServicesUtil.deleteI5ForSO4CUnSettle(
          SoVoTools.getVOsOnlyValues(vos, SquareOutDetailVO.CSQUAREBILLID),
          SoVoTools.getVOPKValues(vos));

      // 取消传成本结算时删除对应存货单据 add by zhangby5 for 65
      PCIA4635For4CServicesUtil.delete4635ForSO4CUnSettle(
          SoVoTools.getVOsOnlyValues(vos, SquareOutDetailVO.CSQUAREBILLID),
          SoVoTools.getVOPKValues(vos));

      this.cancelDetail(vos);

      processer.after(svos);

    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void addAfterRule(AroundProcesser<SquareOutVO> processer) {
    IRule<SquareOutVO> rule = new SquareIAOpenFor4CRule();
    processer.addAfterRule(rule);
  }

  private void cancelDetail(SquareOutDetailVO[] bills) {
    AroundProcesser<SquareOutDetailVO> processer =
        new AroundProcesser<SquareOutDetailVO>(
            BPPlugInPoint.CancelIACostFor4CDetailBP);
    IRule<SquareOutDetailVO> rule = null;

    // 取消结算明细
    new DeleteSquareOutDetailBP().delete(bills);

    // 回写累计成本结算数量
    rule = new RewriteIACostFor4CRule();
    processer.addAfterRule(rule);
    processer.after(bills);
  }

}
