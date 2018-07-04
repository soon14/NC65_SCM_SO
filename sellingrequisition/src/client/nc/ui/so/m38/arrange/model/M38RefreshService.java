package nc.ui.so.m38.arrange.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m38.arrange.IPreOrderArrange;
import nc.ui.pubapp.billref.push.IPushSourceVOUpdate;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m38.entity.PreOrderViewVO;

public class M38RefreshService implements IPushSourceVOUpdate {

  @Override
  public Map<String, Object[]> querySourceVOByDestVO(Object[] destVOs) {
    SaleOrderViewVO[] destviews = (SaleOrderViewVO[]) destVOs;
    if (null == destviews || destviews.length == 0) {
      return null;
    }
    Set<String> setsrcbids = new HashSet<String>();
    for (SaleOrderViewVO view : destviews) {
      String srcbid = view.getBody().getCsrcbid();
      setsrcbids.add(srcbid);
    }
    String[] srcbids = new String[setsrcbids.size()];
    setsrcbids.toArray(srcbids);

    IPreOrderArrange arrsrv =
        NCLocator.getInstance().lookup(IPreOrderArrange.class);
    PreOrderViewVO[] srcviews = null;
    try {
      srcviews = arrsrv.queryPreOrderViewVO(srcbids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    Map<String, Object[]> mapret = new HashMap<String, Object[]>();
    mapret.put(SOBillType.PreOrder.getCode(), srcviews);

    return mapret;
  }

}
