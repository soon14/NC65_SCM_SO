package nc.ui.so.m32.billui.editor.bodyevent;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterDeptRefUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m32.entity.SaleInvoiceBVO;

/**
 * 
 * <p>
 * <b>本类主要完成以下功能：</b>
 * 
 * <ul>
 *  <li>销售部门编辑事件处理
 * </ul> 
 *
 * <p>
 * @version 本版本号 6.0
 * @since 
 * @author fengjb
 * @time 2010-9-10 上午10:21:08
 */
public class DeptEditHandler {

  /**
   * 方法功能描述：销售发票表体部门编辑前事件。
   * <p>
   * <b>参数说明</b>
   * 
   * @param e
   *          <p>
   * @author 冯加滨
   * @time 2010-3-12 下午04:09:10
   */
  public void beforeEdit(CardBodyBeforeEditEvent e) {

    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    // 销售组织为空，不可编辑
    String saleorg =
        keyValue.getBodyStringValue(e.getRow(), SaleInvoiceBVO.CSALEORGID);
    if (PubAppTool.isNull(saleorg)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
    // 销售组织不为空，只能参照销售组织的部门档案
    BillItem deptitem = cardPanel.getBodyItem(SaleInvoiceBVO.CDEPTVID);
    FilterDeptRefUtils filter = FilterDeptRefUtils
        .createFilterDeptRefUtilsOfSO((UIRefPane) deptitem.getComponent());
        filter.filterItemRefByOrg(saleorg);

  }
}
