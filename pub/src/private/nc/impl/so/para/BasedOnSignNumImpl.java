package nc.impl.so.para;

import java.util.HashMap;
import java.util.Map;

import nc.vo.ml.MultiLangContext;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

import nc.itf.so.pub.para.IBasedOnSignNumQuery;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.database.DataAccessUtils;

public class BasedOnSignNumImpl implements IBasedOnSignNumQuery {

  @Override
  public Map<String, String> queryBusitypes() throws BusinessException {
    String pk_group = BSContext.getInstance().getGroupID();
    Map<String, String> map = new HashMap<String, String>();
    String sql = this.getSql(pk_group);
    DataAccessUtils utils = new DataAccessUtils();
    try {
      IRowSet rs = utils.query(sql);
      if (null == rs || rs.size() == 0) {
        return null;
      }
      while (rs.next()) {
        String pk_busitype = rs.getString(0);
        // 主语种流程名称
        String businame1 = rs.getString(1);

        String businame2 = rs.getString(2);
        if (PubAppTool.isNull(businame2)) {
          map.put(pk_busitype, businame1);
        }
        else {
          map.put(pk_busitype, businame2);
        }
      }
      return map;
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private String getSql(String pk_group) {
    StringBuffer sql = new StringBuffer();
    sql.append("select pk_busitype, businame,");
    int LangSeq = MultiLangContext.getInstance().getCurrentLangSeq().intValue();
    switch (LangSeq) {
      case 1:
        sql.append(" businame ");
        break;
      case 2:
        sql.append(" businame2 ");
        break;
      case 3:
        sql.append(" businame3 ");
        break;
      case 4:
        sql.append(" businame4 ");
        break;
      case 5:
        sql.append(" businame5 ");
        break;
      case 6:
        sql.append(" businame6 ");
        break;
    }
    sql.append(" from bd_busitype where ");
    sql.append(" pk_busitype IN (");
    sql.append("select distinct(pk_businesstype) as pk_businesstype");
    sql.append(" from pub_billsource ");
    sql.append("where  referbilltype like '4C%'");
    sql.append("and pub_billsource.pk_billtype like '32%')");
    sql.append(" AND (pk_group ='" + pk_group + "' or pk_group ='@@@@') ");
    sql.append(" AND validity = 1");
    return sql.toString();
  }
}
