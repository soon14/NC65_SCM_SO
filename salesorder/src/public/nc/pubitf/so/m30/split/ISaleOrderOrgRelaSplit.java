package nc.pubitf.so.m30.split;

import java.util.List;

import nc.vo.pub.BusinessException;

/**
 * 销售订单业务委托关系转单分单函数
 * 
 * @since 6.0
 * @version 2011-6-30 下午01:47:09
 * @author fengjb
 */
public interface ISaleOrderOrgRelaSplit {
  /**
   * 按照业务委托关系的库存组织分单
   * 
   * @param splitpara
   * @return
   * @throws BusinessException
   */
  List<String> splitBySendStockOrg(ISaleOrderSplitPara splitpara) throws BusinessException;
  /**
   * 按照业务委托关系的物流组织分单
   * 
   * @param splitpara
   * @return
   * @throws BusinessException
   */
  List<String> splitByTrafficOrg(ISaleOrderSplitPara splitpara) throws BusinessException;
  /**
   * 按照业务委托关系的结算财务组织分单
   * 
   * @param splitpara
   * @return
   * @throws BusinessException
   */
  List<String> splitBySettleOrg(ISaleOrderSplitPara splitpara) throws BusinessException;
  /**
   * 按照业务委托关系的应收组织分单
   * 
   * @param splitpara
   * @return
   * @throws BusinessException
   */
  List<String> splitByArOrg(ISaleOrderSplitPara splitpara) throws BusinessException;
  
}
