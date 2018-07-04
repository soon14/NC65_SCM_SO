package nc.pubitf.so.m32.so.report;

import nc.vo.pub.BusinessException;
import nc.vo.so.m32.paravo.InvoiceFormReportParaVO;
import nc.vo.so.m32.paravo.InvoiceReturnToReportVO;

public interface ISaleInvoiceForDaily {
  /**
   * 给综合日报的查询数据的接口方法
   * 
   * @param paravo 参数VO
   * @return 返回对象
   * @throws BusinessException
   */
  InvoiceReturnToReportVO[] getDailyDataFromInvoice(
      InvoiceFormReportParaVO paravo) throws BusinessException;
}
