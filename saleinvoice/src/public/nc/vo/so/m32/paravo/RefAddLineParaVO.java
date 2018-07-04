package nc.vo.so.m32.paravo;

import nc.vo.pub.lang.UFBoolean;

/**
 * 拉单的USEROBJ
 * 
 * @since 6.0
 * @version 2011-6-10 上午10:44:20
 * @author 么贵敬
 */
public class RefAddLineParaVO {
  /** 出库单到发票的流程 */
  private String[] busitypes;

  /** 源头BID */
  private String[] cfirstbids;

  /** 源头ID（暂时不用） */
  private String[] cfirstids;

  /** 来源BID */
  private String[] csrcbids;

  /** 来源 ID(暂时不用) */
  private String[] csrcids;

  /** 流程是否是订单到发票流程(用于参照增行) */
  private UFBoolean is30to32busitypes;

  /** 主组织 */
  private String pk_org;

  public String[] getBusitypes() {
    return this.busitypes;
  }

  public String[] getCfirstbids() {
    return this.cfirstbids;
  }

  public String[] getCfirstids() {
    return this.cfirstids;
  }

  public String[] getCsrcbids() {
    return this.csrcbids;
  }

  public String[] getCsrcids() {
    return this.csrcids;
  }

  public UFBoolean getIs30to32busitypes() {
    return this.is30to32busitypes;
  }

  public String getPk_org() {
    return this.pk_org;
  }

  public void setBusitypes(String[] busitypes) {
    this.busitypes = busitypes;
  }

  public void setCfirstbids(String[] cfirstbids) {
    this.cfirstbids = cfirstbids;
  }

  public void setCfirstids(String[] cfirstids) {
    this.cfirstids = cfirstids;
  }

  public void setCsrcbids(String[] csrcbids) {
    this.csrcbids = csrcbids;
  }

  public void setCsrcids(String[] csrcids) {
    this.csrcids = csrcids;
  }

  public void setIs30to32busitypes(UFBoolean is30to32busitypes) {
    this.is30to32busitypes = is30to32busitypes;
  }

  public void setPk_org(String pk_org) {
    this.pk_org = pk_org;
  }

}
