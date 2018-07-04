package nc.vo.so.entry;

import nc.vo.ic.onhand.entity.OnhandDlgHeadVO;
import nc.vo.pub.JavaType;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;

/**
 * 
 * @author zhangby5
 *
 */
public class SOOnhandDlgHeadVO extends OnhandDlgHeadVO{
	/**
	 * 单据号
	 */
    public static final String VBILLCODE = "vbillcode";
    
    /**
     * 行号
     */
    public static final String CROWNO = "crowno";
    
    /**
     * 主单位
     */
    public static final String CUNITID = "cunitid";
    
    private String[] fixFields = {CROWNO, VBILLCODE,CUNITID};
    

    private JavaType[] fixFieldJavaTypes = {JavaType.String,JavaType.String,JavaType.String};
    
	public String getVbillcode() {
		return VBILLCODE;
	}

	public String getCrowno() {
		return CROWNO;
	}
	
	public String getCunitid() {
		return CUNITID;
	}
	
	public void setVbillcode(String vbillcode){
		this.setFixMappingFieldValue(VBILLCODE, vbillcode);
	}
	
	public void setCrowno(String crowno){
		this.setFixMappingFieldValue(CROWNO, crowno);
	}
	
	public void setCunitid(String cunitid){
		this.setFixMappingFieldValue(CUNITID, cunitid);
	}

	@Override
    public void addFixAttributes(DataViewMeta dataViewMeta) {
    	super.addFixAttributes(dataViewMeta);
        if (this.fixFields != null) {
            for (int i = 0; i < fixFields.length; i++) {
                dataViewMeta.add(DynamicAttribute.create(fixFields[i], fixFieldJavaTypes[i]));
            }
        }
    }
}
