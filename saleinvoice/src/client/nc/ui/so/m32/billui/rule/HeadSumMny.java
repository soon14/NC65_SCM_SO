package nc.ui.so.m32.billui.rule;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.util.CardPanelValueUtils;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * 计算表头价税合计和、冲抵金额和
 * 
 * @since 6.0
 * @version 2010-12-31 上午09:09:25
 * @author 么贵敬
 */
public class HeadSumMny {

  /**
   * 计算表头价税合计和、冲抵金额和
   * 
   * @param cardPanel 卡片界面
   */
  public void process(BillCardPanel cardPanel) {
    CardPanelValueUtils util = new CardPanelValueUtils(cardPanel);
    SaleInvoiceVO billvo = util.getBillValueVO(SaleInvoiceVO.class);

    UFDouble headsummny = UFDouble.ZERO_DBL;
    UFDouble headorgsummny = UFDouble.ZERO_DBL;
    if (billvo != null && billvo.getChildrenVO() != null) {
      SaleInvoiceBVO[] bvos = billvo.getChildrenVO();
      if (bvos == null || bvos.length == 0) {
        return;
      }
      for (SaleInvoiceBVO bvo : bvos) {

        String materialid = bvo.getCmaterialid();
        if (materialid == null) {
          continue;
        }
        UFBoolean largessflag = bvo.getBlargessflag();
        if (largessflag != null && largessflag.booleanValue()) {
          continue;
        }
        UFDouble origtaxmny = bvo.getNorigtaxmny();
        UFDouble origsubmny = bvo.getNorigsubmny();
        if (origtaxmny != null) {
          headsummny = headsummny.add(origtaxmny);
        }
        if (origsubmny != null) {
          headorgsummny = headorgsummny.add(origsubmny);
        }
      }
    }

    util.setHeadTailValue(SaleInvoiceHVO.NTOTALORIGMNY, headsummny);
    util.setHeadTailValue(SaleInvoiceHVO.NTOTALORIGSUBMNY, headorgsummny);
  }

}
