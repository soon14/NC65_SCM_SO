package nc.bs.so.m30.maintain;

import nc.bs.so.m30.maintain.rule.delete.RewriteBillDeleteRule;
import nc.bs.so.m30.maintain.rule.delete.RewritePromotePriceDeleteRule;
import nc.bs.so.m30.plugin.BP30PlugInPoint;
import nc.bs.so.m30.rule.atp.SaleOrderVOATPAfterRule;
import nc.bs.so.m30.rule.atp.SaleOrderVOATPBeforeRule;
import nc.bs.so.m30.rule.billcode.ReturnBillCodeRule;
import nc.bs.so.m30.rule.businessinfo.DeleteTransferMsgRule;
import nc.bs.so.m30.rule.credit.RenovateARByHidsBeginRule;
import nc.bs.so.m30.rule.credit.RenovateARByHidsEndRule;
import nc.bs.so.m30.rule.feature.FeatureSelectDeleteRule;
import nc.bs.so.m30.rule.maintaincheck.CheckDeletableRule;
import nc.bs.so.m30.rule.maintainprocess.DeletePriceFormWhenDelRule;
import nc.bs.so.m30.rule.maintainprocess.DeleteSoBalanceWhenDelRule;
import nc.bs.so.m30.rule.pu.Rewrite21DeleteRule;
import nc.bs.so.m30.rule.reserve.ReserveDeleteRule;
import nc.bs.so.m30.rule.rewrite.LS41.DelRewriteForLS41BillRule;
import nc.bs.so.m30.rule.rewrite.m28.DelRewriteForPriceAuditBillRule;
import nc.bs.so.m30.rule.rewrite.m35.Rewrite35WhenDelete;
import nc.bs.so.m30.rule.rewrite.m5805.RewriteForM5805DeleteRule;
import nc.bs.so.m30.rule.rewrite.m5805.RewriteForM5805InsertRule;
import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.credit.engrossmaintain.pub.action.M30EngrossAction;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 删除BP
 * 
 * @author gdsjw
 */
public class DeleteSaleOrderBP {

  public SaleOrderVO[] delete(SaleOrderVO[] bills) {
    AroundProcesser<SaleOrderVO> processer =
        new AroundProcesser<SaleOrderVO>(BP30PlugInPoint.DeleteBP);

    // 注入点
    TimeLog.logStart();
    this.addBeforeRule(processer);
    processer.before(bills);

    TimeLog.info("调用删除前BP插入点"); /*-=notranslate=-*/

    TimeLog.logStart();
    BillDelete<SaleOrderVO> bo = new BillDelete<SaleOrderVO>();
    bo.delete(bills);

    TimeLog.info("写数据库，删除单据"); /*-=notranslate=-*/

    // 注入点
    TimeLog.logStart();
    this.addAfterRule(processer);
    processer.after(bills);

    TimeLog.info("调用删除后BP插入点"); /*-=notranslate=-*/

    return bills;
  }

  private void addAfterRule(AroundProcesser<SaleOrderVO> processer) {
    IRule<SaleOrderVO> rule = null;
    // 收款核销关系
    rule = new DeleteSoBalanceWhenDelRule();
    processer.addAfterRule(rule);

    // 删除价格组成
    rule = new DeletePriceFormWhenDelRule();
    processer.addAfterRule(rule);

    // 信用
    rule = new RenovateARByHidsEndRule(M30EngrossAction.M30Delete);
    processer.addAfterRule(rule);

    // 退回单据号规则
    rule = new ReturnBillCodeRule();
    processer.addAfterRule(rule);

    // 回写来源和源头单据（回写报价单、合同、预订单、电子销售、销售订单、出库单、库存借出）
    rule = new RewriteBillDeleteRule();
    processer.addAfterRule(rule);

    // 回写采购订单
    rule = new Rewrite21DeleteRule();
    processer.addAfterRule(rule);

    // 回写销售费用单
    rule = new Rewrite35WhenDelete();
    processer.addAfterRule(rule);

    // 调用内部交易信息
    rule = new DeleteTransferMsgRule();
    processer.addAfterRule(rule);

    boolean icEnable = SysInitGroupQuery.isICEnabled();
    if (icEnable) {
      // 可用量
      rule = new SaleOrderVOATPAfterRule();
      processer.addAfterRule(rule);
    }
    
    // 回写促销价格表 jilu for 恒安限量促销
    if (SysInitGroupQuery.isPRICEEnabled()) {
      rule = new RewritePromotePriceDeleteRule();
      processer.addAfterRule(rule);
    }
    
    // 删除特征码档案来源信息
    rule = new FeatureSelectDeleteRule();
    processer.addAfterRule(rule);
    /**
     * wangzym	增加回写价格审批单 2017-06-07
     */
    rule=new DelRewriteForPriceAuditBillRule();
    processer.addAfterRule(rule);
    /**
     * wangzym	增加回历史代理协议2017-08-31
     */
    rule=new DelRewriteForLS41BillRule();
    processer.addAfterRule(rule);
    
    //add by zhangjjs 2018-3-19
	//回写进口明细单据子表累计已生成销售订单行数量 vbdef14
	rule = new RewriteForM5805DeleteRule();
	processer.addAfterRule(rule);
  }

  private void addBeforeRule(AroundProcesser<SaleOrderVO> processer) {
    // 删除前数据合法性校验
    IRule<SaleOrderVO> rule = new CheckDeletableRule();
    processer.addBeforeRule(rule);

    // 信用
    rule = new RenovateARByHidsBeginRule(M30EngrossAction.M30Delete);
    processer.addBeforeRule(rule);

    boolean icEnable = SysInitGroupQuery.isICEnabled();
    if (icEnable) {
      // 可用量
      rule = new SaleOrderVOATPBeforeRule();
      processer.addBeforeRule(rule);

      // 调用预留调整
      rule = new ReserveDeleteRule();
      processer.addBeforeRule(rule);
    }

  }

}
