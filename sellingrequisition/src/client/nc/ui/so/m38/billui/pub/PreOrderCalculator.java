package nc.ui.so.m38.billui.pub;

import java.util.HashSet;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m38trantype.IM38TranTypeService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.calculator.data.BillCardPanelDataSet;
import nc.ui.pubapp.pub.scale.UIScaleUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.rule.HeadTotalCalculateRule;
import nc.vo.so.m38trantype.entity.M38TranTypeVO;
import nc.vo.so.pub.enumeration.PriceDiscountType;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;
import nc.vo.so.pub.util.SOSysParaInitUtil;

/**
 * 销售预订单前台数量单价金额计算类
 * 
 * @since 6.0
 * @version 2011-6-13 下午02:04:15
 * @author fengjb
 */
public class PreOrderCalculator {
  /**
   * 继承数量单价金额计算的公共卡片模板数据集
   */
  private static class PreOrderCarDataSet extends BillCardPanelDataSet {
    public PreOrderCarDataSet(BillCardPanel cardPanel, int row,
        IRelationForItems item) {
      super(cardPanel, row, item);
    }

    /** 获得原币币种 */
    @Override
    public String getCorigcurrencyid() {
      String value =
          (String) this.getBillCardPanel()
              .getHeadTailItem(PreOrderHVO.CORIGCURRENCYID).getValueObject();
      return value;
    }

    /** 获得集团 */
    @Override
    public String getPk_group() {
      String value =
          (String) this.getBillCardPanel()
              .getHeadTailItem(PreOrderHVO.PK_GROUP).getValueObject();
      return value;
    }

    @Override
    public boolean hasItemKey(String key) {
      if (PreOrderHVO.CORIGCURRENCYID.equals(key)
          || PreOrderHVO.PK_GROUP.equals(key)) {
        return true;
      }
      return super.hasItemKey(key);
    }
  }

  private static final String[] STRNEEDCALKEY = new String[] {
    // 数量、主数量、换算率
    PreOrderBVO.NASTNUM,
    PreOrderBVO.NNUM,
    PreOrderBVO.VCHANGERATE,
    // 单位
    PreOrderBVO.CASTUNITID,
    // 报价单位数量、报价换算率、税率
    PreOrderBVO.NQTUNITNUM,
    PreOrderBVO.VQTUNITRATE,
    PreOrderBVO.NTAXRATE,
    // 整单折扣、单品折扣、主含税单价、主无税单价
    PreOrderBVO.NDISCOUNTRATE, PreOrderBVO.NITEMDISCOUNTRATE,
    PreOrderBVO.NORIGTAXPRICE,
    PreOrderBVO.NORIGPRICE,
    // 主含税净价、主无税净价、含税单价
    PreOrderBVO.NORIGTAXNETPRICE, PreOrderBVO.NORIGNETPRICE,
    PreOrderBVO.NQTORIGTAXPRICE,
    // 无税单价、含税净价、无税净价
    PreOrderBVO.NQTORIGPRICE, PreOrderBVO.NQTORIGTAXNETPRC,
    PreOrderBVO.NQTORIGNETPRICE,

    // TODO 刘景 2012-2-17 删除原币税额字段
    // PreOrderBVO.NORIGTAX

    // 税额、本币无税金额、本币价税合计
    PreOrderBVO.NTAX, PreOrderBVO.NMNY, PreOrderBVO.NTAXMNY,
    // 计税金额
    PreOrderBVO.NCALTAXMNY,
    // 无税金额、价税合计
    PreOrderBVO.NORIGMNY, PreOrderBVO.NORIGTAXMNY,
    // 折扣额、折本汇率
    PreOrderBVO.NORIGDISCOUNT, PreOrderBVO.NEXCHANGERATE,
    // 集团本位币汇率、全局本位币汇率
    PreOrderBVO.NGROUPEXCHGRATE, PreOrderBVO.NGLOBALEXCHGRATE
  };

  private BillCardPanel cardpanel;

  private UFBoolean forechgprice;

  private Set<String> hsNeedCalKey;

  private IKeyValue keyValue;

  private M38TranTypeVO trantypevo;

  /**
   * PreOrderCalculator 的构造子
   * 
   * @param cardpanel
   */
  public PreOrderCalculator(BillCardPanel cardpanel) {
    this.cardpanel = cardpanel;
    this.keyValue = new CardKeyValue(cardpanel);
  }

  /**
   * 编辑字段editkey后计算row行数量单价金额
   * 
   * @param row
   * @param editkey
   */
  public void calculate(int row, String editkey) {
    int[] rows = new int[] {
      row
    };
    this.calcNumPriceMny(rows, editkey, false);
  }

  /**
   * 编辑字段editkey后计算rows[]行数量单价金额
   * 
   * @param rows
   * @param editkey
   */
  public void calculate(int[] rows, String editkey) {
    this.calcNumPriceMny(rows, editkey, false);
  }

