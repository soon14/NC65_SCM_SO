package nc.pubimpl.so.m33.so.m30;

import java.util.HashMap;
import java.util.Map;

import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvViewVO;
import nc.vo.so.pub.SOTable;
import nc.vo.trade.checkrule.VOChecker;

import nc.pubitf.so.m33.so.m30.ISquare32QryFor30SqEnd;
import nc.pubitf.so.m33.so.m30.RetVOFor30;

import nc.bs.so.m33.maintain.m32.query.QuerySquare32VOBP;

import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 销售发票结算单为订单关闭提供服务接口实现类
 * 
 * @since 6.1
 * @version 2012-11-29 11:09:14
 * @author 冯加彬
 */
public class Square32QryFor30SqEndImpl implements ISquare32QryFor30SqEnd {

  @Override
  public RetVOFor30[] querySqEndByOrdBID(String[] ordBids) {
    SquareInvViewVO[] vvos = this.queryViewVOByOrdBID(ordBids);
    RetVOFor30[] retvos = null;
    Map<String, RetVOFor30> map = new HashMap<String, RetVOFor30>();
    if (!VOChecker.isEmpty(vvos)) {
      for (SquareInvViewVO svvo : vvos) {
        SquareInvBVO bvo = svvo.getItem();
        RetVOFor30 rvo = map.get(bvo.getCfirstbid());
        if (rvo == null) {
          rvo = new RetVOFor30();
          map.put(bvo.getCfirstbid(), rvo);
        }
        rvo.setBid(bvo.getCfirstbid());
        rvo.setBsquarearfinish(bvo.getBsquarearfinish());
        rvo.setBsquareiafinish(bvo.getBsquareiafinish());
      }
    }
    if (map.size() > 0) {
      retvos = map.values().toArray(new RetVOFor30[map.size()]);
    }
    return retvos;
  }

  @Override
  public SquareInvViewVO[] queryViewVOByOrdBID(String[] ordBids) {
    QuerySquare32VOBP qry = new QuerySquare32VOBP();
    // 查询表体id
    StringBuilder hsql =
        new StringBuilder("select csalesquarebid from so_squareinv_b ");
    hsql.append("where so_squareinv_b.dr = 0 and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String where = iq.buildSQL(SquareInvBVO.CFIRSTBID, ordBids);
    hsql.append(where);
    String[] bids = qry.queryIDsFromSql(hsql.toString());
    SquareInvViewVO[] ret = null;
    if (!VOChecker.isEmpty(bids)) {
      ret = qry.querySquareInvViewVOByBID(bids);
    }
    return ret;
  }

}
