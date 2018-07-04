package nc.bs.so.m33.maintain.m4c.rule.detail;

import java.util.Map;

import nc.bs.so.m33.maintain.m4c.UpdateSquare4CFieldsBP;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * @description
 * 销售出库待结算单发出商品贷方回写累计发出商品数据
 * @scene
 * 取消销售出库待结算单发出商品、传发出商品贷方、销售出库单传发出商品
 * @param 
 * 无
 */
public class RewriteIARegsiterFor4CRule implements IRule<SquareOutDetailVO> {

  /**
   * 父类方法重写
   * 因为结算的时候第一步就是查询结算单，所以此时回写的时候不需要重新查询
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#setCustRelaDefValue(E[])
   */
  @Override
  public void process(SquareOutDetailVO[] dvos) {
    String[] sqbids =
        SoVoTools.getVOsOnlyValues(dvos, SquareOutDetailVO.CSALESQUAREBID);
    Map<String, SquareOutViewVO> map =
        new QuerySquare4CVOBP().queryMapSquareOutViewVOByBID(sqbids);

    for (SquareOutDetailVO dvo : dvos) {
      String bid = dvo.getCsalesquarebid();
      SquareOutViewVO view = map.get(bid);
      SquareOutBVO bvo = view.getItem();
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
    SquareOutVO[] sqvos =
        SquareOutVOUtils.getInstance().combineBill(
            map.values().toArray(new SquareOutViewVO[size]));

    // 回写结算单累计成本结算数量
    new UpdateSquare4CFieldsBP().updateFields(sqvos, null, new String[] {
      SquareOutBVO.NSQUAREREGNUM, SquareOutBVO.BSQUAREIAFINISH
    });

  }

}
