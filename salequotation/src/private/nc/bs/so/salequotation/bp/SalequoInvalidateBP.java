package nc.bs.so.salequotation.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.BillStatusEnum;
import nc.vo.so.salequotation.entity.SalequotationHVO;

/**
 * 使单据失效BP组件
 *
 * @author chenyyb
 *
 */
public class SalequoInvalidateBP {

  public AggSalequotationHVO[] invalidate(AggSalequotationHVO[] aggVOs)
      throws BusinessException {
    if (aggVOs == null) {
      throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006009_0","04006009-0023")/*@res "请选择单据！"*/);
    }
    BillUpdate<AggSalequotationHVO> updateAction =
        new BillUpdate<AggSalequotationHVO>();
    BillTransferTool<AggSalequotationHVO> transferTool =
        new BillTransferTool<AggSalequotationHVO>(aggVOs);
    AggSalequotationHVO[] originBills = transferTool.getOriginBills();
    AggSalequotationHVO[] fullBills = transferTool.getClientFullInfoBill();

    for (int i = 0; i < fullBills.length; i++) {
      SalequotationHVO hvo = fullBills[i].getParentVO();
      if (hvo != null) {
        // 只有自由状态才可以变成失效状态
        if (hvo.getFstatusflag() != null
            && hvo.getFstatusflag().intValue() == BillStatusEnum.C_FREE) {
          hvo.setFstatusflag(Integer.valueOf(BillStatusEnum.C_INVALIDATE));
          hvo.setStatus(VOStatus.UPDATED);
        }
      }
    }
    AggSalequotationHVO[] newbills =
        updateAction.update(fullBills, originBills);

    // 向前台返回只发生了改变的轻量vo
    return transferTool.getBillForToClient(newbills);
  }
}