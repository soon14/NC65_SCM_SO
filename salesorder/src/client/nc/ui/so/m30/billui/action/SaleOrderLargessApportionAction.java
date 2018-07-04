package nc.ui.so.m30.billui.action;

import java.awt.event.ActionEvent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m30.billui.rule.LargessPropertyRule;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.CardEditSetter;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.NCAction;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;

/**
 * 销售订单赠品价格分摊操作
 * 
 * @since 6.0
 * @version 2011-7-12 下午04:39:02
 * @author fengjb
 */
public class SaleOrderLargessApportionAction extends NCAction {

  private static final long serialVersionUID = 8794889824098926577L;

  private BillForm editor;

  private BillManageModel model;

  public SaleOrderLargessApportionAction() {
    super();
    SCMActionInitializer.initializeAction(this,
        SCMActionCode.SO_LARGESSAPPORTION);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    BillCardPanel cardPanel = this.getCardPanel();

    LargessPropertyRule larprorule = new LargessPropertyRule(cardPanel);
    larprorule.processLargessApportion();

    this.doafter();
  }

  public BillForm getEditor() {
    return this.editor;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public void setEditor(BillForm editor) {
    this.editor = editor;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    AppUiState uistate = this.getModel().getAppUiState();

    if (AppUiState.EDIT == uistate || AppUiState.ADD == uistate
        || AppUiState.COPY_ADD == uistate
        || AppUiState.TRANSFERBILL_ADD == uistate) {
      BillCardPanel cardPanel = this.getCardPanel();
      Object offsetfalg =
          cardPanel.getHeadItem(SaleOrderHVO.BOFFSETFLAG).getValueObject();
      boolean boffsetfalg = ValueUtils.getBoolean(offsetfalg);
      // 2013.11.25 交易类型为赠品兑付，不能赠品价格分摊
      CardKeyValue keyValue = new CardKeyValue(this.editor.getBillCardPanel());
      String tranTypeCode =
          keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);

      String pk_group = AppContext.getInstance().getPkGroup();
      SaleOrderClientContext cache =
          ((SaleOrderBillForm) this.editor).getM30ClientContext();
      M30TranTypeVO m30transvo = cache.getTransType(tranTypeCode, pk_group);
      if (m30transvo != null) {
        boolean blrgcashflag = m30transvo.getBlrgcashflag().booleanValue();

        if (boffsetfalg || blrgcashflag) {
          return false;
        }
        else {
          return true;
        }
      }
      else {
        if (boffsetfalg) {
          return false;
        }
        else {
          return true;
        }
      }
    }
    else {
      return false;
    }
  }

  private BillCardPanel getCardPanel() {
    return this.getEditor().getBillCardPanel();
  }

  private void doafter() {
    CardEditSetter editset =
        new CardEditSetter((SaleOrderBillForm) this.editor);
    editset.setEditEnableByFlargessTypeFlag();
  }

}
