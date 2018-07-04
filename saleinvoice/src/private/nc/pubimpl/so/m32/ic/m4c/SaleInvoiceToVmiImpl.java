package nc.pubimpl.so.m32.ic.m4c;

import java.util.ArrayList;
import java.util.List;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceViewVO;
import nc.vo.so.pub.SOTable;
import nc.vo.trade.checkrule.VOChecker;

import nc.pubitf.ic.m50.m32.IVmiSumQueryFor32;
import nc.pubitf.so.m32.ic.m4c.ISaleInvoiceToVmi;

import nc.bs.framework.common.NCLocator;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 销售发票为VMI汇总提供的服务实现类
 * 
 * @since 6.1
 * @version 2012-11-29 10:36:23
 * @author 冯加彬
 */
public class SaleInvoiceToVmiImpl implements ISaleInvoiceToVmi {

  @Override
  public void clearM32SumID(String[] invoicebids) throws BusinessException {
    String[] sumids = null;
    if (!VOChecker.isEmpty(invoicebids)) {
      sumids = new String[invoicebids.length];
    }
    this.updateM32SumID(invoicebids, sumids);
  }

  @Override
  public SaleInvoiceViewVO[] queryConsumeInvoice(String sql)
      throws BusinessException {
    SaleInvoiceViewVO[] bills = this.queryInvoiceViews(sql);
    SaleInvoiceViewVO[] ret = this.filterBills(bills);
    return ret;
  }

  @Override
  public SaleInvoiceViewVO[] queryInvoiceBybids(String[] invoicebids)
      throws BusinessException {

    if (VOChecker.isEmpty(invoicebids)) {
      return new SaleInvoiceViewVO[0];
    }
    ViewQuery<SaleInvoiceViewVO> query =
        new ViewQuery<SaleInvoiceViewVO>(SaleInvoiceViewVO.class);
    SaleInvoiceViewVO[] bills = query.query(invoicebids);

    return bills;
  }

  @Override
  public SaleInvoiceViewVO[] queryInvoiceBySumids(String[] sumids)
      throws BusinessException {
    if (VOChecker.isEmpty(sumids)) {
      return new SaleInvoiceViewVO[0];
    }
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String insql = iq.buildSQL(SaleInvoiceBVO.CSUMID, sumids);
    SqlBuilder sql = new SqlBuilder();
    sql.append("select csaleinvoicebid from so_saleinvoice_b where dr = 0 and ");
    sql.append(insql);

    return this.queryInvoiceViews(sql.toString());
  }

  @Override
  public SaleInvoiceViewVO[] queryVmiSumInvoice(String[] invoicebids,
      String[] headkeys, String[] bodykeys) throws BusinessException {

    String sumsql = this.getVmiSumInvoiceSql(invoicebids, headkeys, bodykeys);
    DataAccessUtils dataacc = new DataAccessUtils();
    IRowSet rowset = dataacc.query(sumsql);
    List<SaleInvoiceViewVO> arViews = new ArrayList<SaleInvoiceViewVO>();
    while (rowset.next()) {
      SaleInvoiceViewVO view = new SaleInvoiceViewVO();
      SaleInvoiceHVO head = new SaleInvoiceHVO();
      int headlength = 0;
      if (null != headkeys && headkeys.length > 0) {
        headlength = headkeys.length;
        for (int i = 0; i < headlength; i++) {
          head.setAttributeValue(headkeys[i], rowset.getObject(i));
        }
      }
      view.setHead(head);

      SaleInvoiceBVO body = new SaleInvoiceBVO();
      int bodylength = 0;
      if (null != bodykeys && bodykeys.length > 0) {
        bodylength = bodykeys.length;
        for (int i = 0; i < bodylength; i++) {
          body.setAttributeValue(bodykeys[i], rowset.getObject(i + headlength));
        }
      }
      body.setNnum(rowset.getUFDouble(headlength + bodylength));
      body.setNastnum(rowset.getUFDouble(headlength + bodylength + 1));

      view.setItem(body);

      arViews.add(view);
    }
    return arViews.toArray(new SaleInvoiceViewVO[0]);
  }

  @Override
  public void rewriteM32SumID(String[] invoicebids, String[] sumids)
      throws BusinessException {
    this.updateM32SumID(invoicebids, sumids);
  }

