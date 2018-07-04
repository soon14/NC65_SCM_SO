package nc.ui.so.m30.pub;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.calculator.data.BillCardPanelDataSet;
import nc.ui.pubapp.pub.scale.UIScaleUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.bd.material.MaterialConvertVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.enumeration.PriceDiscountType;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;

/**
 * 销售订单数量单价金额计算类
 * 
 * @since 6.0
 * @version 2011-6-13 下午02:36:21
 * @author fengjb
 */
public class SaleOrderCalculator {

  /**
   * 继承数量单价金额计算的公共卡片模板数据集
   */
  private static class SaleOrderBillCardPanelDataSet extends
      BillCardPanelDataSet {

    public SaleOrderBillCardPanelDataSet(BillCardPanel cardPanel, int row,
        IRelationForItems item) {
      super(cardPanel, row, item);
    }

    /** 获得原币币种 */
    @Override
    public String getCorigcurrencyid() {
      String value =
          (String) this.getBillCardPanel()
              .getHeadTailItem(SaleOrderHVO.CORIGCURRENCYID).getValueObject();
      return value;
    }

    @Override
    public boolean hasItemKey(String key) {
      if (SaleOrderHVO.CORIGCURRENCYID.equals(key)) {
        return true;
      }
      return super.hasItemKey(key);
    }
  }

  private BillCardPanel cardPanel;

  private UFBoolean forcechgprice;

  private Set<String> hsNeedCalKey;

  private IKeyValue keyValue;

  private M30TranTypeVO trantypevo;

  public SaleOrderCalculator(BillCardPanel cardPanel) {
    super();
    this.cardPanel = cardPanel;
    this.keyValue = new CardKeyValue(cardPanel);

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

  public Set<String> getNeedCalKey() {
    if (null == this.hsNeedCalKey) {
      this.hsNeedCalKey = new HashSet<String>();
      for (String key : SOConstant.STRNEEDCALKEY) {
        this.hsNeedCalKey.add(key);
      }
    }
    return this.hsNeedCalKey;
  }

  /**
   * 设置强制调整折扣
   * 
   * @param forcechgdis
   */
  public void setChangePrice(UFBoolean forcechgprice) {
    this.forcechgprice = forcechgprice;
  }

  /**
   * 设置缓存的交易类型VO
   * 
   * @param trantypevo
   */
  public void setTranTypeVO(M30TranTypeVO trantypevo) {
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

    // 创建参数实例，在计算的时候用来获得参数条件：是否含税优先等
    Condition cond = SOCalConditionRule.getCondition();
    // 由交易类型获得调单价还是调折扣
    //王梓懿 修改For鞍钢 没有调折扣的情况所以直接调单价
    boolean isChgPriceOrDiscount = Boolean.TRUE;//this.isChgPriceOrDiscount();
    cond.setIsChgPriceOrDiscount(isChgPriceOrDiscount);
    //
    //王梓懿 ：2018-06-22 含税优先 根据非钢李鸣张飒要求
    //根据鞍钢国贸最新要求：主单位原币无税净价、主单位原币无税单价、主单位原币无税金额不变
    cond.setIsTaxOrNet(false);

    SOUnitChangeRateRule unitrule = new SOUnitChangeRateRule(this.keyValue);
    // 性能优化：批量获取单位换算率是否是固定换算率 add by zhangby5 start
    Map<String, UFBoolean> fixedMap = unitrule.isAstAndQtFixedRate(rows);
    
    SOBuysellTriaRule buysellrule = new SOBuysellTriaRule(this.keyValue);
    for (int row : rows) {
      // 2.创建数据集实例 初始化数据关系计算用的数据集
      IDataSetForCal data =
          new SaleOrderBillCardPanelDataSet(this.cardPanel, row, item);
      Calculator tool = new Calculator(data, scale);
      // 设置是否国内销售
      cond.setInternational(buysellrule.isBuysellFlagOut(row));
      String cmaterialvid =
          keyValue.getBodyStringValue(row, SOItemKey.CMATERIALVID);
      String castunitid =
          keyValue.getBodyStringValue(row, SOItemKey.CASTUNITID);
      String cqtunitid = keyValue.getBodyStringValue(row, SOItemKey.CQTUNITID);
      String keycastunitid = cmaterialvid + castunitid;
      String keycqtunitid = cmaterialvid + cqtunitid;
      // 设置是否固定单位换算率
      cond.setIsFixNchangerate(fixedMap.get(keycastunitid).booleanValue());
      // 是否固定报价单位换算率
      cond.setIsFixNqtunitrate(fixedMap.get(keycqtunitid).booleanValue());
      
      // zhangby5 end
      if (isnumonly) {
        tool.calculateOnlyNumAssNumQtNum(cond, editkey);
      }
      else {
        tool.calculate(cond, editkey);
      }

      // 计算冲抵前金额
      UFDouble norigtaxmny =
          this.keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NORIGTAXMNY);
      UFDouble norigsubmny =
          this.keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NORIGSUBMNY);
      this.keyValue.setBodyValue(row, SaleOrderBVO.NBFORIGSUBMNY,
          MathTool.add(norigtaxmny, norigsubmny));
    }

    // 计算表体合计信息
    HeadTotalCalculateRule totalrule =
        new HeadTotalCalculateRule(this.keyValue);
    totalrule.calculateHeadTotal();

  }

  private M30TranTypeVO getTranTypeVO() {
    if (null == this.trantypevo) {
      String trantypecode =
          this.keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
      String pk_group = AppContext.getInstance().getPkGroup();
      if (PubAppTool.isNull(trantypecode)) {
        return null;
      }
      IM30TranTypeService service =
          NCLocator.getInstance().lookup(IM30TranTypeService.class);
      try {
        this.trantypevo = service.queryTranType(pk_group, trantypecode);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }

    }
    return this.trantypevo;
  }

  private boolean isChgPriceOrDiscount() {
    // 如果强制调整折扣，返回
    if (null != this.forcechgprice) {
      return this.forcechgprice.booleanValue();
    }
    M30TranTypeVO m30tranvo = this.getTranTypeVO();
    // 默认调整折扣
    if (null == m30tranvo) {
      return false;
    }
    Integer fmodifymny = m30tranvo.getFmodifymny();
    return PriceDiscountType.PRICETYPE.equalsValue(fmodifymny);

  }
}
