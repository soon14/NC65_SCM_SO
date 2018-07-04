package nc.bs.so.m30.revise.rule;

import java.util.HashMap;
import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 
 * @description
 * 销售订单修订审批前
 * @scene
 * 检查销售订单是否可用修订审批
 * @param
 * 无
 *
 * @since 6.5
 * @version 2015-10-21 下午1:51:23
 * @author 刘景
 */
public class CheckApprovebleRule implements ICompareRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] bills, SaleOrderVO[] originVOs) {
    Map<String, UFDouble> nnummap = new HashMap<String, UFDouble>();
    for (SaleOrderVO bill : bills) {
      SaleOrderBVO[] bvos = bill.getChildrenVO();
      for (SaleOrderBVO bvo : bvos) {
        nnummap.put(bvo.getCsaleorderbid(), bvo.getNnum());
      }
    }
    for (SaleOrderVO orvos : originVOs) {
      SaleOrderBVO[] bvos = orvos.getChildrenVO();
      for (SaleOrderBVO bvo : bvos) {
        UFDouble num =nnummap.get(bvo.getCsaleorderbid());
        UFDouble totalsendnum =bvo.getNtotalsendnum();
        UFDouble totalinvoicenum =bvo.getNtotalinvoicenum();
        UFDouble totaloutnum =bvo.getNtotaloutnum();
        
        UFDouble maxTotalNum = UFDouble.ZERO_DBL;
        String maxTotalNumName = null;
        if (MathTool.absCompareTo(totalsendnum, maxTotalNum) > 0) {
          maxTotalNum = totalsendnum;
          maxTotalNumName =
              NCLangResOnserver.getInstance()
              .getStrByID("4006011_0", "04006011-0300")/*累计发货主数量!*/;
        }
        if (MathTool.absCompareTo(totalinvoicenum, maxTotalNum) > 0) {
          maxTotalNum = totalinvoicenum;
          maxTotalNumName =
              NCLangResOnserver.getInstance()
              .getStrByID("4006011_0", "04006011-0301")/*累计开票主数量!*/;
        }
        if (MathTool.absCompareTo(totaloutnum, maxTotalNum) > 0) {
          maxTotalNum = totaloutnum;
          maxTotalNumName =
              NCLangResOnserver.getInstance()
              .getStrByID("4006011_0", "04006011-0302")/*累计出库主数量!*/;
        }
        if (MathTool.absCompareTo(num, maxTotalNum) < 0) {
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
              .getStrByID(
              "4006011_0", "04006011-0303", null, new String[] {
                maxTotalNumName
              })/*销售订单修订后主数量不能小于{0}*/);
        }
      }
    }
  }
}
