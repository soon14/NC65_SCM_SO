package nc.pubimpl.so.sobalance.arap.listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.so.m30.sobalance.maintain.IUpdateBP;
import nc.impl.so.m30.sobalance.maintain.SobalanceBPFactoryForAcc;
import nc.itf.arap.forso.IDataFromVerifyForM30;
import nc.pubitf.arap.pub.GetSODataByArapUtils;
import nc.pubitf.so.m30.so.balance.IRewrite30ForVerify;
import nc.pubitf.so.m30.so.balance.RewriteVerifyPara;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.enumeration.SoBalanceType;

import nc.vo.scmpub.res.billtype.SOBillType;
/**
 * 反核销后-订单收款核销监听
 * 
 * <p>注册：开发平台-开发配置工具-业务插件注册-应收管理-反核销前-反核销后</p>
 * <p>相应表：pub_eventlistener</p>
 * 
 * @since 6.0
 * @version 2011-3-31 下午04:37:49
 * @author 刘志伟
 */
public class UnVerifyAfterListener implements IBusinessListener {

  /**
   * 将所有反核销记录按销售订单ID分组，并构建出相应SoBalanceVO[]进行更新
   */
  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
    // 1.解析收款单数据
    // --需确保IDataFromVerifyForM30中的数据是应收单与收款单的核销，
    // 否则我们自己要校验，需要IDataFromVerifyForM30加判断所需数据
    IDataFromVerifyForM30[] datas =
        new GetSODataByArapUtils().getIDataFromVerifyForM30(event);
    if (datas.length == 0) {
      return;
    }
    // 2.按不同来源销售订单ID组织数据(过滤掉源头不是销售订单的)
    Map<String, Collection<IDataFromVerifyForM30>> dataMap =
        ArListenerUtils.getInstance().organizeDataMap(datas);

    // 3.根据源头订单ID查询收款核销表中的关系VO
    String[] csaleorderids =
        dataMap.keySet().toArray(new String[dataMap.keySet().size()]);
    if (csaleorderids == null || csaleorderids.length == 0) {
      return;
    }
    SoBalanceVO[] vos =
        ArListenerUtils.getInstance().querySoBalanceVOByIDs(csaleorderids);

    // 4.维护订单收款核销关系
    if (dataMap.size() > 0) {
      this.updateVO(dataMap, vos);
    }
    //begin-ncm-qiwang-这代码写的，上面把不是来源订单的过滤掉了，这边又不处理了
    datas = this.organizeDataMap(datas);
    // 5.回写销售订单表体行收款金额
    this.rewrite30RowTotalPayMny(datas);
  }
  private IDataFromVerifyForM30[] organizeDataMap(IDataFromVerifyForM30[] datas) {
	    // Map<销售订单id,collection<IDataFromVerifyForM30>>
	  List<IDataFromVerifyForM30> dataList = new ArrayList<IDataFromVerifyForM30>();   dataList = new ArrayList<IDataFromVerifyForM30>();
	  for (IDataFromVerifyForM30 data : datas) {
	      String csaleorderid = data.getFirstID();
	      String firstBillType = data.getFirstBillType();
	      if (firstBillType == null
	          || !SOBillType.Order.getCode().equals(firstBillType)
	          || csaleorderid == null) {
	        continue;
	      }
	      dataList.add(data);
	    }
	  	IDataFromVerifyForM30[] datas2 = new IDataFromVerifyForM30[dataList.size()]; 
	    return dataList.toArray(datas2);
  }
 
