package nc.ui.so.salequotation.billref.mz3;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

public class MZ3Ref4310Dlg extends SourceRefDlg {

	/**
   * 
   */
	private static final long serialVersionUID = -5941749394140338864L;

	public MZ3Ref4310Dlg(Container parent, BillSourceVar bsVar) {
		super(parent, bsVar, true);
	}

	@Override
	public String getRefBillInfoBeanPath() {
		return "nc/ui/so/salequotation/billref/mz3/MZ3Ref4310Info.xml";
	}
}
