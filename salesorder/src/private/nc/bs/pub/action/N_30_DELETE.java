package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.itf.so.m30.self.ISaleOrderScriptMaintain;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.so.m30.entity.SaleOrderVO;


/**
 * 
 * 删除动作脚本
 * 
 * @author 平台脚本生成
 * @since 6.0
 */
public class N_30_DELETE extends AbstractCompiler2 {
  public N_30_DELETE() {
    super();
  }

  /*
   * 备注：平台编写原始脚本
   */
  @Override
  public String getCodeRemark() {
    return "\n";
  }

  /*
   * 备注：平台编写规则类 接口执行类
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;

      SaleOrderVO[] inCurVOs = (SaleOrderVO[]) this.getVos();
      PfUserObject userObj = (PfUserObject) this.getUserObj();
      ISaleOrderScriptMaintain maintainsrv =
          NCLocator.getInstance().lookup(ISaleOrderScriptMaintain.class);
      return maintainsrv.saleOrderDelete(inCurVOs, userObj);
    }
    catch (Exception ex) {

      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  }
