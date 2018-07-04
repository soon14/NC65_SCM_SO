package nc.bs.so.m32.maintain;

import nc.bs.so.m32.maintain.rule.insert.AutoUniteInvoiceRule;
import nc.bs.so.m32.maintain.rule.insert.CheckBillCodeRule;
import nc.bs.so.m32.maintain.rule.insert.CheckBillValityRule;
import nc.bs.so.m32.maintain.rule.insert.CheckOppValityRule;
import nc.bs.so.m32.maintain.rule.insert.FillNewDefaultRule;
import nc.bs.so.m32.maintain.rule.insert.OppOffsetRule;
import nc.bs.so.m32.maintain.rule.insert.RewriteBillInsertRule;
import nc.bs.so.m32.maintain.rule.insert.UpdateOppFlagInsertRule;
import nc.bs.so.m32.plugin.BP32PlugInPoint;
import nc.bs.so.pub.rule.FillBillTailInfoRuleForIns;
import nc.impl.pubapp.bd.userdef.UserDefSaveRule;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.util.SetAddAuditInfoRule;
import nc.vo.scmpub.rule.FinanceOrgEnableCheckRule;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.rule.SaleInvoiceScaleCheckRule;
import nc.vo.so.m32.rule.SaveHyfjsdAfterRule;

/**
 * 销售发票新增保存bp
 * 
 * @since 6.3
 * @version 2012-12-21 上午09:12:10
 * @author yaogj
 */
public class InsertSaleInvoiceBP {

  /**
   * 
   * @param bills 发票vo
   * @return 保存后vo
   */
  public SaleInvoiceVO[] insert(SaleInvoiceVO[] bills) {
    AroundProcesser<SaleInvoiceVO> processer =
        new AroundProcesser<SaleInvoiceVO>(BP32PlugInPoint.InsertAction);

    // 增加执行前业务规则
    this.addBeforeRule(processer);

    // 增加执行后业务规则
    this.addAfterRule(processer);

    TimeLog.logStart();
    processer.before(bills);

    // "4006008_0", "04006008-0023"
    TimeLog.info("保存前执行业务规则"); /* -=notranslate=- */

    TimeLog.logStart();
    BillInsert<SaleInvoiceVO> bo = new BillInsert<SaleInvoiceVO>();
    SaleInvoiceVO[] vos = bo.insert(bills);
    // "4006008_0", "04006008-0024"
    TimeLog.info("保存单据到数据库"); /* -=notranslate=- */

    TimeLog.logStart();
    processer.after(vos);
    // "4006008_0", "04006008-0025"
    TimeLog.info("保存后执行业务规则"); /* -=notranslate=- */

    return vos;
  }

  /**
   * 方法功能描述：添加保存后执行规则。
   * <p>
   * <b>参数说明</b>
   * 
   * @param processer
   *          <p>
   * @author 冯加滨
   * @time 2010-1-21 下午05:08:24
   */
  private void addAfterRule(AroundProcesser<SaleInvoiceVO> processer) {
    // 单据号重复性校验
    IRule<SaleInvoiceVO> rule = new CheckBillCodeRule();
    processer.addAfterRule(rule);
    // 对冲生成发票更新来源发票对冲标志
    rule = new UpdateOppFlagInsertRule();
    processer.addAfterRule(rule);
    // 回写发票来源单据
    rule = new RewriteBillInsertRule();
    processer.addAfterRule(rule);

    // 生成对冲发票时反冲抵
    rule = new OppOffsetRule();
    processer.addAfterRule(rule);
    
  }

  /**
   * 方法功能描述：添加保存前执行规则。
   * <p>
   * <b>参数说明</b>
   * 
   * @param processer
   *          <p>
   * @author 冯加滨
   * @time 2010-1-21 下午05:07:25
   */
  private void addBeforeRule(AroundProcesser<SaleInvoiceVO> processer) {

    // 主组织停用检查
    IRule<SaleInvoiceVO> rule = new FinanceOrgEnableCheckRule<SaleInvoiceVO>();
    processer.addBeforeRule(rule);

    // 填充制单信息
    rule = new FillBillTailInfoRuleForIns<SaleInvoiceVO>();
    processer.addBeforeRule(rule);

    // 填充审计信息:创建人、创建时间
    rule = new SetAddAuditInfoRule<SaleInvoiceVO>();
    processer.addBeforeRule(rule);

    // 对冲生成发票校验
    rule = new CheckOppValityRule();
    processer.addBeforeRule(rule);

    // 填充默认值
    rule = new FillNewDefaultRule();
    processer.addBeforeRule(rule);
  
    // 数据合法性校验
    rule = new CheckBillValityRule();
    processer.addBeforeRule(rule);

    // 自动费用冲抵规则
    rule = new AutoUniteInvoiceRule();
    processer.addBeforeRule(rule);

    // 校验表头表体录入的自定义项是否启用
    rule = new UserDefSaveRule<SaleInvoiceVO>(new Class[] {
      SaleInvoiceHVO.class, SaleInvoiceBVO.class
    });
    processer.addBeforeRule(rule);
    
    //销售发票拉海运费结算单回写
    rule = new SaveHyfjsdAfterRule();
    processer.addBeforeRule(rule);

  }
}
