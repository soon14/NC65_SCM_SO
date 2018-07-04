package nc.pubimpl.so.m4331.qc.mc001;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.pubimpl.so.m4331.qc.mc001.rule.CheckEnableReportRule;
import nc.pubimpl.so.m4331.qc.mc001.rule.DeleteQualityDataRule;
import nc.pubimpl.so.m4331.qc.mc001.rule.FillReportCheckDataRule;
import nc.pubitf.so.m4331.qc.mc001.IRewrite4331ForC001;
import nc.pubitf.so.m4331.qc.mc001.RewritePara4331ForC001;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * 报检单回写发货单累计报检数量和是否报检标识
 *
 * @author 祝会征
 * @since 6.0
 * @time 2010-01-28 下午13:49:07
 */
public class Rewrite4331ForC001Impl implements IRewrite4331ForC001 {

  // 报检时，需要处理的发货单子表数据信息
  // 是否报检 是否质检 是否根据质检结果入库
  // 累计报检数量 累计合格数量 累计不合格数量
  private static String[] keys = new String[] {
    DeliveryBVO.BCHECKFLAG, DeliveryBVO.BQUALITYFLAG,
    DeliveryBVO.BUSECHECKFLAG, DeliveryBVO.NTOTALREPORTNUM,
    DeliveryBVO.NTOTALELIGNUM, DeliveryBVO.NTOTALUNELIGNUM
  };

  @Override
  public void rewriteForC001(RewritePara4331ForC001[] paras)
      throws BusinessException {
    try {
      this.rewrite(paras);
    }
    catch (RuntimeException ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  /*
   * 报检回写发货单前处理规则
   * @param index
   * @param views
   */
  private void addRule(Map<String, RewritePara4331ForC001> index,
      DeliveryViewVO[] views) {
    CheckEnableReportRule check = new CheckEnableReportRule();
    check.checkEnable(views);
    // 如果报检过，重新报检的时候删除已经报检的数据信息
    DeleteQualityDataRule delete = new DeleteQualityDataRule();
    delete.deleteQualityData(views);

    // 报检单回写发货单数据填充
    FillReportCheckDataRule filldata = new FillReportCheckDataRule();
    filldata.fillReportData(index, views);
  }

  /*
   * 跟发货单子表主键加锁，用于回写处理
   */
  private String[] lockBills(Map<String, RewritePara4331ForC001> index) {
    int size = index.size();
    String[] bids = new String[size];
    bids = index.keySet().toArray(bids);
    LockOperator locker = new LockOperator();
    String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0085")/*@res "报检单回写发货单累计报检数量，锁发货单表体失败"*/;
    locker.lock(bids, message);
    return bids;
  }

  /*
   * 回写发货单的数据信息缓存到map
   */
  private Map<String, RewritePara4331ForC001> prepareParams(
      RewritePara4331ForC001[] paras) {
    Map<String, RewritePara4331ForC001> index =
        new HashMap<String, RewritePara4331ForC001>();
    for (RewritePara4331ForC001 para : paras) {
      index.put(para.getCdeliverybid(), para);
    }
    return index;
  }

  /*
   * 根据发货单子表id查询发货单的信息
   */
  private DeliveryViewVO[] query(Map<String, RewritePara4331ForC001> index) {
    String[] ids = this.lockBills(index);
    ViewQuery<DeliveryViewVO> bo =
        new ViewQuery<DeliveryViewVO>(DeliveryViewVO.class);
    bo.setSharedHead(true);
    DeliveryViewVO[] views = bo.query(ids);
    if (views.length != index.size()) {
      String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0061")/*@res "出现并发，请重新查询发货单"*/;
      ExceptionUtils.wrappBusinessException(message);
    }
    return views;
  }

  /*
   * 报检回写发货单
   */
  private void rewrite(RewritePara4331ForC001[] paras) {
    Map<String, RewritePara4331ForC001> index = this.prepareParams(paras);
    TimeLog.info("并处理参数"); /*-=notranslate=-*/
    TimeLog.logStart();
    DeliveryViewVO[] views = this.query(index);
    TimeLog.info("查询发货单表体"); /*-=notranslate=-*/
    this.addRule(index, views);
    TimeLog.logStart();
    TimeLog.info("写数据库前执行业务规则"); /*-=notranslate=-*/
    TimeLog.logStart();
    ViewUpdate<DeliveryViewVO> bo = new ViewUpdate<DeliveryViewVO>();
    bo.update(views, DeliveryBVO.class, Rewrite4331ForC001Impl.keys);
    TimeLog.info("更新数据库"); /*-=notranslate=-*/
    TimeLog.logStart();
    TimeLog.info("写数据库后执行业务规则"); /*-=notranslate=-*/
  }

}