package nc.bs.so.salequotation.rule;

import nc.bs.scmpub.app.flow.billcode.BillCodeInfoBuilder;
import nc.impl.pubapp.bill.billcode.BillCodeInfo;
import nc.impl.pubapp.bill.billcode.BillCodeUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.price.pub.context.PriceContext;
import nc.vo.pubapp.util.AuditInfoUtils;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

/**
 * @description
 * 销售报价单新增保存前单据号检查
 * @scene
 * 销售报价单新增保存前
 * @param
 * 无
 */
public class BillNOInsertRule implements IRule<AggSalequotationHVO> {

  @Override
  public void process(AggSalequotationHVO[] vos) {
    BillCodeInfo info =
        BillCodeInfoBuilder.buildBillCodeInfo(PriceContext.SALEQUOBILLTYPE,
            SalequotationHVO.VBILLCODE, SalequotationHVO.PK_GROUP,
            SalequotationHVO.PK_ORG, SalequotationHVO.VTRANTYPE);
    BillCodeUtils util = new BillCodeUtils(info);
    util.createBillCode(vos);
    util.checkUnique(vos);
    // this.setTailDefaultValue(vos);
    AuditInfoUtils.setAddAuditInfo(vos);
  }

  // private void setTailDefaultValue(AggSalequotationHVO[] vos) {
  // InvocationInfoProxy proxy = InvocationInfoProxy.getInstance();
  // if (vos == null) {
  // return;
  // }
  // for (AggSalequotationHVO vo : vos) {
  // if (vo == null) {
  // continue;
  // }
  // SalequotationHVO hvo = (SalequotationHVO) vo.getParent();
  // if (hvo != null) {
  // UFDateTime serverTime =
  // NCLocator.getInstance().lookup(ITimeService.class).getUFDateTime();
  // hvo.setDbilldate(serverTime);
  // // hvo.setOperator(proxy.getUserId());
  // hvo.setBillmaker(null);
  // hvo.setCreator(null);
  // hvo.setModifiedtime(serverTime);
  // hvo.setModifier(proxy.getUserId());
  // }
  // }
  // }
}
