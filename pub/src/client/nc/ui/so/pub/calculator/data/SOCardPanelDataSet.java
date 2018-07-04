package nc.ui.so.pub.calculator.data;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.calculator.data.BillCardPanelDataSet;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.so.m30.entity.SaleOrderHVO;

public class SOCardPanelDataSet extends BillCardPanelDataSet {

  public SOCardPanelDataSet(
      BillCardPanel cardPanel, int row, IRelationForItems item) {
    super(cardPanel, row, item);
  }

  /**
   * 集团从表头获取
   */
  /*
  public String getPk_group() {
   String value = null;
   BillItem groupitem = getBillCardPanel().getHeadItem(
           SaleInvoiceHVO.PK_GROUP);
   if (null != groupitem) {
       Object objorgcurency = groupitem.getValueObject();
       value = ValueUtils.getInstance().getString(objorgcurency);
   }
   return value;
  }
  */

  /**
   * 折本汇率从表头获取
   */
  /*
  public UFDouble getNexchangerate() {
   Object obj = getBillCardPanel().getHeadItem(
           SaleInvoiceHVO.NEXCHANGERATE).getValueObject();
   UFDouble value = ValueUtils.getInstance().getUFDouble(obj);
   return value;
  }
  */

  /**
   * 全局折本汇率从表头获取
   */
  /*
  public UFDouble getNglobalexchgrate() {
   Object obj = getBillCardPanel().getHeadItem(
           SaleInvoiceHVO.NGLOBALEXCHGRATE).getValueObject();
   UFDouble value = ValueUtils.getInstance().getUFDouble(obj);
   return value;
  }
  */

  /**
   * 全局折本汇率从表头获取
   */
  /*
  public UFDouble getNgroupexchgrate() {
    Object obj = getBillCardPanel().getHeadItem(
             SaleInvoiceHVO.NGROUPEXCHGRATE).getValueObject();
    UFDouble value = ValueUtils.getInstance().getUFDouble(obj);
    return value;
  }
  */

  /**
   * 发票整单折扣 = 发票整单折扣*单品折扣/100
   */
  /*
  public UFDouble getNdiscountrate() {
   UFDouble disrate = super.getNdiscountrate();

   UFDouble itemdisrate = ValueUtils.getInstance().getUFDouble(
           getAttributeValue(SaleInvoiceBVO.NITEMDISCOUNTRATE));
   itemdisrate = itemdisrate == null ? UFDouble.ZERO_DBL : itemdisrate;
   disrate = disrate.multiply(itemdisrate).div(new UFDouble(100));
   return disrate;
  }

  */

  /**
   * 由于发票不允许整单折扣、单品折扣,故不用设置整单折扣值
   */
  /*
  public void setNdiscountrate(UFDouble value) {
  }

  */

  @Override
  public String getCorigcurrencyid() {
    String value = null;
    BillItem orgcurencyitem =
        this.getBillCardPanel().getHeadItem(SaleOrderHVO.CORIGCURRENCYID);
    if (null != orgcurencyitem) {
      value = (String) orgcurencyitem.getValueObject();
    }
    return value;
  }

  /**
   * 发票本位币存储在表头，从表头获得
   */
  /*
  public String getCcurrencyid() {

   String value = null;
   BillItem orgcurencyitem = getBillCardPanel().getHeadItem(
           SaleInvoiceHVO.CCURRENCYID);
   if (null != orgcurencyitem) {
       Object objorgcurency = orgcurencyitem.getValueObject();
       value = ValueUtils.getInstance().getString(objorgcurency);
   }
   return value;
  }

  */

  /**
   * 发票报价币种等于原币币种
   */
  /*
  public String getCqtcurrencyid() {
   return this.getCorigcurrencyid();
  }
  
  */

  /**
   * 不仅要判断是否在表头还要判断是否在表体
   */
  /*
  public boolean hasItemKey(String key) {
   if(getAtHeadKey().contains(key)){
       BillItem headItem = getBillCardPanel().getHeadItem(key);
       return headItem != null;
   }
   return super.hasItemKey(key);
  }
  
  */

  /**
   * 
   * 方法功能描述：初始化在表头的字段。
   * <p>
   * <b>参数说明</b>
   * 
   * @return
   *         <p>
   * @author 冯加滨
   * @time 2010-5-5 下午02:17:37
   */
  /*
  public HashSet<String> getAtHeadKey(){
   if(null == hsAtHeadKey){
       hsAtHeadKey = new HashSet<String>();
       for(String key:strAtHeadKey){
           hsAtHeadKey.add(key);
       }
   }
   return hsAtHeadKey;
  }
  
  */

  /**
   * 在模板表头的字段
   */
  /*
  private static final String[] strAtHeadKey = new String[]{
  SaleInvoiceHVO.NEXCHANGERATE,SaleInvoiceHVO.NGROUPEXCHGRATE,
  SaleInvoiceHVO.NGLOBALEXCHGRATE
  };

  private HashSet<String> hsAtHeadKey = null;*/

}
