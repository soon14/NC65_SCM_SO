package nc.vo.so.upgrade;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pub.JavaType;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * @description
 *              销售订单修订用户自定义单据升级处理
 * @scene
 * 
 * @param
 * 
 * 
 * @since 6.5
 * @version 2015-11-21 下午10:26:27
 * @author 刘景
 */
public class HistoryTempletUpgrade {

  /**
   * 63销售修订单据模板 子表METADATAPROPERTY
   */
  private final static String SO_SALEORDER_B = "so.so_saleorder_b";

  public static void Upgrade() {

    // 1.删除V63无用字段
    deleteNotProperty();

    // 2.更新单据模板表头元数据路径
    updataHeadMetaPath();

    // 3.更新单据模板体字段属性头元数据路径
    updataBodyPropertyMeTaPath();

    // 4 更新修订表的单据类型
    updateOrderhistoryBillType();

    // 5.更新打印模板
    updatePrintTemplet();

  }

  private static void updatePrintTemplet() {
    String updatecellsql =
        "update pub_print_cell set VVAR=replace(VVAR,'so_saleorder_b','so_saleorder_history_b') where CTEMPLATEID in(select CTEMPLATEID from pub_print_template where  VNODECODE='40060302' and PK_CORP!='@@@@')  and VVAR is  not null";
    String updatevariablesql =
        "update pub_print_variable set VEXPRESS=replace(VEXPRESS,'so_saleorder_b','so_saleorder_history_b') where CTEMPLATEID  in (select CTEMPLATEID from pub_print_template where  VNODECODE='40060302' and PK_CORP!='@@@@') and VEXPRESS is  not null";
    DataAccessUtils accessutil = new DataAccessUtils();
    accessutil.update(updatecellsql);
    accessutil.update(updatevariablesql);
  }

  private static void updateOrderhistoryBillType() {
    String sql =
        "update  so_orderhistory set vhistrantypecode='30R',chistrantypeid='30R'  where vhistrantypecode is  null and chistrantypeid is null and dr=0";
    DataAccessUtils accessutil = new DataAccessUtils();
    accessutil.update(sql);
  }

  private static void updataHeadMetaPath() {
    DataAccessUtils accessutil = new DataAccessUtils();
    String templet_t1 =
        "update pub_billtemplet_t set METADATACLASS='so.orderhistory' ,METADATAPATH='so.orderhistory'  where TABCODE='billmaker'"
            + getSeletHeadTempletWhere();
    String templet_t2 =
        "update pub_billtemplet_t set METADATACLASS='so.orderhistory', METADATAPATH='so.orderhistory'   where TABCODE='head'"
            + getSeletHeadTempletWhere();
    String templet_t3 =
        "update pub_billtemplet_t set METADATACLASS='so.orderhistory', METADATAPATH='so.orderhistory'   where TABCODE='tail2'"
            + getSeletHeadTempletWhere();
    String templet_t4 =
        "update pub_billtemplet_t set METADATACLASS='so.orderhistory_b' ,METADATAPATH='so.orderhistory_b'  where TABCODE='bodytable1'"
            + getSeletHeadTempletWhere();

    String templet_t5 =
        "update pub_billtemplet_t set METADATACLASS='so.orderhistory_b', METADATAPATH='so.orderhistory_b'  where TABCODE='bodytable2'"
            + getSeletHeadTempletWhere();

    String templet_t6 =
        "update pub_billtemplet_t set METADATACLASS='so.orderhistory_b', METADATAPATH='so.orderhistory_b'  where TABCODE='bodytable3'"
            + getSeletHeadTempletWhere();

    String templet_t7 =
        "update pub_billtemplet_t set METADATACLASS='so.orderhistory_b', METADATAPATH='so.orderhistory_b'  where TABCODE='bodytable4'"
            + getSeletHeadTempletWhere();

    accessutil.update(templet_t1);
    accessutil.update(templet_t2);
    accessutil.update(templet_t3);
    accessutil.update(templet_t4);
    accessutil.update(templet_t5);
    accessutil.update(templet_t6);
    accessutil.update(templet_t7);

    String templet =
        "update pub_billtemplet set metadataclass='so.orderhistory' ,nodecode='40060302',pk_billtypecode='30R' where 1=1"
            + getSeletHeadTempletWhere();

    accessutil.update(templet);
  }

