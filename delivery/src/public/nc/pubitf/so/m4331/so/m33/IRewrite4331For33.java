package nc.pubitf.so.m4331.so.m33;

import nc.vo.pub.BusinessException;

public interface IRewrite4331For33 {
  /**
   * 方法功能描述：回写发货单确认应收数量。
   * 
   * @author 祝会征
   * @time 2010-9-6 下午04:49:14
   */
  void rewrite4331Arnum(RewriteArnumPara[] paras) throws BusinessException;

  /**
   * 方法功能描述:回写发货单暂估应收数量
   * 
   * @author 祝会征
   * @time 2010-9-6 下午04:48:44
   */
  void rewrite4331Estarnum(RewriteEstarnumPara[] paras)
      throws BusinessException;

  /**
   * 方法功能描述：回写发货单对冲数量。
   * 
   * @author 祝会征
   * @time 2010-9-6 下午04:48:19
   */
  void rewrite4331RushNum(RewriteRushNumPara[] paras) throws BusinessException;
}
