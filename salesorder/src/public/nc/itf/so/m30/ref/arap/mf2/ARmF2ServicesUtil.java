package nc.itf.so.m30.ref.arap.mf2;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.arap.gathering.IArapGatheringBillPubQueryService;
import nc.pubitf.arap.gathering.IArapGatheringBillPubService;
import nc.vo.arap.gathering.AggGatheringBillVO;
import nc.vo.arap.gathering.GatheringBillItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 财务arap的服务工具类
 * 
 * @since 6.0
 * @version 2011-5-26 上午09:35:46
 * @author 刘志伟
 */
public class ARmF2ServicesUtil {

  /**
   * 保存收款单VO
   * 
   * @param AggGatheringBillVO[]
   */
  public static void insertGatheringBill(AggGatheringBillVO[] arg0)
      throws BusinessException {
    IArapGatheringBillPubService service =
        NCLocator.getInstance().lookup(IArapGatheringBillPubService.class);
    try {
      service.save(arg0);
    }
    catch (BusinessException e) {
      ExceptionUtils.marsh(e);
    }
  }

  /**
   * 查询可以进行核销的收款单行
   * 
   * @param pk_org 销售组织
   * @param corigcurrencyid 原币币种
   * @return GatheringBillItemVO[]
   */
  public static GatheringBillItemVO[] queryGatheringBillItemCanVerify(
      String wherePartSql) throws BusinessException {
    IArapGatheringBillPubQueryService service =
        NCLocator.getInstance().lookup(IArapGatheringBillPubQueryService.class);
    GatheringBillItemVO[] gatheringItemVOs = null;
    try {
      gatheringItemVOs = service.queryGatheringBillItemCanVerify(wherePartSql);
    }
    catch (BusinessException e) {
      ExceptionUtils.marsh(e);
    }
    return gatheringItemVOs;
  }

  /**
   * 查询可以进行核销的收款单金额
   * 
   * @param pk_org 销售组织
   * @param corigcurrencyid 原币币种
   * @return GatheringBillItemVO[]
   */
  public static UFDouble queryGatheringBillMoneyItemCanVerify(
      String wherePartSql) throws BusinessException {
    IArapGatheringBillPubQueryService service =
        NCLocator.getInstance().lookup(IArapGatheringBillPubQueryService.class);
    UFDouble ret = null;
    try {
      ret = service.queryGatheringBillMoneyItemCanVerify(wherePartSql);
    }
    catch (BusinessException e) {
      ExceptionUtils.marsh(e);
    }
    return ret;
  }
}
