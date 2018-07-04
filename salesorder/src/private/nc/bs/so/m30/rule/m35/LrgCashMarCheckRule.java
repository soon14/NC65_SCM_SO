package nc.bs.so.m30.rule.m35;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.me.lrgcashmar.so.m30.ILrgCashMarForSaleOrder;
import nc.pubitf.me.lrgcashmar.so.m30.LrgCashMarParaVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 
 * @description
 * 销售订单保存时
 * @scene
 * 订单保存时物料赠品兑付范围检查
 * @param
 * 无
 *
 * @since 6.35
 * @version 2013-12-3 上午09:32:13
 * @author dongli2
 */
public class LrgCashMarCheckRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    if (vos.length == 0 || vos[0].getParentVO().getCarsubtypeid() == null) {
      return;
    }
    
    if (!SysInitGroupQuery.isMeEnabled()) {
			ExceptionUtils
					.wrappBusinessException(NCLangResOnserver.getInstance()
							.getStrByID("4006011_0", "04006011-0519")/*请启用营销费用模块！*/);
    }
    
    List<LrgCashMarParaVO> paralist = new ArrayList<LrgCashMarParaVO>();
    for (SaleOrderVO vo : vos) {
      LrgCashMarParaVO paravo = this.getParaVOForCheck(vo);
      if (null != paravo.getCarsubtype()) {
        paralist.add(paravo);
      }
    }
    LrgCashMarParaVO[] paravos =
        paralist.toArray(new LrgCashMarParaVO[paralist.size()]);
    // 调用赠品兑付物料范围匹配接口
    ILrgCashMarForSaleOrder check =
        NCLocator.getInstance().lookup(ILrgCashMarForSaleOrder.class);
    try {
      check.checkLrgCashMarMatch(paravos);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 获取参数VO
   * 
   * @param vo
   * @return LrgCashMarParaVO
   */
  private LrgCashMarParaVO getParaVOForCheck(SaleOrderVO vo) {
    SaleOrderHVO headvo = vo.getParentVO();
    SaleOrderBVO[] bodyvos = vo.getChildrenVO();
    String pk_org = headvo.getPk_org();
    String channeltype = headvo.getCchanneltypeid();
    String customer = headvo.getCcustomerid();
    String carsubtype = headvo.getCarsubtypeid();
    List<String> materials = new ArrayList<String>();
    for (SaleOrderBVO bvo : bodyvos) {
      materials.add(bvo.getCmaterialid());
    }
    String[] materialids = materials.toArray(new String[materials.size()]);

    LrgCashMarParaVO paravo = new LrgCashMarParaVO();
    paravo.setPk_org(pk_org);
    paravo.setChanneltype(channeltype);
    paravo.setCustomer(customer);
    paravo.setCarsubtype(carsubtype);
    paravo.setMaterialiss(materialids);

    return paravo;
  }

}
