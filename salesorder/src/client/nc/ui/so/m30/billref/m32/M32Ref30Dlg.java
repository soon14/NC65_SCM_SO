package nc.ui.so.m30.billref.m32;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

@SuppressWarnings("serial")
public class M32Ref30Dlg extends SourceRefDlg {

	public M32Ref30Dlg(Container parent, BillSourceVar bsVar) {
		super(parent, bsVar, true);
	}

	@Override
	public String getRefBillInfoBeanPath() {
		return "nc/ui/so/m30/billref/m32/M32Ref30Info.xml";
	}

}
