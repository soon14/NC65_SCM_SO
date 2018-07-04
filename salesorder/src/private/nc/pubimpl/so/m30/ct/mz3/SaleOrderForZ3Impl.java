package nc.pubimpl.so.m30.ct.mz3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.SOTable;

import nc.pubitf.so.m30.ct.mz3.ISaleOrderForZ3;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 销售订单为销售合同提供的服务实现类
 * 
 * @since 6.1
 * @version 2012-11-29 10:46:18
 * @author 冯加彬
 */
public class SaleOrderForZ3Impl implements ISaleOrderForZ3 {

  @Override
  public Map<String, UFDouble> queryOrigCurrencyBalance(String[] cthids)
      throws BusinessException {
    // Map<合同hid, 原币余额合计>
    Map<String, UFDouble> returnMap = new HashMap<String, UFDouble>();
    if (cthids == null || cthids.length == 0) {
      return returnMap;
    }

    Map<String, String> associateMap = this.queryBidsByCTHids(cthids);
    if (associateMap != null) {
      // 获得销售合同相关联销售订单表体ids
      // String[] bids =
      // associateMap.keySet().toArray(new String[associateMap.size()]);

      // 获得应收原币余额byBids:？？？？？？？等待arap的接口来获得
      Map<String, UFDouble> balMap = new HashMap<String, UFDouble>();

      // 组织返回结果
      for (Entry<String, UFDouble> entry : balMap.entrySet()) {
        String cthid = associateMap.get(entry.getKey());
        if (returnMap.get(cthid) == null) {
          returnMap.put(cthid, entry.getValue());
        }
        else {
          returnMap.put(cthid, returnMap.get(cthid).add(entry.getValue()));
        }
      }
    }
    return returnMap;
  }

  private Map<String, String> queryBidsByCTHids(String[] cthids) {
    SqlBuilder sql = new SqlBuilder();
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sql.append(" and ");
    sql.append(iq.buildSQL(SaleOrderBVO.CCTMANAGEID, cthids));
    sql.append("and dr = 0");

    VOQuery<SaleOrderBVO> voQuery =
        new VOQuery<SaleOrderBVO>(SaleOrderBVO.class, new String[] {
          SaleOrderBVO.CSALEORDERBID, SaleOrderBVO.CCTMANAGEID
        });

    SaleOrderBVO[] bvos = voQuery.query(sql.toString(), null);
    // Map<销售bid, 合同hid>
    Map<String, String> associateMap = null;
    if (bvos != null && bvos.length > 0) {
      associateMap = new HashMap<String, String>();
      for (SaleOrderBVO bvo : bvos) {
        associateMap.put(bvo.getCsaleorderbid(), bvo.getCctmanageid());
      }
    }
    return associateMap;
  }

  @Override
  public Map<String, UFBoolean> isExistNextOrder(String[] cthids)
      throws BusinessException {
    SqlBuilder wheresql = new SqlBuilder();
    wheresql.append(" and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    wheresql.append(iq.buildSQL(SaleOrderBVO.CSRCID, cthids));

    String[] selkey = new String[] {
      SaleOrderBVO.CSRCID
    };

    VOQuery<SaleOrderBVO> querysrv =
        new VOQuery<SaleOrderBVO>(SaleOrderBVO.class, selkey);

    SaleOrderBVO[] retinvoicebvos = querysrv.query(wheresql.toString(), null);
    Set<String> setexistid = new HashSet<String>();
    for (SaleOrderBVO bvo : retinvoicebvos) {
      setexistid.add(bvo.getCsrcid());
    }
    Map<String, UFBoolean> mapisexist = new HashMap<String, UFBoolean>();
    for (String hid : cthids) {
      if (setexistid.contains(hid)) {
        mapisexist.put(hid, UFBoolean.TRUE);
      }
      else {
        mapisexist.put(hid, UFBoolean.FALSE);
      }
    }
    return mapisexist;
  }
}
