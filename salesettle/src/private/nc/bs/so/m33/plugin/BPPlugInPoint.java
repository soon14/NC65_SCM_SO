package nc.bs.so.m33.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

/**
 * 销售结算业务核心处理逻辑的二次开发插入点定义
 * 
 * @author zc
 * 
 *         2009-12-19 下午02:47:34
 */
public enum BPPlugInPoint implements IPluginPoint {
  /**
   * 取消传暂估应收
   */
  CancelETIncomeFor4CBP(
      "nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelar.CancelETIncomeFor4CBP"),
      
  /**
   * 取消传暂估应收
   */
  CancelETIncomeFor4CDetailBP(
      "nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelar.CancelETIncomeFor4CBP.cancelDetail"),

  /**
   * 取消销售发票传成本应收
   */
  CancelIACostFor32BP("nc.bs.so.m33.biz.bp.cancelia.CancelIACostFor32BP"),
  
  /**
   * 取消销售发票明细传成本应收
   */
  CancelIACostFor32DetailBP("nc.bs.so.m33.biz.bp.cancelia.CancelIACostFor32BP.cancelDetail"),

  /**
   * 取消销售发票传成本应收
   */
  CancelIACostFor4CBP(
      "nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelia.CancelIACostFor4CBP"),
      
  /**
   * 取消销售发票明细传成本应收
   */
  CancelIACostFor4CDetailBP(
      "nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelia.CancelIACostFor4CBP.cancelDetail"),

  /**
   * 取消途损待结算单传成本应收
   */
  CancelIACostFor4453BP(
      "nc.bs.so.m33.biz.m4453.bp.cancelsquare.cancelia.CancelIACostFor4453BP"),

  /**
   * 取消途损待结算单明细传成本应收
   */
  CancelIACostFor4453DetailBP(
      "nc.bs.so.m33.biz.m4453.bp.cancelsquare.cancelia.CancelIACostFor4453BP.cancelDetail"),
                  

  /**
   * 取消销售发票传发出商品
   */
  CancelIARegisterFor32BP(
      "nc.bs.so.m33.biz.m32.bp.cancelsquare.cancelia.CancelIARegisterFor32BP"),
      
  /**
   * 取消销售发票传发出商品
   */
  CancelIARegisterFor32DetailBP(
      "nc.bs.so.m33.biz.m32.bp.cancelsquare.cancelia.CancelIARegisterFor32BP.cancelDetail"),

  /**
   * 取消途损待结算单传发出商品
   */
  CancelIARegisterFor4453BP(
      "nc.bs.so.m33.biz.m4453.bp.cancelsquare.cancelia.CancelIARegisterFor4453BP"),
      
  /**
   * 取消途损待结算单明细传发出商品
   */
  CancelIARegisterFor4453DetailBP(
      "nc.bs.so.m33.biz.m4453.bp.cancelsquare.cancelia.CancelIARegisterFor4453BP.cancelDetail"),
      
  /**
   * 取消销售出库单传发出商品
   */
  CancelIARegisterFor4CBP(
      "nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelia.CancelIARegisterDebitFor4CBP"),
      
  /**
   * 取消销售出库待结算单发出商品贷方
   */
  CancelIARegisterCreditFor4C(
      "nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelia.AbstractCancelIARegisterCreditFor4CBP"),

  /**
   * 取消出库对冲
   */
  CancelOutRush(
      "nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelar.CancelOutRushIncomeFor4CBP"),

  /**
   * 取消销售发票传确认应收
   */
  CancelARIncomeFor32BP("nc.bs.so.m33.biz.m32.bp.cancelsquare.cancelar.CancelARIncomeFor32BP"),

  /**
   * 取消销售发票明细传确认应收
   */
  CancelARIncomeFor32DetailBP("nc.bs.so.m33.biz.m32.bp.cancelsquare.cancelar.CancelARIncomeFor32BP.cancelDetail"),

  /**
   * 取消销售发票传回冲应收
   */
  CancelARRushIncomeFor32BP("nc.bs.so.m33.biz.m32.bp.cancelsquare.cancelar.CancelARRushIncomeFor32BP"),

  /**
   * 取消销售发票明细传回冲应收
   */
  CancelARRushIncomeFor32DetailBP("nc.bs.so.m33.biz.m32.bp.cancelsquare.cancelar.CancelARRushIncomeFor32BP.cancelDetail"),

  /**
   * 取消途损待结算单传确认应收
   */
  CancelARIncomeFor4453BP("nc.bs.so.m33.biz.m4453.bp.cancelsquare.cancelar.CancelARIncomeFor4453BP"),

  /**
   * 取消途损待结算单明细传确认应收
   */
  CancelARIncomeFor4453DetailBP("nc.bs.so.m33.biz.m4453.bp.cancelsquare.cancelar.CancelARIncomeFor4453BP.cancelDetail"),

