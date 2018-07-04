package nc.vo.so.m30.rule;

import java.util.Map;

import nc.vo.bd.income.IncomeChVO;
import nc.vo.bd.income.IncomeVO;
import nc.vo.bd.payment.IPaymentUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

import nc.itf.scmpub.reference.uap.bd.payterm.PaytermService;

/**
 * 收款协议规则——根据收款协议设置收款协议上信息
 * 
 * @since 6.0
 * @version 2011-7-27 下午03:08:46
 * @author 刘志伟
 */
public class PayTermRule {

  private IKeyValue keyValue;

  /**
   * 此处应该引用收款帐期下的收款时点枚举，但目前nc.vo.uap.bd.income.incomeeffectdate找不到
   * 
   * 1001Z01000000000E4JX 出库日期
   * 1001Z01000000000E4JY 出库签字日期
   * 1001Z01000000000E4JZ 销售开票日期
   * 1001Z01000000000E4K0 销售发票审核日期
   * 1001Z01000000000E4K1 销售订单日期
   * 1001Z01000000000E4K2 销售合同生效日期
   */
  private static final String INCOMEEFFDATE_ORDER =
      IPaymentUtil.SALE_ORDER_DATE;// "1001Z01000000000E4K1"

  /**
   * 构造子
   * 
   * @param keyValue
   */
  public PayTermRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 设置收款协议详细信息
   */
  public void setPayTermInfo() {

    this.keyValue.setHeadValue(SaleOrderHVO.NPRECEIVERATE, null);
    this.keyValue.setHeadValue(SaleOrderHVO.NPRECEIVEQUOTA, null);
    this.keyValue.setHeadValue(SaleOrderHVO.BPRECEIVEFLAG, UFBoolean.FALSE);
    // 收款协议
    String paytermid =
        this.keyValue.getHeadStringValue(SaleOrderHVO.CPAYTERMID);
    if (PubAppTool.isNull(paytermid)) {
      return;
    }

    Map<String, IncomeVO> mapPaytem = null;

    mapPaytem = PaytermService.queryIncomeByPk(new String[] {
      paytermid
    });

    if (null == mapPaytem || mapPaytem.size() == 0) {
      return;
    }
    IncomeVO vo = mapPaytem.get(paytermid);
    IncomeChVO[] chvos = vo.getPaymentch();
    if (null == chvos || chvos.length == 0) {
      return;
    }
    UFDouble accrate = null;
    for (IncomeChVO chvo : chvos) {
      // 目前nc.vo.uap.bd.income.incomeeffectdate找不到
      if (PayTermRule.INCOMEEFFDATE_ORDER.equals(chvo.getPk_incomeperiod())) {
        UFBoolean prepayment = chvo.getPrepayment();
        // 只有当勾选了预收款后 才进行设置比例、预收款标志、结算方式 ，modify by wangshu6 20150403
        UFBoolean soHeadPrepayment =
        this.keyValue.getHeadUFBooleanValue(SaleOrderHVO.BPRECEIVEFLAG);
        // 同时已经设置过预收款后 之后的行开始不再进行设置
        if (soHeadPrepayment != null && soHeadPrepayment.booleanValue()) { 
        continue;
        } 
        // 未取到收款协议上的预收款比例，则取模板默认值
        this.keyValue.setHeadValue(SaleOrderHVO.NPRECEIVERATE,
            chvo.getAccrate());
        // 预收款标志
        this.keyValue.setHeadValue(SaleOrderHVO.BPRECEIVEFLAG, prepayment);
        accrate = chvo.getAccrate();
        // 结算方式
        this.keyValue.setHeadValue(SaleOrderHVO.CBALANCETYPEID,
            chvo.getPk_balatype());
      }
    }
    this.keyValue.setHeadValue(SaleOrderHVO.NPRECEIVERATE, accrate);
    // 计算收款限额
    new PreceiveQuotaRule(this.keyValue).calculatePreceiveQuoTa();

  }
}
