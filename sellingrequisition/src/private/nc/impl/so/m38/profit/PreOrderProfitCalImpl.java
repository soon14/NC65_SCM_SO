package nc.impl.so.m38.profit;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.entry.ProfitVO;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.pub.util.ProfitCaculateUtil;

import nc.itf.so.m38.profit.IPreOrderProfitCal;

import nc.impl.pubapp.pattern.data.bill.BillQuery;

/**
 * 销售发票毛利预估——功能/算法概述
 * <ol>
 * <li>根据传入的销售发票头ID的集合计算所有订单行进行毛利预估
 * </ol>
 * 
 * @since 6.0
 * @version 2011-7-13 下午16:05:00
 * @author 旷宗义
 * @see
 */
public class PreOrderProfitCalImpl implements IPreOrderProfitCal {

  /**
   * 毛利计算需要批量获取成本域和成本单价
   * 
   * @param hids 预订单头ID数组
   * @return 计算好的毛利VO
   * @throws BusinessException
   * @see
   */
  @Override
  public ProfitVO[] caculate38Profit(String[] hids) throws BusinessException {
    // 查询销售发票VO
    PreOrderVO[] bills = null;
    BillQuery<PreOrderVO> query = new BillQuery<PreOrderVO>(PreOrderVO.class);
    bills = query.query(hids);
    // 初始化返回毛利VO
    List<ProfitVO> vievowlist = new ArrayList<ProfitVO>();

    // 设置毛利VO基础数据
    for (PreOrderVO vo : bills) {
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
        profitvo.setCastunitid(bvo.getCunitid());
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

}
