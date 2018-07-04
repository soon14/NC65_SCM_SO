package nc.impl.so.ordersummary;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import nc.impl.pubapp.pattern.page.IPage;
import nc.impl.so.ordersummary.processer.OrderSummaryArAndIaProcesser;
import nc.impl.so.ordersummary.processer.OrderSummaryCalculateProcesser;
import nc.impl.so.ordersummary.processer.OrderSummaryLevelProcesser;
import nc.impl.so.ordersummary.processer.OrderSummaryOrProcesser;
import nc.impl.so.ordersummary.temp.OrderSummaryTempTable;
import nc.impl.so.pub.summary.util.SQLCreateUtil;
import nc.itf.so.ordersummary.IOrderSummaryMaintain;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.pubapp.report.ReportQueryConUtil;
import nc.vo.scmpub.report.SCMReportTempTableUtil;
import nc.vo.so.report.ordersummary.OrderSummaryConConvertor;
import nc.vo.so.report.ordersummary.OrderSummaryViewVO;
import nc.vo.so.report.reportpub.ReportUserObject;

import com.ufida.dataset.IContext;

/**
 * 销售订单执行汇总查询实现类12
 * 
 * @since 6.3
 * @version 2012-10-8 上午08:55:50
 * @author 梁吉明
 */
public class OrderSuammaryMaintainImpl implements IOrderSummaryMaintain {

  @Override
  public String queryOrderSummary(IContext context) throws BusinessException {
    // 1.创建临时表
    String temptable = this.createTempTable();

    // 2.判断查询条件是否为空
    ReportQueryConUtil qryconutil = new ReportQueryConUtil(context);
    if (qryconutil.isNull()) {
      return "select * from " + temptable;
    }
    ReportUserObject userObject = (ReportUserObject) qryconutil.getUserObject();
    // 3.获得出库单分页数据
    IPage<OrderSummaryViewVO> page = new OrderSummaryOrProcesser(userObject);
    while (page.hasNext()) {
      OrderSummaryViewVO[] views = page.next();

      // 4.数据处理
      this.processOrderSummary(views, userObject);

      // 5.将最终数据插入临时表
      this.processTemp(views, temptable);
    }

    // 6.返回SQL
    return this.getSelectSql(temptable, userObject);
  }

  private void processTemp(OrderSummaryViewVO[] views, String temptable) {
    String[] fieldnames =
        new OrderSummaryTempTable().getMetaData().getFieldNames();
    SCMReportTempTableUtil tmptableutil = new SCMReportTempTableUtil();
    tmptableutil.insertIntoTempTable(temptable, fieldnames, views);
  }

  private void processOrderSummary(OrderSummaryViewVO[] views,
      ReportUserObject userObject) {
    // 1.待出库数量
    OrderSummaryCalculateProcesser cal = new OrderSummaryCalculateProcesser();
    cal.processCal(views);
    // 2.获取收入信息
    OrderSummaryArAndIaProcesser arandia = new OrderSummaryArAndIaProcesser();
    arandia.queryArAndIa(views);
    // 3.级次信息处理
    OrderSummaryLevelProcesser level = new OrderSummaryLevelProcesser();
    level.processLevel(views, userObject.getIQueryScheme());
  }

  private String getSelectSql(String temptable, ReportUserObject userObject) {
    QuerySchemeProcessor qsp =
        new QuerySchemeProcessor(userObject.getIQueryScheme());
    QueryCondition qc = qsp.getQueryCondition(OrderSummaryViewVO.PK_ORG);
    Object[] orgs = qc.getValues();
    SqlBuilder colmsql = new SqlBuilder();
    String[] groupkeys = this.getGroupKeys(userObject);
    colmsql.append("select ");

    boolean isorgfalg = false;
    for (String key : groupkeys) {
      colmsql.append(key + ",");
      if (OrderSummaryViewVO.PK_ORG.equals(key)) {
        isorgfalg = true;
      }
    }
    
    if (!isorgfalg) {
      colmsql.append("'" + orgs[0] + "' " + OrderSummaryViewVO.PK_ORG + ",");
    }
    for (String key : OrderSummaryConConvertor.AGGKEYS) {
      colmsql.append("sum(" + key + ") " + key + ",");
    }

    String norigtaxnetprice =
        SQLCreateUtil.getSumDivsql(OrderSummaryViewVO.NORIGTAXMNY,
            OrderSummaryViewVO.NNUM);
    colmsql.appendCaseWhen("sum(nnum)<>0", norigtaxnetprice, "0");
    colmsql.append("norigtaxnetprice,");

    String ncostprice =
        SQLCreateUtil.getSumDivsql(OrderSummaryViewVO.NTOTALCOSTMNY,
            OrderSummaryViewVO.NNUM);
    colmsql.appendCaseWhen("sum(nnum)<>0", ncostprice, "0");
    colmsql.append("ncostprice,");

    colmsql.append(" sum(nshouldreceivmny) - sum(ntotalreceivmny) npaymny ");
    colmsql.append(" from ");
    colmsql.append(temptable);
    colmsql.append(getSumGroupbySQL(groupkeys));
    return colmsql.toString();
  }

  private String getSumGroupbySQL(String[] groupkeys) {
    SqlBuilder sumsql = new SqlBuilder();
    sumsql.append(" group by  ");
    for (String sumkey : groupkeys) {
      sumsql.append(sumkey);
      sumsql.append(",");
    }
    sumsql.deleteLastChar();
    return sumsql.toString();
  }

  private String[] getGroupKeys(ReportUserObject userObject) {
    List<String> listgroup = new ArrayList<String>();
    Set<String> selgroups = userObject.getSummaryKeys();
    for (String key : selgroups) {
      listgroup.add(key);
      if (OrderSummaryViewVO.CMATERIALID.equals(key)) {
        listgroup.add(OrderSummaryViewVO.CUNITID);
      }
    }
    listgroup.add(OrderSummaryViewVO.CORIGCURRENCYID);
    listgroup.add(OrderSummaryViewVO.BLABORFLAG);
    listgroup.add(OrderSummaryViewVO.BDISCOUNTFLAG);
    String[] groupkeys = new String[listgroup.size()];
    listgroup.toArray(groupkeys);
    return groupkeys;
  }

  private String createTempTable() {
    OrderSummaryTempTable table = new OrderSummaryTempTable();
    String temptable = table.createOrderSummaryTemptable();
    return temptable;
  }

}
