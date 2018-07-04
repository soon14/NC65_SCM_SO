package nc.ui.so.salequotation.findprice;

import java.util.Arrays;

import nc.itf.scmpub.reference.uap.para.SysParaInitQuery;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.salequotation.pub.SalequoCalculator;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m4310trantype.entity.M4310TranTypeVO;
import nc.vo.so.pub.enumeration.PriceDiscountType;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.FindHistoryPriceParameter;
import nc.vo.so.salequotation.entity.FindPriceParaFactory;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

/**
 * 历史报价询价
 *
 * @author chenyyb
 * @since 6.0
 * @time 2010-8-2 上午10:45:18
 *
 */
public class HistoryPriceFinder extends AbstractPriceFinder {

  private final M4310TranTypeVO trantypeVO;

  public HistoryPriceFinder(M4310TranTypeVO trantypeVO) {
    this.trantypeVO = trantypeVO;
  }

  /**
   * 填充询历史报价参数
   *
   * @param cardPanel
   * @param startRow
   * @param rows
   * @param pk_saleorg
   * @param pk_group
   * @return
   */
  private FindHistoryPriceParameter[] createFindHistoryPriceParameters(
      BillCardPanel cardPanel, int[] selectedRows, String pk_saleorg,
      String pk_group) {
    AggSalequotationHVO data =
        (AggSalequotationHVO) cardPanel.getBillValueVO(
            AggSalequotationHVO.class.getName(),
            SalequotationHVO.class.getName(), SalequotationBVO.class.getName());
    return FindPriceParaFactory.getInstance().createFindHistoryPriceParameters(
        data, selectedRows, pk_saleorg, pk_group);
  }

  private void fillHistoryPriceForPanel(BillCardPanel cardPanel,
      int[] selectedRows, UFDouble[] price) {
    for (int i = 0; i < selectedRows.length; i++) {
      // int row = startRow + i;
      int row = selectedRows[i];
      SalequoCalculator calculator = this.getSalequoCalculator(cardPanel);
      if (null == price[i]) {
        cardPanel.setBodyValueAt(null, row, SalequotationBVO.NQTORIGTAXPRICE);
        cardPanel.setBodyValueAt(null, row, SalequotationBVO.NQTORIGTAXNETPRC);
        cardPanel.setBodyValueAt(null, row, SalequotationBVO.NQTORIGPRICE);
        cardPanel.setBodyValueAt(null, row, SalequotationBVO.NQTORIGNETPRICE);

        cardPanel.setBodyValueAt(null, row, SalequotationBVO.NORIGNETPRICE);
        cardPanel.setBodyValueAt(null, row, SalequotationBVO.NORIGPRICE);
        cardPanel.setBodyValueAt(null, row, SalequotationBVO.NORIGTAXNETPRICE);
        cardPanel.setBodyValueAt(null, row, SalequotationBVO.NORIGTAXPRICE);

        cardPanel.setBodyValueAt(null, row, SalequotationBVO.NORIGTAXMNY);
        cardPanel.setBodyValueAt(null, row, SalequotationBVO.NORIGMNY);
      }
      // 含税价格
      else if (SysParaInitQuery.
          getParaBoolean(this.getPk_group(), "SCM13") == UFBoolean.TRUE) {
        cardPanel.setBodyValueAt(price[i], row,
            SalequotationBVO.NQTORIGTAXNETPRC);
        calculator.calculate(new int[]{row}, SalequotationBVO.NQTORIGTAXNETPRC);
      }
      else {
        cardPanel.setBodyValueAt(price[i], row,
            SalequotationBVO.NQTORIGNETPRICE);
        calculator.calculate(new int[]{row}, SalequotationBVO.NQTORIGNETPRICE);
      }
    }
  }

