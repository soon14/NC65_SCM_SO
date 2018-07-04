package nc.bs.so.m33.biz.m32.rule.toar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.currency.CurrencyInfo;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m33.m32.entity.SquareInvBVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.pub.calculator.PriceNumMnyCalculatorForVO;
import nc.vo.so.pub.util.SOCurrencyUtil;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售发票应收结算前汇率处理 如果结算组织本位币和应收/应付组织本位币一致，从供应链传（因供应链发票可以编辑汇率，汇率从上游携带）；
 * 否则，基于原币重新折算（我们认为所有的委托应收/应付，结算组织和应收/应付组织的本位币都是一致的。 不一致不作为测试和设计主要场景）
 * @scene
 * 销售发票应收结算前
 * @param
 * 无
 * @author zhangcheng
 * @time 2010-5-28 上午10:15:43
 */
public class FillNewChangeRateFor32Rule implements IRule<SquareInvVO> {

  @Override
  public void process(SquareInvVO[] vos) {

    // 组织折本汇率处理
    SquareInvBVO[] bvos = this.orgChangeRateProcess(vos);

    // 重新计算相关本币金额
    if (!VOChecker.isEmpty(bvos)) {
      this.calLocalMny(bvos);
    }
  }

  /**
   * 重新计算相关本币金额
   * 
   * @param vos
   */
  private void calLocalMny(SquareInvBVO[] bvos) {
    new PriceNumMnyCalculatorForVO().calculateLocal(bvos);
  }

  /**
   * 组织折本汇率处理
   * 
   * @param vos
   */
  private SquareInvBVO[] orgChangeRateProcess(SquareInvVO[] vos) {
    // 应收组织本位币<应收组织,应收组织本位币>
    Map<String, String> marorg_cy = new HashMap<String, String>();
    for (SquareInvVO svo : vos) {
      for (SquareInvBVO bvo : svo.getChildrenVO()) {
        String arorg = bvo.getCarorgid();
        String arcy = CurrencyInfo.getLocalCurrtypeByOrgID(arorg);
        marorg_cy.put(arorg, arcy);
      }
    }

    UFDate sysdate = BSContext.getInstance().getDate();
    List<SquareInvBVO> list = new ArrayList<SquareInvBVO>();
    // 重新设置组织折本汇率
    for (SquareInvVO svo : vos) {
      for (SquareInvBVO bvo : svo.getChildrenVO()) {
        String src_currency_pk = bvo.getCorigcurrencyid();
        String sq_currency_pk = bvo.getCcurrencyid();
        String arorg = bvo.getCarorgid();
        String ar_currency_pk = marorg_cy.get(arorg);
        // 结算组织本位币和应收组织本位币不一致
        if (!PubAppTool.isEqual(sq_currency_pk, ar_currency_pk)) {
          // String pk_group = svo.getParentVO().getPk_group();
          UFDouble nexchangerate =
              SOCurrencyUtil.getInCurrencyRateByOrg(arorg, src_currency_pk,
                  ar_currency_pk, sysdate);
          bvo.setNexchangerate(nexchangerate);
          list.add(bvo);
        }
      }
    }

    SquareInvBVO[] bvos = null;
    if (list.size() > 0) {
      bvos = list.toArray(new SquareInvBVO[list.size()]);
    }
    return bvos;
  }
}
