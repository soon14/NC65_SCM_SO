package nc.ui.so.m33.pub;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillData;
import nc.ui.pub.print.IDataSource;
import nc.ui.pubapp.pub.print.AppPrintDataSource;
import nc.ui.pubapp.pub.print.IDigitProcessor;
import nc.ui.pubapp.pub.print.IPrintItemValueProcessor;
import nc.ui.pubapp.pub.print.TemplateDataSourceGenerator;
import nc.vo.pub.CircularlyAccessibleValueObject;

public class SquareTemplateDataSourceGenerator extends
    TemplateDataSourceGenerator {

  private IDigitProcessor intraDigitProcessor;

  public BillCardPanel createBillCardPanel() throws Exception {
    BillCardPanel cardPanel = new BillCardPanel();
    cardPanel.setBillData(new BillData(this.template));
    // 显示行号
    cardPanel.setRowNOShow(true);
    // 编辑公式
    cardPanel.setAutoExecHeadEditFormula(true);
    if (this.intraDigitProcessor != null) {
      this.intraDigitProcessor.cardpanelDigitProcess(cardPanel);
    }
    CircularlyAccessibleValueObject[] vos =
        new CircularlyAccessibleValueObject[this.data.length];
    for (int i = 0; i < this.data.length; i++) {
      vos[i] = (CircularlyAccessibleValueObject) this.data[i];
    }
    cardPanel.getBillData().setBodyValueVO(vos);

    cardPanel.getBillData().getBillModel().loadLoadRelationItemValue();
    cardPanel.getBillData().getBillModel().execLoadFormula();

    cardPanel.getBillData().updateValue();
    // 手动执行表头表尾的item项的显示公式
    cardPanel.execHeadTailLoadFormulas();
   
    this.execLoadFormula(cardPanel);
    return cardPanel;
  }

  @Override
  public IDigitProcessor getDigitProcessor() {
    return this.intraDigitProcessor;
  }

  public IDataSource getSingleDataSource() throws Exception {
    AppPrintDataSource appDataSource =
        new AppPrintDataSource(this.createBillCardPanel());
    appDataSource.setValueProcessor(this.getValueProcessor());
    return appDataSource;
  }

  @Override
  public void setDigitProcessor(IDigitProcessor intraDigitProcessor1) {
    this.intraDigitProcessor = intraDigitProcessor1;
  }

}
