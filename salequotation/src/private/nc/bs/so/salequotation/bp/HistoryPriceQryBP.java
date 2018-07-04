package nc.bs.so.salequotation.bp;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.md.model.impl.MDEnum;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m4310trantype.entity.M4310TranTypeVO;
import nc.vo.so.pub.util.SOSysParaInitUtil;
import nc.vo.so.salequotation.entity.BillStatusEnum;
import nc.vo.so.salequotation.entity.FindHistoryPriceParameter;

/**
 * 报价单历史询价BP
 * 
 * @since 6.0
 * @version 2011-11-14 下午02:01:48
 * @author 王天文
 */
public class HistoryPriceQryBP {
  private static final int MONTH_DAYS = 30;

  private M4310TranTypeVO tranTypeVO;

  public HistoryPriceQryBP(M4310TranTypeVO tranTypeVO) {
    super();
    this.tranTypeVO = tranTypeVO;
  }

  public UFDouble[] findAvgPrice(FindHistoryPriceParameter[] paraVOs) {
    UFDouble[] retPrice = new UFDouble[paraVOs.length];

    for (int i = 0; i < retPrice.length; i++) {
      retPrice[i] = this.findAvgPrice(paraVOs[i]);
    }
    return retPrice;
  }

  public UFDouble findLastPrice(FindHistoryPriceParameter hisParaVO) {

    if (StringUtil.isEmptyWithTrim(hisParaVO.getPk_material())
        || StringUtil.isEmptyWithTrim(hisParaVO.getPk_group())
        || StringUtil.isEmptyWithTrim(hisParaVO.getPk_org())
        || hisParaVO.getTpricedate() == null) {
      return null;
    }
    SqlBuilder sql = new SqlBuilder();

    if (this.isTaxPrior()) {
      sql.append("SELECT ");
      sql.append("so_salequotation_b.nqtorigtaxnetprc ");
    }
    else {
      sql.append("SELECT ");
      sql.append("so_salequotation_b.nqtorignetprice ");
    }
    sql.append(" FROM so_salequotation inner join so_salequotation_b ");
    sql.append(" on so_salequotation.pk_salequotation = so_salequotation_b.pk_salequotation ");
    sql.append(" where ");

    MDEnum[] status = new MDEnum[] {
      BillStatusEnum.AUDIT, BillStatusEnum.CLOSE
    };
    sql.append(" so_salequotation.fstatusflag ", status);

    sql.append(" and ");
    sql.append(" so_salequotation_b.pk_material ", hisParaVO.getPk_material());

    sql.append(" and ");
    sql.append("so_salequotation.pk_group", hisParaVO.getPk_group());
    sql.append(" and ");
    sql.append("so_salequotation_b.pk_group", hisParaVO.getPk_group());

    sql.append(" and ");
    sql.append("so_salequotation.pk_org", hisParaVO.getPk_org());
    sql.append(" and ");
    sql.append("so_salequotation_b.pk_org", hisParaVO.getPk_org());

    sql.append(" and ");
    sql.append(" (so_salequotation.denddate ", " > ", hisParaVO.getTpricedate()
        .toString());
    sql.append(" or ");
    sql.append("so_salequotation.denddate is null )");
    //sql.append(" isnull(cast(so_salequotation.denddate as char),'~') = '~') ");

    sql.append(" and ");
    sql.append(" so_salequotation.dquotedate ", " < ", hisParaVO
        .getTpricedate().toString());

    this.appendMatchRuleSql(sql, hisParaVO);
    sql.append("   order by so_salequotation.dquotedate desc,"
        + "so_salequotation.vbillcode desc,so_salequotation_b.crowno desc ");

    DataAccessUtils datautil = new DataAccessUtils();
    IRowSet rs = datautil.query(sql.toString());
    if (rs.next()) {
      return rs.getUFDouble(0);
    }
    return null;
  }

