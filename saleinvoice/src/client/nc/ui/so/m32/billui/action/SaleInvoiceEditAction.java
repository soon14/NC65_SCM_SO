package nc.ui.so.m32.billui.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.pf.IPFWorkflowQry;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.pub.power.PowerCheckUtils;
import nc.ui.pubapp.uif2app.actions.EditAction;
import nc.ui.so.m32.billui.view.SaleInvoiceEditor;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pub.power.PowerActionEnum;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.util.SOSysParaInitUtil;

/**
 * 销售发票修改按钮响应类
 * 
 * @since 6.3
 * @version 2012-12-21 上午11:18:49
 * @author yaogj
 */
public class SaleInvoiceEditAction extends EditAction {

  /**
   * Version
   */
  private static final long serialVersionUID = -3566521350890738317L;

  private SaleInvoiceEditor editor;

  @Override
  public void doAction(ActionEvent e) throws Exception {

    // add by wangshu6 for 636 2014-01-20 销售发票审批中支持修订
    // 检查当前操作人是不是审批人， 如果是审批中状态并且当前操作人不是审批人，不允许修改
    this.checkApprover();
    // end

    SaleInvoiceVO bill = (SaleInvoiceVO) this.getModel().getSelectedData();
    // 业务操作维护权限
    PowerCheckUtils.checkHasPermission(new SaleInvoiceVO[] {
      bill
    }, SOBillType.Invoice.getCode(), PowerActionEnum.EDIT.getActioncode(),
        SaleInvoiceHVO.VBILLCODE);
    super.doAction(e);

    this.setUpdateBillDefValue();
    this.editor.setCardEditEnable();
  }

  /**
   * 检查当前操作人是不是审批人， 如果是审批中状态并且当前操作人不是审批人，不允许修改
   */
  private void checkApprover() {
    SaleInvoiceVO vo = (SaleInvoiceVO) this.getModel().getSelectedData();
    SaleInvoiceHVO hvo = vo.getParentVO();
    Integer status = hvo.getFstatusflag();
    // 只有当审批中才进行 当前操作人是否是审批人的校验
    if (BillStatus.AUDITING.getIntegerValue().equals(status)) {
      boolean isApprover = false;
      // 获取单据ID
      String billID = hvo.getCsaleinvoiceid();
      // 获取交易类型编码
      String vtrantypecode = hvo.getVtrantypecode();
      // 获取当前操作人
      String user = AppContext.getInstance().getPkUser();
      try {
        isApprover =
            NCLocator.getInstance().lookup(IPFWorkflowQry.class)
                .isCheckman(billID, vtrantypecode, user);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
      if (!isApprover) {
        ExceptionUtils
            .wrappBusinessException(NCLangRes.getInstance().getStrByID(
                "4006008_0", "04006008-0157")/*当前操作人不是审批人，审批中单据禁止修改！*/);
      }
    }
  }

  /**
   * 
   * @return 发票卡片
   */
  public SaleInvoiceEditor getEditor() {
    return this.editor;
  }

  /**
   * 
   * @param editor 发票卡片
   */
  public void setEditor(SaleInvoiceEditor editor) {
    this.editor = editor;
  }

  @Override
  protected boolean isActionEnable() {

    BillManageModel mangemodel = (BillManageModel) this.getModel();
    boolean isEnable =
        mangemodel.getUiState() == UIState.NOT_EDIT
            && null != mangemodel.getSelectedData();
    SaleInvoiceVO selectedData = (SaleInvoiceVO) mangemodel.getSelectedData();
    Object[] selectedRows = mangemodel.getSelectedOperaDatas();
    if(selectedRows==null || selectedRows.length>1){
      return false;
    }
    if (selectedData != null) {
      Integer billstatus = selectedData.getParentVO().getFstatusflag();
      if (isEnable) {
       
        // add by wangshu6 for 636 2015-01-15 销售发票审批中支持修改 审批中状态可以修改
        // 销售订单支持审批中修改。审批中允许当前审批人修改
        // 查询参数
        boolean SO33 = this.getPara(selectedData);
        if (SO33) {
          isEnable =
              BillStatus.AUDITING.equalsValue(billstatus)
                  || BillStatus.FREE.equalsValue(billstatus)
                  || BillStatus.NOPASS.equalsValue(billstatus);
        }
        else {
          isEnable =
              BillStatus.FREE.equalsValue(billstatus)
                  || BillStatus.NOPASS.equalsValue(billstatus);

        }
        String vdef8 = null==selectedData.getParentVO().getVdef8()?"":selectedData.getParentVO().getVdef8().toString();
        if(vdef8!=null&!"".equals(vdef8)&&"0".equals(vdef8)){
        	return true;
        }else if(vdef8!=null&!"".equals(vdef8)&&"1".equals(vdef8)){
      	  return false;
        }
      }
    }
    return isEnable;
  }

  private boolean getPara(SaleInvoiceVO vo) {
    String pk_org = vo.getParentVO().getPk_org();
    UFBoolean so33 = SOSysParaInitUtil.getSO33(pk_org);
    if (null == so33) {
      return false;
    }
    return so33.booleanValue();
  }

  private void setUpdateBillDefValue() {
    CardKeyValue keyValue =
        new CardKeyValue(this.getEditor().getBillCardPanel());
    // modify by wangshu6 2015-01-19 销售发票修改不改单据状态
    // keyValue.setHeadValue(SOItemKey.FSTATUSFLAG,
    // BillStatus.FREE.getIntegerValue());
    // 审批人、审批时间
    keyValue.setHeadValue(SOItemKey.APPROVER, null);
    keyValue.setHeadValue(SOItemKey.TAUDITTIME, null);
  }
}
