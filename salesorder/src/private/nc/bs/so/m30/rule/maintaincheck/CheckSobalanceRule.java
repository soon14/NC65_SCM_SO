package nc.bs.so.m30.rule.maintaincheck;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m30.sobalance.ISOBalanceQuery;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.enumeration.SoBalanceType;
import nc.vo.so.m30.sobalance.util.SoBalanceUtil;

/**
 * 
 * @description
 * 收款核销关系数据检查规则
 * @scene
 * 1、对应的订单只有一个应收组织
 * 2、订单核销关系与订单数据的一致性
 * @param
 * 无
 *
 * @since 6.5
 * @version 2015-10-19 下午3:04:11
 * @author zhangby5
 */
public class CheckSobalanceRule implements IRule<SaleOrderVO> {
  public CheckSobalanceRule() {
    //
  }

  @Override
  public void process(SaleOrderVO[] vos) {
    for (SaleOrderVO saleordervo : vos) {
      ISOBalanceQuery queryservice =
          NCLocator.getInstance().lookup(ISOBalanceQuery.class);
      SoBalanceVO[] sobalancevos = null;
      try {
        sobalancevos =
            queryservice.querySoBalanceVOBySaleOrderIDs(new String[] {
              saleordervo.getParentVO().getPrimaryKey()
            });
      }
      catch (BusinessException e) {

        ExceptionUtils.wrappBusinessException(e.getMessage(), e);
      }
      if (sobalancevos != null && sobalancevos.length > 0) {
        this.checkArorg(sobalancevos[0], saleordervo);
        this.checkSoBalanceAndSaleorderConsistent(sobalancevos[0], saleordervo);
        this.checkNorigordbalmny(sobalancevos[0], saleordervo);
      }
    }
  }

  private void checkArorg(SoBalanceVO bill, SaleOrderVO saleordervo) {
    SoBalanceHVO sobalancehvo = bill.getParentVO();
    String carorgid = sobalancehvo.getCarorgid();
    SoBalanceBVO[] sobalancebvos = bill.getChildrenVO();
    boolean existOrderbal = false;
    for (SoBalanceBVO bodyvo : sobalancebvos) {
      int fibaltype = bodyvo.getFibaltype().intValue();
      if (SoBalanceType.SOBALANCE_ORDERBAL.getIntValue() == fibaltype) {
        existOrderbal = true;
      }
    }

    if (!existOrderbal) {
      return;
    }
    SaleOrderBVO[] bodyvos = saleordervo.getChildrenVO();
    String carorg = null;
    for (SaleOrderBVO bodyvo : bodyvos) {
      int vostatus = bodyvo.getStatus();
      // 删除的行不补充冗余信息
      if (vostatus != VOStatus.DELETED) {
        if (carorg == null) {
          carorg = bodyvo.getCarorgid();
        }
        else if (!(carorg.equals(bodyvo.getCarorgid()))) {

          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0107")/*@res "销售订单表体存在多个应收组织，不允许订单收款。"*/);
        }
      }
    }
    if ((carorg != null) && !(carorgid.equals(carorg))) {

      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0108")/*@res "订单收款核销关系与订单数据不一致，请刷新数据重做业务。"*/);
    }
  }

  private void checkNorigordbalmny(SoBalanceVO bill, SaleOrderVO saleordervo) {
    UFDouble ntotalorigtaxmny = saleordervo.getParentVO().getNtotalorigmny();
    UFDouble totalbodymny =
        SoBalanceUtil.getInstance().caculateSoBalanceTotalBodymny(bill,
            SoBalanceBVO.NORIGORDBALMNY);
    boolean issamedirect = false;

    if (((MathTool.compareTo(ntotalorigtaxmny, UFDouble.ZERO_DBL) <= 0) && (MathTool
        .compareTo(totalbodymny, UFDouble.ZERO_DBL) <= 0))
        || ((MathTool.compareTo(ntotalorigtaxmny, UFDouble.ZERO_DBL) >= 0) && (MathTool
            .compareTo(totalbodymny, UFDouble.ZERO_DBL) >= 0))) {
      issamedirect = true;
    }
    if (!issamedirect) {

      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0109")/*@res "订单收款关系金额合计与订单价税合计方向必须一致。"*/);
    }

    if (MathTool.absCompareTo(ntotalorigtaxmny, totalbodymny) < 0) {

      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0110")/*@res "订单收款关系金额合计不允许大于订单价税合计。"*/);
    }
  }

  private void checkSoBalanceAndSaleorderConsistent(SoBalanceVO bill,
      SaleOrderVO saleordervo) {
    SoBalanceHVO sobalancehvo = bill.getParentVO();
    String cinvoicecustid = sobalancehvo.getCinvoicecustid();
    String corigcurrencyid = sobalancehvo.getCorigcurrencyid();

    if (!(corigcurrencyid
        .equals(saleordervo.getParentVO().getCorigcurrencyid()))) {

      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0108")/*@res "订单收款核销关系与订单数据不一致，请刷新数据重做业务。"*/);
    }
    if (!(cinvoicecustid.equals(saleordervo.getParentVO().getCinvoicecustid()))) {

      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0108")/*@res "订单收款核销关系与订单数据不一致，请刷新数据重做业务。"*/);
    }
  }
}