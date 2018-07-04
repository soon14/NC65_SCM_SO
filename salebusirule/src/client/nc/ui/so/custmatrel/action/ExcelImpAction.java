package nc.ui.so.custmatrel.action;

import java.awt.event.ActionEvent;

import javax.swing.JComponent;

import nc.bs.uif2.IActionCode;
import nc.itf.trade.excelimport.ImportableInfo;
import nc.ui.ls.MessageBox;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.trade.excelimport.ExcelImportInfo;
import nc.ui.trade.excelimport.ExcelImporter;

/**
 * 客户物料关系Excel导入
 * 
 * @since 6.3
 * @version 2013-05-16 08:58:11
 * @author liujingn
 */
public class ExcelImpAction extends nc.ui.uif2.excelimport.ImportAction {

	private static final long serialVersionUID = 6000014224018704919L;

	/**
   *
   */
	public ExcelImpAction() {
		super();
		SCMActionInitializer.initializeAction(this, IActionCode.IMPORT);
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		final JComponent parent = getModel().getContext().getEntranceUI();
		ImportableInfo info = super.getImportableEditor().getImportableInfo();
		if (info != null && (!info.isImportable())) {
			MessageDialog.showErrorDlg(
					parent,
					NCLangRes.getInstance().getStrByID("uif2",
							"ExceptionHandlerWithDLG-000000")/* 错误 */,
					NCLangRes.getInstance().getStrByID("uif2",
							"ImportAction-000000", null,
							new String[] { info.getCannotImportReason() })/*
																		 * 不可导入：{
																		 * 0}
																		 */);
			return;
		}
		final ExcelImporter i = new ExcelImporter();
		final ExcelImportInfo importInfo = i.importFromExcel(parent, super
				.getImportableEditor().getInputItems());
		if (importInfo == null) {
			return;
		}
		((CustMaterImportableEditor) getImportableEditor())
				.setToSaveVOs(importInfo.getVos());
		getImportableEditor().save();
		// 导入成功后显示提示信息
		MessageBox.showMessageDialog(NCLangRes.getInstance().getStrByID("4006007_0", "04006007-0036")/*提示*/, NCLangRes.getInstance().getStrByID("4006007_0", "04006007-0037")/*导入成功，请点击刷新按钮或者重新查询数据！*/);

	}

}
