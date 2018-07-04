package nc.pubimpl.so.sobalance.arap.listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.businessevent.IBusinessEvent;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.so.m30.sobalance.maintain.IInsertBP;
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
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.enumeration.SoBalanceType;
import nc.vo.so.pub.util.SOVOChecker;

public class VerifyAfterListenerAction {

  Map<String, Collection<IDataFromVerifyForM30>> insertDataMap;

  Map<String, Collection<IDataFromVerifyForM30>> updateDataMap;

  /**
   * 将所有核销记录按销售订单ID分组，并构建出相应SoBalanceVO[]进行插入或更新
   */
  public void doAction(IBusinessEvent event) {
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
    SoBalanceVO[] vos = null;
    try {
      vos = ArListenerUtils.getInstance().querySoBalanceVOByIDs(csaleorderids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    // 4.根据订单收款关系VO来区分核销金额需要update还是insert
    this.splitDataMap(vos, dataMap);

    try {
      // 5.维护订单收款核销关系
      if (this.insertDataMap.size() > 0) {
        this.insertVO();
      }
      if (this.updateDataMap.size() > 0) {
        this.updateVO(vos);
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  //begin-ncm-shenjzh-这代码写的，上面把不是来源订单的过滤掉了，这边又不处理了
    datas = this.organizeDataMap(datas);
    // 6.回写销售订单表体行收款金额
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
  //end-ncm-shenjzh-这代码写的，上面把不是来源订单的过滤掉了，这边又不处理了
  private SoBalanceVO createInsertSoBalanceVO(IDataFromVerifyForM30[] datas,
      SaleOrderVO saleOrderVO) {
    // 订单核销关系表头
    SoBalanceHVO headVO =
        ArListenerUtils.getInstance().createSoBalanceHVO(saleOrderVO);
    // 订单核销关系表体
    SoBalanceBVO[] bodyVOs = this.createSoBalanceBVOs(datas);
    SoBalanceVO retVO = new SoBalanceVO();
    retVO.setParentVO(headVO);
    retVO.setChildrenVO(bodyVOs);
    return retVO;
  }

  private SoBalanceBVO createSoBalanceBVO(IDataFromVerifyForM30 data) {
    SoBalanceBVO retBodyVO = new SoBalanceBVO();
    retBodyVO.setCpaybillid(data.getPayBillID());
    retBodyVO.setCpaybillrowid(data.getPayBillRowID());
    retBodyVO.setVarbillcode(data.getPayBillNo());
    retBodyVO.setDarbilldate(data.getPayBillDate());
    retBodyVO.setDarbalancedate(data.getVerifyDate());
    retBodyVO.setCarorigcurrencyid(data.getPayBillCurID());
    retBodyVO.setCprodlineid(data.getCprodlineID());
    // 财务核销金额
    retBodyVO.setNorigaccbalmny(data.getPayBillmny());
    // 订单收款金额
    retBodyVO.setNorigordbalmny(data.getPayBillmny());
    retBodyVO.setFibaltype(Integer.valueOf(SoBalanceType.SOBALANCE_FINBAL
        .getIntValue()));
    retBodyVO.setDr(Integer.valueOf(0));
    retBodyVO.setStatus(VOStatus.NEW);
    return retBodyVO;
  }

  private SoBalanceBVO[] createSoBalanceBVOs(IDataFromVerifyForM30[] datas) {
    // <PayBillRowID,SoBalanceBVO>
    Map<String, SoBalanceBVO> map = new HashMap<String, SoBalanceBVO>();
    for (int i = 0; i < datas.length; i++) {
      String payBillRowID = datas[i].getPayBillRowID();
      SoBalanceBVO sob = map.get(payBillRowID);
      if (SOVOChecker.isEmpty(sob)) {
        sob = new SoBalanceBVO();
        // 财务核销金额
        sob.setNorigaccbalmny(datas[i].getPayBillmny());
        // 订单收款金额
        sob.setNorigordbalmny(datas[i].getPayBillmny());
        sob.setCpaybillid(datas[i].getPayBillID());
        sob.setCpaybillrowid(payBillRowID);
        sob.setVarbillcode(datas[i].getPayBillNo());
        sob.setDarbilldate(datas[i].getPayBillDate());
        sob.setDarbalancedate(datas[i].getVerifyDate());
        sob.setCarorigcurrencyid(datas[i].getPayBillCurID());
        sob.setCprodlineid(datas[i].getCprodlineID());
        sob.setFibaltype(Integer.valueOf(SoBalanceType.SOBALANCE_FINBAL
            .getIntValue()));
        sob.setDr(Integer.valueOf(0));
        sob.setStatus(VOStatus.NEW);
        map.put(payBillRowID, sob);
      }
      else {
        // 财务核销金额
        sob.setNorigaccbalmny(MathTool.add(sob.getNorigaccbalmny(),
            datas[i].getPayBillmny()));
        // 订单收款金额
        sob.setNorigordbalmny(MathTool.add(sob.getNorigordbalmny(),
            datas[i].getPayBillmny()));
      }
    }
    SoBalanceBVO[] retBodyVOs =
        map.values().toArray(new SoBalanceBVO[map.size()]);
    return retBodyVOs;
  }

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
    // 有多少财务核销记录，就要有多少订单核销关系记录
    // Map<payBillRowID,SoBalanceBVO> 所有新SoBalanceBVO
    Map<String, SoBalanceBVO> allBodyMap = new HashMap<String, SoBalanceBVO>();
    // datas中paybillrowid可能相同,只能用双重for循环
    for (IDataFromVerifyForM30 data : datas) {
      String payBillRowID = data.getPayBillRowID();
      if (oldBodyMap.containsKey(payBillRowID)) {
        SoBalanceBVO oldBody = oldBodyMap.get(payBillRowID);
        oldBody = (SoBalanceBVO) oldBody.clone();
        oldBody.setDarbalancedate(data.getVerifyDate());
        // 如果收款金额=核销金额，此情况只会出现在（合同收款后，订单分次出库，下游应收单和收款单核销后，回写的订单收款核销关系数据错误）
        if (MathTool.equals(oldBody.getNorigordbalmny(),
            oldBody.getNorigaccbalmny())) {
          oldBody.setNorigordbalmny(MathTool.add(data.getPayBillmny(),
              oldBody.getNorigordbalmny()));
        }
        oldBody.setNorigaccbalmny(MathTool.add(data.getPayBillmny(),
            oldBody.getNorigaccbalmny()));
        oldBody.setStatus(VOStatus.UPDATED);
        allBodyMap.put(payBillRowID, oldBody);
        // 更新oldBodyMap的oldBody
        oldBodyMap.put(payBillRowID, oldBody);
      }
      // -- 没有建立过关系，则新建一行收款核销关系BVO
      else {
        SoBalanceBVO newBody = this.createSoBalanceBVO(data);
        newBody.setCsobalanceid(newHeadVO.getCsobalanceid());
        String cpaybillrowid = newBody.getCpaybillrowid();
        SoBalanceBVO oldBody = allBodyMap.get(cpaybillrowid);
        if (SOVOChecker.isEmpty(oldBody)) {
          allBodyMap.put(newBody.getCpaybillrowid(), newBody);
        }
        else {
          // 财务核销金额
          oldBody.setNorigaccbalmny(MathTool.add(newBody.getNorigaccbalmny(),
              oldBody.getNorigaccbalmny()));
          // 订单收款金额
          oldBody.setNorigordbalmny(MathTool.add(newBody.getNorigordbalmny(),
              oldBody.getNorigordbalmny()));
        }
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

  private void insertVO() throws BusinessException {
    // --查询销售订单VO
    String[] csaleorderids =
        this.insertDataMap.keySet().toArray(
            new String[this.insertDataMap.keySet().size()]);
    BillQuery<SaleOrderVO> query =
        new BillQuery<SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] saleOrderVOs = query.query(csaleorderids);
    if (this.insertDataMap.size() != saleOrderVOs.length) {
      throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0117")/* @res "销售订单已被删除，请刷新数据重做业务。" */);
    }

    SoBalanceVO[] newSoBalanceVOs = new SoBalanceVO[this.insertDataMap.size()];
    for (int i = 0; i < saleOrderVOs.length; i++) {
      String csaleorderid = saleOrderVOs[i].getParentVO().getCsaleorderid();
      List<IDataFromVerifyForM30> dataList =
          (List<IDataFromVerifyForM30>) this.insertDataMap.get(csaleorderid);
      IDataFromVerifyForM30[] datas =
          dataList.toArray(new IDataFromVerifyForM30[dataList.size()]);
      // --根据销售订单VO和收款data构造SoBalanceVO
      newSoBalanceVOs[i] = this.createInsertSoBalanceVO(datas, saleOrderVOs[i]);
    }
    // --调用新增保存核销关系
    IInsertBP mainservice =
        SobalanceBPFactoryForAcc.getInstance().getInsertBP();
    mainservice.insert(newSoBalanceVOs);
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
      List<IDataFromVerifyForM30> dataList = dataMapList.get(bids[i]);
      UFDouble sumPayBillMny = UFDouble.ZERO_DBL;
      for (IDataFromVerifyForM30 data : dataList) {
        sumPayBillMny = MathTool.add(sumPayBillMny, data.getPayBillmny());
      }
      paras[i] = new RewriteVerifyPara(bids[i], sumPayBillMny);
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

  private void splitDataMap(SoBalanceVO[] vos,
      Map<String, Collection<IDataFromVerifyForM30>> dataMap) {
    Set<String> csaleorderidSet = new HashSet<String>();
    // --按已有收款核销关系将Data数据分成：需要插入Map || 需要更新Map
    if (vos != null && vos.length > 0) {
      for (SoBalanceVO vo : vos) {
        csaleorderidSet.add(vo.getParentVO().getCsaleorderid());
      }
    }
    this.insertDataMap =
        new HashMap<String, Collection<IDataFromVerifyForM30>>();
    this.updateDataMap =
        new HashMap<String, Collection<IDataFromVerifyForM30>>();
    for (Map.Entry<String, Collection<IDataFromVerifyForM30>> entry : dataMap
        .entrySet()) {
      // 需要更新
      if (csaleorderidSet.contains(entry.getKey())) {
        this.updateDataMap.put(entry.getKey(), entry.getValue());
      }
      // 需要插入
      else {
        this.insertDataMap.put(entry.getKey(), entry.getValue());
      }
    }
  }

  private void updateVO(SoBalanceVO[] oldSoBalanceVOs) throws BusinessException {
    // --查询销售订单VO
    String[] csaleorderids =
        this.updateDataMap.keySet().toArray(
            new String[this.updateDataMap.keySet().size()]);
    BillQuery<SaleOrderVO> query =
        new BillQuery<SaleOrderVO>(SaleOrderVO.class);
    SaleOrderVO[] saleOrderVOs = query.query(csaleorderids);
    if (this.updateDataMap.size() != saleOrderVOs.length) {
      throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0117")/* @res "销售订单已被删除，请刷新数据重做业务。" */);
    }

    // 检查可否再次收款
    SaleOrderHVO[] heads = new SaleOrderHVO[saleOrderVOs.length];
    for (int i = 0; i < saleOrderVOs.length; i++) {
      heads[i] = saleOrderVOs[i].getParentVO();
    }

    // 组织更新SoBalanceVOs
    SoBalanceVO[] newSoBalanceVOs = new SoBalanceVO[this.updateDataMap.size()];
    for (int i = 0; i < oldSoBalanceVOs.length; i++) {
      String csaleorderid = oldSoBalanceVOs[i].getParentVO().getCsaleorderid();
      // --订单收款核销VO的销售订单ID = data中应收单源头销售订单ID
      List<IDataFromVerifyForM30> dataList =
          (List<IDataFromVerifyForM30>) this.updateDataMap.get(csaleorderid);
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
