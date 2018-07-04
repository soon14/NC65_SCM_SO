package nc.vo.so.upgrade;

import nc.bs.sm.accountmanage.IUpdateAccount;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.Log;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.StringUtils;

/**
 * 销售管理V633升级v65处理类1
 * 
 * @since 6.36
 * @version 2015-3-17 上午11:20:44
 * @author 刘景
 */
public class SOUpgrade633ToV65 extends SOUpgradeToV633 implements
    IUpdateAccount {

  @Override
  public void doBeforeUpdateData(String oldVersion, String newVersion)
      throws Exception {
    if (StringUtils.isEmpty(oldVersion) || StringUtils.isEmpty(newVersion)
        || newVersion.equals(oldVersion)) {
      // 旧版本、新版本不知道的话，无法进行数据升级
      return;
    }
    if (oldVersion.startsWith("6.3") && newVersion.startsWith("6.5")) {
      super.doBeforeUpdateData(oldVersion, newVersion);
    }
    
    updateTable();
    
    // 升级前删除违规主键交易类型
    updateTrantype();

    // 2.VO转换规则违规主键处理升级
    VOChangeUpGrade.upGrade(PK_VOCHANGE, PK_VOCHANGE_B, PK_VOCHANGE_S);

    // 销售订单修订单据模板升级处理（订单修订单据模板的nodecode和pk_billtypecode改变了，造成平台无法处理自定义单据模板升级）
    HistoryTempletUpgrade.Upgrade();
  
  }
  
 

  @Override
  public void doAfterUpdateData(String oldVersion, String newVersion)
      throws Exception {
    if (StringUtils.isEmpty(oldVersion) || StringUtils.isEmpty(newVersion)
        || newVersion.equals(oldVersion)) {
      // 旧版本、新版本不知道的话，无法进行数据升级
      return;
    }
    if (oldVersion.startsWith("6.3") && newVersion.startsWith("6.5")) {
      super.doAfterUpdateData(oldVersion, newVersion);
    }

    // 1.为空值填充默认值
    fullDefaulValue();

    // 2.禁用预订单节点
    enablePreorderNode();

    // 3.删除重复的发货单单据类型中注册的BillActionClassRunListener
    updateBillTypeForDelivery();

    // 4.MP消息模板删除旧版本没用的pub_msgtemp_type中的数据
    updateOldMsgTempType();
  }
  
  private void updateTable () {
    //更新销售订单元数据（修订增加扩展表导致）
    String sql="update md_column set name='csaleorderbid'  where id = 'so_saleorder_exe@@PK@@' and name='corderhistorybid'";
    DataAccessUtils accessutil = new DataAccessUtils();
    accessutil.update(sql);
    
    
    try {
      // 删除销售订单执行汇总临时表
      String droptable = "drop table tmp_so_ordersummary";
      accessutil.update(droptable);
    }
    catch (Exception ex) {
      Log.error(ex);
    }
  }
  

  private void updateOldMsgTempType() {
    String[] oldid =
        new String[] {
          "1001Z81000000000KMJA", "1001Z81000000000KMJC",
          "1001Z81000000000KMJO", "1001Z81000000000KMJP",
          "1001Z81000000000KMJU"
        };
    DataAccessUtils accessutil = new DataAccessUtils();
    SqlBuilder sql = new SqlBuilder();
    sql.append("delete pub_msgtemp_type where ");
    sql.append("pk_temptype", oldid);
    accessutil.update(sql.toString());
  }

  private void updateBillTypeForDelivery() {
    DataAccessUtils accessutil = new DataAccessUtils();
    SqlBuilder sql = new SqlBuilder();
    sql.append("delete bd_billtype2 where ");
    sql.append("pk_billtypeid", "4331");
    sql.append(" and ");
    sql.append("classname",
        "nc.impl.pubapp.bill.rewrite.BillActionClassRunListener");
    accessutil.update(sql.toString());
  }

  @Override
  public void doBeforeUpdateDB(String oldVersion, String newVersion)
      throws Exception {
    //
  }

  private void enablePreorderNode() throws Exception {
    try {
      // 预订单功能节点禁用，可用在功能节点注册启用（孙宝钱、刘达）
      DataAccessUtils accessutil = new DataAccessUtils();
      accessutil
          .update("update sm_funcregister set isenable='N' where funcode in('40060201','40060202','40060203')");
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  private void fullDefaulValue() throws Exception {
    try {
      DataAccessUtils accessutil = new DataAccessUtils();
      // 销售订单默认值填充
      accessutil
          .update("update so_saleorder_b  set cprcpromottypeid='~' where cprcpromottypeid is null");
      accessutil
          .update("update so_saleorder_b  set blrgcashflag='N' where blrgcashflag is null");
      // 交易类型默认值填充
      accessutil
          .update("update so_m30trantype  set blrgcashflag='N' where blrgcashflag is null");
      accessutil
          .update("update so_m30trantype  set bcopyiseprice='N' where bcopyiseprice is null");
      accessutil
          .update("update so_m30trantype  set naccpricerule=4 where naccpricerule is null");

      // so_custmatrel_b冗销售余组织填充默认值
      accessutil.update(getupdateCustmatrel_bSQL());

      // 更新用户自定义单据模板公式
      accessutil.update(getupdatebilltemplet30_SQL());
      accessutil.update(getupdatebilltemplet30R_SQL());

      // 更新修订结算关闭公式
      accessutil
          .update(" update pub_billtemplet_b  set loadformula='iif(bbarsettleflag && bbcostsettleflag,Y,N)' where pk_billtemplet in (select pk_billtemplet from pub_billtemplet  where  pk_billtypecode='30R') and itemkey='bbsettleflag'  and loadformula is null and dr=0");
      accessutil.update(getupdatebilltemplet4006080201_SQL());

      // 删除买赠设置多余查询模版
      accessutil
          .update("delete from  pub_systemplate_base where pk_systemplate='1001Z81000000000BYK1'");

    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  private String getupdatebilltemplet30_SQL() {
    SqlBuilder sql = new SqlBuilder();
    sql.append("update pub_billtemplet_b  set editformula='dsenddate->date();dreceivedate->date();'");
    sql.append(" where pk_billtemplet in ");
    sql.append(" (select pk_billtemplet from pub_billtemplet  where  metadataclass='so.so_saleorder' and pk_billtypecode='30')");
    sql.append(" and itemkey='cmaterialvid'  and editformula is null and dr=0");
    return sql.toString();
  }

  private String getupdatebilltemplet30R_SQL() {
    SqlBuilder sql = new SqlBuilder();
    sql.append("update pub_billtemplet_b  set editformula='dsenddate->date();dreceivedate->date();' where pk_billtemplet in (select pk_billtemplet from pub_billtemplet  where  pk_billtypecode='30R') and itemkey='cmaterialvid'  and editformula is null and dr=0");
    return sql.toString();
  }

  private String getupdatebilltemplet4006080201_SQL() {
    SqlBuilder sql = new SqlBuilder();
    sql.append("update pub_billtemplet_b  set editformula='dsenddate->date();dreceivedate->date();' where pk_billtemplet in (select pk_billtemplet from pub_billtemplet  where  pk_billtypecode='4006080201') and itemkey='cmaterialvid'  and editformula is null and dr=0");
    return sql.toString();
  }

  private String getupdateCustmatrel_bSQL() {
    SqlBuilder sql = new SqlBuilder();
    sql.append("update so_custmatrel_b set pk_org=");
    sql.append("(select pk_org from so_custmatrel where so_custmatrel.pk_custmatrel=so_custmatrel_b.pk_custmatrel) ");
    sql.append("where exists(select pk_custmatrel from so_custmatrel where so_custmatrel.pk_custmatrel=so_custmatrel_b.pk_custmatrel)");
    return sql.toString();
  }

  private void updateTrantype() throws Exception {
    String billtypesql =
        "delete from bd_billtype where PK_BILLTYPECODE='30-06' and PK_BILLTYPEID='1001E310000000018MHH'";
    String trantypesql =
        "delete from so_m30trantype where VTRANTYPECODE='30-06'and CTRANTYPEID='1001E310000000018MHH'";
    try {
      DataAccessUtils accessutil = new DataAccessUtils();
      accessutil.update(billtypesql);
      accessutil.update(trantypesql);

    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  /**
   * pub_vochange违规主键
   */
  private final static String[] PK_VOCHANGE = {
    "0001ZU10000000002ELD", "1001ZT1000000000BDQ8", "1002ZF1000000000KZEJ"
  };

  /**
   * pub_vochange_b违规主键
   */
  private final static String[] PK_VOCHANGE_B = {
    "1001ZF1000000000GM3W", "1001ZF1000000000GM3X", "1001ZF1000000000GM3Y",
    "1001ZF1000000000GM3Z", "1001ZF1000000000GM40", "1001ZF1000000000GM41",
    "1001ZF1000000000GM42", "1001ZF1000000000GM43", "1001ZF1000000000GM44",
    "1001ZF1000000000GM45", "1001ZF1000000000GM46", "1001ZF1000000000GM47",
    "1001ZF1000000000GM48", "1001ZF1000000000GM49", "1001ZF1000000000GM4A",
    "1001ZF1000000000GM4B", "1001ZF1000000000GM4C", "1001ZF1000000000GM4D",
    "1001ZF1000000000GM4E", "1001ZF1000000000GM4F", "1001ZF1000000000GM4G",
    "1001ZF1000000000GM4H", "1001ZF1000000000GM4I", "1001ZF1000000000GM4J",
    "1001ZF1000000000GM4K", "1001ZF1000000000GM4L", "1001ZF1000000000GM4M",
    "1001ZF1000000000GM4N", "1001ZF1000000000GM4O", "1001ZF1000000000GM4P",
    "1001ZF1000000000GM4Q", "1001ZF1000000000GM4R", "1001ZF1000000000GM4S",
    "1001ZF1000000000GM4T", "1001ZF1000000000GM4U", "1001ZF1000000000GM4V",
    "1001ZF1000000000GM4W", "1001ZF1000000000GM4X", "1001ZF1000000000GM4Y",
    "1001ZF1000000000GM4Z", "1001ZF1000000000GM50", "1001ZF1000000000GM51",
    "1001ZF1000000000GM52", "1001ZF1000000000GM53", "1001ZF1000000000GM54",
    "1001ZF1000000000GM55", "1001ZF1000000000GM56", "1001ZF1000000000GM57",
    "1001ZF1000000000GM58", "1001ZF1000000000GM59", "1001ZF1000000000GM5A",
    "1001ZF1000000000GM5B", "1001ZF1000000000GM5C", "1001ZF1000000000GM5D",
    "1001ZF1000000000GM5E", "1001ZF1000000000GM5F", "1001ZF1000000000GM5G",
    "1001ZF1000000000GM5H", "1001ZF1000000000GM5I", "1001ZF1000000000GM5J",
    "1001ZF1000000000GM5K", "1001ZF1000000000GM5L", "1001ZF1000000000GM5M",
    "1001ZF1000000000GM5N", "1001ZF1000000000GM5O", "1001ZF1000000000GM5P",
    "1001ZF1000000000GM5Q", "1001ZF1000000000GM5R", "1001ZF1000000000GM5S",
    "1001ZF1000000000GM5T", "1001ZF1000000000GM5U", "1001ZF1000000000GM5V",
    "1001ZF1000000000GM5W", "1001ZF1000000000GM5X", "1001ZF1000000000GM5Y",
    "1001ZF1000000000GM5Z", "1001ZF1000000000GM60", "1001ZF1000000000GM61",
    "1001ZF1000000000GM62", "1001ZF1000000000GM63", "1001ZF1000000000GM64",
    "1001ZF1000000000GM65", "1001ZF1000000000GM66", "1001ZF1000000000GM67",
    "1001ZF1000000000GM68", "1001ZF1000000000GM69", "1001ZF1000000000GM6A",
    "1001ZF1000000000GM6B", "1001ZF1000000000GM6C", "1001ZF1000000000GM6D",
    "1001ZF1000000000GM6E", "1001ZF1000000000GM6F", "1001ZF1000000000GM6G",
    "1001ZF1000000000GM6H", "1001ZF1000000000GM6I", "1001ZF1000000000GM6J",
    "1001ZF1000000000GM6K", "1001ZF1000000000GM6L", "1001ZF1000000000GM6M",
    "1001ZF1000000000GM6N", "1001ZF1000000000GM6O", "1001ZF1000000000GM6P",
    "1001ZF1000000000GM6Q", "1001ZF1000000000GM6R", "1001ZF1000000000GM6S",
    "1001ZF1000000000GM6T", "1001ZF1000000000GM6U", "1001ZF1000000000GM6V",
    "1001ZF1000000000GM6W", "1001ZF1000000000GM6X", "1001ZF1000000000GM6Y",
    "1001ZF1000000000GM6Z", "1001ZF1000000000GM70", "1001ZF1000000000GM71",
    "1001ZF1000000000GM72", "1001ZF1000000000GM73", "1001ZF1000000000GM74",
    "1001ZF1000000000GM75", "1001ZF1000000000GM76", "1001ZF1000000000GM77",
    "1001ZF1000000000GM78", "1001ZF1000000000GM79", "1001ZF1000000000GM7A",
    "1001ZF1000000000GM7B", "1001ZF1000000000GM7C", "1001ZF1000000000GM7D",
    "1001ZF1000000000GM7E", "1001ZF1000000000GM7F", "1001ZF1000000000GM7G",
    "1001ZF1000000000GM7H", "1001ZF1000000000GM7I", "1001ZF1000000000GM7J",
    "1001ZF1000000000GM7K", "1001ZF1000000000GM7L", "1001ZF1000000000GM7M",
    "1001ZF1000000000GM7N", "1001ZF1000000000GM7O", "1001ZF1000000000GM7P",
    "1001ZF1000000000GM7Q", "1001ZF1000000000GM7R", "1001ZF1000000000GM7S",
    "1001ZF1000000000GM7T", "1001ZF1000000000GM7U", "1001ZF1000000000GM7V",
    "1001ZF1000000000GM7W", "1001ZF1000000000GM7X", "1001ZF1000000000GM7Y",
    "1001ZF1000000000GM7Z", "1001ZF1000000000GM80", "1001ZF1000000000GM81",
    "1001ZF1000000000GM82", "1001ZF1000000000GM83", "1001ZF1000000000GM84",
    "1001ZF1000000000GM85", "1001ZF1000000000GM86", "1001ZF1000000000GM87",
    "1001ZF1000000000GM88", "1001ZF1000000000GM89", "1001ZF1000000000GM8A",
    "1001ZF1000000000GM8B", "1001ZF1000000000GM8C", "1001ZF1000000000GM8D",
    "1001ZF1000000000GM8E", "1001ZF1000000000GM8F", "1001ZF1000000000GM8G",
    "1001ZF1000000000GM8H", "1001ZF1000000000GM8I", "1001ZF1000000000GM8J",
    "1001ZF1000000000GM8K", "1001ZF1000000000GM8L", "1001ZF1000000000GM8M",
    "1001ZF1000000000GM8N", "1001ZF1000000000GM8O", "1001ZF1000000000GM8P",
    "1001ZF1000000000GM8Q", "1001ZF1000000000GM8R", "1001ZF1000000000GM8S",
    "1001ZF1000000000GM8T", "1001ZF1000000000GM8U", "1001ZF1000000000GM8V",
    "1001ZF1000000000GM8W", "1001ZF1000000000GM8X",

    "1001ZT1000000001LZYR", "1001ZT1000000001LZYS", "1001ZT1000000001LZYT",
    "1001ZT1000000001LZYU", "1001ZT1000000001LZYV", "1001ZT1000000001LZYW",
    "1001ZT1000000001LZYX", "1001ZT1000000001LZYY", "1001ZT1000000001LZYZ",
    "1001ZT1000000001LZZ0", "1001ZT1000000001LZZ1", "1001ZT1000000001LZZ2",
    "1001ZT1000000001LZZ3", "1001ZT1000000001LZZ4", "1001ZT1000000001LZZ5",
    "1001ZT1000000001LZZ6", "1001ZT1000000001LZZ7", "1001ZT1000000001LZZ8",
    "1001ZT1000000001LZZ9", "1001ZT1000000001LZZA", "1001ZT1000000001LZZB",
    "1001ZT1000000001LZZC", "1001ZT1000000001LZZD", "1001ZT1000000001LZZE",
    "1001ZT1000000001LZZF", "1001ZT1000000001LZZG", "1001ZT1000000001LZZH",
    "1001ZT1000000001LZZI", "1001ZT1000000001LZZJ", "1001ZT1000000001LZZK",
    "1001ZT1000000001LZZL", "1001ZT1000000001LZZM", "1001ZT1000000001LZZN",
    "1001ZT1000000001LZZO", "1001ZT1000000001LZZP", "1001ZT1000000001LZZQ",
    "1001ZT1000000001LZZR", "1001ZT1000000001LZZS", "1001ZT1000000001LZZT",
    "1001ZT1000000001LZZU", "1001ZT1000000001LZZV", "1001ZT1000000001LZZW",
    "1001ZT1000000001LZZX", "1001ZT1000000001LZZY", "1001ZT1000000001LZZZ",
    "1001ZT1000000001M000", "1001ZT1000000001M001", "1001ZT1000000001M002",
    "1001ZT1000000001M003", "1001ZT1000000001M004", "1001ZT1000000001M005",
    "1001ZT1000000001M006", "1001ZT1000000001M007", "1001ZT1000000001M008",
    "1001ZT1000000001M009", "1001ZT1000000001M00A", "1001ZT1000000001M00B",
    "1001ZT1000000001M00C", "1001ZT1000000001M00D", "1001ZT1000000001M00E",
    "1001ZT1000000001M00F", "1001ZT1000000001M00G", "1001ZT1000000001M00H",
    "1001ZT1000000001M00I", "1001ZT1000000001M00J", "1001ZT1000000001M00K",
    "1001ZT1000000001M00L", "1001ZT1000000001M00M", "1001ZT1000000001M00N",
    "1001ZT1000000001M00O", "1001ZT1000000001M00P", "1001ZT1000000001M00Q",
    "1001ZT1000000001M00R", "1001ZT1000000001M00S", "1001ZT1000000001M00T",
    "1001ZT1000000001M00U", "1001ZT1000000001M00V", "1001ZT1000000001M00W",
    "1001ZT1000000001M00X", "1001ZT1000000001M00Y", "1001ZT1000000001M00Z",
    "1001ZT1000000001M010", "1001ZT1000000001M011", "1001ZT1000000001M012",
    "1001ZT1000000001M013", "1001ZT1000000001M014", "1001ZT1000000001M015",
    "1001ZT1000000001M016", "1001ZT1000000001M017", "1001ZT1000000001M018",
    "1001ZT1000000001M019", "1001ZT1000000001M01A", "1001ZT1000000001M01B",
    "1001ZT1000000001M01C", "1001ZT1000000001M01D", "1001ZT1000000001M01E",
    "1001ZT1000000001M01F", "1001ZT1000000001M01G", "1001ZT1000000001M01H",
    "1001ZT1000000001M01I", "1001ZT1000000001M01J", "1001ZT1000000001M01K",
    "1001ZT1000000001M01L", "1001ZT1000000001M01M", "1001ZT1000000001M01N",
    "1001ZT1000000001M01O", "1001ZT1000000001M01P", "1001ZT1000000001M01Q",
    "1001ZT1000000001M01R", "1001ZT1000000001M01S", "1001ZT1000000001M01T",
    "1001ZT1000000001M01U", "1001ZT1000000001M01V", "1001ZT1000000002WVZN",

    "1001ZU10000000006ADM", "1001ZU10000000006ADN", "1001ZU10000000006ADO",
    "1001ZU10000000006ADP", "1001ZU10000000006ADQ", "1001ZU10000000006ADR",
    "1001ZU10000000006ADS", "1001ZU10000000006ADT", "1001ZU10000000006ADU",
    "1001ZU10000000006ADV", "1001ZU10000000006ADW", "1001ZU10000000006ADX",
    "1001ZU10000000006ADY", "1001ZU10000000006ADZ", "1001ZU10000000006AE0",
    "1001ZU10000000006AE1", "1001ZU10000000006AE2", "1001ZU10000000006AE3",
    "1001ZU10000000006AE4", "1001ZU10000000006AE5", "1001ZU10000000006AE6",
    "1001ZU10000000006AE7", "1001ZU10000000006AE8", "1001ZU10000000006AE9",
    "1001ZU10000000006AEA", "1001ZU10000000006AEB", "1001ZU10000000006AEC",
    "1001ZU10000000006AED", "1001ZU10000000006AEE", "1001ZU10000000006AEF",
    "1001ZU10000000006AEG", "1001ZU10000000006AEH", "1001ZU10000000006AEI",
    "1003ZF1000000011Z85T"
  };

  /**
   * pub_vochange_s违规主键
   */
  private final static String[] PK_VOCHANGE_S = {
    "1001ZF1000000000GM8Y", "1001ZF1000000000GM8Z", "1001ZF1000000000GM90",
    "1001ZF1000000000GM91", "1001ZF1000000000GM92", "1001ZF1000000000GM93",
    "1001ZF1000000000GM94", "1001ZF1000000000GM95", "1001ZF1000000000GM96"
  };
}
