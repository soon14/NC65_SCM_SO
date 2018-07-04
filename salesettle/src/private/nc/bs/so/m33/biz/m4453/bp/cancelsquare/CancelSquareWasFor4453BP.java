package nc.bs.so.m33.biz.m4453.bp.cancelsquare;

import nc.bs.so.m33.maintain.m4453.DeleteSquare4453BP;
import nc.vo.so.m33.m4453.entity.SquareWasDetailVO;
import nc.vo.so.m33.m4453.entity.SquareWasVO;

public class CancelSquareWasFor4453BP {

  public void cancelSquare(SquareWasDetailVO[] sqdvos, SquareWasVO[] vos) {

    new CancelSquareWasDetailPubFor4453BP().cancelSquare(sqdvos, vos);

    // 自动结算删除结算单
    new DeleteSquare4453BP().delete(vos);
  }

}
