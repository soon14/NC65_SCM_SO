package nc.ui.so.m30.billref.m4c;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

@SuppressWarnings("serial")
public class M4CRef30Dlg extends SourceRefDlg {

	public M4CRef30Dlg(Container parent, BillSourceVar bsVar) {
		super(parent, bsVar, true);
	}

	@Override
	public String getRefBillInfoBeanPath() {
		return "nc/ui/so/m30/billref/m4c/M4CRef30Info.xml";
	}

}
