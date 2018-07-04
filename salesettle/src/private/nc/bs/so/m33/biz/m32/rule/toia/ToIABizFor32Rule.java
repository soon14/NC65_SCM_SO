package nc.bs.so.m33.biz.m32.rule.toia;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * @description
 * <li>结算传成本前执行的业务规则
 * <li>功能条目2 传存货核算数据业务处理
 * @scene
 * 结算传成本前传存货核算数据
 * @param
 * 无
 * @version 本版本号
 * @since 上一版本号
 * @author zhangcheng
 * @time 2010-5-28 上午10:17:59
 */
public class ToIABizFor32Rule implements IRule<SquareInvVO> {

  @Override
  public void process(SquareInvVO[] vos) {

    // 跨组织传存货核算，清空发货库存组织和仓库
    this.clearStockAndStordoc(vos);

  }

  /**
   * 跨组织传存货核算:发货库存组织所属财务组织和结算财务组织不一致
   * 清空发货库存组织和仓库
   * 
   * @param vos
   */
  private void clearStockAndStordoc(SquareInvVO[] vos) {
    // 批量根据库存组织ID获取对应的财务组织ID
    String[] stockorgids =
        SoVoTools.getVOsOnlyValues(SquareInvVOUtils.getInstance()
            .getSquareInvBVO(vos), SquareInvBVO.CSENDSTOCKORGID);
    Map<String, String> m_st_fin =
        StockOrgPubService.queryFinanceOrgIDByStockOrgID(stockorgids);

    for (SquareInvVO svo : vos) {
      for (SquareInvBVO bvo : svo.getChildrenVO()) {
        if (!bvo.getPk_org().equals(m_st_fin.get(bvo.getCsendstockorgid()))) {
          bvo.setCsendstockorgid(null);
          bvo.setCsendstockorgvid(null);
          bvo.setCsendstordocid(null);
        }
      }
    }

  }

}