  public UFDouble[] findLastPrice(FindHistoryPriceParameter[] hisParaVOs) {
    UFDouble[] retPrice = new UFDouble[hisParaVOs.length];
    for (int i = 0; i < retPrice.length; i++) {
      retPrice[i] = this.findLastPrice(hisParaVOs[i]);
    }
    return retPrice;
  }

  private void appendAreaClMatchSql(SqlBuilder sql,
      FindHistoryPriceParameter hisParaVO) {

    // 收货地区匹配历史报价
    if (this.tranTypeVO.getBareamatrule() == UFBoolean.TRUE) {
      if (!StringUtil.isEmptyWithTrim(hisParaVO.getPk_areacl())) {
        sql.append(" and ");
        sql.append(" so_salequotation_b.pk_areacl ", hisParaVO.getPk_areacl());
      }
      else {
        sql.append(" and ");
        String name=" so_salequotation_b.pk_areacl ";
        sql.appendIDIsNull(name);
      }
    }
  }

  private void appendBalaMatchSql(SqlBuilder sql,
      FindHistoryPriceParameter hisParaVO) {

    // 结算方式匹配历史报价
    if (this.tranTypeVO.getBbalatypematrule() == UFBoolean.TRUE) {
      if (!StringUtil.isEmptyWithTrim(hisParaVO.getPk_balatype())) {
        sql.append(" and ");
        sql.append(" so_salequotation.pk_balatype ", hisParaVO.getPk_balatype());
      }
      else {
        sql.append(" and ");
        sql.append(" so_salequotation.pk_balatype = '~' ");
      }
    }
  }

  private void appendChannelMatchSql(SqlBuilder sql,
      FindHistoryPriceParameter hisParaVO) {

    // 渠道类型匹配历史报价
    if (this.tranTypeVO.getBchanmatrule() == UFBoolean.TRUE) {
      if (!StringUtil.isEmptyWithTrim(hisParaVO.getPk_channeltype())) {
        sql.append(" and ");
        sql.append(" so_salequotation.pk_channeltype ",
            hisParaVO.getPk_channeltype());
      }
      else {
        sql.append(" and ");
        sql.append(" so_salequotation.pk_channeltype = '~' ");
      }
    }
  }

  private void appendCurrMatchSql(SqlBuilder sql,
      FindHistoryPriceParameter hisParaVO) {

    // 币种匹配历史报价
    if (this.tranTypeVO.getBcurrtypematrule() == UFBoolean.TRUE) {
      if (!StringUtil.isEmptyWithTrim(hisParaVO.getPk_currtype())) {
        sql.append(" and ");
        sql.append(" so_salequotation.pk_currtype ", hisParaVO.getPk_currtype());
      }
      else {
        sql.append(" and ");
        sql.append(" so_salequotation.pk_currtype = '~' ");
      }
    }
  }

  private void appendCustMatchSql(SqlBuilder sql,
      FindHistoryPriceParameter hisParaVO) {

    // 客户匹配历史报价
    if (this.tranTypeVO.getBcustmatrule() == UFBoolean.TRUE) {
      if (!StringUtil.isEmptyWithTrim(hisParaVO.getPk_customer())) {
        sql.append(" and ");
        sql.append(" so_salequotation.pk_customer ", hisParaVO.getPk_customer());
      }
      else {
        sql.append(" and ");
        sql.append(" so_salequotation.pk_customer = '~' ");
      }
    }
  }

  private void appendDeptMatchSql(SqlBuilder sql,
      FindHistoryPriceParameter hisParaVO) {

    // 部门匹配历史报价
    if (this.tranTypeVO.getBdeptmatrule() == UFBoolean.TRUE) {
      if (!StringUtil.isEmptyWithTrim(hisParaVO.getPk_dept())) {
        sql.append(" and ");
        sql.append(" so_salequotation.pk_dept ", hisParaVO.getPk_dept());
      }
      else {
        sql.append(" and so_salequotation.pk_dept = '~' ");
      }
    }
  }

