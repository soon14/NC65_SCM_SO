package nc.pubimpl.so.mbuylargess.api;

import nc.bs.scmpub.query.SCMBillQuery;
import nc.bs.scmpub.query.SCMViewQuery;
import nc.pubitf.so.mbuylagress.api.IGwpruleQueryAPI;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;
import nc.vo.so.mbuylargess.view.BuyLargessShowViewVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 * 买赠设置查询服务实现
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-10-24 下午2:41:55
 * @author 刘景
 */
public class GwpruleQueryAPIImpl implements IGwpruleQueryAPI {

  @Override
  public BuyLargessShowViewVO[] queryViewVOByBIDs(String[] bids)
      throws BusinessException {
    if (ArrayUtils.isEmpty(bids)) {
      return new BuyLargessShowViewVO[0];
    }
    return this.queryViewVOByBIDs(bids, null);
  }

  @Override
  public BuyLargessShowViewVO[] queryViewVOByBIDs(String[] bids,
      String[] queryFields) throws BusinessException {
    if (ArrayUtils.isEmpty(bids)) {
      return new BuyLargessShowViewVO[0];
    }
    SCMViewQuery<BuyLargessShowViewVO> viewQuery =
        new SCMViewQuery<BuyLargessShowViewVO>(BuyLargessShowViewVO.class);
    return viewQuery.queryViewVOByBIDs(bids, queryFields);
  }

  @Override
  public BuyLargessVO[] queryVOByIDs(String[] hids) throws BusinessException {
    SCMBillQuery<BuyLargessVO> query = new SCMBillQuery<>(BuyLargessVO.class);
    return query.queryVOByIDs(hids);
  }

  @Override
  public BuyLargessVO[] queryVOByIDs(String[] hids, String[] names)
      throws BusinessException {
    SCMBillQuery<BuyLargessVO> query = new SCMBillQuery<>(BuyLargessVO.class);
    return query.queryVOByIDs(hids, names);
  }

  @Override
  public BuyLargessShowViewVO[] queryViewVOByScheme(IQueryScheme queryscheme)
      throws BusinessException {
    SCMViewQuery<BuyLargessShowViewVO> query =
        new SCMViewQuery<>(BuyLargessShowViewVO.class);
    return query.queryViewVOByScheme(queryscheme);
  }

  @Override
  public BuyLargessShowViewVO[] queryViewVOByScheme(IQueryScheme queryscheme,
      String[] names) throws BusinessException {
    SCMViewQuery<BuyLargessShowViewVO> query =
        new SCMViewQuery<>(BuyLargessShowViewVO.class);
    return query.queryViewVOByScheme(queryscheme, names);
  }

  @Override
  public BuyLargessVO[] queryVOByScheme(IQueryScheme queryscheme)
      throws BusinessException {
    SCMBillQuery<BuyLargessVO> query = new SCMBillQuery<>(BuyLargessVO.class);
    return query.queryVOByScheme(queryscheme);
  }

  @Override
  public BuyLargessVO[] queryVOByScheme(IQueryScheme queryscheme, String[] names)
      throws BusinessException {
    SCMBillQuery<BuyLargessVO> query = new SCMBillQuery<>(BuyLargessVO.class);
    return query.queryVOByScheme(queryscheme, names);
  }

  @Override
  public Object[] queryViewVOBySourceBIDs(String[] sourceBIDs)
      throws BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object[] queryViewVOBySourceBIDs(String[] sourceBIDs,
      String[] queryFields) throws BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

}
