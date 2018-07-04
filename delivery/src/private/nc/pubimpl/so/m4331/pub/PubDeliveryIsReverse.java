package nc.pubimpl.so.m4331.pub;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m4331.entity.DeliveryBVO;

/**
 * 根据来源id获得发货单是否做过预留
 * 
 * @since 6.0
 * @version 2011-1-26 下午04:51:13
 * @author 祝会征
 */
public class PubDeliveryIsReverse {
  public Map<String, UFBoolean> queryReverseFlag(String[] bids) {
    SqlBuilder wheresql = new SqlBuilder();
    wheresql.append(" where " + DeliveryBVO.CSRCBID, bids);
    // 根据id查询VO
    VOQuery<DeliveryBVO> query = new VOQuery<DeliveryBVO>(DeliveryBVO.class);
    DeliveryBVO[] bvos = query.queryWithWhereKeyWord(wheresql.toString(), null);
    Map<String, UFBoolean> map = this.getReverseFlag(bvos, bids);
    return map;
  }

  /*
   * 根据来源id来判断发货单是否做过预留 
   * @param bvo
   * @param bids
   * @return
   */
  private Map<String, UFBoolean> getReverseFlag(DeliveryBVO[] bvos,
      String[] bids) {
    Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
    for (String bid : bids) {
      // 有可能来源单据没有生成下游单据 所以初始化默认值为没有做过预留
      map.put(bid, UFBoolean.FALSE);
      for (DeliveryBVO bvo : bvos) {
        String srcbid = bvo.getCsrcbid();
        if (PubAppTool.isEqual(bid, srcbid)) {
          UFDouble reqNum = bvo.getNreqrsnum();
          boolean expr1 = MathTool.compareTo(UFDouble.ZERO_DBL, reqNum) != 0;
          boolean expr2 = map.get(bid).booleanValue();
          // 如果没做过预留循环下一个
          if (!expr1) {
            continue;
          }
          // 如果做过预留，且来源单据id对应的是否做过预留的标志位false 则更改标志
          if (!expr2) {
            map.put(bid, UFBoolean.TRUE);
          }
        }
      }
    }
    return map;
  }
}
