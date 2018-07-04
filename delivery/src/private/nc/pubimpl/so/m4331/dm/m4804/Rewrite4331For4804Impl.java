package nc.pubimpl.so.m4331.dm.m4804;

import nc.pubimpl.so.m4331.dm.m4804.rule.Renovate4331For4804Rule;
import nc.pubimpl.so.m4331.dm.m4804.rule.Rewrite4331For4804Rule;
import nc.pubitf.so.m4331.dm.m4804.IRewrite4331For4804;
import nc.pubitf.so.m4331.dm.m4804.RewritePara4331For4804;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

public class Rewrite4331For4804Impl implements IRewrite4331For4804 {

  @Override
  public void rewriteTransnum(RewritePara4331For4804[] paras)
      throws BusinessException {
    Rewrite4331For4804Rule rule = new Rewrite4331For4804Rule();
    rule.rewriteTransnum(paras);
  }

  @Override
  public void renovateState(String[] bids, UFBoolean state)
      throws BusinessException {
    Renovate4331For4804Rule rule = new Renovate4331For4804Rule();
    rule.renovateState(bids, state);
  }

}
