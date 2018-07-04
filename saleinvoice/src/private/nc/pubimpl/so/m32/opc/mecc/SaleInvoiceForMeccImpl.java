package nc.pubimpl.so.m32.opc.mecc;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceViewVO;
import nc.vo.so.m32.opc.mecc.SaleInvoiceQueryConditionVO;
import nc.vo.so.pub.SOTable;

import nc.pubitf.so.m32.opc.mecc.ISaleInvoiceForMecc;

import nc.bs.ml.NCLangResOnserver;

import nc.impl.pubapp.pattern.data.view.EfficientViewQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 查询发票信息接口实现
 * 
 * @since 6.3
 * @version 2012-10-22 下午08:16:34
 * @author 梁吉明
 */
public class SaleInvoiceForMeccImpl implements ISaleInvoiceForMecc {

  /**
   * 根据销售订单查询发票
   * 
   * @param saleorderhids 销售订单表头ID数组
   * @param saleorderbids 销售订单表体ID数组
   * @param fieldnames 发票字段数组
   * @return 销售发票viewVO数组
   * @throws BusinessException
   */
  @Override
  public SaleInvoiceViewVO[] querySaleInvoiceByOrder(String[] saleorderhids,
      String[] saleorderbids, String[] fieldnames) throws BusinessException {
    if (null == saleorderhids || saleorderhids.length == 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006008_0", "04006008-0145")/*销售订单表头ID为空*/);
    }
    if (null == saleorderbids || saleorderbids.length == 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006008_0", "04006008-0146")/*销售订单表体ID为空*/);
    }
    if (null == fieldnames || fieldnames.length == 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006008_0", "04006008-0147")/*发票字段数组为空*/);
    }
    SaleInvoiceViewVO[] saleInvoiceViewVOs = null;
    try {
      // 查询条件
      SqlBuilder conditions = new SqlBuilder();
      String pk_group = AppContext.getInstance().getPkGroup();
      conditions.append(" and so_saleinvoice." + SaleInvoiceHVO.PK_GROUP,
          pk_group);
      conditions.append(" and so_saleinvoice_b." + SaleInvoiceBVO.PK_GROUP,
          pk_group);
      IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
      conditions.append(iq.buildSQL(" and so_saleinvoice_b."
          + SaleInvoiceBVO.CFIRSTID, saleorderhids));
      iq = new IDExQueryBuilder(SOTable.TMP_SO_ID2.getName());
      conditions.append(iq.buildSQL(" and so_saleinvoice_b."
          + SaleInvoiceBVO.CFIRSTBID, saleorderbids));
      EfficientViewQuery<SaleInvoiceViewVO> voQuery =
          new EfficientViewQuery<SaleInvoiceViewVO>(SaleInvoiceViewVO.class,
              fieldnames);
      voQuery.addQueryTable(SaleInvoiceHVO.CREATOR);
      voQuery.addQueryTable(SaleInvoiceBVO.CFIRSTID);
      saleInvoiceViewVOs = voQuery.query(conditions.toString());
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return saleInvoiceViewVOs;
  }

  @Override
  public SaleInvoiceViewVO[] querySaleInvoiceByCondition(
      SaleInvoiceQueryConditionVO condition, String[] fieldnames)
      throws BusinessException {

    // 获得查询条件
    String[] customer = condition.getCustomer();
    String begindate = condition.getBegindate();
    String enddate = condition.getEnddate();
    String[] billstatus = condition.getBillstatus();

    SaleInvoiceViewVO[] saleInvoiceViewVOs = null;
    try {
      // 获得查询SQL
      SqlBuilder conditions = new SqlBuilder();
      String pk_group = AppContext.getInstance().getPkGroup();
      conditions.append(" and so_saleinvoice." + SaleInvoiceHVO.PK_GROUP,
          pk_group);
      conditions.append(" and so_saleinvoice_b." + SaleInvoiceBVO.PK_GROUP,
          pk_group);
      IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
      conditions.append(iq.buildSQL(" and so_saleinvoice_b."
          + SaleInvoiceBVO.CORDERCUSTID, customer));
      conditions.append(" and (so_saleinvoice.dbilldate  >= '" + begindate
          + "' ");
      conditions.append(" and so_saleinvoice.dbilldate <= '" + enddate + "') ");
      conditions.append(" and (so_saleinvoice_b.dbilldate  >= '" + begindate
          + "' ");
      conditions.append(" and so_saleinvoice_b.dbilldate <= '" + enddate
          + "') ");
      if (null != billstatus && billstatus.length > 0) {
        iq = new IDExQueryBuilder(SOTable.TMP_SO_ID2.getName());
        conditions.append(iq.buildSQL(" and so_saleinvoice."
            + SaleInvoiceHVO.FSTATUSFLAG, billstatus));
      }
      EfficientViewQuery<SaleInvoiceViewVO> voQuery =
          new EfficientViewQuery<SaleInvoiceViewVO>(SaleInvoiceViewVO.class,
              fieldnames);
      voQuery.addQueryTable(SaleInvoiceBVO.CORDERCUSTID);
      voQuery.addQueryTable(SaleInvoiceHVO.FSTATUSFLAG);
      saleInvoiceViewVOs = voQuery.query(conditions.toString());
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return saleInvoiceViewVOs;
  }
}
