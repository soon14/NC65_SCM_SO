package nc.vo.so.pub.util;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.pubitf.initgroup.InitGroupQuery;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.res.NCModule;

/**
 * 判断模块是否启用
 * 
 * @since 6.0
 * @version 2011-6-18 上午11:39:23
 * @author 钟鸣
 */

public class SOSysInitGroupQuery {

  private static String getOrgPk() {
    return InvocationInfoProxy.getInstance().getGroupId();
  }

  /**
   * 判断资产信息管理模块是否启用
   * 
   * @return
   */
  public static boolean isAIMEnabled() {
    try {
      return InitGroupQuery.isEnabled(InvocationInfoProxy.getInstance()
          .getGroupId(), NCModule.AIM.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 判断资产租赁管理模块是否启用
   * 
   * @return
   */
  public static boolean isALMEnabled() {
    try {
      // TODO 常量待定义
      return InitGroupQuery.isEnabled(InvocationInfoProxy.getInstance()
          .getGroupId(), /* NCModule.ALM.getCode() */"4530");
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 资产管理
   * 
   * @return boolean
   */
  public static boolean isAMEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.AM.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 财务模块：应付管理
   * 
   * @return boolean
   */
  public static boolean isAPEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.AP.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 财务模块：应收管理
   * 
   * @return boolean
   */
  public static boolean isAREnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.AR.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 判断资产使用管理模块是否启用
   * 
   * @return
   */
  public static boolean isAUMEnabled() {
    try {
      // TODO 常量待定义
      return InitGroupQuery.isEnabled(InvocationInfoProxy.getInstance()
          .getGroupId(), /* NCModule.AUM.getCode() */"4520");
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 供应链模块：销售返利
   * 
   * @return boolean
   */
  public static boolean isBRMEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.BRM.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 战略管理模块：生产成本管理
   * 
   * @return boolean
   */
  public static boolean isCMEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.CM.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 供应链模块：销售信用
   * 
   * @return boolean
   */
  public static boolean isCREDITEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.CREDIT.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 供应链模块：合同管理
   * 
   * @return boolean
   */
  public static boolean isCTEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.CT.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 供应链模块：运输管理
   * 
   * @return boolean
   */
  public static boolean isDMEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.DM.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 判断 维护管理模块是否启用
   * 
   * @return
   */
  public static boolean isEMMEnabled() {
    try {
      // TODO 常量待定义
      return InitGroupQuery.isEnabled(InvocationInfoProxy.getInstance()
          .getGroupId(), /* NCModule.EMM.getCode() */"4550");
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 判断某个模块在当前集团是否启用
   * 
   * @param module
   *          NC模块
   * @return
   */
  public static boolean isEnable(NCModule module) {
    boolean flag = false;
    String pk_group = SOSysInitGroupQuery.getOrgPk();
    try {
      flag = InitGroupQuery.isEnabled(pk_group, module.getCode());
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return flag;
  }

  /**
   * 判断运行管理模块是否启用
   * 
   * @return
   */
  public static boolean isEOMEnabled() {
    try {
      // TODO 常量待定义
      return InitGroupQuery.isEnabled(InvocationInfoProxy.getInstance()
          .getGroupId(), /* NCModule.EOM.getCode() */"4540");
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 判断维修管理模块是否启用
   * 
   * @return
   */
  public static boolean isEWMEnabled() {
    try {
      // TODO 常量待定义
      return InitGroupQuery.isEnabled(InvocationInfoProxy.getInstance()
          .getGroupId(), /* NCModule.EWM.getCode() */"4560");
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 供应链模块：固定资产
   * 
   * @return boolean
   */
  public static boolean isFAEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.FA.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 企业建模：会计平台
   * 
   * @return boolean
   */
  public static boolean isFIPEnabled() {
    return SOSysInitGroupQuery.isEnable(NCModule.FIP);
  }

  /**
   * 财务总帐是否在本集团已经启用
   * 
   * @return 总帐模块启用返回真
   */
  public static boolean isGLEnabled() {
    return SOSysInitGroupQuery.isEnable(NCModule.GL);
  }

  /**
   * 财务模块：存货核算
   * 
   * @return boolean
   */
  public static boolean isIAEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.IA.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 供应链模块：库存管理
   * 
   * @return boolean
   */
  public static boolean isICEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.IC.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 供应链模块：库存计划
   * 
   * @return boolean
   */
  public static boolean isINVPEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.INVP.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 离散生产任务管理模块
   * 
   * @return boolean
   */
  public static boolean isMMDPACEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.MMDPAC.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 需求管理模块
   * 
   * @return boolean
   */
  public static boolean isMMDPEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.MMDP.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 生产制造模块
   * 
   * @return boolean
   */
  public static boolean isMMEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.MM.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 生产任务管理模块
   * 
   * @return boolean
   */
  public static boolean isMMPACEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.MMPAC.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 流程生产任务管理模块
   * 
   * @return boolean
   */
  public static boolean isMMPPACEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.MMPPAC.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 供应链模块：采购计划
   * 
   * @return boolean
   */
  public static boolean isMPPEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.MPP.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 订单中心
   * 
   * @return boolean
   */
  public static boolean isOPCEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.OPC.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 项目管理模块：项目综合管理
   * 
   * @return boolean
   */
  public static boolean isPIMEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.PIM.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 供应链模块：采购管理
   * 
   * @return boolean
   */
  public static boolean isPOEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.PO.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 供应链模块：采购价格
   * 
   * @return boolean
   */
  public static boolean isPPEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.PURP.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 供应链模块：销售价保
   * 
   * @return boolean
   */
  public static boolean isPPMEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.PPM.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 供应链模块：销售价格
   * 
   * @return boolean
   */
  public static boolean isPRICEEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.PRICE.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 付款排程
   * 
   * @return boolean
   */
  public static boolean isPSEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.PS.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 供应链模块：销售计划
   * 
   * @return boolean
   */
  public static boolean isPSPEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.PSP.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /********************** 战略管理 ********************/

  /**
   * 质量管理
   * 
   * @return boolean
   */
  public static boolean isQCEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.QC.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /********************** 财务 ********************/

  /**
   * 判断周转材租入管理模块是否启用
   * 
   * @return
   */
  public static boolean isRLMEnabled() {
    try {
      // TODO 常量待定义
      return InitGroupQuery.isEnabled(InvocationInfoProxy.getInstance()
          .getGroupId(), /* NCModule.RLM.getCode() */"4585");
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 判断周转材租出管理模块是否启用
   * 
   * @return
   */
  public static boolean isROMEnabled() {
    try {
      // TODO 常量待定义
      return InitGroupQuery.isEnabled(InvocationInfoProxy.getInstance()
          .getGroupId(), /* NCModule.ROM.getCode() */"4583");
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 判断易耗品管理模块是否启用
   * 
   * @return
   */
  public static boolean isRUMEnabled() {
    try {
      return InitGroupQuery.isEnabled(InvocationInfoProxy.getInstance()
          .getGroupId(), NCModule.RUM.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /********************** 资金管理 ********************/

  /**
   * 供应链模块：委外加工
   * 
   * @return boolean
   */
  public static boolean isSCEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.SC.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /********************** 资产管理 ********************/

  /**
   * 供应链模块
   * 
   * @return boolean
   */
  public static boolean isSCMEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.SCM.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /********************** 生产制造 ********************/

  /**
   * 供应链模块：供应链基础设置
   * 
   * @return boolean
   */
  public static boolean isSCMFEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.SCMF.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 供应链模块：供应链管理报表
   * 
   * @return boolean
   */
  public static boolean isSCMMREnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.SCMMR.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 供应链模块：销售管理
   * 
   * @return boolean
   */
  public static boolean isSOEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.SO.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 供应链模块：销售返利
   * 
   * @return boolean
   */
  public static boolean isSREnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(), "4036");// NCModule.SR.getCode()
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 资金管理模块：内部帐户管理
   * 
   * @return boolean
   */
  public static boolean isTAMEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.TAM.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 资金管理
   * 
   * @return boolean
   */
  public static boolean isTMEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.TM.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 供应链模块：内部交易
   * 
   * @return boolean
   */
  public static boolean isTOEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.TO.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 供应链模块：U8零售接口
   * 
   * @return boolean
   */
  public static boolean isU8RMEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.U8RM.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 供应链模块：供应商管理
   * 
   * @return boolean
   */
  public static boolean isVRMEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(),
          NCModule.VRM.getCode());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  /**
   * 供应链模块：营销费用
   * 
   * @return boolean
   */
  public static boolean isMeEnabled() {
    try {
      return InitGroupQuery.isEnabled(SOSysInitGroupQuery.getOrgPk(), "4038");
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

}
