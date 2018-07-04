package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.impl.so.m38.action.ApprovePreOrderAction;
import nc.vo.pub.compiler.PfParameterVO;

public class N_38_APPROVE extends AbstractCompiler2 {

  /**
   * N_38_APPROVE 构造子注解。
   */
  public N_38_APPROVE() {
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

    ApprovePreOrderAction action = new ApprovePreOrderAction();
    return action.approve(this);
  }
}
