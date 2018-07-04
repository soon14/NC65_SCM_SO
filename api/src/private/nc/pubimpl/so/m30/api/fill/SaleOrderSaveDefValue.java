package nc.pubimpl.so.m30.api.fill;

import nc.bs.pubapp.AppBsContext;
import nc.pubimpl.so.m30.api.fill.SaleOrderNPriceMnyCal;
import nc.pubimpl.so.pub.api.fill.BatchCodeBillRule;
import nc.pubimpl.so.pub.api.fill.BusitypeFillRule;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.fill.pricemny.INumPriceMnyCalculator;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.rule.DirectStoreRule;
import nc.vo.so.m30.rule.PayTermRule;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.MaterialFullRule;
import nc.vo.so.pub.rule.ReceiveCustDefAddrRule;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCountryInfoRule;
import nc.vo.so.pub.rule.SOCurrencyRule;
import nc.vo.so.pub.rule.SOCustRelaDefValueRule;
import nc.vo.so.pub.rule.SOExchangeRateRule;
import nc.vo.so.pub.rule.SOGlobalExchangeRate;
import nc.vo.so.pub.rule.SOGroupExchangeRate;
import nc.vo.so.pub.rule.SOProfitCenterValueRule;
import nc.vo.so.pub.rule.SaleOrgFillRule;
import nc.vo.so.pub.rule.SaleOrgRelationRule;
import nc.vo.so.pub.util.ArrayUtil;

/**
 * @description
 * 销售订单填充强制默认值规则1
 * @scene
 * 销售订单保存
 * @param
 * 无
 *
 * @since 6.5
 * @version 2015-10-20 下午2:13:21
 * @author 刘景
 */
public class SaleOrderSaveDefValue {

  /**
   * 销售订单默认值强制填充
   * 
   * @param vos
   */
  public void setDefultValue(SaleOrderVO[] vos) {

    // 设置销售组织vid、人员、部门
    SaleOrgFillRule<SaleOrderVO> orgfill = new SaleOrgFillRule<>(vos);
    orgfill.setOrgEmplyDept();

    // 填充交易类型编码、业务流程
    BusitypeFillRule<SaleOrderVO> busitypefill = new BusitypeFillRule<>(vos);
    busitypefill.setBusitype();

    // 填充物料old
    MaterialFullRule<SaleOrderVO> materialfull = new MaterialFullRule<>(vos);
    materialfull.setMaterialOid();

    for (SaleOrderVO salebillvo : vos) {
      IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(salebillvo);
      // 填充默认值
      setDefultInfo(keyValue);
    }

    //批次处理
    BatchCodeBillRule<SaleOrderVO> batchcode = new BatchCodeBillRule<>();
    batchcode.fillValue(vos);

    // 计算单位、换算率、数量、单价、金额
    INumPriceMnyCalculator cal = new SaleOrderNPriceMnyCal<SaleOrderVO>(vos);
    cal.calculate();
  }

  private void setDefultInfo(IKeyValue keyValue) {
    // 填充强制单据默认值
    this.setForceDefValue(keyValue);

    // 发货库存组织、物流组织、财务组织
    BodyValueRowRule bodycouuitl = new BodyValueRowRule(keyValue);

    SaleOrgRelationRule orgrelrule = new SaleOrgRelationRule(keyValue);
    int[] sendstockrows =
        bodycouuitl.getValueNullRows(SaleOrderBVO.CSENDSTOCKORGVID);
    orgrelrule.setSendStockOrg(sendstockrows);

    int[] trafficrows =
        bodycouuitl.getValueNullRows(SaleOrderBVO.CTRAFFICORGVID);
    orgrelrule.setTrafficOrg(trafficrows);

    int[] finacerows = bodycouuitl.getValueNullRows(SaleOrderBVO.CSETTLEORGVID);
    orgrelrule.setFinanceOrg(finacerows);

    // 利润中心取值规则，直运非直运业务均如此取值
    SOProfitCenterValueRule profitRule = new SOProfitCenterValueRule(keyValue);
    profitRule.setProfitCenterValue(SaleOrderBVO.CSPROFITCENTERVID,
        SaleOrderBVO.CSPROFITCENTERID, sendstockrows);

    // 组织本位币
    SOCurrencyRule currule = new SOCurrencyRule(keyValue);
    currule.setCurrency(finacerows);

    // 填充客户相关默认值
    SOCustRelaDefValueRule custrefrule = new SOCustRelaDefValueRule(keyValue);
    custrefrule.setCustRelaDefValue();

    // 折本汇率
    SOExchangeRateRule exrule = new SOExchangeRateRule(keyValue);
    exrule.calcBodyExchangeRates(finacerows);

    // 设置收款协议信息
    PayTermRule payTermRule = new PayTermRule(keyValue);
    payTermRule.setPayTermInfo();

    // 收货客户
    int[] custisnullrows =
        bodycouuitl.getValueNullRows(SaleOrderBVO.CRECEIVECUSTID);
    custrefrule.setRelaReceiveCust(custisnullrows);

    // 收货地址
    int[] eiveaddrnullrows =
        bodycouuitl.getValueNullRows(SaleOrderBVO.CRECEIVEADDRID);
    ReceiveCustDefAddrRule defaddrule = new ReceiveCustDefAddrRule(keyValue);
    defaddrule.setCustDefaultAddress(eiveaddrnullrows);

    // 国家
    int[] needchangerows = ArrayUtil.combinArrays(sendstockrows, finacerows);
    SOCountryInfoRule countryrule = new SOCountryInfoRule(keyValue);
    countryrule.setCountryInfo(needchangerows);
    // 购销类型
    SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
    buyflgrule.setBuysellAndTriaFlag(needchangerows);

    int[] rows = bodycouuitl.getMarNotNullRows();
    String ctrantypeid = keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);
    // 直运仓
    if (!PubAppTool.isNull(ctrantypeid)) {
      DirectStoreRule dirstorerule = new DirectStoreRule(keyValue);
      dirstorerule.setDirectStore(rows);
    }

