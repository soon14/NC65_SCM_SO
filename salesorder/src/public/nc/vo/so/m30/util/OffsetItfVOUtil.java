/**
 * $文件说明$
 * 
 * @author 么贵敬
 * @version 6.0
 * @see
 * @since
 * @time 2010-11-12 上午11:59:09
 */
package nc.vo.so.m30.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m35.paravo.OffsetParaVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 费用冲抵接口数据收集
 * 将分配关系处理到卡片VO上
 * 
 * @since 6.0
 * @version 2010-12-10 上午10:16:01
 * @author 么贵敬
 */
public class OffsetItfVOUtil {

  /** 用于计算冲抵比例的常量 */
  // private static final UFDouble HUNDRES = new UFDouble(100);

  /** 根据key获得和设置对象属性值类 */
  private IKeyValue keyValue;

  /**
   * 构造方法
   * 
   * @param keyValue 根据key获得和设置对象属性值类
   */
  public OffsetItfVOUtil(
      IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 根据冲抵的分配关系写到订单VO
   * 
   * @param distributeMap 分配关系（行索引为key冲抵金额为Value）
   */
  public void distributeMapToVO(Map<Integer, UFDouble> distributeMap) {
    Set<Entry<Integer, UFDouble>> entrys = distributeMap.entrySet();
    for (Map.Entry<Integer, UFDouble> entry : entrys) {
      // 这里根据分配的关系改变订单VO
      // “thissub”为这次冲抵金额
      int row = entry.getKey().intValue();
      UFDouble thissubmny = entry.getValue();
      // “origtaxmny”为原价税合计
      UFDouble origtaxmny =
          this.keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NORIGTAXMNY);
      // 订单表体冲抵金额 = 原冲抵金额 + 本次冲抵金额
      // “oldsubmny”为原冲抵金额
      UFDouble oldsubmny =
          this.keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NORIGSUBMNY);
      UFDouble submny =
          MathTool.add(oldsubmny, thissubmny);
      this.keyValue.setBodyValue(row, SaleOrderBVO.NORIGSUBMNY, submny);
      // 订单表体价税合计 = 原价税合计 - 本次冲抵金额
      UFDouble nowtaxmny = MathTool.sub(origtaxmny, thissubmny);
      this.keyValue.setBodyValue(row, SaleOrderBVO.NORIGTAXMNY, nowtaxmny);
    }
  }

  /**
   * 收集销售费用单使用的接口数据用于拼接默认sql和按照规则进行分配
   * 
   * @return 接口vo
   */
  public Map<Integer, OffsetParaVO> getinterfaceData() {
    Map<Integer, OffsetParaVO> offsetvomap =
        new HashMap<Integer, OffsetParaVO>();
    for (int i = 0; i < this.keyValue.getBodyCount(); i++) {
      UFBoolean discountflag =
          this.keyValue.getBodyUFBooleanValue(i, SaleOrderBVO.BDISCOUNTFLAG);
      UFBoolean laborflag =
          this.keyValue.getBodyUFBooleanValue(i, SaleOrderBVO.BLABORFLAG);
      UFBoolean largessflag =
          this.keyValue.getBodyUFBooleanValue(i, SaleOrderBVO.BLARGESSFLAG);
      // 折扣、劳务类物料和赠品不冲抵
      if (discountflag.booleanValue() || laborflag.booleanValue()
          || largessflag.booleanValue()) {
        continue;
      }
      // 价税合计为空或0的不冲抵
      UFDouble origtaxmny =
          this.keyValue.getBodyUFDoubleValue(i, SaleOrderBVO.NORIGTAXMNY);
      if (null == origtaxmny || origtaxmny.compareTo(UFDouble.ZERO_DBL) == 0) {
        continue;
      }
      String settleorg =
          this.keyValue.getBodyStringValue(i, SaleOrderBVO.CSETTLEORGID);
      // if (MathTool.isZero(this.getSO15(settleorg))) {
      // continue;
      // }
      // 设置接口VO子实体
      OffsetParaVO bvo = this.getBVO(i, settleorg);
      offsetvomap.put(Integer.valueOf(i), bvo);
    }
    return offsetvomap;
  }

  /**
   * 收集订单表体数据
   * 
   * @param i 行索引
   * @param settleorg 财务组织
   * @return
   */
  private OffsetParaVO getBVO(int i, String settleorg) {
    OffsetParaVO vo = new OffsetParaVO();
    vo.setSaleorg(this.keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG));
    vo.setPk_group(this.keyValue.getHeadStringValue(SaleOrderHVO.PK_GROUP));
    vo.setInvoicecusts(this.keyValue
        .getHeadStringValue(SaleOrderHVO.CINVOICECUSTID));
    vo.setCorigcurrencyid(this.keyValue
        .getHeadStringValue(SaleOrderHVO.CORIGCURRENCYID));
    vo.setOrdercusts(this.keyValue.getHeadStringValue(SaleOrderHVO.CCUSTOMERID));
    vo.setOrdertrantype(this.keyValue
        .getHeadStringValue(SaleOrderHVO.CTRANTYPEID));
    vo.setOrigsubmny(this.keyValue.getBodyUFDoubleValue(i,
        SaleOrderBVO.NORIGSUBMNY));
    vo.setOrigtaxmny(this.keyValue.getBodyUFDoubleValue(i,
        SaleOrderBVO.NORIGTAXMNY));
    vo.setProdline(this.keyValue
        .getBodyStringValue(i, SaleOrderBVO.CPRODLINEID));
    vo.setSaleorg(this.keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG));
    vo.setSettleorg(settleorg);
    return vo;
  }

  /**
   * 返回合并开票比例
   * 
   * @return 返回冲抵比例
   */
  // private UFDouble getSO15(String settleorg) {
  // UFDouble so15 = null;
  // try {
  // so15 = SOSysParaInitUtil.getSO15(settleorg);
  // }
  // catch (BusinessException e) {
  // ExceptionUtils.wrappBusinessException(e.getMessage());
  // }
  //
  // return so15 == null ? UFDouble.ZERO_DBL :
  // so15.div(OffsetItfVOUtil.HUNDRES);
  // }
}
