package nc.vo.so.m4331.rule;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.AbstractKeyValue.RowStatus;
import nc.vo.so.pub.rule.BodyValueRowRule;

public class DeliveryHeadTotalCalculateRule {
  private IKeyValue keyValue;

  public DeliveryHeadTotalCalculateRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public void calculateHeadTotal() {

    // 合计数量
    UFDouble totalastnum = UFDouble.ZERO_DBL;
    // 合计件数
    UFDouble totalpiece = UFDouble.ZERO_DBL;
    // 合计体积
    UFDouble totalvolume = UFDouble.ZERO_DBL;
    // 合计重量
    UFDouble totalweight = UFDouble.ZERO_DBL;
//    // 合计原币含税金额
//    UFDouble totalorigmny = UFDouble.ZERO_DBL;
//    // 合计原币冲减前金额
//    UFDouble totalsuborigmny = UFDouble.ZERO_DBL;

    BodyValueRowRule countutil = new BodyValueRowRule(this.keyValue);
    int[] rows = countutil.getMarNotNullRows();

    for (int row : rows) {

      if (RowStatus.DELETED == this.keyValue.getRowStatus(row)) {
        continue;
      }
      UFDouble astnum =
          this.keyValue.getBodyUFDoubleValue(row, DeliveryBVO.NASTNUM);
      totalastnum = MathTool.add(totalastnum, astnum);

      UFDouble piece =
          this.keyValue.getBodyUFDoubleValue(row, DeliveryBVO.NPIECE);
      totalpiece = MathTool.add(totalpiece, piece);

      UFDouble volume =
          this.keyValue.getBodyUFDoubleValue(row, DeliveryBVO.NVOLUME);
      totalvolume = MathTool.add(totalvolume, volume);

      UFDouble weight =
          this.keyValue.getBodyUFDoubleValue(row, DeliveryBVO.NWEIGHT);
      totalweight = MathTool.add(totalweight, weight);

      UFBoolean largessflag =
          this.keyValue.getBodyUFBooleanValue(row, DeliveryBVO.BLARGESSFLAG);
      if (null != largessflag && largessflag.booleanValue()) {
        continue;
      }
    }
    this.keyValue.setHeadValue(DeliveryHVO.NTOTALASTNUM, totalastnum);
    this.keyValue.setHeadValue(DeliveryHVO.NTOTALPIECE, totalpiece);
    this.keyValue.setHeadValue(DeliveryHVO.NTOTALVOLUME, totalvolume);
    this.keyValue.setHeadValue(DeliveryHVO.NTOTALWEIGHT, totalweight);

  }

}
