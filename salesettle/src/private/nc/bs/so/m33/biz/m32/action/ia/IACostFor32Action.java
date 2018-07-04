package nc.bs.so.m33.biz.m32.action.ia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.so.m33.biz.m32.bp.square.toia.SquareIACostFor32BP;
import nc.bs.so.m33.biz.m32.bp.square.toia.SquareIARegisterCreditFor32BP;
import nc.pubitf.so.m33.self.pub.ISquare434CQuery;
import nc.vo.so.m33.enumeration.SquareType;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;
import nc.vo.so.m33.m32.entity.SquareInvViewVO;
import nc.vo.so.pub.util.AggVOUtil;

public class IACostFor32Action {

  /**
   * 成本结算（如果出库单做过发出商品，需要发票发出商品）
   * 
   * @param vos
   */
  public void execCost(SquareInvVO[] vos) {

    // 过滤销售结算单结算类型
    SquareInvVO[][] svos = this.filterSquareInvVO(vos);

    // 上游出库单做过发出商品，发票传发出商品结算
    if (svos[0] != null && svos[0].length > 0) {
      new SquareIARegisterCreditFor32BP().square(svos[0]);
    }

    // 上游出库单没有做过发出商品，发票成本结算
    if (svos[1] != null && svos[1].length > 0) {
      new SquareIACostFor32BP().square(svos[1]);
    }

  }

  /**
   * 根据上游出库单行是否后做过发出商品将待结算VO分成发出商品结算和成本结算
   * 
   * @param vos
   * @return SquareInvVO[0] -- 发出商品
   *         SquareInvVO[1] -- 成本结算
   */
  private SquareInvVO[][] filterSquareInvVO(SquareInvVO[] vos) {

    // 销售出库结算查询接口
    ISquare434CQuery square4cQry =
        NCLocator.getInstance().lookup(ISquare434CQuery.class);

    // 查询上游出库单发出商品记录
    String[] regOutBids =
        square4cQry.queryREGCostBidBy4CBID(AggVOUtil.getDistinctItemFieldArray(
            vos, SquareInvBVO.CSRCBID, String.class));

    // [0] -- 发出商品 [1] -- 成本结算
    SquareInvVO[][] svos = new SquareInvVO[2][];
    // 销售发票全部成本结算
    if (regOutBids == null || regOutBids.length == 0) {
      svos[0] = null;
      svos[1] = vos;
    }
    else {
      Set<String> set_etOutBids =
          new HashSet<String>(Arrays.asList(regOutBids));

      // 成本结算VO
      List<SquareInvViewVO> l_viewvo_co = new ArrayList<SquareInvViewVO>();
      // 发出商品VO
      List<SquareInvViewVO> l_viewvo_re = new ArrayList<SquareInvViewVO>();
      SquareInvViewVO[] sviewvos =
          SquareInvVOUtils.getInstance().changeToSaleSquareViewVO(vos);
      for (SquareInvViewVO svo : sviewvos) {
        // 待发出商品结算vo
        if (set_etOutBids.contains(svo.getItem().getCsrcbid())) {
          svo.getItem().setFpreiatype(
              SquareType.SQUARETYPE_REG_CREDIT.getIntegerValue());
          l_viewvo_re.add(svo);
        }
        // 待成本结算vo
        else {
          svo.getItem().setFpreiatype(
              SquareType.SQUARETYPE_IA.getIntegerValue());
          l_viewvo_co.add(svo);
        }
      }
      svos[0] =
          SquareInvVOUtils.getInstance().combineBill(
              l_viewvo_re.toArray(new SquareInvViewVO[0]));
      svos[1] =
          SquareInvVOUtils.getInstance().combineBill(
              l_viewvo_co.toArray(new SquareInvViewVO[0]));
    }

    return svos;
  }

}
