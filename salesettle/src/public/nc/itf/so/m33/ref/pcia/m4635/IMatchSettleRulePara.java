package nc.itf.so.m33.ref.pcia.m4635;


import nc.vo.pcto.settlerule.para.MatchSettleRuleParaVO;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
/**
 * 
 * 匹配内部结算规则参数构造接口<br/>
 * 
 * 调用场景：匹配内部结算规则构造查询参数时调用
 * 
 * @author 刘景
 *
 */
public interface IMatchSettleRulePara {
  
  /**
   * 匹配内部结算规则参数构造接口
   * 
   * @param dataview 视图VO
   * @return 匹配利润中心结算规则参数VO 
   */
  MatchSettleRuleParaVO getMatchSettleRuleParaVO(AbstractDataView  dataview);

}
