package nc.pubimpl.so.m38.mmdp.aid;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.AssertUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.entity.PreOrderViewVO;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.enumeration.BillStatusEnum;
import nc.vo.trade.checkrule.VOChecker;

import nc.pubitf.so.m38.mmdp.aid.IPreOrderForAid;
import nc.pubitf.so.m38.mmdp.aid.ParaVO;
import nc.pubitf.so.m38.mmdp.aid.ResultVO;

import nc.impl.pubapp.pattern.data.view.EfficientViewQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 预订单提供给实际独立需求的接口实现
 * 
 * @since 6.0
 * @version 2011-12-5 下午04:21:51
 * @author 么贵敬
 */
public class PreOrderForAidImpl implements IPreOrderForAid {

  private static final String[] QUERYCOLUMNS = new String[] {
    PreOrderBVO.CPREORDERID, PreOrderHVO.VBILLCODE, PreOrderBVO.CPREORDERBID,
    PreOrderBVO.CROWNO, PreOrderBVO.CSENDSTOCKORGID,
    PreOrderBVO.CSENDSTOCKORGVID, PreOrderBVO.CMATERIALID,
    PreOrderHVO.CCUSTOMERID, PreOrderBVO.CPRODUCTORID, PreOrderBVO.CPROJECTID,
    PreOrderBVO.CVENDORID, PreOrderBVO.CMATERIALVID, PreOrderBVO.VFREE1,
    PreOrderBVO.VFREE2, PreOrderBVO.VFREE3, PreOrderBVO.VFREE4,
    PreOrderBVO.VFREE5, PreOrderBVO.VFREE6, PreOrderBVO.VFREE7,
    PreOrderBVO.VFREE8, PreOrderBVO.VFREE9, PreOrderBVO.VFREE10,
    PreOrderBVO.CUNITID, PreOrderBVO.NNUM, PreOrderBVO.DSENDDATE,
    PreOrderHVO.FSTATUSFLAG, PreOrderHVO.NDISCOUNTRATE, PreOrderBVO.NARRNUM,
    PreOrderBVO.CCUSTMATERIALID
  };

