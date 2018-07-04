package nc.impl.so.m30.sobalance;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceViewVO;
import nc.vo.so.pub.SOTable;

import nc.itf.so.m30.sobalance.ISOBalanceQuery;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * @author gdsjw
 * 
 */
public class SOBalanceQueryImpl implements ISOBalanceQuery {

  @Override
  public SoBalanceVO[] querySoBalanceVOBySaleOrderIDs(String[] saleorderids)
      throws BusinessException {
    if (saleorderids == null || saleorderids.length == 0) {
      return new SoBalanceVO[0];
    }
    List<String> idList = new ArrayList<String>();
    for (String saleorderid : saleorderids) {
      if (saleorderid != null) {
        idList.add(saleorderid);
      }
    }
    if (idList.size() == 0) {
      return new SoBalanceVO[0];
    }
    SoBalanceVO[] bills = null;
    try {
      DataAccessUtils utils = new DataAccessUtils();
      IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
      SqlBuilder sql = new SqlBuilder();
      sql.append("select csobalanceid from so_balance where isnull(dr,0) = 0 ");
      sql.append(" and ");
      sql.append(iq.buildSQL(SoBalanceHVO.CSALEORDERID, saleorderids));

      IRowSet rowset = utils.query(sql.toString());
      String[] cbillids = rowset.toOneDimensionStringArray();

      // ¸ù¾Ýid²éÑ¯VO
      BillQuery<SoBalanceVO> query =
          new BillQuery<SoBalanceVO>(SoBalanceVO.class);
      bills = query.query(cbillids);
    }
    catch (Exception ex) {

      ExceptionUtils.marsh(ex);
    }
    if (bills != null && bills.length > 0) {
      return bills;
    }
    return new SoBalanceVO[0];
  }

  @Override
  public SoBalanceViewVO[] querySoBalanceViewByGatheringBillBodyIDs(
      String[] paybillrowids) throws BusinessException {
    StringBuffer sqlid = new StringBuffer();
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sqlid
        .append("Select csobalancebid from so_balance_b ")
        .append(" inner join so_balance on so_balance.csobalanceid = ")
        .append(" so_balance_b.csobalanceid where ")
        .append(iq.buildSQL(SoBalanceBVO.CPAYBILLROWID, paybillrowids))
        .append(
            "and isnull(so_balance.dr,0) = 0 and isnull(so_balance_b.dr,0) = 0 ");
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sqlid.toString());
    String[] csobalancebids = rowset.toOneDimensionStringArray();
    ViewQuery<SoBalanceViewVO> bo =
        new ViewQuery<SoBalanceViewVO>(SoBalanceViewVO.class);
    bo.setSharedHead(true);

    SoBalanceViewVO[] views = bo.query(csobalancebids);

    return views;
  }
}
