package nc.vo.so.m32.util;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.AmountCalculator;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.CacheDataSet;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.calculator.data.VODataSetForCal;
import nc.vo.pubapp.calculator.formula.DiscountMnyFormula;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.rule.UnitChangeRateRule;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;

/**
 * 
 * @since 6.0
 * @version 2011-10-8 下午04:40:00
 * @author 么贵敬
 */
public class SaleInvoiceVOCalculator {

  /**
   * 继承数量单价金额计算的公共卡片模板数据集
   */
  private static class SaleInvoiceVODataSet extends VODataSetForCal {

    private SaleInvoiceHVO voHead;

    /**
     * SaleInvoiceCarDataSet 的构造子
     * 
     * @param cardPanel
     * @param row
     * @param item
     */
    public SaleInvoiceVODataSet(SaleInvoiceHVO voHead, SaleInvoiceBVO currVO,
        IRelationForItems item) {
      super(currVO, item);
      this.voHead = voHead;
    }

    /**
     * 发票本位币存储在表头，从表头获得
     */
    @Override
    public String getCcurrencyid() {
      return this.voHead.getCcurrencyid();
    }

    /**
     * 发票原币存储在表头，从表头获得
     */
    @Override
    public String getCorigcurrencyid() {
      return this.voHead.getCorigcurrencyid();
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
      // NCM_Begin_quyt_誉衡药业_这里返回的是空，这里将空取值为0_20150928
      UFDouble disrate =
          super.getNdiscountrate() == null ? UFDouble.ZERO_DBL : super
              .getNdiscountrate();
      // END

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
      return this.voHead.getNexchangerate();
    }

    /**
     * 全局折本汇率从表头获取
     */
    @Override
    public UFDouble getNglobalexchgrate() {
      return this.voHead.getNglobalexchgrate();
    }

    /**
     * 全局折本汇率从表头获取
     */
    @Override
    public UFDouble getNgroupexchgrate() {
      return this.voHead.getNgroupexchgrate();
    }

    /**
     * 集团从表头获取
     */
    @Override
    public String getPk_group() {
      return this.voHead.getPk_group();
    }

    /**
     * 不仅要判断是否在表头还要判断是否在表体
     */
    @Override
    public boolean hasItemKey(String key) {
      if (CalculatorUtil.getInstance().getAtHeadKey().contains(key)) {
        return true;
      }
      return super.hasItemKey(key);
    }

    /**
     * 设置单品折扣值
     */
    @Override
    public void setNdiscountrate(UFDouble value) {
      // 由于发票不允许整单折扣、单品折扣,故不用设置整单折扣值
    }
  }

  private UFBoolean isforetax;

  // 是否固定换算率是否根据物料参数
  private UFBoolean forcefixunitrate;

  private IRelationForItems item;

  // 发票VO
  private SaleInvoiceVO voInvoice;

  public SaleInvoiceVOCalculator() {
    // 1.创建数据映射实例 获得数据项之间的映射关系
    this.item = new RelationItemForCal();

    // 单品折扣字段设为“发票折扣”
    this.item.setNitemdiscountrateKey(SaleInvoiceBVO.NINVOICEDISRATE);
  }

  /**
   * SaleInvoiceCalculator 的构造子
   * 
   * @param cardpanel
   */
  public SaleInvoiceVOCalculator(SaleInvoiceVO voInvoice) {
    this.voInvoice = voInvoice;
    // 1.创建数据映射实例 获得数据项之间的映射关系
    this.item = new RelationItemForCal();

    // 单品折扣字段设为“发票折扣”
    this.item.setNitemdiscountrateKey(SaleInvoiceBVO.NINVOICEDISRATE);
  }

  /**
   * 表体row行的数量单价金额运算
   * 
   * @param row
   * @param editKey
   */
  public void calculate(int row, String editKey) {
    Integer[] rows = new Integer[] {
      Integer.valueOf(row)
    };
    this.calculate(rows, editKey);
  }

