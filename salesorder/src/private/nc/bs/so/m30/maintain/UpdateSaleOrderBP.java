package nc.bs.so.m30.maintain;

import nc.bs.scmpub.rule.CrossRuleValidateRule;
import nc.bs.so.m30.maintain.rule.update.RewriteBillUpdateRule;
import nc.bs.so.m30.maintain.rule.update.RewritePromotePriceUpdateRule;
import nc.bs.so.m30.plugin.BP30PlugInPoint;
import nc.bs.so.m30.rule.atp.SaleOrderVOATPAfterRule;
import nc.bs.so.m30.rule.atp.SaleOrderVOATPBeforeRule;
import nc.bs.so.m30.rule.billcode.CheckUniqueBillCodeRule;
import nc.bs.so.m30.rule.billcode.UpdateBillCodeRule;
import nc.bs.so.m30.rule.businessinfo.SaveTransferMsgRule;
import nc.bs.so.m30.rule.credit.RenovateARByHidsBeginRule;
import nc.bs.so.m30.rule.credit.RenovateARByHidsEndRule;
import nc.bs.so.m30.rule.feature.ClearMffileSRCRule;
import nc.bs.so.m30.rule.feature.FeatureSelectSaveRule;
import nc.bs.so.m30.rule.feature.RestMffileSRCRule;
import nc.bs.so.m30.rule.m35.ArsubOffsetBeforeSaveRule;
import nc.bs.so.m30.rule.m35.LrgCashMarCheckRule;
import nc.bs.so.m30.rule.maintaincheck.CheckCanUpdateWhenAuditing;
import nc.bs.so.m30.rule.maintaincheck.CheckDateRule;
import nc.bs.so.m30.rule.maintaincheck.CheckLrgTotalMoney;
import nc.bs.so.m30.rule.maintaincheck.CheckNumPriceMnyRule;
import nc.bs.so.m30.rule.maintaincheck.CheckSaveBillRule;
import nc.bs.so.m30.rule.maintaincheck.CheckSettleOrgRepeat;
import nc.bs.so.m30.rule.maintainprocess.FillupFretexchangeRule;
import nc.bs.so.m30.rule.maintainprocess.FillupRedundanceDataRule;
import nc.bs.so.m30.rule.maintainprocess.ThisGatheringRule;
import nc.bs.so.m30.rule.maintainprocess.UpdateSoBalanceWhenUpdateM30HeadRule;
import nc.bs.so.m30.rule.maintainprocess.UpdateSoBalanceWhenUpdateRule;
import nc.bs.so.m30.rule.reserve.AutoReserveRule;
import nc.bs.so.m30.rule.reserve.ReserveUpdateRule;
import nc.bs.so.m30.rule.rewrite.m28.RewriteForPriceAuditBillRule;
import nc.bs.so.m30.rule.rewrite.m28.RewriteForPriceAuditBillUpdateRule;
import nc.bs.so.m30.rule.rewrite.m5805.RewriteForM5805InsertRule;
import nc.bs.so.m30.rule.rewrite.m5805.RewriteForM5805UpdateRule;
import nc.bs.so.m30.rule.rewrite.price.RewritePriceFormRule;
import nc.bs.so.pub.rule.CheckApproverRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.credit.engrossmaintain.pub.action.M30EngrossAction;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.util.SetUpdateAuditInfoRule;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 修改保存动作
 * 
 * @author gdsjw
 */
public class UpdateSaleOrderBP {

  public SaleOrderVO[] update(SaleOrderVO[] bills, SaleOrderVO[] originBills) {
    CompareAroundProcesser<SaleOrderVO> processer =
        new CompareAroundProcesser<SaleOrderVO>(BP30PlugInPoint.UpdateBP);

    // 注入点
    TimeLog.logStart();
    this.addBeforeRule(processer);
    processer.before(bills, originBills);

    TimeLog.info("调用修改保存前BP插入点"); /* -=notranslate=- */

    TimeLog.logStart();
    BillUpdate<SaleOrderVO> bo = new BillUpdate<SaleOrderVO>();
    SaleOrderVO[] vos = bo.update(bills, originBills);

    TimeLog.info("保存修改单据到数据库"); /* -=notranslate=- */

    // 注入点
    TimeLog.logStart();
    this.addAfterRule(processer);
    processer.after(vos, originBills);

    TimeLog.info("调用修改保存后BP插入点"); /* -=notranslate=- */

    return vos;
  }

