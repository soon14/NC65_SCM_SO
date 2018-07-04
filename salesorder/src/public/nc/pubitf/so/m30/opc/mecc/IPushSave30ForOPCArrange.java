package nc.pubitf.so.m30.opc.mecc;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 销售订单提供给订单统一处理中心提供的接口
 * 接口被调用场景：
 * 订单统一处理中心安排界面点击【生成订单】按钮后；
 * 
 * @since 6.1
 * @version 2012-2-22 下午14:11:13
 * @author 刘景
 */

public interface IPushSave30ForOPCArrange {

  /**
   * 推式保存ERP销售订单
   * 
   * @param paras 待保存的销售订单
   * @param businessCheckMap 业务检查Map:ATP检查、 信用检查、超账期金额检查、超账期天数检查、超内控账期天数检查
   * @throws BusinessException
   */
  void pushSave(SaleOrderVO[] paravo, Map<String, Boolean> businessCheckMap)
      throws BusinessException;

}
