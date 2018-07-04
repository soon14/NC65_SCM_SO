package nc.ui.so.pub.listener;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.so.pub.keyvalue.ListKeyValue;
import nc.ui.uif2.editor.BillListView;
import nc.vo.so.pub.enumeration.ListTemplateType;

/**
 * 为列表界面设置合计行的工具类
 * 
 * @author jilu 2014-05-04 for v65
 * 
 */
public class SOListPanelTotalRowUtil {

	/**
	 * 设置列表界面的表头和表体的合计行
	 * 
	 * @param listView
	 *            界面类
	 */
	public static void setListViewTotalHeadAndBodyRow(BillListView listView) {
		BillListPanel listPanel = listView.getBillListPanel();
		setListViewTotalHeadRow(listPanel);
		setListViewTotalBodyRow(listPanel);
	}

	/**
	 * 设置列表界面的表头和表体的合计行
	 * 
	 * @param listPanel
	 *            ShowUpableBillListView中可以使用this.getBillListPanel()方法获取
	 */
	public static void setListViewTotalHeadAndBodyRow(BillListPanel listPanel) {
		setListViewTotalHeadRow(listPanel);
		setListViewTotalBodyRow(listPanel);
	}

	/**
	 * 设置列表界面的表头的合计行
	 * 
	 * @param listPanel
	 */
	public static void setListViewTotalHeadRow(BillListPanel listPanel) {
		// 合计表头
		listPanel.getParentListPanel().setTotalRowShow(true);
		ListKeyValue keyValue1 = new ListKeyValue(listPanel,
				ListTemplateType.MAIN);
		SOListPanelTotalListener totallis1 = new SOListPanelTotalListener(
				keyValue1);
		listPanel.getHeadBillModel().addTotalListener(totallis1);
	}

	/**
	 * 设置列表界面的表体的合计行
	 * 
	 * @param listPanel
	 */
	public static void setListViewTotalBodyRow(BillListPanel listPanel) {
		// 合计表体
		listPanel.getChildListPanel().setTotalRowShow(true);
		ListKeyValue keyValue2 = new ListKeyValue(listPanel,
				ListTemplateType.SUB);
		SOListPanelTotalListener totallis2 = new SOListPanelTotalListener(
				keyValue2);
		listPanel.getBodyBillModel().addTotalListener(totallis2);
	}
}
