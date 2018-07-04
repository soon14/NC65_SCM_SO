package nc.ui.so.mreturncondition.ref;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.UIDialog;
import nc.vo.uif2.LoginContext;

/**
 * 此处插入类型说明。 创建日期：(2004-3-11 9:54:53)
 * 
 * @author：左小军
 */
public class FomulaModel extends nc.ui.pub.formulaset.FormulaEditorModel {
  private UIDialog m_paneAppRetBillMny;

  private UIDialog m_paneAppRetNum;

  private UIDialog m_paneDateFunc;

  private UIDialog m_paneInvCode;

  private UIDialog m_paneInvLifePrd;

  private UIDialog m_paneIsLargessFlag;

  private UIDialog m_paneOrderMny;

  private UIDialog m_paneOutNumber;

  private UIDialog m_paneResBillDate;

  private UIDialog m_paneRetRsnType;

  private UIDialog m_paneSaleInvoiceBillDate;

  private UIDialog m_paneSaleOrderBillDate;

  private UIDialog m_paneSaleOutBillDate;

  /**
   * FomulaModel 构造子注解。
   * 
   * @param loginContext
   */
  public FomulaModel(LoginContext loginContext) {
    super();
    this.initModel(loginContext);
  }

  /**
   * 此处插入方法说明。 创建日期：(2004-3-11 11:15:15)
   * 
   * @return nc.ui.so.so141.ReturncndtnRefPane
   */
  public UIDialog getAppRetBillMnyPane() {
    if (this.m_paneAppRetBillMny == null) {
      this.m_paneAppRetBillMny = new AppRetBillMnyPane();
    }
    return this.m_paneAppRetBillMny;
  }

  /**
   * 此处插入方法说明。 创建日期：(2004-3-11 11:15:15)
   * 
   * @return nc.ui.so.so141.ReturncndtnRefPane
   */
  public UIDialog getAppRetNumPane() {
    if (this.m_paneAppRetNum == null) {
      this.m_paneAppRetNum = new AppRetNumPane();
    }
    return this.m_paneAppRetNum;
  }

  /**
   * 此处插入方法说明。 创建日期：(2004-3-11 11:15:15)
   * 
   * @return nc.ui.so.so141.ReturncndtnRefPane
   */
  public UIDialog getDateFunc() {
    if (this.m_paneDateFunc == null) {
      this.m_paneDateFunc = new DateFuncPane();
    }
    return this.m_paneDateFunc;
  }

  /**
   * 此处插入方法说明。 创建日期：(2004-3-11 11:15:15)
   * 
   * @param loginContext
   * @return nc.ui.so.so141.ReturncndtnRefPane
   */
  public UIDialog getInvCodePane(LoginContext loginContext) {
    if (this.m_paneInvCode == null) {
      this.m_paneInvCode = new InvCodePane(loginContext);
    }
    return this.m_paneInvCode;
  }

  /**
   * 此处插入方法说明。 创建日期：(2004-3-11 11:15:15)
   * 
   * @return nc.ui.so.so141.ReturncndtnRefPane
   */
  public UIDialog getInvLifePrdPane() {
    if (this.m_paneInvLifePrd == null) {
      this.m_paneInvLifePrd = new InvLifePrdPane();
    }
    return this.m_paneInvLifePrd;
  }

  /**
   * 此处插入方法说明。 创建日期：(2004-3-11 11:15:15)
   * 
   * @return nc.ui.so.so141.ReturncndtnRefPane
   */
  public UIDialog getIsLargessFlagPane() {
    if (this.m_paneIsLargessFlag == null) {
      this.m_paneIsLargessFlag = new LargessFlagPane();
    }
    return this.m_paneIsLargessFlag;
  }

  /**
   * 此处插入方法说明。 创建日期：(2004-3-11 11:15:15)
   * 
   * @return nc.ui.so.so141.ReturncndtnRefPane
   */
  public UIDialog getOrderMnyPane() {
    if (this.m_paneOrderMny == null) {
      this.m_paneOrderMny = new OrderMnyPane();
    }
    return this.m_paneOrderMny;
  }

  /**
   * 此处插入方法说明。 创建日期：(2004-3-11 11:15:15)
   * 
   * @return nc.ui.so.so141.ReturncndtnRefPane
   */
  public UIDialog getOutNumberPane() {
    if (this.m_paneOutNumber == null) {
      this.m_paneOutNumber = new OutNumberPane();
    }
    return this.m_paneOutNumber;
  }

