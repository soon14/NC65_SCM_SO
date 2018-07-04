package nc.pubimpl.so.m30.mmdp.aid;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.AssertUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.util.CombineViewToAggUtil;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.enumeration.BillStatusEnum;
import nc.vo.trade.checkrule.VOChecker;

import nc.pubitf.so.m30.mmdp.aid.ISaleOrderForAid;
import nc.pubitf.so.m30.mmdp.aid.ParaVO;
import nc.pubitf.so.m30.mmdp.aid.ResultVO;

import nc.impl.pubapp.pattern.data.view.EfficientViewQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 销售订单提供给实际独立需求的接口实现
 * 
 * @since 6.0
 * @version 2011-12-5 下午04:21:51
 * @author 么贵敬
 */
public class SaleOrderForAidImpl implements ISaleOrderForAid {

  private static final String[] QUERYCOLUMNS = new String[] {
    SaleOrderBVO.CSALEORDERID, SaleOrderHVO.VBILLCODE,
    SaleOrderBVO.CSALEORDERBID, SaleOrderBVO.CROWNO,
    SaleOrderBVO.CSENDSTOCKORGID, SaleOrderBVO.CSENDSTOCKORGVID,
    SaleOrderBVO.CMATERIALID, SaleOrderBVO.CMATERIALVID,
    SaleOrderHVO.CCUSTOMERID, SaleOrderBVO.CPRODUCTORID,
    SaleOrderBVO.CPROJECTID, SaleOrderBVO.CVENDORID, SaleOrderBVO.VFREE1,
    SaleOrderBVO.VFREE2, SaleOrderBVO.VFREE3, SaleOrderBVO.VFREE4,
    SaleOrderBVO.VFREE5, SaleOrderBVO.VFREE6, SaleOrderBVO.VFREE7,
    SaleOrderBVO.VFREE8, SaleOrderBVO.VFREE9, SaleOrderBVO.VFREE10,
    SaleOrderBVO.CUNITID, SaleOrderBVO.NNUM, SaleOrderBVO.NARRANGESCORNUM,
    SaleOrderBVO.NARRANGEPOAPPNUM, SaleOrderBVO.NARRANGETOORNUM,
    SaleOrderBVO.NARRANGETOAPPNUM, SaleOrderBVO.NARRANGEPONUM,
    SaleOrderBVO.DSENDDATE, SaleOrderBVO.NREQRSNUM, SaleOrderBVO.NTOTALOUTNUM,
    SaleOrderBVO.NARRANGEMONUM, SaleOrderBVO.NTOTALPLONUM,
    SaleOrderHVO.FSTATUSFLAG, SaleOrderBVO.CCUSTMATERIALID,
    SaleOrderBVO.CMFFILEID,SaleOrderHVO.CTRANTYPEID
  };

