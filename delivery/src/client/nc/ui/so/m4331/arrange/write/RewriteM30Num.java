package nc.ui.so.m4331.arrange.write;

import nc.ui.pubapp.billref.push.IRewriteService;
import nc.ui.pubapp.billref.push.RewriteInfo;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

public class RewriteM30Num implements IRewriteService {

  @Override
  public RewriteInfo[] getRewriterInfo(Object[] bill) {
    // TODO 自动生成方法存根
    return null;
  }

  @Override
  public void setRewriterNum(Object bill, UFDouble num, String bodyId) {

    SaleOrderViewVO voOrder = (SaleOrderViewVO) bill;
    SaleOrderBVO body = voOrder.getBody();
    body.setNtotalsendnum(MathTool.add(body.getNtotalsendnum(), num));

  }

}
