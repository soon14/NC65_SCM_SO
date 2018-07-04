package nc.bs.so.m30.revise;

import nc.bs.so.m30.plugin.BP30PlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;

/**
 * 销售订单修订插入《销售订单修订表》InsertBP
 *
 * @version 6.0
 * @author 刘志伟
 * @time 2010-8-10 上午11:29:37
 */
public class InsertSaleOrderHistoryBP {
  public SaleOrderHistoryVO[] insert(SaleOrderHistoryVO[] bills) {
    AroundProcesser<SaleOrderHistoryVO> processer =
        new AroundProcesser<SaleOrderHistoryVO>(BP30PlugInPoint.ReviseInsertBP);

    // 注入点
    TimeLog.logStart();
    // this.addBeforeRule(processer);
    processer.before(bills);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0066")/*@res "调用新增保存前BP插入点"*/);

    TimeLog.logStart();
    BillInsert<SaleOrderHistoryVO> bo = new BillInsert<SaleOrderHistoryVO>();
    SaleOrderHistoryVO[] vos = bo.insert(bills);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0067")/*@res "保存单据到数据库"*/);

    // 注入点
    TimeLog.logStart();
    // this.addAfterRule(processer);
    processer.after(vos);

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0","04006011-0068")/*@res "调用新增保存后BP插入点"*/);

    return vos;
  }

}