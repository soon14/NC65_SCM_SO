package nc.bs.so.m4331.assist.state.row;

import java.util.ArrayList;
import java.util.List;

import nc.bs.so.m4331.assist.state.BillStateUtil;
import nc.bs.so.m4331.assist.state.StateCalculateUtil;
import nc.bs.so.m4331.assist.state.bill.BillOpenState;
import nc.bs.so.m4331.assist.state.row.rule.DeliveryViewATPAfterRule;
import nc.bs.so.m4331.assist.state.row.rule.DeliveryViewATPBeforeRule;
import nc.bs.so.m4331.maintain.rule.credit.RenovateARByBidsBeginRule;
import nc.bs.so.m4331.maintain.rule.credit.RenovateARByBidsEndRule;
import nc.bs.so.m4331.plugin.StatePlugInPoint;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.bill.state.ITransitionState;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.credit.engrossmaintain.pub.action.M4331EngrossAction;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * 发货单行打开状态
 *
 * @author 祝会征
 * @since 6.0
 * @time 2010-04-08 上午09:27:07
 */
public class RowOpenState implements
    ITransitionState<DeliveryViewVO, DeliveryVO> {

  private Class<? extends ISuperVO> clazz;

  private String stateKey;

  private Object stateValue;

  public RowOpenState() {
    this.initRowOpenState();
  }

  /**
   * 发货单行打开后处理整单打开
   */
  @Override
  public IState<DeliveryVO> getTransitTargetState() {
    return new BillOpenState();
  }

  @Override
  public boolean isAutoTransitable(DeliveryViewVO vo) {
    boolean isAuto = true;
    if (this.isThisState(vo)) {
      isAuto = false;
    }
    if (!this.isPrevStateValid(vo)) {
      isAuto = false;
    }
    else {
      StateCalculateUtil util = new StateCalculateUtil();
      isAuto = util.isAutoRowsOpenByBillState(vo);
    }
    return isAuto;
  }

  @Override
  public boolean isPrevStateValid(DeliveryViewVO vo) {
    // 只有整单打开状态以及之后的整单状态才可以做行状态的操作
    BillStateUtil statePriority = new BillStateUtil();
    return statePriority.canBeExecuteState(vo);
  }

  @Override
  public boolean isThisState(DeliveryViewVO vo) {
    Object value2 = vo.getAttributeValue(this.stateKey);
    if (value2 == null) {
      return false;
    }
    return this.stateValue.equals(value2);
  }

  @Override
  public List<IState<DeliveryViewVO>> next() {
    List<IState<DeliveryViewVO>> list = new ArrayList<IState<DeliveryViewVO>>();
    return list;
  }

  @Override
  public void setState(DeliveryViewVO[] views) {
    AroundProcesser<DeliveryViewVO> processer =
        new AroundProcesser<DeliveryViewVO>(StatePlugInPoint.RowOpenState);
    this.addRule(processer);
    TimeLog.logStart();
    DeliveryViewVO[] vos = processer.before(views);
    TimeLog.info("行打开前执行业务规则"); /*-=notranslate=-*/
    TimeLog.logStart();
    this.changeState(views);
    TimeLog.info("修改表体状态为行打开"); /*-=notranslate=-*/
    TimeLog.logStart();
    processer.after(vos);
    TimeLog.info("行打开后执行业务规则"); /*-=notranslate=-*/
  }

  private void addRule(AroundProcesser<DeliveryViewVO> processer) {
    // 可用量变更前操作
    processer.addBeforeRule(new DeliveryViewATPBeforeRule());
    // 打开前信用占用
    IRule<DeliveryViewVO> rule =
        new RenovateARByBidsBeginRule(M4331EngrossAction.M4331RowOpen);
    processer.addBeforeRule(rule);
    // 打开后信用占用
    rule = new RenovateARByBidsEndRule(M4331EngrossAction.M4331RowOpen);
    processer.addAfterRule(rule);
    // 可用量变更后操作
    processer.addAfterRule(new DeliveryViewATPAfterRule());
  }

  private void changeState(DeliveryViewVO[] views) {
    for (DeliveryViewVO view : views) {
      if (!this.isPrevStateValid(view)) {
        String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0034")/*@res "当前单据所属的状态不能设置当前状态"*/;
        ExceptionUtils.wrappBusinessException(message);
      }
      view.setAttributeValue(this.stateKey, this.stateValue);
    }
    String[] names = new String[] {
      this.stateKey
    };
    ViewUpdate<DeliveryViewVO> bo = new ViewUpdate<DeliveryViewVO>();
    bo.update(views, this.clazz, names);
  }

  private void initRowOpenState() {
    this.clazz = DeliveryBVO.class;
    this.stateKey = DeliveryBVO.BOUTENDFLAG;
    this.stateValue = UFBoolean.FALSE;
  }
}