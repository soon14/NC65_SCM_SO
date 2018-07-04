package nc.pubitf.so.salequotation.pub;

import nc.vo.pub.BusinessException;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;

/**
 * 报价单针对外模块生成报价单时提供的补全数据接口
 * 
 * @since 6.31
 * @version 2013-11-20 09:52:42
 * @author liujingn
 */
public interface ISalequotaionFillDataForPub {

  /**
   * 对报价单基本业务规则数据进行填充包括：
   * 收货国、发货国、报税国信息、购销类型、三角贸易、税信息
   * 
   * @param salequotationvos 报价单VO
   * 
   * @return 报价单VO
   * @throws BusinessException
   */
  AggSalequotationHVO[] getFillSalequotationVO(
      AggSalequotationHVO[] salequotationvos) throws BusinessException;

  /**
   * 计算报价单表体数量单价金额。通过指定editkey来触发公共单价金额算法
   * 
   * @param salequotationvos 报价单VO
   * 
   * @param editkey 触发单据金额算法字段
   * 
   * @throws BusinessException
   */
  void calSalequotationNumPriceMny(AggSalequotationHVO[] salequotationvos,
      String editkey) throws BusinessException;

}
