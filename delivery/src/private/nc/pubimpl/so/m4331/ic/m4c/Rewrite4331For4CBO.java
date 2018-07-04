package nc.pubimpl.so.m4331.ic.m4c;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m4331.maintain.rule.credit.RenovateARByBidsBeginRule;
import nc.bs.so.m4331.maintain.rule.credit.RenovateARByBidsEndRule;
import nc.bs.so.m4331.plugin.ServicePlugInPoint;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubimpl.so.m4331.ic.rule.RenovateOutInfoRule;
import nc.pubimpl.so.m4331.ic.rule.ToleranceCheck;
import nc.pubimpl.so.m4331.pub.RewriteVOUtil;
import nc.pubimpl.so.m4331.pub.RewriteValueUtil;
import nc.pubitf.so.m4331.ic.m4c.RewritePara4331For4C;
import nc.vo.credit.engrossmaintain.pub.action.M4331EngrossAction;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * 销售出库单回写发货单累计出库数量
 * 
 * @author 祝会征
 * @since 6.0
 * @time 2010-01-28 下午13:49:07
 */
public class Rewrite4331For4CBO {

  // 要更新的发货单子表字段
  private final String[] keys = new String[] {
    DeliveryBVO.NTOTALOUTNUM, DeliveryBVO.NTOTALNOTOUTNUM
  };

  // 处理要回写的行及质检行信息
  private RewriteVOUtil util;

  // 处理回写的值信息
  private RewriteValueUtil valueutil;

  public void rewrite4331OutNumFor4C(RewritePara4331For4C[] paras)
      throws BusinessException {
    this.rewrite(paras);
  }

  private void addRule(AroundProcesser<DeliveryViewVO> processer) {

    // 销售出库回写发货单前信用占用检查
    processer.addBeforeRule(new RenovateARByBidsBeginRule(
        M4331EngrossAction.M4331OutReWrite));

    // 销售出库回写发货单后信用占用检查
    processer.addAfterRule(new RenovateARByBidsEndRule(
        M4331EngrossAction.M4331OutReWrite));
  }

  private UFDouble GetNoNullDouble(UFDouble value) {
    if (value == null) {
      return UFDouble.ZERO_DBL;
    }

    return value;

  }

  /* 加锁 */
  private String[] getRewriteIDS(Map<String, RewritePara4331For4C> index) {
    int size = index.size();
    String[] bids = new String[size];
    bids = index.keySet().toArray(bids);
    return bids;
  }

  /* 获得回写view信息 */
  private DeliveryViewVO[] getRewriteInfos(
      Map<String, RewritePara4331For4C> index,
      Map<String, RewritePara4331For4C> checkindex) {
    String[] ids = this.getRewriteIDS(index);
    String[] checkids = this.getRewriteIDS(checkindex);
    this.util = new RewriteVOUtil(ids, checkids);
    return this.util.getAllRewriteViewVO();
  }

  private Map<String, RewritePara4331For4C> prepareCheckParams(
      RewritePara4331For4C[] paras) {
    Map<String, RewritePara4331For4C> index =
        new HashMap<String, RewritePara4331For4C>();
    for (RewritePara4331For4C para : paras) {
      if (para.getCdeliverybbid() != null) {
        index.put(para.getCdeliverybbid(), para);
      }
    }
    return index;
  }

  private Map<String, RewritePara4331For4C> prepareParams(
      RewritePara4331For4C[] paras) {
    Map<String, RewritePara4331For4C> index =
        new HashMap<String, RewritePara4331For4C>();
    for (RewritePara4331For4C para : paras) {
      if (!index.containsKey(para.getCdeliverybid())) {
        index.put(para.getCdeliverybid(), para);
      }
      else {
        String key = para.getCdeliverybid();
        UFDouble outnum = this.GetNoNullDouble(index.get(key).getOutnum());
        UFDouble nooutnum = this.GetNoNullDouble(index.get(key).getNoOutnum());

        outnum = outnum.add(this.GetNoNullDouble(para.getOutnum()));
        nooutnum = nooutnum.add(this.GetNoNullDouble(para.getNoOutnum()));
        RewritePara4331For4C newpara =
            new RewritePara4331For4C(para.getCdeliverybid(),
                para.getCdeliverybbid(), outnum, nooutnum);
        index.remove(key);
        index.put(key, newpara);
      }
    }
    return index;
  }

  /* 回写发货单
   * 
   * @param paras
   * 
   * @throws BusinessException */
  private void rewrite(RewritePara4331For4C[] paras) throws BusinessException {
    TimeLog.logStart();
    // 回写的行信息
    Map<String, RewritePara4331For4C> index = this.prepareParams(paras);
    // 回写质检行信息
    Map<String, RewritePara4331For4C> checkIndex =
        this.prepareCheckParams(paras);
    // 如果有质检信息 则要将缓存的来源信息设置为质检行信息
    if (checkIndex.size() > 0) {
      this.valueutil = new RewriteValueUtil(checkIndex);
    }

    // 此处设置session变量，以避免程序到处传递
    BSContext.getInstance().setSession(RewritePara4331For4C.class.getName(),
        index);
    TimeLog.info("并处理参数"); /* -=notranslate=- */
    TimeLog.logStart();

    // 获得要回写的行信息
    DeliveryViewVO[] views = this.getRewriteInfos(index, checkIndex);

    TimeLog.info("获得回写视图vo"); /* -=notranslate=- */
    AroundProcesser<DeliveryViewVO> processer =
        new AroundProcesser<DeliveryViewVO>(
            ServicePlugInPoint.rewrite4331outNumFor4C);

    // 检查是否允许超发货单出库
    ToleranceCheck rule = new ToleranceCheck();
    rule.examOverToleranceSaveBusi(this.util, this.valueutil);

    this.addRule(processer);
    TimeLog.logStart();

    TimeLog.info("写数据库前执行业务规则"); /* -=notranslate=- */
    TimeLog.logStart();

    // 更新发货单质检信息和发货单信息
    RenovateOutInfoRule renovate =
        new RenovateOutInfoRule(this.util, this.valueutil);
    renovate.renovateState();

    processer.before(views);
    ViewUpdate<DeliveryViewVO> bo = new ViewUpdate<DeliveryViewVO>();
    views = bo.update(views, DeliveryBVO.class, this.keys);

    TimeLog.info("更新数据库"); /* -=notranslate=- */
    TimeLog.logStart();
    processer.after(views);
    TimeLog.info("写数据库后执行业务规则"); /* -=notranslate=- */

    // 更新发货单状态
    renovate.updateToDB();
    // 此处释放session变量，以免浪费内存
    BSContext.getInstance().removeSession(RewritePara4331For4C.class.getName());
  }
}
