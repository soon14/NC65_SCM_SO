package nc.impl.so.multipleprofit.temp;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.ITableMeta;
import nc.vo.pubapp.pattern.model.meta.table.Column;
import nc.vo.pubapp.pattern.model.meta.table.Table;
import nc.vo.scmpub.report.SCMReportTempTableUtil;
import nc.vo.so.pub.SOTable;
import nc.vo.so.report.multipleprofit.MultipleProfitViewMeta;

import nc.pub.smart.metadata.Field;
import nc.pub.smart.metadata.MetaData;

/**
 * 综合毛利分析临时表
 * 
 * @since 6.3
 * @version 2012-10-18 14:27:47
 * @author zhangkai4
 */
public class MultipleProfitTempTable {

  /**
   * 创建临时表
   * 
   * @return 临时表的表名
   */
  public String createMulProfitTemptable() {
    SCMReportTempTableUtil tmptableutil = new SCMReportTempTableUtil();

    // 1.获得元数据
    MetaData metadata = this.getMetaData();

    // 2.获得临时表字段
    ITableMeta tablemeta = this.getTableMeta(metadata);

    // 3.创建临时表
    String temptable = tmptableutil.createTempTable(tablemeta);
    return temptable;
  }

  /**
   * 获得临时表字段
   * 
   * @param metadata
   * @return
   */
  private ITableMeta getTableMeta(MetaData viewmeta) {
    Table table = new Table(SOTable.TMP_SO_MULTIPLEPROFIT.getName());
    for (Field field : viewmeta.getFields()) {
      Column column = new Column(table, field.getFldname());
      // 设置长度
      column.setLength(field.getPrecision());
      // 设置精度
      column.setPrecision(field.getScale());
      // 设置字段类型
      column.setSqlType(field.getDbColumnType());
      // 设置数据库字段可以为空
      column.setNullable(true);
      // 设置数据库字段元数据实现类
      column.setLabel(field.getFldname());
      // 增加数据库字段到数据库表中
      table.add(column);
    }
    return table;
  }

  /**
   * 元数据模型
   * 
   * @return MetaData
   */
  public MetaData getMetaData() {
    List<Field> list = new ArrayList<Field>();
    for (String key : MultipleProfitViewMeta.MULPROFIT_STRKEYS) {
      Field field = new Field();
      // 设置数据库字段类型
      field.setDbColumnType(Types.VARCHAR);
      // 设置数据库字段名
      field.setFldname(key);
      // 设置数据精度
      field.setPrecision(20);
      // 设置小数位数
      field.setScale(0);
      list.add(field);
    }
    for (String key : MultipleProfitViewMeta.MULPROFIT_UFDKEYS) {
      Field field = new Field();
      // 设置数据库字段类型
      field.setDbColumnType(Types.DECIMAL);
      // 设置数据库字段名
      field.setFldname(key);
      // 设置数据精度
      field.setPrecision(28);
      // 设置小数位数
      field.setScale(8);
      list.add(field);
    }
    return new MetaData(list.toArray(new Field[list.size()]));
  }
}
