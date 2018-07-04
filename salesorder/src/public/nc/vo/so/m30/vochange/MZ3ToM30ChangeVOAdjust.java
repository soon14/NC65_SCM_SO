package nc.vo.so.m30.vochange;

import nc.vo.scmpub.res.billtype.CTBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
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
import nc.vo.so.pub.rule.SOExchangeRateRule;
import nc.vo.so.pub.rule.SOGlobalExchangeRate;
import nc.vo.so.pub.rule.SOGroupExchangeRate;
import nc.vo.so.pub.rule.SOProfitCenterValueRule;
import nc.vo.so.pub.rule.SOTaxInfoRule;
import nc.vo.so.pub.rule.SaleOrgRelationRule;

public class MZ3ToM30ChangeVOAdjust extends AbstractSaleOrderChangeVOAdjust {

  @Override
  protected void fillRefAddValue(SaleOrderVO[] vos) {
    super.fillRefAddValue(vos);

    for (SaleOrderVO billvo : vos) {

      IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(billvo);

      // (1)设置收款协议信息
      PayTermRule payTermRule = new PayTermRule(keyValue);
      payTermRule.setPayTermInfo();

      // (2)发货库存组织、物流组织、财务组织填充
      BodyValueRowRule bodycouuitl = new BodyValueRowRule(keyValue);
      int[] rows = bodycouuitl.getMarNotNullRows();

      SaleOrgRelationRule orgrelrule = new SaleOrgRelationRule(keyValue);
      // 发货库存组织
      int[] sendstockrows =
          bodycouuitl.getValueNullRows(SaleOrderBVO.CSENDSTOCKORGVID);
      orgrelrule.setSendStockOrg(sendstockrows);
      // 物流组织
      int[] trafficrows =
          bodycouuitl.getValueNullRows(SaleOrderBVO.CTRAFFICORGVID);

      // 来源于其它单据时，按照客户固定结算组织、销售业务委托关系匹配规则携带结算财务组织、应收组织和利润中心。
      orgrelrule.setFinanceStockOrg(rows,
          orgrelrule.getRelationOrg(rows));
      
      
      // add by zhangby5 for 利润中心取值规则
      SOProfitCenterValueRule profitRule =
          new SOProfitCenterValueRule(keyValue);
      profitRule.setProfitCenterValue(SaleOrderBVO.CSPROFITCENTERVID,
          SaleOrderBVO.CSPROFITCENTERID);
      

      // 组织本币填充
      SOCurrencyRule currule = new SOCurrencyRule(keyValue);
      currule.setCurrency(rows);

      SOExchangeRateRule exrule = new SOExchangeRateRule(keyValue);
      exrule.calcBodyExchangeRates(rows);

      // 集团、全局汇率计算
      SOGlobalExchangeRate globalraterule = new SOGlobalExchangeRate(keyValue);
      globalraterule.calcGlobalExchangeRate(rows);

      SOGroupExchangeRate groupraterule = new SOGroupExchangeRate(keyValue);
      groupraterule.calcGroupExchangeRate(rows);
      SaleOrderVOCalculator vocalcultor = new SaleOrderVOCalculator(billvo);
      int[] changerows = exrule.getRateChangeRow();
      vocalcultor.calculate(changerows, SaleOrderBVO.NEXCHANGERATE);
      
      /******* 设置国家、购销类型和三角贸易\询税(61) ********/
      SOCountryInfoRule countryrule = new SOCountryInfoRule(keyValue);
      countryrule.setCountryInfo(rows);
      // 购销类型
      SOBuysellTriaRule buyflgrule = new SOBuysellTriaRule(keyValue);
      buyflgrule.setBuysellAndTriaFlag(rows);

      int[] buychgrows = buyflgrule.getBuysellChgRow();
      vocalcultor.calculate(buychgrows, SOCalConditionRule.getCalPriceKey());
      // 询税
      SOTaxInfoRule taxInfo = new SOTaxInfoRule(keyValue);
      taxInfo.setTaxInfoByBodyPos(rows);
      int[] taxchgrows = taxInfo.getTaxChangeRows();
      vocalcultor.calculate(taxchgrows, SaleOrderBVO.NTAXRATE);
      /******* 设置国家、购销类型和三角贸易\询税(61) ********/

      // 直运仓
      DirectStoreRule dirstore = new DirectStoreRule(keyValue);
      dirstore.setSendStordoc(rows);
      // 取默认物流组织要放在取仓库后做
      orgrelrule.setTrafficOrg(trafficrows);

    }

  }

  @Override
  protected String getSrcBillTypeCode() {
    return CTBillType.SaleDaily.getCode();
  }
}
