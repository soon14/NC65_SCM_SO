package nc.impl.so.m4331.action.assist;

import nc.bs.ml.NCLangResOnserver;
import nc.bs.so.m4331.assist.state.DeliveryStateMachine;
import nc.bs.so.m4331.assist.state.row.RowCloseState;
import nc.bs.so.m4331.maintain.rule.atp.DeliveryVOATPAfterRule;
import nc.bs.so.m4331.maintain.rule.atp.DeliveryVOATPBeforeRule;
import nc.bs.so.m4331.plugin.Action4331PlugInPoint;
import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.impl.pubapp.bill.convertor.ViewToBillConvertor;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.pattern.data.view.tool.ViewTransferTool;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.so.m4331.action.assist.rule.RenovateQualityStateRule;
import nc.impl.so.m4331.action.assist.rule.RewriteSrcRule;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IObjectConvert;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;
import nc.vo.so.pub.enumeration.ActionType;
import nc.vo.so.pub.enumeration.FuncodeType;
import nc.vo.so.pub.util.BusinessLogUtil;

/**
 * 发货单行关闭
 * 
 * @since 6.0
 * @version 2011-3-1 下午02:30:54
 * @author 祝会征
 */
public class DeliveryRowCloseAction {
  public DeliveryVO[] closeRow(DeliveryVO[] bills) {
    try {
      
      AroundProcesser<DeliveryVO> atpprocesser =
        new AroundProcesser<DeliveryVO>(Action4331PlugInPoint.RowCloseAction);
      this.addBeforeATPRule(atpprocesser);
      this.addAfterATPRule(atpprocesser);
      
      atpprocesser.before(bills);
      
      this.addRule(bills);
      IObjectConvert<DeliveryVO, DeliveryViewVO> convert =
          new BillToViewConvertor<DeliveryVO, DeliveryViewVO>(
              DeliveryViewVO.class);
      DeliveryViewVO[] views = convert.convert(bills);
      IState<DeliveryViewVO> state = new RowCloseState();
      views = this.setRowStatus(state, views);
      IObjectConvert<DeliveryViewVO, DeliveryVO> billconvert =
          new ViewToBillConvertor<DeliveryViewVO, DeliveryVO>(DeliveryVO.class);
      DeliveryVO[] newbills = billconvert.convert(views);
      this.setBusiLog(newbills);
      atpprocesser.after(newbills);
      return newbills;
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

  private void addRule(DeliveryVO[] bills) {
    // 回写来源单据
    RewriteSrcRule rewrite = new RewriteSrcRule();
    rewrite.rewriteSrc(bills, UFBoolean.TRUE);
    // 如果有质检信息 更新质检信息的出库状态
    RenovateQualityStateRule renovate = new RenovateQualityStateRule();
    renovate.renovateState(bills, UFBoolean.TRUE);
  }

  private void setBusiLog(DeliveryVO[] vos) {
    BusinessLogUtil util = new BusinessLogUtil();
    util.setActiontype(ActionType.OUTCLOSE);
    util.setFuncnode(FuncodeType.DELIVERY);
    util.setBusiobjname(NCLangResOnserver.getInstance().getStrByID("4006002_0", "04006002-0132")/*发货单行出库关闭*/);
    util.setFuncletInitData(null);
    try {
      util.insertBusiLogForBody(vos);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private DeliveryViewVO[] setRowStatus(IState<DeliveryViewVO> state,
      DeliveryViewVO[] originviews) {
    ViewTransferTool<DeliveryViewVO> tool =
        new ViewTransferTool<DeliveryViewVO>(originviews);
    DeliveryViewVO[] views = tool.getOriginViews();
    DeliveryStateMachine machine = new DeliveryStateMachine();
    machine.setState(state, views);
    // 返回轻量级VO
    views = tool.getViewForToClient(views);
    return views;
  }
}
