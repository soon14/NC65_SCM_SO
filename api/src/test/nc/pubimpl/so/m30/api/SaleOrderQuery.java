package nc.pubimpl.so.m30.api;

import nc.bs.framework.common.NCLocator;
import nc.pubimpl.so.pub.api.SOQueryAPI;
import nc.pubitf.so.m30.api.ISaleOrderQueryAPI;
import nc.pubitf.so.m30.api.ISaleOrderVO;
import nc.pubitf.so.pub.api.ISOQueryAPI;
import nc.ui.querytemplate.operator.EqOperator;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.scmpub.util.QuerySchemeBuilder;
import nc.vo.so.m30.entity.SaleOrderHVO;

/**
 * @description
 * 销售订单查询
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-11-10 下午10:58:01
 * @author 刘景
 */
public class SaleOrderQuery extends SOQueryAPI {

  @Override
  public ISOQueryAPI getService() {
    return /*new SaleOrderQueryAPIImpl()*/ NCLocator.getInstance().lookup(ISaleOrderQueryAPI.class);
  }

  @Override
  public String[] getQueryFields() {
    String[] queryFields =
        {
          ISaleOrderVO.SO_SALEORDER_B_CSRCBID,
          ISaleOrderVO.SO_SALEORDER_B_NNUM, ISaleOrderVO.SO_SALEORDER_B_PK_ORG,
          ISaleOrderVO.CCUSTOMERID
        };
    return queryFields;
  }

  @Override
  public String[] getBids() {
    String[] bids = {
      "1001231000000000KUCA", "1001231000000000KUCR"
    };
    return bids;
  }

  @Override
  public String[] getIds() {
    String[] ids = {
      "1001231000000000KUCP", "1001231000000000KUD6"
    };
    return ids;
  }

  @Override
  public String[] getSourceBIDs() {
    String[] sourcebids = {
      "1001E31000000001KX2F", "1001E31000000001KX2F"
    };
    return sourcebids;
  }

  @Override
  public IQueryScheme getQueryscheme() {
    QuerySchemeBuilder builder =
        QuerySchemeBuilder.buildByFullClassName(SaleOrderHVO.class.getName());
    builder.append(ISaleOrderVO.SO_SALEORDER_B_CMATERIALID,
        EqOperator.getInstance(), new String[] {
          "10012310000000000VRK", "10012310000000000X62"
        });
    builder.append(ISaleOrderVO.SO_SALEORDER_B_DR, EqOperator.getInstance(),
        new Integer[] {
          0
        });
    builder.append(ISaleOrderVO.VBILLCODE, EqOperator.getInstance(),
        new String[] {
          "SO302015060900000005", "SO302015060900000007"
        });
    return builder.create();
  }

}
