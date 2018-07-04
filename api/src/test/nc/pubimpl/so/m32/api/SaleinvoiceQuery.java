package nc.pubimpl.so.m32.api;

import nc.bs.framework.common.NCLocator;
import nc.pubimpl.so.pub.api.SOQueryAPI;
import nc.pubitf.so.m32.api.ISaleInvoiceVO;
import nc.pubitf.so.m32.api.ISaleinvoiceQueryAPI;
import nc.pubitf.so.pub.api.ISOQueryAPI;
import nc.ui.querytemplate.operator.EqOperator;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.scmpub.util.QuerySchemeBuilder;
import nc.vo.so.m32.entity.SaleInvoiceHVO;

/**
 * @description
 * 销售发票查询
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-11-10 下午11:13:24
 * @author 刘景
 */
public class SaleinvoiceQuery extends SOQueryAPI {

  @Override
  public ISOQueryAPI getService() {
    // TODO Auto-generated method stub
    return /* new SaleinvoiceQueryAPIImpl() */NCLocator.getInstance().lookup(
        ISaleinvoiceQueryAPI.class);
  }

  @Override
  public String[] getQueryFields() {
    String[] queryFields =
        {
          ISaleInvoiceVO.CSALEINVOICEBID_CSRCBID,
          ISaleInvoiceVO.CSALEINVOICEBID_NNUM, ISaleInvoiceVO.PK_ORG,
          ISaleInvoiceVO.CTRANTYPEID
        };

    return queryFields;
  }

  @Override
  public String[] getBids() {
    String[] bids = {
      "1001231000000000MFWY", "1001231000000000PVSM"
    };
    return bids;
  }

  @Override
  public String[] getIds() {
    String[] ids = {
      "1001231000000000MFWT", "1001231000000000PVSK"
    };
    return ids;
  }

  @Override
  public String[] getSourceBIDs() {
    String[] sourcebids = {
      "1001231000000000MFJW", "1001231000000000PVS4"
    };
    return sourcebids;
  }

  @Override
  public IQueryScheme getQueryscheme() {
    QuerySchemeBuilder builder =
        QuerySchemeBuilder.buildByFullClassName(SaleInvoiceHVO.class.getName());
    builder.append(ISaleInvoiceVO.CSALEINVOICEBID_CMATERIALVID,
        EqOperator.getInstance(), new String[] {
          "10012310000000000XHM", "10012310000000000VRZ"
        });
    builder.append(ISaleInvoiceVO.PK_ORG, EqOperator.getInstance(),
        new String[] {
          "000123100000000026BQ"
        });
    builder.append(ISaleInvoiceVO.VBILLCODE, EqOperator.getInstance(),
        new String[] {
          "SI2015061100000012", "SI2015061200000031"
        });

    return builder.create();
  }

}
