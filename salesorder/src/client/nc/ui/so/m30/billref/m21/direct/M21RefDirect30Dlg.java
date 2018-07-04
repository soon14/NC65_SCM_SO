package nc.ui.so.m30.billref.m21.direct;

import java.awt.Container;

import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;

/**
 * 销售订单为采购订单提供的转单框
 * 
 * @since 6.0
 * @version 2011-12-2 上午09:00:48
 * @author fengjb
 */
public class M21RefDirect30Dlg extends SourceRefDlg {

	/**
   * 
   */
	private static final long serialVersionUID = -7754492890362710411L;

	public M21RefDirect30Dlg(Container parent, BillSourceVar bsVar) {
		super(parent, bsVar, true);
	}

	@Override
	public String getRefBillInfoBeanPath() {
		return "nc/ui/so/m30/billref/m21/direct/M21RefDirect30Info.xml";
	}
}
