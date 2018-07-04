package nc.ui.so.m30.billui.action.printaction;

import nc.ui.pubapp.print.split.PrintDataSplitProcessor;
import nc.ui.pubapp.print.split.SplitBillData;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;

public class SaleOrderPrintDataSplitProcessor extends PrintDataSplitProcessor {

  @Override
  protected IBill[] afterConvertData(SplitBillData<IBill> splitbill,
      IBill[] newDatas) {

    SaleOrderVO[] aftersplitbills =
        (SaleOrderVO[]) super.afterConvertData(splitbill, newDatas);

    for (SaleOrderVO bill : aftersplitbills) {

      IKeyValue keyvalue = new VOKeyValue<SaleOrderVO>(bill);

      // 汇总表头合计
      HeadTotalCalculateRule totalcalrule =
          new HeadTotalCalculateRule(keyvalue);

      totalcalrule.calculateHeadTotal();

    }

    return aftersplitbills;
  }
}
