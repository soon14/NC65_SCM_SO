package nc.pubitf.so.split;

import java.util.List;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

/**
 * 销售对外分单函数接口
 * 
 * @since 6.0
 * @version 2011-7-11 下午03:03:43
 * @author 么贵敬
 */
public interface ISplitBillByFreeCust {
  /**
   * 
   * @param vo 聚合VO
   * @param keys 0: 销售订单ID
   * @return
   * @throws BusinessException
   */
  public List<String> splitByFreeCust(AggregatedValueObject vo, String[] keys)
      throws BusinessException;
}
