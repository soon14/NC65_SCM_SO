package nc.pubitf.so.m4331.pub;

import nc.pubitf.so.m4331.pub.rule.CombinToDeliveryVORule;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * vo之间的合并
 * 
 * @since 6.0
 * @version 2011-1-10 下午02:18:48
 * @author 祝会征
 */
public class VOCombinUtil {
  /**
   * 根据发货单vo信息获得质检信息并把质检vo中的数据信息
   * 并合并到发货单表体上，并形成新的聚合vo
   * 
   * @param vos
   */
  public DeliveryVO[] combinTODeliveryvo(DeliveryVO[] vos) {
    CombinToDeliveryVORule combin = new CombinToDeliveryVORule();
    return combin.combin(vos);
  }
}
