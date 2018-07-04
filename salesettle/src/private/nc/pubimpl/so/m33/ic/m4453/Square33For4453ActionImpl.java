package nc.pubimpl.so.m33.ic.m4453;

import nc.bs.so.m33.biz.m4453.action.CancelSquareFor4453Action;
import nc.bs.so.m33.biz.m4453.action.SquareFor4453Action;
import nc.impl.pubapp.pattern.data.bill.tool.BillConcurrentTool;
import nc.pubitf.so.m33.ic.m4453.ISquareAcionFor4453;
import nc.vo.ic.m4453.entity.WastageVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class Square33For4453ActionImpl implements ISquareAcionFor4453 {

  @Override
  public void cancelSoSquare(WastageVO[] wasvos) throws BusinessException {
    // 对上游途损单加锁
    BillConcurrentTool tool = new BillConcurrentTool();
    tool.lockBill(wasvos);
    try {
      new CancelSquareFor4453Action().cancelSoSquare(wasvos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public void soSquare(WastageVO[] wasvos) throws BusinessException {
    // 对上游途损单加锁
    BillConcurrentTool tool = new BillConcurrentTool();
    tool.lockBill(wasvos);

    try {
      new SquareFor4453Action().soSquare(wasvos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

}
