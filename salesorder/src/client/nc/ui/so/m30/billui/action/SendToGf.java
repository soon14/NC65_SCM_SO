/**
 * 
 */
package nc.ui.so.m30.billui.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.pubapp.pub.smart.IBillQueryService;
import nc.itf.so.m30.msg.ISend2Gf;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.query2.model.ModelDataRefresher;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.bd.meta.IBDObject;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @author wangzym
 * @version 2017年7月26日 上午10:54:19
 */
public class SendToGf extends NCAction {

	/*
	 * （非 Javadoc）
	 * 
	 * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
	 */
	private nc.ui.pubapp.uif2app.model.BillManageModel model;
	private nc.ui.so.m30.billui.view.SaleOrderBillForm editor;

	public SendToGf() {
		this.setBtnName("传股份");
		this.setCode("sendtogf");
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO 由于之前不是新增按钮传股份，为不影响之前的逻辑，在此直接远程调用
		this.checkOverPurchase((SaleOrderVO) editor.getValue());

		ISend2Gf sendData = NCLocator.getInstance().lookup(ISend2Gf.class);
		sendData.process(new SaleOrderVO[] { (SaleOrderVO) this.getModel()
				.getSelectedData() });
		// 刷新一下界面
		// new ModelDataRefresher(this.model).refreshData();
		// 修改刷新方法
		this.reFreshDate(model);

	}

	/**
	 * 校验存在超采给出提示
	 */
	private void checkOverPurchase(SaleOrderVO saleOrderVO) {
		// 是状态栏合适还是其他方式合适，状态栏最后会被刷新 导致可能看不到该条消息
		String msg = "第";
		SaleOrderBVO[] bvos = saleOrderVO.getChildrenVO();
		for (SaleOrderBVO saleOrderBVO : bvos) {
			String rowNo = saleOrderBVO.getCrowno();
			UFDouble nastNum = saleOrderBVO.getNastnum();
			Integer planNum = (int) saleOrderBVO.getAttributeValue("plan_num");
			int nastnum = nastNum.intValue();
			if (planNum != null && nastnum != planNum.intValue()) {
				msg += "[" + rowNo + "] ";
			}
		}
		if (!msg.equals("第")) {

			msg += "行 存在超采或少采！";
			MessageDialog.showWarningDlg(WorkbenchEnvironment.getInstance()
					.getWorkbench().getParent(), "提示", msg);
		}
	}

	/**
	 * @return model
	 */
	public nc.ui.pubapp.uif2app.model.BillManageModel getModel() {
		return model;
	}

	/**
	 * @param model
	 *            要设置的 model
	 */
	public void setModel(nc.ui.pubapp.uif2app.model.BillManageModel model) {
		this.model = model;
	}

	/**
	 * @return editor
	 */
	public nc.ui.so.m30.billui.view.SaleOrderBillForm getEditor() {
		return editor;
	}

	/**
	 * @param editor
	 *            要设置的 editor
	 */
	public void setEditor(nc.ui.so.m30.billui.view.SaleOrderBillForm editor) {
		this.editor = editor;
	}
	public static void reFreshDate(BillManageModel bmModel) throws Exception {
		Object data = bmModel.getSelectedData();
		if (data == null) {
			return;
		}
		String pk = "";
		int i = 0;
		Class<AbstractBill> clazz = null;
		IBDObject target = bmModel.getBusinessObjectAdapterFactory()
				.createBDObject(data);
		pk = (String) target.getId();
		clazz = (Class<AbstractBill>) data.getClass();
		if (clazz == null) {
			return;
		}

		// 注意：下面的写法只是暂时的写法，为了暂时完成CQ问题，这段代码以后肯定要修改的，在等待批查的接口
		// 否则会影响效率，产生很多连接数
		IBillQueryService billQuery = NCLocator.getInstance().lookup(
				IBillQueryService.class);
		AbstractBill bills = billQuery.querySingleBillByPk(clazz, pk);

		if (bills == null) {
			return;
		}
		bmModel.directlyUpdate(bills);
	}

}
