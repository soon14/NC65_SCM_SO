package nc.bs.so.m33.biz.m32.bp.square.toar;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m33.biz.m32.rule.toar.GetNewARorgVidFor32Rule;
import nc.bs.so.m33.biz.m32.rule.toar.ToARCheckFor32Rule;
import nc.bs.so.m33.maintain.m32.InsertSquare32DetailBP;
import nc.bs.so.m33.maintain.m32.rule.detail.RewriteARRushIncomeFor32Rule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.database.DBTool;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.arap.ArapServicesForSOUtil;
import nc.vo.arap.verify.AdjuestVO;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.m32.entity.SquareInvDetailVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;
import nc.vo.so.m33.m32.entity.SquareInvViewVO;
import nc.vo.so.pub.calculator.PriceNumMnyCalculatorForVO;

/**
 * 传回冲应收BP
 * 
 * @author zhangcheng
 * 
 */
public class SquareARRushIncomeFor32BP {

  public void square(SquareInvVO[] sqvos) {
    if (sqvos == null || sqvos.length == 0) {
      return;
    }
    AroundProcesser<SquareInvVO> processer =
        new AroundProcesser<SquareInvVO>(BPPlugInPoint.SquareRushARIncome);
    // 增加执行前业务规则
    this.addBeforeRule(processer);
    processer.before(sqvos);

    // 保存回冲明细vo
    SquareInvDetailVO[] dvos = this.saveSquare32Detail(sqvos);

    // 传回冲应收
    this.toEstVerify(dvos, sqvos);

    processer.after(sqvos);
  }

  private void addBeforeRule(AroundProcesser<SquareInvVO> processer) {
    // 应收结算前获取应收组织最新组织版本
    IRule<SquareInvVO> rule = new GetNewARorgVidFor32Rule();
    processer.addBeforeRule(rule);

    rule = new ToARCheckFor32Rule();
    processer.addBeforeRule(rule);
  }

  private SquareInvDetailVO[] saveSquare32Detail(SquareInvVO[] sqvos) {
    // 将传回冲收入结算单VO转化为传回冲收入结算明细VO
    SquareInvDetailVO[] bills =
        SquareInvVOUtils.getInstance().changeSQVOtoSQDVOForARRUSH(sqvos);

    // 用结算明细id设置回冲批次号
    DBTool dao = new DBTool();
    String[] pks = dao.getOIDs(bills.length);
    int i = 0;
    for (SquareInvDetailVO dvo : bills) {
      dvo.setCsalesquaredid(pks[i]);
      dvo.setProcesseid(pks[i]);
      i++;
    }

    // 重新根据回冲数量计算金额（因为数量取反需重算）
    PriceNumMnyCalculatorForVO cal = new PriceNumMnyCalculatorForVO();
    Condition cond = new Condition();
    cond.setUnitPriorType(Condition.MAIN_PRIOR);
    cal.setCondition(cond);
    cal.calculate(bills, SquareInvDetailVO.NNUM);

    AroundProcesser<SquareInvDetailVO> processer =
        new AroundProcesser<SquareInvDetailVO>(
            BPPlugInPoint.SquareRushARIncomeDetail);

    // 回冲应收明细保存BP
    new InsertSquare32DetailBP().insert(sqvos, bills);

    // 回写累计应收结算数据
    IRule<SquareInvDetailVO> rule = new RewriteARRushIncomeFor32Rule();
    processer.addAfterRule(rule);
    processer.after(bills);

    return bills;
  }

  private void toEstVerify(SquareInvDetailVO[] dvos, SquareInvVO[] sqvos) {
    SquareInvViewVO[] sviewvos =
        SquareInvVOUtils.getInstance().changeToSaleSquareViewVO(sqvos);
    Map<String, SquareInvViewVO> mapsview =
        new HashMap<String, SquareInvViewVO>();
    for (SquareInvViewVO vo : sviewvos) {
      mapsview.put(vo.getItem().getCsalesquarebid(), vo);
    }

    // 准备回冲应收接口参数
    AdjuestVO[] vos = new AdjuestVO[dvos.length];
    int i = 0;
    for (SquareInvDetailVO dvo : dvos) {
      vos[i] = new AdjuestVO();
      SquareInvViewVO view = mapsview.get(dvo.getCsalesquarebid());

      // 上游暂估销售出库单结算明细id赋给回冲应收单
      // (之前暂存在传入SquareARRushIncomeFor32BP的SquareInvVO[]中)
      vos[i].setEst_top_itemid(view.getItem().getProcesseid());

      // 回冲结算明细id传给回冲应收单
      vos[i].setTop_itemid(dvo.getCsalesquaredid());

      // 回冲批次号，回冲应收单使用
      // TODO 其实有了回冲结算明细id传给回冲应收单，回冲批次号也没有用了，取消时直接用回冲结算明细id
      vos[i].setClbh(dvo.getProcesseid());

      // 回冲数量(应收应付要求数量符号和暂估数量一样,而销售结算存储符号与暂估数量相反,所以取反)
      vos[i].setQuantity(MathTool.oppose(view.getItem().getNthisnum()));
      i++;
    }

    // 回冲应收传财务
    ArapServicesForSOUtil.estVerify(vos);
  }

}
