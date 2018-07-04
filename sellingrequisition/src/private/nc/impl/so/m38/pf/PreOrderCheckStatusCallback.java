package nc.impl.so.m38.pf;

import nc.bs.pub.pf.CheckStatusCallbackContext;
import nc.bs.pub.pf.ICheckStatusCallback;
import nc.bs.scmpub.pf.PfBeforeAndAfterAction;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.vo.pub.BusinessException;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.entity.PreOrderVO;

/**
 * 审批流改变单据状态实现类
 * 
 * @author 刘志伟
 */
public class PreOrderCheckStatusCallback extends PfBeforeAndAfterAction
    implements ICheckStatusCallback {

  @Override
  public void callCheckStatus(CheckStatusCallbackContext cscc)
      throws BusinessException {
    PreOrderVO bill = (PreOrderVO) cscc.getBillVo();
    PreOrderHVO head = bill.getParentVO();
    // 更新表头
    String[] names = new String[] {
      PreOrderHVO.FSTATUSFLAG, PreOrderHVO.APPROVER, PreOrderHVO.TAUDITTIME
    };
    VOUpdate<PreOrderHVO> bo = new VOUpdate<PreOrderHVO>();
    bo.update(new PreOrderHVO[] {
      head
    }, names);

  }

}
