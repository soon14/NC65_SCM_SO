package nc.bs.so.m4331.assist.state;

import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.bill.state.TwainStateMachine;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

public class DeliveryStateMachine {

  private TwainStateMachine<DeliveryVO, DeliveryViewVO> machine;

  public DeliveryStateMachine() {
    this.machine =
        new TwainStateMachine<DeliveryVO, DeliveryViewVO>(DeliveryVO.class,
            DeliveryViewVO.class);
  }

  public void setState(IState<DeliveryViewVO> state, DeliveryViewVO[] vos) {
    this.machine.setRowState(state, vos);
  }

  public void setState(IState<DeliveryVO> state, DeliveryVO[] vos) {
    this.machine.setBillState(state, vos);
  }
}
