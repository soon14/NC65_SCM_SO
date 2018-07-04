package nc.itf.so.m33.ref.pcia.m4635;

import nc.vo.pcto.enumeration.FSettleType;
import nc.vo.pcto.settlerule.para.MatchSettleRuleParaVO;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.so.pub.SOItemKey;

/**
 * 匹配内部结算规则参数构造实现
 * 
 * @author 刘景
 *
 */
public class MatchSettleRuleParaImpl implements IMatchSettleRulePara {
  @Override
  public MatchSettleRuleParaVO getMatchSettleRuleParaVO(
      AbstractDataView dataview) {
    String pk_group = (String) dataview.getAttributeValue(SOItemKey.PK_GROUP);
    String cmaterialid =
        (String) dataview.getAttributeValue(SOItemKey.CMATERIALID);
    MatchSettleRuleParaVO mstoVo =
        new MatchSettleRuleParaVO(
            FSettleType.SALE_BETWEEN_ORG.getIntegerValue(), pk_group,
            cmaterialid);
    return mstoVo;
  }

}
