package nc.impl.so.m30.action.main;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pub.action.N_30R_APPROVE;
import nc.bs.so.m30.plugin.Action30PlugInPoint;
import nc.bs.so.m30.rule.approve.CheckApprovableRule;
import nc.bs.so.m30.rule.approve.CheckMaxIversionRule;
import nc.bs.so.m30.rule.approve.CheckSaleOrderStatusRule;
import nc.bs.so.m30.rule.approve.SaleOrderReviseApproveAfterRule;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;
import nc.vo.so.pub.rule.SOPfStatusChgRule;

/**
 * 销售订单修订审批动作
 * 
 * @since 6.3
 * @version 2014-12-5 下午2:17:26
 * @author wangshu6
 */
public class ApproveSaleOrderReviseAction {

  /**
   * 审批操作
   * 
   * @param bills
   * @param script
   * @return 审批后数据
   */
  public Object approve(SaleOrderHistoryVO[] bills, N_30R_APPROVE script) {

    Object ret = null;
    try {

      // 补全
      BillTransferTool<SaleOrderHistoryVO> transferTool =
          new BillTransferTool<SaleOrderHistoryVO>(bills);
      bills = transferTool.getClientFullInfoBill();
      // 注入点
      AroundProcesser<SaleOrderVO> processer =
          new AroundProcesser<SaleOrderVO>(
              Action30PlugInPoint.ApproveAction);

      TimeLog.logStart();
      this.addBeforeRule(processer);
      processer.before(bills);
      TimeLog.info("调用审批前操作插入点"); /* -=notranslate=- */

      /************* 该组件为批动作工作流处理，不能进行修改 *********************/
      ret = script.procActionFlow(script.getPfParameterVO());
      /************** 返回结果 *************************************************/

      // 转换流程平台审批流状态到业务单据状态，并持久化
      SaleOrderHistoryVO[] newbills = script.getVos();
      this.updateNewBillStatus(newbills);
      TimeLog.logStart();

      Integer newbillstatus = newbills[0].getParentVO().getFstatusflag();
      this.addAfterRule(processer, newbillstatus);
      processer.after(newbills);
      TimeLog.info("调用审批后操作插入点"); /* -=notranslate=- */

      // 审批通过时，流程平台返回的参数为null,此时需要返回最新的数据，其他情况下(审批中)只能返回null,否则会走驱动动作
      if (null == ret) {
        ret = newbills;
      }
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
    return ret;
  }

  private void updateNewBillStatus(SaleOrderVO[] newbills) {

    SOPfStatusChgRule statuschgrule = new SOPfStatusChgRule();
    SaleOrderHVO[] updateheads = new SaleOrderHVO[newbills.length];
    List<SaleOrderBVO> listbody = new ArrayList<SaleOrderBVO>();
    int i = 0;
    for (SaleOrderVO ordervo : newbills) {
      statuschgrule.changePfToBillStatus(ordervo);
      updateheads[i++] = ordervo.getParentVO();
      for (SaleOrderBVO bvo : ordervo.getChildrenVO()) {
        listbody.add(bvo);
      }
    }
    String[] headupname = new String[] {
      SaleOrderHVO.FSTATUSFLAG
    };
    VOUpdate<SaleOrderHVO> headupsrv = new VOUpdate<SaleOrderHVO>();
    headupsrv.update(updateheads, headupname);

    String[] bodyupname = new String[] {
      SaleOrderBVO.FROWSTATUS
    };
    VOUpdate<SaleOrderBVO> bodyupsrv = new VOUpdate<SaleOrderBVO>();
    SaleOrderBVO[] updatebodys =
        listbody.toArray(new SaleOrderBVO[listbody.size()]);
    bodyupsrv.update(updatebodys, bodyupname);
  }

  private void addAfterRule(AroundProcesser<SaleOrderVO> processer,
      Integer newbillstatus) {
    // 审批后将数据写到销售订单上 add by wangshu6
    @SuppressWarnings("unchecked")
    IRule<SaleOrderVO> rule = new SaleOrderReviseApproveAfterRule();
    processer.addAfterRule(rule);
  }

  private void addBeforeRule(AroundProcesser<SaleOrderVO> processer) {
    // 检查单据是否可以审批
    IRule<SaleOrderVO> rule = new CheckApprovableRule();
    processer.addBeforeRule(rule);

    // 校验审批版本是否是修订表中最新版本
    rule = new CheckMaxIversionRule();
    processer.addBeforeRule(rule);

    // 检查销售订单修订的修订前版本状态（防止已经被弃审）
    rule = new CheckSaleOrderStatusRule();
    processer.addBeforeRule(rule);

  }

}
