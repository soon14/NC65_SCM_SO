package nc.ui.so.m4331.billui.editor.bodyevent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.Action;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.ui.ic.batchcode.ref.BatchRefDlg;
import nc.ui.ic.batchcode.ref.BatchRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillScrollPane;
import nc.ui.pub.bill.action.BillTableLineAction;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.m4331.billui.pub.calculator.DeliveryCardCalculator;
import nc.ui.so.m4331.billui.view.DeliveryEditor;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.util.SOFillParamForICUtil;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.ic.batch.BatchRefViewVO;
import nc.vo.ic.batchcode.BatchDlgParam;
import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.ic.onhand.entity.OnhandVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmf.ic.mbatchcode.BatchcodeVO;
import nc.vo.scmpub.util.ListUtil;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 批次号编辑事件
 * 
 * @since 6.0
 * @version 2011-3-24 上午11:52:23
 * @author 祝会征
 */
@SuppressWarnings("restriction")
public class BatchCodeEditHandler {

  private DeliveryEditor editor;

  /**
   * 批次号编辑前处理事件
   * 
   * @param e
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int row = e.getRow();
    String materialvid =
        keyValue.getBodyStringValue(row, DeliveryBVO.CMATERIALVID);
    String sendstock =
        keyValue.getBodyStringValue(row, DeliveryBVO.CSENDSTOCKORGID);
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
    BillItem batchcodeitem = cardPanel.getBodyItem(SaleOrderBVO.VBATCHCODE);
    BatchRefPane batchref = (BatchRefPane) batchcodeitem.getComponent();
    BatchDlgParam param =
        SOFillParamForICUtil.getBatchDlgParam(this.getEditor(), keyValue, row);
    batchref.setParam(param);
    batchref.setMultiSelectedEnabled(false);
  }

  public DeliveryEditor getEditor() {
    return this.editor;
  }

  public void setEditor(DeliveryEditor editor) {
    this.editor = editor;
  }

  public void afterEdit(CardBodyAfterEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    String editvalue = (String) e.getValue();
    int row = e.getRow();
    if (PubAppTool.isNull(editvalue)) {
      keyValue.setBodyValue(row, DeliveryBVO.VBATCHCODE, null);
      keyValue.setBodyValue(row, DeliveryBVO.PK_BATCHCODE, null);
      return;
    }
    UFDouble oldNnum = keyValue.getBodyUFDoubleValue(row, DeliveryBVO.NNUM);
    BillItem batchcodeitem = cardPanel.getBodyItem(DeliveryBVO.VBATCHCODE);
    BatchRefPane batchref = (BatchRefPane) batchcodeitem.getComponent();
    BatchRefDlg refdlg = batchref.getBatchRefDlg();
    BatchRefViewVO[] batchvos = ListUtil.toArray(batchref.getRefVOs());
    // 贵州中铝批次号参照问题 任科 2014/1/24 实现批次多选自动增行并携带批次号重量功能
    // 是否手工编辑（手工编辑情况：没有点击参照确定按钮；点确定按钮后，在焦点不离开时手动编辑了单元格文本）
    UFBoolean isByHand = UFBoolean.FALSE;
    if (!batchref.isClicked()) {
      isByHand = UFBoolean.TRUE;
    }else {
      String batchCode =
          (String) batchref.getRefVOs().get(0).getAttributeValue("vbatchcode");
      if (PubAppTool.isNull(editvalue) || !editvalue.equals(batchCode + ".")) {
        isByHand = UFBoolean.TRUE;
      }
    }
    if (UFBoolean.TRUE.equals(isByHand)) {
      String cmaterialvid =
          keyValue.getBodyStringValue(row, DeliveryBVO.CMATERIALVID);
      // 手工录入时候
      BatchDlgParam param =
          SOFillParamForICUtil.getBatchDlgParam(this.getEditor(), keyValue, row);
      param.setIsQueryZeroLot(UFBoolean.TRUE);
      refdlg.setParam(param);
      BatchRefViewVO batchvo = refdlg.getRefVO(cmaterialvid, editvalue);
      if (batchvo != null) {
        keyValue.setBodyValue(row, DeliveryBVO.VBATCHCODE,
            batchvo.getAttributeValue(BatchcodeVO.VBATCHCODE));
        keyValue.setBodyValue(row, DeliveryBVO.PK_BATCHCODE,
            batchvo.getAttributeValue(BatchcodeVO.PK_BATCHCODE));
      }
      else {
        keyValue.setBodyValue(row, DeliveryBVO.VBATCHCODE, e.getOldValue());
      }
      return;
    }

    int dealrow = row;
    int i = 1;
    DeliveryCardCalculator calculator =
        new DeliveryCardCalculator(e.getBillCardPanel());
    UFDouble allBatchNum = UFDouble.ZERO_DBL;
    List<Integer> rows = new ArrayList<Integer>();
    for (BatchRefViewVO batchvo : batchvos) {
      if (i > 1) {
        dealrow = copyLine(cardPanel, keyValue);
      }
      allBatchNum =
          MathTool.add(allBatchNum,
              (UFDouble) batchvo.getAttributeValue(OnhandVO.NNUM));
      this.setBodyValue(keyValue, dealrow, batchvo);
      if (isBatchCodeRef(batchvo)) {
        continue;
      }
      keyValue.setBodyValue(dealrow, DeliveryBVO.NNUM,
          batchvo.getAttributeValue(OnhandVO.NNUM));
      rows.add(dealrow);
      i++;
    }
    if (MathTool.compareTo(oldNnum, allBatchNum) > 0
        && !isBatchCodeRef(batchvos[0])) {
      dealrow = copyLine(cardPanel, keyValue);
      keyValue.setBodyValue(dealrow, DeliveryBVO.NNUM,
          MathTool.sub(oldNnum, allBatchNum));
      keyValue.setBodyValue(dealrow, DeliveryBVO.VBATCHCODE, null);
      keyValue.setBodyValue(dealrow, DeliveryBVO.PK_BATCHCODE, null);
      rows.add(dealrow);
    }
    calculator.calculate(rows, DeliveryBVO.NNUM);
    if (batchref.isClicked()) {
      batchref.setClicked(false);
    }
  }

  /**
   * 批次参照是否参照的是批次档案
   * 
   * @param batchvos
   * @return
   */
  private boolean isBatchCodeRef(BatchRefViewVO batchvo) {
    if (!PubAppTool.isNull((String) batchvo.getAttributeValue("cmaterialoid"))) {
      return false;
    }
    return true;
  }

