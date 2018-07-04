package nc.pubimpl.so.sobalance.arap.listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceViewVO;

import nc.itf.arap.forso.IDataFromF2ForM30;
import nc.itf.arap.forso.IDataFromVerifyForM30;
import nc.itf.so.m30.sobalance.ISOBalanceQuery;

import nc.bs.framework.common.NCLocator;

/**
 * 收款单动作工具类
 * 
 * @since 6.0
 * @version 2011-4-1 下午03:58:08
 * @author 刘志伟
 */
public class ArListenerUtils {

  private static ArListenerUtils instance = new ArListenerUtils();

  public static ArListenerUtils getInstance() {
    return ArListenerUtils.instance;
  }

  /**
   * 按不同来源销售订单组织-收款单动作数据(过滤掉非源于销售订单的)
   * 
   * @param IDataFromF2ForM30[]
   * @return Map<销售订单id,collection<IDataFromF2ForM30>>
   */
  public Map<String, Collection<IDataFromF2ForM30>> organizeDataMap(
      IDataFromF2ForM30[] datas) {
    // Map<销售订单id,collection<IDataFromF2ForM30>>
    Map<String, Collection<IDataFromF2ForM30>> retMap =
        new HashMap<String, Collection<IDataFromF2ForM30>>();
    for (IDataFromF2ForM30 data : datas) {
      // 判断是否来源于销售订单,可以过滤掉自制收款单
      if (!data.isF2FromSaleOrder()) {
        continue;
      }
      String csaleorderid = data.getFirstID();
      List<IDataFromF2ForM30> dataList =
          (List<IDataFromF2ForM30>) retMap.get(csaleorderid);
      if (dataList == null) {
        dataList = new ArrayList<IDataFromF2ForM30>();
        retMap.put(csaleorderid, dataList);
      }
      dataList.add(data);
    }
    return retMap;
  }

  /**
   * 按不同来源销售订单组织-财务核销数据(过滤掉非源于销售订单的)
   * 
   * @param IDataFromVerifyForM30[]
   * @return Map<销售订单id,collection<IDataFromVerifyForM30>>
   */
  public Map<String, Collection<IDataFromVerifyForM30>> organizeDataMap(
      IDataFromVerifyForM30[] datas) {
    // Map<销售订单id,collection<IDataFromVerifyForM30>>
    Map<String, Collection<IDataFromVerifyForM30>> retMap =
        new HashMap<String, Collection<IDataFromVerifyForM30>>();
    for (IDataFromVerifyForM30 data : datas) {
      String csaleorderid = data.getFirstID();
      String firstBillType = data.getFirstBillType();
      if (firstBillType == null
          || !SOBillType.Order.getCode().equals(firstBillType)
          || csaleorderid == null) {
        continue;
      }
      List<IDataFromVerifyForM30> dataList =
          (List<IDataFromVerifyForM30>) retMap.get(csaleorderid);
      if (dataList == null) {
        dataList = new ArrayList<IDataFromVerifyForM30>();
        retMap.put(csaleorderid, dataList);
      }
      dataList.add(data);
    }
    return retMap;
  }

  public SoBalanceVO[] querySoBalanceVOByIDs(String[] csaleorderids)
      throws BusinessException {
    ISOBalanceQuery queryservice =
        NCLocator.getInstance().lookup(ISOBalanceQuery.class);
    return queryservice.querySoBalanceVOBySaleOrderIDs(csaleorderids);
  }

  public SoBalanceViewVO[] querySoBalanceViewByGatheringBillBodyIDs(
      String[] paybillrowids) throws BusinessException {
    ISOBalanceQuery queryservice =
        NCLocator.getInstance().lookup(ISOBalanceQuery.class);
    return queryservice.querySoBalanceViewByGatheringBillBodyIDs(paybillrowids);
  }

  public Map<String, SoBalanceVO> organizeBalanceMap(SoBalanceVO[] vos) {
    Map<String, SoBalanceVO> balanceMap = new HashMap<String, SoBalanceVO>();
    if (vos != null && vos.length > 0) {
      for (SoBalanceVO vo : vos) {
        SoBalanceBVO[] bvos = vo.getChildrenVO();
        for (SoBalanceBVO bvo : bvos) {
          balanceMap.put(bvo.getCpaybillrowid(), vo);
        }
      }
    }
    return balanceMap;
  }

  public SoBalanceHVO createSoBalanceHVO(SaleOrderVO saleOrderVO) {
    SoBalanceHVO retHeadVO = new SoBalanceHVO();
    SaleOrderHVO soheadvo = saleOrderVO.getParentVO();
    SaleOrderBVO[] sobodyvos = saleOrderVO.getChildrenVO();

    retHeadVO.setCsaleorderid(soheadvo.getPrimaryKey());
    retHeadVO.setPk_org(soheadvo.getPk_org());
    retHeadVO.setPk_org_v(soheadvo.getPk_org_v());
    retHeadVO.setPk_group(soheadvo.getPk_group());
    retHeadVO.setCcustomerid(soheadvo.getCcustomerid());
    retHeadVO.setCinvoicecustid(soheadvo.getCinvoicecustid());
    retHeadVO.setVbillcode(soheadvo.getVbillcode());
    retHeadVO.setDbilldate(soheadvo.getDbilldate());
    retHeadVO.setCorigcurrencyid(soheadvo.getCorigcurrencyid());
    retHeadVO.setNtotalorigtaxmny(soheadvo.getNtotalorigmny());
    retHeadVO.setCpaytermid(soheadvo.getCpaytermid());
    retHeadVO.setCemployeeid(soheadvo.getCemployeeid());
    retHeadVO.setCdeptid(soheadvo.getCdeptid());
    retHeadVO.setCarorgid(sobodyvos[0].getCarorgid());
    retHeadVO.setVtrantypecode(soheadvo.getCtrantypeid());
    retHeadVO.setCchanneltypeid(soheadvo.getCchanneltypeid());
    retHeadVO.setDr(Integer.valueOf(0));
    retHeadVO.setStatus(VOStatus.NEW);
    return retHeadVO;
  }

}
