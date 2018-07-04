package nc.vo.so.pub.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.parameter.AskCostPriceParaVO;
import nc.vo.scmpub.parameter.SCMParameterUtils;
import nc.vo.so.entry.ProfitVO;
import nc.vo.so.pub.rule.CostRegionPara;
import nc.vo.so.pub.rule.SOCostRegionRule;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>根据毛利VO基础数据计算毛利
 * <li>批量获取成本域
 * <li>批量获取成本单价
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author 旷宗义
 * @time 2011-7-12 下午18:54:31
 */

public class ProfitCaculateUtil {

  /*
   * 需要计算毛利的ProfitVO
   */
  private List<ProfitVO> m_vievowlist;

  /**
   * 方法功能描述：构造函数，传入需要计算的毛利VO。
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param lstProfitVO
   * @return
   *         <p>
   * @since 6.0
   * @author 旷宗义
   * @time 2011-7-12 下午18:54:31
   */
  public ProfitCaculateUtil(List<ProfitVO> lstProfitVO) {
    this.m_vievowlist = lstProfitVO;
  }

  /**
   * 方法功能描述：根据毛利VO批量获取成本域和成本单价，并计算毛利
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param
   * @return
   *         <p>
   * @since 6.0
   * @author 旷宗义
   * @time 2011-7-12 下午18:54:31
   */
  public void caculateProfit() {

    // 获取成本单价
    Map<String, UFDouble> costpricemap = this.getCostPrice();

    // 计算毛利
    for (ProfitVO profitvo : this.m_vievowlist) {

      UFDouble totalincome = profitvo.getNtotalincome();
      String key =
          profitvo.getPk_org() + "|" + profitvo.getCfinanceorg() + "|"+profitvo.getPk_costregion()+"|"
              + profitvo.getCstockorgid() + "|" + profitvo.getCstordocid()
              + "|" + profitvo.getCmaterialid() + "|"
              + profitvo.getVbatchcode();
      // 获取成本单价
      UFDouble icprice = costpricemap.get(key);
      UFDouble costprice = null == icprice ? new UFDouble(0) : icprice;
      UFDouble totalcost = UFDouble.ZERO_DBL;
      if (null != profitvo.getNastnum()) {
        totalcost = profitvo.getNastnum().multiply(costprice);
      }
      profitvo.setNtotalcost(totalcost);
      // 预订单价税合计可能为null
      if (SOMathUtil.isZero(totalincome)) {
        totalincome = UFDouble.ZERO_DBL;
      }
      UFDouble totalprofi = totalincome.sub(totalcost);
      profitvo.setNtotalprofit(totalprofi);

      if (SOMathUtil.isZero(totalincome)) {
        if (!SOMathUtil.isZero(totalprofi)) {
          UFDouble totalprofitrate =
              totalprofi.div(totalprofi.abs()).multiply(100.0);
          profitvo.setNtotalprofitrate(totalprofitrate);
        }
      }
      else {
        UFDouble totalprofitrate = totalprofi.div(totalincome).multiply(100.0);
        profitvo.setNtotalprofitrate(totalprofitrate);
      }

    }

  }

  /**
   * 方法功能描述：根据毛利VO批量获取成本单价
   * 
   * @param Map<Key：仓库组织+仓库, Value：成本域> costmap
   * @return Map<Key：成本域+物料+批次，Value：成本单价>
   * @since 6.0
   * @author 旷宗义
   * @throws BusinessException
   * @time 2011-7-12 下午18:54:31
   */
  private Map<String, UFDouble> getCostPrice() {
    // 获取初始化数组长度
    int intCount = this.m_vievowlist.size();
    // 初始化库存组织组织和仓库参数数组、物料和批次参数数组
    List<AskCostPriceParaVO> costpriceparas =
        new ArrayList<AskCostPriceParaVO>();
    for (int i = 0; i < intCount; i++) {
      ProfitVO profitvo = this.m_vievowlist.get(i);
      if (profitvo.getPk_org() == null)
        continue;
      AskCostPriceParaVO paravo = new AskCostPriceParaVO();
      paravo.setPk_org(profitvo.getPk_org());
      paravo.setPk_financeorg(profitvo.getCfinanceorg());
      paravo.setCinventoryid(profitvo.getCmaterialid());
      paravo.setPk_stockorg(profitvo.getCstockorgid());
      paravo.setPk_stordoc(profitvo.getCstordocid());
      paravo.setVbatch(profitvo.getVbatchcode());
      paravo.setPk_costregion(profitvo.getPk_costregion());
      costpriceparas.add(paravo);
    }

    SCMParameterUtils scmutils = new SCMParameterUtils();
    Map<String, UFDouble> costpricemap = null;
    try {
      costpricemap =
          scmutils.getPriceBySCM02ForTO(costpriceparas
              .toArray(new AskCostPriceParaVO[costpriceparas.size()]));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    return costpricemap;

  }

  private Map<String, UFBoolean> getNeedCostPrice() {

    List<String> cmaterialvids = new ArrayList<String>();
    int intCount = this.m_vievowlist.size();
    for (int i = 0; i < intCount; i++) {
      ProfitVO profitvo = this.m_vievowlist.get(i);

      cmaterialvids.add(profitvo.getCmaterialvid());

    }

    MaterialVO[] mvos =
        MaterialPubService.queryMaterialBaseInfoByPks(
            cmaterialvids.toArray(new String[cmaterialvids.size()]),
            new String[] {
              MaterialVO.PK_SOURCE, MaterialVO.DISCOUNTFLAG, MaterialVO.FEE
            });

    Map<String, UFBoolean> isneedcostmap = new HashMap<String, UFBoolean>();
    for (MaterialVO vo : mvos) {
      if (vo.getFee().booleanValue() || vo.getDiscountflag().booleanValue()) {
        isneedcostmap.put(vo.getPk_source(), UFBoolean.FALSE);
      }
      else {
        isneedcostmap.put(vo.getPk_source(), UFBoolean.TRUE);
      }

    }
    return isneedcostmap;
  }
}
