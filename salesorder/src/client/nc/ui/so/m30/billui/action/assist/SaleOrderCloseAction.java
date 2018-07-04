package nc.ui.so.m30.billui.action.assist;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30.self.ISaleOrderMaintain;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.pub.task.IMultiReturnObjProcessor;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.ui.pubapp.pub.task.MultiBillTaskRunner;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.components.progress.ProgressActionInterface;
import nc.ui.uif2.components.progress.TPAProgressUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 订单关闭按钮
 * 
 * @since 6.0
 * @version 2011-1-26 下午06:15:09
 * @author 刘志伟
 */
public class SaleOrderCloseAction extends NCAction implements
    IMultiReturnObjProcessor, ProgressActionInterface {

  private static final long serialVersionUID = 8017985392156036350L;

  private SaleOrderBillForm editor;

  private BillManageModel model;

  private TPAProgressUtil tpaProgressUtil;

  private MultiBillTaskRunner<SaleOrderVO> multiBillTaskRunner;

  private ISingleBillService<SaleOrderVO> singleBillService;

  public SaleOrderCloseAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_BILLCLOSE);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    String sReason =
        (String) MessageDialog
            .showInputDlg(this.getModel().getContext().getEntranceUI(),
                NCLangRes.getInstance()
                    .getStrByID("4006011_0", "04006011-0233")/*关闭/打开原因*/,
                NCLangRes.getInstance()
                    .getStrByID("4006011_0", "04006011-0234")/*请输入关闭/打开原因*/,
                null, 120);
    // 取消或关闭原因框返回的都为null,不做关闭/打开处理
    if (sReason == null) {
      return;
    }
    Object[] objs = this.model.getSelectedOperaDatas();
    SaleOrderVO[] bills = new SaleOrderVO[objs.length];

    Set<String> ids = new HashSet<String>();
    for (int i = 0; i < objs.length; i++) {
      bills[i] = (SaleOrderVO) objs[i];
      if (null == bills[i].getChildrenVO()) {
        ids.add(bills[i].getPrimaryKey());
      }
    }
    if (ids.size() > 0) {
      // 此处可能有流量问题、如果有的话 可以选择单独出接口 只查询部分字段
      ISaleOrderMaintain srv =
          NCLocator.getInstance().lookup(ISaleOrderMaintain.class);
      SaleOrderVO[] vos =
          srv.querySaleorder(ids.toArray(new String[ids.size()]));
      Map<String, SaleOrderVO> vomap = new HashMap<String, SaleOrderVO>();
      for (SaleOrderVO vo : vos) {
        vomap.put(vo.getPrimaryKey(), vo);
      }
      for (int i = 0; i < bills.length; i++) {
        if (null == bills[i].getChildrenVO()) {
          bills[i].setChildrenVO(vomap.get(bills[i].getPrimaryKey())
              .getChildrenVO());
        }
      }
    }
    this.setCloseOpenReason(bills, sReason);
    if (this.getSingleBillService() != null) {
      if (bills.length > 1) {
        this.getMultiBillTaskRunner().setOperateObjs(bills);
        this.getMultiBillTaskRunner()
            .setTitle(
                NCLangRes.getInstance()
                    .getStrByID("4006011_0", "04006011-0235")/*关闭*/);
        this.getMultiBillTaskRunner().setMultiReturnObjProcessor(this);
        this.getMultiBillTaskRunner().runTask();
      }
      else if (bills.length == 1) {
        SaleOrderVO[] ret = new SaleOrderVO[] {
          this.getSingleBillService().operateBill(bills[0])
        };
        this.processReturnObjs(ret);
      }
    }
  }

  private void setCloseOpenReason(SaleOrderVO[] bills, String sReason) {
    for (SaleOrderVO bill : bills) {
      SaleOrderBVO[] bodys = bill.getChildrenVO();
      for (SaleOrderBVO body : bodys) {
        body.setVclosereason(sReason);
      }
    }
  }

  public SaleOrderBillForm getEditor() {
    return this.editor;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  /**
   * 获得多任务运行器
   * 
   * @return 多任务运行器
   */
  public MultiBillTaskRunner<SaleOrderVO> getMultiBillTaskRunner() {
    if (this.multiBillTaskRunner == null) {
      this.multiBillTaskRunner =
          new MultiBillTaskRunner<SaleOrderVO>(this.singleBillService);
    }
    this.multiBillTaskRunner.setTpaProgressUtil(this.getTpaProgressUtil());
    return this.multiBillTaskRunner;
  }

  @Override
  public TPAProgressUtil getTpaProgressUtil() {
    if (this.tpaProgressUtil == null) {
      this.tpaProgressUtil = new TPAProgressUtil();
      this.tpaProgressUtil.setContext(this.getModel().getContext());
    }
    return this.tpaProgressUtil;
  }

  @Override
  public void setTpaProgressUtil(TPAProgressUtil tpaProgressUtil) {
    if (this.multiBillTaskRunner != null) {
      this.multiBillTaskRunner.setTpaProgressUtil(tpaProgressUtil);
    }
    this.multiBillTaskRunner.setTpaProgressUtil(this.getTpaProgressUtil());
    this.tpaProgressUtil = tpaProgressUtil;
  }

  public ISingleBillService<SaleOrderVO> getSingleBillService() {
    return this.singleBillService;
  }

  @Override
  public void processReturnObjs(Object[] returnObj) {
    try {
      this.model.directlyUpdate(returnObj);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  public void setEditor(SaleOrderBillForm editor) {
    this.editor = editor;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  public void setSingleBillService(
      ISingleBillService<SaleOrderVO> singleBillService) {
    this.singleBillService = singleBillService;
  }

  @Override
  protected boolean isActionEnable() {
    if (this.model.getSelectedOperaDatas() != null
        && this.model.getSelectedOperaDatas().length > 1) {
      return true;
    }
    if (this.model.getSelectedData() != null) {
      SaleOrderVO vo = (SaleOrderVO) this.model.getSelectedData();
      // 审批态可以关闭
      Integer fstatusflag = vo.getParentVO().getFstatusflag();
      if (BillStatus.AUDIT.equalsValue(fstatusflag)) {
        return true;
      }
    }
    return false;
  }

}
