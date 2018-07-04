package nc.ui.so.m32.billui.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m32.ISaleInvoiceMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.paravo.CombinCacheVO;
import nc.vo.uif2.LoginContext;

public class SaleInvoiceService implements IAppModelService {

  private SaleInvoiceManageModel model;

  public SaleInvoiceManageModel getModel() {
    return model;
  }

  public void setModel(SaleInvoiceManageModel model) {
    this.model = model;
  }

  @Override
  public void delete(Object object) throws Exception {

    SaleInvoiceVO bill = (SaleInvoiceVO) object;
    SaleInvoiceVO[] deletebills = new SaleInvoiceVO[] {
      bill
    };
    ISaleInvoiceMaintain service =
        NCLocator.getInstance().lookup(ISaleInvoiceMaintain.class);
    try {
      service.deleteSaleInvoice(deletebills);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

  }

  @Override
  public Object insert(Object object) throws Exception {
    SaleInvoiceVO[] newbill = new SaleInvoiceVO[] {
      (SaleInvoiceVO) object
    };
    SaleInvoiceVO[] retbill = null;
    ISaleInvoiceMaintain service =
        NCLocator.getInstance().lookup(ISaleInvoiceMaintain.class);
    try {
      retbill = service.insertSaleInvoice(newbill);
      // 后台变化VO与前台合并
      ClientBillCombinServer<SaleInvoiceVO> util =
          new ClientBillCombinServer<SaleInvoiceVO>();
      util.combine(newbill, retbill);
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
    SaleInvoiceVO[] updatebill = new SaleInvoiceVO[] {
      (SaleInvoiceVO) object
    };
    SaleInvoiceVO[] retbill = null;
    ISaleInvoiceMaintain service =
        NCLocator.getInstance().lookup(ISaleInvoiceMaintain.class);
    try {
      retbill = service.updateSaleInvoice(updatebill);
      // 后台变化VO与前台合并
      ClientBillCombinServer<SaleInvoiceVO> util =
          new ClientBillCombinServer<SaleInvoiceVO>();
      util.combine(updatebill, retbill);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return updatebill;
  }

}
