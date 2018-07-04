package nc.bs.so.m33.biz.m4c.rule.toar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.currency.CurrencyInfo;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.calculator.PriceNumMnyCalculatorForVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.SOGlobalExchangeRate;
import nc.vo.so.pub.rule.SOGroupExchangeRate;
import nc.vo.so.pub.util.SOCurrencyUtil;

/**
 * @description
 * 销售出库单应收结算前汇率处理： 1.重新获取集团折本汇率、全局折本汇率、组织折本汇率
 * 2.如果结算组织本位币和应收/应付组织本位币一致，从供应链传（因供应链发票可以编辑汇率，汇率从上游携带）；
 * 否则，基于原币重新折算（我们认为所有的委托应收/应付，结算组织和应收/应付组织的本位币都是一致的。 不一致不作为测试和设计主要场景）
 * 3.并重新计算本币金额
 * @scene
 * 销售出库单应收结算前汇率处理
 * @param
 * 无
 * @author zhangcheng
 * 
 */
public class FillNewChangeRateFor4CRule implements IRule<SquareOutVO> {

  @Override
  public void process(SquareOutVO[] vos) {

    // 组织折本汇率处理
    List<SquareOutVO> listexchg = new ArrayList<SquareOutVO>();
    Set<String> setchgid = new HashSet<String>();
    this.orgChangeRateProcess(vos, listexchg, setchgid);

    // 集团折本汇率、全局折本汇率处理
    List<SquareOutVO> listgroupexchg = new ArrayList<SquareOutVO>();
    List<SquareOutVO> listglobalexchg = new ArrayList<SquareOutVO>();
    this.gloupaAllChangeRateProcess(vos, setchgid, listgroupexchg,
        listglobalexchg);

    // 折本汇率变化行
    if (listexchg.size() > 0) {
      SquareOutVO[] exchgvos = new SquareOutVO[listexchg.size()];
      listexchg.toArray(exchgvos);
      this.calLocalMny(exchgvos, SquareOutBVO.NEXCHANGERATE);
    }
    // 集团折本汇率变化行
    if (listgroupexchg.size() > 0) {
      SquareOutVO[] exchgvos = new SquareOutVO[listgroupexchg.size()];
      listgroupexchg.toArray(exchgvos);
      this.calLocalMny(exchgvos, SquareOutBVO.NGROUPEXCHGRATE);
    }
    // 全局折本汇率变化行
    if (listgroupexchg.size() > 0) {
      SquareOutVO[] exchgvos = new SquareOutVO[listglobalexchg.size()];
      listglobalexchg.toArray(exchgvos);
      this.calLocalMny(exchgvos, SquareOutBVO.NGLOBALEXCHGRATE);
    }

  }

  /**
   * 重新计算相关本币金额
   * 
   * @param vos
   */
  private void calLocalMny(SquareOutVO[] vos, String chgkey) {
    Condition cond = new Condition();
    PriceNumMnyCalculatorForVO pc = new PriceNumMnyCalculatorForVO();
    pc.setCondition(cond);
    pc.calculate(vos, chgkey);
  }

  /**
   * 取最新汇率
   * 
   * @param vos
   * @param orgRes
   */
  private void gloupaAllChangeRateProcess(SquareOutVO[] vos,
      Set<String> setchgid, List<SquareOutVO> listgroupexchg,
      List<SquareOutVO> listglobalexchg) {

    // 缓存旧的全局汇率、集团汇率
    for (SquareOutVO svo : vos) {
      IKeyValue keyValue = new VOKeyValue<SquareOutVO>(svo);
      int ilen = keyValue.getBodyCount();
      int[] rows = new int[ilen];
      UFDouble[] oldgroupchgrates = new UFDouble[ilen];
      UFDouble[] oldglobalchgrates = new UFDouble[ilen];
      for (int i = 0; i < ilen; i++) {
        rows[i] = i;
        oldgroupchgrates[i] =
            keyValue.getBodyUFDoubleValue(i, SOItemKey.NGROUPEXCHGRATE);
        oldglobalchgrates[i] =
            keyValue.getBodyUFDoubleValue(i, SOItemKey.NGLOBALEXCHGRATE);
      }

      // 计算新的集团折本汇率
      SOGroupExchangeRate groupchgrate = new SOGroupExchangeRate(keyValue);
      groupchgrate.calcGroupExchgRateAtBodyByBusidate(rows);

      // 计算新的全局折本汇率
      SOGlobalExchangeRate globalerate = new SOGlobalExchangeRate(keyValue);
      globalerate.calcGlobalExchgRateAtBodyByBusidate(rows);

      List<SquareOutBVO> listgroupchgbvo = new ArrayList<SquareOutBVO>();
      List<SquareOutBVO> listglobalchgbvo = new ArrayList<SquareOutBVO>();
      int i = 0;
      for (SquareOutBVO bvo : svo.getChildrenVO()) {
        if (setchgid.contains(bvo.getCsalesquarebid())) {
          continue;
        }
        if (!MathTool.equals(oldgroupchgrates[i], bvo.getNgroupexchgrate())) {
          listgroupchgbvo.add(bvo);
        }
        if (!MathTool.equals(oldglobalchgrates[i], bvo.getNglobalexchgrate())) {
          listglobalchgbvo.add(bvo);
        }
        i++;
      }
      // 集团折本汇率变化行重新组成新聚合VO
      if (listgroupchgbvo.size() > 0) {
        SquareOutVO squVO = new SquareOutVO();
        squVO.setParentVO(svo.getParentVO());
        SquareOutBVO[] bvos = new SquareOutBVO[listgroupchgbvo.size()];
        listgroupchgbvo.toArray(bvos);
        squVO.setChildrenVO(bvos);
        listgroupexchg.add(squVO);
      }
      // 全局折本汇率变化行重新组成新聚合VO
      if (listglobalchgbvo.size() > 0) {
        SquareOutVO squVO = new SquareOutVO();
        squVO.setParentVO(svo.getParentVO());
        SquareOutBVO[] bvos = new SquareOutBVO[listglobalchgbvo.size()];
        listglobalchgbvo.toArray(bvos);
        squVO.setChildrenVO(bvos);
        listglobalexchg.add(squVO);
      }
    }

  }

