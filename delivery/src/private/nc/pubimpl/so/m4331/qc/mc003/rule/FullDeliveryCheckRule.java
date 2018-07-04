package nc.pubimpl.so.m4331.qc.mc003.rule;

import java.util.HashSet;
import java.util.Set;

import nc.bs.so.m4331.quality.QueryDeliveryCheckBP;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m4331.entity.DeliveryCheckVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * 根据质检回写的发货单质检id获得全质检vo
 *
 * @since 6.0
 * @version 2010-12-30 下午07:20:31
 * @author 祝会征
 */
public class FullDeliveryCheckRule {
  /**
   * 根据发货单表体id和质检单据号 查询质检vo
   *
   * @param vos
   * @return
   */
  public DeliveryCheckVO[] getFullInfo(DeliveryCheckVO[] vos) {
    if (VOChecker.isEmpty(vos)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0086")/*@res "质检报告审批回写发货单的质检vo不能为空。"*/);
    }
    // 缓存发货单表体id
    Set<String> bidSet = new HashSet<String>();
    // 缓存质检单据号
    Set<String> billcodeSet = new HashSet<String>();
    for (DeliveryCheckVO vo : vos) {
      String bid = vo.getCdeliverybid();
      String billcode = vo.getVcheckcode();
      if ((null == bid) || "".equals(bid)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0087")/*@res "质检报告审批回写发货单，发货单表体id不能为空。"*/);
      }
      if ((null == billcode) || "".equals(billcode)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0","04006002-0088")/*@res "质检报告审批回写发货单，质检单据号不能为空。"*/);
      }
      bidSet.add(bid);
      billcodeSet.add(billcode);
    }
    String[] bids = new String[bidSet.size()];
    String[] billcodes = new String[billcodeSet.size()];
    bids = bidSet.toArray(bids);
    billcodes = billcodeSet.toArray(billcodes);
    String sql = this.getQuerySql(bids, billcodes);
    QueryDeliveryCheckBP query = new QueryDeliveryCheckBP();
    return query.queryDeliveryCheckVO(sql);
  }

  /*
   *根据质检表id和质检单据号查询要删除的质检信息
   *
   * @param bids
   * @param billcodes
   * @return
   */
  private String getQuerySql(String[] bids, String[] billcodes) {
    StringBuffer sql = new StringBuffer();
    sql.append("select distinct(");
    sql.append(DeliveryCheckVO.CDELIVERYCID);
    sql.append(") from so_delivery_check where dr=0 and ");
    SqlBuilder sqlbuilder = new SqlBuilder();
    sqlbuilder.append(DeliveryCheckVO.CDELIVERYBID, bids);
    sqlbuilder.append(" and ");
    sqlbuilder.append(DeliveryCheckVO.VCHECKCODE, billcodes);
    sql.append(sqlbuilder.toString());
    return sql.toString();
  }
}