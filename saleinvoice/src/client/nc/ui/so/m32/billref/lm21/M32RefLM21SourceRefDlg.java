package nc.ui.so.m32.billref.lm21;



import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

public class M32RefLM21SourceRefDlg extends SourceRefDlg{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8282594188338309001L;

	public M32RefLM21SourceRefDlg(Container parent, BillSourceVar bsVar) {
		super(parent, bsVar);
	}

	@Override
	public String getRefBillInfoBeanPath() {
		return "nc/ui/so/m32/billref/lm21/M32RefLM21Info.xml";
	}
	
}
