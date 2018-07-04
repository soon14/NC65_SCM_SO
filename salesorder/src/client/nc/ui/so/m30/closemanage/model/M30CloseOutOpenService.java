package nc.ui.so.m30.closemanage.model;

import nc.itf.so.m30.closemanage.ISaleOrderCloseManageMaintain;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.ui.scmpub.model.SCMResumeExceptionInvoker;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.SOParameterVO;

public class M30CloseOutOpenService implements
    ISingleBillService<SOParameterVO> {

  @Override
  public SOParameterVO operateBill(SOParameterVO paraVO) throws Exception {
    SOParameterVO[] ret = null;
    // 参数paraVOs中解析出bills
    SaleOrderViewVO view = (SaleOrderViewVO) paraVO.getView();
    SCMResumeExceptionInvoker invoker = new SCMResumeExceptionInvoker();
    ISaleOrderCloseManageMaintain service =
        invoker.getService(ISaleOrderCloseManageMaintain.class);// IMyService为你调用的服务
    ret = service.openSaleOrderOut(new SaleOrderVO[] {
      view.changeToSaleOrderVO()
    }, false);
    return null == ret ? null : ret[0];
  }

}
