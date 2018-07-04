package nc.bs.so.m33.biz.m4c.bp.square.ar;

import java.util.List;
import java.util.Map;

import nc.bs.so.m33.biz.m4c.rule.toar.GetNewARorgVidFor4CRule;
import nc.bs.so.m33.biz.m4c.rule.toar.ToARCheckFor4CRule;
import nc.bs.so.m33.maintain.m4c.InsertSquareOutDetailBP;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBizBP;
import nc.bs.so.m33.maintain.m4c.rule.detail.RewriteARRushIncomeFor4CRule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.database.DBTool;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.arap.ArapServicesForSOUtil;
import nc.vo.arap.verify.AdjuestVO;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.calculator.PriceNumMnyCalculatorForVO;
import nc.vo.so.pub.util.CirVOUtil;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * 出库对冲传回冲应收BP
 * 
 * @since 6.0
 * @version 2011-7-1 下午12:41:27
 * @author zhangcheng
 */
public class SquareARRushIncomeFor4CRushBP {

  public void square(SquareOutViewVO[] bluevos, SquareOutViewVO[] redvos) {
    // 合并vo
    List<SquareOutViewVO> lview = CirVOUtil.combineBill(bluevos, redvos);
    SquareOutVO[] sqvos =
        SquareOutVOUtils.getInstance().combineBill(
            lview.toArray(new SquareOutViewVO[0]));
    // 将传回冲收入结算单VO转化为传回冲收入结算明细VO
    SquareOutDetailVO[] bills =
        SquareOutVOUtils.getInstance().changeSQVOtoSQDVOForARRUSH(bluevos,
            redvos);
    this.procesSquare(sqvos, bills);
  }

  public void square(SquareOutVO[] sqvos) {
    // 将传回冲收入结算单VO转化为传回冲收入结算明细VO
    SquareOutDetailVO[] bills =
        SquareOutVOUtils.getInstance().changeSQVOtoSQDVOForARRUSH(sqvos);
    this.procesSquare(sqvos, bills);
  }

  private void addAfterRule(AroundProcesser<SquareOutDetailVO> processer) {
    // 回写累计回冲结算数量
    IRule<SquareOutDetailVO> rule = new RewriteARRushIncomeFor4CRule();
    processer.addAfterRule(rule);
  }

  private void addBeforeRule(AroundProcesser<SquareOutVO> processer) {
    // 应收结算前获取应收组织最新组织版本
    IRule<SquareOutVO> rule = new GetNewARorgVidFor4CRule();
    processer.addBeforeRule(rule);

    rule = new ToARCheckFor4CRule();
    processer.addBeforeRule(rule);
  }

  private void procesSquare(SquareOutVO[] sqvos, SquareOutDetailVO[] dvos) {
    AroundProcesser<SquareOutVO> processer =
        new AroundProcesser<SquareOutVO>(BPPlugInPoint.SquareARRushIncomeFor4CRushBP);

    // 增加执行前业务规则
    this.addBeforeRule(processer);
    processer.before(sqvos);

    // 保存回冲明细vo
    SquareOutDetailVO[] dnewvos = this.saveSquareOutDetail(dvos, sqvos);

    // 传回冲应收
    this.toEstVerify(dnewvos);

    AroundProcesser<SquareOutDetailVO> aprocesser =
        new AroundProcesser<SquareOutDetailVO>(BPPlugInPoint.SquareARRushIncomeFor4CDetailRushBP);
    this.addAfterRule(aprocesser);
    aprocesser.after(dvos);
  }

  private SquareOutDetailVO[] saveSquareOutDetail(SquareOutDetailVO[] bills,
      SquareOutVO[] sqvos) {

    DBTool dao = new DBTool();
    String[] pks = dao.getOIDs(bills.length);
    int i = 0;
    for (SquareOutDetailVO dvo : bills) {
      dvo.setCsalesquaredid(pks[i]);
      dvo.setProcesseid(pks[i]);
      i++;
    }
    // 重新根据回冲数量计算金额（因为数量取反需重算）
    PriceNumMnyCalculatorForVO cal = new PriceNumMnyCalculatorForVO();
    Condition cond = new Condition();
    cond.setUnitPriorType(Condition.MAIN_PRIOR);
    cal.setCondition(cond);
    cal.calculate(bills, SquareOutDetailVO.NNUM);

    // 回冲应收明细保存BP
    new InsertSquareOutDetailBP().insert(sqvos, bills);

    return bills;
  }

  private void toEstVerify(SquareOutDetailVO[] dvos) {
    String[] outbids =
        SoVoTools.getVOsOnlyValues(dvos, SquareOutDetailVO.CSQUAREBILLBID);
    Map<String, SquareOutDetailVO> map =
        new QuerySquare4CVOBizBP().queryETSquareOutDetailVOBy4CBID(outbids);
    // 没有暂估数据，说明数据异常
    if (map.size() == 0) {
      ExceptionUtils.unSupported();
    }

    // 准备回冲应收接口参数
    AdjuestVO[] vos = new AdjuestVO[dvos.length];
    int i = 0;
    for (SquareOutDetailVO dvo : dvos) {
      vos[i] = new AdjuestVO();
      // 销售出库暂估结算单
      SquareOutDetailVO etvo = map.get(dvo.getCsquarebillbid());
      // 将暂估的出库结算单id赋给回冲应收单
      vos[i].setEst_top_itemid(etvo.getCsalesquaredid());
      // 回冲结算明细id传给回冲应收单
      vos[i].setTop_itemid(dvo.getCsalesquaredid());
      // 回冲批次号，回冲应收单使用
      // TODO 其实有了回冲结算明细id传给回冲应收单，回冲批次号也没有用了，取消时直接用回冲结算明细id
      vos[i].setClbh(dvo.getProcesseid());
      // 回冲数量(应收应付要求数量符号和暂估数量一样,而销售结算存储符号与暂估数量相反,所以取反)
      vos[i].setQuantity(MathTool.oppose(dvo.getNsquarenum()));
      i++;
    }

    // 回冲应收传财务
    ArapServicesForSOUtil.estVerify(vos);
  }

}
