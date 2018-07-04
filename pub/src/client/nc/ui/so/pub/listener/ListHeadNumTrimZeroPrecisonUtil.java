package nc.ui.so.pub.listener;

import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillModel;
import nc.vo.scmpub.util.ArrayUtil;
import nc.vo.scmpub.util.StringUtil;

/**
 * 列表界面中表头数量的精度处理（需要进行去零处理的那些字段）
 * 
 * @author jilu
 * 
 */
public class ListHeadNumTrimZeroPrecisonUtil {

	/**
	 * 如果只有billmodel和key也可以做到
	 * 
	 * @param billModel
	 * @param headKeys
	 */
	public static void addHeadNumTrimZeroPrecisonListener(BillModel billModel,
			String[] headKeys) {
		if (billModel == null || ArrayUtil.isEmpty(headKeys)) {
			return;
		}

		BillItem[] items = new BillItem[headKeys.length];
		for (int i = 0; i < items.length; i++) {
			items[i] = billModel.getItemByKey(headKeys[i]);
		}

		addHeadNumTrimZeroPrecisonListener(items);
	}

	/**
	 * 给一批billitem增加精度监听，注意这里的item应该是列表界面的表头中的item，如表头数量item，因为一般情况表体走的是单位精度，
	 * 而卡片界面表头已经使用conver处理了
	 * 
	 * @param items
	 */
	public static void addHeadNumTrimZeroPrecisonListener(BillItem[] items) {
		if (ArrayUtil.isEmpty(items)) {
			return;
		}

		for (int i = 0; i < items.length; i++) {
			String key = items[i].getKey();
			if (StringUtil.isEmptyTrimSpace(key)) {
				continue;
			}

			SOHeadNumTrimZeroListener listener = new SOHeadNumTrimZeroListener(
					key);
			items[i].addDecimalListener(listener);
		}
	}

}
