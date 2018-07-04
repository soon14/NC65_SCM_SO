package nc.pubitf.so.m33.ic.m4c;

import nc.vo.pub.BusinessException;
import nc.vo.so.m33.m4c.linkqryoutrush.entity.LinkQueryOutRushVO;
import nc.vo.so.m33.m4c.linkqryoutrush.entity.OutRushExeInfoVO;

/**
 * 为销售出库单提供查询出库对冲信息的服务接口
 * 
 * @since 6.0
 * @version 2011-9-7 下午01:19:48
 * @author zhangcheng
 */
public interface ISquareOutRushLinkQuery {

  OutRushExeInfoVO[] outRushLinkQuery(LinkQueryOutRushVO[] paravo)
      throws BusinessException;

}
