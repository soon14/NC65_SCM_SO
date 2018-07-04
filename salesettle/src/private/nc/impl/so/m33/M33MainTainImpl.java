package nc.impl.so.m33;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.pub.SOTable;

import nc.itf.so.m33.IM33MainTain;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 结算单来源信息获取类
 * 
 * @since 6.1
 * @version 2012-11-29 10:16:00
 * @author 冯加彬
 */
public class M33MainTainImpl implements IM33MainTain {

  @Override
  public Map<String, String> getSrcInfo(String[] bids) throws BusinessException {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select csalesquaredid,csquarebillid,csquarebillbid ");
    sql.append(" from ");
    sql.append("so_squareout_d");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sql.append(" where ");
    sql.append(iq.buildSQL(SquareOutDetailVO.CSALESQUAREDID, bids));

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql.toString());
    Map<String, String> map = new HashMap<String, String>();
    while (rowset.next()) {
      map.put(rowset.getString(0),
          rowset.getString(1) + "," + rowset.getString(2));
    }
    return map;

  }
}
