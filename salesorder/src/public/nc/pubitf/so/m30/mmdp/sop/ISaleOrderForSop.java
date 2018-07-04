package nc.pubitf.so.m30.mmdp.sop;

import java.util.List;

import nc.vo.pub.BusinessException;

/**
 * 
 * @since 6.0
 * @version 2011-12-5 ÏÂÎç03:21:36
 * @author Ã´¹ó¾´
 */
public interface ISaleOrderForSop {

  /**
   * 
   * @param parammvo
   * @return
   * @throws BusinessException
   */
  List<ResultVO> queryOrderNnum(ParaMMVO parammvo) throws BusinessException;
}
