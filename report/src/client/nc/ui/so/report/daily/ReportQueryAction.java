package nc.ui.so.report.daily;

import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import nc.bs.scmpub.report.ReportScaleProcess;
import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.pub.smart.model.preferences.Parameter;
import nc.pub.smart.util.PreferencesHelper;
import nc.ui.iufo.freereport.extend.DefaultQueryAction;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.query2.DefaultQueryConditionDLG;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.query.ConditionVO;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.util.TimeUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.report.daily.DailyBaseQueryCondition;

/**
 * 综合日报查询
 * 
 * @since 6.0
 * @version 2011-4-27 上午08:41:51
 * @author 么贵敬
 */
public class ReportQueryAction extends DefaultQueryAction {

  /**
   * 金额字段
   */
  private static final String[] MNYKEYS = {
    "deliverynmny", "deliveryntaxmny", "ordernmny", "orderntaxmny",
    "invoicenmny", "invoicentaxmny", "saleoutnmny", "saleoutntaxmny",
    "redsaleoutnmny", "redsaleoutntaxmny"
  };

  /**
   * 数量字段
   */
  private static final String[] NUMKEYS = {
    // 主数量、累计出库主数量、签收数量、
    "ordernnum", "deliverynnum", "saleoutnnum", "invoicennum", "redsaleoutnnum"
  };

  @Override
  protected IQueryCondition createQueryCondition(
      com.ufida.dataset.IContext context) {
    DailyBaseQueryCondition condition = new DailyBaseQueryCondition(true);
    ReportScaleProcess process = new ReportScaleProcess();
    // 金额精度
    process.setMnyDigits(SaleOrderBVO.CCURRENCYID, ReportQueryAction.MNYKEYS);
    /*// 数量精度
    process.setNumDigits("CUNITID", ReportQueryAction.NUMKEYS);*/

    condition.setBusiFormat(process);
    return condition;
  }

  @Override
  protected QueryConditionDLG createQueryDlg(Container parent, TemplateInfo ti,
      com.ufida.dataset.IContext context, IQueryCondition oldCondition) {
    DefaultQueryConditionDLG dlg = new DefaultQueryConditionDLG(parent, ti);
    dlg.setTitle(this.getTitle(context));
    dlg.getQryCondEditor().getQueryContext().setMultiTB(true);
    this.setFilter(dlg);
    return dlg;
  }

  @Override
  protected IQueryCondition setQueryResult(IQueryCondition cond,
      QueryConditionDLG queryDlg,
      com.ufida.report.anareport.model.AbsAnaReportModel reportModel,
      com.ufida.dataset.IContext context) {
    if (cond == null) {
      return cond;
    }
    if (!(cond instanceof com.ufida.report.anareport.base.BaseQueryCondition)) {
      return cond;
    }
    DailyBaseQueryCondition result = (DailyBaseQueryCondition) cond;

    ConditionVO[] conds = queryDlg.getLogicalConditionVOs();

    List<Parameter> querycondtion = this.changeToParameter(conds);

    result.setQuerycondtion(querycondtion);
    result.setDescriptors(null);
    return result;
  }

  @Override
  protected IQueryCondition showQueryDialog(Container parent,
      com.ufida.dataset.IContext context,
      com.ufida.report.anareport.model.AbsAnaReportModel reportModel,
      TemplateInfo tempinfo, IQueryCondition oldCondition) {
    QueryConditionDLG queryDlg =
        this.getQueryConditionDlg(parent, context, reportModel, oldCondition);
    QueryConditionDLGDelegator dlgDelegator = this.getDLGDelegator(queryDlg);
    if (dlgDelegator.showModal() == UIDialog.ID_OK) {
      IQueryCondition condition = this.createQueryCondition(context);
      condition =
          this.setQueryResult(condition, queryDlg, reportModel, context);
      return condition;
    }
    return new DailyBaseQueryCondition(false);
  }

