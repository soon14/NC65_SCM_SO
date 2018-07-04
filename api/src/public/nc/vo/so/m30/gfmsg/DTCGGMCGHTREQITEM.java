
package nc.vo.so.m30.gfmsg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

//主表
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DT_CG_GM_CGHT_REQ_ITEM", propOrder = {
    "srcordergm",
    "lifnr",
    "burks",
    "ekgrp",
    "ekorg",
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
public class DTCGGMCGHTREQITEM {

    @XmlElement(name = "SRC_ORDER_GM", required = true)
    protected String srcordergm;
    /*
     * 供应商编码
     */
    @XmlElement(name = "LIFNR", required = true)
    protected String lifnr;
    @XmlElement(name = "BURKS", required = true)
    protected String burks;
    /*
     * 集团主键
     */
    @XmlElement(name = "EKGRP", required = true)
    protected String ekgrp;
    /*
     * 组织主键
     */
    @XmlElement(name = "EKORG", required = true)
    protected String ekorg;
    /*
     * 自定义项
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
    public String getLIFNR() {
        return lifnr;
    }

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLIFNR(String value) {
        this.lifnr = value;
    }

    /**
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBURKS() {
        return burks;
    }

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBURKS(String value) {
        this.burks = value;
    }

    /**
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEKGRP() {
        return ekgrp;
    }

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEKGRP(String value) {
        this.ekgrp = value;
    }

    /**
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEKORG() {
        return ekorg;
    }

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEKORG(String value) {
        this.ekorg = value;
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
