package nc.pubimpl.so.m30.ic.m4c;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m30trantype.enumeration.DirectType;
import nc.vo.so.pub.SOTable;
import nc.vo.trade.checkrule.VOChecker;

import nc.itf.so.m30trantype.IM30TranTypeService;

import nc.pubitf.so.m30.ic.m4c.ISaleOrderFor4C;
import nc.pubitf.so.m4331.so.m30.IDeliveryFor30;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.bs.so.m30.state.SaleOrderStateMachine;
import nc.bs.so.m30.state.bill.BillCloseState;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.view.EfficientViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.pub.LockOperator;

import nc.ui.querytemplate.querytree.FromWhereSQL;
import nc.ui.querytemplate.querytree.FromWhereSQLImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.querytemplate.querytree.QueryScheme;

/**
 * 销售订单为销售出库单提供的服务实现类
 * 
 * @since 6.1
 * @version 2012-11-29 10:46:55
 * @author 冯加彬
 */
public class SaleOrderFor4CImpl implements ISaleOrderFor4C {

  @Override
  public void close30For4C(String[] hids) throws BusinessException {
    // --1.加锁
    LockOperator lock = new LockOperator();
    lock.lock(hids,
        NCLangResOnserver.getInstance()
            .getStrByID("4006011_0", "04006011-0348")/*锁定【 销售订单主表 】ID失败*/);
    try {
      // --2.获取单据全VO
      BillQuery<SaleOrderVO> query =
          new BillQuery<SaleOrderVO>(SaleOrderVO.class);
      SaleOrderVO[] bills = query.query(hids);

      // --3.过滤需要关闭的单据
      BillCloseState state = new BillCloseState();
      List<SaleOrderVO> albills = new ArrayList<SaleOrderVO>();
      if (bills != null && bills.length > 0) {
        for (SaleOrderVO bill : bills) {
          if (!state.isThisState(bill)) {
            albills.add(bill);
          }
        }
      }

      // --4.处理订单关闭
      SaleOrderVO[] newBills = albills.toArray(new SaleOrderVO[albills.size()]);
      SaleOrderStateMachine bo = new SaleOrderStateMachine();
      bo.setState(state, newBills);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  @Override
  public IQueryScheme getInvoicEndSQL4Filter4C(String icbodytable,
      String cfirstbid) throws BusinessException {
    if (icbodytable == null || icbodytable.trim().length() == 0
        || cfirstbid == null || cfirstbid.trim().length() == 0) {
      return null;
    }
    SqlBuilder fromSql = new SqlBuilder();
    fromSql.append(" inner join so_saleorder_b so_saleorder_b on ");
    fromSql.append(icbodytable + "." + cfirstbid
        + " = so_saleorder_b.csaleorderbid");
    SqlBuilder whereSql = new SqlBuilder();
    whereSql.append("isnull(so_saleorder_b.bbinvoicendflag,'N') = 'N'");

    QueryScheme queryScheme = new QueryScheme();
    FromWhereSQL sql =
        new FromWhereSQLImpl(fromSql.toString(), whereSql.toString());
    queryScheme.putTableJoinFromWhereSQL(sql);
    return queryScheme;
  }

  @Override
  public Map<String, UFBoolean> getReserveInfo(String[] bids)
      throws BusinessException {
    // 返回值
    Map<String, UFBoolean> result = null;
    // 根据销售订单表体id查询VO
    VOQuery<SaleOrderBVO> query = new VOQuery<SaleOrderBVO>(SaleOrderBVO.class);
    SaleOrderBVO[] bvos = query.query(bids);
    // 缓存销售订单表体id
    Set<String> idSet = new HashSet<String>();
    for (SaleOrderBVO bvo : bvos) {
      idSet.add(bvo.getCsaleorderbid());
    }
    String[] ids = new String[idSet.size()];
    IDeliveryFor30 service =
        NCLocator.getInstance().lookup(IDeliveryFor30.class);
    try {
      result = service.queryReverseFlag(idSet.toArray(ids));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return result;
  }

  @Override
  public Map<String, String[]> queryCoopPOBid(String[] bids)
      throws BusinessException {
    VOQuery<SaleOrderBVO> query = new VOQuery<SaleOrderBVO>(SaleOrderBVO.class);
    SaleOrderBVO[] bvos = query.query(bids);
    if (VOChecker.isEmpty(bvos)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0181")/*@res "要查询的销售订单不存在。"*/);
    }
    List<String> hidList = new ArrayList<String>();
    for (SaleOrderBVO bvo : bvos) {
      String hid = bvo.getCsaleorderid();
      if (hidList.size() == 0 || !hidList.contains(hid)) {
        hidList.add(hid);
      }
    }
    String[] hids = new String[hidList.size()];
    hids = hidList.toArray(hids);
    Map<String, String[]> mapCoopbid = this.getMapCoopPOBid(bvos, hids);
    return mapCoopbid;
  }

  @Override
  public Map<String, UFBoolean> queryIsDirectPO(String[] ctrantypeids)
      throws BusinessException {
    // Map<交易类型code, 是否直运采购>
    Map<String, UFBoolean> returnMap = null;
    IM30TranTypeService service =
        NCLocator.getInstance().lookup(IM30TranTypeService.class);
    try {
      returnMap = service.queryIsDirectPO(ctrantypeids);
    }
    catch (BusinessException e) {
      ExceptionUtils.marsh(e);
    }

    return returnMap;
  }

  @Override
  public Map<String, UFBoolean> queryIsWastageAppend(String[] ctrantypeids)
      throws BusinessException {
    // Map<交易类型code, 是否途损补货>
    Map<String, UFBoolean> returnMap = new HashMap<String, UFBoolean>();
    M30TranTypeVO[] tranTypeVOs = null;
    IM30TranTypeService service =
        NCLocator.getInstance().lookup(IM30TranTypeService.class);
    try {
      tranTypeVOs = service.queryTranTypeVOs(ctrantypeids);
    }
    catch (BusinessException e) {
      ExceptionUtils.marsh(e);
    }

    if (tranTypeVOs != null) {
      for (M30TranTypeVO vo : tranTypeVOs) {
        returnMap.put(vo.getCtrantypeid(),
            vo.getBlossrenew() == null ? UFBoolean.FALSE : vo.getBlossrenew());
      }
    }
    return returnMap;
  }

  @Override
  public SaleOrderViewVO[] querySaleOrderViewVOs(String[] bids, String[] names)
      throws BusinessException {
    if (bids == null || bids.length == 0 || names == null || names.length == 0) {
      return null;
    }

    EfficientViewQuery<SaleOrderViewVO> viewQuery =
        new EfficientViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class, names);

    return viewQuery.query(bids);
  }

  @Override
  public SaleOrderVO[] querySaleOrderVOs(String[] bids)
      throws BusinessException {
    if (bids == null || bids.length == 0) {
      return null;
    }
    // bids ->ids -> VOs(即VOs中包含数量bids > 参数bids)
    VOQuery<SaleOrderBVO> bvoQuery =
        new VOQuery<SaleOrderBVO>(SaleOrderBVO.class, new String[] {
          SaleOrderBVO.CSALEORDERID
        });

    SaleOrderBVO[] bvos = bvoQuery.query(bids);
    Set<String> idSet = new HashSet<String>();
    if (bvos != null && bvos.length > 0) {
      for (SaleOrderBVO bvo : bvos) {
        idSet.add(bvo.getCsaleorderid());
      }
    }
    String[] ids = idSet.toArray(new String[idSet.size()]);
    BillQuery<SaleOrderVO> billQuery =
        new BillQuery<SaleOrderVO>(SaleOrderVO.class);
    return billQuery.query(ids);
  }

  private Map<String, String[]> getMapCoopPOBid(SaleOrderBVO[] bvos,
      String[] hids) {
    VOQuery<SaleOrderHVO> query = new VOQuery<SaleOrderHVO>(SaleOrderHVO.class);
    SaleOrderHVO[] hvos = query.query(hids);
    Map<String, String[]> mapCoopbid = new HashMap<String, String[]>();
    for (SaleOrderHVO hvo : hvos) {
      if (!hvo.getBpocooptomeflag().booleanValue()) {
        continue;
      }
      String hid = hvo.getCsaleorderid();
      for (SaleOrderBVO bvo : bvos) {
        String bhid = bvo.getCsaleorderid();
        if (hid.equals(bhid)) {
          String[] coopIDs = new String[2];
          coopIDs[0] = bvo.getCsrcid();
          coopIDs[1] = bvo.getCsrcbid();
          mapCoopbid.put(bvo.getCsaleorderbid(), coopIDs);
        }
      }
    }
    return mapCoopbid;
  }

  @Override
  public SaleOrderViewVO[] queryJCSaleOrderViewVOs(String[] srchids,
      String[] srcbids) throws BusinessException {

    if (null == srchids || srchids.length == 0 || null == srcbids
        || srcbids.length == 0) {
      return new SaleOrderViewVO[0];
    }
    SqlBuilder wheresql = new SqlBuilder();
    wheresql.append(" and ");
    wheresql.append(SaleOrderBVO.BJCZXSFLAG, UFBoolean.TRUE);
    wheresql.append(" and ");
    wheresql.append(SaleOrderBVO.VSRCTYPE, ICBillType.BorrowOut.getCode());
    wheresql.append(" and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    wheresql.append(iq.buildSQL(SaleOrderBVO.CSRCID, srchids));

    wheresql.append(" and ");
    iq = new IDExQueryBuilder(SOTable.TMP_SO_ID2.getName());
    wheresql.append(iq.buildSQL(SaleOrderBVO.CSRCBID, srcbids));

    String[] names =
        new String[] {
          // 表头
          SaleOrderHVO.CSALEORDERID,
          SaleOrderHVO.DBILLDATE,
          SaleOrderHVO.TAUDITTIME,
          SaleOrderHVO.CTRANTYPEID,
          SaleOrderHVO.VBILLCODE,
          SaleOrderHVO.CEMPLOYEEID,
          SaleOrderHVO.CDEPTVID,
          SaleOrderHVO.CDEPTID,
          // 表体
          SaleOrderBVO.CSALEORDERBID, SaleOrderBVO.PK_GROUP,
          SaleOrderBVO.CMATERIALVID, SaleOrderBVO.CMATERIALID,
          SaleOrderBVO.PK_BATCHCODE, SaleOrderBVO.VBATCHCODE,
          SaleOrderBVO.CSENDSTOCKORGVID, SaleOrderBVO.CSENDSTOCKORGID,
          SaleOrderBVO.CSENDSTORDOCID, SaleOrderBVO.CVENDORID,
          SaleOrderBVO.CPROJECTID, SaleOrderBVO.CPRODUCTORID,
          SaleOrderBVO.VFREE1, SaleOrderBVO.VFREE2, SaleOrderBVO.VFREE3,
          SaleOrderBVO.VFREE4, SaleOrderBVO.VFREE5, SaleOrderBVO.VFREE6,
          SaleOrderBVO.VFREE7, SaleOrderBVO.VFREE8, SaleOrderBVO.VFREE9,
          SaleOrderBVO.VFREE10, SaleOrderBVO.CUNITID, SaleOrderBVO.CASTUNITID,
          SaleOrderBVO.VCHANGERATE, SaleOrderBVO.NNUM, SaleOrderBVO.NASTNUM
        };
    EfficientViewQuery<SaleOrderViewVO> viewQuery =
        new EfficientViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class, names);

    return viewQuery.query(wheresql.toString());
  }

  @Override
  public Map<String, UFBoolean> queryIsDirect(String[] ctrantypeids)
      throws BusinessException {
    // Map<交易类型code, 是否直运采购>
    Map<String, UFBoolean> returnMap = new HashMap<String, UFBoolean>();
    Map<String, Integer> directtypemap = null;
    IM30TranTypeService service =
        NCLocator.getInstance().lookup(IM30TranTypeService.class);
    try {

      directtypemap = service.queryDirectType(ctrantypeids);
    }
    catch (BusinessException e) {
      ExceptionUtils.marsh(e);
    }
    if (null == directtypemap) {
      return returnMap;
    }
    for (Entry<String, Integer> entry : directtypemap.entrySet()) {
      if (DirectType.DIRECTTRAN_NO.getIntegerValue().equals(entry.getValue())) {
        returnMap.put(entry.getKey(), UFBoolean.FALSE);
      }
      else {
        returnMap.put(entry.getKey(), UFBoolean.TRUE);
      }
    }

    return returnMap;
  }

}
