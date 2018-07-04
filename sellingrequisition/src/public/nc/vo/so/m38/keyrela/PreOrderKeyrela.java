package nc.vo.so.m38.keyrela;

import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.pub.keyvalue.SOKeyRela;

/**
 * 预订单字段映射关系实现
 * 
 * @since 6.3
 * @version 2013-05-21 20:10:03
 * @author liujingn
 */
public class PreOrderKeyrela extends SOKeyRela {

  @Override
  public String getCreceiveadddocidKey() {
    return PreOrderBVO.CRECEIVESITEID;
  }
}
