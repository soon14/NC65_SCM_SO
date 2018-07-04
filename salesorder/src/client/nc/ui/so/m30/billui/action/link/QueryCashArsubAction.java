package nc.ui.so.m30.billui.action.link;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.pub.dlg.CashArsubDetailDlg;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 费用兑付明细查询
 * 
 * @since 6.35
 * @version 2013-12-11 10:43:02
 * @author 宫体雷
 */
public class QueryCashArsubAction extends NCAction {

  private static final long serialVersionUID = 2110541669625985035L;

  /**
   * 销售订单卡片控制
   */
  private SaleOrderBillForm editor;

  /**
   * 模型管理类
   */
  private AbstractAppModel model;

  /**
   * 构造函数
   */
  public QueryCashArsubAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_CASHARSUBDETAIL);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    SaleOrderVO vo = (SaleOrderVO) this.getModel().getSelectedData();
    if (null == vo) {
      return;
    }
    SaleOrderHVO hVO = vo.getParentVO();
    if (hVO != null) {
      String[] saleBillIDs = new String[] {
          hVO.getCsaleorderid()
      };
      CashArsubDetailDlg dlg =
          new CashArsubDetailDlg(this.getEditor().getBillCardPanel(),
              saleBillIDs, true);
      dlg.showModal();
    }
  }

  @Override
  protected boolean isActionEnable() {
    SaleOrderVO vo = (SaleOrderVO) this.getModel().getSelectedData();
    if (vo != null && vo.getParentVO() != null
        &&!PubAppTool.isNull(vo.getParentVO().getVbillcode())) {
      return true;
    }
    return false;
  }

  /**
   * 获取销售订单卡片
   * 
   * @return SaleOrderBillForm
   */
  public SaleOrderBillForm getEditor() {
    return this.editor;
  }

  /**
   * 设置销售订单卡片
   * 
   * @param editor
   */
  public void setEditor(SaleOrderBillForm editor) {
    this.editor = editor;
  }

  /**
   * 获取模型
   * 
   * @return model
   */
  public AbstractAppModel getModel() {
    return this.model;
  }

  /**
   * 设置模型
   * 
   * @param model
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

}
