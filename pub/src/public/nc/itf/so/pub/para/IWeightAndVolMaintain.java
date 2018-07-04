package nc.itf.so.pub.para;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.so.pub.para.WeightVolPieceVO;

/**
 * 重量体积件数接口
 * 
 * @since 6.0
 * @version 2011-7-5 上午11:44:18
 * @author 祝会征
 */
public interface IWeightAndVolMaintain {
  /**
   * 获得标准重量、体积名称
   * 
   * @return
   * @throws BusinessException
   */
  Map<String, String> getWeightAndVolName(String pk_group)
      throws BusinessException;

  /**
   * 获得标准重量、体积的值 以及件数换算率
   * 
   * @return
   * @throws BusinessException
   */
  Map<String, WeightVolPieceVO> getWeightAndVolValue(String[] pk_materials)
      throws BusinessException;

}
