package nc.vo.so.m32.paravo;

import java.io.Serializable;

import nc.vo.pub.lang.MultiLangText;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;

public class InvoiceFormReportParaVO implements Serializable {
  private static final long serialVersionUID = -3679003518783406066L;

  /** 客户IDS */
  private String[] ccustomerids;

  /** 订单客户名称 */
  private MultiLangText ccustomernames;

  /** 部门 */
  private String[] cdeptvids;

  /** 业务员 */
  private String[] cemployeeids;

  /** 物料IDs */
  private String[] cmaterialvids;

  /** 物料名称 */
  private MultiLangText cmaterialvname;

  /** 统计服务和折扣 */
  private UFBoolean containlaboranddiscount;

  /** 币种 */
  private String[] corigcurrencyids;

  /** 客户地区分类 */
  private String[] customerpk_areacls;

  /** 结束时间 */
  private UFDate enddate;

  /** 订单状态 */
  private int[] fstatusflag;

  /** 客户基本分类 */
  private String[] pk_custclass;

  /** 客户销售分类 */
  private String[] pk_custsaleclass;

  /** 物料基本分类 */
  private String[] pk_marbasclass;

  /** 物料销售分类 */
  private String[] pk_marsaleclass;

  /** 销售组织 */
  private String[] saleorgids;

  /** 开始时间 */
  private UFDate startdate;

  /** 库存组织 */
  private String[] stockorgvids;

  /** 订单类型 */
  private String[] vtrantypecodes;

  public String[] getCcustomerids() {
    return this.ccustomerids;
  }

  public MultiLangText getCcustomernames() {
    return this.ccustomernames;
  }

  public String[] getCdeptvids() {
    return this.cdeptvids;
  }

  public String[] getCemployeeids() {
    return this.cemployeeids;
  }

  public String[] getCmaterialvids() {
    return this.cmaterialvids;
  }

  public MultiLangText getCmaterialvname() {
    return this.cmaterialvname;
  }

  public UFBoolean getContainlaboranddiscount() {
    return this.containlaboranddiscount;
  }

  public String[] getCorigcurrencyids() {
    return this.corigcurrencyids;
  }

  public String[] getCustomerpk_areacls() {
    return this.customerpk_areacls;
  }

  public UFDate getEnddate() {
    return this.enddate;
  }

  public int[] getFstatusflag() {
    return this.fstatusflag;
  }

  public String[] getPk_custclass() {
    return this.pk_custclass;
  }

  public String[] getPk_custsaleclass() {
    return this.pk_custsaleclass;
  }

  public String[] getPk_marbasclass() {
    return this.pk_marbasclass;
  }

  public String[] getPk_marsaleclass() {
    return this.pk_marsaleclass;
  }

  public String[] getSaleorgids() {
    return this.saleorgids;
  }

  public UFDate getStartdate() {
    return this.startdate;
  }

  public String[] getStockorgvids() {
    return this.stockorgvids;
  }

  public String[] getVtrantypecodes() {
    return this.vtrantypecodes;
  }

  public void setCcustomerids(String[] ccustomerids) {
    this.ccustomerids = ccustomerids;
  }

  public void setCcustomernames(MultiLangText ccustomernames) {
    this.ccustomernames = ccustomernames;
  }

  public void setCdeptvids(String[] cdeptvids) {
    this.cdeptvids = cdeptvids;
  }

  public void setCemployeeids(String[] cemployeeids) {
    this.cemployeeids = cemployeeids;
  }

  public void setCmaterialvids(String[] cmaterialvids) {
    this.cmaterialvids = cmaterialvids;
  }

  public void setCmaterialvname(MultiLangText cmaterialvname) {
    this.cmaterialvname = cmaterialvname;
  }

  public void setContainlaboranddiscount(UFBoolean containlaboranddiscount) {
    this.containlaboranddiscount = containlaboranddiscount;
  }

  public void setCorigcurrencyids(String[] corigcurrencyids) {
    this.corigcurrencyids = corigcurrencyids;
  }

  public void setCustomerpk_areacls(String[] customerpk_areacls) {
    this.customerpk_areacls = customerpk_areacls;
  }

  public void setEnddate(UFDate enddate) {
    this.enddate = enddate;
  }

  public void setFstatusflag(int[] fstatusflag) {
    this.fstatusflag = fstatusflag;
  }

  public void setPk_custclass(String[] pk_custclass) {
    this.pk_custclass = pk_custclass;
  }

  public void setPk_custsaleclass(String[] pk_custsaleclass) {
    this.pk_custsaleclass = pk_custsaleclass;
  }

  public void setPk_marbasclass(String[] pk_marbasclass) {
    this.pk_marbasclass = pk_marbasclass;
  }

  public void setPk_marsaleclass(String[] pk_marsaleclass) {
    this.pk_marsaleclass = pk_marsaleclass;
  }

  public void setSaleorgids(String[] saleorgids) {
    this.saleorgids = saleorgids;
  }

  public void setStartdate(UFDate startdate) {
    this.startdate = startdate;
  }

  public void setStockorgvids(String[] stockorgvids) {
    this.stockorgvids = stockorgvids;
  }

  public void setVtrantypecodes(String[] vtrantypecodes) {
    this.vtrantypecodes = vtrantypecodes;
  }
}
