package nc.pubitf.so.m30arrange.po.m20;

import nc.pubitf.so.m30arrange.Rewrite30ArrangePara;
import nc.vo.pub.BusinessException;

public interface IRewrite30For20 {

  void rewrite30ArrangeNumFor20(Rewrite30ArrangePara[] paras)
      throws BusinessException;
}
