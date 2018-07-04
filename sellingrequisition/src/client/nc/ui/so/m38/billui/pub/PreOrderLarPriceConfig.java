package nc.ui.so.m38.billui.pub;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m38trantype.IM38TranTypeService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.so.pub.findprice.IFindPriceConfig;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.largessprice.ILargessPriceConfig;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38trantype.entity.M38TranTypeVO;
import nc.vo.so.pub.enumeration.LargessGetqtRule;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class PreOrderLarPriceConfig implements ILargessPriceConfig {

  private BillCardPanel cardPanel;

  private M38TranTypeVO m38trantypevo;

  public PreOrderLarPriceConfig(
      BillCardPanel cardPanel) {
    this.cardPanel = cardPanel;
  }

  @Override
  public Integer getLargessPriceMode() {

    M38TranTypeVO trantype = this.getTranTypeVO();
    if (null == trantype) {
      return LargessGetqtRule.ZERO_QT.getIntegerValue();
    }
    return trantype.getFlargessgetqtrule();

  }

  @Override
  public IFindPriceConfig getFindPriceConfig() {
    PreOrderFindPriceConfig findconfig =
        new PreOrderFindPriceConfig(this.cardPanel);
    return findconfig;
  }

  @Override
  public void processAfterGetPrice(int[] rows, String chgkey) {
    M38TranTypeVO trantype = this.getTranTypeVO();
    PreOrderCalculator calcultor = new PreOrderCalculator(this.cardPanel);
    calcultor.setTranTypeVO(trantype);
    calcultor.setChangePrice(UFBoolean.TRUE);
    calcultor.calculate(rows, chgkey);
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

}
