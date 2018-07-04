package nc.vo.so.pub.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.para.SysInitVO;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmpub.parameter.SCMParameterUtils;
import nc.vo.so.pub.res.ParameterList;

import nc.itf.scmpub.reference.uap.para.SysParaInitQuery;

/**
 * 销售管理复杂参数的获取
 * 
 * @since 6.0
 * @version 2010-12-2 上午09:14:03
 * @author 祝会征
 */
public class SOSysParaInitUtil {

  /**
   * 根据销售组织获得订单限行
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static Integer getSO01(String pk_org) {
    return SysParaInitQuery.getParaInt(pk_org, ParameterList.SO01.getCode());
  }

  /**
   * 配置销售是否统一报价
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static UFBoolean getSO02(String pk_org) {
    return SysParaInitQuery
        .getParaBoolean(pk_org, ParameterList.SO02.getCode());
  }

  /**
   * 订单表头记录预收款比例和预收款金额的优先规则
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static String getSO03(String pk_org) {
    return SysParaInitQuery.getParaString(pk_org, ParameterList.SO03.getCode());
  }

  /**
   * 根据需求变更为销售组织
   * 超销售订单出库容差控制方式
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static String getSO04(String pk_org) {
    return SysParaInitQuery.getParaString(pk_org, ParameterList.SO04.getCode());
  }

  /**
   * 根据需求变更为销售组织
   * 超订单发货容差控制方式
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static String getSO05(String pk_org) {
    return SysParaInitQuery.getParaString(pk_org, ParameterList.SO05.getCode());
  }

  /**
   * 根据需求变更为销售组织
   * 超发货单出库容差控制方式
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static String getSO06(String pk_org) {
    return SysParaInitQuery.getParaString(pk_org, ParameterList.SO06.getCode());
  }

  /**
   * 根据需求变更为销售组织
   * 销售订单开票控制方式
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static String getSO07(String pk_org) {
    return SysParaInitQuery.getParaString(pk_org, ParameterList.SO07.getCode());
  }

  /**
   * 根据需求变更为销售组织
   * 销售出库单开票控制方式
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static String getSO08(String pk_org) {
    return SysParaInitQuery.getParaString(pk_org, ParameterList.SO08.getCode());
  }

  /**
   * 根据需求变更为销售组织
   * 订单关闭时用订单出库数量修订销售合同执行数量
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static UFBoolean getSO10(String pk_org) {
    return SysParaInitQuery
        .getParaBoolean(pk_org, ParameterList.SO10.getCode());
  }

  /**
   * 订单收款核销条件
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static String[] getSO11(String pk_org) {
    SysInitVO initvo =
        SysParaInitQuery.queryByParaCode(pk_org, ParameterList.SO11.getCode()
            + ParameterList.SUFFIX);
    if (null != initvo) {
      String value = initvo.getValue();
      if (null == value || "".equals(value)) {
        return null;
      }
      return value.split(ParameterList.SPLITKEY);
    }
    return null;
  }

  /**
   * 财务组织
   * 销售出库单应收结算允许修改客户和单价
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static UFBoolean getSO12(String pk_org) {
    return SysParaInitQuery
        .getParaBoolean(pk_org, ParameterList.SO12.getCode());
  }

  /**
   * 库存组织
   * 补货/直运安排容差比率
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static UFDouble getSO13(String pk_org) {
    return SysParaInitQuery.getParaDbl(pk_org, ParameterList.SO13.getCode());
  }

  /**
   * 财务组织
   * 发票与冲应收单自动合并开票
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static UFBoolean getSO14(String pk_org) {
    return SysParaInitQuery
        .getParaBoolean(pk_org, ParameterList.SO14.getCode());
  }

  /**
   * 财务组织
   * 冲应收单合并开票时可以冲减发票金额的比例
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static UFDouble getSO15(String pk_org) {
    return SysParaInitQuery.getParaDbl(pk_org, ParameterList.SO15.getCode());
  }

  /**
   * 销售组织
   * 基于签收数量开票的业务流程
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static MapList<String, String> getSO16(String[] pk_orgs) {
    MapList<String, String> sysMap = new MapList<String, String>();
    for (String pk_org : pk_orgs) {
      SysInitVO initvo =
          SysParaInitQuery.queryByParaCode(pk_org, ParameterList.SO16.getCode()
              + ParameterList.SUFFIX);
      if (null != initvo) {
        String value = initvo.getValue();
        if (null == value || "".equals(value)) {
          continue;
        }
        String[] values = value.split(ParameterList.SPLITKEY);
        for (String busi : values) {
          sysMap.put(pk_org, busi);
        }
      }
    }
    return sysMap;
  }

  /**
   * 财务组织
   * 发票限行
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static Integer getSO17(String pk_org) {
    return SysParaInitQuery.getParaInt(pk_org, ParameterList.SO17.getCode());
  }

  /**
   * 销售组织
   * 销售订单分单打印规则
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static String[] getSO18(String pk_org) {
    SysInitVO initvo =
        SysParaInitQuery.queryByParaCode(pk_org, ParameterList.SO18.getCode()
            + ParameterList.SUFFIX);
    if (null != initvo) {
      String value = initvo.getValue();
      if (null == value || "".equals(value)) {
        return null;
      }
      return value.split(ParameterList.SPLITKEY);
    }
    return null;
  }

  /**
   * 物流组织
   * 发货单分单打印规则
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static String[] getSO19(String pk_org) {
    SysInitVO initvo =
        SysParaInitQuery.queryByParaCode(pk_org, ParameterList.SO19.getCode()
            + ParameterList.SUFFIX);
    if (null != initvo) {
      String value = initvo.getValue();
      if (null == value || "".equals(value)) {
        return null;
      }
      return value.split(ParameterList.SPLITKEY);
    }
    return null;
  }

  /**
   * 财务组织
   * 赠品是否开票
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static UFBoolean getSO20(String pk_org) {
    return SysParaInitQuery
        .getParaBoolean(pk_org, ParameterList.SO20.getCode());
  }

  /**
   * 销售组织
   * 销售询价触发条件
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static String[] getSO21(String pk_org) {
    SysInitVO initvo =
        SysParaInitQuery.queryByParaCode(pk_org, ParameterList.SO21.getCode()
            + ParameterList.SUFFIX);
    if (null != initvo) {
      String value = initvo.getValue();
      if (null == value || "".equals(value)) {
        return null;
      }
      return value.split(ParameterList.SPLITKEY);
    }
    return null;
  }

  /**
   * 销售组织
   * 存储询价过程明细
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static UFBoolean getSO22(String pk_org) {
    return SysParaInitQuery
        .getParaBoolean(pk_org, ParameterList.SO22.getCode());
  }

  /**
   * 集团
   * 基价含税、含税优先
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static UFBoolean getSO23(String pk_group) {
    UFBoolean so23 = SCMParameterUtils.getSCM13(pk_group);
    return so23 == null ? UFBoolean.FALSE : so23;
  }

  /**
   * 销售组织
   * 超预订单安排订单控制方式
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static String getSO26(String pk_org) {
    return SysParaInitQuery.getParaString(pk_org, ParameterList.SO26.getCode());
  }

  /**
   * 集团
   * 发票表体默认显示方式
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static String getSO27(String pk_group) {
    return SysParaInitQuery.getParaString(pk_group,
        ParameterList.SO27.getCode());
  }

  /**
   * 销售组织
   * 销售发票汇总规则
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static String[] getSO28(String pk_org) {
    SysInitVO initvo =
        SysParaInitQuery.queryByParaCode(pk_org, ParameterList.SO28.getCode());
    if (null != initvo) {
      String value = initvo.getValue();
      if (null != value && value.length() > 0) {
        return value.split(ParameterList.SPLITKEY);
      }
    }
    return null;
  }

  /**
   * 根据销售组织获得报价单限行
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static Integer getSO29(String pk_org) {
    return SysParaInitQuery.getParaInt(pk_org, ParameterList.SO29.getCode());
  }

  /**
   * 现销处理弹出详细核销界面
   * 
   * @param pk_org 财务组织
   * @return
   * @throws BusinessException
   */
  public static UFBoolean getSO30(String pk_org) {
    return SysParaInitQuery
        .getParaBoolean(pk_org, ParameterList.SO30.getCode());
  }

