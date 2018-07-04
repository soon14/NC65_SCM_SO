package nc.ui.so.salequotation.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.org.IOrgConst;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.pubitf.credit.billcreditquery.IBillCreditQueryMessage;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.pub.locator.NCUILocator;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.ScriptPFlowAction;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.salequotation.view.SalequoBillForm;
import nc.vo.bd.material.MaterialVO;
import nc.vo.credit.billcreditquery.para.BillQueryPara;
import nc.vo.price.pub.context.PriceContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;
import nc.vo.trade.checkrule.VOChecker;

public class QueryCreditAction extends ScriptPFlowAction {

  /**
   *
   */
  private static final long serialVersionUID = -6373222009948293169L;

  private SalequoBillForm editor;

  public QueryCreditAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_CREDITQUERY);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (!SysInitGroupQuery.isCREDITEnabled()) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006011_0", "04006011-0450")/*请启用信用管理模块*/);
    }
    String pk_org = this.getModel().getContext().getPk_org();
    if (PubAppTool.isNull(pk_org)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0000")/*@res "没有数据"*/);
    }
    if (!OrgUnitPubService.isTypeOf(pk_org, IOrgConst.FINANCEORGTYPE)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0001")/*@res "该组织不是财务组织，没法进行信用查询"*/);
    }
    AggSalequotationHVO bill =
        (AggSalequotationHVO) this.getModel().getSelectedData();
    AppUiState uistate = this.getModel().getAppUiState();
    if (uistate == AppUiState.EDIT || uistate == AppUiState.ADD
        || uistate == AppUiState.COPY_ADD) {
      bill = (AggSalequotationHVO) this.editor.getValue();
    }

    SalequotationHVO header = null;
    SalequotationBVO[] bodies = null;
    IKeyValue keyValue = new CardKeyValue(this.getEditor().getBillCardPanel());
    int len = keyValue.getBodyCount();
    if (VOChecker.isEmpty(bill)) {
      header = new SalequotationHVO();
      header.setPk_channeltype(keyValue
          .getHeadStringValue(SalequotationHVO.PK_CHANNELTYPE));
      header.setPk_customer(keyValue
          .getHeadStringValue(SalequotationHVO.PK_CUSTOMER));
      header.setCemployeeid(keyValue
          .getHeadStringValue(SalequotationHVO.CEMPLOYEEID));
      header.setPk_dept_v(keyValue
          .getHeadStringValue(SalequotationHVO.PK_DEPT_V));
      header.setPk_org(keyValue.getHeadStringValue(SalequotationHVO.PK_ORG));
      header.setCtrantypeid(keyValue
          .getHeadStringValue(SalequotationHVO.CTRANTYPEID));
      if (len > 0) {
        bodies = new SalequotationBVO[len];
        for (int i = 0; i < len; i++) {
          bodies[i] = new SalequotationBVO();
          bodies[i].setPk_material_v(keyValue.getBodyStringValue(i,
              SalequotationBVO.PK_MATERIAL_V));
        }
      }
    }
    else {
      header = bill.getParentVO();
      bodies = bill.getChildrenVO();
    }
    int i = 0;
    int bodysLen = bodies.length;
    BillQueryPara[] bqpS = new BillQueryPara[1];
    if (bodysLen == 0) {
      bqpS[i] = new BillQueryPara();
      // 渠道类型
      bqpS[i].setCchanneltypeid(header.getPk_channeltype());
      // 客户
      bqpS[i].setCcustomerid(header.getPk_customer());
      // 销售业务员
      bqpS[i].setCemployeeid(header.getCemployeeid());
      // 销售部门
      bqpS[i].setCsaledeptid(header.getPk_dept_v());
      // 销售组织
      bqpS[i].setCsaleorgid(header.getPk_org());
      // 订单类型
      bqpS[i].setVtrantypecode(header.getCtrantypeid());
    }
    else {
      bqpS = new BillQueryPara[bodysLen];
      Map<String, String> prodlineMap = this.queryProdline(bodies);
      for (SalequotationBVO body : bodies) {
        bqpS[i] = new BillQueryPara();
        // 渠道类型
        bqpS[i].setCchanneltypeid(header.getPk_channeltype());
        // 客户
        bqpS[i].setCcustomerid(header.getPk_customer());
        // 销售业务员
        bqpS[i].setCemployeeid(header.getCemployeeid());
        // 财务组织
        bqpS[i].setCfinanceorgid(pk_org);
        // 物料
        bqpS[i].setCinvtoryid(body.getPk_material_v());
        // 销售部门
        bqpS[i].setCsaledeptid(header.getPk_dept_v());
        // 销售组织
        bqpS[i].setCsaleorgid(header.getPk_org());
        // 产品线
        bqpS[i].setCprodlineid(prodlineMap.get(body.getPk_material_v()));
        // 报价单类型
        // bqps[i].setVtrantypecode(header.getCtrantypeid());
        i++;
      }
    }

    // 调用接口
    IBillCreditQueryMessage service =
        NCUILocator.getInstance().lookup(IBillCreditQueryMessage.class,
            NCModule.CREDIT);
    service.showMessage(WorkbenchEnvironment.getInstance().getWorkbench()
        .getParent(), PriceContext.SALEQUOBILLTYPE, bqpS);
  }

  public SalequoBillForm getEditor() {
    return this.editor;
  }

  public void setEditor(SalequoBillForm editor) {
    this.editor = editor;
  }

  /*
   * 结构可调整优化
   */
  private Map<String, String> queryProdline(SalequotationBVO[] bodies) {
    List<String> pk_material_vs = new ArrayList<String>();
    for (SalequotationBVO body : bodies) {
      pk_material_vs.add(body.getPk_material_v());
    }
    Map<String, MaterialVO> map =
        MaterialPubService.queryMaterialBaseInfo(
            pk_material_vs.toArray(new String[0]), new String[] {
              MaterialVO.PK_PRODLINE
            });
    if (null == map || map.size() == 0) {
      return new HashMap<String, String>();
    }
    Set<Entry<String, MaterialVO>> entrySet = map.entrySet();
    Map<String, String> retMap = new HashMap<String, String>();
    for (Entry<String, MaterialVO> entry : entrySet) {
      retMap.put(entry.getKey(), entry.getValue().getPk_prodline());
    }
    return retMap;
  }
}
