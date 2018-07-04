package nc.impl.so.mbuylargess.service;

import java.util.HashSet;
import java.util.Set;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pub.smart.SmartServiceImpl;
import nc.impl.so.mbuylargess.action.DeleteBuyLargessAction;
import nc.impl.so.mbuylargess.action.InsertBuyLargessAction;
import nc.impl.so.mbuylargess.action.UpdateBuyLargessAction;
import nc.itf.so.mbuylagress.IBuyLargessMaintain;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.so.mbuylargess.entity.BuyLargessHVO;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;
import nc.vo.uif2.LoginContext;

public class BuyLargessMaintainImpl extends SmartServiceImpl implements
    IBuyLargessMaintain {

  @Override
  public void deleteBuylargess(BuyLargessVO todelVO) throws BusinessException {

    BuyLargessVO[] billvos = new BuyLargessVO[] {
      todelVO
    };

    TimeLog.logStart();
    BillTransferTool<BuyLargessVO> transferTool =
        new BillTransferTool<BuyLargessVO>(billvos);

    billvos = transferTool.getClientFullInfoBill();

    TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006003_0", "04006003-0012")/*@res "前台单据VO补充完整"*/);

    DeleteBuyLargessAction action = new DeleteBuyLargessAction();
    action.deleteBuylargess(billvos);

  }

  @Override
  public BuyLargessVO insertBuylargess(BuyLargessVO newVO)
      throws BusinessException {
    InsertBuyLargessAction action = new InsertBuyLargessAction();
    BuyLargessVO[] retnewbill = action.insertBuylargess(new BuyLargessVO[] {
      newVO
    });
    return retnewbill[0];
  }

  @Override
  public BuyLargessVO[] queryBuylargess(IQueryScheme queryScheme)
      throws BusinessException {
    BuyLargessVO[] bills = null;
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    SqlBuilder sql = new SqlBuilder();
    String mainTableAlias = qrySchemeProcessor.getMainTableAlias();
    sql.append("select distinct(" + mainTableAlias + ".pk_buylargess)");
    sql.append(qrySchemeProcessor.getFinalFromWhere());
    sql.append(" and " + mainTableAlias + ".dr", 0);
    sql.append(" and " + mainTableAlias + ".pk_group", BSContext.getInstance()
        .getGroupID());
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql.toString());
    String[] cbillids = rowset.toOneDimensionStringArray();

    // 根据id查询VO
    BillQuery<BuyLargessVO> query =
        new BillQuery<BuyLargessVO>(BuyLargessVO.class);
    bills = query.query(cbillids);
    return bills;
  }

  @Override
  public String queryMaterialClassMeas(String materialclasscode)
      throws BusinessException {
    // 根据参数SO79:物料分类方式 SO80:客户分类方式 设置分类字段显示属性
    String so79 = "物料基本分类";/*-=notranslate=-*/
    SqlBuilder querysql = null;
    if ("物料基本分类".equals(so79)) {/*-=notranslate=-*/
      querysql = new SqlBuilder();
      querysql.append("select bs_material.pk_measdoc from bs_material "
          + "where bs_material.pk_marclass in");
      querysql
          .append("(select bd_marbasclass.pk_marbasclass from bd_marbasclass "
              + "where bd_marbasclass.code like '");
      querysql.append(materialclasscode);
      querysql.append("%'");
    }
    else {
      querysql = new SqlBuilder();
      querysql.append("select bs_material.pk_measdoc from bs_material inner "
          + "join bd_materialsale on bs_material.materialsale = "
          + "bd_materialsale.pk_materialsale ");
      querysql.append("where bd_materialsale.pk_marsaleclass in("
          + "select bd_marsaleclass.pk_marsaleclass from bd_marsaleclass "
          + " where bd_marsaleclass.code like '");
      querysql.append(materialclasscode);
      querysql.append("%'");
    }

    DataAccessUtils util = new DataAccessUtils();
    IRowSet rs = util.query(querysql.toString());
    Set<String> hs_measdoc = new HashSet<String>();
    while (rs.next()) {
      hs_measdoc.add(rs.getString(0));
    }
    if (hs_measdoc.size() == 0 || hs_measdoc.size() > 1) {
      return null;
    }
    return hs_measdoc.toArray(new String[0])[0];
  }

  @Override
  public Object[] queryTariffDef(LoginContext context) throws BusinessException {
    Object[] objs = new Object[1];
    try {
      objs[0] =
          super.queryByDataVisibilitySetting(context, BuyLargessHVO.class);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return objs;
  }

  @Override
  public BuyLargessVO updateBuylargess(BuyLargessVO updateVO)
      throws BusinessException {
    BuyLargessVO[] result = null;
    BuyLargessVO[] bills = new BuyLargessVO[] {
      updateVO
    };
    try {
      UpdateBuyLargessAction action = new UpdateBuyLargessAction();
      result = action.updateBuylargess(bills);
      return result[0];
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