  /**
   * 销售发票行合并税额计算
   * 
   * @param pk_org
   * @return so31
   */
  public static UFBoolean getSO31(String pk_org) {
    return SysParaInitQuery
        .getParaBoolean(pk_org, ParameterList.SO31.getCode());
  }
  
  /**
   * 销售订单是否支持审批中修改
   * 
   * @param pk_org
   * @return so32
   */
  public static UFBoolean getSO32(String pk_org) {
    return SysParaInitQuery
        .getParaBoolean(pk_org, ParameterList.SO32.getCode());
  }
  
  /**
   * 销售发票是否支持审批中修改
   * 
   * @param pk_org
   * @return so33
   */
  public static UFBoolean getSO33(String pk_org) {
    return SysParaInitQuery
        .getParaBoolean(pk_org, ParameterList.SO33.getCode());
  }


  /**
   * 获取参数表里面存的原始字符串
   * 
   * @param pk_orgs
   * @param initCode
   * @return
   * @throws BusinessException
   */
  public static Map<String, String> queryBatchParaStringValues(
      String[] pk_orgs, String initCode) {
    if (pk_orgs.length == 0) {
      return null;
    }
    Map<String, String> paraMap = new HashMap<String, String>();
    for (String pk_org : pk_orgs) {
      String code = SysParaInitQuery.getParaString(pk_org, initCode);
      paraMap.put(pk_org, code);
    }
    return paraMap;
  }

