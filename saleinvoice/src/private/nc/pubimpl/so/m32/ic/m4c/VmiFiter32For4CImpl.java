package nc.pubimpl.so.m32.ic.m4c;

import java.util.List;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.pub.SOTable;

import nc.pubitf.so.m32.ic.m4c.IVmiFiter32For4C;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 过滤销售发票已经做过汇总的
 * 
 * @since 6.0
 * @version 2011-6-18 下午01:17:45
 * @author 么贵敬
 */
public class VmiFiter32For4CImpl implements IVmiFiter32For4C {

  /**
   * 返回发票没有左右汇总的销售出库单bids
   */
  @Override
  public List<String> getSumBids(List<String> listBids, String[] ids)
      throws BusinessException {
    String[] bids = listBids.toArray(new String[0]);
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String inbsql = iq.buildSQL(SaleInvoiceBVO.CSRCBID, bids);

    iq = new IDExQueryBuilder(SOTable.TMP_SO_ID2.getName());
    String insql = iq.buildSQL(SaleInvoiceBVO.CSRCID, ids);
    SqlBuilder sql = new SqlBuilder();
    sql.append("select ");
    sql.append(SaleInvoiceBVO.CSRCBID);
    sql.append(" from so_saleinvoice_b ");
    sql.append(" where ");
    sql.append(inbsql);
    sql.append(" and ");
    sql.append(insql);
    sql.append(" and ");
    sql.append(SaleInvoiceBVO.VSRCTYPE, ICBillType.SaleOut.getCode());
    sql.append(" and ");
    sql.appendIDIsNotNull(SaleInvoiceBVO.CSUMID);
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql.toString());
    // List<String> ret = new ArrayList<String>();
    while (rowset.next()) {
      listBids.remove(rowset.getString(0));
    }

    return listBids;
  }

}
