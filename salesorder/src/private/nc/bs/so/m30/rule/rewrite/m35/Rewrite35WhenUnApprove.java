package nc.bs.so.m30.rule.rewrite.m35;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.so.m35.so.m30.IArsubToSaleorder;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 
 * @description
 * 销售订单取消审核回写销售费用单已审核金额
 * @scene
 * 取消审核回写销售费用单已审核金额
 * @param
 * 无
 *
 * @since 6.0
 * @version 2010-12-10 下午12:13:08
 * @author 么贵敬
 */
public class Rewrite35WhenUnApprove implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    List<String> alorderid = new ArrayList<String>();
    // 过滤得到做过费用冲抵的销售订单主表ID
    for (SaleOrderVO voorder : vos) {
      UFBoolean subflag = voorder.getParentVO().getBoffsetflag();
      if (null != subflag && subflag.booleanValue()) {
        alorderid.add(voorder.getParentVO().getCsaleorderid());
      }
    }
    if (alorderid.size() > 0) {
      IArsubToSaleorder arsubsrv =
          NCLocator.getInstance().lookup(IArsubToSaleorder.class);
      String[] saleorderids = alorderid.toArray(new String[0]);
      try {
        arsubsrv.writeNoriginvoicemny(saleorderids, false);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }

}
