package nc.bs.so.m33.biz.m32.bp.square.toar;

import nc.bs.arap.util.ArapFlowUtil;
import nc.bs.so.m33.biz.m32.rule.toar.GetNewARorgVidFor32Rule;
import nc.bs.so.m33.biz.m32.rule.toar.SquareARCloseFor32Rule;
import nc.bs.so.m33.biz.m32.rule.toar.ToARCheckFor32Rule;
import nc.bs.so.m33.maintain.m32.InsertSquare32DetailBP;
import nc.bs.so.m33.maintain.m32.rule.detail.RewriteARIncomeFor32Rule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.arap.fieldmap.IBillFieldGet;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.arap.receivable.AggReceivableBillVO;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m32.entity.SquareInvDetailVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;
import nc.vo.so.m33.pub.exchange.ExchangeBillUtils;
import nc.vo.so.m33.pub.util.ARBillUtil;
import nc.vo.so.pub.util.ListUtil;

public class SquareARIncomeFor32BP {

  public void square(SquareInvVO[] sqvos) {
    if (sqvos == null || sqvos.length == 0) {
      return;
    }
    AroundProcesser<SquareInvVO> processer =
        new AroundProcesser<SquareInvVO>(BPPlugInPoint.SquareARIncome);

    // 增加执行前业务规则
    this.addBeforeRule(processer);

    // 增加执行后业务规则
    this.addAfterRule(processer);

    // 将传确认收入结算单VO转化为传确认收入结算明细VO
    SquareInvDetailVO[] bills =
        SquareInvVOUtils.getInstance().changeSQVOtoSQDVOForAR(sqvos);

    processer.before(sqvos);

    // 确认收入明细保存BP
    this.saveDetail(sqvos, bills);

    // 传应收
    this.toAR(sqvos);

    processer.after(sqvos);
  }

  private void addAfterRule(AroundProcesser<SquareInvVO> processer) {
    IRule<SquareInvVO> rule = new SquareARCloseFor32Rule();
    processer.addAfterRule(rule);
  }

  private void addBeforeRule(AroundProcesser<SquareInvVO> processer) {
    // 应收结算前获取应收组织最新组织版本
    IRule<SquareInvVO> rule = new GetNewARorgVidFor32Rule();
    processer.addBeforeRule(rule);

    rule = new ToARCheckFor32Rule();
    processer.addBeforeRule(rule);
  }

  private void saveDetail(SquareInvVO[] sqvos, SquareInvDetailVO[] bills) {
    AroundProcesser<SquareInvDetailVO> processer =
        new AroundProcesser<SquareInvDetailVO>(
            BPPlugInPoint.SquareARIncomeDetail);

    // 确认收入明细保存BP
    new InsertSquare32DetailBP().insert(sqvos, bills);

    // 回写累计应收结算数据
    IRule<SquareInvDetailVO> rule = new RewriteARIncomeFor32Rule();
    processer.addAfterRule(rule);
    processer.after(bills);
  }

  private void toAR(SquareInvVO[] sqvos) {
    // 上下游关系接口定义里取应收单交易类型，销售发票传应收找32到F0的
    String srcBillType = SOBillType.Invoice.getCode();
    String destBillType = IBillFieldGet.F0;
    String squareBillType = SOBillType.SquareInvoice.getCode();
    AggReceivableBillVO[] arapvos =
        new ExchangeBillUtils<SquareInvVO, AggReceivableBillVO>(
            SquareInvVO.class).exchangeBill(sqvos, squareBillType, srcBillType,
                destBillType);
    MapList<String, AggReceivableBillVO> arapvoMapList =
        ARBillUtil.getInstance().splitArapVO(arapvos);
    for (String pk_org : arapvoMapList.keySet()) {
      // 调用收付单据保存脚本
      PfServiceScmUtil.processBatch(
          ArapFlowUtil.getCommitActionCode(pk_org, destBillType), destBillType,
          ListUtil.toArray(arapvoMapList.get(pk_org)), null, null);
    }
  }

}