  /**
   * 固定销售组织 批量获取，批量参数值的获取
   * 
   * @param pk_org
   * @param initCodes
   * @return
   * @throws BusinessException
   */
  public static Map<String, List<String>> queryBatchParaValues(String pk_org,
      String[] initCodes) {
    if (initCodes.length == 0) {
      return null;
    }
    Map<String, List<String>> paraMap = new HashMap<String, List<String>>();
    Map<String, String> mapCode =
        SysParaInitQuery.queryBatchParaValues(pk_org, initCodes);
    for (Map.Entry<String, String> entry : mapCode.entrySet()) {
      List<String> paraList = new ArrayList<String>();
      String code = entry.getValue();
      if (null != code && !"".equals(code)) {
        String[] paras = code.split(ParameterList.SPLITKEY);
        for (String para : paras) {
          paraList.add(para);
        }
      }
      if (paraList.size() > 0) {
        paraMap.put(entry.getKey(), paraList);
      }
    }
    return paraMap;
  }

  /**
   * 批量组织 获取相同参数在不同组织下的参数值
   * 
   * @param pk_org
   * @param initCode
   * @return
   * @throws BusinessException
   */
  public static Map<String, List<String>> queryBatchParaValues(
      String[] pk_orgs, String initCode) {
    if (pk_orgs.length == 0) {
      return null;
    }
    Map<String, List<String>> paraMap = new HashMap<String, List<String>>();
    for (String pk_org : pk_orgs) {
      List<String> paraList = new ArrayList<String>();
      String code = SysParaInitQuery.getParaString(pk_org, initCode);
      if (null != code && !"".equals(code)) {
        String[] paras = code.split(ParameterList.SPLITKEY);
        for (String para : paras) {
          paraList.add(para);
        }
      }
      if (paraList.size() > 0) {
        paraMap.put(pk_org, paraList);
      }
    }
    return paraMap;
  }

  /**
   * 批量组织 批量参数 必须一一对应
   * 
   * @param pk_orgs
   * @param initCodes
   * @return
   * @throws BusinessException
   */
  public static Map<String, List<String>> queryBatchParaValues(
      String[] pk_orgs, String[] initCodes) {
    if (pk_orgs.length == 0 || initCodes.length == 0) {
      return null;
    }
    Map<String, List<String>> paraMap = new HashMap<String, List<String>>();
    for (int i = 0; i < pk_orgs.length; i++) {
      List<String> paraList = new ArrayList<String>();
      String code = SysParaInitQuery.getParaString(pk_orgs[i], initCodes[i]);
      if (null != code && !"".equals(code)) {
        String[] paras = code.split(ParameterList.SPLITKEY);
        for (String para : paras) {
          paraList.add(para);
        }
      }
      if (paraList.size() > 0) {
        paraMap.put(initCodes[i], paraList);
      }
    }
    return paraMap;
  }
}
