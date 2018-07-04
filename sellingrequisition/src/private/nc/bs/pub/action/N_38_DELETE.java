package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.impl.so.m38.action.DeletePreOrderAction;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m38.entity.PreOrderVO;

public class N_38_DELETE extends AbstractCompiler2 {

  public N_38_DELETE() {
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
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;

      PreOrderVO[] inCurVOs = (PreOrderVO[]) this.getVos();
      DeletePreOrderAction action = new DeletePreOrderAction();
      return action.delete(inCurVOs);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }
}
