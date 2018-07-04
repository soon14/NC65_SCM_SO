/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018年6月6日 上午10:22:08 
 * @version: V6.5   
 */
package nc.impl.so.m30.action.main;

import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.rule.SOPfStatusChgRule;

/**
 * @Description: TODO
 * @author: wangzy
 * @date: 2018年6月6日 上午10:22:08
 */
public class DeleteSaleOrderReviseAction {
	public SaleOrderVO[] delete(SaleOrderVO[] clientBills,
			SaleOrderVO[] originBills) {
		// 把VO持久化到数据库中
		BillDelete<SaleOrderVO> delete = new BillDelete<SaleOrderVO>();
		delete.delete(clientBills);
		return clientBills;
	}
}