  /**
   * 取消途损待结算单传回冲应收
   */
  CancelARRushIncomeFor4453BP("nc.bs.so.m33.biz.m4453.bp.cancelsquare.cancelar.CancelARRushIncomeFor4453BP"),

  /**
   * 取消途损待结算单明细传回冲应收
   */
  CancelARRushIncomeFor4453DetailBP("nc.bs.so.m33.biz.m4453.bp.cancelsquare.cancelar.CancelARRushIncomeFor4453BP.cancelDetail"),

  /**
   * 取消销售出库传确认应收
   */
  CancelARIncomeFor4CBP("nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelar.CancelARIncomeFor4CBP"),

  /**
   * 取消销售出库明细传确认应收
   */
  CancelARIncomeFor4CDetailBP("nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelar.CancelARIncomeFor4CBP.cancelDetail"),

  /**
   * 取消销售出库传回冲应收
   */
  CancelARRushIncomeFor4CBP("nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelar.CancelARRushIncomeFor4CBP"),

  /**
   * 取消销售出库明细传回冲应收
   */
  CancelARRushIncomeFor4CDetailBP("nc.bs.so.m33.biz.m4c.bp.cancelsquare.cancelar.CancelARRushIncomeFor4CBP.cancelDetail"),

  /**
   * 销售结算明细表删除
   */
  DeleteDetailBP("nc.bs.so.m33.service.DeleteSquareDetailBP"),

  /**
   * 销售发票结算单新增保存
   */
  InsertSquare32BP("nc.bs.so.m33.maintain.m32.InsertSquare32BP"),
  
  /**
   * 销售途损待待结算单新增保存
   */
  InsertSquare4453BP("nc.bs.so.m33.maintain.m4453.InsertSquare4453BP"),
  
  /**
   * 销售出库待结算单新增保存
   */
  InsertSquare4CBP("nc.bs.so.m33.maintain.m4c.InsertSquare4CBP"),

  /**
   * 销售结算明细表新增保存
   */
  InsertDetailBP("nc.bs.so.m33.service.InsertSquareDetailBP"),

  /**
   * 销售出库单手工暂估应收
   */
  ManualET("nc.impl.so.m33.m4c.SaleOutManualEstimateAction"),

  /**
   *  销售出库单手工发出商品
   */
  ManualREG("nc.impl.so.m33.m4c.SaleOutManualRegsiterAction"),

  /**
   * 销售出库单手工结算
   */
  ManualSquare("nc.impl.so.m33.m4c.SaleOutSettleMaintainImpl"),

  /**
   * 传确认应收
   */
  SquareARIncomeFor4C("nc.bs.so.m33.biz.m4c.bp.square.ar.SquareARIncomeFor4CBP"),
  
  /**
   * 传确认应收明细
   */
  SquareARIncomeFor4CDetail("nc.bs.so.m33.biz.m4c.bp.square.ar.SquareARIncomeFor4CBP.saveDetail"),
  
  /**
   * 销售出库传回冲应收
   */
  SquareARRushIncomeFor4C("nc.bs.so.m33.biz.m4c.bp.square.ar.SquareARRushIncomeFor4CBP"),
  
  /**
   * 销售出库传回冲应收
   */
  SquareARRushIncomeFor4CDetail("nc.bs.so.m33.biz.m4c.bp.square.ar.SquareARRushIncomeFor4CBP.saveDetail"),
  
  /**
   * 销售出库对冲传回冲应收
   */
  SquareARRushIncomeFor4CRushBP("nc.bs.so.m33.biz.m4c.bp.square.ar.SquareARRushIncomeFor4CRushBP"),
  
  /**
   * 销售出库对冲传回冲应收
   */
  SquareARRushIncomeFor4CDetailRushBP("nc.bs.so.m33.biz.m4c.bp.square.ar.SquareARRushIncomeFor4CRushBP.saveDetail"),
  
  /**
   * 基于签收开票退回的出库单传回冲应收
   */
  SquareARRushIncomeFor4CSBP("nc.bs.so.m33.biz.m4c.bp.square.ar.SquareARRushIncomeFor4CSBP"),
  
  /**
   * 基于签收开票退回的出库单传回冲应收
   */
  SquareARRushIncomeFor4CSDetailSBP("nc.bs.so.m33.biz.m4c.bp.square.ar.SquareARRushIncomeFor4CSBP.saveDetail"),
  
  
  
  /**
   * 途损待结算单传确认应收
   */
  SquareARIncomeFor4453("nc.bs.so.m33.biz.m4453.bp.square.ar.SquareARIncomeFor4453BP"),
  
  /**
   * 途损待结算单明细传确认应收
   */
  SquareARIncomeFor4453Detail("nc.bs.so.m33.biz.m4453.bp.square.ar.SquareARIncomeFor4453BP.saveDetail"),

  /**
   * 途损待结算单传回冲应收
   */
  SquareARRushIncomeFor4453("nc.bs.so.m33.biz.m4453.bp.square.ar.SquareARRushIncomeFor4453BP"),
  
