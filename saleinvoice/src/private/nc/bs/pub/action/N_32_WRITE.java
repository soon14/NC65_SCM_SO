package nc.bs.pub.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.impl.pubapp.env.BSContext;
import nc.itf.so.m32.ISaleInvoiceScriptMaintain;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.scmpub.res.BusinessCheck;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * 销售发票保存动作后台入口
 * 
 * @since 6.0
 * @version 2011-8-4 下午12:59:44
 * @author 么贵敬
 */
public class N_32_WRITE extends AbstractCompiler2 {
  /**
   * N_32_WRITE 的构造子
   */
  public N_32_WRITE() {
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
    super.m_tmpVo = vo;
    try {

      Object[] inCurObjects = this.getPfParameterVO().m_preValueVos;
      SaleInvoiceVO[] clientvos = (SaleInvoiceVO[]) inCurObjects;
      SaleInvoiceVO[] updatevos = this.getUpdateVO(clientvos);
      PfUserObject userObj = (PfUserObject) this.getUserObj();
      SaleInvoiceVO[] originBills =
          (SaleInvoiceVO[]) vo
              .getCustomProperty(PfParameterUtil.ORIGIN_VO_PARAMETER);
      ISaleInvoiceScriptMaintain maintainsrv =
          NCLocator.getInstance().lookup(ISaleInvoiceScriptMaintain.class);
      if (updatevos != null && updatevos.length > 0) {

        return maintainsrv.saleInvoiceUpdate(clientvos, userObj, originBills);
      }
      SaleInvoiceVO[] insertvos = this.getInsertVO(clientvos);
      if (insertvos != null && insertvos.length > 0) {
        return maintainsrv.saleInvoiceInsert(clientvos, userObj);
      }
    }
    catch (Exception e) {
      if (e instanceof BusinessException) {
        throw (BusinessException) e;
      }
      throw new PFBusinessException(e.getMessage(), e);
    }
    // finally {
    // // 此处释放session变量，以免浪费内存
    // BSContext.getInstance().removeSession(BusinessCheck.class.getName());
    // }
    return null;
  }

  private SaleInvoiceVO[] getInsertVO(SaleInvoiceVO[] orderVos) {
    if (orderVos == null || orderVos.length == 0) {
      return null;
    }
    List<SaleInvoiceVO> newVos = new ArrayList<SaleInvoiceVO>();
    for (SaleInvoiceVO vo : orderVos) {
      if (vo != null && vo.getParentVO() != null) {

        if (PubAppTool.isNull(vo.getParentVO().getPrimaryKey())) {
          newVos.add(vo);
        }
      }
    }
    return newVos.toArray(new SaleInvoiceVO[newVos.size()]);
  }

  private SaleInvoiceVO[] getUpdateVO(SaleInvoiceVO[] orderVos) {

    if (orderVos == null || orderVos.length == 0) {
      return null;
    }
    List<SaleInvoiceVO> updateVos = new ArrayList<SaleInvoiceVO>();
    for (SaleInvoiceVO vo : orderVos) {
      if (vo != null && vo.getParentVO() != null) {

        if (!PubAppTool.isNull(vo.getParentVO().getPrimaryKey())) {
          updateVos.add(vo);
        }
      }
    }
    return updateVos.toArray(new SaleInvoiceVO[updateVos.size()]);
  }

}
