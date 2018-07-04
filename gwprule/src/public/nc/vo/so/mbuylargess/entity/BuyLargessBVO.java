package nc.vo.so.mbuylargess.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class BuyLargessBVO extends SuperVO {

  /** 开始日期 */
  public static final String DBEGDATE = "dbegdate";

  /** 截止日期 */
  public static final String DENDDATE = "denddate";

  /** 上限类型 */
  public static final String FTOPLIMITTYPE = "ftoplimittype";

  /** 金额 */
  public static final String NMNY = "nmny";

  /** 赠送数量 */
  public static final String NNUM = "nnum";

  /** 单价 */
  public static final String NPRICE = "nprice";

  /** 上限值 */
  public static final String NTOPLIMITVALUE = "ntoplimitvalue";

  /** 买赠设置_主键 */
  public static final String PK_BUYLARGESS = "pk_buylargess";

  /** 买赠子表id */
  public static final String PK_BUYLARGESS_B = "pk_buylargess_b";

  /** 集团 */
  public static final String PK_GROUP = "pk_group";

  /** 物料编码 */
  public static final String PK_MATERIAL = "pk_material";

  /** 单位 */
  public static final String PK_MEASDOC = "pk_measdoc";

  // 时间戳
  public static final String TS = "ts";

  private static final long serialVersionUID = 1L;

  public BuyLargessBVO() {
    super();
  }

  public UFDate getDbegdate() {
    return (UFDate) this.getAttributeValue(BuyLargessBVO.DBEGDATE);
  }

  public UFDate getDenddate() {
    return (UFDate) this.getAttributeValue(BuyLargessBVO.DENDDATE);
  }

  public Integer getFtoplimittype() {
    return (Integer) this.getAttributeValue(BuyLargessBVO.FTOPLIMITTYPE);
  }

  @Override
  public IVOMeta getMetaData() {
    // 得到元数据的实体属性，其中”
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.so_buylargess_b");
    return meta;
  }

  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(BuyLargessBVO.NMNY);
  }

  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(BuyLargessBVO.NNUM);
  }

  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(BuyLargessBVO.NPRICE);
  }

  public UFDouble getNtoplimitvalue() {
    return (UFDouble) this.getAttributeValue(BuyLargessBVO.NTOPLIMITVALUE);
  }

  public String getPk_buylargess() {
    return (String) this.getAttributeValue(BuyLargessBVO.PK_BUYLARGESS);
  }

  public String getPk_buylargess_b() {
    return (String) this.getAttributeValue(BuyLargessBVO.PK_BUYLARGESS_B);
  }

  public String getPk_group() {
    return (String) this.getAttributeValue(BuyLargessBVO.PK_GROUP);
  }

  public String getPk_material() {
    return (String) this.getAttributeValue(BuyLargessBVO.PK_MATERIAL);
  }

  public String getPk_measdoc() {
    return (String) this.getAttributeValue(BuyLargessBVO.PK_MEASDOC);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(BuyLargessBVO.TS);
  }

  public void setDbegdate(UFDate dbegdate) {
    this.setAttributeValue(BuyLargessBVO.DBEGDATE, dbegdate);
  }

  public void setDenddate(UFDate denddate) {
    this.setAttributeValue(BuyLargessBVO.DENDDATE, denddate);
  }

  public void setFtoplimittype(Integer ftoplimittype) {
    this.setAttributeValue(BuyLargessBVO.FTOPLIMITTYPE, ftoplimittype);
  }

  public void setNmny(UFDouble nmny) {
    this.setAttributeValue(BuyLargessBVO.NMNY, nmny);
  }

  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(BuyLargessBVO.NNUM, nnum);
  }

  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(BuyLargessBVO.NPRICE, nprice);
  }

  public void setNtoplimitvalue(UFDouble ntoplimitvalue) {
    this.setAttributeValue(BuyLargessBVO.NTOPLIMITVALUE, ntoplimitvalue);
  }

  public void setPk_buylargess(String pk_buylargess) {
    this.setAttributeValue(BuyLargessBVO.PK_BUYLARGESS, pk_buylargess);
  }

  public void setPk_buylargess_b(String pk_buylargess_b) {
    this.setAttributeValue(BuyLargessBVO.PK_BUYLARGESS_B, pk_buylargess_b);
  }

  public void setPk_group(String pk_group) {
    this.setAttributeValue(BuyLargessBVO.PK_GROUP, pk_group);
  }

  public void setPk_material(String pk_material) {
    this.setAttributeValue(BuyLargessBVO.PK_MATERIAL, pk_material);
  }

  public void setPk_measdoc(String pk_measdoc) {
    this.setAttributeValue(BuyLargessBVO.PK_MEASDOC, pk_measdoc);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(BuyLargessBVO.TS, ts);
  }
}
