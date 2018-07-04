package nc.pubimpl.so.m32.ic.m4453;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m32.entity.SaleInvoiceBVO;

import nc.pubitf.so.m32.ic.m4453.ISaleInvoiceFor4453;

import nc.impl.pubapp.pattern.data.vo.VOQuery;

/**
 * 销售发票提供给销售发票待结算单的接口
 * 
 * @since 6.3
 * @version 2012-12-21 上午10:36:15
 * @author yaogj
 */
public class SaleInvoiceFor4453Impl implements ISaleInvoiceFor4453 {

  @Override
  public void isHasDest(String[] ids, String[] bids) throws BusinessException {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" and ");
    sql.append(SaleInvoiceBVO.CSRCID, ids);
    sql.append(" and ");
    sql.append(SaleInvoiceBVO.CSRCBID, bids);

    VOQuery<SaleInvoiceBVO> querysrv =
        new VOQuery<SaleInvoiceBVO>(SaleInvoiceBVO.class, new String[] {
          SaleInvoiceBVO.PK_ORG
        });
    SaleInvoiceBVO[] bvo = querysrv.query(sql.toString(), null);
    if (null != bvo && bvo.length > 0) {
      // ExceptionUtils.wrappBusinessException("您选择的签收途损单已经影响发票 不允许取消签字");
      ExceptionUtils
          .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID(
              "4006008_0", "04006008-0126")/*@res "您选择的签收途损单已经影响发票 不允许取消签字"*/);
    }
  }

}
