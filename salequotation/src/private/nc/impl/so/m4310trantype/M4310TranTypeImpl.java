package nc.impl.so.m4310trantype;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pub.pf.ITranstypeBiz;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pub.smart.BatchSaveAction;
import nc.itf.scmpub.reference.uap.bd.refcheck.ReferenceCheck;
import nc.itf.so.m4310trantype.IM4310TranTypeService;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.so.m4310trantype.entity.M4310TranTypeVO;
import nc.vo.so.pub.SOTable;

/**
 * 发货单交易类型业务实现类
 * 
 * @since 6.1
 * @version 2012-11-29 09:45:53
 * @author 冯加彬
 */
public class M4310TranTypeImpl implements ITranstypeBiz, IM4310TranTypeService {

  @Override
  public void deleteTransType(Object userObj) throws BusinessException {
    M4310TranTypeVO vo = (M4310TranTypeVO) userObj;
    this.checkReferenced(vo);
    BatchOperateVO boVO = new BatchOperateVO();
    boVO.setDelObjs(new M4310TranTypeVO[] {
      vo
    });
    this.maintainTranTypeExtProps(boVO);
  }

  @Override
  public void execOnDelPublish(BilltypeVO transTypeVO,
      ArrayList<FuncRegisterVO> funcVOs) throws BusinessException {
    // NO PROCESS

  }

  @Override
  public void execOnPublish(String nodecode, String newNodecode,
      boolean isExecFunc) throws BusinessException {
    // NO PROCESS

  }

  @Override
  public M4310TranTypeVO[] queryAllTranType(String pkGroup, String pkBilltype)
      throws BusinessException {
    String sql = this.getQueryAllSql(pkGroup, pkBilltype);
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql);
    if (rowset.size() == 0) {
      return new M4310TranTypeVO[0];
    }
    String[] ids = rowset.toOneDimensionStringArray();
    VOQuery<M4310TranTypeVO> query =
        new VOQuery<M4310TranTypeVO>(M4310TranTypeVO.class);
    M4310TranTypeVO[] vos = query.query(ids);
    return vos;
  }

  @Override
  public M4310TranTypeVO queryTranType(String pkGroup, String pkBilltypecode)
      throws BusinessException {
    String sql = this.getQuerySql(pkGroup, new String[] {
      pkBilltypecode
    });
    return this.queryTranType(sql)[0];
  }

  @Override
  public M4310TranTypeVO[] queryTranType(String pkGroup, String[] pkBilltypecode)
      throws BusinessException {
    String sql = this.getQuerySql(pkGroup, pkBilltypecode);
    return this.queryTranType(sql);
  }

  @Override
  public void saveTransType(Object userObj) throws BusinessException {
    M4310TranTypeVO vo = (M4310TranTypeVO) userObj;
    this.checkData(vo);
    BatchOperateVO boVO = new BatchOperateVO();
    boVO.setAddObjs(new M4310TranTypeVO[] {
      vo
    });
    this.maintainTranTypeExtProps(boVO);
  }

  @Override
  public void updateTransType(Object userObj) throws BusinessException {
    M4310TranTypeVO vo = (M4310TranTypeVO) userObj;
    this.checkData(vo);
    BatchOperateVO boVO = new BatchOperateVO();
    boVO.setUpdObjs(new M4310TranTypeVO[] {
      vo
    });
    this.maintainTranTypeExtProps(boVO);
  }

  private void checkData(M4310TranTypeVO vo) {
    if (vo.getIavgmonth() == null) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0040")/*@res "平均报价月数必填！"*/);
    }
    if (vo.getIavgmonth().intValue() <= 0) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0041")/*@res "平均报价月数必须是正数！"*/);
    }
  }

  private void checkReferenced(M4310TranTypeVO vo) {
    List<String> pklist = new ArrayList<String>();
    pklist.add(vo.getVtrantype());
    if (ReferenceCheck.isReferenced("so_salequotationtype",
        pklist.toArray(new String[pklist.size()]))) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0042")/*@res "要删除的交易类型已被引用，请检查。"*/);
    }
  }

  private String getQueryAllSql(String pkGroup, String pkBilltype) {
    StringBuffer sql = new StringBuffer();
    sql.append("select pk_trantype ");
    sql.append("  from so_salequotationtype s ");
    sql.append(" where s.vtrantype in ");
    sql.append(" (select b.pk_billtypecode ");
    sql.append(" from bd_billtype b ");
    sql.append(" where b.parentbilltype = '" + pkBilltype + "' ");
    sql.append(" and b.pk_group = '" + pkGroup+"')");
    sql.append(" and s.pk_group = '" + pkGroup+"'");
    // modify by zhangby5 此处把集团全局的也拼进去了，会出现重复的交易类型，应该只能查询当前集团的交易类型
    return sql.toString();
  }

  private String getQuerySql(String pk_group, String[] pk_billtypecode) {
    SqlBuilder sql = new SqlBuilder();
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    sql.append("select pk_trantype from so_salequotationtype where dr=0 and ");
    sql.append(M4310TranTypeVO.PK_GROUP, pk_group);
    // modify by zhangby5 此处把集团全局的也拼进去了，会出现重复的交易类型，应该只能查询当前集团的交易类型
    sql.append(" and ");
    sql.append(iq.buildSQL(M4310TranTypeVO.VTRANTYPE, pk_billtypecode));
    return sql.toString();
  }

  private void maintainTranTypeExtProps(BatchOperateVO bOVO) {
    BatchSaveAction<M4310TranTypeVO> action =
        new BatchSaveAction<M4310TranTypeVO>();
    action.batchSave(bOVO);
  }

  private M4310TranTypeVO[] queryTranType(String sql) {
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql);
    if (rowset.size() == 0) {
      return new M4310TranTypeVO[0];
    }
    String[] ids = rowset.toOneDimensionStringArray();
    VOQuery<M4310TranTypeVO> query =
        new VOQuery<M4310TranTypeVO>(M4310TranTypeVO.class);
    M4310TranTypeVO[] vos = query.query(ids);
    if (vos == null || vos.length == 0) {
      return new M4310TranTypeVO[0];
    }
    return vos;
  }
}
