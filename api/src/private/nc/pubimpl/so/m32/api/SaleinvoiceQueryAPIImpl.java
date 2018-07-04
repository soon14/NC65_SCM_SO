package nc.pubimpl.so.m32.api;

import nc.bs.scmpub.query.SCMBillQuery;
import nc.bs.scmpub.query.SCMViewQuery;
import nc.pubitf.so.m32.api.ISaleinvoiceQueryAPI;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.entity.SaleInvoiceViewVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 * 销售发票查询服务实现
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-10-24 下午2:26:47
 * @author 刘景
 */
public class SaleinvoiceQueryAPIImpl implements ISaleinvoiceQueryAPI {

  @Override
  public SaleInvoiceViewVO[] queryViewVOByBIDs(String[] bids)
      throws BusinessException {
    if (ArrayUtils.isEmpty(bids)) {
      return new SaleInvoiceViewVO[0];
    }
    return this.queryViewVOByBIDs(bids, null);
  }

  @Override
  public SaleInvoiceViewVO[] queryViewVOByBIDs(String[] bids,
      String[] queryFields) throws BusinessException {
    if (ArrayUtils.isEmpty(bids)) {
      return new SaleInvoiceViewVO[0];
    }
    SCMViewQuery<SaleInvoiceViewVO> viewQuery =
        new SCMViewQuery<SaleInvoiceViewVO>(SaleInvoiceViewVO.class);
    return viewQuery.queryViewVOByBIDs(bids, queryFields);
  }

  @Override
  public SaleInvoiceVO[] queryVOByIDs(String[] hids) throws BusinessException {
    SCMBillQuery<SaleInvoiceVO> query = new SCMBillQuery<>(SaleInvoiceVO.class);
    return query.queryVOByIDs(hids);
  }

  @Override
  public SaleInvoiceVO[] queryVOByIDs(String[] hids, String[] names)
      throws BusinessException {
    SCMBillQuery<SaleInvoiceVO> query = new SCMBillQuery<>(SaleInvoiceVO.class);
    return query.queryVOByIDs(hids, names);
  }

  @Override
  public SaleInvoiceViewVO[] queryViewVOByScheme(IQueryScheme queryscheme)
      throws BusinessException {
    SCMViewQuery<SaleInvoiceViewVO> query =
        new SCMViewQuery<>(SaleInvoiceViewVO.class);
    return query.queryViewVOByScheme(queryscheme);
  }

  @Override
  public SaleInvoiceViewVO[] queryViewVOByScheme(IQueryScheme queryscheme,
      String[] names) throws BusinessException {
    SCMViewQuery<SaleInvoiceViewVO> query =
        new SCMViewQuery<>(SaleInvoiceViewVO.class);
    return query.queryViewVOByScheme(queryscheme, names);
  }

  @Override
  public SaleInvoiceVO[] queryVOByScheme(IQueryScheme queryscheme)
      throws BusinessException {
    SCMBillQuery<SaleInvoiceVO> query = new SCMBillQuery<>(SaleInvoiceVO.class);
    return query.queryVOByScheme(queryscheme);
  }

  @Override
  public SaleInvoiceVO[] queryVOByScheme(IQueryScheme queryscheme,
      String[] names) throws BusinessException {
    SCMBillQuery<SaleInvoiceVO> query = new SCMBillQuery<>(SaleInvoiceVO.class);
    return query.queryVOByScheme(queryscheme, names);
  }

  @Override
  public SaleInvoiceViewVO[] queryViewVOBySourceBIDs(String[] srcbids)
      throws BusinessException {
    return this.queryViewVOBySourceBIDs(srcbids, null);
  }

  @Override
  public SaleInvoiceViewVO[] queryViewVOBySourceBIDs(String[] srcbids,
      String[] names) throws BusinessException {
    SCMViewQuery<SaleInvoiceViewVO> query =
        new SCMViewQuery<SaleInvoiceViewVO>(SaleInvoiceViewVO.class);
    return query
        .queryViewVOBySourceBIDs(SaleInvoiceBVO.CSRCBID, srcbids, names);
  }

}
