package nc.bs.so.m30.rule.sobalance;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.enumeration.SoBalanceType;
import nc.vo.so.m30.sobalance.util.GatherbillUtil;

/**
 * @description
 * 收款核销关系数据检查规则
 * 1、对应的订单只有一个应收组织
 * 2、订单核销关系与订单数据的一致性
 * @scene
 * 销售订单保存收款核销关系前
 * @param
 * gatherUtil 收款核销关系数据检查工具类
 */
public class CheckSaleOrderRule implements IRule<SoBalanceVO> {
  private GatherbillUtil gatherUtil;

  public CheckSaleOrderRule() {
    //
  }

  @Override
  public void process(SoBalanceVO[] vos) {
    // 数据准备——订单查询
    Set<String> csaleorderidSet = new HashSet<String>();
    for (SoBalanceVO vo : vos) {
      csaleorderidSet.add(vo.getParentVO().getCsaleorderid());
    }
    Map<String, SaleOrderVO> soBillMap = new HashMap<String, SaleOrderVO>();
    if (csaleorderidSet.size() > 0) {
      BillQuery<SaleOrderVO> query =
          new BillQuery<SaleOrderVO>(SaleOrderVO.class);
      SaleOrderVO[] soBills =
          query
              .query(csaleorderidSet.toArray(new String[csaleorderidSet.size()]));
      if (soBills != null && soBills.length > 0) {
        for (SaleOrderVO soBill : soBills) {
          soBillMap.put(soBill.getPrimaryKey(), soBill);
        }
      }
    }
    // 检查
    for (SoBalanceVO sbBill : vos) {
      String csaleorderid = sbBill.getParentVO().getCsaleorderid();
      SaleOrderVO soBill = soBillMap.get(csaleorderid);
      if (soBill == null) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0117")/*@res "销售订单已被删除，请刷新数据重做业务。"*/);
      }
      else {
        // 财务组织是否唯一
        this.checkArorg(sbBill, soBill);
        // 相关信息
        this.getGatherbillUtil().checkSoBalanceAndSaleorderConsistent(sbBill,
            soBill);
      }
    }
  }

  private void checkArorg(SoBalanceVO bill, SaleOrderVO saleordervo) {
    SoBalanceHVO sobalancehvo = bill.getParentVO();
    String carorgid = sobalancehvo.getCarorgid();
    SoBalanceBVO[] sobalancebvos = bill.getChildrenVO();
    boolean existOrderbal = false;
    for (SoBalanceBVO bodyvo : sobalancebvos) {
      int vostatus = bodyvo.getStatus();
      // 删除的行不补充冗余信息
      if (vostatus != VOStatus.DELETED) {
        int fibaltype = bodyvo.getFibaltype().intValue();
        if (SoBalanceType.SOBALANCE_ORDERBAL.getIntValue() == fibaltype) {
          existOrderbal = true;
        }
      }
    }

    if (existOrderbal) {
      SaleOrderBVO[] bodyvos = saleordervo.getChildrenVO();
      String carorg = saleordervo.getChildrenVO()[0].getCarorgid();
      if (!(carorgid.equals(carorg))) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006011_0", "04006011-0108")/*@res "订单收款核销关系与订单数据不一致，请刷新数据重做业务。"*/);
      }
      for (SaleOrderBVO bodyvo : bodyvos) {
        if (!(carorg.equals(bodyvo.getCarorgid()))) {
          ExceptionUtils
              .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
                  .getNCLangRes().getStrByID("4006011_0", "04006011-0107")/*@res "销售订单表体存在多个应收组织，不允许订单收款。"*/);
        }
      }
    }
  }

  private GatherbillUtil getGatherbillUtil() {
    if (this.gatherUtil == null) {
      this.gatherUtil = GatherbillUtil.getInstance();
    }
    return this.gatherUtil;
  }
}
