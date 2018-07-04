package nc.bs.so.m30.maintain.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDQueryBuilder;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryHVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;
import nc.vo.so.m30.util.Transfer30and30RVOTool;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.util.ArrayUtil;
import nc.vo.so.pub.util.ListUtil;

/**
 * vo校验类1
 * 
 * @since 6.36
 * @version 2014-12-26 下午3:05:57
 * @author wangshu6
 */
public class VOCheckFor30R {
  
  /**
   * 销售订单修订保存时 vocheck
   * 
   * @param bills
   * @throws BusinessException
   */
  public void voCheckForRevise(SaleOrderHistoryVO[] bills)
      throws BusinessException {
    // 1. 加锁 销售订单修订保存时 对销售订单主键进行加锁
    lock(bills);
    // 2. 校验版本号是否最新
    checkLatestIversion(bills);
    SaleOrderVO[] saleorderVO = this.query30BeforeRevise(bills);
    // 3. 修订时 校验销售订单和销售订单修订ts
    if (saleorderVO[0] != null) {
      check30And30RTS(bills[0], saleorderVO[0]);
    }
  }

  /**
   * 销售订单修订保存时 vocheck
   * 
   * @param bills
   * @throws BusinessException
   */
  public SaleOrderHistoryVO[] voCheckAndQueryOriginForRevise(
      SaleOrderHistoryVO[] bills) throws BusinessException {
    // 1. 加锁 销售订单修订保存时 对销售订单主键进行加锁
    lock(bills);
    // 2. 校验版本号是否最新
    checkLatestIversion(bills);
    SaleOrderVO[] saleorderVO = this.query30BeforeRevise(bills);
    // 3. 修订时 校验销售订单和销售订单修订ts
    if (saleorderVO[0] != null) {
      check30And30RTS(bills[0], saleorderVO[0]);
    }
    return new Transfer30and30RVOTool().transfer30TOOrderhisVO(saleorderVO);
  }
  
  /**
   * 销售订单修订 提交 审批时 vocheck
   * 
   * @param bills
   * @throws BusinessException
   */
  public void voCheckForSaveAndApprove(SaleOrderHistoryVO[] bills)
      throws BusinessException {

    // 1. 加锁
    lock(bills);
    // 2. 校验ts
    this.check30RTS(bills);
    // 3. 校验版本号是否最新
    this.checkLatestIversion(bills);
  }

  /**
   * 校验版本号是否最新
   * 
   * @param bills
   * @return 是否有过修订版本
   * @throws BusinessException
   */
  private boolean checkLatestIversion(SaleOrderHistoryVO[] bills)
      throws BusinessException {

    String[] saleoderIDs = getSaleOrderPKs(bills);
    // 1. 从数据库查询最新版本版本号
    SaleOrderHistoryVO[] orderhisVOs =
        this.queryMaxIversionFromOrderHistory(saleoderIDs);

    Map<String, SaleOrderHistoryVO> saleorderIDAndHisVOMap =
        getSaleOrderIDAndMaxVersionHisVOMap(orderhisVOs);
    if (saleorderIDAndHisVOMap == null) {
      return false;
    }
    for (SaleOrderHistoryVO bill : bills) {

      String csaleorderID = bill.getParentVO().getCsaleorderid();
      SaleOrderHistoryVO hisVO = saleorderIDAndHisVOMap.get(csaleorderID);

      // 2. 如果有修订版本， 则校验当前单据是否是修订最新版本; 如果没有修订版本 则查询的销售订单单据
      if (hisVO == null) {
        return false;
      }
      else {
        // 校验当前单据是否是修订最新版本
        Integer iversion = bill.getParentVO().getIversion();
        if (!iversion.equals(hisVO.getParentVO().getIversion())) {
          ExceptionUtils
              .wrappBusinessException(NCLangResOnserver.getInstance()
                  .getStrByID("4006011_0", "04006011-0511")/*
                                                            * 当前操作单据不是最新修订版本，请刷新！
                                                            */);
        }
      }
    }
    return true;
  }

  /**
   * 将销售订单修订最大版本vo组成一个<销售订单修订caleorderid,已经修订过的最大版本vo>的Map
   * 
   * @param orderhisVOs 已经有最大版本号的修订vo
   * @return
   */
  private Map<String, SaleOrderHistoryVO> getSaleOrderIDAndMaxVersionHisVOMap(
      SaleOrderHistoryVO[] orderhisVOs) {
    if (ArrayUtil.isEmpty(orderhisVOs)) {
      return new HashMap<String, SaleOrderHistoryVO>();
    }
    Map<String, SaleOrderHistoryVO> saleorderIDAndHisVOMap =
        new HashMap<String, SaleOrderHistoryVO>();
    for (SaleOrderHistoryVO saleOrderHistoryVO : orderhisVOs) {
      saleorderIDAndHisVOMap.put(saleOrderHistoryVO.getParentVO()
          .getCsaleorderid(), saleOrderHistoryVO);
    }
    return saleorderIDAndHisVOMap;
  }

  /**
   * 根据销售订单修订单据查询对应销售订单单据
   * 
   * @param bills 销售订单修订bills
   * @return saleorderbills 销售订单bills
   * @throws BusinessException
   */
  public SaleOrderVO[] query30BeforeRevise(SaleOrderHistoryVO[] bills)
      throws BusinessException {
    if (ArrayUtil.isEmpty(bills)) {
      return null;
    }
    String[] ids = getSaleOrderPKs(bills);
    SaleOrderVO[] saleorderbills = null;
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select csaleorderid ");
    sql.append(" from so_saleorder where ");
    sql.append(SaleOrderHVO.CSALEORDERID, ids);
    sql.append(" and dr = 0");

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet set = utils.query(sql.toString());
    if (set.size() == 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006011_0", "04006011-0525")/*销售订单已被删除，不允许修订，请在列表界面刷新单据*/);
    }

    BillQuery<SaleOrderVO> query =
        new BillQuery<SaleOrderVO>(SaleOrderVO.class);
    saleorderbills = query.query(set.toOneDimensionStringArray());
    return saleorderbills;

  }

