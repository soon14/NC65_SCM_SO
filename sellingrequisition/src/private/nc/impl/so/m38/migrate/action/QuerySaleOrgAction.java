package nc.impl.so.m38.migrate.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

public class QuerySaleOrgAction {
  public Map<String, List<String>> getSaleOrgNotElectSaleOrgs(){
      SqlBuilder sqlbuilder = new SqlBuilder();
      sqlbuilder.append("select a.code as orgcode, b.code as groupcode from org_salesorg a inner join org_group b on a.pk_group = b.pk_group and b.dr = 0 where a.pk_salesorg in (select distinct pk_org from SO_PREORDER where dr = 0) and a.iselectsale = 'N' ");
      DataAccessUtils dau = new DataAccessUtils();
      IRowSet rs = dau.query(sqlbuilder.toString());
      String[][] org_groups = rs.toTwoDimensionStringArray();
      Map<String, List<String>> groupOrg_CodeMap = new HashMap<String, List<String>>();
      for (String[] s : org_groups) {
        String orgCode = s[0];
        String groupCode = s[1];
        List<String> list = null;
        if(groupOrg_CodeMap.containsKey(groupCode)){
          list = groupOrg_CodeMap.get(groupCode);
        }else{
          list = new ArrayList<String>();
          groupOrg_CodeMap.put(groupCode, list);
        }
        list.add(orgCode);
      }
      return groupOrg_CodeMap;
  }
}
