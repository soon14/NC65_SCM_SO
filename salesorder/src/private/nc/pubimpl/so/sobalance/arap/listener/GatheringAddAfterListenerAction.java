package nc.pubimpl.so.sobalance.arap.listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.enumeration.SoBalanceType;

import nc.itf.arap.forso.IDataFromF2ForM30;

import nc.pubitf.arap.pub.GetSODataByArapUtils;

import nc.bs.businessevent.IBusinessEvent;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.so.m30.sobalance.maintain.IInsertBP;
import nc.impl.so.m30.sobalance.maintain.IUpdateBP;
import nc.impl.so.m30.sobalance.maintain.SobalanceBPFactoryForSoAuto;

/**
 * 订单收款时收款单的保存监听
 * 
 * @since 6.3
 * @version 2013-03-14 13:43:45
 * @author yixl
 */
public class GatheringAddAfterListenerAction {

  /**
   * 插入数据Map
   */
  Map<String, Collection<IDataFromF2ForM30>> insertDataMap;

  /**
   * 更新数据Map
   */
  Map<String, Collection<IDataFromF2ForM30>> updateDataMap;

  /**
   * 将所有收款记录按销售订单ID分组，并构建出相应SoBalanceVO[]进行插入或更新
   * 
   * @param event
   */
  public void doAction(IBusinessEvent event) {
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
    SoBalanceVO[] vos = null;
    try {
      vos = ArListenerUtils.getInstance().querySoBalanceVOByIDs(csaleorderids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    // 4.根据订单收款关系VO来区分收款单数据需要update还是insert
    this.splitDataMap(vos, dataMap);

    // 5.维护订单收款核销关系
    try {
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
  }

  private void checkGatheringAndSaleOrderConsistent(IDataFromF2ForM30[] datas,
      SaleOrderVO saleOrderVO) throws BusinessException {
    // 不可修改：订单客户、开票客户、币种、应收组织
    SaleOrderHVO head = saleOrderVO.getParentVO();
    String ccustomerid = head.getCcustomerid();
    String corigcurrencyid = head.getCorigcurrencyid();
    String cinvoicecustid = head.getCinvoicecustid();
    String carorgid = saleOrderVO.getChildrenVO()[0].getCarorgid();
    UFDouble ntotalorigmny = head.getNtotalorigmny();
    // 累计实际预收款金额
    UFDouble npreceivemny = head.getNpreceivemny();
    boolean bpreceiveflag =
        head.getBpreceiveflag() == null ? false : head.getBpreceiveflag()
            .booleanValue();
    UFDouble npreceivequota = head.getNpreceivequota();
    for (IDataFromF2ForM30 data : datas) {
      // 订单客户是否允许修改待议，先按用例上的来,且IDataFromF2ForM30
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
      if (MathTool.isDiffSign(ntotalorigmny, data.getPayBillmny())) {
        throw new BusinessException(NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006011_0", "04006011-0412")/*@res "销售订单价税合计与收款单金额算术符号不一致！"*/);
      }
      // --dongli2 2013.8.23后面有校验，这里的校验会导致修改已保存的收款单后（不做实际修改）保存报错
      // if (data.isPrePay()) {
      // npreceivemny = MathTool.add(npreceivemny, data.getPayBillmny());
      // }
    }
    // --dongli2 2013.8.23后面有校验，这里的校验会导致修改已保存的收款单后（不做实际修改）保存报错
    // 实际预收款金额+本次收款金额 >订单收款限额
    // if (bpreceiveflag
    // && MathTool.absCompareTo(npreceivemny, npreceivequota) > 0) {
    // throw new BusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID(
    // "4006011_0", "04006011-0423")/*@res
    // "收款限额控制预收的销售订单,实际预收款金额不能大于订单收款限额！"*/);
    // }
  }

  /**
   * 根据销售订单VO——新建一条订单收款关系记录
   * 
   * @param datas
   * @param SaleOrderVO
   */

  private SoBalanceVO createInsertSoBalanceVO(IDataFromF2ForM30[] datas,
      SaleOrderVO saleOrderVO) {
    SoBalanceHVO headVO =
        ArListenerUtils.getInstance().createSoBalanceHVO(saleOrderVO);
    // 会有多行收款单行对应同一销售订单吗？可能吗？有的化，下面组织方式错误
    UFDouble ntotalorigmny = saleOrderVO.getParentVO().getNtotalorigmny();
    SoBalanceBVO[] bodyVOs =
        this.createSoBalanceBVOs(datas, ntotalorigmny, UFDouble.ZERO_DBL);
    SoBalanceVO retVO = new SoBalanceVO();
    retVO.setParentVO(headVO);
    retVO.setChildrenVO(bodyVOs);
    return retVO;
  }

  private SoBalanceBVO[] createSoBalanceBVOs(IDataFromF2ForM30[] datas,
      UFDouble ntotalorigmny, UFDouble ntotalpaymny) {
    SoBalanceBVO[] retBodyVOs = new SoBalanceBVO[datas.length];
    for (int i = 0; i < datas.length; i++) {
      retBodyVOs[i] = new SoBalanceBVO();
      retBodyVOs[i].setCpaybillid(datas[i].getPayBillID());
      retBodyVOs[i].setCpaybillrowid(datas[i].getPayBillRowID());
      retBodyVOs[i].setVarbillcode(datas[i].getPayBillNo());
      retBodyVOs[i].setDarbilldate(datas[i].getPayBillDate());
      retBodyVOs[i].setDarbalancedate(datas[i].getPayBillDate());
      retBodyVOs[i].setCarorigcurrencyid(datas[i].getPayBillCurID());
      retBodyVOs[i].setCprodlineid(datas[i].getCprodlineID());
      retBodyVOs[i].setNorigarmny(datas[i].getPayBillmny());
      // 表体最多核销金额=min(|价税合计-已核销金额|,|收款单金额|)
      UFDouble canPayMny = MathTool.sub(ntotalorigmny, ntotalpaymny);
      if (MathTool.absCompareTo(canPayMny, datas[i].getPayBillmny()) > 0) {
        retBodyVOs[i].setNorigordbalmny(datas[i].getPayBillmny());
      }
      else {
        retBodyVOs[i].setNorigordbalmny(canPayMny);
      }
      retBodyVOs[i].setFibaltype(Integer
          .valueOf(SoBalanceType.SOBALANCE_ORDERBAL.getIntValue()));
      retBodyVOs[i].setBpreceiveflag(UFBoolean.valueOf(datas[i].isPrePay()));
      retBodyVOs[i].setDr(Integer.valueOf(0));
      retBodyVOs[i].setStatus(VOStatus.NEW);
    }
    return retBodyVOs;
  }

  /**
   * 根据订单收款核销VO——组建一条订单收款关系记录，包括处理：
   * <ol>
   * <li>没有变化的收款单行</li>
   * <li>修改已有收款单</li>
   * <li>新建立关系的订单收款行</li>
   * </ol>
   * 
   * @param datas
   * @param SoBalanceVO
   */
  private SoBalanceVO createUpdateSoBalanceVO(IDataFromF2ForM30[] datas,
      SoBalanceVO oldSoBalanceVO) {
    // 构造更新的关系headVO
    SoBalanceHVO newHeadVO =
        (SoBalanceHVO) oldSoBalanceVO.getParentVO().clone();
    newHeadVO.setStatus(VOStatus.UNCHANGED);
    // 合并关系子表
    UFDouble ntotalorigtaxmny = newHeadVO.getNtotalorigtaxmny();
    UFDouble ntotalpaymny = newHeadVO.getNtotalpaymny();
    SoBalanceBVO[] oldBodyVOs = oldSoBalanceVO.getChildrenVO();

    Map<String, SoBalanceBVO> oldBodyMap = new HashMap<String, SoBalanceBVO>();
    for (SoBalanceBVO oldBodyVO : oldBodyVOs) {
      oldBodyMap.put(oldBodyVO.getCpaybillrowid(), oldBodyVO);
    }
    Map<String, IDataFromF2ForM30> updateMap =
        new HashMap<String, IDataFromF2ForM30>();
    List<IDataFromF2ForM30> newList = new ArrayList<IDataFromF2ForM30>();
    for (IDataFromF2ForM30 data : datas) {
      // 需要修改订单核销行
      String paybillrowid = data.getPayBillRowID();
      if (oldBodyMap.containsKey(paybillrowid)) {
        updateMap.put(data.getPayBillRowID(), data);
      }
      else {
        // 需要新建订单核销行
        newList.add(data);
      }
    }

    List<SoBalanceBVO> bodyVOList = new ArrayList<SoBalanceBVO>();
    // 构造新的订单核销行
    SoBalanceBVO[] newBodyVOs =
        this.createSoBalanceBVOs(
            newList.toArray(new IDataFromF2ForM30[newList.size()]),
            ntotalorigtaxmny, ntotalpaymny);
    if (newBodyVOs.length > 0) {
      for (SoBalanceBVO bodyvo : newBodyVOs) {
        bodyvo.setDr(Integer.valueOf(0));
        bodyvo.setStatus(VOStatus.NEW);
        bodyvo.setCsobalanceid(newHeadVO.getPrimaryKey());
        bodyVOList.add(bodyvo);
      }
    }
    // 构造更新后的订单核销行和未改变的收款单行
    for (SoBalanceBVO oldBody : oldBodyVOs) {
      String paybillrowid = oldBody.getCpaybillrowid();
      if (updateMap.containsKey(paybillrowid)) {
        SoBalanceBVO newbodyvo = (SoBalanceBVO) oldBody.clone();
        newbodyvo
            .setNorigordbalmny(updateMap.get(paybillrowid).getPayBillmny());
        newbodyvo.setStatus(VOStatus.UPDATED);
        bodyVOList.add(newbodyvo);
      }
      else {
        SoBalanceBVO newbodyvo = (SoBalanceBVO) oldBody.clone();
        newbodyvo.setStatus(VOStatus.UNCHANGED);
        bodyVOList.add(newbodyvo);
      }
    }

    SoBalanceVO retSobalanceVO = new SoBalanceVO();
    retSobalanceVO.setParentVO(newHeadVO);
    retSobalanceVO.setChildrenVO(bodyVOList.toArray(new SoBalanceBVO[bodyVOList
        .size()]));
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
          .getStrByID("4006011_0", "04006011-0117")/*@res "销售订单已被删除，请刷新数据重做业务。"*/);
    }

    SoBalanceVO[] newSoBalanceVOs = new SoBalanceVO[this.insertDataMap.size()];
    for (int i = 0; i < saleOrderVOs.length; i++) {
      String csaleorderid = saleOrderVOs[i].getParentVO().getCsaleorderid();
      List<IDataFromF2ForM30> dataList =
          (List<IDataFromF2ForM30>) this.insertDataMap.get(csaleorderid);
      IDataFromF2ForM30[] datas =
          dataList.toArray(new IDataFromF2ForM30[dataList.size()]);
      // --检查订单和收款单要求字段一致性
      this.checkGatheringAndSaleOrderConsistent(datas, saleOrderVOs[i]);
      // --根据销售订单VO和收款data构造SoBalanceVO
      newSoBalanceVOs[i] = this.createInsertSoBalanceVO(datas, saleOrderVOs[i]);
    }
    // --调用新增保存核销关系
    IInsertBP service = SobalanceBPFactoryForSoAuto.getInstance().getInsertBP();
    service.insert(newSoBalanceVOs);
  }

  /**
   * 按数据Map分离成：需要插入Map || 需要更新Map
   * <p>
   * 更新情景： 订单100，第一次收款50，第二次收款50。则一张订单收款核销两行表体对应不同收款单号。即第二次是更新增加一行
   * </p>
   * 
   * Map<销售订单id,collection<IDataFromF2ForM30>>
   */
  private void splitDataMap(SoBalanceVO[] vos,
      Map<String, Collection<IDataFromF2ForM30>> dataMap) {
    Set<String> csaleorderidSet = new HashSet<String>();
    // --按已有收款核销关系将Data数据分成：需要插入Map || 需要更新Map
    if (vos != null && vos.length > 0) {
      for (SoBalanceVO vo : vos) {
        csaleorderidSet.add(vo.getParentVO().getCsaleorderid());
      }
    }
    this.insertDataMap = new HashMap<String, Collection<IDataFromF2ForM30>>();
    this.updateDataMap = new HashMap<String, Collection<IDataFromF2ForM30>>();
    for (Map.Entry<String, Collection<IDataFromF2ForM30>> entry : dataMap
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
          .getStrByID("4006011_0", "04006011-0117")/*@res "销售订单已被删除，请刷新数据重做业务。"*/);
    }

    SoBalanceVO[] newSoBalanceVOs = new SoBalanceVO[this.updateDataMap.size()];
    for (int i = 0; i < oldSoBalanceVOs.length; i++) {
      String csaleorderid = oldSoBalanceVOs[i].getParentVO().getCsaleorderid();
      List<IDataFromF2ForM30> dataList =
          (List<IDataFromF2ForM30>) this.updateDataMap.get(csaleorderid);
      IDataFromF2ForM30[] datas =
          dataList.toArray(new IDataFromF2ForM30[dataList.size()]);
      // --检查订单和收款单要求字段一致性
      this.checkGatheringAndSaleOrderConsistent(datas, saleOrderVOs[i]);
      // --根据订单收款核销VO和收款data构造SoBalanceVO
      newSoBalanceVOs[i] =
          this.createUpdateSoBalanceVO(datas, oldSoBalanceVOs[i]);
      newSoBalanceVOs[i].setListenerflag(UFBoolean.TRUE);
    }
    // 调用修改保存核销关系
    IUpdateBP mainservice =
        SobalanceBPFactoryForSoAuto.getInstance().getUpdateBP();
    mainservice.update(newSoBalanceVOs, oldSoBalanceVOs);
  }

}
