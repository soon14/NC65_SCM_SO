package nc.vo.so.m30.vochange;

import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.pub.SaleOrderVOCalculator;
import nc.vo.so.m30.rule.DirectStoreRule;
import nc.vo.so.m30.rule.PayTermRule;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOCalConditionRule;
import nc.vo.so.pub.rule.SOCountryInfoRule;
import nc.vo.so.pub.rule.SOCurrencyRule;
import nc.vo.so.pub.rule.SOCustRelaDefValueRule;
import nc.vo.so.pub.rule.SOExchangeRateRule;
import nc.vo.so.pub.rule.SOGlobalExchangeRate;
import nc.vo.so.pub.rule.SOGroupExchangeRate;
import nc.vo.so.pub.rule.SOProfitCenterValueRule;
import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.so.pub.rule.SaleOrgRelationRule;
import nc.vo.so.pub.util.ArrayUtil;

public class M38ToM30ChangeVOAdjust extends AbstractSaleOrderChangeVOAdjust {

  @Override
  protected void fillRefAddValue(SaleOrderVO[] vos) {

    super.fillRefAddValue(vos);

    for (SaleOrderVO billvo : vos) {

      IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(billvo);

      // 发货库存组织、物流组织、财务组织填充
      BodyValueRowRule bodycouuitl = new BodyValueRowRule(keyValue);

      SaleOrgRelationRule orgrelrule = new SaleOrgRelationRule(keyValue);
      int[] sendstockrows =
          bodycouuitl.getValueNullRows(SaleOrderBVO.CSENDSTOCKORGVID);
      orgrelrule.setSendStockOrg(sendstockrows);

      int[] trafficrows =
          bodycouuitl.getValueNullRows(SaleOrderBVO.CTRAFFICORGVID);
      orgrelrule.setTrafficOrg(trafficrows);

      int[] finacerows =
          bodycouuitl.getValueNullRows(SaleOrderBVO.CSETTLEORGVID);
      orgrelrule.setFinanceOrg(finacerows);
      
      // add by zhangby5 for 利润中心取值规则
      SOProfitCenterValueRule profitRule = new SOProfitCenterValueRule(keyValue);
      profitRule.setProfitCenterValue(SaleOrderBVO.CSPROFITCENTERVID,
          SaleOrderBVO.CSPROFITCENTERID);

      // 组织本位币
      SOCurrencyRule currule = new SOCurrencyRule(keyValue);
      currule.setCurrency(finacerows);
      // 折本汇率
      SOExchangeRateRule exrule = new SOExchangeRateRule(keyValue);
      exrule.calcBodyExchangeRates(finacerows);

      // 集团、全局汇率计算
      SOGlobalExchangeRate globalraterule = new SOGlobalExchangeRate(keyValue);
      globalraterule.calcGlobalExchangeRate(finacerows);

      SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
      groupraterule.calcGroupExchangeRate(finacerows);

      SaleOrderVOCalculator vocalcultor = new SaleOrderVOCalculator(billvo);
      vocalcultor.calculate(finacerows, SaleOrderBVO.NEXCHANGERATE);

      // 开票客户填充
      String invoicecust =
          keyValue.getHeadStringValue(SaleOrderHVO.CINVOICECUSTID);
      if (PubAppTool.isNull(invoicecust)) {
        SOCustRelaDefValueRule custrelarule =
            new SOCustRelaDefValueRule(keyValue);
        custrelarule.setCustRelaInvoiceCust();
      }
      // 设置收款协议信息
      PayTermRule payTermRule = new PayTermRule(keyValue);
      payTermRule.setPayTermInfo();

      int[] needchangerows = ArrayUtil.combinArrays(sendstockrows, finacerows);
      SOCountryInfoRule countryrule = new SOCountryInfoRule(keyValue);
      countryrule.setCountryInfo(needchangerows);
      // 购销类型
      SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
      buyflgrule.setBuysellAndTriaFlag(needchangerows);

      int[] buychgrows = buyflgrule.getBuysellChgRow();
      vocalcultor.calculate(buychgrows, SOCalConditionRule.getCalPriceKey());
      // 询税
      SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
      taxInfo.setTaxInfoByBodyPos(needchangerows);
      int[] taxchgrows = taxInfo.getTaxChangeRows();
      vocalcultor.calculate(taxchgrows, SaleOrderBVO.NTAXRATE);

      int[] rows = bodycouuitl.getMarNotNullRows();
      // 直运仓
      DirectStoreRule dirstorerule = new DirectStoreRule(keyValue);
      dirstorerule.setDirectStore(rows);
    }
  }

  @Override
  protected String getSrcBillTypeCode() {
    return SOBillType.PreOrder.getCode();
  }
}
