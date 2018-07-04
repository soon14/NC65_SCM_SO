package nc.bs.so.m4331.maintain.rule.util;

import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.ItemKeyMapping;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.pubitf.so.m30.so.m4331.IRewrite30For4331;
import nc.pubitf.to.m5x.so.m4331.IRewrite5XFor4331;
import nc.pubitf.to.m5x.so.m4331.Rewrite4331Para;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.to.m5x.entity.BillHeaderVO;
import nc.vo.to.m5x.entity.BillItemVO;

/**
 * 发货单回写工具类
 * 
 * @since 6.1
 * @version 2013-05-21 20:21:37
 * @author yixl
 */
public class RewriteBillUtil {

  /**
   * 方法功能描述：返回来源单据回写工具类。
   * 
   * @author 祝会征
   * @time 2010-4-28 下午04:09:25
   */
  public BillRewriter getSrcBillRewriter() {
    ItemKeyMapping mapping = new ItemKeyMapping();
    mapping.setVsrctypeKey(DeliveryBVO.VSRCTYPE);
    mapping.setCsrcidKey(DeliveryBVO.CSRCID);
    mapping.setCsrcbidKey(DeliveryBVO.CSRCBID);
    mapping.setNnumKey(DeliveryBVO.NNUM);
    mapping.setSrcTSKey(DeliveryBVO.SRCTS);

    // 添加发货单上游单据类型
    BillRewriter tool = new BillRewriter(mapping);
    // 销售订单走流程平台回写 add by zhangby5
    //暂时不走流程平台回写 modify by wangzy
     tool.addSRCHeadClazz("30", SaleOrderHVO.class);
     tool.addSRCItemClazz("30", SaleOrderBVO.class);
    // 调拨订单
    tool.addSRCHeadClazz(TOBillType.TransOrder.getCode(), BillHeaderVO.class);
    tool.addSRCItemClazz(TOBillType.TransOrder.getCode(), BillItemVO.class);

    return tool;

  }

  /**
   * 方法功能描述：发货单新增、修改、删除时回写销售订单
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          <p>
   * @author 祝会征
   * @param paraList
   * @param map
   * @time 2010-1-30 下午02:45:49
   */
  public void reWriteSrc30(List<RewritePara> paraList,
      Map<String, UFBoolean> map) {

    int size = paraList.size();
    nc.pubitf.so.m30.so.m4331.Rewrite4331Para[] paras =
        new nc.pubitf.so.m30.so.m4331.Rewrite4331Para[size];
    for (int i = 0; i < size; i++) {
      String bid = paraList.get(i).getCsrcbid();
      UFDouble nnum = paraList.get(i).getNnum();
      UFBoolean closeflag = map.get(bid);
      paras[i] =
          new nc.pubitf.so.m30.so.m4331.Rewrite4331Para(bid, nnum, closeflag,
              UFBoolean.TRUE);
    }
    this.reWriteSrc30(paras);
  }

  /**
   * 方法功能描述：发货单新增、删除、修改时回写调拨订单
   * 
   * @author 祝会征
   * @param map
   * @time 2010-6-9 下午03:07:09
   */
  public void reWriteSrc5X(List<RewritePara> paraList,
      Map<String, UFBoolean> map) {
    int size = paraList.size();
    Rewrite4331Para[] paras = new Rewrite4331Para[size];
    for (int i = 0; i < size; i++) {
      String bid = paraList.get(i).getCsrcbid();
      UFDouble nnum = paraList.get(i).getNnum();
      UFBoolean closeflag = map.get(bid);
      paras[i] = new Rewrite4331Para(bid, nnum, closeflag);
    }
    this.reWriteSrc5X(paras);
  }

  /*
   * 回写销售订单
   */
  private void reWriteSrc30(nc.pubitf.so.m30.so.m4331.Rewrite4331Para[] paras) {
    IRewrite30For4331 api =
        NCLocator.getInstance().lookup(IRewrite30For4331.class);
    try {
      api.rewrite30SendNumFor4331(paras);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
  }

  /*
   * 回写调拨订单
   */
  private void reWriteSrc5X(Rewrite4331Para[] paras) {
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
