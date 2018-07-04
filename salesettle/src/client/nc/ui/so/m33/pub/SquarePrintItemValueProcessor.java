package nc.ui.so.m33.pub;

import nc.ui.format.NCFormater;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.pub.print.IPrintItemValueProcessor;

import org.apache.commons.lang.StringUtils;


public class SquarePrintItemValueProcessor implements IPrintItemValueProcessor{
  
  @Override
  public String itemValueProcess(String oldValue, int pos, int row,
      String itemkey, BillCardPanel billcardpanel) {
    
    BillItem item = billcardpanel.getBillData().getBodyItem(itemkey);
    try {
      switch (item.getDataType()) {
        case IBillItem.INTEGER:
          oldValue = NCFormater.formatNumber(oldValue).getValue();
          break;
        case IBillItem.DECIMAL:
          oldValue = NCFormater.formatNumber(oldValue).getValue();
          break;
        case IBillItem.MONEY:
          oldValue = NCFormater.formatNumber(oldValue).getValue();
          break;
      }
    }catch (Exception e) {
      oldValue = StringUtils.EMPTY;
    }
    return oldValue;
  }
}
