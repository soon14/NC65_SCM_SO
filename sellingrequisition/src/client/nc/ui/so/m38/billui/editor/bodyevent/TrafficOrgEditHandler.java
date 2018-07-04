package nc.ui.so.m38.billui.editor.bodyevent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.stordoc.StordocPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.org.TrafficOrgPubService;
import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.bd.stordoc.StordocVO;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class TrafficOrgEditHandler {

  public void beforeEdit(CardBodyBeforeEditEvent e) {

    int row = e.getRow();
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    String csendstockorgid = this.getSendStockOrgID(keyValue, row);

    // 发货库存组织为空不允许编辑
    if (PubAppTool.isNull(csendstockorgid)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }

    Map<String, Collection<String>> trafficOrgIDMap = null;
    String[] trafficOrgVIDs = null;

    String[] csendstockorgids = new String[] {
      csendstockorgid
    };
    trafficOrgIDMap =
        TrafficOrgPubService
            .getTrafficOrgIDSByStockOrgIDSWithIsSend(csendstockorgids);
    trafficOrgVIDs = this.getTraficOrgVIDs(trafficOrgIDMap);
    BillItem trafficOrgvItem =
        cardPanel.getBodyItem(PreOrderBVO.CTRAFFICORGVID);
    UIRefPane trafficOrgvRefPane = (UIRefPane) trafficOrgvItem.getComponent();
    AbstractRefModel model = trafficOrgvRefPane.getRefModel();
    if (null != trafficOrgVIDs && trafficOrgVIDs.length > 0) {
      model.setFilterPks(trafficOrgVIDs);
    }
    else {
      model.setFilterPks(new String[0]);
    }

  }

  private String getSendStockOrgID(IKeyValue keyValue, int row) {

    String sendstordocid =
        keyValue.getBodyStringValue(row, PreOrderBVO.CSENDSTORDOCID);
    String sendstockorgid =
        keyValue.getBodyStringValue(row, PreOrderBVO.CSENDSTOCKORGID);
    // --仓库为空：取发货库存组织
    if (PubAppTool.isNull(sendstordocid)) {
      return sendstockorgid;
    }
    // --仓库非空：取仓库所属库存组织
    String[] sendstordocids = new String[] {
      sendstordocid
    };
    String[] fields = new String[] {
      StordocVO.PK_STORDOC, StordocVO.PK_ORG
    };
    StordocVO[] stordocVOs =
        StordocPubService.queryStordocByPks(sendstordocids, fields);
    if (null != stordocVOs && stordocVOs.length > 0) {
      return stordocVOs[0].getPk_org();
    }
    return sendstockorgid;

  }

  private String[] getTraficOrgVIDs(
      Map<String, Collection<String>> trafficOrgIDMap) {
    String[] ret = null;
    if (null == trafficOrgIDMap || trafficOrgIDMap.size() == 0) {
      return ret;
    }

    List<String> trafficOrgIDList = new ArrayList<String>();
    Set<Entry<String, Collection<String>>> entrySet =
        trafficOrgIDMap.entrySet();
    for (Entry<String, Collection<String>> entry : entrySet) {
      Collection<String> idList = entry.getValue();
      trafficOrgIDList.addAll(idList);
    }
    String[] trafficOrgIDs = new String[trafficOrgIDList.size()];
    trafficOrgIDList.toArray(trafficOrgIDs);
    Map<String, String> vidMap = null;

    vidMap = OrgUnitPubService.getNewVIDSByOrgIDS(trafficOrgIDs);

    if (vidMap != null) {
      List<String> vidlist = new ArrayList<String>();
      for (Entry<String, String> entry : vidMap.entrySet()) {
        String value = entry.getValue();
        if (value != null && value.length() > 0) {
          vidlist.add(entry.getValue());
        }
      }
      if (vidlist.size() > 0) {
        ret = vidlist.toArray(new String[vidlist.size()]);
      }
    }
    return ret;
  }
}
