package nc.pubimpl.so.m30.mm.mmpac;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.pubitf.so.m30.mm.mmpac.ISaleOrderForMMPac;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30.entity.SaleOrderViewVO;

public class SaleOrderForMMPacImpl implements ISaleOrderForMMPac {

  @Override
  public SaleOrderViewVO[] querySaleOrderViews(String fromwheresql) {

    SqlBuilder sql = new SqlBuilder();
    sql.append("select so_saleorder_b.csaleorderbid ");
    sql.append(fromwheresql);

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet set = utils.query(sql.toString());
    if (set.size() == 0) {
      return new SaleOrderViewVO[0];
    }
    String[] ids = set.toOneDimensionStringArray();
    ViewQuery<SaleOrderViewVO> query =
        new ViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class);
    SaleOrderViewVO[] bills = query.query(ids);

    return bills;
  }
}
