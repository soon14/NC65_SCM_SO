package nc.pubitf.so.m30;

import java.util.Map;

import nc.vo.pub.BusinessException;

/**
 * 订单退货政策检查接口
 * 
 * @since 6.0
 * @version 2011-4-14 下午01:36:48
 * @author 祝会征
 */
public interface IReturnAssignMatch {
  /**
   * so的退货函数检查
   * @param matchparas
   * @return
   * @throws BusinessException
   */
  Map<String, String> matchReturnAssign(ReturnAssignMatchVO[] matchvos)
      throws BusinessException;
  /**
   * 匹配退货政策
   * 
   * @param matchvos
   * @return
   * @throws BusinessException
   */
  Map<String,String> matchReturnPolicy(ReturnAssignMatchVO[] matchvos)
      throws BusinessException;
}
