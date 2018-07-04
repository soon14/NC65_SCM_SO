package nc.impl.so.m38.migrate.rule;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.so.m38.migrate.action.QuerPreOrderMigStateAction;
import nc.impl.so.m38.migrate.action.QuerySaleOrgAction;
import nc.impl.so.m38.migrate.constant.PreorderMigState;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.pub.util.ListUtil;

public class PreOrderMigBeforeRule {

  public void check() throws BusinessException {
    // 1.获取预订单迁移标记，判断是否已经做过升迁
    QuerPreOrderMigStateAction migState = new QuerPreOrderMigStateAction();
    if(migState.getPreOrderMigStatus() == PreorderMigState.FINISHED){
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID(
          "4006012_0", "04006012-0123")/* 预订单迁移只能执行一次。 */);
    }
    
    // 2.是否启用了电子销售模块, 未启用，抛异常并提示未启用
    if (!SysInitGroupQuery.isOPCEnabled()) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006012_0", "04006012-0110")/* 电子销售模块未启用，无法启用升级程序！ */);
    }

    // 3.判断销售预订单的组织类型是否有未勾选“电子销售”的，如果有，则进行异常提示
    QuerySaleOrgAction action = new QuerySaleOrgAction();
    Map<String, List<String>> groupOrg_CodeMap = action.getSaleOrgNotElectSaleOrgs();
    if(groupOrg_CodeMap.size() > 0){
      hint(groupOrg_CodeMap);
    }
  }

  private void hint(Map<String, List<String>> groupOrg_CodeMap) throws BusinessException {
    if (groupOrg_CodeMap.size() > 0) {
      StringBuilder sb = new StringBuilder();
      Iterator<Entry<String, List<String>>> it = groupOrg_CodeMap.entrySet().iterator();
      while (it.hasNext()) {
        Entry<String, List<String>> entry = it.next();
        String groupCode = entry.getKey();
        List<String> orgCodes = entry.getValue();
        sb.append("\n");
        sb.append(NCLangResOnserver.getInstance().getStrByID("4006012_0","04006012-0111")/*集团编码*/);
        sb.append(":");
        sb.append(groupCode);
        sb.append(",");
        sb.append(NCLangResOnserver.getInstance().getStrByID("4006012_0","04006012-0112")/*组织编码*/);
        sb.append(":");
        sb.append(Arrays.toString(ListUtil.toArray(orgCodes)));
        sb.append(";");
      }

      String msg = sb.substring(0, sb.length() - 1);
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006012_0", "04006012-0114", null, new String[] {
            msg
          })/* 有销售预订单的组织类型未勾选“电子销售”选项，请手动勾选后重新点击迁移按钮进行迁移！对应的集团与组织编码为：{0} */);
    }
  }
}
