package nc.pubimpl.so.m32.ic.m4c;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceViewVO;

import nc.pubitf.so.m32.ic.m4c.IRewrite32For4C;
import nc.pubitf.so.m32.ic.m4c.RewritePara32For4C;

import nc.bs.so.m32.plugin.ServicePlugInPoint;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

import nc.pubimpl.so.m32.ic.m4c.rule.RewriteOutNumRule;

/**
 * 销售发票提供给销售出库单的回写接口实现类
 * 
 * @since 6.3
 * @version 2012-12-21 上午10:37:33
 * @author yaogj
 */
public class Rewrite32For4CImpl implements IRewrite32For4C {

  @Override
  public void rewrite32OutNumFor4C(RewritePara32For4C[] paras)
      throws BusinessException {

    TimeLog.logStart();
    Map<String, RewritePara32For4C> mappara = this.prepareParams(paras);
    // 此处设置session变量，以避免程序到处传递
    BSContext.getInstance().setSession(RewritePara32For4C.class.getName(),
        mappara);

    TimeLog.info("合并处理参数"); /*-=notranslate=-*/

    TimeLog.logStart();
    SaleInvoiceViewVO[] views = this.queryViewVO(mappara);
    /*-=notranslate=-*/
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0055")/*@res "查询销售发票表体"*/);

    AroundProcesser<SaleInvoiceViewVO> processer =
        new AroundProcesser<SaleInvoiceViewVO>(
            ServicePlugInPoint.rewrite32OutNumFor4C);

    this.addRule(processer);

    TimeLog.logStart();
    processer.before(views);
    /*-=notranslate=-*/
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0056")/*@res "写数据库前执行业务规则"*/);

    TimeLog.logStart();
    String[] updatekeys = new String[] {
      SaleInvoiceBVO.NSHOULDOUTNUM, SaleInvoiceBVO.NTOTALOUTNUM
    };

    ViewUpdate<SaleInvoiceViewVO> bo = new ViewUpdate<SaleInvoiceViewVO>();
    views = bo.update(views, SaleInvoiceBVO.class, updatekeys);
    /*-=notranslate=-*/
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0057")/*@res "更新数据库"*/);

    TimeLog.logStart();
    processer.after(views);
    /*-=notranslate=-*/
    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006008_0", "04006008-0058")/*@res "写数据库后执行业务规则"*/);

    // 此处释放session变量，以免浪费内存
    BSContext.getInstance().removeSession(RewritePara32For4C.class.getName());

  }

  /**
   * 方法功能描述：添加回写规则。
   * <p>
   * <b>参数说明</b>
   * 
   * @param processer
   *          <p>
   * @author fengjb
   * @time 2010-5-11 下午01:39:43
   */
  private void addRule(AroundProcesser<SaleInvoiceViewVO> processer) {
    IRule<SaleInvoiceViewVO> rule = new RewriteOutNumRule();
    processer.addBeforeRule(rule);
  }

  /**
   * 方法功能描述：下游销售出库单会写销售发票时对设计到的发票加锁。
   * <p>
   * <b>参数说明</b>
   * 
   * @param mappara
   * @return <p>
   * @author 冯加滨
   * @time 2010-3-24 上午11:25:31
   */
  private String[] lockBills(Map<String, RewritePara32For4C> mappara) {

    String[] invoicebids = mappara.keySet().toArray(new String[0]);
    LockOperator locker = new LockOperator();
    String message =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006008_0",
            "04006008-0059")/*@res "销售出库单回写销售发票累计出库数量，对发票表体加锁失败"*/;
    locker.lock(invoicebids, message);
    return invoicebids;

  }

  /**
   * 方法功能描述：预处理回写参数。
   * <p>
   * <b>参数说明</b>
   * 
   * @param paras
   * @return <p>
   * @author 冯加滨
   * @time 2010-3-24 上午09:38:59
   */
  private Map<String, RewritePara32For4C> prepareParams(
      RewritePara32For4C[] paras) {

    Map<String, RewritePara32For4C> hmpara =
        new HashMap<String, RewritePara32For4C>();

    for (RewritePara32For4C para : paras) {
      hmpara.put(para.getCsaleinvoicebid(), para);
    }
    return hmpara;

  }

  /**
   * 方法功能描述：查询视图VO。
   * <p>
   * <b>参数说明</b>
   * 
   * @param mappara
   * @return <p>
   * @author fengjb
   * @time 2010-5-11 下午01:40:10
   */
  private SaleInvoiceViewVO[] queryViewVO(
      Map<String, RewritePara32For4C> mappara) {

    String[] invoicebids = this.lockBills(mappara);
    ViewQuery<SaleInvoiceViewVO> bo =
        new ViewQuery<SaleInvoiceViewVO>(SaleInvoiceViewVO.class);
    bo.setSharedHead(true);

    SaleInvoiceViewVO[] views = bo.query(invoicebids);
    if (views.length != mappara.size()) {
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006008_0",
              "04006008-0060")/*@res "存在并发操作，请重新查询销售订单"*/;
      ExceptionUtils.wrappBusinessException(message);
    }
    return views;

  }

}
