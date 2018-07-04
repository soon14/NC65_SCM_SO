package nc.bs.so.buylargess.maintain.rule;

import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.mbuylargess.entity.BuyLargessHVO;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;
import nc.vo.so.pub.util.BaseSaleClassUtil;
import nc.vo.trade.checkrule.VOChecker;

import nc.bs.ml.NCLangResOnserver;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * 保存前唯一性校验：表头销售组织+物料+物料分类+物料销售分类+客户+客户分类+客户销售分类不允许重复
 * @scene
 * 买赠设置保存前规则
 * @param
 * 无
 * @since 6.3
 * @version 2010-1-12
 * @author 祝会征
 */
public class BuyLargessUniqueCheck implements IRule<BuyLargessVO> {

  @Override
  public void process(BuyLargessVO[] bills) {
    for (BuyLargessVO bill : bills) {
      this.process(bill);
    }

  }

  private SqlBuilder getSql(SqlBuilder querysql, BuyLargessHVO head) {
    querysql
        .append("select so_buylargess.pk_buylargess from so_buylargess where ");
    querysql.append("so_buylargess.pk_org", head.getPk_org());
    // 物料
    querysql.append(" and ");
    querysql.append("so_buylargess.cbuymarid", head.getCbuymarid());
    // 物料分类
    querysql.append(" and ");
    querysql.append("so_buylargess.pk_marbasclass", head.getPk_marbasclass());
    // 物料销售分类
    querysql.append(" and ");
    querysql.append("so_buylargess.pk_marsaleclass", head.getPk_marsaleclass());
    // 客戶
    querysql.append(" and ");
    querysql.append("so_buylargess.pk_customer", head.getPk_customer());
    // 客户分类
    querysql.append(" and ");
    querysql.append("so_buylargess.pk_custclass", head.getPk_custclass());
    // 客户销售分类
    querysql.append(" and ");
    querysql.append("so_buylargess.pk_custsaleclass",
        head.getPk_custsaleclass());

    // 删除标志
    querysql.append(" and  so_buylargess.dr = 0 ");
    return querysql;
  }

  private void process(BuyLargessVO bill) {
    SqlBuilder querysql = new SqlBuilder() {

      @Override
      public void append(String name, String value) {
        if (VOChecker.isEmpty(value)) {
          super.append(name);
          super.append(" = '~' ");
        }
        else {
          super.append(name, value);
        }

      }
    };
    BuyLargessHVO head = bill.getParentVO();
    querysql = this.getSql(querysql, head);
    StringBuffer errMsg = new StringBuffer();
    errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
        "04006003-0013")/*销售组织+*/);

    String material = head.getCbuymarid();
    if (PubAppTool.isNull(material)) {
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
          "04006003-0014")/*物料+*/);
    }
    String marbascl = head.getPk_marbasclass();
    String pk_group = BSContext.getInstance().getGroupID();
    boolean ismarbas = BaseSaleClassUtil.isMarBaseClass(pk_group);
    if (ismarbas && PubAppTool.isNull(marbascl)) {
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
          "04006003-0015")/*物料分类+*/);
    }
    String marsalecl = head.getPk_marsaleclass();
    if (!ismarbas && PubAppTool.isNull(marsalecl)) {
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
          "04006003-0016")/*物料销售分类+*/);
    }

    String custid = head.getPk_customer();
    if (PubAppTool.isNull(custid)) {
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
          "04006003-0017")/*客户+*/);
    }

    String custbasecl = head.getPk_custclass();
    boolean iscustbas = BaseSaleClassUtil.isCustBaseClass(pk_group);
    if (iscustbas && PubAppTool.isNull(custbasecl)) {
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
          "04006003-0018")/*客户分类+*/);
    }

    String custsalecl = head.getPk_custsaleclass();
    if (!iscustbas && PubAppTool.isNull(custsalecl)) {
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
          "04006003-0019")/*客户销售分类+*/);
    }
    errMsg.deleteCharAt(errMsg.length() - 1);

    errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
        "04006003-0020")/*重复！*/);
    DataAccessUtils util = new DataAccessUtils();
    IRowSet rs = util.query(querysql.toString());
    while (rs.next()) {
      String oldpk = rs.getString(0);
      String newpk = head.getPk_buylargess();
      if (null == newpk || !newpk.equals(oldpk)) {
        ExceptionUtils.wrappBusinessException(errMsg.toString());
      }
    }
  }
}
