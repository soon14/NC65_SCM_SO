package nc.pubitf.so.m30.so.m38;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.SOParameterVO;

/**
 * 销售订单供其他单据推式保存公共服务接口
 * 
 * @version 6.0
 * @author 刘志伟
 * @time 2010-6-30 上午11:37:24
 */
public interface IPushSave30For38Arrange {

  /**
   * 预订单安排，生成销售订单。
   * 
   * @param bills 待保存的单据VO数组。上游负责VO对照、分单。需要包括销售订单的时间戳。
   * @return SaleOrderVO[] 销售订单VO[]
   * @throws BusinessException
   */
  SaleOrderVO[] pushSave30For38Arrange(SOParameterVO vo)
      throws BusinessException;
}
