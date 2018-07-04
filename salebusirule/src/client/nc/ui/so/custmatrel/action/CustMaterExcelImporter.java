package nc.ui.so.custmatrel.action;

import java.util.List;

import javax.swing.JComponent;

import nc.itf.trade.excelimport.IImportableEditor;
import nc.ui.pub.beans.UIDialog;
import nc.ui.trade.excelimport.BillItemImportSetDlg;
import nc.ui.trade.excelimport.ExcelImporter;
import nc.ui.trade.excelimport.InputItem;

/**
 * excel导入器
 * 
 * @since 6.1
 * @version 2013-12-10 14:49:31
 * @author liujingn
 */
public class CustMaterExcelImporter extends ExcelImporter {

	private String funcode;

	private boolean uidialogok = true;

	public boolean isUidialogok() {
		return uidialogok;
	}

	public void setUidialogok(boolean uidialogok) {
		this.uidialogok = uidialogok;
	}

	/**
	 * 
	 * @return funcode
	 */
	public String getFuncode() {
		return this.funcode;
	}

	/**
   *
   */
	@Override
	public void setFuncode(String funcode) {
		this.funcode = funcode;
		super.setFuncode(funcode);
	}

	@Override
	public boolean beforeExport(IImportableEditor editor) {
		boolean ret = false;
		JComponent parent = editor.getJComponent();

		this.setDlg(new BillItemImportSetDlg(parent, editor.getInputItems(),
				this.getFuncode(), null, false));
		// 导出模块添加特殊显示字段
		this.getDlg().setInputitemSeleStrategy(
				new CustMaterExportDataItemSeleStrategy());
		this.getDlg().initialize();

		if (this.getDlg().showModal() == UIDialog.ID_OK) {
			List<InputItem> items = this.getDlg().getInputItems();
			if (items == null || items.size() == 0) {
				return false;
			}
			ret = this.getSaveExcelFile(parent) != null;
		} else {
			this.setUidialogok(false);
		}

		return ret;
	}
	
	@Override
	public void exportToExcel(IImportableEditor editor) throws Exception {
		if(beforeExport(editor)){
			super.exportToExcel(editor, true);
		}
	}

}