  @Override
  public void findPrice(CardBodyAfterEditEvent e, int rows) {


    if (SalequotationBVO.PK_MATERIAL_V.equals(e.getKey())
        || SalequotationBVO.CASTUNITID.equals(e.getKey())
        || SalequotationBVO.PK_QUALITYLEVEL.equals(e.getKey())
        || SalequotationBVO.PK_AREACL.equals(e.getKey())) {
      int[] selectedRows = new int[rows];
      for (int i = 0; i < rows; i++) {
        selectedRows[i] = e.getRow() + i;
      }
      if (this.ifFindHistoryPrice(e.getBillCardPanel(), e.getKey())) {
        FindHistoryPriceParameter[] findHistoryPriceParameter =
            this.createFindHistoryPriceParameters(e.getBillCardPanel(),
                selectedRows, e.getContext().getPk_org(), e.getContext()
                    .getPk_group());
        try {
          UFDouble[] price =
              this.getFindPriceService().findHistoryPrice(
                  findHistoryPriceParameter, this.trantypeVO);
          this.fillHistoryPriceForPanel(e.getBillCardPanel(), selectedRows,
              price);
        }
        catch (BusinessException e1) {
          ExceptionUtils.wrappException(e1);
        }
      }
      else {
        //编辑表体触发询价字段，如果不满足询价执行条件，清空表体行单价
        for (int row : selectedRows) {
          e.getBillCardPanel().setBodyValueAt(
              null, row, SalequotationBVO.NORIGNETPRICE);
          e.getBillCardPanel().setBodyValueAt(
              null, row, SalequotationBVO.NORIGPRICE);
          e.getBillCardPanel().setBodyValueAt(
              null, row, SalequotationBVO.NORIGTAXPRICE);
          e.getBillCardPanel().setBodyValueAt(
              null, row, SalequotationBVO.NORIGTAXNETPRICE);
          e.getBillCardPanel().setBodyValueAt(
              null, row, SalequotationBVO.NORIGTAXMNY);
          e.getBillCardPanel().setBodyValueAt(
              null, row, SalequotationBVO.NORIGMNY);
        }
      }
    }

  }

  @Override
  public void findPrice(CardHeadTailAfterEditEvent e) {

    String[] keys =
        new String[] {
          SalequotationHVO.PK_CUSTOMER, SalequotationHVO.PK_CHANNELTYPE,
          SalequotationHVO.PK_CURRTYPE, SalequotationHVO.PK_BALATYPE,
          SalequotationHVO.DQUOTEDATE, SalequotationHVO.VTRANTYPE,
          SalequotationHVO.PK_DEPT_V, SalequotationHVO.CEMPLOYEEID,
          SalequotationHVO.PK_PAYTERM, SalequotationHVO.CSENDTYPEID
        };
    if (Arrays.asList(keys).contains(e.getKey())) {
      int[] selectedRows =
          new BodySelectedRowsGetter(e.getBillCardPanel()).getAllRows();
      if (this.ifFindHistoryPrice(e.getBillCardPanel(), e.getKey())) {
        FindHistoryPriceParameter[] findHistoryPriceParameter =
            this.createFindHistoryPriceParameters(e.getBillCardPanel(),
                selectedRows, e.getContext().getPk_org(), e.getContext()
                    .getPk_group());
        try {
          UFDouble[] price =
              this.getFindPriceService().findHistoryPrice(
                  findHistoryPriceParameter, this.trantypeVO);
          this.fillHistoryPriceForPanel(e.getBillCardPanel(), selectedRows,
              price);
        }
        catch (BusinessException e1) {
          ExceptionUtils.wrappException(e1);
        }
      }
      else {
        //编辑表头触发询价字段，如果不满足询价执行条件，清空表体行单价
        for (int row : selectedRows) {
          e.getBillCardPanel().setBodyValueAt(
              null, row, SalequotationBVO.NORIGNETPRICE);
          e.getBillCardPanel().setBodyValueAt(
              null, row, SalequotationBVO.NORIGPRICE);
          e.getBillCardPanel().setBodyValueAt(
              null, row, SalequotationBVO.NORIGTAXPRICE);
          e.getBillCardPanel().setBodyValueAt(
              null, row, SalequotationBVO.NORIGTAXNETPRICE);
          e.getBillCardPanel().setBodyValueAt(
              null, row, SalequotationBVO.NORIGTAXMNY);
          e.getBillCardPanel().setBodyValueAt(
              null, row, SalequotationBVO.NORIGMNY);
        }
      }
    }
  }

