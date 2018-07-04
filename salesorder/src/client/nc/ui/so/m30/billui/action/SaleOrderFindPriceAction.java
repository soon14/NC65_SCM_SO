package nc.ui.so.m30.billui.action;

import java.awt.event.ActionEvent;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderFindPriceConfig;
import nc.ui.so.pub.findprice.FindSalePrice;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.enumeration.Largesstype;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 询价 按钮
 * 
 * @version 6.0
 * @author 刘志伟
 * @time 2010-9-2 下午08:00:29
 */
public class SaleOrderFindPriceAction extends NCAction {
  /**
   * 
   */
  private static final long serialVersionUID = -9217927220346916128L;

  private SaleOrderBillForm editor;

  private AbstractAppModel model;

  public SaleOrderFindPriceAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_ASKPRICE);
  }

  @Override
  public void doAction(ActionEvent e) {
    BillCardPanel cardPanel = this.editor.getBillCardPanel();
    int[] rows = cardPanel.getBillTable().getSelectedRows();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    this.checkCanFindPrice(keyValue);
    String trantypecode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    String pk_group = AppContext.getInstance().getPkGroup();
    M30TranTypeVO m30trantypevo =
        this.editor.getM30ClientContext().getTransType(trantypecode, pk_group);
    SaleOrderFindPriceConfig config =
        new SaleOrderFindPriceConfig(cardPanel, m30trantypevo);
    FindSalePrice findPrice = new FindSalePrice(cardPanel, config);
    findPrice.forceFindPrice(rows);
    
    
    cardPanel.getBillModel().loadLoadRelationItemValue();
    cardPanel.getBillModel().execLoadFormulaByKey("priceformstr");
    
    for(int row : rows){
    	// 计算冲抵前金额
        UFDouble norigtaxmny =
            keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NORIGTAXMNY);
        UFDouble norigsubmny =
            keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NORIGSUBMNY);
        keyValue.setBodyValue(row, SaleOrderBVO.NBFORIGSUBMNY,
            MathTool.add(norigtaxmny, norigsubmny));
    }
    
    // 计算表体合计信息
    HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
    totalrule.calculateHeadTotal();
  }

  private void checkCanFindPrice(IKeyValue keyValue) {
    if(isSaleOrderOffset(keyValue) || isSaleOrderApportion(keyValue)){
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006011_0", "04006011-0538")/*费用冲抵或赠品价格分摊后不允许询价！*/);
    }
  }
  
  /**
   * 根据表体行判断订单是否进行了赠品价格分摊
   * 
   * @param saleordervo
   * @return
   */
  private boolean isSaleOrderApportion(IKeyValue keyValue) {
    int bodycount = keyValue.getBodyCount();
    for (int i = 0; i < bodycount; i++) {
      Integer largesstypeflag =
          ValueUtils.getInteger(keyValue.getBodyValue(i,
              SaleOrderBVO.FLARGESSTYPEFLAG));
      if (Largesstype.APPORTIONMATERIAL.equalsValue(largesstypeflag)
          || Largesstype.APPORTIONLARGESS.equalsValue(largesstypeflag)) {
        return true;
      }
    }
    return false;
  }
  
  private boolean isSaleOrderOffset(IKeyValue keyValue) {
    UFBoolean offsetfalg =
        keyValue.getHeadUFBooleanValue(SaleOrderHVO.BOFFSETFLAG);
    return offsetfalg == null ? false : offsetfalg.booleanValue();
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
    int[] rows =
        this.editor.getBillCardPanel().getBillTable().getSelectedRows();
    if (rows.length > 1) {
      return true;
    }
    if (this.editor.getBillCardPanel().getBillTable().getSelectedRow() == -1) {
      return false;
    }

    return true;
  }
}
