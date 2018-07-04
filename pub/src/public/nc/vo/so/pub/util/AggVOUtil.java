package nc.vo.so.pub.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.trade.checkrule.VOChecker;

import nc.bs.logging.Logger;

/**
 * 处理聚合VO工具类
 * 
 * @since 6.3
 * @version 2013-11-22 下午03:49:03
 * @author liujingn
 */
public class AggVOUtil {

  private AggVOUtil() {
    super();
  }

  /**
   * 
   * 方法功能描述：将主子VO数组，组合成AGGVO。
   * <p>
   * <b>参数说明</b>
   * 
   * @param <T>
   * @param headers 头
   * @param items　体
   * @param billClass　aggvo的类
   * @param headIdName　头体相关联的外键
   * @return
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-5-17 下午02:24:05
   */
  public static <T extends AggregatedValueObject> T[] createAggVO(
      CircularlyAccessibleValueObject[] headers,
      CircularlyAccessibleValueObject[] items, Class<T> billClass,
      String headIdName) {
    Map<String, T> newBillMap = new HashMap<String, T>();
    Set<String> dealtIds = new HashSet<String>();
    try {
      for (CircularlyAccessibleValueObject head : headers) {
        String hid = head.getPrimaryKey();
        if (newBillMap.containsKey(hid)) {
          continue;
        }

        // 建新的BILLVO
        T newBill = Constructor.construct(billClass);
        newBill.setParentVO(head);
        newBillMap.put(hid, newBill);
        if (null == items) {
          continue;
        }
        List<CircularlyAccessibleValueObject> children =
            new ArrayList<CircularlyAccessibleValueObject>();
        // 找表体VO
        for (CircularlyAccessibleValueObject item : items) {
          if (!dealtIds.contains(item.getPrimaryKey())
              && hid.equals(item.getAttributeValue(headIdName))) {
            children.add(item);
            dealtIds.add(item.getPrimaryKey());
          }
        }
        if (0 < children.size()) {
          CircularlyAccessibleValueObject[] childrenAry =
              new ListToArrayTool<CircularlyAccessibleValueObject>()
                  .convertToArray(children);
          newBill.setChildrenVO(childrenAry);
        }
      }
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }

    T[] bills = Constructor.construct(billClass, newBillMap.size());
    return newBillMap.values().toArray(bills);
  }

  /**
   * 
   * 方法功能描述：由分裂的表头和表体生成聚合VO。
   * <p>
   * <b>参数说明</b>
   * 
   * @param headers
   * @param items
   * @return
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-1-14 下午03:34:48
   */
  public static IBill[] createBillVO(ISuperVO[] headers, ISuperVO[] items,
      Class<? extends IBill> billClass) {
    Map<String, IBill> newBillMap = new HashMap<String, IBill>();
    IVOMeta childMeta = null;
    String headIdName =
        headers[0].getMetaData().getPrimaryAttribute().getName();
    Set<String> deltIds = new HashSet<String>();
    for (ISuperVO head : headers) {
      String hid = head.getPrimaryKey();
      if (newBillMap.containsKey(hid)) {
        continue;
      }

      // 建新的BILLVO
      IBill newBill = Constructor.construct(billClass);
      newBill.setParent(head);
      List<ISuperVO> children = new ArrayList<ISuperVO>();
      childMeta =
          childMeta == null ? newBill.getMetaData().getChildren()[0]
              : childMeta;
      // 找表体VO
      for (ISuperVO item : items) {
        if (!deltIds.contains(item.getPrimaryKey())
            && hid.equals(item.getAttributeValue(headIdName))) {
          children.add(item);
          deltIds.add(item.getPrimaryKey());
        }
      }
      if (0 < children.size()) {
        ISuperVO[] childrenAry =
            new ListToArrayTool<ISuperVO>().convertToArray(children);
        newBill.setChildren(childMeta, childrenAry);
      }
      newBillMap.put(hid, newBill);
    }

    IBill[] bills = Constructor.construct(billClass, newBillMap.size());
    return newBillMap.values().toArray(bills);
  }

