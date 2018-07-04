package nc.bs.so.salequotation.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.BillStatusEnum;
import nc.vo.so.salequotation.entity.SalequotationHVO;

/**
 * 单据关闭BP组件
 *
 * @author chenyyb
 *
 */
public class SalequoCloseBP {

  /**
   * 关闭单据
   *
   * @param aggVO
   *          单据VO主键
   * @return 单据VO
   * @throws Exception
   */
  public AggSalequotationHVO[] close(AggSalequotationHVO[] aggVOs)
      throws Exception {
    if (aggVOs == null || aggVOs.length == 0) {
      throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006009_0","04006009-0023")/*@res "请选择单据！"*/);
    }
    BillUpdate<AggSalequotationHVO> updateAction =
        new BillUpdate<AggSalequotationHVO>();
    BillTransferTool<AggSalequotationHVO> transferTool =
        new BillTransferTool<AggSalequotationHVO>(aggVOs);
    AggSalequotationHVO[] originBills = transferTool.getOriginBills();
    AggSalequotationHVO[] fullBills = transferTool.getClientFullInfoBill();
    for (AggSalequotationHVO aggVO : fullBills) {
      SalequotationHVO hvo = aggVO.getParentVO();
      if (hvo != null) {
        // 只有审批之后才能关闭
        if (hvo.getFstatusflag().intValue() == BillStatusEnum.C_AUDIT) {
          hvo.setFstatusflag(Integer.valueOf(BillStatusEnum.C_CLOSE));
          hvo.setStatus(VOStatus.UPDATED);
        }
      }
    }
    // 更新之后返回出删除行VO之外的全VO，TS已经被更新过
    AggSalequotationHVO[] newbills =
        updateAction.update(fullBills, originBills);
    // 向前台返回只发生了改变的轻量vo
    return transferTool.getBillForToClient(newbills);
  }
}