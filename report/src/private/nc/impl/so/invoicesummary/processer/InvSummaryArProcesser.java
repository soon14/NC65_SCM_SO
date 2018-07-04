package nc.impl.so.invoicesummary.processer;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.so.pub.summary.util.ReportSummaryARForSOUtil;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.so.m33.so.m32.ISquare33For32;
import nc.vo.arap.itfvo.ReceivableBillInfoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapSet;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.report.invoicesummary.InvSummaryViewVO;

/**
 * 处理收入信息
 * 
 * @since 6.3
 * @version 2012-10-18 14:42:09
 * @author 梁吉明
 */
public class InvSummaryArProcesser {

  /**
   * 收入信息
   * 
   * @param views
   */
  public void queryAr(InvSummaryViewVO[] views) {
    // 1.取表头id与表体id
    String[] invheadids = this.getInvHeadIds(views);
    String[] invbodyids = this.getInvBodyIds(views);
    // 2.取发票结算明细
    String[] invdetailbids = this.getInvDetailIds(invheadids, invbodyids);
    MapSet<String, String> inv_detailmap =
        this.getInv_Detailmap(invheadids, invbodyids);
    // 3.取出库单收入和成本信息，并赋值给VO
    if (null != invdetailbids && 0 != invdetailbids.length)
      this.setInvAr(views, invheadids, invdetailbids, inv_detailmap);
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

  private void setInvAr(InvSummaryViewVO[] views, String[] invheadids,
      String[] invdetailbids, MapSet<String, String> inv_detailmap) {
    // add by wangshu6 for v636 报表立即执行报错 2015-1-28 先判断是否已用关联模块，
    if(!SysInitGroupQuery.isAPEnabled()){
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4006005_0", "04006005-0028")/*应收模块未启用！*/);
      return;
    }
    // end
    Map<String, ReceivableBillInfoVO> arinfo = null;
    arinfo = ReportSummaryARForSOUtil.queryReceivableBillInfoByTopBill(invheadids, invdetailbids);
    Set<String> invdetailsids = null;
    for (InvSummaryViewVO view : views) {
      String invbid = view.getCsaleinvoicebid();
      invdetailsids = inv_detailmap.get(invbid);
      UFDouble nshouldreceivnum = null;
      UFDouble nshouldreceivmny = null;
      UFDouble ntotalreceivmny = null;
      // UFDouble notax = null;
      // UFDouble tax = null;
      if (null != invdetailsids) {
        for (String invdetailsid : invdetailsids) {
          if (null != arinfo && arinfo.containsKey(invdetailsid)) {
            ReceivableBillInfoVO icinfo = arinfo.get(invdetailsid);
            nshouldreceivnum =
                MathTool.add(nshouldreceivnum, icinfo.getQuantity_de());
            // tax = MathTool.add(tax, icinfo.getLocal_tax_de());
            // notax = MathTool.add(notax, icinfo.getLocal_notax_de());
            nshouldreceivmny =
                MathTool.add(nshouldreceivmny, icinfo.getMoney_de());
            ntotalreceivmny =
                MathTool.add(ntotalreceivmny, icinfo.getMoney_bal());
          }
        }
      }
      view.setNshouldreceivnum(nshouldreceivnum);
      // nshouldreceivmny = MathTool.add(tax, notax);
      view.setNshouldreceivmny(nshouldreceivmny);
      view.setNtotalreceivmny(ntotalreceivmny);
    }
  }

  private String[] getInvBodyIds(InvSummaryViewVO[] views) {
    Set<String> invbodyid = new HashSet<String>();
    for (InvSummaryViewVO view : views) {
      invbodyid.add(view.getCsaleinvoicebid());
    }
    String[] invbodyids = new String[invbodyid.size()];
    invbodyid.toArray(invbodyids);
    return invbodyids;
  }

  private String[] getInvHeadIds(InvSummaryViewVO[] views) {
    Set<String> invheadid = new HashSet<String>();
    for (InvSummaryViewVO view : views) {
      invheadid.add(view.getCsaleinvoiceid());
    }
    String[] Invheadids = new String[invheadid.size()];
    invheadid.toArray(Invheadids);
    return Invheadids;
  }
}
