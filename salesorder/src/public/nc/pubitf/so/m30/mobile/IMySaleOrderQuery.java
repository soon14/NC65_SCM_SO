package nc.pubitf.so.m30.mobile;

import java.util.List;
import java.util.Map;

/**
 * 移动应用我的订单接口
 * 
 * @since 6.1
 * @version 2013-04-16 09:06:39
 * @author yixl
 */
public interface IMySaleOrderQuery {

  /**
   * 条件搜索订单
   * 
   * @param groupId 集团标识
   * @param userId 用户标识
   * @param condition 搜索条件
   * @param startLine 开始位置
   * @param count 每页记录数
   * @return List
   */
  List<Map<String, Object>> getBillByCondition(String groupId, String userId,
      int startLine, int count, String condition);

  /**
   * 查询订单明细
   * 
   * @param groupId 集团标识
   * @param userId 用户标识
   * @param billId 订单ID
   * @param startLine 开始位置
   * @param count 每页记录数
   * @return List
   */
  List<Map<String, Object>> getBillDetail(String groupId, String userId,
      String billId, int startLine, int count);

  /**
   * 查询客户关联订单列表
   * 
   * @param groupId 集团标识
   * @param userId 用户标识
   * @param startLine 开始位置
   * @param count 每页记录数
   * @param customerid 客户标识
   * @return List
   */
  List<Map<String, Object>> getBillListByCustomer(String groupId,
      String userId, int startLine, int count, String customerid);

  /**
   * 查询客户详情
   * 
   * @param groupid
   * @param userid
   * @param pk_customer
   * @return List
   */
  List<Map<String, Object>> getCustomerDetail(String groupid, String userid,
      String pk_customer);

  /**
   * 查询我的订单分组方式
   * 
   * @param groupId 集团标识
   * @param userId 用户标识
   * @return List
   */
  List<Map<String, Object>> getMyBillGrpType(String groupId, String userId);

  /**
   * 查询我的订单列表
   * 
   * @param groupId 集团标识
   * @param userId 用户标识
   * @param startLine 开始位置
   * @param count 每页记录数
   * @param groupType 分组方式
   * @return List
   */
  List<Map<String, Object>> getMyBillList(String groupId, String userId,
      int startLine, int count, String groupType);

  /**
   * 查询我的客户列表
   * 
   * @param pk_group 集团标识
   * @param pk_userid 用户标识
   * @param start
   * @param count
   * @return List
   */
  List<Map<String, Object>> getMyCustomerList(String pk_group,
      String pk_userid, int start, int count);

  /**
   * 查询销售订单搜索条件中文描述
   * 
   * @param groupId
   * @param userId
   * @return List
   */
  List<Map<String, Object>> getSOSerchCondition(String groupId, String userId);
}
