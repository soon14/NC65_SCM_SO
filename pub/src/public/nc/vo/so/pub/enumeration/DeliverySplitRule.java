package nc.vo.so.pub.enumeration;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;

/**
 * 发货单分单打印规则
 * 
 * @since 6.0
 * @version 2011-1-6 下午06:46:36
 * @author 祝会征
 */
public enum DeliverySplitRule {

  CINSTOCKORGID(DeliveryBVO.CINSTOCKORGID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0046")/*收货库存组织*/),
  CINSTORDOCID(DeliveryBVO.CINSTORDOCID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0047")/*收货仓库*/),
  CORDERCUSTID(DeliveryBVO.CORDERCUSTID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0048")/*客户*/),
  CRECEIVEADDDOCID(DeliveryBVO.CRECEIVEADDDOCID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0049")/*收货地点*/),
  CRECEIVEAREAID(DeliveryBVO.CRECEIVEAREAID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0050")/*收货地区*/),
  CRECEIVECUSTID(DeliveryBVO.CRECEIVECUSTID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0051")/*收货客户*/),
  CSENDSTOCKORGID(DeliveryBVO.CSENDSTOCKORGID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0052")/*发货库存组织*/),
  CSENDSTORDOCID(DeliveryBVO.CSENDSTORDOCID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0053")/*发货仓库*/),
  CSUPERCARGOID(DeliveryBVO.CSUPERCARGOID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0054")/*押运员*/),
  CTRANSCUSTID(DeliveryBVO.CTRANSCUSTID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0055")/*承运商*/),
  CVEHICLEID(DeliveryBVO.CVEHICLEID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0056")/*车辆*/),
  CVEHICLETYPEID(DeliveryBVO.CVEHICLETYPEID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0057")/*车型*/),
  VBILLCODE(DeliveryHVO.VBILLCODE, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0058")/*发货单号*/),
  DSENDDATE(DeliveryBVO.DSENDDATE, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0059")/*发货日期*/),
  CTRANTYPEID(DeliveryHVO.CTRANTYPEID, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0060")/*发货类型*/),
  CROWNO(DeliveryBVO.CROWNO, NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0", "04006004-0240")/*行号*/);
  // 单据类型
  private String key;

  // 单据名称
  private String name;

  private DeliverySplitRule(String key, String name) {
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
