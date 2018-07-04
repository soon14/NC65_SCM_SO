package nc.impl.so.invoicesummary.temp;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.IAttributeMeta;
import nc.vo.pub.IColumnMeta;
import nc.vo.pub.ITableMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.pubapp.pattern.model.meta.table.Column;
import nc.vo.pubapp.pattern.model.meta.table.Table;
import nc.vo.scmpub.report.SCMReportTempTableUtil;
import nc.vo.so.pub.SOTable;
import nc.vo.so.report.invoicesummary.InvSummaryViewMeta;
import nc.vo.so.report.invoicesummary.InvSummaryViewVO;

import nc.pub.smart.metadata.Field;
import nc.pub.smart.metadata.MetaData;

/**
 * 销售发票执行汇总临时表
 * 
 * @since 6.3
 * @version 2012-10-18 14:01:33
 * @author 梁吉明
 */
public class InvSummaryTempTable {

  /**
   * 创建临时表
   * 
   * @return temptable
   */
  public String createInvSummaryTemptable() {
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
   * 元数据模型
   * 
   * @return MetaData
   */
  public MetaData getMetaData() {
    List<Field> list = new ArrayList<Field>();
    InvSummaryViewVO view = new InvSummaryViewVO();
    IDataViewMeta viewmeta = view.getMetaData();
    for (String key : InvSummaryViewMeta.TMPTABLE_INVKEYS) {
      // VO属性的元数据;根据名字得到属性
      IAttributeMeta attrmeta = viewmeta.getAttribute(key);
      // 数据库字段元数据;得到对应的数据库字段
      IColumnMeta colmeta = attrmeta.getColumn();
      Field field = new Field();
      // 设置数据库字段类型
      field.setDbColumnType(colmeta.getSqlType());
      // 设置数据库字段名
      field.setFldname(colmeta.getName());
      // 设置数据长度
      field.setPrecision(colmeta.getLength());
      // 设置小数位数
      field.setScale(colmeta.getPrecision());
      list.add(field);
    }
    for (String key : InvSummaryViewMeta.EXTEND_STRKEYS) {
      Field field = new Field();
      field.setDbColumnType(Types.VARCHAR);
      field.setFldname(key);
      field.setPrecision(20);
      field.setScale(0);
      list.add(field);
    }
    for (String key : InvSummaryViewMeta.EXTEND_UFKEYS) {
      Field field = new Field();
      field.setDbColumnType(Types.DECIMAL);
      field.setFldname(key);
      field.setPrecision(28);
      field.setScale(8);
      list.add(field);
    }
    Field[] fields = new Field[list.size()];
    list.toArray(fields);
    return new MetaData(fields);
  }

  private ITableMeta getTableMeta(MetaData viewmeta) {
    Table table = new Table(SOTable.TMP_SO_INVSUMMARY.getName());
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
}
