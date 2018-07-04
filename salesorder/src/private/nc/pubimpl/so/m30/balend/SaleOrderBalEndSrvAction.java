package nc.pubimpl.so.m30.balend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.so.m30.state.SaleOrderStateMachine;
import nc.bs.so.m30.state.row.ArSettleCloseState;
import nc.bs.so.m30.state.row.ArSettleOpenState;
import nc.bs.so.m30.state.row.CostSettleCloseState;
import nc.bs.so.m30.state.row.CostSettleOpenState;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.tool.VOConcurrentTool;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.pubitf.so.m30.balend.BalEndPara;
import nc.pubitf.so.m30.balend.BalOpenPara;
import nc.vo.bd.material.IMaterialEnumConst;
import nc.vo.bd.material.fi.MaterialFiVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.balend.entity.BalCheckPara;
import nc.vo.so.m30.balend.entity.InvoiceBalVO;
import nc.vo.so.m30.balend.entity.SaleOutBalVO;
import nc.vo.so.m30.balend.enumeration.BalBillType;
import nc.vo.so.m30.balend.util.TriggerJudgeUtil;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.util.SOVOChecker;
import nc.vo.trade.checkrule.VOChecker;

public class SaleOrderBalEndSrvAction {

  // 影响成本结算订单表体ID
  Set<String> m_costBalEnd;

  // 触发点不影响成本结算订单表体ID
  Set<String> m_costNoAffect;

  // 影响应收结算订单表体ID
  Set<String> m_incomeBalEnd;

  // 触发点不影响应收结算订单表体ID
  Set<String> m_incomeNoAffect;

  public UFBoolean[] isCostBalEnd(String[] orderbids) {
    BalEndDataAccess dataacc = new BalEndDataAccess();
    Map<String, UFBoolean> map =
        dataacc.queryOrderEndFlag(orderbids, SaleOrderBVO.BBCOSTSETTLEFLAG);
    UFBoolean[] flag = new UFBoolean[orderbids.length];
    for (int i = 0; i < orderbids.length; i++) {
      UFBoolean tflag = map.get(orderbids[i]);
      flag[i] = tflag;
    }
    return flag;
  }

  public UFBoolean[] isIncomeBalEnd(String[] orderbids) {
    BalEndDataAccess dataacc = new BalEndDataAccess();
    Map<String, UFBoolean> map =
        dataacc.queryOrderEndFlag(orderbids, SaleOrderBVO.BBARSETTLEFLAG);
    UFBoolean[] flag = new UFBoolean[orderbids.length];
    for (int i = 0; i < orderbids.length; i++) {
      UFBoolean tflag = map.get(orderbids[i]);
      flag[i] = tflag;
    }
    return flag;

  }

  public void processAutoBalEnd(BalEndPara para) {
    // 要处理的销售订单行ID
    String[] orderbids = para.getOrderbids();
    // 订单行为空，返回
    if (VOChecker.isEmpty(orderbids)) {
      return;
    }
    // 加锁
    new VOConcurrentTool().lock(SaleOrderBVO.class, orderbids);

    BalEndDataAccess dataacc = new BalEndDataAccess();
    // 查询订单行结算关闭标志位信息
    Map<String, UFBoolean[]> mapBalEnd = dataacc.queryBalEndFlag(orderbids);
    // 应收结算关闭的订单子表ID
    this.m_incomeBalEnd = new HashSet<String>();
    this.m_incomeNoAffect = new HashSet<String>();
    // 成本结算关闭的订单子表ID
    this.m_costBalEnd = new HashSet<String>();
    this.m_costNoAffect = new HashSet<String>();

    // 触发点判定
    String trigger = para.getTrigger().getCode();
    // 触发点是否影响应收结算关闭
    boolean isIncomebal =
        TriggerJudgeUtil.getInstance().isAffectIncome(trigger);
    // 触发点是否影响成本结算关闭
    boolean isCostbal = TriggerJudgeUtil.getInstance().isAffectCost(trigger);

    for (String bid : orderbids) {
      UFBoolean[] balend = mapBalEnd.get(bid);

      // 影响应收结算关闭且未应收结算关闭
      if (isIncomebal && (null == balend[0] || !balend[0].booleanValue())) {
        this.m_incomeBalEnd.add(bid);
      }
      // 影响成本结算关闭且未成本结算关闭
      if (isCostbal && (null == balend[1] || !balend[1].booleanValue())) {
        this.m_costBalEnd.add(bid);
      }
    }
    // 根据判定条件决定是否结算关闭
    this.checkBalEndEnable(trigger);

    // 存在要应收或成本结算关闭的行
    if (this.m_incomeBalEnd.size() > 0 || this.m_costBalEnd.size() > 0) {
      this.processAfterBalEnd();
    }
  }

