package nc.vo.so.mbuylargess.match;

import java.util.HashMap;
import java.util.Map;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * 买赠匹配算法使用的内部参数类
 * 
 * @since 6.1
 * @version 2012-10-30 18:08:28
 * @author 冯加彬
 */
public class BuyLargessMatchPara extends CircularlyAccessibleValueObject {

  /**
   * 数量
   */
  public static final String CASSUNITID = "cassunitid";

  /**
   * 币种
   */
  public static final String CCURRTYPEID = "ccurrtypeid";

  /**
   * 客户基本分类
   */
  public static final String CCUSTCLID = "ccustclid";

  /**
   * 客户
   */
  public static final String CCUSTOMERID = "ccustomerid";

  /**
   * 客户销售分类
   */
  public static final String CCUSTSALECLID = "ccustsaleclid";

  /**
   * 上级销售uzuzhi
   */
  public static final String CFATHERORGID = "cfatherorgid";

  /**
   * 物料基本分类
   */
  public static final String CMARBASECLID = "cmarbaseclid";

  /**
   * 物料销售分类
   */
  public static final String CMARSALECLID = "cmarsaleclid";

  /**
   * 物料
   */
  public static final String CMATERIALID = "cmaterialid";

  /**
   * 销售组织
   */
  public static final String CSALEORGID = "csaleorgid";

  /**
   * 买赠日期
   */
  public static final String DBILLDATE = "dbilldate";

  /**
   * 购买数量
   */
  public static final String NBILLNUM = "nbillnum";

  /**
   * 空值
   */
  public static final String NULLVALUE = "~";

  /**
   * 序列号
   */
  public static final String PARAINDEX = "paraindex";

  private static final long serialVersionUID = 2755457401914203737L;

  private Map<String, Object> mapTemp = new HashMap<String, Object>();

  @Override
  public String[] getAttributeNames() {
    return new String[] {
      BuyLargessMatchPara.CSALEORGID, BuyLargessMatchPara.CMATERIALID,
      BuyLargessMatchPara.CMARBASECLID, BuyLargessMatchPara.CMARSALECLID,
      BuyLargessMatchPara.CCUSTOMERID, BuyLargessMatchPara.CCUSTCLID,
      BuyLargessMatchPara.CCUSTSALECLID, BuyLargessMatchPara.CASSUNITID,
      BuyLargessMatchPara.CCURRTYPEID, BuyLargessMatchPara.NBILLNUM,
      BuyLargessMatchPara.DBILLDATE, BuyLargessMatchPara.PARAINDEX
    };
  }

  @Override
  public Object getAttributeValue(String attributeName) {
    return this.mapTemp.get(attributeName);
  }

  /**
   * 获得单位
   * 
   * @return assunitid
   */
  public String getCassunitid() {
    return (String) this.getAttributeValue(BuyLargessMatchPara.CASSUNITID);
  }

  /**
   * 获得币种
   * 
   * @return currtypeid
   */
  public String getCcurrtypeid() {
    return (String) this.getAttributeValue(BuyLargessMatchPara.CCURRTYPEID);
  }

  /**
   * 获得客户基本分类
   * 
   * @return custclid
   */
  public String getCcustclid() {
    return (String) this.getAttributeValue(BuyLargessMatchPara.CCUSTCLID);
  }

  /**
   * 获得客户
   * 
   * @return customerid
   */
  public String getCcustomerid() {
    return (String) this.getAttributeValue(BuyLargessMatchPara.CCUSTOMERID);
  }

  /**
   * 获得客户销售分类
   * 
   * @return custsaleclid
   */
  public String getCcustsaleclid() {
    return (String) this.getAttributeValue(BuyLargessMatchPara.CCUSTSALECLID);
  }

  /**
   * 获得上级销售组织
   * 
   * @return fatherorgid
   */
  public String getCfatherorgid() {
    return (String) this.getAttributeValue(BuyLargessMatchPara.CFATHERORGID);
  }

  /**
   * 获得物料基本分类
   * 
   * @return marbaseclid
   */
  public String getCmarbaseclid() {
    return (String) this.getAttributeValue(BuyLargessMatchPara.CMARBASECLID);
  }

  /**
   * 获得物料销售分类
   * 
   * @return marsaleclid
   */
  public String getCmarsaleclid() {
    return (String) this.getAttributeValue(BuyLargessMatchPara.CMARSALECLID);
  }

  /**
   * 获得物料
   * 
   * @return materialid
   */
  public String getCmaterialid() {
    return (String) this.getAttributeValue(BuyLargessMatchPara.CMATERIALID);
  }

  /**
   * 获得销售组织
   * 
   * @return saleorgid
   */
  public String getCsaleorgid() {
    return (String) this.getAttributeValue(BuyLargessMatchPara.CSALEORGID);
  }

