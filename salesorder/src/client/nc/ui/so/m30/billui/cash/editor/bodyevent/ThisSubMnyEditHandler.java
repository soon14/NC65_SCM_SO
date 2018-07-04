package nc.ui.so.m30.billui.cash.editor.bodyevent;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m35.entity.ArsubBVO;
import nc.vo.so.m35.entity.ArsubViewVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m30.billui.cash.model.CashSaleArsubModel;
import nc.ui.so.m30.billui.cash.view.CashSaleTopPanel;
import nc.ui.so.pub.keyvalue.CardKeyValue;

public class ThisSubMnyEditHandler {

  private CashSaleArsubModel arsubModel;

  private CashSaleTopPanel topPanel;

  public void afterEdit(CardBodyAfterEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int row = e.getRow();

    UFDouble thisSubMny =
        keyValue.getBodyUFDoubleValue(row, ArsubBVO.NTHISSUBMNY);
    String carsubbid = keyValue.getBodyStringValue(row, ArsubBVO.CARSUBBID);
    ArsubViewVO view = this.arsubModel.getArsubViewVOMap().get(carsubbid);
    UFDouble oldTotalSubMny = view.getItem().getNthissubmny();
    UFDouble oldSubValue = (UFDouble) e.getOldValue();
    UFDouble nowSubMny = view.getItem().getNremainmny();
    if (null == thisSubMny) {
      thisSubMny = UFDouble.ZERO_DBL;
    }
    /*if (MathTool.compareTo((UFDouble) this.topPanel
        .getThisreceivemnyTextField().getValue(), MathTool.sub(thisSubMny,
        oldSubValue)) < 0) {
      keyValue.setBodyValue(row, ArsubBVO.NTHISSUBMNY, oldSubValue);
      return;
    }*/

    if (MathTool.absCompareTo(thisSubMny, nowSubMny) > 0) {
      MessageDialog
          .showHintDlg(
              cardPanel,
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006011_0", "04006011-0010")/*@res "提示"*/,
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006011_0", "04006011-0026")/*@res "本次冲抵金额不能改大！"*/);
      keyValue.setBodyValue(row, ArsubBVO.NTHISSUBMNY, oldSubValue);
      return;
    }
    else if (MathTool.isDiffSign(thisSubMny, oldTotalSubMny)) {
      MessageDialog
          .showHintDlg(
              cardPanel,
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006011_0", "04006011-0010")/*@res "提示"*/,
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006011_0", "04006011-0027")/*@res "本次冲抵金额不能与冲抵金额符号相反！"*/);
      keyValue.setBodyValue(row, ArsubBVO.NTHISSUBMNY, oldSubValue);
      return;
    }

    this.updateTopPanelSubMny(MathTool.sub(thisSubMny, oldSubValue));
  }

  public CashSaleArsubModel getArsubModel() {
    return this.arsubModel;
  }

  public CashSaleTopPanel getTopPanel() {
    return this.topPanel;
  }

  public void setArsubModel(CashSaleArsubModel arsubModel) {
    this.arsubModel = arsubModel;
  }

  public void setTopPanel(CashSaleTopPanel topPanel) {
    this.topPanel = topPanel;
  }

  private void updateTopPanelSubMny(UFDouble changeMny) {
    UFDouble summny = (UFDouble) this.topPanel.getSummnyTextField().getValue();
    this.topPanel.getSummnyTextField().setValue(summny.add(changeMny));
    // 计算本次收款金额
    UFDouble orgsummny =
        (UFDouble) this.topPanel.getOrgsummnyTextField().getValue();
    UFDouble summny_new =
        (UFDouble) this.topPanel.getSummnyTextField().getValue();
    UFDouble hxmny = (UFDouble) this.topPanel.getHxmnyTextField().getValue();
    this.topPanel.getThisreceivemnyTextField().setValue(
        MathTool.sub(orgsummny, MathTool.add(summny_new, hxmny)));
  }

}
