package nc.ui.so.m38.billui.pub;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.CalculatorUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.m38trantype.entity.M38TranTypeVO;
import nc.vo.so.pub.enumeration.AskPriceRule;
import nc.vo.so.pub.enumeration.LargessGetqtRule;
import nc.vo.so.pub.keyvalue.IKeyValue;

import nc.itf.so.m38trantype.IM38TranTypeService;

import nc.bs.framework.common.NCLocator;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.so.pub.findprice.IFindPriceConfig;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * 销售订单询价配置 类
 * 
 */
public class PreOrderFindPriceConfig implements IFindPriceConfig {

  private M38TranTypeVO m38trantypevo;

  private BillCardPanel cardPanel;

  public PreOrderFindPriceConfig(BillCardPanel cardPanel) {
    this.cardPanel = cardPanel;
  }

  private M38TranTypeVO getTranTypeVO() {
    if (null == this.m38trantypevo) {
      IKeyValue keyValue = new CardKeyValue(this.cardPanel);
      String trantypeid = keyValue.getHeadStringValue(PreOrderHVO.CTRANTYPEID);
      if (PubAppTool.isNull(trantypeid)) {
        return null;
      }
      IM38TranTypeService srv =
          NCLocator.getInstance().lookup(IM38TranTypeService.class);
      try {
        this.m38trantypevo = srv.queryTranTypeVO(trantypeid);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }

    }
    return this.m38trantypevo;
  }

  @Override
  public boolean isModifyAskSucess() {
    M38TranTypeVO trantype = this.getTranTypeVO();
    if (null == trantype) {
      return false;
    }
    UFBoolean bmodify = trantype.getBmodifyaskedqt();
    return null != bmodify && bmodify.booleanValue();
  }

  @Override
  public boolean isModifyAskFail() {
    M38TranTypeVO trantype = this.getTranTypeVO();
    if (null == trantype) {
      return false;
    }
    UFBoolean bmodify = trantype.getBmodifyunaskedqt();
    return null != bmodify && bmodify.booleanValue();
  }

  @Override
  public boolean isShowMsgAskFail() {
    M38TranTypeVO trantype = this.getTranTypeVO();
    if (null == trantype) {
      return false;
    }
    UFBoolean bhint = trantype.getBnofindpricehit();
    return null != bhint && bhint.booleanValue();

  }

  @Override
  public boolean isLargessAskPrice() {

    M38TranTypeVO trantype = this.getTranTypeVO();
    if (null == trantype) {
      return false;
    }
    return LargessGetqtRule.ASK_SALEQT.equalsValue(trantype
        .getFlargessgetqtrule());

  }

  @Override
  public Integer getAskPriceRule() {

    M38TranTypeVO trantype = this.getTranTypeVO();
    if (null == trantype) {
      return AskPriceRule.ASKPRICE_NO.getIntegerValue();
    }
    return trantype.getFaskqtrule();
  }

  @Override
  public void processAskSucessRows(int[] sucessrows, String chgkey) {
    PreOrderCalculator calcultor = new PreOrderCalculator(this.cardPanel);
    calcultor.setChangePrice(UFBoolean.FALSE);
    calcultor.calculate(sucessrows, chgkey);

    IKeyValue keyValue = new CardKeyValue(this.cardPanel);
    for (int i : sucessrows) {
      UFDouble nitemdiscountrate =
          keyValue.getBodyUFDoubleValue(i, PreOrderBVO.NITEMDISCOUNTRATE);
      nitemdiscountrate =
          CalculatorUtil.div(nitemdiscountrate, new UFDouble(100.0));

      UFDouble ndiscountrate =
          keyValue.getBodyUFDoubleValue(i, PreOrderBVO.NDISCOUNTRATE);
      ndiscountrate = CalculatorUtil.div(ndiscountrate, new UFDouble(100.0));

      UFDouble discount =
          CalculatorUtil.multiply(nitemdiscountrate, ndiscountrate);
      int intZkl = MathTool.compareTo(discount, UFDouble.ZERO_DBL);

      if (intZkl != 0) {

        // 主无税净价
        UFDouble norignetprice =
            keyValue.getBodyUFDoubleValue(i, PreOrderBVO.NORIGNETPRICE);
        UFDouble norigprice = CalculatorUtil.div(norignetprice, discount);
        keyValue.setBodyValue(i, PreOrderBVO.NORIGPRICE, norigprice);

        // 主含税净价
        UFDouble norigtaxnetprice =
            keyValue.getBodyUFDoubleValue(i, PreOrderBVO.NORIGTAXNETPRICE);
        UFDouble norigtaxprice = CalculatorUtil.div(norigtaxnetprice, discount);
        keyValue.setBodyValue(i, PreOrderBVO.NORIGTAXPRICE, norigtaxprice);

        // 主本币无税净价
        UFDouble nnetprice =
            keyValue.getBodyUFDoubleValue(i, PreOrderBVO.NNETPRICE);
        UFDouble nprice = CalculatorUtil.div(nnetprice, discount);
        keyValue.setBodyValue(i, PreOrderBVO.NPRICE, nprice);

        // 主本币含税净价
        UFDouble ntaxnetprice =
            keyValue.getBodyUFDoubleValue(i, PreOrderBVO.NTAXNETPRICE);
        UFDouble ntaxprice = CalculatorUtil.div(ntaxnetprice, discount);
        keyValue.setBodyValue(i, PreOrderBVO.NTAXPRICE, ntaxprice);

      }
    }
  }

  @Override
  public void processAskFailRows(int[] failrows) {
  }

  @Override
  public AggregatedValueObject getBillVO() {

    AggregatedValueObject bill =
        this.cardPanel.getBillValueVO(PreOrderVO.class.getName(),
            PreOrderHVO.class.getName(), PreOrderBVO.class.getName());
    return bill;

  }

  @Override
  public boolean isblrgcashflag() {
    return true;
  }
  
  @Override
  public int getSalemode() {
    // TODO Auto-generated method stub
    return 0;
  }
}
