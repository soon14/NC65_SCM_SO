package nc.vo.so.custmatrel.paravo;


public class CompareParaVO {

	private String[] orgList;

	private String[] matBase;

	private String[] matSale;

	private String[] material;

	private String[] custBase;

	private String[] custSale;

	private String[] custom;
	
	private String[] exclude;
	
	private String[] vnote;
	
	private String[] custmatelbid;

	/**
	 * 获取这些需要比较的字段的值
	 * 
	 * @param matBase
	 * @param matSale
	 * @param material
	 * @param custBase
	 * @param custSale
	 * @param custom
	 * @return
	 */
	public CompareParaVO getCompareParaVO(String[] orgList, String[] matBase,
			String[] matSale, String[] material, String[] custBase,
			String[] custSale, String[] custom, String[] exclude, String[] vnote,String[] custmatelbid) {
		CompareParaVO compareParaVO = new CompareParaVO();
		compareParaVO.setOrgList(orgList);
		compareParaVO.setCustBase(custBase);
		compareParaVO.setCustom(custom);
		compareParaVO.setCustSale(custSale);
		compareParaVO.setMatBase(matBase);
		compareParaVO.setMaterial(material);
		compareParaVO.setMatSale(matSale);
		compareParaVO.setExclude(exclude);
		compareParaVO.setVnote(vnote);
		compareParaVO.setCustmatelbid(custmatelbid);
		return compareParaVO;
	}

	public String[] getOrgList() {
		return orgList;
	}

	public void setOrgList(String[] orgList) {
		this.orgList = orgList;
	}

	/**
	 * @return the matBase
	 */
	public String[] getMatBase() {
		return matBase;
	}

	/**
	 * @param matBase
	 *            the matBase to set
	 */
	public void setMatBase(String[] matBase) {
		this.matBase = matBase;
	}

	/**
	 * @return the matSale
	 */
	public String[] getMatSale() {
		return matSale;
	}

	/**
	 * @param matSale
	 *            the matSale to set
	 */
	public void setMatSale(String[] matSale) {
		this.matSale = matSale;
	}

	/**
	 * @return the material
	 */
	public String[] getMaterial() {
		return material;
	}

	/**
	 * @param material
	 *            the material to set
	 */
	public void setMaterial(String[] material) {
		this.material = material;
	}

	/**
	 * @return the custBase
	 */
	public String[] getCustBase() {
		return custBase;
	}

	/**
	 * @param custBase
	 *            the custBase to set
	 */
	public void setCustBase(String[] custBase) {
		this.custBase = custBase;
	}

	/**
	 * @return the custSale
	 */
	public String[] getCustSale() {
		return custSale;
	}

	/**
	 * @param custSale
	 *            the custSale to set
	 */
	public void setCustSale(String[] custSale) {
		this.custSale = custSale;
	}

	/**
	 * @return the custom
	 */
	public String[] getCustom() {
		return custom;
	}

	/**
	 * @param custom
	 *            the custom to set
	 */
	public void setCustom(String[] custom) {
		this.custom = custom;
	}
	
	public String[] getExclude() {
		return exclude;
	}

	public void setExclude(String[] exclude) {
		this.exclude = exclude;
	}

	public String[] getVnote() {
		return vnote;
	}

	public void setVnote(String[] vnote) {
		this.vnote = vnote;
	}

  
  public String[] getCustmatelbid() {
    return custmatelbid;
  }

  
  public void setCustmatelbid(String[] custmatelbid) {
    this.custmatelbid = custmatelbid;
  }

}
