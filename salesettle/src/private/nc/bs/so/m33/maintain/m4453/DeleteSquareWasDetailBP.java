package nc.bs.so.m33.maintain.m4453;

import nc.impl.pubapp.pattern.data.vo.VODelete;
import nc.vo.so.m33.m4453.entity.SquareWasDetailVO;

public class DeleteSquareWasDetailBP {

  public void delete(SquareWasDetailVO[] bills) {
    VODelete<SquareWasDetailVO> bo = new VODelete<SquareWasDetailVO>();
    bo.delete(bills);
  }

}
