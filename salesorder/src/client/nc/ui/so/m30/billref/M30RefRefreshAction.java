package nc.ui.so.m30.billref;

import java.util.List;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.so.pub.query.SOQuerySchemeUtils;

/**
 * 参照销售订单刷新按钮基类
 * <p>子类可重写executeQuery添加自己的逻辑</p>
 * 
 * @since 6.0
 * @version 2011-6-18 下午02:45:55
 * @author 刘志伟
 */
public class M30RefRefreshAction extends
    nc.ui.pubapp.billref.src.action.RefreshAction {

  private static final long serialVersionUID = 9056631557545346943L;

  private SOQuerySchemeUtils querySchemeUtils;

  @Override
  protected void executeQuery(IQueryScheme queryScheme) {
    this.appendBusitypeWhr(queryScheme);
    this.appendTranTypeWhr(queryScheme);
    super.executeQuery(queryScheme);
  }

  private void appendBusitypeWhr(IQueryScheme qs) {
    List<String> busiLst =
        this.getRefContext().getBillReferQuery().getBusitypes();
    if (null != busiLst) {
      this.getQuerySchemeUtils().storeBusitype(qs, busiLst);
    }
  }

  private void appendTranTypeWhr(IQueryScheme qs) {
    List<String> tranList =
        this.getRefContext().getBillReferQuery().getTranstypes();
    // 把上游交易类型集合放到sheme中，在后台取出拼接相应的条件
    if (null != tranList) {
      this.getQuerySchemeUtils().storeTranType(qs, tranList);
    }
  }

  private SOQuerySchemeUtils getQuerySchemeUtils() {
    if (this.querySchemeUtils == null) {
      this.querySchemeUtils = new SOQuerySchemeUtils();
    }
    return this.querySchemeUtils;
  }
}
