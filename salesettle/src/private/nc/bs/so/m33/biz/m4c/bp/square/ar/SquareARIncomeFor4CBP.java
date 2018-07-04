package nc.bs.so.m33.biz.m4c.bp.square.ar;

import nc.bs.arap.util.ArapFlowUtil;
import nc.bs.so.m33.biz.m4c.rule.toar.GetNewARorgVidFor4CRule;
import nc.bs.so.m33.biz.m4c.rule.toar.SquareARCloseFor4CRule;
import nc.bs.so.m33.biz.m4c.rule.toar.ToARCheckFor4CRule;
import nc.bs.so.m33.maintain.m4c.InsertSquareOutDetailBP;
import nc.bs.so.m33.maintain.m4c.rule.detail.RewriteARIncomeFor4CRule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.arap.fieldmap.IBillFieldGet;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.arap.receivable.AggReceivableBillVO;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.pub.exchange.ExchangeBillUtils;
import nc.vo.so.m33.pub.util.ARBillUtil;
import nc.vo.so.pub.util.ListUtil;

public class SquareARIncomeFor4CBP {

  public void square(SquareOutVO[] sqvos) {

    if (sqvos == null || sqvos.length == 0) {
      return;
    }

    AroundProcesser<SquareOutVO> processer =
        new AroundProcesser<SquareOutVO>(BPPlugInPoint.SquareARIncomeFor4C);

    // 增加执行前业务规则
    this.addBeforeRule(processer);

    // 增加执行后业务规则
    this.addAfterRule(processer);

    // 将传确认收入结算单VO转化为传确认收入结算明细VO
    SquareOutDetailVO[] bills =
        SquareOutVOUtils.getInstance().changeSQVOtoSQDVOForAR(sqvos);

    processer.before(sqvos);

    this.saveDetail(sqvos, bills);

    // 传应收
    this.toAR(sqvos);

    processer.after(sqvos);
  }

  private void addAfterRule(AroundProcesser<SquareOutVO> processer) {
    IRule<SquareOutVO> rule = new SquareARCloseFor4CRule();
    processer.addAfterRule(rule);
  }

  private void addBeforeRule(AroundProcesser<SquareOutVO> processer) {
    // 应收结算前获取应收组织最新组织版本
    IRule<SquareOutVO> rule = new GetNewARorgVidFor4CRule();
    processer.addBeforeRule(rule);

    rule = new ToARCheckFor4CRule();
    processer.addBeforeRule(rule);
  }

  private void saveDetail(SquareOutVO[] sqvos, SquareOutDetailVO[] bills) {
    AroundProcesser<SquareOutDetailVO> processer =
        new AroundProcesser<SquareOutDetailVO>(
            BPPlugInPoint.SquareARIncomeFor4CDetail);
    IRule<SquareOutDetailVO> rule = null;

    // 确认收入明细保存BP
    new InsertSquareOutDetailBP().insert(sqvos, bills);

    // 回写累计应收结算数量
    rule = new RewriteARIncomeFor4CRule();
    processer.addAfterRule(rule);
    processer.after(bills);
  }

  private void toAR(SquareOutVO[] sqvos) {
    // 上下游关系接口定义里取应收单交易类型，销售发票传应收找32到F0的
    String srcBillType = ICBillType.SaleOut.getCode();
    String destBillType = IBillFieldGet.F0;
    String squareBillType = SOBillType.SquareOut.getCode();
    AggReceivableBillVO[] arapvos =
        new ExchangeBillUtils<SquareOutVO, AggReceivableBillVO>(
            SquareOutVO.class).exchangeBill(sqvos, squareBillType, srcBillType,
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
