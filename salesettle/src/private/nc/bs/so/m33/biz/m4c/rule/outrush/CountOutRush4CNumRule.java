package nc.bs.so.m33.biz.m4c.rule.outrush;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.bs.so.m33.biz.m4c.bp.pub.ProcessWC;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.so.m33.ref.ic.m4c.ICM4CServiceUtil;
import nc.vo.ic.m4c.entity.SaleOutBodyVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.m33.pub.util.ParaUtils;

/**
 * @description
 * 蓝字出库单可对冲数量
 * 
 * 基于出库数量开票：
 * 可对冲数量 = 出库数量–max(累计开票数量+累计途损数量，X）–累计出库对冲数量
 * X：如果出库单传确认应收，或出库单手工结算（收入或成本结算)
 * X = 出库单累计结算数量;否则X = 0
 * 
 * 基于签收数量开票：
 * 可对冲数量 = 签收数量 – 累计开票数量 – 累计出库对冲数量；
 * 
 * 红字出库单可对冲数量：
 * 参与对冲的红字出库单，必须是累计应收结算数量=0，累计对冲数量=0，累计开票数量=0，累计途损数量 = 0；
 * 可对冲数量 = 出库单数量。否则可对冲数量 = 0
 * @scene
 * 销售出库单出库对冲前
 * @param 
 * 无
 * @since 6.0
 * @version 2010-11-29 下午03:38:53
 * @author zhangcheng
 */
public class CountOutRush4CNumRule implements ICompareRule<SquareOutViewVO> {

  @Override
  public void process(SquareOutViewVO[] bluevos, SquareOutViewVO[] redvos) {
    List<String> lbid = new ArrayList<String>();
    for (SquareOutViewVO blue : bluevos) {
      lbid.add(blue.getItem().getCsquarebillbid());
    }
    for (SquareOutViewVO red : redvos) {
      lbid.add(red.getItem().getCsquarebillbid());
    }

    // 调用库存接口，查询出库单累计开票数量、累计途损数量、累计出库对冲数量 、累计签收数量
    SaleOutBodyVO[] outBvos = null;
    try {
      outBvos =
          ICM4CServiceUtil.queryTotalNumBy4CBids(lbid.toArray(new String[0]));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }

    // 计算本次出库对冲数量
    if (null != outBvos) {
      Map<String, SaleOutBodyVO> m4cbvo = new HashMap<String, SaleOutBodyVO>();
      for (SaleOutBodyVO bvo : outBvos) {
        m4cbvo.put(bvo.getPrimaryKey(), bvo);
      }
      this.countRushNum(bluevos, redvos, m4cbvo);
    }

  }

