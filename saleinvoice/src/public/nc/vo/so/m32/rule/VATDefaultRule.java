package nc.vo.so.m32.rule;

import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.vat.CustSuppVATCodeQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.OrgVATCodeQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATBDService;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * VAT字段默认值设置 报错客户VAT
 * 
 * @since 6.0
 * @version 2012-2-22 下午04:35:12
 * @author 么贵敬
 */
public class VATDefaultRule {

  private IKeyValue keyValue;

  public VATDefaultRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 设置两个VAT字段
   */
  public void setVatCodeValue() {
    this.setCustvatCode();
    this.setVATCode();
  }

  /**
   * 设置客户VAT码
   */
  public void setCustvatCode() {
    String custid =
        this.keyValue.getHeadStringValue(SaleInvoiceHVO.CINVOICECUSTID);
    String creccountryid =
        this.keyValue.getHeadStringValue(SaleInvoiceHVO.CRECECOUNTRYID);

    CustSuppVATCodeQueryVO vo =
        new CustSuppVATCodeQueryVO(custid, creccountryid);
    Map<CustSuppVATCodeQueryVO, String> custvatcodes =
        VATBDService.queryCustVATCodeM(new CustSuppVATCodeQueryVO[] {
          vo
        });

    this.keyValue.setHeadValue(SaleInvoiceHVO.VCUSTVATCODE,
        custvatcodes.get(vo));
  }

  /**
   * 设置国家VAT字段
   */
  private void setVATCode() {
    String pk_prg = this.keyValue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
    String ctaxcountryid =
        this.keyValue.getHeadStringValue(SaleInvoiceHVO.CTAXCOUNTRYID);
    OrgVATCodeQueryVO vo = new OrgVATCodeQueryVO(pk_prg, ctaxcountryid);
    Map<OrgVATCodeQueryVO, String> custvatcodes =
        VATBDService.queryOrgVATCodeM(new OrgVATCodeQueryVO[] {
          vo
        });

    this.keyValue.setHeadValue(SaleInvoiceHVO.VVATCODE, custvatcodes.get(vo));
  }
}
