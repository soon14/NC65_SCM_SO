package nc.pubimpl.so.m32.ic.mnreport;

import java.util.HashMap;
import java.util.Map;

import nc.vo.ic.icreport.param.mncommon.MNReportQueryParam;
import nc.vo.ic.icreport.param.mncommon.MNRptTempTableWrapperParam;
import nc.vo.ic.icreport.vo.mncommon.MNReportComBodyVO;
import nc.vo.org.OrgVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.pub.SOTable;

import nc.pubitf.so.m32.ic.mnreport.ISaleInvoiceQueryForMN;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 销售发票为国际化报表取数提供的服务实现类
 * 
 * @since 6.1
 * @version 2012-11-29 10:37:43
 * @author 冯加彬
 */
public class SaleInvoiceQueryForMNImpl implements ISaleInvoiceQueryForMN {

  /**
   * 国际化报表临时表字段名
   */
  private static final String[] Mntablefieldnames = new String[] {
    MNReportComBodyVO.CCOUNTRYID, MNReportComBodyVO.CMATERIALOID,
    MNReportComBodyVO.CMATERIALVID, MNReportComBodyVO.CTRANSACTIONTYPE,
    MNReportComBodyVO.CAREAID, MNReportComBodyVO.CORIGCURRENCYID,
    MNReportComBodyVO.NORIGTAXMNY, MNReportComBodyVO.NORIGINALNUM,
    MNReportComBodyVO.CSENDTYPEID, MNReportComBodyVO.CTRADEWORDID,
    MNReportComBodyVO.REMARK
  };

  /**
   * 发货 销售发票取数SQL字段名
   */
  private static final String[] SoSendFieldNames = new String[] {
    SaleInvoiceHVO.CRECECOUNTRYID, SaleInvoiceBVO.CMATERIALID,
    SaleInvoiceBVO.CMATERIALVID, MNReportComBodyVO.CTRANSACTIONTYPE,
    SaleOrderBVO.CORIGAREAID, SaleInvoiceHVO.CORIGCURRENCYID,
    SaleInvoiceBVO.NORIGTAXMNY, SaleInvoiceBVO.NNUM,
    SaleInvoiceBVO.CTRANSPORTTYPEID, SaleInvoiceHVO.CTRADEWORDID,
    MNReportComBodyVO.REMARK
  };

  private Map<String, String> getColumnMap() {
    Map<String, String> columnMap = new HashMap<String, String>();
    for (int i = 0; i < SaleInvoiceQueryForMNImpl.Mntablefieldnames.length; i++) {
      columnMap.put(SaleInvoiceQueryForMNImpl.Mntablefieldnames[i],
          SaleInvoiceQueryForMNImpl.SoSendFieldNames[i]);
    }
    return columnMap;
  }

