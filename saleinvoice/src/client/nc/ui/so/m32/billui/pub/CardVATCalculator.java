package nc.ui.so.m32.billui.pub;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.parameter.SCMParameterUtils;
import nc.vo.scmpub.vattax.vo.CalVatFieldValues;
import nc.vo.scmpub.vattax.vo.CalVatParam;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.util.SOSysParaInitUtil;

import nc.itf.scmpub.vattax.ICalculateVat;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.scmpub.vattax.calvat.CalculatorVatClient;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * 前台的VAT合并计算规则类
 * 
 * @since 6.1
 * @version 2012-11-20 17:51:28
 * @author 冯加彬
 */
public class CardVATCalculator {

  private BillCardPanel cardPanel;

  private Map<String, UFBoolean> isvatcalmap;

  private Map<String, UFBoolean> istaxpriormap;

  private IKeyValue keyvalue;

  /**
   * 构造子
   * 
   * @param cardPanel
   */
  public CardVATCalculator(BillCardPanel cardPanel) {
    this.cardPanel = cardPanel;
    this.keyvalue = new CardKeyValue(this.cardPanel);
    // 初始化参数(效率优化)
    String pk_group = AppContext.getInstance().getPkGroup();
    if (istaxpriormap != null && istaxpriormap.containsKey(pk_group)) {
      return;
    }
    UFBoolean scm13 = SCMParameterUtils.getSCM13(pk_group);
    this.istaxpriormap = new HashMap<String, UFBoolean>();
    this.istaxpriormap.put(pk_group, scm13);

    String pk_org = keyvalue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
    if (isvatcalmap != null && isvatcalmap.containsKey(pk_org)) {
      return;
    }
    UFBoolean so31 = SOSysParaInitUtil.getSO31(pk_org);
    this.isvatcalmap = new HashMap<String, UFBoolean>();
    this.isvatcalmap.put(pk_org, so31);
  }

  /**
   * 编辑非VAT字段后重算税额
   * 
   * @param row
   * @param editkey
   */
  public void calculateVatWhenEditNoVat(int row, String editkey) {
    String pk_org = keyvalue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
    UFBoolean isvatcal = isvatcalmap.get(pk_org);
    if (isvatcal != null && isvatcal.booleanValue()) {
      CalVatParam vatpara = this.getVatParam(editkey);
      CardVatCalConfig config = new CardVatCalConfig(this.cardPanel);
      CalculatorVatClient<SaleInvoiceBVO> calClient =
          new CalculatorVatClient<SaleInvoiceBVO>(this.cardPanel, vatpara,
              config);
      calClient.calVatAddOrUpdateNoVat(row, SaleInvoiceBVO.class);
    }
  }

  /**
   * 新增行后重算税额
   * 
   * @param row
   */
  public void calculateVatWhenAddLine(int row) {
    String pk_org = keyvalue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
    UFBoolean isvatcal = isvatcalmap.get(pk_org);
    if (isvatcal != null && isvatcal.booleanValue()) {
      CalVatParam vatpara = this.getVatParam(null);
      CardVatCalConfig config = new CardVatCalConfig(this.cardPanel);
      CalculatorVatClient<SaleInvoiceBVO> calClient =
          new CalculatorVatClient<SaleInvoiceBVO>(this.cardPanel, vatpara,
              config);
      calClient.calVatAddOrUpdateNoVat(row, SaleInvoiceBVO.class);
    }
  }

  /**
   * 编辑VAT字段后重算税额
   * 
   * @param row
   * @param oldValue
   */
  public void calculateVatWhenEditVat(int row, CalVatFieldValues oldValue) {
    String pk_org = keyvalue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
    UFBoolean isvatcal = isvatcalmap.get(pk_org);
    if (isvatcal != null && isvatcal.booleanValue()) {
      CalVatParam vatpara = this.getVatParam(null);
      CardVatCalConfig config = new CardVatCalConfig(this.cardPanel);
      CalculatorVatClient<SaleInvoiceBVO> calClient =
          new CalculatorVatClient<SaleInvoiceBVO>(this.cardPanel, vatpara,
              config);
      calClient.calVatUpdateVat(row, oldValue, SaleInvoiceBVO.class);
    }
  }

