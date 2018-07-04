/**
 * 
 */
package nc.impl.so.tranmatrel.maintain;

import nc.bs.so.tranmatrel.plugin.BPPlugInPoint;
import nc.bs.so.tranmatrel.rule.CheckInvSaleorgRule;
import nc.bs.so.tranmatrel.rule.CheckSaveBillRule;
import nc.bs.so.tranmatrel.rule.NullRule;
import nc.bs.so.tranmatrel.rule.TranMatRelPriorityRule;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.tranmatrel.entity.TranMatRelVO;

/**
 * 新增保存BP
 * 
 * @author gdsjw
 */
public class InsertBP {
  public TranMatRelVO[] insert(TranMatRelVO[] bills) {
    AroundProcesser<TranMatRelVO> processer =
        new AroundProcesser<TranMatRelVO>(BPPlugInPoint.InsertBP);

    // 注入点
    TimeLog.logStart();
    this.addBeforeRule(processer);
    processer.before(bills);

    TimeLog.info("调用新增保存前BP插入点"); /*-=notranslate=-*/

    TimeLog.logStart();
    BillInsert<TranMatRelVO> bo = new BillInsert<TranMatRelVO>();
    TranMatRelVO[] vos = bo.insert(bills);

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
  protected void addAfterRule(AroundProcesser<TranMatRelVO> processer) {
    processer.addAfterRule(new NullRule<TranMatRelVO>());
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
  protected void addBeforeRule(AroundProcesser<TranMatRelVO> processer) {
    IRule<TranMatRelVO> rule = null;

    rule = new CheckSaveBillRule();
    processer.addBeforeRule(rule);

    rule = new CheckInvSaleorgRule();
    processer.addBeforeRule(rule);

    rule = new TranMatRelPriorityRule();
    processer.addBeforeRule(rule);
  }

}