  /**
   * 从销售订单修订表中查询最新版本号
   * 
   * @param vos 销售订单修订vo
   * @return 版本号
   */
  public String queryMaxIversionFromOrderHistory2(SaleOrderHistoryVO[] vos) {

    String[] iversions = null;
    for (SaleOrderHistoryVO vo : vos) {
      SaleOrderHistoryHVO head = vo.getParentVO();
      String csaleorderid = head.getCsaleorderid();
      SqlBuilder sql = new SqlBuilder();
      sql.append("select iversion ");
      sql.append("from so_orderhistory where csaleorderid = '");
      sql.append(csaleorderid);
      sql.append("' and iversion = (select max(iversion) from so_orderhistory");
      sql.append(" where csaleorderid = '");
      sql.append(csaleorderid);
      sql.append("')");
      sql.append(" and dr = 0");

      DataAccessUtils dataUtil = new DataAccessUtils();
      IRowSet set = dataUtil.query(sql.toString());
      iversions = set.toOneDimensionStringArray();
    }
    if (ArrayUtil.isEmpty(iversions)) {
      return null;
    }
    return iversions[0];
  }

  public SaleOrderHistoryVO[] queryMaxIversionFromOrderHistory(
      String[] saleoderIDs) {
    IDQueryBuilder sqlBuilder = new IDQueryBuilder();
    String insql =
        sqlBuilder.buildSQL("h." + SaleOrderHistoryHVO.CSALEORDERID,
            saleoderIDs);

    SaleOrderHistoryVO[] bills = null;
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select a.corderhistoryid from so_orderhistory a, ");
    sql.append(" (select csaleorderid, max(iversion) as iversion from so_orderhistory h ");
    sql.append(" where ");
    sql.append(insql);
    sql.append(" and h.dr = 0 ");
    sql.append(" group by csaleorderid) maxhs ");
    sql.append(" where a.csaleorderid = maxhs.csaleorderid ");
    sql.append(" and a.iversion = maxhs.iversion ");
    sql.append(" and a.dr = 0 ");

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet set = utils.query(sql.toString());
    if (set.size() == 0) {
      return null;
    }

    BillQuery<SaleOrderHistoryVO> query =
        new BillQuery<SaleOrderHistoryVO>(SaleOrderHistoryVO.class);
    bills = query.query(set.toOneDimensionStringArray());
    return bills;
  }

  /**
   * 加锁
   * 
   * @param 单据主键
   */
  private void lock(SaleOrderHistoryVO[] bills) {
    String[] csaleOrderIDs = getSaleOrderPKs(bills);
    new LockOperator()
        .lock(
            csaleOrderIDs,
            NCLangResOnserver.getInstance().getStrByID("4006011_0",
                "04006011-0512")/* 该销售订单正在被他人操作，请稍后刷新并重新操作！ */);
  }

  /**
   * 获取销售订单主键
   * 
   * @param bills 销售订单修订vo
   * @return 销售订单主键
   */
  private String[] getSaleOrderPKs(SaleOrderHistoryVO[] bills) {

    List<String> list = new ArrayList<String>();

    for (SaleOrderHistoryVO bill : bills) {
      String csaleorderID = bill.getParentVO().getCsaleorderid();
      list.add(csaleorderID);
    }
    return ListUtil.toArray(list);
  }

  /**
   * 校验销售订单、销售订单修订voTS
   * 
   * @param orderhistoryVO 销售订单修订
   * @param saleorderVO 销售订单
   */
  public void check30And30RTS(SaleOrderHistoryVO bill, SaleOrderVO saleorderVO) {
    if (!bill.getParentVO().getTs().equals(saleorderVO.getParentVO().getTs())
        && !BillStatus.AUDIT.equalsValue(saleorderVO.getParentVO()
            .getFstatusflag())) {

      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006011_0", "04006011-0526")/* 销售订单已被操作，请检查销售订单后再操作。*/);

    }
  }
  
  /**
   * 校验销售订单修订vo时间戳
   * 
   * @param updatebills 前台的完整单据实体
   * @param originBills 数据库完整原始实体
   */
  private void check30RTS(SaleOrderHistoryVO[] bills) {
    BillTransferTool<SaleOrderHistoryVO> transferTool =
        new BillTransferTool<SaleOrderHistoryVO>(bills);
    SaleOrderHistoryVO[] updatebills = transferTool.getClientFullInfoBill();
    SaleOrderHistoryVO[] originBills = transferTool.getOriginBills();
    for (SaleOrderHistoryVO updatebill : updatebills) {
      String corderhisIDforUpdate = updatebill.getPrimaryKey();
      for (SaleOrderHistoryVO originBill : originBills) {
        if (corderhisIDforUpdate.equals(originBill.getPrimaryKey())) {
          if (updatebill.getParentVO().getTs()
              .equals(originBill.getParentVO().getTs())) {
            return;
          }
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
              .getStrByID("4006011_0", "04006011-0514")/* 当前单据已被操作，请刷新！ */);
        }
      }
    }
  }
}
