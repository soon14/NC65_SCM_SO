package nc.bs.so.m33.biz.m4c.action.outrush;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;

import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.util.CirVOUtil;
import nc.vo.so.pub.util.ViewVOUtil;
import nc.vo.so.pub.votools.SoVoTools;

import nc.bs.so.m33.biz.m4c.bp.cancelsquare.CancelSquareFor4CPubBP;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBizBP;

public class CancelOutRushFor4CAction {

  /**
   * 蓝字出库单取消出库对冲：蓝字和对应的红字出库单全部取消出库对冲
   * 
   * @param bluevos
   */
  public void cancelBlueOutRush(SquareOutViewVO[] blueview) {
    String[] rushbatchid =
        ViewVOUtil.getDistinctFieldArray(blueview, SquareOutBVO.class,
            SquareOutBVO.CRUSHOUTBATCHID, String.class);

    // 查询蓝字对冲出库单对应的对冲明细记录
    String[] blueOutBids =
        ViewVOUtil.getDistinctFieldArray(blueview, SquareOutBVO.class,
            SquareOutBVO.CSQUAREBILLBID, String.class);
    QuerySquare4CVOBizBP bizqry = new QuerySquare4CVOBizBP();
    SquareOutDetailVO[] bluedvos =
        bizqry.queryOutRushSquareOutDetailVOBy4CBID(blueOutBids, rushbatchid);

    // 查询蓝字对冲出库单对应的红字对冲出库单
    String[] redOutBids =
        SoVoTools.getVOsOnlyValues(bluedvos, SquareOutDetailVO.CRUSHGENERALBID);
    SquareOutViewVO[] redview =
        new QuerySquare4CVOBP().querySquareOutViewVOBy4CBID(redOutBids);

    // 查询红字对冲出库单得对冲明细记录
    SquareOutDetailVO[] reddvos =
        bizqry.queryOutRushSquareOutDetailVOBy4CBID(redOutBids, rushbatchid);

    // 合并VO
    SquareOutVO[] sqvos = this.combineSquareOutViewVO(blueview, redview);
    SquareOutDetailVO[] sqdvos =
        this.combineSquareOutDetailVO(bluedvos, reddvos);

    // 取消结算明细
    new CancelSquareFor4CPubBP().cancelSquare(sqdvos, sqvos);
  }

  /**
   * 红字字出库单取消出库对冲：红字出库单全部取消出库对冲，对应的蓝字出库单取消对应数量的出库对冲
   * 
   * @param redvos
   */
  public void cancelRedOutRush(SquareOutViewVO[] redview) {
    QuerySquare4CVOBizBP bizqry = new QuerySquare4CVOBizBP();
    String[] rushbatchid =
        ViewVOUtil.getDistinctFieldArray(redview, SquareOutBVO.class,
            SquareOutBVO.CRUSHOUTBATCHID, String.class);

    // 查询红字对冲出库单对应的对冲明细记录
    String[] redOutBids =
        ViewVOUtil.getDistinctFieldArray(redview, SquareOutBVO.class,
            SquareOutBVO.CSQUAREBILLBID, String.class);
    SquareOutDetailVO[] reddvos =
        bizqry.queryOutRushSquareOutDetailVOBy4CBID(redOutBids, rushbatchid);

    // 查询红字对冲出库单对应的蓝字对冲出库单
    String[] blueOutBids =
        SoVoTools.getVOsOnlyValues(reddvos, SquareOutDetailVO.CRUSHGENERALBID);
    SquareOutViewVO[] blueview =
        new QuerySquare4CVOBP().querySquareOutViewVOBy4CBID(blueOutBids);

    // 查询蓝字对冲出库单对应此张红字出库单的对冲明细记录
    SquareOutDetailVO[] bluedvos =
        bizqry.queryOutRushSquareOutDetailVOBy4CBID(blueOutBids, rushbatchid);
    List<SquareOutDetailVO> bluevosList = new ArrayList<SquareOutDetailVO>();
    Set<String> redbidSet = new HashSet<String>();
    // 需要根据红字id过滤一下
    if (!ArrayUtils.isEmpty(redOutBids) && !ArrayUtils.isEmpty(bluedvos)) {
      for (String key : redOutBids) {
        redbidSet.add(key);
      }
      for (SquareOutDetailVO vo : bluedvos) {
        String rushgeneralbid = vo.getCrushgeneralbid();
        if (PubAppTool.isNull(rushgeneralbid)
            || redbidSet.contains(rushgeneralbid)) {
          bluevosList.add(vo);
        }
      }
    }
    bluedvos = bluevosList.toArray(new SquareOutDetailVO[bluevosList.size()]);
    // 合并VO
    SquareOutVO[] sqvos = this.combineSquareOutViewVO(redview, blueview);
    SquareOutDetailVO[] sqdvos =
        this.combineSquareOutDetailVO(reddvos, bluedvos);

    // 取消结算明细
    new CancelSquareFor4CPubBP().cancelSquare(sqdvos, sqvos);

  }

  private SquareOutDetailVO[] combineSquareOutDetailVO(SquareOutDetailVO[] voa,
      SquareOutDetailVO[] vob) {
    List<SquareOutDetailVO> lsdvo = CirVOUtil.combineBill(voa, vob);
    SquareOutDetailVO[] sqdvos = lsdvo.toArray(new SquareOutDetailVO[0]);
    return sqdvos;
  }

  private SquareOutVO[] combineSquareOutViewVO(SquareOutViewVO[] voa,
      SquareOutViewVO[] vob) {
    List<SquareOutViewVO> lview = CirVOUtil.combineBill(voa, vob);
    SquareOutVO[] sqvos =
        SquareOutVOUtils.getInstance().combineBill(
            lview.toArray(new SquareOutViewVO[0]));
    return sqvos;
  }

}
