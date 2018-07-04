package nc.pubimpl.so.m4310.api;

import nc.bs.scmpub.query.SCMBillQuery;
import nc.bs.scmpub.query.SCMViewQuery;
import nc.pubitf.so.m4310.api.ISaleQuotationQueryAPI;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.SalequoViewVO;
import nc.vo.so.salequotation.entity.SalequotationBVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 * 报价单查询服务实现
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-10-24 下午2:06:31
 * @author 刘景
 */
public class SaleQuotationQueryAPIImpl implements ISaleQuotationQueryAPI {

  @Override
  public SalequoViewVO[] queryViewVOByBIDs(String[] bids)
      throws BusinessException {
    if (ArrayUtils.isEmpty(bids)) {
      return new SalequoViewVO[0];
    }
    return this.queryViewVOByBIDs(bids, null);
  }

  @Override
  public SalequoViewVO[] queryViewVOByBIDs(String[] bids, String[] queryFields)
      throws BusinessException {
    if (ArrayUtils.isEmpty(bids)) {
      return new SalequoViewVO[0];
    }
    SCMViewQuery<SalequoViewVO> viewQuery =
        new SCMViewQuery<SalequoViewVO>(SalequoViewVO.class);
    return viewQuery.queryViewVOByBIDs(bids, queryFields);
  }

  @Override
  public AggSalequotationHVO[] queryVOByIDs(String[] hids)
      throws BusinessException {
    SCMBillQuery<AggSalequotationHVO> query =
        new SCMBillQuery<>(AggSalequotationHVO.class);
    return query.queryVOByIDs(hids);
  }

  @Override
  public AggSalequotationHVO[] queryVOByIDs(String[] hids, String[] names)
      throws BusinessException {
    SCMBillQuery<AggSalequotationHVO> query =
        new SCMBillQuery<>(AggSalequotationHVO.class);
    return query.queryVOByIDs(hids, names);
  }

  @Override
  public SalequoViewVO[] queryViewVOByScheme(IQueryScheme queryscheme)
      throws BusinessException {
    SCMViewQuery<SalequoViewVO> query = new SCMViewQuery<>(SalequoViewVO.class);
    return query.queryViewVOByScheme(queryscheme);
  }

  @Override
  public SalequoViewVO[] queryViewVOByScheme(IQueryScheme queryscheme,
      String[] names) throws BusinessException {
    SCMViewQuery<SalequoViewVO> query = new SCMViewQuery<>(SalequoViewVO.class);
    return query.queryViewVOByScheme(queryscheme, names);
  }

  @Override
  public AggSalequotationHVO[] queryVOByScheme(IQueryScheme queryscheme)
      throws BusinessException {
    SCMBillQuery<AggSalequotationHVO> query =
        new SCMBillQuery<>(AggSalequotationHVO.class);
    return query.queryVOByScheme(queryscheme);
  }

  @Override
  public AggSalequotationHVO[] queryVOByScheme(IQueryScheme queryscheme,
      String[] names) throws BusinessException {
    SCMBillQuery<AggSalequotationHVO> query =
        new SCMBillQuery<>(AggSalequotationHVO.class);
    return query.queryVOByScheme(queryscheme, names);
  }

  @Override
  public SalequoViewVO[] queryViewVOBySourceBIDs(String[] srcbids)
      throws BusinessException {
    return this.queryViewVOBySourceBIDs(srcbids, null);
  }

  @Override
  public SalequoViewVO[] queryViewVOBySourceBIDs(String[] srcbids,
      String[] names) throws BusinessException {
    SCMViewQuery<SalequoViewVO> query =
        new SCMViewQuery<SalequoViewVO>(SalequoViewVO.class);
    return query.queryViewVOBySourceBIDs(SalequotationBVO.CSRCBID, srcbids,
        names);
  }

}
