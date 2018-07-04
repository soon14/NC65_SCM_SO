package nc.bs.so.m33.biz.m4453.bp.square.ar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.so.m33.biz.m4453.rule.ar.GetNewARorgVidFor4453Rule;
import nc.bs.so.m33.biz.m4453.rule.ar.ToARCheckFor4453Rule;
import nc.bs.so.m33.maintain.m4453.InsertSquareWasDetailBP;
import nc.bs.so.m33.maintain.m4453.rule.detail.RewriteARRushIncomeFor4453Rule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.database.DBTool;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.arap.ArapServicesForSOUtil;
import nc.vo.arap.verify.AdjuestVO;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.m4453.entity.SquareWasDetailVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;
import nc.vo.so.m33.m4453.entity.SquareWasVOUtils;
import nc.vo.so.m33.m4453.entity.SquareWasViewVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * 传回冲应收BP
 * 
 * @author zhangcheng
 * 
 */
public class SquareARRushIncomeFor4453BP {

  public void square(List<SquareWasViewVO> list) {
    if (list == null || list.size() == 0) {
      return;
    }
    SquareWasViewVO[] sqvvos = list.toArray(new SquareWasViewVO[0]);
    this.square(sqvvos);
  }

  public void square(SquareWasViewVO[] sqvvos) {
    if (VOChecker.isEmpty(sqvvos)) {
      return;
    }
    SquareWasVO[] sqvos = SquareWasVOUtils.getInstance().combineBill(sqvvos);
    AroundProcesser<SquareWasVO> processer =
        new AroundProcesser<SquareWasVO>(
            BPPlugInPoint.SquareARRushIncomeFor4453);
    // 增加执行前业务规则
    this.addBeforeRule(processer);
    processer.before(sqvos);

    // 保存回冲明细vo
    SquareWasDetailVO[] dvos = this.saveSquareWasDetail(sqvos);

    // 传回冲应收
    this.toEstVerify(dvos, sqvvos);

    processer.after(sqvos);
  }

  private void addBeforeRule(AroundProcesser<SquareWasVO> processer) {
    // 应收结算前获取应收组织最新组织版本
    IRule<SquareWasVO> rule = new GetNewARorgVidFor4453Rule();
    processer.addBeforeRule(rule);

    rule = new ToARCheckFor4453Rule();
    processer.addBeforeRule(rule);
  }

  private SquareWasDetailVO[] saveSquareWasDetail(SquareWasVO[] sqvos) {
    // 将传回冲收入结算单VO转化为传回冲收入结算明细VO
    SquareWasDetailVO[] bills =
        SquareWasVOUtils.getInstance().changeSQVOtoSQDVOForARRUSH(sqvos);

    // 用结算明细id设置回冲批次号
    DBTool dao = new DBTool();
    String[] pks = dao.getOIDs(bills.length);
    int i = 0;
    for (SquareWasDetailVO dvo : bills) {
      dvo.setCsalesquaredid(pks[i]);
      dvo.setProcesseid(pks[i]);
      i++;
    }

    AroundProcesser<SquareWasDetailVO> processer =
        new AroundProcesser<SquareWasDetailVO>(
            BPPlugInPoint.SquareARRushIncomeFor4453Detail);

    // 回冲应收明细保存BP
    new InsertSquareWasDetailBP().insert(sqvos, bills);

    IRule<SquareWasDetailVO> rule = null;
    // 回写累计应收结算数量
    rule = new RewriteARRushIncomeFor4453Rule();
    processer.addAfterRule(rule);
    processer.after(bills);

    return bills;
  }

  private void toEstVerify(SquareWasDetailVO[] dvos, SquareWasViewVO[] sviewvos) {
    Map<String, SquareWasViewVO> mapsview =
        new HashMap<String, SquareWasViewVO>();
    for (SquareWasViewVO vo : sviewvos) {
      mapsview.put(vo.getItem().getCsalesquarebid(), vo);
    }

    // 准备回冲应收接口参数
    AdjuestVO[] vos = new AdjuestVO[dvos.length];
    int i = 0;
    for (SquareWasDetailVO dvo : dvos) {
      vos[i] = new AdjuestVO();
      SquareWasViewVO view = mapsview.get(dvo.getCsalesquarebid());

      // 上游暂估销售出库单结算明细id赋给回冲应收单
      // (之前暂存在传入SquareARRushIncomeFor4453BP的SquareWasVO[]中)
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
