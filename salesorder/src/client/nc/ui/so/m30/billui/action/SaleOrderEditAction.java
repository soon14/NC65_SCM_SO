package nc.ui.so.m30.billui.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.pf.IPFWorkflowQry;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.pub.power.PowerCheckUtils;
import nc.ui.pubapp.uif2app.actions.EditAction;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.CardEditSetter;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pub.power.PowerActionEnum;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.util.SOSysParaInitUtil;

@SuppressWarnings("serial")
public class SaleOrderEditAction extends EditAction {
  /**
   * 
   */
  private static final long serialVersionUID = 5247655763415269579L;

  private BillForm editor;

  private AbstractAppModel model;

  private SaleOrderBillForm billform;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    
    // add by wangshu6 for 636 2014-01-20 销售发票审批中支持修订
    // 检查当前操作人是不是审批人， 如果是审批中状态并且当前操作人不是审批人，不允许修改
    this.checkApprover();
    // end 
    
    SaleOrderVO bill = (SaleOrderVO) this.getModel().getSelectedData();
    // 业务操作维护权限
    PowerCheckUtils.checkHasPermission(new SaleOrderVO[] {
        bill
    }, SOBillType.Order.getCode(), PowerActionEnum.EDIT.getActioncode(),
    SaleOrderHVO.VBILLCODE);

    this.model.setUiState(UIState.EDIT);

//    this.setUpdateBillDefValue();

    // 录入冲抵金额后订单表体行数量\单价\金额\等字段不允许编辑。
    CardEditSetter editset =
        new CardEditSetter((SaleOrderBillForm) this.editor);
    editset.setEditEnable();
    editset.setEditEnableByFlargessTypeFlag();

    // 1.取交易类型参数
    String tranTypeCode = bill.getParentVO().getVtrantypecode();
    String carsubtypeid = bill.getParentVO().getCarsubtypeid();
    if (null != tranTypeCode && null != carsubtypeid) {
      BillItem bodyitem =
          this.editor.getBillCardPanel().getBodyItem(SaleOrderBVO.BLARGESSFLAG);
      bodyitem.setEdit(false);
    }
    this.clearAggffilevo();
  }
  
  private void clearAggffilevo() {
    CardKeyValue keyValue =
        new CardKeyValue(this.getEditor().getBillCardPanel());
    int rowCount = keyValue.getBodyCount();
    for (int i = 0; i < rowCount; i++) {
      keyValue.setBodyValue(i, SOConstant.AGGFFILEVO, null);
    }
  }

  /**
   * 检查当前操作人是不是审批人， 如果是审批中状态并且当前操作人不是审批人，不允许修改
   */
  private void checkApprover() {
    SaleOrderVO vo = (SaleOrderVO) this.getModel().getSelectedData();
    SaleOrderHVO hvo = vo.getParentVO();
    Integer status = hvo.getFstatusflag();
    // 只有当审批中才进行 当前操作人是否是审批人的校验
    if (BillStatus.AUDITING.getIntegerValue().equals(status)) {
      // 获取单据ID
      String billID = hvo.getCsaleorderid();
      // 获取交易类型编码
      String vtrantypecode = hvo.getVtrantypecode();
      // 获取当前操作人
      String user = AppContext.getInstance().getPkUser();
      boolean isCheckMan = false;
      try {
         isCheckMan  =  NCLocator.getInstance().lookup(IPFWorkflowQry.class)
            .isCheckman(billID, vtrantypecode, user);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
      if (!isCheckMan) {
        ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0486")/*当前操作人不是审批人，审批中单据禁止修改！*/);
      }
    
    }
  }

  /**
   * 
   * @return BillForm
   */
  public BillForm getEditor() {
    return this.editor;
  }

  @Override
  public AbstractAppModel getModel() {
    return this.model;
  }

  /**
   * 
   * 
   * @return s
   */
  public SaleOrderBillForm getBillform() {
    return this.billform;
  }

  /**
   * 
   * 
   * @param billform
   */
  public void setBillform(SaleOrderBillForm billform) {
    this.billform = billform;
  }

  /**
   * 
   * @param editor
   */
  public void setEditor(BillForm editor) {
    this.editor = editor;
  }

  @Override
  public void setModel(AbstractAppModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    boolean iseidtable =
        this.model.getUiState() == UIState.NOT_EDIT
            && this.model.getSelectedData() != null;
    if (iseidtable) {
      SaleOrderVO vo = (SaleOrderVO) this.getModel().getSelectedData();
      if (vo != null) {
        Integer fstatusflag = vo.getParentVO().getFstatusflag();
        // modify by wangshu6 for 636 2015-01-22 销售订单审批中支持修改
        // 审批中状态可以修改(取组织级参数销售订单是否支持审批中修改)
        boolean SO32 = this.getPara(vo);
        if (SO32) {
          iseidtable =
              BillStatus.AUDITING.equalsValue(fstatusflag)
                  || BillStatus.FREE.equalsValue(fstatusflag)
                  || BillStatus.NOPASS.equalsValue(fstatusflag);
        }
        else {
          iseidtable =
              BillStatus.FREE.equalsValue(fstatusflag)
                  || BillStatus.NOPASS.equalsValue(fstatusflag);
        }
      }
    }
    return iseidtable;
  }

  private boolean getPara(SaleOrderVO vo) {
    String pk_org = vo.getParentVO().getPk_org();
    UFBoolean so32 = SOSysParaInitUtil.getSO32(pk_org);
    if (null == so32) {
      return false;
    }
    return so32.booleanValue();
  }

  private void setUpdateBillDefValue() {
    CardKeyValue keyValue =
        new CardKeyValue(this.getEditor().getBillCardPanel());
    keyValue.setHeadValue(SOItemKey.FSTATUSFLAG,
        BillStatus.FREE.getIntegerValue());

  }

  /**
   * 根据交易类型设置标题赠品兑付字段
   * 
   * @param keyValue
   * @param rows
   */
  private void setBlrgcashflagByTranType(IKeyValue keyValue, int[] rows) {
    for (int row : rows) {
      keyValue.setBodyValue(row, SaleOrderBVO.BLRGCASHFLAG, UFBoolean.FALSE);
      keyValue.setBodyValue(row, SaleOrderBVO.BLARGESSFLAG, UFBoolean.FALSE);
    }
  }
}
