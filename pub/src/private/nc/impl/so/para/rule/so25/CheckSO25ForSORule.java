package nc.impl.so.para.rule.so25;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.so.custmatrel.entity.CustMatRelBVO;
import nc.vo.so.mbuylargess.entity.BuyLargessHVO;
import nc.vo.so.mreturnassign.entity.ReturnAssignVO;

public class CheckSO25ForSORule {
  private String pk_group;

  private boolean so25;

  public boolean isEnable(String pkgroup, String para) {
    this.pk_group = pkgroup;
    this.isBase(para);
    boolean isEnable = this.checkBuyLargess();
    if (!isEnable) {
      return false;
    }
    isEnable = this.checkCustAndMat();
    if (!isEnable) {
      return false;
    }
    isEnable = this.checkReturnPolice();
    if (!isEnable) {
      return false;
    }
    return true;
  }

  /*
   * 检查买赠 
   * @param pk_group
   * @param so24
   * @return
   */
  private boolean checkBuyLargess() {
    String pk;
    String table = "so_buylargess";
    if (!this.so25) {
      pk = BuyLargessHVO.PK_CUSTSALECLASS;
    }
    else {
      pk = BuyLargessHVO.PK_CUSTCLASS;
    }
    return this.getEnable(table, pk);
  }

  /*
   * 检查客户和物料 
   * @param pk_group
   * @param so24
   * @return
   */
  private boolean checkCustAndMat() {
    String pk;
    String table = "so_custmatrel_b";
    if (!this.so25) {
      pk = CustMatRelBVO.PK_CUSTSALECLASS;
    }
    else {
      pk = CustMatRelBVO.PK_CUSTBASECLASS;
    }
    return this.getEnable(table, pk);
  }

  /*
   * 检查退货政策 
   * @param pk_group
   * @param so24
   * @return
   */
  private boolean checkReturnPolice() {
    String pk;
    String table = "so_returnassign";
    if (!this.so25) {
      pk = ReturnAssignVO.PK_CUSTSALECLASS;
    }
    else {
      pk = ReturnAssignVO.PK_CUSTCLASS;
    }
    return this.getEnable(table, pk);
  }

  private boolean getEnable(String table, String pk) {
    StringBuffer sql = new StringBuffer();
    sql.append("select " + pk + " from " + table + " where");
    sql.append(" pk_group ='" + this.pk_group + "'");
    sql.append(" and dr =0");
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql.toString());
    if (rowset.size() > 0) {
      return false;
    }
    return true;
  }

  private void isBase(String para) {
    if ("客户基本分类".equals(para)) {/*-=notranslate=-*/
      this.so25 = true;
    }
    else {
      this.so25 = false;
    }
  }
}
