package nc.bs.so.m32.maintain.rule.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.so.m32.maintain.rule.insert.Rewrite4CFor32Para;
import nc.cmbd.vo.scmpub.res.billtype.SOBillType;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.ItemKeyMapping;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.env.BSContext;
import nc.pubitf.ic.m4c.m32.IRewrite4CFor32;
import nc.pubitf.so.m30.so.m32.Rewrite32Para;
import nc.vo.ic.m4c.entity.SaleOutBodyVO;
import nc.vo.ic.m4c.entity.SaleOutHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.util.RemoteFormSOUtil;
import nc.vo.so.pub.tolerance.IAbandonToleranceCheck;
import nc.vo.so.pub.util.SOSysParaInitUtil;
import nc.vo.trade.checkrule.VOChecker;

/**
 * 回写工具类
 * 
 * @since 6.3
 * @version 2012-12-21 上午09:06:23
 * @author yaogj
 */
public class RewriteBillUtil {

  private Map<String, String> mapOrg = new HashMap<String, String>();

  /**
   * 
   * RewriteBillUtil 的构造子
   */
  public RewriteBillUtil() {
    // 初始化时需要设置检查标志位
    this.setBusinessCheckFlag();
  }

  /**
   * 缓存销售组织
   * 
   * @param voInvoices
   */
  public void catcheOrg(SaleInvoiceVO[] voInvoices) {
    for (SaleInvoiceVO invoice : voInvoices) {
      for (SaleInvoiceBVO bvo : invoice.getChildrenVO()) {
        if (ICBillType.SaleOut.getCode().equals(bvo.getVsrctype())) {
          this.mapOrg.put(bvo.getCsrcbid(), bvo.getCsaleorgid());
        }
      }
    }

  }

  /**
   * 方法功能描述：根据来源30的回写记录过滤源头数据。
   * <p>
   * <b>参数说明</b>
   * 
   * @param firstParaList
   * @param srcParaList
   * @return <p>
   * @author fengjb
   * @time 2010-5-11 下午06:55:09
   */
  public List<RewritePara> filtrateSrc30(List<RewritePara> firstParaList,
      List<RewritePara> srcParaList) {
    // 如果来源为空，直接返回传入源头
    if (VOChecker.isEmpty(srcParaList)) {
      return firstParaList;
    }
    Set<String> hsBid = new HashSet<String>();
    for (RewritePara para : srcParaList) {
      hsBid.add(para.getCsrcbid());
    }

    List<RewritePara> filter = new ArrayList<RewritePara>();

    for (RewritePara para : firstParaList) {
      if (!hsBid.contains(para.getCsrcbid())) {
        filter.add(para);
      }
    }
    return filter;
  }

  /**
   * 方法功能描述：返回源头单据回写工具类。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-5-11 下午06:37:17
   */
  public BillRewriter getFirstBillRewriter() {
    // 设置发票源头单据类型、ID、BID、数量字段
    ItemKeyMapping mapping = new ItemKeyMapping();
    mapping.setVsrctypeKey(SaleInvoiceBVO.VFIRSTTYPE);
    mapping.setCsrcidKey(SaleInvoiceBVO.CFIRSTID);
    mapping.setCsrcbidKey(SaleInvoiceBVO.CFIRSTBID);
    mapping.setNnumKey(SaleInvoiceBVO.NNUM);

    // 添加发票源头单据类型
    BillRewriter tool = new BillRewriter(mapping);

    // 销售订单
//    tool.addSRCHeadClazz(SOBillType.Order.getCode(), SaleOrderHVO.class);
//    tool.addSRCItemClazz(SOBillType.Order.getCode(), SaleOrderBVO.class);

    return tool;
  }

  /**
   * 方法功能描述：返回来源单据回写工具类。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author 冯加滨
   * @time 2010-4-28 下午04:09:25
   */
  public BillRewriter getSrcBillRewriter() {

    // 设置发票上游单据类型、ID、BID、数量字段
    ItemKeyMapping mapping = new ItemKeyMapping();
    mapping.setVsrctypeKey(SaleInvoiceBVO.VSRCTYPE);
    mapping.setCsrcidKey(SaleInvoiceBVO.CSRCID);
    mapping.setCsrcbidKey(SaleInvoiceBVO.CSRCBID);
    mapping.setNnumKey(SaleInvoiceBVO.NNUM);
    mapping.setSrcTSKey(SaleInvoiceBVO.SRCTS);
    // 添加发票上游单据类型
    BillRewriter tool = new BillRewriter(mapping);
    // 销售订单走流程平台回写 add by zhangby5
     tool.addSRCHeadClazz("30", SaleOrderHVO.class);
     tool.addSRCItemClazz("30", SaleOrderBVO.class);

    // 销售出库单
    tool.addSRCHeadClazz(ICBillType.SaleOut.getCode(), SaleOutHeadVO.class);
    tool.addSRCItemClazz(ICBillType.SaleOut.getCode(), SaleOutBodyVO.class);

    return tool;

  }

