package nc.impl.so.m38.action;

import nc.bs.pub.action.N_38_APPROVE;
import nc.bs.so.m38.plugin.ActionPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.so.m38.action.rule.approve.ApproveBillBeforeRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m38.entity.PreOrderVO;

public class ApprovePreOrderAction {

  /**
   * 方法功能描述：预订单审批操作实现。
   * <p>
   * <b>参数说明</b>
   *
   * @param script
   * @return <p>
   * @author 刘志伟
   * @time 2010-04-06 上午10:38:45
   */
  public PreOrderVO[] approve(N_38_APPROVE script) {
    PreOrderVO[] retvos = null;
    try {
      Object[] inCurObjects = script.getPfParameterVO().m_preValueVos;
      if (inCurObjects == null || inCurObjects.length == 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0","04006012-0026")/*@res "错误：您希望审核的预订单没有数据"*/);
      }
      if (!(inCurObjects instanceof PreOrderVO[])) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0","04006012-0027")/*@res "错误：您希望审核的预订单类型不匹配"*/);
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
            new AroundProcesser<PreOrderVO>(ActionPlugInPoint.ApproveAction);
        this.addRule(processer);

        TimeLog.logStart();
        processer.before(inCurVOs);
        TimeLog.info("调用审批流前执行业务规则"); /*-=notranslate=-*/

        TimeLog.logStart();
        script.procFlowBacth(script.getPfParameterVO());
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
   * 方法功能描述：添加审核规则。
   * <p>
   * <b>参数说明</b>
   *
   * @param processer
   *          <p>
   * @author 刘志伟
   * @time 2010-04-06 上午10:38:45
   */
  private void addRule(AroundProcesser<PreOrderVO> processer) {

    // 检查预订单当前状态是否可审核
    IRule<PreOrderVO> rule = new ApproveBillBeforeRule();
    processer.addBeforeRule(rule);

  }

  /**
   * 方法功能描述：查询审核后预订单VO
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
    BillQuery<PreOrderVO> billQuery =
        new BillQuery<PreOrderVO>(PreOrderVO.class);
    return billQuery.query(ids);

  }
}