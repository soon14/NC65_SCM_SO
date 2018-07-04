package nc.vo.so.m38.util;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m38.profit.IPreOrderProfitCal;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.entry.ProfitVO;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.pub.util.ProfitCaculateUtil;

/**
 * 毛利计算需要批量获取成本域和成本单价
 * 
 * @param vos 预订单VO数组
 * @return 计算好的毛利VO
 * @throws BusinessException
 * @see
 */
public class PreOrderVOUtil {

  public ProfitVO[] changeToProfitVO(PreOrderVO[] vos) {
    List<ProfitVO> vievowlist = new ArrayList<ProfitVO>();

    for (PreOrderVO vo : vos) {
      PreOrderBVO[] bvos = vo.getChildrenVO();
      for (PreOrderBVO bvo : bvos) {

        ProfitVO profitvo = new ProfitVO();
        profitvo.setCstockorgid(bvo.getCsendstockorgid());
        profitvo.setCstordocid(bvo.getCsendstordocid());
        profitvo.setCmaterialid(bvo.getCmaterialid());
        profitvo.setCmaterialvid(bvo.getCmaterialvid());
        profitvo.setNastnum(bvo.getNnum());
        profitvo.setVbatchcode(bvo.getVbatchcode());
        profitvo.setNqtorignetprice(bvo.getNnetprice());
        profitvo.setCastunitid(bvo.getCastunitid());
        profitvo.setCorigcurrencyid(bvo.getCcurrencyid());
        UFDouble totalincome = bvo.getNmny();
        if (bvo.getBlargessflag().booleanValue()) {
          totalincome = UFDouble.ZERO_DBL;
        }
        profitvo.setNtotalincome(totalincome);
        profitvo.setPk_org(bvo.getPk_org());
        profitvo.setCfinanceorg(bvo.getCsettleorgid());
        profitvo.setBlargessflag(bvo.getBlargessflag());

        vievowlist.add(profitvo);
      }
    }
    // 批量调用取成本域和成本单价接口计算毛利
    ProfitCaculateUtil cacProfigUtil = new ProfitCaculateUtil(vievowlist);
    cacProfigUtil.caculateProfit();

    ProfitVO[] profitvos = vievowlist.toArray(new ProfitVO[vievowlist.size()]);
    return profitvos;

  }

  /**
   * 毛利计算需要批量获取成本域和成本单价
   * 
   * @param hids 预订单头ID数组
   * @return 计算好的毛利VO
   * @throws BusinessException
   * @see
   */
  public ProfitVO[] changeToProfitVO(String[] hids) throws BusinessException {
    IPreOrderProfitCal service =
        NCLocator.getInstance().lookup(IPreOrderProfitCal.class);
    return service.caculate38Profit(hids);
  }
}
