package nc.bs.so.m30.rule.maintaincheck;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.util.SOSysParaInitUtil;

/**
 * 
 * @description
 *              销售订单审批前校验so32参数是否可以审批中修改
 * @scene
 *        销售订单审批前
 * @param 无
 * 
 * @since 6.5
 * @version 2015-11-24 下午2:17:18
 * @author zhangby5
 */
public class CheckCanUpdateWhenAuditing implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    boolean so32 = this.getPara(vos[0].getParentVO().getPk_org());
    for (SaleOrderVO vo : vos) {
      Integer status = vo.getParentVO().getFstatusflag();
      if (BillStatus.AUDITING.getIntegerValue().equals(status) && !so32) {
        ExceptionUtils
            .wrappBusinessException(NCLangResOnserver.getInstance().getStrByID(
                "4006011_0", "04006011-0542")/*保存失败！业务组织参数so32不允许审批中修改。*/);
      }
    }
  }

  private boolean getPara(String pk_org) {
    UFBoolean so32 = SOSysParaInitUtil.getSO32(pk_org);
    if (null == so32) {
      return false;
    }
    return so32.booleanValue();
  }

}
