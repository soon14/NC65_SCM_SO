package nc.ui.so.m4310trantype;

import nc.ui.pubapp.AppUiContext;

import java.awt.BorderLayout;
import java.awt.Component;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m4310trantype.IM4310TranTypeService;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillEditListener;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.transtype.EditorContext;
import nc.ui.pub.transtype.ITranstypeEditor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m4310trantype.entity.M4310TranTypeVO;
import nc.vo.so.m4310trantype.entity.SalequoDataSource;

public class M4310TranTypeUI implements ITranstypeEditor, BillEditListener {

  private static final String TRAN_TEMPLATE_CODE = "4310tran";

  private BillCardPanel billCardPanel;

  private IM4310TranTypeService m4310TranTypeService;

  private String pk_group = AppUiContext.getInstance().getPkGroup();
  
  private M4310TranTypeVO tranTypeExtPropVO=null;

  @Override
  public void afterEdit(BillEditEvent e) {
    // 报价数据来源
    if ("fsourceflag".equals(e.getKey())) {
      Integer fsourceflag =
          (Integer) this.getBillCardPanel().getHeadItem("fsourceflag")
              .getValueObject();
      this.setFSourceFlagRefEditable(fsourceflag);
    }
  }

  @Override
  public void bodyRowChange(BillEditEvent e) {
    // TODO Auto-generated method stub
  }

  @Override
  public void doAction(EditorContext context) throws BusinessException {
    switch (context.getEventtype()) {
      case EditorContext.TYPE_BROWSE:
        // 不可编辑
        tranTypeExtPropVO =
        this.queryM4310TranTypeExtProps(context);
        this.browseTranTypeExtProp(tranTypeExtPropVO);
        this.setEditable(false);
        break;
      case EditorContext.TYPE_NEW:
        this.newTranstypeExtProp();
        break;
      case EditorContext.TYPE_EDIT:
        this.editTranstypeExtProp();
        break;
      case EditorContext.TYPE_CLEAR:
        this.setEditable(false);
        break;
      case EditorContext.TYPE_CANCEL:
        this.setEditable(false);
        this.browseTranTypeExtProp(tranTypeExtPropVO);
        break;
      default:
        break;
    }
  }

  @Override
  public void doButtonAction(ButtonObject bo) throws BusinessException {
    // TODO Auto-generated method stub

  }

  @Override
  public Component getEditorPane() {
    UIPanel panel = new UIPanel();
    panel.setLayout(new BorderLayout());
    panel.add(this.getBillCardPanel());

    this.billCardPanel.addBillEditListenerHeadTail(new BillEditListener() {
      @Override
      public void afterEdit(BillEditEvent e) {
        if ("fsourceflag".equals(e.getKey())) {
          Integer fsourceflag =
              (Integer) M4310TranTypeUI.this.billCardPanel.getHeadItem(
                  "fsourceflag").getValueObject();
          M4310TranTypeUI.this.setFSourceFlagRefEditable(fsourceflag);
        }
      }

      @Override
      public void bodyRowChange(BillEditEvent e) {
        // 表体行变化
      }
    });

    return panel;
  }

  @Override
  public ButtonObject[] getExtButtonObjects() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object getTransTypeExtObj(EditorContext context)
      throws BusinessException {
    M4310TranTypeVO vo =
        (M4310TranTypeVO) this.billCardPanel.getBillData().getHeaderValueVO(
            M4310TranTypeVO.class.getName());
    vo.setVtrantype(context.getTranstype().getPk_billtypecode());
    vo.setPk_group(this.pk_group);
    return vo;
  }

  private void browseTranTypeExtProp(M4310TranTypeVO tranTypeExtPropVO) {
    this.getBillCardPanel().getBillData().setHeaderValueVO(tranTypeExtPropVO);
  }

