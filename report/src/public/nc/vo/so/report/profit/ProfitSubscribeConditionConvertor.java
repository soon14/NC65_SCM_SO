package nc.vo.so.report.profit;

import java.util.List;

import nc.bs.pubapp.report.ReportPermissionUtils;
import nc.bs.scmpub.report.ReportQueryCondition;
import nc.bs.scmpub.report.ReportScaleProcess2;
import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.itf.iufo.freereport.extend.ISubscribeConditionConvertor;
import nc.itf.iufo.freereport.extend.ISubscribeQueryCondition;
import nc.ui.format.NCFormatterInstance;
import nc.ui.querytemplate.filter.DefaultFilter;
import nc.ui.querytemplate.filter.IFilter;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.querytemplate.querytree.QueryScheme;
import nc.ui.querytemplate.value.IFieldValueElement;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.util.SOMilSymbolUtil;
import nc.vo.so.report.paravo.ProfitContext;
import nc.vo.so.report.paravo.ProfitQryInfoParaVO;
import nc.vo.so.report.util.ProfitReportAdjustor;

import com.ufida.report.free.plugin.param.ReportVariables;

/**
 * 报表订阅条件处理类
 * 
 * @since 6.0
 * @version 2011-8-3 下午04:00:56
 * @author 么贵敬
 */
public class ProfitSubscribeConditionConvertor implements
    ISubscribeConditionConvertor {

  /**
   * 金额
   */
  private static final String[] MNYKEYS = new String[] {
    OrderProfitViewVO.NTOTALCOSTMNY, OrderProfitViewVO.NTOTALESTARMNY,
    OrderProfitViewVO.NPROFITMNY, OrderProfitViewVO.NTOTALRECEIVMNY,
    OrderProfitViewVO.NTOTALSETTLECOSTMNY, OrderProfitViewVO.NTOTALARMNY,
    OrderProfitViewVO.NNTAXMNY,OrderProfitViewVO.NPROFITCATE

  };

  /**
   * 数量
   */
  private static final String[] NUMKEYS = new String[] {
    OrderProfitViewVO.NTOTALCOSTNUM, OrderProfitViewVO.NTOTALRECEIVNUM,
    OrderProfitViewVO.NORDERNNUM, OrderProfitViewVO.NTOTALARNUM,
    OrderProfitViewVO.NTOTALESTARNUM, OrderProfitViewVO.NMAINNUM
  };

  /**
   * 单价
   */
  private static final String[] PRICEMNYKEYS = new String[] {
    OrderProfitViewVO.NNETPRICE, OrderProfitViewVO.NNTAXPRICE,
    OrderProfitViewVO.NPRICE,
  };
  
  @Override
  public IQueryCondition getQueryCondition(
      ISubscribeQueryCondition subscribCondition,
      com.ufida.dataset.IContext context,
      com.ufida.report.anareport.model.AbsAnaReportModel reportModel) {

    ReportPermissionUtils utils = new ReportPermissionUtils(context);
    utils.setMainBeanClass(SaleOrderHVO.class);
    if (subscribCondition == null) {
      return null;
    }

    com.ufida.report.anareport.base.BaseSubscribeCondition base =
        (com.ufida.report.anareport.base.BaseSubscribeCondition) subscribCondition;
    QueryScheme scheme = (QueryScheme) base.getScheme();
    ConditionVO[] conds =
        (ConditionVO[]) scheme.get(IQueryScheme.KEY_LOGICAL_CONDITION);

    boolean issummaryconditionsnull = true;

    ProfitQryInfoParaVO paravo = new ProfitQryInfoParaVO();
    for (ConditionVO cond : conds) {
      if (cond.getFieldCode().equals(ProfitContext.SUMMARYCONDITIONS)) {
        if (null != cond.getValue()) {
          issummaryconditionsnull = false;
          paravo.setGroupcondtion(cond.getValue());
        }
        break;
      }
    }

    IFilter[] filters = (IFilter[]) scheme.get(IQueryScheme.KEY_FILTERS);
    StringBuffer dbilldate = new StringBuffer();
    NCFormatterInstance ncformatter = reportModel.getNCFormatterInstance();
    for (IFilter filter : filters) {
      DefaultFilter fl = (DefaultFilter) filter;
      if ("dbilldate".equals(fl.getFilterMeta().getFieldCode())) {
        List<IFieldValueElement> valuelist =
            fl.getFieldValue().getFieldValues();
        for (IFieldValueElement val : valuelist) {
          dbilldate.append(SOMilSymbolUtil.setDateFormat(val.getSqlString().substring(0, 10),ncformatter));
          dbilldate.append("-");
        }
        dbilldate = dbilldate.deleteCharAt(dbilldate.length() - 1);
        ReportVariables varPool =
            ReportVariables.getInstance(reportModel.getFormatModel());
        varPool.getVariable("var_dbilldate").setValue(dbilldate);
        break;
      }
    }

    if (issummaryconditionsnull) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006005_0", "04006005-0024")/*汇总条件不能为空*/);
    }
    ReportQueryCondition result = new ReportQueryCondition(true);

    ReportScaleProcess2 process = new ReportScaleProcess2();
    // 金额精度
    process.setMnyDigits(SaleOrderBVO.CCURRENCYID,
        ProfitSubscribeConditionConvertor.MNYKEYS);
    process.setNumTotalFields(ProfitSubscribeConditionConvertor.NUMKEYS);
    // 无税单价精度
    process.setPriceDigits(ProfitSubscribeConditionConvertor.PRICEMNYKEYS,
        SaleOrderBVO.CCURRENCYID);
    // 成本单价
    process.setCostPriceDigits(new String[] {
      OrderProfitViewVO.NCOSTPRICE
    });
    // 合计精度
    process.setTotalFields(ProfitSubscribeConditionConvertor.MNYKEYS);
    result.setBusiFormat(process);
    result.setRoportAdjustor(new ProfitReportAdjustor());
    result.setUserObject(scheme);
    return result;
  }

  // private boolean isNeedDoubleEngine(ConditionVO[] conds) {
  // for (ConditionVO convo : conds) {
  // String field = convo.getFieldCode();
  // String value = convo.getValue();
  // // 销售组织没有
  // if (SOItemKey.PK_ORG.equals(field)) {
  // if (null == value || value.length() == 0) {
  // return true;
  // }
  // }
  // if (SOItemKey.DBILLDATE.equals(field)) {
  // UFDate startdate = null;
  // String[] date = value.split(",");
  // if (null != date[0] && !date[0].equals("ISNULL")) {
  // startdate = TimeUtils.getStartDate(new UFDate(date[0]));
  // }
  //
  // UFDate enddate = AppContext.getInstance().getBusiDate();
  // if (null != date[1] && !date[1].equals("ISNULL")) {
  // enddate = TimeUtils.getEndDate(new UFDate(date[1]));
  // }
  //
  // if (null == startdate) {
  // return true;
  // }
  //
  // int days = UFDate.getDaysBetween(startdate, enddate);
  // if (days > 31) {
  // return true;
  // }
  //
  // }
  //
  // }
  // return false;
  // }

}
