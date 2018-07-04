package nc.pubitf.so.m30.api.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONString;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.so.m30.api.ISaleOrderMaintainAPI;
import nc.pubitf.so.m30.api.ISaleOrderQueryAPI;
import nc.pubitf.so.m30.api.ISaleOrderVO;
import nc.pubitf.so.pub.api.rest.AbstractSORestResource;
import nc.ui.querytemplate.operator.EqOperator;
import nc.vo.org.OrgVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.api.rest.utils.RestUtils;
import nc.vo.scmpub.json.GsonUtils;
import nc.vo.scmpub.util.QuerySchemeBuilder;
import nc.vo.scmpub.util.translate.TranslateUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @description
 *              查询销售订单VO资源
 *              <li>
 *              http://127.0.0.1:80/uapws/rest/saleorder/bybillcode?
 *              vbillcode=SO302015111900000152
 *              </li>
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-11-13 下午3:18:01
 * @author 刘景
 */
@Path("saleorder")
public class SaleOrderResource extends AbstractSORestResource {

  @GET
  @Produces("application/json")
  @Path("bybillcode")
  public JSONString getSaleorderByBillcode(
      @QueryParam("orgcode") String orgcode,
      @QueryParam("vbillcode") String vbillcode) {
    if (StringUtils.isEmpty(vbillcode)) {
      return RestUtils.emptyJSONString;
    }
    RestUtils.initInvocationInfo();
    
    
    ISaleOrderQueryAPI query =
        NCLocator.getInstance().lookup(ISaleOrderQueryAPI.class);
    QuerySchemeBuilder builder =
        QuerySchemeBuilder.buildByFullClassName(SaleOrderHVO.class.getName());
    builder.append(ISaleOrderVO.VBILLCODE, EqOperator.getInstance(),
        new String[] {
          vbillcode
    });
    
    if (!StringUtils.isEmpty(orgcode)) {
      String pkGroup = AppContext.getInstance().getPkGroup();
      OrgVO orgVO = new OrgVO();
      Map<String, String> codeToId =
          TranslateUtils.trancelateCodeToID(orgVO, new String[] {
            orgcode
      }, pkGroup);

      if (codeToId != null && codeToId.get(orgcode) != null) {
        builder.append(ISaleOrderVO.PK_ORG, EqOperator.getInstance(),
            new String[] {
              codeToId.get(orgcode)
        });
      }
    }

    SaleOrderVO[] vos = null;
    try {
      vos = query.queryVOByScheme(builder.create());
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    if (ArrayUtils.isEmpty(vos)) {
      return RestUtils.toJSONString(new SaleOrderVO());
    }
    
    return RestUtils.toJSONString(vos[0]);
  }

  @POST
  @Path("insertWithArray")
  @Consumes("application/json")
  @Produces("application/json")
  public JSONString insertArriveBills(JSONString str) throws BusinessException {
    SaleOrderVO[] bills = GsonUtils.buildNCGson4Rest().fromJson(str.toString(),
        SaleOrderVO[].class);

    if (ArrayUtils.isEmpty(bills)) {
      return RestUtils.emptyJSONString;
    }
    RestUtils.initInvocationInfo();

    // 翻译VO
    bills = new SaleOrderTranslateAdaptor().doTranslate(bills);

    // 执行插入
    SaleOrderVO[] saleorderVOs = NCLocator.getInstance()
        .lookup(ISaleOrderMaintainAPI.class).insertBills(bills);
    List<String> vbillCodes = new ArrayList<>();
    for (SaleOrderVO saleorderVO : saleorderVOs) {
      vbillCodes.add(saleorderVO.getParentVO().getVbillcode());
    }
    return RestUtils.toJSONString(vbillCodes.toArray(new String[0]));
  }

}
