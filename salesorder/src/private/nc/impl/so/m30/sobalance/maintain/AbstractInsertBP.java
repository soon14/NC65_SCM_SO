/**
 * 
 */
package nc.impl.so.m30.sobalance.maintain;

import nc.bs.so.m30.plugin.BP30PlugInPoint;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * 新增保存BP
 * 
 * @author gdsjw
 */
public abstract class AbstractInsertBP implements IInsertBP {
  @Override
  public SoBalanceVO[] insert(SoBalanceVO[] bills) {
    AroundProcesser<SoBalanceVO> processer =
        new AroundProcesser<SoBalanceVO>(BP30PlugInPoint.SOBalanceInsertBP);

    // 注入点
    TimeLog.logStart();
    this.addBeforeRule(processer);
    processer.before(bills);

    TimeLog.info("调用新增保存前BP插入点"); /*-=notranslate=-*/

    TimeLog.logStart();
    BillInsert<SoBalanceVO> bo = new BillInsert<SoBalanceVO>();
    SoBalanceVO[] vos = bo.insert(bills);

    TimeLog.info("保存单据到数据库"); /*-=notranslate=-*/

    // 注入点
    TimeLog.logStart();
    this.addAfterRule(processer);
    processer.after(vos);

    TimeLog.info("调用新增保存后BP插入点"); /*-=notranslate=-*/

    return vos;
  }

  protected abstract void addBeforeRule(AroundProcesser<SoBalanceVO> processer);

  protected abstract void addAfterRule(AroundProcesser<SoBalanceVO> processer);

}
