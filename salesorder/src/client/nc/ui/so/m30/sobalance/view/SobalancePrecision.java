package nc.ui.so.m30.sobalance.view;

import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.pubapp.scale.TableScaleProcessor;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.scale.BillModelScaleProcessor;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.scale.ListPaneScaleProcessor;

/**
 * 收款核销精度处理工具类
 * 
 * @since 6.0
 * @version 2011-5-24 上午11:19:06
 * @author 么贵敬
 */
public class SobalancePrecision {

  // 原币金额
  private static final String[] ORIGMNYKEYS = new String[] {
    SoBalanceBVO.NORIGACCBALMNY, SoBalanceBVO.NORIGORDBALMNY,
    SoBalanceBVO.NORIGTHISBALMNY, SoBalanceBVO.NORIGARMNY,
    // 其他核销金额(订单现销用)
    "norigotherbalmny"
  };

  // 原币金额（表头）
  private static final String[] ORIGMNYKEYSHEAD = new String[] {
    SoBalanceHVO.NTOTALORIGBALMNY, SoBalanceHVO.NTOTALORIGTAXMNY,
    SoBalanceHVO.NTOTALPAYMNY,
  };

  private static SobalancePrecision precision = new SobalancePrecision();

  /**
   * 
   * PreOrderPrecision 的构造子
   */
  private SobalancePrecision() {
    //
  }

  public static SobalancePrecision getInstance() {
    return SobalancePrecision.precision;
  }

  /**
   * 提供给销售订单现销用，因为现销字段在表体
   * 
   * @param scaleprocess
   */
  public void setCashSalePrecision(BillScaleProcessor scaleprocess) {

    // 原币金额
    scaleprocess.setMnyCtlInfo(SobalancePrecision.ORIGMNYKEYS, PosEnum.body,
        null, SoBalanceHVO.CORIGCURRENCYID, PosEnum.body, null);
    scaleprocess.setMnyCtlInfo(SobalancePrecision.ORIGMNYKEYSHEAD,
        PosEnum.body, null, SoBalanceHVO.CORIGCURRENCYID, PosEnum.body, null);
    scaleprocess.process();
  }

  public void setBillPrecision(BillScaleProcessor scaleprocess) {

    // 原币金额
    scaleprocess.setMnyCtlInfo(SobalancePrecision.ORIGMNYKEYS, PosEnum.body,
        null, SoBalanceHVO.CORIGCURRENCYID, PosEnum.head, null);

    // 原币金额（表头）
    scaleprocess.setMnyCtlInfo(SobalancePrecision.ORIGMNYKEYSHEAD,
        PosEnum.head, null, SoBalanceHVO.CORIGCURRENCYID, PosEnum.head, null);

    scaleprocess.process();

  }

  /**
   * 
   * 方法功能描述：设置卡片界面精度。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_group
   * @param cardpanel
   *          <p>
   * @author fengjb
   * @time 2010-5-26 下午04:55:35
   */
  public void setCardPrecision(String pk_group, BillCardPanel cardpanel) {
    BillScaleProcessor scaleprocess =
        new CardPaneScaleProcessor(pk_group, cardpanel);

    this.setBillPrecision(scaleprocess);
  }

  public void setListPrecision(String pk_group, BillListPanel listpanel) {
    BillScaleProcessor scaleprocess =
        new ListPaneScaleProcessor(pk_group, listpanel);

    this.setBillPrecision(scaleprocess);
  }

  public void setModelPrecision(String pk_group, BillModel model) {
    TableScaleProcessor scaleprocess =
        new BillModelScaleProcessor(pk_group, model);
    this.setTablePrecision(scaleprocess);
  }

  private void setTablePrecision(TableScaleProcessor scaleprocess) {

    // 原币金额
    scaleprocess.setMnyCtlInfo(SobalancePrecision.ORIGMNYKEYS,
        SoBalanceHVO.CORIGCURRENCYID);

    scaleprocess.process();
  }

}
