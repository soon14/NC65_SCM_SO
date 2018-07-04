package nc.vo.so.m32.rule;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m35.paravo.OffsetParaVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.util.SOSysParaInitUtil;

/**
 * 费用冲抵接口数据收集
 * 将分配关系处理到卡片VO上
 * 
 * @since 6.0
 * @version 2010-12-10 下午01:35:46
 * @author 么贵敬
 */
public class UniteArsubRule {

  /** 用于计算冲抵比例的常量 */
  private static final UFDouble HUNDRES = new UFDouble(100);

  /** 根据key获得和设置对象属性值类 */
  private IKeyValue keyValue;

  /**
   * 构造方法
   * 
   * @param keyValue 根据key获得和设置对象属性值类
   */
  public UniteArsubRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 根据合并开票的分配关系写到订单VO
   * 
   * @param distributeMap 分配关系（行索引为key冲抵金额为Value）
   */
  public void distributeMapToVO(Map<Integer, UFDouble> distributeMap) {
    for (Map.Entry<Integer, UFDouble> entry : distributeMap.entrySet()) {
      // 这里根据分配的关系改变订单VO
      // “thissub”为这次合并开票金额
      int row = entry.getKey().intValue();
      UFDouble thissub = entry.getValue();
      // “origtaxmny”为原价税合计
      UFDouble origtaxmny =
          this.keyValue.getBodyUFDoubleValue(row, SaleInvoiceBVO.NORIGTAXMNY);
      // 订单表体合并开票金额 = 原合并开票金额 + 本次合并开票金额
      // “oldsubmny”为原合并开票金额
      UFDouble oldsubmny =
          this.keyValue.getBodyUFDoubleValue(row, SaleInvoiceBVO.NORIGSUBMNY);
      UFDouble submny = MathTool.add(oldsubmny, thissub);
      this.keyValue.setBodyValue(row, SaleInvoiceBVO.NORIGSUBMNY, submny);
      // 订单表体价税合计 = 原价税合计 - 本次合并开票金额
      UFDouble nowtaxmny = MathTool.sub(origtaxmny, thissub);
      this.keyValue.setBodyValue(row, SaleInvoiceBVO.NORIGTAXMNY, nowtaxmny);
    }
  }

  // /**
  // * 收集销售费用单使用的接口数据用于拼接默认sql和按照规则进行分配
  // *
  // * @return 接口vo
  // */
  // public OffsetOrUniteVO getinterfaceData() {
  // OffsetOrUniteVO offsetvo = new OffsetOrUniteVO();
  // offsetvo.setPk_org(this.keyValue.getHeadStringValue(SaleInvoiceHVO.PK_ORG));
  // offsetvo.setPk_group(this.keyValue
  // .getHeadStringValue(SaleInvoiceHVO.PK_GROUP));
  // offsetvo.setInvoicecust(this.keyValue
  // .getHeadStringValue(SaleInvoiceHVO.CINVOICECUSTID));
  // offsetvo.setCorigcurrencyid(this.keyValue
  // .getHeadStringValue(SaleInvoiceHVO.CORIGCURRENCYID));
  //
  // Map<Integer, OffsetOrUniteBVO> bvomap =
  // new HashMap<Integer, OffsetOrUniteBVO>();
  // if (MathTool.isZero(this.getSO15())) {
  // ExceptionUtils
  // .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
  // .getStrByID("4006008_0", "04006008-0065")/*@res "冲抵比例为0不允许冲抵"*/);
  // }
  // for (int i = 0; i < this.keyValue.getBodyCount(); i++) {
  // UFBoolean discountflag =
  // this.keyValue.getBodyUFBooleanValue(i, SaleInvoiceBVO.BDISCOUNTFLAG);
  // UFBoolean laborflag =
  // this.keyValue.getBodyUFBooleanValue(i, SaleInvoiceBVO.BLABORFLAG);
  // UFBoolean largessflag =
  // this.keyValue.getBodyUFBooleanValue(i, SaleInvoiceBVO.BLARGESSFLAG);
  // // 折扣、劳务类物料和赠品不合并开票
  // if (discountflag.booleanValue() || laborflag.booleanValue()
  // || largessflag.booleanValue()) {
  // continue;
  // }
  // // 价税合计为空或0的不合并开票
  // UFDouble origtaxmny =
  // this.keyValue.getBodyUFDoubleValue(i, SaleInvoiceBVO.NORIGTAXMNY);
  // if (null == origtaxmny || origtaxmny.compareTo(UFDouble.ZERO_DBL) == 0) {
  // continue;
  // }
  // // 设置接口VO子实体
  // OffsetOrUniteBVO unitebvo = this.getBVO(i);
  //
  // bvomap.put(Integer.valueOf(i), unitebvo);
  // }
  // offsetvo.setHmbody(bvomap);
  // return offsetvo;
  // }

