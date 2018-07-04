/**
 * $文件说明$
 * 
 * @author gdsjw
 * @version
 * @see
 * @since
 * @time 2010-6-24 下午04:21:18
 */
package nc.itf.so.m30.sobalance;

import java.util.Map;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>功能条目1
 * <li>功能条目2
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author gdsjw
 * @time 2010-6-24 下午04:21:18
 */
public interface ISOBalanceMaintain {
  SoBalanceVO[] querySoBalanceVO(String where, Map<String, String[]> key)
      throws BusinessException;

  SoBalanceVO[] querySoBalanceVO(IQueryScheme queryScheme)
      throws BusinessException;

  SoBalanceVO[] updateSoBalanceVO(SoBalanceVO[] bill) throws BusinessException;

  SoBalanceVO[] insertSoBalanceVO(SoBalanceVO[] bill) throws BusinessException;

  // SoBalanceVO[] deleteSoBalanceVO(SoBalanceVO[] bill)
  // throws BusinessException;

}
