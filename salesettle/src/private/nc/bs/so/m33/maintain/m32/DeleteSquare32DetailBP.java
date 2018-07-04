package nc.bs.so.m33.maintain.m32;

import nc.impl.pubapp.pattern.data.vo.VODelete;
import nc.vo.so.m33.m32.entity.SquareInvDetailVO;

public class DeleteSquare32DetailBP {

  public void delete(SquareInvDetailVO[] bills) {
    
    VODelete<SquareInvDetailVO> bo = new VODelete<SquareInvDetailVO>();
    bo.delete(bills);
  }
}
