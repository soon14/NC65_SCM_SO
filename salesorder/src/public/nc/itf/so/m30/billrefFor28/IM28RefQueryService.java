/**
 * 
 */
package nc.itf.so.m30.billrefFor28;

import java.util.HashMap;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.it.m5801.entity.ContractVO;
import nc.vo.lm.lsdlxy.AggLsxywtHVO;
import nc.vo.pp.m28.entity.PriceAuditVO;
import nc.vo.pub.BusinessException;

/**
 * @author wangzym
 * @version 2017年6月6日 上午9:35:00
 */
public interface IM28RefQueryService {
	// 上游单据AggVo
	public PriceAuditVO[] query28For30(IQueryScheme queryScheme)
			throws BusinessException;
	// 上游单据AggVo
	public AggLsxywtHVO[] queryLS41For30(IQueryScheme queryScheme)
			throws BusinessException;

	// 删除时要判断下游单据是否已经生成进口合同，如果生成了则不允许删除，不管进口合同是否被删除（不判断dr=0）
	public HashMap<String, Integer> queryForSaleOrderDel(String pk_Head[])
			throws BusinessException;
}
