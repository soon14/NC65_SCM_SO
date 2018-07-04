package nc.bs.so.m33.biz.m32.rule.toia;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.pubitf.so.m30.balend.BalOpenPara;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.balend.enumeration.BalOpenTrigger;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.pub.util.AggVOUtil;

/**
 * @description
 * 取消成本结算后，订单结算打开处理
 * @scene
 * 取消成本结算后
 * @param
 * 无
 */
public class SquareIAOpenFor32Rule implements IRule<SquareInvVO> {

  @Override
  public void process(SquareInvVO[] vos) {
    // 源头销售订单表体id
    String[] orderbids =
        AggVOUtil.getDistinctItemFieldArray(vos, SquareInvBVO.CFIRSTBID,
            String.class);
    BalOpenTrigger trigger = BalOpenTrigger.VOICE_NOCOST;
    BalOpenPara para = new BalOpenPara(orderbids, trigger);
    try {
      SOSaleOrderServicesUtil.processAutoBalOpen(para);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
