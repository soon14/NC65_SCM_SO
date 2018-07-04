package nc.pubimpl.so.m4331.pub;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m4331.entity.DeliveryViewVO;

import nc.pubitf.so.m30.so.m4331.IRewrite30For4331;
import nc.pubitf.so.m30.so.m4331.Rewrite4331Para;
import nc.pubitf.to.m5x.so.m4331.IRewrite5XFor4331;

import nc.bs.framework.common.NCLocator;

/**
 * 回写来源单据
 * 
 * @since 6.0
 * @version 2011-2-28 上午10:49:25
 * @author 祝会征
 */
public class RewriteSrcUtil {

  /**
   * 缓存回写销售订单信息
   */
  List<Rewrite4331Para> saleorderList;

  /**
   * 缓存回写调拨订单信息
   */
  List<nc.pubitf.to.m5x.so.m4331.Rewrite4331Para> tranOrderList;

  private RewriteVOUtil voutil;

  /**
   * 
   * @param util
   */
  public RewriteSrcUtil(RewriteVOUtil util) {
    this.voutil = util;
  }

  /**
   * 回写来源单据
   * 
   * @param valueMap key 发货单表体id value 发货单要回写来源单据的变化量
   */
  public void rewriteSrc(Map<String, UFDouble> valueMap) {
    // 缓存回写销售订单信息
    this.saleorderList = new ArrayList<Rewrite4331Para>();
    // 缓存回写调拨订单信息
    this.tranOrderList =
        new ArrayList<nc.pubitf.to.m5x.so.m4331.Rewrite4331Para>();
    DeliveryViewVO[] views = this.voutil.getAllRewriteViewVO();
    for (DeliveryViewVO view : views) {
      String bid = view.getItem().getCdeliverybid();
      UFDouble reValue = valueMap.get(bid);
      if (reValue.compareTo(UFDouble.ZERO_DBL) == 0) {
        continue;
      }
      String srcBilltype = view.getItem().getVsrctype();
      String srcBid = view.getItem().getCsrcbid();
      // 是否关闭上游单据
      UFBoolean bclosesrcflag = view.getItem().getBclosesrcflag();
      if (SOBillType.Order.getCode().equals(srcBilltype)) {
        Rewrite4331Para para =
            new Rewrite4331Para(srcBid, reValue, bclosesrcflag, UFBoolean.TRUE);
        this.saleorderList.add(para);
      }
      else if (TOBillType.TransOrder.getCode().equals(srcBilltype)) {
        nc.pubitf.to.m5x.so.m4331.Rewrite4331Para para =
            new nc.pubitf.to.m5x.so.m4331.Rewrite4331Para(srcBid, reValue,
                bclosesrcflag);
        this.tranOrderList.add(para);
      }
    }
    this.rewriteSaleOrder();
    this.rewriteTranOrder();
  }

  /**
   * 回写销售订单
   */
  private void rewriteSaleOrder() {
    if (this.saleorderList.size() == 0) {
      return;
    }
    Rewrite4331Para[] paras = new Rewrite4331Para[this.saleorderList.size()];
    paras = this.saleorderList.toArray(paras);
    IRewrite30For4331 api =
        NCLocator.getInstance().lookup(IRewrite30For4331.class);
    try {
      api.rewrite30SendNumFor4331(paras);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
  }

  /**
   * 回写调拨订单
   */
  private void rewriteTranOrder() {
    if (this.tranOrderList.size() == 0) {
      return;
    }
    int size = this.tranOrderList.size();
    nc.pubitf.to.m5x.so.m4331.Rewrite4331Para[] paras =
        new nc.pubitf.to.m5x.so.m4331.Rewrite4331Para[size];
    paras = this.tranOrderList.toArray(paras);
    IRewrite5XFor4331 api =
        NCLocator.getInstance().lookup(IRewrite5XFor4331.class);
    try {
      api.rewrite5XSendNumFor4331(paras);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
  }
}
