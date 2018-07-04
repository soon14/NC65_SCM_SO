package nc.pubimpl.so.sobalance.arap.verify;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.vo.arap.pfflow.ArapBillMapVO;
import nc.vo.arap.pub.BillEnumCollection.FromSystem;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.ARAPBillType;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceHVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceViewVO;
import nc.vo.so.pub.util.SOVOChecker;
import nc.vo.so.pub.votools.SoVoTools;

import nc.itf.arap.fieldmap.IBillFieldGet;
import nc.itf.so.m30.sobalance.ISOBalanceQuery;

import nc.pubitf.arap.gathering.IArapGatheringBillPubServiceForSCM;
import nc.pubitf.arap.receivable.IArapReceivableBillPubQueryService;

import nc.bs.framework.common.NCLocator;

public class SoBalance4VerifyQryBillAction {

  // 转换传入ArapBillMapVO并作为Map.key时用到的字段名称
  private static final String[] ARAPVOKEY_MAPKEYCHANGE = new String[] {
    "s_billtype", "s_system", "ybye", "maptype", "ybje", "t_termid",
    "t_itemid", "t_billid", "t_billtype", "s_termid", "s_itemid", "s_billid",
    "pk_billmap", "oldje", "ts", "dr", "pk_currtype", "pk_org"
  };

  // 转换查询到的结果集ArapBillMapVO并作为Map.value时用到的字段名称
  private static final String[] ARAPVOKEY_MAPVALUECHANGE = new String[] {
    "t_billtype", "s_system", "ybye", "maptype", "ybje", "s_termid",
    "s_itemid", "s_billid", "s_billtype", "t_termid", "t_itemid", "t_billid",
    "pk_billmap", "oldje", "ts", "dr", "pk_currtype", "pk_org"
  };

  private static final String[] SOBALANCEBILLMAPVOKEY = new String[] {
    "s_billtype", "s_system", "ybye", "maptype", "ybje", "t_termid",
    "t_itemid", "t_billid", "t_billtype", "s_termid", "s_itemid", "s_billid",
    "pk_billmap", "oldje", "ts", "dr", "pk_currtype", "pk_org"
  };

  /**
   * 1.processSK->processRush+processVerify
   * 2.processYS->processRush+processVerify
   * <p>
   * 注明：按蓝字销售订单流程设计及注释;调通后,红字流程红字应收单的过程， 代码可逆,即红字相当于注释的蓝字,蓝字相当于注释的红字;
   * </p>
   */
  public HashMap<ArapBillMapVO, Collection<ArapBillMapVO>> queryArapBillmap(
      ArapBillMapVO[] arVOs) throws BusinessException {
    if (arVOs == null || arVOs.length == 0) {
      return null;
    }

    // begin:转换成销售的处理VO以便代码不受arap变化影响
    SoBalanceBillMapVO[] soVOs = this.changeToSoBalanceBillMapVO(arVOs, false);

    // 如果非来源与销售的应收单不做核销
    if (SOVOChecker.isEmpty(soVOs)) {
      return null;
    }

    Map<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>> retMap =
        new HashMap<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>>();
    // 传入参数ArapBillMapVO[] mapvos肯定是一种单据类型：如 ys--应收单 sk--收款单
    if (IBillFieldGet.F2.equals(soVOs[0].getT_billtype())) {
      this.processSK(soVOs, retMap);
    }
    else if (IBillFieldGet.F0.equals(soVOs[0].getT_billtype())) {
      this.processYS(soVOs, retMap);
    }
    // end:由销售的处理VOMap构造arap的返回值Map
    return (HashMap<ArapBillMapVO, Collection<ArapBillMapVO>>) this
        .createReturnMap(retMap);
  }

