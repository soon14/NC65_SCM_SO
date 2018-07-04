package nc.vo.so.m30.rule;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.keyvalue.AbstractKeyValue.RowStatus;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.BodyValueRowRule;
import nc.vo.so.pub.util.SOSysParaInitUtil;

public class HeadTotalCalculateRule {
  private IKeyValue keyValue;

  public HeadTotalCalculateRule(IKeyValue keyValue) {
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
    // 合计原币含税金额
    UFDouble totalorigmny = UFDouble.ZERO_DBL;
    // 合计原币含税金额（只包含赠品）
    UFDouble lrgtotalorigmny = UFDouble.ZERO_DBL;
    // 合计原币费用冲抵金额金额
    UFDouble totalsuborigmny = UFDouble.ZERO_DBL;
    // 合计原币冲减前金额
    UFDouble totalntotalmny = UFDouble.ZERO_DBL;
    BodyValueRowRule countutil = new BodyValueRowRule(this.keyValue);
    int[] rows = countutil.getMarNotNullRows();
    for (int row : rows) {
      if (RowStatus.DELETED == this.keyValue.getRowStatus(row)) {
        continue;
      }
      UFDouble astnum =
          this.keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NASTNUM);
      totalastnum = MathTool.add(totalastnum, astnum);

      UFDouble piece =
          this.keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NPIECE);
      totalpiece = MathTool.add(totalpiece, piece);

      UFDouble volume =
          this.keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NVOLUME);
      totalvolume = MathTool.add(totalvolume, volume);

      UFDouble weight =
          this.keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NWEIGHT);
      totalweight = MathTool.add(totalweight, weight);

      UFBoolean largessflag =
          this.keyValue.getBodyUFBooleanValue(row, SaleOrderBVO.BLARGESSFLAG);
      if (null != largessflag && largessflag.booleanValue()) {
        UFDouble lrgorigtaxmny =
            this.keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NORIGTAXMNY);
        lrgtotalorigmny = MathTool.add(lrgtotalorigmny, lrgorigtaxmny);
        continue;
      }
      UFDouble origtaxmny =
          this.keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NORIGTAXMNY);
      totalorigmny = MathTool.add(totalorigmny, origtaxmny);

      UFDouble origsubmny =
          this.keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NORIGSUBMNY);
      totalsuborigmny = MathTool.add(totalsuborigmny, origsubmny);

      UFDouble nbforigsubmny =
          this.keyValue.getBodyUFDoubleValue(row, SaleOrderBVO.NBFORIGSUBMNY);
      totalntotalmny = MathTool.add(totalntotalmny, nbforigsubmny);

    }
    this.keyValue.setHeadValue(SaleOrderHVO.NTOTALNUM, totalastnum);
    this.keyValue.setHeadValue(SaleOrderHVO.NTOTALPIECE, totalpiece);
    this.keyValue.setHeadValue(SaleOrderHVO.NTOTALVOLUME, totalvolume);
    this.keyValue.setHeadValue(SaleOrderHVO.NTOTALWEIGHT, totalweight);
    this.keyValue.setHeadValue(SaleOrderHVO.NTOTALORIGMNY, totalorigmny);
    this.keyValue.setHeadValue(SaleOrderHVO.NLRGTOTALORIGMNY, lrgtotalorigmny);
    this.keyValue.setHeadValue(SaleOrderHVO.NTOTALORIGSUBMNY, totalsuborigmny);
    this.keyValue.setHeadValue(SaleOrderHVO.NTOTALMNY, totalntotalmny);

    // --根据收款比例和价税合计,计算收款限额
    PreceiveQuotaRule prequotarule = new PreceiveQuotaRule(this.keyValue);
    String pk_org = this.keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);
    String sSO03 = SOSysParaInitUtil.getSO03(pk_org);
    prequotarule.calculateByPara(sSO03);
  }
}
