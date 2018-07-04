package nc.pubimpl.so.m30.pf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nc.bs.pub.pf.CheckStatusCallbackContext;
import nc.bs.pub.pf.ICheckStatusCallback;
import nc.bs.scmpub.pf.PfBeforeAndAfterAction;
import nc.bs.scmpub.pf.PfResumeExceptionUtils;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.vo.pf.change.IActionDriveChecker;
import nc.vo.pf.change.IChangeVOCheck;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.pf.IPFSourceBillFinder;
import nc.vo.pub.pf.SourceBillInfo;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.rule.SOPfStatusChgRule;

/**
 * 销售订单修订实现的流程平台审批流接口，持久化审批相关字段信息
 * 
 * @since 6.3
 * @version 2014-12-26 下午4:59:22
 * @author wangshu6
 */
public class M30ReviseCheckStatusCallback extends PfBeforeAndAfterAction
    implements IActionDriveChecker, IChangeVOCheck, IPFSourceBillFinder,
    ICheckStatusCallback {

  // wangshu6 beforBatch afterBatch 重写这两个方法 避免平台进行对比校验，不进行差异对比
  @Override
  public AggregatedValueObject[] beforeBatch(Object[] billVOs,
      Object[] userObjAry, HashMap hmPfExParams) throws BusinessException {
	PfResumeExceptionUtils.processBusinessCheck(hmPfExParams); 
    return (AggregatedValueObject[]) billVOs;
  }

  @Override
  public Object[] afterBatch(AggregatedValueObject[] reloadVOs,
      Object[] objsAfterAction, HashMap hmPfExParams) throws BusinessException {
    return objsAfterAction;
  }

  @Override
  public void callCheckStatus(CheckStatusCallbackContext cscc)
      throws BusinessException {

    SaleOrderVO billvo = (SaleOrderVO) cscc.getBillVo();
    SaleOrderHVO headvo = billvo.getParentVO();

    String[] headnames =
        new String[] {
          SaleOrderHVO.FPFSTATUSFLAG, SaleOrderHVO.APPROVER,
          SaleOrderHVO.TAUDITTIME
        };
    // 更新表头
    VOUpdate<SaleOrderHVO> updatesrv = new VOUpdate<SaleOrderHVO>();
    updatesrv.update(new SaleOrderHVO[] {
      headvo
    }, headnames);

    if (cscc.isTerminate()) {
      this.updateNewBillStatus(new SaleOrderVO[] {
        billvo
      });
    }
  }

  private void updateNewBillStatus(SaleOrderVO[] newbills) {

    SOPfStatusChgRule statuschgrule = new SOPfStatusChgRule();
    SaleOrderHVO[] updateheads = new SaleOrderHVO[newbills.length];
    List<SaleOrderBVO> listbody = new ArrayList<SaleOrderBVO>();
    int i = 0;
    for (SaleOrderVO ordervo : newbills) {
      statuschgrule.changePfToBillStatus(ordervo);
      updateheads[i] = ordervo.getParentVO();
      for (SaleOrderBVO bvo : ordervo.getChildrenVO()) {
        listbody.add(bvo);
      }
    }
    String[] headupname = new String[] {
      SaleOrderHVO.FSTATUSFLAG
    };
    VOUpdate<SaleOrderHVO> headupsrv = new VOUpdate<SaleOrderHVO>();
    headupsrv.update(updateheads, headupname);

    String[] bodyupname = new String[] {
      SaleOrderBVO.FROWSTATUS
    };
    VOUpdate<SaleOrderBVO> bodyupsrv = new VOUpdate<SaleOrderBVO>();
    SaleOrderBVO[] updatebodys =
        listbody.toArray(new SaleOrderBVO[listbody.size()]);
    bodyupsrv.update(updatebodys, bodyupname);

  }

  @Override
  public SourceBillInfo[] findSourceBill(String pk_srcBilltype,
      Object billEntity) throws BusinessException {
    return null;
  }

  @Override
  public boolean checkValidOrNeed(AggregatedValueObject srcBillVo,
      String srcAction, String destBilltype, String drivedAction)
      throws BusinessException {
    return true;
  }

  @Override
  public boolean isEnableDrive(String srcBilltype,
      AggregatedValueObject srcBillVO, String srcAction, String destBillType,
      String beDrivedActionName) throws BusinessException {
    return true;
  }
}
