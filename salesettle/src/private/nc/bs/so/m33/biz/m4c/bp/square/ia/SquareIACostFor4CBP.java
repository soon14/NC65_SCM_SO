package nc.bs.so.m33.biz.m4c.bp.square.ia;

import nc.bs.so.m33.biz.m4c.rule.toia.SquareIACloseFor4CRule;
import nc.bs.so.m33.biz.m4c.rule.toia.ToIABizFor4CRule;
import nc.bs.so.m33.biz.m4c.rule.toia.ToIACheckFor4CRule;
import nc.bs.so.m33.maintain.m4c.InsertSquareOutDetailBP;
import nc.bs.so.m33.maintain.m4c.rule.detail.RewriteIACostFor4CRule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.so.m33.ref.ia.mi5.IAI5For4CServicesUtil;
import nc.itf.so.m33.ref.pcia.m4635.PCIA4635For4CServicesUtil;
import nc.vo.ia.mi5.entity.I5BillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.IABillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;

public class SquareIACostFor4CBP {

  public void square(SquareOutVO[] sqvos) {
    try {
      AroundProcesser<SquareOutVO> processer =
          new AroundProcesser<SquareOutVO>(BPPlugInPoint.SquareToIABy4C);

      // 增加执行前业务规则
      this.addBeforeRule(processer);

      // 增加执行后业务规则
      this.addAfterRule(processer);

      // 销售出库单传成本结算前执行业务规则
      processer.before(sqvos);

      // 将传成本结算单VO转化为传成本结算明细VO
      SquareOutDetailVO[] bills =
          SquareOutVOUtils.getInstance().changeSQVOtoSQDVOForIA(sqvos);

      this.saveDetail(sqvos, bills);

      // 进行vo交换
      I5BillVO[] i5vos =
          (I5BillVO[]) PfServiceScmUtil.executeVOChange(
              SOBillType.SquareOut.getCode(), IABillType.XSCBJZ.getCode(),
              sqvos);

      // 调用I5的保存接口
      IAI5For4CServicesUtil.insertI5ForSO4CSettle(i5vos);

      // 调用利润中心存货销售成本结转单的保存接口 add by zhangby5 for 65
      PCIA4635For4CServicesUtil.insert4635ForSO4CSettle(sqvos);

      // 销售出库单传成本结算前执行业务规则
      processer.after(sqvos);

    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void addAfterRule(AroundProcesser<SquareOutVO> processer) {
    IRule<SquareOutVO> rule = null;
    rule = new SquareIACloseFor4CRule();
    processer.addAfterRule(rule);
  }

  private void addBeforeRule(AroundProcesser<SquareOutVO> processer) {
    // 检查成本域
    IRule<SquareOutVO> rule = new ToIACheckFor4CRule();
    processer.addBeforeRule(rule);

    // 传存货核算数据业务处理
    rule = new ToIABizFor4CRule();
    processer.addBeforeRule(rule);
  }

  private void saveDetail(SquareOutVO[] sqvos, SquareOutDetailVO[] bills) {
    AroundProcesser<SquareOutDetailVO> processer =
        new AroundProcesser<SquareOutDetailVO>(BPPlugInPoint.SquareToIABy4CDetail);
    IRule<SquareOutDetailVO> rule = null;

    // 成本结算明细保存BP
    new InsertSquareOutDetailBP().insert(sqvos, bills);

    // 回写累计应收结算数量
    rule = new RewriteIACostFor4CRule();
    processer.addAfterRule(rule);
    processer.after(bills);
  }

}
