package nc.ui.so.m32.billui.action.link;

import java.awt.event.ActionEvent;

import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.entry.ProfitVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.util.SaleInvoiceVOUtil;

import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m32.billui.view.SaleInvoiceEditor;
import nc.ui.so.pub.dlg.ProfitDlg;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;

/**
 * 销售发票联查——毛利预估
 * 
 * @since 6.1
 * @version 2013-03-18 11:15:14
 * @author yixl
 */
public class EstimateProfitAction extends NCAction {

  private static final long serialVersionUID = -7060279075801042976L;

  private SaleInvoiceEditor editor;

  private BillManageModel model;

  /**
   * 毛利预估构造子
   */
  public EstimateProfitAction() {
    super();
    this.initializeAction();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {

    ProfitVO[] viewvos = null;
    SaleInvoiceVOUtil voutil = new SaleInvoiceVOUtil();
    // 编辑和新增状态前台计算毛利
    if (this.model.getUiState() == UIState.EDIT
        || this.model.getUiState() == UIState.ADD) {
      SaleInvoiceVO[] vos = new SaleInvoiceVO[] {
        (SaleInvoiceVO) this.editor.getValue()
      };
      viewvos = voutil.changeToProfitVO(vos);
    }
    else {
      // 列表状态和非编辑态后台计算毛利
      Object[] objs = this.model.getSelectedOperaDatas();
      int intCount = objs.length;
      String[] hids = new String[intCount];
      for (int i = 0; i < intCount; i++) {
        SaleInvoiceVO vo = (SaleInvoiceVO) objs[i];
        hids[i] = vo.getPrimaryKey();
      }
      viewvos = voutil.changeToProfitVO(hids);
    }

    ProfitDlg profitDlg =
        new ProfitDlg(this.getEditor().getBillCardPanel(), viewvos, true);
    profitDlg.showModal();
  }

  /**
   * 获得发票的billForm
   * 
   * @return SaleInvoiceEditor
   */
  public SaleInvoiceEditor getEditor() {
    return this.editor;
  }

  /**
   * 获得发票的model
   * 
   * @return BillManageModel
   */
  public BillManageModel getModel() {
    return this.model;
  }

  /**
   * 设置发票的billForm
   * 
   * @param editor
   */
  public void setEditor(SaleInvoiceEditor editor) {
    this.editor = editor;
  }

  /**
   * 设置发票的model
   * 
   * @param model
   */
  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  private void initializeAction() {
    SCMActionInitializer.initializeAction(this,
        SCMActionCode.SCM_CROSSPROFITRPT);
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
