package nc.pubitf.so.m30.invp;

import nc.vo.pub.BusinessException;

import nc.pubitf.invp.plan.IReqResultForInvp;

/**
 * 销售订单提供库存计划接口
 * 
 * @since 6.0
 * @version 2010-12-14 下午01:32:43
 * @author 刘志伟
 */
public interface ISaleOrderForInvp {

  /**
   * 取得取数VO
   * 
   * @param sendStockOrg 库存组织
   * @param tempName 临时表（pk_material 物料OID,dstart 开始时间,dend 结束时间）
   * @param needRed 是否包含红字单据
   * @return 查询方案VO
   * @throws BusinessException
   */
  IReqResultForInvp getVO(String sendStockOrg, String tempName, boolean needRed)
      throws BusinessException;

}
