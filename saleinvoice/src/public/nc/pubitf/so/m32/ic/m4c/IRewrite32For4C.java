package nc.pubitf.so.m32.ic.m4c;

import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售发票为销售出库单提供的回写类
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since 上一版本号 5.6
 * @author 冯加滨
 * @time 2010-3-23 下午06:38:24
 */
public interface IRewrite32For4C {
  /**
   * 方法功能描述：回写销售发票上的累计出库数量字段。
   * <p>
   * <b>参数说明</b>
   * 
   * @param paras
   * @throws BusinessException
   *           <p>
   * @author 冯加滨
   * @time 2010-3-23 下午06:38:40
   */
  void rewrite32OutNumFor4C(RewritePara32For4C[] paras)
      throws BusinessException;
}
