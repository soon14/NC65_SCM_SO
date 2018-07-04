package nc.ui.so.m32.billui.pub;

import nc.vo.pubapp.scale.TableScaleProcessor;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.scale.BillModelScaleProcessor;

public class VatSubTotalPrecison {

  private static VatSubTotalPrecison instance = new VatSubTotalPrecison();

  private static final String[] MNYKEY = new String[] {
    SaleInvoiceBVO.NTAX, SaleInvoiceBVO.NTAXMNY, SaleInvoiceBVO.NCALTAXMNY,
    SaleInvoiceBVO.NMNY,
  };

  // 表体税率
  private static final String[] TAXRATEKEY = new String[] {
    SaleInvoiceBVO.NTAXRATE
  };

  private VatSubTotalPrecison() {
    // 缺省构造方法
  }

  public static VatSubTotalPrecison getInstance() {
    return VatSubTotalPrecison.instance;
  }

  public void setModelPrecision(String pk_group, BillModel model) {
    TableScaleProcessor scaleprocess =
        new BillModelScaleProcessor(pk_group, model);
    this.setTablePrecision(scaleprocess);
  }

  private void setTablePrecision(TableScaleProcessor scaleprocess) {

    // 本币金额
    scaleprocess.setMnyCtlInfo(VatSubTotalPrecison.MNYKEY,
        SaleInvoiceHVO.CCURRENCYID);
    // 税率
    scaleprocess.setTaxRateCtlInfo(VatSubTotalPrecison.TAXRATEKEY);
    scaleprocess.process();
  }
}
