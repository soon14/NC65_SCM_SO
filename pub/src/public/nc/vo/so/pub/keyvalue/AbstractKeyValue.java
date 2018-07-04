package nc.vo.so.pub.keyvalue;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>抽象类，根据key来获取和设置对象属性值
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since 上一版本号 5.6
 * @author 冯加滨
 * @time 2010-4-20 下午08:22:04
 */
public abstract class AbstractKeyValue implements IKeyValue {
  /**
   * <p>
   * <b>本类主要完成以下功能：</b>
   * <ul>
   * <li>行状态枚举
   * </ul>
   * <p>
   * 
   * @version 本版本号 6.0
   * @since 上一版本号 5.6
   * @author
   * @time 2010-4-21 上午10:15:05
   */
  public enum RowStatus {
    /**
     * 删除
     */
    DELETED,

    /**
     * 新增
     */
    NEW,

    /**
     * 无改变
     */
    UNCHANGED,

    /**
     * 更新
     */
    UPDATED,
  }

  @Override
  public abstract int getBodyCount();

  @Override
  public Integer getBodyIntegerValue(int index, String key) {
    Object value = this.getBodyValue(index, key);
    return ValueUtils.getInteger(value);
  }

  @Override
  public String getBodyStringValue(int index, String key) {
    Object value = this.getBodyValue(index, key);
    return ValueUtils.getString(value);
  }

  @Override
  public UFBoolean getBodyUFBooleanValue(int index, String key) {
    Object value = this.getBodyValue(index, key);
    return ValueUtils.getUFBoolean(value);
  }

  @Override
  public UFDate getBodyUFDateValue(int index, String key) {
    Object value = this.getBodyValue(index, key);

    return ValueUtils.getUFDate(value);
  }

  @Override
  public UFDouble getBodyUFDoubleValue(int index, String key) {
    Object value = this.getBodyValue(index, key);

    return ValueUtils.getUFDouble(value);
  }

  @Override
  public abstract Object getBodyValue(int index, String key);

  @Override
  public Integer getHeadIntegerValue(String key) {
    Object value = this.getHeadValue(key);

    return ValueUtils.getInteger(value);
  }

  @Override
  public String getHeadStringValue(String key) {
    Object value = this.getHeadValue(key);

    return ValueUtils.getString(value);
  }

  @Override
  public UFBoolean getHeadUFBooleanValue(String key) {
    Object value = this.getHeadValue(key);

    return ValueUtils.getUFBoolean(value);
  }

  @Override
  public UFDate getHeadUFDateValue(String key) {
    Object value = this.getHeadValue(key);

    return ValueUtils.getUFDate(value);
  }

  @Override
  public UFDouble getHeadUFDoubleValue(String key) {
    Object value = this.getHeadValue(key);

    return ValueUtils.getUFDouble(value);
  }

  @Override
  public abstract Object getHeadValue(String key);

  @Override
  public abstract RowStatus getRowStatus(int index);

  @Override
  public abstract void setBodyValue(int index, String key, Object value);

  @Override
  public abstract void setHeadValue(String key, Object value);

}