  private String[] getFinanceOrg(MNReportQueryParam reportQueryParam) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select pk_financeorg from org_financeorg where pk_financeorg in ");
    sql.startParentheses();
    sql.append(" select pk_org from org_orgs where dr=0 and ");
    String pk_corp = reportQueryParam.getCorpoid();
    sql.append(OrgVO.PK_CORP, pk_corp);
    sql.endParentheses();
    sql.append(" and dr=0 ");
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet set = utils.query(sql.toString());
    return set.toOneDimensionStringArray();
  }

  private String getInvoicBTableItem(String item) {
    return "so_saleinvoice_b." + item;
  }

  private String getInvoicHTableItem(String item) {
    return "so_saleinvoice." + item;
  }

  private String getOrderBTableItem(String item) {
    return "so_saleorder_b." + item;
  }

  private String getCountryHTableItem(String item) {
    return "BD_COUNTRYZONE." + item;
  }

  private String getOriginSql(MNReportQueryParam reportQueryParam) {
    String selectFrom = this.getSelectForm();
    String where = this.getWhere(reportQueryParam);
    SqlBuilder sql = new SqlBuilder();
    sql.append(selectFrom);
    sql.append(where);
    return sql.toString();
  }

  private String getSelectForm() {
    SqlBuilder selecFr = new SqlBuilder();
    selecFr.append(" select ");
    selecFr.append(this.getInvoicHTableItem(SaleInvoiceHVO.CRECECOUNTRYID)
        + ", ");
    selecFr.append(this.getInvoicBTableItem(SaleInvoiceBVO.CMATERIALID) + ", ");
    selecFr
        .append(this.getInvoicBTableItem(SaleInvoiceBVO.CMATERIALVID) + ", ");
    selecFr.append("(case when ");
    selecFr.append(this.getInvoicBTableItem(SaleInvoiceBVO.BLARGESSFLAG), "Y");
    selecFr.append(" then '3'");
    selecFr.append(" when ");
    selecFr.append(this.getInvoicBTableItem(SaleInvoiceBVO.NNUM) + " < '0'");
    selecFr.append(" then '2' ");
    selecFr.append("else '1' end) ctransactiontype" + ", ");
    selecFr.append(this.getOrderBTableItem(SaleOrderBVO.CORIGAREAID) + ", ");
    selecFr.append(this.getInvoicHTableItem(SaleInvoiceHVO.CORIGCURRENCYID)
        + ", ");
    selecFr.append(this.getInvoicBTableItem(SaleInvoiceBVO.NORIGTAXMNY) + ", ");
    selecFr.append(this.getInvoicBTableItem(SaleInvoiceBVO.NNUM) + ", ");
    selecFr.append(this.getInvoicBTableItem(SaleInvoiceBVO.CTRANSPORTTYPEID)
        + ", ");
    selecFr
        .append(this.getInvoicHTableItem(SaleInvoiceHVO.CTRADEWORDID) + ", ");
    selecFr.append("'SO' " + MNReportComBodyVO.REMARK);
    selecFr
        .append(" from so_saleinvoice , so_saleinvoice_b , so_saleorder_b , BD_COUNTRYZONE");
    return selecFr.toString();
  }

  private String getWhere(MNReportQueryParam param) {
    SqlBuilder where = new SqlBuilder();
    where.append(" where ");
    where.append(this.getInvoicHTableItem(SaleInvoiceHVO.CSALEINVOICEID)
        + " = " + this.getInvoicBTableItem(SaleInvoiceBVO.CSALEINVOICEID));
    where.append(" and ");
    where.append(this.getOrderBTableItem(SaleOrderBVO.CSALEORDERBID) + " = "
        + this.getInvoicBTableItem(SaleInvoiceBVO.CFIRSTBID));
    where.append(" and ");
    where.append(this.getInvoicHTableItem(SaleInvoiceHVO.CRECECOUNTRYID)
        + " = " + this.getCountryHTableItem("PK_COUNTRY"));
    where.append(" and ");
    where.append(this.getInvoicHTableItem(SaleInvoiceHVO.DR), "0");
    where.append(" and ");
    where.append(this.getInvoicBTableItem(SaleInvoiceBVO.DR), "0");
    where.append(" and ");
    where.append(this.getOrderBTableItem(SaleOrderBVO.DR), "0");
    where.append(" and ");
    where.append(this.getInvoicHTableItem(SaleInvoiceHVO.PK_GROUP), AppContext
        .getInstance().getPkGroup());
    where.append(" and ");
    String[] orgs = this.getFinanceOrg(param);
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String inSelect =
        iq.buildSQL(this.getInvoicHTableItem(SaleInvoiceHVO.PK_ORG), orgs);
    where.append(inSelect);
    where.append(" and ");
    where.append(this.getCountryHTableItem("ISEUCOUNTRY"), "Y");
    where.append(" and ");
    where.append(" NOT "
        + this.getInvoicHTableItem(SaleInvoiceHVO.CRECECOUNTRYID) + " = "
        + this.getInvoicHTableItem(SaleInvoiceHVO.CSENDCOUNTRYID));
    where.append(" and ");

    where.append(this.getInvoicHTableItem(SaleInvoiceHVO.DBILLDATE) + ">= '"
        + param.getDstartdate().toString() + "'");
    where.append(" and ");
    where.append(this.getInvoicHTableItem(SaleInvoiceHVO.DBILLDATE) + "<= '"
        + param.getDenddate().toString() + "'");
    return where.toString();
  }

  @Override
  public MNRptTempTableWrapperParam querySendAndInvoice(
      MNReportQueryParam reportQueryParam) throws BusinessException {
    MNRptTempTableWrapperParam rptTableWrapper =
        new MNRptTempTableWrapperParam();
    String originSql = this.getOriginSql(reportQueryParam);
    Map<String, String> columnMap = new HashMap<String, String>();
    columnMap = this.getColumnMap();
    try {
      rptTableWrapper.setSqlString(originSql);
      rptTableWrapper.setColumnMap(columnMap);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return rptTableWrapper;
  }
}
