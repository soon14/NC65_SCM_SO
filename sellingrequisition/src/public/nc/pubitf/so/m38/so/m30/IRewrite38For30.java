package nc.pubitf.so.m38.so.m30;

import nc.vo.pub.BusinessException;

/**
 * 销售订单回写预订单服务接口
 * 
 * @since 6.0
 * @version 2010-04-08 上午09:27:07
 * @author 刘志伟
 */
public interface IRewrite38For30 {

  void rewrite38NarrnumFor30(Rewrite30Para[] paras) throws BusinessException;

}
