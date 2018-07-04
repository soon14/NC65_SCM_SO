package nc.pubimpl.so.m32.so.m30;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.enumeration.BillStatus;

import nc.pubitf.so.m32.so.m30.IQuery32For30;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 销售发票为销售订单提供的服务实现类
 * 
 * @since 6.1
 * @version 2012-11-29 10:43:40
 * @author 冯加彬
 */
public class Query32For30Impl implements IQuery32For30 {

  @Override
  public Map<String, UFDouble> getInvoiceApproveNum(String[] ids, String[] bids)
      throws BusinessException {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select ");

    sql.append("body.cfirstbid,sum(isnull(nnum,0)) ");
    sql.append("from so_saleinvoice head ");
    sql.append("inner join so_saleinvoice_b body on ");
    sql.append("head.csaleinvoiceid = body.csaleinvoiceid ");
    sql.append("where head.dr = 0 and body.dr = 0 and ");
    sql.append("head.fstatusflag", BillStatus.AUDIT.getIntegerValue());
    sql.append(" and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sql.append(iq.buildSQL(SaleInvoiceBVO.CFIRSTBID, bids));
    sql.append(" and ");

    iq = new IDExQueryBuilder(SOTable.TMP_SO_ID2.getName());
    sql.append(iq.buildAnotherSQL(SaleInvoiceBVO.CFIRSTID, ids));
    sql.append(" group by body.cfirstbid");
    DataAccessUtils dataacc = new DataAccessUtils();
    Map<String, UFDouble> ret = new HashMap<String, UFDouble>();
    try {
      IRowSet rowset = dataacc.query(sql.toString());
      while (rowset.next()) {
        ret.put(rowset.getString(0), rowset.getUFDouble(1));
      }
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return ret;
  }

  @Override
  public UFBoolean[] isInvoiceAllApprove(String[] orderids, String[] orderbids)
      throws BusinessException {

    SqlBuilder sql = new SqlBuilder();
    sql.append("select body.cfirstbid from so_saleinvoice head ");
    sql.append("inner join so_saleinvoice_b body on ");
    sql.append("head.csaleinvoiceid = body.csaleinvoiceid ");
    sql.append("where head.dr = 0 and body.dr = 0 and ");
    sql.append("head.FSTATUSFLAG", "<>", (Integer) BillStatus.AUDIT.value());
    sql.append(" and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sql.append(iq.buildSQL(SaleInvoiceBVO.CFIRSTBID, orderbids));
    sql.append(" and ");

    iq = new IDExQueryBuilder(SOTable.TMP_SO_ID2.getName());
    sql.append(iq.buildAnotherSQL(SaleInvoiceBVO.CFIRSTID, orderids));
    Set<String> hsorderbid = new HashSet<String>();
    try {
      DataAccessUtils dataacc = new DataAccessUtils();
      IRowSet rowset = dataacc.query(sql.toString());

      for (String bid : rowset.toOneDimensionStringArray()) {
        hsorderbid.add(bid);
      }
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    int ilength = orderbids.length;
    UFBoolean[] isAllApp = new UFBoolean[ilength];
    for (int i = 0; i < ilength; i++) {
      if (hsorderbid.contains(orderbids[i])) {
        isAllApp[i] = UFBoolean.FALSE;
      }
      else {
        isAllApp[i] = UFBoolean.TRUE;
      }
    }

    return isAllApp;
  }

  @Override
  public Map<String, UFBoolean> isExistNextInvoice(String[] orderhids)
      throws BusinessException {

    SqlBuilder wheresql = new SqlBuilder();
    wheresql.append(" and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    wheresql.append(iq.buildSQL(SaleInvoiceBVO.CSRCID, orderhids));

    wheresql.append(" and ");
    wheresql.append(SaleInvoiceBVO.VSRCTYPE, SOBillType.Order.getCode());

    String[] selkey = new String[] {
      SaleInvoiceBVO.CSRCID
    };
    VOQuery<SaleInvoiceBVO> querysrv =
        new VOQuery<SaleInvoiceBVO>(SaleInvoiceBVO.class, selkey);

    SaleInvoiceBVO[] retinvoicebvos = querysrv.query(wheresql.toString(), null);
    Set<String> setexistid = new HashSet<String>();
    for (SaleInvoiceBVO bvo : retinvoicebvos) {
      setexistid.add(bvo.getCsrcid());
    }
    Map<String, UFBoolean> mapisexist = new HashMap<String, UFBoolean>();
    for (String hid : orderhids) {
      if (setexistid.contains(hid)) {
        mapisexist.put(hid, UFBoolean.TRUE);
      }
      else {
        mapisexist.put(hid, UFBoolean.FALSE);
      }
    }
    return mapisexist;
  }

  @Override
  public Map<String, UFDouble> getInvoiceNorigtaxmny(String[] ids, String[] bids)
      throws BusinessException {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select ");

    sql.append("body.cfirstbid,sum(isnull(ntaxmny,0)) ");
    sql.append("from so_saleinvoice head ");
    sql.append("inner join so_saleinvoice_b body on ");
    sql.append("head.csaleinvoiceid = body.csaleinvoiceid ");
    sql.append("where head.dr = 0 and body.dr = 0 and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sql.append(iq.buildSQL(SaleInvoiceBVO.CFIRSTBID, bids));
    sql.append(" and ");

    iq = new IDExQueryBuilder(SOTable.TMP_SO_ID2.getName());
    sql.append(iq.buildAnotherSQL(SaleInvoiceBVO.CFIRSTID, ids));
    sql.append(" group by body.cfirstbid");
    DataAccessUtils dataacc = new DataAccessUtils();
    Map<String, UFDouble> ret = new HashMap<String, UFDouble>();
    try {
      IRowSet rowset = dataacc.query(sql.toString());
      while (rowset.next()) {
        ret.put(rowset.getString(0), rowset.getUFDouble(1));
      }
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return ret;
  }
}
