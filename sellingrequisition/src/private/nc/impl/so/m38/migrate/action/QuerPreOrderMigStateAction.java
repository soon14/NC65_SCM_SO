package nc.impl.so.m38.migrate.action;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.so.m38.migrate.constant.PreorderMigState;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;

/**
 * ²éÑ¯ÏúÊÛÔ¤¶©µ¥Ç¨ÒÆ×´Ì¬
 * 
 * @since 6.36
 * @version 2015-05-22 ÏÂÎç13:39:32
 * @author liylr
 */
public class QuerPreOrderMigStateAction {
  public int getPreOrderMigStatus() throws BusinessException {
    String sql = "select fmigstatus from so_m38miglog";
    DataAccessUtils dau = new DataAccessUtils();
    IRowSet rs = dau.query(sql);
    String[] status = rs.toOneDimensionStringArray();
    for (String s : status) {
      if (s != null && s.length() > 0) {
        return Integer.valueOf(s);
      }
    }
    return PreorderMigState.UNMIGRATE;
  }
}
