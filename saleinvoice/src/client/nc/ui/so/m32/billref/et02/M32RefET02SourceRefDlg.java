package nc.ui.so.m32.billref.et02;



import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

public class M32RefET02SourceRefDlg extends SourceRefDlg{

	/**
	 * 
	 */
	private static final long serialVersionUID = -78224815140148315L;

	public M32RefET02SourceRefDlg(Container parent, BillSourceVar bsVar) {
		super(parent, bsVar);
	}

	@Override
	public String getRefBillInfoBeanPath() {
		return "nc/ui/so/m32/billref/et02/M32RefET02Info.xml";
	}
	
}
