package nc.ui.so.m30.billui.action.assist;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.pubapp.pub.exception.IResumeException;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.pu.m21.so.m30.ICoopOrderPushSaveFor30;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.RefreshSingleAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.scmpub.action.ReferenceAction;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.scmpub.util.ResumeExceptionUIProcessUtils;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 销售订单协同生成采购订单
 * 
 * @since 6.0
 * @version 2011-3-29 下午01:36:45
 * @author 祝会征
 */
public class SaleOrderPushTo21Action extends ReferenceAction {

  private static final long serialVersionUID = 318973906744522014L;

  private SaleOrderBillForm editor;

  private BillManageModel model;

  private RefreshSingleAction refreshAction;

  @Override
  public void doAction(ActionEvent e) throws Exception {
	
    if (!SysInitGroupQuery.isPOEnabled()) {
         ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
	          "4006011_0", "04006011-0521")/*请启用采购模块！*/);
	}
	  
    boolean flag = this.editor.isComponentVisible();
    SaleOrderVO[] vos = null;
    if (flag) {
      vos = new SaleOrderVO[1];
      vos[0] = (SaleOrderVO) this.model.getSelectedData();
    }
    else if (!flag) {
      Object[] objs = this.model.getSelectedOperaDatas();
      vos = new SaleOrderVO[objs.length];
      int i = 0;
      for (Object obj : objs) {
        vos[i] = (SaleOrderVO) obj;
        i++;
      }
    }
    else {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0013")/*@res "请选中销售订单。"*/);
    }
    SaleOrderVO[]filervo = this.filerVO(vos);
    
    this.pushTO21(filervo, e);
  }

  public SaleOrderBillForm getEditor() {
    return this.editor;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public RefreshSingleAction getreFreshAction() {
    return this.refreshAction;
  }

  public void setEditor(SaleOrderBillForm editor) {
    this.editor = editor;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  public void setRefreshAction(RefreshSingleAction refreshAction1) {
    this.refreshAction = refreshAction1;
  }

  @Override
  public void setSourceBillName(String sourceBillName) {
    super.setSourceBillName(sourceBillName);
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_TO21);
  }

  @Override
  protected boolean isActionEnable() {
    boolean isEnable =
        (this.getModel().getAppUiState() == AppUiState.NOT_EDIT)
            && (null != this.getModel().getSelectedData());
    if (isEnable) {
      Object[] selectedRows = this.getModel().getSelectedOperaDatas();
      SaleOrderVO selectedData = (SaleOrderVO) this.model.getSelectedData();
      Integer status = selectedData.getParentVO().getFstatusflag();
      // 协同生成的so不能再次协同生成po
      String poCode = selectedData.getParentVO().getVcooppohcode();
      if (poCode != null && poCode.length() > 0) {
        isEnable = false;
      }
      else {
        isEnable =
            ((null != selectedRows) && (selectedRows.length > 1))
                || BillStatus.AUDIT.equalsValue(status)
                || BillStatus.AUDITING.equalsValue(status);
      }
    }
    return isEnable;
  }

  private SaleOrderVO[] filerVO(SaleOrderVO[] vos) {
    if(vos==null|| vos.length==0){
      return new SaleOrderVO[0];
    }
    Set<SaleOrderVO> voSet = new HashSet<SaleOrderVO>();
    StringBuffer errMsg = new StringBuffer();
    for (SaleOrderVO vo : vos) {
      boolean flag = this.filterOrderVO(vo, errMsg);
      if (!flag) {
        continue;
      }
      SaleOrderBVO[] bvos = vo.getChildrenVO();
      if (bvos.length == 1) {
        voSet.add(vo);
        continue;
      }
      String tempStr = null;
      for (SaleOrderBVO bvo : bvos) {
        if (null == tempStr) {
          tempStr = bvo.getCsettleorgvid();
          continue;
        }
        String setorg = bvo.getCsettleorgvid();
        if (!setorg.equals(tempStr)) {
          String code = vo.getParentVO().getVbillcode();
          errMsg.append(NCLangRes.getInstance().getStrByID("4006011_0",
              "04006011-0240", null, new String[] {
                code
              })/*单据号：{0}的销售订单表体行的结算财务组织不一致，不能生成采购订单。*/);
          errMsg.append("\n");
          break;
        }
      }
      voSet.add(vo);
    }
    if (errMsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(errMsg.toString());
    }
    return voSet.toArray(new SaleOrderVO[voSet.size()]);
  }

  /**
   * 过滤出不是审核状态的发货单和已经协同生成采购订单的销售订单
   * 
   * @param vo
   * @return
   */
  private boolean filterOrderVO(SaleOrderVO vo, StringBuffer errMsg) {
    Integer state = vo.getParentVO().getFstatusflag();
    String code = vo.getParentVO().getVbillcode();
    if (BillStatus.AUDIT.equalsValue(state)) {
      UFBoolean flag = vo.getParentVO().getBcooptopoflag();
      if ((null == flag) || !flag.booleanValue()) {
        return true;
      }
      errMsg.append(NCLangRes.getInstance().getStrByID("4006011_0",
          "04006011-0241", null, new String[] {
            code
          })/*单据号：{0}的销售订单已经协同生成采购订单，不能生成采购订单。*/);
      errMsg.append("\n");
      return false;
    }

    errMsg.append(NCLangRes.getInstance().getStrByID("4006011_0",
        "04006011-0242", null, new String[] {
          code
        })/*单据号：{0}的销售订单没有审核，不能生成采购订单。*/);
    errMsg.append("\n");
    return false;
  }

  private void pushTO21(SaleOrderVO[] vos, ActionEvent Action) throws Exception {
    if(vos==null || vos.length==0){
      return ;
    }
    ICoopOrderPushSaveFor30 service =
        NCLocator.getInstance().lookup(ICoopOrderPushSaveFor30.class);
    Map<String, Object> result  = new HashMap<String, Object>();
    boolean isContinue = true;
    boolean status = true;
    while (isContinue) {
      try {
        service.coopOrderPushSaveFor30(vos, result);
        isContinue = false;
      }
      catch (BusinessException e) {
        Throwable ex = ExceptionUtils.unmarsh(e);
        if (ex instanceof IResumeException) {
          if (!ResumeExceptionUIProcessUtils.isResume((IResumeException) e, result)) {
            isContinue = false;
            status=false;
          }
        }
        else {
          ExceptionUtils.wrappException(e);
        }
      }
    }
    if(status){
    this.getreFreshAction().doAction(Action);
    ShowStatusBarMsgUtil.showStatusBarMsg(
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
            "04006011-0014")/*@res "操作成功"*/, this.getModel().getContext());
    }
  }
}
