package nc.vo.so.pub.mmpps.planbusi;

import java.io.Serializable;

import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.SOItemKey;

public abstract class SODemandMapVO implements Serializable {
  private static final long serialVersionUID = -4751577454148356365L;

  public abstract String getBodyDemandbillid();

  /**
   * 需要子类返回主表名
   * 
   * @return
   */
  public abstract String getBodyTable();

  public String getBodyTableCol(String bodykey) {
    return getBodyTable() + SOConstant.POINT + bodykey;
  }

  /**
   * 需要子类返回主表名
   * 
   * @return
   */
  public abstract String getHeadTable();

  public String getHeadTableCol(String headkey) {
    return getHeadTable() + SOConstant.POINT + headkey;
  }

  public String getFrom() {
    SqlBuilder fromsql = new SqlBuilder();
    fromsql.append(getHeadTable() + " inner join " + getBodyTable());
    fromsql.append(" on " + getDemandbillid());
    fromsql.append(" = " + getBodyDemandbillid());
    return fromsql.toString();
  }

  public String getWhere() {
    SqlBuilder wheresql = new SqlBuilder();
    wheresql.append(getHeadTableCol(SOItemKey.DR), 0);
    wheresql.append(" and ");
    wheresql.append(getBodyTableCol(SOItemKey.DR), 0);
    return wheresql.toString();
  }

  public abstract String getDemandTypeCodeValue();

  public abstract String getDemandTypeIdValue();

  public abstract String getDemandbillid();

  public abstract String getDemandbillbid();

  public String getDemandRowNo() {
    return getBodyTableCol(SOItemKey.CROWNO);
  }

  public String getDemandOrgid() {
    return getBodyTableCol(SOItemKey.CSENDSTOCKORGID);
  }

  public String getDemandOrgvid() {
    return getBodyTableCol(SOItemKey.CSENDSTOCKORGVID);
  }

  public String getMaterialid() {
    return getBodyTableCol(SOItemKey.CMATERIALID);
  }

  public String getMaterialvid() {
    return getBodyTableCol(SOItemKey.CMATERIALVID);
  }

  public String getDemandCode() {
    return getHeadTableCol(SOItemKey.VBILLCODE);
  }

  public String getDemandTime() {
    return getBodyTableCol(SOItemKey.DSENDDATE);
  }

  public String getDbilldate() {
    return getBodyTableCol(SOItemKey.DBILLDATE);
  }

  public String getCunitid() {
    return getBodyTableCol(SOItemKey.CUNITID);
  }

  public String getNnum() {
    return getBodyTableCol(SOItemKey.NNUM);
  }

  public abstract String getNexenum();

  public abstract String getNdemandnum();

  public String getVchangerate() {
    return getBodyTableCol(SOItemKey.VCHANGERATE);
  }

  public String getCastunitid() {
    return getBodyTableCol(SOItemKey.CASTUNITID);
  }

  public String getNastnum() {
    return getBodyTableCol(SOItemKey.NASTNUM);
  }

  public abstract String getReservatioNnum();

  public String getVendorid() {
    return getBodyTableCol(SOItemKey.CVENDORID);
  }

  public String getProductorid() {
    return getBodyTableCol(SOItemKey.CPRODUCTORID);
  }

  public String getProjectid() {
    return getBodyTableCol(SOItemKey.CPROJECTID);
  }

  public String getCustomerid() {
    return getHeadTableCol(SOItemKey.CCUSTOMERID);
  }

  public String getFree1() {
    return getBodyTableCol(SOItemKey.VFREE1);
  }

  public String getFree2() {
    return getBodyTableCol(SOItemKey.VFREE2);
  }

  public String getFree3() {
    return getBodyTableCol(SOItemKey.VFREE3);
  }

  public String getFree4() {
    return getBodyTableCol(SOItemKey.VFREE4);
  }

  public String getFree5() {
    return getBodyTableCol(SOItemKey.VFREE5);
  }

  public String getFree6() {
    return getBodyTableCol(SOItemKey.VFREE6);
  }

  public String getFree7() {
    return getBodyTableCol(SOItemKey.VFREE7);
  }

  public String getFree8() {
    return getBodyTableCol(SOItemKey.VFREE8);
  }

  public String getFree9() {
    return getBodyTableCol(SOItemKey.VFREE9);
  }

  public String getFree10() {
    return getBodyTableCol(SOItemKey.VFREE10);
  }
  
  public String getCmffileid() {
    return getBodyTableCol(SOItemKey.CMFFILEID);
  }

  public abstract String getFirstBid();

  public abstract String getFirstCode();

  public abstract String getFirstId();

  public abstract String getFirstRowNo();

  public abstract String getFirstType();

  public abstract String getSrcBid();

  public abstract String getSrcCode();

  public abstract String getSrcId();

  public abstract String getSrcRowNo();

  public abstract String getSrcType();

  public abstract String getCmarcustid();

  public abstract String getVBillStatus();

  public abstract String getVBillStatusEnumID();
}
