package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.impl.so.m38.action.UnApprovePreOrderAction;
import nc.vo.pub.compiler.PfParameterVO;

public class N_38_UNAPPROVE extends AbstractCompiler2 {

  public N_38_UNAPPROVE() {
    super();
  }

  /*
   * 备注：平台编写原始脚本
   */
  @Override
  public String getCodeRemark() {
    return "  \n";
  }

  /*
   * 备注：平台编写规则类 接口执行类
   */
  @Override
  public Object runComClass(PfParameterVO vo) {
    super.m_tmpVo = vo;

    UnApprovePreOrderAction action = new UnApprovePreOrderAction();
    return action.unApprove(this);
  }
}
