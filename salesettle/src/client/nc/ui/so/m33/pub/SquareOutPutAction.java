package nc.ui.so.m33.pub;

import java.awt.event.ActionEvent;

import nc.ui.pub.print.IDataSource;
import nc.ui.pubapp.pub.print.IDigitProcessor;
import nc.ui.pubapp.pub.print.IPrintItemValueProcessor;
import nc.ui.pubapp.uif2app.actions.OutputAction;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.trade.checkrule.VOChecker;

public class SquareOutPutAction extends OutputAction {
  private static final long serialVersionUID = 8292943936065661594L;

  private IDigitProcessor digitProcessor;

  private SquareTemplateDataSourceGenerator dataSourceGenerator;

  private ShowUpableBillListView listView;

  private nc.ui.pub.print.PrintEntry printEntry;

  private IPrintItemValueProcessor valueProcessor;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.printEntry = this.getPrintEntry();
    if (this.printEntry.selectTemplate() != 1) {
      return;
    }
    Object[] vos = this.getBillVO();
    if (vos == null || vos.length == 0) {
      return;
    }
    this.getDataSourceGenerator().setData(vos);
    IDataSource datasource =
        this.getDataSourceGenerator().getSingleDataSource();

    this.printEntry.setDataSource(datasource);

    this.printEntry.output();
  }

  public SquareTemplateDataSourceGenerator getDataSourceGenerator() {
    if (this.dataSourceGenerator == null) {

      this.dataSourceGenerator = new SquareTemplateDataSourceGenerator();
      // this.dataSourceGenerator.setTemplate(this.getEditor()
      // .getBillCardPanel().getBillData().getBillTempletVO());
      this.dataSourceGenerator.setTemplate(this.getListView()
          .getBillListPanel().getBillListData().getBillTempletVO());
      this.dataSourceGenerator.setDigitProcessor(this.digitProcessor);
      this.dataSourceGenerator.setValueProcessor(this.valueProcessor);
    }
    return this.dataSourceGenerator;
  }

  public ShowUpableBillListView getListView() {
    return this.listView;
  }

  public IPrintItemValueProcessor getValueProcessor() {
    return this.valueProcessor;
  }

  public void setDataSourceGenerator(
      SquareTemplateDataSourceGenerator dataSourceGenerator) {
    this.dataSourceGenerator = dataSourceGenerator;
  }

  public void setListView(ShowUpableBillListView listView1) {
    this.listView = listView1;
  }

  public void setValueProcessor(IPrintItemValueProcessor valueProcessor) {
    this.valueProcessor = valueProcessor;
  }

  protected Object[] getBillVO() {
    // 销售结算打印数据 都从界面取得
    // if ((this.getModel().getData() == null)
    // || (this.getModel().getData().size() == 0)) {
    // return null;
    // }
    if (this.getListView().getBillListPanel().getBodyBillModel()
        .getBodySelectedVOs(SquareOutViewVO.class.getName()) == null
        || this.getListView().getBillListPanel().getBodyBillModel()
            .getBodySelectedVOs(SquareOutViewVO.class.getName()).length == 0) {
      Object[] vos = new Object[1];
      vos[0] = this.getModel().getSelectedData();
      return vos;
    }

    return this.getListView().getBillListPanel().getBodyBillModel()
        .getBodySelectedVOs(SquareOutViewVO.class.getName());
  }

  @Override
  protected boolean isActionEnable() {
    boolean flag = false;
    // Object data = this.getModel().getSelectedData();
    Object[] datas =
        this.getListView().getBillListPanel().getBodyBillModel()
            .getBodySelectedVOs(SquareOutViewVO.class.getName());
    if (datas != null && datas.length > 0) {
      if (!VOChecker.isEmpty(datas[0])) {
        SquareOutViewVO svo = (SquareOutViewVO) datas[0];
        flag = svo.ifCanET();
      }
    }
    return flag;
  }

  public IDigitProcessor getDigitProcessor() {
    return digitProcessor;
  }

  public void setDigitProcessor(IDigitProcessor digitProcessor) {
    this.digitProcessor = digitProcessor;
  }

}
