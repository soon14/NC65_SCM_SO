package nc.ui.so.mbuylargess.editor.headevent;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.editor.BillForm;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.mbuylargess.entity.BuyLargessBVO;
import nc.vo.so.mbuylargess.entity.BuyLargessHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.trade.checkrule.VOChecker;

/**
 * 
 * @since 6.3
 * @version 2012-11-13 下午03:23:39
 * @author 梁吉明
 */
@SuppressWarnings("restriction")
public class HeadAfterEditHandler implements
    IAppEventHandler<CardHeadTailAfterEditEvent> {
  // private BillForm editor = null;

  // private BillManageModel model = null;
  //
  // private LoginContext context = null;

  private BillCardPanel billCardPanel;
  
  public HeadAfterEditHandler(BillForm editor) {
    this.billCardPanel = editor.getBillCardPanel();
  }

  @Override
  public void handleAppEvent(CardHeadTailAfterEditEvent e) {
    if (e.getKey().equals(BuyLargessHVO.CBUYMARID)) {
      this.setMaterial(e);
    }
    else if (e.getKey().equals(BuyLargessHVO.PK_MARBASCLASS)) {
      this.setMaterialClass(e);
    }
    else if (e.getKey().equals(BuyLargessHVO.PK_MARSALECLASS)) {
      this.setMateSalerial();
    }
    else if (e.getKey().equals(BuyLargessHVO.PK_CUSTOMER)) {
      this.setCustomer(e);
    }
    else if (e.getKey().equals(BuyLargessHVO.PK_CUSTCLASS)) {
      this.setCustClass(e);
    }
    else if (e.getKey().equals(BuyLargessHVO.PK_CUSTSALECLASS)) {
      this.setCustSaleClass(e);
    }else if(e.getKey().equals(BuyLargessHVO.PK_CURRINFO)){
      this.setCurrinfo(e);
    }
  }

  /**
   * 币种编辑后，经刘达确认：清空表体单价金额字段 2015-7-14
   * @param e
   */
  private void setCurrinfo(CardHeadTailAfterEditEvent e) {
    IKeyValue keyValue = new CardKeyValue(this.billCardPanel);
    int rowCount = keyValue.getBodyCount();
    if(rowCount == 0){
      return;
    }
    for( int row = 0 ; row<rowCount ; row++){
      keyValue.setBodyValue(row, BuyLargessBVO.NPRICE, null);
      keyValue.setBodyValue(row, BuyLargessBVO.NMNY, null);
    }
  }
  
  /**
   * 编辑客户分类
   */
  private void setCustClass(CardHeadTailAfterEditEvent e) {
    String custclass =
        (String) this.billCardPanel.getHeadItem(e.getKey()).getValueObject();
    if ((null == custclass) || (custclass.length() == 0)) {
      this.billCardPanel.getHeadItem(BuyLargessHVO.PK_CUSTOMER)
          .setEnabled(true);
    }
    else {
      this.billCardPanel.getHeadItem(BuyLargessHVO.PK_CUSTOMER).setEnabled(
          false);
    }
  }

  /**
   * 编辑客户
   */
  private void setCustomer(CardHeadTailAfterEditEvent e) {
    String pk_customer =
        (String) this.billCardPanel.getHeadItem(e.getKey()).getValueObject();
    // 客户为空,分类字段可编辑
    if (VOChecker.isEmpty(pk_customer)) {

      this.billCardPanel.getHeadItem(BuyLargessHVO.PK_CUSTCLASS).setEnabled(
          true);
      this.billCardPanel.getHeadItem(BuyLargessHVO.PK_CUSTSALECLASS)
          .setEnabled(true);

    }
    else {

      this.billCardPanel.getHeadItem(BuyLargessHVO.PK_CUSTCLASS).setEnabled(
          false);
      this.billCardPanel.getHeadItem(BuyLargessHVO.PK_CUSTSALECLASS)
          .setEnabled(false);
    }
  }

  /** 编辑客户销售分类 */
  private void setCustSaleClass(CardHeadTailAfterEditEvent e) {
    String custsaleclass =
        (String) this.billCardPanel.getHeadItem(e.getKey()).getValueObject();
    if (null == custsaleclass) {
      this.billCardPanel.getHeadItem(BuyLargessHVO.PK_CUSTOMER)
          .setEnabled(true);
    }
    else {
      this.billCardPanel.getHeadItem(BuyLargessHVO.PK_CUSTOMER).setEnabled(
          false);
    }
  }

  /**
   * 编辑物料要处理的数据项
   */
  private void setMaterial(CardHeadTailAfterEditEvent e) {
    String pk_material =
        (String) this.billCardPanel.getHeadItem(e.getKey()).getValueObject();

    // 物料为空,分类字段可编辑
    if (VOChecker.isEmpty(pk_material)) {

      this.billCardPanel.getHeadItem(BuyLargessHVO.PK_MARBASCLASS).setEnabled(
          true);
      this.billCardPanel.getHeadItem(BuyLargessHVO.PK_MARSALECLASS).setEnabled(
          true);
    }
    else {
      //取物料对应的辅单位
      IKeyValue keyValue = new CardKeyValue(e.getBillCardPanel());
      String cbuymarid = keyValue.getHeadStringValue(BuyLargessHVO.CBUYMARID);
      String cbuyunitid = MaterialPubService.querySaleMeasdocIDByPk(cbuymarid);
      if(cbuyunitid == null){        
        //获取物料对应的主单位
        cbuyunitid = MaterialPubService.queryMaterialMeasdoc(new String[]{cbuymarid}).get(cbuymarid);
      }
      keyValue.setHeadValue(BuyLargessHVO.CBUYUNITID, cbuyunitid);
      this.billCardPanel.getBillData().loadEditHeadRelation(e.getKey());    
      this.billCardPanel.getHeadItem(BuyLargessHVO.PK_MARBASCLASS).setEnabled(
          false);
      this.billCardPanel.getHeadItem(BuyLargessHVO.PK_MARSALECLASS).setEnabled(
          false);
    }
  }

  /**
   * 编辑物料分类要处理的数据项
   */
  private void setMaterialClass(CardHeadTailAfterEditEvent e) {
    Object pk_material =
        this.billCardPanel.getHeadItem(e.getKey()).getValueObject();
    if (pk_material != null) {
      this.billCardPanel.getHeadItem(BuyLargessHVO.CBUYMARID).setEnabled(false);
    }
    else {
      this.billCardPanel.getHeadItem(BuyLargessHVO.CBUYMARID).setEnabled(true);
    }
  }

  /** 编辑物料销售分类 */
  private void setMateSalerial() {
    Object pk_marsaleclass =
        this.billCardPanel.getHeadItem(BuyLargessHVO.PK_MARSALECLASS)
            .getValueObject();
    if (pk_marsaleclass != null) {
      this.billCardPanel.getHeadItem(BuyLargessHVO.CBUYMARID).setEnabled(false);
    }
    else {
      this.billCardPanel.getHeadItem(BuyLargessHVO.CBUYMARID).setEnabled(true);
    }
  }
}
