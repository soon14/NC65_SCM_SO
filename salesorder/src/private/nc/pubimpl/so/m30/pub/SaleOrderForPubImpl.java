package nc.pubimpl.so.m30.pub;

import java.util.HashSet;
import java.util.Set;

import nc.impl.pubapp.pattern.data.view.EfficientViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubitf.so.m30.pub.ISaleOrderForPub;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.enumeration.BillStatus;

/**
 * 销售订单提供给外部的公共接口实现
 * 
 * @since 6.3
 * @version 2014-07-07 10:09:10
 * @author 刘景
 */
public class SaleOrderForPubImpl implements ISaleOrderForPub {

  @Override
  public SaleOrderBVO[] querySaleOrderBVOs(String[] bids, String[] names)
      throws BusinessException {
    VOQuery<SaleOrderBVO> voquery =
        new VOQuery<SaleOrderBVO>(SaleOrderBVO.class, names);
    return voquery.query(bids);
  }

  @Override
  public SaleOrderHVO[] querySaleOrderHVOs(String[] ids, String[] names)
      throws BusinessException {
    VOQuery<SaleOrderHVO> voquery =
        new VOQuery<SaleOrderHVO>(SaleOrderHVO.class, names);
    return voquery.query(ids);
  }

  @Override
  public SaleOrderViewVO[] querySaleOrderViewVOs(String[] bids, String[] names)
      throws BusinessException {
    if (bids == null || bids.length == 0 || names == null || names.length == 0) {
      return null;
    }

    EfficientViewQuery<SaleOrderViewVO> viewQuery =
        new EfficientViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class, names);

    return viewQuery.query(bids);
  }

  @Override
  public SaleOrderViewVO[] querySaleOrderViewVOs(String[] bids)
      throws BusinessException {
    if (bids == null || bids.length == 0) {
      return null;
    }

    EfficientViewQuery<SaleOrderViewVO> viewQuery =
        new EfficientViewQuery<SaleOrderViewVO>(SaleOrderViewVO.class);

    return viewQuery.query(bids);
  }

  @Override
  public String[] getNotCloseOrder(String[] saleorderids)
      throws BusinessException {
    Set<String> billcodeset = new HashSet<String>();
    try {
      SaleOrderHVO[] salehvos =
          this.querySaleOrderHVOs(saleorderids, new String[] {
              SaleOrderHVO.CSALEORDERID, SaleOrderHVO.FSTATUSFLAG
          });
      if (salehvos == null || salehvos.length == 0) {
        return new String[0];
      }
      for (SaleOrderHVO salehvo : salehvos) {
        if (!BillStatus.CLOSED.equalsValue(salehvo.getFstatusflag())) {
          billcodeset.add(salehvo.getVbillcode());
        }
      }
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return billcodeset.toArray(new String[0]);
  }
}
