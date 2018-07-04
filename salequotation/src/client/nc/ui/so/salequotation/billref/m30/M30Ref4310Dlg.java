package nc.ui.so.salequotation.billref.m30;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

public class M30Ref4310Dlg extends SourceRefDlg {

	private static final long serialVersionUID = 1686654751526760488L;

	public M30Ref4310Dlg(Container parent, BillSourceVar bsVar) {
		super(parent, bsVar, true);
	}

	@Override
	public String getRefBillInfoBeanPath() {
		return "nc/ui/so/salequotation/billref/m30/M30Ref4310Info.xml";
	}
}
