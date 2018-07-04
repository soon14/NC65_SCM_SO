package nc.vo.so.saleinvoice;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by shangguoqiang on 2017-03-20 09:38:29
 */
@XmlRootElement(name = "datarow")//采购计价结算单
@XmlType(propOrder = { "bpoId" , "bpoLineId" , "orderId" , "orderLineId" , "balanceId" , "balanceLineId" , "dryMeasure" , "wetMeasure" , "balancePrice" , "balanceUnitPrice" , "taxRate" , "taxPrice" , "taxAmount" , "carNum" , "batchNo" , "itemName" , "itemType" , "itemSpec" , "companyName" , "uom"  })//设置XML中属性顺序
public class MsgCGAG000003 {
    String bpoId;   //采购合同号  长度:64
    String bpoLineId;   //采购合同行号  长度:64
    String orderId;   //执行单号  长度:64
    String orderLineId;   //执行单行号  长度:64
    String balanceId;   //结算单单号  长度:64

    String balanceLineId;   //结算单行号  长度:64
    String dryMeasure;   //干吨数量  长度:64
    BigDecimal wetMeasure;   //湿吨数量  长度:32,6
    BigDecimal balancePrice;   //无税金额  长度:32,6
    BigDecimal balanceUnitPrice;   //无税单价  长度:32,6
    BigDecimal taxRate;   //税率  长度:32,2
    BigDecimal taxPrice;   //税额  长度:32,6
    BigDecimal taxAmount;   //含税金额  长度:32,6
    String carNum;   //车数  长度:64
    String batchNo;   //批次号  长度:64
    String itemName;   //物料名称  长度:64
    String itemType;   //型号  长度:2048
    String itemSpec;   //规格  长度:2048
    String companyName;   //公司名称  长度:512
    String uom;   //计量单位  长度:64

    public MsgCGAG000003() {
    }

    public MsgCGAG000003(
            String bpoId, String bpoLineId, String orderId, String orderLineId, String balanceId, String balanceLineId, String dryMeasure, BigDecimal wetMeasure, BigDecimal balancePrice, BigDecimal balanceUnitPrice, BigDecimal taxRate, BigDecimal taxPrice, BigDecimal taxAmount, String carNum, String batchNo, String itemName, String itemType, String itemSpec, String companyName, String uom
    ) {
        this.bpoId = bpoId;
        this.bpoLineId = bpoLineId;
        this.orderId = orderId;
        this.orderLineId = orderLineId;
        this.balanceId = balanceId;
        this.balanceLineId = balanceLineId;
        this.dryMeasure = dryMeasure;
        this.wetMeasure = wetMeasure;
        this.balancePrice = balancePrice;
        this.balanceUnitPrice = balanceUnitPrice;
        this.taxRate = taxRate;
        this.taxPrice = taxPrice;
        this.taxAmount = taxAmount;
        this.carNum = carNum;
        this.batchNo = batchNo;
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemSpec = itemSpec;
        this.companyName = companyName;
        this.uom = uom;


    }

    @XmlElement(name = "bpoId")
    public String getBpoId() {
        return bpoId;
    }

    public void setBpoId(String bpoId) {
        this.bpoId = bpoId;
    }

    @XmlElement(name = "bpoLineId")
    public String getBpoLineId() {
        return bpoLineId;
    }

    public void setBpoLineId(String bpoLineId) {
        this.bpoLineId = bpoLineId;
    }

    @XmlElement(name = "orderId")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @XmlElement(name = "orderLineId")
    public String getOrderLineId() {
        return orderLineId;
    }

    public void setOrderLineId(String orderLineId) {
        this.orderLineId = orderLineId;
    }

    @XmlElement(name = "balanceId")
    public String getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(String balanceId) {
        this.balanceId = balanceId;
    }

    @XmlElement(name = "balanceLineId")
    public String getBalanceLineId() {
        return balanceLineId;
    }

    public void setBalanceLineId(String balanceLineId) {
        this.balanceLineId = balanceLineId;
    }

    @XmlElement(name = "dryMeasure")
    public String getDryMeasure() {
        return dryMeasure;
    }

    public void setDryMeasure(String dryMeasure) {
        this.dryMeasure = dryMeasure;
    }

    @XmlElement(name = "wetMeasure")
    public BigDecimal getWetMeasure() {
        return wetMeasure;
    }

    public void setWetMeasure(BigDecimal wetMeasure) {
        this.wetMeasure = wetMeasure;
    }

    @XmlElement(name = "balancePrice")
    public BigDecimal getBalancePrice() {
        return balancePrice;
    }

    public void setBalancePrice(BigDecimal balancePrice) {
        this.balancePrice = balancePrice;
    }

    @XmlElement(name = "balanceUnitPrice")
    public BigDecimal getBalanceUnitPrice() {
        return balanceUnitPrice;
    }

    public void setBalanceUnitPrice(BigDecimal balanceUnitPrice) {
        this.balanceUnitPrice = balanceUnitPrice;
    }

    @XmlElement(name = "taxRate")
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    @XmlElement(name = "taxPrice")
    public BigDecimal getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(BigDecimal taxPrice) {
        this.taxPrice = taxPrice;
    }

    @XmlElement(name = "taxAmount")
    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    @XmlElement(name = "carNum")
    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    @XmlElement(name = "batchNo")
    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    @XmlElement(name = "itemName")
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @XmlElement(name = "itemType")
    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    @XmlElement(name = "itemSpec")
    public String getItemSpec() {
        return itemSpec;
    }

    public void setItemSpec(String itemSpec) {
        this.itemSpec = itemSpec;
    }

    @XmlElement(name = "companyName")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @XmlElement(name = "uom")
    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }


}

