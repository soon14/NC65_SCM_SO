package nc.pubimpl.so.m30.split;

import java.util.ArrayList;
import java.util.List;

import nc.pubitf.so.m30.split.ISaleOrderOrgRelaSplit;
import nc.pubitf.so.m30.split.ISaleOrderSplitPara;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.SaleOrgRelationRule;

public class SaleOrderOrgRelaSplitImpl implements ISaleOrderOrgRelaSplit {

  @Override
  public List<String> splitBySendStockOrg(ISaleOrderSplitPara splitpara)
      throws BusinessException {
    SaleOrderVO saleorder = this.changeToSaleOrderVO(splitpara);
    IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(saleorder);
    int[] rows = this.getBodyNoValueRow(keyValue, SaleOrderBVO.CSENDSTOCKORGID);

    if (rows.length > 0) {
      SaleOrgRelationRule relrule = new SaleOrgRelationRule(keyValue);
      relrule.setSendStockOrg(rows);
    }
    return this.getBodyValue(keyValue, SaleOrderBVO.CSENDSTOCKORGID);
  }

  private List<String> getBodyValue(IKeyValue keyValue, String bodykey) {
    List<String> alvalue = new ArrayList<String>();
    int bodycount = keyValue.getBodyCount();
    for (int i = 0; i < bodycount; i++) {
      String value = keyValue.getBodyStringValue(i, bodykey);
      alvalue.add(value);
    }
    return alvalue;
  }

  private int[] getBodyNoValueRow(IKeyValue keyValue, String bodykey) {

    List<Integer> alrow = new ArrayList<Integer>();
    int bodycount = keyValue.getBodyCount();
    for (int i = 0; i < bodycount; i++) {
      String value = keyValue.getBodyStringValue(i, bodykey);
      if (PubAppTool.isNull(value)) {
        alrow.add(Integer.valueOf(i));
      }
    }
    int[] rows = new int[alrow.size()];
    int i = 0;
    for (Integer row : alrow) {
      rows[i] = row.intValue();
      i++;
    }
    return rows;
  }

  private SaleOrderVO changeToSaleOrderVO(ISaleOrderSplitPara splitpara) {
    SaleOrderVO saleorder = new SaleOrderVO();
    // 表头
    SaleOrderHVO head = new SaleOrderHVO();
    head.setPk_org(splitpara.getSaleOrgid());
    head.setCcustomerid(splitpara.getCustomerid());
    saleorder.setParentVO(head);
    // 表体
    int bodycount = splitpara.getBodyCount();
    SaleOrderBVO[] bodys = new SaleOrderBVO[bodycount];
    for (int i = 0; i < bodycount; i++) {
      bodys[i] = new SaleOrderBVO();
      String marid = splitpara.getMaterialid(i);
      bodys[i].setCmaterialid(marid);
      String sendstockorg = splitpara.getSendStockOrgid(i);
      bodys[i].setCsendstockorgid(sendstockorg);
      String trafficorgid = splitpara.getTrafficOrgid(i);
      bodys[i].setCtrafficorgid(trafficorgid);
      String settleorgid = splitpara.getSettleOrgid(i);
      bodys[i].setCsettleorgid(settleorgid);
      String arorgid = splitpara.getArOrgid(i);
      bodys[i].setCarorgid(arorgid);
    }
    saleorder.setChildrenVO(bodys);

    return saleorder;
  }

  @Override
  public List<String> splitByTrafficOrg(ISaleOrderSplitPara splitpara)
      throws BusinessException {

    SaleOrderVO saleorder = this.changeToSaleOrderVO(splitpara);
    IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(saleorder);
    int[] rows = this.getBodyNoValueRow(keyValue, SaleOrderBVO.CTRAFFICORGID);

    if (rows.length > 0) {

      SaleOrgRelationRule relrule = new SaleOrgRelationRule(keyValue);
      // 先填充发货库存组织
      int[] sendnullrows =
          this.getBodyNoValueRow(keyValue, SaleOrderBVO.CSENDSTOCKORGID);
      if (sendnullrows.length > 0) {
        relrule.setSendStockOrg(sendnullrows);
      }
      // 填充物流组织
      relrule.setSendStockOrg(rows);
    }
    return this.getBodyValue(keyValue, SaleOrderBVO.CTRAFFICORGID);

  }

  @Override
  public List<String> splitBySettleOrg(ISaleOrderSplitPara splitpara)
      throws BusinessException {

    SaleOrderVO saleorder = this.changeToSaleOrderVO(splitpara);
    IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(saleorder);
    int[] rows = this.getBodyNoValueRow(keyValue, SaleOrderBVO.CSETTLEORGID);

    if (rows.length > 0) {

      SaleOrgRelationRule relrule = new SaleOrgRelationRule(keyValue);
      // 先填充发货库存组织
      int[] sendnullrows =
          this.getBodyNoValueRow(keyValue, SaleOrderBVO.CSENDSTOCKORGID);
      if (sendnullrows.length > 0) {
        relrule.setSendStockOrg(sendnullrows);
      }
      // 填充财务组织
      relrule.setFinanceOrg(rows);
    }
    return this.getBodyValue(keyValue, SaleOrderBVO.CSETTLEORGID);

  }

  @Override
  public List<String> splitByArOrg(ISaleOrderSplitPara splitpara)
      throws BusinessException {

    SaleOrderVO saleorder = this.changeToSaleOrderVO(splitpara);
    IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(saleorder);
    int[] rows = this.getBodyNoValueRow(keyValue, SaleOrderBVO.CARORGID);

    if (rows.length > 0) {

      SaleOrgRelationRule relrule = new SaleOrgRelationRule(keyValue);
      // 先填充发货库存组织
      int[] sendnullrows =
          this.getBodyNoValueRow(keyValue, SaleOrderBVO.CSENDSTOCKORGID);
      if (sendnullrows.length > 0) {
        relrule.setSendStockOrg(sendnullrows);
      }
      // 填充财务组织
      relrule.setFinanceOrg(rows);
    }
    return this.getBodyValue(keyValue, SaleOrderBVO.CARORGID);
  }

}
