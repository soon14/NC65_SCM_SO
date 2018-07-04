package nc.ui.so.m30.revise.action.line;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction;
import nc.ui.so.m30.billui.rule.CmffilePasteRule;
import nc.ui.so.m30.revise.rule.RevisePasteLineRule;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

public class SaleOrderRevisePasteToTailAction extends BodyPasteToTailAction {

  /**
   * 
   */
  private static final long serialVersionUID = 8172226653634419905L;

  private void initClearItems() {
    SaleOrderVO vo = (SaleOrderVO) this.getModel().getSelectedData();
    SaleOrderHVO hvo = vo.getParentVO();
    RevisePasteLineRule pasteLineRule = new RevisePasteLineRule();
    List<String> clearItems = new ArrayList<String>();
    clearItems.addAll(pasteLineRule.getClearItems());
    if (null != hvo.getBoffsetflag() && hvo.getBoffsetflag().booleanValue()) {
      clearItems.addAll(pasteLineRule.getClearItemsWhenOffSet());
    }
    this.setClearItems(clearItems);
  }

  @Override
  public void doAction() {
    this.initClearItems();
    super.doAction();
    int lastPastedRow = this.lastPastedRow();
    int rowlength =
        this.getCardPanel().getBodyPanel().getTableModel().getPasteLineNumer();
    this.setDefaultValue(lastPastedRow, rowlength);
    CmffilePasteRule pastRule = new CmffilePasteRule();
    pastRule.process(getCardPanel(), lastPastedRow);
  }

  private void setDefaultValue(int lastPastedRow, int rowlength) {
    for (int line = 0; line < rowlength; line++) {
      int row = lastPastedRow - line;
      UFDate date = AppContext.getInstance().getBusiDate();
      this.getCardPanel().getBillModel()
          .setValueAt(date, row, SaleOrderBVO.DBILLDATE);
      this.getCardPanel().getBillModel()
          .setValueAt(date.asLocalEnd(), row, SaleOrderBVO.DSENDDATE);
      this.getCardPanel().getBillModel()
          .setValueAt(date.asLocalEnd(), row, SaleOrderBVO.DRECEIVEDATE);
    }
  }
}
