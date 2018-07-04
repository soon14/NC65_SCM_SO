package nc.impl.so.m32.action.rule.unapprove;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.util.RemoteFormSOUtil;

/**
 * @description
 * 销售发票弃审后回写销售费用单
 * @scene
 * 销售发票弃审后
 * @param
 * 无
 * @since 6.0
 * @version 2010-12-10 下午12:59:16
 * @author 么贵敬
 */
public class ReWriteArsubUnAppRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {

    List<String> alInvoiceid = new ArrayList<String>();
    // 过滤得到费用冲抵过的发票主表ID
    for (SaleInvoiceVO voInvoice : vos) {
      UFBoolean subflag = voInvoice.getParentVO().getBsubunitflag();
      if (null != subflag && subflag.booleanValue()) {
        alInvoiceid.add(voInvoice.getParentVO().getCsaleinvoiceid());
      }
    }
    if (alInvoiceid.size() > 0) {

      String[] invoiceids = alInvoiceid.toArray(new String[0]);

      RemoteFormSOUtil.writeNoriginvoicemny(invoiceids, false);

    }

  }

}
