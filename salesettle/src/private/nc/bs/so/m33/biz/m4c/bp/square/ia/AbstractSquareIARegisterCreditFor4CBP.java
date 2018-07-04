package nc.bs.so.m33.biz.m4c.bp.square.ia;

import nc.bs.so.m33.biz.m4c.rule.toia.FillDataForToREGCredit;
import nc.bs.so.m33.biz.m4c.rule.toia.ToIABizFor4CRule;
import nc.bs.so.m33.biz.m4c.rule.toia.ToIACheckFor4CRule;
import nc.bs.so.m33.maintain.m4c.InsertSquareOutDetailBP;
import nc.bs.so.m33.maintain.m4c.rule.detail.RewriteIARegsiterFor4CRule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.ia.mi5.entity.I5BillVO;
import nc.vo.scmpub.res.billtype.IABillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;

/**
 * 销售出库单传发出商品贷方
 * 
 * @since 6.0
 * @version 2011-7-21 上午09:29:53
 * @author zhangcheng
 */
public abstract class AbstractSquareIARegisterCreditFor4CBP {

  /**
   * 传发出商品贷方
   * 
   * @param bluevos
   * @param redvos
   */
  public void square(SquareOutVO[] sqvos) {
    AroundProcesser<SquareOutVO> bprocesser =
        new AroundProcesser<SquareOutVO>(BPPlugInPoint.SquareToIARegisterCreditBy4C);

    // 增加执行前业务规则
    this.addBeforeRule(bprocesser);

    AroundProcesser<SquareOutDetailVO> aprocesser =
        new AroundProcesser<SquareOutDetailVO>(BPPlugInPoint.SquareToIARegisterCreditBy4CDetail);

    // 增加执行后业务规则
    this.addAfterRule(aprocesser);

    // 销售出库单传发出商品前执行业务规则
    bprocesser.before(sqvos);

    // 将传发出商品结算单VO转化为传发出商品结算明细VO
    SquareOutDetailVO[] bills =
        SquareOutVOUtils.getInstance().changeSQVOtoSQDVOForREGCredit(sqvos);

    // 发出商品明细保存BP
    new InsertSquareOutDetailBP().insert(sqvos, bills);

    // 进行vo交换
    I5BillVO[] i5vos =
        (I5BillVO[]) PfServiceScmUtil.executeVOChange(
            SOBillType.SquareOut.getCode(), IABillType.XSCBJZ.getCode(), sqvos);

    // 传发出商品贷方
    this.toIA(i5vos);

    aprocesser.after(bills);
  }

  /**
   * 传发出商品贷方，目前分为两个场景：出库对冲、结算关闭
   * 
   * @param i5vos
   */
  protected abstract void toIA(I5BillVO[] i5vos);

  private void addAfterRule(AroundProcesser<SquareOutDetailVO> processer) {
    // 回写累计成本结算数量
    IRule<SquareOutDetailVO> rule = new RewriteIARegsiterFor4CRule();
    processer.addAfterRule(rule);
  }

  private void addBeforeRule(AroundProcesser<SquareOutVO> processer) {
    // 设置传贷方数据补充
    IRule<SquareOutVO> rule = new FillDataForToREGCredit();
    processer.addBeforeRule(rule);

    // 检查成本域
    rule = new ToIACheckFor4CRule();
    processer.addBeforeRule(rule);

    // 传存货核算数据业务处理
    rule = new ToIABizFor4CRule();
    processer.addBeforeRule(rule);
  }

}