  /* 20110727 旷宗义根据需求变更删除敏感性校验
   * 需求描述:2011-5-30  预订单、销售报价单、销售订单、发货单、销售发票、销售费用单 补充单据交易类型被引用后，交易类型属性的可编辑性规则：
  销售订单：直运类型、销售模式不可编辑；
  销售费用单：所有属性均不可编辑。
  其它单据的交易类型被引用后，交易类型的属性可以编辑。  金冬梅 陈恩宇

  private boolean checkReferenced(M4310TranTypeVO vo) {
    List<String> pklist = new ArrayList<String>();
    pklist.add(vo.getVtrantype());
    if (ReferenceCheck.isReferenced("so_salequotationtype",
        pklist.toArray(new String[pklist.size()]))) {
      MessageDialog.showErrorDlg(this.billCardPanel, "提示",
          "交易类型已被报价单引用，除了交易类型名称之外其他属性都不能被修改");
      return true;
    }
    return false;
  }
  */

  private void editTranstypeExtProp() {

    /* 20110727 旷宗义根据需求变更删除敏感性校验
    M4310TranTypeVO vo =
        (M4310TranTypeVO) this.billCardPanel.getBillData().getHeaderValueVO(
            M4310TranTypeVO.class.getName());
    if (!this.checkReferenced(vo)) {
      this.setEditable(true);
    }
    else {
      this.setEditable(false);
    }
    */
    this.setEditable(true);

    Integer fsourceflag =
        (Integer) this.getBillCardPanel().getHeadItem("fsourceflag")
            .getValueObject();
    this.setFSourceFlagRefEditable(fsourceflag);
  }

  private BillCardPanel getBillCardPanel() {
    if (this.billCardPanel == null) {
      this.billCardPanel = new BillCardPanel();
      this.billCardPanel.loadTemplet(M4310TranTypeUI.TRAN_TEMPLATE_CODE, null,
          null, "@@@@");
      this.billCardPanel.setEnabled(false);
      this.billCardPanel.addEditListener(this);
    }
    return this.billCardPanel;
  }

  private IM4310TranTypeService getM4310TranTypeService() {
    if (this.m4310TranTypeService == null) {
      this.m4310TranTypeService =
          NCLocator.getInstance().lookup(IM4310TranTypeService.class);
    }
    return this.m4310TranTypeService;
  }

  private void newTranstypeExtProp() {
    this.billCardPanel.addNew();
    this.billCardPanel.updateValue();
    this.setEditable(true);
    this.setDefaultVaule();
  }

  private M4310TranTypeVO queryM4310TranTypeExtProps(EditorContext context) {
    M4310TranTypeVO tranTypeVO = new M4310TranTypeVO();
    if (context.getTranstype() == null
        || context.getTranstype().getPk_billtypecode() == null) {
      return tranTypeVO;
    }
    String billtypecode = context.getTranstype().getPk_billtypecode();
    try {
      tranTypeVO =
          this.getM4310TranTypeService().queryTranType(this.pk_group,
              billtypecode);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return tranTypeVO;
  }

  private void setDefaultVaule() {
    Integer fsourceflag =
        (Integer) this.getBillCardPanel().getHeadItem("fsourceflag")
            .getValueObject();
    this.setFSourceFlagRefEditable(fsourceflag);
  }

  private void setEditable(boolean isEdit) {
    this.billCardPanel.setEnabled(isEdit);
  }

  private void setFSourceFlagRefEditable(Integer fSourceFlag) {
    if (SalequoDataSource.PRICE_MANAGEMENT.value().equals(fSourceFlag)) {
      BillItem bsuccqteditableItem =
          this.getBillCardPanel().getHeadItem("bsuccqteditable");
      bsuccqteditableItem.setEnabled(true);
      BillItem bfailqteditableItem =
          this.getBillCardPanel().getHeadItem("bfailqteditable");
      bfailqteditableItem.setEnabled(true);
    }
    else {
      BillItem bsuccqteditableItem =
          this.getBillCardPanel().getHeadItem("bsuccqteditable");
      bsuccqteditableItem.setEnabled(false);
      bsuccqteditableItem.setValue(UFBoolean.FALSE);
      BillItem bfailqteditableItem =
          this.getBillCardPanel().getHeadItem("bfailqteditable");
      bfailqteditableItem.setEnabled(false);
      bfailqteditableItem.setValue(UFBoolean.FALSE);
    }
  }
}
