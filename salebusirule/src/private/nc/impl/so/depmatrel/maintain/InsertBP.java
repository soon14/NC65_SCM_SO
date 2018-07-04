/**
 * 
 */
package nc.impl.so.depmatrel.maintain;

import nc.bs.so.depmatrel.plugin.BPPlugInPoint;
import nc.bs.so.depmatrel.rule.CheckInvSaleorgRule;
import nc.bs.so.depmatrel.rule.CheckSaveBillRule;
import nc.bs.so.depmatrel.rule.DeptMatRelPriorityRule;
import nc.bs.so.depmatrel.rule.NullRule;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.depmatrel.entity.DepMatRelVO;

/**
 * 新增保存BP
 * 
 * @author gdsjw
 */
public class InsertBP {
  public DepMatRelVO[] insert(DepMatRelVO[] bills) {
    AroundProcesser<DepMatRelVO> processer =
        new AroundProcesser<DepMatRelVO>(BPPlugInPoint.InsertBP);

    // 注入点
    TimeLog.logStart();
    this.addBeforeRule(processer);
    processer.before(bills);

    TimeLog.info("调用新增保存前BP插入点"); /*-=notranslate=-*/

    TimeLog.logStart();
    BillInsert<DepMatRelVO> bo = new BillInsert<DepMatRelVO>();
    DepMatRelVO[] vos = bo.insert(bills);

    TimeLog.info("保存单据到数据库"); /*-=notranslate=-*/

    // 注入点
    TimeLog.logStart();
    this.addAfterRule(processer);
    processer.after(vos);

    TimeLog.info("调用新增保存后BP插入点"); /*-=notranslate=-*/

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
   * @time 2010-9-10 下午03:17:23
   */
  protected void addAfterRule(AroundProcesser<DepMatRelVO> processer) {
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
   * @time 2010-9-10 下午03:17:20
   */
  protected void addBeforeRule(AroundProcesser<DepMatRelVO> processer) {
    IRule<DepMatRelVO> rule = null;

    rule = new CheckSaveBillRule();
    processer.addBeforeRule(rule);

    rule = new CheckInvSaleorgRule();
    processer.addBeforeRule(rule);

    rule = new DeptMatRelPriorityRule();
    processer.addBeforeRule(rule);
  }

}
