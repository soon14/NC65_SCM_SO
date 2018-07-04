package nc.impl.so.m32trantype;

import java.util.ArrayList;

import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.so.m32trantype.entity.M32TranTypeVO;

import nc.itf.so.m32trantype.IM32TranTypeService;

import nc.bs.pub.pf.ITranstypeBiz;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pub.smart.BatchSaveAction;

/**
 * 发票交易类型接口实现类
 * 
 * @since 6.3
 * @version 2012-12-21 上午10:07:59
 * @author yaogj
 */
public class M32TranTypeImpl implements ITranstypeBiz, IM32TranTypeService {

  @Override
  public void deleteTransType(Object userObj) throws BusinessException {
    M32TranTypeVO bill = (M32TranTypeVO) userObj;
    BatchOperateVO bills = new BatchOperateVO();
    bills.setDelObjs(new M32TranTypeVO[] {
      bill
    });
    this.executeAction(bills);

  }

  @Override
  public void execOnDelPublish(BilltypeVO transTypeVO,
      ArrayList<FuncRegisterVO> funcVOs) throws BusinessException {
    return;
  }

  @Override
  public void execOnPublish(String nodecode, String newNodecode,
      boolean isExecFunc) throws BusinessException {
    return;

  }

  @Override
  public M32TranTypeVO queryTranType(String pk_group, String vtrantype)
      throws BusinessException {
    String sql = this.getQuerySql(pk_group, vtrantype);
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql);
    if (rowset.size() == 0) {
      return new M32TranTypeVO();
    }
    String[] ids = rowset.toOneDimensionStringArray();
    VOQuery<M32TranTypeVO> query =
        new VOQuery<M32TranTypeVO>(M32TranTypeVO.class);
    M32TranTypeVO[] bills = query.query(ids);
    if (bills == null || bills.length == 0) {
      return new M32TranTypeVO();
    }
    return bills[0];
  }

  @Override
  public void saveTransType(Object userObj) throws BusinessException {
    M32TranTypeVO bill = (M32TranTypeVO) userObj;
    BatchOperateVO bills = new BatchOperateVO();
    bills.setAddObjs(new M32TranTypeVO[] {
      bill
    });
    this.executeAction(bills);

  }

  @Override
  public void updateTransType(Object userObj) throws BusinessException {
    M32TranTypeVO bill = (M32TranTypeVO) userObj;
    BatchOperateVO bills = new BatchOperateVO();
    bills.setUpdObjs(new M32TranTypeVO[] {
      bill
    });
    this.executeAction(bills);
  }

  /**
   * 方法功能描述：执行相应的动作。
   * <p>
   * <b>参数说明</b>
   * 
   * @param bills
   *          <p>
   * @author 冯加滨
   * @throws BusinessException
   * @time 2010-1-25 下午06:48:26
   */
  private void executeAction(BatchOperateVO bills) throws BusinessException {
    try {
      BatchSaveAction<M32TranTypeVO> action =
          new BatchSaveAction<M32TranTypeVO>();
      action.batchSave(bills);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  private String getQuerySql(String pk_group, String vtrantype) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select pk_trantype from so_m32trantype where dr=0 and ");
    sql.startParentheses();
    sql.append(M32TranTypeVO.PK_GROUP, pk_group);
    sql.append(" or ");
    sql.append(M32TranTypeVO.PK_GROUP, "global00000000000000");
    sql.endParentheses();
    sql.append(" and ");
    sql.append(M32TranTypeVO.VTRANTYPECODE, vtrantype);
    return sql.toString();
  }

}
