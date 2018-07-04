package nc.impl.so.m30.action.main;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pub.action.N_30_UNAPPROVE;
import nc.bs.so.m30.maintain.rule.insert.RewritePromotePriceInsertRule;
import nc.bs.so.m30.plugin.Action30PlugInPoint;
import nc.bs.so.m30.rule.approve.CheckPush5Aor20Rule;
import nc.bs.so.m30.rule.atp.SaleOrderVOATPAfterRule;
import nc.bs.so.m30.rule.atp.SaleOrderVOATPBeforeRule;
import nc.bs.so.m30.rule.credit.RenovateARByHidsBeginRule;
import nc.bs.so.m30.rule.credit.RenovateARByHidsEndRule;
import nc.bs.so.m30.rule.m35.Rewrite35WhenUnApproveRule;
import nc.bs.so.m30.rule.me.SaleOrderVOUnApproveAfterRule;
import nc.bs.so.m30.rule.unapprove.BusiLog;
import nc.bs.so.m30.rule.unapprove.CheckUnApprovableRule;
import nc.bs.so.m30.rule.unapprove.UNApproveStateRule;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.credit.engrossmaintain.pub.action.M30EngrossAction;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.rule.SOPfStatusChgRule;

/**
 * 弃审动作
 */
public class UnApproveSaleOrderAction {

  public SaleOrderVO[] unApprove(SaleOrderVO[] bills, N_30_UNAPPROVE script) {
    SaleOrderVO[] ret = null;
    try {

      // 注入点
      AroundProcesser<SaleOrderVO> processer =
          new AroundProcesser<SaleOrderVO>(Action30PlugInPoint.UnApproveAction);
      Integer oldbillstatus = bills[0].getParentVO().getFstatusflag();
      TimeLog.logStart();
      this.addBeforeRule(processer, oldbillstatus);
      processer.before(bills);
      TimeLog.info("调用审批前操作插入点"); /* -=notranslate=- */

      /************* 该组件为批动作工作流处理，不能进行修改 *********************/
      script.procUnApproveFlow(script.getPfParameterVO());
      /************** 返回结果 *************************************************/

      // 转换流程平台审批流状态到业务单据状态，并持久化
      SaleOrderVO[] newbills = script.getVos();

      this.updateNewBillStatus(newbills);
      Integer newbillstatus = newbills[0].getParentVO().getFstatusflag();

      TimeLog.logStart();
      this.addAfterRule(processer, newbillstatus,oldbillstatus);
      processer.after(newbills);
      TimeLog.info("调用审批后操作插入点"); /* -=notranslate=- */

      ret = newbills;
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
      updateheads[i] = ordervo.getParentVO();
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
      Integer newbillstatus,Integer oldbillstatus) {
    IRule<SaleOrderVO> rule = null;
    // 信用检查
    if (SysInitGroupQuery.isCREDITEnabled()) {
      rule = new RenovateARByHidsEndRule(M30EngrossAction.M30UnApprove);
      processer.addAfterRule(rule);
    }
    if (BillStatus.FREE.equalsValue(newbillstatus)
        || BillStatus.NOPASS.equalsValue(newbillstatus)
        || BillStatus.AUDITING.equalsValue(newbillstatus)) {
      // 弃审时各种状态的处理
      rule = new UNApproveStateRule();
      processer.addAfterRule(rule);
    }

    // 弃审后 删除相应的订单收益表数据
    rule = new SaleOrderVOUnApproveAfterRule();
    processer.addAfterRule(rule);

    // 索芙特：弃审后删除关联的赠品兑付费用单
    rule = new Rewrite35WhenUnApproveRule();
    processer.addAfterRule(rule);
    if (BillStatus.NOPASS.equalsValue(oldbillstatus)) {
      boolean icEnable = SysInitGroupQuery.isICEnabled();
      if (icEnable) {
        // 可用量
        rule = new SaleOrderVOATPAfterRule();
        processer.addAfterRule(rule);
      }
    }
  }

  private void addBeforeRule(AroundProcesser<SaleOrderVO> processer,
      Integer oldbillstatus) {
    IRule<SaleOrderVO> rule = null;

    // 检查是否配置消息驱动(PUSH5AOR20)
    rule = new CheckPush5Aor20Rule();
    processer.addBeforeRule(rule);

    // 检查单据是否可以弃审
    rule = new CheckUnApprovableRule();
    processer.addBeforeRule(rule);

    // 信用检查
    if (SysInitGroupQuery.isCREDITEnabled()) {
      rule = new RenovateARByHidsBeginRule(M30EngrossAction.M30UnApprove);
      processer.addBeforeRule(rule);
    }

    rule = new BusiLog();
    processer.addBeforeRule(rule);

    if (BillStatus.NOPASS.equalsValue(oldbillstatus)) {
      // 回写促销价格表 zhangby5 for 恒安限量促销
      if (SysInitGroupQuery.isPRICEEnabled()) {
        rule = new RewritePromotePriceInsertRule();
        processer.addAfterRule(rule);
      }
      boolean icEnable = SysInitGroupQuery.isICEnabled();
      if (icEnable) {
        // 可用量
        rule = new SaleOrderVOATPBeforeRule();
        processer.addBeforeRule(rule);
      }
    }
  }

}
