package nc.vo.so.pub.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.bd.pub.BDCacheQueryUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;


/**
 * 物料批量查询物料相关信息工具类
 * 
 * @since 6.36
 * @version 2015-6-3 下午6:17:38
 * @author 刘景
 */
public class MaterialPubServiceUtil {
  
  

  /**
   * 根据物料+库存组织查询MaterialStockVO
   * 
   * @param matids 物料id
   * @param pk_stockorg 库存组织
   * @param selectFiels 需要查询的字段
   * @return
   */
  public static Map<String, MaterialStockVO> queryMaterialStockInfoByPks(String[] matids,
      String[] pk_stockorg,String[] selectFiels) {
    HashMap<String, MaterialStockVO> pk_materialStockVO_map = null;
    try {
      //查询的字段
      Set<String> fieldSet = new HashSet<String>(Arrays.asList(selectFiels));
      fieldSet.add(MaterialStockVO.PK_MATERIAL);
      fieldSet.add(MaterialStockVO.PK_ORG);
      
      //查询条件的值
      Map<String, Object[]> paramField_paramValues_map =
          new HashMap<String, Object[]>();
      paramField_paramValues_map.put(MaterialStockVO.PK_MATERIAL, matids);
      paramField_paramValues_map.put(MaterialStockVO.PK_ORG, pk_stockorg);
      

      MaterialStockVO[] materialStockVOs =
          (MaterialStockVO[]) BDCacheQueryUtil.queryVOs(MaterialStockVO.class,
              fieldSet.toArray(new String[0]), paramField_paramValues_map);
      materialStockVOs =
          BDCacheQueryUtil
              .filterNullVO(MaterialStockVO.class, materialStockVOs);
      pk_materialStockVO_map = new HashMap<String, MaterialStockVO>();
      for (MaterialStockVO materialStockVO : materialStockVOs) {
        String pk_material = materialStockVO.getPk_material();
        String pk_org = materialStockVO.getPk_org();
        pk_materialStockVO_map.put(pk_material + pk_org, materialStockVO);
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
    return pk_materialStockVO_map;
  }


}
