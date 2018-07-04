package nc.impl.so.m4331.action.maintain.rule.unapprove;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.dm.m4804.IDelivBillServiceFor4331;
import nc.pubitf.ic.m4c.I4CQueryPubService;
import nc.pubitf.ic.m4y.I4YQueryPubService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * @description
 * 销售发货单弃审前检查单据是否可以弃审
 * @scene
 * 销售发货单弃审前
 * @param
 * 无
 */
public class CheckEnableUnApproveRule implements IRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] vos) {
    // 检查发货单是否进行报检、报检的发货单不能弃审
    this.isChecked(vos);
    // 检查是否已经关闭
    this.checkIsClose(vos);
    // 检查是否存在下游单据
    this.existBill(vos);
    // 审核者权限校验
    this.validate(vos);
  }

  private void validate(DeliveryVO[] aggVOs) {
    // DeliveryVO[] aggVOs = (DeliveryVO[]) vo;
    if (null != aggVOs && aggVOs.length > 0) {
      for (DeliveryVO aggVO : aggVOs) {
        if (!nc.vo.pubapp.pub.power.BillPowerChecker.hasApproverPermission(
            aggVO, "4331")) {
          String msg =
              NCLangResOnserver.getInstance().getStrByID("4006002_0",
                  "04006002-0176");// "不满足审核者权限。"
          ExceptionUtils.wrappBusinessException(msg);
        }
      }
    }
  }

  private void checkExist4804(String[] cdeliveryids,
      Map<String, DeliveryVO> temMap) {
    // 如果运输管理模块未启用，不处理
    if (!SysInitGroupQuery.isDMEnabled()) {
      return;
    }
    IDelivBillServiceFor4331 service =
        NCLocator.getInstance().lookup(IDelivBillServiceFor4331.class);
    try {
      Map<String, Boolean> hmExit =
          service.queryHasDownriverBillFor4331(cdeliveryids);
      for (String key : cdeliveryids) {
        if (hmExit.get(key).booleanValue()) {
          DeliveryVO vo = temMap.get(key);
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
              .getStrByID("4006002_0", "04006002-0134", null, new String[] {
                vo.getParentVO().getVbillcode()
              })/*单据{0}已经生成下游单据，不能弃审。*/);
        }

      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void checkExist4CAnd4Y(String[] cdeliveryids,
      Map<String, DeliveryVO> temMap) {
    // 检查是否存在下游单据
    I4CQueryPubService icquerysrv =
        NCLocator.getInstance().lookup(I4CQueryPubService.class);
    I4YQueryPubService ic4yquerysrv =
        NCLocator.getInstance().lookup(I4YQueryPubService.class);
    try {
      Map<String, UFBoolean> hmExit =
          icquerysrv.existBill(cdeliveryids, true,
              SOBillType.Delivery.getCode());
      Map<String, UFBoolean> ic4yExit =
          ic4yquerysrv.existBill(cdeliveryids, true,
              SOBillType.Delivery.getCode());
      for (String key : cdeliveryids) {
        if (hmExit.get(key).booleanValue()) {
          DeliveryVO vo = temMap.get(key);
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
              .getStrByID("4006002_0", "04006002-0134", null, new String[] {
                vo.getParentVO().getVbillcode()
              })/*单据{0}已经生成下游单据，不能弃审。*/);
        }
        if (ic4yExit.get(key).booleanValue()) {
          DeliveryVO vo = temMap.get(key);
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
              .getStrByID("4006002_0", "04006002-0134", null, new String[] {
                vo.getParentVO().getVbillcode()
              })/*单据{0}已经生成下游单据，不能弃审。*/);
        }
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void checkIsClose(DeliveryVO[] vos) {
    for (DeliveryVO vo : vos) {
      Integer state = vo.getParentVO().getFstatusflag();
      String code = vo.getParentVO().getVbillcode();
      if (BillStatus.CLOSED.equalsValue(state)) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4006002_0", "04006002-0135", null, new String[] {
              code
            })/*单据{0}已经整单关闭，不能弃审。*/);
      }
      DeliveryBVO[] bvos = vo.getChildrenVO();
      for (DeliveryBVO bvo : bvos) {
        UFBoolean flag = bvo.getBoutendflag();
        if ((flag == null) || !flag.booleanValue()) {
          continue;
        }
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4006002_0", "04006002-0136", null, new String[] {
              code
            })/*单据{0}已经行关闭，不能弃审。*/);
      }
    }
  }

  /*
   * 检查发货单是否存在下游单据，如果存在不能弃审
   */
  private void existBill(DeliveryVO[] vos) {
    Map<String, DeliveryVO> temMap = new HashMap<String, DeliveryVO>();
    String[] cdeliveryids = new String[vos.length];
    for (int i = 0; i < vos.length; i++) {
      boolean expr1 =
          BillStatus.AUDIT.equalsValue(vos[i].getParentVO().getFstatusflag());
      boolean expr2 =
          BillStatus.AUDITING
              .equalsValue(vos[i].getParentVO().getFstatusflag());
      boolean expr3 =
          BillStatus.NOPASS
              .equalsValue(vos[i].getParentVO().getFstatusflag());

      if (!expr1 && !expr2 && !expr3) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4006002_0", "04006002-0137", null, new String[] {
              vos[i].getParentVO().getVbillcode()
            })/*单据{0}当前状态，不可进行 弃审。*/);
      }
      temMap.put(vos[i].getParentVO().getCdeliveryid(), vos[i]);
      cdeliveryids[i] = vos[i].getParentVO().getCdeliveryid();
    }
    if (SysInitGroupQuery.isICEnabled()) {
      this.checkExist4CAnd4Y(cdeliveryids, temMap);
    }
    this.checkExist4804(cdeliveryids, temMap);
  }

  /*
   * 检查弃审的发货单是否已经报检 报检过的发货单不能弃审
   * @param vos
   */
  private void isChecked(DeliveryVO[] vos) {
    for (DeliveryVO vo : vos) {
      DeliveryBVO[] bvos = vo.getChildrenVO();
      for (DeliveryBVO bvo : bvos) {
        if (bvo.getBcheckflag().booleanValue()) {
          ExceptionUtils
              .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                  .getNCLangRes().getStrByID("4006002_0", "04006002-0066")/*@res "当前发货单已经报检，不可进行弃审"*/);
        }
      }
    }
  }
}
