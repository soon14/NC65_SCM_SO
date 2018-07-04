package nc.bs.so.m38.state;

import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.bill.state.TwainStateMachine;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.m38.entity.PreOrderViewVO;

public class PreOrderStateMachine {

  private TwainStateMachine<PreOrderVO, PreOrderViewVO> machine;

  public PreOrderStateMachine() {
    this.machine =
        new TwainStateMachine<PreOrderVO, PreOrderViewVO>(PreOrderVO.class,
            PreOrderViewVO.class);
  }

  public void setState(IState<PreOrderViewVO> state, PreOrderViewVO[] vos) {
    this.machine.setRowState(state, vos);
  }

  public void setState(IState<PreOrderVO> state, PreOrderVO[] vos) {
    this.machine.setBillState(state, vos);
  }
}
