package nc.pubimpl.so.rule;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.JavaType;
import nc.vo.so.pub.SOTable;

import nc.pubitf.so.m30.ReturnAssignMatchVO;

import nc.impl.pubapp.pattern.database.TempTable;

/**
 * 临时表的创建
 * 
 * @since 6.0
 * @version 2011-4-15 上午09:53:32
 * @author 祝会征
 */
public class MatchParaTableCreateRule {

  public String createParaTempTable(ReturnAssignMatchVO[] paras) {
    String[] columns = this.getColumns();
    List<List<Object>> listdata = new ArrayList<List<Object>>();
    for (ReturnAssignMatchVO para : paras) {
      List<Object> rowdata = new ArrayList<Object>();
      for (String key : columns) {
        rowdata.add(para.getAttributeValue(key));
      }
      listdata.add(rowdata);
    }
    TempTable bo = new TempTable();
    String table =
        bo.getTempTable(SOTable.TMP_SO_RETURNASSIGN.getName(),
            this.getColumns(), this.getColumnTypes(), this.getJavaTypes(),
            listdata);

    return table;
  }

  private String[] getColumns() {
    // String marclkey = null;
    // String pk_group = BSContext.getInstance().getGroupID();
    // if (BaseSaleClassUtil.isMarBaseClass(pk_group)) {
    // marclkey = ReturnAssignMatchVO.PK_MARBASCLASS;
    // }
    // else {
    // marclkey = ReturnAssignMatchVO.PK_MARSALECLASS;
    // }
    // String custclkey = null;
    // if (BaseSaleClassUtil.isCustBaseClass(pk_group)) {
    // custclkey = ReturnAssignMatchVO.PK_CUSTCLASS;
    // }
    // else {
    // custclkey = ReturnAssignMatchVO.PK_CUSTSALECLASS;
    // }
    String[] columns =
        new String[] {
          ReturnAssignMatchVO.PARAINDEX, ReturnAssignMatchVO.PK_SALEORG,
          ReturnAssignMatchVO.PK_GROUP, ReturnAssignMatchVO.PK_PRODUCTLINE,
          ReturnAssignMatchVO.PK_MATERIAL, ReturnAssignMatchVO.PK_MARBASCLASS,
          ReturnAssignMatchVO.PK_MARSALECLASS, ReturnAssignMatchVO.PK_CUSTOMER,
          ReturnAssignMatchVO.PK_CUSTCLASS,
          ReturnAssignMatchVO.PK_CUSTSALECLASS
        };
    return columns;
  }

  private String[] getColumnTypes() {
    String[] columnTypes =
        new String[] {
          "integer not null", "varchar(20)", "varchar(20)", "varchar(20)",
          "varchar(20)", "varchar(20)", "varchar(20)", "varchar(20)",
          "varchar(20)", "varchar(20)"
        };
    return columnTypes;
  }

  private JavaType[] getJavaTypes() {
    JavaType[] types =
        new JavaType[] {
          JavaType.Integer, JavaType.String, JavaType.String, JavaType.String,
          JavaType.String, JavaType.String, JavaType.String, JavaType.String,
          JavaType.String, JavaType.String
        };
    return types;
  }
}
