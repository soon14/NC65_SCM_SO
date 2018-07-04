/**
 * 
 */
package nc.impl.so.custmatrel.maintain;

import nc.bs.so.custmatrel.plugin.BPPlugInPoint;
import nc.bs.so.custmatrel.rule.CheckInvSaleorgRule;
import nc.bs.so.custmatrel.rule.CheckSaveBillRule;
import nc.bs.so.custmatrel.rule.CustMatRelPriorityRule;
import nc.bs.so.custmatrel.rule.NullRule;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.custmatrel.entity.CustMatRelVO;

/**
 * 新增保存BP
 * 
 * @author gdsjw
 */
public class InsertBP {
  public CustMatRelVO[] insert(CustMatRelVO[] bills) {
    AroundProcesser<CustMatRelVO> processer =
        new AroundProcesser<CustMatRelVO>(BPPlugInPoint.InsertBP);

    // 注入点
    TimeLog.logStart();
    this.addBeforeRule(processer);
    processer.before(bills);

    TimeLog.info("调用新增保存前BP插入点"); /*-=notranslate=-*/

    TimeLog.logStart();
    BillInsert<CustMatRelVO> bo = new BillInsert<CustMatRelVO>();
    CustMatRelVO[] vos = bo.insert(bills);

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
  protected void addAfterRule(AroundProcesser<CustMatRelVO> processer) {
    processer.addAfterRule(new NullRule<CustMatRelVO>());
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
  protected void addBeforeRule(AroundProcesser<CustMatRelVO> processer) {
    IRule<CustMatRelVO> rule = null;

    rule = new CheckSaveBillRule();
    processer.addBeforeRule(rule);

    rule = new CheckInvSaleorgRule();
    processer.addBeforeRule(rule);

    rule = new CustMatRelPriorityRule();
    processer.addBeforeRule(rule);
  }

}
