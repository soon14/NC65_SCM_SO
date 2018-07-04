package nc.bs.so.salequotation.rule;

import nc.bs.scmpub.app.flow.billcode.BillCodeInfoBuilder;
import nc.impl.pubapp.bill.billcode.BillCodeInfo;
import nc.impl.pubapp.bill.billcode.BillCodeUtils;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.price.pub.context.PriceContext;
import nc.vo.pubapp.util.AuditInfoUtils;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

/**
 * @description
 * 销售报价单修改保存前单据号检查
 * @scene
 * 销售报价单修改保存前
 * @param
 * 无
 */
public class BillNOUpdateRule implements ICompareRule<AggSalequotationHVO> {

  @Override
  public void process(AggSalequotationHVO[] vos, AggSalequotationHVO[] originVOs) {
    BillCodeInfo info =
        BillCodeInfoBuilder.buildBillCodeInfo(PriceContext.SALEQUOBILLTYPE,
            SalequotationHVO.VBILLCODE, SalequotationHVO.PK_GROUP,
            SalequotationHVO.PK_ORG, SalequotationHVO.VTRANTYPE);
    BillCodeUtils util = new BillCodeUtils(info);
    util.upadteBillCode(vos, originVOs);
    util.checkUnique(vos);
    // TailFieldSetter.setTailDefaultValue(vos, false);
    AuditInfoUtils.setUpdateAuditInfo(vos);
  }
}
