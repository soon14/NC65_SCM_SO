package nc.ui.so.m30.billui.cash.editor.bodyevent;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.so.m30.billui.cash.model.CashSaleSobalanceModel;
import nc.ui.so.m30.billui.cash.view.CashSaleTopPanel;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class ThisBalMnyEditHandler {
  private CashSaleSobalanceModel sobalanceModel;

  private CashSaleTopPanel topPanel;

  public void afterEdit(CardBodyAfterEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int row = e.getRow();
    UFDouble thisbalmny =
        keyValue.getBodyUFDoubleValue(row, SoBalanceBVO.NORIGTHISBALMNY);
    UFDouble oldTotalBalmny =
        (UFDouble) this.topPanel.getOrgsummnyTextField().getValue();

    oldTotalBalmny =
        MathTool.sub(oldTotalBalmny, (UFDouble) this.topPanel
            .getSummnyTextField().getValue());

    UFDouble oldThisBalMny =
        (UFDouble) this.topPanel.getHxmnyTextField().getValue();
    if (null == oldThisBalMny) {
      oldThisBalMny = UFDouble.ZERO_DBL;
    }
    UFDouble newBalMny = thisbalmny;

    if (MathTool.absCompareTo(newBalMny, oldTotalBalmny) > 0) {
      MessageDialog
          .showHintDlg(
              cardPanel,
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006011_0", "04006011-0010")/*@res "提示"*/,
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006011_0", "04006011-0024")/*@res "本次核销金额不能大于可收款单核销金额！"*/);
      keyValue.setBodyValue(row, SoBalanceBVO.NORIGTHISBALMNY, oldThisBalMny);
      cardPanel.execBodyFormula(row, SoBalanceBVO.NORIGTHISBALMNY);
      return;
    }
    else if (MathTool.isDiffSign(newBalMny, oldTotalBalmny)) {
      MessageDialog
          .showHintDlg(
              cardPanel,
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006011_0", "04006011-0010")/*@res "提示"*/,
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4006011_0", "04006011-0025")/*@res "本次核销金额不能与收款单金额符号相反！"*/);
      keyValue.setBodyValue(row, SoBalanceBVO.NORIGTHISBALMNY, oldThisBalMny);
      cardPanel.execBodyFormula(row, SoBalanceBVO.NORIGTHISBALMNY);
      return;
    }

    this.updateTopPanelBalMny(MathTool.sub(thisbalmny,
        (UFDouble) e.getOldValue()));
  }

  public CashSaleSobalanceModel getSobalanceModel() {
    return this.sobalanceModel;
  }

  public CashSaleTopPanel getTopPanel() {
    return this.topPanel;
  }

  public void setSobalanceModel(CashSaleSobalanceModel sobalanceModel) {
    this.sobalanceModel = sobalanceModel;
  }

  public void setTopPanel(CashSaleTopPanel topPanel) {
    this.topPanel = topPanel;
  }

  private void updateTopPanelBalMny(UFDouble changeMny) {
    UFDouble hxmny = (UFDouble) this.topPanel.getHxmnyTextField().getValue();
    this.topPanel.getHxmnyTextField().setValue(MathTool.add(hxmny, changeMny));
    // 计算本次收款金额
    UFDouble orgsummny =
        (UFDouble) this.topPanel.getOrgsummnyTextField().getValue();
    UFDouble summny = (UFDouble) this.topPanel.getSummnyTextField().getValue();
    UFDouble hxmny_new =
        (UFDouble) this.topPanel.getHxmnyTextField().getValue();
    this.topPanel.getThisreceivemnyTextField().setValue(
        MathTool.sub(orgsummny, MathTool.add(summny, hxmny_new)));
  }
}