  private void addAfterRule(CompareAroundProcesser<SaleOrderVO> processer) {
    IRule<SaleOrderVO> rule = null;

    // 收款核销关系
    rule = new UpdateSoBalanceWhenUpdateRule();
    processer.addAfterRule(rule);

    // 信用
    rule = new RenovateARByHidsEndRule(M30EngrossAction.M30Edit);
    processer.addAfterRule(rule);

    // 本次收款金额规则
    rule = new ThisGatheringRule();
    processer.addAfterRule(rule);

    // 检查单据号是否重复
    rule = new CheckUniqueBillCodeRule();
    processer.addAfterRule(rule);

    // 回写来源和源头单据（回写报价单、合同、预订单、电子销售、销售订单、出库单、库存借出）
    ICompareRule<SaleOrderVO> compareRule = new RewriteBillUpdateRule();
    processer.addAfterRule(compareRule);

    // 调用内部交易信息
    rule = new SaveTransferMsgRule();
    processer.addAfterRule(rule);

    // 保存价格组成
    rule = new RewritePriceFormRule();
    processer.addAfterRule(rule);

    // 如果已经有收款核销关系，用订单总金额更新订单核销表头订单总金额
    rule = new UpdateSoBalanceWhenUpdateM30HeadRule();
    processer.addAfterRule(rule);

    boolean icEnable = SysInitGroupQuery.isICEnabled();
    if (icEnable) {
      // 可用量
      rule = new SaleOrderVOATPAfterRule();
      processer.addAfterRule(rule);
      // 调用预留调整
      rule = new ReserveUpdateRule();
      processer.addAfterRule(rule);
      // 自动预留
      rule = new AutoReserveRule();
      processer.addAfterRule(rule);
    }
    
    // 回写限量促销价格 jilu for 恒安限量促销
    if (SysInitGroupQuery.isPRICEEnabled()) {
      compareRule = new RewritePromotePriceUpdateRule();
      processer.addAfterRule(compareRule);
    }
    
    // 特征码行处理
    compareRule = new RestMffileSRCRule();
    processer.addAfterRule(compareRule);
    
    
     /**
      *回写价格审批单 wangzym 2017-06-06
     */
    compareRule = new RewriteForPriceAuditBillUpdateRule();
    processer.addAfterRule(compareRule);
    /**
     *回写历史代理协议 wangzym 2017-08-30
     */
    compareRule = new nc.bs.so.m30.rule.rewrite.LS41.RewriteForLS41BillUpdateRule();
    processer.addAfterRule(compareRule);
    
    //add by zhangjjs 2018-3-19
	//回写进口明细单据子表累计已生成销售订单行数量 vbdef14
    compareRule = new RewriteForM5805UpdateRule();
	processer.addAfterRule(compareRule);
  }

  private void addBeforeRule(CompareAroundProcesser<SaleOrderVO> processer) {
    IRule<SaleOrderVO> rule = null;
    
    // 检查参数是否支持审批中修改
    rule = new CheckCanUpdateWhenAuditing();
    processer.addBeforeRule(rule);
    
    // add by wangshu6 for 636 2014-01-20 销售订单审批中支持修订
    // 检查当前操作人是不是审批人， 如果是审批中状态并且当前操作人不是审批人，不允许修改
    rule = new CheckApproverRule();
    processer.addBeforeRule(rule);
    // end
    
    
    // 补全数据规则
    rule = new FillupRedundanceDataRule();
    processer.addBeforeRule(rule);
    
    // 补全退换货标记
    rule = new FillupFretexchangeRule();
    processer.addBeforeRule(rule);

    // 补全审计信息:最后修改人、最后修改时间
    rule = new SetUpdateAuditInfoRule<SaleOrderVO>();
    processer.addBeforeRule(rule);

    // 修改时单据号规则
    ICompareRule<SaleOrderVO> compareRule = new UpdateBillCodeRule();
    processer.addBeforeRule(compareRule);

    // 数量单价金额检查规则
    rule = new CheckNumPriceMnyRule();
    processer.addBeforeRule(rule);
    // 检查表体财务组织是否一致
    rule = new CheckSettleOrgRepeat();
    processer.addBeforeRule(rule);
    // 日期检查规则
    /**wangzym 2017-06-20
	 * 根据鞍钢项目组宋国强要求，去掉关于日期的检查规则
	 * 
	 */
    /*rule = new CheckDateRule();
    processer.addBeforeRule(rule);*/
    
    // 订单检查规则
    rule = new CheckSaveBillRule();
    processer.addBeforeRule(rule);

    // 信用
    rule = new RenovateARByHidsBeginRule(M30EngrossAction.M30Edit);
    processer.addBeforeRule(rule);

    boolean icEnable = SysInitGroupQuery.isICEnabled();
    if (icEnable) {
      // 可用量
      rule = new SaleOrderVOATPBeforeRule();
      processer.addBeforeRule(rule);
    }

    // 销售订单保存时物料赠品兑付范围检查
    rule = new LrgCashMarCheckRule();
    processer.addBeforeRule(rule);

    // 销售订单保存时赠品兑付费用单冲抵保存前处理
    rule = new ArsubOffsetBeforeSaveRule();
    processer.addBeforeRule(rule);

    // 检查表头和表体赠品价税合计是否一致
    rule = new CheckLrgTotalMoney();
    processer.addBeforeRule(rule);
    
    // 销售订单特征码选配保存
    rule = new FeatureSelectSaveRule();
    processer.addBeforeRule(rule);
    
    //交叉校验规则
    rule = new CrossRuleValidateRule<SaleOrderVO>();
    processer.addBeforeRule(rule);
    
    // 特征码行处理
    compareRule = new ClearMffileSRCRule();
    processer.addBeforeRule(compareRule);
  
  }

}
