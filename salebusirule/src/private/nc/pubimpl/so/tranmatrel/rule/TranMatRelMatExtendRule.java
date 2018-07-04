package nc.pubimpl.so.tranmatrel.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialBaseClassPubService;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.bd.material.MaterialSaleClassPubSerive;
import nc.pubitf.so.tanmatrel.TranMatRelParaVO;
import nc.vo.bd.accessor.IBDData;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.material.marbasclass.MarBasClassVO;
import nc.vo.bd.material.sale.MaterialSaleVO;
import nc.vo.so.pub.util.ObjectUtil;

/**
 * 单据类型与物料关系定义：物料属性扩展
 * 
 * @since 6.0
 * @version 2011-4-14 下午03:58:43
 * @author 祝会征
 */
public class TranMatRelMatExtendRule {

  /**
   * 扩展物料基本分类
   * 
   * @param matchparas
   * @return
   */
  public List<TranMatRelParaVO> extendBaseClass(TranMatRelParaVO[] matchparas) {
    List<TranMatRelParaVO> paraList = new ArrayList<TranMatRelParaVO>();
    Set<String> pkSet = new HashSet<String>();
    for (TranMatRelParaVO para : matchparas) {
      paraList.add(para);
      pkSet.add(para.getPk_material());
    }
    String[] pks = new String[pkSet.size()];
    pkSet.toArray(pks);
    Map<String, String> baseMap = this.getMaterialBaseInfos(pks);
    if (baseMap.size() == 0) {
      return paraList;
    }
    Map<String, String[]> tempMap = new HashMap<String, String[]>();
    for (TranMatRelParaVO para : matchparas) {
      String pk = para.getPk_material();
      if (tempMap.containsKey(pk)) {
        String[] temppks = tempMap.get(pk);
        for (String basecl : temppks) {
          TranMatRelParaVO basclextend =
              (TranMatRelParaVO) ObjectUtil.serializableClone(para);
          basclextend.setPk_material(null);
          basclextend.setPk_materialbaseclass(basecl);
          paraList.add(basclextend);
        }
      }
      else {
        String pk_marbasclass = baseMap.get(pk);
        String[] basepks = this.getFatherMarBaseClass(pk_marbasclass);
        for (String basepk : basepks) {
          TranMatRelParaVO basclextend =
              (TranMatRelParaVO) ObjectUtil.serializableClone(para);
          basclextend.setPk_material(null);
          basclextend.setPk_materialbaseclass(basepk);
          paraList.add(basclextend);
        }
        tempMap.put(pk, basepks);
      }
    }
    return paraList;
  }

  /**
   * 扩展物料销售分类信息
   * 
   * @param matchparas
   * @return
   */
  public List<TranMatRelParaVO> extendMarSaleClass(TranMatRelParaVO[] matchparas) {
    List<TranMatRelParaVO> paraList = new ArrayList<TranMatRelParaVO>();
    Set<String> pkSet = new HashSet<String>();
    for (TranMatRelParaVO para : matchparas) {
      paraList.add(para);
      pkSet.add(para.getPk_material());
    }
    String[] pks = new String[pkSet.size()];
    pkSet.toArray(pks);
    String pk_org = matchparas[0].getPk_org();
    Map<String, MaterialSaleVO> materialMap =
        this.getMaterialSaleInfo(pks, pk_org);
    if (null == materialMap || materialMap.size() == 0) {
      return paraList;
    }
    Map<String, String[]> tempMap = new HashMap<String, String[]>();
    for (TranMatRelParaVO para : matchparas) {
      String marid = para.getPk_material();
      if (tempMap.containsKey(marid)) {
        String[] saleclids = tempMap.get(marid);
        for (String salecl : saleclids) {
          TranMatRelParaVO basclextend =
              (TranMatRelParaVO) ObjectUtil.serializableClone(para);
          basclextend.setPk_material(null);
          basclextend.setPk_materialsaleclass(salecl);
          paraList.add(basclextend);
        }
      }
      else {
        MaterialSaleVO salevo = materialMap.get(marid);
        String pk_marsaleclass = salevo.getPk_marsaleclass();
        String[] saleclids =
            this.getFatherMarSaleClass(pk_org, pk_marsaleclass);
        for (String salecl : saleclids) {
          TranMatRelParaVO basclextend =
              (TranMatRelParaVO) ObjectUtil.serializableClone(para);
          basclextend.setPk_material(null);
          basclextend.setPk_materialsaleclass(salecl);
          paraList.add(basclextend);
        }
        tempMap.put(marid, saleclids);
      }
    }
    return paraList;
  }

  private String[] getFatherMarBaseClass(String pk_marbasclass) {
    MarBasClassVO[] marbasclassvos = null;

    marbasclassvos =
        MaterialBaseClassPubService.queryMaterialBaseClassByPk(pk_marbasclass,
            true);
    String[] fatherclass = new String[marbasclassvos.length];
    int i = 0;
    for (MarBasClassVO classvo : marbasclassvos) {
      fatherclass[i] = classvo.getPrimaryKey();
      i++;
    }
    return fatherclass;

  }

  private String[] getFatherMarSaleClass(String pk_saleorg,
      String pk_marsaleclass) {
    List<IBDData> marsaleclassvos =
        MaterialSaleClassPubSerive.queryMaterialSaleClassFather(pk_saleorg,
            pk_marsaleclass, true);
    String[] fathersalecls = new String[marsaleclassvos.size()];
    int i = 0;
    for (IBDData bddata : marsaleclassvos) {
      fathersalecls[i] = bddata.getPk();
      i++;
    }
    return fathersalecls;
  }

  /**
   * 获得物理基本分类信息
   * 
   * @param pks
   * @return
   */
  private Map<String, String> getMaterialBaseInfos(String[] pks) {
    String[] names = new String[] {
      MaterialVO.PK_MATERIAL, MaterialVO.PK_MARBASCLASS
    };
    MaterialVO[] materialvos =
        MaterialPubService.queryMaterialBaseInfoByPks(pks, names);
    Map<String, String> strMap = new HashMap<String, String>();
    for (MaterialVO marvo : materialvos) {
      strMap.put(marvo.getPk_material(), marvo.getPk_marbasclass());
    }
    return strMap;
  }

  private Map<String, MaterialSaleVO> getMaterialSaleInfo(String[] pks,
      String pk_org) {
    String[] names = new String[] {
      MaterialSaleVO.PK_MARSALECLASS
    };
    Map<String, MaterialSaleVO> mapMarSale =
        MaterialPubService.queryMaterialSaleInfoByPks(pks, pk_org, names);
    return mapMarSale;
  }
}
