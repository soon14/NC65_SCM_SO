package nc.ui.so.m32.billref.et03;



import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

public class M32RefET03SourceRefDlg extends SourceRefDlg{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8282594188338309001L;

	public M32RefET03SourceRefDlg(Container parent, BillSourceVar bsVar) {
		super(parent, bsVar);
	}

	@Override
	public String getRefBillInfoBeanPath() {
		return "nc/ui/so/m32/billref/et03/M32RefET03Info.xml";
	}
	
}
