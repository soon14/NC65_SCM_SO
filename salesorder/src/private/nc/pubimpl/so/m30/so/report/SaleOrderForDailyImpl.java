package nc.pubimpl.so.m30.so.report;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.pubitf.so.m30.so.report.ISaleOrderForDaily;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.MultiLangText;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.paravo.OrderFormReportParaVO;
import nc.vo.so.m30.paravo.OrderReturnToReportVO;

/**
 * 销售订单提供给综合日报的接口实现类
 * 
 * @since 6.0
 * @version 2011-1-21 上午08:44:33
 * @author 么贵敬
 */
public class SaleOrderForDailyImpl implements ISaleOrderForDaily {

  @Override
  public OrderReturnToReportVO[] getDailyDataFromOrder(
      OrderFormReportParaVO paravo) throws BusinessException {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select t1.csaleorderid ");
    String fromwhere = this.getFormWhere(paravo);
    sql.append(fromwhere);

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql.toString());
    String[] hids = rowset.toOneDimensionStringArray();

    SaleOrderVO[] bills = null;
    BillQuery<SaleOrderVO> query =
        new BillQuery<SaleOrderVO>(SaleOrderVO.class);
    bills = query.query(hids);

    OrderReturnToReportVO[] retvo = this.getPavaVOs(bills);

