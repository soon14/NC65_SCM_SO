package nc.pubitf.so.mobile.analy;

import java.util.List;
import java.util.Map;

/**
 * 移动应用订单分析接口
 * 
 * @since 6.1
 * @version 2013-04-16 09:05:06
 * @author yixl
 */
public interface ISaleOrderAnaly {

  /**
   * 查询销售日分析数据
   * 
   * @param groupid 集团标识
   * @param usrid 用户标识
   * @param qrydate 查询日期
   * @param grouptype 分组方式
   * @param productlineid 产品线
   * @param brandid 品牌
   * @param channeltypeid 渠道类型
   * @param saleorgid 销售组织
   * @param customerid 客户
   * @return List
   */
  List<Map<String, Object>> qryDayAnalysis(String groupid, String usrid,
      String qrydate, String grouptype, String productlineid, String brandid,
      String channeltypeid, String saleorgid, String customerid);

  /**
   * 查询销售日报数据
   * 
   * @param groupid 集团标识
   * @param usrid 用户标识
   * @param qrydate 查询日期
   * @param currid 币种
   * @param grouptype 分组方式
   * @param bizmanid 业务员
   * @param customerid 客户
   * @param invid 物料
   * @return List
   */
  List<Map<String, Object>> qryDayReport(String groupid, String usrid,
      String qrydate, String currid, String grouptype, String bizmanid,
      String customerid, String invid);

  /**
   * 查询订单列表
   * 
   * @param groupid 集团标识
   * @param usrid 用户标识
   * @param qrydate 查询日期
   * @param currid 币种
   * @param bizmanid 业务员
   * @param customerid 客户
   * @param invid 物料
   * @param startline 开始位置
   * @param count 每页记录数
   * @return List
   */
  List<Map<String, Object>> qrySOList(String groupid, String usrid,
      String qrydate, String currid, String bizmanid, String customerid,
      String invid, int startline, int count);

  /**
   * 查询币种列表
   * 
   * @param groupid 集团标识
   * @param usrid 用户标识
   * @param qrydate 查询日期
   * @param grouptype 分组方式
   * @param bizmanid 业务员
   * @param customerid 客户
   * @param invid 物料
   * @return List
   */
  List<Map<String, Object>> qryCurAndReport(String groupid, String usrid,
      String qrydate, String grouptype, String bizmanid, String customerid,
      String invid);
}
