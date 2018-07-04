package nc.bs.so.m33.biz.m4453.bp.square.ia;

import java.util.List;

import nc.bs.so.m33.biz.m4453.rule.ia.ToIABizFor4453Rule;
import nc.bs.so.m33.biz.m4453.rule.ia.ToIACheckFor4453Rule;
import nc.bs.so.m33.biz.m4453.rule.push.CheckBeforeCostSquareWasRule;
import nc.bs.so.m33.maintain.m4453.InsertSquareWasDetailBP;
import nc.bs.so.m33.maintain.m4453.rule.detail.RewriteIARegsiterFor4453Rule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.so.m33.ref.ia.mi5.IAI5For4453ServicesUtil;
import nc.vo.ia.mi5.entity.I5BillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.IABillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m4453.entity.SquareWasDetailVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;
import nc.vo.so.m33.m4453.entity.SquareWasVOUtils;
import nc.vo.so.m33.m4453.entity.SquareWasViewVO;

public class SquareIARegisterFor4453BP {

  public void square(List<SquareWasViewVO> list) {
    if (list == null || list.size() == 0) {
      return;
    }
    SquareWasViewVO[] sqvvos = list.toArray(new SquareWasViewVO[0]);
    this.square(sqvvos);
  }

  public void square(SquareWasViewVO[] sqvvos) {
    try {

      SquareWasVO[] sqvos = SquareWasVOUtils.getInstance().combineBill(sqvvos);

      AroundProcesser<SquareWasVO> processer =
          new AroundProcesser<SquareWasVO>(BPPlugInPoint.SquareToRegisterBy4453);

      // 增加执行前业务规则
      this.addBeforeRule(processer);

      // 销售出库单传发出商品前执行业务规则
      processer.before(sqvos);

      // 将传发出商品结算单VO转化为传发出商品结算明细VO
      SquareWasDetailVO[] bills =
          SquareWasVOUtils.getInstance().changeSQVOtoSQDVOForREG(sqvos);

      this.saveDetail(sqvos, bills);

      // 进行vo交换
      I5BillVO[] i5vos =
          (I5BillVO[]) PfServiceScmUtil.executeVOChange(
              SOBillType.SquareWas.getCode(), IABillType.XSCBJZ.getCode(),
              sqvos);

      // 调用4453的销售出库单发出商品接口
      IAI5For4453ServicesUtil.insertI5ForSO4453Intransit(i5vos);

      processer.after(sqvos);

    }
    catch (BusinessException e) {

      ExceptionUtils.wrappException(e);
    }
  }

  private void addBeforeRule(AroundProcesser<SquareWasVO> processer) {
    // 途损单成本结算时判断是否源头订单行成本结算关闭
    IRule<SquareWasVO> rule = new CheckBeforeCostSquareWasRule();
    processer.addBeforeRule(rule);

    // 检查成本域
    rule = new ToIACheckFor4453Rule();
    processer.addBeforeRule(rule);

    // 传存货核算数据业务处理
    rule = new ToIABizFor4453Rule();
    processer.addBeforeRule(rule);
  }

  private void saveDetail(SquareWasVO[] sqvos, SquareWasDetailVO[] bills) {
    AroundProcesser<SquareWasDetailVO> processer =
        new AroundProcesser<SquareWasDetailVO>(BPPlugInPoint.SquareToRegisterBy4453Detail);

    // 发出商品明细保存BP
    new InsertSquareWasDetailBP().insert(sqvos, bills);

    IRule<SquareWasDetailVO> rule = null;
    // 回写累计应收结算数量
    rule = new RewriteIARegsiterFor4453Rule();
    processer.addAfterRule(rule);
    processer.after(bills);
  }

}
