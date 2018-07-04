package nc.impl.so.outprofit;

import java.util.HashSet;
import java.util.Set;

import nc.impl.pubapp.pattern.page.IPage;
import nc.impl.so.outprofit.processer.OutProfitArAndIaProcesser;
import nc.impl.so.outprofit.processer.OutProfitCalculateProcesser;
import nc.impl.so.outprofit.processer.OutProfitIcProcesser;
import nc.impl.so.outprofit.processer.OutProfitLevelProcesser;
import nc.impl.so.outprofit.temp.OutProfitTempTable;
import nc.impl.so.pub.summary.util.SQLCreateUtil;
import nc.itf.so.outprifit.IOutProfitMaintain;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.pubapp.report.ReportQueryConUtil;
import nc.vo.scmpub.report.SCMReportTempTableUtil;
import nc.vo.so.report.outprofit.OutProfitConConvertor;
import nc.vo.so.report.outprofit.OutProfitViewVO;
import nc.vo.so.report.reportpub.ReportUserObject;

import com.ufida.dataset.IContext;

/**
 * 销售出库毛利分析接口实现类12
 * 
 * @since 6.3
 * @version 2012-09-03 11:19:15
 * @author 梁吉明
 */
public class OutProfitMaintainImpl implements IOutProfitMaintain {

  @Override
  public String queryOutPrifit(IContext context) {

    // 1.创建临时表
    String temptable = this.createTempTable();

    // 2.判断查询条件是否为空
    ReportQueryConUtil qryconutil = new ReportQueryConUtil(context);

    if (qryconutil.isNull()) {
      return "select * from " + temptable;
    }
    ReportUserObject userObject = (ReportUserObject) qryconutil.getUserObject();
    // 3.获得出库单分页数据
    IPage<OutProfitViewVO> page = new OutProfitIcProcesser(userObject);

    while (page.hasNext()) {
      OutProfitViewVO[] views = page.next();

      // 4.数据处理
      this.processOutProfit(views, userObject);

      // 5.将最终数据插入临时表
      this.processTemp(views, temptable);
    }

    // 6.返回SQL
    return this.getSelectSql(temptable, userObject);
  }

  private void processOutProfit(OutProfitViewVO[] views,
      ReportUserObject userObject) {

    // 1.获取成本和收入信息
    OutProfitArAndIaProcesser arandia = new OutProfitArAndIaProcesser();
    arandia.queryArAndIa(views);

    // 2.获取毛利信息
    OutProfitCalculateProcesser cal = new OutProfitCalculateProcesser();
    cal.calculateProfit(views);

    // 3.级次信息处理
    OutProfitLevelProcesser level = new OutProfitLevelProcesser();
    level.processLevel(views, userObject.getIQueryScheme());
  }

  private void processTemp(OutProfitViewVO[] views, String temptable) {
    String[] fieldnames =
        new OutProfitTempTable().getMetaData().getFieldNames();
    SCMReportTempTableUtil tmptableutil = new SCMReportTempTableUtil();
    tmptableutil.insertIntoTempTable(temptable, fieldnames, views);
  }

  private String createTempTable() {
    OutProfitTempTable table = new OutProfitTempTable();
    String temptable = table.createOutprofitTemptable();
    return temptable;
  }

  private String getSelectSql(String temptable, ReportUserObject userObject) {
    QuerySchemeProcessor qsp =
        new QuerySchemeProcessor(userObject.getIQueryScheme());
    QueryCondition qc = qsp.getQueryCondition(OutProfitViewVO.CSALEORGOID);
    Object[] orgs = qc.getValues();

    SqlBuilder colmsql = new SqlBuilder();
    String[] groupkeys = this.getGroupKeys(userObject);
    colmsql.append("select ");

    boolean isorgfalg = false;
    for (String key : groupkeys) {
      colmsql.append(key + ",");
      if (OutProfitViewVO.CSALEORGOID.equals(key)) {
        isorgfalg = true;
      }
    }

    if (!isorgfalg) {
      colmsql.append("'" + orgs[0] + "' " + OutProfitViewVO.CSALEORGOID + ",");
    }

    for (String key : OutProfitConConvertor.AGGKEYS) {
      colmsql.append("sum(" + key + ") " + key + ",");
    }
    String nnotaxprice =
        SQLCreateUtil.getSumDivsql(OutProfitViewVO.NNOTAXMNY,
            OutProfitViewVO.NMAINNUM);
    colmsql.appendCaseWhen("sum(nmainnum)<>0", nnotaxprice, "0");
    colmsql.append(" nnotaxprice,");

    String ncostprice =
        SQLCreateUtil.getSumDivsql(OutProfitViewVO.NTOTALCOSTMNY,
            OutProfitViewVO.NMAINNUM);
    colmsql.appendCaseWhen("sum(nmainnum)<>0", ncostprice, "0");
    colmsql.append(" ncostprice,");

    String nprofitrate =
        SQLCreateUtil.getSumDivsql(OutProfitViewVO.NPROFITMNY,
            OutProfitViewVO.NNOTAXMNY);
    colmsql.appendCaseWhen("sum(nnotaxmny)<>0", nprofitrate, "0");
    colmsql.append(" nprofitrate ");

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

  private String[] getGroupKeys(ReportUserObject userobject) {
    Set<String> listgroup = new HashSet<String>();
    Set<String> selgroups = userobject.getSummaryKeys();
    for (String key : selgroups) {
      listgroup.add(key);
      if (OutProfitViewVO.CMATERIALVID.equals(key)
          || OutProfitViewVO.CMATERIALOID.equals(key)) {
        listgroup.add(OutProfitViewVO.CUNITID);
        listgroup.add(OutProfitViewVO.CMATERIALVID);
        listgroup.add(OutProfitViewVO.CMATERIALOID);
      }
    }
    listgroup.add(OutProfitViewVO.CORIGCURRENCYID);
    String[] groupkeys = new String[listgroup.size()];
    listgroup.toArray(groupkeys);
    return groupkeys;
  }
}
