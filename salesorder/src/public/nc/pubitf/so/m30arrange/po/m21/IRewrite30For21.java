package nc.pubitf.so.m30arrange.po.m21;

import nc.pubitf.so.m30arrange.Rewrite30ArrangePara;
import nc.vo.pub.BusinessException;

/**
 * 采购订单回写销售订单服务接口。(补货/直运安排)
 * 
 * @author 刘志伟
 * @time 2010-05-08
 */
public interface IRewrite30For21 {

  void rewrite30ArrangeNumFor21(Rewrite30ArrangePara[] paras)
      throws BusinessException;
}
