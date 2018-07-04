package nc.bs.so.m33.maintain.m4c;

import nc.impl.pubapp.pattern.data.vo.VODelete;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;

public class DeleteSquareOutDetailBP {

  public void delete(SquareOutDetailVO[] bills) {
    
    VODelete<SquareOutDetailVO> bo = new VODelete<SquareOutDetailVO>();
    bo.delete(bills);
  }
}
