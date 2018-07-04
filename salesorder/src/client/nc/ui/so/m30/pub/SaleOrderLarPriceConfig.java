package nc.ui.so.m30.pub;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.so.pub.findprice.IFindPriceConfig;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.largessprice.ILargessPriceConfig;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.enumeration.LargessGetqtRule;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class SaleOrderLarPriceConfig implements ILargessPriceConfig {

  private BillCardPanel cardPanel;

  private M30TranTypeVO m30trantypevo;

  public SaleOrderLarPriceConfig(BillCardPanel cardPanel) {
    this.cardPanel = cardPanel;
  }

  @Override
  public Integer getLargessPriceMode() {

    M30TranTypeVO trantype = this.getTranTypeVO();
    if (null == trantype) {
      return LargessGetqtRule.ZERO_QT.getIntegerValue();
    }
    return trantype.getFlargessgetqtrule();

  }

  @Override
  public IFindPriceConfig getFindPriceConfig() {
    M30TranTypeVO trantype = this.getTranTypeVO();
    SaleOrderFindPriceConfig findconfig =
        new SaleOrderFindPriceConfig(this.cardPanel, trantype);
    return findconfig;
  }

  @Override
  public void processAfterGetPrice(int[] rows, String chgkey) {
    M30TranTypeVO trantype = this.getTranTypeVO();
    SaleOrderCalculator calcultor = new SaleOrderCalculator(this.cardPanel);
    calcultor.setTranTypeVO(trantype);
    calcultor.setChangePrice(UFBoolean.TRUE);
    calcultor.calculate(rows, chgkey);
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

}
