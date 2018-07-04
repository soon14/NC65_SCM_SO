package nc.ui.so.salequotation.action;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.so.salequotation.findprice.ManagementPriceFinder;
import nc.ui.so.salequotation.model.FindPriceService;
import nc.ui.so.salequotation.model.SalequoModel;
import nc.ui.so.salequotation.view.SalequoBillForm;
import nc.ui.uif2.NCAction;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m4310trantype.entity.M4310TranTypeVO;
import nc.vo.so.m4310trantype.entity.SalequoDataSource;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

public class SaleQuotationFindPriceAction extends NCAction {

  private static final long serialVersionUID = 3003069759539301354L;

  private SalequoBillForm editor;

  private FindPriceService findPriceService;

  private SalequoModel model;

  public SaleQuotationFindPriceAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_ASKPRICE);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    
    // 如果没有加载价格模块，返回
    if (!SysInitGroupQuery.isPRICEEnabled()) {
      return;
    }   
    
    BillCardPanel cardPanel = this.editor.getBillCardPanel();
    int[] rows = cardPanel.getBillTable().getSelectedRows();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    Map<String, M4310TranTypeVO> tranTypeBuffer =
        this.getModel().getM4310TranTypeBuffer();
    String vtrantype = keyValue.getHeadStringValue(SalequotationHVO.VTRANTYPE);
    // 报价单类型空时不询价
    if (StringUtil.isEmptyWithTrim(vtrantype)) {
      return;
    }
    M4310TranTypeVO tranTypeVO = tranTypeBuffer.get(vtrantype);
    if (tranTypeVO == null) {
      return;
    }
	
    List<Integer> askRowList = new ArrayList<Integer>();
	String materId;
	for(int row : rows){
	  materId = (String) cardPanel.getBodyValueAt(row, SalequotationBVO.PK_MATERIAL);
	  if(!PubAppTool.isNull(materId)){
		askRowList.add(Integer.valueOf(row));
	  }		
	} 
	if(askRowList.size() == 0){
		ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006009_0","04006009-0003")/*@res "没有选中数据"*/);
	}
	int[] askRows = new int[askRowList.size()];
	int i = 0;
	for(Integer askRow : askRowList){
		askRows[i] = askRow.intValue();
		i++;
	}
	
    // 价格管理
    if (!SalequoDataSource.PRICE_MANAGEMENT.value().equals(
        tranTypeVO.getFsourceflag())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006009_0","04006009-0009")/*@res "报价数据来源为价格管理的时候，才可以询价！"*/);
    }

    String pk_group = keyValue.getHeadStringValue(SalequotationHVO.PK_GROUP);
    String pk_org = keyValue.getHeadStringValue(SalequotationHVO.PK_ORG);
    ManagementPriceFinder mp = new ManagementPriceFinder(tranTypeVO);
    mp.setFindPriceService(this.findPriceService);
    mp.findPriceByManual(cardPanel, askRows, pk_org, pk_group);

  }

  public SalequoBillForm getEditor() {
    return this.editor;
  }

  public FindPriceService getFindPriceService() {
    return this.findPriceService;
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

  public void setModel(SalequoModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    int[] rows =
        this.editor.getBillCardPanel().getBillTable().getSelectedRows();
    if (rows.length > 1) {
      return true;
    }
    if (this.editor.getBillCardPanel().getBillTable().getSelectedRow() == -1) {
      return false;
    }

    return true;
  }

}