package nc.pubitf.so.mbuylagress.opc.mecc;

import nc.vo.pub.BusinessException;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;

/**
 * 电子销售买赠设置查询接口
 * 
 * @since 6.3
 * @version 2012-10-22 下午01:18:00
 * @author 梁吉明
 */
public interface IBuylargessForMecc {
  /**
   * 根据集团、销售组织查询买赠设置
   * 
   * @param saleorerhids 集团ID数组
   * @param saleorerbids 销售组织ID数组
   * @return 买赠设置VO数组
   * @throws BusinessException
   * 
   */
  BuyLargessVO[] queryBuyLargessVO(String[] pk_groups, String[] pk_orgs)
      throws BusinessException;
}
