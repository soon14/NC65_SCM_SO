package nc.ui.so.m32.billui.view;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.UIState;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.rule.VATDefaultRule;
import nc.vo.so.pub.keyvalue.VOKeyValue;

public class YshtInitDataListener extends DefaultFuncNodeInitDataListener{
	
	private BillManageModel model;
	private SaleInvoiceEditor autoShowUpComponent;
	
	public BillManageModel getModel() {
		return model;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
	}

	public SaleInvoiceEditor getAutoShowUpComponent() {
		return autoShowUpComponent;
	}

	public void setAutoShowUpComponent(SaleInvoiceEditor autoShowUpComponent) {
		this.autoShowUpComponent = autoShowUpComponent;
	}

	@Override
	public void initData(FuncletInitData data) {
		super.initData(data);
		 if (null==data) {
	            return;
	        } else {
	        	// 取出数据
				Object dataObject = data.getInitData();
				if(dataObject != null){
					int initType = data.getInitType();
					if(initType == 28){
						doLinkAdd(dataObject);
					}
				}
	        }
	}

	public void doLinkAdd(Object dataObject) {
		//取得卡片界面并展示
		ShowUpableBillForm billForm = autoShowUpComponent;
		billForm.showMeUp();
		//设置界面状态
		model.setUiState(UIState.ADD);
		
		//传过来的数据的vo
		SaleInvoiceVO aggvo = (SaleInvoiceVO) dataObject;
		if (aggvo == null) {
			return;
		}
//		getModel().initModel(aggvo);
		BillCardPanel billCardPanel = billForm.getBillCardPanel();
		
		BillItem[] headItems = billCardPanel.getHeadItems();
		
		// 设置表头属性
		billForm.getBillOrgPanel().setPkOrg( (String) aggvo.getParentVO().getAttributeValue("pk_org_v"));
		for (BillItem billItem : headItems) {
			Object attributeValue = aggvo.getParentVO().getAttributeValue(billItem.getKey());
			if(attributeValue != null && billItem != null){
				billItem.setValue(aggvo.getParentVO().getAttributeValue(billItem.getKey()));
			}
		}
		
		// 设置表体属性
		BillItem[] bodyItems = billCardPanel.getBodyItems();
		if(bodyItems != null && bodyItems.length>0){
			CircularlyAccessibleValueObject[] bvos = aggvo.getChildrenVO();
			if(bvos != null && bvos.length > 0){
				for(int i=0;i<bvos.length;i++){
					billCardPanel.addLine();
					for (BillItem billItem : bodyItems) {
						String itemkey = billItem.getKey();
						billCardPanel.setBodyValueAt(bvos[i].getAttributeValue(itemkey), i, itemkey);
						billCardPanel.getBillModel().loadEditRelationItemValue(i, itemkey);
					}
				}
			}
		}
		
		// 设置两个VAT字段
        VOKeyValue<SaleInvoiceVO> keyvalue = new VOKeyValue<SaleInvoiceVO>(aggvo);
        VATDefaultRule vatrule = new VATDefaultRule(keyvalue);
        vatrule.setVatCodeValue();
		
		billCardPanel.execHeadTailLoadFormulas();
		billCardPanel.getBillData().loadLoadHeadRelation();
		billCardPanel.getBillModel().loadLoadRelationItemValue();
	}
}
