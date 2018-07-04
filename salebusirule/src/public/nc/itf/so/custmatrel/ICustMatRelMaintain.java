/**
 * $文件说明$
 * 
 * @author gdsjw
 * @version
 * @see
 * @since
 * @time 2010-6-24 下午04:21:18
 */
package nc.itf.so.custmatrel;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.custmatrel.entity.CustMatRelVO;

/**
 * @author gdsjw
 */
public interface ICustMatRelMaintain {
  CustMatRelVO queryByOrg(String pk_org) throws BusinessException;

  CustMatRelVO update(CustMatRelVO bill) throws BusinessException;

  CustMatRelVO insert(CustMatRelVO bill) throws BusinessException;

  CustMatRelVO delete(CustMatRelVO bill) throws BusinessException;
	/**
	 * 根据where条件查询数据
	 * 
	 * @param queryScheme
	 * @return
	 * @throws BusinessException
	 */
	CustMatRelVO[] queryCustMatRel(IQueryScheme queryScheme)
			throws BusinessException;

	/**
	 * 导入XLS
	 * 
	 * @param bills
	 * @return
	 * @throws BusinessException
	 */
	void importXLS(CustMatRelVO[] bills) throws BusinessException;
}
