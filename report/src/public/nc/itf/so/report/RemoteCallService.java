package nc.itf.so.report;

import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.env.BSContext;
import nc.pubitf.arap.receivable.IArapReceivableBillPubServiceForSCM;
import nc.pubitf.bd.accessor.IGeneralAccessor;
import nc.pubitf.ia.mi5.so.IIAI5ForSOProfitAnalyse;
import nc.pubitf.ia.mi5.so.IIAI5ForSOReportAnalyse;
import nc.pubitf.uapbd.IMaterialBaseClassPubService;
import nc.pubitf.uapbd.IMaterialPubService;
import nc.vo.bd.accessor.IBDData;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.parameter.AskCostPriceParaVO;
import nc.vo.scmpub.parameter.SCMParameterUtils;

public class RemoteCallService {
  private RemoteCallService() {
    // TODO
  }

  public static int getMaterialBaseClassMaxlevel(String pk) {
    IMaterialBaseClassPubService srv =
        NCLocator.getInstance().lookup(IMaterialBaseClassPubService.class);
    int ret = 0;
    try {
      ret = srv.getMaterialBaseClassMaxlevel(pk);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return ret;
  }

  public static Map<String, UFDouble> getNotaxForSoorder(String[] bids) {
    Map<String, UFDouble> ret = null;
    IArapReceivableBillPubServiceForSCM srv =
        NCLocator.getInstance().lookup(
            IArapReceivableBillPubServiceForSCM.class);

    try {
      ret = srv.getNotaxForSoorder(bids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return ret;
  }

  /**
   * 取成本价
   * 
   * @param paraTotalVOs
   * @return
   */
  public static Map<String, UFDouble> getPriceBySCM02(
      AskCostPriceParaVO[] paraTotalVOs) {
    SCMParameterUtils utils = new SCMParameterUtils();
    Map<String, UFDouble> costprice = null;
    try {
      costprice = utils.getPriceBySCM02(paraTotalVOs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return costprice;
  }

  public static Map<String, UFDouble[]> getSOCostMny(String[] bids) {
    IIAI5ForSOProfitAnalyse srv =
        NCLocator.getInstance().lookup(IIAI5ForSOProfitAnalyse.class);
    Map<String, UFDouble[]> ret = null;
    try {
      ret = srv.getSOCostMny(bids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return ret;
  }

  /**
   * 取销售订单行传入存货核算的已成本计算的金额和未成本计算的数量
   * 
   * @param bids
   * @return Map<bid, UFDouble[]>
   */
  public static Map<String, UFDouble[]> getSOReportCostMny(String[] bids) {
    Map<String, UFDouble[]> iainfor = null;
    IIAI5ForSOReportAnalyse iasrv =
        NCLocator.getInstance().lookup(IIAI5ForSOReportAnalyse.class);
    try {
      iainfor = iasrv.queryCostDataByOrderIDForSO(bids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return iainfor;
  }

  /**
   * 物料OID数组批量查找物料所属的指定编码级次的物料基本分类主键
   * 
   * @param cmaterialids
   * @param level
   * @return
   */
  public static Map<String, String> queryMarBasClassIDByClassLevelAndMaterialOIDs(
      String[] cmaterialids, int level) {
    IMaterialBaseClassPubService srv =
        NCLocator.getInstance().lookup(IMaterialBaseClassPubService.class);
    Map<String, String> ret = null;
    try {
      ret =
          srv.queryMarBasClassIDByClassLevelAndMaterialOIDs(level, cmaterialids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return ret;
  }

  /**
   * 
   * @param innercode
   * @return
   */
  public static Map<String, MaterialVO> queryMaterialBaseInfoByPks(
      String[] pks, String[] fields) {
    IMaterialPubService srv =
        NCLocator.getInstance().lookup(IMaterialPubService.class);
    Map<String, MaterialVO> ret = null;
    try {
      ret = srv.queryMaterialBaseInfoByPks(pks, fields);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return ret;
  }

  public static String querySaleorgIDByLevel(String saleorgid, int level) {
    IGeneralAccessor srv =
        NCLocator.getInstance().lookup(IGeneralAccessor.class);
    String ret = null;
    String pk_group = BSContext.getInstance().getGroupID();
    List<IBDData> fatherDocs = srv.getFatherDocs(pk_group, saleorgid, false);
    if (fatherDocs != null && fatherDocs.size() > 0) {
      for (IBDData father : fatherDocs) {
        if (father.getLevel() == level) {
          return father.getPk();
        }
      }
    }
    return ret;
  }

}
