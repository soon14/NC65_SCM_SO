package nc.vo.so.report.outsummary;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import nc.bs.scmpub.report.ReportQueryCondition;
import nc.bs.scmpub.report.ReportScaleProcess;
import nc.bs.scmpub.report.ReportScaleProcess2;
import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.itf.iufo.freereport.extend.ISubscribeConditionConvertor;
import nc.itf.iufo.freereport.extend.ISubscribeQueryCondition;
import nc.ui.querytemplate.querytree.QueryScheme;
import nc.vo.so.report.reportpub.ReportUIAdjust;
import nc.vo.so.report.reportpub.ReportUserObject;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.base.BaseSubscribeCondition;
import com.ufida.report.anareport.model.AbsAnaReportModel;

/**
 * 销售出库执行汇总报表订阅处理类
 * 
 * @since 6.3
 * @version 2012-10-18 13:35:44
 * @author 梁吉明
 */
public class OutSummaryConConvertor implements ISubscribeConditionConvertor {

  /**
   * 所有分组字段
   */
  private static final String[] ALLKEY = new String[] {
    OutSummaryViewVO.CSALEORGOID, OutSummaryViewVO.PK_ORG,
    OutSummaryViewVO.CBIZID, OutSummaryViewVO.CDPTID,
    OutSummaryViewVO.PK_CUSTCLASS, OutSummaryViewVO.PK_CUSTSALECLASS,
    OutSummaryViewVO.PK_AREACL, OutSummaryViewVO.CCUSTOMERID,
    OutSummaryViewVO.CINVOICECUSTID, OutSummaryViewVO.CRECEIEVEID,
    OutSummaryViewVO.PK_MARBASCLASS, OutSummaryViewVO.PK_MARSALECLASS,
    OutSummaryViewVO.VBATCHCODE, OutSummaryViewVO.CMATERIALOID,
    OutSummaryViewVO.FLARGESS, OutSummaryViewVO.CCHANNELTYPEID
  };

  /**
   * 所有汇总字段
   */
  public static final String[] AGGKEYS = new String[] {
    OutSummaryViewVO.NNUM, OutSummaryViewVO.NACCUMOUTSIGNNUM,
    OutSummaryViewVO.NACCUMOUTBACKNUM, OutSummaryViewVO.NACCUMWASTNUM,
    OutSummaryViewVO.NSIGNNUM, OutSummaryViewVO.NARNUM,
    OutSummaryViewVO.NORIGTAXMNY, OutSummaryViewVO.NINVOICEMNY,
    OutSummaryViewVO.NARMNY, OutSummaryViewVO.NARREMAINMNY,

  };

  /**
   * 数量字段
   */
  private static final String[] NUMKEY = new String[] {
    OutSummaryViewVO.NNUM, OutSummaryViewVO.NACCUMOUTSIGNNUM,
    OutSummaryViewVO.NACCUMOUTBACKNUM, OutSummaryViewVO.NACCUMWASTNUM,
    OutSummaryViewVO.NSIGNNUM, OutSummaryViewVO.NARNUM
  };

  /**
   * 金额字段
   */
  private static final String[] MNYKEY = new String[] {
    OutSummaryViewVO.NORIGTAXMNY, OutSummaryViewVO.NINVOICEMNY,
    OutSummaryViewVO.NARMNY, OutSummaryViewVO.NARREMAINMNY,
    OutSummaryViewVO.NPAYMNY
  };

  @Override
  public IQueryCondition getQueryCondition(
      ISubscribeQueryCondition subscribCondition, IContext context,
      AbsAnaReportModel reportModel) {
    if (null == subscribCondition) {
      return null;
    }

    BaseSubscribeCondition base = (BaseSubscribeCondition) subscribCondition;
    QueryScheme scheme = (QueryScheme) base.getScheme();

    // 1.设置报表筛选描述器为空
    ReportQueryCondition result = new ReportQueryCondition(true);
    // 2.精度处理
    ReportScaleProcess process = this.scaleProcess();
    result.setBusiFormat(process);

    // 格式调整
    ReportUIAdjust adjust = new ReportUIAdjust(OutSummaryConConvertor.ALLKEY);
    adjust.addSpecialHideRela(OutSummaryViewVO.CMATERIALOID,
        OutSummaryViewVO.CUNITID);
    result.setRoportAdjustor(adjust);

    ReportUserObject userobject = new ReportUserObject(scheme);
    result.setUserObject(userobject);

    // 分组、汇总字段
//    String[] groupkeys = this.getGroupKeys(userobject);
//    ReportAggDes descriptor =
//        new ReportAggDes(groupkeys, OutSummaryConConvertor.AGGKEYS);
//    result.setDescriptors(new Descriptor[] {
//      descriptor
//    });
    return result;
  }

  private ReportScaleProcess scaleProcess() {
    ReportScaleProcess2 process = new ReportScaleProcess2();

    // 金额处理
    process.setMnyDigits(OutSummaryViewVO.CORIGCURRENCYID,
        OutSummaryConConvertor.MNYKEY);
    // 合计处理
    process.setTotalFields(OutSummaryConConvertor.MNYKEY);
    process.setNumTotalFields(OutSummaryConConvertor.NUMKEY);
    return process;
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
}
