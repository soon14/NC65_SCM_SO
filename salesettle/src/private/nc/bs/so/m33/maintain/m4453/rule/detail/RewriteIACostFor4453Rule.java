package nc.bs.so.m33.maintain.m4453.rule.detail;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m33.maintain.m4453.UpdateSquare4453FieldsBP;
import nc.bs.so.m33.maintain.m4453.query.QuerySquare4453VOBP;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.pubitf.so.m30.so.m33.Rewrite33Para;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m33.m4453.entity.SquareWasBVO;
import nc.vo.so.m33.m4453.entity.SquareWasDetailVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;
import nc.vo.so.m33.m4453.entity.SquareWasVOUtils;
import nc.vo.so.m33.m4453.entity.SquareWasViewVO;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * @description
 * 成本结算明细保存后回写累计成本结算数量
 * @scene
 * 成本结算、取消结算明细保存后
 * @param
 * 无
 */
public class RewriteIACostFor4453Rule implements IRule<SquareWasDetailVO> {

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

    // 回写销售订单参数<30bid,Rewrite33Para>
    Map<String, Rewrite33Para> m30para = new HashMap<String, Rewrite33Para>();
    Rewrite33Para para30 = null;

    for (SquareWasDetailVO dvo : dvos) {
      String bid = dvo.getCsalesquarebid();
      SquareWasViewVO view = map.get(bid);
      UFDouble oldianum = view.getItem().getNsquareianum();
      UFDouble newianum = dvo.getNsquarenum();

      // 回写销售出库待结算单
      view.getItem().setNsquareianum(MathTool.add(oldianum, newianum));
      if (MathTool.equals(view.getItem().getNnum(), view.getItem()
          .getNsquareianum())) {
        view.getItem().setBsquareiafinish(UFBoolean.TRUE);
      }
      else {
        view.getItem().setBsquareiafinish(UFBoolean.FALSE);
      }

      // 回写订单的参数
      String ordbid = view.getItem().getCfirstbid();
      para30 = m30para.get(ordbid);
      if (null == para30) {
        para30 = new Rewrite33Para(ordbid, newianum);
        m30para.put(ordbid, para30);
      }
      else {
        UFDouble new30num = MathTool.add(para30.getNarnum(), newianum);
        para30.setNarnum(new30num);
      }
    }

    int size = map.values().size();
    SquareWasVO[] sqvos =
        SquareWasVOUtils.getInstance().combineBill(
            map.values().toArray(new SquareWasViewVO[size]));

    try {
      // 回写结算单累计确认应收数量
      new UpdateSquare4453FieldsBP().updateFields(sqvos, null, new String[] {
        SquareWasBVO.NSQUAREIANUM, SquareWasBVO.BSQUAREIAFINISH
      });

      // 回写销售订单累计确认应收数量、金额
      size = m30para.size();
      Rewrite33Para[] paras = m30para.values().toArray(new Rewrite33Para[size]);
      SOSaleOrderServicesUtil.rewrite30IAFor33(paras);

      // 设置最新TS
      // SquareOutVOUtils.getInstance().setNewTS(vos, svos);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

  }
}
