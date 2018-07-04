/**
 * 
 */
package nc.vo.so.m30.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * @author wangzym
 * @version 2017年3月2日 上午10:02:00
 * 为单价取整取整新建枚举类
 */
public class Djqz extends MDEnum {

	/**
	 * @param enumValue
	 */
	public Djqz(IEnumValue enumValue) {
		super(enumValue);
		// TODO 自动生成的构造函数存根
	}

	// 是
	public static final Djqz YES = MDEnum.valueOf(Djqz.class,
			Integer.valueOf(0));

	// 否
	public static final Djqz NO = MDEnum
			.valueOf(Djqz.class, Integer.valueOf(1));

	public Integer getIntegerValue() {
		return Integer.valueOf(this.value().toString());
	}

	public int getIntValue() {
		return Integer.parseInt(this.value().toString());
	}

	public String getStringValue() {
		return this.value().toString();
	}

}
