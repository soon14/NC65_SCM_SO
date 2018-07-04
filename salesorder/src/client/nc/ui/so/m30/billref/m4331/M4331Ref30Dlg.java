package nc.ui.so.m30.billref.m4331;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

public class M4331Ref30Dlg extends SourceRefDlg {
	/**
   * 
   */
	private static final long serialVersionUID = -5821101352882501506L;

	public M4331Ref30Dlg(Container parent, BillSourceVar bsVar) {
		super(parent, bsVar, true);
	}

	@Override
	public String getRefBillInfoBeanPath() {
		return "nc/ui/so/m30/billref/m4331/M4331Ref30Info.xml";
	}
}
