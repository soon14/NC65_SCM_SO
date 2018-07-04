package nc.impl.so.para.rule.so24;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.so.custmatrel.entity.CustMatRelBVO;
import nc.vo.so.depmatrel.entity.DepMatRelBVO;
import nc.vo.so.mbuylargess.entity.BuyLargessHVO;
import nc.vo.so.mreturnassign.entity.ReturnAssignVO;
import nc.vo.so.tranmatrel.entity.TranMatRelBVO;

public class CheckSO24ForSORule {
  private String pk_group;

  private boolean so24;

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
    isEnable = this.checkDeptAndMat();
    if (!isEnable) {
      return false;
    }
    isEnable = this.checkReturnPolice();
    if (!isEnable) {
      return false;
    }
    isEnable = this.checkTrantypeAndMat();
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
    if (!this.so24) {
      pk = BuyLargessHVO.PK_MARSALECLASS;
    }
    else {
      pk = BuyLargessHVO.PK_MARBASCLASS;
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
    if (!this.so24) {
      pk = CustMatRelBVO.PK_MATERIALSALECLASS;
    }
    else {
      pk = CustMatRelBVO.PK_MATERIALBASECLASS;
    }
    return this.getEnable(table, pk);
  }

  /*
   * 检查部门和物料关系 
   * @param pk_group
   * @param so24
   * @return
   */
  private boolean checkDeptAndMat() {
    String pk;
    String table = "so_depmatrel_b";
    if (!this.so24) {
      pk = DepMatRelBVO.PK_MATERIALSALECLASS;
    }
    else {
      pk = DepMatRelBVO.PK_MATERIALBASECLASS;
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
    if (!this.so24) {
      pk = ReturnAssignVO.PK_MARSALECLASS;
    }
    else {
      pk = ReturnAssignVO.PK_MARBASCLASS;
    }
    return this.getEnable(table, pk);
  }

  /*
   * 检查交易类型和物料
   * @param pk_group
   * @param so24
   * @return
   */
  private boolean checkTrantypeAndMat() {
    String pk;
    String table = "so_tranmatrel_b";
    if (!this.so24) {
      pk = TranMatRelBVO.PK_MATERIALSALECLASS;
    }
    else {
      pk = TranMatRelBVO.PK_MATERIALBASECLASS;
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
    if ("物料基本分类".equals(para)) {/*-=notranslate=-*/
      this.so24 = true;
    }
    else {
      this.so24 = false;
    }
  }
}