  /**
   * 此处插入方法说明。 创建日期：(2004-3-11 11:15:15)
   * 
   * @return nc.ui.so.so141.ReturncndtnRefPane
   */
  public UIDialog getResBillDatePane() {
    if (this.m_paneResBillDate == null) {
      this.m_paneResBillDate = new ResBillDatePane();
    }
    return this.m_paneResBillDate;
  }

  /**
   * 此处插入方法说明。 创建日期：(2004-3-11 11:15:15)
   * 
   * @return nc.ui.so.so141.ReturncndtnRefPane
   */
  public UIDialog getRetRsnTypePane() {
    if (this.m_paneRetRsnType == null) {
      this.m_paneRetRsnType = new RetRsnTypePane();
    }
    return this.m_paneRetRsnType;
  }

  /**
   * 此处插入方法说明。 创建日期：(2004-3-11 11:15:15)
   * 
   * @return nc.ui.so.so141.ReturncndtnRefPane
   */
  public UIDialog getSaleInvoiceBillDatePane() {
    if (this.m_paneSaleInvoiceBillDate == null) {
      this.m_paneSaleInvoiceBillDate = new SaleInvoiceBillDatePane();
    }
    return this.m_paneSaleInvoiceBillDate;
  }

  /**
   * 此处插入方法说明。 创建日期：(2004-3-11 11:15:15)
   * 
   * @return nc.ui.so.so141.ReturncndtnRefPane
   */
  public UIDialog getSaleOrderBillDatePane() {
    if (this.m_paneSaleOrderBillDate == null) {
      this.m_paneSaleOrderBillDate = new SaleOrderBillDatePane();
    }
    return this.m_paneSaleOrderBillDate;
  }

  /**
   * 此处插入方法说明。 创建日期：(2004-3-11 11:15:15)
   * 
   * @return nc.ui.so.so141.ReturncndtnRefPane
   */
  public UIDialog getSaleOutBillDatePane() {
    if (this.m_paneSaleOutBillDate == null) {
      this.m_paneSaleOutBillDate = new SaleOutBillDatePane();
    }
    return this.m_paneSaleOutBillDate;
  }

  private void initModel(LoginContext loginContext) {
    this.setSysFunc(new String[][] {
      {
        "abs()", NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0053")/*取一个数的绝对值,本函数需要参数一个数值型参数*/, "abs"
      }, {
        "floor()", NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0054")/*取小于且距离一个数最近的整数,本函数需要参数一个数值型参数*/, "floor"
      },
    });
    this.setBusinessFunc(new Object[][] {
      {
        "getInvCode()", this.getInvCodePane(loginContext), NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0055")/*本函数不需要参数*/, NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0056")/*物料编码*/
      },
      {
        "getInvLifePrd()", this.getInvLifePrdPane(), NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0055")/*本函数不需要参数*/, NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0057")/*物料的生命周期*/
      },
      {
        "getOutNumber()", this.getOutNumberPane(), NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0058")/*本函数需要输入时间区间*/, NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0059")/*实际出库数量*/
      },
      {
        "getAppRetNum()", this.getAppRetNumPane(), NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0055")/*本函数不需要参数*/, NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0060")/*申请退货数量*/
      },
      {
        "getOrderMny()", this.getOrderMnyPane(), NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0058")/*本函数需要输入时间区间*/, NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0061")/*销售订单金额*/
      },
      {
        "getAppRetBillMny()", this.getAppRetBillMnyPane(), NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0055")/*本函数不需要参数*/,
        NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0062")/*申请退货单据金额*/
      },
      {
        "isLargessFlag()", this.getIsLargessFlagPane(), NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0055")/*本函数不需要参数*/, NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0063")/*赠品*/
      },
      {
        "getResBillDate()", this.getResBillDatePane(), NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0055")/*本函数不需要参数*/, NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0064")/*单据日期*/
      },
      {
        "getRetRsnType()", this.getRetRsnTypePane(), NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0055")/*本函数不需要参数*/, NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0065")/*退货原因类型*/
      },
      {
        "getSaleInvoiceBillDate()", this.getSaleInvoiceBillDatePane(),
        NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0055")/*本函数不需要参数*/, NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0066")/*源销售发票日期*/
      },
      {
        "getSaleOrderBillDate()", this.getSaleOrderBillDatePane(), NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0055")/*本函数不需要参数*/,
        NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0067")/*源销售订单日期*/
      },
      {
        "getSaleOutBillDate()", this.getSaleOutBillDatePane(), NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0055")/*本函数不需要参数*/,
        NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0068")/*源出库单日期*/
      }, {
        "get()", this.getDateFunc(), NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0055")/*本函数不需要参数*/, NCLangRes.getInstance().getStrByID("4006006_0", "04006006-0069")/*日期运算函数*/
      }
    });
    this.setInputControl(false);
  }
}
