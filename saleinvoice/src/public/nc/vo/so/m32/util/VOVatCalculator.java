package nc.vo.so.m32.util;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.scmpub.parameter.SCMParameterUtils;
import nc.vo.scmpub.vattax.CalculatorVat;
import nc.vo.scmpub.vattax.vo.CalVatParam;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.util.SOSysParaInitUtil;

import nc.itf.scmpub.vattax.ICalculateVat;

/**
 * 销售发票公共VAT合并算法类
 * 
 * @since 6.1
 * @version 2012-11-21 14:46:32
 * @author 冯加彬
 */
public class VOVatCalculator {

  private SaleInvoiceVO invoicevo;

  /**
   * 构造子
   * 
   * @param invoicevo
   */
  public VOVatCalculator(SaleInvoiceVO invoicevo) {
    this.invoicevo = invoicevo;
  }

  /**
   * 重新计算所有行的VAT信息
   */
  public void calVatAll() {
    if (!this.isVatCal()) {
      return;
    }
    CalVatParam vatpara = this.getVatParam();
    ICalculateVat<SaleInvoiceBVO> config = new VOVatCalConfig(this.invoicevo);
    CalculatorVat<SaleInvoiceBVO> vatcal =
        new CalculatorVat<SaleInvoiceBVO>(vatpara, config);
    vatcal.calculateVat(this.invoicevo.getChildrenVO());
  }

  private CalVatParam getVatParam() {
    IRelationForItems item = new RelationItemForCal();
    boolean istaxfirst = this.isTaxPrior();
    CalVatParam vatpara = new CalVatParam(item, null, istaxfirst);
    String[] groupkeys =
        new String[] {
          SaleInvoiceBVO.CTAXCODEID, SaleInvoiceBVO.NTAXRATE,
          SaleInvoiceBVO.FTAXTYPEFLAG, SaleInvoiceBVO.BLARGESSFLAG
        };
    vatpara.addGroupings(groupkeys);
    return vatpara;
  }

  private boolean isTaxPrior() {
    UFBoolean scm13 = null;
    String pk_group = AppContext.getInstance().getPkGroup();
    scm13 = SCMParameterUtils.getSCM13(pk_group);
    if (null == scm13) {
      return false;
    }
    return scm13.booleanValue();
  }

  private boolean isVatCal() {
    IKeyValue keyvalue = new VOKeyValue<SaleInvoiceVO>(this.invoicevo);
    String pk_org = keyvalue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
    UFBoolean so31 = SOSysParaInitUtil.getSO31(pk_org);
    if (null == so31) {
      return false;
    }
    return so31.booleanValue();
  }
}
