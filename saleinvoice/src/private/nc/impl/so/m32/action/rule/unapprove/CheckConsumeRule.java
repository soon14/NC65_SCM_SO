package nc.impl.so.m32.action.rule.unapprove;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.ml.AbstractNCLangRes;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * @description
 * 销售发票弃审前： <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售发票弃审时检查是否进行过消耗汇总操作
 * </ul>
 * <p>
 * @scene
 * 销售发票弃审前
 * @param
 * 无
 * @version 本版本号 6.0
 * @since
 * @author fengjb
 * @time 2010-7-28 下午03:19:51
 */
public class CheckConsumeRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {
    AbstractNCLangRes nclangres = NCLangRes4VoTransl.getNCLangRes();
    for (SaleInvoiceVO voInvoice : vos) {
      for (SaleInvoiceBVO bvo : voInvoice.getChildrenVO()) {
        if (!StringUtil.isEmpty(bvo.getCsumid())) {
          ExceptionUtils.wrappBusinessException(nclangres.getStrByID(
              "4006008_0", "04006008-0032")/*@res "当前发票已进行消耗汇总操作，不可进行弃审。"*/);
        }
      }
    }
  }

}