  /**
   * 算法处理器：计算收款单与应收单核销关系(及红蓝字对冲)并存到retMap结构中
   * <p>
   * 递归计算一直到unKeyList/unValueList一方消耗完。核销关系保存到retMap，并返回剩余没有处理
   * 
   * 的valueVOs
   * </p>
   * 
   * @param unKeyList 作为map.Key的未使用过的List<SoBalanceBillMapVO>
   * @param unValueList 作为map.Value的未使用过的List<SoBalanceBillMapVO>
   * @param retMap 存储处理的核销关系的map
   */
  private void calculateRelation(List<SoBalanceBillMapVO> unKeyList,
      List<SoBalanceBillMapVO> unValueList,
      Map<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>> balanceRelationMap) {
    for (SoBalanceBillMapVO unKeyVO : unKeyList) {
      for (SoBalanceBillMapVO unValueVO : unValueList) {
        SoBalanceBillMapVO newValueVO = (SoBalanceBillMapVO) unValueVO.clone();
        // --将核销关系存入balanceRelationMap
        this.injectBalanceRelationMap(unKeyVO, newValueVO, balanceRelationMap);
      }

    }
    return;
  }

  /**
   * SoBalanceBillMapVO -> ArapBillMapVO
   * 
   * @param soVOs SoBalanceBillMapVO
   */
  private ArapBillMapVO[] changeToArapBillMapVO(SoBalanceBillMapVO[] soVOs) {
    // SoBalanceBillMapVO->ArapBillMapVO:确保对应字段顺序是一样的
    int arapMapKeyChangeLength =
        SoBalance4VerifyQryBillAction.ARAPVOKEY_MAPKEYCHANGE.length;
    int soLength = SoBalance4VerifyQryBillAction.SOBALANCEBILLMAPVOKEY.length;
    if (arapMapKeyChangeLength != soLength) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0197")/*@res "SoBalanceBillMapVO转换ArapBillMapVO异常"*/);
    }
    ArapBillMapVO[] retMapvo = new ArapBillMapVO[soVOs.length];
    for (int i = 0; i < soVOs.length; i++) {
      retMapvo[i] = new ArapBillMapVO();

      for (int j = 0; j < soLength; j++) {
        String arapKey =
            SoBalance4VerifyQryBillAction.ARAPVOKEY_MAPKEYCHANGE[j];
        String soKey = SoBalance4VerifyQryBillAction.SOBALANCEBILLMAPVOKEY[j];
        retMapvo[i].setAttributeValue(arapKey,
            soVOs[i].getAttributeValue(soKey));
      }
    }
    return retMapvo;
  }

  /**
   * ArapBillMapVO -> SoBalanceBillMapVO
   * <p>
   * 例如: 传入的是应收单数据，那么ArapBillMapVO中t_是应收单、s_是销售订单数据
   * 
   * @param mapvos ArapBillMapVO[]
   * @param isRetMapValue 是否返回给Arap结果Map中对应value的值的转换
   */
  private SoBalanceBillMapVO[] changeToSoBalanceBillMapVO(
      ArapBillMapVO[] mapvos, boolean isRetMapValue) {
    // ArapBillMapVO->SoBalanceBillMapVO:确保对应字段顺序是一样的
    int arapMapKeyChangeLength =
        SoBalance4VerifyQryBillAction.ARAPVOKEY_MAPKEYCHANGE.length;
    int arapMapValueChangeLength =
        SoBalance4VerifyQryBillAction.ARAPVOKEY_MAPVALUECHANGE.length;
    int soLength = SoBalance4VerifyQryBillAction.SOBALANCEBILLMAPVOKEY.length;
    if (arapMapKeyChangeLength != arapMapValueChangeLength
        || arapMapKeyChangeLength != soLength) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0198")/*@res "ArapBillMapVO转换SoBalanceBillMapVO异常"*/);
    }

    List<SoBalanceBillMapVO> list = new ArrayList<SoBalanceBillMapVO>();
    for (int i = 0; i < mapvos.length; i++) {
      SoBalanceBillMapVO retMapvo = new SoBalanceBillMapVO();
      // 待核销单据类型
      String t_billtype = mapvos[i].getT_billtype();
      // 来源系统
      int s_system = mapvos[i].getS_system();
      // 如果非来源与销售的应收单不做核销
      if (IBillFieldGet.F0.equals(t_billtype)) {
        if (FromSystem.SO.VALUE.intValue() != s_system) {
          continue;
        }
        // 设置销售订单ID， 为以后按照销售订单分组应收单、收款单准备
        retMapvo.setOrderid(mapvos[i].getS_billid());
      }

      for (int j = 0; j < arapMapKeyChangeLength; j++) {
        String arapKey = null;
        if (isRetMapValue) {
          arapKey = SoBalance4VerifyQryBillAction.ARAPVOKEY_MAPVALUECHANGE[j];
        }
        else {
          arapKey = SoBalance4VerifyQryBillAction.ARAPVOKEY_MAPKEYCHANGE[j];
        }
        String soKey = SoBalance4VerifyQryBillAction.SOBALANCEBILLMAPVOKEY[j];
        retMapvo.setAttributeValue(soKey, mapvos[i].getAttributeValue(arapKey));
      }
      list.add(retMapvo);
    }

    SoBalanceBillMapVO[] retMapvo = null;
    int size = list.size();
    if (size > 0) {
      retMapvo = list.toArray(new SoBalanceBillMapVO[size]);
    }
    return retMapvo;
  }

  /**
   * 判断是否应收单和收款单同号
   * 
   * @param newYsVO
   * @param newSkVO
   * @return 应收单和收款单同号 -- true 应收单和收款单异号 -- false
   */
  private boolean checkisDiffSign(SoBalanceBillMapVO newYsVO,
      SoBalanceBillMapVO newSkVO) {
    // --判断是否应收单和收款单同号
    UFDouble ysmny = newYsVO.getYbye();
    UFDouble skmny = newSkVO.getYbye();
    if (MathTool.isDiffSign(ysmny, skmny)) {
      return false;
    }
    return true;
  }

  // ====================================================================
  private Map<ArapBillMapVO, Collection<ArapBillMapVO>> createReturnMap(
      Map<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>> voMap) {
    Map<ArapBillMapVO, Collection<ArapBillMapVO>> retMap =
        new HashMap<ArapBillMapVO, Collection<ArapBillMapVO>>();
    Set<Entry<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>>> entrySet =
        voMap.entrySet();
    for (Entry<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>> entry : entrySet) {
      // --转换key
      SoBalanceBillMapVO soBalanceBillMapVO = entry.getKey();
      ArapBillMapVO arKey =
          this.changeToArapBillMapVO(new SoBalanceBillMapVO[] {
            soBalanceBillMapVO
          })[0];
      // --转换value
      List<SoBalanceBillMapVO> soValueList =
          (List<SoBalanceBillMapVO>) entry.getValue();
      SoBalanceBillMapVO[] soValues =
          soValueList.toArray(new SoBalanceBillMapVO[soValueList.size()]);
      ArapBillMapVO[] arValues = this.changeToArapBillMapVO(soValues);

      List<ArapBillMapVO> arValueList = new ArrayList<ArapBillMapVO>();
      for (ArapBillMapVO arValue : arValues) {
        arValueList.add(arValue);
      }
      // --组装HashMap<ArapBillMapVO, Collection<ArapBillMapVO>>
      retMap.put(arKey, arValueList);
    }
    return retMap;
  }

  private void injectBalanceRelationMap(SoBalanceBillMapVO keyVO,
      SoBalanceBillMapVO valueVO,
      Map<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>> balanceRelationMap) {
    List<SoBalanceBillMapVO> valueList =
        (List<SoBalanceBillMapVO>) balanceRelationMap.get(keyVO);
    if (valueList == null) {
      valueList = new ArrayList<SoBalanceBillMapVO>();
    }
    valueList.add(valueVO);
    balanceRelationMap.put(keyVO, valueList);
  }

  /**
   * 如果balanceRelationMap中应收单或者收款单行id重复，需要合并
   * 目前的场景是一行收款单，作为多个key:SoBalanceBillMapVO，
   * 对应多组Collection<SoBalanceBillMapVO>
   * 因为一行收款单可以和多张不同的销售订单建立核销关系
   * 
   * @param balanceRelationMap
   */
  private void processRelationMap(
      Map<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>> balanceRelationMap) {
    List<SoBalanceBillMapVO> l_remove = new ArrayList<SoBalanceBillMapVO>();
    Map<String, Entry<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>>> mitemid =
        new HashMap<String, Entry<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>>>();
    for (Entry<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>> entry : balanceRelationMap
        .entrySet()) {
      SoBalanceBillMapVO keyVO = entry.getKey();
      String t_itemid = keyVO.getT_itemid();
      Entry<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>> mentry =
          mitemid.get(t_itemid);
      if (!SOVOChecker.isEmpty(mentry)) {
        mentry.getValue().addAll(entry.getValue());
        l_remove.add(keyVO);
      }
      else {
        mitemid.put(t_itemid, entry);
      }
    }

    for (SoBalanceBillMapVO vo : l_remove) {
      balanceRelationMap.remove(vo);
    }

  }

  // /**
  // * 收款单/应收单红蓝字对冲
  // * <p>
  // * 红蓝对冲关系保存到retMap，并返回剩余没有处理的蓝字vos
  // * </p>
  // *
  // * @param mapvos 要处理的ArapBillMapVO[]
  // * @param retMap 存储处理的关系的map
  // * @return ArapBillMapVO[] 没有做过处理的ArapBillMapVO[]
  // */
  // private SoBalanceBillMapVO[] processRush(SoBalanceBillMapVO[] vos,
  // Map<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>> balanceRelationMap)
  // {
  // if (vos == null || vos.length == 0) {
  // return null;
  // }
  // // -- 红蓝分组
  // List<SoBalanceBillMapVO> redList = new ArrayList<SoBalanceBillMapVO>();
  // List<SoBalanceBillMapVO> blueList = new ArrayList<SoBalanceBillMapVO>();
  // for (SoBalanceBillMapVO vo : vos) {
  // // --红字 < 0
  // if (MathTool.compareTo(vo.getYbye(), UFDouble.ZERO_DBL) < 0) {
  // redList.add(vo);
  // }
  // else {
  // blueList.add(vo);
  // }
  // }
  // List<SoBalanceBillMapVO> newBlueVOList =
  // new ArrayList<SoBalanceBillMapVO>();
  // // 有红字的
  // if (redList.size() > 0) {
  // // --计算并建立关系
  // newBlueVOList =
  // this.calculateRelation(redList, blueList, true, balanceRelationMap);
  // }
  // // 只有蓝字的
  // else {
  // newBlueVOList = blueList;
  // }
  // return newBlueVOList.toArray(new SoBalanceBillMapVO[newBlueVOList.size()]);
  // }

  private void processSK(SoBalanceBillMapVO[] skVOs,
      Map<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>> balanceRelationMap)
      throws BusinessException {
    // --收款单->收款单bids->SoBalanceViewVO->csaleorderids->应收单
    String[] t_itemids = SoVoTools.getVOsOnlyValues(skVOs, "t_itemid");
    ISOBalanceQuery service =
        NCLocator.getInstance().lookup(ISOBalanceQuery.class);
    SoBalanceViewVO[] views =
        service.querySoBalanceViewByGatheringBillBodyIDs(t_itemids);
    if (views == null || views.length == 0) {
      return;
    }

    // 因为1行收款单可能分别和多张销售订单核销，所以要重新拆分skVOs
    SoBalanceBillMapVO[] SkbyorderVOs = this.processSKVOs(skVOs, views);
    if (SkbyorderVOs == null || SkbyorderVOs.length == 0) {
      return;
    }

    // // 1.processRush：处理收款单红蓝对冲
    // SoBalanceBillMapVO[] newSkVOs =
    // this.processRush(SkbyorderVOs, balanceRelationMap);
    // // --红字>蓝字，即剩余的蓝字为0，不需在和应收单核销
    // if (newSkVOs == null || newSkVOs.length == 0) {
    // return;
    // }
    // 2.processVerify：根据订单收款核销关系建立返回map的收款单与应收单关系，
    // 同时将销售订单ID设置到对应的收款单上
    // -- 获得应收单信息VO
    SoBalanceBillMapVO[] ysVOs = this.queryYsVOsBySkVOs(views);

    // // -- 处理应收单红蓝对冲
    // SoBalanceBillMapVO[] newYsVOs = this.processRush(ysVOs,
    // balanceRelationMap);

    // 判断是否应收单和收款单是否同号，异号不处理核销
    if (SOVOChecker.isEmpty(ysVOs) || SOVOChecker.isEmpty(SkbyorderVOs)
        || !this.checkisDiffSign(ysVOs[0], SkbyorderVOs[0])) {
      return;
    }

    // -- 填充订单收款核销关系
    this.processVerify(SkbyorderVOs, ysVOs, balanceRelationMap);
  }

  /**
   * 因为1行收款单可能分别和多张销售订单核销，所以要重新拆分skVOs
   * 
   * @param skVOs
   * @param views
   * @return
   */
  private SoBalanceBillMapVO[] processSKVOs(SoBalanceBillMapVO[] skVOs,
      SoBalanceViewVO[] views) {
    Map<String, SoBalanceBillMapVO> mapskVOs =
        new HashMap<String, SoBalanceBillMapVO>();
    for (SoBalanceBillMapVO vo : skVOs) {
      mapskVOs.put(vo.getT_itemid(), vo);
    }

    List<SoBalanceBillMapVO> skVOList = new ArrayList<SoBalanceBillMapVO>();
    for (SoBalanceViewVO view : views) {
      SoBalanceHVO head = view.getHead();
      SoBalanceBVO body = view.getBody();
      UFDouble norigordbalmny = body.getNorigordbalmny();
      UFDouble norigaccbalmny = body.getNorigaccbalmny();
      // 可核销余额
      UFDouble canbalmny = MathTool.sub(norigordbalmny, norigaccbalmny);
      if (MathTool.absCompareTo(canbalmny, UFDouble.ZERO_DBL) == 0) {
        continue;
      }
      String t_itemid = body.getCpaybillrowid();
      SoBalanceBillMapVO oldvo = mapskVOs.get(t_itemid);
      // 返回值vo：收款单信息放在s_中
      SoBalanceBillMapVO skVO = new SoBalanceBillMapVO();
      // 设置收款单对应的销售订单ID
      skVO.setOrderid(head.getCsaleorderid());
      skVO.setYbye(canbalmny);
      skVO.setYbje(canbalmny);
      skVO.setS_billtype(ARAPBillType.GatheringOrder.getCode());
      skVO.setS_system(FromSystem.SO.VALUE.intValue());
      skVO.setS_termid(oldvo.getS_termid());
      skVO.setS_itemid(oldvo.getS_itemid());
      skVO.setS_billid(oldvo.getS_billid());
      skVO.setDr(oldvo.getDr());
      skVO.setPk_currtype(oldvo.getPk_currtype());
      skVO.setPk_org(oldvo.getPk_org());
      skVO.setMaptype(oldvo.getMaptype());
      skVO.setT_termid(oldvo.getT_termid());
      skVO.setT_itemid(oldvo.getT_itemid());
      skVO.setT_billid(oldvo.getT_billid());
      skVO.setT_billtype(oldvo.getT_billtype());
      skVO.setPk_billmap(oldvo.getPk_billmap());
      skVO.setOldje(oldvo.getOldje());
      skVO.setTs(oldvo.getTs());
      skVOList.add(skVO);
    }
    return skVOList.toArray(new SoBalanceBillMapVO[skVOList.size()]);
  }

  private void processVerify(SoBalanceBillMapVO[] keyVOs,
      SoBalanceBillMapVO[] valueVOs,
      Map<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>> balanceRelationMap) {
    if (keyVOs == null || keyVOs.length == 0 || valueVOs == null
        || valueVOs.length == 0) {
      return;
    }

    // 根据销售订单分组，一个销售订单对应一组应收单、收款单
    // <销售订单id，List<SoBalanceBillMapVO>> keyVOList
    MapList<String, SoBalanceBillMapVO> mkeyvo =
        new MapList<String, SoBalanceBillMapVO>();
    for (SoBalanceBillMapVO svo : keyVOs) {
      mkeyvo.put(svo.getOrderid(), svo);
    }
    // <销售订单id，List<SoBalanceBillMapVO>> valueVOList
    MapList<String, SoBalanceBillMapVO> mvaluevo =
        new MapList<String, SoBalanceBillMapVO>();
    for (SoBalanceBillMapVO svo : valueVOs) {
      mvaluevo.put(svo.getOrderid(), svo);
    }
    // 已销售订单为外层循环，建立应收单和收款单的核销关系
    Map<String, List<SoBalanceBillMapVO>> mkey = mkeyvo.toMap();
    for (Entry<String, List<SoBalanceBillMapVO>> entry : mkey.entrySet()) {
      String orderid = entry.getKey();
      List<SoBalanceBillMapVO> keyVOList = entry.getValue();
      List<SoBalanceBillMapVO> valueVOList = mvaluevo.get(orderid);
      if (SOVOChecker.isEmpty(valueVOList)) {
        continue;
      }
      // 计算并建立关系
      this.calculateRelation(keyVOList, valueVOList, balanceRelationMap);
    }

    // 如果balanceRelationMap中应收单或者收款单行id重复，需要合并
    this.processRelationMap(balanceRelationMap);

  }

  private void processYS(SoBalanceBillMapVO[] ysVOs,
      Map<SoBalanceBillMapVO, Collection<SoBalanceBillMapVO>> balanceRelationMap)
      throws BusinessException {
    // 1.processRush：处理应收单红蓝对冲
    // SoBalanceBillMapVO[] newYsVOs = this.processRush(ysVOs,
    // balanceRelationMap);
    // // --红字>蓝字，即剩余的蓝字为0，不需在和收款核销
    // if (newYsVOs == null || newYsVOs.length == 0) {
    // return;
    // }
    // 2.processVerify：根据订单收款核销关系建立返回map的收款单与应收单关系
    // -- 获得收款单信息VO
    SoBalanceBillMapVO[] skVOs = this.querySkVOsByYsVOs(ysVOs);
    // -- 处理收款单红蓝对冲
    // SoBalanceBillMapVO[] newSkVOs = this.processRush(skVOs,
    // balanceRelationMap);

    // 判断是否应收单和收款单是否同号，异号不处理核销
    if (SOVOChecker.isEmpty(ysVOs) || SOVOChecker.isEmpty(skVOs)
        || !this.checkisDiffSign(ysVOs[0], skVOs[0])) {
      return;
    }

    // -- 填充订单收款核销关系
    this.processVerify(ysVOs, skVOs, balanceRelationMap);
  }

  /**
   * 根据应收单SoBalanceBillMapVO查询可建立关系的收款单SoBalanceBillMapVO
   * 
   * @param ysVOs ysVO中来源 = 应收单的源头,即销售订单
   * @throws BusinessException
   */
  private SoBalanceBillMapVO[] querySkVOsByYsVOs(SoBalanceBillMapVO[] ysVOs)
      throws BusinessException {
    // --应收单->csaleorderids->SoBalanceVO->收款单
    String[] csaleorderids = new String[ysVOs.length];
    for (int i = 0; i < ysVOs.length; i++) {
      csaleorderids[i] = ysVOs[i].getS_billid();
    }
    ISOBalanceQuery service =
        NCLocator.getInstance().lookup(ISOBalanceQuery.class);
    SoBalanceVO[] vos = service.querySoBalanceVOBySaleOrderIDs(csaleorderids);
    if (vos == null || vos.length == 0) {
      return null;
    }
    Set<String> hids = new HashSet<String>();
    // 收款单ID
    for (SoBalanceVO vo : vos) {
      SoBalanceBVO[] bvos = vo.getChildrenVO();
      for (SoBalanceBVO bvo : bvos) {
        hids.add(bvo.getCpaybillid());
      }
    }
    // 查询已经生效的收款单IDS
    IArapGatheringBillPubServiceForSCM srv =
        NCLocator.getInstance()
            .lookup(IArapGatheringBillPubServiceForSCM.class);
    List<String> effectids =
        srv.getEffectGatherBill(hids.toArray(new String[hids.size()]));

    List<SoBalanceBillMapVO> skVOList = new ArrayList<SoBalanceBillMapVO>();
    for (SoBalanceVO vo : vos) {
      SoBalanceHVO head = vo.getParentVO();
      SoBalanceBVO[] bodys = vo.getChildrenVO();
      for (SoBalanceBVO body : bodys) {
        // 收款单未生效
        if (!effectids.contains(body.getCpaybillid())) {
          continue;
        }
        UFDouble norigordbalmny = body.getNorigordbalmny();
        UFDouble norigaccbalmny = body.getNorigaccbalmny();
        // 可核销余额
        UFDouble canbalmny = MathTool.sub(norigordbalmny, norigaccbalmny);
        if (MathTool.absCompareTo(canbalmny, UFDouble.ZERO_DBL) == 0) {
          continue;
        }
        // 返回值vo：收款单信息放在s_中
        SoBalanceBillMapVO skVO = new SoBalanceBillMapVO();
        // 设置收款单对应的销售订单ID
        skVO.setOrderid(head.getCsaleorderid());
        skVO.setS_billtype(ARAPBillType.GatheringOrder.getCode());
        skVO.setS_system(FromSystem.SO.VALUE.intValue());
        skVO.setS_termid(null);
        skVO.setS_itemid(body.getCpaybillrowid());
        skVO.setS_billid(body.getCpaybillid());
        skVO.setDr(Integer.valueOf(0));
        skVO.setPk_currtype(head.getCorigcurrencyid());
        skVO.setPk_org(head.getPk_org());
        skVO.setYbye(canbalmny);
        skVO.setYbje(canbalmny);
        skVO.setMaptype(0);
        skVO.setT_termid(null);
        skVO.setT_itemid(null);
        skVO.setT_billid(null);
        skVO.setT_billtype(null);
        skVO.setPk_billmap(null);
        skVO.setOldje(null);
        skVO.setTs(null);

        skVOList.add(skVO);
      }
    }
    return skVOList.toArray(new SoBalanceBillMapVO[skVOList.size()]);
  }

  /**
   * 根据收款单SoBalanceBillMapVO查询可建立关系的应收单SoBalanceBillMapVO
   * 同时将销售订单ID设置到对应的收款单上
   */
  private SoBalanceBillMapVO[] queryYsVOsBySkVOs(SoBalanceViewVO[] views)
      throws BusinessException {
    String[] csaleorderids = null;
    Set<String> csaleorderidSet = new HashSet<String>();
    for (SoBalanceViewVO view : views) {
      String orderid = view.getHead().getCsaleorderid();
      csaleorderidSet.add(orderid);
    }
    csaleorderids = csaleorderidSet.toArray(new String[csaleorderidSet.size()]);
    // --查询应收单信息
    IArapReceivableBillPubQueryService arapService =
        NCLocator.getInstance()
            .lookup(IArapReceivableBillPubQueryService.class);
    ArapBillMapVO[] ysVOs = arapService.queryArapBillmap(csaleorderids);
    return this.changeToSoBalanceBillMapVO(ysVOs, true);
  }

}
