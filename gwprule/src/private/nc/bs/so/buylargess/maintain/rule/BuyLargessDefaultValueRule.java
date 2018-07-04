package nc.bs.so.buylargess.maintain.rule;

import nc.vo.pubapp.AppContext;
import nc.vo.so.mbuylargess.entity.BuyLargessBVO;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * 设置默认集团
 * @scene
 * 买赠设置保存前 默认集团设置
 * @param
 * 无
 * @since 6.0
 * @version 2011-1-25 上午09:48:08
 * @author 祝会征
 */
public class BuyLargessDefaultValueRule implements IRule<BuyLargessVO> {

  @Override
  public void process(BuyLargessVO[] vos) {
    for (BuyLargessVO vo : vos) {
      String pk_group = AppContext.getInstance().getPkGroup();
      vo.getParentVO().setPk_group(pk_group);
      BuyLargessBVO[] bvos = vo.getChildrenVO();
      for (BuyLargessBVO bvo : bvos) {
        bvo.setPk_group(pk_group);
      }
    }
  }
}
