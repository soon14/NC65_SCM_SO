package nc.impl.so.m30.sobalance.maintain;

import nc.bs.so.m30.plugin.BP30PlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * 修改保存动作
 * 
 * @author gdsjw
 */
public abstract class AbstractUpdateBP implements IUpdateBP {
  public AbstractUpdateBP() {
    //
  }

  @Override
  public SoBalanceVO[] update(SoBalanceVO[] bills, SoBalanceVO[] originBills) {
    CompareAroundProcesser<SoBalanceVO> processer =
        new CompareAroundProcesser<SoBalanceVO>(BP30PlugInPoint.SOBalanceUpdateBP);

    // 注入点
    TimeLog.logStart();
    this.addBeforeRule(processer);
    processer.before(bills, originBills);

    TimeLog.info("调用修改保存前BP插入点"); /*-=notranslate=-*/

    TimeLog.logStart();
    BillUpdate<SoBalanceVO> bo = new BillUpdate<SoBalanceVO>();
    SoBalanceVO[] vos = bo.update(bills, originBills);

    TimeLog.info("保存修改单据到数据库"); /*-=notranslate=-*/

    // 注入点
    TimeLog.logStart();
    this.addAfterRule(processer);
    processer.after(vos, originBills);

    TimeLog.info("调用修改保存后BP插入点"); /*-=notranslate=-*/

    return vos;
  }

  protected abstract void addBeforeRule(
      CompareAroundProcesser<SoBalanceVO> processer);

  protected abstract void addAfterRule(
      CompareAroundProcesser<SoBalanceVO> processer);

}
