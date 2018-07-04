package nc.vo.so.pf;

import nc.bs.pub.pf.IPrintDataGetter;
import nc.ui.pub.print.IDataSource;
import nc.vo.pub.BusinessException;

/**
 * 销售管理单据邮件审批实现类
 * 
 * @since 6.0
 * @version 2011-12-23 下午12:53:34
 * @author fengjb
 */
public class SOMobileService implements IPrintDataGetter {

  @Override
  public IDataSource getPrintDs(String billId, String billtype, String checkman)
      throws BusinessException {
    return new SOPrintDataSourceForMailAudit(billtype, billId);
  }

}
