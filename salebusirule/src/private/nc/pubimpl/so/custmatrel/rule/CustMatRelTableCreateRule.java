package nc.pubimpl.so.custmatrel.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.database.TempTable;
import nc.pubitf.so.custmatrel.CustMatRelParaVO;
import nc.vo.pub.JavaType;

/**
 * 临时表的创建
 * 
 * @since 6.0
 * @version 2011-4-15 上午09:53:32
 * @author 祝会征
 */
public class CustMatRelTableCreateRule {
  // private static final String TEMPTABLENAME = "tmp_so_custmatrel";
  private static final String TEMPTABLENAME = "tmp_so_custmatrel2";

  public String createParaTempTable(CustMatRelParaVO[] paras) {
    String[] columns = this.getColumns();
    List<List<Object>> listdata = new ArrayList<List<Object>>();
    for (CustMatRelParaVO para : paras) {
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
        else if(key.equals("pk_custclass")){
          if(para.getAttributeValue("pk_custbaseclass")==null){
            rowdata.add(para.getAttributeValue("pk_custsaleclass"));
          }
          else{
            rowdata.add(para.getAttributeValue("pk_custbaseclass"));
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
        bo.getTempTable(CustMatRelTableCreateRule.TEMPTABLENAME,
            this.getColumns(), this.getColumnTypes(), this.getJavaTypes(),
            listdata);

    return table;
  }

  private String[] getColumns() {
    String marclkey = "pk_materialclass";
    // OracleTempTableCreator.createTempTable：临时表创建过会直接返回,所以不能随便更换临时表字段名称
    // String pk_group = BSContext.getInstance().getGroupID();
    // if (BaseSaleClassUtil.isMarBaseClass(pk_group)) {
    // marclkey = CustMatRelParaVO.PK_MATERIALBASECLASS;
    // }
    // else {
    // marclkey = CustMatRelParaVO.PK_MATERIALSALECLASS;
    // }
    String custclkey = "pk_custclass";
    // if (BaseSaleClassUtil.isCustBaseClass(pk_group)) {
    // custclkey = CustMatRelParaVO.PK_CUSTBASECLASS;
    // }
    // else {
    // custclkey = CustMatRelParaVO.PK_CUSTSALECLASS;
    // }

    String[] columns =
        new String[] {
          CustMatRelParaVO.PARAINDEX, CustMatRelParaVO.PK_ORG,
          CustMatRelParaVO.PK_MATERIAL, marclkey, CustMatRelParaVO.PK_CUSTOMER,
          custclkey
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
