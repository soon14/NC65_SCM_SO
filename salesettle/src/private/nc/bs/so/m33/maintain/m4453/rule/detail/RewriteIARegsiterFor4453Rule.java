package nc.bs.so.m33.maintain.m4453.rule.detail;

import java.util.Map;

import nc.bs.so.m33.maintain.m4453.UpdateSquare4453FieldsBP;
import nc.bs.so.m33.maintain.m4453.query.QuerySquare4453VOBP;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.m4453.entity.SquareWasBVO;
import nc.vo.so.m33.m4453.entity.SquareWasDetailVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;
import nc.vo.so.m33.m4453.entity.SquareWasVOUtils;
import nc.vo.so.m33.m4453.entity.SquareWasViewVO;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * @description
 * 发出商品明细保存后回写累计应收结算数量
 * @scene
 * 发出商品明细、取消发出商品明细保存后
 * @param
 * 无
 */
public class RewriteIARegsiterFor4453Rule implements IRule<SquareWasDetailVO> {

  /**
   * 父类方法重写
   * 因为结算的时候第一步就是查询结算单，所以此时回写的时候不需要重新查询
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#setCustRelaDefValue(E[])
   */
  @Override
  public void process(SquareWasDetailVO[] dvos) {
    String[] sqbids =
      SoVoTools.getVOsOnlyValues(dvos, SquareWasDetailVO.CSALESQUAREBID);
  Map<String, SquareWasViewVO> map =
      new QuerySquare4453VOBP().queryMapSquareWasViewVOByBID(sqbids);

  for (SquareWasDetailVO dvo : dvos) {
    String bid = dvo.getCsalesquarebid();
    SquareWasViewVO view = map.get(bid);
    SquareWasBVO bvo = view.getItem();
    UFDouble oldvalue = bvo.getNsquareregnum();
    UFDouble newvalue = dvo.getNsquarenum();
    bvo.setNsquareregnum(MathTool.add(oldvalue, newvalue));
    if (bvo.getNnum().compareTo(bvo.getNsquareregnum()) == 0) {
      bvo.setBsquareiafinish(UFBoolean.TRUE);
    }
    else {
      bvo.setBsquareiafinish(UFBoolean.FALSE);
    }
  }

  int size = map.values().size();
  SquareWasVO[] sqvos =
      SquareWasVOUtils.getInstance().combineBill(
          map.values().toArray(new SquareWasViewVO[size]));

  // 回写结算单累计成本结算数量
  new UpdateSquare4453FieldsBP().updateFields(sqvos, null, new String[] {
    SquareWasBVO.NSQUAREREGNUM, SquareWasBVO.BSQUAREIAFINISH
  });

}

}
