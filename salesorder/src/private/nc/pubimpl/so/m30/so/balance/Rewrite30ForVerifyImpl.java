package nc.pubimpl.so.m30.so.balance;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m30.plugin.ServicePlugInPoint;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubimpl.so.m30.so.balance.rule.Rewrite30SetTotalPayMnyRule;
import nc.pubitf.so.m30.so.balance.IRewrite30ForVerify;
import nc.pubitf.so.m30.so.balance.RewriteVerifyPara;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m30.entity.SaleOrderBVO;

public class Rewrite30ForVerifyImpl implements IRewrite30ForVerify {

  @Override
  public void rewrite30TotalPayMnyVerifyListener(RewriteVerifyPara[] paras)
      throws BusinessException {
    try {
      this.rewrite30TotalPayMny(paras);
    }
    catch (RuntimeException ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  private void rewrite30TotalPayMny(RewriteVerifyPara[] paras) {
    Map<String, RewriteVerifyPara> index = this.prepareParams(paras);
    // 此处设置session变量，以避免程序到处传递
    BSContext.getInstance()
        .setSession(RewriteVerifyPara.class.getName(), index);
    TimeLog.info("并处理参数"); /*-=notranslate=-*/

    TimeLog.logStart();
    SaleOrderBVO[] bodys = this.query(index);
    TimeLog.info("查询销售订单表体"); /*-=notranslate=-*/

    AroundProcesser<SaleOrderBVO> processer =
        new AroundProcesser<SaleOrderBVO>(
            ServicePlugInPoint.rewrite30TotalPayMnyForVerifyListener);
    this.addRuleForReceivedMny(processer);

    TimeLog.logStart();
    processer.before(bodys);
    TimeLog.info("写数据库前执行业务规则"); /*-=notranslate=-*/

    TimeLog.logStart();
    String[] names = new String[] {
      SaleOrderBVO.NTOTALPAYMNY
    };
    VOUpdate<SaleOrderBVO> voUpate = new VOUpdate<SaleOrderBVO>();
    bodys = voUpate.update(bodys, names);
    TimeLog.info("更新数据库"); /*-=notranslate=-*/

    TimeLog.logStart();
    processer.after(bodys);
    TimeLog.info("写数据库后执行业务规则"); /*-=notranslate=-*/

    // 此处释放session变量，以免浪费内存
    BSContext.getInstance().removeSession(RewriteVerifyPara.class.getName());
  }

  private void addRuleForReceivedMny(AroundProcesser<SaleOrderBVO> processer) {
    // 设置累计财务核销金额
    processer.addBeforeRule(new Rewrite30SetTotalPayMnyRule());
  }

  private SaleOrderBVO[] query(Map<String, RewriteVerifyPara> index) {
    String[] ids = this.lockBills(index);
    VOQuery<SaleOrderBVO> voQuery =
        new VOQuery<SaleOrderBVO>(SaleOrderBVO.class);

    SaleOrderBVO[] bodys = voQuery.query(ids);
    if (bodys.length != index.size()) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
              "04006011-0171")/*@res "出现并发，请重新查询销售订单"*/;
      ExceptionUtils.wrappBusinessException(message);
    }
    return bodys;
  }

  private String[] lockBills(Map<String, RewriteVerifyPara> index) {
    int size = index.size();
    String[] ids = new String[size];
    ids = index.keySet().toArray(ids);
    LockOperator locker = new LockOperator();
    String message =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
            "04006011-0185")/*@res "订单核销回写销售订单实际收款金额，锁销售订单表头失败"*/;
    locker.lock(ids, message);
    return ids;
  }

  private Map<String, RewriteVerifyPara> prepareParams(RewriteVerifyPara[] paras) {
    Map<String, RewriteVerifyPara> index =
        new HashMap<String, RewriteVerifyPara>();
    for (RewriteVerifyPara para : paras) {
      index.put(para.getCsaleorderbid(), para);
    }
    return index;
  }

}
