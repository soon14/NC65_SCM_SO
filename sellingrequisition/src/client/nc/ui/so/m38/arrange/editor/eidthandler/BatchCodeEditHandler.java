package nc.ui.so.m38.arrange.editor.eidthandler;

import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.ui.ic.batchcode.ref.BatchRefDlg;
import nc.ui.ic.batchcode.ref.BatchRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.billref.push.BillContext;
import nc.ui.pubapp.billref.push.PushBillEvent;
import nc.ui.so.pub.keyvalue.ListKeyValue;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.ic.batch.BatchRefViewVO;
import nc.vo.ic.batchcode.BatchDlgParam;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmf.ic.mbatchcode.BatchcodeVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.enumeration.ListTemplateType;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class BatchCodeEditHandler {

  public void beforeEdit(BillListPanel listPanel, PushBillEvent e,
      BillContext billcontext) {

    int row = e.getEditEvent().getRow();
    IKeyValue keyValue = new ListKeyValue(listPanel, row, ListTemplateType.SUB);

    String materialvid = keyValue.getHeadStringValue(SaleOrderBVO.CMATERIALVID);
    String sendstock =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CSENDSTOCKORGID);
    if (PubAppTool.isNull(materialvid) || PubAppTool.isNull(sendstock)) {
      return;
    }

    String[] wholeflag = new String[] {
      MaterialStockVO.WHOLEMANAFLAG
    };
    Map<String, MaterialStockVO> map =
        MaterialPubService.queryMaterialStockInfo(new String[] {
          materialvid
        }, sendstock, wholeflag);
    MaterialStockVO marstockvo = map.get(materialvid);
    UFBoolean flag = null;
    if (null != marstockvo) {
      flag = marstockvo.getWholemanaflag();
    }
    if (null == flag || !flag.booleanValue()) {
      return;
    }
    String sendstordoc =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CSENDSTORDOCID);
    String materialoid =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CMATERIALID);
    String astunit = keyValue.getBodyStringValue(row, SaleOrderBVO.CASTUNITID);
    String vbatchcode =
        keyValue.getBodyStringValue(row, SaleOrderBVO.VBATCHCODE);

    BillItem batchcodeitem = listPanel.getBodyItem(SaleOrderBVO.VBATCHCODE);
    BatchRefPane batchref = (BatchRefPane) batchcodeitem.getComponent();
    BatchDlgParam param = new BatchDlgParam();
    param.setPk_calbody(sendstock);
    param.setCwarehouseid(sendstordoc);
    param.setCmaterialvid(materialvid);
    param.setCmaterialoid(materialoid);
    param.setVbatchcode(vbatchcode);
    param.setCastUnitID(astunit);
    param.setLoginContext(billcontext.getTabBillInfo().getLoginContext());
    batchref.setParam(param);
    batchref.setMultiSelectedEnabled(false);
    batchref.showModel();
  }

  public void afterEdit(BillListPanel listPanel, PushBillEvent e) {
    int row = e.getEditEvent().getRow();
    String editvalue = (String) e.getEditEvent().getValue();

    IKeyValue keyValue = new ListKeyValue(listPanel, row, ListTemplateType.SUB);

    BillItem batchcodeitem = listPanel.getBodyItem(SaleOrderBVO.VBATCHCODE);
    BatchRefPane batchref = (BatchRefPane) batchcodeitem.getComponent();
    BatchRefDlg refdlg = batchref.getBatchRefDlg();

    String cmaterialvid =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CMATERIALVID);

    BatchRefViewVO batchvo = refdlg.getRefVO(cmaterialvid, editvalue);
    if (PubAppTool.isNull(editvalue) || null == batchvo) {
      keyValue.setBodyValue(row, SaleOrderBVO.VBATCHCODE, null);
      keyValue.setBodyValue(row, SaleOrderBVO.PK_BATCHCODE, null);
    }
    else {
      keyValue.setBodyValue(row, SaleOrderBVO.VBATCHCODE,
          batchvo.getAttributeValue(SaleOrderBVO.VBATCHCODE));
      keyValue.setBodyValue(row, SaleOrderBVO.PK_BATCHCODE,
          batchvo.getAttributeValue(BatchcodeVO.PK_BATCHCODE));
    }
  }
}
