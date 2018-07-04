package nc.bs.so.m33.biz.m4c.rule.toia;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.vo.so.m33.m4c.entity.SquareOutHVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.pub.util.AggVOUtil;

/**
 * @description
 * 销售出库待结算单传发出商品贷方前执行业务规则，跨组织传存货核算，清空发货库存组织和仓库
 * @scene
 * 销售出库待结算单传发出商品贷方、销售出库单传成本结算、销售出库单传发出商品
 * @param 
 * 无
 * @version 本版本号
 * @since 上一版本号
 * @author zhangcheng
 * @time 2010-5-28 上午10:17:59
 */
public class ToIABizFor4CRule implements IRule<SquareOutVO> {

  @Override
  public void process(SquareOutVO[] vos) {

    // 跨组织传存货核算，清空发货库存组织和仓库
    this.clearStockAndStordoc(vos);

  }

  /**
   * 跨组织传存货核算:发货库存组织所属财务组织和结算财务组织不一致
   * 清空发货库存组织和仓库
   * 
   * @param vos
   */
  private void clearStockAndStordoc(SquareOutVO[] vos) {
    // 批量根据库存组织ID获取对应的财务组织ID
    String[] stockorgids =
        AggVOUtil.getDistinctHeadFieldArray(vos, SquareOutHVO.CSENDSTOCKORGID,
            String.class);

    Map<String, String> m_st_fin =
        StockOrgPubService.queryFinanceOrgIDByStockOrgID(stockorgids);

    SquareOutHVO hvo = null;
    for (SquareOutVO svo : vos) {
      hvo = svo.getParentVO();
      if (!hvo.getPk_org().equals(m_st_fin.get(hvo.getCsendstockorgid()))) {
        hvo.setCsendstockorgid(null);
        hvo.setCsendstockorgvid(null);
        hvo.setCsendstordocid(null);
      }
    }

  }

}