  /**
   * 销售发票新操作时回写源头销售订单
   * 
   * @param paraList 回写参数
   */
  public void reWriteFirst30(List<RewritePara> paraList) {

    int size = paraList.size();
    Rewrite32Para[] paras = new Rewrite32Para[size];
    for (int i = 0; i < size; i++) {
      String bid = paraList.get(i).getCsrcbid();
      UFDouble nnum = paraList.get(i).getNnum();
      paras[i] = new Rewrite32Para(bid, nnum);
    }

    RemoteFormSOUtil.rewrite30NumFor32(paras);

  }

  /**
   * 销售发票操作时回写来源销售订单
   * 
   * @param paraList 回写参数
   */
  public void reWriteSrc30(List<RewritePara> paraList) {

    int size = paraList.size();
    Rewrite32Para[] paras = new Rewrite32Para[size];
    for (int i = 0; i < size; i++) {
      String bid = paraList.get(i).getCsrcbid();
      UFDouble nnum = paraList.get(i).getNnum();
      paras[i] = new Rewrite32Para(bid, nnum);
    }

    RemoteFormSOUtil.rewrite30NumFor32(paras);

  }

  /**
   * 方法功能描述：销售发票操作时回写来源销售出库单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param paraList
   *          <p>
   * @author 冯加滨
   * @time 2010-1-30 下午03:11:21
   */
  public void reWriteSrc4C(List<RewritePara> paraList) {
    int size = paraList.size();
    Rewrite4CFor32Para[] paras = new Rewrite4CFor32Para[size];
    for (int i = 0; i < size; i++) {
      RewritePara para = paraList.get(i);
      String voicebid = para.getCbill_bid();
      String hid = para.getCsrcid();
      String bid = para.getCsrcbid();
      UFDouble nnum = para.getNnum();
      paras[i] = new Rewrite4CFor32Para(voicebid, hid, bid, nnum);
    }
    this.setToleranceCheckPara(paras);

    IRewrite4CFor32 api = NCLocator.getInstance().lookup(IRewrite4CFor32.class);
    try {
      api.rewrite4CAccInNumFor32(paras);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
  }

  private String getSO08(String pk_org) {
    String so08 = null;

    so08 = SOSysParaInitUtil.getSO08(pk_org);
    // so08 =
    // SysParaInitQuery.getParaString(pk_org, ParameterList.SO08.getCode());

    return so08;
  }

  /**
   * 
   * 方法功能描述：设置开票回写上游单据检查控制标志。
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @author fengjb
   * @time 2010-8-31 下午02:41:15
   */
  @SuppressWarnings("unchecked")
  private void setBusinessCheckFlag() {
    Map<String, Boolean> busicheck =
        (Map<String, Boolean>) BSContext.getInstance().getSession(
            BusinessCheck.class.getName());
    if (VOChecker.isEmpty(busicheck)) {
      return;
    }

    IAbandonToleranceCheck service =
        NCLocator.getInstance().lookup(IAbandonToleranceCheck.class);
    // 订单开票控制
    Boolean tocheckorder =
        busicheck.get(BusinessCheck.OrderToleranceCheck.getCheckCode());
    if (null != tocheckorder && !tocheckorder.booleanValue()) {
      service.abandonOrderToleranceCheck();
    }
    // 出库单开票控制
    Boolean tocheckout =
        busicheck.get(BusinessCheck.OutToleranceCheck.getCheckCode());
    if (null != tocheckout && !tocheckout.booleanValue()) {
      service.abandonOutToleranceCheck();
    }
  }

  /**
   * 
   * 方法功能描述：填充校验参数。
   * <p>
   * <b>参数说明</b>
   * 
   * @param paras
   *          <p>
   * @author fengjb
   * @time 2010-9-1 下午03:32:03
   */
  private void setToleranceCheckPara(Rewrite4CFor32Para[] paras) {

    Map<String, String> mapCheckPara = new HashMap<String, String>();
    for (Rewrite4CFor32Para para : paras) {
      String orgid = this.mapOrg.get(para.getBid());
      if (mapCheckPara.containsKey(orgid)) {
        para.setSO08(mapCheckPara.get(orgid));
      }
      else {
        String so08 = this.getSO08(orgid);
        mapCheckPara.put(orgid, so08);
        para.setSO08(so08);
      }
    }
  }
}
