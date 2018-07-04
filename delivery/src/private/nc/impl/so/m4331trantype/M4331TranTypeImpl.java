package nc.impl.so.m4331trantype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.so.m4331trantype.entity.M4331trantypeVO;
import nc.vo.so.pub.SOTable;
import nc.vo.trade.checkrule.VOChecker;

import nc.itf.so.m4331trantype.IM4331TranTypeService;

import nc.bs.pub.pf.ITranstypeBiz;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pub.smart.BatchSaveAction;

/**
 * 发货单交易类型操作、服务接口实现类
 * 
 * @since 6.1
 * @version 2012-11-29 10:58:57
 * @author 冯加彬
 */
public class M4331TranTypeImpl implements ITranstypeBiz, IM4331TranTypeService {

  @Override
  public void deleteTransType(Object userObj) throws BusinessException {
    M4331trantypeVO bill = (M4331trantypeVO) userObj;
    BatchOperateVO bills = new BatchOperateVO();
    bills.setDelObjs(new M4331trantypeVO[] {
      bill
    });
    this.executeAction(bills);

  }

  @Override
  public void execOnDelPublish(BilltypeVO transTypeVO,
      ArrayList<FuncRegisterVO> funcVOs) throws BusinessException {
    return;
    // String message = "删除交易类型时，execOnDelPublish功能未实现";
    // ExceptionUtils.wrappBusinessException(message);
  }

  @Override
  public void execOnPublish(String nodecode, String newNodecode,
      boolean isExecFunc) throws BusinessException {
    return;
    // String message = "交易类型发布为节点时的扩展处理execOnPublish未实现";
    // ExceptionUtils.wrappBusinessException(message);

  }

  @Override
  public M4331trantypeVO queryTranType(String pk_group, String vtrantype)
      throws BusinessException {
    String sql = this.getQuerySql(pk_group, new String[] {
      vtrantype
    });
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql);
    if (rowset.size() > 0) {
      String[] ids = rowset.toOneDimensionStringArray();
      VOQuery<M4331trantypeVO> query =
          new VOQuery<M4331trantypeVO>(M4331trantypeVO.class);
      M4331trantypeVO[] bills = query.query(ids);
      if (!VOChecker.isEmpty(bills)) {
        return bills[0];
      }
    }
    return new M4331trantypeVO();
  }

  @Override
  public Map<String, UFBoolean> queryTranTypes(String pk_group,
      String[] vtrantypes) throws BusinessException {
    Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
    for (String billtyp : vtrantypes) {
      map.put(billtyp, UFBoolean.FALSE);
    }
    String sql = this.getQuerySql(pk_group, vtrantypes);
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql);
    if (rowset.size() == 0) {
      return map;
    }
    String[] ids = rowset.toOneDimensionStringArray();
    VOQuery<M4331trantypeVO> query =
        new VOQuery<M4331trantypeVO>(M4331trantypeVO.class);
    M4331trantypeVO[] bills = query.query(ids);
    for (M4331trantypeVO bill : bills) {
      String billtype = bill.getVtrantypecode();
      map.put(billtype, bill.getBonceoutflag());
    }
    return map;
  }

  @Override
  public void saveTransType(Object userObj) throws BusinessException {
    M4331trantypeVO bill = (M4331trantypeVO) userObj;
    BatchOperateVO bills = new BatchOperateVO();
    bills.setAddObjs(new M4331trantypeVO[] {
      bill
    });
    this.executeAction(bills);

  }

  @Override
  public void updateTransType(Object userObj) throws BusinessException {
    M4331trantypeVO bill = (M4331trantypeVO) userObj;
    BatchOperateVO bills = new BatchOperateVO();
    bills.setUpdObjs(new M4331trantypeVO[] {
      bill
    });
    this.executeAction(bills);
  }

  private void executeAction(BatchOperateVO bills) throws BusinessException {
    try {
      BatchSaveAction<M4331trantypeVO> action =
          new BatchSaveAction<M4331trantypeVO>();
      action.batchSave(bills);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  private String getQuerySql(String pk_group, String[] vtrantypes) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select pk_trantype from so_m4331trantype "
        + "where dr=0 and (pk_group ='~' or ");
    sql.append(M4331trantypeVO.PK_GROUP, pk_group);
    sql.append(") and ");
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sql.append(iq.buildSQL(M4331trantypeVO.VTRANTYPECODE, vtrantypes));
    return sql.toString();
  }
}
