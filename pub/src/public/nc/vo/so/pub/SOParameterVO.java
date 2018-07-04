package nc.vo.so.pub;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;

/**
 * 销售前后台动作处理参数类
 * <ol>
 * <li>单据VO
 * <li>单据VO数组
 * <li>业务检查Map
 * </ol>
 * 
 * @since 6.0
 * @version 2011-5-7 下午03:38:32
 * @author 刘志伟
 */
public class SOParameterVO implements Serializable {

  private static final long serialVersionUID = -3143042766360177639L;

  /** 单据VO */
  public AggregatedValueObject vo;

  /** 单据VO数组 */
  public AggregatedValueObject[] vos;

  /** 单据View */
  public CircularlyAccessibleValueObject view;

  /** 单据Views */
  public CircularlyAccessibleValueObject[] views;

  private Object userobject;

  /** 业务检查Map:ATP检查、 信用检查、超账期金额检查、超账期天数检查、超内控账期天数检查 */
  private Map<String, Boolean> businessCheckMap =
      new HashMap<String, Boolean>();

  public AggregatedValueObject getVo() {
    return this.vo;
  }

  public void setVo(AggregatedValueObject vo) {
    this.vo = vo;
  }

  public AggregatedValueObject[] getVos() {
    return this.vos;
  }

  public void setVos(AggregatedValueObject[] vos) {
    this.vos = vos;
  }

  public CircularlyAccessibleValueObject getView() {
    return this.view;
  }

  public void setView(CircularlyAccessibleValueObject view) {
    this.view = view;
  }

  public CircularlyAccessibleValueObject[] getViews() {
    return this.views;
  }

  public void setViews(CircularlyAccessibleValueObject[] views) {
    this.views = views;
  }

  public Map<String, Boolean> getBusinessCheckMap() {
    if (null == this.businessCheckMap) {
      this.businessCheckMap = new HashMap<String, Boolean>();
    }
    return this.businessCheckMap;
  }

  public void setBusinessCheckMap(Map<String, Boolean> businessCheckMap) {
    this.businessCheckMap = businessCheckMap;
  }

  public Object getUserObject() {
    return this.userobject;
  }

  public void setUserObject(Object userobject) {
    this.userobject = userobject;
  }
}
