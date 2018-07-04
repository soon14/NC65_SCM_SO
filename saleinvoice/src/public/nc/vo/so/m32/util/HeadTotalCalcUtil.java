package nc.vo.so.m32.util;

import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.rule.TotalCalcateRule;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>VO批量运算表头合计数量、金额的工具类
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since 上一版本号 5.6
 * @author 冯加滨
 * @time 2010-4-22 下午08:43:07
 */
public class HeadTotalCalcUtil {

  private static HeadTotalCalcUtil instancce = new HeadTotalCalcUtil();

  /**
   * VOTotalCalculator 的构造子
   */
  private HeadTotalCalcUtil() {
    // 缺省构造方法
  }

  /**
   * 方法功能描述：返回单例模式实例。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-8-9 下午03:42:48
   */
  public static HeadTotalCalcUtil getInstance() {
    return HeadTotalCalcUtil.instancce;
  }

  public void calcHeadTotalValue(IKeyValue keyvalue) {

    TotalCalcateRule calcrule = null;
    calcrule = new TotalCalcateRule(keyvalue);
    calcrule.calcHeadTotal();

  }

  /**
   * 方法功能描述：计算表头合计值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param voInvoices
   *          <p>
   * @author fengjb
   * @time 2010-8-9 下午03:43:08
   */
  public void calcHeadTotalValue(SaleInvoiceVO[] voInvoices) {

    IKeyValue vokeyvalue = null;
    TotalCalcateRule calcrule = null;
    for (SaleInvoiceVO invoicevo : voInvoices) {
      vokeyvalue = new VOKeyValue<SaleInvoiceVO>(invoicevo);
      calcrule = new TotalCalcateRule(vokeyvalue);
      calcrule.calcHeadTotal();
    }
  }
}
