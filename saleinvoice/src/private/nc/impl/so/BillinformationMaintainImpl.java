package nc.impl.so;

import nc.impl.pub.ace.AceBillinformationPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.so.billinformation.AggBillInforMationVO;
import nc.itf.so.IBillinformationMaintain;
import nc.vo.pub.BusinessException;

public class BillinformationMaintainImpl extends AceBillinformationPubServiceImpl
		implements IBillinformationMaintain {

	@Override
	public void delete(AggBillInforMationVO[] clientFullVOs,
			AggBillInforMationVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggBillInforMationVO[] insert(AggBillInforMationVO[] clientFullVOs,
			AggBillInforMationVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggBillInforMationVO[] update(AggBillInforMationVO[] clientFullVOs,
			AggBillInforMationVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggBillInforMationVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggBillInforMationVO[] save(AggBillInforMationVO[] clientFullVOs,
			AggBillInforMationVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggBillInforMationVO[] unsave(AggBillInforMationVO[] clientFullVOs,
			AggBillInforMationVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggBillInforMationVO[] approve(AggBillInforMationVO[] clientFullVOs,
			AggBillInforMationVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggBillInforMationVO[] unapprove(AggBillInforMationVO[] clientFullVOs,
			AggBillInforMationVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
