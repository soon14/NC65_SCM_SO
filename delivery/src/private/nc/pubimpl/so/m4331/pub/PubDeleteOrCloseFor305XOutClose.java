package nc.pubimpl.so.m4331.pub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.util.AggVOUtil;

import nc.pubitf.so.m4331.pub.RewritePara4331;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.pub.LockOperator;

import nc.pubimpl.so.m4331.so.rule.CloseDeliveryRule;
import nc.pubimpl.so.m4331.so.rule.DeleteDeliveryRule;

/**
 * 销售订单或者调拨订单发货关闭
 * 
 * @author 祝会征
 * @since 6.0
 * @time 2010-01-28 下午13:49:07
 */
public class PubDeleteOrCloseFor305XOutClose {

  /**
   * 根据SQL查询发货单表体
   * 
   * @param sql
   * @return 发货单表体VO
   */
  public DeliveryBVO[] queryDeliveryBVO(String sql) {
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql);
    if (rowset.size() == 0) {
      return null;
    }
    String[] ids = rowset.toOneDimensionStringArray();
    LockOperator locker = new LockOperator();
    String message =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0",
            "04006002-0082")/*@res "下游单据回写发货单累计出库数量，锁发货单表体失败"*/;
    locker.lock(ids, message);
    VOQuery<DeliveryBVO> query = new VOQuery<DeliveryBVO>(DeliveryBVO.class);
    DeliveryBVO[] bills = query.query(ids);
    return bills;
  }

  /**
   * 根据SQL查询发货单表头VO
   * 
   * @param sql
   * @return 发货单表头VO数组
   */
  public DeliveryHVO[] queryDeliveryHVO(String sql) {
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql);
    if (rowset.size() == 0) {
      return new DeliveryHVO[0];
    }
    String[] ids = rowset.toOneDimensionStringArray();
    LockOperator locker = new LockOperator();
    String message =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006002_0",
            "04006002-0083")/*@res "下游单据回写发货单累计出库数量，锁表失败"*/;
    locker.lock(ids, message);
    VOQuery<DeliveryHVO> query = new VOQuery<DeliveryHVO>(DeliveryHVO.class);
    DeliveryHVO[] bills = query.query(ids);
    return bills;
  }

  /**
   * 回写发货单
   * 
   * @param paras
   */
  public void rewrite(RewritePara4331[] paras) {
    Map<String, RewritePara4331> index = this.prepareParams(paras);
    // 此处设置session变量，以避免程序到处传递
    BSContext.getInstance().setSession(RewritePara4331.class.getName(), index);
    String sql = this.getQueryBodySql(index);
    DeliveryBVO[] bvos = this.queryDeliveryBVO(sql);
    if (bvos == null) {
      return;
    }
    String[] hids = this.getCdeliveryIds(bvos);
    DeliveryHVO[] hvos = this.queryDeliveryHVO(this.getQueryHeadVOSql(hids));
    DeliveryVO[] aggVOs =
        (DeliveryVO[]) AggVOUtil.createBillVO(hvos, bvos, DeliveryVO.class);
    DeleteDeliveryRule delete = new DeleteDeliveryRule();
    delete.deleteDelivery(aggVOs);
    CloseDeliveryRule close = new CloseDeliveryRule();
    close.closeDelivery(aggVOs);
  }

  private String[] getCdeliveryIds(DeliveryBVO[] bvos) {
    List<String> list = new ArrayList<String>();
    for (DeliveryBVO bvo : bvos) {
      String deliveryId = bvo.getCdeliveryid();
      if (list.contains(deliveryId)) {
        continue;
      }
      list.add(deliveryId);
    }
    String[] ids = new String[list.size()];
    for (int i = 0; i < list.size(); i++) {
      ids[i] = list.get(i);
    }
    return ids;
  }

  private String[] getCsrcbid(Map<String, RewritePara4331> index) {
    int size = index.size();
    String[] bids = new String[size];
    bids = index.keySet().toArray(bids);
    return bids;
  }

  private String getQueryBodySql(Map<String, RewritePara4331> index) {
    String[] bids = this.getCsrcbid(index);
    SqlBuilder sql = new SqlBuilder();
    sql.append("select cdeliverybid from so_delivery_b where dr=0 and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sql.append(iq.buildSQL(DeliveryBVO.CSRCBID, bids));
    return sql.toString();
  }

  private String getQueryHeadVOSql(String[] hids) {
    String groupid = BSContext.getInstance().getGroupID();
    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct so_delivery.cdeliveryid"
        + " from so_delivery where dr =0 and  ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sql.append(iq.buildSQL(DeliveryHVO.CDELIVERYID, hids));
    sql.append(" and ");
    sql.append(DeliveryHVO.PK_GROUP, groupid);
    return sql.toString();
  }

  private Map<String, RewritePara4331> prepareParams(RewritePara4331[] paras) {
    Map<String, RewritePara4331> index = new HashMap<String, RewritePara4331>();
    for (RewritePara4331 para : paras) {
      index.put(para.getCsrcbid(), para);
    }
    return index;
  }
}