  /**
   * 
   * @param orderbids
   * @return <订单bid,[0]是否赠品、[1]是否费用折扣行、物料价值管理模式不是传存货核算>
   */
  private Map<String, UFBoolean[]> querySaleOrderEndInfo(String[] orderbids) {
    SaleOrderViewVO[] views = null;
    try {
      views =
          SOSaleOrderServicesUtil.querySaleOrderViewVOs(orderbids,
              new String[] {
              SaleOrderBVO.CSALEORDERBID, SaleOrderBVO.CSETTLEORGID,
              SaleOrderBVO.CMATERIALVID, SaleOrderBVO.BDISCOUNTFLAG,
              SaleOrderBVO.BLABORFLAG, SaleOrderBVO.BLARGESSFLAG
          });
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    if (views==null||views.length==0) {
      ExceptionUtils.wrappBusinessException("根据bids查询销售订单ViewVOs指定值失败！");/*-=notranslate=-*/
    }
    Map<String, Set<String>> mfin_mater = this.getmMaterialvid(views);
    // <财务组织oid+物料vid,是否物料价值管理模式是传存货核算Y|N>
    Map<String, String> result = new HashMap<String, String>();
    if (!VOChecker.isEmpty(mfin_mater)) {
      for (Entry<String, Set<String>> entry : mfin_mater.entrySet()) {
        Set<String> lmvid = entry.getValue();
        String[] mvids = lmvid.toArray(new String[lmvid.size()]);
        String finoid = entry.getKey();
        MaterialFiVO[] mfvos =
            MaterialPubService.queryMaterialFinanceInfoByPks(mvids, finoid,
                new String[] {
                MaterialFiVO.MATERIALVALUEMGT
            });
        for (MaterialFiVO mfvo : mfvos) {
          String flag = "N";
          if (IMaterialEnumConst.MATERIALVALUEMGT_INVCOSTING == mfvo
              .getMaterialvaluemgt().intValue()) {
            flag = "Y";
          }
          result.put(finoid + mfvo.getPk_material(), flag);
        }
      }
    }

    Map<String, UFBoolean[]> map = new HashMap<String, UFBoolean[]>();
    for (SaleOrderViewVO view : views) {
      SaleOrderBVO bvo = view.getBody();
      UFBoolean blargessflag = ValueUtils.getUFBoolean(bvo.getBlargessflag());
      UFBoolean bdiscountflag = ValueUtils.getUFBoolean(bvo.getBdiscountflag());
      UFBoolean blaborflag = ValueUtils.getUFBoolean(bvo.getBlaborflag());
      UFBoolean binvcosting =
          ValueUtils.getUFBoolean(result.get(bvo.getCsettleorgid()
              + bvo.getCmaterialvid()));
      UFBoolean bcostflag =
          ValueUtils.getUFBoolean(bdiscountflag.booleanValue()
              || blaborflag.booleanValue() || !binvcosting.booleanValue());
      UFBoolean[] flag = new UFBoolean[] {
          blargessflag, bcostflag
      };
      map.put(bvo.getCsaleorderbid(), flag);
    }

    return map;
  }

  /**
   * @return <财务组织oid,Set 物料vid>
   */
  private Map<String, Set<String>> getmMaterialvid(SaleOrderViewVO[] views) {
    Map<String, Set<String>> mfin_mater = new HashMap<String, Set<String>>();
    for (SaleOrderViewVO view : views) {
      String finoid = view.getBody().getCsettleorgid();
      String materialvid = view.getBody().getCmaterialvid();
      Set<String> set = mfin_mater.get(finoid);
      if (VOChecker.isEmpty(set)) {
        set = new HashSet<String>();
        mfin_mater.put(finoid, set);
      }
      set.add(materialvid);
    }
    return mfin_mater;
  }

  public void processAutoBalOpen(BalOpenPara para) {
    // 要处理的销售订单行ID
    String[] orderbids = para.getOrderbids();
    // 订单行为空，返回
    if (VOChecker.isEmpty(orderbids)) {
      return;
    }
    // 加锁
    new VOConcurrentTool().lock(SaleOrderBVO.class, orderbids);

    BalEndDataAccess dataacc = new BalEndDataAccess();
    // 查询订单行结算关闭标志位信息
    Map<String, UFBoolean[]> mapBalEnd = dataacc.queryBalEndFlag(orderbids);
    // 进行应收结算标志判断的订单子表ID
    this.m_incomeBalEnd = new HashSet<String>();
    this.m_incomeNoAffect = new HashSet<String>();
    // 进行成本结算标志判断的订单子表ID
    this.m_costBalEnd = new HashSet<String>();
    this.m_costNoAffect = new HashSet<String>();
    // 触发点判定
    String trigger = para.getTrigger().getCode();
    boolean isIncomebal =
        TriggerJudgeUtil.getInstance().isAffectIncome(trigger);
    boolean isCostbal = TriggerJudgeUtil.getInstance().isAffectCost(trigger);

    List<String> incomeendids = new ArrayList<String>();
    List<String> costendids = new ArrayList<String>();
    // 查询订单行是否赠品、费用折扣行、物料价值管理模式不是传存货核算的标志，这些行不做自动结算打开
    Map<String, UFBoolean[]> map = this.querySaleOrderEndInfo(orderbids);
    for (String bid : orderbids) {
      UFBoolean[] balend = mapBalEnd.get(bid);
      UFBoolean[] flag = map.get(bid);
      UFBoolean barflag = flag[0];
      UFBoolean biaflag = flag[1];
      // 影响应收结算关闭且已关闭
      if (isIncomebal && null != balend[0] && balend[0].booleanValue()
          && !barflag.booleanValue()) {
        this.m_incomeBalEnd.add(bid);
        incomeendids.add(bid);
      }
      // 影响成本结算关闭且已关闭
      if (isCostbal && null != balend[1] && balend[1].booleanValue()
          && !biaflag.booleanValue()) {
        this.m_costBalEnd.add(bid);
        costendids.add(bid);
      }
    }
    // 根据判定条件决定是否结算关闭
    this.checkBalEndEnable(trigger);

    // 存在要应收或成本结算关闭的行
    if (incomeendids.size() > 0 || costendids.size() > 0) {
      this.processAfterBalOpen(incomeendids, costendids);
    }

  }

  private void checkAllApprove(Map<String, BalCheckPara> mapCheckParas,
      String billtypecode) {
    Set<String> orderids = new HashSet<String>();
    String[] orderbids =
        this.getFilteOrderbids(mapCheckParas, billtypecode, orderids);
    if (orderbids.length > 0) {
      BalEndDataAccess dataacc = new BalEndDataAccess();
      UFBoolean[] isAllApprove =
          dataacc.queryBalBillApprove(billtypecode,
              orderids.toArray(new String[orderids.size()]), orderbids);
      int iloop = orderbids.length;
      for (int i = 0; i < iloop; i++) {
        if (null == isAllApprove[i] || !isAllApprove[i].booleanValue()) {
          this.m_incomeBalEnd.remove(orderbids[i]);
          this.m_costBalEnd.remove(orderbids[i]);
        }
      }
    }
  }

  /**
   * 方法功能描述：处理订单行下游所有出库单/发票全部签字/审批。
   * <p>
   * <b>参数说明</b>
   * 
   * @param mapCheckParas
   *          <p>
   * @author fengjb
   * @time 2010-7-20 上午09:41:46
   */
  private void checkBalBillApprove(Map<String, BalCheckPara> mapCheckParas) {
    this.checkAllApprove(mapCheckParas, SOBillType.Invoice.getCode());
    this.checkAllApprove(mapCheckParas, ICBillType.SaleOut.getCode());
  }

  /**
   * 方法功能描述：处理所有出库单/发票行结算关闭。
   * <p>
   * <b>参数说明</b>
   * 
   * @param mapCheckParas
   *          <p>
   * @author fengjb
   * @time 2010-7-20 上午10:21:12
   */
  private void checkBalBillBalClose(Map<String, BalCheckPara> mapCheckParas) {
    this.checkBillBalClose(mapCheckParas, SOBillType.Invoice.getCode());
    this.checkBillBalClose(mapCheckParas, ICBillType.SaleOut.getCode());
  }

  /**
   * 方法功能描述：订单行已经出库/开票关闭。
   * <p>
   * <b>参数说明</b>
   * 
   * @param mapCheckParas
   *          <p>
   * @author fengjb
   * @time 2010-7-20 上午09:42:28
   */
  private void checkBalBillOrderClose(Map<String, BalCheckPara> mapCheckParas) {
    this.checkOrderClose(mapCheckParas, SOBillType.Invoice.getCode());
    this.checkOrderClose(mapCheckParas, ICBillType.SaleOut.getCode());
  }

  /**
   * 
   * 方法功能描述：检查传入销售订单附表ID对应行是否能够自动应收/成本结算关闭。
   * <p>
   * <b>参数说明</b>
   * 
   * @param trigger
   *          <p>
   * @author fengjb
   * @time 2010-9-13 下午02:29:37
   */
  private void checkBalEndEnable(String trigger) {
    // 合并应收和成本关闭查询，减少数据库连接次数
    Set<String> hsAllBids = new HashSet<String>();
    hsAllBids.addAll(this.m_incomeBalEnd);
    hsAllBids.addAll(this.m_costBalEnd);
    // 没有要检查的订单BID，返回
    if (hsAllBids.size() == 0) {
      return;
    }
    // 查询订单行下游单据结算明细信息
    String[] filterbids = hsAllBids.toArray(new String[0]);
    BalEndDataAccess dataacc = new BalEndDataAccess();
    Map<String, BalCheckPara> mapCheckParas = dataacc.querySquare(filterbids);
    // 检查结算明细数据，如果某订单BID没有明细记录，不能关闭
    for (String bid : filterbids) {
      if (!mapCheckParas.containsKey(bid)) {
        this.m_incomeBalEnd.remove(bid);
        this.m_costBalEnd.remove(bid);
      }
    }
    if (this.m_incomeBalEnd.size() == 0 && this.m_costBalEnd.size() == 0) {
      return;
    }

    // 设置结算类型
    this.setBalType(mapCheckParas, trigger);
    // 触发点和结算单据类型判定
    this.checkTrigger(mapCheckParas, trigger);
    // 所有出库单/发票全部签字/审批
    this.checkBalBillApprove(mapCheckParas);
    // 订单行已经出库/开票关闭
    this.checkBalBillOrderClose(mapCheckParas);
    // 所有出库单/发票行结算关闭
    this.checkBalBillBalClose(mapCheckParas);
    // 处理其他条件
    this.checkOthercon(mapCheckParas);
  }

  private void checkBillBalClose(Map<String, BalCheckPara> mapCheckParas,
      String billtype) {
    Set<String> orderids = new HashSet<String>();
    String[] orderbids =
        this.getFilteOrderbids(mapCheckParas, billtype, orderids);
    if (orderbids.length > 0) {
      BalEndDataAccess dataacc = new BalEndDataAccess();

      Map<String, UFBoolean[]> mapbalclose =
          dataacc.queryBillBalClose(billtype, orderbids);

      for (String orderbid : orderbids) {
        BalCheckPara para = mapCheckParas.get(orderbid);
        UFBoolean[] isbalclose = mapbalclose.get(orderbid);
        if (null == isbalclose) {
          continue;
        }
        // 下游结算单据不影响应收结算||下游结算单据行未应收结算关闭
        if (!para.isAffectIncomeBal(billtype) || !isbalclose[0].booleanValue()) {
          this.m_incomeBalEnd.remove(orderbid);
        }
        // 下游结算单据不影响成本结算||下游结算单据行成本结算关闭
        if (!para.isAffectCostBal(billtype) || !isbalclose[1].booleanValue()) {
          this.m_costBalEnd.remove(orderbid);
        }
      }
    }
  }

  /**
   * 方法功能描述：处理触发点是否影响成本结算关闭。
   * <p>
   * <b>参数说明</b>
   * 
   * @param para
   * @param trigger
   *          <p>
   * @author fengjb
   * @time 2010-7-20 下午02:39:26
   */
  private void checkCostTrigger(BalCheckPara para, String trigger) {
    String orderbid = para.getOrderbid();
    if (!this.m_costBalEnd.contains(orderbid)) {
      return;
    }
    BalBillType costbaltype = para.getCostbaltype();
    if (null == costbaltype) {
      return;
    }

    // 成本结算单据只有发票，触发点只影响出库单 或者 成本结算单据只有出库单，触发点只影响发票
    if (BalBillType.VOICECOST.equalsValue(costbaltype) && TriggerJudgeUtil
        .getInstance().isOnlyAffectOut(trigger)
        || BalBillType.OUTCOST.equalsValue(costbaltype) && TriggerJudgeUtil
        .getInstance().isOnlyAffectVoice(trigger)
        || BalBillType.OnlyVOICECOST.equalsValue(costbaltype) && TriggerJudgeUtil
        .getInstance().isOnlyAffectOutNoWas(trigger)) {
      this.m_costBalEnd.remove(orderbid);
      this.m_costNoAffect.add(orderbid);
    }

  }

  /**
   * 方法功能描述：处理触发点是否影响应收成本结算关闭。
   * <p>
   * <b>参数说明</b>
   * 
   * @param para
   * @param trigger
   *          <p>
   * @author fengjb
   * @time 2010-7-20 下午02:39:51
   */
  private void checkIncomeTrigger(BalCheckPara para, String trigger) {
    String orderbid = para.getOrderbid();
    if (!this.m_incomeBalEnd.contains(orderbid)) {
      return;
    }
    BalBillType incomebaltype = para.getIncomebaltype();
    // 应收结算单据只有发票，触发点却只影响销售出库单 或者 应收结算单据只有出库单，触发点只影响发票
    if (BalBillType.VOICEINCOME.equalsValue(incomebaltype) && TriggerJudgeUtil
        .getInstance().isOnlyAffectOut(trigger)
        || BalBillType.OUTINCOME.equalsValue(incomebaltype) && TriggerJudgeUtil
        .getInstance().isOnlyAffectVoice(trigger)
        || BalBillType.OnlyVOICEINCOME.equalsValue(incomebaltype) && TriggerJudgeUtil
        .getInstance().isOnlyAffectOutNoWas(trigger)) {
      this.m_incomeBalEnd.remove(orderbid);
      this.m_incomeNoAffect.add(orderbid);
    }
  }

  private void checkOrderClose(Map<String, BalCheckPara> mapCheckParas,
      String billtypecode) {
    Set<String> orderids = new HashSet<String>();
    String[] orderbids =
        this.getFilteOrderbids(mapCheckParas, billtypecode, orderids);
    if (orderbids.length > 0) {
      String key = null;
      if (SOBillType.Invoice.isEqual(billtypecode)) {
        key = SaleOrderBVO.BBINVOICENDFLAG;
      }
      else if (ICBillType.SaleOut.isEqual(billtypecode)) {
        key = SaleOrderBVO.BBOUTENDFLAG;
      }
      BalEndDataAccess dataacc = new BalEndDataAccess();
      Map<String, UFBoolean> map = dataacc.queryOrderEndFlag(orderbids, key);
      int iloop = orderbids.length;
      for (int i = 0; i < iloop; i++) {
        UFBoolean isorderclose = map.get(orderbids[i]);
        if (null == isorderclose || !isorderclose.booleanValue()) {
          this.m_incomeBalEnd.remove(orderbids[i]);
          this.m_costBalEnd.remove(orderbids[i]);
        }
      }
    }
  }

  /**
   * 方法功能描述：处理其他校验条件，目前是途损单全部签字、虚权组件。
   * <p>
   * <b>参数说明</b>
   * 
   * @param mapCheckParas
   *          <p>
   * @author fengjb
   * @time 2010-7-20 下午02:40:55
   */
  private void checkOthercon(Map<String, BalCheckPara> mapCheckParas) {
    Set<String> orderids = new HashSet<String>();
    String[] orderbids =
        this.getFilteOrderbids(mapCheckParas, ICBillType.WastageBill.getCode(),
            orderids);
    if (orderbids.length > 0) {
      // 调用途损单提供接口
      BalEndDataAccess dataacc = new BalEndDataAccess();
      UFBoolean[] allapprove =
          dataacc.queryBalBillApprove(ICBillType.WastageBill.getCode(),
              orderids.toArray(new String[orderids.size()]), orderbids);
      int iloop = orderbids.length;
      for (int i = 0; i < iloop; i++) {
        BalCheckPara para = mapCheckParas.get(orderbids[i]);
        if (!allapprove[i].booleanValue()
            && para.isAffectIncomeBal(ICBillType.WastageBill.getCode())) {
          this.m_incomeBalEnd.remove(orderbids[i]);
        }
        if (!allapprove[i].booleanValue()

            && para.isAffectCostBal(ICBillType.WastageBill.getCode())) {
          this.m_costBalEnd.remove(orderbids[i]);
        }
      }
    }
  }

  /**
   * 方法功能描述：触发点和结算关闭的关系。
   * <p>
   * <b>参数说明</b>
   * 
   * @param mapCheckParas
   * @param trigger
   *          <p>
   * @author fengjb
   * @time 2010-7-19 下午03:05:25
   */
  private void checkTrigger(Map<String, BalCheckPara> mapCheckParas,
      String trigger) {

    for (Entry<String, BalCheckPara> entry : mapCheckParas.entrySet()) {
      BalCheckPara para = entry.getValue();
      // 处理应收结算单据类型
      this.checkIncomeTrigger(para, trigger);
      // 处理成本结算单据类型
      this.checkCostTrigger(para, trigger);
    }

  }

  /**
   * 方法功能描述：返回所有影响结算的销售订单ID数组。
   * <p>
   * <b>参数说明</b>
   * 
   * @param mapCheckParas
   * @param billtype
   * @return <p>
   * @author fengjb
   * @time 2010-7-20 下午02:38:53
   */
  private String[] getFilteOrderbids(Map<String, BalCheckPara> mapCheckParas,
      String billtype, Set<String> orderids) {

    List<String> alorderbids = new ArrayList<String>();
    for (Entry<String, BalCheckPara> entry : mapCheckParas.entrySet()) {
      String orderbid = entry.getKey();
      BalCheckPara para = entry.getValue();
      if ((this.m_incomeBalEnd.contains(orderbid) || this.m_costBalEnd
          .contains(orderbid)) && para.isAffectBal(billtype)) {
        alorderbids.add(orderbid);
        orderids.add(para.getOrderid());
      }
    }
    return alorderbids.toArray(new String[0]);
  }

  private void processAfterBalEnd() {
    // 要进行自动结算关闭的销售订单表体VO
    Set<String> hsupdate = new HashSet<String>();
    hsupdate.addAll(this.m_incomeBalEnd);
    hsupdate.addAll(this.m_costBalEnd);

    ViewQuery<SaleOrderViewVO> viewquery =
        new ViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class);
    SaleOrderViewVO[] viewvos =
        viewquery.query(hsupdate.toArray(new String[0]));

    List<SaleOrderViewVO> arclosviews = new ArrayList<SaleOrderViewVO>();
    List<SaleOrderViewVO> costcloseviews = new ArrayList<SaleOrderViewVO>();

    for (SaleOrderViewVO view : viewvos) {
      String bid = view.getBody().getCsaleorderbid();
      if (this.m_incomeBalEnd.contains(bid)) {
        arclosviews.add(view);
      }
      if (this.m_costBalEnd.contains(bid)) {
        costcloseviews.add(view);
      }
    }
    // 使用销售订单状态机统一处理单据状态，回写整单状态和影响信用统一在一起
    SaleOrderStateMachine statemachine = new SaleOrderStateMachine();
    if (arclosviews.size() > 0) {
      ArSettleCloseState arclosestate = new ArSettleCloseState();
      SaleOrderViewVO[] arclosevos =
          arclosviews.toArray(new SaleOrderViewVO[0]);
      statemachine.setState(arclosestate, arclosevos);
    }

    if (costcloseviews.size() > 0) {
      CostSettleCloseState costclose = new CostSettleCloseState();
      SaleOrderViewVO[] costclosevos =
          costcloseviews.toArray(new SaleOrderViewVO[0]);
      statemachine.setState(costclose, costclosevos);
    }

  }

