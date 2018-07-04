package nc.impl.so.m30.revise.action;

import nc.bs.so.m30.maintain.util.VOCheckFor30R;
import nc.bs.so.m30.plugin.Action30PlugInPoint;
import nc.bs.so.m30.revise.SaveReviseSaleOrderBP;
import nc.bs.so.m30.rule.feature.FeatureSelectSaveRule;
import nc.bs.so.m30.rule.maintaincheck.CheckNumPriceMnyRule;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.so.m30.revise.action.rule.FillupDataRule;
import nc.impl.so.m30.revise.action.rule.OffsetCheckRule;
import nc.impl.so.m30.revise.action.rule.ReviseFeatureCheckRule;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;

/**
 * 销售修订保存
 * <p>
 * <b>本类主要完成以下功能：</b>
 * 
 * <ul>
 * <li>将SaleOrderVO转变为SaleOrderHistoryVO
 * <li>修订前版本VO保留至销售订单历史表
 * <li>修订后最新版本VO更新至销售订单表
 * <li>...
 * </ul>
 * 
 * @version 6..0
 * @author 刘志伟
 * @time 2010-8-11 下午01:57:10
 */
public class ReviseSaveSaleOrderAction {

  /* 历史表附表元数据名称 */
  public static final String BODY_METANAME = "so.orderhistory_b";

  /* 历史表主表元数据 名称 */
  public static final String HEAD_METANAME = "so.orderhistory";

  /* 历史表主表名称 */
  public static final String TABLE_HISTORY = "so_orderhistory";

  /* 历史表附表名称 */
  public static final String TABLE_HISTORY_B = "so_orderhistory_b";

  public SaleOrderHistoryVO[] reviseSave(SaleOrderHistoryVO[] bills) throws BusinessException {
    TimeLog.logStart();
    BillTransferTool<SaleOrderHistoryVO> transferTool =
        new BillTransferTool<SaleOrderHistoryVO>(bills);
    SaleOrderHistoryVO[] fullbills = transferTool.getClientFullInfoBill();
    // add by zhangby5 此原始VO不是正真的原始VO，如要使用该原始VO，需要重新查询
    TimeLog.info("补全前台VO、获得修改前VO"); /*-=notranslate=-*/
    // 控制并发 modify by zhangby5
    VOCheckFor30R vocheck = new VOCheckFor30R();
    SaleOrderHistoryVO[] originBills = vocheck.voCheckAndQueryOriginForRevise(bills);
    CompareAroundProcesser<SaleOrderVO> compareProcesser =
        new CompareAroundProcesser<SaleOrderVO>(
            Action30PlugInPoint.ReviseSaveAction);
    // 增加执行前业务规则
    this.addBeforeRule(compareProcesser);

    TimeLog.logStart();
    compareProcesser.before(fullbills, originBills);
    TimeLog.info("调用修订保存前操作插入点"); /*-=notranslate=-*/

    SaveReviseSaleOrderBP updateBP = new SaveReviseSaleOrderBP();
    SaleOrderHistoryVO[] ret = updateBP.update(fullbills, originBills);
    TimeLog.logStart();

    this.addAfterRule(compareProcesser);
    compareProcesser.after(ret, fullbills);

    TimeLog.info("调用修订保存后操作插入点"); /*-=notranslate=-*/

    TimeLog.logStart();

    ret = transferTool.getBillForToClient(ret);
    TimeLog.info("组织返回值,返回轻量级VO"); /*-=notranslate=-*/

    TimeLog.logStart();
    // TODO
    TimeLog.info("业务日志"); /*-=notranslate=-*/
  
    return ret;
  }
  private void addBeforeRule(
      CompareAroundProcesser<SaleOrderVO> compareProcesser) {
    IRule<SaleOrderVO> rule = new FillupDataRule();
    compareProcesser.addBeforeRule(rule);
    // 累计冲抵金额校验
    rule = new OffsetCheckRule();
    compareProcesser.addBeforeRule(rule);
    // 校验特征选配
    ICompareRule<SaleOrderVO> comparerule = new ReviseFeatureCheckRule();
    compareProcesser.addBeforeRule(comparerule);
    
    // 销售订单特征码选配保存
    rule = new FeatureSelectSaveRule();
    compareProcesser.addBeforeRule(rule);
  }

  private void addAfterRule(CompareAroundProcesser<SaleOrderVO> compareProcesser) {
    
    IRule<SaleOrderVO> rule = null;
    // 数量单价金额检查规则
    rule = new CheckNumPriceMnyRule();
    compareProcesser.addAfterRule(rule);
  }
}
