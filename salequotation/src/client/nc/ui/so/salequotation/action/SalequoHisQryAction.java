package nc.ui.so.salequotation.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m4310trantype.IM4310TranTypeService;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.salequotation.findprice.BodySelectedRowsGetter;
import nc.ui.so.salequotation.model.FindPriceService;
import nc.ui.so.salequotation.model.SalequoModel;
import nc.ui.so.salequotation.pub.SalequoCalculator;
import nc.ui.so.salequotation.view.HisQuotationDlg;
import nc.ui.so.salequotation.view.SalequoBillForm;
import nc.ui.uif2.NCAction;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m4310trantype.entity.M4310TranTypeVO;
import nc.vo.so.pub.enumeration.PriceDiscountType;
import nc.vo.so.pub.util.SOSysParaInitUtil;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.FindHistoryPriceParameter;
import nc.vo.so.salequotation.entity.FindPriceParaFactory;
import nc.vo.so.salequotation.entity.HisSalequoVO;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

public class SalequoHisQryAction extends NCAction {

  /**
   *
   */
  private static final long serialVersionUID = 565567798482734412L;

  private SalequoBillForm editor;

  private FindPriceService findPriceService;

  private ShowUpableBillListView list;

  private SalequoModel model;

  private M4310TranTypeVO tranTypeVO;

