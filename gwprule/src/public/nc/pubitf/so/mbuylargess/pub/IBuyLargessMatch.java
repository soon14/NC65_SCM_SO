package nc.pubitf.so.mbuylargess.pub;

import nc.vo.pub.BusinessException;
import nc.vo.so.mbuylargess.match.BuyLargessMatchResult;

/**
 * 买赠设置提供的公共匹配接口
 * 
 * @since 6.1
 * @version 2012-10-30 17:05:27
 * @author 冯加彬
 */
public interface IBuyLargessMatch {

  /**
   * 匹配买赠政策展示视图
   * 
   * @param matchparas
   * @return 匹配到的买赠政策展示视图
   * @throws BusinessException
   */
  BuyLargessMatchResult[] matchBuyLargessView(IBuyLargessMatchPara[] matchparas)
      throws BusinessException;

  /**
   * 匹配买赠设置结果
   * 
   * @param matchparas
   * @return 匹配到的买赠设置结果
   * @throws BusinessException
   */
  BuyLargessMatchResult[] matchBuyLargessResult(
      IBuyLargessMatchPara[] matchparas) throws BusinessException;

}
