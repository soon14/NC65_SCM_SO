package nc.impl.so.para;

import java.util.HashMap;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.accesor.MeasAccessor;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.para.SysParaInitQuery;
import nc.itf.so.pub.para.IWeightAndVolMaintain;
import nc.vo.bd.accessor.IBDData;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pub.BusinessException;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.para.WeightVolPieceVO;

public class WeightAndVolMaintainImpl implements IWeightAndVolMaintain {

  @Override
  public Map<String, String> getWeightAndVolName(String pk_group)
      throws BusinessException {
    Map<String, String> map = new HashMap<String, String>();
    this.setWeight(pk_group, map);
    this.setVol(pk_group, map);
    return map;
  }

  @Override
  public Map<String, WeightVolPieceVO> getWeightAndVolValue(
      String[] pk_materials) throws BusinessException {
    Map<String, String> convertMap = this.getConvertMap(pk_materials);
    Map<String, MaterialVO> materialvoMap = this.getMaterialMap(pk_materials);
    Map<String, WeightVolPieceVO> map = new HashMap<String, WeightVolPieceVO>();
    int len = pk_materials.length;
    for (int i = 0; i < len; i++) {
      String pk_material = pk_materials[i];
      WeightVolPieceVO vo = new WeightVolPieceVO();
      MaterialVO matvo = materialvoMap.get(pk_material);
      vo.setNweight(matvo.getUnitweight());
      vo.setNvol(matvo.getUnitvolume());
      if (null == convertMap || !convertMap.containsKey(pk_material)) {
        vo.setNpiece(null);
      }
      else {
        vo.setNpiece(convertMap.get(pk_material));
      }
      map.put(pk_material, vo);
    }
    return map;
  }

  /**
   * 初始化物料辅计量单位信息
   * 
   * @param pks
   * @param measdocIDs
   */
  private Map<String, String> getConvertMap(String[] pks) {
    Map<String, String> mappiece =
        MaterialPubService.queryPieceMeasdocIDByPks(pks);
    return mappiece;
  }

  /**
   * 初始化物料基本信息
   * 
   * @param pks
   */
  private Map<String, MaterialVO> getMaterialMap(String[] pks) {
    return MaterialPubService.queryMaterialBaseInfo(pks, new String[] {
      MaterialVO.PK_MEASDOC, MaterialVO.UNITWEIGHT, MaterialVO.UNITVOLUME
    });
  }

  /**
   * 设置标准体积名称
   * 
   * @param pk_group
   */
  private void setVol(String pk_group, Map<String, String> map) {
    String volcode = SysParaInitQuery.getParaString(pk_group, SOConstant.BD304);
    if ((null == volcode) || "".equals(volcode)) {
      map.put(SOConstant.BD304, null);
      return;
    }
    IBDData data = MeasAccessor.getDocByPk(volcode);
    if (null == data) {
      map.put(SOConstant.BD304, null);
      return;
    }
    int index = data.getName().getCurrLangIndex();
    String volname = data.getName().getText(index);
    map.put(SOConstant.BD304, volname);

  }

  /**
   * 设置重量的表中单位名称
   * 
   * @param pk_group
   */
  private void setWeight(String pk_group, Map<String, String> map) {
    String weightcode =
        SysParaInitQuery.getParaString(pk_group, SOConstant.BD305);
    if ((null == weightcode) || "".equals(weightcode)) {
      map.put(SOConstant.BD305, null);
      return;
    }
    IBDData data = MeasAccessor.getDocByPk(weightcode);
    if (null == data) {
      map.put(SOConstant.BD305, null);
      return;
    }
    if (null == data.getName()) {
      map.put(SOConstant.BD305, null);
      return;
    }
    int index = data.getName().getCurrLangIndex();
    String name = data.getName().getText(index);
    map.put(SOConstant.BD305, name);

  }
}
