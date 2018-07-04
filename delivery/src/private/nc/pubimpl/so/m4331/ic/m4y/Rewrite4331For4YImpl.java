package nc.pubimpl.so.m4331.ic.m4y;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m4331.plugin.ServicePlugInPoint;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubimpl.so.m4331.ic.rule.RenovateOutInfoRule;
import nc.pubimpl.so.m4331.ic.rule.ToleranceCheck;
import nc.pubimpl.so.m4331.pub.RewriteVOUtil;
import nc.pubitf.so.m4331.ic.m4y.IRewrite4331For4Y;
import nc.pubitf.so.m4331.ic.m4y.RewritePara4331For4Y;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * 调拨出库单回写发货单累计出库数量
 * 
 * @author 祝会征
 * @since 6.0
 * @time 2010-01-28 下午13:49:07
 */
public class Rewrite4331For4YImpl implements IRewrite4331For4Y {

  // 要更新的发货单子表字段
  private final String[] keys = new String[] {
      DeliveryBVO.NTOTALOUTNUM, DeliveryBVO.NTOTALNOTOUTNUM
  };

  private RewriteVOUtil util;

  @Override
  public void rewrite4331OutNumFor4Y(RewritePara4331For4Y[] paras)
      throws BusinessException {
    try {
      this.rewrite(paras);
    }
    catch (RuntimeException ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  private void addRule() throws BusinessException {
    // 检查是否允许超发货单出库
    ToleranceCheck rule = new ToleranceCheck();
    rule.examOverToleranceSaveBusi(this.util,null);

    // 更新发货单质检信息和发货单信息
    RenovateOutInfoRule renovate = new RenovateOutInfoRule(this.util,null);
    renovate.renovateState();
    //更新发货单状态
    renovate.updateToDB();

  }

  private String[] getRewriteIDS(Map<String, RewritePara4331For4Y> index) {
    int size = index.size();
    String[] bids = new String[size];
    bids = index.keySet().toArray(bids);
    return bids;
  }

  /*
   * 获得回写view信息
   */
  private DeliveryViewVO[] getRewriteInfos(
      Map<String, RewritePara4331For4Y> index) {
    String[] ids = this.getRewriteIDS(index);
    this.util = new RewriteVOUtil(ids);
    return this.util.getAllRewriteViewVO();
  }

  private Map<String, RewritePara4331For4Y> prepareParams(
      RewritePara4331For4Y[] paras) {
    Map<String, RewritePara4331For4Y> index =
        new HashMap<String, RewritePara4331For4Y>();
    for (RewritePara4331For4Y para : paras) {
      index.put(para.getCdeliverybid(), para);
    }
    return index;
  }

  /*
   * 回写发货单
   * @param paras
   * @throws BusinessException
   */
  private void rewrite(RewritePara4331For4Y[] paras) throws BusinessException {
    TimeLog.logStart();
    Map<String, RewritePara4331For4Y> index = this.prepareParams(paras);
    // 此处设置session变量，以避免程序到处传递
    BSContext.getInstance().setSession(RewritePara4331For4Y.class.getName(),
        index);
    TimeLog.info("并处理参数"); /*-=notranslate=-*/
    TimeLog.logStart();
    DeliveryViewVO[] views = this.getRewriteInfos(index);
    TimeLog.info("获得回写视图vo"); /*-=notranslate=-*/
    AroundProcesser<DeliveryViewVO> processer =
        new AroundProcesser<DeliveryViewVO>(
            ServicePlugInPoint.rewrite4331OutNumFor4Y);
    this.addRule();
    TimeLog.logStart();
    processer.before(views);
    TimeLog.info("写数据库前执行业务规则"); /*-=notranslate=-*/
    TimeLog.logStart();
    ViewUpdate<DeliveryViewVO> bo = new ViewUpdate<DeliveryViewVO>();
    views = bo.update(views, DeliveryBVO.class, this.keys);
    TimeLog.info("更新数据库"); /*-=notranslate=-*/
    TimeLog.logStart();
    processer.after(views);
    TimeLog.info("写数据库后执行业务规则"); /*-=notranslate=-*/

    // 此处释放session变量，以免浪费内存
    BSContext.getInstance().removeSession(RewritePara4331For4Y.class.getName());
  }
}