  /**
   * 途损待结算单明细传回冲应收
   */
  SquareARRushIncomeFor4453Detail("nc.bs.so.m33.biz.m4453.bp.square.ar.SquareARRushIncomeFor4453BP.saveDetail"),


  /**
   * 传回冲应收
   */
  SquareRushARIncome("nc.bs.so.m33.biz.m32.bp.square.toar.SquareARRushIncomeFor32BP"),
  
  /**
   * 保存应收结算单
   */
  SquareARIncome("nc.bs.so.m33.biz.bp.toar.SquareARIncomeBP"),
  
  /**
   * 保存应收结算单
   */
  SquareARIncomeDetail("nc.bs.so.m33.biz.bp.toar.SquareARIncomeBP.saveDetail"),
  
  /**
   * 保存回冲应收结算单
   */
  SquareRushARIncomeDetail("nc.bs.so.m33.biz.m32.bp.square.toar.SquareARRushIncomeFor32BP.saveDetail"),
  
  /**
   * 暂估应收明细保存
   */
  SquareETIncome("nc.bs.so.m33.biz.bp.toar.SquareETIncomeBP"),
  
  /**
   * 暂估应收明细保存
   */
  SquareETIncomeDetail("nc.bs.so.m33.biz.bp.toar.SquareETIncomeBP.saveDetail"),

  /**
   * 出库对冲
   */
  SquareOutRushByViewVO("nc.bs.so.m33.biz.m4c.bp.outrush.OutRushBP.byViewVO"),
  
  /**
   * 出库对冲
   */
  SquareOutRushByDetailVO("nc.bs.so.m33.biz.m4c.bp.outrush.OutRushBP.byDetailVO"),

  /**
   * 销售发票传成本
   */
  SquareToIABy32("nc.bs.so.m33.biz.bp.toia.SquareIACostFor32BP"),
  
  /**
   * 销售发票传发出商品
   */
  SquareToIARegisterCreditBy32("nc.bs.so.m33.biz.m32.bp.square.toia.SquareIARegisterCreditFor32BP"),

  /**
   * 销售发票传成本 明细保存
   */
  SquareToIABy32Detail(
      "nc.bs.so.m33.biz.bp.toia.SquareIACostFor32BP.saveDetail"),
      
  /**
   * 销售发票传发出商品明细保存
   */
  SquareToIARegisterCreditBy32Detail(
      "nc.bs.so.m33.biz.m32.bp.square.toia.SquareIARegisterCreditFor32BP.saveDetail"),
      
  /**
   * 销售出库传发出商品明细保存
   */
  SquareToIARegisterDebitBy4C(
      "nc.bs.so.m33.biz.m4c.bp.square.ia.SquareIARegisterDebitFor4CBP"),
      
  /**
   * 销售发票传发出商品明细保存
   */
  SquareToIARegisterDebitBy4CDetail(
      "nc.bs.so.m33.biz.m4c.bp.square.ia.SquareIARegisterDebitFor4CBP.saveDetail"),

  /**
   * 销售出库传发出商品明细保存
   */
  SquareToIARegisterCreditBy4CDetail(
      "nc.bs.so.m33.biz.m4c.bp.square.ia.AbstractSquareIARegisterCreditFor4CBP.saveDetail"),
      
  /**
   * 销售出库传发出商品保存
   */
  SquareToIARegisterCreditBy4C(
      "nc.bs.so.m33.biz.m4c.bp.square.ia.AbstractSquareIARegisterCreditFor4CBP"),
      
  /**
   * 途损待结算单传发出商品保存
   */
  SquareToRegisterBy4453(
      "nc.bs.so.m33.biz.m4453.bp.square.ia.SquareIARegisterFor4453BP"),

  /**
   * 途损待结算单明细传发出商品保存
   */
  SquareToRegisterBy4453Detail(
      "nc.bs.so.m33.biz.m4453.bp.square.ia.SquareIARegisterFor4453BP.saveDetail"),
      
  /**
   * 途损待结算单传成本
   */
  SquareToIABy4453(
      "nc.bs.so.m33.biz.m4453.bp.square.ia.SquareIACostFor4453BP"),

  /**
   * 途损待结算单传成本
   */
  SquareToIABy4453Detail(
      "nc.bs.so.m33.biz.m4453.bp.square.ia.SquareIACostFor4453BP.saveDetail"),


  /**
   * 销售出库单传成本
   */
  SquareToIABy4C("nc.bs.so.m33.biz.bp.toia.SquareIACostFor4CBP"),
  
  /**
   * 销售出库单明细传成本
   */
  SquareToIABy4CDetail("nc.bs.so.m33.biz.bp.toia.SquareIACostFor4CBP.saveDetail");

  // 插入点
  private String point;

  private BPPlugInPoint(String point2) {
    this.point = point2;
  }

  @Override
  public String getComponent() {
    return "m33";
  }

  @Override
  public String getModule() {
    return NCModule.SO.getName();
  }

  @Override
  public String getPoint() {
    return this.point;
  }
}
