package nc.pubimpl.so.m30.ic.m4c.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.opc.opcapi.so.IRewriteExecuteInfo;
import nc.pubitf.so.m30.ic.m4c.Rewrite4CPara;
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
 * <p>回写电子销售标准订单累计出库数量
 * @param
 * 无
 *
 * @author 刘景
 * @since 6.1
 * @time 2010-04-9 下午13:49:07
 */
public class RewriteOPCOutNumRule implements IRule<SaleOrderViewVO> {

  @SuppressWarnings("unchecked")
  @Override
  public void process(SaleOrderViewVO[] vos) {

    if (!SysInitGroupQuery.isOPCEnabled()) {
      return;
    }
    Map<String, Rewrite4CPara> mParas =
        (Map<String, Rewrite4CPara>) BSContext.getInstance().getSession(
            Rewrite4CPara.class.getName());
    List<RewriteCustomerPOPara> ListParas =
        new ArrayList<RewriteCustomerPOPara>();
    for (SaleOrderViewVO viewvo : vos) {
      SaleOrderBVO body = viewvo.getBody();
      Rewrite4CPara rewritePara = mParas.get(body.getCsaleorderbid());
      RewriteCustomerPOPara rewritepara =
          new RewriteCustomerPOPara(body.getCsrcbid(),
              rewritePara.getNchangenum());
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
      rewrite.rewriteTotalOutNum(ListParas
          .toArray(new RewriteCustomerPOPara[ListParas.size()]));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }
}
