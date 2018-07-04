package nc.bs.so.m32.maintain.rule.delete;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.util.RemoteFormSOUtil;
import nc.vo.so.m32.util.SaleInvoiceVOUtil;
import nc.vo.so.m35.entity.ArsubInterfaceVO;

/**
 * @description
 * 销售发票删除操作后删除回写销售费用单
 * @scene
 * 销售发票删除保存后
 * @param
 * 无
 * @since 6.0
 * @version 2010-12-10 下午01:01:28
 * @author 么贵敬
 */
public class RewriteARSubDeleteRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {
    SaleInvoiceVOUtil voutil = new SaleInvoiceVOUtil();
    ArsubInterfaceVO[] arsubvo = voutil.changeToArsubInterfaceVO(vos);
    // 删除合并开票关系回写费用单

    OffsetTempVO tempvo = new OffsetTempVO();
    tempvo.setIsCancelOffset(true);

    try {
      RemoteFormSOUtil.writeArsubRelation(arsubvo, tempvo);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }

  }

}
