package nc.bs.so.m33.pub;

import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.pfflow04.MessagedriveVO;
import nc.vo.scmpub.res.billaction.SOBillAction;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.trade.checkrule.VOChecker;

public class CheckSquareBiz {

  /**
   * 判断当前流程中源单据某动作是否配置结算动作
   * 
   * @param sourceBillType
   *          源单据交易类型
   * @param sourceBusiType
   *          业务流程
   * @param sourceAction
   *          动作
   * @return
   * @throws BusinessException
   */
  public boolean ifHasSquareAction(String sourceBillType,
      String sourceBusiType, String sourceAction) {
    boolean flag = false;
    String checkBillType = sourceBillType;
    MessagedriveVO[] mvos =
        PfServiceScmUtil.queryAllMsgdrvVOs(checkBillType, sourceBusiType,
            sourceAction);

    // 交易类型查不到，用单据类型查
    if (VOChecker.isEmpty(mvos)) {
      checkBillType = SOBillType.Invoice.getCode();
      mvos =
          PfServiceScmUtil.queryAllMsgdrvVOs(checkBillType, sourceBusiType,
              sourceAction);
    }

    for (MessagedriveVO mvo : mvos) {
      if (checkBillType.equals(mvo.getPk_billtype())) {
        if (SOBillAction.SaleInvoiceADJUSTINCOME.getCode().equals(
            mvo.getActiontype())
            || SOBillAction.SaleInvoiceSQUARECOST.getCode().equals(
                mvo.getActiontype())
            || SOBillAction.SaleInvoiceSQUAREINCOME.getCode().equals(
                mvo.getActiontype())) {
          flag = true;
          break;
        }

      }
    }
    return flag;
  }

}
