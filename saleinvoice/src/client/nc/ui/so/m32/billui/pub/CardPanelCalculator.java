package nc.ui.so.m32.billui.pub;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.rule.UnitChangeRateRule;
import nc.vo.so.m32.util.CalculatorUtil;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.calculator.data.BillCardPanelDataSet;
import nc.ui.pubapp.pub.scale.UIScaleUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * 销售发票卡片单价金额算法
 * 
 * @since 6.3
 * @version 2012-12-21 下午01:01:01
 * @author yaogj
 */
public class CardPanelCalculator {

  private UFBoolean isforetax;

  /**
   * 继承数量单价金额计算的公共卡片模板数据集
   */
  private static class SaleInvoiceCarDataSet extends BillCardPanelDataSet {

    /**
     * SaleInvoiceCarDataSet 的构造子
     * 
     * @param cardPanel
     * @param row
     * @param item
     */
    public SaleInvoiceCarDataSet(BillCardPanel cardPanel, int row,
        IRelationForItems item) {
      super(cardPanel, row, item);
    }

    /**
     * 发票本位币存储在表头，从表头获得
     */
    @Override
    public String getCcurrencyid() {

      String value = null;
      BillItem orgcurencyitem =
          this.getBillCardPanel().getHeadItem(SaleInvoiceHVO.CCURRENCYID);
      if (null != orgcurencyitem) {
        Object objorgcurency = orgcurencyitem.getValueObject();
        value = ValueUtils.getString(objorgcurency);
      }
      return value;
    }

    /**
     * 发票原币存储在表头，从表头获得
     */
    @Override
    public String getCorigcurrencyid() {
      String value = null;
      BillItem orgcurencyitem =
          this.getBillCardPanel().getHeadItem(SaleInvoiceHVO.CORIGCURRENCYID);
      if (null != orgcurencyitem) {
        Object objorgcurency = orgcurencyitem.getValueObject();
        value = ValueUtils.getString(objorgcurency);
      }
      return value;
    }

    /**
     * 发票报价币种等于原币币种
     */
    @Override
    public String getCqtcurrencyid() {
      return this.getCorigcurrencyid();
    }

    /**
     * 发票整单折扣 = 发票整单折扣*单品折扣/100
     */
    @Override
    public UFDouble getNdiscountrate() {
      UFDouble disrate = super.getNdiscountrate();

      UFDouble itemdisrate =
          ValueUtils.getUFDouble(this
              .getAttributeValue(SaleInvoiceBVO.NITEMDISCOUNTRATE));
      itemdisrate = itemdisrate == null ? UFDouble.ZERO_DBL : itemdisrate;
      disrate = disrate.multiply(itemdisrate).div(SOConstant.ONEHUNDRED);
      return disrate;
    }

    /**
     * 折本汇率从表头获取
     */
    @Override
    public UFDouble getNexchangerate() {
      Object obj =
          this.getBillCardPanel().getHeadItem(SaleInvoiceHVO.NEXCHANGERATE)
              .getValueObject();
      UFDouble value = ValueUtils.getUFDouble(obj);
      return value;
    }

    /**
     * 全局折本汇率从表头获取
     */
    @Override
    public UFDouble getNglobalexchgrate() {
      Object obj =
          this.getBillCardPanel().getHeadItem(SaleInvoiceHVO.NGLOBALEXCHGRATE)
              .getValueObject();
      UFDouble value = ValueUtils.getUFDouble(obj);
      return value;
    }

    /**
     * 全局折本汇率从表头获取
     */
    @Override
    public UFDouble getNgroupexchgrate() {
      Object obj =
          this.getBillCardPanel().getHeadItem(SaleInvoiceHVO.NGROUPEXCHGRATE)
              .getValueObject();
      UFDouble value = ValueUtils.getUFDouble(obj);
      return value;
    }

    /**
     * 集团从表头获取
     */
    @Override
    public String getPk_group() {
      String value = null;
      BillItem groupitem =
          this.getBillCardPanel().getHeadItem(SaleInvoiceHVO.PK_GROUP);
      if (null != groupitem) {
        Object objorgcurency = groupitem.getValueObject();
        value = ValueUtils.getString(objorgcurency);
      }
      return value;
    }

    /**
     * 不仅要判断是否在表头还要判断是否在表体
     */
    @Override
    public boolean hasItemKey(String key) {
      if (CalculatorUtil.getInstance().getAtHeadKey().contains(key)) {
        BillItem headItem = this.getBillCardPanel().getHeadItem(key);
        return headItem != null;
      }
      return super.hasItemKey(key);
    }

    /**
     * 设置整单折扣值
     */
    @Override
    public void setNdiscountrate(UFDouble value) {
      // 由于发票不允许整单折扣、单品折扣,故不用设置整单折扣值
    }
  }

  private BillCardPanel cardpanel;

  /**
   * SaleInvoiceCalculator 的构造子
   * 
   * @param cardpanel
   */
  public CardPanelCalculator(BillCardPanel cardpanel) {
    this.cardpanel = cardpanel;
  }

