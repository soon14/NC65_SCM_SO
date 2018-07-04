package nc.impl.so.m38.migrate.action.pub;

import java.util.List;
import java.util.Set;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.so.m38.migrate.util.TempTableUtils;
import nc.impl.so.m38.migrate.vo.PreOrderBilltypeVO;
import nc.jdbc.framework.SQLParameter;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class QueryBillTypeVOAction {
  public List<PreOrderBilltypeVO> queryBilltypeVOByIds(Set<String> pk_trantypeids){
    StringBuffer cond = new StringBuffer();
    cond.append(TempTableUtils.buildSQL("PK_BILLTYPEID", listToArray(pk_trantypeids)));
    cond.append(" and isnull(dr,0) = 0");
    SQLParameter params = new SQLParameter();
    BaseDAO dao = new BaseDAO();

    List<PreOrderBilltypeVO> list = null;
    try {
      list = (List<PreOrderBilltypeVO>) dao.retrieveByClause(
          PreOrderBilltypeVO.class, cond.toString(), params);
    } catch (DAOException e) {
      ExceptionUtils.wrappException(e);
    }
    return list;
  }
  
  private static String[] listToArray(Set<String> set) {
    String[] items = new String[set.size()];
    int i = 0;
    for(String item : set){
      items[i] = item;
      i++;
    }
    return items;
  }
}
