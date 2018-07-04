package nc.ui.so.pub.precision;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.scale.BillModelScaleProcessor;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.scale.ListPaneScaleProcessor;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.pubapp.scale.TableScaleProcessor;
import nc.vo.so.entry.ProfitVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

public class ProfitPrecision {

  // 数量
  private static final String[] ASTNUMKEY = new String[] {
    ProfitVO.NASTNUM
  };

  private static ProfitPrecision instance = new ProfitPrecision();

  // 原币金额
  private static final String[] ORIGMNYKEY = new String[] {
    ProfitVO.NTOTALCOST, ProfitVO.NTOTALINCOME, ProfitVO.NTOTALPROFIT
  };

  // 单价
  private static final String[] PRICEKEY = new String[] {
    ProfitVO.NQTORIGNETPRICE
  };

  //毛利率
  private static final String[] RATEKEY = new String[]{
	  ProfitVO.NTOTALPROFITRATE
  };
  /**
   * ProfitPrecision 的构造子
   */
  private ProfitPrecision() {
    // 缺省构造方法
  }

  public static ProfitPrecision getInstance() {
    return ProfitPrecision.instance;
  }

  /**
   * 設置卡片界面精度
   * 
   * @param pk_group
   * @param cardpanel
   */
  public void setCardPrecision(String pk_group, BillCardPanel cardpanel) {
    BillScaleProcessor scaleprocess =
        new CardPaneScaleProcessor(pk_group, cardpanel);
    this.setBillPrecision(scaleprocess);
  }

  /**
   * 设置列表界面精度
   * 
   * @param pk_group
   * @param listpanel
   */
  public void setListPrecision(String pk_group, BillListPanel listpanel) {
    BillScaleProcessor scaleprocess =
        new ListPaneScaleProcessor(pk_group, listpanel);
    this.setBillPrecision(scaleprocess);
  }

  /**
   * 设置拉平界面的精度
   * 
   * @param pk_group
   * @param model
   */
  public void setModelPrecision(String pk_group, BillModel model) {
    TableScaleProcessor scaleprocess =
        new BillModelScaleProcessor(pk_group, model);
    this.setTablePrecision(scaleprocess);
  }

  /**
   * 设置单据精度
   * 
   * @param scaleprocess
   */
  private void setBillPrecision(BillScaleProcessor scaleprocess) {

    // 单价
    scaleprocess.setPriceCtlInfo(ProfitPrecision.PRICEKEY, PosEnum.body, null,ProfitVO.CORIGCURRENCYID,PosEnum.body,null);

    // 原币金额
    scaleprocess.setMnyCtlInfo(ProfitPrecision.ORIGMNYKEY, PosEnum.body, null,
        SaleInvoiceHVO.CORIGCURRENCYID, PosEnum.head, null);
    
    //毛利率
    scaleprocess.setSaleDiscountCtlInfo(ProfitPrecision.RATEKEY, PosEnum.body, null);

    scaleprocess.process();

  }

  /**
   * 设置表格精度
   * 
   * @param scaleprocess
   */
  private void setTablePrecision(TableScaleProcessor scaleprocess) {

    // 单价
    scaleprocess.setPriceCtlInfo(ProfitPrecision.PRICEKEY,ProfitVO.CORIGCURRENCYID);
    // 数量
    scaleprocess.setNumCtlInfo(ProfitPrecision.ASTNUMKEY, ProfitVO.CASTUNITID);

    // 原币金额
    scaleprocess.setMnyCtlInfo(ProfitPrecision.ORIGMNYKEY,
        ProfitVO.CORIGCURRENCYID);
    //毛利率
    scaleprocess.setSaleDiscountCtlInfo(ProfitPrecision.RATEKEY);
    scaleprocess.process();
  }
}
