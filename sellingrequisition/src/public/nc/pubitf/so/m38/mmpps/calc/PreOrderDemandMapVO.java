package nc.pubitf.so.m38.mmpps.calc;

import java.io.Serializable;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.mmpps.calc.DemandResultForCalcImpl;

import nc.md.model.impl.MDEnum;

/**
 * 销售预订单给离散制造提供的需求单据信息
 * 
 * @since 6.3.1
 * @version 2013-08-21 09:08:31
 * @author 张云枫
 * 
 */
public class PreOrderDemandMapVO extends DemandResultForCalcImpl implements
    Serializable {

  private static final long serialVersionUID = -3937422528947737843L;

  @Override
  public String getBodyDemandbillid() {
    return super.getBodyTableCol(PreOrderHVO.CPREORDERID);
  }

  @Override
  public String getBodyTable() {
    return "so_preorder_b";
  }

  @Override
  public String getDemandbillbid() {
    return super.getBodyTableCol(PreOrderBVO.CPREORDERBID);
  }

  @Override
  public String getDemandbillid() {
    return super.getHeadTableCol(PreOrderHVO.CPREORDERID);
  }

  @Override
  public String getDemandNnum() {
    return "isnull(" + super.getBodyTableCol(PreOrderBVO.NNUM) + ",0)"
        + " - isnull(" + super.getBodyTableCol(PreOrderBVO.NARRNUM) + ",0)";
  }

  @Override
  public String getFirstCode() {
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
  public String getHeadTable() {
    return "so_preorder";
  }

  @Override
  public String getReservatioNnum() {
    return null;
  }

  @Override
  public String getWhere() {
    String wheresql = super.getWhere();
    SqlBuilder newWhere = new SqlBuilder();
    newWhere.append(wheresql);
    newWhere.append(" and ");
    newWhere.append(super.getBodyTableCol(PreOrderBVO.BLINECLOSE),
        UFBoolean.FALSE);
    newWhere.append(" and ");
    newWhere.append(super.getHeadTableCol(PreOrderHVO.DABATEDATE), ">",
        AppContext.getInstance().getBusiDate().toString());

    newWhere.append(" and ");
    MDEnum[] status = new BillStatus[] {
      BillStatus.CLOSED, BillStatus.INVALIDATE
    };
    newWhere.appendNot(super.getHeadTableCol(PreOrderHVO.FSTATUSFLAG), status);
    newWhere.append(" and ");
    newWhere
        .append("isnull(" + super.getBodyTableCol(PreOrderBVO.NNUM) + ",0)"
            + " - isnull(" + super.getBodyTableCol(PreOrderBVO.NARRNUM)
            + ",0) > 0");
    return newWhere.toString();
  }

  @Override
  public String getBatchCode() {
    return super.getBodyTableCol(PreOrderBVO.VBATCHCODE);
  }

  @Override
  public String getPk_BatchCode() {
    return super.getBodyTableCol(PreOrderBVO.PK_BATCHCODE);
  }

  @Override
  public String getNnum() {
    return super.getBodyTableCol(PreOrderBVO.NNUM);
  }

  @Override
  public String getNexenum() {
    return super.getBodyTableCol(PreOrderBVO.NARRNUM);
  }

  @Override
  public String getNastnum() {
    return super.getBodyTableCol(PreOrderBVO.NASTNUM);
  }

  @Override
  public String getCcustmaterialid() {
    return super.getBodyTableCol(PreOrderBVO.CCUSTMATERIALID);
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
  public String getVBillStatus() {
    return super.getHeadTableCol(PreOrderHVO.FSTATUSFLAG);
  }

  @Override
  public String getVBillStatusEnumID() {
    return "380e1847-6624-48b9-b525-2bcba39b6d7d";
  }

  @Override
  public String getBoutendflag() {
    return super.getBodyTableCol(PreOrderBVO.BLINECLOSE);
  }

  @Override
  public String getDbilldate() {
    return super.getHeadTableCol(PreOrderHVO.DBILLDATE);
  }

  @Override
  public String getCunitid() {
    return super.getBodyTableCol(PreOrderBVO.CUNITID);
  }

  @Override
  public String getVchangerate() {
    return super.getBodyTableCol(PreOrderBVO.VCHANGERATE);
  }

  @Override
  public String getCastunitid() {
    return super.getBodyTableCol(PreOrderBVO.CASTUNITID);
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
  public String getTranType() {
    return super.getHeadTableCol(PreOrderHVO.CTRANTYPEID);
  }

  @Override
  public String getCffileid() {
    // TODO Auto-generated method stub
    return null;
  }

}
