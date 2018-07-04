package nc.ui.so.m30.billui.action.line;

import java.awt.event.ActionEvent;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.BodyInsertLineAction;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.util.SpecialBusiUtil;

public class SaleOrderBodyInsertLineAction extends BodyInsertLineAction {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 149015179958455211L;

	  @Override
	  protected boolean doBeforeAction(ActionEvent e) {
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
		  
		  return super.doBeforeAction(e);
	  }
	
}