  private static void updataBodyPropertyMeTaPath() {
    HistoryBillTempletVO[] billtempletvos = getBillTempletBodyVO();
    if (billtempletvos == null || billtempletvos.length == 0) {
      return;
    }
    List<List<Object>> data = new ArrayList<List<Object>>();
    for (HistoryBillTempletVO billtempletvo : billtempletvos) {
      String tmetadataproperty = billtempletvo.getMetadataproperty();
      String metadatapath = billtempletvo.getMetadatapath();

      List<Object> obj = new ArrayList<Object>();
      if (tmetadataproperty.contains(SO_SALEORDER_B)) {
        obj.add("so.orderhistory_b." + metadatapath);
      }
      else {
        obj.add("so.orderhistory." + metadatapath);
      }
      obj.add(billtempletvo.getPk_billtemplet_b());

      data.add(obj);
    }

    String updatesql =
        "update pub_billtemplet_b set metadataproperty=? where pk_billtemplet_b=?";
    JavaType[] types = new JavaType[] {
      JavaType.String, JavaType.String
    };

    DataAccessUtils accessutil = new DataAccessUtils();
    accessutil.update(updatesql, types, data);

  }

  private static void deleteNotProperty() {
    DataAccessUtils accessutil = new DataAccessUtils();
    SqlBuilder deletesql = new SqlBuilder();
    deletesql.append("delete  pub_billtemplet_b where metadataproperty in");
    deletesql
        .append("('so.so_saleorder_b.ntotalpaymny','so.so_saleorder_b.nreqrsnum','so.so_saleorder.ntotalmny','so.so_saleorder.nthisreceivemny')");
    deletesql.append(getSeletHeadTempletWhere());

    accessutil.update(deletesql.toString());
  }

  private static String getSeletHeadTempletWhere() {
    SqlBuilder headwhere = new SqlBuilder();
    headwhere
        .append("  and  pk_billtemplet in (select  pk_billtemplet from pub_billtemplet where ");
    headwhere
        .append("pk_billtypecode='30revise' and metadataclass='so.so_saleorder')");
    return headwhere.toString();
  }

  private static HistoryBillTempletVO[] getBillTempletBodyVO() {
    DataAccessUtils accessutil = new DataAccessUtils();
    SqlBuilder sql = new SqlBuilder();
    sql.append("select pk_billtemplet_b,metadatapath,metadataproperty from  pub_billtemplet_b ");
    sql.append("where  pk_billtemplet in (");
    sql.append("select pk_billtemplet from pub_billtemplet where ");
    sql.append("nodecode='40060302' and pk_billtypecode='30R' ");
    sql.append(")");
    sql.append("and metadataproperty is not null and metadatapath is not null ");
    sql.append("and metadataproperty like 'so.so_saleorder%'");
    IRowSet rs = accessutil.query(sql.toString());
    List<HistoryBillTempletVO> listTempletVOs =
        new ArrayList<HistoryBillTempletVO>();
    while (rs.next()) {
      HistoryBillTempletVO templetvo = new HistoryBillTempletVO();
      templetvo.setPk_billtemplet_b(rs.getString(0));
      templetvo.setMetadatapath(rs.getString(1));
      templetvo.setMetadataproperty(rs.getString(2));
      listTempletVOs.add(templetvo);
    }

    return listTempletVOs.toArray(new HistoryBillTempletVO[0]);
  }

  private static class HistoryBillTempletVO {

    public String getPk_billtemplet_b() {
      return pk_billtemplet_b;
    }

    public void setPk_billtemplet_b(String pk_billtemplet_b) {
      this.pk_billtemplet_b = pk_billtemplet_b;
    }

    public String getMetadataproperty() {
      return metadataproperty;
    }

    public void setMetadataproperty(String metadataproperty) {
      this.metadataproperty = metadataproperty;
    }

    public String getMetadatapath() {
      return metadatapath;
    }

    public void setMetadatapath(String metadatapath) {
      this.metadatapath = metadatapath;
    }

    private String pk_billtemplet_b;

    private String metadataproperty;

    private String metadatapath;

  }

}
