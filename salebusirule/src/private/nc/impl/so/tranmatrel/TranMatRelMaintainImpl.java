package nc.impl.so.tranmatrel;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.so.tranmatrel.action.DeleteAction;
import nc.impl.so.tranmatrel.action.InsertAction;
import nc.impl.so.tranmatrel.action.UpdateAction;
import nc.itf.so.tranmatrel.ITranMatRelMaintain;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.tranmatrel.entity.TranMatRelVO;

public class TranMatRelMaintainImpl implements ITranMatRelMaintain {

  @Override
  public TranMatRelVO update(TranMatRelVO bill) throws BusinessException {
    UpdateAction action = new UpdateAction();
    return action.update(bill);
  }

  @Override
  public TranMatRelVO delete(TranMatRelVO bill) throws BusinessException {
    DeleteAction action = new DeleteAction();
    return action.delete(bill);
  }

  @Override
  public TranMatRelVO insert(TranMatRelVO bill) throws BusinessException {
    InsertAction action = new InsertAction();
    return action.insert(bill);
  }

  @Override
  public TranMatRelVO queryByOrg(String pk_org) throws BusinessException {
    TranMatRelVO bill = null;
    try {
      DataAccessUtils utils = new DataAccessUtils();
      StringBuffer whereSql = new StringBuffer();
      whereSql.append("so_tranmatrel.pk_org = '" + pk_org + "' ").append(
          "and so_tranmatrel.dr = 0 ");
      String sql = "select pk_tranmatrel from so_tranmatrel where " + whereSql;
      IRowSet rowset = utils.query(sql);
      String[] cbillids = rowset.toOneDimensionStringArray();

      // ¸ù¾Ýid²éÑ¯VO
      BillQuery<TranMatRelVO> query =
          new BillQuery<TranMatRelVO>(TranMatRelVO.class);
      TranMatRelVO[] bills = query.query(cbillids);
      if ((bills != null) && (bills.length > 0)) {
        bill = bills[0];
      }
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return bill;
  }

}
