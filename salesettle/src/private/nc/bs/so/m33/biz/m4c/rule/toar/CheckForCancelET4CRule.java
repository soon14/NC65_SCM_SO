package nc.bs.so.m33.biz.m4c.rule.toar;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m33.maintain.m32.query.QuerySquare32VOBP;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvViewVO;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutHVO;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.util.SOVOChecker;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * @description
 * 取消暂估应收时的业务校验
 * @scene
 * 取消暂估应收前
 * @param
 * 无
 * @since 6.0
 * @version 2011-9-20 下午05:51:45
 * @author zhangcheng
 */
public class CheckForCancelET4CRule implements IRule<SquareOutViewVO> {

  @Override
  public void process(SquareOutViewVO[] vos) {
    // 查询下游销售发票待结算单
    String[] outbids =
        SoVoTools.getVOsOnlyValues(vos, SquareOutBVO.CSQUAREBILLBID);
    String[] outhids =
        SoVoTools.getVOsOnlyValues(vos, SquareOutBVO.CSQUAREBILLID);
    String[] querykeys = new String[] {
      SquareInvBVO.CSRCBID, SquareInvBVO.NARRUSHNUM
    };
    SquareInvViewVO[] invviews =
        new QuerySquare32VOBP().querySquareInvViewVOBy4CBIDHID(outbids,
            outhids, querykeys);
    // <出库单表体id,SquareInvViewVO>
    Map<String, SquareInvViewVO> minv = new HashMap<String, SquareInvViewVO>();
    for (SquareInvViewVO view : invviews) {
      minv.put(view.getItem().getCsrcbid(), view);
    }

    // 下游发票已经有回冲应收，则不可以取消暂估应收
    for (SquareOutViewVO view : vos) {
      SquareInvViewVO invview = minv.get(view.getItem().getCsquarebillbid());
      // 发票应收结算产生回冲应收
      UFDouble ndowninvrushnum = UFDouble.ZERO_DBL;
      if (!SOVOChecker.isEmpty(invview)) {
        ndowninvrushnum = invview.getItem().getNarrushnum();
      }
      // 出库对红产生回冲应收
      UFDouble ndownarrushnum = view.getItem().getNarrushnum();
      SquareOutHVO hvo = view.getHead();
      String billno = hvo.getVbillcode();
      if (!MathTool.isZero(ndowninvrushnum) || !MathTool.isZero(ndownarrushnum)) {
//        ExceptionUtils.wrappBusinessException(billno
//            + "出库单下游已经有回冲应收，则不可以取消暂估应收");
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
            "4006010_0", "04006010-0142",null,new String[]{billno})/*{0}出库单下游已经有回冲应收，则不可以取消暂估应收
*/);
      }
    }

  }
}
