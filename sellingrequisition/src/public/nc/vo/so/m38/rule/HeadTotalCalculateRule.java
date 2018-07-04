package nc.vo.so.m38.rule;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.AbstractKeyValue.RowStatus;
import nc.vo.so.pub.rule.BodyValueRowRule;

public class HeadTotalCalculateRule {

  private IKeyValue keyValue;

  public HeadTotalCalculateRule(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public void calculateHeadTotal() {

    // 合计数量
    UFDouble totalastnum = UFDouble.ZERO_DBL;
    // 合计原币含税金额
    UFDouble totalorigmny = UFDouble.ZERO_DBL;

    BodyValueRowRule countutil = new BodyValueRowRule(this.keyValue);
    int[] rows = countutil.getMarNotNullRows();

    for (int row : rows) {
      if (RowStatus.DELETED == this.keyValue.getRowStatus(row)) {
        continue;
      }
      UFDouble astnum =
          this.keyValue.getBodyUFDoubleValue(row, PreOrderBVO.NASTNUM);
      totalastnum = MathTool.add(totalastnum, astnum);

      UFBoolean largessflag =
          this.keyValue.getBodyUFBooleanValue(row, SaleOrderBVO.BLARGESSFLAG);
      if (null != largessflag && largessflag.booleanValue()) {
        continue;
      }
      UFDouble origtaxmny =
          this.keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NORIGTAXMNY);
      totalorigmny = MathTool.add(totalorigmny, origtaxmny);

    }
    this.keyValue.setHeadValue(PreOrderHVO.NTOTALNUM, totalastnum);
    this.keyValue.setHeadValue(PreOrderHVO.NHEADSUMMNY, totalorigmny);
  }
}
