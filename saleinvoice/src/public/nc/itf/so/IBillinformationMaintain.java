package nc.itf.so;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.so.billinformation.AggBillInforMationVO;
import nc.vo.pub.BusinessException;

public interface IBillinformationMaintain {

	public void delete(AggBillInforMationVO[] clientFullVOs,
			AggBillInforMationVO[] originBills) throws BusinessException;

	public AggBillInforMationVO[] insert(AggBillInforMationVO[] clientFullVOs,
			AggBillInforMationVO[] originBills) throws BusinessException;

	public AggBillInforMationVO[] update(AggBillInforMationVO[] clientFullVOs,
			AggBillInforMationVO[] originBills) throws BusinessException;

	public AggBillInforMationVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggBillInforMationVO[] save(AggBillInforMationVO[] clientFullVOs,
			AggBillInforMationVO[] originBills) throws BusinessException;

	public AggBillInforMationVO[] unsave(AggBillInforMationVO[] clientFullVOs,
			AggBillInforMationVO[] originBills) throws BusinessException;

	public AggBillInforMationVO[] approve(AggBillInforMationVO[] clientFullVOs,
			AggBillInforMationVO[] originBills) throws BusinessException;

	public AggBillInforMationVO[] unapprove(AggBillInforMationVO[] clientFullVOs,
			AggBillInforMationVO[] originBills) throws BusinessException;
}
