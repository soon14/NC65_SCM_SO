package nc.ui.so.m32.billui.model;

import nc.ui.pubapp.uif2app.query2.model.IModelDataProcessor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.so.m32.billui.pub.SaleInvoiceCombin;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.paravo.CombinCacheVO;
import nc.vo.so.m32.paravo.CombinResultVO;

public class SaleInvoiceModelDataPrc implements IModelDataProcessor {

  private SaleInvoiceManageModel model;

  public SaleInvoiceManageModel getModel() {
    return this.model;
  }

  @Override
  public Object[] processModelData(IQueryScheme qryScheme, Object[] datas) {
    SaleInvoiceVO[] detailvos = (SaleInvoiceVO[]) datas;
    CombinResultVO combinres = this.combinSaleInvoices(detailvos);
    Object[] retdatas = null;
    if (combinres.getBcombinflag()) {
      retdatas = combinres.getCombinvos();
    }
    else {
      retdatas = datas;
    }
    // »º´æ»ã×Ü½á¹û
    this.getModel().setCombinCacheVO(combinres.getCacheVO());
    return retdatas;
  }

  public void setModel(SaleInvoiceManageModel model) {
    this.model = model;
  }

  private CombinResultVO combinSaleInvoices(SaleInvoiceVO[] newvos) {

    CombinResultVO combinres = null;
    CombinCacheVO cache = this.getModel().getCombinCacheVO();
    if (cache.getBcombinflag()) {
      cache.setCombinrela(null);
      SaleInvoiceCombin combinutil = new SaleInvoiceCombin();
      combinres = combinutil.combinSaleInvoices(newvos, cache);
    }
    else {
      combinres = new CombinResultVO(false);
    }
    return combinres;
  }
}
