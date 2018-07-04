package nc.pubitf.so.m30.mmpps.calc;

import java.io.Serializable;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.mmpps.calc.DemandResultForCalcImpl;

/**
 * 销售订单给离散制造提供的需求单据信息
 * 
 * @since 6.3.1
 * @version 2013-08-21 09:04:37
 * @author 张云枫
 */
public class SaleOrderDemandMapVO extends DemandResultForCalcImpl implements
    Serializable {

  private static final long serialVersionUID = -5945222185469136575L;

  

  @Override
  public String getBodyDemandbillid() {
    return super.getBodyTableCol(SaleOrderHVO.CSALEORDERID);
  }

  @Override
  public String getBodyTable() {
    return "so_saleorder_b";
  }

  @Override
  public String getDemandbillbid() {
    return super.getBodyTableCol(SaleOrderBVO.CSALEORDERBID);
  }

  @Override
  public String getDemandbillid() {

    return super.getHeadTableCol(SaleOrderHVO.CSALEORDERID);
  }

  @Override
  public String getDemandNnum() {
    return "isnull(" + super.getBodyTableCol(SaleOrderBVO.NNUM) + ",0)"
        + "- isnull(" + this.getExeTableCol(SaleOrderBVO.NTOTALOUTNUM) + ",0)";
  }

  private String getExeTable() {
    return "so_saleorder_exe";
  }

  private String getExeTableCol(String exekey) {
    return "so_saleorder_exe" + SOConstant.POINT + exekey;
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
  public String getHeadTable() {
    return "so_saleorder";
  }

  @Override
  public String getReservatioNnum() {
    return this.getExeTableCol(SaleOrderBVO.NREQRSNUM);
  }

  @Override
  public String getWhere() {
    String wheresql = super.getWhere();
    SqlBuilder newwhere = new SqlBuilder();
    newwhere.append(wheresql);
    newwhere.append(" and ");
    newwhere.append(this.getBodyTableCol(SaleOrderBVO.BBOUTENDFLAG),
        UFBoolean.FALSE);
    newwhere.append(" and ");
    newwhere.append("( " + this.getBodyTableCol(SaleOrderBVO.NNUM)
        + " - isnull(");
    newwhere
        .append(this.getExeTableCol(SaleOrderBVO.NTOTALOUTNUM) + ",0)) > 0");
    newwhere.append("and ");
    newwhere.append(this.getBodyTableCol(SaleOrderBVO.NNUM) + " > 0");

    return newwhere.toString();
  }

  @Override
  public String getCcustmaterialid() {
    return super.getBodyTableCol(SaleOrderBVO.CCUSTMATERIALID);
  }

  @Override
  public String getSrcBid() {
    return super.getBodyTableCol(SaleOrderBVO.CSRCBID);
  }

  @Override
  public String getSrcId() {
    return super.getBodyTableCol(SaleOrderBVO.CSRCID);
  }

  @Override
  public String getSrcCode() {
    return super.getBodyTableCol(SaleOrderBVO.VSRCCODE);
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
  public String getBatchCode() {
    return super.getBodyTableCol(SaleOrderBVO.VBATCHCODE);
  }

  @Override
  public String getPk_BatchCode() {
    return super.getBodyTableCol(SaleOrderBVO.PK_BATCHCODE);
  }

  @Override
  public String getNnum() {
    return super.getBodyTableCol(SaleOrderBVO.NNUM);
  }

  @Override
  public String getNexenum() {
    return this.getExeTableCol(SaleOrderBVO.NTOTALOUTNUM);
  }

  @Override
  public String getNastnum() {
    return super.getBodyTableCol(SaleOrderBVO.NASTNUM);
  }

  @Override
  public String getVBillStatus() {
    return super.getHeadTableCol(SaleOrderHVO.FSTATUSFLAG);
  }

  @Override
  public String getVBillStatusEnumID() {
    return "380e1847-6624-48b9-b525-2bcba39b6d7d";
  }

  @Override
  public String getBoutendflag() {
    return super.getHeadTableCol(SaleOrderHVO.BOUTENDFLAG);
  }

  @Override
  public String getDbilldate() {
    return super.getHeadTableCol(SaleOrderHVO.DBILLDATE);
  }

  @Override
  public String getCunitid() {
    return super.getBodyTableCol(SaleOrderBVO.CUNITID);
  }

  @Override
  public String getVchangerate() {
    return super.getBodyTableCol(SaleOrderBVO.VCHANGERATE);
  }

  @Override
  public String getCastunitid() {
    return super.getBodyTableCol(SaleOrderBVO.CASTUNITID);
  }

  @Override
  public String getDemandTypeCodeValue() {
    return "30";
  }

  @Override
  public String getDemandTypeIdValue() {
    return "30";
  }

  public String getTranType() {
    return super.getHeadTableCol(SaleOrderHVO.CTRANTYPEID);
  }
  
  /**
   * 特征码，636增加
   * 
   * @return
   */
  @Override
  public String getCffileid() {
    return super.getBodyTableCol(SaleOrderBVO.CMFFILEID);
  }

}
