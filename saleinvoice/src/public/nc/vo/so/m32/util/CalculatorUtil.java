package nc.vo.so.m32.util;

import java.util.HashSet;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m32trantype.IM32TranTypeService;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.Log;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32trantype.entity.M32TranTypeVO;
import nc.vo.so.m32trantype.enumeration.Adjuster;
import nc.vo.so.pub.SOConstant;

public class CalculatorUtil {

  /**
   * 在发票模板表头的字段
   */
  private static final String[] ATHEADKEYS = new String[] {
    SaleInvoiceHVO.NEXCHANGERATE, SaleInvoiceHVO.NGROUPEXCHGRATE,
    SaleInvoiceHVO.NGLOBALEXCHGRATE
  };

  private static CalculatorUtil util = new CalculatorUtil();

  private Set<String> hsAtHeadKey;

  private Set<String> hsNeedCalKey;

  /**
   * CalculatorUtil 的构造子
   */
  private CalculatorUtil() {
    // 私有化构造子
  }

  public static CalculatorUtil getInstance() {
    return CalculatorUtil.util;
  }

  /**
   * 方法功能描述：初始化在表头的字段。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author 冯加滨
   * @time 2010-5-5 下午02:17:37
   */
  public Set<String> getAtHeadKey() {
    if (null == this.hsAtHeadKey) {
      this.hsAtHeadKey = new HashSet<String>();
      for (String key : CalculatorUtil.ATHEADKEYS) {
        this.hsAtHeadKey.add(key);
      }
    }
    return this.hsAtHeadKey;
  }

  /**
   * 方法功能描述：初始化触发数量单价金额编辑事件的字段。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author 冯加滨
   * @time 2010-4-21 下午03:46:10
   */
  public Set<String> getNeedCalKey() {
    if (null == this.hsNeedCalKey) {
      this.hsNeedCalKey = new HashSet<String>();
      for (String key : SOConstant.STRNEEDCALKEY) {
        this.hsNeedCalKey.add(key);
      }
      this.hsNeedCalKey.add(SaleInvoiceBVO.NINVOICEDISRATE);
      this.hsNeedCalKey.add(SaleInvoiceBVO.NPRICE);
      // 取成本价需要这个字段触发
      this.hsNeedCalKey.add(SaleInvoiceBVO.NNETPRICE);
    }
    return this.hsNeedCalKey;
  }

  /**
   * 方法功能描述：返回发票交易类型调整项。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_group
   * @param trantypecode
   * @return <p>
   * @author fengjb
   * @time 2010-6-25 上午09:45:58
   */
  public boolean getChgPriceOrDiscount(String pk_group, String trantypecode) {
    boolean chgprice = false;
    // 默认调整折扣
    if (StringUtil.isEmptyWithTrim(pk_group)
        || StringUtil.isEmptyWithTrim(trantypecode)) {
      return chgprice;
    }
    M32TranTypeVO trantype = null;
    try {
      IM32TranTypeService service =
          NCLocator.getInstance().lookup(IM32TranTypeService.class);
      trantype = service.queryTranType(pk_group, trantypecode);
    }
    catch (Exception e) {
      Log.info(e);
      ExceptionUtils.wrappException(e);
    }
    // 查询到的发票交易类型为空
    if (null != trantype
        && Adjuster.ADJUSTPRICE.equalsValue(trantype.getFadjuster())) {
      chgprice = true;
    }
    return chgprice;
  }
}
