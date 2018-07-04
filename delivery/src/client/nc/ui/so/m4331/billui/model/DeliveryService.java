package nc.ui.so.m4331.billui.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m4331.IDeliveryMaintain;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.uif2.LoginContext;

public class DeliveryService implements IAppModelService{

  @Override
  public void delete(Object object) throws Exception {

    DeliveryVO bill = (DeliveryVO) object;
    DeliveryVO[] deletebills = new DeliveryVO[] {
      bill
    };
    IDeliveryMaintain service =
        NCLocator.getInstance().lookup(IDeliveryMaintain.class);
    try {
      service.deleteDelivery(deletebills);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

  }

  @Override
  public Object insert(Object object) throws Exception {
    DeliveryVO[] newbill = new DeliveryVO[] {
      (DeliveryVO) object
    };
    DeliveryVO[] retbill = null;
    IDeliveryMaintain service =
        NCLocator.getInstance().lookup(IDeliveryMaintain.class);
    try {
      retbill = service.insertDelivery(newbill);
      // 后台变化VO与前台合并
      new ClientBillCombinServer<DeliveryVO>().combine(newbill, retbill);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return newbill;
  }

  @Override
  public Object[] queryByDataVisibilitySetting(LoginContext context)
      throws Exception {
    // TODO 自动生成方法存根
    return null;
  }

  @Override
  public Object update(Object object) throws Exception {
    return null;
  }

}
