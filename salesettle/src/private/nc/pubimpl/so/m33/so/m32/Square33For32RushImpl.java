package nc.pubimpl.so.m33.so.m32;

import nc.bs.so.m33.biz.m32.bp.check.SquareInvoiceCheckBP;
import nc.bs.so.m33.maintain.m32.query.QuerySquare32VOBP;
import nc.pubitf.so.m33.so.m32.ISquare33For32Rush;
import nc.vo.so.m33.m32.entity.SquareInvVO;

public class Square33For32RushImpl implements ISquare33For32Rush {

  @Override
  public void checkIFCanInvoiceRush(String[] invoiceIDs) {
    // 查询结算单数据
    SquareInvVO[] sqvos =
        new QuerySquare32VOBP().querySquareInvVOBy32ID(invoiceIDs);
    // 如果发票审批没有配置结算动作 则返回
    if (null == sqvos || sqvos.length == 0) {
      return;
    }
    // 如果上游出库单部分暂估、发出商品，则发票不可生成对冲发票
    new SquareInvoiceCheckBP().checkETREGForCreateRushInvoice(sqvos);
  }

}
