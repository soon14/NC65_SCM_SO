package nc.ui.so.m30.closemanage.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.uif2app.view.BatchBillTable;
import nc.ui.pubapp.uif2app.view.util.BillPanelUtils;
import nc.ui.so.m30.pub.SaleOrderPrecision;
import nc.vo.pubapp.AppContext;
import nc.vo.so.m30.entity.SaleOrderBVO;

public class SaleOrderCloseManageListView extends BatchBillTable {

  /**
   * 
   */
  private static final long serialVersionUID = -7148555028163535040L;

  @Override
  public void initUI() {
    super.initUI();

    // 销售订单关闭多表头处理
    Map<String, List<String>> groupMap = new HashMap<String, List<String>>();
    List<String> alSend = new ArrayList<String>();
    alSend.add(SaleOrderBVO.NSENDUNFINISEDNUM);
    alSend.add(SaleOrderBVO.NSENDAUDITNUM);
    alSend.add(SaleOrderBVO.BBSENDENDFLAG);
    List<String> alOut = new ArrayList<String>();
    alOut.add(SaleOrderBVO.NOUTUNFINISEDNUM);
    alOut.add(SaleOrderBVO.NOUTAUDITNUM);
    alOut.add(SaleOrderBVO.BBOUTENDFLAG);
    List<String> alInvoice = new ArrayList<String>();
    alInvoice.add(SaleOrderBVO.NINVUNFINISEDNUM);
    alInvoice.add(SaleOrderBVO.NINVOICEAUDITNUM);
    alInvoice.add(SaleOrderBVO.BBINVOICENDFLAG);
    List<String> alSettle = new ArrayList<String>();
    alSettle.add(SaleOrderBVO.NOUTNOTAUDITNUM);
    alSettle.add(SaleOrderBVO.NNOTARNUM);
    alSettle.add(SaleOrderBVO.NLOSSNOTAUDITNUM);
    alSettle.add(SaleOrderBVO.NNOTCOSTNUM);
    alSettle.add(SaleOrderBVO.BBSETTLEENDFLAG);
    groupMap
        .put(
            NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0293")/*发货数量*/,
            alSend);
    groupMap
        .put(
            NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0294")/*出库数量*/,
            alOut);
    groupMap
        .put(
            NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0295")/*开票数量*/,
            alInvoice);
    groupMap
        .put(
            NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0296")/*结算数量*/,
            alSettle);

    BillPanelUtils.createSingleLayMultiHeadTable(this.getBillCardPanel(), this
        .getBillCardPanel().getCurrentBodyTableCode(), groupMap);

    // 设置界面精度
    String pk_group = AppContext.getInstance().getPkGroup();
    SaleOrderPrecision.getInstance().setModelPrecision(pk_group,
        this.getBillCardPanel().getBillModel());
  }
}
