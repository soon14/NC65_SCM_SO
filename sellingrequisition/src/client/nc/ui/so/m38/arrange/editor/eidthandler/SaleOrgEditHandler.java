package nc.ui.so.m38.arrange.editor.eidthandler;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.billref.push.PushBillEvent;
import nc.ui.so.m38.arrange.pub.M38ArrangeModelCalculator;
import nc.ui.so.pub.keyvalue.ListKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.enumeration.ListTemplateType;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOCurrencyRule;
import nc.vo.so.pub.rule.SOExchangeRateRule;
import nc.vo.so.pub.rule.SaleOrgRelationRule;
import nc.vo.uap.rbac.profile.FunctionPermProfileManager;

/**
 * 预订单集中安排界面订单销售组织编辑事件
 * 
 * @since 6.0
 * @version 2012-3-28 下午03:13:13
 * @author fengjb
 */
public class SaleOrgEditHandler {

  public void afterEdit(BillListPanel listPanel, PushBillEvent e) {

    int row = e.getEditEvent().getRow();
    IKeyValue keyValue = new ListKeyValue(listPanel, row, ListTemplateType.SUB);

    // 设置部门为空
    keyValue.setHeadValue(SaleOrderHVO.CDEPTVID, null);
    keyValue.setHeadValue(SaleOrderHVO.CDEPTID, null);
    // 设置业务员为空
    keyValue.setHeadValue(SaleOrderHVO.CEMPLOYEEID, null);

    String csaleorgid = keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);
    // 转VID
    String csaleorgvid = OrgUnitPubService.getNewVIDByOrgID(csaleorgid);
    keyValue.setHeadValue(SaleOrderHVO.PK_ORG_V, csaleorgvid);

    // 重新匹配业务流程
    String trantypecode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    String userid = AppUiContext.getInstance().getPkUser();
    String cbiztypeid = null;
    try {
      cbiztypeid =
          PfUtilClient.retBusitypeCanStart(SOBillType.Order.getCode(),
              trantypecode, csaleorgid, userid);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    // 设置业务流程
    keyValue.setHeadValue(SaleOrderHVO.CBIZTYPEID, cbiztypeid);

    int[] rows = new int[] {
      row
    };
    // 缓存原始结算财务组织和本币
    String old_sendstockorg =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CSENDSTOCKORGVID);
    String old_settleorg =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CSETTLEORGVID);
    String old_currency =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CCURRENCYID);

    // 根据业务委托关系重新匹配新的结算财务组织、库存组织、物流组织
    SaleOrgRelationRule orgrelarule = new SaleOrgRelationRule(keyValue);
    orgrelarule.setFinanceOrg(rows);
    orgrelarule.setSendStockOrg(rows);
    orgrelarule.setTrafficOrg(rows);
    // 如果库存组织发生变化
    String new_sendstockorg =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CSENDSTOCKORGVID);
    if (!PubAppTool.isEqual(old_sendstockorg, new_sendstockorg)) {
      keyValue.setBodyValue(row, SaleOrderBVO.CSENDSTORDOCID, null);
    }
    // 如果财务组织发生变化
    String new_settleorg =
        keyValue.getBodyStringValue(row, SaleOrderBVO.CSETTLEORGVID);
    if (!PubAppTool.isEqual(old_settleorg, new_settleorg)) {
      SOCurrencyRule currule = new SOCurrencyRule(keyValue);
      currule.setCurrency(rows);
      // 如果本币币种发生变化
      String new_currency =
          keyValue.getBodyStringValue(row, SaleOrderBVO.CCURRENCYID);
      if (!PubAppTool.isEqual(old_currency, new_currency)) {
        SOExchangeRateRule exchangerule = new SOExchangeRateRule(keyValue);
        exchangerule.calcBodyExchangeRates(rows);
        // 数量单价金额计算
        M38ArrangeModelCalculator calculator =
            new M38ArrangeModelCalculator(listPanel);
        calculator.calculate(rows, SaleOrderBVO.NEXCHANGERATE);
      }
    }

  }

  public void beforeEdit(BillListPanel listPanel, PushBillEvent e) {
    BillItem orgitem = listPanel.getBodyItem(SaleOrderHVO.PK_ORG);
    String usercode =
        WorkbenchEnvironment.getInstance().getLoginUser().getUser_code();
    String[] orgpks =
        FunctionPermProfileManager.getInstance().getProfile(usercode)
            .getPermPkorgs();
    UIRefPane orgRefPane = (UIRefPane) orgitem.getComponent();
    AbstractRefModel model = orgRefPane.getRefModel();
    model.setFilterPks(orgpks);

  }
}
