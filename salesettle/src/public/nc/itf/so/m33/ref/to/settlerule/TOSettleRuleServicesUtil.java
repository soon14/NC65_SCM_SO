package nc.itf.so.m33.ref.to.settlerule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.pcto.settlerule.so.ISettleRuleMatchForSOService;
import nc.pubitf.to.settlerule.ic.MatchSettleRuleResult;
import nc.pubitf.to.settlerule.so.IMatchSettleruleServiceForSo;
import nc.pubitf.to.settlerule.so.MatchSettleRuleVOForSo;
import nc.vo.pcto.settlerule.para.MatchSettleRuleParaVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class TOSettleRuleServicesUtil {

  private TOSettleRuleServicesUtil() {
    super();
  }

  /**
   * 
   * 方法功能描述：销售结算单发货库存组织所属财务组织和结算财务组织不一致
   * <p>
   * 的时候调用TO内部结算规则的接口取成本域
   * <p>
   * <b>参数说明</b>
   * 
   * @param ids
   *          <p>
   * @since 6.0
   * @author zhangcheng
   * @time 2010-4-3 下午02:26:47
   */
  public static Map<MatchSettleRuleVOForSo, MatchSettleRuleResult> matchSettlerule(
      List<MatchSettleRuleVOForSo> vos) throws BusinessException {
    if (!SysInitGroupQuery.isTOEnabled()) {
      new HashMap<MatchSettleRuleVOForSo, MatchSettleRuleResult>();
    }
    IMatchSettleruleServiceForSo bo =
        NCLocator.getInstance().lookup(IMatchSettleruleServiceForSo.class);
    return bo.matchSettleruleForSo(vos);
  }

  /**
   * 销售结算发货利润中心和结算利润中心不一致的算时候调用内部结算规则接口，用来判断是否传存货
   * 
   * @param vos
   * @return
   * @throws BusinessException
   */
  public static Map<MatchSettleRuleParaVO, UFBoolean> matchSettleRule(
      MatchSettleRuleParaVO[] vos)  {
    ISettleRuleMatchForSOService bo =
        NCLocator.getInstance().lookup(ISettleRuleMatchForSOService.class);
    Map<MatchSettleRuleParaVO, UFBoolean> settlerule =
        new HashMap<MatchSettleRuleParaVO, UFBoolean>();
    try {
      settlerule = bo.matchSettleRuleForSO(vos);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    return settlerule;
  }

}
