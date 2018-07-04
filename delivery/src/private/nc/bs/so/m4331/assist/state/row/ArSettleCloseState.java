package nc.bs.so.m4331.assist.state.row;

import java.util.ArrayList;
import java.util.List;

import nc.bs.so.m4331.maintain.rule.credit.RenovateARByBidsBeginRule;
import nc.bs.so.m4331.maintain.rule.credit.RenovateARByBidsEndRule;
import nc.bs.so.m4331.plugin.StatePlugInPoint;
import nc.impl.pubapp.bill.state.AbstractRowState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.credit.engrossmaintain.pub.action.M4331EngrossAction;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * 收入结算关闭
 * 
 * @since 6.3
 * @version 2013-1-8 上午09:02:11
 * @author yaogj
 */
public class ArSettleCloseState extends AbstractRowState<DeliveryViewVO> {

  /**
   * 收入结算关闭
   */
  public ArSettleCloseState() {
    super(DeliveryBVO.class, DeliveryBVO.BBARSETTLEFLAG, UFBoolean.TRUE);
  }

  @Override
  public boolean isAutoTransitable(DeliveryViewVO vo) {
    boolean isAuto = true;
    if (this.isThisState(vo)) {
      isAuto = false;
    }
    else if (!this.isPrevStateValid(vo)) {
      isAuto = false;
    }

    return isAuto;
  }

  @Override
  public boolean isPrevStateValid(DeliveryViewVO vo) {
    return true;
  }

  @Override
  public List<IState<DeliveryViewVO>> next() {
    List<IState<DeliveryViewVO>> list = new ArrayList<IState<DeliveryViewVO>>();
    return list;
  }

  @Override
  public void setState(DeliveryViewVO[] views) {
    AroundProcesser<DeliveryViewVO> processer =
        new AroundProcesser<DeliveryViewVO>(StatePlugInPoint.ArSettleCloseState);
    this.addRule(processer);
    TimeLog.logStart();
    DeliveryViewVO[] vos = processer.before(views);

    TimeLog.info("行关闭前执行业务规则");/*-=notranslate=-*/

    TimeLog.logStart();
    super.setState(vos);

    TimeLog.info("修改表体状态为行关闭");/*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info("行关闭后执行业务规则");/*-=notranslate=-*/
  }

  private void addRule(AroundProcesser<DeliveryViewVO> processer) {

    // 关闭前信用占用
    IRule<DeliveryViewVO> rule =
        new RenovateARByBidsBeginRule(M4331EngrossAction.M4331RowClose);
    processer.addBeforeRule(rule);
    // 关闭后信用占用
    rule = new RenovateARByBidsEndRule(M4331EngrossAction.M4331RowClose);
    processer.addAfterRule(rule);

  }

}
