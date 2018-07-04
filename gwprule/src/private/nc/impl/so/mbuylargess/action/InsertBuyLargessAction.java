package nc.impl.so.mbuylargess.action;

import nc.bs.so.buylargess.maintain.InsertMblargessInBP;
import nc.bs.so.buylargess.plugin.ActionMblargessPlugInPoint;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;

/**
 * Created on 2010-1-12
 * <p>
 * Title: NC56
 * </p>
 * <p>
 * Description: [买赠设置新增操作]
 * </p>
 * <p>
 * Copyright: ufida
 * </p>
 * <p>
 * Company: NC供应链销售管理
 * </p>
 * <p>
 * Department: 供应链开发小组
 * </p>
 * 
 * @author 祝会征[祝会征@ufida.com.cn]
 * @version 1.0
 */
public class InsertBuyLargessAction {
  public BuyLargessVO[] insertBuylargess(BuyLargessVO[] bills) {

    TimeLog.logStart();
    BillTransferTool<BuyLargessVO> transferTool =
        new BillTransferTool<BuyLargessVO>(bills);
    TimeLog.info("保存前台VO，组织返回值时使用"); /*-=notranslate=-*/

    AroundProcesser<BuyLargessVO> processer =
        new AroundProcesser<BuyLargessVO>(
            ActionMblargessPlugInPoint.InsertBuyLargessAction);

    TimeLog.logStart();
    processer.before(bills);
    TimeLog.info("调用新增保存BP前执行业务规则"); /*-=notranslate=-*/

    TimeLog.logStart();
    InsertMblargessInBP action = new InsertMblargessInBP();
    BuyLargessVO[] vos = action.insert(bills);
    TimeLog.info("调用新增保存BP，进行保存"); /*-=notranslate=-*/

    TimeLog.logStart();
    vos = transferTool.getBillForToClient(vos);
    TimeLog.info("组织返回值,返回轻量级VO"); /*-=notranslate=-*/

    TimeLog.logStart();
    TimeLog.info("业务日志"); /*-=notranslate=-*/
    return vos;
  }
}
