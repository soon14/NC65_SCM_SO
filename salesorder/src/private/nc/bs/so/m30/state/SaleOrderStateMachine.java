package nc.bs.so.m30.state;

import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.bill.state.TwainStateMachine;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * ¶©µ¥×´Ì¬»ú
 */
public class SaleOrderStateMachine {

  private TwainStateMachine<SaleOrderVO, SaleOrderViewVO> machine;

  public SaleOrderStateMachine() {
    this.machine =
        new TwainStateMachine<SaleOrderVO, SaleOrderViewVO>(SaleOrderVO.class,
            SaleOrderViewVO.class);
  }

  public void setState(IState<SaleOrderViewVO> state, SaleOrderViewVO[] vos) {
    this.machine.setRowState(state, vos);
  }

  public void setState(IState<SaleOrderVO> state, SaleOrderVO[] vos) {
    this.machine.setBillState(state, vos);
  }

}
