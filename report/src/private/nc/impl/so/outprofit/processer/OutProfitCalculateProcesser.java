package nc.impl.so.outprofit.processer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.parameter.AskCostPriceParaVO;
import nc.vo.so.report.outprofit.OutProfitViewVO;

import nc.itf.so.report.RemoteCallService;

/**
 * 计算相应的信息
 * 
 * @since 6.3
 * @version 2012-09-09 16:07:37
 * @author 梁吉明
 */
public class OutProfitCalculateProcesser {

  /**
   * 计算主数量、毛利信息
   * 
   * @param views
   */
  public void calculateProfit(OutProfitViewVO[] views) {
    // 获取成本结算成本金额，成本金额，成本单价
    Map<String, UFDouble> outpricrmap = this.getOutpriceMap(views);
    for (OutProfitViewVO view : views) {

      // 1.设置主数量
      this.setMainnum(view);

      // 2.设置无税金额和无税单价
      this.setNnotaxmny(view);
      // 3.设置成本结算成本金额，成本金额，成本单价
      this.setNtotalcostmny(view, outpricrmap);

      // 4.设置毛利与毛利率
      view.setNprofitmny(MathTool.sub(view.getNnotaxmny(),
          view.getNtotalcostmny()));
      // view.setNprofitrate(view.getNprofitmny().div(view.getNnotaxmny()));
    }

  }

  private Map<String, UFDouble> getOutpriceMap(OutProfitViewVO[] views) {
    List<AskCostPriceParaVO> costpricelist =
        new ArrayList<AskCostPriceParaVO>();
    for (OutProfitViewVO view : views) {
      AskCostPriceParaVO acpp = new AskCostPriceParaVO();
      // dongli2 2013-08-16 此处修改为物料OID，因为存货核算不关注VID
      acpp.setCinventoryid(view.getCmaterialoid());
      acpp.setPk_financeorg(view.getCfanaceorgoid());
      acpp.setPk_org(view.getPk_org());
      acpp.setPk_stordoc(view.getCbodywarehouseid());
      acpp.setPk_stockorg(view.getPk_org());
      acpp.setVbatch(view.getVbatchcode());
      costpricelist.add(acpp);
    }
    Map<String, UFDouble> outpricemap =
        RemoteCallService.getPriceBySCM02(costpricelist
            .toArray(new AskCostPriceParaVO[costpricelist.size()]));
    return outpricemap;
  }

  private void setNtotalcostmny(OutProfitViewVO view,
      Map<String, UFDouble> outpricrmap) {
    String key = this.getKey(view);
    UFDouble outprice = outpricrmap.get(key);
    // 成本结算成本金额
    UFDouble nocostnum = view.getNocostnum();
    UFDouble nocostmny = UFDouble.ZERO_DBL;
    if (null != outprice && null != nocostnum) {
      nocostmny = nocostnum.multiply(outprice);
    }
    UFDouble ncostmuy = MathTool.add(view.getNcost(), nocostmny);
    view.setNcostmny(ncostmuy);

    // 成本金额=存货核算成本金额 + （主数量-传存货核算主数量）* （外模块取成本规则：结存单价or参考成本or计划价）
    UFDouble mny = UFDouble.ZERO_DBL;
    if (null != outprice) {
      mny =
          MathTool.sub(view.getNmainnum(), view.getNcostnum()).multiply(
              outprice);
    }

    view.setNtotalcostmny(MathTool.add(view.getNcostmny(), mny));
    // 成本单价
    // view.setNcostprice(view.getNtotalcostmny().div(view.getNmainnum()));
  }

  private String getKey(OutProfitViewVO view) {

    StringBuffer ret = new StringBuffer();
    ret.append(view.getPk_org() + "|");
    ret.append(view.getCfanaceorgoid() + "|");
    ret.append(view.getPk_org() + "|");
    ret.append(view.getCbodywarehouseid() + "|");
    ret.append(view.getCmaterialoid() + "|");
    ret.append(view.getVbatchcode());
    return ret.toString();
  }

  private void setNnotaxmny(OutProfitViewVO view) {
    // 无税金额=应收无税金额 + （主数量 - 应收主数量）* 本币无税净价(在数据加工中计算)
    if (view.getFlargess() == null || !view.getFlargess().booleanValue()) {
      UFDouble qtorignetprice = view.getNqtorignetprice();
      UFDouble mny = UFDouble.ZERO_DBL;
      if (null != qtorignetprice) {
        mny =
            MathTool.sub(view.getNmainnum(), view.getNshouldreceivnum())
                .multiply(qtorignetprice);
      }
      view.setNnotaxmny(MathTool.add(view.getNtotalreceivmny(), mny));
      // 无税单价
      // view.setNnotaxprice(view.getNnotaxmny().div(view.getNmainnum()));
    }
    else {
      view.setNnotaxmny(UFDouble.ZERO_DBL);
    }
  }

  private void setMainnum(OutProfitViewVO view) {
    UFDouble retmainnum = view.getNnum();
    UFDouble recinum = view.getNshouldreceivnum();
    UFDouble costnum = view.getNcostnum();
    if (MathTool.absCompareTo(recinum, retmainnum) > 0) {
      retmainnum = recinum;
    }
    if (MathTool.absCompareTo(costnum, retmainnum) > 0) {
      retmainnum = costnum;
    }
    view.setNmainnum(retmainnum);
  }
}
