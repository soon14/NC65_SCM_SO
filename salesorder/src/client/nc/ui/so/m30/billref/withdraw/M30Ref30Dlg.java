package nc.ui.so.m30.billref.withdraw;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

@SuppressWarnings("serial")
public class M30Ref30Dlg extends SourceRefDlg {

	public M30Ref30Dlg(Container parent, BillSourceVar bsVar) {
		super(parent, bsVar, true);
	}

	@Override
	public String getRefBillInfoBeanPath() {
		return "nc/ui/so/m30/billref/withdraw/M30Ref30Info.xml";
	}

}
