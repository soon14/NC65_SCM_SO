package nc.ui.so.m33.mansquare.action;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.so.m33.mansquare.pub.ManualSquarePubEditProcess;
import nc.ui.so.m33.pub.action.SquarePubQueryAction;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.pub.constant.QueryFlag;

public class ManualSquareQueryAction extends SquarePubQueryAction {

  private static final long serialVersionUID = 7733265866804832536L;

  @Override
  protected void executeQuery(IQueryScheme queryScheme) {
    super.executeQuery(queryScheme);
    BillListPanel listpanel = this.getListView().getBillListPanel();
    // 设置本次结算数量的编辑性
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryScheme);
    QueryCondition qc = qsp.getQueryCondition(QueryFlag.SQUAREFLAG);
    Object[] obj = qc.getValues();
    int flag = Integer.parseInt(obj[0].toString());
    if (flag == QueryFlag.SQUARE) {
      // 设置开票客户和价格的编辑性
      new ManualSquarePubEditProcess().initEditEnable(listpanel);
    }
    super.getListView().setQueryFlag(flag);
    // 2013-07-03 tianft 对冲时，不允许编辑本次结算数量
    if (flag == QueryFlag.SQUARED || flag == QueryFlag.UNOUTRUSHBLUE
        || flag == QueryFlag.UNOUTRUSHRED) {
      listpanel.getBodyItem(SquareOutBVO.NTHISNUM).setEdit(false);
    }
    else {
      listpanel.getBodyItem(SquareOutBVO.NTHISNUM).setEdit(true);
    }
  }
}
