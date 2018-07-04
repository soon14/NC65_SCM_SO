package nc.vo.so.m32.rule;

import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.uif2.LoginContext;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售发票主表默认值设置工具类
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since 上一版本号 5.6
 * @author 冯加滨
 * @time 2010-4-23 下午03:56:31
 */
public class HeadDefaultValue {

  private final UFDouble hundred = new UFDouble(100);

  private IKeyValue keyValue;

  private LoginContext logctx;

  /**
   * HeadDefaultValue 的构造子
   * 
   * @param keyValue
   * @param logctx
   */
  public HeadDefaultValue(IKeyValue keyValue, LoginContext logctx) {
    this.keyValue = keyValue;
    this.logctx = logctx;
  }

  /**
   * 方法功能描述：设置销售发票主表默认值。
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @author 冯加滨
   * @time 2010-4-23 下午03:32:16
   */
  public void setDefaultValue() {
    // 集团
    this.keyValue.setHeadValue(SaleInvoiceHVO.PK_GROUP,
        this.logctx.getPk_group());
    // 发票折扣 100
    this.keyValue.setHeadValue(SaleInvoiceHVO.NHVOICEDISRATE, this.hundred);
    // 单据状态
    this.keyValue.setHeadValue(SaleInvoiceHVO.FSTATUSFLAG,
        BillStatus.FREE.value());
    // 打印次数
    this.keyValue.setHeadValue(SaleInvoiceHVO.IPRINTCOUNT, Integer.valueOf(0));
//    // 创建者
//    this.keyValue.setHeadValue(SaleInvoiceHVO.CREATOR,
//        this.logctx.getPk_loginUser());
//    // 制单人
//    this.keyValue.setHeadValue(SaleInvoiceHVO.BILLMAKER,
//        this.logctx.getPk_loginUser());
  }
}
