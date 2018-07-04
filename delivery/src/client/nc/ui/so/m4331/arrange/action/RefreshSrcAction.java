package nc.ui.so.m4331.arrange.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m4331.entity.DeliveryViewVO;

import nc.pubitf.so.m30.so.m4331.ISaleOrderFor4331;

import nc.bs.framework.common.NCLocator;

import nc.ui.pubapp.billref.push.IPushSourceVOUpdate;

public class RefreshSrcAction implements IPushSourceVOUpdate {

  private Object[] getSaleorderInfo(List<String> list) {
    String[] srcBids = new String[list.size()];
    list.toArray(srcBids);
    ISaleOrderFor4331 service =
        NCLocator.getInstance().lookup(ISaleOrderFor4331.class);
    try {
      return service.querySaleOrderViewVOsFor4331Arrange(srcBids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private MapList<String, String> getSplitId(DeliveryViewVO[] views) {
    MapList<String, String> mapList = new MapList<String, String>();
    for (DeliveryViewVO view : views) {
      String srcBid = view.getItem().getCsrcbid();
      String billtype = view.getItem().getVsrctype();
      mapList.put(billtype, srcBid);
    }
    return mapList;
  }

  // private Object[] getTranorderInfo(List<String> list) {
  // String[] srcBids = new String[list.size()];
  // list.toArray(srcBids);
  // IM5XRefQueryFor4331 service =
  // NCLocator.getInstance().lookup(IM5XRefQueryFor4331.class);
  // try {
  // return service.qu(srcBids);
  // }
  // catch (BusinessException e) {
  // ExceptionUtils.wrappException(e);
  // }
  // return null;
  // }

  @Override
  public Map<String, Object[]> querySourceVOByDestVO(Object[] destVOs) {
    if (null == destVOs || destVOs.length == 0) {
      return null;
    }
    DeliveryViewVO[] views = new DeliveryViewVO[destVOs.length];
    int i = 0;
    for (Object obj : destVOs) {
      views[i] = (DeliveryViewVO) obj;
      i++;
    }
    Map<String, Object[]> map = new HashMap<String, Object[]>();
    MapList<String, String> mapList = this.getSplitId(views);
    if (mapList.containsKey(SOBillType.Order.getCode())) {
      Object[] orderviews =
          this.getSaleorderInfo(mapList.get(SOBillType.Order.getCode()));
      map.put(SOBillType.Order.getCode(), orderviews);
    }
    if (mapList.containsKey(TOBillType.TransOrder.getCode())) {
      // IM5XRefQueryFor4331
    }
    return map;
  }

}
