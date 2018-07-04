package nc.bs.so.m33.maintain.m4c;

import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;

public class InsertSquareOutDetailBP {

  public SquareOutVO[] insert(SquareOutVO[] sqvos, SquareOutDetailVO[] bills) {

    VOInsert<SquareOutDetailVO> bo = new VOInsert<SquareOutDetailVO>();
    SquareOutDetailVO[] vos = bo.insert(bills);
    // 将结算明细ID赋给SquareOutVO
    SquareOutVOUtils.getInstance().fillDidToSquareVO(sqvos, vos);
    return sqvos;
  }
}