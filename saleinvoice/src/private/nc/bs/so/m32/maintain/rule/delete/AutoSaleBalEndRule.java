package nc.bs.so.m32.maintain.rule.delete;

import java.util.ArrayList;
import java.util.List;

import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.balend.enumeration.BalEndTrigger;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.util.RemoteFormSOUtil;

import nc.pubitf.so.m30.balend.BalEndPara;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * 发票自动结算关闭规则
 * 
 * @since 6.3
 * @version 2012-12-21 上午09:03:41
 * @author yaogj
 */
public class AutoSaleBalEndRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {
    List<String> alorderbids = new ArrayList<String>();
    for (SaleInvoiceVO voInvoice : vos) {
      for (SaleInvoiceBVO bvo : voInvoice.getChildrenVO()) {
        if (SOBillType.Order.isEqual(bvo.getVsrctype())) {
          alorderbids.add(bvo.getCfirstbid());
        }
      }
    }
    if (alorderbids.size() > 0) {
      String[] orderbids = alorderbids.toArray(new String[0]);
      BalEndPara para = new BalEndPara(orderbids, BalEndTrigger.VOICE_DELETE);

      RemoteFormSOUtil.processAutoBalEnd(para);

    }
  }

}
