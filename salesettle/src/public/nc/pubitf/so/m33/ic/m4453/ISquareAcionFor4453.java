package nc.pubitf.so.m33.ic.m4453;

import nc.vo.ic.m4453.entity.WastageVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>销售结算给库存途损单提供的接口</b>
 * 
 * <ul>
 * <li>途损单签字时调用soSquare
 * <li>途损单取消签字时调用cancelSoSquare
 * <li>...
 * </ul>
 * 
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author zhangcheng
 * @time 2010-8-25 上午09:36:23
 */
public interface ISquareAcionFor4453 {

  /**
   * 方法功能描述：途损单取消签字的时候调用，取消销售结算
   * <p>
   * <b>参数说明</b>
   * 
   * @param wasvos
   * @throws BusinessException
   *           <p>
   * @author zhangcheng
   * @time 2010-8-31 下午01:39:08
   */
  void cancelSoSquare(WastageVO[] wasvos) throws BusinessException;

  /**
   * 方法功能描述：途损单签字的时候调用，进行销售结算
   * <p>
   * <b>参数说明</b>
   * 
   * @param wasvos
   * @throws BusinessException
   *           <p>
   * @author zhangcheng
   * @time 2010-8-31 下午01:39:08
   */
  void soSquare(WastageVO[] wasvos) throws BusinessException;

}
