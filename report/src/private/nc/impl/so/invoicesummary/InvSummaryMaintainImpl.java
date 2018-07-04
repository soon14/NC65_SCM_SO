package nc.impl.so.invoicesummary;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import nc.impl.pubapp.pattern.page.IPage;
import nc.impl.so.invoicesummary.processer.InvSummaryArProcesser;
import nc.impl.so.invoicesummary.processer.InvSummaryInProcesser;
import nc.impl.so.invoicesummary.processer.InvSummaryLevelProcesser;
import nc.impl.so.invoicesummary.temp.InvSummaryTempTable;
import nc.impl.so.pub.summary.util.SQLCreateUtil;
import nc.itf.so.invoicesummary.IInvSummaryMaintain;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.pubapp.report.ReportQueryConUtil;
import nc.vo.scmpub.report.SCMReportTempTableUtil;
import nc.vo.so.report.invoicesummary.InvSummaryConConvertor;
import nc.vo.so.report.invoicesummary.InvSummaryViewVO;
import nc.vo.so.report.reportpub.ReportUserObject;

import com.ufida.dataset.IContext;

/**
 * 12
 * @since 6.3
 * @version 2012-9-23 上午10:19:47
 * @author 梁吉明
 */
public class InvSummaryMaintainImpl implements IInvSummaryMaintain {

  @Override
  public String queryInvSummary(IContext context) throws BusinessException {
    // 1.创建临时表
    String temptable = this.createTempTable();

    // 2.判断查询条件是否为空
    ReportQueryConUtil qryconutil = new ReportQueryConUtil(context);

    if (qryconutil.isNull()) {
      return "select * from " + temptable;
    }
    ReportUserObject userObject = (ReportUserObject) qryconutil.getUserObject();
    // 3.获得出库单分页数据
    IPage<InvSummaryViewVO> page = new InvSummaryInProcesser(userObject);
    while (page.hasNext()) {
      InvSummaryViewVO[] views = page.next();

      // 4.数据处理
      this.processInvSummary(views, userObject);

      // 5.将最终数据插入临时表
      this.processTemp(views, temptable);
    }

    // 6.返回SQL
    return this.getSelectSql(temptable, userObject);
  }

  private void processTemp(InvSummaryViewVO[] views, String temptable) {
    String[] fieldnames =
        new InvSummaryTempTable().getMetaData().getFieldNames();
    SCMReportTempTableUtil tmptableutil = new SCMReportTempTableUtil();
    tmptableutil.insertIntoTempTable(temptable, fieldnames, views);
  }

  private void processInvSummary(InvSummaryViewVO[] views,
      ReportUserObject userObject) {
    // 1.获取收入信息
    InvSummaryArProcesser arandia = new InvSummaryArProcesser();
    arandia.queryAr(views);
    // 2.级次信息处理
    InvSummaryLevelProcesser level = new InvSummaryLevelProcesser();
    level.processLevel(views, userObject.getIQueryScheme());
  }

  private String getSelectSql(String temptable, ReportUserObject userObject) {
    QuerySchemeProcessor qsp =
        new QuerySchemeProcessor(userObject.getIQueryScheme());
    QueryCondition qc = qsp.getQueryCondition(InvSummaryViewVO.PK_ORG);
    Object[] orgs = qc.getValues();
    SqlBuilder colmsql = new SqlBuilder();
    String[] groupkeys = this.getGroupKeys(userObject);
    
    colmsql.append("select  ");
    boolean isorgfalg = false;
    for (String key : groupkeys) {
      colmsql.append(key + ",");
      if (InvSummaryViewVO.PK_ORG.equals(key)) {
        isorgfalg = true;
      }
    }
    
    if (!isorgfalg) {
      colmsql.append("'" + orgs[0] + "' " + InvSummaryViewVO.PK_ORG + ",");
    }

    for (String key : InvSummaryConConvertor.AGGKEYS) {
      colmsql.append("sum(" + key + ") " + key + ",");
    }

    String norigtaxnetprice =
        SQLCreateUtil.getSumDivsql(InvSummaryViewVO.NORIGTAXMNY,
            InvSummaryViewVO.NNUM);
    colmsql.appendCaseWhen("sum (nnum )<>0", norigtaxnetprice, "0");
    colmsql.append(" norigtaxnetprice,");

    colmsql.append("sum (nshouldreceivmny )- sum (ntotalreceivmny )");
    colmsql.append(" receivmny");
    colmsql.append(" from  ");
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
      if (InvSummaryViewVO.CMATERIALID.equals(key)) {
        listgroup.add(InvSummaryViewVO.CUNITID);
      }
    }
    listgroup.add(InvSummaryViewVO.CORIGCURRENCYID);
    listgroup.add(InvSummaryViewVO.BLABORFLAG);
    listgroup.add(InvSummaryViewVO.BDISCOUNTFLAG);
    String[] groupkeys = new String[listgroup.size()];
    listgroup.toArray(groupkeys);
    return groupkeys;
  }

  private String createTempTable() {
    InvSummaryTempTable table = new InvSummaryTempTable();
    String temptable = table.createInvSummaryTemptable();
    return temptable;
  }

}
