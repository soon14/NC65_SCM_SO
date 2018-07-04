package nc.bs.so.m33.maintain.m4453;

import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.vo.so.m33.m4453.entity.SquareWasDetailVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;
import nc.vo.so.m33.m4453.entity.SquareWasVOUtils;

public class InsertSquareWasDetailBP {

  public SquareWasVO[] insert(SquareWasVO[] sqvos, SquareWasDetailVO[] bills) {

    VOInsert<SquareWasDetailVO> bo = new VOInsert<SquareWasDetailVO>();
    SquareWasDetailVO[] vos = bo.insert(bills);
    // 将结算明细ID赋给SquareWasVO
    SquareWasVOUtils.getInstance().fillDidToSquareVO(sqvos, vos);
    return sqvos;
  }

}