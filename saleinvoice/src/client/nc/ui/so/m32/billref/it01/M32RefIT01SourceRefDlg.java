package nc.ui.so.m32.billref.it01;



import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

public class M32RefIT01SourceRefDlg extends SourceRefDlg{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2157269761608420566L;

	public M32RefIT01SourceRefDlg(Container parent, BillSourceVar bsVar) {
		super(parent, bsVar);
	}

	@Override
	public String getRefBillInfoBeanPath() {
		return "nc/ui/so/m32/billref/it01/M32RefIT01Info.xml";
	}
	
}
