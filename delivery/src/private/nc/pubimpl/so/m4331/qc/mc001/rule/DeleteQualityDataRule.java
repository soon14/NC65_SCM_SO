package nc.pubimpl.so.m4331.qc.mc001.rule;

import java.util.HashSet;
import java.util.Set;

import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m4331.entity.DeliveryCheckVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

import nc.bs.so.m4331.quality.DeleteDeliverycheckBP;
import nc.bs.so.m4331.quality.QueryDeliveryCheckBP;

/**
 * 报检过的发货单重新报检时，删除已经报检过的数据
 * 
 * @since 6.0
 * @version 2010-12-28 下午02:09:11
 * @author 祝会征
 */
public class DeleteQualityDataRule {

  /**
   * 重新报检 删除已经报检的信息
   * 
   * @param views
   */
  public void deleteQualityData(DeliveryViewVO[] views) {
    // 缓存发货单表体id
    Set<String> bidSet = new HashSet<String>();
    for (DeliveryViewVO view : views) {
      bidSet.add(view.getItem().getCdeliverybid());
    }
    String[] bids = new String[bidSet.size()];
    bids = bidSet.toArray(bids);
    // 获得质检信息vo
    DeliveryCheckVO[] vos = this.getDeliverycheckVO(bids);
    if (null != vos) {
      // 删除质检信息
      DeleteDeliverycheckBP delete = new DeleteDeliverycheckBP();
      delete.delete(vos, true);
    }
  }

  /*
   * 根据发货单表体id 获得查询质检信息的sql 
   * @param bids
   * @return
   */
  private DeliveryCheckVO[] getDeliverycheckVO(String[] bids) {
    StringBuffer sql = new StringBuffer();
    SqlBuilder sqlbuilder = new SqlBuilder();
    sql.append("select distinct(");
    sql.append(DeliveryCheckVO.CDELIVERYCID);
    sql.append(") from so_delivery_check where dr =0 and ");
    sqlbuilder.append(DeliveryCheckVO.CDELIVERYBID, bids);
    sql.append(sqlbuilder.toString());
    QueryDeliveryCheckBP query = new QueryDeliveryCheckBP();
    return query.queryDeliveryCheckVO(sql.toString());
  }
}
