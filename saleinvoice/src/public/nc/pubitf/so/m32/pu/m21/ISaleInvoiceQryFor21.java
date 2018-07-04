/**
 * $文件说明$
 *
 * @author 么贵敬
 * @version 6.0
 * @see
 * @since
 * @time 2010-11-4 下午07:10:09
 */
package nc.pubitf.so.m32.pu.m21;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * 销售票发票提供给采购订单的查询功能接口
 * 
 * @since 6.0
 * @version 2010-12-15 上午09:05:20
 * @author 么贵敬
 */
public interface ISaleInvoiceQryFor21 {

  /**
   * 查询物料的总数量
   * 
   * @param cmaterialid 物料ID
   * @param queryDate 查询开始日期
   * @param queryDay 时间短长度
   * @param pk_group 集团
   * @param saleorg 销售组织
   * @return 物料ID为KEY，数量为Value的Map
   * @throws BusinessException
   */
  Map<String, UFDouble> getInvInvoiceNumber(String[] cmaterialid,
      UFDate queryDate, Integer queryDay, String pk_group, String saleorg)
      throws BusinessException;
}