    // 集团、全局汇率计算
    SOGlobalExchangeRate globalraterule = new SOGlobalExchangeRate(keyValue);
    globalraterule.calcGlobalExchangeRate(rows);
    SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
    groupraterule.calcGroupExchangeRate(rows);

  }

  /**
   * 设置转单后强制的数据默认值
   */
  private void setForceDefValue(IKeyValue keyValue) {

    // 单据状态
    keyValue.setHeadValue(SaleOrderHVO.FSTATUSFLAG,
        BillStatus.FREE.getIntegerValue());
    // 整单折扣
    UFDouble discountrate =
        keyValue.getHeadUFDoubleValue(SaleOrderHVO.NDISCOUNTRATE);
    if (null == discountrate) {
      discountrate = SOConstant.ONEHUNDRED;
      keyValue.setHeadValue(SaleOrderHVO.NDISCOUNTRATE, discountrate);
    }

    // 开票客户填充
    String invoicecust =
        keyValue.getHeadStringValue(SaleOrderHVO.CINVOICECUSTID);
    if (PubAppTool.isNull(invoicecust)) {
      SOCustRelaDefValueRule custrelarule =
          new SOCustRelaDefValueRule(keyValue);
      custrelarule.setCustRelaInvoiceCust();
    }

    // 单据日期
    UFDate busdate = AppBsContext.getInstance().getBusiDate();
    keyValue.setHeadValue(SaleOrderHVO.DBILLDATE, busdate);

    String pk_group = AppBsContext.getInstance().getPkGroup();
    keyValue.setHeadValue(SaleOrderHVO.PK_GROUP, pk_group);

    UFDate enddate = busdate.asLocalEnd();
    int bodycount = keyValue.getBodyCount();
    for (int i = 0; i < bodycount; i++) {
      keyValue.setBodyValue(i, SaleOrderBVO.DBILLDATE, busdate);
      keyValue.setBodyValue(i, SaleOrderBVO.PK_GROUP, pk_group);
      // 计划发货日期
      UFDate senddate = keyValue.getBodyUFDateValue(i, SaleOrderBVO.DSENDDATE);
      if (null == senddate || senddate.before(busdate)) {
        keyValue.setBodyValue(i, SaleOrderBVO.DSENDDATE, enddate);
      }

      // 要求到货日期
      UFDate receivedate =
          keyValue.getBodyUFDateValue(i, SaleOrderBVO.DRECEIVEDATE);
      if (null == receivedate || receivedate.before(busdate)) {
        keyValue.setBodyValue(i, SaleOrderBVO.DRECEIVEDATE, enddate);
      }

      // 整单折扣
      UFDouble disrate =
          keyValue.getBodyUFDoubleValue(i, SaleOrderBVO.NDISCOUNTRATE);
      if (null == disrate) {
        keyValue.setBodyValue(i, SaleOrderHVO.NDISCOUNTRATE, discountrate);
      }
      // 单品折扣
      UFDouble itemdisrate =
          keyValue.getBodyUFDoubleValue(i, SaleOrderBVO.NITEMDISCOUNTRATE);
      if (null == itemdisrate) {
        keyValue.setBodyValue(i, SaleOrderBVO.NITEMDISCOUNTRATE,
            SOConstant.ONEHUNDRED);
      }
      // 行状态
      keyValue.setBodyValue(i, SaleOrderBVO.FROWSTATUS,
          BillStatus.FREE.getIntegerValue());
    }
  }

}
