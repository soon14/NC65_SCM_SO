package nc.vo.so.m4331.pub;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.pub.para.IWeightAndVolMaintain;
import nc.vo.pub.BusinessException;
import nc.vo.so.pub.para.WeightVolPieceVO;

/**
 * 获得标准单位、标准体积
 * 
 * @since 6.0
 * @version 2011-6-13 下午03:32:49
 * @author 祝会征
 */
public class WeightAndVolParaUtil {

  /**
   * key=pk_materials+measdocIDs
   * 
   * @param pk_materials
   * @param measdocIDs
   * @return
   * @throws BusinessException
   */
  public static Map<String, WeightVolPieceVO> getWeightAndVolValue(
      String[] pk_materials) throws BusinessException {
    return WeightAndVolParaUtil.getValue(pk_materials);
  }

  private static Map<String, WeightVolPieceVO> getValue(String[] pk_materials)
      throws BusinessException {
    IWeightAndVolMaintain service =
        NCLocator.getInstance().lookup(IWeightAndVolMaintain.class);
    return service.getWeightAndVolValue(pk_materials);
  }
}
