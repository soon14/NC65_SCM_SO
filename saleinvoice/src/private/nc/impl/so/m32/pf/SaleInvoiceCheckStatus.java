package nc.impl.so.m32.pf;

import nc.bs.pub.pf.CheckStatusCallbackContext;
import nc.bs.pub.pf.ICheckStatusCallback;
import nc.bs.scmpub.pf.PfBeforeAndAfterAction;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.vo.pf.change.IActionDriveChecker;
import nc.vo.pf.change.IChangeVOCheck;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.pf.IPFSourceBillFinder;
import nc.vo.pub.pf.SourceBillInfo;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售发票实现的流程平台审批流回写单据状态的接口
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since 上一版本号 5.6
 * @author 冯加滨
 * @time 2010-4-20 上午09:27:23
 */
public class SaleInvoiceCheckStatus extends PfBeforeAndAfterAction implements
    IActionDriveChecker, IChangeVOCheck, IPFSourceBillFinder,
    ICheckStatusCallback {

  @Override
  public void callCheckStatus(CheckStatusCallbackContext cscc)
      throws BusinessException {
    SaleInvoiceVO bill = (SaleInvoiceVO) cscc.getBillVo();
    SaleInvoiceHVO head = bill.getParentVO();
    // 更新表头
    String[] names =
        new String[] {
          SaleInvoiceHVO.FSTATUSFLAG, SaleInvoiceHVO.APPROVER,
          SaleInvoiceHVO.TAUDITTIME
        };
    VOUpdate<SaleInvoiceHVO> bo = new VOUpdate<SaleInvoiceHVO>();
    bo.update(new SaleInvoiceHVO[] {
      head
    }, names);
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