  @Override
  public List<ResultVO> queryPreOrderDetails(ParaVO para)
      throws BusinessException {
    AssertUtils.assertValue(null != para.getDbeginbilldate(),
        "null != para.getDbeginbilldate()");
    AssertUtils.assertValue(null != para.getDendbilldate(),
        "null != para.getDendbilldate()");
    EfficientViewQuery<PreOrderViewVO> viewQuery =
        new EfficientViewQuery<PreOrderViewVO>(PreOrderViewVO.class,
            PreOrderForAidImpl.QUERYCOLUMNS);
    String pk_group = AppContext.getInstance().getPkGroup();
    SqlBuilder sqlcondition = new SqlBuilder();
    sqlcondition.append(" and ");
    this.getCondition(sqlcondition, para);
    this.appendVfree(sqlcondition, para);
    sqlcondition.append(" so_preorder.dr=0");
    sqlcondition.append(" and ");
    sqlcondition.append(" so_preorder_b.dr=0");
    sqlcondition.append(" and ");
    sqlcondition.append(" so_preorder.pk_group", pk_group);
    sqlcondition.append(" and ");
    sqlcondition.append(" so_preorder_b.pk_group", pk_group);
    sqlcondition.append(" and ");
    sqlcondition.append(" nnum>0");
    List<ResultVO> rets = new ArrayList<ResultVO>();
    try {
      PreOrderViewVO[] viewvos = viewQuery.query(sqlcondition.toString());
      for (PreOrderViewVO viewvo : viewvos) {
        ResultVO ret = new ResultVO();
        for (String key : PreOrderForAidImpl.QUERYCOLUMNS) {
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
      sqlcondition.append(PreOrderBVO.VFREE1, values);
      sqlcondition.append(" and ");
    }

    if (!VOChecker.isEmpty(para.getVfree2s())) {
      String[] values = para.getVfree2s().toArray(new String[0]);
      sqlcondition.append(PreOrderBVO.VFREE2, values);
      sqlcondition.append(" and ");
    }

    if (!VOChecker.isEmpty(para.getVfree3s())) {
      String[] values = para.getVfree3s().toArray(new String[0]);
      sqlcondition.append(PreOrderBVO.VFREE3, values);
      sqlcondition.append(" and ");
    }

    if (!VOChecker.isEmpty(para.getVfree4s())) {
      String[] values = para.getVfree4s().toArray(new String[0]);
      sqlcondition.append(PreOrderBVO.VFREE4, values);
      sqlcondition.append(" and ");
    }

    if (!VOChecker.isEmpty(para.getVfree5s())) {
      String[] values = para.getVfree5s().toArray(new String[0]);
      sqlcondition.append(PreOrderBVO.VFREE5, values);
      sqlcondition.append(" and ");
    }

    if (!VOChecker.isEmpty(para.getVfree6s())) {
      String[] values = para.getVfree6s().toArray(new String[0]);
      sqlcondition.append(PreOrderBVO.VFREE6, values);
      sqlcondition.append(" and ");
    }

    if (!VOChecker.isEmpty(para.getVfree7s())) {
      String[] values = para.getVfree7s().toArray(new String[0]);
      sqlcondition.append(PreOrderBVO.VFREE7, values);
      sqlcondition.append(" and ");
    }
    if (!VOChecker.isEmpty(para.getVfree8s())) {
      String[] values = para.getVfree8s().toArray(new String[0]);
      sqlcondition.append(PreOrderBVO.VFREE8, values);
      sqlcondition.append(" and ");
    }
    if (!VOChecker.isEmpty(para.getVfree9s())) {
      String[] values = para.getVfree9s().toArray(new String[0]);
      sqlcondition.append(PreOrderBVO.VFREE9, values);
      sqlcondition.append(" and ");
    }
    if (!VOChecker.isEmpty(para.getVfree10s())) {
      String[] values = para.getVfree10s().toArray(new String[0]);
      sqlcondition.append(PreOrderBVO.VFREE10, values);
      sqlcondition.append(" and ");
    }
  }

  private void getCondition(SqlBuilder sqlcondition, ParaVO para) {
    String btablename = "so_preorder_b.";
    String htablename = "so_preorder.";
    sqlcondition.append(htablename + PreOrderHVO.DBILLDATE, ">=", para
        .getDbeginbilldate().toString());
    sqlcondition.append(" and ");
    sqlcondition.append(htablename + PreOrderHVO.DBILLDATE, "<=", para
        .getDendbilldate().toString());
    sqlcondition.append(" and ");
    // 销售预订单号
    if (!PubAppTool.isNull(para.getVbillcode())) {
      sqlcondition.append(htablename + PreOrderHVO.VBILLCODE,
          para.getVbillcode());
      sqlcondition.append(" and ");
    }
    // if (null != para.getDbeginbilldate()) {
    sqlcondition.append(btablename + PreOrderBVO.DBILLDATE, ">=", para
        .getDbeginbilldate().toString());
    sqlcondition.append(" and ");
    // }
    // if (null != para.getDendbilldate()) {
    sqlcondition.append(btablename + PreOrderBVO.DBILLDATE, "<=", para
        .getDendbilldate().toString());
    sqlcondition.append(" and ");
    // }

    if (null != para.getBlineclose()) {
      sqlcondition.append(btablename + PreOrderBVO.BLINECLOSE, para
          .getBlineclose().toString());
      sqlcondition.append(" and ");
    }

    if (null != para.getDbeginmodifieddate()) {
      sqlcondition.append(htablename + PreOrderHVO.MODIFIEDTIME, ">=", para
          .getDbeginmodifieddate().toString());
      sqlcondition.append(" and ");
    }
    if (null != para.getDendmodifieddate()) {
      sqlcondition.append(htablename + PreOrderHVO.MODIFIEDTIME, "<=", para
          .getDendmodifieddate().toString());
      sqlcondition.append(" and ");
    }

    // 发货库存组织
    if (!VOChecker.isEmpty(para.getCsendstockorgids())) {
      IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
      String[] values = para.getCsendstockorgids().toArray(new String[0]);
      sqlcondition.append(iq.buildSQL(btablename + PreOrderBVO.CSENDSTOCKORGID,
          values));
      sqlcondition.append(" and ");
    }
    // 物料
    if (!VOChecker.isEmpty(para.getCmaterialids())) {
      String[] values = para.getCmaterialids().toArray(new String[0]);
      IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID2.getName());
      sqlcondition.append(iq.buildSQL(btablename + PreOrderBVO.CMATERIALID,
          values));
      sqlcondition.append(" and ");
    }
    // 客户物料码
    if (!VOChecker.isEmpty(para.getCcustmaterialids())) {
      String[] values = para.getCcustmaterialids().toArray(new String[0]);
      IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID2.getName());
      sqlcondition.append(iq.buildSQL(btablename + PreOrderBVO.CCUSTMATERIALID,
          values));
      sqlcondition.append(" and ");
    }
    if (!VOChecker.isEmpty(para.getCvendorids())) {
      String[] values = para.getCvendorids().toArray(new String[0]);
      sqlcondition.append(btablename + PreOrderBVO.CVENDORID, values);
      sqlcondition.append(" and ");
    }

    if (!VOChecker.isEmpty(para.getCproductorids())) {
      String[] values = para.getCproductorids().toArray(new String[0]);
      sqlcondition.append(btablename + PreOrderBVO.CPRODUCTORID, values);
      sqlcondition.append(" and ");
    }

    if (!VOChecker.isEmpty(para.getCprojectids())) {
      String[] values = para.getCprojectids().toArray(new String[0]);
      sqlcondition.append(btablename + PreOrderBVO.CPROJECTID, values);
      sqlcondition.append(" and ");
    }

    if (!VOChecker.isEmpty(para.getCcustomerids())) {
      String[] values = para.getCcustomerids().toArray(new String[0]);
      sqlcondition.append(htablename + PreOrderHVO.CCUSTOMERID, values);
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
      sqlcondition.append(htablename + PreOrderHVO.FSTATUSFLAG, values);
      sqlcondition.append(" and ");
    }
  }

}
