package nc.bs.so.m30.rule.pu;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21.so.m30.IOrderUpdateCoopFor30;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @description
 * 销售订单删除后，如果该销售订单是协同采购订单生成，需要回写采购
 * @scene
 * 销售订单删除后，如果该销售订单是协同采购订单生成
 * @param 
 * 无
 * @since 6.0
 * @version 2011-3-29 下午06:43:37
 * @author 祝会征
 */
public class Rewrite21DeleteRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    Map<String, String> map = new HashMap<String, String>();
    for (SaleOrderVO vo : vos) {
      SaleOrderHVO hvo = vo.getParentVO();
      UFBoolean iscoop = hvo.getBpocooptomeflag();
      if ((null != iscoop) && iscoop.booleanValue()) {
        String srcHid = vo.getChildrenVO()[0].getCsrcid();
        String billcode = hvo.getVbillcode();
        map.put(srcHid, billcode);
      }
    }
    if (map.size() > 0) {
      IOrderUpdateCoopFor30 service =
          NCLocator.getInstance().lookup(IOrderUpdateCoopFor30.class);
      try {
        service.updateCoopFlag(false, map);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }
}
