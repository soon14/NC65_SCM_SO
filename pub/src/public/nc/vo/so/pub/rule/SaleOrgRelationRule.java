package nc.vo.so.pub.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.vo.bd.stordoc.StordocVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;

import nc.itf.scmpub.reference.uap.bd.stordoc.StordocPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.org.SaleOrgPubService;
import nc.itf.scmpub.reference.uap.org.TrafficOrgPubService;
import nc.itf.so.m30.IQueryRelationOrg;

import nc.bs.framework.common.NCLocator;

public class SaleOrgRelationRule {

  IKeyValue keyValue;

  public SaleOrgRelationRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public void setFinanceOrg(int[] rows) {

    if (null == rows || rows.length == 0) {
      return;
    }

    String[] clearkeys =
        new String[] {
          SOItemKey.CSETTLEORGID, SOItemKey.CSETTLEORGVID, SOItemKey.CARORGID,
          SOItemKey.CARORGVID, SOItemKey.CPROFITCENTERID,
          SOItemKey.CPROFITCENTERVID
        };
    this.clearBodyOldValue(clearkeys, rows);

    List<String> alMaterialid = new ArrayList<String>();
    List<String> alOrgs = new ArrayList<String>();
    List<String> alSendStockOrgIDs = new ArrayList<String>();
    String pk_org = this.keyValue.getHeadStringValue(SOItemKey.PK_ORG);

    for (int row : rows) {
      String cmaterialid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CMATERIALID);
      String csendstockorgid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CSENDSTOCKORGID);
      if (PubAppTool.isNull(cmaterialid)) {
        continue;
      }
      alMaterialid.add(cmaterialid);
      alOrgs.add(pk_org);
      alSendStockOrgIDs.add(csendstockorgid);
    }
    if (alMaterialid.size() == 0) {
      return;
    }

    Map<String, String[]> hmFinanceOrgid = null;
    Map<String, String> hmFinanceOrgvid = null;

    String ccustomerid =
        this.keyValue.getHeadStringValue(SOItemKey.CCUSTOMERID);
    String[] cmaterialids = new String[alMaterialid.size()];
    alMaterialid.toArray(cmaterialids);

    String[] pk_orgs = new String[alOrgs.size()];
    alOrgs.toArray(pk_orgs);

    String[] csendstockorgids = new String[alSendStockOrgIDs.size()];
    alSendStockOrgIDs.toArray(csendstockorgids);

    // 查询结算财务组织ID、应收组织ID、利润中心ID和结算财务组织VID、应收组织VID、利润中心VID
    try {
      hmFinanceOrgid =
          SaleOrgPubService
              .getDefaultFinanceOrgIDSAndReceiveOrgIDSAndLiaCenterIDS(
                  ccustomerid, pk_orgs, cmaterialids, csendstockorgids);
      hmFinanceOrgvid = this.getFinanceOrgVIDs(hmFinanceOrgid);
    }
    catch (BusinessException e1) {
      ExceptionUtils.wrappException(e1);
    }
    // 往界面上设值
    this.setFinanceOrgValue(hmFinanceOrgid, hmFinanceOrgvid, rows);

  }

  public void setSendStockOrg(int[] rows) {

    if (null == rows || rows.length == 0) {
      return;
    }
    // 清空原有发货库存组织
    String[] clearkey = new String[] {
      SOItemKey.CSENDSTOCKORGID, SOItemKey.CSENDSTOCKORGVID
    };
    this.clearBodyOldValue(clearkey, rows);

    List<String> alOrg = new ArrayList<String>();
    List<String> alMaterial = new ArrayList<String>();
    String pk_org = this.keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    for (int row : rows) {
      // 获取物料
      String materialid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CMATERIALID);
      if (!PubAppTool.isNull(materialid)) {
        alOrg.add(pk_org);
        alMaterial.add(materialid);
      }
    }
    if (alOrg.size() == 0 || alMaterial.size() == 0) {
      return;
    }
    String[] pk_orgs = new String[alOrg.size()];
    alOrg.toArray(pk_orgs);

    String[] materialids = new String[alMaterial.size()];
    alMaterial.toArray(materialids);

    Map<String, String> hmSendStockOrgIDs = null;
    Map<String, String> hmSendStockOrgVIDs = null;
    try {
      // 查询默认发货库存组织ID和发货库存组织VID
      hmSendStockOrgIDs =
          SaleOrgPubService.getDefaultStockOrgIDS(pk_orgs, materialids);

      hmSendStockOrgVIDs = this.getSendStockOrgVIDs(hmSendStockOrgIDs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    if (null != hmSendStockOrgIDs && null != hmSendStockOrgVIDs) {
      for (int row : rows) {
        String marvid =
            this.keyValue.getBodyStringValue(row, SOItemKey.CMATERIALID);
        if (PubAppTool.isNull(marvid)) {
          continue;
        }
        String key = pk_org + SaleOrgPubService.SPLIT + marvid;
        String csendstockorgid = hmSendStockOrgIDs.get(key);
        this.keyValue.setBodyValue(row, SOItemKey.CSENDSTOCKORGID,
            csendstockorgid);

        String csendstockorgvid = hmSendStockOrgVIDs.get(csendstockorgid);
        this.keyValue.setBodyValue(row, SOItemKey.CSENDSTOCKORGVID,
            csendstockorgvid);
      }
    }

  }

  /**
   * 根据发货库库组织在物流业务委托关系中查找物流组织
   * <p>
   * 仓库非空：用仓库所属库存组织查找
   * </p>
   * <p>
   * 仓库为空：用订单发货库存组织查找
   * </p>
   */
  public void setTrafficOrg(int[] rows) {
    /* 需求描述：
     * 物流组织是进行发货安排和运输安排的组织。是发货单和运输单的主组织。
     * 系统认为，发货安排和运输安排，基于物理位置，在当地安排。
     * 按照仓库所属库存组织，而不是按照物权，匹配物流组织。*/
    if (null == rows || rows.length == 0) {
      return;
    }
    String[] clearkey = new String[] {
      SOItemKey.CTRAFFICORGID, SOItemKey.CTRAFFICORGVID
    };
    this.clearBodyOldValue(clearkey, rows);
    // 获取库存组织
    List<String> alsendStockOrgid = new ArrayList<String>();
    List<String> alsendstordocid = new ArrayList<String>();
    for (int row : rows) {
      // --仓库非空：取仓库所属库存组织
      String sendstordocid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CSENDSTORDOCID);
      if (!PubAppTool.isNull(sendstordocid)) {
        alsendstordocid.add(sendstordocid);
      }
      // --仓库为空：取发货库存组织
      else {
        String sendStockOrgID =
            this.keyValue.getBodyStringValue(row, SOItemKey.CSENDSTOCKORGID);
        if (!PubAppTool.isNull(sendStockOrgID)) {
          alsendStockOrgid.add(sendStockOrgID);
        }
      }
    }
    // stockOrgMap<仓库ID,所属库存组织ID>
    Map<String, String> stockOrgMap = new HashMap<String, String>();
    if (alsendstordocid.size() > 0) {
      String[] sendstordocids =
          alsendstordocid.toArray(new String[alsendstordocid.size()]);
      String[] fields = new String[] {
        StordocVO.PK_STORDOC, StordocVO.PK_ORG
      };
      StordocVO[] stordocVOs =
          StordocPubService.queryStordocByPks(sendstordocids, fields);

      if (stordocVOs != null && stordocVOs.length > 0) {
        for (StordocVO stordocVO : stordocVOs) {
          stockOrgMap.put(stordocVO.getPk_stordoc(), stordocVO.getPk_org());
        }
      }
    }

    if (alsendStockOrgid.size() == 0 && stockOrgMap.size() == 0) {
      return;
    }
    alsendStockOrgid.addAll(stockOrgMap.values());
    String[] sendStockOrgIDs = new String[alsendStockOrgid.size()];
    alsendStockOrgid.toArray(sendStockOrgIDs);

    Map<String, String> trafficOrgIDMap = null;
    Map<String, String> trafficOrgVIDMap = null;

    trafficOrgIDMap =
        TrafficOrgPubService.getTrafficOrgIDSByStockOrgIDS(sendStockOrgIDs);
    trafficOrgVIDMap = this.getTraficOrgVIDs(trafficOrgIDMap);

    if (null != trafficOrgIDMap && null != trafficOrgVIDMap) {
      for (int row : rows) {
        // 仓库非空
        String sendstordocid =
            this.keyValue.getBodyStringValue(row, SOItemKey.CSENDSTORDOCID);
        if (!PubAppTool.isNull(sendstordocid)) {
          String trafficOrgID =
              trafficOrgIDMap.get(stockOrgMap.get(sendstordocid));
          this.keyValue
              .setBodyValue(row, SOItemKey.CTRAFFICORGID, trafficOrgID);
          this.keyValue.setBodyValue(row, SOItemKey.CTRAFFICORGVID,
              trafficOrgVIDMap.get(trafficOrgID));
        }
        // 仓库为空
        else {
          String sendStockOrgID =
              this.keyValue.getBodyStringValue(row, SOItemKey.CSENDSTOCKORGID);
          if (!PubAppTool.isNull(sendStockOrgID)) {
            String trafficOrgID = trafficOrgIDMap.get(sendStockOrgID);
            this.keyValue.setBodyValue(row, SOItemKey.CTRAFFICORGID,
                trafficOrgID);
            this.keyValue.setBodyValue(row, SOItemKey.CTRAFFICORGVID,
                trafficOrgVIDMap.get(trafficOrgID));
          }
        }
      }
    }
  }

  private void clearBodyOldValue(String[] bodykeys, int[] rows) {
    for (int row : rows) {
      for (String key : bodykeys) {
        this.keyValue.setBodyValue(row, key, null);
      }
    }
  }

  private Map<String, String> getFinanceOrgVIDs(
      Map<String, String[]> hmFinanceOrgid) throws BusinessException {
    Map<String, String> hmFinanceOrgvid = null;
    if (hmFinanceOrgid == null || hmFinanceOrgid.size() == 0) {
      return hmFinanceOrgvid;
    }
    Set<String> hsIDs = new HashSet<String>();
    for (Entry<String, String[]> entry : hmFinanceOrgid.entrySet()) {
      String[] ids = entry.getValue();
      if (null == ids || ids.length == 0) {
        continue;
      }
      for (String id : ids) {
        if (!PubAppTool.isNull(id)) {
          hsIDs.add(id);
        }
      }
    }
    if (hsIDs.size() > 0) {
      String[] orgIDs = new String[hsIDs.size()];
      hsIDs.toArray(orgIDs);

      // 查询结算财务组织VID、应收组织VID、利润中心VID
      hmFinanceOrgvid = OrgUnitPubService.getNewVIDSByOrgIDS(orgIDs);
    }
    return hmFinanceOrgvid;
  }

  private Map<String, String> getSendStockOrgVIDs(
      Map<String, String> hmSendStockOrgIDs) throws BusinessException {
    Map<String, String> hmSendStockOrgVIDs = null;
    // 非空判定
    if (null == hmSendStockOrgIDs || hmSendStockOrgIDs.size() == 0) {
      return hmSendStockOrgVIDs;
    }
    String[] sendstockids = new String[hmSendStockOrgIDs.values().size()];
    hmSendStockOrgIDs.values().toArray(sendstockids);

    // 根据发货库存组织ID查询发货库存组织VID
    hmSendStockOrgVIDs = OrgUnitPubService.getNewVIDSByOrgIDS(sendstockids);

    return hmSendStockOrgVIDs;
  }

  private Map<String, String> getTraficOrgVIDs(
      Map<String, String> trafficOrgIDMap) {
    Map<String, String> returnMap = null;
    if (null == trafficOrgIDMap || trafficOrgIDMap.size() == 0) {
      return returnMap;
    }

    String[] trafficOrgIDs = new String[trafficOrgIDMap.values().size()];
    trafficOrgIDMap.values().toArray(trafficOrgIDs);

    returnMap = OrgUnitPubService.getNewVIDSByOrgIDS(trafficOrgIDs);

    return returnMap;
  }

  private void setFinanceOrgValue(Map<String, String[]> hmFinanceOrgIDs,
      Map<String, String> hmFinanceOrgVIDs, int[] rows) {
    if (null == hmFinanceOrgIDs || null == hmFinanceOrgVIDs) {
      return;
    }
    for (int row : rows) {
      String cmaterialid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CMATERIALID);
      // 物料为空
      if (PubAppTool.isNull(cmaterialid)) {
        continue;
      }
      String[] orgids = hmFinanceOrgIDs.get(cmaterialid);
      String csettleorgid = orgids[0];
      String carorgid = orgids[1];
      String cprofitcenterid = orgids[2];
      this.keyValue.setBodyValue(row, SOItemKey.CSETTLEORGID, csettleorgid);
      this.keyValue.setBodyValue(row, SOItemKey.CARORGID, carorgid);
      this.keyValue.setBodyValue(row, SOItemKey.CPROFITCENTERID,
          cprofitcenterid);
      // 设置VID
      this.keyValue.setBodyValue(row, SOItemKey.CSETTLEORGVID,
          hmFinanceOrgVIDs.get(csettleorgid));
      this.keyValue.setBodyValue(row, SOItemKey.CARORGVID,
          hmFinanceOrgVIDs.get(carorgid));
      this.keyValue.setBodyValue(row, SOItemKey.CPROFITCENTERVID,
          hmFinanceOrgVIDs.get(cprofitcenterid));

    }
  }

  /**
   * 销售订单
   * 根据客户、销售组织及物料获取行上的发货库存组织、结算财务组织ID、应收组织ID、利润中心ID、默认物流组织 根据交易类型是否直运获取直运仓
   * 
   * @param keyValue
   * @param rows
   * @return
   */
  public Map<String, String[]> getRelationOrg(int[] rows) {

    Map<String, String[]> hmRelationOrgid = null;
    // 组织、客户、交易类型、物料参数准备
    String pk_org = this.keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    String ccustomerid =
        this.keyValue.getHeadStringValue(SOItemKey.CCUSTOMERID);

    List<String> alMaterialid = new ArrayList<String>();

    for (int row : rows) {
      String cmaterialid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CMATERIALID);
      if (PubAppTool.isNull(cmaterialid)) {
        continue;
      }
      alMaterialid.add(cmaterialid);
    }
    if (alMaterialid.size() == 0) {
      return null;
    }

    String[] cmaterialids = new String[alMaterialid.size()];
    alMaterialid.toArray(cmaterialids);

    String transtypeID =
        this.keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);
    // 查询结算财务组织ID、应收组织ID、利润中心ID和结算财务组织VID、应收组织VID、利润中心VID
    try {
      // 如果交易类型非空，按照交易类型获取直运仓

      IQueryRelationOrg service =
          NCLocator.getInstance().lookup(IQueryRelationOrg.class);
      hmRelationOrgid =
          service.querySaleRelationOrg(transtypeID, ccustomerid, pk_org,
              cmaterialids);

    }
    catch (BusinessException e1) {
      ExceptionUtils.wrappException(e1);
    }
    return hmRelationOrgid;
  }

  /**
   * 设置行上的发货库存组织、结算财务组织ID、应收组织ID、利润中心ID、仓库
   * 取默认物流组织要放在取仓库后做
   * 
   * @throws BusinessException
   * @see
   */
  public void setFinanceStockOrg(int[] rows,
      Map<String, String[]> hmRelationOrgid) {
    if (null == rows || rows.length == 0 || null == hmRelationOrgid) {
      return;
    }

    // 清除行上原始值
    String[] clearkeys =
        new String[] {
          SOItemKey.CSETTLEORGID, SOItemKey.CSETTLEORGVID, SOItemKey.CARORGID,
          SOItemKey.CARORGVID, SOItemKey.CPROFITCENTERID,
          SOItemKey.CPROFITCENTERVID, SOItemKey.CSENDSTOCKORGID,
          SOItemKey.CSENDSTOCKORGVID, SOItemKey.CSENDSTORDOCID
        };
    this.clearBodyOldValue(clearkeys, rows);

    // //获取关联组织的VID
    Map<String, String> hmRelationOrgVid =
        this.getRelationOrgVIDs(hmRelationOrgid);

    // 往界面上设值
    this.setRelationOrgValue(hmRelationOrgid, hmRelationOrgVid, rows);
  }

  /*
   * 获取关联组织的VID
   */
  private Map<String, String> getRelationOrgVIDs(
      Map<String, String[]> hmRelationOrgid) {
    Map<String, String> hmRelationOrgvid = null;

    if (hmRelationOrgid == null || hmRelationOrgid.size() == 0) {
      return hmRelationOrgvid;
    }
    Set<String> hsIDs = new HashSet<String>();
    for (Entry<String, String[]> entry : hmRelationOrgid.entrySet()) {
      String[] ids = entry.getValue();
      if (null == ids || ids.length == 0) {
        continue;
      }
      for (String id : ids) {
        if (!PubAppTool.isNull(id)) {
          hsIDs.add(id);
        }
      }
    }
    if (hsIDs.size() > 0) {
      String[] orgIDs = new String[hsIDs.size()];
      hsIDs.toArray(orgIDs);
      // 获取组织VID
      hmRelationOrgvid = OrgUnitPubService.getNewVIDSByOrgIDS(orgIDs);
    }

    return hmRelationOrgvid;
  }

  /*
   * 设置UI上相关组织和直运仓库的值
   */
  private void setRelationOrgValue(Map<String, String[]> hmRelationOrgIDs,
      Map<String, String> hmRelationOrgVIDs, int[] rows) {
    if (null == hmRelationOrgIDs || null == hmRelationOrgVIDs) {
      return;
    }
    for (int row : rows) {
      String cmaterialid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CMATERIALID);
      // 物料为空
      if (PubAppTool.isNull(cmaterialid)) {
        continue;
      }
      String[] orgids = hmRelationOrgIDs.get(cmaterialid);
      String csendstockorgid = orgids[0];
      String csettleorgid = orgids[1];
      String carorgid = orgids[2];
      String cprofitcenterid = orgids[3];
      String ctrafficorgid = orgids[4];
      String dirstore = orgids[5];
      this.keyValue.setBodyValue(row, SOItemKey.CSENDSTOCKORGID,
          csendstockorgid);
      this.keyValue.setBodyValue(row, SOItemKey.CSETTLEORGID, csettleorgid);
      this.keyValue.setBodyValue(row, SOItemKey.CARORGID, carorgid);
      this.keyValue.setBodyValue(row, SOItemKey.CPROFITCENTERID,
          cprofitcenterid);
      this.keyValue.setBodyValue(row, SOItemKey.CSENDSTORDOCID, dirstore);

      // 设置VID
      this.keyValue.setBodyValue(row, SOItemKey.CSETTLEORGVID,
          hmRelationOrgVIDs.get(csettleorgid));
      this.keyValue.setBodyValue(row, SOItemKey.CARORGVID,
          hmRelationOrgVIDs.get(carorgid));
      this.keyValue.setBodyValue(row, SOItemKey.CPROFITCENTERVID,
          hmRelationOrgVIDs.get(cprofitcenterid));
      this.keyValue.setBodyValue(row, SOItemKey.CSENDSTOCKORGVID,
          hmRelationOrgVIDs.get(csendstockorgid));
      this.keyValue.setBodyValue(row, SOItemKey.CTRAFFICORGID, ctrafficorgid);
      this.keyValue.setBodyValue(row, SOItemKey.CTRAFFICORGVID,
          hmRelationOrgVIDs.get(ctrafficorgid));

    }
  }

}
