package nc.vo.so.m30.rule;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 收款限额/收款比例计算规则
 * 
 * @since 6.0
 * @version 2011-7-27 下午03:08:46
 * @author 刘志伟
 */
public class PreceiveQuotaRule {
  private IKeyValue keyValue;
  
  public PreceiveQuotaRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 价税合计影响收款限额时要走参数SO03
   */
  public void calculateByPara(String sSO03) {
    // 参数SO03优先规则为收款限额:除手工编辑其他因数不影响收款限额值
    // 收款限额不变,收款比例变化
    if (sSO03 != null && "收款限额".equals(sSO03)) {/*-=notranslate=-*/
      this.calculatePreceiveRate();
    }
    // 收款比例不变,收款限额变化
    else {
      this.calculatePreceiveQuoTa();
    }
  }

  /**
   * 收款比例影响收款限额
   */
  public void calculatePreceiveQuoTa() {
    UFDouble rate =
        this.keyValue.getHeadUFDoubleValue(SaleOrderHVO.NPRECEIVERATE);
    UFDouble totalorigmny =
        this.keyValue.getHeadUFDoubleValue(SaleOrderHVO.NTOTALORIGMNY);

    String corigcurrency =
        this.keyValue.getHeadStringValue(SaleOrderHVO.CORIGCURRENCYID);
    // rate可能来源模板默认:计算收款限额
    if (rate != null && totalorigmny != null) {
      UFDouble npreceivequota = totalorigmny.multiply(rate).multiply(0.01);
      ScaleUtils scaleutil =
          new ScaleUtils(AppContext.getInstance().getPkGroup());
      npreceivequota = scaleutil.adjustMnyScale(npreceivequota, corigcurrency);

      this.keyValue.setHeadValue(SaleOrderHVO.NPRECEIVEQUOTA, npreceivequota);
    }
    else {
      this.keyValue.setHeadValue(SaleOrderHVO.NPRECEIVEQUOTA, null);
    }
  }

  /**
   * 收款限额影响收款比例
   */
  public void calculatePreceiveRate() {
    UFDouble totalorigmny =
        this.keyValue.getHeadUFDoubleValue(SaleOrderHVO.NTOTALORIGMNY);
    totalorigmny = totalorigmny == null ? UFDouble.ZERO_DBL : totalorigmny;
    UFDouble npreceivequota =
        this.keyValue.getHeadUFDoubleValue(SaleOrderHVO.NPRECEIVEQUOTA);
    npreceivequota =
        npreceivequota == null ? UFDouble.ZERO_DBL : npreceivequota;

    if (MathTool.compareTo(totalorigmny, UFDouble.ZERO_DBL) != 0) {
      // 根据收款限额和价税合计,计算收款比例
      this.keyValue.setHeadValue(SaleOrderHVO.NPRECEIVERATE, npreceivequota
          .div(totalorigmny).multiply(100));
    }
  }
}
