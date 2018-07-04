package nc.impl.so.m4331.action.maintain;

import nc.bs.ml.NCLangResOnserver;
import nc.bs.pub.action.N_4331_APPROVE;
import nc.bs.so.m4331.plugin.Action4331PlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.so.m4331.action.maintain.rule.approve.CheckEnableApproveRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.trade.checkrule.VOChecker;

public class DeliveryApproveAction {

  public Object approve(N_4331_APPROVE script) {
    Object ret = null;
    try {
      Object[] inCurObjects = script.getPfParameterVO().m_preValueVos;
      this.checkDefaultData(inCurObjects);
      DeliveryVO[] inCurVOs = new DeliveryVO[inCurObjects.length];
      int length = inCurObjects.length;
      for (int i = 0; i < length; i++) {
        inCurVOs[i] = (DeliveryVO) inCurObjects[i];
      }
      TimeLog.logStart();
      // BillTransferTool<DeliveryVO> transferTool =
      // new BillTransferTool<DeliveryVO>(inCurVOs);
      // inCurVOs = transferTool.getClientFullInfoBill();
      if (inCurVOs.length > 0) {
        script.getPfParameterVO().m_preValueVo = inCurVOs[0];
        script.getPfParameterVO().m_preValueVos = inCurVOs;
      }

      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006002_0", "04006002-0067")/*@res "补全前台VO、加锁、检查时间戳"*/);
      TimeLog.logStart();
      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006002_0", "04006002-0068")/*@res "调用审批流前执行业务规则"*/);

      // 注入点
      AroundProcesser<DeliveryVO> processer =
          new AroundProcesser<DeliveryVO>(Action4331PlugInPoint.ApproveAction);

      TimeLog.logStart();
      this.addBeforeRule(processer);
      processer.before(inCurVOs);
      TimeLog.info("调用审批前操作插入点"); /* -=notranslate=- */

      TimeLog.logStart();
      ret = script.procActionFlow(script.getPfParameterVO());
      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006002_0", "04006002-0069")/*@res "走审批工作流处理，此处不允许进行修改"*/);
      if (null == ret) {
        TimeLog.logStart();
        TimeLog.info(NCLangResOnserver.getInstance().getStrByID("4006002_0",
            "04006002-0138")/*调用审批流后执行业务规则*/); /*-=notranslate=-*/
        TimeLog.logStart();
        ret = this.queryNewVO(inCurVOs);

        processer.after((DeliveryVO[]) ret);
        // ret = transferTool.getBillForToClient((DeliveryVO[]) ret);
        TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
            "4006002_0", "04006002-0070")/*@res "组织返回值,返回轻量级VO"*/);
        // this.setBusiLog(inCurVOs);
      }

    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return ret;
  }

  private void addBeforeRule(AroundProcesser<DeliveryVO> processer) {
    // 检查单据是否可以审批
    IRule<DeliveryVO> rule = new CheckEnableApproveRule();
    processer.addBeforeRule(rule);
    // 写业务日志
    // rule = new BusiLog();
    // processer.addBeforeRule(rule);

  }

  private void checkDefaultData(Object[] inCurObjects) {
    if (VOChecker.isEmpty(inCurObjects)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006002_0", "04006002-0071")/*@res "错误：您希望审核的发货单没有数据"*/);
    }
    if (!(inCurObjects instanceof DeliveryVO[])) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006002_0", "04006002-0072")/*@res "错误：您希望审核的发货单类型不匹配"*/);
    }
  }

  private DeliveryVO[] queryNewVO(DeliveryVO[] bills) {
    String[] ids = new String[bills.length];
    int length = bills.length;
    for (int i = 0; i < length; i++) {
      ids[i] = bills[i].getPrimaryKey();
    }
    BillQuery<DeliveryVO> query = new BillQuery<DeliveryVO>(DeliveryVO.class);
    return query.query(ids);
  }
}