  /**
   * 
   * 方法功能描述：自动应收/成本结算打开后续处理，包括状态更新、信用处理、删除回冲应收。
   * <p>
   * <b>参数说明</b>
   * 
   * @param incomeendids
   * @param costendids
   *          <p>
   * @author fengjb
   * @time 2010-9-13 下午02:31:32
   */
  private void processAfterBalOpen(List<String> incomeendids,
      List<String> costendids) {

    Set<String> setAropenId = new HashSet<String>();
    Set<String> setCostopenId = new HashSet<String>();
    for (String bid : incomeendids) {
      if (!this.m_incomeBalEnd.contains(bid)
          && !this.m_incomeNoAffect.contains(bid)) {
        setAropenId.add(bid);
      }
    }
    for (String bid : costendids) {
      if (!this.m_costBalEnd.contains(bid)
          && !this.m_costNoAffect.contains(bid)) {
        setCostopenId.add(bid);
      }
    }
    Set<String> hsupdate = new HashSet<String>();
    hsupdate.addAll(setAropenId);
    hsupdate.addAll(setCostopenId);
    String[] openids = hsupdate.toArray(new String[0]);

    ViewQuery<SaleOrderViewVO> viewquery =
        new ViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class);
    SaleOrderViewVO[] viewvos = viewquery.query(openids);

