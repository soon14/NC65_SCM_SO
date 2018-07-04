package nc.impl.so.outsummary;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import nc.impl.pubapp.pattern.page.IPage;
import nc.impl.so.outsummary.processer.OutSummaryArProcesser;
import nc.impl.so.outsummary.processer.OutSummaryIcProcesser;
import nc.impl.so.outsummary.processer.OutSummaryLevelProcesser;
import nc.impl.so.outsummary.temp.OutSummaryTempTable;
import nc.itf.so.outsummary.IOutSummaryMaintain;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.pubapp.report.ReportQueryConUtil;
import nc.vo.scmpub.report.SCMReportTempTableUtil;
import nc.vo.so.report.outsummary.OutSummaryConConvertor;
import nc.vo.so.report.outsummary.OutSummaryViewVO;
import nc.vo.so.report.reportpub.ReportUserObject;

import com.ufida.dataset.IContext;

/**
 * 出库执行汇总查询实现类12
 * 
 * @since 6.3
 * @version 2012-10-8 上午08:55:50
 * @author 梁吉明
 */
public class OutSummaryMaintainImpl implements IOutSummaryMaintain {

  @Override
  public String queryOutSummary(IContext context) {
    // 1.创建临时表
    String temptable = this.createTempTable();

    // 2.判断查询条件是否为空
    ReportQueryConUtil qryconutil = new ReportQueryConUtil(context);

    if (qryconutil.isNull()) {
      return "select * from " + temptable;
    }
    ReportUserObject userObject = (ReportUserObject) qryconutil.getUserObject();
    // 3.获得出库单分页数据
    IPage<OutSummaryViewVO> page = new OutSummaryIcProcesser(userObject);
    while (page.hasNext()) {
      OutSummaryViewVO[] views = page.next();

      // 4.数据处理(未处理)
      this.processOutSummary(views, userObject);

      // 5.将最终数据插入临时表
      this.processTemp(views, temptable);
    }

    // 6.返回SQL
    return this.getSelectSql(temptable, userObject);
  }

  private void processTemp(OutSummaryViewVO[] views, String temptable) {
    String[] fieldnames =
        new OutSummaryTempTable().getMetaData().getFieldNames();
    SCMReportTempTableUtil tmptableutil = new SCMReportTempTableUtil();
    tmptableutil.insertIntoTempTable(temptable, fieldnames, views);
  }

  private void processOutSummary(OutSummaryViewVO[] views,
      ReportUserObject userObject) {
    // 1.获取收入信息
    OutSummaryArProcesser ar = new OutSummaryArProcesser();
    ar.queryAr(views);
    // 2.级次信息处理
    OutSummaryLevelProcesser level = new OutSummaryLevelProcesser();
    level.processLevel(views, userObject.getIQueryScheme());
  }

  private String getSelectSql(String temptable, ReportUserObject userObject) {
    QuerySchemeProcessor qsp =
        new QuerySchemeProcessor(userObject.getIQueryScheme());
    Object[] orgs =
        qsp.getQueryCondition(OutSummaryViewVO.CSALEORGOID).getValues();
    SqlBuilder colmsql = new SqlBuilder();
    String[] groupkeys = this.getGroupKeys(userObject);
    colmsql.append("select ");
    boolean isorgfalg = false;
    for (String key : groupkeys) {
      colmsql.append(key + ",");
      if (OutSummaryViewVO.CSALEORGOID.equals(key)) {
        isorgfalg = true;
      }
    }
    if (!isorgfalg) {
      colmsql.append("'" + orgs[0] + "' " + OutSummaryViewVO.CSALEORGOID + ",");
    }
    for (String key : OutSummaryConConvertor.AGGKEYS) {
      colmsql.append("sum(" + key + ") " + key + ",");
    }
    colmsql.append(" sum (narmny) - sum (narremainmny) npaymny ");
    colmsql.append(" from ");
    colmsql.append(temptable);
    colmsql.append(getSumGroupbySQL(groupkeys));
    return colmsql.toString();
  }

  public String getSumGroupbySQL(String[] groupkeys) {
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
    List<String> listgroup = new ArrayList<String>();
    Set<String> selgroups = userobject.getSummaryKeys();
    for (String key : selgroups) {
      listgroup.add(key);
      if (OutSummaryViewVO.CMATERIALOID.equals(key)) {
        listgroup.add(OutSummaryViewVO.CUNITID);
      }
    }
    listgroup.add(OutSummaryViewVO.CORIGCURRENCYID);
    String[] groupkeys = new String[listgroup.size()];
    listgroup.toArray(groupkeys);
    return groupkeys;
  }

  private String createTempTable() {
    OutSummaryTempTable table = new OutSummaryTempTable();
    String temptable = table.createOutSummaryTemptable();
    return temptable;
  }
}
