package nc.pubimpl.so.m4331.qc.mc003;

import java.util.HashSet;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.so.m4331.quality.QueryDeliveryCheckBP;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.pubitf.qc.c003.pub.IReportPubQuery;
import nc.pubitf.so.m4331.qc.mc003.IQueryReportVOForC003;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.qc.c003.entity.ReportVO;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryCheckVO;

public class QueryReportVOForC003Impl implements IQueryReportVOForC003 {

  @Override
  public ReportVO[] queryReportVOs(String[] deliverybids)
      throws BusinessException {
    if(deliverybids == null || deliverybids.length == 0){
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0000")/*@res "请至少选择一行记录"*/);
    }
    this.lock(deliverybids);
    this.checkBids(deliverybids);
    DeliveryCheckVO[] vos = this.getCheckVOs(deliverybids);
    ReportVO[] reportvo = this.getReportVos(vos);
    return reportvo;
  }

  /**
   * 检查并发性
   *
   * @param deliverybids
   */
  private void checkBids(String[] deliverybids) {
    VOQuery<DeliveryBVO> query = new VOQuery<DeliveryBVO>(DeliveryBVO.class);
    DeliveryBVO[] bvos = query.query(deliverybids);
    for (DeliveryBVO bvo : bvos) {
      UFBoolean isQuality = bvo.getBqualityflag();
      if ((null == isQuality) || !isQuality.booleanValue()) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0089")/*@res "没有质检结束，不能联查质检报告。"*/);
      }
    }
  }

  private DeliveryCheckVO[] getCheckVOs(String[] deliverybids) {
    StringBuffer sql = new StringBuffer();
    sql.append("select " + DeliveryCheckVO.CDELIVERYCID);
    sql.append(" from so_delivery_check where ");
    SqlBuilder sqlbuider = new SqlBuilder();
    sqlbuider.append(DeliveryCheckVO.CDELIVERYBID, deliverybids);
    sql.append(sqlbuider.toString());
    DeliveryCheckVO[] vos =
        new QueryDeliveryCheckBP().queryDeliveryCheckVO(sql.toString());
    // 重新报检导致质检信息清空
    if (null == vos) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0089")/*@res "没有质检结束，不能联查质检报告。"*/);
    }
    return vos;
  }

  private ReportVO[] getReportVos(DeliveryCheckVO[] vos) {
    Set<String> idSet = new HashSet<String>();
    for (DeliveryCheckVO vo : vos) {
      idSet.add(vo.getCsrcid());
    }
    String[] ids = new String[idSet.size()];
    idSet.toArray(ids);
    IReportPubQuery service =
        NCLocator.getInstance().lookup(IReportPubQuery.class);
    try {
      ReportVO vo = service.queryReportVOByHid(ids[0]);
      return new ReportVO[] {
        vo
      };
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  // 加锁
  private void lock(String[] deliverybids) {
    LockOperator lock = new LockOperator();
    lock.lock(deliverybids, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0090")/*@res "给发货单表体加锁失败。"*/);
  }
}