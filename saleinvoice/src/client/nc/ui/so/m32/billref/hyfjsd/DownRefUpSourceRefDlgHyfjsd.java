package nc.ui.so.m32.billref.hyfjsd;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

public class DownRefUpSourceRefDlgHyfjsd extends SourceRefDlg{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5580573907313290422L;

	public DownRefUpSourceRefDlgHyfjsd(Container parent, BillSourceVar bsVar) {
		super(parent, bsVar);
	}

	@Override
	public String getRefBillInfoBeanPath() {
		return "nc/ui/so/m32/billref/hyfjsd/DownRefUpInfoHyfjsd.xml";
	}
	
}
