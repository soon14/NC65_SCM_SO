package nc.bs.so.m30.state.row;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.so.m30.plugin.StatePlugInPoint;
import nc.bs.so.m30.rule.atp.SaleOrderViewATPAfterRule;
import nc.bs.so.m30.rule.atp.SaleOrderViewATPBeforeRule;
import nc.bs.so.m30.rule.credit.RenovateARByBidsBeginRule;
import nc.bs.so.m30.rule.credit.RenovateARByBidsEndRule;
import nc.bs.so.m30.rule.rewrite.price.RewriteProPirceWhenSendClose;
import nc.bs.so.m30.state.BillStateUtil;
import nc.bs.so.m30.state.StateCalculateUtil;
import nc.impl.pubapp.bill.state.AbstractRowState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.bd.material.MaterialVO;
import nc.vo.credit.engrossmaintain.pub.action.M30EngrossAction;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * 销售订单发货关闭
 * 
 * @author 刘志伟
 * @time 2010-01-28 下午13:49:07
 */
public class SendCloseState extends AbstractRowState<SaleOrderViewVO> {

  private StateCalculateUtil stateCalculateUtil;

  public SendCloseState() {
    super(SaleOrderBVO.class, SaleOrderBVO.BBSENDENDFLAG, UFBoolean.TRUE);
  }

  @Override
  public boolean isAutoTransitable(SaleOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }

    return this.getStateCalculateUtil().isAutoTransitSendClose(vo);
  }

  @Override
  public boolean isPrevStateValid(SaleOrderViewVO vo) {
    // 只有整单打开状态以及之后的整单状态才可以做行状态的操作
    BillStateUtil statePriority = new BillStateUtil();
    return statePriority.canBeExecuteState(vo);
  }

  /**
   * 修订时是否可以关闭
   * 
   * @param vo
   * @return boolean
   */
  public boolean isSendColseForRevise(SaleOrderViewVO vo,
      SaleOrderViewVO originVo, Map<String, MaterialVO> materrialmaps) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isSendColseForRevise(vo, originVo,
        materrialmaps);
  }
  
  /**
   * 是否可以行关闭
   * 
   * @param vo
   * @return boolean
   */
  public boolean isSendClose(SaleOrderViewVO vo,
      Map<String, MaterialVO> materrialmaps) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return this.getStateCalculateUtil().isSendClose(vo, materrialmaps);
  }

  /**
   * 途损不会导致发货关闭
   * 
   * @param vo
   * @return
   */
  public boolean isSendCloseFor4453(SaleOrderViewVO vo) {
    if (this.isThisState(vo) || !this.isPrevStateValid(vo)) {
      return false;
    }
    return false;
  }

  @Override
  public List<IState<SaleOrderViewVO>> next() {
    List<IState<SaleOrderViewVO>> list =
        new ArrayList<IState<SaleOrderViewVO>>();
    list.add(new RowCloseState());
    return list;
  }

  @Override
  public void setState(SaleOrderViewVO[] views) {
    AroundProcesser<SaleOrderViewVO> processer =
        new AroundProcesser<SaleOrderViewVO>(StatePlugInPoint.SendCloseState);
    this.addRule(processer);

    TimeLog.logStart();
    SaleOrderViewVO[] vos = processer.before(views);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0156")/*@res "发货关闭前执行业务规则"*/);

    TimeLog.logStart();
    super.setState(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0157")/*@res "修改表体状态为发货关闭"*/);

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006011_0", "04006011-0158")/*@res "发货关闭后执行业务规则"*/);
  }

  private void addRule(AroundProcesser<SaleOrderViewVO> processer) {
    // 更新信用调用前
    processer.addBeforeRule(new RenovateARByBidsBeginRule(
        M30EngrossAction.M30SendClose));
    boolean icEnable = SysInitGroupQuery.isICEnabled();
    if (icEnable) {
      // 可用量变更前
      processer.addBeforeRule(new SaleOrderViewATPBeforeRule());
      // 可用量变更后
      processer.addAfterRule(new SaleOrderViewATPAfterRule());
    }

    // --------------------------------------

    // 更新信用调用前
    processer.addAfterRule(new RenovateARByBidsEndRule(
        M30EngrossAction.M30SendClose));
    
    // 回写促销价格表
    if (SysInitGroupQuery.isPRICEEnabled()) {
      processer.addAfterRule(new RewriteProPirceWhenSendClose());
    }

  }

  private StateCalculateUtil getStateCalculateUtil() {
    if (this.stateCalculateUtil == null) {
      this.stateCalculateUtil = new StateCalculateUtil();
    }
    return this.stateCalculateUtil;
  }

}
