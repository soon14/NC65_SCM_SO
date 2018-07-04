package nc.pubitf.so.m30arrange.to.m5a;

import nc.pubitf.so.m30arrange.Rewrite30ArrangePara;
import nc.vo.pub.BusinessException;

/**
 * 调入申请回写销售订单服务接口。(补货/直运安排)
 * 
 * @author 刘志伟
 * @time 2010-05-08
 */
public interface IRewrite30For5A {

  void rewrite30ArrangeNumFor5A(Rewrite30ArrangePara[] paras)
      throws BusinessException;
}
