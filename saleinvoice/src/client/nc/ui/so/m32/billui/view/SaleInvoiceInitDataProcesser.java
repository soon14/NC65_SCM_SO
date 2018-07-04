package nc.ui.so.m32.billui.view;

import nc.bs.framework.common.NCLocator;
import nc.funcnode.ui.FuncletInitData;
import nc.itf.pub.link.ILinkQueryDataPlural;
import nc.itf.so.m32.ISaleInvoiceMaintain;
import nc.ui.pub.linkoperate.ILinkQueryData;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * 
 * @since 6.3
 * @version 2014-05-12 10:13:22
 * @author zhangyfr
 */
public class SaleInvoiceInitDataProcesser implements
    DefaultFuncNodeInitDataListener.IInitDataProcessor {

  private BillManageModel model;

  public BillManageModel getModel() {
    return this.model;
  }

  @Override
  public void process(FuncletInitData data) {

    if (data != null) {
      if (data.getInitData() instanceof ILinkQueryDataPlural) {
        ILinkQueryDataPlural datas = (ILinkQueryDataPlural) data.getInitData();
        String[] ids = datas.getBillIDs();
        ISaleInvoiceMaintain query =
            NCLocator.getInstance().lookup(ISaleInvoiceMaintain.class);
        try {
          SaleInvoiceVO[] vos = query.querySaleInvoice(ids);
          this.getModel().initModel(vos);
        }
        catch (BusinessException e) {
          ExceptionUtils.wrappException(e);
        }
      }
      else {
        ILinkQueryData querydata = (ILinkQueryData) data.getInitData();
        String id = querydata.getBillID();
        ISaleInvoiceMaintain query =
            NCLocator.getInstance().lookup(ISaleInvoiceMaintain.class);
        try {
          SaleInvoiceVO[] vos = query.querySaleInvoice(new String[] {
            id
          });
          this.getModel().initModel(vos);
        }
        catch (BusinessException e) {
          ExceptionUtils.wrappException(e);
        }
      }
    }
  }

  public void setModel(BillManageModel model) {
    this.model = model;
  }

}
