package nc.pubitf.so.m33.ic.m4c;

import java.util.Map;

import nc.vo.pub.BusinessException;

public interface ICheckCostRegionFor4C {

  /**
   * 方法功能描述：销售出库单保存的时候校验出库单表体成本域与销售发票传存货的成本域是否一致
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param srcbid_costregid --- Map<表体销售发票行id,表体结算成本域id>
   *          <p>Map中只存放销售出库单表体行来源于销售发票的记录
   * 
   * @throws BusinessException
   *           <p>
   * @author zhangcheng
   * @time 2010-8-16 下午03:57:49
   */
  void checkCostRegion(Map<String, String> srcbid_costregid)
      throws BusinessException;

}
