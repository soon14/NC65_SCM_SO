package nc.pubimpl.so.m38.so.m30;

import java.util.ArrayList;
import java.util.List;

import nc.bs.ml.NCLangResOnserver;
import nc.bs.so.m38.state.PreOrderStateMachine;
import nc.bs.so.m38.state.row.RowCloseState;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.pubitf.so.m38.so.m30.IPreOrderFor30;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m38.entity.PreOrderViewVO;

public class PreOrderFor30Impl implements IPreOrderFor30 {

  @Override
  public void closeRowFor38Arrange(String[] bids) throws BusinessException {
    // --1.加锁
    LockOperator lock = new LockOperator();
    lock.lock(bids, NCLangResOnserver.getInstance().getStrByID("4006012_0", "04006012-0067")/*锁定【 预订单表体 】ID失败*/);
    try {
      // --2.获取单据全VO
      ViewQuery<PreOrderViewVO> query =
          new ViewQuery<PreOrderViewVO>(PreOrderViewVO.class);
      PreOrderViewVO[] views = query.query(bids);

      // --3.过滤需要关闭的单据
      RowCloseState state = new RowCloseState();
      List<PreOrderViewVO> viewList = new ArrayList<PreOrderViewVO>();
      if ((views != null) && (views.length > 0)) {
        for (PreOrderViewVO view : views) {
          if (!state.isThisState(view)) {
            viewList.add(view);
          }
        }
      }

      // --4.处理订单关闭
      PreOrderViewVO[] newViews =
          viewList.toArray(new PreOrderViewVO[viewList.size()]);
      PreOrderStateMachine bo = new PreOrderStateMachine();
      bo.setState(state, newViews);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

}
