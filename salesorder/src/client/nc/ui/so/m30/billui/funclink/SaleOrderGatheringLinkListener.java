package nc.ui.so.m30.billui.funclink;

import nc.bs.framework.common.NCLocator;
import nc.funcnode.ui.FuncletLinkEvent;
import nc.funcnode.ui.FuncletLinkListener;
import nc.itf.so.m30.self.ISaleOrderMaintain;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.vo.arap.gathering.AggGatheringBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 收款单保存监听
 * <p>用最新销售订单VO刷新model中数据</p>
 * 
 * @since 6.0
 * @version 2011-5-30 下午02:15:06
 * @author 刘志伟
 */
public class SaleOrderGatheringLinkListener implements FuncletLinkListener {

  private BillManageModel model;

  @Override
  public void dealLinkEvent(FuncletLinkEvent event) {
    AggGatheringBillVO bill = (AggGatheringBillVO) event.getUserObject();

    ISaleOrderMaintain service =
        NCLocator.getInstance().lookup(ISaleOrderMaintain.class);
    SaleOrderVO[] newBills = null;
    // 如果从订单链接的收款单界面 做复制 则此收款单与订单没有关系 不需要订单做任何处理
    if (bill.getBodyVOs()[0].getSrc_billid() != null
        && !bill.getBodyVOs()[0].getSrc_billid().isEmpty()) {
      try {
        newBills = service.querySaleorder(new String[] {
          bill.getBodyVOs()[0].getSrc_billid()
        });
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
      this.getModel().directlyUpdate(newBills);
    }
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
  }

}
