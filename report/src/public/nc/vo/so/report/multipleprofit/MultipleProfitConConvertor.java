package nc.vo.so.report.multipleprofit;

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
 * 报表订阅处理类
 * 
 * @since 6.3
 * @version 2012-10-22 10:30:37
 * @author zhangkai4
 */
public class MultipleProfitConConvertor implements ISubscribeConditionConvertor {

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
        new ReportUIAdjust(MultipleProfitViewMeta.GROUPKEYS);
    adjust.addSpecialHideRela(MultipleProfitViewVO.CMATERIALID,
        MultipleProfitViewVO.CUNITID);
    result.setRoportAdjustor(adjust);
    // 4.用户前台设置信息
    ReportUserObject userobject = new ReportUserObject(scheme);
    result.setUserObject(userobject);
    // 5.汇总字段
//    String[] groupkeys = this.getGroupKeys(userobject);
//    ReportAggDes descriptor =
//        new ReportAggDes(groupkeys, MultipleProfitViewMeta.AGGKEYS);
//    result.setDescriptors(new Descriptor[] {
//      descriptor
//    });

    return result;
  }

  private ReportScaleProcess scaleProcess() {
    ReportScaleProcess2 process = new ReportScaleProcess2();

    // 金额处理
    process.setMnyDigits(MultipleProfitViewVO.CORIGCURRENCYID,
        MultipleProfitViewMeta.MNYKEYS);
    // 合计处理
    process.setTotalFields(MultipleProfitViewMeta.MNYKEYS);
    process.setNumTotalFields(MultipleProfitViewMeta.NUMKEY);
    // 无税单价处理
    process.setPriceDigits(new String[] {
      MultipleProfitViewVO.NTOTALRECEIVEPRICE
    }, MultipleProfitViewVO.CORIGCURRENCYID);

    // 成本单价
    process.setCostPriceDigits(new String[] {
      MultipleProfitViewVO.NCOSTPRICE
    });
    return process;
  }

  /**
   * 获得分组字段
   * 
   * @param userobject
   * @return
   */
  private String[] getGroupKeys(ReportUserObject userobject) {
    List<String> listgroup = new ArrayList<String>();
    Set<String> selgroups = userobject.getSummaryKeys();
    for (String key : selgroups) {
      listgroup.add(key);
      if (MultipleProfitViewVO.CMATERIALID.equals(key)) {
        listgroup.add(MultipleProfitViewVO.CUNITID);
      }
    }
    listgroup.add(MultipleProfitViewVO.CORIGCURRENCYID);
    String[] groupkeys = new String[listgroup.size()];
    listgroup.toArray(groupkeys);
    return groupkeys;
  }

}
