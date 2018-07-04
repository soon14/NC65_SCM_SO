package nc.impl.so.depmatrel;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.so.depmatrel.action.DeleteAction;
import nc.impl.so.depmatrel.action.InsertAction;
import nc.impl.so.depmatrel.action.UpdateAction;
import nc.itf.so.depmatrel.IDepMatRelMaintain;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.depmatrel.entity.DepMatRelVO;

public class DepMatRelMaintainImpl implements IDepMatRelMaintain {

  @Override
  public DepMatRelVO update(DepMatRelVO bill) throws BusinessException {
    UpdateAction action = new UpdateAction();
    return action.update(bill);
  }

  @Override
  public DepMatRelVO delete(DepMatRelVO bill) throws BusinessException {
    DeleteAction action = new DeleteAction();
    return action.delete(bill);
  }

  @Override
  public DepMatRelVO insert(DepMatRelVO bill) throws BusinessException {
    InsertAction action = new InsertAction();
    return action.insert(bill);
  }

  @Override
  public DepMatRelVO queryByOrg(String pk_org) throws BusinessException {
    DepMatRelVO bill = null;
    try {
      DataAccessUtils utils = new DataAccessUtils();
      StringBuffer whereSql = new StringBuffer();
      whereSql.append("so_depmatrel.pk_org = '" + pk_org + "' ").append(
          "and so_depmatrel.dr = 0 ");
      String sql = "select pk_depmatrel from so_depmatrel where " + whereSql;
      IRowSet rowset = utils.query(sql);
      String[] cbillids = rowset.toOneDimensionStringArray();

      // ¸ù¾Ýid²éÑ¯VO
      BillQuery<DepMatRelVO> query =
          new BillQuery<DepMatRelVO>(DepMatRelVO.class);
      DepMatRelVO[] bills = query.query(cbillids);
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
