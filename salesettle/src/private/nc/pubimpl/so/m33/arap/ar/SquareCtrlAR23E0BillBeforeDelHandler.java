package nc.pubimpl.so.m33.arap.ar;

import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.itf.arap.forso.IDataFromF0ForM33;
import nc.pubitf.arap.pub.GetSODataByArapUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;

public class SquareCtrlAR23E0BillBeforeDelHandler implements IBusinessListener {

  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
    // 1.解析收款单数据
    IDataFromF0ForM33[] datas =
        new GetSODataByArapUtils().getIDataFromF0ForM33(event);
    boolean firstBillType30 = false;
    boolean source324C4453 = false;
    for (IDataFromF0ForM33 data : datas) {
      String firstBillType = data.getFirstBillType();
      if (PubAppTool.isEqual(SOBillType.Order.getCode(), firstBillType)) {
        firstBillType30 = true;
      }
      String sourceBillType = data.getSourceBillType();
      if (PubAppTool.isEqual(SOBillType.Invoice.getCode(), sourceBillType)
          || PubAppTool.isEqual(ICBillType.SaleOut.getCode(), sourceBillType)
          || PubAppTool.isEqual(ICBillType.WastageBill.getCode(),
              sourceBillType)) {
        source324C4453 = true;
      }
      if (data.isFromSO() && firstBillType30 && source324C4453) {
        throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006010_0","04006010-0055")/*@res "来源于销售结算的未确认应收单不能手工删除！"*/);
      }
    }

  }

}