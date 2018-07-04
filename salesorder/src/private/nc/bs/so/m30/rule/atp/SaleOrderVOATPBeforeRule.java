package nc.bs.so.m30.rule.atp;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.pub.ref.ic.m4c.SOATPprocess;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @description
 * 销售订单动作执行前物料库存可用量检查
 * @scene 
 * 销售订单审批、新增、修改、删除保存动作、订单冻结动作前
 * @param 
 * 无
 */
public class SaleOrderVOATPBeforeRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    try {
      SOATPprocess.modifyATPBefore(SOBillType.Order.getCode(), vos);
    }
    catch (BusinessException e) {
      
      ExceptionUtils.wrappException(e);
    }
  }
}