  public SalequoHisQryAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_HISQRY);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {

    AggSalequotationHVO selectvo = this.getCurrentData();
    if (selectvo == null) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0003")/* @res "没有选中数据！" */);
    }
    SalequotationHVO hvo = selectvo.getParentVO();
    String vtrantype = hvo.getVtrantype(); // 报价单类型
    String pk_group = hvo.getPk_group(); // 集团
    if (!StringUtil.isEmptyWithTrim(vtrantype)) {
      IM4310TranTypeService service =
          NCLocator.getInstance().lookup(IM4310TranTypeService.class);
      this.tranTypeVO = service.queryTranType(pk_group, vtrantype);
      if (this.tranTypeVO == null) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006009_0", "04006009-0002")/* @res "没有报价单交易类型！" */);
      }
    }
    else {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0002")/* @res "没有报价单交易类型！" */);
    }

    BodySelectedRowsGetter rowsGetter =
        new BodySelectedRowsGetter(this.editor.getBillCardPanel());
    int[] selectedRowsnew =
        rowsGetter.getSelectedRows(this.list.getBillListPanel());
    if (selectedRowsnew.length == 0) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0003")/* @res "没有选中数据！" */);
    }
    SalequotationBVO[] bvos = selectvo.getChildrenVO();
    List<Integer> lrow = new ArrayList<Integer>();
    for (int i = 0; i < selectedRowsnew.length; i++) {
      if (bvos.length < selectedRowsnew[i] + 1) {
        ExceptionUtils
            .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006009_0", "04006009-0003")/* @res "没有选中数据！" */);
      }
      String mid = bvos[selectedRowsnew[i]].getPk_material();
      if (!PubAppTool.isNull(mid)) {
        lrow.add(Integer.valueOf(selectedRowsnew[i]));
      }
    }

    int[] selectedRows = new int[lrow.size()];
    if (lrow.size() == 0) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0003")/* @res "没有选中数据！" */);
    }
    else {
      int i = 0;
      for (Integer irow : lrow) {
        selectedRows[i] = irow.intValue();
        i++;
      }
    }

    UFDouble[] price = this.findHistoryPrice(selectvo, selectedRows);
    HisSalequoVO[] vos =
        this.createHisSalequoVOs(selectvo, price, selectedRows);
    HisQuotationDlg quoDLG =
        new HisQuotationDlg(
            this.getEditor().getBillCardPanel(),
            NCLangRes.getInstance().getStrByID("4006009_0", "04006009-0045")/* 历史报价情况 */,
            vos, this.getModel().getAppUiState(), true);

    quoDLG.setSize(739, 260); // 调整大小
    if (quoDLG.showModal() != UIDialog.ID_OK) {
      return;
    }

    // 拟报价
    price = quoDLG.getNewPrice();

    // 只有编辑态的时候才能调整表体的价格
    if (this.getModel().getAppUiState() == AppUiState.ADD
        || this.getModel().getAppUiState() == AppUiState.EDIT
        || this.getModel().getAppUiState() == AppUiState.COPY_ADD) {
      BillCardPanel cardPanel = this.getEditor().getBillCardPanel();
      SalequoCalculator calculator = this.getSalequoCalculator(cardPanel);
      // 含税优先
      if (SOSysParaInitUtil.getSO23(this.getModel().getContext().getPk_group()) == UFBoolean.TRUE) {
        for (int i = 0; i < selectedRows.length; i++) {
          cardPanel.setBodyValueAt(price[i], selectedRows[i],
              SalequotationBVO.NQTORIGTAXNETPRC);
        }
        calculator.calculate(selectedRows, SalequotationBVO.NQTORIGTAXNETPRC);
      }
      else {
        for (int i = 0; i < selectedRows.length; i++) {
          cardPanel.setBodyValueAt(price[i], selectedRows[i],
              SalequotationBVO.NQTORIGNETPRICE);
        }
        calculator.calculate(selectedRows, SalequotationBVO.NQTORIGNETPRICE);
      }
    }
  }

  public SalequoBillForm getEditor() {
    return this.editor;
  }

  public FindPriceService getFindPriceService() {
    return this.findPriceService;
  }

  public ShowUpableBillListView getList() {
    return this.list;
  }

  public SalequoModel getModel() {
    return this.model;
  }

  public void setEditor(SalequoBillForm editor) {
    this.editor = editor;
  }

  public void setFindPriceService(FindPriceService findPriceService) {
    this.findPriceService = findPriceService;
  }

  public void setList(ShowUpableBillListView list) {
    this.list = list;
  }

  public void setModel(SalequoModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    boolean enabled1 = super.isActionEnable();
    if (this.getModel().getAppUiState().equals(AppUiState.NOT_EDIT)) {
      enabled1 = true;
    }
    return enabled1;
  }

  private HisSalequoVO[] createHisSalequoVOs(AggSalequotationHVO data,
      UFDouble[] price, int[] selectedRows) {
    HisSalequoVO[] vos = new HisSalequoVO[price.length];
    SalequotationBVO[] bvos = data.getChildrenVO();
    SalequotationHVO hvo = data.getParentVO();
    for (int i = 0; i < vos.length; i++) {
      HisSalequoVO vo = new HisSalequoVO();
      vos[i] = vo;
      SalequotationBVO bvo = bvos[selectedRows[i]];

      vo.setPk_material(bvo.getPk_material());
      vo.setCrowno(bvo.getCrowno());
      vo.setHisprice(price[i]);
      vo.setPk_currtype(hvo.getPk_currtype());
      // 取参数
      // 含税优先
      if (SOSysParaInitUtil.getSO23(this.getModel().getContext().getPk_group()) == UFBoolean.TRUE) {
        vo.setNewprice(bvo.getNqtorigtaxnetprc());
      }
      else { // 无税优先
        vo.setNewprice(bvo.getNqtorignetprice());
      }
    }
    return vos;
  }

  private UFDouble[] findHistoryPrice(AggSalequotationHVO data,
      int[] selectedRows) throws Exception {
    SalequotationHVO hvo = data.getParentVO();
    String vtrantype = hvo.getVtrantype(); // 报价单类型
    if (StringUtil.isEmptyWithTrim(vtrantype)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0004")/* @res "报价单类型不能为空" */);
    }
    FindHistoryPriceParameter[] findHistoryPriceParameter =
        FindPriceParaFactory.getInstance().createFindHistoryPriceParameters(
            data, selectedRows, this.getModel().getContext().getPk_org(),
            this.getModel().getContext().getPk_group());
    UFDouble[] price =
        this.getFindPriceService().findHistoryPrice(findHistoryPriceParameter,
            this.tranTypeVO); // 历史价
    return price;
  }

  private AggSalequotationHVO getCurrentData() {
    AggSalequotationHVO data = null;
    if (this.getModel().getAppUiState() == AppUiState.ADD
        || this.getModel().getAppUiState() == AppUiState.EDIT
        || this.getModel().getAppUiState() == AppUiState.COPY_ADD) {
      data = (AggSalequotationHVO) this.getEditor().getValue();
    }
    else {
      data = (AggSalequotationHVO) this.getModel().getSelectedData();
    }
    return data;
  }

  private SalequoCalculator getSalequoCalculator(BillCardPanel cardPanel) {
    SalequoCalculator calculator = new SalequoCalculator(cardPanel);
    // 设置调单价方式
    boolean isChgPriceOrDiscount = false;
    String modifymny = this.tranTypeVO.getFmodifymny();
    if (PriceDiscountType.PRICETYPE.getStringValue().equals(modifymny)) {
      isChgPriceOrDiscount = true;
    }
    calculator.setIsChgPriceOrDiscount(isChgPriceOrDiscount);
    return calculator;
  }

}
