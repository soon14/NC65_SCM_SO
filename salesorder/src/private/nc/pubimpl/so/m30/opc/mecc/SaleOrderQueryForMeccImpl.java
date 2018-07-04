package nc.pubimpl.so.m30.opc.mecc;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.SOTable;

import nc.pubitf.so.m30.opc.mecc.ISaleOrderQueryForMecc;

import nc.bs.ml.NCLangResOnserver;

import nc.impl.pubapp.pattern.data.view.EfficientViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 根据电子销售预订单头ID、行ID获取下游销售订单头ID、行ID、销售订单行出库关闭状态
 * 
 * @since 6.0
 * @version 2012-02-14 上午15:44:05
 * @author 刘景
 */
public class SaleOrderQueryForMeccImpl implements ISaleOrderQueryForMecc {

  @Override
  public SaleOrderBVO[] query(String[] csrcid, String[] csrcbid,
      String[] fieldnames) throws BusinessException {
    SaleOrderBVO[] saleOrderBVO = null;
    try {
      SqlBuilder swhere = new SqlBuilder();
      String pk_group = AppContext.getInstance().getPkGroup();
      swhere.append(" and ");
      swhere.append(SaleOrderBVO.PK_GROUP, pk_group);
      swhere.append(" and ");
      IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
      swhere.append(iq.buildSQL(SaleOrderBVO.CSRCID, csrcid));
      swhere.append(" and ");

      iq = new IDExQueryBuilder(SOTable.TMP_SO_ID2.getName());
      swhere.append(iq.buildSQL(SaleOrderBVO.CSRCBID, csrcbid));
      VOQuery<SaleOrderBVO> query =
          new VOQuery<SaleOrderBVO>(SaleOrderBVO.class, fieldnames);
      saleOrderBVO = query.query(swhere.toString(), null);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return saleOrderBVO;
  }

  /**
   * 根据输入的销售订单编号查询销售订单信息
   * 
   * @param saleorerbids 销售订单表体ID数组
   * @param fieldnames 订单查询的字段数组
   * @return 销售订单viewVO数组
   * @throws BusinessException
   * @author 梁吉明
   */
  @Override
  public SaleOrderViewVO[] querySaleOrderViewVO(String[] saleorerbids,
      String[] fieldnames) throws BusinessException {
    if (null == saleorerbids || saleorerbids.length == 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006011_0", "04006011-0461")/*销售订单表体ID为空*/);
    }
    if (null == fieldnames || fieldnames.length == 0) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006011_0", "04006011-0462")/*销售订单字段数组为空*/);
    }
    SaleOrderViewVO[] saleOrderViewVOs = null;
    try {
      // 获得viewVO
      EfficientViewQuery<SaleOrderViewVO> viewQuery =
          new EfficientViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class,
              fieldnames);
      saleOrderViewVOs = viewQuery.query(saleorerbids);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return saleOrderViewVOs;
  }
}
