package nc.ui.so.pub.util;

import nc.ui.bd.ref.model.CustBankaccDefaultRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.scmpub.ref.FilterCustBankaccRefUtil;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

public class FilterCustBankaccUtil extends FilterCustBankaccRefUtil{

  private BillItem billitem;

  /**
   * 
   * FilterCustBankaccUtil 的构造子
   * @param billitem
   */
  public FilterCustBankaccUtil(BillItem billitem) {
    super(billitem);
    this.billitem = billitem;
  }

  public void filterItemRefByCustAndBank(String cust, String bank, String currency) {

    if ((null == this.billitem) || StringUtil.isEmpty(cust)) {
      return;
    }

    UIRefPane refpanel = (UIRefPane) this.billitem.getComponent();
    CustBankaccDefaultRefModel bankacc =
        (CustBankaccDefaultRefModel) refpanel.getRefModel();
    bankacc.setPk_cust(cust);
    if (!StringUtil.isEmptyWithTrim(bank)) {
      SqlBuilder where = new SqlBuilder();
      where.append(" and ");
      where.append("pk_bankdoc", bank);
      bankacc.addWherePart(where.toString());
    }
    
    if(!StringUtil.isEmptyWithTrim(currency)){
      SqlBuilder where = new SqlBuilder();
      where.append(" and ");
      where.append("pk_currtype", currency);
      bankacc.addWherePart(where.toString());
    }
  }
}
