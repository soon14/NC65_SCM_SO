package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * 发货单推式保存并审批组件
 *
 * @since 6.0
 * @version 2010-11-10 下午02:49:46
 * @author 祝会征
 */
public class N_4331_PUSHWRITE extends AbstractCompiler2 {
  /**
   * N_32_WRITE 的构造子
   */
  public N_4331_PUSHWRITE() {
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
      if (null == inCurObjects) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0022")/*@res "错误：您希望推式保存的发货单没有数据"*/);
        return null;
      }
      if (!(inCurObjects instanceof DeliveryVO[])) {

        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0023")/*@res "错误：您希望推式保存的发货单类型不匹配"*/);

      }

      int ilength = inCurObjects.length;
      DeliveryVO[] voInvoices = new DeliveryVO[ilength];
      for (int i = 0; i < ilength; i++) {
        voInvoices[i] = (DeliveryVO) inCurObjects[i];
      }
      // 调用发货单保存脚本
      DeliveryVO[] vos =
          (DeliveryVO[]) PfServiceScmUtil.processBatch("WRITE",
              SOBillType.Delivery.getCode(), voInvoices, null, null);
      return vos;
    }
    catch (Exception e) {
      throw new PFBusinessException(e.getMessage(), e);
    }
  }
}