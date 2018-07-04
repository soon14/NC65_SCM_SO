package nc.pubitf.so.m33.arap;

import java.util.List;
import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

/**
 * 销售结算给应收单提供的查询供应链各相关单据日期的服务
 * 应收单根据销售结算传给他的收付款协议，设置自己的起效日期
 * 需要根据收付款协议表体上规定的各类单据日期进行计算
 * 销售结算提供上述各类日期的查询服务，例如订单、出库单、发票的日期
 * 
 * @since 6.0
 * @version 2011-6-11 上午09:52:55
 * @author zhangcheng
 */
public interface IQueryAccountDateFromM33 {

  /**
   * 根据销售结算明细ID,和指定的日期类型，查询相关单据日期
   * 此处调用必是来源相同的一组应收单
   * 
   * @param map <销售结算明细ID,指定日期类型>
   *          String 应收单来源单据类型
   * @return <销售结算明细ID,指定日期类型的具体单据日期><p>
   *         注意返回的日期值和传入参数一一对应,如果没有取到相关的值，会报错<p>
   *         例如参数List<AccountDateType>有订单日期、发票审核日期<p>
   *         返回值就是订单日期的值，如果没有取到发票审核日期，则抛异常<p>
   */
  Map<String, UFDate[]> queryAccountDate(
      Map<String, List<AccountDateType>> map, String billType)
      throws BusinessException;

}
