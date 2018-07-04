/**   
 * Copyright  2017 Yonyou. All rights reserved.
 * 
 * @Title: TransferLogicforSelf.java 
 * @Prject: SCM_SO
 * @Package: nc.ui.so.m30.billui.push 
 * @Description: TODO
 * @author: wangzy   
 * @date: 2017年12月27日 下午11:18:49 
 * @version: V6.5   
 */
package nc.ui.so.m30.billui.push;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.billref.dest.DefaultBillDataLogic;
import nc.ui.so.m30.billui.action.printaction.SaleOrderPreviewAction;
import nc.ui.uif2.UIState;

/**
 * @ClassName: TransferLogicforSelf
 * @Description: TODO
 * @author: wangzy
 * @date: 2017年12月27日 下午11:18:49
 */
public class TransferLogicforSelf extends DefaultBillDataLogic {
	@Override
	public void doTransferAddLogic(Object selectedData) {

		// 把数据设置到界面上
		super.doTransferAddLogic(selectedData);
		super.getBillForm().getModel().setUiState(UIState.NOT_EDIT);
		this.getBillForm().getModel().initModel(selectedData);
		//以后在进行优化
		/*SaleOrderPreviewAction action =new SaleOrderPreviewAction();
		action.setModel(this.getBillForm().getModel());
		try {
			action.doAction(new ActionEvent(this,1,null));
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		*/
	}
}