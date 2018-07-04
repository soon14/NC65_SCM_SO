package nc.ui.so.pub.listener;

import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.IBillModelDecimalListener;
import nc.vo.pub.lang.UFDouble;


/**
 * 处理界面中主表上的数量字段精度设置，如销售订单的表头合计数量字段，需要进行去零处理，根据去零后的精度设置item的精度，这样做可以保证合计行上的精度正确性
 * 
 * @author jilu v65
 *
 */
public class SOHeadNumTrimZeroListener implements IBillModelDecimalListener {
	
	private String key;
	
	/**
	 * 需要注册的item的key
	 * 
	 * @param key
	 */
	public SOHeadNumTrimZeroListener(String key){
		this.key = key;
	}

	@Override
	public String getSource() {
		return key;
	}

	@Override
	public int getDecimalFromSource(int row, Object okValue) {
		if (okValue == null) {
			return 0;
		}
		if (okValue instanceof UFDouble) {
			UFDouble doubleVal = (UFDouble) okValue;
			// 去零处理
			doubleVal.setTrimZero(true);
			int point = doubleVal.toString().lastIndexOf(".");
			int digit = -1 == point ? 0 : doubleVal.toString().length() - 1
					- point;
			return digit;
		}

		return 0;
	}

	@Override
	public boolean isTarget(BillItem item) {
		return false;
	}

}
