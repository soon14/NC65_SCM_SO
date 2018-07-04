package nc.vo.so.report.ordersummary;

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
 * 销售订单执行汇总报表订阅处理类
 * 
 * @since 6.3
 * @version 2012-10-18 13:33:55
 * @author 梁吉明
 */
public class OrderSummaryConConvertor implements ISubscribeConditionConvertor {

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
    ReportUIAdjust adjust =
        new ReportUIAdjust(OrderSummaryConConvertor.ALLKEYS);
    adjust.addSpecialHideRela(OrderSummaryViewVO.CMATERIALID,
        OrderSummaryViewVO.CUNITID);
    result.setRoportAdjustor(adjust);
    // 4.用户前台设置信息
    ReportUserObject userobject = new ReportUserObject(scheme);
    result.setUserObject(userobject);
    // 5.汇总字段
    // String[] groupkeys = this.getGroupKeys(userobject);
    // ReportAggDes descriptor =
    // new ReportAggDes(groupkeys, OrderSummaryConConvertor.AGGKEYS);
    // result.setDescriptors(new Descriptor[] {
    // descriptor
    // });

    return result;
  }

  private String[] getGroupKeys(ReportUserObject userobject) {
    List<String> listgroup = new ArrayList<String>();
    Set<String> selgroups = userobject.getSummaryKeys();
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

  private ReportScaleProcess scaleProcess() {
    ReportScaleProcess2 process = new ReportScaleProcess2();

    // 金额处理
    process.setMnyDigits(OrderSummaryViewVO.CORIGCURRENCYID,
        OrderSummaryConConvertor.MNYKEYS);
    // 合计处理
    process.setTotalFields(OrderSummaryConConvertor.MNYKEYS);
    process.setNumTotalFields(OrderSummaryConConvertor.NUMKEY);
    // 单价处理
    process.setCostPriceDigits(OrderSummaryConConvertor.PRICEKEY);
    process.setPriceDigits(new String[] {
      OrderSummaryViewVO.NORIGTAXNETPRICE
    }, OrderSummaryViewVO.CORIGCURRENCYID);
    return process;
  }

  /**
   * 所有汇总字段
   */
  public static final String[] AGGKEYS = new String[] {
    OrderSummaryViewVO.NNUM, OrderSummaryViewVO.NORIGMNY,
    OrderSummaryViewVO.NORIGTAXMNY, OrderSummaryViewVO.NTAX,
    OrderSummaryViewVO.NORIGDISCOUNT, OrderSummaryViewVO.NOUTNUM,
    OrderSummaryViewVO.NOUTSIGNNUM, OrderSummaryViewVO.NNORWASTNUM,
    OrderSummaryViewVO.NWAITOUTNUM, OrderSummaryViewVO.NINVOICENUM,
    OrderSummaryViewVO.NINVOICEORIGTAXMNY, OrderSummaryViewVO.NSHOULDRECEIVNUM,
    OrderSummaryViewVO.NSHOULDRECEIVMNY, OrderSummaryViewVO.NTOTALRECEIVMNY,
    OrderSummaryViewVO.NTOTALCOSTMNY
  };

  /**
   * 所有分组字段
   */
  private static final String[] ALLKEYS = new String[] {
    OrderSummaryViewVO.PK_ORG, OrderSummaryViewVO.CSENDSTOCKORGID,
    OrderSummaryViewVO.CDEPTID, OrderSummaryViewVO.CEMPLOYEEID,
    OrderSummaryViewVO.CCHANNELTYPEID, OrderSummaryViewVO.PK_CUSTCLASS,
    OrderSummaryViewVO.PK_CUSTSALECLASS, OrderSummaryViewVO.CCUSTOMERID,
    OrderSummaryViewVO.PK_AREACL, OrderSummaryViewVO.PK_MARBASCLASS,
    OrderSummaryViewVO.PK_MARSALECLASS, OrderSummaryViewVO.CTRANTYPEID,
    OrderSummaryViewVO.CMATERIALID, OrderSummaryViewVO.VBATCHCODE,
    OrderSummaryViewVO.BLARGESSFLAG
  };

  /**
   * 金额字段
   */
  private static final String[] MNYKEYS = new String[] {
    OrderSummaryViewVO.NORIGMNY, OrderSummaryViewVO.NORIGTAXMNY,
    OrderSummaryViewVO.NTAX, OrderSummaryViewVO.NORIGDISCOUNT,
    OrderSummaryViewVO.NINVOICEORIGTAXMNY, OrderSummaryViewVO.NSHOULDRECEIVMNY,
    OrderSummaryViewVO.NTOTALRECEIVMNY, OrderSummaryViewVO.NTOTALCOSTMNY,
    OrderSummaryViewVO.NPAYMNY
  };

  /**
   * 数量字段
   */
  private static final String[] NUMKEY = new String[] {
    OrderSummaryViewVO.NNUM, OrderSummaryViewVO.NSHOULDRECEIVNUM,
    OrderSummaryViewVO.NOUTNUM, OrderSummaryViewVO.NOUTSIGNNUM,
    OrderSummaryViewVO.NNORWASTNUM, OrderSummaryViewVO.NWAITOUTNUM,
    OrderSummaryViewVO.NINVOICENUM,
  };

  /**
   * 单价
   */
  private static final String[] PRICEKEY = new String[] {
    OrderSummaryViewVO.NCOSTPRICE
  };

}
