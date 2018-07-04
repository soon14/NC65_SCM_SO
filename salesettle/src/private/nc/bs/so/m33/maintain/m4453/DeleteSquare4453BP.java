package nc.bs.so.m33.maintain.m4453;

import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.vo.so.m33.m4453.entity.SquareWasVO;

public class DeleteSquare4453BP {

  public void delete(SquareWasVO[] bills) {
    BillDelete<SquareWasVO> bo = new BillDelete<SquareWasVO>();
    bo.delete(bills);
  }

}
