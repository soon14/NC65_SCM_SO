package nc.vo.so.m4331.rule;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class UnitChangeRateRule {

  private IKeyValue keyValue;

  /**
   * UnitChangeRateHandler 的构造子
   * 
   * @param keyValue
   */
  public UnitChangeRateRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 方法功能描述：简计算业务单位和主单位之间换算率。
   * 
   * @author 祝会征
   * @time 2010-6-7 下午03:25:00
   */
  public void calcAstChangeRate(int index) {
    String astChangeRate = this.calcChangeRate(index, DeliveryBVO.CASTUNITID);
    this.keyValue.setBodyValue(index, DeliveryBVO.VCHANGERATE, astChangeRate);
  }

  /**
   * 方法功能描述：计算报价单位和主单位之间换算率。
   * 
   * @author 祝会征
   * @time 2010-6-7 下午03:25:51
   */
  public void calcQTChangeRate(int index) {
    String qtChangeRate = this.calcChangeRate(index, DeliveryBVO.CQTUNITID);
    this.keyValue.setBodyValue(index, DeliveryBVO.VQTUNITRATE, qtChangeRate);
  }

  /**
   * 方法功能描述：业务单位和主单位是否固定换算率。
   * 
   * @author 祝会征
   * @time 2010-6-7 下午03:27:07
   */
  public boolean isAstFixedRate(int index) {
    return this.isFixedRate(index, DeliveryBVO.CASTUNITID);
  }

  /**
   * 方法功能描述：报价单位和主单位是否固定换算率。
   * 
   * @author 祝会征
   * @time 2010-6-7 下午03:27:48
   */
  public boolean isQtFixedRate(int index) {
    return this.isFixedRate(index, DeliveryBVO.CQTUNITID);
  }

  /**
   * 方法功能描述：计算传入编辑单位和主单位之间换算率。
   * 
   * @author 祝会征
   * @time 2010-6-7 下午03:26:19
   */
  private String calcChangeRate(int index, String editunitkey) {
    String material =
        this.keyValue.getBodyStringValue(index, DeliveryBVO.CMATERIALVID);
    String unit = this.keyValue.getBodyStringValue(index, DeliveryBVO.CUNITID);
    String editunit = this.keyValue.getBodyStringValue(index, editunitkey);
    String changerate = null;
    changerate =
        MaterialPubService.queryMainMeasRateByMaterialAndMeasdoc(material,
            unit, editunit);
    return changerate;

  }

  /**
   * 方法功能描述：传入单位和主单位是否固定换算率。
   * 
   * @author 祝会征
   * @time 2010-6-7 下午03:28:27
   */
  private boolean isFixedRate(int index, String unitkey) {

    String material =
        this.keyValue.getBodyStringValue(index, DeliveryBVO.CMATERIALVID);
    String unit = this.keyValue.getBodyStringValue(index, DeliveryBVO.CUNITID);
    String tounit = this.keyValue.getBodyStringValue(index, unitkey);

    boolean isfixed = false;
    isfixed =
        MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(material, unit,
            tounit);

    return isfixed;
  }
}
