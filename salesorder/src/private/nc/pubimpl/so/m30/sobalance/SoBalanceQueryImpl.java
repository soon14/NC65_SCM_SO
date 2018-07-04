/**
 * 
 */
package nc.pubimpl.so.m30.sobalance;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.pubitf.so.m30.sobalance.ISOBalanceQueryForArap;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.so.m30.sobalance.entity.SoBalanceViewVO;

/**
 * @author gdsjw
 * 
 */
public class SoBalanceQueryImpl implements ISOBalanceQueryForArap {

  /**
   * 
   */
  public SoBalanceQueryImpl() {
    // TODO Auto-generated constructor stub
  }

  /*
   * (non-Javadoc)
   * @seenc.pubitf.so.m30.sobalance.ISOBalanceQueryForArap#
   * querySoBalanceAccbalmnyForVerify(java.lang.String, java.lang.String)
   */
  @Override
  public SoBalanceViewVO[] querySoBalanceAccbalmnyForVerify(
      String paybillrowid, String saleorderid) throws BusinessException {
    StringBuffer sqlid = new StringBuffer();
    sqlid
        .append("Select csobalancebid from so_balance_b ")
        .append("inner join so_balance on ")
        .append("so_balance.csobalanceid = so_balance_b.csobalanceid ");
    sqlid.append(" where ");
    if ((saleorderid != null) && (saleorderid.length() > 0)) {
      sqlid.append(" so_balance.csaleorderid = '" + saleorderid + "' and ");
    }
    sqlid.append(" so_balance_b.cpaybillrowid = '" + paybillrowid + "' ");
    sqlid
        .append("and isnull(so_balance.dr,0) = 0 ")
        .append("and isnull(so_balance_b.dr,0) = 0 ");
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sqlid.toString());
    String[] cbillids = rowset.toOneDimensionStringArray();
    ViewQuery<SoBalanceViewVO> bo =
        new ViewQuery<SoBalanceViewVO>(SoBalanceViewVO.class);
    bo.setSharedHead(true);

    SoBalanceViewVO[] views = bo.query(cbillids);

    return views;
  }

}
