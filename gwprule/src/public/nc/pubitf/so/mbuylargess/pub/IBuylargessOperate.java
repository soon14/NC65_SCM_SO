package nc.pubitf.so.mbuylargess.pub;

import java.util.List;
import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;

/**
 * 买赠设置对外提供操作接口
 * 
 * @since 6.3
 * @version 2014-04-09 14:34:49
 * @author 刘景
 */
public interface IBuylargessOperate {

  /**
   * 
   * 买赠设置保存校验接口
   * 
   * 使用场景：
   * CRM营销活动保存时，模拟买赠政策保存校验的过程，提前进行校验，
   * 以免在营销活动审批时推买赠设置才抛出校验失败信息。
   * 
   * @param buylargessVOs 买赠设置聚合VO数组
   * @return Map<校验失败数组下标,失败原因>
   * @throws BusinessException
   */
  Map<Integer, String> checkBuylargessVO(BuyLargessVO[] buylargessVOs)
      throws BusinessException;

  /**
   * 买赠设置保存接口
   * 
   * 
   * @param buylargessVOs 买赠设置聚合VO数组
   * @return 买赠设置聚合VO数组
   * @throws BusinessException
   */
  BuyLargessVO[] saveBuylargessVO(BuyLargessVO[] buylargessVOs)
      throws BusinessException;

  /**
   * 买赠设置删除接口
   * 
   * @param pk_buylargess 买赠设置主键
   * @throws BusinessException
   */
  void deleteBuylargessVO(String[] pk_buylargess) throws BusinessException;
  
  /**
	 * 买赠设置删除接口
	 * 
	 * @param buyLargessVOs
	 * @throws BusinessException
	 */
	void deleteBuylargessVOs(BuyLargessVO[] buyLargessVOs)
			throws BusinessException;
	
	/**
	 * 根据营销活动id查询买赠VO
	 * 
	 * @param cmarketactids
	 *            营销活动id数组
	 * @param whereCon
	 *            其他查询条件(不需要加dr=0，默认已经增加)，开头不用加and，里面的条件只能是主表的条件，
	 *            需要加so_buylargess.的表别名；如果没有其他条件则为null
	 * @return Map<cmarketactid, List<BuyLargessVO>>
	 * @throws BusinessException
	 */
	Map<String, List<BuyLargessVO>> queryBuylargessVOsByActIDs(
			String[] cmarketactids, String whereCon) throws BusinessException;
}
