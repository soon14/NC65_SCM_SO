package nc.ui.so.m30.billref.m5801;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

/**
 * 
 * @since JCK 6.31
 * @version 2014-03-17 11:13:54
 * @author zhangyfr
 */
@SuppressWarnings("serial")
public class M5801Ref30Dlg extends SourceRefDlg {

	/**
	 * 
	 * @param parent
	 * @param bsVar
	 */
	public M5801Ref30Dlg(Container parent, BillSourceVar bsVar) {
		super(parent, bsVar, true);
	}

	@Override
	public String getRefBillInfoBeanPath() {
		return "nc/ui/so/m30/billref/m5801/M5801Ref30Info.xml";
	}

}
