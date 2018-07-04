package nc.bs.so.m30.revise;

import nc.bs.so.m30.plugin.BP30PlugInPoint;
import nc.bs.so.m30.revise.rule.FillDataHistoryRule;
import nc.bs.so.m30.revise.rule.RewriteVbillcodeFor30R;
import nc.bs.so.m30.revise.rule.UpdateVersionNumRule;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;

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
public class SaveReviseSaleOrderBP {

  public SaleOrderHistoryVO[] update(SaleOrderHistoryVO[] bills,
      SaleOrderHistoryVO[] originBills) throws BusinessException {
    CompareAroundProcesser<SaleOrderHistoryVO> processer =
        new CompareAroundProcesser<SaleOrderHistoryVO>(
            BP30PlugInPoint.ReviseSaveBP);

    TimeLog.logStart();
    this.addBeforeRule(processer);
    processer.before(bills, originBills);
    TimeLog.info("调用修改保存前BP插入点"); /* -=notranslate=- */

    TimeLog.logStart();
    BillInsert<SaleOrderHistoryVO> bo = new BillInsert<SaleOrderHistoryVO>();
    SaleOrderHistoryVO[] vo = (SaleOrderHistoryVO[]) bo.insert(bills);
    TimeLog.info("保存修改单据到数据库"); /* -=notranslate=- */
    //王梓懿 2018-04-02 新增将新的代理协议号回写
    processer.addAfterRule(new RewriteVbillcodeFor30R());
    TimeLog.logStart();
    processer.after(bills, originBills);
    TimeLog.info("调用修改保存后BP插入点");/* -=notranslate=- */

    return vo;
  }

  private void addBeforeRule(
      CompareAroundProcesser<SaleOrderHistoryVO> processer) {
    // 修订前填充数据
    ICompareRule<SaleOrderHistoryVO> rule = new FillDataHistoryRule();
    processer.addBeforeRule(rule);

    // 更新版本号
    ICompareRule<SaleOrderHistoryVO> comparerule = new UpdateVersionNumRule();
    processer.addBeforeRule(comparerule);
  }
}
