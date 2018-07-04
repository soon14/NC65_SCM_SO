package nc.pubitf.so.m30.mmpps.planbusi.mapvo;

import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.mmpps.planbusi.SODemandMapVO;

/**
 * 
 * @since 6.3
 * @version 2012-11-14 ÏÂÎç03:16:10
 * @author Áº¼ªÃ÷
 */
public class SaleOrderSADemandMapVO extends SODemandMapVO {

  private static final long serialVersionUID = -4720096137983188072L;

  @Override
  public String getFrom() {
    String fromsql = super.getFrom();
    SqlBuilder newfrom = new SqlBuilder();
    newfrom.append(fromsql);
    newfrom.append(" inner join " + this.getExeTable());
    newfrom.append(" on " + this.getExeTableCol(SaleOrderBVO.CSALEORDERBID));
    newfrom.append(" = " + this.getDemandbillbid());
    return newfrom.toString();
  }

  @Override
  public String getWhere() {
    String wheresql = super.getWhere();
    SqlBuilder newwhere = new SqlBuilder();
    newwhere.append(wheresql);
    newwhere.append(" and ");
    newwhere.append(this.getBodyTableCol(SaleOrderBVO.NNUM) + " > 0");
    return newwhere.toString();
  }

  @Override
  public String getDemandTypeCodeValue() {
    return "30";
  }

  @Override
  public String getDemandTypeIdValue() {
    return "30";
  }

  @Override
  public String getBodyDemandbillid() {
    return super.getBodyTableCol(SaleOrderHVO.CSALEORDERID);
  }

  @Override
  public String getBodyTable() {
    return "so_saleorder_b";
  }

  @Override
  public String getHeadTable() {
    return "so_saleorder";
  }

  @Override
  public String getDemandbillid() {
    return super.getHeadTableCol(SaleOrderHVO.CSALEORDERID);
  }

  @Override
  public String getDemandbillbid() {
    return super.getBodyTableCol(SaleOrderBVO.CSALEORDERBID);
  }

  @Override
  public String getNexenum() {
    return this.getExeTableCol(SaleOrderBVO.NTOTALOUTNUM);
  }

  @Override
  public String getNdemandnum() {
    return "isnull(" + super.getBodyTableCol(SaleOrderBVO.NNUM) + ",0)"
        + "- isnull(" + this.getExeTableCol(SaleOrderBVO.NTOTALOUTNUM) + ",0)";
  }

  @Override
  public String getReservatioNnum() {
    return this.getExeTableCol(SaleOrderBVO.NREQRSNUM);
  }

  @Override
  public String getFirstBid() {
    return super.getBodyTableCol(SaleOrderBVO.CFIRSTBID);
  }

  @Override
  public String getFirstId() {
    return super.getBodyTableCol(SaleOrderBVO.CFIRSTID);
  }

  @Override
  public String getFirstCode() {
    return super.getBodyTableCol(SaleOrderBVO.VFIRSTCODE);
  }

  @Override
  public String getFirstRowNo() {
    return super.getBodyTableCol(SaleOrderBVO.VFIRSTROWNO);
  }

  @Override
  public String getFirstType() {
    return super.getBodyTableCol(SaleOrderBVO.VFIRSTTYPE);
  }

  @Override
  public String getSrcBid() {
    return super.getBodyTableCol(SaleOrderBVO.CSRCBID);
  }

  @Override
  public String getSrcCode() {
    return super.getBodyTableCol(SaleOrderBVO.VSRCCODE);
  }

  @Override
  public String getSrcId() {
    return super.getBodyTableCol(SaleOrderBVO.CSRCID);
  }

  @Override
  public String getSrcRowNo() {
    return super.getBodyTableCol(SaleOrderBVO.VSRCROWNO);
  }

  @Override
  public String getSrcType() {
    return super.getBodyTableCol(SaleOrderBVO.VSRCTYPE);
  }

  @Override
  public String getCmarcustid() {
    return super.getBodyTableCol(SaleOrderBVO.CCUSTMATERIALID);
  }

  @Override
  public String getVBillStatus() {
    return super.getHeadTableCol(SaleOrderHVO.FSTATUSFLAG);
  }

  @Override
  public String getVBillStatusEnumID() {
    return "380e1847-6624-48b9-b525-2bcba39b6d7d";
  }

  private String getExeTable() {
    return "so_saleorder_exe";
  }

  private String getExeTableCol(String exekey) {
    return "so_saleorder_exe" + SOConstant.POINT + exekey;
  }

  public String getBboutendflag() {
    return super.getBodyTableCol(SaleOrderBVO.BBOUTENDFLAG);
  }
}
