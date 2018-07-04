package nc.impl.so.m38.migrate.constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * 迁移后需要将下游单据（销售订单，销售出库单，销售发货单）中的VFIRSTTRANTYPE更改为新的单据类型PK
 * 新旧PK的映射关系：Map<String, String> oldNewTranTypeIdMap
 * @author 李彦龙
 *
 */
public class SQLCenter {

  /**
   * 返回所有需要更执行的SQL语句
   * @return
   */
  public List<String> getUpdateSqls(Map<String, String> oldNewTranTypeIdMap) {
    List<String> list = new ArrayList<String>();
    list.addAll(updateFirstTrantype(oldNewTranTypeIdMap));
    list.addAll(updateFirstType());
    return list;
  }

  private List<String> updateFirstType() {
  List<String> list = new ArrayList<String>(4);
  //销售订单的来源单据类型
    String saleorder_vsrctype = "update so_saleorder_b set VSRCTYPE='"+OPC_PreData.ECC1_CODE+"' where VSRCTYPE='38'";
  //销售订单的源头单据类型
    String saleorder_vfirsttype = "update so_saleorder_b set VFIRSTTYPE='"+OPC_PreData.ECC1_CODE+"' where VFIRSTTYPE='38'";
  //销售出库的源头单据类型
//    String saleout_vfirsttype = "update ic_saleout_b set cfirsttype='ECC1' where cfirsttype='38'";
  //销售发货单的源头单据类型
    String delivery_vfirsttype = "update so_delivery_b set vfirsttype='ECC1' where vfirsttype='38'";
    
    list.add(saleorder_vsrctype);
    list.add(saleorder_vfirsttype);
//    list.add(saleout_vfirsttype);
    list.add(delivery_vfirsttype);
    return list;
  }

  private List<String> updateFirstTrantype(Map<String, String> oldNewTranTypeIdMap) {
    List<String> list = new ArrayList<String>();
    for (Entry<String, String> entry : oldNewTranTypeIdMap.entrySet()) {
      String update_saleorder_b = getSqls(entry, "so_saleorder_b");
//      String update_saleout_b = getSaleOut_b(entry);
      String update_delivery_b = getSqls(entry, "so_delivery_b");
      list.add(update_saleorder_b);
//      list.add(update_saleout_b);
      list.add(update_delivery_b);
    }
    return list;
  }

//  private String getSaleOut_b(Entry<String, String> entry) {
//    StringBuffer sql = new StringBuffer();
//    sql.append("update ic_saleout_b set CFIRSTTRANSTYPE = '");
//    sql.append(entry.getValue());
//    sql.append("' where CFIRSTTYPE='38' and CFIRSTTRANSTYPE = '");
//    sql.append(entry.getKey());
//    sql.append("'");
//    return sql.toString();
//  }

  private String getSqls(Entry<String, String> entry, String tableName) {
    StringBuffer sql = new StringBuffer();
    sql.append("update ");
    sql.append(tableName);
    sql.append(" set VFIRSTTRANTYPE = '");
    sql.append(entry.getValue());
    sql.append("' where VFIRSTTYPE='38' and VFIRSTTRANTYPE = '");
    sql.append(entry.getKey());
    sql.append("'");
    return sql.toString();
  }
}
