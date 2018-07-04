package nc.pubimpl.so.m30.ic.m4453.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.opc.opcapi.so.IRewriteExecuteInfo;
import nc.pubitf.so.m30.ic.m4453.Rewrite4453Para;
import nc.vo.opc.param.so.RewriteCustomerPOPara;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.OPCBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * 
 * @description
 * 销售出库回写销售订单执行后的规则类(After)：
 * @scene
 * <p>回写电子销售标准订单累计签收数量
 * @param
 * 无
 *
 * @since 6.1
 * @version 2010-04-9 下午13:49:07
 * @author 刘景
 */
public class RewriteNtotalsigNnumRule implements IRule<SaleOrderViewVO> {

  @SuppressWarnings("unchecked")
  @Override
  public void process(SaleOrderViewVO[] vos) {
    if (!SysInitGroupQuery.isOPCEnabled()) {
      return;
    }
    Map<String, Rewrite4453Para> map =
        (Map<String, Rewrite4453Para>) BSContext.getInstance().getSession(
            Rewrite4453Para.class.getName());
    List<RewriteCustomerPOPara> ListParas =
        new ArrayList<RewriteCustomerPOPara>();
    for (SaleOrderViewVO viewvo : vos) {
      SaleOrderBVO body = viewvo.getBody();
      Rewrite4453Para rewritePara = map.get(body.getCsaleorderbid());
      RewriteCustomerPOPara rewritepara =
          new RewriteCustomerPOPara(body.getCsrcbid(),
              rewritePara.getNsignnum());
      if (PubAppTool
          .isEqual(body.getVsrctype(), OPCBillType.OPCORDER.getCode())) {
        ListParas.add(rewritepara);
      }
    }
    if (ListParas.size() == 0) {
      return;
    }
    try {
      IRewriteExecuteInfo rewrite =
          NCLocator.getInstance().lookup(IRewriteExecuteInfo.class);
      rewrite.rewriteTotalSignNum(ListParas
          .toArray(new RewriteCustomerPOPara[ListParas.size()]));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }
}
