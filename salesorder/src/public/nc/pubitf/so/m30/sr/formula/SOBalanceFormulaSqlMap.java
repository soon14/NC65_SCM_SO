package nc.pubitf.so.m30.sr.formula;

import java.io.Serializable;

import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.pub.SOTable;

import nc.pubitf.sr.formula.ISRFormulaSqlMap;
import nc.pubitf.sr.formula.ISRFormulaSqlPara;

import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 订单收款核销为返利提供的核销金额取数函数映射
 * 
 * @since 6.1
 * @version 2012-11-27 16:59:29
 * @author 冯加彬
 */
public class SOBalanceFormulaSqlMap implements ISRFormulaSqlMap, Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 8686729709825558923L;

  private ISRFormulaSqlPara para;

  /**
   * 构造子
   * 
   * @param para
   */
  public SOBalanceFormulaSqlMap(ISRFormulaSqlPara para) {
    this.para = para;
  }

  @Override
  public String getFrom() {
    SqlBuilder from = new SqlBuilder();
    from.append("so_balance");
    String[] transtype = this.para.getOrdertype();
    String[] baltype = this.para.getBaltype();
    // 促销类型ID数组
    // String[] prcPromotTypeIDs = this.para.getCprcpromottypeid();
    if (null != transtype && transtype.length > 0 || null != baltype
        && baltype.length > 0) {
      from.append(" inner join so_saleorder on ");
      from.append("so_balance.csaleorderid = so_saleorder.csaleorderid ");
    }
    // if ((prcPromotTypeIDs != null && prcPromotTypeIDs.length > 0)) {
    // from.append(" inner join so_saleorder_b on ");
    // from.append("so_balance.csaleorderid = so_saleorder_b.csaleorderid ");
    // }
    return from.toString();
  }

  @Override
  public String getWhere() {
    SqlBuilder where = new SqlBuilder();
    this.appendParaWhere(where);
    this.appendFixWhere(where);
    return where.toString();
  }

  private void appendFixWhere(SqlBuilder where) {

    String pk_group = AppContext.getInstance().getPkGroup();
    where.append(" and ");
    where.append(" so_balance.pk_group", pk_group);
    where.append(" and ");
    where.append(" so_balance.dr ", 0);

  }

  private void appendParaWhere(SqlBuilder where) {
    IDExQueryBuilder iq =
        new IDExQueryBuilder(SOTable.TMP_SO_SALEORG.getName());
    String[] saleorg = this.para.getSaleorg();
    where.append(iq.buildSQL("so_balance.pk_org", saleorg));
    // 开始日期
    String begindate = this.para.getDbegindate().toString();
    where.append(" and ");
    where.append("so_balance.dbilldate", ">=", begindate);

    // 结束日期
    String enddate = this.para.getDenddate().toString();
    where.append(" and ");
    where.append("so_balance.dbilldate", "<=", enddate);

    // 订单类型
    String[] ordertype = this.para.getOrdertype();
    if (null != ordertype && ordertype.length > 0) {
      where.append(" and ");
      iq = new IDExQueryBuilder(SOTable.TMP_SO_ORDERTYPE.getName());
      where.append(iq.buildSQL("so_saleorder.ctrantypeid", ordertype));
    }
    // 订单客户
    String[] ordercust = this.para.getOrdercust();
    if (null != ordercust && ordercust.length > 0) {
      where.append(" and ");
      iq = new IDExQueryBuilder(SOTable.TMP_SO_ORDERCUST.getName());
      where.append(iq.buildSQL("so_balance.ccustomerid", ordercust));
    }
    // 开票客户
    String[] invoicecust = this.para.getInvoicecust();
    if (null != invoicecust && invoicecust.length > 0) {
      where.append(" and ");
      iq = new IDExQueryBuilder(SOTable.TMP_SO_INVCUST.getName());
      where.append(iq.buildSQL("so_balance.cinvoicecustid", invoicecust));
    }
    // 币种
    String currency = this.para.getCcurrencyid();
    where.append(" and ");
    where.append("so_balance.corigcurrencyid", currency);
    // 结算方式
    String[] baltype = this.para.getBaltype();
    if (null != baltype && baltype.length > 0) {
      where.append(" and ");
      iq = new IDExQueryBuilder(SOTable.TMP_SO_BALTYPE.getName());
      where.append(iq.buildSQL("so_saleorder.cbalancetypeid", baltype));
    }
    // // 促销类型ID数T组
    // String[] prcPromotTypeIDs = this.para.getCprcpromottypeid();
    // // 促销类型ID数组不为空的时候进行过滤
    // if (null != prcPromotTypeIDs && prcPromotTypeIDs.length > 0) {
    // where.append(" and ");
    // iq = new IDExQueryBuilder(SOTable.TMP_SO_PRCPROMTYPE.getName());
    // where.append(iq.buildSQL("so_saleorder_b.cprcpromottypeid",
    // prcPromotTypeIDs));
    // }
  }

  @Override
  public String getSumKey() {
    return "so_balance.ntotalpaymny";
  }

  @Override
  public String getSaleOrgKey() {
    return "so_balance.pk_org";
  }

  @Override
  public String getSettleOrgKey() {
    return "so_balance.carorgid";
  }

  @Override
  public String getOrderCustKey() {
    return "so_balance.ccustomerid";
  }

  @Override
  public String getInvoiceCustKey() {
    return "so_balance.cinvoicecustid";
  }

  @Override
  public String getMaterialOIDKey() {
    return null;
  }
}
