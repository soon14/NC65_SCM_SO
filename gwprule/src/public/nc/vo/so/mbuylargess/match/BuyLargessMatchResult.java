package nc.vo.so.mbuylargess.match;

import java.io.Serializable;

import nc.vo.so.mbuylargess.view.BuyLargessMatchViewVO;
import nc.vo.so.mbuylargess.view.BuyLargessShowViewVO;

/**
 * 匹配买赠设置的结果，分别包括了：
 * (1)买赠政策展示视图,用于需要展示匹配到的买赠政策的场景
 * (2)买赠匹配结果视图,用于需要匹配到的买赠结果记录的场景
 * 
 * @since 6.1
 * @version 2012-10-30 17:46:13
 * @author 冯加彬
 */
public class BuyLargessMatchResult implements Serializable {

  private static final long serialVersionUID = -3889917601872934280L;

  private BuyLargessMatchViewVO[] matchviewvos;

  private BuyLargessShowViewVO[] showviewvos;

  /**
   * 使用买赠匹配结果初始化的构造子
   * 
   * @param matchviews
   */
  public BuyLargessMatchResult(BuyLargessMatchViewVO[] matchviews) {
    this.matchviewvos = matchviews;
  }

  /**
   * 使用买赠展示视图初始化的构造子
   * 
   * @param showviewvos
   */
  public BuyLargessMatchResult(BuyLargessShowViewVO[] showviewvos) {
    this.showviewvos = showviewvos;
  }

  /**
   * 返回买赠匹配结果视图
   * 
   * @return BuyLargessMatchViewVO
   */
  public BuyLargessMatchViewVO[] getBuyLargessMatchViews() {
    return this.matchviewvos;
  }

  /**
   * 返回买赠政策展示视图
   * 
   * @return BuyLargessShowViewVO
   */
  public BuyLargessShowViewVO[] getBuyLargessShowViews() {
    return this.showviewvos;
  }
}