  /**
   * 计算
   * 
   * @param row 行
   * @param editKey 修改的字段
   */
  public void calculate(int row, String editKey) {
    // 如果编辑字段不会影响到数量单价金额，不进行计算
    if (!CalculatorUtil.getInstance().getNeedCalKey().contains(editKey)) {
      return;
    }
    // 1.创建数据映射实例 获得数据项之间的映射关系
    IRelationForItems item = new RelationItemForCal();
    // 单品折扣字段设为“发票折扣”
    item.setNitemdiscountrateKey(SaleInvoiceBVO.NINVOICEDISRATE);

    // 2.创建数据集实例 初始化数据关系计算用的数据集
    IDataSetForCal data = null;
    Calculator tool = null;
    ScaleUtils scale = UIScaleUtils.getScaleUtils();
    CardKeyValue keyValue = new CardKeyValue(this.cardpanel);
    // 创建参数实例，在计算的时候用来获得参数条件：是否含税优先等
    Condition cond = SOCalConditionRule.getCondition();
    // 设置调单价方式调折扣
    String trantypecode =
        keyValue.getHeadStringValue(SaleInvoiceHVO.VTRANTYPECODE);
    String pk_group = AppContext.getInstance().getPkGroup();
    boolean ischgprice =
        CalculatorUtil.getInstance().getChgPriceOrDiscount(pk_group,
            trantypecode);
    cond.setIsChgPriceOrDiscount(ischgprice);

    SOBuysellTriaRule buysellrule = new SOBuysellTriaRule(keyValue);
    cond.setInternational(buysellrule.isHeadBuysellFlagOut());
    // 3.计算每行数据
    UnitChangeRateRule rule = new UnitChangeRateRule(keyValue);

    // 设置业务单位是否固定换算率
    cond.setIsFixNchangerate(rule.isAstFixedRate(row));
    // 设置报价单位是否固定换算率
    cond.setIsFixNqtunitrate(rule.isQtFixedRate(row));

    data = new SaleInvoiceCarDataSet(this.cardpanel, row, item);
    tool = new Calculator(data, scale);
    // 两个参数 cond 为计算时的参数条件
    tool.calculate(cond, editKey);

    // 数量单价金额编辑后重建前金额处理
    // 冲减前金额 = 价税合计 + 费用冲抵金额
    UFDouble taxmny =
        keyValue.getBodyUFDoubleValue(row, SaleInvoiceBVO.NORIGTAXMNY);

    UFDouble origarsubmny =
        keyValue.getBodyUFDoubleValue(row, SaleInvoiceBVO.NORIGSUBMNY);
    UFDouble nbfsubmny = MathTool.add(taxmny, origarsubmny);
    keyValue.setBodyValue(row, "nbforigsubmny", nbfsubmny);

  }

  /**
   * 销售发票卡片界面数量单价金额运算
   * 
   * @param rows
   * @param editKey
   */
  public void calculate(int[] rows, String editKey) {
    // 如果编辑字段不会影响到数量单价金额，不进行计算
    if (!CalculatorUtil.getInstance().getNeedCalKey().contains(editKey)) {
      return;
    }
    // 1.创建数据映射实例 获得数据项之间的映射关系
    IRelationForItems item = new RelationItemForCal();
    // 单品折扣字段设为“发票折扣”
    item.setNitemdiscountrateKey(SaleInvoiceBVO.NINVOICEDISRATE);

    // 2.创建数据集实例 初始化数据关系计算用的数据集
    IDataSetForCal data = null;
    Calculator tool = null;
    ScaleUtils scale = UIScaleUtils.getScaleUtils();
    CardKeyValue keyValue = new CardKeyValue(this.cardpanel);
    // 创建参数实例，在计算的时候用来获得参数条件：是否含税优先等
    Condition cond = SOCalConditionRule.getCondition();
    if (null != this.isforetax) {
      cond.setIsTaxOrNet(this.isforetax.booleanValue());
    }
    // 设置调单价方式调折扣
    String trantypecode =
        keyValue.getHeadStringValue(SaleInvoiceHVO.VTRANTYPECODE);
    String pk_group = AppContext.getInstance().getPkGroup();
    boolean ischgprice =
        CalculatorUtil.getInstance().getChgPriceOrDiscount(pk_group,
            trantypecode);
    cond.setIsChgPriceOrDiscount(ischgprice);

    SOBuysellTriaRule buysellrule = new SOBuysellTriaRule(keyValue);
    cond.setInternational(buysellrule.isHeadBuysellFlagOut());
    // 3.计算每行数据
    UnitChangeRateRule rule = new UnitChangeRateRule(keyValue);
    for (int row : rows) {
      // 设置业务单位是否固定换算率
      cond.setIsFixNchangerate(rule.isAstFixedRate(row));
      // 设置报价单位是否固定换算率
      cond.setIsFixNqtunitrate(rule.isQtFixedRate(row));

      data = new SaleInvoiceCarDataSet(this.cardpanel, row, item);
      tool = new Calculator(data, scale);
      // 两个参数 cond 为计算时的参数条件
      tool.calculate(cond, editKey);

      // 数量单价金额编辑后重建前金额处理
      // 冲减前金额 = 价税合计 + 费用冲抵金额
      UFDouble taxmny =
          keyValue.getBodyUFDoubleValue(row, SaleInvoiceBVO.NORIGTAXMNY);

      UFDouble origarsubmny =
          keyValue.getBodyUFDoubleValue(row, SaleInvoiceBVO.NORIGSUBMNY);
      UFDouble nbfsubmny = MathTool.add(taxmny, origarsubmny);
      keyValue.setBodyValue(row, "nbforigsubmny", nbfsubmny);
    }
  }

  /**
   * 方法功能描述：计算表体所有行。
   * <p>
   * <b>参数说明</b>
   * 
   * @param editKey
   *          <p>
   * @author 冯加滨
   * @time 2010-5-6 下午08:26:38
   */
  public void calculateAll(String editKey) {
    // 表体行数
    int rowCount = this.cardpanel.getRowCount();

    int[] editRows = new int[rowCount];
    for (int i = 0; i < rowCount; i++) {
      editRows[i] = i;
    }

    this.calculate(editRows, editKey);
  }

  /**
   * 设置强制含税优先标志
   * 
   * @param forcetax
   */
  public void setForceTaxPrior(UFBoolean forcetax) {
    this.isforetax = forcetax;
  }
}
