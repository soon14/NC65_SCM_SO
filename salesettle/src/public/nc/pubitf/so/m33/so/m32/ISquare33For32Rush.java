package nc.pubitf.so.m33.so.m32;

/**
 * 销售结算提供给对冲发票的服务
 * 
 * @since 6.0
 * @version 2011-8-29 下午04:01:40
 * @author zhangcheng
 */
public interface ISquare33For32Rush {

  /**
   * 检查销售发票是否可以生成对冲发票
   * 
   * @param invoiceIDs
   */
  void checkIFCanInvoiceRush(String[] invoiceIDs);

}
