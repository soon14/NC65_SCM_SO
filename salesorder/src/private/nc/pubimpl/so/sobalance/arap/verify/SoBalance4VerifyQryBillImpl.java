package nc.pubimpl.so.sobalance.arap.verify;

import java.util.Collection;
import java.util.HashMap;

import nc.pubitf.arap.pub.IArap4VerifyQryBill;
import nc.vo.arap.pfflow.ArapBillMapVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * IArap4VerifyQryBill接口实现：提供销售这边订单收款核销关系查询，供Arap核销用
 * <p>返回Map<收款单信息,List<应收单信息>></p>
 * <p>返回Map<应收单信息,List<收款单信息>></p>
 * 
 * @since 6.0
 * @version 2011-4-11 上午10:14:47
 * @author 刘志伟
 */
public class SoBalance4VerifyQryBillImpl implements IArap4VerifyQryBill {

  /**
   * 1.processSK->processRush+processVerify
   * 2.processYS->processRush+processVerify
   * <p>注明：按蓝字销售订单流程设计及注释;调通后,红字流程红字应收单的过程，
   * 代码可逆,即红字相当于注释的蓝字,蓝字相当于注释的红字;</p>
   */
  @Override
  public HashMap<ArapBillMapVO, Collection<ArapBillMapVO>> queryArapBillmap(
      ArapBillMapVO[] arVOs) throws BusinessException {
    HashMap<ArapBillMapVO, Collection<ArapBillMapVO>> map = null;
    try {
      map = new SoBalance4VerifyQryBillAction().queryArapBillmap(arVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return map;
  }
}
