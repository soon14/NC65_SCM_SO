package nc.pubitf.so.m30.ic.m4480;

import nc.vo.pub.BusinessException;

/**
 * 预留回写销售订单服务接口。
 * 
 * @author 刘志伟
 * @since 6.0
 * @time 2010-06-08 上午09:59:07
 */
public interface IRewrite30For4480 {

  /**
   * 预留回写销售订单预留数量
   * 
   * @param paras Rewrite4480Para[]
   * @throws BusinessException
   */
  void rewrite30ReqrsNumFor4480(Rewrite4480Para[] paras)
      throws BusinessException;
}
