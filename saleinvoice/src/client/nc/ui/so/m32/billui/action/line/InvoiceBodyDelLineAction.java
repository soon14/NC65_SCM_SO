package nc.ui.so.m32.billui.action.line;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.actions.BodyDelLineAction;
import nc.ui.so.m32.billui.pub.CardVATCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.vattax.vo.CalVatFieldValues;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.util.HeadTotalCalcUtil;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.util.SOMathUtil;

/**
 * 发票删行控制
 * 
 * 行已经有冲抵金额此行不能删除
 * 
 * @since 6.0
 * @version 2011-9-8 下午06:47:56
 * @author 么贵敬
 */
public class InvoiceBodyDelLineAction extends BodyDelLineAction {

  private static final long serialVersionUID = -6452641588755419635L;

  @Override
  protected boolean doBeforeAction(ActionEvent e) {
    BillCardPanel cardPanel = this.getCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int[] srcrows = cardPanel.getBodyPanel().getTable().getSelectedRows();
    // 修订时检查行是否做过冲抵、做过冲抵不允许删除
    if (!this.checkCanDelOffsetRows(srcrows, keyValue)) {
      return false;
    }
    return super.doBeforeAction(e);
  }

  @Override
  public void doAction() {
    // 需要重新计算VAT信息
    BillCardPanel cardPanel = this.getCardPanel();
    int[] selrows = cardPanel.getBodyPanel().getTable().getSelectedRows();
    CardVATCalculator vatcal = new CardVATCalculator(cardPanel);
    CalVatFieldValues[] oldvalues = new CalVatFieldValues[selrows.length];
    int i = 0;
    for (int row : selrows) {
      oldvalues[i] = vatcal.getVatFieldValues(row);
      i++;
    }
    super.doAction();
    for (CalVatFieldValues oldvalue : oldvalues) {
      vatcal.calVatWhenDeleteLine(oldvalue);
    }
    // 修改处：zhangby5 未进行价税合计
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    HeadTotalCalcUtil.getInstance().calcHeadTotalValue(keyValue);
  }

  /**
   * 已经做过冲抵的行不允许删除
   * 
   * @param srcrows
   * @param keyValue
   * @return
   */
  private boolean checkCanDelOffsetRows(int[] srcrows, IKeyValue keyValue) {
    if (null == srcrows || srcrows.length == 0) {
      return true;
    }

    boolean combindelrow = false;
    for (int row : srcrows) {
      String csaleinvoicebid =
          keyValue.getBodyStringValue(row, SaleInvoiceBVO.CSALEINVOICEBID);
      if ("isnull".equals(csaleinvoicebid)) {
        combindelrow = true;
        break;
      }
    }
    if (combindelrow) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006008_0", "04006008-0117")/*非末级物料类行不允许删除*/);
    }

    List<String> offsetnotdelrow = new ArrayList<String>();
    for (int row : srcrows) {
      String rowno = keyValue.getBodyStringValue(row, SaleInvoiceBVO.CROWNO);
      UFDouble norigsubmny =
          keyValue.getBodyUFDoubleValue(row, SaleInvoiceBVO.NORIGSUBMNY);
      if (!SOMathUtil.isZero(norigsubmny)) {
        offsetnotdelrow.add(rowno);
        continue;
      }

    }
    if (offsetnotdelrow.size() == 0) {
      return true;
    }
    StringBuilder offseterrrows = new StringBuilder();

    for (String rowno : offsetnotdelrow) {
      offseterrrows.append("[" + rowno + "]、");/*-=notranslate=-*/
    }
    offseterrrows.deleteCharAt(offseterrrows.length() - 1);

    // fengjb 2012.03.05 UE提示规范
    ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
        "4006008_0", "04006008-0129", null, new String[] {
            offseterrrows.toString()
        })/*销售发票下列行:{0}已做过冲抵，不能删除*/);

    return false;
  }
}
