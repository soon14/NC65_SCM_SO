package nc.pubimpl.so.m30.so.balance;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.SOTable;

import nc.pubitf.so.m30.so.balance.ISaleOrderForBalance;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 销售订单提供给订单核销服务接口
 * 
 * @since 6.1
 * @version 2012-11-29 11:13:02
 * @author 冯加彬
 */
public class SaleOrderForBalanceImpl implements ISaleOrderForBalance {

  @Override
  public SaleOrderViewVO[] querySaleOrderViewVOByHIDs(String[] hids)
      throws BusinessException {
    DataAccessUtils utils = new DataAccessUtils();
    String sql = this.createSqlByIDs(hids);
    String[] bids = utils.query(sql).toOneDimensionStringArray();
    ViewQuery<SaleOrderViewVO> query =
        new ViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class);
    SaleOrderViewVO[] views = query.query(bids);
    return views;
  }

  private String createSqlByIDs(String[] hids) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct(csaleorderbid) from so_saleorder_b where ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sql.append(iq.buildSQL(SaleOrderBVO.CSALEORDERID, hids));
    return sql.toString();
  }

}
