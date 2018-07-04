package nc.pubitf.so.m4331.opc.mecc1;

import nc.vo.pub.BusinessException;

/**
 * 发货单给订单处理中心预订单提供的ERP系统发货单发货信息接口
 * 
 * 使用场景：电子销售网上订单查询ASN发货信息
 * 
 * @since 6.0
 * @version 2011-12-28 下午02:30:31
 * @author zhangcheng
 */

public interface IQuerySendInfo {

  /**
   * 根据发货单表体id查询发货单：发货联系人、发货联系电话、要求收货日期
   * 
   * @param bids 发货单表体id
   * @return ReturnSendInfoVO[]
   * @throws BusinessException
   */
  ReturnSendInfoVO[] query(String[] bids) throws BusinessException;

}
