package nc.pubimpl.so.m30.ic.m4453;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubitf.so.m30.ic.m4453.ISaleOrderFor4453;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderHVO;

public class SaleOrderFor4453Impl implements ISaleOrderFor4453 {

  @Override
  public Map<String, String> querySaleOrgsByIDs(String[] hids)
      throws BusinessException {
    Map<String, String> returnMap = new HashMap<String, String>();
    if (hids == null || hids.length == 0) {
      return returnMap;
    }
    // 查表头VO[]{hid、销售组织}
    VOQuery<SaleOrderHVO> hvoQuery =
        new VOQuery<SaleOrderHVO>(SaleOrderHVO.class, new String[] {
          SaleOrderHVO.CSALEORDERID, SaleOrderHVO.PK_ORG
        });
    SaleOrderHVO[] hvos = hvoQuery.query(hids);
    // 组装返回map
    if (hvos != null && hvos.length > 0) {
      for (SaleOrderHVO hvo : hvos) {
        returnMap.put(hvo.getCsaleorderid(), hvo.getPk_org());
      }
    }
    return returnMap;
  }

}
