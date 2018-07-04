package nc.vo.so.report.invoicesummary;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.base.BaseSubscribeCondition;
import com.ufida.report.anareport.model.AbsAnaReportModel;

import nc.vo.so.report.reportpub.ReportAggDes;
import nc.vo.so.report.reportpub.ReportUIAdjust;
import nc.vo.so.report.reportpub.ReportUserObject;

import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.itf.iufo.freereport.extend.ISubscribeConditionConvertor;
import nc.itf.iufo.freereport.extend.ISubscribeQueryCondition;

import nc.pub.smart.model.descriptor.Descriptor;

import nc.bs.scmpub.report.ReportQueryCondition;
import nc.bs.scmpub.report.ReportScaleProcess;
import nc.bs.scmpub.report.ReportScaleProcess2;

import nc.ui.querytemplate.querytree.QueryScheme;

/**
 * 销售发票执行汇总报表订阅处理类
 * 
 * @since 6.3
 * @version 2012-10-18 13:24:05
 * @author 梁吉明
 */
public class InvSummaryConConvertor implements ISubscribeConditionConvertor {

  @Override
  public IQueryCondition getQueryCondition(
      ISubscribeQueryCondition subscribCondition, IContext context,
      AbsAnaReportModel reportModel) {
    BaseSubscribeCondition base = (BaseSubscribeCondition) subscribCondition;
    QueryScheme scheme = (QueryScheme) base.getScheme();

    // 1. 设置报表筛选描述器为空
    ReportQueryCondition result = new ReportQueryCondition(true);
    // 2.精度处理
    ReportScaleProcess process = this.scaleProcess();
    result.setBusiFormat(process);
    // 3.自由报表格式调整
    ReportUIAdjust adjust = new ReportUIAdjust(InvSummaryConConvertor.ALLKEYS);
    adjust.addSpecialHideRela(InvSummaryViewVO.CMATERIALID,
        InvSummaryViewVO.CUNITID);
    result.setRoportAdjustor(adjust);
    // 4.用户前台设置信息
    ReportUserObject userobject = new ReportUserObject(scheme);
    result.setUserObject(userobject);
    /*  // 5.汇总字段
    String[] groupkeys = this.getGroupKeys(userobject);
    ReportAggDes descriptor =
        new ReportAggDes(groupkeys, InvSummaryConConvertor.AGGKEYS);
    result.setDescriptors(new Descriptor[] {
      descriptor
    });*/

    return result;
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

  private ReportScaleProcess scaleProcess() {
    ReportScaleProcess2 process = new ReportScaleProcess2();
    // 金额处理
    process.setMnyDigits(InvSummaryViewVO.CORIGCURRENCYID,
        InvSummaryConConvertor.MNYKEYS);
    // 合计处理
    process.setTotalFields(InvSummaryConConvertor.MNYKEYS);
    process.setNumTotalFields(InvSummaryConConvertor.NUMKEY);
    
    process.setPriceDigits(new String[] {
      InvSummaryViewVO.NORIGTAXNETPRICE
    }, InvSummaryViewVO.CORIGCURRENCYID);
    return process;
  }

  /**
   * 所有汇总字段
   */
  public static final String[] AGGKEYS = new String[] {
    InvSummaryViewVO.NNUM, InvSummaryViewVO.NORIGMNY,
    InvSummaryViewVO.NORIGTAXMNY, InvSummaryViewVO.NTAX,
    InvSummaryViewVO.NORIGDISCOUNT, InvSummaryViewVO.NSHOULDRECEIVNUM,
    InvSummaryViewVO.NSHOULDRECEIVMNY, InvSummaryViewVO.NTOTALRECEIVMNY
  };

  /**
   * 所有分组字段
   */
  private static final String[] ALLKEYS = new String[] {
    InvSummaryViewVO.PK_ORG, InvSummaryViewVO.CSALEORGID,
    InvSummaryViewVO.CSENDSTOCKORGID, InvSummaryViewVO.CDEPTID,
    InvSummaryViewVO.CEMPLOYEEID, InvSummaryViewVO.CCHANNELTYPEID,
    InvSummaryViewVO.PK_CUSTCLASS, InvSummaryViewVO.PK_CUSTSALECLASS,
    InvSummaryViewVO.PK_AREACL, InvSummaryViewVO.CORDERCUSTID,
    InvSummaryViewVO.CINVOICECUSTID, InvSummaryViewVO.PK_MARBASCLASS,
    InvSummaryViewVO.PK_MARSALECLASS, InvSummaryViewVO.CMATERIALID,
    InvSummaryViewVO.VBATCHCODE, InvSummaryViewVO.CTRANTYPEID,
    InvSummaryViewVO.BLARGESSFLAG, InvSummaryViewVO.VFIRSTTRANTYPE
  };

  /**
   * 金额字段
   */
  private static final String[] MNYKEYS = new String[] {
    InvSummaryViewVO.NORIGMNY, InvSummaryViewVO.NORIGTAXMNY,
    InvSummaryViewVO.NTAX, InvSummaryViewVO.NORIGDISCOUNT,
    InvSummaryViewVO.NSHOULDRECEIVMNY, InvSummaryViewVO.NTOTALRECEIVMNY,
    InvSummaryViewVO.RECEIVMNY
  };

  /**
   * 数量字段
   */
  private static final String[] NUMKEY = new String[] {
    InvSummaryViewVO.NNUM, InvSummaryViewVO.NSHOULDRECEIVNUM
  };

  /**
   * 合计字段
   */
  private static final String[] TOTALKEYS = new String[] {
    InvSummaryViewVO.NNUM, InvSummaryViewVO.NORIGMNY,
    InvSummaryViewVO.NORIGTAXMNY, InvSummaryViewVO.NORIGDISCOUNT,
    InvSummaryViewVO.NSHOULDRECEIVMNY, InvSummaryViewVO.NSHOULDRECEIVMNY,
    InvSummaryViewVO.NTOTALRECEIVMNY
  };
}
