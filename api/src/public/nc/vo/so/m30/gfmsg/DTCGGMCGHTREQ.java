
package nc.vo.so.m30.gfmsg;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DT_CG_GM_CGHT_REQ", propOrder = {
    "cghttt",
    "htmx"
})
public class DTCGGMCGHTREQ {

    @XmlElement(name = "CGHTTT", required = true)
    protected DTCGGMCGHTREQITEM cghttt;
    @XmlElement(name = "HTMX", required = true)
    protected List<DTCGGMHTMXITEM> htmx;

    /**
     * 
     * @return
     *     possible object is
     *     {@link DTCGGMCGHTREQITEM }
     *     
     */
    public DTCGGMCGHTREQITEM getCGHTTT() {
        return cghttt;
    }

    /**
     * 
     * @param value
     *     allowed object is
     *     {@link DTCGGMCGHTREQITEM }
     *     
     */
    public void setCGHTTT(DTCGGMCGHTREQITEM value) {
        this.cghttt = value;
    }

    /**
     * Gets the value of the htmx property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the htmx property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHTMX().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTCGGMHTMXITEM }
     * 
     * 
     */
    public List<DTCGGMHTMXITEM> getHTMX() {
        if (htmx == null) {
            htmx = new ArrayList<DTCGGMHTMXITEM>();
        }
        return this.htmx;
    }

	/**
	 * @param htmx ÒªÉèÖÃµÄ htmx
	 */
	public void setHtmx(List<DTCGGMHTMXITEM> htmx) {
		this.htmx = htmx;
	}

}
