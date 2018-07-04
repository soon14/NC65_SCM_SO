package nc.impl.so.outsummary.processer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.so.pub.summary.util.ReportSummaryARForSOUtil;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.arap.receivable.IArapReceivableBillPubServiceForSCM;
import nc.pubitf.so.m32.so.report.IM32ForOutSum;
import nc.pubitf.so.m33.ic.m4c.ISquare33For4C;
import nc.pubitf.so.m33.so.m32.ISquare33For32;
import nc.vo.arap.itfvo.ReceivableBillInfoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MapSet;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.report.outsummary.OutSummaryViewVO;

/**
 * 收入信息处理
 * 
 * @since 6.3
 * @version 2012-09-04 14:15:33
 * @author 梁吉明
 */
public class OutSummaryArProcesser {

  /**
   * 收入信息
   * 
   * @param views
   */
  public void queryAr(OutSummaryViewVO[] views) {

    // 1.取出库单表头id与表体id
    String[] outheadids = this.getOutHeadIds(views);
    String[] outbodyids = this.getOutBodyIds(views);

    // 2.填写开票信息
    this.setInvoiceMny(views, outheadids, outbodyids);

    // 3.取出库单结算明细
    String[] outdetailids = this.getOutDetailIds(outheadids, outbodyids);
    MapSet<String, String> out_detailmap =
        this.getOut_Detailmap(outheadids, outbodyids);

    // 4.取出库单收入信息，并赋值给VO
    if (null != outdetailids && 0 != outdetailids.length) {
      this.setOutArInfo(views, outheadids, outdetailids, out_detailmap);
    }
    // 5.取出库单下游发票ids信息
    String[] invheadids = this.getInvHeadIds(outheadids, outbodyids);
    String[] invbodyids = this.getInvBodyIds(outheadids, outbodyids);
    MapList<String, String> out_invmap =
        this.getOut_Invmap(outheadids, outbodyids);

    // 6.取发票结算明细
    if (null != invheadids && 0 != invheadids.length && null != invbodyids
        && 0 != invbodyids.length) {
      String[] invdetailbids = this.getInvDetailIds(invheadids, invbodyids);
      MapSet<String, String> inv_detailmap =
          this.getInv_Detailmap(invheadids, invbodyids);
      // 7.取发票的收入,，并赋值给VO
      if (null != invdetailbids && 0 != invdetailbids.length) {
        this.setInvArInfo(views, invheadids, invdetailbids, inv_detailmap,
            out_invmap);

      }
    }

  }

  private void setInvoiceMny(OutSummaryViewVO[] views, String[] outheadids,
      String[] outbodyids) {
    MapList<String, SaleInvoiceBVO> mapinvoice =
        this.getInvoiceIds(outheadids, outbodyids);
    if (null != mapinvoice && mapinvoice.size() != 0) {
      for (OutSummaryViewVO view : views) {
        if (view.getflargess().equals(UFBoolean.TRUE)) {
          view.setNinvoicemny(UFDouble.ZERO_DBL);
        }
        else {
          String outbid = view.getCgeneralbid();
          if (mapinvoice.containsKey(outbid)) {
            UFDouble totalinvmny = null;
            List<SaleInvoiceBVO> listinvb = mapinvoice.get(outbid);
            for (SaleInvoiceBVO bvo : listinvb) {
              totalinvmny = MathTool.add(totalinvmny, bvo.getNorigtaxmny());
            }
            view.setNinvoicemny(totalinvmny);
          }
        }

      }
    }
  }

  private String[] getOutHeadIds(OutSummaryViewVO[] views) {
    Set<String> outheadid = new HashSet<String>();
    for (OutSummaryViewVO view : views) {
      outheadid.add(view.getCgeneralhid());
    }
    String[] outheadids = new String[outheadid.size()];
    outheadids = outheadid.toArray(outheadids);
    return outheadids;
  }

  private String[] getOutBodyIds(OutSummaryViewVO[] views) {
    Set<String> outbodyid = new HashSet<String>();
    for (OutSummaryViewVO view : views) {
      outbodyid.add(view.getCgeneralbid());
    }
    String[] outbodyids = new String[outbodyid.size()];
    outbodyids = outbodyid.toArray(outbodyids);
    return outbodyids;
  }

  private MapSet<String, String> getOut_Detailmap(String[] outheadids,
      String[] outbodyids) {
    Map<String, String> outdetail = this.getOutDetail(outheadids, outbodyids);
    MapSet<String, String> out_detailmap = new MapSet<String, String>();
    if (null != outdetail && outdetail.size() != 0) {
      for (Entry<String, String> entry : outdetail.entrySet()) {
        out_detailmap.put(entry.getValue(), entry.getKey());
      }
    }
    return out_detailmap;

  }

