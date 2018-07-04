package nc.pubimpl.so.m30.so.m33.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bs.so.m30.state.SaleOrderStateMachine;
import nc.bs.so.m30.state.row.InvoiceCloseState;
import nc.bs.so.m30.state.row.InvoiceOpenState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * 
 * @description
 * 出库对冲回写销售订单时
 * @scene
 * 出库对冲影响发票关闭状态，注意要在信用调用之后调用此方法
 * @param
 * 无
 *
 * @since 6.5
 * @version 2015-10-19 下午2:09:49
 * @author zhangby5
 */
public class RewriteInvoiceStateRule implements IRule<SaleOrderViewVO> {

  @Override
  public void process(SaleOrderViewVO[] vos) {
    InvoiceOpenState openState = new InvoiceOpenState();
    InvoiceCloseState closeState = new InvoiceCloseState();
    List<SaleOrderViewVO> closeList = new ArrayList<SaleOrderViewVO>();
    List<SaleOrderViewVO> openList = new ArrayList<SaleOrderViewVO>();
    for (SaleOrderViewVO vo : vos) {
      if (openState.isInvoiceOpenForOutRush(vo)) {
        openList.add(vo);
      }
      else if (closeState.isInvoiceCloseForOutRush(vo)) {
        closeList.add(vo);
      }
    }
    this.setState(openList, openState);
    this.setState(closeList, closeState);
  }

  private void setState(List<SaleOrderViewVO> list,
      IState<SaleOrderViewVO> state) {
    int size = list.size();
    if (size <= 0) {
      return;
    }
    SaleOrderViewVO[] views = new SaleOrderViewVO[size];
    views = list.toArray(views);
    SaleOrderStateMachine bo = new SaleOrderStateMachine();
    bo.setState(state, views);
  }

}