  private void appendEmpMatchSql(SqlBuilder sql,
      FindHistoryPriceParameter hisParaVO) {

    // 业务员匹配历史报价
    if (this.tranTypeVO.getBempmatrule() == UFBoolean.TRUE) {
      if (!StringUtil.isEmptyWithTrim(hisParaVO.getCemployeeid())) {
        sql.append(" and ");
        sql.append(" so_salequotation.cemployeeid ", hisParaVO.getCemployeeid());
      }
      else {
        sql.append(" and so_salequotation.cemployeeid = '~' ");
      }
    }
  }

  private void appendMatchRuleSql(SqlBuilder sql,
      FindHistoryPriceParameter hisParaVO) {

    this.appendCustMatchSql(sql, hisParaVO);
    this.appendChannelMatchSql(sql, hisParaVO);
    this.appendTranTypeMatchSql(sql, hisParaVO);
    this.appendDeptMatchSql(sql, hisParaVO);
    this.appendEmpMatchSql(sql, hisParaVO);
    this.appendPaytermMatchSql(sql, hisParaVO);
    this.appendBalaMatchSql(sql, hisParaVO);
    this.appendCurrMatchSql(sql, hisParaVO);
    this.appendUnitMatchSql(sql, hisParaVO);
    this.appendSendtypeMatchSql(sql, hisParaVO);
    this.appendQuallevelMatchSql(sql, hisParaVO);
    this.appendAreaClMatchSql(sql, hisParaVO);
  }

  private void appendPaytermMatchSql(SqlBuilder sql,
      FindHistoryPriceParameter hisParaVO) {

    // 收付款方式匹配历史报价
    if (this.tranTypeVO.getBpaytermmatrule() == UFBoolean.TRUE) {
      if (!StringUtil.isEmptyWithTrim(hisParaVO.getPk_payterm())) {
        sql.append(" and ");
        sql.append(" so_salequotation.pk_payterm ", hisParaVO.getPk_payterm());
      }
      else {
        sql.append(" and ");
        sql.append(" so_salequotation.pk_payterm = '~' ");
      }
    }
  }

  private void appendQuallevelMatchSql(SqlBuilder sql,
      FindHistoryPriceParameter hisParaVO) {
    // 质量等级匹配历史报价
    if (this.tranTypeVO.getBquallevelmatrule() == UFBoolean.TRUE) {
      if (!StringUtil.isEmptyWithTrim(hisParaVO.getPk_qualitylevel())) {
        sql.append(" and ");
        sql.append(" so_salequotation_b.pk_qualitylevel ",
            hisParaVO.getPk_qualitylevel());
      }
      else {
        sql.append(" and ");
        sql.append(" so_salequotation_b.pk_qualitylevel = '~' ");
      }
    }
  }

  private void appendSendtypeMatchSql(SqlBuilder sql,
      FindHistoryPriceParameter hisParaVO) {
    // 运输方式匹配历史报价
    if (this.tranTypeVO.getBsendtypematrule() == UFBoolean.TRUE) {
      if (!StringUtil.isEmptyWithTrim(hisParaVO.getCsendtypeid())) {
        sql.append(" and ");
        sql.append(" so_salequotation.csendtypeid ", hisParaVO.getCsendtypeid());
      }
      else {
        sql.append(" and ");
        sql.append(" so_salequotation.csendtypeid = '~' ");
      }
    }
  }

  private void appendTranTypeMatchSql(SqlBuilder sql,
      FindHistoryPriceParameter hisParaVO) {
    // 报价单类型匹配历史报价
    if (this.tranTypeVO.getBquotypematrule() == UFBoolean.TRUE) {
      if (!StringUtil.isEmptyWithTrim(hisParaVO.getVtrantype())) {
        sql.append(" and ");
        sql.append(" so_salequotation.vtrantype ", hisParaVO.getVtrantype());
      }
      else {
        sql.append(" and ");
        sql.append(" so_salequotation.vtrantype = '~' ");
      }
    }
  }

