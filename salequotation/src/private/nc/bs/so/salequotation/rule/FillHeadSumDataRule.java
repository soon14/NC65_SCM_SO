package nc.bs.so.salequotation.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

/**
 * @description
 * 销售报价单保存前计算表头:总数量、加税合计
 * @scene
 * 销售报价单新增、修改保存前
 * @param
 * 无
 */
public class FillHeadSumDataRule implements IRule<AggSalequotationHVO> {

  @Override
  public void process(AggSalequotationHVO[] vos) {
    for (AggSalequotationHVO vo : vos) {
      this.sumOneBill(vo);
    }
  }

  private void sumOneBill(AggSalequotationHVO bill) {
    SalequotationHVO header = bill.getParentVO();
    UFDouble tastnum = UFDouble.ZERO_DBL;
    UFDouble ttaxmny = UFDouble.ZERO_DBL;

    SalequotationBVO[] items = bill.getChildrenVO();
    for (SalequotationBVO item : items) {
      int vostatus = item.getStatus();
      if (vostatus == VOStatus.DELETED) {
        continue;
      }
      UFDouble astnum = item.getNassistnum();
      if (astnum != null) {
        tastnum = tastnum.add(astnum);
      }
      // 赠品行金额不在表头汇总
      UFBoolean largessflag = item.getBlargessflag();
      if (largessflag != null && largessflag.booleanValue()) {
        continue;
      }
      ttaxmny = MathTool.add(ttaxmny, item.getNorigtaxmny());
    }

    header.setNtotalnum(tastnum);
    header.setNtotalmny(ttaxmny);
  }
}
