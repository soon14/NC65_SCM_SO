package nc.pubimpl.so.m30.pu.m21;

import java.util.Map;

import nc.pubimpl.so.m30.pu.m21.rule.Renovate30For21Rule;
import nc.pubimpl.so.m30.pu.m21.rule.Rewrite30For21Rule;
import nc.pubitf.so.m30.pu.m21.IRewrite30For21;
import nc.vo.pub.BusinessException;

public class Rewrite30For21Impl implements IRewrite30For21 {

  @Override
  public void rewriteBillCode(Map<String, String> codeMap)
      throws BusinessException {
    Rewrite30For21Rule rule = new Rewrite30For21Rule();
    rule.rewrite30For21(codeMap);
  }

  @Override
  public void renovate30For21Delete(String[] ids) throws BusinessException {
    Renovate30For21Rule rule = new Renovate30For21Rule();
    rule.renovate30For21(ids);
  }
}
