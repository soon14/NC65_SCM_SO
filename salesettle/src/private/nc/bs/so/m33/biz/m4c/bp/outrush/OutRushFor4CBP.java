package nc.bs.so.m33.biz.m4c.bp.outrush;

import java.util.ArrayList;
import java.util.List;

import nc.bs.so.m33.biz.m4c.bp.square.ar.SquareARRushIncomeFor4CRushBP;
import nc.bs.so.m33.biz.m4c.bp.square.ia.SquareIARegisterCreditFor4COutRushBP;
import nc.bs.so.m33.biz.m4c.rule.outrush.CheckOutRush4CRule;
import nc.bs.so.m33.biz.m4c.rule.outrush.CountOutRush4CNumRule;
import nc.bs.so.m33.maintain.m4c.InsertSquareOutDetailBP;
import nc.bs.so.m33.maintain.m4c.rule.detail.RewriteOutRush4CRule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.tool.performance.DeepCloneTool;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.m33.pub.util.SquareCalculatorForVO;
import nc.vo.so.pub.util.CirVOUtil;
import nc.vo.trade.checkrule.VOChecker;

/**
 * 
 * @since 6.0
 * @version 2012-8-15 下午05:11:03
 * @author 么贵敬
 */
public class OutRushFor4CBP {

  /**
   * 销售出库单出库对冲
   * 
   * @param sqvos
   */
  public UFDouble square(SquareOutViewVO[] bluevos, SquareOutViewVO[] redvos) {

    CompareAroundProcesser<SquareOutViewVO> processer =
        new CompareAroundProcesser<SquareOutViewVO>(BPPlugInPoint.SquareOutRushByViewVO);

    TimeLog.logStart();
    this.addBeforeRule(processer);
    processer.before(bluevos, redvos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006010_0", "04006010-0020")/* @res "出库对冲前执行业务规则-业务规则检查" */);

    // 将待出库对冲结算VO转化为出库对冲结算明细VO
    SquareOutDetailVO[] bills =
        SquareOutVOUtils.getInstance().changeSQVOtoSQDVOForOUTRUSH(bluevos,
            redvos);

    // 合并vo
    List<SquareOutViewVO> lview = CirVOUtil.combineBill(bluevos, redvos);
    SquareOutVO[] sqvos =
        SquareOutVOUtils.getInstance().combineBill(
            lview.toArray(new SquareOutViewVO[0]));

    // 出库对冲明细保存BP
    new InsertSquareOutDetailBP().insert(sqvos, bills);

    // 处理对冲
    this.processARRush(bluevos, redvos);
    // 处理发出商品
    this.processREGCost(bluevos, redvos);

    TimeLog.logStart();
    AroundProcesser<SquareOutDetailVO> afprocesser =
        new AroundProcesser<SquareOutDetailVO>(BPPlugInPoint.SquareOutRushByDetailVO);
    this.addAfterRule(afprocesser);
    afprocesser.after(bills);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006010_0", "04006010-0021")/* @res "出库对冲后执行业务规则-回写销售出库单待结算单" */);

    return bluevos[0].getItem().getNrushnum();

  }

  private void processREGCost(SquareOutViewVO[] bluevos,
      SquareOutViewVO[] redvos) {
    // 处理发出商品
    SquareOutViewVO[] regbluevos =
        SquareOutVOUtils.getInstance().deepClone(bluevos);
    SquareOutViewVO[] regredvos =
        SquareOutVOUtils.getInstance().deepClone(redvos);
    // 清空对冲结算明细id
    this.setDIDNull(regbluevos);
    this.setDIDNull(regredvos);
    this.processREGCostDetail(regbluevos, regredvos);
  }

  private void processREGCostDetail(SquareOutViewVO[] bluevos,
      SquareOutViewVO[] redvos) {
    // 过滤红字
    List<SquareOutViewVO> listred = new ArrayList<SquareOutViewVO>();
    this.addREGCostVO(redvos, listred);

    // 过滤蓝字
    List<SquareOutViewVO> listblue = new ArrayList<SquareOutViewVO>();
    this.addREGCostVO(bluevos, listblue);

    SquareOutViewVO[] blueview = null;
    SquareOutViewVO[] redview = null;
    if (listblue.size() > 0) {
      blueview = listblue.toArray(new SquareOutViewVO[listblue.size()]);
    }
    if (listred.size() > 0) {
      redview = listred.toArray(new SquareOutViewVO[listred.size()]);
    }

    // 蓝字红字同时传发出商品
    if (listred.size() > 0 && listblue.size() > 0) {
      // 重新设置蓝字出库vo，要求蓝字表体行数和红字表体行数相等，数量=对应红字出库单行数量
      SquareOutViewVO[] newbluevos = this.resetBluevoByRedvo(blueview, redview);
      // 合并vo
      List<SquareOutViewVO> lview = CirVOUtil.combineBill(newbluevos, redview);
      SquareOutVO[] sqvos =
          SquareOutVOUtils.getInstance().combineBill(
              lview.toArray(new SquareOutViewVO[0]));
      new SquareIARegisterCreditFor4COutRushBP().square(sqvos);
    }
    // 蓝字传发出商品
    else if (listblue.size() > 0) {
      // 重新设置蓝字出库vo，要求蓝字表体行数和红字表体行数相等，数量=对应红字出库单行数量
      SquareOutViewVO[] newbluevos = this.resetBluevoByRedvo(blueview, redvos);
      SquareOutVO[] sqvos =
          SquareOutVOUtils.getInstance().combineBill(newbluevos);
      new SquareIARegisterCreditFor4COutRushBP().square(sqvos);
    }
    // 红字传发出商品
    else if (listred.size() > 0) {
      SquareOutVO[] sqvos = SquareOutVOUtils.getInstance().combineBill(redview);
      new SquareIARegisterCreditFor4COutRushBP().square(sqvos);
    }
  }

  private void processARRush(SquareOutViewVO[] bluevos, SquareOutViewVO[] redvos) {
    // 处理回冲
    SquareOutViewVO[] rushbluevos =
        SquareOutVOUtils.getInstance().deepClone(bluevos);
    SquareOutViewVO[] rushredvos =
        SquareOutVOUtils.getInstance().deepClone(redvos);
    // 清空对冲结算明细id
    this.setDIDNull(rushbluevos);
    this.setDIDNull(rushredvos);
    this.processARRushDetail(rushbluevos, rushredvos);
  }

  private void processARRushDetail(SquareOutViewVO[] bluevos,
      SquareOutViewVO[] redvos) {
    // 过滤红字
    List<SquareOutViewVO> listred = new ArrayList<SquareOutViewVO>();
    this.addARRoushVO(redvos, listred);

    // 过滤蓝字
    List<SquareOutViewVO> listblue = new ArrayList<SquareOutViewVO>();
    this.addARRoushVO(bluevos, listblue);

    SquareOutViewVO[] blueview = null;
    SquareOutViewVO[] redview = null;
    if (listblue.size() > 0) {
      blueview = listblue.toArray(new SquareOutViewVO[listblue.size()]);
    }
    if (listred.size() > 0) {
      redview = listred.toArray(new SquareOutViewVO[listred.size()]);
    }

    // 蓝字红字同时回冲应收
    if (!VOChecker.isEmpty(blueview) && !VOChecker.isEmpty(redview)) {
      // 重新设置蓝字出库vo，要求蓝字表体行数和红字表体行数相等，数量=对应红字出库单行数量
      SquareOutViewVO[] newbluevos = this.resetBluevoByRedvo(blueview, redview);
      // 将待回冲数据数量取反
      this.setOpposeNumData(newbluevos, redview);
      new SquareARRushIncomeFor4CRushBP().square(newbluevos, redview);
    }
    // 蓝字回冲应收
    else if (!VOChecker.isEmpty(blueview)) {
      // 重新设置蓝字出库vo，要求蓝字表体行数和红字表体行数相等，数量=对应红字出库单行数量
      SquareOutViewVO[] newbluevos = this.resetBluevoByRedvo(blueview, redvos);
      // 将待回冲数据数量取反
      this.setOpposeNumData(newbluevos, null);
      SquareOutVO[] sqvos =
          SquareOutVOUtils.getInstance().combineBill(newbluevos);
      new SquareARRushIncomeFor4CRushBP().square(sqvos);
    }
    // 红字回冲应收
    else if (!VOChecker.isEmpty(redview)) {
      // 将待回冲数据数量取反
      this.setOpposeNumData(redview, null);
      SquareOutVO[] sqvos = SquareOutVOUtils.getInstance().combineBill(redview);
      new SquareARRushIncomeFor4CRushBP().square(sqvos);
    }
  }

  /**
   * 将待数量取反，重新计算单价数量、金额
   * 
   * @param bluevos
   * @param redvos
   */
  private void setOpposeNumData(SquareOutViewVO[] bluevos,
      SquareOutViewVO[] redvos) {
    if (!VOChecker.isEmpty(bluevos)) {
      for (SquareOutViewVO view : bluevos) {
        this.setOpposeNumData(view);
      }
    }
    if (!VOChecker.isEmpty(redvos)) {
      for (SquareOutViewVO view : redvos) {
        this.setOpposeNumData(view);
      }
    }
  }

  private void setOpposeNumData(SquareOutViewVO view) {

    SquareOutBVO body = view.getItem();
    // 结算主数量
    body.setNthisnum(MathTool.oppose(body.getNthisnum()));
    // 结算数量
    body.setNastnum(MathTool.oppose(body.getNastnum()));
    // 原币金额
    body.setNorigtaxmny(MathTool.oppose(body.getNorigtaxmny()));
    body.setNorigmny(MathTool.oppose(body.getNorigmny()));
    // TODO 2012.02.16 fbinly v61删除原币税额字段
    // ntempnum = MathTool.oppose(view.getItem().getNorigtax());
    // view.getItem().setNorigtax(ntempnum);
    body.setNorigdiscount(MathTool.oppose(body.getNorigdiscount()));
    // 本币金额
    body.setNtaxmny(MathTool.oppose(body.getNtaxmny()));
    body.setNmny(MathTool.oppose(body.getNmny()));
    body.setNtax(MathTool.oppose(body.getNtax()));
    body.setNdiscount(MathTool.oppose(body.getNdiscount()));

    // 2012.02.16 fbinly v61新增字段
    body.setNcaltaxmny(MathTool.oppose(body.getNcaltaxmny()));

  }

  private void addARRoushVO(SquareOutViewVO[] svvos,
      List<SquareOutViewVO> lARRoushVO) {
    for (SquareOutViewVO svo : svvos) {
      UFDouble ntotalestnum = svo.getItem().getNsquareestnum();
      // 累计暂估应收数量不为0，则需要传回冲应收
      if (!VOChecker.isEmpty(ntotalestnum)
          && ntotalestnum.compareTo(UFDouble.ZERO_DBL) != 0) {
        lARRoushVO.add(svo);
      }
    }
  }

  private SquareOutViewVO[] resetBluevoByRedvo(SquareOutViewVO[] bluevos,
      SquareOutViewVO[] redvos) {
    // 根据红字出库单设置蓝字出库单
    List<SquareOutViewVO> list = new ArrayList<SquareOutViewVO>();
    SquareOutViewVO bluevo = null;
    DeepCloneTool dct = new DeepCloneTool();
    for (SquareOutViewVO redview : redvos) {
      // 蓝字克隆
      UFDouble nbluenum = MathTool.abs(bluevos[0].getItem().getNthisnum());
      UFDouble nrednum = MathTool.abs(redview.getItem().getNthisnum());
      // 蓝字数量大于红字数量
      if (MathTool.greaterThan(nbluenum, nrednum)) {
        bluevo = (SquareOutViewVO) dct.deepClone(bluevos[0]);
        bluevo.getItem().setNthisnum(nrednum);
        list.add(bluevo);
      }
    }

    SquareOutViewVO[] rets = null;
    if (list.size() > 0) {
      rets = list.toArray(new SquareOutViewVO[list.size()]);
      SquareOutBVO[] bvos =
          SquareOutVOUtils.getInstance().getSquareOutBVO(rets);
      // 重新计算单价数量金额
      new SquareCalculatorForVO().calculate(bvos, SquareOutBVO.NTHISNUM);
    }
    else {
      rets = bluevos;
    }

    return rets;
  }

  private void addREGCostVO(SquareOutViewVO[] svvos,
      List<SquareOutViewVO> lREGCostVO) {
    for (SquareOutViewVO svo : svvos) {
      UFDouble ntotalregnum = svo.getItem().getNsquareregnum();
      // 累计发出商品数量不为0，则需要传相反的发出商品
      if (!VOChecker.isEmpty(ntotalregnum)
          && ntotalregnum.compareTo(UFDouble.ZERO_DBL) != 0) {
        lREGCostVO.add(svo);
      }
    }
  }

  /**
   * 
   * 将结算明细id置Null：因为之前出库对冲明细已经生成，
   * 所以当前传发出商品vo中有结算明细id
   */
  private void setDIDNull(SquareOutViewVO[] views) {
    if (VOChecker.isEmpty(views)) {
      return;
    }
    for (SquareOutViewVO svo : views) {
      svo.getItem().setCsalesquaredid(null);
    }
  }

  private void addAfterRule(AroundProcesser<SquareOutDetailVO> processer) {
    IRule<SquareOutDetailVO> rule = null;

    // 回写累计对冲数量
    rule = new RewriteOutRush4CRule();
    processer.addAfterRule(rule);
  }

  private void addBeforeRule(CompareAroundProcesser<SquareOutViewVO> processer) {
    ICompareRule<SquareOutViewVO> rule = null;

    // 检查销售出库待结算单是否可以参与出库对冲
    rule = new CheckOutRush4CRule();
    processer.addBeforeRule(rule);

    // 计算可出库对冲数量
    rule = new CountOutRush4CNumRule();
    processer.addBeforeRule(rule);

  }

}
