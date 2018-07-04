package nc.vo.so.m30.sobalance.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

public class SoBalanceBVO extends SuperVO {

  public static final String ENTITYNAME = "so.so_balance_b";

  // 币种
  public static final String CARORIGCURRENCYID = "carorigcurrencyid";

  // 收款单主实体
  public static final String CPAYBILLID = "cpaybillid";

  // 收款单子实体
  public static final String CPAYBILLROWID = "cpaybillrowid";

  // 产品线
  public static final String CPRODLINEID = "cprodlineid";

  // 订单收款核销子实体
  public static final String CSOBALANCEBID = "csobalancebid";

  // 订单收款核销主实体_主键
  public static final String CSOBALANCEID = "csobalanceid";

  // 核销日期
  public static final String DARBALANCEDATE = "darbalancedate";

  // 单据日期
  public static final String DARBILLDATE = "darbilldate";

  // dr
  public static final String DR = "dr";

  // 核销类型
  public static final String FIBALTYPE = "fibaltype";

  // 收款单已财务核销金额
  public static final String NORIGACCBALMNY = "norigaccbalmny";

  // 订单核销金额
  public static final String NORIGORDBALMNY = "norigordbalmny";

  // 本次订单核销金额
  public static final String NORIGTHISBALMNY = "norigthisbalmny";

  // 单据行金额
  public static final String NORIGARMNY = "norigarmny";

  // 其他核销金额
  public static final String NORIGOTHERBALMNY = "norigotherbalmny";

  // 销售组织
  public static final String PK_ORG = "pk_org";

  // 时间戳
  public static final String TS = "ts";

  // 单据号
  public static final String VARBILLCODE = "varbillcode";

  // 收款限额控制预收
  public static final String BPRECEIVEFLAG = "bpreceiveflag";

  private static final long serialVersionUID = 9004142056883934589L;

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta(SoBalanceBVO.ENTITYNAME);
    return meta;
  }

  public String getCarorigcurrencyid() {
    return (String) this.getAttributeValue(SoBalanceBVO.CARORIGCURRENCYID);
  }

  public void setCarorigcurrencyid(String carorigcurrencyid) {
    this.setAttributeValue(SoBalanceBVO.CARORIGCURRENCYID, carorigcurrencyid);
  }

  public String getCpaybillid() {
    return (String) this.getAttributeValue(SoBalanceBVO.CPAYBILLID);
  }

  public void setCpaybillid(String cpaybillid) {
    this.setAttributeValue(SoBalanceBVO.CPAYBILLID, cpaybillid);
  }

  public String getCpaybillrowid() {
    return (String) this.getAttributeValue(SoBalanceBVO.CPAYBILLROWID);
  }

  public void setCpaybillrowid(String cpaybillrowid) {
    this.setAttributeValue(SoBalanceBVO.CPAYBILLROWID, cpaybillrowid);
  }

  public String getCprodlineid() {
    return (String) this.getAttributeValue(SoBalanceBVO.CPRODLINEID);
  }

  public void setCprodlineid(String cprodlineid) {
    this.setAttributeValue(SoBalanceBVO.CPRODLINEID, cprodlineid);
  }

  public String getCsobalancebid() {
    return (String) this.getAttributeValue(SoBalanceBVO.CSOBALANCEBID);
  }

  public void setCsobalancebid(String csobalancebid) {
    this.setAttributeValue(SoBalanceBVO.CSOBALANCEBID, csobalancebid);
  }

  public String getCsobalanceid() {
    return (String) this.getAttributeValue(SoBalanceBVO.CSOBALANCEID);
  }

  public void setCsobalanceid(String csobalanceid) {
    this.setAttributeValue(SoBalanceBVO.CSOBALANCEID, csobalanceid);
  }

  public UFDate getDarbalancedate() {
    return (UFDate) this.getAttributeValue(SoBalanceBVO.DARBALANCEDATE);
  }

  public void setDarbalancedate(UFDate darbalancedate) {
    this.setAttributeValue(SoBalanceBVO.DARBALANCEDATE, darbalancedate);
  }

  public UFDate getDarbilldate() {
    return (UFDate) this.getAttributeValue(SoBalanceBVO.DARBILLDATE);
  }

  public void setDarbilldate(UFDate darbilldate) {
    this.setAttributeValue(SoBalanceBVO.DARBILLDATE, darbilldate);
  }

  public Integer getDr() {
    return (Integer) this.getAttributeValue(SoBalanceBVO.DR);
  }

  public void setDr(Integer dr) {
    this.setAttributeValue(SoBalanceBVO.DR, dr);
  }

  public Integer getFibaltype() {
    return (Integer) this.getAttributeValue(SoBalanceBVO.FIBALTYPE);
  }

  public void setFibaltype(Integer fibaltype) {
    this.setAttributeValue(SoBalanceBVO.FIBALTYPE, fibaltype);
  }

  public UFDouble getNorigaccbalmny() {
    return (UFDouble) this.getAttributeValue(SoBalanceBVO.NORIGACCBALMNY);
  }

  public void setNorigaccbalmny(UFDouble norigaccbalmny) {
    this.setAttributeValue(SoBalanceBVO.NORIGACCBALMNY, norigaccbalmny);
  }

  public UFDouble getNorigordbalmny() {
    return (UFDouble) this.getAttributeValue(SoBalanceBVO.NORIGORDBALMNY);
  }

  public void setNorigordbalmny(UFDouble norigordbalmny) {
    this.setAttributeValue(SoBalanceBVO.NORIGORDBALMNY, norigordbalmny);
  }

  public UFDouble getNorigthisbalmny() {
    return (UFDouble) this.getAttributeValue(SoBalanceBVO.NORIGTHISBALMNY);
  }

  public void setNorigthisbalmny(UFDouble norigthisbalmny) {
    this.setAttributeValue(SoBalanceBVO.NORIGTHISBALMNY, norigthisbalmny);
  }

  public UFDouble getNorigarmny() {
    return (UFDouble) this.getAttributeValue(SoBalanceBVO.NORIGARMNY);
  }

  public void setNorigarmny(UFDouble norigarmny) {
    this.setAttributeValue(SoBalanceBVO.NORIGARMNY, norigarmny);
  }

  public UFDouble getNorigotherbalmny() {
    return (UFDouble) this.getAttributeValue(SoBalanceBVO.NORIGOTHERBALMNY);
  }

  public void setNorigotherbalmny(UFDouble norigotherbalmny) {
    this.setAttributeValue(SoBalanceBVO.NORIGOTHERBALMNY, norigotherbalmny);
  }

  public String getPk_org() {
    return (String) this.getAttributeValue(SoBalanceBVO.PK_ORG);
  }

  public void setPk_org(String pk_org) {
    this.setAttributeValue(SoBalanceBVO.PK_ORG, pk_org);
  }

  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SoBalanceBVO.TS);
  }

  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SoBalanceBVO.TS, ts);
  }

  public String getVarbillcode() {
    return (String) this.getAttributeValue(SoBalanceBVO.VARBILLCODE);
  }

  public void setVarbillcode(String varbillcode) {
    this.setAttributeValue(SoBalanceBVO.VARBILLCODE, varbillcode);
  }

  public UFBoolean getBpreceiveflag() {
    return (UFBoolean) this.getAttributeValue(SoBalanceBVO.BPRECEIVEFLAG);
  }

  public void setBpreceiveflag(UFBoolean bpreceiveflag) {
    this.setAttributeValue(SoBalanceBVO.BPRECEIVEFLAG, bpreceiveflag);
  }

}
