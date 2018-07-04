package nc.vo.so.saleinvoice;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by shangguoqiang on 2017-03-03 15:26:05
 */
@XmlRootElement(name = "datarow")//攀钢采购系统发票明细
public class MsgAGCG000003 {
    String invoiceId;   //发票号（主信息主键）  长度:64
    String invoiceNum;   //发票票面号码  长度:64
    String invoiceLineId;   //发票明细号（明细信息主键）  长度:64
    String bpoLineId;   //合同行号  长度:64
    String orderLineId;   //执行单行号  长度:64
    String balanceLineId;   //结算单行号  长度:64
    String itemId;   //物料编码  长度:64
    String itemName;   //物料名称  长度:64
    BigDecimal invoicedQty;   //发票数量  长度:32,3
    String Uom;   //计量单位  长度:64
    BigDecimal netPrice;   //未税单价  长度:32,6
    BigDecimal taxPrice;   //含税单价  长度:32,6
    BigDecimal taxRate;   //税率  长度:32,2
    BigDecimal totNetAmt;   //未税总金额（发票）  长度:32,2
    BigDecimal totAmt;   //含税总金额（发票）  长度:32,2
    BigDecimal totTaxAmt;   //总税额  长度:32,2
    String balanceId;   //结算单号  长度:64
    String bpoId;   //合同号  长度:64
    String orderId;   //执行单号  长度:64
    String invoiceCode;   //发票代码  长度:64
    String invoiceDate;   //开票票面日期  长度:14

    public MsgAGCG000003() {
    }

    public MsgAGCG000003(
            String invoiceId, String invoiceNum, String invoiceLineId, String bpoLineId, String orderLineId, String balanceLineId, String itemId, String itemName, BigDecimal invoicedQty, String Uom, BigDecimal netPrice, BigDecimal taxPrice, BigDecimal taxRate, BigDecimal totNetAmt, BigDecimal totAmt, BigDecimal totTaxAmt, String balanceId, String bpoId, String orderId, String invoiceCode, String invoiceDate
    ) {
        this.invoiceId = invoiceId;
        this.invoiceNum = invoiceNum;
        this.invoiceLineId = invoiceLineId;
        this.bpoLineId = bpoLineId;
        this.orderLineId = orderLineId;
        this.balanceLineId = balanceLineId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.invoicedQty = invoicedQty;
        this.Uom = Uom;
        this.netPrice = netPrice;
        this.taxPrice = taxPrice;
        this.taxRate = taxRate;
        this.totNetAmt = totNetAmt;
        this.totAmt = totAmt;
        this.totTaxAmt = totTaxAmt;
        this.balanceId = balanceId;
        this.bpoId = bpoId;
        this.orderId = orderId;
        this.invoiceCode = invoiceCode;
        this.invoiceDate = invoiceDate;


    }

    @XmlElement(name = "invoiceId")
    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    @XmlElement(name = "invoiceNum")
    public String getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    @XmlElement(name = "invoiceLineId")
    public String getInvoiceLineId() {
        return invoiceLineId;
    }

    public void setInvoiceLineId(String invoiceLineId) {
        this.invoiceLineId = invoiceLineId;
    }

    @XmlElement(name = "bpoLineId")
    public String getBpoLineId() {
        return bpoLineId;
    }

    public void setBpoLineId(String bpoLineId) {
        this.bpoLineId = bpoLineId;
    }

    @XmlElement(name = "orderLineId")
    public String getOrderLineId() {
        return orderLineId;
    }

    public void setOrderLineId(String orderLineId) {
        this.orderLineId = orderLineId;
    }

    @XmlElement(name = "balanceLineId")
    public String getBalanceLineId() {
        return balanceLineId;
    }

    public void setBalanceLineId(String balanceLineId) {
        this.balanceLineId = balanceLineId;
    }

    @XmlElement(name = "itemId")
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @XmlElement(name = "itemName")
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @XmlElement(name = "invoicedQty")
    public BigDecimal getInvoicedQty() {
        return invoicedQty;
    }

    public void setInvoicedQty(BigDecimal invoicedQty) {
        this.invoicedQty = invoicedQty;
    }

    @XmlElement(name = "uom")
    public String getUom() {
        return Uom;
    }

    public void setUom(String Uom) {
        this.Uom = Uom;
    }

    @XmlElement(name = "netPrice")
    public BigDecimal getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(BigDecimal netPrice) {
        this.netPrice = netPrice;
    }

    @XmlElement(name = "taxPrice")
    public BigDecimal getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(BigDecimal taxPrice) {
        this.taxPrice = taxPrice;
    }

    @XmlElement(name = "taxRate")
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    @XmlElement(name = "totNetAmt")
    public BigDecimal getTotNetAmt() {
        return totNetAmt;
    }

    public void setTotNetAmt(BigDecimal totNetAmt) {
        this.totNetAmt = totNetAmt;
    }

    @XmlElement(name = "totAmt")
    public BigDecimal getTotAmt() {
        return totAmt;
    }

    public void setTotAmt(BigDecimal totAmt) {
        this.totAmt = totAmt;
    }

    @XmlElement(name = "totTaxAmt")
    public BigDecimal getTotTaxAmt() {
        return totTaxAmt;
    }

    public void setTotTaxAmt(BigDecimal totTaxAmt) {
        this.totTaxAmt = totTaxAmt;
    }

    @XmlElement(name = "balanceId")
    public String getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(String balanceId) {
        this.balanceId = balanceId;
    }

    @XmlElement(name = "bpoId")
    public String getBpoId() {
        return bpoId;
    }

    public void setBpoId(String bpoId) {
        this.bpoId = bpoId;
    }

    @XmlElement(name = "orderId")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @XmlElement(name = "invoiceCode")
    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    @XmlElement(name = "invoiceDate")
    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }


}

