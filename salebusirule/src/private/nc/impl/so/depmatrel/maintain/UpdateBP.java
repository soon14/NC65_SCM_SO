package nc.impl.so.depmatrel.maintain;

import nc.bs.so.depmatrel.plugin.BPPlugInPoint;
import nc.bs.so.depmatrel.rule.CheckInvSaleorgRule;
import nc.bs.so.depmatrel.rule.CheckSaveBillRule;
import nc.bs.so.depmatrel.rule.DeptMatRelPriorityRule;
import nc.bs.so.depmatrel.rule.NullRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.depmatrel.entity.DepMatRelVO;

/**
 * 修改保存动作
 * 
 * @author gdsjw
 */
public class UpdateBP {
  public UpdateBP() {
    //
  }

  public DepMatRelVO[] update(DepMatRelVO[] bills, DepMatRelVO[] originBills) {
    CompareAroundProcesser<DepMatRelVO> processer =
        new CompareAroundProcesser<DepMatRelVO>(BPPlugInPoint.UpdateBP);

    // 注入点
    TimeLog.logStart();
    this.addBeforeRule(processer);
    processer.before(bills, originBills);

    TimeLog.info("调用修改保存前BP插入点"); /*-=notranslate=-*/

    TimeLog.logStart();
    BillUpdate<DepMatRelVO> bo = new BillUpdate<DepMatRelVO>();
    DepMatRelVO[] vos = bo.update(bills, originBills);

    TimeLog.info("保存修改单据到数据库"); /*-=notranslate=-*/

    // 注入点
    TimeLog.logStart();
    this.addAfterRule(processer);
    processer.after(vos, originBills);

    TimeLog.info("调用修改保存后BP插入点"); /*-=notranslate=-*/

    return vos;
  }

  /**
   * 方法功能描述：简要描述本方法的功能。
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param processer
   *          <p>
   * @author zhangcheng
   * @time 2010-9-10 下午03:17:12
   */
  protected void addAfterRule(CompareAroundProcesser<DepMatRelVO> processer) {
    processer.addAfterRule(new NullRule<DepMatRelVO>());
  }

  /**
   * 方法功能描述：简要描述本方法的功能。
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param processer
   *          <p>
   * @author zhangcheng
   * @time 2010-9-10 下午03:17:09
   */
  protected void addBeforeRule(CompareAroundProcesser<DepMatRelVO> processer) {
    IRule<DepMatRelVO> rule = null;

    rule = new CheckSaveBillRule();
    processer.addBeforeRule(rule);

    rule = new CheckInvSaleorgRule();
    processer.addBeforeRule(rule);

    rule = new DeptMatRelPriorityRule();
    processer.addBeforeRule(rule);
  }

}