  /**
   * 方法功能描述：销售发票卡片界面数量单价金额运算。
   * <p>
   * <b>参数说明</b>
   * 
   * @param cardpanel
   * @param rows
   * @param editKey
   *          <p>
   * @author 冯加滨
   * @time 2010-4-21 下午03:14:20
   */
  public void calculate(Integer[] rows, String editKey) {
    // 如果编辑字段不会影响到数量单价金额，不进行计算
    if (!CalculatorUtil.getInstance().getNeedCalKey().contains(editKey)) {
      return;
    }

    // 2.创建数据集实例 初始化数据关系计算用的数据集
    IDataSetForCal data = null;
    Calculator tool = null;
    IKeyValue keyValue = new VOKeyValue<SaleInvoiceVO>(this.voInvoice);
    String pk_group = keyValue.getHeadStringValue(SaleInvoiceHVO.PK_GROUP);
    ScaleUtils scale = new ScaleUtils(pk_group);
    // 创建参数实例，在计算的时候用来获得参数条件：是否含税优先等
    Condition cond = SOCalConditionRule.getCondition();
    if (null != this.isforetax) {
      cond.setIsTaxOrNet(this.isforetax.booleanValue());
    }
    // 设置调单价方式调折扣
    String trantypecode =
        keyValue.getHeadStringValue(SaleInvoiceHVO.VTRANTYPECODE);
    boolean ischgprice =
        CalculatorUtil.getInstance().getChgPriceOrDiscount(pk_group,
            trantypecode);
    cond.setIsChgPriceOrDiscount(ischgprice);
    // 设置是否跨国
    SOBuysellTriaRule buyselrule = new SOBuysellTriaRule(keyValue);
    cond.setInternational(buyselrule.isHeadBuysellFlagOut());

    // 3.计算每行数据
    UnitChangeRateRule rule = new UnitChangeRateRule(keyValue);
    SaleInvoiceHVO voHead = this.voInvoice.getParentVO();
    SaleInvoiceBVO voBody = null;
    for (Integer row : rows) {
      int irow = row.intValue();
      voBody = this.voInvoice.getChildrenVO()[irow];
      if (null != this.forcefixunitrate) {
        // 设置业务单位是否固定换算率
        cond.setIsFixNchangerate(this.forcefixunitrate.booleanValue());
        // 设置报价单位是否固定换算率
        cond.setIsFixNqtunitrate(this.forcefixunitrate.booleanValue());
      }
      else {
        // 设置业务单位是否固定换算率
        cond.setIsFixNchangerate(rule.isAstFixedRate(irow));
        // 设置报价单位是否固定换算率
        cond.setIsFixNqtunitrate(rule.isQtFixedRate(irow));
      }
      data = new SaleInvoiceVODataSet(voHead, voBody, this.item);
      tool = new Calculator(data, scale);
      // 两个参数 cond 为计算时的参数条件
      tool.calculate(cond, editKey);

      // 数量单价金额编辑后重建前金额处理
      // 冲减前金额 = 价税合计 + 合并开票金额
      UFDouble taxmny =
          keyValue.getBodyUFDoubleValue(irow, SaleInvoiceBVO.NORIGTAXMNY);

      UFDouble origarsubmny =
          keyValue.getBodyUFDoubleValue(irow, SaleInvoiceBVO.NORIGSUBMNY);
      UFDouble nbfsubmny = MathTool.add(taxmny, origarsubmny);
      keyValue.setBodyValue(irow, "nbforigsubmny", nbfsubmny);
    }

  }

  public void calculateAll(String editKey) {
    int bodylen = this.voInvoice.getChildrenVO().length;
    Integer[] rows = new Integer[bodylen];
    for (int i = 0; i < bodylen; i++) {
      rows[i] = Integer.valueOf(i);
    }
    this.calculate(rows, editKey);
  }

  /**
   * 只计算原币折扣额 现在用于销售发票拉出库单的时候 因为出库单没有折扣额没有办法尾差
   * 再处理为差之间处理又是错误的（价税合计不对）
   * 所以需要处理完文采在单独计算一下折扣额
   * 
   * @param rows
   * @param editKey
   */
  public void calculateDiscountmny(Integer[] rows, String editKey) {
    // 如果编辑字段不会影响到数量单价金额，不进行计算
    if (!CalculatorUtil.getInstance().getNeedCalKey().contains(editKey)) {
      return;
    }
    // 2.创建数据集实例 初始化数据关系计算用的数据集
    IDataSetForCal data = null;
    SaleInvoiceHVO voHead = this.voInvoice.getParentVO();
    SaleInvoiceBVO voBody = null;
    String pk_group = voHead.getPk_group();
    ScaleUtils scale = new ScaleUtils(pk_group);
    // 折本汇率
    UFDouble nexchangerate = voHead.getNexchangerate();
    String taxPriceKey = this.item.getNqtorigtaxpriceKey();
    String numKey = this.item.getNqtunitnumKey();
    String summnyKey = this.item.getNorigtaxmnyKey();
    String discountMnyKey = this.item.getNorigdiscountKey();
    String curridKey = this.item.getCorigcurrencyidKey();

    String origcurr = voHead.getCorigcurrencyid();
    String pk_currency = voHead.getCcurrencyid();
    UFDate dbilldate = voHead.getDbilldate();
    for (Integer row : rows) {
      int irow = row.intValue();
      voBody = this.voInvoice.getChildrenVO()[irow];
      data = new SaleInvoiceVODataSet(voHead, voBody, this.item);

      CacheDataSet cachedata = new CacheDataSet(data);
      Object currid = cachedata.getAttributeValue(curridKey);
      boolean flag =
          data.hasItemKey(discountMnyKey) && data.hasItemKey(summnyKey)
              && data.hasItemKey(numKey) && currid != null;
      if (!flag) {
        return;
      }
      DiscountMnyFormula formula =
          new DiscountMnyFormula(cachedata, scale, taxPriceKey, numKey,
              summnyKey, discountMnyKey, curridKey);
      // 修改价税合计才需要传原币含税净价
      if (editKey.equals(SOItemKey.NORIGTAXMNY)) {
        formula.setTaxNetPriceKey(this.item.getNqtorigtaxnetprcKey());
      }
      formula.calculateDiscountMny();
      cachedata.commit();

      UFDouble norigdiscount = voBody.getNorigdiscount();
      if (null != norigdiscount) {
        if (origcurr.equals(pk_currency)) {
          voBody.setNdiscount(norigdiscount);
        }
        else {
          // 用本币价税合计的精度
          UFDouble ndiscount =
              AmountCalculator.convertLocalCurreny(norigdiscount, origcurr,
                  pk_currency, pk_group, dbilldate, nexchangerate);
          voBody.setNdiscount(ndiscount);
        }

      }
    }
  }

  public void setForcefixunitrate(UFBoolean forcefixunitrate) {
    this.forcefixunitrate = forcefixunitrate;
  }

  public void setVoInvoice(SaleInvoiceVO voInvoice) {
    this.voInvoice = voInvoice;
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
