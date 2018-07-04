package nc.ui.so.m38.billref.m30;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

public class M30Ref38SourceRefDlg extends SourceRefDlg {
	/**
   * 
   */
	private static final long serialVersionUID = 3581432494198863324L;

	public M30Ref38SourceRefDlg(Container parent, BillSourceVar bsVar) {
		super(parent, bsVar, true);
	}

	@Override
	public String getRefBillInfoBeanPath() {
		return "nc/ui/so/m38/billref/m30/M30Ref38Info.xml";
	}

}
