package nc.pubimpl.so.m4331.api;

import nc.bs.scmpub.query.SCMBillQuery;
import nc.bs.scmpub.query.SCMViewQuery;
import nc.pubitf.so.m4331.api.IDeliveryQueryAPI;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 * 发货单查询服务实现
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-10-21 下午12:41:18
 * @author 刘景
 */
public class DeliveryQueryAPIImpl implements IDeliveryQueryAPI {

  @Override
  public DeliveryViewVO[] queryViewVOByBIDs(String[] bids)
      throws BusinessException {
    if (ArrayUtils.isEmpty(bids)) {
      return new DeliveryViewVO[0];
    }
    return this.queryViewVOByBIDs(bids, null);
  }

  @Override
  public DeliveryViewVO[] queryViewVOByBIDs(String[] bids, String[] queryFields)
      throws BusinessException {
    if (ArrayUtils.isEmpty(bids)) {
      return new DeliveryViewVO[0];
    }
    SCMViewQuery<DeliveryViewVO> viewQuery =
        new SCMViewQuery<DeliveryViewVO>(DeliveryViewVO.class);
    return viewQuery.queryViewVOByBIDs(bids, queryFields);
  }

  @Override
  public DeliveryVO[] queryVOByIDs(String[] hids) throws BusinessException {
    SCMBillQuery<DeliveryVO> query = new SCMBillQuery<>(DeliveryVO.class);
    return query.queryVOByIDs(hids);
  }

  @Override
  public DeliveryVO[] queryVOByIDs(String[] hids, String[] names)
      throws BusinessException {
    SCMBillQuery<DeliveryVO> query = new SCMBillQuery<>(DeliveryVO.class);
    return query.queryVOByIDs(hids, names);
  }

  @Override
  public DeliveryViewVO[] queryViewVOByScheme(IQueryScheme queryscheme)
      throws BusinessException {
    SCMViewQuery<DeliveryViewVO> query =
        new SCMViewQuery<>(DeliveryViewVO.class);
    return query.queryViewVOByScheme(queryscheme);
  }

  @Override
  public DeliveryViewVO[] queryViewVOByScheme(IQueryScheme queryscheme,
      String[] names) throws BusinessException {
    SCMViewQuery<DeliveryViewVO> query =
        new SCMViewQuery<>(DeliveryViewVO.class);
    return query.queryViewVOByScheme(queryscheme, names);
  }

  @Override
  public DeliveryVO[] queryVOByScheme(IQueryScheme queryscheme)
      throws BusinessException {
    SCMBillQuery<DeliveryVO> query = new SCMBillQuery<>(DeliveryVO.class);
    return query.queryVOByScheme(queryscheme);
  }

  @Override
  public DeliveryVO[] queryVOByScheme(IQueryScheme queryscheme, String[] names)
      throws BusinessException {
    SCMBillQuery<DeliveryVO> query = new SCMBillQuery<>(DeliveryVO.class);
    return query.queryVOByScheme(queryscheme, names);
  }

  @Override
  public DeliveryViewVO[] queryViewVOBySourceBIDs(String[] srcbids)
      throws BusinessException {
    return this.queryViewVOBySourceBIDs(srcbids, null);
  }

  @Override
  public DeliveryViewVO[] queryViewVOBySourceBIDs(String[] srcbids,
      String[] names) throws BusinessException {
    SCMViewQuery<DeliveryViewVO> query =
        new SCMViewQuery<DeliveryViewVO>(DeliveryViewVO.class);
    return query.queryViewVOBySourceBIDs(DeliveryBVO.CSRCBID, srcbids, names);
  }

}
