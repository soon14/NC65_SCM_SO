package nc.ui.so.m33.pub;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.KeyStroke;

import nc.bs.uif2.IActionCode;
import nc.funcnode.ui.action.INCAction;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.print.IDataSource;
import nc.ui.pub.query.tools.ImageIconAccessor;
import nc.ui.pubapp.pub.print.IDigitProcessor;
import nc.ui.pubapp.pub.print.IPrintItemValueProcessor;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.actions.ActionInitializer;
import nc.vo.uif2.LoginContext;

public abstract class AbstractSquarePrintAction extends NCAction {
  private static final long serialVersionUID = 1L;

 //处理精度
  private IDigitProcessor digitProcessor;

  private SquareTemplateDataSourceGenerator dataSourceGenerator;

  private ShowUpableBillListView listView;

  private nc.ui.uif2.model.BillManageModel model;

  private boolean preview = true;

  private nc.ui.pub.print.PrintEntry printEntry;

  private IPrintItemValueProcessor valueProcessor;
 
  public IDigitProcessor getDigitProcessor() {
    return this.digitProcessor;
  }
  
  public void setDigitProcessor(
      IDigitProcessor digitProcessor) {
    this.digitProcessor = digitProcessor;
  }
  @Override
  public void doAction(ActionEvent e) throws Exception {
    Object[] vos = this.getBillVO();
    if (vos == null || vos.length == 0) {
      return;
    }
    if (this.getPrintEntry().selectTemplate() < 0) {
      return;
    }
    this.getDataSourceGenerator().setData(vos);
    IDataSource datasource =
        this.getDataSourceGenerator().getSingleDataSource();
    this.printEntry.setDataSource(datasource);

    if (this.preview) {
      this.printEntry.preview();
    }
    else {
      this.printEntry.print();
    }
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

  public nc.ui.uif2.model.BillManageModel getModel() {
    return this.model;
  }

  public IPrintItemValueProcessor getValueProcessor() {
    return this.valueProcessor;
  }

  /**
   * 
   * 是否预览
   * 
   * @return 如果是预览，返回true；如果是打印，返回false
   */
  public boolean isPreview() {
    return this.preview;
  }

  public void setDataSourceGenerator(
      SquareTemplateDataSourceGenerator dataSourceGenerator) {
    this.dataSourceGenerator = dataSourceGenerator;
  }

  public void setListView(ShowUpableBillListView listView) {
    this.listView = listView;
  }

  public void setMetaValueSet(boolean isMetaValueSet) {

    this.getDataSourceGenerator().setMetaValueSet(isMetaValueSet);
  }

  public void setModel(nc.ui.uif2.model.BillManageModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  public void setPreview(boolean preview) {
    if (preview) {
      // ActionInitializer.initializeAction(this, IActionCode.PREVIEW);
      this.setBtnName(NCLangRes.getInstance().getStrByID("4006010_0", "04006010-0085")/*预览*/);
      this.putValue(Action.ACCELERATOR_KEY,
          KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
      // setDisplayHotKey("(Ctrl+W)");
      this.putValue(INCAction.CODE, IActionCode.PREVIEW);
      // setBtnChinaName("预览");
      this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName() + "(Ctrl+W)");
      this.putValue(Action.SMALL_ICON,
          ImageIconAccessor.getIcon("toolbar/icon/preview.gif"));
    }
    else {
      ActionInitializer.initializeAction(this, IActionCode.PRINT);
      this.putValue(Action.SMALL_ICON,
          ImageIconAccessor.getIcon("toolbar/icon/print.gif"));
    }
    this.preview = preview;
  }

  public void setValueProcessor(IPrintItemValueProcessor valueProcessor) {
    this.valueProcessor = valueProcessor;
  }

  protected Object[] getBillVO() {
    return null;
  }

  protected LoginContext getContext() {
    return this.getModel().getContext();
  }

  @Override
  protected boolean isActionEnable() {
    return true;
  }

  private nc.ui.pub.print.PrintEntry getPrintEntry() {
    this.printEntry = new nc.ui.pub.print.PrintEntry(null, null);
    LoginContext ctx = this.getContext();
    this.printEntry.setTemplateID(ctx.getPk_group(), ctx.getNodeCode(),
        ctx.getPk_loginUser(), null);
    return this.printEntry;
  }

}