  @Override
  public SaleOrderVO[] queryOrderByBid(String[] csaleorderbids)
      throws BusinessException {

    EfficientViewQuery<SaleOrderViewVO> viewquery =
        new EfficientViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class);
    SaleOrderVO[] queryVOs = null;
    try {
      SaleOrderViewVO[] views = viewquery.query(csaleorderbids);
      CombineViewToAggUtil<SaleOrderVO> utils =
          new CombineViewToAggUtil<SaleOrderVO>(SaleOrderVO.class,
              SaleOrderHVO.class, SaleOrderBVO.class);

      queryVOs = utils.combineViewToAgg(views, SaleOrderHVO.CSALEORDERID);
      for (SaleOrderVO vo : queryVOs) {
        SaleOrderHVO headvo = vo.getParentVO();
        SaleOrderBVO[] bodyvos = vo.getChildrenVO();
        if (VOChecker.isEmpty(bodyvos)) {
          continue;
        }
        headvo.setPk_group(bodyvos[0].getPk_group());
        headvo.setPk_org(bodyvos[0].getPk_org());
        headvo.setDbilldate(bodyvos[0].getDbilldate());
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return queryVOs;
  }

  @Override
  public List<ResultVO> queryOrderDetails(ParaVO para) throws BusinessException {
    AssertUtils.assertValue(null != para.getDbeginbilldate(),
        "null != para.getDbeginbilldate()");
    AssertUtils.assertValue(null != para.getDendbilldate(),
        "null != para.getDendbilldate()");
    EfficientViewQuery<SaleOrderViewVO> viewQuery =
        new EfficientViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class,
            SaleOrderForAidImpl.QUERYCOLUMNS);
    String pk_group = AppContext.getInstance().getPkGroup();
    SqlBuilder sqlcondition = new SqlBuilder();
    sqlcondition.append(" and ");
    this.getCondition(sqlcondition, para);
    this.appendVfree(sqlcondition, para);
    sqlcondition.append(" so_saleorder.dr=0");
    sqlcondition.append(" and ");
    sqlcondition.append(" so_saleorder_b.dr=0");
    sqlcondition.append(" and ");
    sqlcondition.append(" so_saleorder.pk_group", pk_group);
    sqlcondition.append(" and ");
    sqlcondition.append(" so_saleorder_b.pk_group", pk_group);
    sqlcondition.append(" and ");
    sqlcondition.append(" nnum>0");
    List<ResultVO> rets = new ArrayList<ResultVO>();
    try {
      SaleOrderViewVO[] viewvos = viewQuery.query(sqlcondition.toString());
      for (SaleOrderViewVO viewvo : viewvos) {
        ResultVO ret = new ResultVO();
        for (String key : SaleOrderForAidImpl.QUERYCOLUMNS) {
          ret.setAttributeValue(key, viewvo.getAttributeValue(key));
        }
        rets.add(ret);
      }
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return rets;
  }

  private void appendVfree(SqlBuilder sqlcondition, ParaVO para) {
    if (!VOChecker.isEmpty(para.getVfree1s())) {
      String[] values = para.getVfree1s().toArray(new String[0]);
      sqlcondition.append(SaleOrderBVO.VFREE1, values);
      sqlcondition.append(" and ");
    }

    if (!VOChecker.isEmpty(para.getVfree2s())) {
      String[] values = para.getVfree2s().toArray(new String[0]);
      sqlcondition.append(SaleOrderBVO.VFREE2, values);
      sqlcondition.append(" and ");
    }

    if (!VOChecker.isEmpty(para.getVfree3s())) {
      String[] values = para.getVfree3s().toArray(new String[0]);
      sqlcondition.append(SaleOrderBVO.VFREE3, values);
      sqlcondition.append(" and ");
    }

    if (!VOChecker.isEmpty(para.getVfree4s())) {
      String[] values = para.getVfree4s().toArray(new String[0]);
      sqlcondition.append(SaleOrderBVO.VFREE4, values);
      sqlcondition.append(" and ");
    }

    if (!VOChecker.isEmpty(para.getVfree5s())) {
      String[] values = para.getVfree5s().toArray(new String[0]);
      sqlcondition.append(SaleOrderBVO.VFREE5, values);
      sqlcondition.append(" and ");
    }

    if (!VOChecker.isEmpty(para.getVfree6s())) {
      String[] values = para.getVfree6s().toArray(new String[0]);
      sqlcondition.append(SaleOrderBVO.VFREE6, values);
      sqlcondition.append(" and ");
    }

    if (!VOChecker.isEmpty(para.getVfree7s())) {
      String[] values = para.getVfree7s().toArray(new String[0]);
      sqlcondition.append(SaleOrderBVO.VFREE7, values);
      sqlcondition.append(" and ");
    }
    if (!VOChecker.isEmpty(para.getVfree8s())) {
      String[] values = para.getVfree8s().toArray(new String[0]);
      sqlcondition.append(SaleOrderBVO.VFREE8, values);
      sqlcondition.append(" and ");
    }
    if (!VOChecker.isEmpty(para.getVfree9s())) {
      String[] values = para.getVfree9s().toArray(new String[0]);
      sqlcondition.append(SaleOrderBVO.VFREE9, values);
      sqlcondition.append(" and ");
    }
    if (!VOChecker.isEmpty(para.getVfree10s())) {
      String[] values = para.getVfree10s().toArray(new String[0]);
      sqlcondition.append(SaleOrderBVO.VFREE10, values);
      sqlcondition.append(" and ");
    }
  }

  private void getCondition(SqlBuilder sqlcondition, ParaVO para) {
    // if (null != para.getDbeginbilldate()) {
    sqlcondition.append(SaleOrderBVO.METAPATH + SaleOrderBVO.DBILLDATE, ">=",
        para.getDbeginbilldate().toString());
    sqlcondition.append(" and ");
    // }
    // if (null != para.getDendbilldate()) {
    sqlcondition.append(SaleOrderBVO.METAPATH + SaleOrderBVO.DBILLDATE, "<=",
        para.getDendbilldate().toString());
    sqlcondition.append(" and ");
    // }

    sqlcondition.append("so_saleorder." + SaleOrderHVO.DBILLDATE, ">=", para
        .getDbeginbilldate().toString());
    sqlcondition.append(" and ");
    // }
    // if (null != para.getDendbilldate()) {
    sqlcondition.append("so_saleorder." + SaleOrderHVO.DBILLDATE, "<=", para
        .getDendbilldate().toString());
    sqlcondition.append(" and ");
    // 销售订单号
    if (null != para.getVbillcode()) {
      sqlcondition.append("so_saleorder." + SaleOrderHVO.VBILLCODE,
          para.getVbillcode());
      sqlcondition.append(" and ");
    }
    
    // 订单交易类型
    if (!VOChecker.isEmpty(para.getCbilltranstypeids())) {
        String[] values = para.getCbilltranstypeids().toArray(new String[0]);
        IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID2.getName());
        sqlcondition.append(iq.buildSQL("so_saleorder." + SaleOrderHVO.CTRANTYPEID, values));
        sqlcondition.append(" and ");
    }
    if (null != para.getDbeginmodifieddate()) {
      sqlcondition.append("so_saleorder." + SaleOrderHVO.MODIFIEDTIME, ">=",
          para.getDbeginmodifieddate().toString());
      sqlcondition.append(" and ");
    }

    // 出库关闭
    if (null != para.getBboutendflag()) {
      sqlcondition.append(SaleOrderBVO.METAPATH + SaleOrderBVO.BBOUTENDFLAG,
          para.getBboutendflag().toString());
      sqlcondition.append(" and ");
    }

    if (null != para.getDendmodifieddate()) {
      sqlcondition.append("so_saleorder." + SaleOrderHVO.MODIFIEDTIME, "<=",
          para.getDendmodifieddate().toString());
      sqlcondition.append(" and ");
    }

    // 发货库存组织
    if (!VOChecker.isEmpty(para.getCsendstockorgids())) {
      IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
      String[] values = para.getCsendstockorgids().toArray(new String[0]);
      sqlcondition.append(iq.buildSQL(SaleOrderBVO.METAPATH
          + SaleOrderBVO.CSENDSTOCKORGID, values));
      sqlcondition.append(" and ");
    }
    // 客户物料码
    if (!VOChecker.isEmpty(para.getCcustmaterialids())) {
      String[] values = para.getCcustmaterialids().toArray(new String[0]);
      IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID2.getName());
      sqlcondition.append(iq.buildSQL(SaleOrderBVO.METAPATH
          + SaleOrderBVO.CCUSTMATERIALID, values));
      sqlcondition.append(" and ");
    }