  /**
   * 编辑字段editkey后只计算row行数量字段
   * 
   * @param row
   * @param editkey
   */
  public void calculateOnlyNum(int row, String editkey) {
    int[] rows = new int[] {
      row
    };
    this.calcNumPriceMny(rows, editkey, true);
  }

  /**
   * 编辑字段editkey后只计算rows[]行数量字段
   * 
   * @param row
   * @param editkey
   */
  public void calculateOnlyNum(int[] rows, String editkey) {
    this.calcNumPriceMny(rows, editkey, true);
  }

  /**
   * 处理购销类型发生变化后触发单价金额算法
   * 
   * @param rows 购销类型变化的行号数组
   */
  public void calculatorBuysell(int[] rows) {
    if (null != rows && rows.length > 0) {
      if (this.isTaxPrior()) {
        this.calculate(rows, PreOrderBVO.NQTORIGTAXPRICE);
      }
      else {
        this.calculate(rows, PreOrderBVO.NQTORIGPRICE);
      }
    }
  }

  /**
   * 设置强制调整折扣
   * 
   * @param forcechgdis
   */
  public void setChangePrice(UFBoolean forechgprice) {
    this.forechgprice = forechgprice;
  }

  /**
   * 设置缓存的交易类型VO
   * 
   * @param trantypevo
   */
  public void setTranTypeVO(M38TranTypeVO trantypevo) {
    this.trantypevo = trantypevo;
  }

  private void calcNumPriceMny(int[] rows, String editkey, boolean isnumonly) {
    // 参数合法性检查
    if (null == rows || rows.length == 0 || PubAppTool.isNull(editkey)) {
      return;
    }
    // 如果编辑字段不会影响到数量单价金额，不进行计算
    if (!this.getNeedCalKey().contains(editkey)) {
      return;
    }
    // 1.创建数据映射实例 获得数据项之间的映射关系
    IRelationForItems item = new RelationItemForCal();
    ScaleUtils scale = UIScaleUtils.getScaleUtils();

    // 2. 创建参数实例，在计算的时候用来获得参数条件：是否含税优先等
    Condition cond = SOCalConditionRule.getCondition();

    // 由交易类型获得调单价还是调折扣
    boolean isChgPriceOrDiscount = this.isChgPriceOrDiscount();
    cond.setIsChgPriceOrDiscount(isChgPriceOrDiscount);

    SOUnitChangeRateRule unitrule = new SOUnitChangeRateRule(this.keyValue);
    SOBuysellTriaRule buysellrule = new SOBuysellTriaRule(this.keyValue);
    for (int row : rows) {
      // 2.创建数据集实例 初始化数据关系计算用的数据集
      IDataSetForCal data = new PreOrderCarDataSet(this.cardpanel, row, item);
      Calculator tool = new Calculator(data, scale);

      // 设置是否跨国
      cond.setInternational(buysellrule.isBuysellFlagOut(row));
      // 设置是否固定单位换算率
      cond.setIsFixNchangerate(unitrule.isAstFixedRate(row));
      // 是否固定报价单位换算率
      cond.setIsFixNqtunitrate(unitrule.isQtFixedRate(row));
      if (isnumonly) {
        tool.calculateOnlyNumAssNumQtNum(cond, editkey);
      }
      else {
        tool.calculate(cond, editkey);
      }
    }

    HeadTotalCalculateRule totalrule =
        new HeadTotalCalculateRule(this.keyValue);
    totalrule.calculateHeadTotal();
  }

  private Set<String> getNeedCalKey() {
    if (null == this.hsNeedCalKey) {
      this.hsNeedCalKey = new HashSet<String>();
      for (String key : PreOrderCalculator.STRNEEDCALKEY) {
        this.hsNeedCalKey.add(key);
      }
    }
    return this.hsNeedCalKey;
  }

  private M38TranTypeVO getTranTypeVO() {

    if (null == this.trantypevo) {
      String trantypeid =
          this.keyValue.getHeadStringValue(PreOrderHVO.CTRANTYPEID);
      if (PubAppTool.isNull(trantypeid)) {
        return null;
      }
      IM38TranTypeService srv =
          NCLocator.getInstance().lookup(IM38TranTypeService.class);
      try {
        this.trantypevo = srv.queryTranTypeVO(trantypeid);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
    return this.trantypevo;
  }

  private boolean isChgPriceOrDiscount() {

    // 返回设置的强制调整项
    if (null != this.forechgprice) {
      return this.forechgprice.booleanValue();
    }
    M38TranTypeVO m38tranvo = this.getTranTypeVO();
    // 默认调整折扣
    if (null == m38tranvo) {
      return false;
    }
    Integer fmodifymny = m38tranvo.getFmodifymny();
    return PriceDiscountType.PRICETYPE.equalsValue(fmodifymny);
  }

  /**
   * 判断含税单价、无税单价的优先级别，以便触发单价金额算法
   */
  private boolean isTaxPrior() {
    String pk_group = AppUiContext.getInstance().getPkGroup();
    UFBoolean so23 = SOSysParaInitUtil.getSO23(pk_group);
    if (null == so23) {
      return false;
    }
    return so23.booleanValue();
  }
}
