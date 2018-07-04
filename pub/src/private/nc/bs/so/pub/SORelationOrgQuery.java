package nc.bs.so.pub;

import java.util.HashMap;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.stordoc.StordocPubService;
import nc.itf.scmpub.reference.uap.org.SaleOrgPubService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.util.SaleOrderTranTypeUtil;

/**
 * 预订单、销售订单、报价单等需要根据客户、销售组织及物料获取行上的发货库存组织、结算财务组织ID、应收组织ID、利润中心ID——功能/算法概述
 * <ol>
 * <li>调用公共服务获取发货库存组织
 * <li>调用公共服务获取结算财务组织ID、应收组织ID、利润中心ID
 * <li>--调用公共服务获取默认物流组织-- modify by zhangby5 取默认物流组织要放在取仓库后做
 * </ol>
 * 
 * @since 6.0
 * @version 2011-7-28 下午15:05:00
 * @author 旷宗义
 * @see
 */
public class SORelationOrgQuery {

  /*
   * 客户
   */
  private String customerID;

  public String getCustomerID() {
    return this.customerID;
  }

  public void setCustomerID(String customerID) {
    this.customerID = customerID;
  }

  /*
   * 销售组织
   */
  private String saleOrg;

  public String getSaleOrg() {
    return this.saleOrg;
  }

  public void setSaleOrg(String saleOrg) {
    this.saleOrg = saleOrg;
  }

  /*
   * 交易类型
   */
  private String transtypeID;

  public String getTranstypeID() {
    return this.transtypeID;
  }

  public void setTranstypeID(String transtypeID) {
    this.transtypeID = transtypeID;
  }

  /*
   * 物料数组
   */
  private String[] materialIDS;

  public String[] getMaterialIDS() {
    return this.materialIDS;
  }

  public void setMaterialIDS(String[] materialIDS) {
    this.materialIDS = materialIDS;
  }

  /*
   * 发货库存组织ID
   */
  private String[] sendStockOrgIDs;

  /*
   * 发货库存组织+直运仓库键值对
   */
  private Map<String, String> sendStordocidMap;

  /*
   * 缺省构造函数
   */
  public SORelationOrgQuery() {
    /*
     * 缺省构造函数
     */
  }

  /**
   * @param customerID 客户ID
   * @param saleOrg 销售组织
   * @param materialIDS 物料ID数组
   */
  public SORelationOrgQuery(String customerID, String saleOrg,
      String[] materialIDS) {
    this.customerID = customerID;
    this.saleOrg = saleOrg;
    this.materialIDS = materialIDS;
  }

  /**
   * 根据客户、销售组织及物料获取行上的发货库存组织、结算财务组织ID、应收组织ID、利润中心ID
   * modify by zhangby5 取默认物流组织要放在取仓库后做，若此处没有取到直运仓库，那么就要按照发货库存组织取，
   * 若根据发货库存组织能带出仓库，那物流组织还要按照仓库的库存组织过滤，互相矛盾，因此要放在取仓库后做  --- 测试部提出问题：NCdp205432523
   * @param customerID 客户ID
   * @param saleOrg 销售组织
   * @param materialIDS 物料ID数组
   * @return Map<Key：物料，Value：String[]{发货库存组织、结算财务组织ID、应收组织ID、利润中心ID}>
   * @throws BusinessException
   * @see
   */

  public Map<String, String[]> querySaleRelationOrg() {

    // 初始化返回参数
    Map<String, String[]> returnOrgMap = new HashMap<String, String[]>();
    // 返回数据行数
    int intLength = this.materialIDS.length;
    // 销售组织参数
    String[] saleOrgs = new String[intLength];
    for (int i = 0; i < intLength; i++) {
      saleOrgs[i] = this.saleOrg;
    }

    // 查询默认发货库存组织ID
    Map<String, String> hmSendStockOrgIDs =
        SaleOrgPubService.getDefaultStockOrgIDS(saleOrgs, this.materialIDS);
    this.sendStockOrgIDs = new String[intLength];

    // 设置返回库存组织
    if (null != hmSendStockOrgIDs && hmSendStockOrgIDs.size() > 0) {
      for (int i = 0; i < intLength; i++) {
        String stockOrgkey =
            this.saleOrg + SaleOrgPubService.SPLIT + this.materialIDS[i];
        this.sendStockOrgIDs[i] = hmSendStockOrgIDs.get(stockOrgkey);
      }
    }

    // 获取直运仓
    this.setSendStordoc();

    // 查询结算财务组织ID、应收组织ID、利润中心ID
    Map<String, String[]> hmFinanceOrgid =
        SaleOrgPubService
            .getDefaultFinanceOrgIDSAndReceiveOrgIDSAndLiaCenterIDS(
                this.customerID, saleOrgs, this.materialIDS,
                this.sendStockOrgIDs);

    // 设置返回结果
    for (int i = 0; i < intLength; i++) {
      String strKey = this.materialIDS[i];
      String[] returnOrgs = new String[6];
      // 默认发货库存组织ID
      returnOrgs[0] = this.sendStockOrgIDs[i];

      if (null != hmFinanceOrgid && hmFinanceOrgid.size() > 0) {
        String[] financeOrgs = hmFinanceOrgid.get(strKey);
        if (null != financeOrgs && financeOrgs.length == 3) {
          // 财务组织ID、应收组织ID、利润中心ID
          returnOrgs[1] = financeOrgs[0];
          returnOrgs[2] = financeOrgs[1];
          returnOrgs[3] = financeOrgs[2];
        }
      }
      if (null != this.sendStordocidMap && this.sendStordocidMap.size() > 0) {
        // 仓库
        returnOrgs[5] = this.sendStordocidMap.get(this.sendStockOrgIDs[i]);
      }
      returnOrgMap.put(strKey, returnOrgs);
    }

    return returnOrgMap;
  }

  /**
   * 根据交易类型是否直运设置仓库值,如果不是直运的清空原有仓库值，否则设置为直运仓
   * 
   * @param transtypeID 交易类型ID
   * @param stockorgs 库存组织数组
   * @return Map<库存组织ID, 直运仓库ID>
   */
  public void setSendStordoc() {
    this.sendStordocidMap = new HashMap<String, String>();
    // 如果传入了交易类型，获取直运仓
    if (null != this.transtypeID) {
      SaleOrderTranTypeUtil dirutil = new SaleOrderTranTypeUtil();
      if (dirutil.isDirectTran(this.transtypeID)
          && null != this.sendStockOrgIDs && this.sendStockOrgIDs.length != 0) {
        for (String sendstockorg : this.sendStockOrgIDs) {
          if (PubAppTool.isNull(sendstockorg)) {
            continue;
          }
          String direcstore = StordocPubService.getDirectstore(sendstockorg);
          this.sendStordocidMap.put(sendstockorg, direcstore);
        }

      }
    }
  }
}
