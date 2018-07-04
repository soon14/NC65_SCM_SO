package nc.impl.so.m38.action;

import nc.bs.so.m38.maintain.InvalidatePreorderBP;
import nc.vo.so.m38.entity.PreOrderVO;

/**
 * 预定单失效调用类
 * 
 * @since 6.0
 * @version 2011-5-7 下午02:04:55
 * @author 祝会征
 */
public class InvalidatePreorderAction {
  public PreOrderVO[] invalidatePreorder(PreOrderVO[] vos) {
    InvalidatePreorderBP bp = new InvalidatePreorderBP();
    return bp.invalidatePreorder(vos);
  }
}
