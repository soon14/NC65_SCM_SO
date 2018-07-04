package nc.bs.so.m30.revise.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.so.m30.state.SaleOrderStateMachine;
import nc.bs.so.m30.state.row.OutCloseState;
import nc.bs.so.m30.state.row.OutOpenState;
import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.MaterialVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * 
 * @description
 * 修订出库状态变化规则
 * @scene
 * <b>主数量变大，出库要打开
 * @param
 * 无
 *
 * @since 6.0
 * @version 2011-9-8 上午10:53:20
 * @author 刘志伟
 */
public class ReviseOutStateRule implements ICompareRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos, SaleOrderVO[] originVOs) {
    // 转化view
    BillToViewConvertor<SaleOrderVO, SaleOrderViewVO> convertor =
        new BillToViewConvertor<SaleOrderVO, SaleOrderViewVO>(
            SaleOrderViewVO.class);
    SaleOrderViewVO[] views = convertor.convert(vos);
    Map<String, MaterialVO> materrialmaps = this.getMaterialMaps(views);
    SaleOrderViewVO[] originViews = convertor.convert(originVOs);
    Map<String, SaleOrderViewVO> originViewMap =
        new HashMap<String, SaleOrderViewVO>();
    for (SaleOrderViewVO originView : originViews) {
      originViewMap.put(originView.getBody().getCsaleorderbid(), originView);
    }

    OutOpenState openState = new OutOpenState();
    OutCloseState closeState = new OutCloseState();
    List<SaleOrderViewVO> closeList = new ArrayList<SaleOrderViewVO>();
    List<SaleOrderViewVO> openList = new ArrayList<SaleOrderViewVO>();
    for (SaleOrderViewVO view : views) {
      SaleOrderViewVO originView =
          originViewMap.get(view.getBody().getCsaleorderbid());
      if (originView != null
          && openState.isOutOpenForRevise(view, originView, materrialmaps)) {
        openList.add(view);
      }
      else if (originView!=null&&closeState.isOutColseForRevise(view, originView, materrialmaps)) {
        closeList.add(view);
      }
    }
    if (openList.size() > 0) {
      this.setState(openList, openState);
    }
    if (closeList.size() > 0) {
      this.setState(closeList, closeState);
    }
  }

  /**
   * 获得物料档案上的基本信息出库下限
   */
  private Map<String, MaterialVO> getMaterialMaps(SaleOrderViewVO[] vos) {
    Set<String> materialvid = new HashSet<String>();
    for (SaleOrderViewVO vo : vos) {
      materialvid.add(vo.getBody().getCmaterialvid());
    }
    Map<String, MaterialVO> materrialmaps = null;
    // 获得物料基本信息
    materrialmaps =
        MaterialPubService.queryMaterialBaseInfo(
            materialvid.toArray(new String[materialvid.size()]), new String[] {
              MaterialVO.OUTCLOSELOWERLIMIT
            });

    return materrialmaps;
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
