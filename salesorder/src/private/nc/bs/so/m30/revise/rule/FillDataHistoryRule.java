package nc.bs.so.m30.revise.rule;

import nc.impl.pubapp.pattern.database.DBTool;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.IPfRetCheckInfo;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryBVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryHVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 
 * @description
 * 销售订单修订
 * @scene
 * 销售订单修订数据填充规则
 * @param
 * 无
 *
 * @since 6.36
 * @version 2015-7-13 上午10:39:27
 * @author 刘景
 */
public class FillDataHistoryRule implements ICompareRule<SaleOrderHistoryVO> {

  @Override
  public void process(SaleOrderHistoryVO[] vos, SaleOrderHistoryVO[] originVOs) {
    fillDataHistory(vos);
  }

  private void fillDataHistory(SaleOrderHistoryVO[] vos) {
    int index = 0;
    for (SaleOrderHistoryVO vo : vos) {
      SaleOrderHistoryHVO hvo = vo.getParentVO();
      // 审批流状态
      hvo.setFpfstatusflag(IPfRetCheckInfo.NOSTATE);
      // 单据状态
      hvo.setFstatusflag(BillStatus.I_FREE);
      // 清空当前审批人和审批时间 信息
      hvo.setApprover(null);
      hvo.setTaudittime(null);
      hvo.setTs(null);
      hvo.setVhistrantypecode(SOBillType.Order30R.getCode());
      hvo.setChistrantypeid(SOBillType.Order30R.getCode());
      SaleOrderHistoryBVO[] bvos = vo.getChildrenVO();
      for (SaleOrderHistoryBVO bvo : bvos) {
        // 如果是修订vo是删除态，把单据状态置成不能修改。
        if (bvo.getStatus() == VOStatus.DELETED) {
          bvo.setStatus(VOStatus.UNCHANGED);
        }
        else {
          // 其他状态不管是新增 还是修改 都应该将表体主键置空
          bvo.setStatus(VOStatus.NEW);
          bvo.setCorderhistorybid(null);
          bvo.setFrowstatus(BillStatus.I_FREE);
          // 修订新增行
          if (PubAppTool.isNull(bvo.getCsaleorderbid())
              || PubAppTool.isNull(bvo.getCsaleorderid())) {
            index++;
          }
        }
      }
    }
    if (index == 0) {
      return;
    }
    // 创建修订时新增行的销售订单id
    DBTool dao = new DBTool();
    String[] ids = dao.getOIDs(index);
    int tempindex = 0;
    for (SaleOrderHistoryVO vo : vos) {
      SaleOrderHistoryHVO hvo = vo.getParentVO();
      SaleOrderHistoryBVO[] bvos = vo.getChildrenVO();
      for (SaleOrderHistoryBVO bvo : bvos) {
        if (PubAppTool.isNull(bvo.getCsaleorderbid())
            || PubAppTool.isNull(bvo.getCsaleorderid())) {
          bvo.setCsaleorderid(hvo.getCsaleorderid());
          bvo.setCsaleorderbid(ids[tempindex]);
          tempindex++;
        }
      }
    }
  }

}
