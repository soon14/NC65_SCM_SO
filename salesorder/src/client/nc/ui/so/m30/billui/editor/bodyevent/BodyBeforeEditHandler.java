package nc.ui.so.m30.billui.editor.bodyevent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.CardEditSetter;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 销售订单表体编辑前事件派发类
 * 
 * @since 6.0
 * @version 2011-6-9 上午09:54:19
 * @author fengjb
 */
public class BodyBeforeEditHandler implements
IAppEventHandler<CardBodyBeforeEditEvent> {
  
 private final  static Set<String>  headfiedl=new HashSet<String>();
 
 private SaleOrderBillForm billform;
 
  static{
    for (String key : CardEditSetter.BODYNOTEDITFIELDS) {
      headfiedl.add(key);
    }
  }

 

 
  @Override
  public void handleAppEvent(CardBodyBeforeEditEvent e) {
    String editKey = e.getKey();
    // 检查交易类型是否为空
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    String trantypeid = keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);
    if (PubAppTool.isNull(trantypeid)) {
      ExceptionUtils
      .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0029")/*@res "请先录入交易类型"*/);
    }
    
    //收款后不能编辑的字段
    receMnyNotEdit(e);

    // 价格项
    if (SaleOrderBVO.CPRICEITEMID.equals(editKey)) {
      PriceItemEditHandler handler = new PriceItemEditHandler();
      handler.beforeEdit(e);
    }
    // 物料
    if (SaleOrderBVO.CMATERIALVID.equals(editKey)) {
      MaterialEditHandler handler = new MaterialEditHandler();
      handler.beforeEdit(e, this.billform);
    } // 客户物料码(V63新加)
    else if (SaleOrderBVO.CCUSTMATERIALID.equals(editKey)) {
      CustMaterialEditHandler handler = new CustMaterialEditHandler();
      handler.beforeEdit(e);
    }
    // 税码
    else if (SaleOrderBVO.CTAXCODEID.equals(editKey)) {
      TaxCodeEditHandler handler = new TaxCodeEditHandler();
      handler.beforeEdit(e);
    }
    // 业务单位
    else if (SaleOrderBVO.CASTUNITID.equals(editKey)) {
      AstUnitEditHandler handler = new AstUnitEditHandler();
      handler.beforeEdit(e);
    }
    // 业务单位换算率
    else if (SaleOrderBVO.VCHANGERATE.equals(editKey)) {
      ChangeRateEditHandler handler = new ChangeRateEditHandler();
      handler.beforeEdit(e);
    }
    // 报价单位
    else if (SaleOrderBVO.CQTUNITID.equals(editKey)) {
      QtUnitEditHandler handler = new QtUnitEditHandler();
      handler.beforeEdit(e);
    }
    // 报价单位换算率
    else if (SaleOrderBVO.VQTUNITRATE.equals(editKey)) {
      QtUnitRateEditHandler handler = new QtUnitRateEditHandler();
      handler.beforeEdit(e);
    }
    // 赠品标志
    else if (SaleOrderBVO.BLARGESSFLAG.equals(editKey)) {
      LargessFlagEditHandler handler = new LargessFlagEditHandler();
      handler.beforeEdit(e, this.billform);
    }
    // 单品折扣
    else if (SaleOrderBVO.NITEMDISCOUNTRATE.equals(editKey)) {
      ItemDisRateEditHandler handler = new ItemDisRateEditHandler();
      handler.beforeEdit(e);
    }
    // 批次号
    else if (SaleOrderBVO.VBATCHCODE.equals(editKey)) {
      BatchCodeEditHandler handler = new BatchCodeEditHandler();
      handler.beforeEdit(e, this.billform);
    }
    // 发货库存组织
    else if (SaleOrderBVO.CSENDSTOCKORGVID.equals(editKey)) {
      SendStockOrgEditHandler handler = new SendStockOrgEditHandler();
      handler.beforeEdit(e);
    }
    // 发货仓库
    else if (SaleOrderBVO.CSENDSTORDOCID.equals(editKey)) {
      SendStordocEditHandler handler = new SendStordocEditHandler();
      handler.beforeEdit(e);
    }
    // 结算财务组织
    else if (SaleOrderBVO.CSETTLEORGVID.equals(editKey)) {
      SettleOrgEditHandler handler = new SettleOrgEditHandler();
      handler.beforeEdit(e);
    }
    // 应收组织
    else if (SaleOrderBVO.CARORGVID.equals(editKey)) {
      ArOrgEditHandler handler = new ArOrgEditHandler();
      handler.beforeEdit(e);
    }
    // 利润中心
    else if (SaleOrderBVO.CPROFITCENTERVID.equals(editKey)) {
      ProfitCenterEditHandler handler = new ProfitCenterEditHandler();
      handler.beforeEdit(e);
    }
    // 物流组织
    else if (SaleOrderBVO.CTRAFFICORGVID.equals(editKey)) {
      TrafficOrgEditHandler handler = new TrafficOrgEditHandler();
      handler.beforeEdit(e);
    }
    // 折本汇率
    else if (SaleOrderBVO.NEXCHANGERATE.equals(editKey)) {
      ExchangeRateEditHandler handler = new ExchangeRateEditHandler();
      handler.beforeEdit(e);
    }
    // 全局折本汇率
    else if (SaleOrderBVO.NGLOBALEXCHGRATE.equals(editKey)) {
      GlobalExchgRateEditHandler handler = new GlobalExchgRateEditHandler();
      handler.beforeEdit(e);
    }
    // 集团折本汇率
    else if (SaleOrderBVO.NGROUPEXCHGRATE.equals(editKey)) {
      GroupExchgRateEditHandler handler = new GroupExchgRateEditHandler();
      handler.beforeEdit(e);
    }
    // 计税金额
    else if (SaleOrderBVO.NCALTAXMNY.equals(editKey)) {
      CaltaxmnyEditHandler handler = new CaltaxmnyEditHandler();
      handler.beforeEdit(e);
    }
    // 原产地区
    else if (SaleOrderBVO.CORIGAREAID.equals(editKey)) {
      OrigAreaEditHandler handler = new OrigAreaEditHandler();
      handler.beforeEdit(e);
    }
    // 收货地址
    else if (SaleOrderBVO.CRECEIVEADDRID.equals(editKey)) {
      ReceiveaddrEditHandler handler = new ReceiveaddrEditHandler();
      handler.beforeEdit(e);
    }
    // 询价相关字段编辑
    else if (this.getPirceMnyList().contains(editKey)) {
      AllPricesEditHandle handler = new AllPricesEditHandle();
      handler.beforeEdit(e, this.billform);
    }
    // 发货利润中心字段编辑
    else if (SaleOrderBVO.CSPROFITCENTERVID.equals(editKey)) {
      SProfitCenterEditHandler handler = new SProfitCenterEditHandler();
      handler.beforeEdit(e);
    }
    //特征码
    else if(SaleOrderBVO.CMFFILEID.equals(editKey)){
    	CmffileidEditHandle handler = new CmffileidEditHandle();
    	handler.beforeEdit(e);
    }
    
  }
  
  private  void  receMnyNotEdit(CardBodyBeforeEditEvent e){
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    UFDouble nreceivedmny = keyValue.getHeadUFDoubleValue(SaleOrderHVO.NRECEIVEDMNY);
    if(!MathTool.isZero(nreceivedmny)&& headfiedl.contains(e.getKey())){
      e.setReturnValue(Boolean.FALSE);
    }
  }
  

  /**
   * 取单价金额字段List（给询价后字段编辑性）
   * 
   * @return priceAndMny
   */
  private List<String> getPirceMnyList() {
    // 询价相关单价金额
    String[] priceAndMnys =
        new String[] {
        SaleOrderBVO.NORIGTAXPRICE, SaleOrderBVO.NORIGPRICE,
        SaleOrderBVO.NORIGTAXNETPRICE, SaleOrderBVO.NORIGNETPRICE,
        SaleOrderBVO.NQTORIGTAXPRICE, SaleOrderBVO.NQTORIGPRICE,
        SaleOrderBVO.NQTORIGTAXNETPRC, SaleOrderBVO.NQTORIGNETPRICE,
        SaleOrderBVO.NTAXPRICE, SaleOrderBVO.NPRICE,
        SaleOrderBVO.NTAXNETPRICE, SaleOrderBVO.NNETPRICE,
        SaleOrderBVO.NQTTAXPRICE, SaleOrderBVO.NQTPRICE,
        SaleOrderBVO.NQTTAXNETPRICE, SaleOrderBVO.NQTNETPRICE,
        SaleOrderBVO.NORIGTAXMNY, SaleOrderBVO.NORIGMNY,
        SaleOrderBVO.NORIGDISCOUNT, SaleOrderBVO.NTAXMNY, SaleOrderBVO.NMNY,
        SaleOrderBVO.NTAX, SaleOrderBVO.NDISCOUNT
    };
    List<String> priceAndMny = Arrays.asList(priceAndMnys);
    return priceAndMny;
  }

  /**
   * 
   * 
   * @param billform
   */
  public void setBillform(SaleOrderBillForm billform) {
    this.billform = billform;
  }

  /**
   * 
   * 
   * @return hh
   */
  public SaleOrderBillForm getBillform() {
    return this.billform;
  }

}
