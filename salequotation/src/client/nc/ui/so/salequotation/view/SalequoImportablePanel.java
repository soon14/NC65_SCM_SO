package nc.ui.so.salequotation.view;


public class SalequoImportablePanel {

}

// public class SalequoImportablePanel extends Uif2ImportablePanel {
//
// @Override
// public List<InputItem> getInputItems() {
// List<InputItem> inputItems = super.getInputItems();
// String tableCode = null;
// String tableName = null;
// if (inputItems == null) {
// inputItems = new ArrayList<InputItem>();
// }
// if (inputItems.size() > 0) {
// tableCode = inputItems.get(0).getTabCode();
// tableName = inputItems.get(0).getTabName();
// }
// InputItem orgItem =
// InputItemGenerator.getHeadInputItem(SalequotationHVO.PK_ORG, 0, "销售组织",
// tableCode, tableName, true);
// inputItems.add(orgItem);
// return inputItems;
// }
//
// @Override
// protected String getAddActionBeanName() {
// return null;
// }
//
// @Override
// protected String getBillCardEditorBeanName() {
// return null;
// }
//
// @Override
// protected String getCancelActionBeanName() {
// return null;
// }
//
// @Override
// protected String getSaveActionBeanName() {
// return null;
// }
//
// @Override
// protected void setProcessedVO(ExtendedAggregatedValueObject eavo) {
// // CircularlyAccessibleValueObject headVO = eavo.getParentVO();
// // SalequoBillForm billForm = (SalequoBillForm)
// // this.getBillcardPanelEditor();
// // billForm.getBillOrgPanel().setPkOrg(
// // (String) headVO.getAttributeValue(SalequotationHVO.PK_ORG));
// super.setProcessedVO(eavo);
// }
//
// }
