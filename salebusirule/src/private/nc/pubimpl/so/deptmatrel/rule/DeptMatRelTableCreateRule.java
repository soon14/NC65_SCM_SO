package nc.pubimpl.so.deptmatrel.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.database.TempTable;
import nc.pubitf.so.deptmatrel.DeptMatRelParaVO;
import nc.vo.pub.JavaType;

/**
 * 部门、业务员、物料之间关系定义：临时表的创建
 * 
 * @since 6.0
 * @version 2011-4-15 上午09:53:32
 * @author 祝会征
 */
public class DeptMatRelTableCreateRule {
  // private static final String TEMPTABLENAME = "tmp_so_depmatrel";
  private static final String TEMPTABLENAME = "tmp_so_depmatrel2";

  public String createParaTempTable(DeptMatRelParaVO[] paras) {
    String[] columns = this.getColumns();
    List<List<Object>> listdata = new ArrayList<List<Object>>();
    for (DeptMatRelParaVO para : paras) {
      List<Object> rowdata = new ArrayList<Object>();
      for (String key : columns) {
        if(key.equals("pk_materialclass")){
          if(para.getAttributeValue("pk_materialbaseclass")==null){
            rowdata.add(para.getAttributeValue("pk_materialsaleclass"));
          }
          else{
            rowdata.add(para.getAttributeValue("pk_materialbaseclass"));
          }
        }
        else{
          rowdata.add(para.getAttributeValue(key));
        }
      }
      listdata.add(rowdata);
    }
    TempTable bo = new TempTable();
    String table =
        bo.getTempTable(DeptMatRelTableCreateRule.TEMPTABLENAME,
            this.getColumns(), this.getColumnTypes(), this.getJavaTypes(),
            listdata);

    return table;
  }

  private String[] getColumns() {
    String marclkey = "pk_materialclass";
    // OracleTempTableCreator.createTempTable：临时表创建过会直接返回,所以不能随便更换临时表字段名称
    // String pk_group = BSContext.getInstance().getGroupID();
    // if (BaseSaleClassUtil.isMarBaseClass(pk_group)) {
    // marclkey = DeptMatRelParaVO.PK_MATERIALBASECLASS;
    // }
    // else {
    // marclkey = DeptMatRelParaVO.PK_MATERIALSALECLASS;
    // }
    String[] columns =
        new String[] {
          DeptMatRelParaVO.PARAINDEX, DeptMatRelParaVO.PK_ORG,
          DeptMatRelParaVO.PK_MATERIAL, marclkey, DeptMatRelParaVO.PK_DEPT,
          DeptMatRelParaVO.CEMPLOYEEID
        };
    return columns;
  }

  private String[] getColumnTypes() {
    String[] columnTypes =
        new String[] {
          "integer not null", "varchar(20)", "varchar(20)", "varchar(20)",
          "varchar(20)", "varchar(20)"
        };
    return columnTypes;
  }

  private JavaType[] getJavaTypes() {
    JavaType[] types =
        new JavaType[] {
          JavaType.Integer, JavaType.String, JavaType.String, JavaType.String,
          JavaType.String, JavaType.String
        };
    return types;
  }
}
