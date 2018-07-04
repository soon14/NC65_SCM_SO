package nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.so.m33.maintain.m4c.DeleteSquareOutDetailBP;
import nc.bs.so.m33.maintain.m4c.rule.detail.RewriteETIncomeFor4CRule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.arap.ArapServicesForSOUtil;
import nc.itf.so.m33.biz.canclesquare.ICancelSquareAction;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;

public class CancelETIncomeFor4CBP implements
ICancelSquareAction<SquareOutDetailVO> {

  @Override
  public void cancelSquare(List<SquareOutDetailVO> list) {

    if (list == null || list.size() == 0) {
      return;
    }

    SquareOutDetailVO[] vos = list.toArray(new SquareOutDetailVO[0]);

    AroundProcesser<SquareOutVO> processer =
        new AroundProcesser<SquareOutVO>(BPPlugInPoint.CancelETIncomeFor4CBP);

    SquareOutVO[] svos =
        (SquareOutVO[]) BSContext.getInstance().getSession(
            SquareOutVO.class.getName());

    // <子表行id,应收组织>
    Map<String, String> sq_map = new HashMap<String, String>();
    for (SquareOutVO sqvo : svos) {
      for (SquareOutBVO bvo : sqvo.getChildrenVO()) {
        sq_map.put(bvo.getPrimaryKey(), bvo.getCarorgid());
      }
    }

    // 准备接口参数<明细表id,应收组织>
    Map<String, String> map = new HashMap<String, String>();
    for (SquareOutDetailVO svo : vos) {
      map.put(svo.getPrimaryKey(), sq_map.get(svo.getCsalesquarebid()));
    }

    processer.before(svos);

    // 取消传财务暂估应收
    ArapServicesForSOUtil.deleteEffectBill(map);

    this.cancelDetail(vos);

    processer.after(svos);

  }

  private void cancelDetail(SquareOutDetailVO[] bills) {
    AroundProcesser<SquareOutDetailVO> processer =
        new AroundProcesser<SquareOutDetailVO>(
            BPPlugInPoint.CancelETIncomeFor4CDetailBP);
    IRule<SquareOutDetailVO> rule = null;

    // 取消结算明细
    new DeleteSquareOutDetailBP().delete(bills);

    // 回写累计应收结算数量
    rule = new RewriteETIncomeFor4CRule();
    processer.addAfterRule(rule);
    processer.after(bills);
  }

}
