package nc.bs.so.m33.maintain.m32;

import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.vo.so.m33.m32.entity.SquareInvDetailVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;

public class InsertSquare32DetailBP {

  public SquareInvVO[] insert(SquareInvVO[] sqvos, SquareInvDetailVO[] bills) {

    VOInsert<SquareInvDetailVO> bo = new VOInsert<SquareInvDetailVO>();
    SquareInvDetailVO[] vos = bo.insert(bills);
    // 将结算明细ID赋给SquareInvVO
    SquareInvVOUtils.getInstance().fillDidToSquareVO(sqvos, vos);
    return sqvos;
  }

}