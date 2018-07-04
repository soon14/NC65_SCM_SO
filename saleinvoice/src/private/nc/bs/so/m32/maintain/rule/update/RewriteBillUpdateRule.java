package nc.bs.so.m32.maintain.rule.update;

import java.util.List;
import java.util.Map;

import nc.bs.so.m32.maintain.rule.util.RewriteBillUtil;
import nc.cmbd.vo.scmpub.res.billtype.SOBillType;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 * 销售发票更新的时候的回写来源和源头规则
 * @scene
 * 销售发票修改保存后
 * @param
 * 无
 * @since 6.3
 * @version 2012-12-21 上午09:06:00
 * @author yaogj
 */
public class RewriteBillUpdateRule implements ICompareRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos, SaleInvoiceVO[] originVOs) {
    // 回写来源单据
    RewriteBillUtil rewriteUtil = new RewriteBillUtil();
    // 缓存组织信息
    rewriteUtil.catcheOrg(vos);

    BillRewriter srctool = rewriteUtil.getSrcBillRewriter();
    Map<String, List<RewritePara>> srcParaIndex =
        srctool.splitForUpdate(vos, originVOs);
    List<RewritePara> srcSaleOut =
        srcParaIndex.get(ICBillType.SaleOut.getCode());
    List<RewritePara> srcSaleOrder =
            srcParaIndex.get("30");
        
    if (!VOChecker.isEmpty(srcSaleOut)) {
      rewriteUtil.reWriteSrc4C(srcSaleOut);
    }
    if (!VOChecker.isEmpty(srcSaleOrder)) {
        rewriteUtil.reWriteSrc30(srcSaleOrder);
      }

  }

}
