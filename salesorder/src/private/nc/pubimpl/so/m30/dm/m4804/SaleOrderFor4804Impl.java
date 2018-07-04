package nc.pubimpl.so.m30.dm.m4804;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubitf.so.m30.dm.m4804.ISaleOrderFor4804;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderBVO;

public class SaleOrderFor4804Impl implements ISaleOrderFor4804 {

  @Override
  public Map<String, String> querySettleOrgsByBIDs(String[] bids)
      throws BusinessException {
    Map<String, String> returnMap = new HashMap<String, String>();
    if (bids == null || bids.length == 0) {
      return returnMap;
    }
    // 查表体VO[]{BID、结算财务组织OID}
    VOQuery<SaleOrderBVO> bodyQuery =
        new VOQuery<SaleOrderBVO>(SaleOrderBVO.class, new String[] {
          SaleOrderBVO.CSALEORDERBID, SaleOrderBVO.CSETTLEORGID
        });
    SaleOrderBVO[] bodys = bodyQuery.query(bids);
    // 组装返回Map<销售订单BID, 结算财务组织OID>
    if (bodys != null && bodys.length > 0) {
      for (SaleOrderBVO body : bodys) {
        returnMap.put(body.getCsaleorderbid(), body.getCsettleorgid());
      }
    }
    return returnMap;
  }
}
