package nc.ui.so.m4331trantype;

import java.awt.BorderLayout;
import java.awt.Component;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.so.m4331trantype.IM4331TranTypeService;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.transtype.EditorContext;
import nc.ui.pub.transtype.ITranstypeEditor;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.Log;
import nc.vo.so.m4331trantype.entity.M4331trantypeVO;
import nc.vo.trade.checkrule.VOChecker;

public class M4331TransTypeUI implements ITranstypeEditor {

  private BillCardPanel billCardPanel;

  private String cgroupid = WorkbenchEnvironment.getInstance().getGroupVO()
      .getPk_group();

  private String templet = "4331type";

  private UIPanel uipanel = new UIPanel();
  
  private M4331trantypeVO bill=null;

  private void addNewTranstype() {
    this.billCardPanel.addNew();
    this.billCardPanel.updateValue();
    this.setEditable(true);
  }

  private void clearEditorPane() {
    // TODO 自动生成方法存根
  }

  @Override
  public void doAction(EditorContext context) throws BusinessException {

    switch (context.getEventtype()) {
      case EditorContext.TYPE_BROWSE:

         bill = this.queryTranstypeExtProp(context);
        this.setTranType(bill);
        // 不可编辑
        this.setEditable(false);
        break;
      case EditorContext.TYPE_NEW:
        // 新增时将编辑器界面清空，状态为可编辑
        this.addNewTranstype();
        break;
      case EditorContext.TYPE_EDIT:

        this.setEditable(true);

        break;
      case EditorContext.TYPE_CLEAR:
        this.clearEditorPane();
        this.setEditable(false);
        break;
      case EditorContext.TYPE_CANCEL:
        this.setEditable(false);
        this.setTranType(bill);
        break;
      default:
        break;
    }
  }

  @Override
  public void doButtonAction(ButtonObject bo) throws BusinessException {
    // TODO 自动生成方法存根

  }

  @Override
  public Component getEditorPane() {
    this.initilize();
    return this.uipanel;
  }

  @Override
  public ButtonObject[] getExtButtonObjects() {
    // TODO 自动生成方法存根
    return null;
  }

  @Override
  public Object getTransTypeExtObj(EditorContext context)
      throws BusinessException {
    M4331trantypeVO vo =
        (M4331trantypeVO) this.billCardPanel.getBillData().getHeaderValueVO(
            M4331trantypeVO.class.getName());
    vo.setVtrantypecode(context.getTranstype().getPk_billtypecode());
    vo.setPk_group(this.cgroupid);
    return vo;
  }

  private void initilize() {
    if (this.billCardPanel != null) {
      return;
    }
    this.billCardPanel = new BillCardPanel();
    this.billCardPanel.loadTemplet(this.templet, null, null, "@@@@");
    this.billCardPanel.setEnabled(false);
    this.uipanel.setLayout(new BorderLayout());
    this.uipanel.add(this.billCardPanel);

  }

  private M4331trantypeVO queryTranstypeExtProp(EditorContext context) {

    if (VOChecker.isEmpty(context.getTranstype())
        || VOChecker.isEmpty(context.getTranstype().getPk_billtypecode())) {
      return new M4331trantypeVO();
    }
    String billtypecode = context.getTranstype().getPk_billtypecode();
    M4331trantypeVO returnVos = null;
    try {
      IM4331TranTypeService service =
          NCLocator.getInstance().lookup(IM4331TranTypeService.class);
      returnVos = service.queryTranType(this.cgroupid, billtypecode);
    }
    catch (Exception e) {
      Log.info(e);
      ExceptionUtils.wrappException(e);
    }
    return returnVos;
  }

  private void setEditable(boolean isEdit) {
    this.billCardPanel.setEnabled(isEdit);
  }

  private void setTranType(M4331trantypeVO bill) {
    this.billCardPanel.getBillData().setHeaderValueVO(bill);
  }
}
