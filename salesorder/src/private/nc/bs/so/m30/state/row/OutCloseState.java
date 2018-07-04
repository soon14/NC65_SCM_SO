package nc.bs.so.m30.state.row;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.so.m30.plugin.StatePlugInPoint;
import nc.bs.so.m30.rule.atp.SaleOrderViewATPAfterRule;
import nc.bs.so.m30.rule.atp.SaleOrderViewATPBeforeRule;
import nc.bs.so.m30.rule.credit.RenovateARByBidsBeginRule;
import nc.bs.so.m30.rule.credit.RenovateARByBidsEndRule;
import nc.bs.so.m30.rule.reserve.ReserveCloseRule;
import nc.bs.so.m30.rule.rewrite.m35.Rewrite35WhenOutClose;
import nc.bs.so.m30.rule.rewrite.m4331.Rewrite4331WhenOutCloseRule;
import nc.bs.so.m30.rule.rewrite.me.RewriteME35WhenOutClose;
import nc.bs.so.m30.rule.rewrite.opc.RewriteOPCWhenOutClose;
import nc.bs.so.m30.rule.rewrite.price.RewriteProPirceWhenOutClose;
import nc.bs.so.m30.state.BillStateUtil;
import nc.bs.so.m30.state.StateCalculateUtil;
import nc.impl.pubapp.bill.state.AbstractRowState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.pubitf.so.m30.balend.BalEndPara;
import nc.vo.bd.material.MaterialVO;
import nc.vo.credit.engrossmaintain.pub.action.M30EngrossAction;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.balend.enumeration.BalEndTrigger;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.util.SOSysInitGroupQuery;

/**
 * 销售订单出库关闭
 * 
 * @author 刘志伟
 * @since 6.0
 * @time 2010-01-28 下午13:49:07
 */
public class OutCloseState extends AbstractRowState<SaleOrderViewVO> {

  private StateCalculateUtil stateCalculateUtil;

  /**
   * 销售订单出库关闭构造子
   */
  public OutCloseState() {
    super(SaleOrderBVO.class, SaleOrderBVO.BBOUTENDFLAG, UFBoolean.TRUE);
  }

  @Override
  public boolean isAutoTransitable(SaleOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }

    return this.getStateCalculateUtil().isAutoTransitOutClose(vo);
  }

  /**
   * 是否可以出库关闭
   * 
   * @param vo
   * @param materrialmaps
   * @return boolean
   */
  public boolean isOutClose(SaleOrderViewVO vo,
      Map<String, MaterialVO> materrialmaps) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isOutClose(vo, materrialmaps);
  }

  
  /**
   * 是否可以出库关闭4修订
   * 
   * @param vo
   * @return boolean
   */
  public boolean isOutColseForRevise(SaleOrderViewVO vo,
      SaleOrderViewVO originVo, Map<String, MaterialVO> materrialmaps) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isOutCloseForRevise(vo, originVo,
        materrialmaps);
  }
  
  /**
   * 是否可以出库关闭4途损单
   * 
   * @param vo
   * @param materrialmaps
   * @return boolean
   */
  public boolean isOutCloseFor4453(SaleOrderViewVO vo,
      Map<String, MaterialVO> materrialmaps) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isOutCloseFor4453(vo, materrialmaps);
  }

  @Override
  public boolean isPrevStateValid(SaleOrderViewVO vo) {
    // 只有整单打开状态以及之后的整单状态才可以做行状态的操作
    BillStateUtil statePriority = new BillStateUtil();
    return statePriority.canBeExecuteState(vo);
  }

  @Override
  public List<IState<SaleOrderViewVO>> next() {
    List<IState<SaleOrderViewVO>> list =
        new ArrayList<IState<SaleOrderViewVO>>();
    list.add(new SendCloseState());
    list.add(new RowCloseState());
    return list;
  }

  @Override
  public void setState(SaleOrderViewVO[] views) {
    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(StatePlugInPoint.OutCloseState);
    this.addRule(processer);

    TimeLog.logStart();
    SaleOrderViewVO[] vos = processer.before(views);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0150")/*@res "出库关闭前执行业务规则"*/);

    TimeLog.logStart();
    super.setState(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0151")/*@res "修改表体状态为出库关闭"*/);

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0152")/*@res "出库关闭后执行业务规则"*/);

    /**
     * 处理结算关闭，由于历史代码原因，没有根据状态机进行越迁方式进行编写
     * 正确的写法应该在next()方法中和ArSettleCloseState中isAutoTransitable（）写逻辑
     * 但状态机不支持批量模式.....
     */
    this.processOrderSquareClose(views);

  }

  private void addRule(AroundProcesser<SaleOrderViewVO> processer) {

    // 更新信用调用前
    processer.addBeforeRule(new RenovateARByBidsBeginRule(
        M30EngrossAction.M30OutClose));

    // --------------------------------------

    // 更新信用调用后
    processer.addAfterRule(new RenovateARByBidsEndRule(
        M30EngrossAction.M30OutClose));

    // 出库关闭后处理上游发货单(要求发货单处理单据时不调用信用,并放在销售订单调用可用量逻辑之外)
    processer.addAfterRule(new Rewrite4331WhenOutCloseRule());
    boolean icEnable = SysInitGroupQuery.isICEnabled();
    if (icEnable) {
      // 可用量变更前
      processer.addBeforeRule(new SaleOrderViewATPBeforeRule());
      // 可用量变更后
      processer.addAfterRule(new SaleOrderViewATPAfterRule());
      // 预留调整
      processer.addAfterRule(new ReserveCloseRule());
    }

    // 调整冲抵关系
    if (SOSysInitGroupQuery.isMeEnabled()) {
      processer.addBeforeRule(new RewriteME35WhenOutClose());
    }
    else {
      processer.addAfterRule(new Rewrite35WhenOutClose());
    }
    if(SysInitGroupQuery.isOPCEnabled()){
      // 回写电子销售出库关闭状态
      processer.addAfterRule(new RewriteOPCWhenOutClose());
    }
    // 回写促销价格表
    if (SysInitGroupQuery.isPRICEEnabled()) {
      processer.addAfterRule(new RewriteProPirceWhenOutClose());
    }
  }

  private StateCalculateUtil getStateCalculateUtil() {
    if (this.stateCalculateUtil == null) {
      this.stateCalculateUtil = new StateCalculateUtil();
    }
    return this.stateCalculateUtil;
  }

  /**
   * 处理结算关闭
   * 
   * @param views
   */
  private void processOrderSquareClose(SaleOrderViewVO[] views) {
    // 销售订单表体id
    int len = views.length;
    String[] orderbids = new String[len];
    for (int i = 0; i < len; i++) {
      orderbids[i] = views[i].getBody().getCsaleorderbid();
    }
    BalEndTrigger trigger = BalEndTrigger.OUT_CLOSE;
    BalEndPara para = new BalEndPara(orderbids, trigger);
    try {
      SOSaleOrderServicesUtil.processAutoBalEnd(para);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    /*** 2012-04-28 冯加滨 幺贵敬 只有手工操作需要刷新前台TS，因此迁移到最外层取处理 ***/
    // // 结算关闭刷新ts，结算关闭标志
    // new SaleOrderVOUtil().refreshViewForSettleClose(views);
  }

}
