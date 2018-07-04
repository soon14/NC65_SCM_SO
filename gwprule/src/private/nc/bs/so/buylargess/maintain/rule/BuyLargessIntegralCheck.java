package nc.bs.so.buylargess.maintain.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description 
 * 新增数据完整性校验
 * @scene 
 * 买赠设置 保存前进行校验
 * @param
 * 无
 * @since 6.3
 * @author 祝会征[祝会征@ufida.com.cn]
 * @version 1.0
 */
public class BuyLargessIntegralCheck implements IRule<BuyLargessVO> {

  @Override
  public void process(BuyLargessVO[] bills) {
    for (BuyLargessVO bill : bills) {
      // 新增保存时表体数据不能为空
      if (VOChecker.isEmpty(bill.getChildrenVO())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006003_0","04006003-0003")/*@res "单据体不能为空。"*/);
      }
    }

  }
}