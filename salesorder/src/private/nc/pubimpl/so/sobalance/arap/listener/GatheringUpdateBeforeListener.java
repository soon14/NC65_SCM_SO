package nc.pubimpl.so.sobalance.arap.listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.bs.ml.NCLangResOnserver;
import nc.itf.arap.forso.IDataFromF2ForM30;
import nc.pubitf.arap.pub.GetSODataByArapUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.enumeration.SoBalanceType;

/**
 * 收款单-修改前-订单收款核销监听
 * 
 * <p>注册：开发平台-开发配置工具-业务插件注册-应收管理-客户收款单-修改前</p>
 * <p>相应表：pub_eventlistener</p>
 * 
 * @since 6.0
 * @version 2011-3-31 下午04:37:49
 * @author 刘志伟
 */
public class GatheringUpdateBeforeListener implements IBusinessListener {

  /**
   * 已建立订单收款核销关系的收款单不允许修改
   */
  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
    // 1.解析收款单数据
    IDataFromF2ForM30[] datas =
        new GetSODataByArapUtils().getIDataFromF2ForM30(event);

    // 2.按不同来源销售订单ID组织数据(过滤掉源头不是销售订单的)
    Map<String, Collection<IDataFromF2ForM30>> dataMap =
        ArListenerUtils.getInstance().organizeDataMap(datas);

    // 3.根据源头订单ID查询收款核销表中的关系VO
    String[] csaleorderids =
        dataMap.keySet().toArray(new String[dataMap.keySet().size()]);
    if (csaleorderids == null || csaleorderids.length == 0) {
      return;
    }
    SoBalanceVO[] vos =
        ArListenerUtils.getInstance().querySoBalanceVOByIDs(csaleorderids);

    // 4. 组织收款单行与销售订单关系：Map<收款单bid, SoBalanceVO>
    Map<String, SoBalanceVO> balanceMap =
        ArListenerUtils.getInstance().organizeBalanceMap(vos);

    // 5.检查建立过收款核销关系的不允许修改
    this.checkDatas(datas, balanceMap);
  }

  private void checkDatas(IDataFromF2ForM30[] datas,
      Map<String, SoBalanceVO> balanceMap) throws BusinessException {
    StringBuffer errMsg = new StringBuffer();
    List<IDataFromF2ForM30> deleteList = new ArrayList<IDataFromF2ForM30>();
    List<IDataFromF2ForM30> updateList = new ArrayList<IDataFromF2ForM30>();
    for (IDataFromF2ForM30 data : datas) {
      if (VOStatus.DELETED == data.getRowStatus()) {
        deleteList.add(data);
      }
      else if (VOStatus.UPDATED == data.getRowStatus()) {
        updateList.add(data);
      }
    }
    if (deleteList.size() > 0) {
      IDataFromF2ForM30[] deleteDatas =
          deleteList.toArray(new IDataFromF2ForM30[deleteList.size()]);
      for (IDataFromF2ForM30 data : deleteDatas) {
        if (balanceMap.containsKey(data.getPayBillRowID())) {
          String payBillNo = data.getPayBillNo();
          String billRowID =
              balanceMap.get(data.getPayBillRowID()).getParentVO()
                  .getVbillcode();
          errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006011_0",
              "04006011-0363", null, new String[] {
                payBillNo, billRowID
              })/*收款单[{0}]与销售订单[{1}]，已建立订单收款核销关系,不允许删除！*/);
          errMsg.append("\n");
        }
      }
    }
    if (updateList.size() > 0) {
      IDataFromF2ForM30[] updateDatas =
          updateList.toArray(new IDataFromF2ForM30[updateList.size()]);
      for (IDataFromF2ForM30 data : updateDatas) {
        if (balanceMap.containsKey(data.getPayBillRowID())) {
          SoBalanceVO sbVO = balanceMap.get(data.getPayBillRowID());
          this.checkGatheringbillAndSoBalanceConsistent(data, sbVO);
        }
      }
    }
    if (errMsg.length() > 0) {
      throw new BusinessException(errMsg.toString());
    }
  }

  private void checkGatheringbillAndSoBalanceConsistent(IDataFromF2ForM30 data,
      SoBalanceVO sbVO) throws BusinessException {
    SoBalanceHVO head = sbVO.getParentVO();
    String ccustomerid = head.getCcustomerid();
    String cinvoicecustid = head.getCinvoicecustid();
    String corigcurrencyid = head.getCorigcurrencyid();
    String carorgid = head.getCarorgid();
    SoBalanceBVO[] bodys = sbVO.getChildrenVO();
    int fibaltype = 1;
    for (SoBalanceBVO body : bodys) {
      if (body.getCpaybillrowid().equals(data.getPayBillRowID())) {
        fibaltype = body.getFibaltype().intValue();
        break;
      }
    }

    if (SoBalanceType.SOBALANCE_ORDERBAL.getIntValue() == fibaltype) {
      // TODO 订单客户是否允许修改待议，先按用例上的来,且IDataFromF2ForM30
      if (!ccustomerid.equals(data.getPayBillOrderCust())) {
        throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0193")/*@res "不允许修改订单客户。"*/);
      }
      if (!cinvoicecustid.equals(data.getPayBillCustomer())) {
        throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0194")/*@res "不允许修改开票客户。"*/);
      }
      if (!corigcurrencyid.equals(data.getPayBillCurID())) {
        throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0195")/*@res "不允许修改币种。"*/);
      }
      if (!carorgid.equals(data.getPayBillOrg())) {
        throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0196")/*@res "不允许修改应收组织。"*/);
      }

    }
  }

}
