package nc.pubitf.so.m38.mmdp.dt.mapvo;

import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.pub.mmpps.planbusi.SODemandMapVO;

/**
 * 
 * @since 6.3
 * @version 2012-11-14 ÏÂÎç03:16:23
 * @author Áº¼ªÃ÷
 */
public class SalePreOrderDTDemandMapVO extends SODemandMapVO {

  private static final long serialVersionUID = 5032621139028037732L;

  @Override
  public String getWhere() {
    String wheresql = super.getWhere();
    SqlBuilder newwhere = new SqlBuilder();
    newwhere.append(wheresql);
    newwhere.append(" and ");
    newwhere.append(this.getBodyTableCol(PreOrderBVO.NNUM) + " > 0");
    return newwhere.toString();
  }

  @Override
  public String getDemandTypeCodeValue() {
    return "38";
  }

  @Override
  public String getDemandTypeIdValue() {
    return "38";
  }

  @Override
  public String getBodyDemandbillid() {
    return super.getBodyTableCol(PreOrderHVO.CPREORDERID);
  }

  @Override
  public String getBodyTable() {
    return "so_preorder_b";
  }

  @Override
  public String getHeadTable() {
    return "so_preorder";
  }

  @Override
  public String getDemandbillid() {
    return super.getHeadTableCol(PreOrderHVO.CPREORDERID);
  }

  @Override
  public String getDemandbillbid() {
    return super.getBodyTableCol(PreOrderBVO.CPREORDERBID);
  }

  @Override
  public String getNexenum() {
    return super.getBodyTableCol(PreOrderBVO.NARRNUM);
  }

  @Override
  public String getNdemandnum() {
    return "isnull(" + super.getBodyTableCol(PreOrderBVO.NNUM) + ",0)"
        + "- isnull(" + super.getBodyTableCol(PreOrderBVO.NARRNUM) + ",0)";

  }

  @Override
  public String getFirstBid() {
    return null;
  }

  @Override
  public String getFirstCode() {
    return null;
  }

  @Override
  public String getFirstId() {
    return null;
  }

  @Override
  public String getFirstRowNo() {
    return null;
  }

  @Override
  public String getFirstType() {
    return null;
  }

  @Override
  public String getSrcBid() {
    return null;
  }

  @Override
  public String getSrcCode() {
    return null;
  }

  @Override
  public String getSrcId() {
    return null;
  }

  @Override
  public String getSrcRowNo() {
    return null;
  }

  @Override
  public String getSrcType() {
    return null;
  }

  @Override
  public String getCmarcustid() {
    return super.getBodyTableCol(PreOrderBVO.CCUSTMATERIALID);
  }

  @Override
  public String getVBillStatus() {
    return super.getHeadTableCol(PreOrderHVO.FSTATUSFLAG);
  }

  @Override
  public String getVBillStatusEnumID() {
    return "380e1847-6624-48b9-b525-2bcba39b6d7d";
  }

  @Override
  public String getReservatioNnum() {
    return null;
  }

  /**
   * 
   * 
   * @return h
   */
  public String getBlineclose() {
    return super.getBodyTableCol(PreOrderBVO.BLINECLOSE);
  }
}
