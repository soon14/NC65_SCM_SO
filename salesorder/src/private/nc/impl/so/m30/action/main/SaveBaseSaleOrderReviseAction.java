/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018年5月30日 上午9:09:08 
 * @version: V6.5   
 */
package nc.impl.so.m30.action.main;

import nc.bs.so.m30.revise.SaveBaseReviseSaleOrderBP;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryHVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;
import nc.vo.so.pub.rule.SOPfStatusChgRule;

/**
 * @Description: TODO
 * @author: wangzy
 * @date: 2018年5月30日 上午9:09:08
 */
public class SaveBaseSaleOrderReviseAction {
	public SaleOrderHistoryVO[] saveBase(SaleOrderHistoryVO[] clientBills,
			SaleOrderHistoryVO[] originBills) {
		// 可以在此处做一些关于数据更新的处理，由于目前没有需求直接就调bp
		// 将表头的标志设置为null
		before(clientBills);
		SaveBaseReviseSaleOrderBP bp = new SaveBaseReviseSaleOrderBP();
		SaleOrderHistoryVO[] ret = null;
		try {
			ret = bp.update(clientBills, originBills);
		} catch (BusinessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * @Description: 一次调用完成，将标志设置为null
	 */
	private void before(SaleOrderHistoryVO[] clientBills) {
		// TODO 自动生成的方法存根
		for (SaleOrderHistoryVO saleOrderHistoryVO : clientBills) {
			SaleOrderHistoryHVO parentVO = saleOrderHistoryVO.getParentVO();
			parentVO.setAttributeValue("agdef1", null);
		}

	}

}
