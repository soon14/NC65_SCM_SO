package nc.ui.so.m38.billui.editor.bodyevent;

import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.ui.ic.batchcode.ref.BatchRefDlg;
import nc.ui.ic.batchcode.ref.BatchRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.m38.billui.view.PreOrderEditor;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.ic.batch.BatchRefViewVO;
import nc.vo.ic.batchcode.BatchDlgParam;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmf.ic.mbatchcode.BatchcodeVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.uif2.LoginContext;

/**
 * 预订单批次号编辑事件
 * 
 * @since 6.0
 * @version 2012-4-11 上午11:42:18
 * @author 刘景
 */
public class BatchCodeEditHandler {
  private PreOrderEditor editor;

  public void afterEdit(CardBodyAfterEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    String editvalue = (String) e.getValue();
    int row = e.getRow();

    BillItem batchcodeitem = cardPanel.getBodyItem(PreOrderBVO.VBATCHCODE);
    BatchRefPane batchref = (BatchRefPane) batchcodeitem.getComponent();
    BatchRefDlg refdlg = batchref.getBatchRefDlg();

    String cmaterialvid =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CMATERIALVID);
    BatchRefViewVO batchvo = refdlg.getRefVO(cmaterialvid, editvalue);
    if (PubAppTool.isNull(editvalue) || null == batchvo) {
      keyValue.setBodyValue(row, PreOrderBVO.VBATCHCODE, null);
      keyValue.setBodyValue(row, PreOrderBVO.PK_BATCHCODE, null);
    }
    else {
      keyValue.setBodyValue(row, PreOrderBVO.VBATCHCODE,
          batchvo.getAttributeValue(BatchcodeVO.VBATCHCODE));
      keyValue.setBodyValue(row, PreOrderBVO.PK_BATCHCODE,
          batchvo.getAttributeValue(BatchcodeVO.PK_BATCHCODE));
    }
  }

  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    int row = e.getRow();
    String materialvid =
        keyValue.getBodyStringValue(row, PreOrderBVO.CMATERIALVID);
    String sendstock =
        keyValue.getBodyStringValue(row, PreOrderBVO.CSENDSTOCKORGID);
    if (PubAppTool.isNull(materialvid) || PubAppTool.isNull(sendstock)) {
      e.setReturnValue(false);
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
      e.setReturnValue(false);
      return;
    }
    String sendstordoc =
        keyValue.getBodyStringValue(row, PreOrderBVO.CSENDSTORDOCID);
    String materialoid =
        keyValue.getBodyStringValue(row, PreOrderBVO.CMATERIALID);
    String astunit = keyValue.getBodyStringValue(row, PreOrderBVO.CASTUNITID);
    String vbatchcode =
        keyValue.getBodyStringValue(row, PreOrderBVO.VBATCHCODE);

    BillItem batchcodeitem = cardPanel.getBodyItem(PreOrderBVO.VBATCHCODE);
    BatchRefPane batchref = (BatchRefPane) batchcodeitem.getComponent();
    BatchDlgParam param = new BatchDlgParam();
    param.setPk_calbody(sendstock);
    param.setCwarehouseid(sendstordoc);
    param.setCmaterialvid(materialvid);
    param.setCmaterialoid(materialoid);
    param.setVbatchcode(vbatchcode);
    param.setCastUnitID(astunit);
    LoginContext context = this.getEditor().getModel().getContext();
    param.setLoginContext(context);
    batchref.setParam(param);
    batchref.setMultiSelectedEnabled(false);
  }

  public PreOrderEditor getEditor() {
    return this.editor;
  }

  public void setEditor(PreOrderEditor editor) {
    this.editor = editor;
  }

}
