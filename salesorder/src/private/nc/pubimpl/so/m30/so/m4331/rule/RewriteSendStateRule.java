package nc.pubimpl.so.m30.so.m4331.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.so.m30.state.SaleOrderStateMachine;
import nc.bs.so.m30.state.row.SendCloseState;
import nc.bs.so.m30.state.row.SendOpenState;
import nc.impl.pubapp.bill.state.IState;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.MaterialVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

/**
 * 
 * @description
 * 发货单回写销售订单执行后规则类(after):
 * @scene
 * <p>回写后处理发货状态打开/关闭
 * @param
 * 无
 * @since 6.0
 * @version  2010-01-28 下午13:49:07
 * @author 刘志伟
 */
public class RewriteSendStateRule implements IRule<SaleOrderViewVO> {

  @Override
  public void process(SaleOrderViewVO[] vos) {
    SendOpenState openState = new SendOpenState();
    SendCloseState closeState = new SendCloseState();

    Map<String, MaterialVO> materrialmaps = this.getMaterialMaps(vos);
    List<SaleOrderViewVO> closeList = new ArrayList<SaleOrderViewVO>();
    List<SaleOrderViewVO> openList = new ArrayList<SaleOrderViewVO>();
    for (SaleOrderViewVO vo : vos) {
      if (openState.isSendOpen(vo, materrialmaps)) {
        openList.add(vo);
      }
      else if (closeState.isSendClose(vo, materrialmaps)) {
        closeList.add(vo);
      }
    }
    this.setState(openList, openState);
    this.setState(closeList, closeState);
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