  private void setInvArInfo(OutSummaryViewVO[] views, String[] invheadids,
      String[] invdetailbids, MapSet<String, String> inv_detailmap,
      MapList<String, String> out_invmap) {
    // add by wangshu6 for v636 报表立即执行报错 2015-1-28 先判断是否已用关联模块，
    if(!SysInitGroupQuery.isAPEnabled()){
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006005_0", "04006005-0028")/*应收模块未启用！*/);
      return;
    }
    // end
    Map<String, ReceivableBillInfoVO> invarinfo = null;
    invarinfo = ReportSummaryARForSOUtil.queryReceivableBillInfoByTopBill(invheadids, invdetailbids);
    Set<String> invdetailsids = null;
    for (OutSummaryViewVO view : views) {
      String outbid = view.getCgeneralbid();
      UFDouble narnum = view.getNarnum();
      UFDouble narmny = view.getNarmny();
      UFDouble narremainmny = view.getNarremainmny();
      UFDouble npaymny = view.getNpaymny();
      // UFDouble notax = null;
      // UFDouble tax = null;

      List<String> listinvbid = out_invmap.get(outbid);
      if (null != listinvbid && listinvbid.size() != 0 && null != invarinfo
          && invarinfo.size() != 0) {
        for (String invbid : listinvbid) {
          invdetailsids = inv_detailmap.get(invbid);
          if (null != invdetailsids) {
            for (String invdetailsid : invdetailsids) {
              if (invarinfo.containsKey(invdetailsid)) {
                ReceivableBillInfoVO invinfo = invarinfo.get(invdetailsid);
                narnum = MathTool.add(narnum, invinfo.getQuantity_de());
                // tax = MathTool.add(tax, invinfo.getLocal_tax_de());
                // notax = MathTool.add(notax, invinfo.getLocal_notax_de());
                narmny = MathTool.add(narmny, invinfo.getMoney_de());
                narremainmny =
                    MathTool.add(narremainmny, invinfo.getMoney_bal());
              }
            }
          }
        }
      }
      view.setNarnum(narnum);
      // narmny = MathTool.add(tax, notax);
      view.setNarmny(narmny);
      view.setNarremainmny(narremainmny);
      npaymny = MathTool.sub(narmny, narremainmny);
      view.setNpaymny(npaymny);
    }
  }