  /**
   * 收集销售费用单使用的接口数据
   * 用于拼接默认sql和按照规则进行分配
   * 
   * @return
   */
  public Map<Integer, OffsetParaVO> getinterfaceDatas() {
    if (MathTool.isZero(this.getSO15())) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006008_0", "04006008-0066")/* @res "发票冲抵比例为0" */);
    }
    Map<Integer, OffsetParaVO> offsetvomap =
        new HashMap<Integer, OffsetParaVO>();
    for (int i = 0; i < this.keyValue.getBodyCount(); i++) {
      UFBoolean discountflag =
          this.keyValue.getBodyUFBooleanValue(i, SaleInvoiceBVO.BDISCOUNTFLAG);
      UFBoolean laborflag =
          this.keyValue.getBodyUFBooleanValue(i, SaleInvoiceBVO.BLABORFLAG);
      UFBoolean largessflag =
          this.keyValue.getBodyUFBooleanValue(i, SaleInvoiceBVO.BLARGESSFLAG);
      // 折扣、劳务类物料和赠品不冲抵
      if (discountflag.booleanValue() || laborflag.booleanValue()
          || largessflag.booleanValue()) {
        continue;
      }
      // 价税合计为空或0的不冲抵
      UFDouble origtaxmny =
          this.keyValue.getBodyUFDoubleValue(i, SaleInvoiceBVO.NORIGTAXMNY);
      if (null == origtaxmny || origtaxmny.compareTo(UFDouble.ZERO_DBL) == 0) {
        continue;
      }
      // 设置接口VO子实体
      OffsetParaVO bvo = this.getBVOs(i);
      offsetvomap.put(Integer.valueOf(i), bvo);
    }
    return offsetvomap;
  }

  /**
   * 返回是否自动合并开票
   * 
   * @return 返回是否自动合并开票
   */
  public UFBoolean getSO14() {
    UFBoolean so14 = null;

    so14 = SOSysParaInitUtil.getSO14(this.getPk_org());

    return so14 == null ? UFBoolean.FALSE : so14;
  }

  /**
   * 返回合并开票比例
   * 
   * @return 返回合并开票比例
   */
  public UFDouble getSO15() {
    UFDouble so15 = null;

    so15 = SOSysParaInitUtil.getSO15(this.getPk_org());

    return so15 == null ? UFDouble.ZERO_DBL : so15.div(UniteArsubRule.HUNDRES);
  }

  // private OffsetOrUniteBVO getBVO(int i) {
  // OffsetOrUniteBVO bvo = new OffsetOrUniteBVO();
  // bvo.setOrdercusts(this.keyValue.getBodyStringValue(i,
  // SaleInvoiceBVO.CORDERCUSTID));
  // bvo.setOrdertrantype(this.keyValue.getBodyStringValue(i,
  // SaleInvoiceBVO.VFIRSTTRANTYPE));
  // bvo.setOrigsubmny(this.keyValue.getBodyUFDoubleValue(i,
  // SaleInvoiceBVO.NORIGSUBMNY));
  // bvo.setOrigtaxmny(this.keyValue.getBodyUFDoubleValue(i,
  // SaleInvoiceBVO.NORIGTAXMNY));
  // bvo.setProdline(this.keyValue.getBodyStringValue(i,
  // SaleInvoiceBVO.CPRODLINEID));
  // bvo.setSaleorg(this.keyValue.getHeadStringValue(SaleInvoiceHVO.PK_ORG));
  // bvo.setSettleorg(this.keyValue.getHeadStringValue(SaleInvoiceHVO.PK_ORG));
  // return bvo;
  // }

  private OffsetParaVO getBVOs(int i) {
    OffsetParaVO vo = new OffsetParaVO();
    // 销售组织
    vo.setSaleorg(this.keyValue
        .getBodyStringValue(i, SaleInvoiceBVO.CSALEORGID));
    // 集团
    vo.setPk_group(this.keyValue.getHeadStringValue(SaleInvoiceHVO.PK_GROUP));
    // 开票客户
    vo.setInvoicecusts(this.keyValue
        .getHeadStringValue(SaleInvoiceHVO.CINVOICECUSTID));
    // 币种
    vo.setCorigcurrencyid(this.keyValue
        .getHeadStringValue(SaleInvoiceHVO.CORIGCURRENCYID));
    // 订单客户
    vo.setOrdercusts(this.keyValue.getBodyStringValue(i,
        SaleInvoiceBVO.CORDERCUSTID));
    // 订单交易类型
    vo.setOrdertrantype(this.keyValue.getBodyStringValue(i,
        SaleInvoiceBVO.VFIRSTTRANTYPE));
    vo.setOrigsubmny(this.keyValue.getBodyUFDoubleValue(i,
        SaleInvoiceBVO.NORIGSUBMNY));
    vo.setOrigtaxmny(this.keyValue.getBodyUFDoubleValue(i,
        SaleInvoiceBVO.NORIGTAXMNY));
    vo.setProdline(this.keyValue.getBodyStringValue(i,
        SaleInvoiceBVO.CPRODLINEID));
    vo.setSettleorg(this.getPk_org());
    return vo;
  }

  private String getPk_org() {
    return this.keyValue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
  }

}
