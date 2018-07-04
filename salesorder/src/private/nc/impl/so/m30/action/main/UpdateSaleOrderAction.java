package nc.impl.so.m30.action.main;

import nc.bs.so.m30.maintain.UpdateSaleOrderBP;
import nc.bs.so.m30.plugin.Action30PlugInPoint;
import nc.bs.so.m30.rule.maintainprocess.FillupDataWhenUpdateRule;
import nc.bs.so.m30.rule.maintainprocess.NullRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 修改保存动作
 * 
 * @author gdsjw
 */
public class UpdateSaleOrderAction {

  public SaleOrderVO[] update(SaleOrderVO[] bills, SaleOrderVO[] originBills) {

    CompareAroundProcesser<SaleOrderVO> processer =
        new CompareAroundProcesser<SaleOrderVO>(
            Action30PlugInPoint.UpdateAction);
    // 增加业务规则
    this.addBeforeRule(processer);
    this.addAfterRule(processer);

    TimeLog.logStart();
    processer.before(bills, originBills);

    TimeLog.info("调用修改保存前操作插入点"); /*-=notranslate=-*/

    TimeLog.logStart();
    UpdateSaleOrderBP action = new UpdateSaleOrderBP();
    SaleOrderVO[] vos = action.update(bills, originBills);

    TimeLog.info("调用修改保存BP，进行保存"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(vos, originBills);

    TimeLog.info("调用修改保存后操作插入点"); /*-=notranslate=-*/
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
   * @time 2010-9-10 下午04:08:51
   */
  private void addAfterRule(CompareAroundProcesser<SaleOrderVO> processer) {
    processer.addAfterRule(new NullRule<SaleOrderVO>());
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
   * @time 2010-9-10 下午04:08:54
   */
  private void addBeforeRule(CompareAroundProcesser<SaleOrderVO> processer) {
    IRule<SaleOrderVO> rule = null;

    rule = new FillupDataWhenUpdateRule();
    processer.addBeforeRule(rule);

  }

}
