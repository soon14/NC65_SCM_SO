package nc.bs.so.m32.maintain.rule.insert;

import nc.bs.scmpub.app.flow.billcode.BillCodeInfoBuilder;
import nc.impl.pubapp.bill.billcode.BillCodeInfo;
import nc.impl.pubapp.bill.billcode.BillCodeUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * @description
 * 销售发票保存后：
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>功能条目：
 *  单据号重复性校验
 * </ul>
 * <p>
 * @scene
 * 销售发票新增、修改保存后
 * @param
 * 无
 * @version 本版本号 6.0
 * @since 上一版本号 5.6
 * @author 冯加滨
 * @time 2010-1-21 下午05:14:11
 */
public class CheckBillCodeRule implements IRule<SaleInvoiceVO> {

  @Override
  public void process(SaleInvoiceVO[] vos) {
    BillCodeInfo info =
        BillCodeInfoBuilder.buildBillCodeInfo(SOBillType.Invoice.getCode(),
            SaleInvoiceHVO.VBILLCODE, SaleInvoiceHVO.PK_GROUP,
            SaleInvoiceHVO.PK_ORG, SaleInvoiceHVO.VTRANTYPECODE);
    BillCodeUtils util = new BillCodeUtils(info);
    util.checkUnique(vos);

  }

}