    // 物料
    if (!VOChecker.isEmpty(para.getCmaterialids())) {
      String[] values = para.getCmaterialids().toArray(new String[0]);
      IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID2.getName());
      sqlcondition.append(iq.buildSQL(SaleOrderBVO.METAPATH
          + SaleOrderBVO.CMATERIALID, values));
      sqlcondition.append(" and ");
    }

    if (!VOChecker.isEmpty(para.getCvendorids())) {
      String[] values = para.getCvendorids().toArray(new String[0]);
      sqlcondition.append(SaleOrderBVO.METAPATH + SaleOrderBVO.CVENDORID,
          values);
      sqlcondition.append(" and ");
    }

    if (!VOChecker.isEmpty(para.getCproductorids())) {
      String[] values = para.getCproductorids().toArray(new String[0]);
      sqlcondition.append(SaleOrderBVO.METAPATH + SaleOrderBVO.CPRODUCTORID,
          values);
      sqlcondition.append(" and ");
    }

    if (!VOChecker.isEmpty(para.getCprojectids())) {
      String[] values = para.getCprojectids().toArray(new String[0]);
      sqlcondition.append(SaleOrderBVO.METAPATH + SaleOrderBVO.CPROJECTID,
          values);
      sqlcondition.append(" and ");
    }

    if (!VOChecker.isEmpty(para.getCcustomerids())) {
      String[] values = para.getCcustomerids().toArray(new String[0]);
      sqlcondition.append("so_saleorder." + SaleOrderHVO.CCUSTOMERID, values);
      sqlcondition.append(" and ");
    }

    if (!VOChecker.isEmpty(para.getCmffileid())) {
      String[] values = para.getCmffileid().toArray(new String[0]);
      sqlcondition.append(SaleOrderBVO.METAPATH + SaleOrderBVO.CMFFILEID,
          values);
      sqlcondition.append(" and ");
    }
    
    if (!VOChecker.isEmpty(para.getFstatusflags())) {
      Set<BillStatusEnum> valueenums = para.getFstatusflags();
      Integer[] values = new Integer[valueenums.size()];
      int i = 0;
      for (BillStatusEnum valueenum : valueenums) {
        values[i] = valueenum.getValue();
        i++;
      }
      sqlcondition.append("so_saleorder." + SaleOrderHVO.FSTATUSFLAG, values);
      sqlcondition.append(" and ");
    }
  }

}