  private SalequoCalculator getSalequoCalculator(BillCardPanel cardPanel) {
    SalequoCalculator calculator = new SalequoCalculator(cardPanel);
    // 设置调单价方式
    boolean isChgPriceOrDiscount = false;
    String modifymny = this.trantypeVO.getFmodifymny();
    if (PriceDiscountType.PRICETYPE.getStringValue().equals(modifymny)) {
      isChgPriceOrDiscount = true;
    }
    calculator.setIsChgPriceOrDiscount(isChgPriceOrDiscount);
    return calculator;
  }

  private boolean ifFindHistoryPrice(BillCardPanel cardPanel, String itemKey) {
    boolean flag = true;
    //历史报价默认匹配规则非空：物料+单位+币种+销售组织，以及报价日期，报价单类型
    flag &= this.ifHeadItemValueNotNull(cardPanel, SalequotationHVO.VTRANTYPE);
    flag &= this.ifHeadItemValueNotNull(cardPanel, SalequotationHVO.DQUOTEDATE);
//    flag &=
//        this.ifHeadItemValueNotNull(cardPanel, SalequotationHVO.PK_CUSTOMER);
    flag &=
        this.ifHeadItemValueNotNull(cardPanel, SalequotationHVO.PK_CURRTYPE);
    return flag && this.ifMatchRule(itemKey);
  }

  private boolean ifMatchRule(String itemKey) {
    boolean flag = false;
    //编辑任一历史报价匹配规则默认必选项以及勾选可选项，开始询价
    if (SalequotationHVO.PK_CUSTOMER.equals(itemKey)) {
      flag = this.trantypeVO.getBcustmatrule().booleanValue();
    }
    else if (SalequotationHVO.PK_CHANNELTYPE.equals(itemKey)) {
      flag = this.trantypeVO.getBchanmatrule().booleanValue();
    }
    else if (SalequotationHVO.PK_CURRTYPE.equals(itemKey)) {
      flag = this.trantypeVO.getBcurrtypematrule().booleanValue();
    }
    else if (SalequotationHVO.PK_BALATYPE.equals(itemKey)) {
      flag = this.trantypeVO.getBbalatypematrule().booleanValue();
    }
    else if (SalequotationHVO.VTRANTYPE.equals(itemKey)) {
      flag = this.trantypeVO.getBquotypematrule().booleanValue();
    }
    else if (SalequotationHVO.PK_DEPT_V.equals(itemKey)) {
      flag = this.trantypeVO.getBdeptmatrule().booleanValue();
    }
    else if (SalequotationHVO.CEMPLOYEEID.equals(itemKey)) {
      flag = this.trantypeVO.getBempmatrule().booleanValue();
    }
    else if (SalequotationHVO.PK_PAYTERM.equals(itemKey)) {
      flag = this.trantypeVO.getBpaytermmatrule().booleanValue();
    }
    else if (SalequotationHVO.CSENDTYPEID.equals(itemKey)) {
      flag = this.trantypeVO.getBsendtypematrule().booleanValue();
    }
    else if (SalequotationBVO.CASTUNITID.equals(itemKey)) {
      flag = this.trantypeVO.getBunitmatrule().booleanValue();
    }
    else if (SalequotationBVO.PK_QUALITYLEVEL.equals(itemKey)) {
      flag = this.trantypeVO.getBquallevelmatrule().booleanValue();
    }
    else if (SalequotationBVO.PK_AREACL.equals(itemKey)) {
      flag = this.trantypeVO.getBareamatrule().booleanValue();
    }
    else if (SalequotationBVO.PK_MATERIAL_V.equals(itemKey)
        || SalequotationHVO.DQUOTEDATE.equals(itemKey)
        || SalequotationHVO.PK_CURRTYPE.equals(itemKey)
        || SalequotationBVO.CASTUNITID.equals(itemKey)
        || SalequotationHVO.VTRANTYPE.equals(itemKey)
        || SalequotationHVO.DQUOTEDATE.equals(itemKey)) {
      flag = true;
    }
    return flag;
  }

}
