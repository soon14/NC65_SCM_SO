package nc.impl.so.m38.action;

import nc.bs.pub.action.N_38_UNAPPROVE;
import nc.bs.so.m38.plugin.ActionPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.so.m38.action.rule.unapprove.UnApproveBillBeforeRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m38.entity.PreOrderVO;

public class UnApprovePreOrderAction {
  /**
   * 方法功能描述：预订单弃审操作实现。
   * <p>
   * <b>参数说明</b>
   *
   * @param script
   * @return <p>
   * @author 刘志伟
   * @time 2010-04-06 上午10:38:45
   */
  public PreOrderVO[] unApprove(N_38_UNAPPROVE script) {
    PreOrderVO[] retvos = null;
    try {
      Object[] inCurObjects = script.getPfParameterVO().m_preValueVos;
      if ((inCurObjects == null) || (inCurObjects.length == 0)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0","04006012-0028")/*@res "错误：您希望弃审的预订单没有数据"*/);
      }
      if (!(inCurObjects instanceof PreOrderVO[])) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0","04006012-0029")/*@res "错误：您希望弃审的预订单类型不匹配"*/);
      }
      if (inCurObjects != null) {
        PreOrderVO[] inCurVOs = new PreOrderVO[inCurObjects.length];
        for (int i = 0; i < inCurObjects.length; i++) {
          inCurVOs[i] = (PreOrderVO) inCurObjects[i];
        }
        TimeLog.logStart();
        BillTransferTool<PreOrderVO> transferTool =
            new BillTransferTool<PreOrderVO>(inCurVOs);
        inCurVOs = transferTool.getClientFullInfoBill();
        TimeLog.info("补全前台VO、加锁、检查时间戳"); /*-=notranslate=-*/

        AroundProcesser<PreOrderVO> processer =
            new AroundProcesser<PreOrderVO>(ActionPlugInPoint.UnApproveAction);

        this.addRule(processer);

        TimeLog.logStart();
        processer.before(inCurVOs);
        TimeLog.info("调用审批流前执行业务规则"); /*-=notranslate=-*/

        TimeLog.logStart();
        script.procUnApproveFlow(script.getPfParameterVO());
        TimeLog.info("走审批工作流处理，此处不允许进行修改"); /*-=notranslate=-*/

        TimeLog.logStart();
        processer.after(inCurVOs);
        TimeLog.info("调用审批流后执行业务规则"); /*-=notranslate=-*/

        TimeLog.logStart();
        retvos = this.queryNewVO(inCurVOs);
        retvos = transferTool.getBillForToClient(retvos);
        TimeLog.info("组织返回值,返回轻量级VO"); /*-=notranslate=-*/
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return retvos;
  }

  /**
   * 方法功能描述：添加弃审规则。
   * <p>
   * <b>参数说明</b>
   *
   * @param processer
   *          <p>
   * @author 刘志伟
   * @time 2010-04-06 上午10:38:45
   */
  private void addRule(AroundProcesser<PreOrderVO> processer) {

    // 检查预订单当前状态是否可弃审
    IRule<PreOrderVO> rule = new UnApproveBillBeforeRule();
    processer.addBeforeRule(rule);

  }

  /**
   * 方法功能描述：查询弃审后最新预订单VO。
   * <p>
   * <b>参数说明</b>
   *
   * @param bills
   * @return <p>
   * @author 刘志伟
   * @time 2010-04-06 上午10:38:45
   */
  private PreOrderVO[] queryNewVO(PreOrderVO[] bills) {
    String[] ids = new String[bills.length];
    for (int i = 0; i < bills.length; i++) {
      ids[i] = bills[i].getPrimaryKey();
    }
    BillQuery<PreOrderVO> query = new BillQuery<PreOrderVO>(PreOrderVO.class);
    return query.query(ids);

  }
}