package nc.bs.so.m33.maintain.m32;

import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.vo.so.m33.m32.entity.SquareInvVO;

public class DeleteSquare32BP {

  public void delete(SquareInvVO[] bills) {
    
    BillDelete<SquareInvVO> bo = new BillDelete<SquareInvVO>();
    bo.delete(bills);
  }
}