  /**
   * 
   * 方法功能描述：将聚合VO的表体生成［表体主键，表体］的Map。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2009-12-31 下午03:15:17
   */
  @SuppressWarnings("unchecked")
  public static <E extends CircularlyAccessibleValueObject> Map<String, E> createItemMap(
      AggregatedValueObject[] vos) {
    if (ArrayUtil.isEmpty(vos)) {
      return null;
    }
    Map<String, E> retMap = new HashMap<String, E>();
    for (AggregatedValueObject vo : vos) {
      if (null == vo || ArrayUtil.isEmpty(vo.getChildrenVO())) {
        continue;
      }
      for (CircularlyAccessibleValueObject item : vo.getChildrenVO()) {
        if (null == item) {
          continue;
        }
        String bid = null;
        try {
          bid = item.getPrimaryKey();
        }
        catch (BusinessException e) {
          Logger.error("Find the primary key error:" + e.getMessage());
        }
        if (!StringUtil.isEmptyTrimSpace(bid)) {
          retMap.put(bid, (E) item);
        }
      }
    }
    return retMap;
  }

  /**
   * 
   * 方法功能描述：将聚合VO生成[表头主键，VO]的Map
   * <p>
   * <b>参数说明</b>
   * 
   * @param <E>
   * @param vos
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-6 上午10:02:57
   */
  @SuppressWarnings("unchecked")
  public static <E extends AggregatedValueObject> Map<String, E> createVOMap(
      AggregatedValueObject[] vos) {
    if (ArrayUtil.isEmpty(vos)) {
      return null;
    }

    Map<String, E> retMap = new HashMap<String, E>();
    for (AggregatedValueObject vo : vos) {
      if (null == vo || null == vo.getParentVO()) {
        continue;
      }

      String id = null;
      try {
        id = vo.getParentVO().getPrimaryKey();
      }
      catch (BusinessException e) {
        Logger.error("Find the primary key error:" + e.getMessage());
      }

      if (!StringUtil.isEmptyTrimSpace(id)) {
        retMap.put(id, (E) vo);
      }
    }

    return retMap;
  }

  /**
   * 
   * 方法功能描述：获取全vo中的itemvo的组合
   * <p>
   * <b>参数说明</b>
   * 
   * @param <T>
   * @param VOs
   * @return
   *         <p>
   * @since 6.0
   * @author tianft
   * @time 2010-4-11 下午01:54:44
   */
  @SuppressWarnings("unchecked")
  public static <T extends CircularlyAccessibleValueObject> T[] getCombinItemVOs(
      AggregatedValueObject[] vOs) {
    if (ArrayUtil.isEmpty(vOs)) {
      return null;
    }
    T[] itemVOs = null;
    for (AggregatedValueObject vo : vOs) {
      itemVOs = ArrayUtil.combinArrays(itemVOs, (T[]) vo.getChildrenVO());
    }
    return itemVOs;
  }

  @SuppressWarnings("unchecked")
  public static <T extends CircularlyAccessibleValueObject> T[] getCombinItemVOs(
      AggregatedValueObject[] vOs, T[] itemVOs) {
    if (ArrayUtil.isEmpty(vOs) && ArrayUtil.isEmpty(itemVOs)) {
      return null;
    }
    T[] resutlItemVOs =
        (T[]) ArrayUtil.combinArrays(itemVOs, AggVOUtil.getCombinItemVOs(vOs));

    return resutlItemVOs;
  }

  /**
   * 
   * 方法功能描述：得到VO数组中按某个域得到的值数组
   * 返回的数组中不含NULL或空串，并过滤重复值
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaPlan VO数组
   * @param sField 域名称
   * @param sFieldClass 域类型
   * @return
   *         <p>
   * @since 6.0
   * @author 王印芬
   * @time 2010-4-13 下午03:39:53
   */
  @SuppressWarnings("unchecked")
  public static <T extends Object> T[] getDistinctFieldArray(
      CircularlyAccessibleValueObject[] voaPlan, String sField,
      Class<T> sFieldClass) {
    if (null == voaPlan || null == sField) {
      return null;
    }

    Set<T> mapValue = new HashSet<T>();
    T oValue = null;
    int iLen = voaPlan.length;
    for (int j = 0; j < iLen; j++) {

      oValue = (T) voaPlan[j].getAttributeValue(sField);

      // 不含空及重复键
      if (!VOChecker.isEmpty(oValue) && !mapValue.contains(oValue)) {
        mapValue.add(oValue);
      }
    }

    iLen = mapValue.size();
    if (iLen == 0) {
      return null;
    }

    T[] oaRet = null;
    try {

      oaRet = Constructor.declareArray(sFieldClass, iLen);
      // oaRet = (T[])Array.newInstance( sFieldClass, iLen ) ;
    }
    catch (Exception e) {
      Logger.error("nc.vo.pu.pub.util.AggVOUtil." + "getDistinctFieldArray"
          + "(CircularlyAccessibleValueObject [], String, Class)类型转换错误");
      return null;
    }

    mapValue.toArray(oaRet);
    return oaRet;
  }

