package nc.pubimpl.so.m30.api;

import nc.bs.scmpub.query.SCMBillQuery;
import nc.bs.scmpub.query.SCMViewQuery;
import nc.pubimpl.so.m30.pub.SaleOrderForPubImpl;
import nc.pubitf.so.m30.api.ISaleOrderQueryAPI;
import nc.pubitf.so.m30.pub.ISaleOrderForPub;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * 销售订单查询服务实现
 * 
 * @version 6.5
 * @since
 * @author 刘景
 * @time 2015-10-13 下午6:42:13
 */
public class SaleOrderQueryAPIImpl implements ISaleOrderQueryAPI {

  @Override
  public SaleOrderViewVO[] queryViewVOByBIDs(String[] bids)
      throws BusinessException {
    if (ArrayUtils.isEmpty(bids)) {
      return new SaleOrderViewVO[0];
    }
    return this.queryViewVOByBIDs(bids, null);
  }

  @Override
  public SaleOrderViewVO[] queryViewVOByBIDs(String[] bids, String[] queryFields)
      throws BusinessException {
    if (ArrayUtils.isEmpty(bids)) {
      return new SaleOrderViewVO[0];
    }
    SCMViewQuery<SaleOrderViewVO> viewQuery =
        new SCMViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class);
    return viewQuery.queryViewVOByBIDs(bids, queryFields);
  }

  @Override
  public SaleOrderVO[] queryVOByIDs(String[] hids) throws BusinessException {
    SCMBillQuery<SaleOrderVO> query = new SCMBillQuery<>(SaleOrderVO.class);
    return query.queryVOByIDs(hids);
  }

  @Override
  public SaleOrderVO[] queryVOByIDs(String[] hids, String[] names)
      throws BusinessException {
    SCMBillQuery<SaleOrderVO> query = new SCMBillQuery<>(SaleOrderVO.class);
    return query.queryVOByIDs(hids, names);
  }

  @Override
  public SaleOrderViewVO[] queryViewVOByScheme(IQueryScheme queryscheme)
      throws BusinessException {
    SCMViewQuery<SaleOrderViewVO> query =
        new SCMViewQuery<>(SaleOrderViewVO.class);
    return query.queryViewVOByScheme(queryscheme);
  }

  @Override
  public SaleOrderViewVO[] queryViewVOByScheme(IQueryScheme queryscheme,
      String[] names) throws BusinessException {
    SCMViewQuery<SaleOrderViewVO> query =
        new SCMViewQuery<>(SaleOrderViewVO.class);
    return query.queryViewVOByScheme(queryscheme, names);
  }

  @Override
  public SaleOrderVO[] queryVOByScheme(IQueryScheme queryscheme)
      throws BusinessException {
    SCMBillQuery<SaleOrderVO> query = new SCMBillQuery<>(SaleOrderVO.class);
    return query.queryVOByScheme(queryscheme);
  }

  @Override
  public SaleOrderVO[] queryVOByScheme(IQueryScheme queryscheme, String[] names)
      throws BusinessException {
    SCMBillQuery<SaleOrderVO> query = new SCMBillQuery<>(SaleOrderVO.class);
    return query.queryVOByScheme(queryscheme, names);
  }

  @Override
  public SaleOrderViewVO[] queryViewVOBySourceBIDs(String[] srcbids)
      throws BusinessException {
    return this.queryViewVOBySourceBIDs(srcbids, null);
  }

  @Override
  public SaleOrderViewVO[] queryViewVOBySourceBIDs(String[] srcbids,
      String[] names) throws BusinessException {
    SCMViewQuery<SaleOrderViewVO> query =
        new SCMViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class);
    return query.queryViewVOBySourceBIDs(SaleOrderBVO.CSRCBID, srcbids, names);
  }

  @Override
  public String[] getNotCloseOrder(String[] hids) throws BusinessException {
    ISaleOrderForPub query = new SaleOrderForPubImpl();
    return query.getNotCloseOrder(hids);
  }
}
