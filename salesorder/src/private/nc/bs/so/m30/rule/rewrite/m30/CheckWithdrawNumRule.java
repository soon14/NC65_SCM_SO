package nc.bs.so.m30.rule.rewrite.m30;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m30trantype.enumeration.DirectType;

/**
 * 
 * @description
 * 销售订单退货时回写销售订单数量前检查数量规则
 * @scene
 * 销售订单退货时回写销售订单数量前
 * @param
 * 无
 *
 * @since 6.5
 * @version 2015-10-19 下午3:01:21
 * @author zhangby5
 */
public class CheckWithdrawNumRule implements IRule<SaleOrderViewVO> {

  @Override
  public void process(SaleOrderViewVO[] vos) {

    for (SaleOrderViewVO vo : vos) {
      this.checkData(vo);
    }
  }

  /**
   * 根据交易类型检查是否超出库退货
   */
  private void checkData(SaleOrderViewVO vo) {
    SaleOrderHVO head = vo.getHead();
    SaleOrderBVO body = vo.getBody();

    UFDouble nnum = body.getNnum();
    UFDouble ntotalreturnnum = body.getNtotalreturnnum();

    // TODO 检查只能针对正数行订单退货

    if (MathTool.compareTo(nnum, ntotalreturnnum) < 0) {
      String message = NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0339")/*不允许超订单退货*/;
      String location =
          NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0340", null, new String[]{head.getVbillcode(),body.getCrowno()})/*销售订单{0}第{1}行*/;

      ExceptionUtils.wrappBusinessException(message, location);
    }

    String pk_group = head.getPk_group();
    String trantypecode = head.getVtrantypecode();
    M30TranTypeVO trantype = null;
    IM30TranTypeService service =
        NCLocator.getInstance().lookup(IM30TranTypeService.class);
    try {
      trantype = service.queryTranType(pk_group, trantypecode);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    if (trantype == null) {

      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0102")/*@res "查询订单类型出错。"*/);
      return;
    }
    Integer fdirecttype = trantype.getFdirecttype();
    if (!(fdirecttype != null && fdirecttype.equals(DirectType.DIRECTTRAN_PO
        .value()))) {
      UFDouble ntotaloutnum = body.getNtotaloutnum();

      if (MathTool.compareTo(ntotaloutnum, ntotalreturnnum) < 0) {
        String message = NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0341")/*不允许超累计出库数量退货*/;
        String location =
            NCLangResOnserver.getInstance().getStrByID("4006011_0", "04006011-0340", null, new String[]{head.getVbillcode(),body.getCrowno()})/*销售订单{0}第{1}行*/;

        ExceptionUtils.wrappBusinessException(message, location);
      }
    }

  }

}