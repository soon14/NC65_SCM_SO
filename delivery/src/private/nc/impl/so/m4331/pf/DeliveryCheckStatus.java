package nc.impl.so.m4331.pf;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pub.pf.CheckStatusCallbackContext;
import nc.bs.pub.pf.ICheckStatusCallback;
import nc.bs.scmpub.pf.PfBeforeAndAfterAction;
import nc.bs.so.m4331.maintain.rule.atp.DeliveryVOATPAfterRule;
import nc.bs.so.m4331.maintain.rule.atp.DeliveryVOATPBeforeRule;
import nc.bs.so.m4331.maintain.rule.credit.RenovateARByHidsBeginRule;
import nc.bs.so.m4331.maintain.rule.credit.RenovateARByHidsEndRule;
import nc.bs.so.m4331.plugin.Action4331PlugInPoint;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.credit.engrossmaintain.pub.action.M4331EngrossAction;
import nc.vo.pf.change.IActionDriveChecker;
import nc.vo.pf.change.IChangeVOCheck;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.pf.IPFSourceBillFinder;
import nc.vo.pub.pf.SourceBillInfo;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.to.m5x.entity.BillHeaderVO;
import nc.vo.trade.checkrule.VOChecker;

public class DeliveryCheckStatus extends PfBeforeAndAfterAction implements
IActionDriveChecker, IChangeVOCheck, IPFSourceBillFinder,
ICheckStatusCallback {

  @Override
  public void callCheckStatus(CheckStatusCallbackContext cscc)
      throws BusinessException {
    DeliveryVO bill = (DeliveryVO) cscc.getBillVo();
    DeliveryHVO head = bill.getParentVO();
    DeliveryVO[] vos = new DeliveryVO[] {
        bill
    };

    AroundProcesser<DeliveryVO> creprocesser =
        new AroundProcesser<DeliveryVO>(
            Action4331PlugInPoint.CheckStatusByCredit);
    AroundProcesser<DeliveryVO> atpprocesser =
        new AroundProcesser<DeliveryVO>(Action4331PlugInPoint.CheckStatusByATP);

    Integer statusflag = head.getFstatusflag();

    // 信用
    this.addBeforeCreditRule(creprocesser, statusflag);
    creprocesser.before(vos);

    // 审批不通过调用可用量
    if (BillStatus.NOPASS.getIntValue() == statusflag.intValue()) {
      this.addBeforeATPRule(atpprocesser);
      atpprocesser.before(vos);
    }

    // 更新表头
    String[] names = new String[] {
        DeliveryHVO.FSTATUSFLAG, DeliveryHVO.APPROVER, DeliveryHVO.TAUDITTIME
    };
    VOUpdate<DeliveryHVO> bo = new VOUpdate<DeliveryHVO>();
    bo.update(new DeliveryHVO[] {
        head
    }, names);

    // 信用
    this.addAfterCreditRule(creprocesser, statusflag);
    creprocesser.after(vos);

    // 审批不通过调用可用量
    if (BillStatus.NOPASS.getIntValue() == statusflag.intValue()) {
      this.addAfterATPRule(atpprocesser);
      atpprocesser.after(vos);
    }

  }

  @Override
  public boolean checkValidOrNeed(AggregatedValueObject srcBillVo,
      String srcAction, String destBilltype, String drivedAction)
          throws BusinessException {
    return true;
  }

  @Override
  public SourceBillInfo[] findSourceBill(String pkSrcBilltype, Object billEntity)
      throws BusinessException {
    if (billEntity == null || pkSrcBilltype == null) {
      return null;
    }
    DeliveryVO vo = (DeliveryVO) billEntity;
    if (pkSrcBilltype.startsWith(SOBillType.Order.getCode())
        || pkSrcBilltype.equals(TOBillType.TransOrder.getCode())) {
      DeliveryBVO[] items = vo.getChildrenVO();
      if (VOChecker.isEmpty(items) || null == items[0].getCsrcid()) {
        String id = vo.getParentVO().getCdeliveryid();
        items = this.getDeliveryInfo(id);
      }
      List<String> listOrderId = this.getSaleInfo(items);
      List<String> listtranId = this.getTranInfo(items);
      if (listOrderId.size() > 0 || listtranId.size() > 0) {

        SourceBillInfo[] voaRet = null;
        try {
          if (pkSrcBilltype.startsWith(SOBillType.Order.getCode())) {
            voaRet = this.getSaleorderBillInfo(listOrderId);
          }
          else if (!pkSrcBilltype.startsWith(TOBillType.TransOrder.getCode())) {
            voaRet = this.getTranorderBillInfo(listtranId);
          }
        }
        catch (Exception e) {
          ExceptionUtils.marsh(e);
        }
        return voaRet;
      }
    }
    return null;
  }

  @Override
  public boolean isEnableDrive(String srcBilltype,
      AggregatedValueObject srcBillVO, String srcAction, String destBillType,
      String beDrivedActionName) throws BusinessException {
    return true;
  }

  private void addAfterATPRule(AroundProcesser<DeliveryVO> processer) {
    IRule<DeliveryVO> rule = null;
    rule = new DeliveryVOATPAfterRule();
    processer.addAfterRule(rule);
  }

  private void addAfterCreditRule(AroundProcesser<DeliveryVO> processer,
      Integer statusflag) {
    IRule<DeliveryVO> rule = null;
    M4331EngrossAction billaction = null;
    if (BillStatus.FREE.getIntValue() == statusflag.intValue()) {
      billaction = M4331EngrossAction.M4331UnApprove;
    }
    else {
      billaction = M4331EngrossAction.M4331Approve;
    }
    // 信用占用检查
    rule = new RenovateARByHidsEndRule(billaction);
    processer.addAfterRule(rule);
  }

  private void addBeforeATPRule(AroundProcesser<DeliveryVO> processer) {
    IRule<DeliveryVO> rule = null;
    // 可用量
    rule = new DeliveryVOATPBeforeRule();
    processer.addBeforeRule(rule);
  }

  private void addBeforeCreditRule(AroundProcesser<DeliveryVO> processer,
      Integer statusflag) {
    IRule<DeliveryVO> rule = null;
    M4331EngrossAction billaction = null;
    if (BillStatus.FREE.getIntValue() == statusflag.intValue()) {
      billaction = M4331EngrossAction.M4331UnApprove;
    }
    else {
      billaction = M4331EngrossAction.M4331Approve;
    }
    // 信用占用检查
    rule = new RenovateARByHidsBeginRule(billaction);
    processer.addBeforeRule(rule);
  }

  private DeliveryBVO[] getDeliveryInfo(String hid) {
    StringBuffer sql = new StringBuffer();
    sql.append("select " + DeliveryBVO.VSRCTYPE + "," + DeliveryBVO.CSRCID
        + " from ");
    sql.append("  so_delivery_b where ");
    SqlBuilder where = new SqlBuilder();
    where.append(DeliveryBVO.CDELIVERYID, hid);
    sql.append(where.toString());
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet result = utils.query(sql.toString());
    List<DeliveryBVO> tempList = new ArrayList<DeliveryBVO>();
    while (result.next()) {
      DeliveryBVO bvo = new DeliveryBVO();
      bvo.setVsrctype(result.getString(0));
      bvo.setCsrcid(result.getString(1));
      tempList.add(bvo);
    }
    if (tempList.size() == 0) {
      return null;
    }
    DeliveryBVO[] bvos = new DeliveryBVO[tempList.size()];
    return tempList.toArray(bvos);
  }

  private List<String> getSaleInfo(DeliveryBVO[] items) {
    List<String> listtranId = new ArrayList<String>();
    for (DeliveryBVO bvo : items) {
      if (SOBillType.Order.getCode().equals(bvo.getVsrctype())) {
        listtranId.add(bvo.getCsrcid());
      }
    }
    return listtranId;
  }

  private SourceBillInfo[] getSaleorderBillInfo(List<String> listOrderId) {
    if (listOrderId.size() == 0) {
      return null;
    }
    VOQuery<SaleOrderHVO> query = new VOQuery<SaleOrderHVO>(SaleOrderHVO.class);
    SaleOrderHVO[] heads =
        query.query(listOrderId.toArray(new String[listOrderId.size()]));
    int iLen = heads.length;
    SourceBillInfo[] voaRet = new SourceBillInfo[iLen];
    for (int i = 0; i < iLen; i++) {
      voaRet[i] = new SourceBillInfo();
      voaRet[i].setBillId(heads[i].getPk_org());
      voaRet[i].setApprover(heads[i].getApprover());
      voaRet[i].setBillmaker(heads[i].getCreator());
    }
    return voaRet;
  }

  private List<String> getTranInfo(CircularlyAccessibleValueObject[] items) {
    List<String> orderList = new ArrayList<String>();
    int iLen = items.length;
    for (int i = 0; i < iLen; i++) {
      if (TOBillType.TransOrder.getCode().equalsIgnoreCase(
          (String) items[i].getAttributeValue(DeliveryBVO.VSRCTYPE))) {
        orderList.add((String) items[i].getAttributeValue(DeliveryBVO.CSRCID));
      }
    }
    return orderList;
  }

  private SourceBillInfo[] getTranorderBillInfo(List<String> listtranId) {
    if (listtranId.size() == 0) {
      return null;
    }
    VOQuery<BillHeaderVO> query = new VOQuery<BillHeaderVO>(BillHeaderVO.class);
    BillHeaderVO[] heads =
        query.query(listtranId.toArray(new String[listtranId.size()]));

    int iLen = heads.length;
    SourceBillInfo[] voaRet = new SourceBillInfo[iLen];

    for (int i = 0; i < iLen; i++) {
      voaRet[i] = new SourceBillInfo();
      voaRet[i].setBillId(heads[i].getPk_org());
      voaRet[i].setApprover(heads[i].getApprover());
      voaRet[i].setBillmaker(heads[i].getCreator());
    }
    return voaRet;
  }
}
