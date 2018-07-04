package nc.ui.so.m30.pub;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.pub.scale.UIScaleUtils;
import nc.ui.so.pub.findprice.IFindPriceConfig;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.calculator.CalculatorUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.enumeration.AskPriceRule;
import nc.vo.so.pub.enumeration.LargessGetqtRule;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 销售订单询价 配 置 类
 * 
 * @since 6.0
 * @version 2011-6-13 下午03:18:27
 * @author feng jb
 */
public class SaleOrderFindPriceConfig implements IFindPriceConfig {

  private BillCardPanel cardPanel;

  private M30TranTypeVO m30trantypevo;

  /**
   * 构造方法
   * 
   * @param cardPanel
   */
  public SaleOrderFindPriceConfig(BillCardPanel cardPanel) {
    this.cardPanel = cardPanel;
  }

  /**
   * 构造方法
   * 
   * @param cardPanel
   * @param m30trantypevo
   */
  public SaleOrderFindPriceConfig(BillCardPanel cardPanel,
      M30TranTypeVO m30trantypevo) {
    this.cardPanel = cardPanel;
    this.m30trantypevo = m30trantypevo;
  }
  
  @Override
  public int getSalemode() {
    return m30trantypevo.getFsalemode();
  }

  @Override
  public Integer getAskPriceRule() {

    M30TranTypeVO trantype = this.getTranTypeVO();
    if (null == trantype) {
      return AskPriceRule.ASKPRICE_NO.getIntegerValue();
    }
    return trantype.getFaskqtrule();
  }

  @Override
  public AggregatedValueObject getBillVO() {
    AggregatedValueObject bill =
        this.cardPanel.getBillValueVO(SaleOrderVO.class.getName(),
            SaleOrderHVO.class.getName(), SaleOrderBVO.class.getName());
    return bill;
  }

  @Override
  public boolean isLargessAskPrice() {

    M30TranTypeVO trantype = this.getTranTypeVO();
    if (null == trantype) {
      return false;
    }
    return LargessGetqtRule.ASK_SALEQT.equalsValue(trantype
        .getFlargessgetqtrule());

  }

  @Override
  public boolean isModifyAskFail() {
    M30TranTypeVO trantype = this.getTranTypeVO();
    if (null == trantype) {
      return false;
    }
    UFBoolean bmodify = trantype.getBmodifyunaskedqt();
    return null != bmodify && bmodify.booleanValue();
  }

  @Override
  public boolean isModifyAskSucess() {
    M30TranTypeVO trantype = this.getTranTypeVO();
    if (null == trantype) {
      return false;
    }
    UFBoolean bmodify = trantype.getBmodifyaskedqt();
    return null != bmodify && bmodify.booleanValue();
  }

  @Override
  public boolean isShowMsgAskFail() {
    M30TranTypeVO trantype = this.getTranTypeVO();
    if (null == trantype) {
      return false;
    }
    UFBoolean bhint = trantype.getBnofindpricehit();
    return null != bhint && bhint.booleanValue();

  }

  @Override
  public void processAskFailRows(int[] failrows) {
    // TODO
  }

