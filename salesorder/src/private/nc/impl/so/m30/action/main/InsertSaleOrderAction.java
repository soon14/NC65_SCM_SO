/**
 * 
 */
package nc.impl.so.m30.action.main;

import nc.bs.so.m30.maintain.InsertSaleOrderBP;
import nc.bs.so.m30.plugin.Action30PlugInPoint;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 新增保存动作
 * 
 * @author gdsjw
 * 
 */
public class InsertSaleOrderAction {
  public SaleOrderVO[] insert(SaleOrderVO[] bills) {

    AroundProcesser<SaleOrderVO> processer =
        new AroundProcesser<SaleOrderVO>(Action30PlugInPoint.InsertAction);

    TimeLog.logStart();
    processer.before(bills);

    TimeLog.info("调用新增保存前操作插入点"); /*-=notranslate=-*/

    TimeLog.logStart();
    InsertSaleOrderBP action = new InsertSaleOrderBP();
    SaleOrderVO[] vos = action.insert(bills);

    TimeLog.info("调用新增保存BP，进行保存"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(vos);

    TimeLog.info("调用新增保存后操作插入点"); /*-=notranslate=-*/

    return vos;
  }

}
