package nc.pubimpl.so.m30.so.m4331.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.so.m30.so.m4331.Rewrite4331Para;
import nc.vo.price.pplimitexe.SOUpdatePPLimitExePara;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.OPCBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m30.enumeration.Fretexchange;
import nc.vo.so.m30.util.PromoteLimitUtil;
import nc.vo.so.m30.util.RewriteProPriceUtil;
import nc.vo.so.m30.util.SaleOrderNumScaleUtil;

/**
 * 
 * @description
 * 发货单回写销售订单累计发货数量后
 * @scene
 * 发货单回写销售订单累计发货数量后，更新销售价格限量促销执行量
 * @param
 * 无
 *
 * @since 6.5
 * @version 2014-03-13 16:22:05
 * @author zhangyfr
 */
public class RewritePriceNumRule implements IRule<SaleOrderViewVO> {

  @Override
  public void process(SaleOrderViewVO[] vos) {

    if (!SysInitGroupQuery.isPRICEEnabled()) {
      return;
    }

    // 1.初始回写参数
    Map<String, Rewrite4331Para> rewriteParas =
        (Map<String, Rewrite4331Para>) BSContext.getInstance().getSession(
            Rewrite4331Para.class.getName());

    List<SOUpdatePPLimitExePara> plimitParas =
        new ArrayList<SOUpdatePPLimitExePara>();
    // 用于暂存<来源表体行ID，回写参数>，方便B2B预定单查询接口返回值给回写参数赋值
    Map<String, SOUpdatePPLimitExePara> map =
        new HashMap<String, SOUpdatePPLimitExePara>();
    for (SaleOrderViewVO vo : vos) {
      SaleOrderHVO head = vo.getHead();
      SaleOrderBVO body = vo.getBody();

      if (!body.getBboutendflag().booleanValue()
          && body.getBbsendendflag().booleanValue()) {
        UFDouble thischangenum =
            rewriteParas.get(body.getCsaleorderbid()).getNchangenum();
        // 数量改小（改后累计出库数量小于订单主数量），若订单手动发货关闭，不会自动发货打开，自动关闭才会发货打开（在状态机里面测试）
        // zhangby5 红字变化量大于零不用回写；蓝字小于零不用回写
        boolean isAutoSendClose = this.getIsAutoSendClose(body);

        if (isAutoSendClose
            && !Fretexchange.WITHDRAW.equalsValue(body.getFretexchange())
            && MathTool.compareTo(thischangenum, UFDouble.ZERO_DBL) < 0) {
          continue;
        }
        if (isAutoSendClose
            && Fretexchange.WITHDRAW.equalsValue(body.getFretexchange())
            && MathTool.compareTo(thischangenum, UFDouble.ZERO_DBL) > 0) {
          continue;
        }
        int scale = SaleOrderNumScaleUtil.getNumPower(body);
        SOUpdatePPLimitExePara plimitPara = new SOUpdatePPLimitExePara();
        plimitPara.setBilltypecode(SOBillType.Order.getCode());
        plimitPara.setCcustomerid(head.getCcustomerid());
        plimitPara.setCpromoetpriceid(body.getCpromotpriceid());
        plimitPara.setRowID(body.getCsaleorderbid());
        plimitPara.setRowNo(body.getCrowno());
        String qtunitrate = body.getVqtunitrate();
        UFDouble ntotalsendnum =
            MathTool.add(body.getNtotalsendnum(), thischangenum);
        UFDouble nqtunittotalsendnum =
            HslParseUtil.hslDivUFDouble(qtunitrate, ntotalsendnum);
        nqtunittotalsendnum =
            nqtunittotalsendnum.setScale(scale, UFDouble.ROUND_HALF_UP);
        plimitPara.setNnum(nqtunittotalsendnum);
        // 来源单据类型为电子订单'ECC1'
        if (OPCBillType.OPCORDER.getCode().equals(body.getVsrctype())) {
          plimitPara.setSrcbilltypecode(body.getVsrctype());
          String srcbic = body.getCsrcbid();
          map.put(srcbic, plimitPara);
        }
        else {
          plimitParas.add(plimitPara);
        }
      }
    }

    if (map.size() > 0) {
      RewriteProPriceUtil util = new RewriteProPriceUtil();
      plimitParas = util.setSrcParas(map);
    }
    if (plimitParas.size() > 0) {
      PromoteLimitUtil.updateExecutedNumByOpenOrClose(plimitParas
          .toArray(new SOUpdatePPLimitExePara[plimitParas.size()]));
    }

  }

  private boolean getIsAutoSendClose(SaleOrderBVO bvo) {
    if (null != bvo.getBbsendendflag()
        && bvo.getBbsendendflag().booleanValue()
        && MathTool
            .compareTo(bvo.getNnum().abs(), bvo.getNtotalsendnum().abs()) > 0) {
      // 本身处于手工关闭状态
      return false;
    }
    return true;
  }

}
