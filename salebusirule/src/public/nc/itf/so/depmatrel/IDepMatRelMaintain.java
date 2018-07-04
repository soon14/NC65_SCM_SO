/**
 * $文件说明$
 * 
 * @author gdsjw
 * @version
 * @see
 * @since
 * @time 2010-6-24 下午04:21:18
 */
package nc.itf.so.depmatrel;

import nc.vo.pub.BusinessException;
import nc.vo.so.depmatrel.entity.DepMatRelVO;

/**
 * @author gdsjw
 */
public interface IDepMatRelMaintain {
  DepMatRelVO queryByOrg(String pk_org) throws BusinessException;

  DepMatRelVO update(DepMatRelVO bill) throws BusinessException;

  DepMatRelVO insert(DepMatRelVO bill) throws BusinessException;

  DepMatRelVO delete(DepMatRelVO bill) throws BusinessException;

}