    return retvo;
  }

  private void appendCustCondtion(OrderFormReportParaVO paravo, SqlBuilder where) {
    // 订单客户编码
    String[] ccustomerid = paravo.getCcustomerids();
    if (null != ccustomerid && ccustomerid.length > 0) {
      where.append(" and ");
      where.append("t1.ccustomerid", ccustomerid);
    }

    // 订单客户名称
    MultiLangText ccustomername = paravo.getCcustomernames();
    if (null != ccustomername) {
      where.append(" and ");
      where.append("t5.name like '" + ccustomername.getText() + "%'");
    }

    // 订单客户基本分类
    String[] pk_custclass = paravo.getPk_custclass();
    if (null != pk_custclass && pk_custclass.length > 0) {
      where.append(" and ");
      where.append("t5.pk_custclass", pk_custclass);
    }

    // 订单客户销售分类
    String[] pk_custsaleclass = paravo.getPk_custsaleclass();
    if (null != pk_custsaleclass && pk_custsaleclass.length > 0) {
      where.append(" and ");
      where.append("t6.pk_custsaleclass", pk_custsaleclass);
    }

    // 订单客户地区分类
    String[] ccustomerpk_areacl = paravo.getCustomerpk_areacls();
    if (null != ccustomerpk_areacl && ccustomerpk_areacl.length > 0) {
      where.append(" and ");
      where.append("t5.pk_areacl", ccustomerpk_areacl);
    }
  }

  private void appendDbilldateAndSaleOrg(OrderFormReportParaVO paravo,
      SqlBuilder where, boolean ishasbodyitem) {
    where.startParentheses();
    where.append("t1.dbilldate", ">=", paravo.getStartdate().toString());
    where.append(" and ");
    where.append("t1.dbilldate", "<=", paravo.getEnddate().toLocalString());
    where.endParentheses();
    // 销售组织
    String[] saleorgid = paravo.getSaleorgids();
    if (null != saleorgid && saleorgid.length > 0) {
      where.append(" and ");
      where.append("t1.pk_org", saleorgid);
    }

    if (ishasbodyitem) {
      where.append(" and ");
      where.startParentheses();
      where.append("t2.dbilldate", ">=", paravo.getStartdate().toString());
      where.append(" and ");
      where.append("t2.dbilldate", "<=", paravo.getEnddate().toString());
      where.endParentheses();
      if (null != saleorgid && saleorgid.length > 0) {
        where.append(" and ");
        where.append("t2.pk_org", saleorgid);
      }
    }
  }

  private void appendFixCondtion(boolean ishasbodyitem, SqlBuilder where) {
    String pk_group = BSContext.getInstance().getGroupID();
    where.append(" and ");
    where.append("t1.pk_group", pk_group);
    where.append(" and ");
    where.append("t1.dr", 0);
    if (ishasbodyitem) {
      where.append(" and ");
      where.append("t2.dr", 0);
    }
  }

  private void appendMaterialCondtion(OrderFormReportParaVO paravo,
      SqlBuilder where) {

    // 物料编码
    String[] cmaterialvid = paravo.getCmaterialvids();
    if (null != cmaterialvid && cmaterialvid.length > 0) {
      where.append(" and ");
      where.append("t2.cmaterialvid", cmaterialvid);
    }
    // 物料名称
    MultiLangText cmaterialvname = paravo.getCmaterialvname();
    if (null != cmaterialvname) {
      where.append(" and ");
      where.append("t3.name like '" + cmaterialvname.getText() + "%'");
    }
    // 物料基本分类
    String[] pk_marbasclass = paravo.getPk_marbasclass();
    if (null != pk_marbasclass && pk_marbasclass.length > 0) {
      where.append(" and ");
      where.append("t3.pk_marbasclass", pk_marbasclass);
    }
    // 物料销售分类
    String[] pk_marsaleclass = paravo.getPk_marsaleclass();
    if (null != pk_marsaleclass && pk_marsaleclass.length > 0) {
      where.append(" and ");
      where.append("t4.pk_marsaleclass", pk_marsaleclass);
    }
  }

  private void appendOtherCondtion(OrderFormReportParaVO paravo,
      SqlBuilder where) {

    // 部门
    String[] cdeptvid = paravo.getCdeptvids();
    if (null != cdeptvid && cdeptvid.length > 0) {
      where.append(" and ");
      where.append("t1.cdeptvid", cdeptvid);
    }
    // 业务员
    String[] cemployeeid = paravo.getCemployeeids();
    if (null != cemployeeid && cemployeeid.length > 0) {
      where.append(" and ");
      where.append("t1.cemployeeid", cemployeeid);
    }

    // 币种
    String[] corigcurrencyid = paravo.getCorigcurrencyids();
    if (null != corigcurrencyid && corigcurrencyid.length > 0) {
      where.append(" and ");
      where.append("t1.corigcurrencyid", corigcurrencyid);
    }
    // 单据状态
    int[] fstatusflag = paravo.getFstatusflag();
    if (null != fstatusflag && fstatusflag.length > 0) {
      where.append(" and ");
      where.append("t1.fstatusflag", fstatusflag);
    }

    // 库存组织
    String[] stockorgvid = paravo.getStockorgvids();
    if (null != stockorgvid && stockorgvid.length > 0) {
      where.append(" and ");
      where.append("t2.csendstockorgvid", stockorgvid);
    }
    // 订单类型
    String[] vtrantypecode = paravo.getVtrantypecodes();
    if (null != vtrantypecode && vtrantypecode.length > 0) {
      where.append(" and ");
      where.append("t1.vtrantypecode", vtrantypecode);
    }
    // 统计服务和折扣
    UFBoolean containlaboranddiscount = paravo.getContainlaboranddiscount();
    if (null != containlaboranddiscount
        && !containlaboranddiscount.booleanValue()) {
      where.append(" and ");
      where.append("t2.blaborflag", UFBoolean.FALSE);
      where.append(" and ");
      where.append("t2.bdiscountflag", UFBoolean.FALSE);
    }
  }

  private String getFormWhere(OrderFormReportParaVO paravo) {

    String from = this.getOnlyFromSQL(paravo);
    boolean ishasbodyitem = this.getIshasbodyitem(paravo);
    String where = this.getOnlyWhereSQL(paravo, ishasbodyitem);

    return from + where;
  }

  private boolean getIshasbodyitem(OrderFormReportParaVO paravo) {
    boolean ishasbodyitem = false;
    // 物料编码
    if (null != paravo.getCmaterialvids()
        && paravo.getCmaterialvids().length > 0) {
      ishasbodyitem = true;
    }
    // 物料名称
    if (null != paravo.getCmaterialvname()) {
      ishasbodyitem = true;
    }
    // 物料基本分类
    if (null != paravo.getPk_marbasclass()
        && paravo.getPk_marbasclass().length > 0) {
      ishasbodyitem = true;
    }
    // 物料销售分类
    if (null != paravo.getPk_marsaleclass()
        && paravo.getPk_marsaleclass().length > 0) {
      ishasbodyitem = true;
    }

    // 库存组织
    if (null != paravo.getStockorgvids() && paravo.getStockorgvids().length > 0) {
      ishasbodyitem = true;
    }

    // 统计服务和折扣
    if (!ishasbodyitem && UFBoolean.TRUE == paravo.getContainlaboranddiscount()) {
      ishasbodyitem = true;
    }
    return ishasbodyitem;
  }

  private boolean getIshascustitem(OrderFormReportParaVO paravo) {
    boolean ishascustitem = false;

    // 订单客户名称
    if (null != paravo.getCcustomernames()) {
      ishascustitem = true;
    }
    // 订单客户基本分类
    if (null != paravo.getPk_custclass() && paravo.getPk_custclass().length > 0) {
      ishascustitem = true;
    }
    // 订单客户销售分类
    if (null != paravo.getPk_custsaleclass()
        && paravo.getPk_custsaleclass().length > 0) {
      ishascustitem = true;
    }
    // 订单客户地区分类
    if (null != paravo.getCustomerpk_areacls()
        && paravo.getCustomerpk_areacls().length > 0) {
      ishascustitem = true;
    }
    return ishascustitem;
  }

  private boolean getIshasmaterialitem(OrderFormReportParaVO paravo) {
    boolean ishasmaterialitem = false;

    // 物料名称
    if (null != paravo.getCmaterialvname()) {
      ishasmaterialitem = true;
    }
    // 物料基本分类
    if (null != paravo.getPk_marbasclass()
        && paravo.getPk_marbasclass().length > 0) {
      ishasmaterialitem = true;
    }
    // 物料销售分类
    if (null != paravo.getPk_marsaleclass()
        && paravo.getPk_marsaleclass().length > 0) {
      ishasmaterialitem = true;
    }
    return ishasmaterialitem;
  }

  private String getOnlyFromSQL(OrderFormReportParaVO paravo) {
    SqlBuilder from = new SqlBuilder();
    // 是否包含子表条件
    boolean ishasbodyitem = this.getIshasbodyitem(paravo);

    // 是否包含物料条件
    boolean ishasmaterialitem = this.getIshasmaterialitem(paravo);

    // 是否包含客户条件
    boolean ishascustitem = this.getIshascustitem(paravo);
    from.append(" from so_saleorder t1");
    if (ishasbodyitem) {
      from.append(" inner join so_saleorder_b t2 on t2.csaleorderid = t1.csaleorderid");
    }
    if (ishasmaterialitem) {
      from.append(" left outer join bd_material t3 on t3.pk_material = t2.cmaterialvid");
      from.append(" inner join bd_materialsale t4 on t4.pk_material = t3.pk_material");
    }

    if (ishascustitem) {
      from.append(" left outer join bd_customer t5 on t5.pk_customer = t1.ccustomerid");
      from.append(" inner join bd_custsale t6 on t6.pk_customer = t5.pk_customer");
    }
    return from.toString();
  }

  private String getOnlyWhereSQL(OrderFormReportParaVO paravo,
      boolean ishasbodyitem) {
    SqlBuilder where = new SqlBuilder();
    where.append(" where ");
    this.appendDbilldateAndSaleOrg(paravo, where, ishasbodyitem);
    // 拼接客户条件
    this.appendMaterialCondtion(paravo, where);
    // 拼接客户条件
    this.appendCustCondtion(paravo, where);

    this.appendOtherCondtion(paravo, where);
    // 拼接固定条件
    this.appendFixCondtion(ishasbodyitem, where);

    return where.toString();
  }

  private OrderReturnToReportVO[] getPavaVOs(SaleOrderVO[] bills) {
    if (null == bills) {
      return new OrderReturnToReportVO[0];
    }
    List<OrderReturnToReportVO> volist = new ArrayList<OrderReturnToReportVO>();
    for (SaleOrderVO bill : bills) {
      SaleOrderHVO hvo = bill.getParentVO();
      SaleOrderBVO[] bvos = bill.getChildrenVO();
      for (SaleOrderBVO bvo : bvos) {
        OrderReturnToReportVO retvo = new OrderReturnToReportVO();
        // 销售组织
        retvo.setSaleorgid(hvo.getPk_org());
        // 库存组织
        retvo.setStockorgvid(bvo.getCsendstockorgvid());
        retvo.setCmaterialvid(bvo.getCmaterialvid());
        retvo.setCcustomerid(hvo.getCcustomerid());
        retvo.setCinvoicecustid(hvo.getCinvoicecustid());
        retvo.setBlargessflag(bvo.getBlargessflag());
        retvo.setCunitid(bvo.getCunitid());
        retvo.setNnum(bvo.getNnum());
        retvo.setNorigmny(bvo.getNorigmny());
        retvo.setNorigtaxmny(bvo.getNorigtaxmny());
        volist.add(retvo);
      }
    }
    OrderReturnToReportVO[] retvos = new OrderReturnToReportVO[volist.size()];
    retvos = volist.toArray(retvos);
    return retvos;
  }
}
