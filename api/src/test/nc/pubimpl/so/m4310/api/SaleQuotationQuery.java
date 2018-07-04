package nc.pubimpl.so.m4310.api;

import nc.bs.framework.common.NCLocator;
import nc.pubimpl.so.pub.api.SOQueryAPI;
import nc.pubitf.so.m4310.api.IAggSalequotationHVO;
import nc.pubitf.so.m4310.api.ISaleQuotationQueryAPI;
import nc.pubitf.so.pub.api.ISOQueryAPI;
import nc.ui.querytemplate.operator.EqOperator;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.scmpub.util.QuerySchemeBuilder;
import nc.vo.so.salequotation.entity.SalequotationHVO;

/**
 * @description
 * 报价单查询
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-11-10 下午11:31:18
 * @author 刘景
 */
public class SaleQuotationQuery extends SOQueryAPI {

  @Override
  public ISOQueryAPI getService() {
    return /* new SaleQuotationQueryAPIImpl() */NCLocator.getInstance().lookup(
        ISaleQuotationQueryAPI.class);
  }

  @Override
  public String[] getQueryFields() {
    String[] queryFields =
        {
          IAggSalequotationHVO.PK_GROUP, IAggSalequotationHVO.PK_CUSTOMER,
          IAggSalequotationHVO.PK_ORG,
          IAggSalequotationHVO.SALEQUOTATIONDETAIL_PK_MATERIAL_V
        };
    return queryFields;
  }

  @Override
  public String[] getBids() {
    String[] bids = {
      "1001E31000000001MIX3", "1001E31000000001MIXC"
    };
    return bids;
  }

  @Override
  public String[] getIds() {
    String[] ids = {
      "1001E31000000001MIX2", "1001E31000000001MIXB"
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
        QuerySchemeBuilder.buildByFullClassName(SalequotationHVO.class
            .getName());
    builder.append(IAggSalequotationHVO.SALEQUOTATIONDETAIL_PK_MATERIAL_V,
        EqOperator.getInstance(), new String[] {
          "1001E31000000001J8AV", "1001E31000000001J8AV"
        });
    builder.append(IAggSalequotationHVO.DR, EqOperator.getInstance(),
        new Integer[] {
          0
        });
    builder.append(IAggSalequotationHVO.VBILLCODE, EqOperator.getInstance(),
        new String[] {
          "PRICE43102015110400000001", "PRICE43102015110400000002"
        });
    return builder.create();
  }

}
