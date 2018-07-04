package nc.impl.so.m38.action;

import nc.bs.so.m38.maintain.UpdatePreOrderBP;
import nc.bs.so.m38.plugin.ActionPlugInPoint;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m38.entity.PreOrderVO;

/**
 * 预订单修改保存动作
 * 
 * @author 刘志伟
 */
public class UpdatePreOrderAction {

  public PreOrderVO[] update(PreOrderVO[] bills, PreOrderVO[] originBills) {

    PreOrderVO[] newBills = bills;

    TimeLog.logStart();
    TimeLog.info("检查参照38的时间戳、锁来源"); /*-=notranslate=-*/

    CompareAroundProcesser<PreOrderVO> compareProcesser =
        new CompareAroundProcesser<PreOrderVO>(ActionPlugInPoint.UpdateAction);

    TimeLog.logStart();
    compareProcesser.before(newBills, originBills);
    TimeLog.info("调用修改保存前操作插入点"); /*-=notranslate=-*/

    UpdatePreOrderBP action = new UpdatePreOrderBP();
    PreOrderVO[] ret = action.update(newBills, originBills);

    TimeLog.logStart();
    compareProcesser.after(ret, originBills);
    TimeLog.info("调用修改保存后操作插入点"); /*-=notranslate=-*/

    return ret;
  }
}