  private void setOutArInfo(OutSummaryViewVO[] views, String[] outheadids,
      String[] outdetailids, MapSet<String, String> out_detailmap) {
    Map<String, ReceivableBillInfoVO> icarinfo = null;
    IArapReceivableBillPubServiceForSCM arsrv =
        NCLocator.getInstance().lookup(
            IArapReceivableBillPubServiceForSCM.class);
    try {
      icarinfo =
          arsrv.queryReceivableBillInfoByTopBill(ICBillType.SaleOut.getCode(),
              outheadids, outdetailids);

    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    Set<String> outdetailsids = null;

    for (OutSummaryViewVO view : views) {
      String outbid = view.getCgeneralbid();
      outdetailsids = out_detailmap.get(outbid);
      UFDouble narnum = null;
      UFDouble narmny = null;
      UFDouble narremainmny = null;
      UFDouble npaymny = null;
      // UFDouble notax = null;
      // UFDouble tax = null;
      if (null != outdetailsids) {
        for (String outdetailsid : outdetailsids) {
          if (null != icarinfo && icarinfo.containsKey(outdetailsid)) {
            ReceivableBillInfoVO icinfo = icarinfo.get(outdetailsid);
            narnum = MathTool.add(narnum, icinfo.getQuantity_de());
            // tax = MathTool.add(tax, icinfo.getLocal_tax_de());
            // notax = MathTool.add(notax, icinfo.getLocal_notax_de());
            narmny = MathTool.add(narmny, icinfo.getMoney_de());
            narremainmny = MathTool.add(narremainmny, icinfo.getMoney_bal());
          }
        }
      }
      view.setNarnum(narnum);
      // narmny = MathTool.add(tax, notax);
      view.setNarmny(narmny);
      view.setNarremainmny(narremainmny);
      npaymny = MathTool.sub(narmny, narremainmny);
      view.setNpaymny(npaymny);

    }
  }

  private MapSet<String, String> getInv_Detailmap(String[] invheadids,
      String[] invbodyids) {
    Map<String, String> invdetail = this.getInvDetail(invheadids, invbodyids);
    MapSet<String, String> inv_detailmap = new MapSet<String, String>();
    if (null != invdetail && invdetail.size() != 0) {
      for (Entry<String, String> entry : invdetail.entrySet()) {
        inv_detailmap.put(entry.getValue(), entry.getKey());
      }
    }
    return inv_detailmap;
  }

  private String[] getInvDetailIds(String[] invheadids, String[] invbodyids) {
    Map<String, String> invdetail = this.getInvDetail(invheadids, invbodyids);

    Set<String> invdetailset = new HashSet<String>();
    if (null != invdetail && invdetail.size() != 0) {
      invdetailset = invdetail.keySet();
    }
    String[] invdetailbids = new String[invdetailset.size()];
    invdetailset.toArray(invdetailbids);
    return invdetailbids;
  }

  private MapList<String, String> getOut_Invmap(String[] outheadids,
      String[] outbodyids) {
    MapList<String, SaleInvoiceBVO> mapinvoice =
        this.getInvoiceIds(outheadids, outbodyids);
    MapList<String, String> out_invmap = new MapList<String, String>();
    if (null != mapinvoice && mapinvoice.size() != 0) {
      for (String outbid : outbodyids) {
        if (mapinvoice.containsKey(outbid)) {
          List<String> listinvid = new ArrayList<String>();
          List<SaleInvoiceBVO> listinvb = mapinvoice.get(outbid);
          for (SaleInvoiceBVO bvo : listinvb) {
            listinvid.add(bvo.getCsaleinvoicebid());
          }
          out_invmap.putAll(outbid, listinvid);
        }
      }
    }
    return out_invmap;
  }

  private String[] getInvHeadIds(String[] outheadids, String[] outbodyids) {
    Set<String> invheadid = new HashSet<String>();
    MapList<String, SaleInvoiceBVO> mapinvoice =
        this.getInvoiceIds(outheadids, outbodyids);
    if (null != mapinvoice && mapinvoice.size() != 0) {
      for (String outbid : outbodyids) {
        if (mapinvoice.containsKey(outbid)) {
          List<SaleInvoiceBVO> listinvb = mapinvoice.get(outbid);
          for (SaleInvoiceBVO bvo : listinvb) {
            invheadid.add(bvo.getCsaleinvoiceid());
          }
        }
      }
    }
    String[] invheadids = new String[invheadid.size()];
    invheadid.toArray(invheadids);
    return invheadids;
  }

  private String[] getInvBodyIds(String[] outheadids, String[] outbodyids) {
    Set<String> invbodyid = new HashSet<String>();
    MapList<String, SaleInvoiceBVO> mapinvoice =
        this.getInvoiceIds(outheadids, outbodyids);
    if (null != mapinvoice && mapinvoice.size() != 0) {
      for (String outbid : outbodyids) {
        if (mapinvoice.containsKey(outbid)) {
          List<SaleInvoiceBVO> listinvb = mapinvoice.get(outbid);
          for (SaleInvoiceBVO bvo : listinvb) {
            invbodyid.add(bvo.getCsaleinvoicebid());
          }
        }
      }
    }
    String[] invbodyids = new String[invbodyid.size()];
    invbodyid.toArray(invbodyids);
    return invbodyids;
  }

  private String[] getOutDetailIds(String[] outheadids, String[] outbodyids) {
    Map<String, String> outdetail = this.getOutDetail(outheadids, outbodyids);
    Set<String> outdetailset = new HashSet<String>();
    if (null != outdetail && outdetail.size() != 0) {
      outdetailset = outdetail.keySet();
    }
    String[] outdetailbids = new String[outdetailset.size()];
    outdetailset.toArray(outdetailbids);
    return outdetailbids;
  }

  private Map<String, String> getInvDetail(String[] invheadids,
      String[] invbodyids) {
    ISquare33For32 s33for32 =
        NCLocator.getInstance().lookup(ISquare33For32.class);
    Map<String, String> invdetail = null;
    try {
      invdetail = s33for32.queryInvSquareDetail(invheadids, invbodyids);
      return invdetail;
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }

    return invdetail;
  }

  private Map<String, String> getOutDetail(String[] outheadids,
      String[] outbodyids) {
    ISquare33For4C s33for4c =
        NCLocator.getInstance().lookup(ISquare33For4C.class);
    Map<String, String> outdetail = null;
    try {
      outdetail = s33for4c.queryOutSquareDetail(outheadids, outbodyids);
      return outdetail;
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }

    return outdetail;
  }

  private MapList<String, SaleInvoiceBVO> getInvoiceIds(String[] outheadids,
      String[] outbodyids) {
    IM32ForOutSum m32srv = NCLocator.getInstance().lookup(IM32ForOutSum.class);
    MapList<String, SaleInvoiceBVO> mapinvoice = null;
    try {
      mapinvoice = m32srv.queryInvoiceFromOut(outheadids, outbodyids);
      return mapinvoice;
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    return mapinvoice;

  }

}
