package nc.bs.so.billinformation.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.so.billinformation.AggBillInforMationVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceBillinformationUnApproveBP {

	public AggBillInforMationVO[] unApprove(AggBillInforMationVO[] clientBills,
			AggBillInforMationVO[] originBills) {
		for (AggBillInforMationVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggBillInforMationVO> update = new BillUpdate<AggBillInforMationVO>();
		AggBillInforMationVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
