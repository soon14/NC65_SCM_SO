package nc.ui.so.m30.closemanage.action;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.print.IMetaDataDataSource;
import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction;
import nc.ui.pubapp.uif2app.model.BatchBillTableModel;
import nc.ui.pubapp.uif2app.view.BatchBillTable;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.trade.checkrule.VOChecker;

public class M30ClosePreViewAction extends MetaDataBasedPrintAction {

  private static final long serialVersionUID = -8268565232601090214L;

  private BatchBillTable billTable;

  public M30ClosePreViewAction() {
    this.setBtnName(NCLangRes.getInstance().getStrByID("4006011_0",
        "04006011-0382")/*‘§¿¿*/);
    this.setCode("preView");
    this.putValue(Action.SHORT_DESCRIPTION,
        NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0382")/*‘§¿¿*/);
  }

  public BatchBillTable getBillTable() {
    return this.billTable;
  }


  public void setBillTable(BatchBillTable billTable) {
    this.billTable = billTable;
  }

  @Override
  protected IMetaDataDataSource[] getDefaultMetaDataSource() {
    IMetaDataDataSource[] defaultDataSource = null;
    Object[] datas = null;
    boolean isMultiTask = false;
    if (this.isPrintAll()) {
      datas = ((BatchBillTableModel)this.getModel()).getRows().toArray(new Object[0]);
    }
    else {
      datas = ((BatchBillTableModel)this.getModel()).getSelectedOperaDatas();
    }

    if (null == datas || datas.length == 0) {
      return null;
    }

    List<SaleOrderViewVO> vos = new ArrayList<SaleOrderViewVO>();
    for (Object data : datas) {
      SaleOrderViewVO viewVO = (SaleOrderViewVO) data;
      this.processBeforePrint(viewVO);
      vos.add(viewVO);
    }
    List<SaleOrderVO> aggvos = new ArrayList<SaleOrderVO>();
    for (SaleOrderViewVO vo : vos) {
      aggvos.add(vo.changeToSaleOrderVO());
    }
    datas = new ListToArrayTool<SaleOrderVO>().convertToArray(aggvos);
    if (this.getDataSplit() != null) {
      datas = this.getDataSplit().splitData(datas);
    }
    if (!VOChecker.isEmpty(datas)) {
      if (isMultiTask) {
        defaultDataSource = new MetaDataSource[datas.length];
        for (int i = 0; i < defaultDataSource.length; i++) {
          defaultDataSource[i] = new MetaDataSource(new Object[] {
            datas[i]
          });
        }
      }
      else {
        defaultDataSource = new MetaDataSource[] {
          new MetaDataSource(datas)
        };
      }
    }
    return defaultDataSource;
  }

  private void processBeforePrint(SaleOrderViewVO viewVO) {
    SaleOrderBVO bvo = viewVO.getBody();
    bvo.setNsendunfinisednum(MathTool.sub(bvo.getNnum(),bvo.getNtotalsendnum()));
    bvo.setNtotaloutnum(MathTool.sub(bvo.getNnum(),bvo.getNtotaloutnum()));
    bvo.setNtotalinvoicenum(MathTool.sub(bvo.getNnum(),bvo.getNtotalinvoicenum()));
    if(bvo.getBbarsettleflag().booleanValue()&&
        bvo.getBbcostsettleflag().booleanValue()){
      bvo.setBbsettleendflag(UFBoolean.TRUE);
    }else{
      bvo.setBbsettleendflag(UFBoolean.FALSE);
    }
  }
}
