package nc.ui.so.m4331.arrange.write;

import nc.ui.pubapp.billref.push.IRewriteService;
import nc.ui.pubapp.billref.push.RewriteInfo;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.to.m5x.entity.BillItemVO;
import nc.vo.to.m5x.entity.BillViewVO;

public class RewriteM5XNum implements IRewriteService {

  @Override
  public RewriteInfo[] getRewriterInfo(Object[] bill) {
    // TODO 自动生成方法存根
    return null;
  }

  @Override
  public void setRewriterNum(Object bill, UFDouble num, String bodyId) {

    BillViewVO voOrder = (BillViewVO) bill;
    BillItemVO body = voOrder.getItem();
    body.setNsendnum(MathTool.add(body.getNsendnum(), num));

  }

}