    List<SaleOrderViewVO> aropenviews = new ArrayList<SaleOrderViewVO>();
    List<SaleOrderViewVO> costopenviews = new ArrayList<SaleOrderViewVO>();

    for (SaleOrderViewVO view : viewvos) {
      String bid = view.getBody().getCsaleorderbid();
      if (setAropenId.contains(bid)) {
        aropenviews.add(view);
      }
      if (setCostopenId.contains(bid)) {
        costopenviews.add(view);
      }
    }
    // 使用销售订单状态机统一处理单据状态，回写整单状态和影响信用统一在一起
    SaleOrderStateMachine statemachine = new SaleOrderStateMachine();
    if (aropenviews.size() > 0) {
      ArSettleOpenState arclosestate = new ArSettleOpenState();
      SaleOrderViewVO[] arclosevos =
          aropenviews.toArray(new SaleOrderViewVO[0]);
      statemachine.setState(arclosestate, arclosevos);
    }

    if (costopenviews.size() > 0) {
      CostSettleOpenState costclose = new CostSettleOpenState();
      SaleOrderViewVO[] costclosevos =
          costopenviews.toArray(new SaleOrderViewVO[0]);
      statemachine.setState(costclose, costclosevos);
    }
    // 暂估应收结算关闭后生成回冲应收单
    /*    IProcess4CAdjustFor30BalEnd adjustsrv =
            NCLocator.getInstance().lookup(IProcess4CAdjustFor30BalEnd.class);
        String[] incomebalendids = this.m_incomeBalEnd.toArray(new String[0]);
        adjustsrv.process4CAdjust(incomebalendids);*/
  }

  /**
   * 方法功能描述：处理应收和成本结算单据类型。
   * <p>
   * <b>参数说明</b>
   * 
   * @param mapCheckParas
   *          <p>
   * @author fengjb
   * @time 2010-7-19 下午02:41:34
   */
  private void setBalType(Map<String, BalCheckPara> mapCheckParas,
      String trigger) {

    for (Entry<String, BalCheckPara> entry : mapCheckParas.entrySet()) {
      BalCheckPara para = entry.getValue();
      // 处理应收结算单据类型
      this.setIncomeBalType(para, trigger);
      // 处理成本结算单据类型
      this.setCostBalType(para, trigger);
    }
  }

  /**
   * 方法功能描述：处理成本结算单据类型。
   * <p>
   * <b>参数说明</b>
   * 
   * @param para
   *          <p>
   * @author fengjb
   * @time 2010-7-19 下午02:34:06
   */
  private void setCostBalType(BalCheckPara para, String trigger) {
    String orderbid = para.getOrderbid();
    // 如果没有在影响成本结算关闭的set中不用处理
    if (!this.m_costBalEnd.contains(orderbid)) {
      return;
    }
    SaleOutBalVO outbal = para.getOutbal();
    InvoiceBalVO voicebal = para.getInvoicebal();
    // 出库单手工计入发出商品
    if (null != outbal && outbal.isManualReg()) {
      if (!TriggerJudgeUtil.getInstance().isVoiceCloseTrigger(trigger)
          && (null == voicebal || !voicebal.isCostbal())) {
        this.m_costBalEnd.remove(orderbid);
      }
      else {
        para.setCostbaltype(BalBillType.OnlyVOICECOST);
      }
    }
    // 出库单自动计入发出商品
    else if (null != outbal && outbal.isAutoReg()) {
      // 如果没有发票数据，则成本结算无法关闭
      if (!TriggerJudgeUtil.getInstance().isVoiceCloseTrigger(trigger)
          && (null == voicebal || !voicebal.isCostbal())) {
        this.m_costBalEnd.remove(orderbid);
      }
      else {
        para.setCostbaltype(BalBillType.BOTHCOST);
      }
    }

    // 出库单参与成本结算
    else if (null != outbal && outbal.isCostbal()) {
      if (null != voicebal && voicebal.isCostbal()) {
        para.setCostbaltype(BalBillType.BOTHCOST);
      }
      else {
        para.setCostbaltype(BalBillType.OUTCOST);
      }
    } // 出库单不参与结算动作
    else {
      if (null != voicebal && voicebal.isCostbal()) {
        para.setCostbaltype(BalBillType.VOICECOST);
      }
      else {
        para.setCostbaltype(BalBillType.NONEBAL);
        this.m_costBalEnd.remove(orderbid);
      }
    }
  }

  /**
   * 方法功能描述：处理应收结算单据类型。
   * <p>
   * <b>参数说明</b>
   * 
   * @param para
   *          <p>
   * @author fengjb
   * @time 2010-7-19 下午02:16:29
   */
  private void setIncomeBalType(BalCheckPara para, String trigger) {
    String orderbid = para.getOrderbid();
    // 如果没有在影响应收结算关闭的set中不用处理
    if (!this.m_incomeBalEnd.contains(orderbid)) {
      return;
    }
    SaleOutBalVO outbal = para.getOutbal();
    InvoiceBalVO voicebal = para.getInvoicebal();

    // 出库单手工暂估
    if (null != outbal && outbal.isManualEt()) {
      // 如果没有发票数据，则应收结算无法关闭
      if (!TriggerJudgeUtil.getInstance().isVoiceCloseTrigger(trigger)
          && (null == voicebal || !voicebal.isIncomebal())) {
        this.m_incomeBalEnd.remove(orderbid);
      }
      else {
        para.setIncomebaltype(BalBillType.OnlyVOICEINCOME);
      }
    } // 出库单自动暂估
    else if (null != outbal && outbal.isAutoEt()) {
      // 如果没有发票数据，则应收结算无法关闭
      if (!TriggerJudgeUtil.getInstance().isVoiceCloseTrigger(trigger)
          && (null == voicebal || !voicebal.isIncomebal())) {
        this.m_incomeBalEnd.remove(orderbid);
      }
      else {
        para.setIncomebaltype(BalBillType.BOTHINCOME);
      }
    } // 出库单参与应收结算
    else if (null != outbal && outbal.isIncomebal()) {
      if (null != voicebal && voicebal.isIncomebal()) {
        para.setIncomebaltype(BalBillType.BOTHINCOME);
      }
      else {
        para.setIncomebaltype(BalBillType.OUTINCOME);
      }
    } // 出库单不参与应收结算
    else {
      if (null != voicebal && voicebal.isIncomebal()) {
        para.setIncomebaltype(BalBillType.VOICEINCOME);
      }
      else {
        para.setIncomebaltype(BalBillType.NONEBAL);
        this.m_incomeBalEnd.remove(orderbid);
      }
    }
  }

}
