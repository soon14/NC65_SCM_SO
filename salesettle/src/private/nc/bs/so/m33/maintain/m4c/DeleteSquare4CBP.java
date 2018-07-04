package nc.bs.so.m33.maintain.m4c;

import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.vo.so.m33.m4c.entity.SquareOutVO;

public class DeleteSquare4CBP {

  public void delete(SquareOutVO[] bills) {
    BillDelete<SquareOutVO> bo = new BillDelete<SquareOutVO>();
    bo.delete(bills);
  }

}