  /**
   * 组织折本汇率处理
   * 
   * @param vos
   */
  private void orgChangeRateProcess(SquareOutVO[] vos,
      List<SquareOutVO> listexchg, Set<String> setchgid) {
    // 应收组织本位币<应收组织,应收组织本位币>
    Map<String, String> marorg_cy = new HashMap<String, String>();
    for (SquareOutVO svo : vos) {
      for (SquareOutBVO bvo : svo.getChildrenVO()) {
        String arorg = bvo.getCarorgid();
        String arcy = CurrencyInfo.getLocalCurrtypeByOrgID(arorg);
        marorg_cy.put(arorg, arcy);
      }
    }

    UFDate sysdate = AppContext.getInstance().getBusiDate();

    // 重新设置组织折本汇率
    for (SquareOutVO svo : vos) {
      List<SquareOutBVO> listtchgbvo = new ArrayList<SquareOutBVO>();
      for (SquareOutBVO bvo : svo.getChildrenVO()) {
        String src_currency_pk = bvo.getCorigcurrencyid();
        String sq_currency_pk = bvo.getCcurrencyid();
        String arorg = bvo.getCarorgid();
        String ar_currency_pk = marorg_cy.get(arorg);
        UFDouble oldchgrate = bvo.getNexchangerate();

        // 结算组织本位币和应收组织本位币一致
        if (PubAppTool.isEqual(sq_currency_pk, ar_currency_pk)) {
          String pk_org = bvo.getPk_org();
          UFDouble nexchangerate =
              this.getInCurrencyRateByOrg(pk_org, src_currency_pk,
                  sq_currency_pk, sysdate);
          bvo.setNexchangerate(nexchangerate);
        }
        // 结算组织本位币和应收组织本位币不一致
        else {
          UFDouble nexchangerate =
              this.getInCurrencyRateByOrg(arorg, src_currency_pk,
                  ar_currency_pk, sysdate);
          bvo.setNexchangerate(nexchangerate);
        }
        if (!MathTool.equals(oldchgrate, bvo.getNexchangerate())) {
          setchgid.add(bvo.getCsalesquarebid());
          listtchgbvo.add(bvo);
        }
      }
      // 重新组成新vo
      if (listtchgbvo.size() > 0) {
        SquareOutVO squVO = new SquareOutVO();
        squVO.setParentVO(svo.getParentVO());
        SquareOutBVO[] bvos = new SquareOutBVO[listtchgbvo.size()];
        listtchgbvo.toArray(bvos);
        squVO.setChildrenVO(bvos);
        listexchg.add(squVO);
      }
    }
  }

  private UFDouble getInCurrencyRateByOrg(String pk_org,
      String src_currency_pk, String dest_currency_pk, UFDate billdate) {
    UFDouble nexchangerate =
        SOCurrencyUtil.getInCurrencyRateByOrg(pk_org, src_currency_pk,
            dest_currency_pk, billdate);
    if (UFDouble.ZERO_DBL.equals(nexchangerate)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006010_0", "04006010-0151")/* 取最新汇率时，
                                                                   * 汇率没有定义或为0
                                                                   * ，请检查！ */);
    }
    return nexchangerate;
  }

}
