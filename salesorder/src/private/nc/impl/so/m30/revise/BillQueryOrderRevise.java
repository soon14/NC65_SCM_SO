package nc.impl.so.m30.revise;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryBVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;
import nc.vo.so.pub.SOTable;

/**
 * 查询订单扩展表数据
 * 
 * @since 6.36
 * @version 2015-5-21 下午1:27:35
 * @author 刘景
 */
public class BillQueryOrderRevise {

  /**
   * 根据销售订单修订VO查询订单扩展表
   * 
   * 注意：修订时无法直接取得扩展表VO,所以需要手动处理。考虑到扩展表的数据在任何状态都可以变化的，所以修订不再单据建表。
   * 
   * @param vos
   * @return
   */
  public SaleOrderHistoryVO[] joinSaleOrderexe(SaleOrderHistoryVO[] vos) {
    Set<String> salebid = new HashSet<String>();
    for (SaleOrderHistoryVO vo : vos) {
      SaleOrderHistoryBVO[] historybvos = vo.getChildrenVO();
      for (SaleOrderHistoryBVO historybvo : historybvos) {
        salebid.add(historybvo.getCsaleorderbid());
      }
    }
    DataAccessUtils dao = new DataAccessUtils();
    SqlBuilder sql = new SqlBuilder();
    createSelect(sql, salebid);
    IRowSet rowset = dao.query(sql.toString());
    Map<String, SaleOrderHistoryBVO> historybvomap = getOrderExeVO(rowset);
    for (SaleOrderHistoryVO vo : vos) {
      SaleOrderHistoryBVO[] historybvos = vo.getChildrenVO();
      for (SaleOrderHistoryBVO historybvo : historybvos) {
        SaleOrderHistoryBVO bvo =
            historybvomap.get(historybvo.getCsaleorderbid());
        if (bvo == null) {
          continue;
        }
        for (String filed : FIELDS) {
          historybvo.setAttributeValue(filed, bvo.getAttributeValue(filed));
        }
      }
    }
    return vos;
  }

  private void createSelect(SqlBuilder sql, Set<String> salebid) {
    sql.append("select ");
    for (String field : FIELDS) {
      sql.append(field);
      sql.append(",");
    }
    sql.deleteLastChar();
    sql.append(" from so_saleorder_exe where ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sql.append(iq.buildSQL(SaleOrderBVO.CSALEORDERBID,
        salebid.toArray(new String[0])));
    sql.append(" and dr=0");
  }

  private Map<String, SaleOrderHistoryBVO> getOrderExeVO(IRowSet rowset) {
    Map<String, SaleOrderHistoryBVO> historybvomap = new HashMap<>();
    SaleOrderHistoryBVO[] bvos = new SaleOrderHistoryBVO[rowset.size()];
    int j = 0;
    while (rowset.next()) {
      bvos[j] = new SaleOrderHistoryBVO();
      int i = 0;
      for (String field : FIELDS) {
        bvos[j].setAttributeValue(field, rowset.getObject(i));
        i++;
      }
      historybvomap.put(bvos[j].getCsaleorderbid(), bvos[j]);
      j++;
    }
    return historybvomap;
  }

  // 扩展表数据
  private static final String[] FIELDS = new String[] {
    SaleOrderHistoryBVO.CSALEORDERBID,
    // 累计发货数量、累计开票数量
    SaleOrderHistoryBVO.NTOTALSENDNUM, SaleOrderHistoryBVO.NTOTALINVOICENUM,
    // 累计出库数量、 累计应发未出库数量
    SaleOrderHistoryBVO.NTOTALOUTNUM, SaleOrderHistoryBVO.NTOTALNOTOUTNUM,
    // 累计签收数量、 累计途损数量
    SaleOrderHistoryBVO.NTOTALSIGNNUM, SaleOrderHistoryBVO.NTRANSLOSSNUM,
    // 累计出库对冲数量、累计暂估应收数量
    SaleOrderHistoryBVO.NTOTALRUSHNUM, SaleOrderHistoryBVO.NTOTALESTARNUM,
    // 累计确认应收数量、累计成本结算数量
    SaleOrderHistoryBVO.NTOTALARNUM, SaleOrderHistoryBVO.NTOTALCOSTNUM,
    // 累计暂估应收金额、 累计确认应收金额
    SaleOrderHistoryBVO.NTOTALESTARMNY, SaleOrderHistoryBVO.NTOTALARMNY,
    // 累计安排委外订单数量、累计安排请购单数量
    SaleOrderHistoryBVO.NARRANGESCORNUM, SaleOrderHistoryBVO.NARRANGEPOAPPNUM,
    // 累计安排调拨订单数量、累计安排调入申请数量
    SaleOrderHistoryBVO.NARRANGETOORNUM, SaleOrderHistoryBVO.NARRANGETOAPPNUM,
    // 累计安排生产订单数量、累计安排采购订单数量
    SaleOrderHistoryBVO.NARRANGEMONUM, SaleOrderHistoryBVO.NARRANGEPONUM,
    // 累计发出商品、 累计退货数量
    SaleOrderHistoryBVO.NTOTALRETURNNUM, SaleOrderHistoryBVO.NTOTALTRADENUM
  };
}
