package nc.impl.so.m4331;

import nc.bs.framework.common.NCLocator;
import nc.impl.so.m4331.action.maintain.DeliveryDeleteAction;
import nc.itf.so.m4331.IDeliveryMaintain;
import nc.itf.so.m4331.IDeliveryScriptMaintain;
import nc.itf.so.pub.ref.ic.m4c.SOATPprocess;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryUserObject;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.scale.DeliveryScaleProcessor;

public class DeliveryScriptMaintainImpl implements IDeliveryScriptMaintain {

  @Override
  public void deliveryDelete(DeliveryVO[] vos, PfUserObject userObj)
      throws BusinessException {
    try {
      // 蓝字发货单删除不做ATP检查，红字发货单删除做ATP检查
      // 批量删除也是一条一条的走这个删除逻辑
      this.checkAtp(userObj, vos);

      DeliveryDeleteAction action = new DeliveryDeleteAction();
      action.delete(vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public DeliveryVO[] deliveryWrite(DeliveryVO[] vos, PfUserObject userObj,
      DeliveryVO[] originBills) throws BusinessException {
    DeliveryVO[] retvos = null;
    try {
      // 判断是否做ATP检查，在session中设置标识
      this.checkAtp(userObj, vos);
      // 精度检查
      this.checkScale(userObj, vos);
      IDeliveryMaintain maintainsrv =
          NCLocator.getInstance().lookup(IDeliveryMaintain.class);
      if (StringUtil.isEmptyWithTrim(vos[0].getParentVO().getCdeliveryid())) {
        retvos = maintainsrv.insertDelivery(vos);
      }
      else {
        retvos = maintainsrv.updateDelivery(vos, originBills);
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return retvos;

  }

  private void checkScale(PfUserObject userObj, DeliveryVO[] vos) {
    // 外部交换平台的单据才进行精度检查。
    if (userObj == null || userObj.getUserObject() == null) {
      new DeliveryScaleProcessor().checkBillPrecision(vos);
    }
    else {
      DeliveryUserObject obj = (DeliveryUserObject) userObj.getUserObject();
      if (!obj.isIsclientsave()) {
        new DeliveryScaleProcessor().checkBillPrecision(vos);
      }
    }
  }

  private void checkAtp(PfUserObject userObj, DeliveryVO[] vos) {
    if (vos != null && vos.length > 0) {
      if (this.IsRedDelivery(vos[0])) {
        // 不做ATP检查
        SOATPprocess.abandonATPCheck();
      }
      else {
        if (userObj != null
            && userObj.getBusinessCheckMap().get(
                BusinessCheck.ATPCheck.getCheckCode()) != null
            && !userObj.getBusinessCheckMap()
                .get(BusinessCheck.ATPCheck.getCheckCode()).booleanValue()) {
          // 不做ATP检查
          SOATPprocess.abandonATPCheck();
        }
      }
    }
  }

  /*
   * 判断是否是红字发货单
   */
  private boolean IsRedDelivery(DeliveryVO deliveryVO) {
    DeliveryBVO[] deliverybvos = deliveryVO.getChildrenVO();
    if (deliverybvos != null && deliverybvos.length > 0) {
      if (deliverybvos[0].getStatus() == VOStatus.NEW) {
        if (deliverybvos[0].getNnum().compareTo(UFDouble.ZERO_DBL) < 0) {
          return true;
        }

      }

    }

    return false;
  }

}
