package nc.ui.so.m4331.billui.action.printaction;

import nc.ui.pubapp.print.split.PrintDataSplitProcessor;
import nc.ui.pubapp.print.split.SplitBillData;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.rule.DeliveryHeadTotalCalculateRule;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;

public class DeliveryPrintDataSplitProcessor extends PrintDataSplitProcessor {

  @Override
  protected IBill[] afterConvertData(SplitBillData<IBill> splitbill,
      IBill[] newDatas) {

    DeliveryVO[] aftersplitbills =
        (DeliveryVO[]) super.afterConvertData(splitbill, newDatas);

    for (DeliveryVO bill : aftersplitbills) {

      IKeyValue keyvalue = new VOKeyValue<DeliveryVO>(bill);
      // 汇总表头合计
      DeliveryHeadTotalCalculateRule totalcalrule =
          new DeliveryHeadTotalCalculateRule(keyvalue);

      totalcalrule.calculateHeadTotal();

    }

    return aftersplitbills;
  }
}
