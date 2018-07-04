package nc.vo.so.m30.util;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.price.pplimitexe.ISOUpdatePPLimitExe;
import nc.vo.price.pplimitexe.SOUpdatePPLimitExePara;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 销售订单限量促销工具类
 * 
 * @since 6.36
 * @version 2015-5-30 下午12:33:20
 * @author 刘景
 */
public class PromoteLimitUtil {

  /**
   * 全释放执行量回写
   * 
   * @param paras
   */
  public static void DeletePPLimit(SOUpdatePPLimitExePara[] paras) {
    if (SysInitGroupQuery.isPRICEEnabled()) {
      ISOUpdatePPLimitExe service =
          NCLocator.getInstance().lookup(ISOUpdatePPLimitExe.class);
      try {
        service.updateExecutedNumByDelete(paras);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }

  /**
   * 全占用执行量回写
   * 
   * @param paras
   */
  public static void InsertPPLimit(SOUpdatePPLimitExePara[] paras) {
    if (SysInitGroupQuery.isPRICEEnabled()) {
      ISOUpdatePPLimitExe service =
          NCLocator.getInstance().lookup(ISOUpdatePPLimitExe.class);
      // 回写促销价格表
      try {
        service.updateExecutedNumByInsert(paras);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }

  /**
   * 更新执行量回写
   */
  public static void updatePLimit(SOUpdatePPLimitExePara[] releaseparas,
      SOUpdatePPLimitExePara[] paras) {
    if (SysInitGroupQuery.isPRICEEnabled()) {
      ISOUpdatePPLimitExe service =
          NCLocator.getInstance().lookup(ISOUpdatePPLimitExe.class);
      try {
        service.updateExecutedNumByUpdate(releaseparas, paras);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }

  /**
   * 订单发货关闭/打开;订单出库关闭/打开
   * 
   * @param paras 回写参数
   * @throws BusinessException
   */
  public static void updateExecutedNumByOpenOrClose(
      SOUpdatePPLimitExePara[] paras) {
    if (SysInitGroupQuery.isPRICEEnabled()) {
      ISOUpdatePPLimitExe service =
          NCLocator.getInstance().lookup(ISOUpdatePPLimitExe.class);
      try {
        service.updateExecutedNumByOpenOrClose(paras);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }

}
