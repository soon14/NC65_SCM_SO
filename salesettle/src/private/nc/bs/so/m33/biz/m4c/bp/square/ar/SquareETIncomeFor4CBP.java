package nc.bs.so.m33.biz.m4c.bp.square.ar;

import nc.bs.so.m33.biz.m4c.rule.toar.GetNewARorgVidFor4CRule;
import nc.bs.so.m33.biz.m4c.rule.toar.ToARCheckFor4CRule;
import nc.bs.so.m33.maintain.m4c.InsertSquareOutDetailBP;
import nc.bs.so.m33.maintain.m4c.rule.detail.RewriteETIncomeFor4CRule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.arap.fieldmap.IBillFieldGet;
import nc.itf.scmpub.reference.arap.ArapServicesForSOUtil;
import nc.vo.arap.estireceivable.AggEstiReceivableBillVO;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.pub.exchange.ExchangeBillUtils;

public class SquareETIncomeFor4CBP {

  public void square(SquareOutVO[] sqvos) {
    if (sqvos == null || sqvos.length == 0) {
      return;
    }

    AroundProcesser<SquareOutVO> processer =
        new AroundProcesser<SquareOutVO>(BPPlugInPoint.SquareETIncome);

    // 增加执行前业务规则
    this.addBeforeRule(processer);

    // 将传暂估收入结算单VO转化为传暂估收入结算明细VO
    SquareOutDetailVO[] bills =
        SquareOutVOUtils.getInstance().changeSQVOtoSQDVOForET(sqvos);

    processer.before(sqvos);

    this.saveDetail(sqvos, bills);

    // 传暂估
    this.toET(sqvos);

    processer.after(sqvos);

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
        new AroundProcesser<SquareOutDetailVO>(BPPlugInPoint.SquareETIncomeDetail);
    IRule<SquareOutDetailVO> rule = null;
    // 暂估应收明细保存BP
    new InsertSquareOutDetailBP().insert(sqvos, bills);

    // 回写累计应收结算数量
    rule = new RewriteETIncomeFor4CRule();
    processer.addAfterRule(rule);
    processer.after(bills);
  }

  private void toET(SquareOutVO[] sqvos) {
    // 上下游关系接口定义里取应收单交易类型，销售发票传应收找32到F0的
    String srcBillType = ICBillType.SaleOut.getCode();
    String destBillType = IBillFieldGet.E0;
    String squareBillType = SOBillType.SquareOut.getCode();
    AggEstiReceivableBillVO[] arapvos =
        new ExchangeBillUtils<SquareOutVO, AggEstiReceivableBillVO>(
            SquareOutVO.class).exchangeBill(sqvos, squareBillType, srcBillType,
                destBillType);

    // 暂估应收传财务:调用I5的保存接口
    ArapServicesForSOUtil.saveEffectBill(arapvos);
  }

}
