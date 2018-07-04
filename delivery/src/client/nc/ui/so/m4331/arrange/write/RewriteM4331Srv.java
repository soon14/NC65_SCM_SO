package nc.ui.so.m4331.arrange.write;

import nc.ui.pubapp.billref.push.IRewriteService;
import nc.ui.pubapp.billref.push.RewriteInfo;
import nc.vo.pub.lang.UFDouble;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;
import nc.vo.trade.checkrule.VOChecker;

public class RewriteM4331Srv implements IRewriteService {

  @Override
  public RewriteInfo[] getRewriterInfo(Object[] bill) {

    if (VOChecker.isEmpty(bill)) {
      return null;
    }
    DeliveryViewVO[] voDeliverys = (DeliveryViewVO[]) bill;
    int ilength = voDeliverys.length;
    RewriteInfo[] rewinfos = new RewriteInfo[ilength];
    for (int i = 0; i < ilength; i++) {

      DeliveryBVO body = voDeliverys[i].getItem();
      rewinfos[i] = new RewriteInfo();
      rewinfos[i].setDestType(SOBillType.Order.getCode());
      rewinfos[i].setRewriteNum(body.getNnum());
      rewinfos[i].setSourceHeadId(body.getCsrcid());
      rewinfos[i].setSourceRowId(body.getCsrcbid());
      rewinfos[i].setSourceType(body.getVsrctype());

    }
    return rewinfos;

  }

  @Override
  public void setRewriterNum(Object bill, UFDouble num, String bodyId) {
    // TODO 自动生成方法存根

  }

}
