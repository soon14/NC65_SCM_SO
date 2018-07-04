package nc.vo.so.pub.query;

import java.util.List;

import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.enumeration.BillStatus;

import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

import nc.ui.querytemplate.querytree.IQueryScheme;

public class SOQuerySchemeUtils {

  /**
   * 将业务流程、交易类型、单据状态过滤条件拼进IQueryScheme中
   * 
   * @param qs
   */
  public void appendFixedWhr(IQueryScheme qs, QuerySchemeProcessor qsp) {
    String halias = qsp.getMainTableAlias();
    SqlBuilder whr = new SqlBuilder();

    // 业务流程过滤
    Object obusi = qs.get(SOItemKey.CBIZTYPEID);
    String[] sbusi = null;
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    if (obusi != null) {
      sbusi = (String[]) obusi;
      if (sbusi.length > 0) {
        whr.append(" and (");
        whr.append(iq.buildSQL(halias + "." + SOItemKey.CBIZTYPEID, sbusi));
        whr.append(") ");
      }
    }
    // 交易类型
    Object otrantype = qs.get(SOItemKey.VTRANTYPECODE);
    String[] strantype = null;
    if (otrantype != null) {
      strantype = (String[]) otrantype;
      if (strantype.length > 0) {
        whr.append(" and (");
        whr.append(iq.buildSQL(halias + "." + SOItemKey.VTRANTYPECODE,
            strantype));
        whr.append(") ");
      }
    }

    // 单据状态
    whr.append(" and (");
    whr.append(halias);
    whr.append(".");
    whr.append(SOItemKey.FSTATUSFLAG, BillStatus.AUDIT.getIntValue());
    whr.append(") ");

    qsp.appendWhere(whr.toString());
  }

  /**
   * 封装业务流程条件到IQueryScheme中
   * 
   * @param qs
   * @param busiLst
   */
  public void storeBusitype(IQueryScheme qs, List<String> busiLst) {
    qs.put(SOItemKey.CBIZTYPEID, busiLst.toArray(new String[busiLst.size()]));
  }

  /**
   * 封装交易类型条件到IQueryScheme中
   * 
   * @param qs
   * @param busiLst
   */
  public void storeTranType(IQueryScheme qs, List<String> tranList) {
    qs.put(SOItemKey.VTRANTYPECODE,
        tranList.toArray(new String[tranList.size()]));
  }

}
