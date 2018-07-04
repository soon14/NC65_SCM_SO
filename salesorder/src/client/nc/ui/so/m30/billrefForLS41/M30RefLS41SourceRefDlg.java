package nc.ui.so.m30.billrefForLS41;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

/*来源单据显示类*/
public class M30RefLS41SourceRefDlg extends SourceRefDlg {

	private static final long serialVersionUID = 3581432494198863324L;

	public M30RefLS41SourceRefDlg(Container parent, BillSourceVar bsVar) {
		super(parent, bsVar);
	}

	@Override
	public String getRefBillInfoBeanPath() {
		return "nc/ui/so/m30/billrefForLS41/LS41RefQg28Info.xml";
	}
}