package nc.ui.so.m30.billui.action.line;

import java.awt.event.ActionEvent;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.BodyPasteLineAction;
import nc.ui.so.m30.billui.rule.CmffilePasteRule;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.util.SpecialBusiUtil;

/**
 * 复制行时判断是否进行询价
 * 
 * @author quyt
 * 
 */
public class SaleOrderPasteLineAction extends BodyPasteLineAction {

  /**
   * 
   */
  private static final long serialVersionUID = -6298734043503954122L;

  private SaleOrderBillForm editor;

  public SaleOrderBillForm getEditor() {
    return editor;
  }

  public void setEditor(SaleOrderBillForm editor) {
    this.editor = editor;
  }

  @Override
  public void doAction() {
    super.doAction();
    // TODO 刘景 、吴停、刘达讨论结论，复制行不询价。
    CmffilePasteRule pastRule = new CmffilePasteRule();
    pastRule.process(getCardPanel(), this.lastPastedRow());
    
  }
  
  @Override
  protected boolean doBeforeAction(ActionEvent e) {

	  boolean isdo = super.doBeforeAction(e);
	  if(isdo){
	  	//==== lijj 添加是否有下游进口合同的判断=====
		SpecialBusiUtil busiUtil = new SpecialBusiUtil();
		SaleOrderVO bill = (SaleOrderVO) this.getModel().getSelectedData();
	
		if(bill != null){
			boolean hasLowerBill = busiUtil.hasLowerBill(bill.getPrimaryKey());
			if(hasLowerBill){
				MessageDialog.showHintDlg(null, "提示", "已生成进口合同不能进行此操作！");
				return false;
			}
		}
		//==== lijj 添加是否有下游进口合同的判断=====
	  }
	  
	  return true;
  }
}