  private SaleInvoiceViewVO[] filterBills(SaleInvoiceViewVO[] bills) {
    List<String> bids = new ArrayList<String>();
    for (SaleInvoiceViewVO bill : bills) {
      bids.add(bill.getItem().getCsrcbid());
    }
    IVmiSumQueryFor32 srv =
        NCLocator.getInstance().lookup(IVmiSumQueryFor32.class);
    List<String> newbids = new ArrayList<String>();
    try {
      newbids = srv.filterHasOutVmi(bids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    List<SaleInvoiceViewVO> retlist = new ArrayList<SaleInvoiceViewVO>();
    for (SaleInvoiceViewVO bill : bills) {
      String bid = bill.getItem().getCsrcbid();
      if (newbids.contains(bid)) {
        retlist.add(bill);
      }
    }
    return retlist.toArray(new SaleInvoiceViewVO[retlist.size()]);
  }

  private String getVmiSumInvoiceSql(String[] invoicebids, String[] headkeys,
      String[] bodykeys) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select ");
    // 由于后面要根据该条件分组，所以缓存
    SqlBuilder headsql = new SqlBuilder();
    if (null != headkeys && headkeys.length > 0) {
      for (String key : headkeys) {
        headsql.append("head.");
        headsql.append(key);
        headsql.append(",");
      }
    }
    sql.append(headsql.toString());

    SqlBuilder bodysql = new SqlBuilder();
    if (null != bodykeys && bodykeys.length > 0) {
      for (String key : bodykeys) {
        bodysql.append("body.");
        bodysql.append(key);
        bodysql.append(",");
      }
    }
    sql.append(bodysql.toString());

    sql.append("sum(");
    sql.append("body.");
    sql.append(SaleInvoiceBVO.NNUM);
    sql.append("),");
    sql.append("sum(");
    sql.append("body.");
    sql.append(SaleInvoiceBVO.NASTNUM);
    sql.append(")");

    sql.append(" from so_saleinvoice head inner join so_saleinvoice_b body");
    sql.append(" on head.csaleinvoiceid = body.csaleinvoiceid ");

    sql.append(" where ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String idsql = iq.buildSQL("body.csaleinvoicebid", invoicebids);
    sql.append(idsql);

    sql.append(" and head.dr = 0 and body.dr = 0 ");
    sql.append(" group by ");
    sql.append(headsql.toString());
    bodysql.deleteLastChar();
    sql.append(bodysql.toString());

    return sql.toString();
  }

  private SaleInvoiceViewVO[] queryInvoiceViews(String sql) {

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet set = utils.query(sql);
    if (set.size() == 0) {
      return new SaleInvoiceViewVO[0];
    }
    String[] ids = set.toOneDimensionStringArray();
    ViewQuery<SaleInvoiceViewVO> query =
        new ViewQuery<SaleInvoiceViewVO>(SaleInvoiceViewVO.class);
    SaleInvoiceViewVO[] bills = query.query(ids);

    return bills;
  }

  /**
   * 方法功能描述：更新销售发票的消耗汇总ID。
   * <p>
   * <b>参数说明</b>
   * 
   * @param invoicebids
   * @param sumids
   *          <p>
   * @author fengjb
   * @time 2010-7-8 下午06:35:08
   */
  private void updateM32SumID(String[] invoicebids, String[] sumids) {

    if (VOChecker.isEmpty(invoicebids) || VOChecker.isEmpty(sumids)
        || invoicebids.length != sumids.length) {

      ExceptionUtils
          .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID(
              "4006008_0", "04006008-0061")/*@res "消耗汇总回写销售发票传入参数错误"*/);
    }
    int ilength = invoicebids.length;
    SaleInvoiceBVO[] voBodys = new SaleInvoiceBVO[ilength];
    for (int i = 0; i < ilength; i++) {
      voBodys[i] = new SaleInvoiceBVO();
      voBodys[i].setCsaleinvoicebid(invoicebids[i]);
      voBodys[i].setCsumid(sumids[i]);
    }

    VOUpdate<SaleInvoiceBVO> updatesrv = new VOUpdate<SaleInvoiceBVO>();
    String[] names = new String[] {
      SaleInvoiceBVO.CSUMID
    };
    updatesrv.update(voBodys, names);
  }

  @Override
  public SaleInvoiceBVO[] queryInvoiceBodyBySrc(String[] srchids,
      String[] srcbids, String[] qrykeys) throws BusinessException {

    if (null == srcbids || srcbids.length == 0) {
      return new SaleInvoiceBVO[0];
    }
    SqlBuilder wheresql = new SqlBuilder();
    wheresql.append(" and ");
    String pk_group = AppContext.getInstance().getPkGroup();
    wheresql.append(SaleInvoiceBVO.PK_GROUP, pk_group);
    wheresql.append(" and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    wheresql.append(iq.buildSQL(SaleInvoiceBVO.CSRCID, srchids));
    wheresql.append(" and ");

    iq = new IDExQueryBuilder(SOTable.TMP_SO_ID2.getName());
    wheresql.append(iq.buildSQL(SaleInvoiceBVO.CSRCBID, srcbids));

    VOQuery<SaleInvoiceBVO> qrysrv =
        new VOQuery<SaleInvoiceBVO>(SaleInvoiceBVO.class, qrykeys);
    SaleInvoiceBVO[] invbvos = qrysrv.query(wheresql.toString(), null);
    return invbvos;
  }
}