  private void checkOutNum(SquareOutViewVO vo) {
    if (vo.getItem().getNthisnum().compareTo(UFDouble.ZERO_DBL) == 0) {
      String vbillcode = vo.getHead().getVbillcode();
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006010_0", "04006010-0096", null, new String[] {
            vbillcode
          })/*单据号为：{0} 的出库单可对冲数量不能为0!*/);
    }
  }

  private void countBlueRushNum(SquareOutViewVO[] bluevos,
      Map<String, SaleOutBodyVO> m4cbvo, UFDouble redTotalNum) {
    // 表体销售组织集合
    Set<String> set = new HashSet<String>();
    String csaleorgid = null;
    for (SquareOutViewVO vo : bluevos) {
      csaleorgid = vo.getItem().getCsaleorgid();
      if (!set.contains(csaleorgid)) {
        set.add(csaleorgid);
      }
    }
    String[] saleorgid = set.toArray(new String[0]);

    // 是否基于签收开票<销售组织oid,业务流程set>
    Map<String, List<String>> mifBaseSignInvoice =
        ParaUtils.getifBaseSignInvoiceBiz(saleorgid);

    // 蓝字出库单可对冲数量
    for (SquareOutViewVO bluevo : bluevos) {
      // 基于签收开票的业务流程
      List<String> sbiz =
          mifBaseSignInvoice.get(bluevo.getItem().getCsaleorgid());
      // 基于签收开票
      if (sbiz != null && sbiz.size() > 0
          && sbiz.contains(bluevo.getHead().getCbiztypeid())) {
        this.countBlueRushNumForSign(bluevo, m4cbvo, redTotalNum);
      }
      // 基于出库开票
      else {
        this.countBlueRushNumForOut(bluevo, m4cbvo, redTotalNum);
      }
    }

    // 由于对冲数量可能不等于出库单结算数量所以需要重新计算
    new ProcessWC().reCalNumMny(bluevos);
  }

  private void countBlueRushNumForOut(SquareOutViewVO bluevo,
      Map<String, SaleOutBodyVO> m4cbvo, UFDouble redTotalNum) {
    // 出库数量
    UFDouble nout = bluevo.getItem().getNnum();
    // 累计开票数量
    UFDouble ninvoice =
        m4cbvo.get(bluevo.getItem().getCsquarebillbid()).getNsignnum();
    ninvoice = MathTool.nvl(ninvoice);
    // 累计途损数量
    UFDouble nwas =
        m4cbvo.get(bluevo.getItem().getCsquarebillbid()).getNaccumwastnum();
    nwas = MathTool.nvl(nwas);
    // 累计出库对冲数量
    UFDouble nrush =
        m4cbvo.get(bluevo.getItem().getCsquarebillbid()).getNrushnum();
    nrush = MathTool.nvl(nrush);
    // 累计结算数量
    UFDouble nsquare = bluevo.getItem().getNsquarearnum();
    // 只控制应收结算和手工结算
    if (nsquare == null || nsquare.compareTo(UFDouble.ZERO_DBL) == 0) {
      UFBoolean bautosquarecost = bluevo.getHead().getBautosquarecost();
      if (!bautosquarecost.booleanValue()) {
        nsquare = bluevo.getItem().getNsquareianum();
      }
    }
    nsquare = MathTool.nvl(nsquare);

    UFDouble temp = ninvoice.add(nwas);
    if (temp.compareTo(nsquare) >= 0) {
      nsquare = temp;
    }

    UFDouble ncanrushout = nout.sub(nsquare).sub(nrush);
    this.setBlueRushNum(bluevo, ncanrushout, redTotalNum);
  }

  private void countBlueRushNumForSign(SquareOutViewVO bluevo,
      Map<String, SaleOutBodyVO> m4cbvo, UFDouble redTotalNum) {
    // 累计签收数量
    UFDouble nsign =
        m4cbvo.get(bluevo.getItem().getCsquarebillbid()).getNaccumoutsignnum();
    // 累计开票数量
    UFDouble ninvoice =
        m4cbvo.get(bluevo.getItem().getCsquarebillbid()).getNsignnum();
    // 累计出库对冲数量
    UFDouble nrush =
        m4cbvo.get(bluevo.getItem().getCsquarebillbid()).getNrushnum();
    // 可对冲数量 = 累计签收数量 - 累计开票数量 - 累计出库对冲数量
    UFDouble ncanrushout = nsign.sub(ninvoice).sub(nrush);

    this.setBlueRushNum(bluevo, ncanrushout, redTotalNum);
  }

  private void countRedRushNum(SquareOutViewVO[] redvos,
      Map<String, SaleOutBodyVO> m4cbvo) {
    // 红字出库单可对冲数量
    SaleOutBodyVO outbvo = null;
    for (SquareOutViewVO redvo : redvos) {
      outbvo = m4cbvo.get(redvo.getItem().getCsquarebillbid());
      UFDouble total32num = outbvo.getNsignnum();
      UFDouble total4453num = outbvo.getNaccumwastnum();
      UFDouble totalrushnum = outbvo.getNrushnum();
      // 只控制应收
      UFDouble total33arnum = redvo.getItem().getNsquarearnum();
      if (total32num != null && total32num.compareTo(UFDouble.ZERO_DBL) != 0
          || total4453num != null
          && total4453num.compareTo(UFDouble.ZERO_DBL) != 0
          || totalrushnum != null
          && totalrushnum.compareTo(UFDouble.ZERO_DBL) != 0
          || total33arnum != null
          && total33arnum.compareTo(UFDouble.ZERO_DBL) != 0) {
        redvo.getItem().setNthisnum(UFDouble.ZERO_DBL);
      }
      else {
        redvo.getItem().setNthisnum(redvo.getItem().getNnum());
      }
      // 红字出库单可对冲数量不能为0
      this.checkOutNum(redvo);
    }
  }

  private void countRushNum(SquareOutViewVO[] bluevos,
      SquareOutViewVO[] redvos, Map<String, SaleOutBodyVO> m4cbvo) {
    this.countRedRushNum(redvos, m4cbvo);

    // 红字出库单累计可出库对冲数量的绝对值
    UFDouble redTotalNum = UFDouble.ZERO_DBL;
    for (SquareOutViewVO redvo : redvos) {
      redTotalNum = MathTool.add(redTotalNum, redvo.getItem().getNthisnum());
    }
    redTotalNum = MathTool.abs(redTotalNum);

    this.countBlueRushNum(bluevos, m4cbvo, redTotalNum);
  }

  /**
   * 
   * @param bluevo ------- 蓝字出库待结算单
   * @param ncanrushout -- 蓝字出库单可出库对冲数量
   * @param redTotalNum -- 红字出库单累计可出库对冲数量的绝对值
   */
  private void setBlueRushNum(SquareOutViewVO bluevo, UFDouble ncanrushout,
      UFDouble redTotalNum) {
    // 可对冲数量 = min(可对冲数量,累计红字出库单可对冲数量)
    if (MathTool.lessThan(ncanrushout, redTotalNum)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006010_0", "04006010-0030")/*@res "蓝字出库单可对冲数量必须大于等于红字出库单可对冲数量"*/);
    }
    bluevo.getItem().setNthisnum(redTotalNum);
    // 蓝字出库单可对冲数量不能为0
    this.checkOutNum(bluevo);
  }

}