  private int copyLine(BillCardPanel cardPanel, IKeyValue keyValue) {
    int dealrow;
    BillScrollPane scrollPane = cardPanel.getBodyPanel();
    Action copyAction =
        scrollPane.getBillTableAction(BillTableLineAction.COPYLINE);
    copyAction.actionPerformed(null);
    Action pasteAction =
        scrollPane.getBillTableAction(BillTableLineAction.PASTELINE);
    pasteAction.actionPerformed(null);

    dealrow = cardPanel.getBillTable().getSelectedRow();
    keyValue.setBodyValue(dealrow, DeliveryBVO.CDELIVERYBID, null);
    return dealrow;
  }

  private void setBodyValue(IKeyValue keyValue, int dealrow,
      BatchRefViewVO batchvo) {
    keyValue.setBodyValue(dealrow, DeliveryBVO.VBATCHCODE,
        batchvo.getAttributeValue(BatchcodeVO.VBATCHCODE));
    keyValue.setBodyValue(dealrow, DeliveryBVO.PK_BATCHCODE,
        batchvo.getAttributeValue(BatchcodeVO.PK_BATCHCODE));
    keyValue.setBodyValue(dealrow, DeliveryBVO.VFREE1,
        batchvo.getAttributeValue(OnhandDimVO.VFREE1));
    keyValue.setBodyValue(dealrow, DeliveryBVO.VFREE2,
        batchvo.getAttributeValue(OnhandDimVO.VFREE2));
    keyValue.setBodyValue(dealrow, DeliveryBVO.VFREE3,
        batchvo.getAttributeValue(OnhandDimVO.VFREE3));
    keyValue.setBodyValue(dealrow, DeliveryBVO.VFREE4,
        batchvo.getAttributeValue(OnhandDimVO.VFREE4));
    keyValue.setBodyValue(dealrow, DeliveryBVO.VFREE5,
        batchvo.getAttributeValue(OnhandDimVO.VFREE5));
    keyValue.setBodyValue(dealrow, DeliveryBVO.VFREE6,
        batchvo.getAttributeValue(OnhandDimVO.VFREE6));
    keyValue.setBodyValue(dealrow, DeliveryBVO.VFREE7,
        batchvo.getAttributeValue(OnhandDimVO.VFREE7));
    keyValue.setBodyValue(dealrow, DeliveryBVO.VFREE8,
        batchvo.getAttributeValue(OnhandDimVO.VFREE8));
    keyValue.setBodyValue(dealrow, DeliveryBVO.VFREE9,
        batchvo.getAttributeValue(OnhandDimVO.VFREE9));
    keyValue.setBodyValue(dealrow, DeliveryBVO.VFREE10,
        batchvo.getAttributeValue(OnhandDimVO.VFREE10));
  }
}
