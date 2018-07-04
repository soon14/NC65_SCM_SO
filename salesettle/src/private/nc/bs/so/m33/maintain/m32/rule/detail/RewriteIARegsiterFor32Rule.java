package nc.bs.so.m33.maintain.m32.rule.detail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.so.m33.maintain.m32.UpdateSquare32FieldsBP;
import nc.bs.so.m33.maintain.m32.query.QuerySquare32VOBP;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.pubitf.so.m30.so.m33.Rewrite33Para;
import nc.pubitf.so.m32.so.m33.RewritePara32For33;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvDetailVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;
import nc.vo.so.m33.m32.entity.SquareInvViewVO;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * @description
 * 发出商品结算明细保存后回写累计成本结算数据
 * @scene
 * 发出商品成本结算明细保存后
 * @param
 * 无
 * @since 6.0
 * @version 2011-6-14 下午08:05:52
 * @author zhangcheng
 */
public class RewriteIARegsiterFor32Rule implements IRule<SquareInvDetailVO> {

  /**
   * 父类方法重写
   * 因为结算的时候第一步就是查询结算单，所以此时回写的时候不需要重新查询
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#setCustRelaDefValue(E[])
   */
  @Override
  public void process(SquareInvDetailVO[] dvos) {
    String[] sqbids =
        SoVoTools.getVOsOnlyValues(dvos, SquareInvDetailVO.CSALESQUAREBID);
    Map<String, SquareInvViewVO> map =
        new QuerySquare32VOBP().queryMapSquareInvViewVOByBID(sqbids);

    // 回写销售订单参数<30bid,Rewrite33Para>
    Map<String, Rewrite33Para> m30para = new HashMap<String, Rewrite33Para>();
    Rewrite33Para para30 = null;

    // 回写销售发票参数,销售发票和销售发票结算单一一对应
    List<RewritePara32For33> list32 = new ArrayList<RewritePara32For33>();
    RewritePara32For33 para32 = null;

    for (SquareInvDetailVO dvo : dvos) {
      String bid = dvo.getCsalesquarebid();
      SquareInvViewVO view = map.get(bid);
      SquareInvBVO bvo = view.getItem();
      UFDouble oldregnum = bvo.getNsquareregnum();
      UFDouble newregnum = dvo.getNsquarenum();

      // 回写销售发票待结算单
      bvo.setNsquareregnum(MathTool.add(oldregnum, newregnum));
      if (MathTool.equals(bvo.getNnum(), bvo.getNsquareregnum())) {
        bvo.setBsquareiafinish(UFBoolean.TRUE);
      }
      else {
        bvo.setBsquareiafinish(UFBoolean.FALSE);
      }

      // 回写销售发票
      String invbid = dvo.getCsquarebillbid();
      para32 = new RewritePara32For33(invbid, null, null, newregnum);
      list32.add(para32);

      // 回写订单,销售发票可以在编辑的时候新增无来源的劳务折扣行要过滤掉
      String ordbid = bvo.getCfirstbid();
      if (!PubAppTool.isNull(ordbid)) {
        para30 = m30para.get(ordbid);
        if (null == para30) {
          para30 = new Rewrite33Para(ordbid, newregnum);
          m30para.put(ordbid, para30);
        }
        else {
          UFDouble new30num = MathTool.add(para30.getNarnum(), newregnum);
          para30.setNarnum(new30num);
        }
      }
    }

    int size = map.values().size();
    SquareInvVO[] sqvos =
        SquareInvVOUtils.getInstance().combineBill(
            map.values().toArray(new SquareInvViewVO[size]));

    // 回写销售发票待结算单累计成本结算数量
    new UpdateSquare32FieldsBP().updateFields(sqvos, null, new String[] {
      SquareInvBVO.NSQUAREREGNUM, SquareInvBVO.BSQUAREIAFINISH
    });

    // 回写销售发票累计成本结算数量
    try {
      SOSaleOrderServicesUtil.reWriteBalNumMny(list32
          .toArray(new RewritePara32For33[list32.size()]));

      // 回写销售订单累计成本结算数量
      size = m30para.size();
      Rewrite33Para[] paras = m30para.values().toArray(new Rewrite33Para[size]);
      SOSaleOrderServicesUtil.rewrite30IAFor33(paras);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
