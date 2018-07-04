package nc.vo.so.m32.rule;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.AbstractKeyValue.RowStatus;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>表头
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since 上一版本号 5.6
 * @author 冯加滨
 * @time 2010-4-22 下午08:12:33
 */
public class TotalCalcateRule {
  private IKeyValue keyValue;

  /**
   * HeadTotalCalculator 的构造子
   * 
   * @param keyValue
   */
  public TotalCalcateRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 方法功能描述： 根据表体值计算表头合计。
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @author 冯加滨
   * @time 2010-4-21 上午09:55:19
   */
  public void calcHeadTotal() {
    UFDouble totalnum = new UFDouble(0);
    UFDouble totalmny = new UFDouble(0);
    UFDouble totalsub = new UFDouble(0);
    int rowcount = this.keyValue.getBodyCount();
    for (int i = 0; i < rowcount; i++) {
      // 删除行不计算合计
      if (RowStatus.DELETED == this.keyValue.getRowStatus(i)) {
        continue;
      }
      // 数量
      UFDouble astnum =
          this.keyValue.getBodyUFDoubleValue(i, SaleInvoiceBVO.NASTNUM);

      totalnum = MathTool.add(totalnum, astnum);
      // 赠品标志
      UFBoolean largessflag =
          this.keyValue.getBodyUFBooleanValue(i, SaleInvoiceBVO.BLARGESSFLAG);
      // 赠品行金额不计入到表头累计金额
      if ((null != largessflag) && largessflag.booleanValue()) {
        continue;
      }
      // 价税合计
      UFDouble orgtaxmny =
          this.keyValue.getBodyUFDoubleValue(i, SaleInvoiceBVO.NORIGTAXMNY);
      totalmny = MathTool.add(totalmny, orgtaxmny);
      // 冲减金额
      UFDouble orgsubmny =
          this.keyValue.getBodyUFDoubleValue(i, SaleInvoiceBVO.NORIGSUBMNY);
      totalsub = MathTool.add(totalsub, orgsubmny);
    }
    this.keyValue.setHeadValue(SaleInvoiceHVO.NTOTALASTNUM, totalnum);
    this.keyValue.setHeadValue(SaleInvoiceHVO.NTOTALORIGMNY, totalmny);
    this.keyValue.setHeadValue(SaleInvoiceHVO.NTOTALORIGSUBMNY, totalsub);
  }
}
