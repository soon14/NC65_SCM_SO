package nc.vo.so.m30.gfmsg;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

//子表
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DT_CG_GM_HTMX_ITEM", propOrder = {
    "srcordergm",
    "banfn",
    "bnfpo",
    "menge",
    "epein",
    "netpr",
    "badat",
    "mwskz",
    "zzfield1",
    "zzfield2",
    "zzfield3",
    "zzfield4",
    "zzfield5",
    "zzfield6",
    "zzfield7",
    "zzfield8",
    "zzfield9",
    "zzfield10"
})
public class DTCGGMHTMXITEM {

    @XmlElement(name = "SRC_ORDER_GM", required = true)
    protected String srcordergm;
    /**
    *采购申请号
    */
    @XmlElement(name = "BANFN", required = true)
    protected String banfn;
    /**
     * 采购申请行号
     */
    @XmlElement(name = "BNFPO", required = true)
    protected String bnfpo;
    @XmlElement(name = "MENGE", required = true)
    protected BigDecimal menge;
    /**
     * 价格单位
     */
    @XmlElement(name = "EPEIN", required = true)
    protected BigInteger epein;
    @XmlElement(name = "NETPR", required = true)
    protected BigDecimal netpr;
    @XmlElement(name = "BADAT", required = true)
    protected String badat;
    @XmlElement(name = "MWSKZ", required = true)
    protected String mwskz;
    /**
     * 自定义项1
     */
    @XmlElement(name = "ZZ_FIELD1", required = true)
    protected String zzfield1;
    @XmlElement(name = "ZZ_FIELD2", required = true)
    protected String zzfield2;
    @XmlElement(name = "ZZ_FIELD3", required = true)
    protected String zzfield3;
    @XmlElement(name = "ZZ_FIELD4", required = true)
    protected String zzfield4;
    @XmlElement(name = "ZZ_FIELD5", required = true)
    protected String zzfield5;
    @XmlElement(name = "ZZ_FIELD6", required = true)
    protected String zzfield6;
    @XmlElement(name = "ZZ_FIELD7", required = true)
    protected String zzfield7;
    @XmlElement(name = "ZZ_FIELD8", required = true)
    protected String zzfield8;
    @XmlElement(name = "ZZ_FIELD9", required = true)
    protected String zzfield9;
    @XmlElement(name = "ZZ_FIELD10", required = true)
    protected String zzfield10;

    /**
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSRCORDERGM() {
        return srcordergm;
    }

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSRCORDERGM(String value) {
        this.srcordergm = value;
    }

    /**
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBANFN() {
        return banfn;
    }

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBANFN(String value) {
        this.banfn = value;
    }

    /**
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBNFPO() {
        return bnfpo;
    }

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBNFPO(String value) {
        this.bnfpo = value;
    }

    /**
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMENGE() {
        return menge;
    }

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMENGE(BigDecimal value) {
        this.menge = value;
    }

    /**
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEPEIN() {
        return epein;
    }

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEPEIN(BigInteger value) {
        this.epein = value;
    }

    /**
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNETPR() {
        return netpr;
    }

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNETPR(BigDecimal value) {
        this.netpr = value;
    }

    /**
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBADAT() {
        return badat;
    }

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBADAT(String value) {
        this.badat = value;
    }

    /**
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMWSKZ() {
        return mwskz;
    }

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMWSKZ(String value) {
        this.mwskz = value;
    }

    /**
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZZFIELD1() {
        return zzfield1;
    }

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZZFIELD1(String value) {
        this.zzfield1 = value;
    }

    /**
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZZFIELD2() {
        return zzfield2;
    }

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZZFIELD2(String value) {
        this.zzfield2 = value;
    }

    /**
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZZFIELD3() {
        return zzfield3;
    }

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZZFIELD3(String value) {
        this.zzfield3 = value;
    }

    /**
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZZFIELD4() {
        return zzfield4;
    }

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZZFIELD4(String value) {
        this.zzfield4 = value;
    }

    /**
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZZFIELD5() {
        return zzfield5;
    }

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZZFIELD5(String value) {
        this.zzfield5 = value;
    }

    /**
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZZFIELD6() {
        return zzfield6;
    }

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZZFIELD6(String value) {
        this.zzfield6 = value;
    }

    /**
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZZFIELD7() {
        return zzfield7;
    }

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZZFIELD7(String value) {
        this.zzfield7 = value;
    }

    /**
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZZFIELD8() {
        return zzfield8;
    }

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZZFIELD8(String value) {
        this.zzfield8 = value;
    }

    /**
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZZFIELD9() {
        return zzfield9;
    }

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZZFIELD9(String value) {
        this.zzfield9 = value;
    }

    /**
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZZFIELD10() {
        return zzfield10;
    }

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZZFIELD10(String value) {
        this.zzfield10 = value;
    }

}
