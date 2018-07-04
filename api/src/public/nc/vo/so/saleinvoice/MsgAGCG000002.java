package nc.vo.so.saleinvoice;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by shangguoqiang on 2017-03-03 15:25:39
 */
@XmlRootElement(name = "datarow")//攀钢采购系统发票主信息
public class MsgAGCG000002 {
    String invoiceId;   //发票号（主信息主键）  长度:64
    String invoiceNum;   //发票票面号码  长度:64
    String invoiceCode;   //发票代码  长度:64
    String invoiceDate;   //开票票面日期  长度:14
    BigDecimal taxRate;   //税率  长度:32,2
    BigDecimal totNetAmt;   //未税总金额（发票）  长度:32,2
    BigDecimal totAmt;   //含税总金额（发票）  长度:32,2
    BigDecimal totTaxAmt;   //总税额  长度:32,2
    String balanceId;   //结算单号  长度:64
    String bpoId;   //合同号  长度:64
    String orderId;   //执行单号  长度:64

    public MsgAGCG000002() {
    }

    public MsgAGCG000002(
            String invoiceId, String invoiceNum, String invoiceCode, String invoiceDate, BigDecimal taxRate, BigDecimal totNetAmt, BigDecimal totAmt, BigDecimal totTaxAmt, String balanceId, String bpoId, String orderId
    ) {
        this.invoiceId = invoiceId;
        this.invoiceNum = invoiceNum;
        this.invoiceCode = invoiceCode;
        this.invoiceDate = invoiceDate;
        this.taxRate = taxRate;
        this.totNetAmt = totNetAmt;
        this.totAmt = totAmt;
        this.totTaxAmt = totTaxAmt;
        this.balanceId = balanceId;
        this.bpoId = bpoId;
        this.orderId = orderId;


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


}

