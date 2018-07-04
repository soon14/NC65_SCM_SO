package nc.pubitf.so.m33.self.m4332;

import nc.vo.pub.BusinessException;

public interface IRewrite434CFor4332 {

  /**
   * 方法功能描述：销售发票应收结算回写销售出库待结算单累计应收结算数量、金额
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param paras
   * @throws BusinessException
   *           <p>
   * @author zhangcheng
   * @time 2010-7-16 上午11:28:13
   */
  void rewrite434CDownARNumMnyFor4332(Rewrite434CPara[] paras)
      throws BusinessException;

  /**
   * 方法功能描述：销售发票应收结算回写销售出库待结算单累计成本结算数量
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param paras
   * @throws BusinessException
   *           <p>
   * @author zhangcheng
   * @time 2010-7-16 上午11:28:13
   */
  void rewrite434CDownIANumFor4332(Rewrite434CPara[] paras)
      throws BusinessException;

}
