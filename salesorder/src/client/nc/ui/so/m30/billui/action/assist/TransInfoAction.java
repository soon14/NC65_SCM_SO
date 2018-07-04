package nc.ui.so.m30.billui.action.assist;

import java.awt.event.ActionEvent;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.to.businessinfo.to.m30.IBizinfoUIServiceForM30;
import nc.ui.pubapp.pub.locator.NCUILocator;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 内部交易信息
 * 
 * @since 6.0
 * @version 2011-11-23 下午02:15:50
 * @author 么贵敬
 */
public class TransInfoAction extends NCAction {

  private static final long serialVersionUID = 1053656076089099491L;

  protected AbstractAppModel model;

  private SaleOrderBillForm editor;

  public TransInfoAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_TRANSINFO);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    SaleOrderVO value = (SaleOrderVO) this.model.getSelectedData();
    if(SysInitGroupQuery.isTOEnabled()){
    IBizinfoUIServiceForM30 adapter =
        NCUILocator.getInstance().lookup(IBizinfoUIServiceForM30.class,
            NCModule.TO);
    adapter.setBill(value);
    adapter.setParent(this.editor);
    adapter.showModal();
    }
    else{
    	ExceptionUtils
        .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0474")/* @res
                                                      * "内部交易模块未启用，无法查看内部交易！" */);
    	
    }
  }

  public SaleOrderBillForm getEditor() {
    return this.editor;
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setEditor(SaleOrderBillForm editor) {
    this.editor = editor;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    boolean isEnable = false;
    if (null != this.model.getSelectedData()) {
      isEnable = true;
    }
    return isEnable;
  }
}
