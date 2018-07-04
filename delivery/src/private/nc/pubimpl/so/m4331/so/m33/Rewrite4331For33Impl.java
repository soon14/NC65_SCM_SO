package nc.pubimpl.so.m4331.so.m33;

import nc.pubimpl.so.m4331.so.m33.bp.RewriteArnumFor33;
import nc.pubimpl.so.m4331.so.m33.bp.RewriteEstArnumFor33;
import nc.pubimpl.so.m4331.so.m33.bp.RewriteRushnumFor33;
import nc.pubitf.so.m4331.so.m33.IRewrite4331For33;
import nc.pubitf.so.m4331.so.m33.RewriteArnumPara;
import nc.pubitf.so.m4331.so.m33.RewriteEstarnumPara;
import nc.pubitf.so.m4331.so.m33.RewriteRushNumPara;
import nc.vo.pub.BusinessException;

public class Rewrite4331For33Impl implements IRewrite4331For33 {

  @Override
  public void rewrite4331Arnum(RewriteArnumPara[] paras)
      throws BusinessException {
    RewriteArnumFor33 rewrite = new RewriteArnumFor33();
    rewrite.rewrite(paras);
  }

  @Override
  public void rewrite4331Estarnum(RewriteEstarnumPara[] paras)
      throws BusinessException {
    RewriteEstArnumFor33 rewrite = new RewriteEstArnumFor33();
    rewrite.rewrite(paras);
  }

  @Override
  public void rewrite4331RushNum(RewriteRushNumPara[] paras)
      throws BusinessException {
    RewriteRushnumFor33 rewrite = new RewriteRushnumFor33();
    rewrite.rewrite(paras);
  }
}
