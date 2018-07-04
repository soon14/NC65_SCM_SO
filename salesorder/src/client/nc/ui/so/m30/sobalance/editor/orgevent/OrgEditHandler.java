package nc.ui.so.m30.sobalance.editor.orgevent;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30trantype.IM30TranTypeService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.OrgChangedEvent;
import nc.ui.pubapp.uif2app.view.util.BillPanelUtils;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.SaleOrderPrecision;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.UIState;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.uif2.LoginContext;

public class OrgEditHandler implements IAppEventHandler<OrgChangedEvent> {

  private BillCardPanel billCardPanel;

  private SaleOrderBillForm billfrom;

  private LoginContext context;

  public OrgEditHandler(SaleOrderBillForm bill, LoginContext context) {
    this.billfrom = bill;
    this.billCardPanel = bill.getBillCardPanel();
    this.context = context;
  }

  @Override
  public void handleAppEvent(OrgChangedEvent e) {

    if (this.billfrom.isEditable()) {
      // 在编辑状态下，主组织切换时，清空界面数据，自动表体增行，并设置行号，songhy
      this.billfrom.addNew();
    }

    // 设置界面精度
    SaleOrderPrecision.getInstance().setCardPrecision(
        this.context.getPk_group(), this.billCardPanel);

    // 新增单据
    if (this.billfrom.getModel().getUiState().equals(UIState.ADD)) {
      // 清空单据面板
      this.clearPanel();

      if (PubAppTool.isNull(e.getNewPkOrg())) {
        return;
      }
      // 设置默认值
      this.setDefValue(e);
      // 设置退货节点默认值
      this.setReturnFedValue(e);
    }
    BillPanelUtils.setOrgForAllRef(this.billfrom.getBillCardPanel(),
        this.billfrom.getModel().getContext());
  }

  private void setReturnFedValue(OrgChangedEvent e) {
    IKeyValue keyValue = new CardKeyValue(this.billCardPanel);
    // 退换货节点特征
    if ("4006080201".equals(this.context.getNodeCode())) {

      M30TranTypeVO m30trantypevo = null;
      IM30TranTypeService srv =
          NCLocator.getInstance().lookup(IM30TranTypeService.class);
      String pk_group = AppUiContext.getInstance().getPkGroup();
      try {
        m30trantypevo = srv.queryTranType(pk_group, "30-02");
      }
      catch (BusinessException ex) {
        ExceptionUtils.wrappException(ex);
      }
      // 退货订单设置交易类型
      if (null != m30trantypevo) {
        this.billfrom.getM30ClientContext()
            .setTransType("30-02", m30trantypevo);
        keyValue.setHeadValue(SaleOrderHVO.CTRANTYPEID,
            m30trantypevo.getCtrantypeid());
        this.billCardPanel.getBillData().loadEditHeadRelation(
            SaleOrderHVO.CTRANTYPEID);
        // 2.自动匹配业务流程
        String csaleorgid = keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);
        String userid = AppUiContext.getInstance().getPkUser();
        String cbiztypeid = null;
        try {
          cbiztypeid =
              PfUtilClient.retBusitypeCanStart(SOBillType.Order.getCode(),
                  "30-02", csaleorgid, userid);
        }
        catch (BusinessException ex) {
          ExceptionUtils.wrappException(ex);
        }
        // 设置业务流程
        keyValue.setHeadValue(SaleOrderHVO.CBIZTYPEID, cbiztypeid);
      }
    }
  }

  private void clearPanel() {
    IKeyValue keyValue = new CardKeyValue(this.billCardPanel);
    BillItem[] headItems = this.billCardPanel.getHeadItems();
    for (BillItem item : headItems) {
      item.setValue(item.getDefaultValue());
    }

    BillItem[] bodyItems = this.billCardPanel.getBodyItems();
    int irowcount = this.billCardPanel.getRowCount();
    for (BillItem item : bodyItems) {
      // 表体行号不应清除
      if (SaleOrderBVO.CROWNO.equals(item.getKey())) {
        continue;
      }
      for (int i = 0; i < irowcount; i++) {
        keyValue.setBodyValue(i, item.getKey(), item.getDefaultValue());
      }
    }

    BillItem[] tailItems = this.billCardPanel.getTailItems();
    for (BillItem item : tailItems) {
      item.setValue(item.getDefaultValue());
    }
  }

  private void setDefValue(OrgChangedEvent e) {
    IKeyValue keyValue = new CardKeyValue(this.billCardPanel);
    String pk_org = e.getNewPkOrg();
    // 1.表头
    // 销售组织
    keyValue.setHeadValue(SaleOrderHVO.PK_ORG, pk_org);
    this.billCardPanel.getBillData().loadEditHeadRelation(SaleOrderHVO.PK_ORG);
    // 集团
    keyValue.setHeadValue(SaleOrderHVO.PK_GROUP, this.context.getPk_group());
    // 单据日期
    UFDate busidate = AppUiContext.getInstance().getBusiDate();
    keyValue.setHeadValue(SaleOrderHVO.DBILLDATE, busidate);
    // 整单折扣
    keyValue.setHeadValue(SaleOrderHVO.NDISCOUNTRATE, SOConstant.ONEHUNDRED);
    // 单据状态
    keyValue.setHeadValue(SaleOrderHVO.FSTATUSFLAG,
        BillStatus.FREE.getIntegerValue());

    // 2.表体
    UFDate localenddate = busidate.asLocalEnd();
    for (int i = 0; i < keyValue.getBodyCount(); i++) {
      // 销售组织
      keyValue.setBodyValue(i, SaleOrderBVO.PK_ORG, pk_org);
      // 单据日期
      keyValue.setBodyValue(i, SaleOrderBVO.DBILLDATE, busidate);
      // 整单折扣
      keyValue.setBodyValue(i, SaleOrderBVO.NDISCOUNTRATE,
          SOConstant.ONEHUNDRED);
      // 单品折扣
      keyValue.setBodyValue(i, SaleOrderBVO.NITEMDISCOUNTRATE,
          SOConstant.ONEHUNDRED);
      // 主数量
      keyValue.setBodyValue(i, SaleOrderBVO.NNUM, UFDouble.ZERO_DBL);
      // 计划发货日期、要求收货日期
      keyValue.setBodyValue(i, SaleOrderBVO.DSENDDATE, localenddate);
      keyValue.setBodyValue(i, SaleOrderBVO.DRECEIVEDATE, localenddate);
    }
  }
}
