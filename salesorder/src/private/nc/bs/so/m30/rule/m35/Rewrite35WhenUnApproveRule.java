package nc.bs.so.m30.rule.m35;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.so.m35.so.m30.IArsubToSaleorder;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.util.SaleOrderVOUtil;
import nc.vo.so.m35.entity.ArsubInterfaceVO;

/**
 * 
 * @description
 * 销售订单取消审批时删除关联的赠品兑付费用单
 * @scene
 * 弃审时删除关联的赠品兑付费用单
 * @param
 * 无
 *
 * @since 6.5
 * @version 2013-12-25 上午11:28:32
 * @author 董礼
 */
public class Rewrite35WhenUnApproveRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    SaleOrderVOUtil voutil = new SaleOrderVOUtil();

    // 赠品兑付VO集合
    List<SaleOrderVO> arsubcashvolist = new ArrayList<SaleOrderVO>();
    for (SaleOrderVO vo : vos) {
      String arsubtypeid = vo.getParentVO().getCarsubtypeid();
      if (nc.vo.pubapp.pattern.pub.PubAppTool.isNull(arsubtypeid)) {
        continue;
      }
      arsubcashvolist.add(vo);
    }
    if (arsubcashvolist.size() == 0) {
      return;
    }
    if (!SysInitGroupQuery.isMeEnabled()) {
		ExceptionUtils
				.wrappBusinessException(NCLangResOnserver.getInstance()
						.getStrByID("4006011_0", "04006011-0519")/*请启用营销费用模块！*/);
    }
    ArsubInterfaceVO[] arsubcashvo =
        voutil.changeToArsubInterfaceVO(arsubcashvolist
            .toArray(new SaleOrderVO[arsubcashvolist.size()]));
    // 删除冲抵关系回写费用单
    OffsetTempVO tempvo = new OffsetTempVO();
    tempvo.setIsCancelOffset(true);
    if (arsubcashvolist.size() == 0) {
      return;
    }
    IArsubToSaleorder service =
        NCLocator.getInstance().lookup(IArsubToSaleorder.class);

    try {
      service.writeArsubRelationForArsub(arsubcashvo, tempvo);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
  }

}