  private void appendUnitMatchSql(SqlBuilder sql,
      FindHistoryPriceParameter hisParaVO) {
    // 报价单位匹配历史报价
    if (this.tranTypeVO.getBunitmatrule() == UFBoolean.TRUE) {
      if (!StringUtil.isEmptyWithTrim(hisParaVO.getCqtunitid())) {
        sql.append(" and ");
        sql.append(" so_salequotation_b.cqtunitid ", hisParaVO.getCqtunitid());
      }
      else {
        sql.append(" and ");
        sql.append(" so_salequotation_b.cqtunitid = '~' ");
      }
    }
  }

  private UFDouble findAvgPrice(FindHistoryPriceParameter hisParaVO) {

    if (StringUtil.isEmptyWithTrim(hisParaVO.getPk_material())
        || StringUtil.isEmptyWithTrim(hisParaVO.getPk_group())
        || StringUtil.isEmptyWithTrim(hisParaVO.getPk_org())
        || hisParaVO.getTpricedate() == null) {
      return null;
    }

    int iavgmonth = this.tranTypeVO.getIavgmonth().intValue();
    UFDateTime currTime = hisParaVO.getTpricedate();
    int currDaysOfMonth = HistoryPriceQryBP.MONTH_DAYS;
    UFDateTime bDateTime =
        currTime.getDateTimeBefore(iavgmonth * currDaysOfMonth);
    UFDateTime eDateTime = currTime.getDateTimeAfter(1);

    SqlBuilder sql = new SqlBuilder();
    if (this.isTaxPrior()) {
      sql.append(" SELECT ");
      sql.append(" sum(norigtaxmny)/sum(nassistnum) ")/*含税净价数量加权平均*/;
    }
    else {
      sql.append(" SELECT ");
      sql.append(" sum(norigmny)/sum(nassistnum) ")/*无税净价数量加权平均*/;
    }
    sql.append(" FROM so_salequotation inner join so_salequotation_b ");
    sql.append(" on so_salequotation.pk_salequotation = so_salequotation_b.pk_salequotation ");
    sql.append(" where ");
    MDEnum[] status = new MDEnum[] {
      BillStatusEnum.AUDIT, BillStatusEnum.CLOSE
    };
    sql.append(" so_salequotation.fstatusflag", status);

    sql.append(" and ");
    sql.append("so_salequotation_b.pk_material", hisParaVO.getPk_material());

    sql.append(" and ");
    sql.append("so_salequotation.dquotedate ", ">", bDateTime.toString());

    sql.append(" and ");
    sql.append("so_salequotation.dquotedate", "<=", eDateTime.toString());

    sql.append(" and ");
    sql.append("so_salequotation.pk_group", hisParaVO.getPk_group());

    sql.append(" and ");
    sql.append("so_salequotation_b.pk_group", hisParaVO.getPk_group());

    sql.append(" and ");
    sql.append("so_salequotation.pk_org", hisParaVO.getPk_org());
    sql.append(" and ");
    sql.append("so_salequotation_b.pk_org", hisParaVO.getPk_org());

    sql.append(" and ");
    sql.append(" (so_salequotation.denddate ", " > ", hisParaVO.getTpricedate()
        .toString());
    sql.append(" or ");
    sql.append(" isnull(cast(so_salequotation.denddate as char),'~') = '~') ");

    sql.append(" and ");
    sql.append(" so_salequotation.dquotedate ", " < ", hisParaVO
        .getTpricedate().toString());

    this.appendMatchRuleSql(sql, hisParaVO);
    DataAccessUtils datautil = new DataAccessUtils();
    IRowSet rs = datautil.query(sql.toString());
    if (rs.next()) {
      return rs.getUFDouble(0);
    }
    return null;
  }

  private boolean isTaxPrior() {
    UFBoolean so23 = null;

    String pk_group = BSContext.getInstance().getGroupID();
    so23 = SOSysParaInitUtil.getSO23(pk_group);

    if (null == so23) {
      return false;
    }
    return so23.booleanValue();
  }
}
