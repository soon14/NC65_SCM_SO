package nc.ui.so.pub.util;

import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.so.ic.m4c.ISaleFor4CParaQuery;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.so.m4331.billui.view.DeliveryEditor;
import nc.vo.ic.batchcode.BatchDlgParam;
import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.entry.SOOnhandDlgHeadVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.uif2.LoginContext;

/**
 * 销售补全参数的工具类
 * 
 * @author zhangby5
 * 
 */
public class SOFillParamForICUtil {

  /**
   * 库存批次参照的补全参数
   * 
   * @param billform
   * @param keyValue
   * @param row
   * @return
   */
  public static BatchDlgParam getBatchDlgParam(ShowUpableBillForm billform,
      IKeyValue keyValue, int row) {
    String pk_org = null;
    if(DeliveryEditor.class.isInstance(billform)){
      pk_org = keyValue.getBodyStringValue(row, "csaleorgid");
      if(PubAppTool.isNull(pk_org)){
        pk_org = keyValue.getBodyStringValue(row, SOItemKey.PK_ORG);
      }
    }else{
      pk_org = keyValue.getBodyStringValue(row, SOItemKey.PK_ORG);
    }
    if (PubAppTool.isNull(pk_org)) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006004_0", "04006004-0250")/* 批次参照失败！主组织不能为空。 */);
    }
    ISaleFor4CParaQuery query =
        NCLocator.getInstance().lookup(ISaleFor4CParaQuery.class);
    // 有权限的组织
    Map<String, List<String>> orgmap = null;
    try {
      orgmap =
          query.getStockOrgIDSBySaleOrgID(new String[]{pk_org});
      
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
    BatchDlgParam param = new BatchDlgParam();
    SOOnhandDlgHeadVO headVO = new SOOnhandDlgHeadVO();
    OnhandDimVO dimVO = param.getOnhandDim();
    for (String str : BATCHCODE_ITEM) {
      param.setAttributeValue(str, keyValue.getBodyStringValue(row, str));
    }
    for (String dimVOItem : SOConstant.ONHANDDLG_BODY_KEY) {
      dimVO.setAttributeValue(dimVOItem,
          keyValue.getBodyStringValue(row, dimVOItem));
    }
    String materialoid =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CMATERIALID);
    param.setCmaterialoid(materialoid);
    dimVO.setAttributeValue(OnhandDimVO.CMATERIALOID, materialoid);
    dimVO.setAttributeValue(OnhandDimVO.CWAREHOUSEID,
        keyValue.getBodyStringValue(row, SOItemKey.CSENDSTORDOCID));
    dimVO.setAttributeValue(OnhandDimVO.PK_ORG,
        keyValue.getBodyStringValue(row, SOItemKey.CSENDSTOCKORGID));
    LoginContext context = billform.getModel().getContext();
    dimVO.setAttributeValue(OnhandDimVO.PK_GROUP, context.getPk_group());
    dimVO.setCffileid(keyValue.getBodyStringValue(row, SOItemKey.CMFFILEID));
    param.setLoginContext(context);
    param.setPluginConfigPath(SOConstant.SO_BATCHCODE_PATH);
    param.setIsNewBatchRef(UFBoolean.TRUE);
    DataViewMeta dataViewMeta = new DataViewMeta(dimVO.getClass());
    headVO.setDataViewMeta(dataViewMeta);
    headVO.setVO(dimVO);
    headVO.setCrowno((String) keyValue
        .getBodyStringValue(row, SOItemKey.CROWNO));
    headVO.setCunitid((String) keyValue
            .getBodyStringValue(row, SOItemKey.CUNITID));
    UFDouble nnum =
        (UFDouble) keyValue.getBodyUFDoubleValue(row, SOItemKey.NNUM);
    headVO.setOnhandshouldnum(nnum);
    headVO.setOnhandshouldassnum((UFDouble) keyValue.getBodyUFDoubleValue(row,
        SOItemKey.NASTNUM));
    String org = (String) keyValue.getBodyStringValue(row, SOItemKey.PK_ORG);
    List<String> orglist = orgmap.get(org);
    //发货单取表体销售组织
    if (orglist == null) {
      org = (String) keyValue.getBodyStringValue(row, "csaleorgid");
      orglist = orgmap.get(org);
    }
    if (orglist == null) {
      headVO.setPk_orgs(null);
    }
    else {
      headVO.setPk_orgs(orglist.toArray(new String[orglist.size()]));
    }
    param.setHeadVO(headVO);
    return param;
  }

  private static final String[] BATCHCODE_ITEM = new String[] {
    SOItemKey.CSENDSTOCKORGID, SOItemKey.CSENDSTORDOCID,
    SOItemKey.CMATERIALVID, SOItemKey.VBATCHCODE
  };
}
