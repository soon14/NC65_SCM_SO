package nc.bs.so.m30.rule.billcode;

import nc.bs.scmpub.app.flow.billcode.BillCodeInfoBuilder;
import nc.impl.pubapp.bill.billcode.BillCodeInfo;
import nc.impl.pubapp.bill.billcode.BillCodeUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @description
 * 检查单据号是否重复
 * @scene
 * 销售订单新增、修改保存后
 * @param 
 * 无
 */
public class CheckUniqueBillCodeRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    BillCodeInfo info =
        BillCodeInfoBuilder.buildBillCodeInfo(SOBillType.Order.getCode(), SaleOrderHVO.VBILLCODE,
            SaleOrderHVO.PK_GROUP, SaleOrderHVO.PK_ORG, SaleOrderHVO.VTRANTYPECODE);
    BillCodeUtils util = new BillCodeUtils(info);
    util.checkUnique(vos);
  }

}
