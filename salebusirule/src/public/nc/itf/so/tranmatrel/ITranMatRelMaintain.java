/**
 * $文件说明$
 * 
 * @author gdsjw
 * @version
 * @see
 * @since
 * @time 2010-6-24 下午04:21:18
 */
package nc.itf.so.tranmatrel;

import nc.vo.pub.BusinessException;
import nc.vo.so.tranmatrel.entity.TranMatRelVO;

/**
 * @author gdsjw
 */
public interface ITranMatRelMaintain {
  TranMatRelVO queryByOrg(String pk_org) throws BusinessException;

  TranMatRelVO update(TranMatRelVO bill) throws BusinessException;

  TranMatRelVO insert(TranMatRelVO bill) throws BusinessException;

  TranMatRelVO delete(TranMatRelVO bill) throws BusinessException;

}
