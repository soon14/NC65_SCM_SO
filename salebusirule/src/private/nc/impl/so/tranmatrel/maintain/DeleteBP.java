package nc.impl.so.tranmatrel.maintain;

import nc.bs.so.tranmatrel.plugin.BPPlugInPoint;
import nc.bs.so.tranmatrel.rule.NullRule;
import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.tranmatrel.entity.TranMatRelVO;

/**
 * 删除BP
 * 
 * @author gdsjw
 */
public class DeleteBP {
  public DeleteBP() {
    //
  }

  public TranMatRelVO[] delete(TranMatRelVO[] bills) {
    AroundProcesser<TranMatRelVO> processer =
        new AroundProcesser<TranMatRelVO>(BPPlugInPoint.DeleteBP);

    // 注入点
    TimeLog.logStart();
    this.addBeforeRule(processer);
    processer.before(bills);

    TimeLog.info("调用删除前BP插入点"); /*-=notranslate=-*/

    TimeLog.logStart();
    BillDelete<TranMatRelVO> bo = new BillDelete<TranMatRelVO>();
    bo.delete(bills);

    TimeLog.info("写数据库，删除单据"); /*-=notranslate=-*/

    // 注入点
    TimeLog.logStart();
    this.addAfterRule(processer);
    processer.after(bills);

    TimeLog.info("调用删除后BP插入点"); /*-=notranslate=-*/

    return bills;
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
   * @time 2010-9-10 下午03:17:29
   */
  protected void addBeforeRule(AroundProcesser<TranMatRelVO> processer) {
    processer.addBeforeRule(new NullRule<TranMatRelVO>());
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
   * @time 2010-9-10 下午03:17:31
   */
  protected void addAfterRule(AroundProcesser<TranMatRelVO> processer) {
    processer.addAfterRule(new NullRule<TranMatRelVO>());
  }

}
