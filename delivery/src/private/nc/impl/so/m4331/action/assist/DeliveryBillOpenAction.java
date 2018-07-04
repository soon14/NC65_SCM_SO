package nc.impl.so.m4331.action.assist;

import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.bs.so.m4331.assist.state.DeliveryStateMachine;
import nc.bs.so.m4331.assist.state.bill.BillOpenState;
import nc.bs.so.m4331.maintain.rule.atp.DeliveryVOATPAfterRule;
import nc.bs.so.m4331.maintain.rule.atp.DeliveryVOATPBeforeRule;
import nc.bs.so.m4331.plugin.Action4331PlugInPoint;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.so.m4331.action.assist.rule.BussiCheckRule;
import nc.impl.so.m4331.action.assist.rule.RenovateQualityStateRule;
import nc.impl.so.m4331.action.assist.rule.RewriteSrcRule;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.SOParameterVO;
import nc.vo.so.pub.enumeration.ActionType;
import nc.vo.so.pub.enumeration.FuncodeType;
import nc.vo.so.pub.util.BusinessLogUtil;

/**
 * 发货单整单打开
 * 
 * @since 6.0
 * @version 2011-3-1 下午04:22:03
 * @author 祝会征
 */
public class DeliveryBillOpenAction {
  public DeliveryVO[] openBill(SOParameterVO paravo) {
    try {
      this.setCheck(paravo.getBusinessCheckMap());
      DeliveryVO[] bills = (DeliveryVO[]) paravo.getVos();
      TimeLog.logStart();
      AroundProcesser<DeliveryVO> atpprocesser =
        new AroundProcesser<DeliveryVO>(Action4331PlugInPoint.BillOpenAction);
      this.addBeforeATPRule(atpprocesser);
      this.addAfterATPRule(atpprocesser);

      atpprocesser.before(bills);
      BillTransferTool<DeliveryVO> transferTool =
          new BillTransferTool<DeliveryVO>(bills);
      DeliveryVO[] newbills = transferTool.getClientFullInfoBill();
      TimeLog.info("补全前台VO、加锁、检查时间戳"); /*-=notranslate=-*/
      this.addRule(bills, paravo.getBusinessCheckMap());
      BillOpenState state = new BillOpenState();
      DeliveryStateMachine bo = new DeliveryStateMachine();
      // 应该有返回值，为状态改变后的VO
      bo.setState(state, newbills);
      this.setBusiLog(newbills);
      atpprocesser.after(newbills);
      return transferTool.getBillForToClient(newbills);
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
    return null;
  }

  /**
   * 方法功能描述：添加可用量规则。
   */
  private void addAfterATPRule(AroundProcesser<DeliveryVO> processer) {
    IRule<DeliveryVO> rule = null;
    rule = new DeliveryVOATPAfterRule();
    processer.addAfterRule(rule);
  }

  /**
   * 方法功能描述：添加可用量规则。
   */
  private void addBeforeATPRule(AroundProcesser<DeliveryVO> processer) {
    IRule<DeliveryVO> rule = null;
    // 可用量
    rule = new DeliveryVOATPBeforeRule();
    processer.addBeforeRule(rule);
  }
  
  private void addRule(DeliveryVO[] bills, Map<String, Boolean> map) {
    BussiCheckRule busicheck = new BussiCheckRule();
    busicheck.setBusiCheckFlag(map);
    // 回写来源单据
    RewriteSrcRule rewrite = new RewriteSrcRule();
    rewrite.rewriteSrc(bills, UFBoolean.FALSE);
    // 如果有质检信息 更新质检信息的出库状态
    RenovateQualityStateRule renovate = new RenovateQualityStateRule();
    renovate.renovateState(bills, UFBoolean.FALSE);
  }

  private void setBusiLog(DeliveryVO[] vos) {
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.BILLOPEN);
    util.setFuncnode(FuncodeType.DELIVERY);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0131")/*发货单整单打开*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLog(vos, true);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void setCheck(Map<String, Boolean> map) {
    if (null == map || map.size() == 0) {
      return;
    }
  }
}
