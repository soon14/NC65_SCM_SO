package nc.ui.so.mbuylargess.view;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.scale.ListPaneScaleProcessor;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.so.mbuylargess.entity.BuyLargessBVO;
import nc.vo.so.mbuylargess.entity.BuyLargessHVO;

public class BuyLargessPrecision {

  /**
   * 表体赠送主数量
   */
  private static final String[] BODYNUMKEYS = new String[] {
    BuyLargessBVO.NNUM
  };

  /**
   * 表体赠送金额
   */
  private static final String[] BODYORIGMNYKEYS = new String[] {
    BuyLargessBVO.NMNY
  };

  /**
   * 表体单价
   */
  private static final String[] BODYPRICEKEYS = new String[] {
    BuyLargessBVO.NPRICE
  };

  /**
   * 表头购买主数量
   */
  private static final String[] HEADNUMKEYS = new String[] {
    BuyLargessHVO.NBUYNUM
  };

  private static BuyLargessPrecision precision = new BuyLargessPrecision();

  private BuyLargessPrecision() {
    //
  }

  public static BuyLargessPrecision getInstance() {
    return BuyLargessPrecision.precision;
  }

  /**
   * 
   * 方法功能描述：设置卡片界面精度。
   */
  public void setCardPrecision(String pk_group, BillCardPanel cardpanel) {
    BillScaleProcessor scaleprocess =
        new CardPaneScaleProcessor(pk_group, cardpanel);
    this.setBillPrecision(scaleprocess);
    // 上限值
    BillItem curitem = cardpanel.getHeadItem(BuyLargessHVO.PK_CURRINFO);
    BillModel model = cardpanel.getBillModel();
    TopLimitValueDecimalAdapter adpter =
        new TopLimitValueDecimalAdapter(model, curitem);
    cardpanel.getBodyItem(BuyLargessBVO.NTOPLIMITVALUE).addDecimalListener(
        adpter);
  }

  /**
   * 
   * 方法功能描述：设置列表界面精度。
   */
  public void setListPrecision(String pk_group, BillListPanel listpanel) {
    BillScaleProcessor scaleprocess =
        new ListPaneScaleProcessor(pk_group, listpanel);
    this.setBillPrecision(scaleprocess);
    // 上限值
    BillItem curitem =
        listpanel.getHeadBillModel().getItemByKey(BuyLargessHVO.PK_CURRINFO);
    BillModel model = listpanel.getBodyBillModel();
    TopLimitValueDecimalAdapter adpter =
        new TopLimitValueDecimalAdapter(model, curitem);
    listpanel.getBodyItem(BuyLargessBVO.NTOPLIMITVALUE).addDecimalListener(
        adpter);
  }

  private void setBillPrecision(BillScaleProcessor scaleprocess) {
    // 表体单价
    scaleprocess.setPriceCtlInfo(BuyLargessPrecision.BODYPRICEKEYS,
        PosEnum.body, null,BuyLargessHVO.PK_CURRINFO,PosEnum.head,null);

    // 表体赠送主数量
    scaleprocess.setNumCtlInfo(BuyLargessPrecision.BODYNUMKEYS, PosEnum.body,
        null, BuyLargessBVO.PK_MEASDOC, PosEnum.body, null);

    // 表头购买主数量
    scaleprocess.setNumCtlInfo(BuyLargessPrecision.HEADNUMKEYS, PosEnum.head,
        null, BuyLargessHVO.CBUYUNITID, PosEnum.head, null);

    // 表体赠送金额
    scaleprocess.setMnyCtlInfo(BuyLargessPrecision.BODYORIGMNYKEYS,
        PosEnum.body, null, BuyLargessHVO.PK_CURRINFO, PosEnum.head, null);

    scaleprocess.process();

  }

}