  /**
   * 重新计算所有行的VAT信息
   */
  public void calVatAll() {
    String pk_org = keyvalue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
    UFBoolean isvatcal = isvatcalmap.get(pk_org);
    if (isvatcal != null && isvatcal.booleanValue()) {
      CalVatParam vatpara = this.getVatParam(null);
      ICalculateVat<SaleInvoiceBVO> config =
          new CardVatCalConfig(this.cardPanel);
      CalculatorVatClient<SaleInvoiceBVO> calClient =
          new CalculatorVatClient<SaleInvoiceBVO>(this.cardPanel, vatpara,
              config);
      calClient.calVatAll(SaleInvoiceBVO.class);
    }
  }

  /**
   * 删行时重新计算VAT合并信息
   * 
   * @param oldValue
   */
  public void calVatWhenDeleteLine(CalVatFieldValues oldValue) {
    String pk_org = keyvalue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
    UFBoolean isvatcal = isvatcalmap.get(pk_org);
    if (isvatcal != null && isvatcal.booleanValue()) {
      CalVatParam vatpara = this.getVatParam(null);
      CardVatCalConfig config = new CardVatCalConfig(this.cardPanel);
      CalculatorVatClient<SaleInvoiceBVO> calClient =
          new CalculatorVatClient<SaleInvoiceBVO>(this.cardPanel, vatpara,
              config);
      calClient.calVatDelete(oldValue, SaleInvoiceBVO.class);
    }
  }

  /**
   * 获得指定行的目前界面上的VAT字段信息，需要自己填充对应字段的oldvalue
   * 
   * @param row
   * @return CalVatFieldValues
   */
  public CalVatFieldValues getVatFieldValues(int row) {
    CalVatFieldValues vatvalue = new CalVatFieldValues();
    IKeyValue keyvalue = new CardKeyValue(this.cardPanel);
    // 赠品
    UFBoolean largess =
        keyvalue.getBodyUFBooleanValue(row, SaleInvoiceBVO.BLARGESSFLAG);
    vatvalue.setBlargessflag(largess);
    // 税码
    String taxcode =
        keyvalue.getBodyStringValue(row, SaleInvoiceBVO.CTAXCODEID);
    vatvalue.setCtaxcodeid(taxcode);

    // 购销类型
    // Integer buysellflag =
    // keyvalue.getHeadIntegerValue(SaleInvoiceHVO.FBUYSELLFLAG);
    // vatvalue.setFbuysellflag(buysellflag);

    // 扣税类别
    Integer taxtypeflag =
        keyvalue.getBodyIntegerValue(row, SaleInvoiceBVO.FTAXTYPEFLAG);
    vatvalue.setFtaxtypeflag(taxtypeflag);
    // 税率
    UFDouble taxrate =
        keyvalue.getBodyUFDoubleValue(row, SaleInvoiceBVO.NTAXRATE);
    vatvalue.setNtaxrate(taxrate);

    return vatvalue;
  }

  private CalVatParam getVatParam(String editkey) {
    IRelationForItems item = new RelationItemForCal();
    String pk_org = keyvalue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
    UFBoolean istaxfirst = istaxpriormap.get(pk_org);
    CalVatParam vatpara =
        new CalVatParam(item, editkey,
            istaxfirst == null ? UFBoolean.FALSE.booleanValue()
                : istaxfirst.booleanValue());
    String[] groupkeys =
        new String[] {
          SaleInvoiceBVO.CTAXCODEID, SaleInvoiceBVO.NTAXRATE,
          SaleInvoiceBVO.FTAXTYPEFLAG, SaleInvoiceBVO.BLARGESSFLAG
        };
    vatpara.addGroupings(groupkeys);
    return vatpara;
  }

}
