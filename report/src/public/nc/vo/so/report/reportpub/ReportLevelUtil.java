package nc.vo.so.report.reportpub;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.bd.accessor.IBDData;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.pubitf.bd.accessor.IGeneralAccessor;
import nc.pubitf.uapbd.IMaterialBaseClassPubService;

import nc.bs.framework.common.NCLocator;

import nc.impl.pubapp.env.BSContext;

/**
 * 级次处理
 * 
 * @since 6.3
 * @version 2012-09-12 20:21:39
 * @author 梁吉明
 */
public class ReportLevelUtil {

  /**
   * 物料级次处理
   * 
   * @param cmaterialids
   * @param level
   * @return cmarmap
   */
  public Map<String, String> queryMarBasClassIDByLevel(String[] cmaterialids,
      int level) {
    IMaterialBaseClassPubService srv =
        NCLocator.getInstance().lookup(IMaterialBaseClassPubService.class);
    Map<String, String> cmarmap = null;
    try {
      cmarmap =
          srv.queryMarBasClassIDByClassLevelAndMaterialOIDs(level, cmaterialids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return cmarmap;
  }

  /**
   * 销售组织级次处理
   * 
   * @param saleorgid
   * @param level
   * @return neworg
   */
  public String querySaleorgIDByLevel(String saleorgid, int level) {
    IGeneralAccessor srv =
        NCLocator.getInstance().lookup(IGeneralAccessor.class);
    String neworg = null;
    String pk_group = BSContext.getInstance().getGroupID();
    List<IBDData> fatherDocs = srv.getFatherDocs(pk_group, saleorgid, false);
    if (fatherDocs != null && fatherDocs.size() > 0) {
      for (IBDData father : fatherDocs) {
        if (father.getLevel() == level) {
          neworg = father.getPk();
          return neworg;
        }
      }
    }
    return neworg;
  }

  /**
   * 销售组织级次处理
   * 
   * @param saleorgids
   * @param level
   * @return maps
   */
  public Map<String, String> querySaleorgIDByLevel(String[] saleorgids,
      int level) {
    Map<String, String> materialMap = new HashMap<String, String>();
    for (String salorgid : saleorgids) {
      String newSalerg = this.querySaleorgIDByLevel(salorgid, level);
      materialMap.put(salorgid, newSalerg);
    }
    return materialMap;
  }
}
