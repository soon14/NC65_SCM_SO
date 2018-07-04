package nc.vo.so.pub.enumeration;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.so.pub.SOItemKey;

/**
 * SO21 销售询价触发条件
 * 
 * @since 6.0
 * @version 2011-6-14 上午10:03:18
 * @author fengjb
 */
public enum FindPriceTrigRule {

  CMATERIALVID(SOItemKey.CMATERIALVID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0208")/*物料*/),
  CQUALITYLEVELID(SOItemKey.CQUALITYLEVELID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0209")/*质量等级*/),
  CCHANNELTYPEID(SOItemKey.CCHANNELTYPEID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0175")/*渠道类型*/),
  CRECEIVEAREAID(SOItemKey.CRECEIVEAREAID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0050")/*收货地区*/),
  CCUSTOMERID(SOItemKey.CCUSTOMERID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0048")/*客户*/),
  CBALANCETYPEID(SOItemKey.CBALANCETYPEID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0210")/*结算方式*/),
  CORIGCURRENCYID(SOItemKey.CORIGCURRENCYID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0043")/*币种*/),
  CQTUNITID(SOItemKey.CQTUNITID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0211")/*计量单位*/),
  NQTUNITNUM(SOItemKey.NQTUNITNUM, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0116")/*数量*/),
  CTRANSPORTTYPEID(SOItemKey.CTRANSPORTTYPEID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0113")/*运输方式*/),
  CTRANTYPEID(SOItemKey.CTRANTYPEID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0174")/*销售订单类型*/),
  DBILLDATE(SOItemKey.DBILLDATE, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0212")/*单据日期*/),
  VFREE1(SOItemKey.VFREE1, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0157")/*自由辅助属性1*/),
  VFREE2(SOItemKey.VFREE2, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0159")/*自由辅助属性2*/),
  VFREE3(SOItemKey.VFREE3, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0160")/*自由辅助属性3*/),
  VFREE4(SOItemKey.VFREE4, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0161")/*自由辅助属性4*/),
  VFREE5(SOItemKey.VFREE5, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0162")/*自由辅助属性5*/),
  VFREE6(SOItemKey.VFREE6, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0163")/*自由辅助属性6*/),
  VFREE7(SOItemKey.VFREE7, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0164")/*自由辅助属性7*/),
  VFREE8(SOItemKey.VFREE8, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0165")/*自由辅助属性8*/),
  VFREE9(SOItemKey.VFREE9, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0166")/*自由辅助属性9*/),
  VFREE10(SOItemKey.VFREE10, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0158")/*自由辅助属性10*/);
  // 单据类型
  private String key;

  // 单据名称
  private String name;

  private FindPriceTrigRule(String key, String name) {
    this.key = key;
    this.name = name;
  }

  public String getKey() {
    return this.key;
  }

  public String getName() {
    return this.name;
  }

}
