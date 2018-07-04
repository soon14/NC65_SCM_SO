package nc.ui.so.m30.billui.tranferbill;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.billref.dest.DefaultBillDataLogic;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.so.m30.rule.FillNmffilePriceRule;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 销售订单参照借出单转单后前台数据处理类
 * 
 * @since 6.0
 * @version 2011-7-4 下午03:34:25
 * @author fengjb
 */
public class M30Ref4HTransferBillDataLogic extends DefaultBillDataLogic {
  @Override
  public void doTransferAddLogic(Object selectedData) {

    // 把数据设置在界面上
    super.doTransferAddLogic(selectedData);
    
    // 基于界面卡片填充值运算
    BillCardPanel cardPanel = this.getBillForm().getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 1.设置特征价
    FillNmffilePriceRule nmffileRule = new FillNmffilePriceRule(keyValue);
    nmffileRule.setNmffilePrice();
    // 2.表头合计
    HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
    totalrule.calculateHeadTotal();

  }

}
