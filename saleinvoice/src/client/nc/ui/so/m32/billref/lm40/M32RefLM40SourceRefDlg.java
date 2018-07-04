package nc.ui.so.m32.billref.lm40;



import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

public class M32RefLM40SourceRefDlg extends SourceRefDlg{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6050939599256758056L;

	public M32RefLM40SourceRefDlg(Container parent, BillSourceVar bsVar) {
		super(parent, bsVar);
	}

	@Override
	public String getRefBillInfoBeanPath() {
		return "nc/ui/so/m32/billref/lm40/M32RefLM40Info.xml";
	}
	
}
