package nc.vo.so.m30.pub;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.calculator.AmountCalculator;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.CacheDataSet;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.calculator.data.VODataSetForCal;
import nc.vo.pubapp.calculator.formula.DiscountMnyFormula;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.enumeration.PriceDiscountType;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;

public class SaleOrderVOCalculator {

  /**
   * 继承数量单价金额计算的VO模板数据集
   */
  private static class SaleOrderVODataSet extends VODataSetForCal {

    private SaleOrderHVO voHead;

    public SaleOrderVODataSet(SaleOrderHVO voHead, SaleOrderBVO currVO,
        IRelationForItems item) {
      super(currVO, item);
      this.voHead = voHead;
    }

    /** 获得原币币种 */
    @Override
    public String getCorigcurrencyid() {
      return this.voHead.getCorigcurrencyid();
    }

    @Override
    public boolean hasItemKey(String key) {
      if (SaleOrderHVO.CORIGCURRENCYID.equals(key)) {
        return true;
      }
      return super.hasItemKey(key);
    }
  }

  private UFBoolean forcechgprice;

  private UFBoolean forcefixunitrate;

  private Set<String> hsNeedCalKey;

  private IKeyValue keyValue;

  private M30TranTypeVO trantypevo;

  private SaleOrderVO voSaleOrder;

  public SaleOrderVOCalculator(SaleOrderVO voSaleOrder) {
    super();
    this.voSaleOrder = voSaleOrder;
    this.keyValue = new VOKeyValue<SaleOrderVO>(voSaleOrder);

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
   * 设置强制固定换算率
   * 
   * @param forcefix
   */
  public void setForceFixUnitRate(UFBoolean forcefix) {
    this.forcefixunitrate = forcefix;
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
    String pk_group = AppContext.getInstance().getPkGroup();
    ScaleUtils scale = new ScaleUtils(pk_group);

    // 创建参数实例，在计算的时候用来获得参数条件：是否含税优先等
    Condition cond = SOCalConditionRule.getCondition();

    // 由交易类型获得调单价还是调折扣
    boolean isChgPriceOrDiscount = this.isChgPriceOrDiscount();
    cond.setIsChgPriceOrDiscount(isChgPriceOrDiscount);

    SOUnitChangeRateRule unitrule = new SOUnitChangeRateRule(this.keyValue);
    // 性能优化：批量获取单位换算率是否是固定换算率 add by zhangby5 start
    Map<String, UFBoolean> fixedMap = unitrule.isAstAndQtFixedRate(rows);
    SOBuysellTriaRule buysellrule = new SOBuysellTriaRule(this.keyValue);
    SaleOrderHVO voHead = this.voSaleOrder.getParentVO();
    SaleOrderBVO[] voBodys = this.voSaleOrder.getChildrenVO();
    for (int row : rows) {

      SaleOrderBVO voBody = voBodys[row];
      // 2.创建数据集实例 初始化数据关系计算用的数据集
      IDataSetForCal data = new SaleOrderVODataSet(voHead, voBody, item);
      Calculator tool = new Calculator(data, scale);

      // 设置是否国内销售
      cond.setInternational(buysellrule.isBuysellFlagOut(row));
      if (null != this.forcefixunitrate) {
        // 设置是否固定单位换算率
        cond.setIsFixNchangerate(this.forcefixunitrate.booleanValue());
        // 是否固定报价单位换算率
        cond.setIsFixNqtunitrate(this.forcefixunitrate.booleanValue());
      }
      else {
        String cmaterialvid =
            keyValue.getBodyStringValue(row, SOItemKey.CMATERIALVID);
        String castunitid =
            keyValue.getBodyStringValue(row, SOItemKey.CASTUNITID);
        String cqtunitid =
            keyValue.getBodyStringValue(row, SOItemKey.CQTUNITID);
        String keycastunitid = cmaterialvid + castunitid;
        String keycqtunitid = cmaterialvid + cqtunitid;
        // 设置是否固定单位换算率
        cond.setIsFixNchangerate(fixedMap.get(keycastunitid).booleanValue());
        // 是否固定报价单位换算率
        cond.setIsFixNqtunitrate(fixedMap.get(keycqtunitid).booleanValue());

      }
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

  /**
   * 只计算原币折扣额 现在用于销售发票拉出库单的时候 因为出库单没有折扣额没有办法尾差
   * 再处理为差之间处理又是错误的（价税合计不对）
   * 所以需要处理完文采在单独计算一下折扣额
   * 
   * @param rows
   * @param editKey
   */
  public void calculateDiscountmny(int[] rows, String editkey) {
    // 参数合法性检查
    if (null == rows || rows.length == 0 || PubAppTool.isNull(editkey)) {
      return;
    }
    // 如果编辑字段不会影响到数量单价金额，不进行计算
    if (!this.getNeedCalKey().contains(editkey)) {
      return;
    }
    IRelationForItems item = new RelationItemForCal();
    // 2.创建数据集实例 初始化数据关系计算用的数据集
    IDataSetForCal data = null;
    SaleOrderHVO voHead = this.voSaleOrder.getParentVO();
    SaleOrderBVO[] voBodys = this.voSaleOrder.getChildrenVO();
    String pk_group = voHead.getPk_group();
    ScaleUtils scale = new ScaleUtils(pk_group);

    String taxPriceKey = item.getNqtorigtaxpriceKey();
    String numKey = item.getNqtunitnumKey();
    String summnyKey = item.getNorigtaxmnyKey();
    String discountMnyKey = item.getNorigdiscountKey();
    String curridKey = item.getCorigcurrencyidKey();

    // 原币
    String origcur = voHead.getCorigcurrencyid();
    UFDate billdate = voHead.getDbilldate();
    for (int row : rows) {
      SaleOrderBVO voBody = voBodys[row];
      data = new SaleOrderVODataSet(voHead, voBody, item);
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
      formula.setTaxNetPriceKey(item.getNqtorigtaxnetprcKey());
      formula.calculateDiscountMny();
      cachedata.commit();

      // 本币
      String pk_currency = voBody.getCcurrencyid();
      UFDouble norigdiscount = voBody.getNorigdiscount();
      UFDouble nexchangerate = voBody.getNexchangerate();
      if (null != norigdiscount) {
        // 用本币价税合计的精度
        UFDouble ndiscount =
            AmountCalculator.convertLocalCurreny(norigdiscount, origcur,
                pk_currency, pk_group, billdate, nexchangerate);
        voBody.setNdiscount(ndiscount);
      }
    }
  }

  private M30TranTypeVO getTranTypeVO() {
    if (null == this.trantypevo) {
      String trantypeid =
          this.keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);

      if (PubAppTool.isNull(trantypeid)) {
        return null;
      }
      IM30TranTypeService service =
          NCLocator.getInstance().lookup(IM30TranTypeService.class);
      try {
        this.trantypevo = service.queryTranTypeVO(trantypeid);
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