  private List<Parameter> changeToParameter(ConditionVO[] conds) {
    List<Parameter> paras = new ArrayList<Parameter>();
    for (ConditionVO cond : conds) {
      Parameter para = new Parameter();
      if (cond.getFieldCode().equals("dbilldate")) {
        String dbilldate = cond.getValue();
        String[] date = dbilldate.split(",");
        if (null != date[0] && !date[0].equals("ISNULL")) {
          Parameter startdata = new Parameter();
          UFDate startdate = TimeUtils.getStartDate(new UFDate(date[0]));
          startdata.setCode("startdate");
          startdata.setDataType(Integer
              .valueOf(PreferencesHelper.DATATYPE_ENUM_STRING));
          startdata.setOperator(">=");
          startdata.setName("startdate");
          startdata.setValue(startdate.toString());
          paras.add(startdata);
        }
        if (null != date[1] && !date[1].equals("ISNULL")) {
          String[] enddatestrs = date[1].split("@@");
          Parameter enddata = new Parameter();
          UFDate enddate =
              TimeUtils.getEndDate(TimeUtils.getEndDate(new UFDate(
                  enddatestrs[1])));
          enddata.setCode("enddate");
          enddata.setDataType(Integer
              .valueOf(PreferencesHelper.DATATYPE_ENUM_STRING));
          enddata.setOperator("<=");
          enddata.setName("enddate");
          enddata.setValue(enddate.toString());
          paras.add(enddata);
        }
        continue;
      }
      if (null == cond.getValue()) {
        continue;
      }
      String ret = cond.getValue().replace("('", "");
      ret = ret.replace("')", "");

      para.setCode(cond.getFieldCode());
      para.setDataType(Integer.valueOf(cond.getDataType()));
      para.setOperator(cond.getOperaCode());
      para.setName(cond.getFieldCode());
      paras.add(para);
    }

    return paras;
  }

  private QueryConditionDLGDelegator getDLGDelegator(QueryConditionDLG queryDlg) {
    QueryConditionDLGDelegator condDLGDelegator =
        new QueryConditionDLGDelegator(queryDlg);
    // 为查询模板的字段参照等过滤
    // this.setFilter(condDLGDelegator);
    return condDLGDelegator;
  }

  private void setFilter(DefaultQueryConditionDLG condDLG) {

    // 销售订单交易类型
    QTransTypeFilter trantype =
        new QTransTypeFilter(condDLG, SaleOrderHVO.CTRANTYPEID,
            SOBillType.Order.getCode());
    trantype.filter();

    // 客户:如果查询一个销售组织的数据，则可参照该销售组织可见的客户，否则参照集团范围客户档案。
    QCustomerFilter cust = new QCustomerFilter(condDLG, "ccustomercode");
    cust.setPk_orgCode("csaleorgid");
    cust.addEditorListener();

    // 销售业务员：参照销售组织可见的人员（销售组织非空且唯一）,否则参照集团可见的物料进行录入
    QPsndocFilter employee =
        new QPsndocFilter(condDLG, SaleOrderHVO.CEMPLOYEEID);
    employee.setPk_orgCode("csaleorgid");
    employee.addEditorListener();

    // 销售部门: 参照销售组织可见的部门（销售组织非空且唯一）,否则参照集团可见的物料进行录入
    QDeptFilter dept = new QDeptFilter(condDLG, SaleOrderHVO.CDEPTVID);
    dept.setPk_orgCode("csaleorgid");
    dept.addEditorListener();

    // 物料基本分类：参照集团物料基本分类
    // QMarbasclassFilter marbasclass =
    // new QMarbasclassFilter(condDLG, "pk_marbasclass");
    // marbasclass.filterByGroup();

    // 物料编码:参照销售组织可见的物料（销售组织非空且唯一）,否则参照集团可见的物料进行录入
    QMarterialFilter marteral =
        new QMarterialFilter(condDLG, "csaleorgid", "cmaterialcode");
    marteral.addEditorListener();
    
    new QFfileFilterByMaterCode(condDLG, "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLG, "so_saleorder_b.cmaterialid.code", "so_saleorder_b.cmffileid.vskucode").addEditorListener();

  }
}
