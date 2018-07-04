package nc.bs.so.billinformation.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.so.billinformation.AggBillInforMationVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceBillinformationUnSendApproveBP {

	public AggBillInforMationVO[] unSend(AggBillInforMationVO[] clientBills,
			AggBillInforMationVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggBillInforMationVO> update = new BillUpdate<AggBillInforMationVO>();
		AggBillInforMationVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggBillInforMationVO[] clientBills) {
		for (AggBillInforMationVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
