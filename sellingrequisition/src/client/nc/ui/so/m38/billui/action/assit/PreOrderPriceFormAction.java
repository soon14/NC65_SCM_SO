package nc.ui.so.m38.billui.action.assit;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.price.priceform.IPriceFormDialog;
import nc.itf.scmpub.reference.uap.para.SysParaInitQuery;
import nc.itf.so.m38trantype.IM38TranTypeService;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.pub.locator.NCUILocator;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m38.billui.pub.PreOrderFindPriceConfig;
import nc.ui.so.m38.billui.view.PreOrderEditor;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.price.pub.entity.FindPriceResultVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38trantype.entity.M38TranTypeVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.res.ParameterList;

public class PreOrderPriceFormAction extends NCAction {

  /**
   *
   */
  private static final long serialVersionUID = 2164769029503027127L;

  private PreOrderEditor editor;

  private AbstractAppModel model;

  public PreOrderPriceFormAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_PRICEFORM);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    BillCardPanel cardPanel = this.editor.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    boolean bSO22 =
        SysParaInitQuery.getParaBoolean(
            keyValue.getHeadStringValue(PreOrderHVO.PK_ORG),
            ParameterList.SO22.getCode()).booleanValue();
    int[] rows =
        this.editor.getBillCardPanel().getBillTable().getSelectedRows();
    // 没有选择行，选择多行只按第一行处理
    if (rows.length == 0) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006012_0", "04006012-0005")/*@res "必须选中表体行"*/);
    }

    String priceFormID =
        keyValue.getBodyStringValue(rows[0], PreOrderBVO.CPRICEFORMID);
    if (PubAppTool.isNull(priceFormID)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006012_0", "04006012-0006")/*@res "当前行没有价格组成信息"*/);
    }
    IPriceFormDialog dialog =
        NCUILocator.getInstance()
            .lookup(IPriceFormDialog.class, NCModule.PRICE);
    boolean bEditFlag = false;
    M38TranTypeVO trantypevo = this.getTranTypeVO(keyValue);
    if ((this.getModel().getUiState() == AppUiState.ADD.getUiState() || this
        .getModel().getUiState() == AppUiState.EDIT.getUiState())
        && null != trantypevo) {
      UFBoolean bmodigy = trantypevo.getBmodifyaskedqt();
      if (null != bmodigy) {
        bEditFlag = bmodigy.booleanValue();
      }
    }
    FindPriceResultVO[] resultVOs = new FindPriceResultVO[1];
    String pk_currtype = keyValue.getHeadStringValue(PreOrderHVO.CORIGCURRENCYID);
    if (dialog.showModal(cardPanel, bSO22, bEditFlag, priceFormID, pk_currtype) == UIDialog.ID_OK) {
      resultVOs[0] = dialog.getFindPriceResultVO();
      // 卡片上设值并计算
      if (null != resultVOs[0]) {
        PreOrderFindPriceConfig config = new PreOrderFindPriceConfig(cardPanel);
        FindSalePrice findPrice = new FindSalePrice(cardPanel, config);
        findPrice.setResultAfterPriceFormEdit(rows[0], resultVOs[0]);
      }
    }
  }

  private M38TranTypeVO getTranTypeVO(IKeyValue keyValue) {

    String trantypeid = keyValue.getHeadStringValue(PreOrderHVO.CTRANTYPEID);
    if (PubAppTool.isNull(trantypeid)) {
      return null;
    }
    M38TranTypeVO m38trantypevo = null;
    IM38TranTypeService srv =
        NCLocator.getInstance().lookup(IM38TranTypeService.class);
    try {
      m38trantypevo = srv.queryTranTypeVO(trantypeid);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return m38trantypevo;
  }

  public PreOrderEditor getEditor() {
    return this.editor;
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setEditor(PreOrderEditor editor) {
    this.editor = editor;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    // 没有选中单据，置灰
    int[] rows =
        this.editor.getBillCardPanel().getBillTable().getSelectedRows();
    // 如果是多选，则按钮不可用
    if (null == rows || rows.length == 0) {
      return false;
    }
    return true;
  }
}
