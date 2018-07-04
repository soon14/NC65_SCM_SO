package nc.pubimpl.so.m4331.so.m30.bp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.so.m4331.assist.state.DeliveryStateMachine;
import nc.bs.so.m4331.assist.state.row.ArSettleCloseState;
import nc.bs.so.m4331.assist.state.row.ArSettleOpenState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.pattern.data.view.EfficientViewQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;
import nc.vo.so.pub.SOTable;

/**
 * 回写发货单收入结算关闭
 * 
 * @since 6.3
 * @version 2013-1-8 下午01:17:46
 * @author yaogj
 */
public class RewriteArsettleStateBP {

  /**
   * 回写发货单的收入结算关闭状态
   * 
   * @param viewvos 订单试图vo
   */
  public void processArsettleState(SaleOrderViewVO[] viewvos) {
    Set<String> ids = new HashSet<String>();
    Map<String, UFBoolean> bidmaps = new HashMap<String, UFBoolean>();
    for (SaleOrderViewVO viewvo : viewvos) {
      SaleOrderBVO bvo = viewvo.getBody();
      ids.add(bvo.getCsaleorderid());
      bidmaps.put(bvo.getCsaleorderbid(), bvo.getBbarsettleflag());
    }
    SqlBuilder sql = new SqlBuilder();
    sql.append(" and ");
    sql.append(new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName()).buildSQL(
        SaleOrderBVO.CFIRSTID, ids.toArray(new String[ids.size()])));
    sql.append(" and ");
    sql.append(new IDExQueryBuilder(SOTable.TMP_SO_ID2.getName()).buildSQL(
        SaleOrderBVO.CFIRSTBID,
        bidmaps.keySet().toArray(new String[bidmaps.keySet().size()])));
    sql.append(" and ");
    sql.append(" so_delivery.dr ", 0);
    sql.append(" and ");
    sql.append(" so_delivery_b.dr ", 0);
    sql.append(" and ");
    sql.append("so_delivery." + SaleOrderBVO.PK_GROUP, AppContext.getInstance()
        .getPkGroup());
    sql.append(" and ");
    sql.append("so_delivery_b." + SaleOrderBVO.PK_GROUP, AppContext
        .getInstance().getPkGroup());
    EfficientViewQuery<DeliveryViewVO> viewQuery =
        new EfficientViewQuery<DeliveryViewVO>(DeliveryViewVO.class);
    DeliveryViewVO[] deliveryviewvos = viewQuery.query(sql.toString());

    ArSettleOpenState openState = new ArSettleOpenState();
    ArSettleCloseState closeState = new ArSettleCloseState();

    List<DeliveryViewVO> closeList = new ArrayList<DeliveryViewVO>();
    List<DeliveryViewVO> openList = new ArrayList<DeliveryViewVO>();
    for (DeliveryViewVO vo : deliveryviewvos) {
      String orderbid = vo.getItem().getCfirstbid();
      if (bidmaps.get(orderbid).booleanValue()) {
        if (null == vo.getItem().getBbarsettleflag()
            || !vo.getItem().getBbarsettleflag().booleanValue()) {
          closeList.add(vo);
        }

      }
      else {
        if (null != vo.getItem().getBbarsettleflag()
            && vo.getItem().getBbarsettleflag().booleanValue()) {
          openList.add(vo);
        }
      }

    }
    this.setState(openList, openState);
    this.setState(closeList, closeState);
  }

  private void setState(List<DeliveryViewVO> list, IState<DeliveryViewVO> state) {
    int size = list.size();
    if (size <= 0) {
      return;
    }
    DeliveryViewVO[] views = new DeliveryViewVO[size];
    views = list.toArray(views);
    DeliveryStateMachine bo = new DeliveryStateMachine();
    bo.setState(state, views);
  }
}
