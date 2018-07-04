package nc.bs.so.m30.rule.sobalance;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.enumeration.SoBalanceType;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * @description
 * 收款核销关系数据检查规则
 * 订单关闭后：
 * 1、不允许再增加订单核销关系 2、不允许编辑订单核销关系 3、可以删除订单核销关系
 * 红字订单是否可以订单收款受交易类型的参数控制：
 * @scene
 * 销售订单保存收款核销关系前
 * @param
 * 无
 */
public class CheckBalmnyUpdatableRule implements IRule<SoBalanceVO> {
  public CheckBalmnyUpdatableRule() {
    //
  }

  @Override
  public void process(SoBalanceVO[] vos) {
    for (SoBalanceVO bill : vos) {
      SoBalanceHVO headvo = bill.getParentVO();
      BillQuery<SaleOrderVO> query =
          new BillQuery<SaleOrderVO>(SaleOrderVO.class);
      SaleOrderVO[] saleordervos = query.query(new String[] {
        headvo.getCsaleorderid()
      });
      if ((saleordervos == null) || (saleordervos.length == 0)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0117")/*@res "销售订单已被删除，请刷新数据重做业务。"*/);
      }
      else {
        SaleOrderVO saleordervo = saleordervos[0];

        this.checkSaleOrderClose(bill, saleordervo);
        this.checkRedOrder(bill, saleordervo);
      }
    }
  }

  private void checkRedOrder(SoBalanceVO bill, SaleOrderVO saleordervo) {
    SaleOrderHVO header = saleordervo.getParentVO();
    M30TranTypeVO trantype = null;
    try {
      IM30TranTypeService service =
          NCLocator.getInstance().lookup(IM30TranTypeService.class);
      trantype = service.queryTranTypeVO(header.getCtrantypeid());
    }
    catch (Exception e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
    if (trantype == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0102")/*@res "查询订单类型出错。"*/);
      return;
    }
    UFBoolean bredorderpay = trantype.getBredorderpay();
    if ((bredorderpay != null) && (bredorderpay.booleanValue())) {
      return;
    }
    UFDouble ntotalorigmny = header.getNtotalorigmny();
    if (MathTool.compareTo(ntotalorigmny, UFDouble.ZERO_DBL) >= 0) {
      return;
    }
    SoBalanceBVO[] sobalancebvos = bill.getChildrenVO();
    for (SoBalanceBVO bodyvo : sobalancebvos) {
      int fibaltype = bodyvo.getFibaltype().intValue();
      if (SoBalanceType.SOBALANCE_ORDERBAL.getIntValue() == fibaltype) {
        int vostatus = bodyvo.getStatus();
        //
        if (vostatus == VOStatus.NEW) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0118")/*@res "销售订单不支持红字收款，不允许建立订单收款核销关系。"*/);
        }
        else if (vostatus == VOStatus.UPDATED) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0119")/*@res "销售订单不支持红字收款，不允许修改订单收款核销关系。"*/);
        }
      }
    }

  }

  private void checkSaleOrderClose(SoBalanceVO bill, SaleOrderVO saleordervo) {
    Integer fstatusflag = saleordervo.getParentVO().getFstatusflag();
    if (fstatusflag.intValue() == BillStatus.CLOSED.getIntValue()) {
      SoBalanceBVO[] sobalancebvos = bill.getChildrenVO();
      for (SoBalanceBVO bodyvo : sobalancebvos) {
        int fibaltype = bodyvo.getFibaltype().intValue();
        if (SoBalanceType.SOBALANCE_ORDERBAL.getIntValue() != fibaltype) {
          continue;
        }
        int vostatus = bodyvo.getStatus();
        //
        if (vostatus == VOStatus.NEW) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0120")/*@res "销售订单已经关闭，不允许建立订单收款核销关系。"*/);
        }
        else if (vostatus == VOStatus.UPDATED) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0121")/*@res "销售订单已经关闭，不允许修改订单收款核销关系。"*/);
        }
      }
    }

  }

}