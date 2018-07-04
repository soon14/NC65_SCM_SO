package nc.vo.so.report.daily;

import nc.bs.pubapp.report.ReportPermissionUtils;
import nc.bs.scmpub.report.ReportScaleProcess2;
import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.itf.iufo.freereport.extend.ISubscribeConditionConvertor;
import nc.itf.iufo.freereport.extend.ISubscribeQueryCondition;
import nc.pub.smart.context.ISmartContextKey;
import nc.ui.format.NCFormatterInstance;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.querytemplate.querytree.QueryScheme;
import nc.vo.arap.receivable.AggReceivableBillVO;
import nc.vo.ic.m4c.entity.SaleOutHeadVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.util.TimeUtils;
import nc.vo.scmpub.report.SCMReportDoubleEngineUntil;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.util.SOMilSymbolUtil;

import com.ufida.report.free.plugin.param.ReportVariables;

/**
 * 综合日报报表订阅条 件处理类
 * 
 * @since 6.0
 * @version 2011-8-3 下午04:00:56
 * @author 么贵敬
 */
public class DailySubscribeConditionConvertor implements
    ISubscribeConditionConvertor {

  /**
   * 金额字段
   */
  private static final String[] MNYKEYS = {
    "ordernmny", "orderntaxmny", "DELIVERYNMNY", "DELIVERYNTAXMNY",
    "invoicenmny", "invoicentaxmny", "saleoutnmny", "saleoutntaxmny",
    "redsaleoutnmny", "redsaleoutntaxmny", "receivablenmny", "gatheringnmny"
  };

  /**
   * 数量字段
   */
  private static final String[] NUMKEYS = {
    // 主数量、累计出库主数量、签收数量、
    "ordernnum", "deliverynnum", "saleoutnnum", "invoicennum", "redsaleoutnnum"
  };

  @SuppressWarnings({
    "unchecked", "static-access"
  })
  @Override
  public IQueryCondition getQueryCondition(
      ISubscribeQueryCondition subscribCondition,
      com.ufida.dataset.IContext context,
      com.ufida.report.anareport.model.AbsAnaReportModel reportModel) {

    ReportPermissionUtils utils = new ReportPermissionUtils(context);
    utils.setMainBeanClass(new Class[] {
      SaleOrderHVO.class, SaleInvoiceHVO.class, SaleOutHeadVO.class,
      DeliveryHVO.class, AggReceivableBillVO.class
    });
    if (subscribCondition == null) {
      return null;
    }

    com.ufida.report.anareport.base.BaseSubscribeCondition base =
        (com.ufida.report.anareport.base.BaseSubscribeCondition) subscribCondition;
    QueryScheme scheme = (QueryScheme) base.getScheme();
    ConditionVO[] conds =
        (ConditionVO[]) scheme.get(IQueryScheme.KEY_LOGICAL_CONDITION);
    DailyBaseQueryCondition result = new DailyBaseQueryCondition(true);
    ReportVariables varPool =
        ReportVariables.getInstance(reportModel.getFormatModel());
    NCFormatterInstance ncformatter = reportModel.getNCFormatterInstance();
    for (ConditionVO cond : conds) {
      if (cond.getFieldCode().equals("dbilldate")) {
        String dbilldate = cond.getValue();
        String[] date = dbilldate.split(",");
        StringBuffer showdate = new StringBuffer();
        if (null != date[0] && !date[0].equals("ISNULL")) {
          UFDate startdate = TimeUtils.getStartDate(new UFDate(date[0]));
          context.setAttribute(ISmartContextKey.PREX_PARAM + "startdate",
              startdate.toString());
          showdate.append(SOMilSymbolUtil.setDateTimeFormat(startdate.toString(),ncformatter));
        }
        if (null != date[1] && !date[1].equals("ISNULL")) {
          String[] enddatestrs = date[1].split("@@");
          UFDate enddate = TimeUtils.getEndDate(new UFDate(enddatestrs[1]));
          context.setAttribute(ISmartContextKey.PREX_PARAM + "enddate",
              enddate.toString());
          showdate.append("-" + SOMilSymbolUtil.setDateTimeFormat(enddate.toString(),ncformatter));
        }
        // 为报表自定义变量设值
        varPool.getVariable(SOConstant.VAR_DBILLDATE).setValue(showdate);
        continue;
      }
      if (null == cond.getValue()) {
        continue;
      }
      String ret = cond.getValue().replace("('", "");
      ret = ret.replace("')", "");
      if ("containlaboranddiscount".equals(cond.getFieldCode())) {
        if ("1".equals(ret) || "Y".equals(ret)) {
          ret = null;
          continue;
        }
        ret = "N";

      }
      context.setAttribute(ISmartContextKey.PREX_PARAM + cond.getFieldCode(),
          ret);
    }

    ReportScaleProcess2 process = new ReportScaleProcess2();
    // 金额精度
    process.setMnyDigits(SaleOrderBVO.CCURRENCYID,
        DailySubscribeConditionConvertor.MNYKEYS);
    /* // 数量精度
     process.setNumDigits("this.CMATERIALID." + MaterialVO.PK_MEASDOC,
         DailySubscribeConditionConvertor.NUMKEYS);
    */
    process.setTotalFields(DailySubscribeConditionConvertor.MNYKEYS);
    process.setNumTotalFields(DailySubscribeConditionConvertor.NUMKEYS);
    result.setBusiFormat(process);

    result.setDescriptors(null);
    result.setUserObject(conds);
    SCMReportDoubleEngineUntil doubledsutil = new SCMReportDoubleEngineUntil();
    if (this.isNeedDoubleEngine(conds)) {
      doubledsutil.setSCMDoubleEngine(context);
    }
    else {
      doubledsutil.resumeCurrDataSource(context);
    }

    return result;
  }

  /**
   * 是否需要执行双引擎
   * 
   * @param conds
   * @return
   */
  private boolean isNeedDoubleEngine(ConditionVO[] conds) {
    for (ConditionVO convo : conds) {
      String field = convo.getFieldCode();
      String value = convo.getValue();
      if (SOItemKey.DBILLDATE.equals(field)) {
        UFDate startdate = null;
        String[] date = value.split(",");
        if (null != date[0] && !date[0].equals("ISNULL")) {
          startdate = TimeUtils.getStartDate(new UFDate(date[0]));
        }
        UFDate enddate = AppContext.getInstance().getBusiDate();
        if (null != date[1] && !date[1].equals("ISNULL")) {
          enddate = TimeUtils.getEndDate(new UFDate(date[1]));
        }

        if (null == startdate) {
          return true;
        }

        int days = UFDate.getDaysBetween(startdate, enddate);
        if (days > 31) {
          return true;
        }

      }

    }
    return false;
  }

}
