package nc.pubitf.so.mreturnreason.opc.mecc1;

import nc.vo.pub.BusinessException;
import nc.vo.so.mreturnreason.entity.ReturnReasonVO;

/**
 * 退货原因为订单处理中心预订单提供的ERP系统退货原因信息查询接口
 * 
 * 使用场景：网上退货订单录入退货原因时，查询ERP系统中定义的退货原因
 * 
 * @since 6.0
 * @version 2011-12-28 下午02:53:05
 * @author zhangcheng
 */

public interface IQueryReturnReasonInfo {

  /**
   * 查询集团下所有的退货原因信息
   * 
   * 这里只查集团级的退货原因，不查业务单元级的退货原因
   * 
   * @param pk_group 集团PK（非空）
   * @return 退货原因信息ReturnReasonInfoVO
   * @throws BusinessException
   */
  ReturnReasonInfoVO[] queryGroupReturnReason(String[] pk_group)
      throws BusinessException;

  /**
   * 根据电子销售输入的销售组织，销售管理模块查询该销售组织可见的退货原因档案
   * 
   * @param 销售组织ID数组
   * @return 退货原因信息ReturnReasonVO
   * @throws BusinessException
   * @author 梁吉明
   */
  ReturnReasonVO[] queryReturnReasonByPk_orgs(String[] pk_orgs)
      throws BusinessException;

}