  /**
   * 获得匹配日期
   * 
   * @return billdate
   */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(BuyLargessMatchPara.DBILLDATE);
  }

  @Override
  public String getEntityName() {
    return null;
  }

  /**
   * 获得买赠数量
   * 
   * @return billnum
   */
  public UFDouble getNbillnum() {
    return (UFDouble) this.getAttributeValue(BuyLargessMatchPara.NBILLNUM);
  }

  /**
   * 获得序列号
   * 
   * @return Paraindex
   */
  public Integer getParaindex() {
    return (Integer) this.getAttributeValue(BuyLargessMatchPara.PARAINDEX);
  }

  @Override
  public void setAttributeValue(String name, Object value) {
    this.mapTemp.put(name, value);
  }

  /**
   * 设置物料单位
   * 
   * @param cassunitid
   */
  public void setCassunitid(String cassunitid) {
    this.setAttributeValue(BuyLargessMatchPara.CASSUNITID, cassunitid);
  }

  /**
   * 设置币种
   * 
   * @param ccurrtypeid
   */
  public void setCcurrtypeid(String ccurrtypeid) {
    this.setAttributeValue(BuyLargessMatchPara.CCURRTYPEID, ccurrtypeid);
  }

  /**
   * 设置客户基本分类
   * 
   * @param ccustclid
   */
  public void setCcustclid(String ccustclid) {
    this.setAttributeValue(BuyLargessMatchPara.CCUSTCLID, ccustclid);
  }

  /**
   * 设置客户
   * 
   * @param ccustomerid
   */
  public void setCcustomerid(String ccustomerid) {
    this.setAttributeValue(BuyLargessMatchPara.CCUSTOMERID, ccustomerid);
  }

  /**
   * 设置客户销售分类
   * 
   * @param ccustsaleclid
   */
  public void setCcustsaleclid(String ccustsaleclid) {
    this.setAttributeValue(BuyLargessMatchPara.CCUSTSALECLID, ccustsaleclid);
  }

  /**
   * 设置上级销售组织
   * 
   * @param cfatherorgid
   */
  public void setCfatherorgid(String cfatherorgid) {
    this.setAttributeValue(BuyLargessMatchPara.CFATHERORGID, cfatherorgid);
  }

  /**
   * 设置物料基本分类
   * 
   * @param cmarbaseclid
   */
  public void setCmarbaseclid(String cmarbaseclid) {
    this.setAttributeValue(BuyLargessMatchPara.CMARBASECLID, cmarbaseclid);
  }

  /**
   * 设置物料销售分类
   * 
   * @param cmarsaleclid
   */
  public void setCmarsaleclid(String cmarsaleclid) {
    this.setAttributeValue(BuyLargessMatchPara.CMARSALECLID, cmarsaleclid);
  }

  /**
   * 设置物料
   * 
   * @param cmaterialid
   */
  public void setCmaterialid(String cmaterialid) {
    this.setAttributeValue(BuyLargessMatchPara.CMATERIALID, cmaterialid);
  }

  /**
   * 设置销售组织
   * 
   * @param csaleorgid
   */
  public void setCsaleorgid(String csaleorgid) {
    this.setAttributeValue(BuyLargessMatchPara.CSALEORGID, csaleorgid);
  }

  /**
   * 设置买赠匹配日期
   * 
   * @param dbilldate
   */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(BuyLargessMatchPara.DBILLDATE, dbilldate);
  }

  /**
   * 设置买赠数量
   * 
   * @param nbillnum
   */
  public void setNbillnum(UFDouble nbillnum) {
    this.setAttributeValue(BuyLargessMatchPara.NBILLNUM, nbillnum);
  }

  /**
   * 设置序列号
   * 
   * @param paraindex
   */
  public void setParaindex(Integer paraindex) {
    this.setAttributeValue(BuyLargessMatchPara.PARAINDEX, paraindex);
  }

  @Override
  public void validate() throws ValidationException {
    StringBuilder errmsg = new StringBuilder();
    if (PubAppTool.isNull(this.getCmaterialid())
        && PubAppTool.isNull(this.getCmarbaseclid())
        && PubAppTool.isNull(this.getCmarsaleclid())) {
      errmsg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006003_0",
          "04006003-0027")/* 匹配买赠时物料、物料分类不能同时为空*/);
      errmsg.append("/n");
    }
    if (PubAppTool.isNull(this.getCcustomerid())
        && PubAppTool.isNull(this.getCcustclid())
        && PubAppTool.isNull(this.getCcustsaleclid())) {
      errmsg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006003_0",
          "04006003-0028")/* 匹配买赠时客户、客户分类不能同时为空*/);
      errmsg.append("/n");
    }
    StringBuilder validateitem = new StringBuilder();
    if (null == this.getCsaleorgid()) {
      validateitem.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006003_0", "04006003-0029")/*销售组织、*/);
    }
    if (null == this.getDbilldate()) {
      validateitem.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006003_0", "04006003-0030")/*单据日期、*/);
    }
    if (validateitem.length() > 0) {
      errmsg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006003_0",
          "04006003-0032")/*匹配买赠时下列项目不允许为空：*/);
      validateitem.deleteCharAt(validateitem.length() - 1);
      errmsg.append(validateitem);
    }
    if (errmsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(errmsg.toString());
    }

  }

}
