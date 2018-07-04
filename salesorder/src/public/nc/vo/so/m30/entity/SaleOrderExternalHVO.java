package nc.vo.so.m30.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * 销售订单主表VO
 * 
 * @since 6.3
 * @version 2013-5-24 上午09:55:34
 * @author dongli2
 */

public class SaleOrderExternalHVO extends SuperVO {

  /**
   * 序列化
   */
  private static final long serialVersionUID = -9194238805817916447L;

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("so.so_saleorder");
    return meta;
  }

  /**
   * 审批人
   */
  public static final String APPROVER = "approver";

  /**
   * 代垫运费
   */
  public static final String BADVFEEFLAG = "badvfeeflag";

  /**
   * 收入结算关闭
   */
  public static final String BARSETTLEFLAG = "barsettleflag";

  /**
   * 已协同生成采购订单
   */
  public static final String BCOOPTOPOFLAG = "bcooptopoflag";

  /**
   * 成本结算关闭
   */
  public static final String BCOSTSETTLEFLAG = "bcostsettleflag";

  /**
   * 是否散户
   */
  public static final String BFREECUSTFLAG = "bfreecustflag";

  /**
   * 制单人
   */
  public static final String BILLMAKER = "billmaker";

  /**
   * 开票关闭
   */
  public static final String BINVOICENDFLAG = "binvoicendflag";

  /**
   * 是否冲抵
   */
  public static final String BOFFSETFLAG = "boffsetflag";

  /**
   * 出库关闭
   */
  public static final String BOUTENDFLAG = "boutendflag";

  /**
   * 由采购订单协同生成
   */
  public static final String BPOCOOPTOMEFLAG = "bpocooptomeflag";

  /**
   * 收款限额控制预收
   */
  public static final String BPRECEIVEFLAG = "bpreceiveflag";

  /**
   * 发货关闭
   */
  public static final String BSENDENDFLAG = "bsendendflag";

  /**
   * 结算方式
   */
  public static final String CBALANCETYPEID = "cbalancetypeid";

  /**
   * 业务流程
   */
  public static final String CBIZTYPEID = "cbiztypeid";

  /**
   * 销售渠道类型
   */
  public static final String CCHANNELTYPEID = "cchanneltypeid";

  /**
   * 开户银行账户
   */
  public static final String CCUSTBANKACCID = "ccustbankaccid";

  /**
   * 开户银行
   */
  public static final String CCUSTBANKID = "ccustbankid";

  /**
   * 客户
   */
  public static final String CCUSTOMERID = "ccustomerid";

  /**
   * 部门最新版本
   */
  public static final String CDEPTID = "cdeptid";

  /**
   * 部门
   */
  public static final String CDEPTVID = "cdeptvid";

  /**
   * 业务员
   */
  public static final String CEMPLOYEEID = "cemployeeid";

  /**
   * 散户
   */
  public static final String CFREECUSTID = "cfreecustid";

  /**
   * 开票客户
   */
  public static final String CINVOICECUSTID = "cinvoicecustid";

  /**
   * 原币
   */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  /**
   * 收款协议
   */
  public static final String CPAYTERMID = "cpaytermid";

  /**
   * 创建时间
   */
  public static final String CREATIONTIME = "creationtime";

  /**
   * 创建人
   */
  public static final String CREATOR = "creator";

  /**
   * 修订人
   */
  public static final String CREVISERID = "creviserid";

  /**
   * 销售主表ID
   */
  public static final String CSALEORDERID = "csaleorderid";

  /**
   * 贸易术语（61）
   */
  public static final String CTRADEWORDID = "ctradewordid";

  /**
   * 运输方式
   */
  public static final String CTRANSPORTTYPEID = "ctransporttypeid";

  /**
   * 交易类型
   */
  public static final String CTRANTYPEID = "ctrantypeid";

  /**
   * 单据日期
   */
  public static final String DBILLDATE = "dbilldate";

  /**
   * 计算属性：下游目的单据主组织
   */
  public static final String DEST_PK_ORG = "dest_pk_org";

  /**
   * 制单日期
   */
  public static final String DMAKEDATE = "dmakedate";

  /**
   * dr
   */
  public static final String DR = "dr";

  /**
   * 审批流状态
   */
  public static final String FPFSTATUSFLAG = "fpfstatusflag";

  /**
   * 单据状态
   */
  public static final String FSTATUSFLAG = "fstatusflag";

  /**
   * 打印次数
   */
  public static final String IPRINTCOUNT = "iprintcount";

  /**
   * 修订版本号
   */
  public static final String IVERSION = "iversion";

  /**
   * 修改时间
   */
  public static final String MODIFIEDTIME = "modifiedtime";

  /**
   * 修改人
   */
  public static final String MODIFIER = "modifier";

  /**
   * 整单折扣
   */
  public static final String NDISCOUNTRATE = "ndiscountrate";

  /**
   * 实际预收款金额
   */
  public static final String NPRECEIVEMNY = "npreceivemny";

  /**
   * 订单收款限额
   */
  public static final String NPRECEIVEQUOTA = "npreceivequota";

  /**
   * 订单收款比例
   */
  public static final String NPRECEIVERATE = "npreceiverate";

  /**
   * 实际收款金额
   */
  public static final String NRECEIVEDMNY = "nreceivedmny";

  /**
   * 计算属性：本次收款金额
   */
  public static final String NTHISRECEIVEMNY = "nthisreceivemny";

  /**
   * ntotalmny
   */
  public static final String NTOTALMNY = "ntotalmny";

  /**
   * 合计数量
   */
  public static final String NTOTALNUM = "ntotalnum";

  /**
   * 金额合计(价税合计)
   */
  public static final String NTOTALORIGMNY = "ntotalorigmny";

  /**
   * 费用冲抵金额
   */
  public static final String NTOTALORIGSUBMNY = "ntotalorigsubmny";

  /**
   * 总件数
   */
  public static final String NTOTALPIECE = "ntotalpiece";

  /**
   * 合计体积
   */
  public static final String NTOTALVOLUME = "ntotalvolume";

  /**
   * 合计重量
   */
  public static final String NTOTALWEIGHT = "ntotalweight";

  /**
   * 集团
   */
  public static final String PK_GROUP = "pk_group";

  /**
   * 销售组织
   */
  public static final String PK_ORG = "pk_org";

  /**
   * 销售组织版本
   */
  public static final String PK_ORG_V = "pk_org_v";

  /**
   * 审批时间
   */
  public static final String TAUDITTIME = "taudittime";

  /**
   * 修订时间
   */
  public static final String TREVISETIME = "trevisetime";

  /**
   * 时间戳
   */
  public static final String TS = "ts";

  /**
   * 单据号
   */
  public static final String VBILLCODE = "vbillcode";

  /**
   * 对方订单号
   */
  public static final String VCOOPPOHCODE = "vcooppohcode";

  /**
   * 信用证号
   */
  public static final String VCREDITNUM = "vcreditnum";

  /**
   * 自定义项1
   */
  public static final String VDEF1 = "vdef1";

  /**
   * 自定义项10
   */
  public static final String VDEF10 = "vdef10";

  /**
   * 自定义项11
   */
  public static final String VDEF11 = "vdef11";

  /**
   * 自定义项12
   */
  public static final String VDEF12 = "vdef12";

  /**
   * 自定义项13
   */
  public static final String VDEF13 = "vdef13";

  /**
   * 自定义项14
   */
  public static final String VDEF14 = "vdef14";

  /**
   * 自定义项15
   */
  public static final String VDEF15 = "vdef15";

  /**
   * 自定义项16
   */
  public static final String VDEF16 = "vdef16";

  /**
   * 自定义项17
   */
  public static final String VDEF17 = "vdef17";

  /**
   * 自定义项18
   */
  public static final String VDEF18 = "vdef18";

  /**
   * 自定义项19
   */
  public static final String VDEF19 = "vdef19";

  /**
   * 自定义项2
   */
  public static final String VDEF2 = "vdef2";

  /**
   * 自定义项20
   */
  public static final String VDEF20 = "vdef20";

  /**
   * 自定义项3
   */
  public static final String VDEF3 = "vdef3";

  /**
   * 自定义项4
   */
  public static final String VDEF4 = "vdef4";

  /**
   * 自定义项5
   */
  public static final String VDEF5 = "vdef5";

  /**
   * 自定义项6
   */
  public static final String VDEF6 = "vdef6";

  /**
   * 自定义项7
   */
  public static final String VDEF7 = "vdef7";

  /**
   * 自定义项8
   */
  public static final String VDEF8 = "vdef8";

  /**
   * 自定义项9
   */
  public static final String VDEF9 = "vdef9";

  /**
   * 备注
   */
  public static final String VNOTE = "vnote";

  /**
   * 修订理由
   */
  public static final String VREVISEREASON = "vrevisereason";

  /**
   * 交易类型编码
   */
  public static final String VTRANTYPECODE = "vtrantypecode";

  /**
   * 获取销售组织
   * 
   * @return销售组织
   */
  public String getPk_org() {
    return (String) this.getAttributeValue(PK_ORG);
  }

  /**
   * 设置销售组织
   * 
   * @param pk_org销售组织
   */
  public void setPk_org(final String pk_org) {
    this.setAttributeValue(PK_ORG, pk_org);
  }

  /**
   * 获取交易类型
   * 
   * @return交易类型
   */
  public String getCtrantypeid() {
    return (String) this.getAttributeValue(CTRANTYPEID);
  }

  /**
   * 设置交易类型
   * 
   * @param ctrantypeid交易类型
   */
  public void setCtrantypeid(final String ctrantypeid) {
    this.setAttributeValue(CTRANTYPEID, ctrantypeid);
  }

  /**
   * 获取单据号
   * 
   * @return单据号
   */
  public String getVbillcode() {
    return (String) this.getAttributeValue(VBILLCODE);
  }

  /**
   * 设置单据号
   * 
   * @param vbillcode单据号
   */
  public void setVbillcode(final String vbillcode) {
    this.setAttributeValue(VBILLCODE, vbillcode);
  }

  /**
   * 获取业务流程
   * 
   * @return业务流程
   */
  public String getCbiztypeid() {
    return (String) this.getAttributeValue(CBIZTYPEID);
  }

  /**
   * 设置业务流程
   * 
   * @param cbiztypeid业务流程
   */
  public void setCbiztypeid(final String cbiztypeid) {
    this.setAttributeValue(CBIZTYPEID, cbiztypeid);
  }

  /**
   * 获取单据日期
   * 
   * @return单据日期
   */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(DBILLDATE);
  }

  /**
   * 设置单据日期
   * 
   * @param dbilldate单据日期
   */
  public void setDbilldate(final UFDate dbilldate) {
    this.setAttributeValue(DBILLDATE, dbilldate);
  }

  /**
   * 获取客户
   * 
   * @return客户
   */
  public String getCcustomerid() {
    return (String) this.getAttributeValue(CCUSTOMERID);
  }

  /**
   * 设置客户
   * 
   * @param ccustomerid客户
   */
  public void setCcustomerid(final String ccustomerid) {
    this.setAttributeValue(CCUSTOMERID, ccustomerid);
  }

  /**
   * 获取散户
   * 
   * @return散户
   */
  public String getCfreecustid() {
    return (String) this.getAttributeValue(CFREECUSTID);
  }

  /**
   * 设置散户
   * 
   * @param cfreecustid散户
   */
  public void setCfreecustid(final String cfreecustid) {
    this.setAttributeValue(CFREECUSTID, cfreecustid);
  }

  /**
   * 获取销售渠道类型
   * 
   * @return销售渠道类型
   */
  public String getCchanneltypeid() {
    return (String) this.getAttributeValue(CCHANNELTYPEID);
  }

  /**
   * 设置销售渠道类型
   * 
   * @param cchanneltypeid销售渠道类型
   */
  public void setCchanneltypeid(final String cchanneltypeid) {
    this.setAttributeValue(CCHANNELTYPEID, cchanneltypeid);
  }

  /**
   * 获取业务员
   * 
   * @return业务员
   */
  public String getCemployeeid() {
    return (String) this.getAttributeValue(CEMPLOYEEID);
  }

  /**
   * 设置业务员
   * 
   * @param cemployeeid业务员
   */
  public void setCemployeeid(final String cemployeeid) {
    this.setAttributeValue(CEMPLOYEEID, cemployeeid);
  }

  /**
   * 获取部门
   * 
   * @return部门
   */
  public String getCdeptvid() {
    return (String) this.getAttributeValue(CDEPTVID);
  }

  /**
   * 设置部门
   * 
   * @param cdeptvid部门
   */
  public void setCdeptvid(final String cdeptvid) {
    this.setAttributeValue(CDEPTVID, cdeptvid);
  }

  /**
   * 获取开票客户
   * 
   * @return开票客户
   */
  public String getCinvoicecustid() {
    return (String) this.getAttributeValue(CINVOICECUSTID);
  }

  /**
   * 设置开票客户
   * 
   * @param cinvoicecustid开票客户
   */
  public void setCinvoicecustid(final String cinvoicecustid) {
    this.setAttributeValue(CINVOICECUSTID, cinvoicecustid);
  }

  /**
   * 获取开户银行
   * 
   * @return开户银行
   */
  public String getCcustbankid() {
    return (String) this.getAttributeValue(CCUSTBANKID);
  }

  /**
   * 设置开户银行
   * 
   * @param ccustbankid开户银行
   */
  public void setCcustbankid(final String ccustbankid) {
    this.setAttributeValue(CCUSTBANKID, ccustbankid);
  }

  /**
   * 获取开户银行账户
   * 
   * @return开户银行账户
   */
  public String getCcustbankaccid() {
    return (String) this.getAttributeValue(CCUSTBANKACCID);
  }

  /**
   * 设置开户银行账户
   * 
   * @param ccustbankaccid开户银行账户
   */
  public void setCcustbankaccid(final String ccustbankaccid) {
    this.setAttributeValue(CCUSTBANKACCID, ccustbankaccid);
  }

  /**
   * 获取收款协议
   * 
   * @return
   */
  public String getCpaytermid() {
    return (String) this.getAttributeValue(CPAYTERMID);
  }

  /**
   * 设置收款协议
   * 
   * @param cpaytermid
   */
  public void setCpaytermid(final String cpaytermid) {
    this.setAttributeValue(CPAYTERMID, cpaytermid);
  }

  /**
   * 获取原币
   * 
   * @return原币
   */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(CORIGCURRENCYID);
  }

  /**
   * 设置原币
   * 
   * @param corigcurrencyid原币
   */
  public void setCorigcurrencyid(final String corigcurrencyid) {
    this.setAttributeValue(CORIGCURRENCYID, corigcurrencyid);
  }

  /**
   * 获取运输方式
   * 
   * @return运输方式
   */
  public String getCtransporttypeid() {
    return (String) this.getAttributeValue(CTRANSPORTTYPEID);
  }

  /**
   * 设置运输方式
   * 
   * @param ctransporttypeid运输方式
   */
  public void setCtransporttypeid(final String ctransporttypeid) {
    this.setAttributeValue(CTRANSPORTTYPEID, ctransporttypeid);
  }

  /**
   * 获取贸易术语
   * 
   * @return贸易术语
   */
  public String getCtradewordid() {
    return (String) this.getAttributeValue(CTRADEWORDID);
  }

  /**
   * 设置贸易术语
   * 
   * @param ctradewordid贸易术语
   */
  public void setCtradewordid(final String ctradewordid) {
    this.setAttributeValue(CTRADEWORDID, ctradewordid);
  }

  /**
   * 获取信用证号
   * 
   * @return信用证号
   */
  public String getVcreditnum() {
    return (String) this.getAttributeValue(VCREDITNUM);
  }

  /**
   * 设置信用账号
   * 
   * @param vcreditnum信用证号
   */
  public void setVcreditnum(final String vcreditnum) {
    this.setAttributeValue(VCREDITNUM, vcreditnum);
  }

  /**
   * 获取结算方式
   * 
   * @return结算方式
   */
  public String getCbalancetypeid() {
    return (String) this.getAttributeValue(CBALANCETYPEID);
  }

  /**
   * 设置结算方式
   * 
   * @param cbalancetypeid结算方式
   */
  public void setCbalancetypeid(final String cbalancetypeid) {
    this.setAttributeValue(CBALANCETYPEID, cbalancetypeid);
  }

  /**
   * 获取代垫运费
   * 
   * @return代垫运费
   */
  public String getBadvfreeflag() {
    return (String) this.getAttributeValue(BADVFEEFLAG);
  }

  /**
   * 设置代垫运费
   * 
   * @param badvfreeflag代垫运费
   */
  public void setBadvfreeflag(final String badvfeeflag) {
    this.setAttributeValue(BADVFEEFLAG, badvfeeflag);
  }

  /**
   * 获取备注
   * 
   * @return备注
   */
  public String getVnote() {
    return (String) this.getAttributeValue(VNOTE);
  }

  /**
   * 设置备注
   * 
   * @param vnote
   */
  public void setVnote(final String vnote) {
    this.setAttributeValue(VNOTE, vnote);
  }

  public String getVdef1() {
    return (String) this.getAttributeValue(VDEF1);
  }

  public void setVdef1(final String vdef1) {
    this.setAttributeValue(VDEF1, vdef1);
  }

  public String getVdef2() {
    return (String) this.getAttributeValue(VDEF2);
  }

  public void setVdef2(final String vdef2) {
    this.setAttributeValue(VDEF2, vdef2);
  }

  public String getVdef3() {
    return (String) this.getAttributeValue(VDEF3);
  }

  public void setVdef3(final String vdef3) {
    this.setAttributeValue(VDEF3, vdef3);
  }

  public String getVdef4() {
    return (String) this.getAttributeValue(VDEF4);
  }

  public void setVdef4(final String vdef4) {
    this.setAttributeValue(VDEF4, vdef4);
  }

  public String getVdef5() {
    return (String) this.getAttributeValue(VDEF5);
  }

  public void setVdef5(final String vdef5) {
    this.setAttributeValue(VDEF5, vdef5);
  }

  public String getVdef6() {
    return (String) this.getAttributeValue(VDEF6);
  }

  public void setVdef6(final String vdef6) {
    this.setAttributeValue(VDEF6, vdef6);
  }

  public String getVdef7() {
    return (String) this.getAttributeValue(VDEF7);
  }

  public void setVdef7(final String vdef7) {
    this.setAttributeValue(VDEF7, vdef7);
  }

  public String getVdef8() {
    return (String) this.getAttributeValue(VDEF8);
  }

  public void setVdef8(final String vdef8) {
    this.setAttributeValue(VDEF8, vdef8);
  }

  public String getVdef9() {
    return (String) this.getAttributeValue(VDEF9);
  }

  public void setVdef9(final String vdef9) {
    this.setAttributeValue(VDEF9, vdef9);
  }

  public String getVdef10() {
    return (String) this.getAttributeValue(VDEF10);
  }

  public void setVdef10(final String vdef10) {
    this.setAttributeValue(VDEF10, vdef10);
  }

  public String getVdef11() {
    return (String) this.getAttributeValue(VDEF11);
  }

  public void setVdef11(final String vdef11) {
    this.setAttributeValue(VDEF11, vdef11);
  }

  public String getVdef12() {
    return (String) this.getAttributeValue(VDEF12);
  }

  public void setVdef12(final String vdef12) {
    this.setAttributeValue(VDEF12, vdef12);
  }

  public String getVdef13() {
    return (String) this.getAttributeValue(VDEF13);
  }

  public void setVdef13(final String vdef13) {
    this.setAttributeValue(VDEF13, vdef13);
  }

  public String getVdef14() {
    return (String) this.getAttributeValue(VDEF14);
  }

  public void setVdef14(final String vdef14) {
    this.setAttributeValue(VDEF14, vdef14);
  }

  public String getVdef15() {
    return (String) this.getAttributeValue(VDEF15);
  }

  public void setVdef15(final String vdef15) {
    this.setAttributeValue(VDEF15, vdef15);
  }

  public String getVdef16() {
    return (String) this.getAttributeValue(VDEF16);
  }

  public void setVdef16(final String vdef16) {
    this.setAttributeValue(VDEF16, vdef16);
  }

  public String getVdef17() {
    return (String) this.getAttributeValue(VDEF17);
  }

  public void setVdef17(final String vdef17) {
    this.setAttributeValue(VDEF17, vdef17);
  }

  public String getVdef18() {
    return (String) this.getAttributeValue(VDEF18);
  }

  public void setVdef18(final String vdef18) {
    this.setAttributeValue(VDEF18, vdef18);
  }

  public String getVdef19() {
    return (String) this.getAttributeValue(VDEF19);
  }

  public void setVdef19(final String vdef19) {
    this.setAttributeValue(VDEF19, vdef19);
  }

  public String getVdef20() {
    return (String) this.getAttributeValue(VDEF20);
  }

  public void setVdef20(final String vdef20) {
    this.setAttributeValue(VDEF20, vdef20);
  }

  /**
   * 获取制单人
   * 
   * @return 制单人
   */
  public String getBillmaker() {
    return (String) this.getAttributeValue(BILLMAKER);
  }

  /**
   * 设置制单人
   * 
   * @param billmaker 制单人
   */
  public void setBillmaker(final String billmaker) {
    this.setAttributeValue(BILLMAKER, billmaker);
  }

  /**
   * 获取制单日期
   * 
   * @return制单日期
   */
  public UFDate getDmakedate() {
    return (UFDate) this.getAttributeValue(DMAKEDATE);
  }

  /**
   * 设置制单日期
   * 
   * @param dmakedate制单日期
   */
  public void setDmakedate(final UFDate dmakedate) {
    this.setAttributeValue(DMAKEDATE, dmakedate);
  }

}
