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
import nc.vo.so.entry.RecPlanVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

/**
 * 收款计划精度处理
 * 
 * @since 6.0
 * @version 2011-7-1 下午03:36:37
 * @author 么贵敬
 */
public class RecPlanPrecision {

  private static RecPlanPrecision instance = new RecPlanPrecision();

  // 原币金额
  private static final String[] ORIGMNYKEY = new String[] {
    RecPlanVO.NORIGMNY, RecPlanVO.NTOTALORIGMNY
  };

  /**
   * ProfitPrecision 的构造子
   */
  private RecPlanPrecision() {
    // 缺省构造方法
  }

  public static RecPlanPrecision getInstance() {
    return RecPlanPrecision.instance;
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

    // 原币金额
    scaleprocess.setMnyCtlInfo(RecPlanPrecision.ORIGMNYKEY, PosEnum.body, null,
        SaleInvoiceHVO.CORIGCURRENCYID, PosEnum.head, null);

    scaleprocess.process();

  }

  /**
   * 设置表格精度
   * 
   * @param scaleprocess
   */
  private void setTablePrecision(TableScaleProcessor scaleprocess) {
    // 原币金额
    scaleprocess.setMnyCtlInfo(RecPlanPrecision.ORIGMNYKEY,
        RecPlanVO.CORIGCURRENCYID);

    scaleprocess.process();
  }
}