//    // 5.回写销售订单表体行收款金额
//    this.rewrite30RowTotalPayMny(datas);
//  }
  //end-ncm-qiwang-这代码写的，上面把不是来源订单的过滤掉了，这边又不处理了   
  
  private SoBalanceVO createUpdateSoBalanceVO(IDataFromVerifyForM30[] datas,
      SoBalanceVO oldSoBalanceVO) {
    // 构造更新的SoBalanceHVO
    SoBalanceHVO newHeadVO =
        (SoBalanceHVO) oldSoBalanceVO.getParentVO().clone();
    newHeadVO.setStatus(VOStatus.UNCHANGED);
    // 构造更新的SoBalanceBVO[]...
    SoBalanceBVO[] oldBodyVOs = oldSoBalanceVO.getChildrenVO();
    Map<String, SoBalanceBVO> oldBodyMap = new HashMap<String, SoBalanceBVO>();
    for (SoBalanceBVO oldBodyVO : oldBodyVOs) {
      oldBodyMap.put(oldBodyVO.getCpaybillrowid(), oldBodyVO);
    }
    // Map<payBillRowID,SoBalanceBVO> 所有新SoBalanceBVO
    Map<String, SoBalanceBVO> allBodyMap = new HashMap<String, SoBalanceBVO>();
    // datas中paybillrowid可能相同,只能用双重for循环
    for (IDataFromVerifyForM30 data : datas) {
      String payBillRowID = data.getPayBillRowID();
      if (oldBodyMap.containsKey(payBillRowID)) {
        SoBalanceBVO oldBody = oldBodyMap.get(payBillRowID);
        oldBody = (SoBalanceBVO) oldBody.clone();
        UFDouble oldBalMny = oldBody.getNorigaccbalmny();
        UFDouble chgBalMny = data.getPayBillmny();
        // 已核销金额 = 反核销金额 && 核销方式为财务核销：删除核销记录
        if (MathTool.absCompareTo(oldBalMny, chgBalMny) == 0
            && oldBody.getFibaltype().equals(
                SoBalanceType.SOBALANCE_FINBAL.getIntegerValue())) {
          oldBody.setStatus(VOStatus.DELETED);
        }
        // --已核销金额 >= 反核销金额 ：更新成(已核销金额 - 反核销金额)
        else if (MathTool.absCompareTo(oldBalMny, chgBalMny) >= 0) {
          oldBody.setNorigaccbalmny(MathTool.sub(oldBody.getNorigaccbalmny(),
              data.getPayBillmny()));
          oldBody.setStatus(VOStatus.UPDATED);
        }
        allBodyMap.put(payBillRowID, oldBody);
        // 更新oldBodyMap的oldBody
        oldBodyMap.put(payBillRowID, oldBody);
      }
    }

    // 添加未改变行
    Set<Map.Entry<String, SoBalanceBVO>> oldBodyEntrySet =
        oldBodyMap.entrySet();
    for (Entry<String, SoBalanceBVO> oldBodyEntry : oldBodyEntrySet) {
      SoBalanceBVO oldBody = oldBodyEntry.getValue();
      if (oldBody.getStatus() == VOStatus.UNCHANGED) {
        allBodyMap.put(oldBody.getCpaybillrowid(), oldBody);
      }
    }

    SoBalanceVO retSobalanceVO = new SoBalanceVO();
    retSobalanceVO.setParentVO(newHeadVO);
    retSobalanceVO.setChildrenVO(allBodyMap.values().toArray(
        new SoBalanceBVO[allBodyMap.size()]));
    return retSobalanceVO;
  }

  private void rewrite30RowTotalPayMny(IDataFromVerifyForM30[] datas) {
    // 按行分组，按行回写
    MapList<String, IDataFromVerifyForM30> dataMapList =
        new MapList<String, IDataFromVerifyForM30>();
    for (IDataFromVerifyForM30 data : datas) {
      dataMapList.put(data.getFirstBID(), data);
    }
    String[] bids =
        dataMapList.keySet().toArray(new String[dataMapList.size()]);
    RewriteVerifyPara[] paras = new RewriteVerifyPara[bids.length];
    for (int i = 0; i < paras.length; i++) {
      String ordbid = bids[i];
      List<IDataFromVerifyForM30> dataList = dataMapList.get(ordbid);
      UFDouble sumPayBillMny = UFDouble.ZERO_DBL;
      for (IDataFromVerifyForM30 data : dataList) {
        sumPayBillMny =
            MathTool.add(sumPayBillMny,
                MathTool.sub(UFDouble.ZERO_DBL, data.getPayBillmny()));
      }
      paras[i] = new RewriteVerifyPara(ordbid, sumPayBillMny);
    }
    IRewrite30ForVerify api =
        NCLocator.getInstance().lookup(IRewrite30ForVerify.class);
    try {
      api.rewrite30TotalPayMnyVerifyListener(paras);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
  }

  private void updateVO(Map<String, Collection<IDataFromVerifyForM30>> dataMap,
      SoBalanceVO[] oldSoBalanceVOs) throws BusinessException {
    // --查询销售订单VO
    String[] csaleorderids =
        dataMap.keySet().toArray(new String[dataMap.keySet().size()]);
    BillQuery<SaleOrderVO> query =
        new BillQuery<SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] saleOrderVOs = query.query(csaleorderids);
    if (csaleorderids.length != saleOrderVOs.length) {
      throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0117")/*@res "销售订单已被删除，请刷新数据重做业务。"*/);
    }

    SoBalanceVO[] newSoBalanceVOs = new SoBalanceVO[oldSoBalanceVOs.length];
    for (int i = 0; i < oldSoBalanceVOs.length; i++) {
      String csaleorderid = oldSoBalanceVOs[i].getParentVO().getCsaleorderid();
      // --订单收款核销VO的销售订单ID = data中应收单源头销售订单ID
      List<IDataFromVerifyForM30> dataList =
          (List<IDataFromVerifyForM30>) dataMap.get(csaleorderid);
      IDataFromVerifyForM30[] datas =
          dataList.toArray(new IDataFromVerifyForM30[dataList.size()]);
      // --根据订单收款核销VO和核销data构造SoBalanceVO
      newSoBalanceVOs[i] =
          this.createUpdateSoBalanceVO(datas, oldSoBalanceVOs[i]);
    }

    // 调用修改保存核销关系
    IUpdateBP mainservice =
        SobalanceBPFactoryForAcc.getInstance().getUpdateBP();
    mainservice.update(newSoBalanceVOs, oldSoBalanceVOs);
  }
}
