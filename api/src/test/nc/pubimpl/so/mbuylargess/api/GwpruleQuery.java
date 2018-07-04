package nc.pubimpl.so.mbuylargess.api;

import nc.bs.framework.common.NCLocator;
import nc.pubimpl.so.pub.api.SOQueryAPI;
import nc.pubitf.so.mbuylagress.api.IBuyLargessVO;
import nc.pubitf.so.mbuylagress.api.IGwpruleQueryAPI;
import nc.pubitf.so.pub.api.ISOQueryAPI;
import nc.ui.querytemplate.operator.EqOperator;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.scmpub.util.QuerySchemeBuilder;
import nc.vo.so.mbuylargess.entity.BuyLargessHVO;

/**
 * @description
 * ¬Ú‘˘…Ë÷√≤È—Ø
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-11-10 œ¬ŒÁ11:46:39
 * @author ¡ıæ∞
 */
public class GwpruleQuery extends SOQueryAPI {

  @Override
  public ISOQueryAPI getService() {
    return /*new GwpruleQueryAPIImpl() */NCLocator.getInstance().lookup(IGwpruleQueryAPI.class);
  }

  @Override
  public String[] getQueryFields() {
    String[] queryFields =
      {
        IBuyLargessVO.CBUYMARID,
        IBuyLargessVO.NBUYNUM, IBuyLargessVO.PK_BUYLARGESS_B_PK_MATERIAL,
        IBuyLargessVO.PK_BUYLARGESS_B_NNUM
      };
    return queryFields;
  }

  @Override
  public String[] getBids() {
    String[] bids = {
      "1001E31000000001NBIB", "1001E31000000002GFQE"
    };
    return bids;
  }

  @Override
  public String[] getIds() {
    String[] ids = {
      "1001E31000000001NBIA", "1001E31000000002GFQD"
    };
    return ids;
  }

  @Override
  public String[] getSourceBIDs() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IQueryScheme getQueryscheme() {
    QuerySchemeBuilder builder =
        QuerySchemeBuilder
            .buildByFullClassName(BuyLargessHVO.class.getName());
    builder.append(IBuyLargessVO.PK_BUYLARGESS_B_PK_MATERIAL,
        EqOperator.getInstance(), new String[] {
          "1001E31000000001J8CD", "1001E31000000001YCR0"
        });
    builder.append(IBuyLargessVO.DR, EqOperator.getInstance(), new Integer[] {
      0
    });
    builder.append(IBuyLargessVO.CBUYMARID, EqOperator.getInstance(),
        new String[] {
          "1001E31000000001J8AV", "1001E31000000001LR99"
        });
    return builder.create();
  }

}
