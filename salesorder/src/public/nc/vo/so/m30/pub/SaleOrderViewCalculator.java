package nc.vo.so.m30.pub;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.env.BSContext;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.calculator.data.VODataSetForCal;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.enumeration.PriceDiscountType;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.ViewKeyValue;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;

public class SaleOrderViewCalculator {

  /**
   * 继承数量单价金额计算的VO模板数据集
   */
  private static class SaleOrderViewDataSet extends VODataSetForCal {

    private SaleOrderBVO voBody;

    private SaleOrderHVO voHead;

    public SaleOrderViewDataSet(SaleOrderHVO voHead, SaleOrderBVO voBody,
        IRelationForItems item) {
      super(voBody, item);
      this.voHead = voHead;
      this.voBody = voBody;
    }

    /** 获得原币币种 */
    @Override
    public String getCorigcurrencyid() {
      return this.voHead.getCorigcurrencyid();
    }

    @Override
    public String getPk_org() {
      String value = this.voHead.getPk_org();
      if (PubAppTool.isNull(value)) {
        value = this.voBody.getPk_org();
      }
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

  private UFBoolean forcechgprice;

  private Set<String> hsNeedCalKey;

  private Map<String, M30TranTypeVO> maptrantypevo =
      new HashMap<String, M30TranTypeVO>();

  private SaleOrderViewVO[] viewSaleOrder;

  public SaleOrderViewCalculator(SaleOrderViewVO[] viewSaleOrder) {
    super();
    this.viewSaleOrder = viewSaleOrder;

  }

  /**
   * 编辑字段editkey后计算rows[]行数量单价金额
   * 
   * @param rows
   * @param editkey
   */
  public void calculate(String editkey) {
    this.calcNumPriceMny(editkey, false);
  }

  /**
   * 编辑字段editkey后只计算rows[]行数量字段
   * 
   * @param row
   * @param editkey
   */
  public void calculateOnlyNum(String editkey) {
    this.calcNumPriceMny(editkey, true);
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
  public void setChangePrice(UFBoolean bforcechgprice) {
    this.forcechgprice = bforcechgprice;
  }

  private void calcNumPriceMny(String editkey, boolean isnumonly) {
    // 参数合法性检查
    if (null == this.viewSaleOrder || this.viewSaleOrder.length == 0
        || PubAppTool.isNull(editkey)) {
      return;
    }

    // 如果编辑字段不会影响到数量单价金额，不进行计算
    if (!this.getNeedCalKey().contains(editkey)) {
      return;
    }
    // 1.创建数据映射实例 获得数据项之间的映射关系
    IRelationForItems item = new RelationItemForCal();
    String pk_group = BSContext.getInstance().getGroupID();
    ScaleUtils scale = new ScaleUtils(pk_group);
    Condition cond = SOCalConditionRule.getCondition();

    for (SaleOrderViewVO view : this.viewSaleOrder) {

      SaleOrderHVO voHead = view.getHead();
      SaleOrderBVO voBody = view.getBody();

      // 2.创建数据集实例 初始化数据关系计算用的数据集
      IDataSetForCal data = new SaleOrderViewDataSet(voHead, voBody, item);
      Calculator tool = new Calculator(data, scale);

      // 由交易类型获得调单价还是调折扣
      boolean isChgPriceOrDiscount = this.isChgPriceOrDiscount(view);
      cond.setIsChgPriceOrDiscount(isChgPriceOrDiscount);

      IKeyValue keyValue = new ViewKeyValue<SaleOrderViewVO>(view);
      SOUnitChangeRateRule unitrule = new SOUnitChangeRateRule(keyValue);
      // 设置是否固定单位换算率
      cond.setIsFixNchangerate(unitrule.isAstFixedRate(0));
      // 是否固定报价单位换算率
      cond.setIsFixNqtunitrate(unitrule.isQtFixedRate(0));
      // 是否跨国
      SOBuysellTriaRule buselrule = new SOBuysellTriaRule(keyValue);
      cond.setInternational(buselrule.isBuysellFlagOut(0));

      if (isnumonly) {
        tool.calculateOnlyNumAssNumQtNum(cond, editkey);
      }
      else {
        tool.calculate(cond, editkey);
      }

      // 计算冲抵前金额
      UFDouble norigtaxmny = view.getBody().getNorigtaxmny();
      UFDouble norigsubmny = view.getBody().getNorigsubmny();
      view.setAttributeValue(SaleOrderBVO.NBFORIGSUBMNY,
          MathTool.add(norigtaxmny, norigsubmny));
    }
  }

  private M30TranTypeVO getTranTypeVO(SaleOrderViewVO viewvo) {
    String trantypeid = viewvo.getHead().getCtrantypeid();
    if (this.maptrantypevo.containsKey(trantypeid)) {
      return this.maptrantypevo.get(trantypeid);
    }

    M30TranTypeVO trantypevo = null;
    IM30TranTypeService service =
        NCLocator.getInstance().lookup(IM30TranTypeService.class);
    try {
      trantypevo = service.queryTranTypeVO(trantypeid);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    this.maptrantypevo.put(trantypeid, trantypevo);
    return trantypevo;
  }

  private boolean isChgPriceOrDiscount(SaleOrderViewVO viewvo) {
    // 如果强制调整折扣，返回
    if (null != this.forcechgprice) {
      return this.forcechgprice.booleanValue();
    }
    M30TranTypeVO m30tranvo = this.getTranTypeVO(viewvo);
    // 默认调整折扣
    if (null == m30tranvo) {
      return false;
    }
    Integer fmodifymny = m30tranvo.getFmodifymny();
    return PriceDiscountType.PRICETYPE.equalsValue(fmodifymny);

  }

}