  /**
   * 得到VO数组的头中按某个域的值数组
   * 返回的数组中不含NULL或空串，并过滤重复值
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaCheck 用于检查的VO数组.每个VO的头体均饱满，无空值
   * @param sField 域
   * @param sFieldClass 域的类型
   * @param bNullAndDuplicateIncluded 是否包括空值及重复值
   * @return
   *         <p>
   * @author wangyf
   * @time 2009-6-29 下午03:26:59
   */
  public static <T extends Object> T[] getDistinctHeadFieldArray(
      AggregatedValueObject[] voaCheck, String sField, Class<T> sFieldClass) {

    return AggVOUtil.getFieldArray(voaCheck, true, sField, sFieldClass, false);

  }

  /**
   * 得到VO数组的体中按某个域的值数组
   * 返回的数组中不含NULL或空串，并过滤重复值
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaCheck 用于检查的VO数组.每个VO的头体均饱满，无空值
   * @param sField 域
   * @param sFieldClass 域的类型
   * @param bNullAndDuplicateIncluded 是否包括空值及重复值
   * @return
   *         <p>
   * @author wangyf
   * @time 2009-6-29 下午03:26:59
   */
  public static <T extends Object> T[] getDistinctItemFieldArray(
      AggregatedValueObject[] voaCheck, String sField, Class<T> sFieldClass) {

    return AggVOUtil.getFieldArray(voaCheck, false, sField, sFieldClass, false);

  }

  /**
   * 
   * 方法功能描述：得到单据的表头主键数组。
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param bills
   * @return bills的表头主键数组 可能为null
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2009-12-29 上午11:23:45
   */
  public static String[] getPrimaryKeys(IBill[] bills) {
    if (ArrayUtil.isEmpty(bills)) {
      return null;
    }
    List<String> ids = new ArrayList<String>();
    for (IBill bill : bills) {
      ids.add(bill.getPrimaryKey());
    }
    return ids.toArray(new String[ids.size()]);
  }

  /**
   * 
   * 方法功能描述：筛选出聚合VO数组中为新增状态的VO
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return
   *         <p>
   * @since 6.0
   * @author linsf
   * @time 2010-2-24 下午04:16:20
   */
  public static IBill[] pickupInsertVO(IBill[] vos) {
    List<IBill> newVos = new ArrayList<IBill>();
    for (IBill vo : vos) {
      if (VOStatus.NEW == vo.getParent().getStatus()) {
        newVos.add(vo);
      }
    }
    if (0 == newVos.size()) {
      return null;
    }
    return new ListToArrayTool<IBill>().convertToArray(newVos);
  }

  /**
   * 
   * 方法功能描述：筛选出聚合VO数组中为修改状态的VO
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return
   *         <p>
   * @since 6.0
   * @author linsf
   * @time 2010-2-24 下午04:18:14
   */
  public static IBill[] pickupUpdateVO(IBill[] vos) {
    List<IBill> newVos = new ArrayList<IBill>();
    for (IBill vo : vos) {
      if (VOStatus.UPDATED == vo.getParent().getStatus()) {
        newVos.add(vo);
      }
    }
    if (0 == newVos.size()) {
      return null;
    }
    return new ListToArrayTool<IBill>().convertToArray(newVos);
  }

  /**
   * 
   * 方法功能描述：调整指定VO数组，按照参照的VO数组顺序排列。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos　要排序的VO数组 两个VO数组必须有相同长度，且相同主键
   * @param referVos　参照的VO数组
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-4-28 上午08:56:46
   */
  public static <T extends AggregatedValueObject> void reSortVO(T[] vos,
      T[] referVos) {
    Map<String, T> vomap = AggVOUtil.createVOMap(vos);
    for (int i = 0; i < referVos.length; i++) {
      String pk = null;
      try {
        pk = referVos[i].getParentVO().getPrimaryKey();
      }
      catch (BusinessException e) {
        Logger.error("Find the primary key error:" + e.getMessage());

      }
      vos[i] = vomap.get(pk);
    }
  }