  @Override
  public void processAskSucessRows(int[] sucessrows, String chgkey) {
    SaleOrderCalculator calcultor = new SaleOrderCalculator(this.cardPanel);
    calcultor.setTranTypeVO(this.getTranTypeVO());
    calcultor.setChangePrice(UFBoolean.FALSE);

    calcultor.calculate(sucessrows, chgkey);
    IKeyValue keyValue = new CardKeyValue(this.cardPanel);

    // 由净价计算单价，保障满足折扣率的关系
    for (int row : sucessrows) {

      // 主单位系列无税单价=主单位系列税净价/折扣率
      this.calPriceByNetPrice(keyValue, row, SaleOrderBVO.NORIGPRICE,
          SaleOrderBVO.NORIGNETPRICE, true);
      this.calPriceByNetPrice(keyValue, row, SaleOrderBVO.NORIGTAXPRICE,
          SaleOrderBVO.NORIGTAXNETPRICE, true);
      this.calPriceByNetPrice(keyValue, row, SaleOrderBVO.NPRICE,
          SaleOrderBVO.NNETPRICE, false);
      this.calPriceByNetPrice(keyValue, row, SaleOrderBVO.NTAXPRICE,
          SaleOrderBVO.NTAXNETPRICE, false);

      // 报单位系列无税单价=报单位系列税净价/折扣率
      this.calPriceByNetPrice(keyValue, row, SaleOrderBVO.NQTORIGPRICE,
          SaleOrderBVO.NQTORIGNETPRICE, true);
      this.calPriceByNetPrice(keyValue, row, SaleOrderBVO.NQTORIGTAXPRICE,
          SaleOrderBVO.NQTORIGTAXNETPRC, true);
      this.calPriceByNetPrice(keyValue, row, SaleOrderBVO.NQTPRICE,
          SaleOrderBVO.NQTNETPRICE, false);
      this.calPriceByNetPrice(keyValue, row, SaleOrderBVO.NQTTAXPRICE,
          SaleOrderBVO.NQTTAXNETPRICE, false);
    }
  }

  /**
   * 由净价计算单价，保障满足折扣率的关系
   * 
   * @param keyValue
   * @param row
   * @param priceKey
   * @param netPriceKey
   */
  private void calPriceByNetPrice(IKeyValue keyValue, int row, String priceKey,
      String netPriceKey, boolean iscurtype) {
    UFDouble nitemdiscountrate =
        keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NITEMDISCOUNTRATE);
    nitemdiscountrate =
        CalculatorUtil.div(nitemdiscountrate, new UFDouble(100.0));

    UFDouble ndiscountrate =
        keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NDISCOUNTRATE);
    ndiscountrate = CalculatorUtil.div(ndiscountrate, new UFDouble(100.0));

    UFDouble discount =
        CalculatorUtil.multiply(nitemdiscountrate, ndiscountrate);
    int intZkl = MathTool.compareTo(discount, UFDouble.ZERO_DBL);
    if (intZkl != 0) {
      // 含税单价=含税净价/折扣率
      UFDouble ntaxnetprice = keyValue.getBodyUFDoubleValue(row, netPriceKey);
      UFDouble ntaxprice = CalculatorUtil.div(ntaxnetprice, discount);
      // 处理精度
      ScaleUtils scale = UIScaleUtils.getScaleUtils();
      String ccurrencyid =
          keyValue.getBodyStringValue(row, SaleOrderBVO.CCURRENCYID);
      String corigcurrencyid =
          keyValue.getHeadStringValue(SaleOrderHVO.CORIGCURRENCYID);
      if (iscurtype) {
        ntaxprice = scale.adjustSoPuPriceScale(ntaxprice, corigcurrencyid);
      }
      else {
        ntaxprice = scale.adjustSoPuPriceScale(ntaxprice, ccurrencyid);
      }
      keyValue.setBodyValue(row, priceKey, ntaxprice);
    }
  }

  private M30TranTypeVO getTranTypeVO() {
    if (null == this.m30trantypevo) {
      IKeyValue keyValue = new CardKeyValue(this.cardPanel);
      String trantypecode =
          keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
      String pk_group = AppContext.getInstance().getPkGroup();
      if (PubAppTool.isNull(trantypecode)) {
        return null;
      }
      IM30TranTypeService srv =
          NCLocator.getInstance().lookup(IM30TranTypeService.class);
      try {
        this.m30trantypevo = srv.queryTranType(pk_group, trantypecode);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }

    }
    return this.m30trantypevo;
  }

  @Override
  public boolean isblrgcashflag() {
    M30TranTypeVO trantype = this.getTranTypeVO();
    if (null == trantype) {
      return false;
    }
    if (trantype.getBlrgcashflag() == null
        || !trantype.getBlrgcashflag().booleanValue()) {
      return false;
    }
    return true;
  }
}
