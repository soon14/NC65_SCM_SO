package nc.pf.so.function.split;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.pubitf.pu.position.IPositionForSplit;
import nc.vo.bd.material.IMaterialEnumConst;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.pu.position.entity.PositionHeaderVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

public class M30For20SplitFunc {

  public List<String> splitByPlanPosition(AggregatedValueObject vo)
      throws BusinessException {
    IPositionForSplit service =
        NCLocator.getInstance().lookup(IPositionForSplit.class);
    return service.splitBorgByPosition(vo, new String[] {
      SaleOrderBVO.CSENDSTOCKORGID, SaleOrderBVO.CMATERIALVID, null
    }, PositionHeaderVO.planPosition);

  }

  public List<String> splitByPurchasePosition(AggregatedValueObject vo)
      throws BusinessException {
    IPositionForSplit service =
        NCLocator.getInstance().lookup(IPositionForSplit.class);
    return service.splitHorgByPosition(vo, new String[] {
      SaleOrderHVO.DEST_PK_ORG, SaleOrderBVO.CMATERIALVID, null
    }, PositionHeaderVO.purchasePosition);
  }

  /*
   * 
   * 根据物料库存组织页签上的物料类型是否委外分单
   */
  public List<String> splitByMaterialType(AggregatedValueObject vo)
      throws BusinessException {
    SaleOrderVO orderVO = (SaleOrderVO) vo;

    // 批量获取物料库存组织信息。
    Map<String, List<String>> materialMapByStockOrg =
        new HashMap<String, List<String>>();
    for (SaleOrderBVO orderBVO : orderVO.getChildrenVO()) {
      if (materialMapByStockOrg.containsKey(orderBVO.getCsendstockorgid())) {
        materialMapByStockOrg.get(orderBVO.getCsendstockorgid()).add(
            orderBVO.getCmaterialid());
      }
      else {
        List<String> materialPks = new ArrayList<String>();
        materialPks.add(orderBVO.getCmaterialid());
        materialMapByStockOrg.put(orderBVO.getCsendstockorgid(), materialPks);
      }
    }
    Map<String, MaterialStockVO> tempRet =
        new HashMap<String, MaterialStockVO>();
    for (String key : materialMapByStockOrg.keySet()) {
      Map<String, MaterialStockVO> marterialStockMapping =
          MaterialPubService.queryMaterialStockInfo(
              materialMapByStockOrg.get(key).toArray(
                  new String[materialMapByStockOrg.get(key).size()]), key,
              new String[] {
                MaterialStockVO.MARTYPE
              });
      tempRet.putAll(marterialStockMapping);
    }

    // 为每行表体设置是否委外标志
    List<String> result = new ArrayList<String>();
    for (SaleOrderBVO orderBVO : orderVO.getChildrenVO()) {
      if (IMaterialEnumConst.MATERTYPE_OT.equals(tempRet.get(
          orderBVO.getCmaterialid()).getMartype())) {
        result.add("SC");
      }
      else {
        result.add("NSC");
      }
    }
    return result;
  }
}
