package nc.bs.so.m4331.maintain.rule.delete;

import nc.bs.scmpub.app.flow.billcode.BillCodeInfoBuilder;
import nc.impl.pubapp.bill.billcode.BillCodeInfo;
import nc.impl.pubapp.bill.billcode.BillCodeUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * @description
 * 销售发货单删除后退回单据号
 * @scene
 * 销售发货单删除后
 * @param
 * 无
 */
public class ReturnBillCodeRule implements IRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] vos) {

    BillCodeInfo info =
        BillCodeInfoBuilder.buildBillCodeInfo(SOBillType.Delivery.getCode(), DeliveryHVO.VBILLCODE,
            DeliveryHVO.PK_GROUP, DeliveryHVO.PK_ORG, DeliveryHVO.VTRANTYPECODE);
    BillCodeUtils util = new BillCodeUtils(info);
    util.returnBillCode(vos);
  }
}
