package nc.vo.so.m30.paravo;

/**
 * 销售订单参照增行参数VO
 * @author zhangby5
 *
 */
public class OrderRefAddLineParaVO {

	/** 
	 * 主组织 
	 */
	private String pk_org;
	
	/**
	 * 客户
	 */
	private String ccustomerid;

	public String getPk_org() {
		return pk_org;
	}

	public void setPk_org(String pk_org) {
		this.pk_org = pk_org;
	}

	public String getCcustomerid() {
		return ccustomerid;
	}

	public void setCcustomerid(String ccustomerid) {
		this.ccustomerid = ccustomerid;
	}
}
