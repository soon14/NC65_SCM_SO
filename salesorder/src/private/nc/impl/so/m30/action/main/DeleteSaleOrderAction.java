package nc.impl.so.m30.action.main;

import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderVO;

import nc.bs.so.m30.maintain.DeleteSaleOrderBP;
import nc.bs.so.m30.plugin.Action30PlugInPoint;
import nc.bs.so.m30.rule.maintainprocess.NullRule;

import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

/**
 * 删除动作
 * 
 * @author gdsjw
 */
public class DeleteSaleOrderAction {

  /**
   * 销售订单删除
   * 
   * @param bills
   * @return 订单
   */
  public SaleOrderVO[] delete(SaleOrderVO[] bills) {

    SaleOrderVO[] delbills = bills;

    AroundProcesser<SaleOrderVO> processer =
        new AroundProcesser<SaleOrderVO>(Action30PlugInPoint.DeleteAction);
    // 增加业务规则
    this.addBeforeRule(processer);

    TimeLog.logStart();
    processer.before(delbills);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0165")/*@res "调用删除前操作插入点"*/); /* -=notranslate=- */

    TimeLog.logStart();
    DeleteSaleOrderBP action = new DeleteSaleOrderBP();
    action.delete(delbills);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0166")/*@res "调用删除BP，进行删除"*/); /* -=notranslate=- */

    TimeLog.logStart();
    processer.after(delbills);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0167")/*@res "调用删除后操作插入点"*/); /* -=notranslate=- */

    return delbills;

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
   * @time 2010-9-10 下午04:08:24
   */
  private void addBeforeRule(AroundProcesser<SaleOrderVO> processer) {
    processer.addBeforeRule(new NullRule<SaleOrderVO>());
  }
}
