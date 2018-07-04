package nc.pubimpl.so.m30arrange;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.data.view.EfficientViewQuery;
import nc.pubitf.so.m30arrange.IM30ArrangeForPub;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.util.CombineViewToAggUtil;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

public class M30ArrangeForPubImpl implements IM30ArrangeForPub {

  @Override
  public Map<String, Object[]> querySaleOrderVOs(String[] bids)
      throws BusinessException {
    Map<String, Object[]> retMap = new HashMap<String, Object[]>();

    EfficientViewQuery<SaleOrderViewVO> vq =
        new EfficientViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class);
    SaleOrderViewVO[] vos = vq.query(bids);
    Map<String, String> mapGroup = new HashMap<String, String>();
    Map<String, String> mapOrg = new HashMap<String, String>();
    for (SaleOrderViewVO view : vos) {
      mapGroup.put(view.getHead().getCsaleorderid(),
          (String) view.getAttributeValue(SaleOrderHVO.PK_GROUP));
      mapOrg.put(view.getHead().getCsaleorderid(),
          (String) view.getAttributeValue(SaleOrderHVO.PK_ORG));
    }
    CombineViewToAggUtil<SaleOrderVO> util =
        new CombineViewToAggUtil<SaleOrderVO>(SaleOrderVO.class,
            SaleOrderHVO.class, SaleOrderBVO.class);
    SaleOrderVO[] bills = util.combineViewToAgg(vos, SaleOrderHVO.CSALEORDERID);
    Object[] objs = new Object[bills.length];
    for (int i = 0; i < bills.length; i++) {
      String saleorderid = bills[i].getParentVO().getCsaleorderid();
      bills[i].getParentVO().setPk_group(mapGroup.get(saleorderid));
      bills[i].getParentVO().setPk_org(mapOrg.get(saleorderid));
      objs[i] = bills[i];
    }
    retMap.put(SOBillType.Order.getCode(), objs);
    return retMap;
  }
}
