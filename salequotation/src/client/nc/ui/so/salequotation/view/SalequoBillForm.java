package nc.ui.so.salequotation.view;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.salequotation.entity.BillStatusEnum;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;
import nc.vo.uif2.LoginContext;

import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.event.OrgChangedEvent;
import nc.ui.pubapp.uif2app.model.IAppModelEx;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.util.BillPanelUtils;
import nc.ui.scmpub.ref.FilterCustomerRefUtils;
import nc.ui.scmpub.util.BillCardPanelUtils;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.pub.listener.SOBillTotalListener;
import nc.ui.so.salequotation.scale.SalequoScaleProcessor;
import nc.ui.uif2.AppEvent;

public class SalequoBillForm extends ShowUpableBillForm {

  private static final int MONTH_DAYS = 30;

  private static final String[] NOEDITBODYKEY = new String[] {
    SalequotationBVO.PK_SALEQUOTATION_B, SalequotationBVO.PK_SALEQUOTATION,
    SalequotationBVO.TS, SalequotationBVO.NORDERNUM,
    SalequotationBVO.NCONTRACTNUM, SalequotationBVO.NDISCOUNTRATE,
    SalequotationBVO.PK_TARIFFDEF
  };

  private static final String[] NOEDITHEADKEY = new String[] {
    SalequotationHVO.NTOTALMNY, SalequotationHVO.NTOTALNUM,
    SalequotationHVO.PK_GROUP, SalequotationHVO.FSTATUSFLAG
  };

  private static final double NUMBER_100 = 100;

  private static final int NUMBER_6 = 6;

  /**
   * 
   */
  private static final long serialVersionUID = -4836950488923224235L;

  private LoginContext context;

  public LoginContext getContext() {
    return this.context;
  }

  @Override
  public void handleEvent(AppEvent event) {
    super.handleEvent(event);
    if (event instanceof OrgChangedEvent) {
      if (((IAppModelEx) this.getModel()).getAppUiState() == AppUiState.ADD) {
        // 清除表单数据
        this.addNew();
      }

      String pk_org = this.getModel().getContext().getPk_org();
      this.setOrgVid(pk_org);
      this.setDefaultCurrency();

      BillPanelUtils.setOrgForAllRef(this.billCardPanel, this.getContext());

    }
  }

  @Override
  public void initUI() {
    super.initUI();

    BillCardPanel cardPanel = this.getBillCardPanel();
    cardPanel.getBodyPanel().setTotalRowShow(true);
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    SOBillTotalListener totallis = new SOBillTotalListener(keyValue);
    cardPanel.getBillModel().addTotalListener(totallis);

    // 初始化编辑性
    this.initEditEnable();

    SalequoScaleProcessor.getInstance().setCardPrecision(
        AppUiContext.getInstance().getPkGroup(), this.billCardPanel);
    this.initFillEnabled(cardPanel);
  }

  public void setContext(LoginContext context) {
    this.context = context;
  }

  @Override
  protected void setDefaultValue() {
    super.setDefaultValue();
    UFDate serverDate = AppUiContext.getInstance().getBusiDate();
    this.billCardPanel.getHeadItem(SalequotationHVO.DQUOTEDATE).setValue(
        serverDate);
    this.billCardPanel.getHeadItem(SalequotationHVO.DENDDATE)
        .setValue(
            serverDate.getDateAfter(
                SalequoBillForm.NUMBER_6 * SalequoBillForm.MONTH_DAYS)
                .asLocalEnd());
    this.billCardPanel.getHeadItem(SalequotationHVO.PK_GROUP).setValue(
        this.getModel().getContext().getPk_group());
    this.billCardPanel.getHeadItem(SalequotationHVO.NDISCOUNT).setValue(
        new UFDouble(SalequoBillForm.NUMBER_100));
    this.setDefaultCurrency();
    String pk_org = this.getModel().getContext().getPk_org();
    this.setOrgVid(pk_org);
    this.billCardPanel.getHeadItem(SalequotationHVO.FSTATUSFLAG).setValue(
        BillStatusEnum.FREE);
  }

  /**
   * 卡片界面初始化能否编辑属性
   */
  private void initEditEnable() {

    // 表头不可编辑项
    for (String key : SalequoBillForm.NOEDITHEADKEY) {
      BillItem headitem = this.getBillCardPanel().getHeadItem(key);
      if (null != headitem) {
        headitem.setEdit(false);
      }
    }
    // 表体不可编辑项
    for (String key : SalequoBillForm.NOEDITBODYKEY) {
      BillItem bodyitem = this.getBillCardPanel().getBodyItem(key);
      if (null != bodyitem) {
        bodyitem.setEdit(false);
      }
    }
  }

  /**
   * 初始化界面的编辑性
   * 
   * @param cardPanel
   */
  private void initFillEnabled(BillCardPanel cardPanel) {
    BillCardPanelUtils util = new BillCardPanelUtils(cardPanel);
    util.disableItemsFill();
  }



  /**
   * 设置默认币种
   */
  private void setDefaultCurrency() {
    String pk_org = this.getModel().getContext().getPk_org();
    if (pk_org != null) {
      String pk_currency = OrgUnitPubService.queryOrgCurrByPk(pk_org);
      this.billCardPanel.getHeadItem(SalequotationHVO.PK_CURRTYPE).setValue(
          pk_currency);
    }
  }

  private void setOrgVid(String pk_org) {
    if (pk_org != null) {
      String pk_org_vid = OrgUnitPubService.getNewVIDByOrgID(pk_org);
      this.billCardPanel.getHeadItem(SalequotationHVO.PK_ORG_V).setValue(
          pk_org_vid);
      this.billCardPanel.getBillData().loadEditHeadRelation(
          SalequotationHVO.PK_ORG_V);
      if (null != this.billCardPanel.getBodyItem(SalequotationHVO.PK_ORG_V)) {
        for (int i = 0; i < this.billCardPanel.getRowCount(); i++) {
          this.billCardPanel.setBodyValueAt(pk_org_vid, i,
              SalequotationHVO.PK_ORG_V);
          this.billCardPanel.setBodyValueAt(pk_org, i, SalequotationBVO.PK_ORG);
          this.billCardPanel.getBillModel().loadEditRelationItemValue(i,
              SalequotationHVO.PK_ORG_V);
        }
      }
    }
  }

}
