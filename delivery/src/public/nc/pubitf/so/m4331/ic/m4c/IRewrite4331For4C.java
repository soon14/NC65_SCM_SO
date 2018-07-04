package nc.pubitf.so.m4331.ic.m4c;

import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>发货单为销售出库单提供的回写类
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since 上一版本号 5.6
 * @author 祝会征
 * @time 2010-3-23 下午06:38:24
 */
public interface IRewrite4331For4C {
  /**
   * 方法功能描述：回写发货单上的累计出库数量字段。
   * <p>
   * <b>参数说明</b>
   * 
   * @param paras
   * @throws BusinessException
   *           <p>
   * @author 祝会征
   * @time 2010-3-23 下午06:38:40
   */
  void rewrite4331OutNumFor4C(RewritePara4331For4C[] paras)
      throws BusinessException;
}
