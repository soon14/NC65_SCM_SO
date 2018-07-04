package nc.pubimpl.tbb;

import nc.pubimpl.tbb.detail.InvoiceTbbDetailUtil;
import nc.pubimpl.tbb.detail.OrderTbbDetailUtil;
import nc.pubitf.so.tbb.detail.ISOTbbDetail;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m32.entity.SaleInvoiceViewVO;
import nc.vo.tb.obj.NtbParamVO;

public class OrderDetailImpl implements ISOTbbDetail {
  // TODO ·ë¼Ó±õ 2012.03.14
  @Override
  public SaleOrderViewVO[] getSaleorderDetail(NtbParamVO ntbparamvos)
      throws BusinessException {
    OrderTbbDetailUtil util = new OrderTbbDetailUtil();
    return util.getExecDataBatch(ntbparamvos);
  }

  @Override
  public SaleInvoiceViewVO[] getInvoiceDetail(NtbParamVO ntbparamvos)
      throws BusinessException {
    InvoiceTbbDetailUtil util = new InvoiceTbbDetailUtil();
    return util.getExecDataBatch(ntbparamvos);
  }

}