  /**
   * 得到VO数组中按某个域得到的值数组
   * 返回的数组中不含NULL或空串，并过滤重复值
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaCheck 用于检查的VO数组.每个VO的头体均饱满，无空值
   * @param bHead 如果是从头VO中取数组，则传true；如果是体，传false.
   * @param sField 域
   * @param sFieldClass 域的类型
   * @param bNullAndDuplicateIncluded 是否包括空值及重复值
   * @return
   *         <p>
   * @author wangyf
   * @time 2009-6-29 下午03:26:59
   */
  @SuppressWarnings("unchecked")
  private static <T extends Object> T[] getFieldArray(
      AggregatedValueObject[] voaCheck, boolean bHead, String sField,
      Class<T> sFieldClass, boolean bNullAndDuplicateIncluded) {
    if (voaCheck == null || sField == null) {
      return null;
    }
    int iLen = voaCheck.length;
    Object oValue = null;
    CircularlyAccessibleValueObject[] voaItem = null;
    int iBodyLen = 0;
    List<Object> listValue = new ArrayList<Object>();
    for (int i = 0; i < iLen; i++) {
      if (voaCheck[i] == null) {
        continue;
      }
      if (bHead) {

        oValue = voaCheck[i].getParentVO().getAttributeValue(sField);

        if (bNullAndDuplicateIncluded) {
          listValue.add(oValue);
        }
        else {
          // 含空及重复键
          if (!StringUtil.isEmptyTrimSpace(oValue)
              && !listValue.contains(oValue)) {
            listValue.add(oValue);
          }
        }
      }
      else {
        voaItem = voaCheck[i].getChildrenVO();
        if (voaItem != null) {
          iBodyLen = voaItem.length;
          for (int j = 0; j < iBodyLen; j++) {
            oValue = voaItem[j].getAttributeValue(sField);
            if (bNullAndDuplicateIncluded) {
              listValue.add(oValue);
            }
            else {
              // 含空及重复键
              if (!StringUtil.isEmptyTrimSpace(oValue)
                  && !listValue.contains(oValue)) {
                listValue.add(oValue);
              }
            }
          }
        }
      }

    }
    iLen = listValue.size();
    if (iLen == 0) {
      return null;
    }

    T[] oaRet = null;
    try {
      oaRet = (T[]) Array.newInstance(sFieldClass, iLen);
    }
    catch (Exception e) {
      Logger.error("nc.vo.po.pub.OrderPubVO.getAllFieldArray(OrderVO [], "
          + "Class, String, Class, boolean)类型转换错误");
      return null;
    }
    listValue.toArray(oaRet);
    return oaRet;

  }

  /**
   * 针对两个相同类型，数据不同的VO做合并处理。
   * 
   * 把目的VO的数据填充到来源VO中，如果来源VO中字段值为空则填充,否则不填充
   * 
   * @param srcvos 来源VO
   * @param destvos 目的VO
   * @return 合并后VO
   */
  public static AggregatedValueObject[] combinBillVO(
      AggregatedValueObject[] srcvos, AggregatedValueObject[] destvos) {
    int j = 0;
    for (AggregatedValueObject srcvo : srcvos) {
      String[] headnames = srcvo.getParentVO().getAttributeNames();
      CircularlyAccessibleValueObject srcheadvo = srcvo.getParentVO();
      CircularlyAccessibleValueObject destheadvo = destvos[j].getParentVO();
      for (String headname : headnames) {
        if (srcheadvo.getAttributeValue(headname) == null) {
          srcheadvo.setAttributeValue(headname,
              destheadvo.getAttributeValue(headname));
        }
      }
      CircularlyAccessibleValueObject[] srcbodyvos = srcvo.getChildrenVO();
      CircularlyAccessibleValueObject[] destbodyvos =
          destvos[j].getChildrenVO();
      if (srcbodyvos == null || srcbodyvos.length == 0) {
        return srcvos;
      }
      int i = 0;
      for (CircularlyAccessibleValueObject srcbodyvo : srcbodyvos) {
        String[] srcbodynames = srcbodyvo.getAttributeNames();
        for (String srcbodyname : srcbodynames) {
          if (srcbodyvo.getAttributeValue(srcbodyname) == null) {
            srcbodyvo.setAttributeValue(srcbodyname,
                destbodyvos[i].getAttributeValue(srcbodyname));
          }
        }
        i++;
      }
      j++;
    }
    return srcvos;
  }

}
