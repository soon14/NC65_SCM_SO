package nc.ui.so.m30.billref.m21.coop;

import java.awt.Container;

import nc.ui.pub.bill.BillUIUtil;
import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.view.SourceRefDlg;
import nc.vo.pub.bill.BillTempletVO;

public class CoopRefDlg extends SourceRefDlg {

	/**
   * 
   */
	private static final long serialVersionUID = -7754492890362710411L;

	public CoopRefDlg(Container parent, BillSourceVar bsVar) {
		super(parent, bsVar, true);
	}

	@Override
	public void addBillUI() {
		try {
			String[] nodes = new String[2];
			nodes[0] = this.getRefContext().getRefInfo().getBillNodeKey();
			nodes[1] = this.getRefContext().getRefInfo().getBillViewNodeKey();

			BillTempletVO bill = BillUIUtil.getDefaultTempletStatic("30",
					this.getRefContext().getRefInfo().getBillSrcVar()
							.getBillType(), this.getRefContext().getRefInfo()
							.getBillSrcVar().getUserId(), this.getRefContext()
							.getRefInfo().getBillSrcVar().getPk_group(),
					nodes[0], null);
			BillTempletVO view = BillUIUtil.getDefaultTempletStatic("30",
					this.getRefContext().getRefInfo().getBillSrcVar()
							.getBillType(), this.getRefContext().getRefInfo()
							.getBillSrcVar().getUserId(), this.getRefContext()
							.getRefInfo().getBillSrcVar().getPk_group(),
					nodes[1], null);

			if (bill != null && view != null) {
				BillTempletVO[] vos = new BillTempletVO[2];
				vos[0] = bill;
				vos[1] = view;
				this.getRefContext().setBillTemplate(vos[0]);
				if (vos.length > 1) {
					this.getRefContext().setBillViewTemplate(vos[1]);
				}
			}
			this.getUIDialogContentPane().add(this.getRefBill(), "Center");
		} catch (Exception ivjExc) {
			this.handleException(ivjExc);
		}
	}

	@Override
	public String getRefBillInfoBeanPath() {
		return "nc/ui/so/m30/billref/m21/coop/cooprefinfo.xml";
	}
}
