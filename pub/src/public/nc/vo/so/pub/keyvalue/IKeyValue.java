package nc.vo.so.pub.keyvalue;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.pub.keyvalue.AbstractKeyValue.RowStatus;

/**
 * 这里指的表头表体都是vo的表头表体
 * 
 * @since 6.0
 * @version 2011-8-1 下午02:35:05
 * @author zhangcheng
 */
public interface IKeyValue {
  /**
   * 方法功能描述：返回表体行数。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author 冯加滨
   * @time 2010-4-21 上午10:17:36
   */
  int getBodyCount();

  Integer getBodyIntegerValue(int index, String key);

  /**
   * 方法功能描述：返回表体index行字段key的String类型值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param index
   * @param key
   * @return <p>
   * @author 冯加滨
   * @time 2010-4-21 上午11:22:28
   */
  String getBodyStringValue(int index, String key);

  /**
   * 方法功能描述：返回表体index行字段key的UFBoolean类型值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param index
   * @param key
   * @return <p>
   * @author 冯加滨
   * @time 2010-4-21 上午11:25:09
   */
  UFBoolean getBodyUFBooleanValue(int index, String key);

  UFDate getBodyUFDateValue(int index, String key);

  /**
   * 方法功能描述：返回表体index行字段key的UFDouble类型值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param index
   * @param key
   * @return <p>
   * @author 冯加滨
   * @time 2010-4-21 上午11:23:48
   */
  UFDouble getBodyUFDoubleValue(int index, String key);

  /**
   * 方法功能描述：返回表体index行字段key的值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param index
   * @param key
   * @return <p>
   * @author 冯加滨
   * @time 2010-4-21 上午10:16:03
   */
  Object getBodyValue(int index, String key);

  /**
   * 返回表头index行字段key的值
   * 
   * @param key
   * @return
   */
  Integer getHeadIntegerValue(String key);

  /**
   * 方法功能描述：返回表头字段key的String类型值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param key
   * @return <p>
   * @author 冯加滨
   * @time 2010-4-21 上午11:18:12
   */
  String getHeadStringValue(String key);

  /**
   * 方法功能描述：返回表头字段key的UFBoolean类型值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param key
   * @return <p>
   * @author 冯加滨
   * @time 2010-4-21 上午11:19:53
   */
  UFBoolean getHeadUFBooleanValue(String key);

  /**
   * 方法功能描述：返回表头字段key的UFDate类型值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param key
   * @return <p>
   * @author 冯加滨
   * @time 2010-4-21 上午11:19:53
   */
  UFDate getHeadUFDateValue(String key);

  /**
   * 方法功能描述：返回表头字段key的UFDouble类型值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param key
   * @return <p>
   * @author 冯加滨
   * @time 2010-4-21 上午11:21:12
   */
  UFDouble getHeadUFDoubleValue(String key);

  /**
   * 方法功能描述：返回表头字段key的值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param key
   * @return <p>
   * @author
   * @time 2010-4-21 上午10:15:31
   */
  Object getHeadValue(String key);

  /**
   * 方法功能描述：返回表体index行行状态。
   * <p>
   * <b>参数说明</b>
   * 
   * @param index
   * @return <p>
   * @author 冯加滨
   * @time 2010-4-21 上午10:17:51
   */
  RowStatus getRowStatus(int index);

  /**
   * 方法功能描述：设置表体index行字段key的值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param index
   * @param key
   * @param value
   *          <p>
   * @author 冯加滨
   * @time 2010-4-21 上午10:17:14
   */
  void setBodyValue(int index, String key, Object value);

  /**
   * 方法功能描述：设置表头key字段值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param key
   * @param value
   *          <p>
   * @author 冯加滨
   * @time 2010-4-21 上午10:16:47
   */
  void setHeadValue(String key, Object value);

}
