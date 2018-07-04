package nc.bs.so.m30.revise;

import nc.bs.so.m30.plugin.BP30PlugInPoint;
import nc.bs.so.m30.revise.rule.CheckApprovebleRule;
import nc.bs.so.m30.revise.rule.ReviseApproveStateRule;
import nc.bs.so.m30.revise.rule.ReviseOutStateRule;
import nc.bs.so.m30.revise.rule.ReviseSendStateRule;
import nc.bs.so.m30.revise.rule.Rewrite4331WhenReviseRule;
import nc.bs.so.m30.revise.rule.Rewrite4CWhenReviseRule;
import nc.bs.so.m30.revise.rule.ReviseInvoiceStateRule;
import nc.bs.so.m30.rule.m35.ArsubOffsetAfterApproveRule;
import nc.bs.so.m30.rule.m35.ArsubOffsetUpdateRule;
import nc.impl.pubapp.bd.userdef.UserDefSaveRule;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.so.m30.revise.action.rule.ReviseFeatureCheckRule;
import nc.impl.so.m30.revise.action.rule.UpdateSaleorderProRule;
import nc.impl.so.m30.revise.action.rule.UpdateSobalanceRule;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 销售订单修订更新《销售订单表》UpdateBP
 * <p>
 * 修订updateBP内包含订单updateBP，修订updateBP处理修订逻辑
 * </p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author 刘志伟
 * @time 2010-8-11 下午01:27:22
 */
public class UpdateSaleOrderBP {

  public SaleOrderVO[] update(SaleOrderVO[] bills, SaleOrderVO[] originBills) {
    CompareAroundProcesser<SaleOrderVO> processer =
        new CompareAroundProcesser<SaleOrderVO>(BP30PlugInPoint.ReviseUpdateBP);
    // 注入点
    TimeLog.logStart();
    this.addBeforeRule(processer);
    processer.before(bills, originBills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0069")/* @res "调用修改保存前BP插入点" */);

    // 调用nc.bs.so.m30.maintain.UpdateSaleOrderBP完成销售订单更新
    TimeLog.logStart();
    nc.bs.so.m30.maintain.UpdateSaleOrderBP updateBP =
        new nc.bs.so.m30.maintain.UpdateSaleOrderBP();
    SaleOrderVO[] ret = updateBP.update(bills, originBills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0070")/* @res "保存修改单据到数据库" */);

    // 注入点
    TimeLog.logStart();
    this.addAfterRule(processer);
    processer.after(bills, originBills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0071")/* @res "调用修改保存后BP插入点" */);

    return ret;
  }

  private void addAfterRule(CompareAroundProcesser<SaleOrderVO> processer) {
    IRule<SaleOrderVO> rule = null;
    // 告知发货单上游销售订单已改价
    rule = new Rewrite4331WhenReviseRule();
    processer.addAfterRule(rule);
    // 告知出库单上游销售订单已改价
    rule = new Rewrite4CWhenReviseRule();
    processer.addAfterRule(rule);

    // 价税合计变化更新订单收款核销关系
    rule = new UpdateSobalanceRule();
    processer.addAfterRule(rule);

    ICompareRule<SaleOrderVO> compareRule = null;
    // 修订主数量打开出库
    compareRule = new ReviseOutStateRule();
    processer.addAfterRule(compareRule);
    // 修订主数量打开发货
    compareRule = new ReviseSendStateRule();
    processer.addAfterRule(compareRule);
    //修订主数量开发打开
    compareRule = new  ReviseInvoiceStateRule();
    processer.addAfterRule(compareRule);

    // 销售订单修订审批后对新增行订单状态的处理 add by zhangby5
    rule = new ReviseApproveStateRule();
    processer.addAfterRule(rule);

    // 审批后赠品兑付先删除 add by wangshu6 for 修订后更新赠品对付 2015041
    rule = new ArsubOffsetUpdateRule();
    processer.addAfterRule(rule);

    // 审批后进行赠品对付设置
    rule = new ArsubOffsetAfterApproveRule();
    processer.addAfterRule(rule);

    // 销售订单修订后，把最新VO插入订单收益表中
    rule = new UpdateSaleorderProRule();
    processer.addAfterRule(rule);
  }

  private void addBeforeRule(CompareAroundProcesser<SaleOrderVO> processer) {
    IRule<SaleOrderVO> rule = null;
    // 校验表头表体录入的自定义项是否启用
    rule = new UserDefSaveRule<SaleOrderVO>(new Class[] {
      SaleOrderHVO.class, SaleOrderBVO.class
    });
    processer.addBeforeRule(rule);

    ICompareRule<SaleOrderVO> comparerule = new CheckApprovebleRule();
    processer.addBeforeRule(comparerule);
    
    comparerule = new ReviseFeatureCheckRule();
    processer.addBeforeRule(comparerule);

  }
}
