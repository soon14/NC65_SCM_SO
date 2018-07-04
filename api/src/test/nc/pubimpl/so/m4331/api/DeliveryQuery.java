package nc.pubimpl.so.m4331.api;

import nc.bs.framework.common.NCLocator;
import nc.pubimpl.so.pub.api.SOQueryAPI;
import nc.pubitf.so.m4331.api.IDeliveryQueryAPI;
import nc.pubitf.so.m4331.api.IDeliveryVO;
import nc.pubitf.so.pub.api.ISOQueryAPI;
import nc.ui.querytemplate.operator.EqOperator;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.scmpub.util.QuerySchemeBuilder;
import nc.vo.so.m4331.entity.DeliveryHVO;

/**
 * @description
 * 发货单查询
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-11-10 下午11:37:51
 * @author 刘景
 */
public class DeliveryQuery extends SOQueryAPI {

  @Override
  public ISOQueryAPI getService() {
    return NCLocator.getInstance().lookup(IDeliveryQueryAPI.class);
  }

  @Override
  public String[] getQueryFields() {
    String[] queryFields =
        {
          IDeliveryVO.CDELIVERYBID_VSRCTYPE, IDeliveryVO.CDELIVERYBID_NASTNUM,
          IDeliveryVO.PK_ORG, IDeliveryVO.DBILLDATE
        };
    return queryFields;
  }

  @Override
  public String[] getBids() {
    String[] bids = {
      "1001E31000000001LQ7I", "1001E31000000001LQ7J"
    };
    return bids;
  }

  @Override
  public String[] getIds() {
    String[] ids = {
      "1001E31000000001LQ7H", "1001E31000000001M5I7"
    };
    return ids;
  }

  @Override
  public String[] getSourceBIDs() {

    String[] sourcebids = {
      "1001E31000000001LQ5Y", "1001E31000000001LQ5Z"
    };
    return sourcebids;
  }

  @Override
  public IQueryScheme getQueryscheme() {
    QuerySchemeBuilder builder =
        QuerySchemeBuilder.buildByFullClassName(DeliveryHVO.class.getName());
    builder.append(IDeliveryVO.CDELIVERYBID_CMATERIALVID,
        EqOperator.getInstance(), new String[] {
          "1001E31000000001J3RO"
        });
    builder.append(IDeliveryVO.DR, EqOperator.getInstance(), new Integer[] {
      0
    });
    builder.append(IDeliveryVO.VBILLCODE, EqOperator.getInstance(),
        new String[] {
          "DN2015110400000001", "DN2015110400000002"
        });
    return builder.create();
  }

}
