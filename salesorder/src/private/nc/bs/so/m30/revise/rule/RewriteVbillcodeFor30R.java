/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018年4月2日 下午6:52:59 
 * @version: V6.5   
 */
package nc.bs.so.m30.revise.rule;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;

/**
 * @Description: TODO
 * @author: wangzy
 * @date: 2018年4月2日 下午6:52:59
 */
public class RewriteVbillcodeFor30R implements IRule<SaleOrderHistoryVO> {

	@Override
	public void process(SaleOrderHistoryVO[] vos) {
		// TODO 自动生成的方法存根
		// 空的话不保存
		if (vos == null || vos.length == 0) {
			return;
		}
		// 一次只能保存一个处理第一个
		String saleOrderID = vos[0].getParentVO().getCsaleorderid();
		String vbillcode = vos[0].getParentVO().getVbillcode();
		VOUpdate<SaleOrderHVO> update = new VOUpdate<SaleOrderHVO>();
		VOQuery<SaleOrderHVO> query = new VOQuery(SaleOrderHVO.class);
		SaleOrderHVO[] hvo = query.query(new String[] { saleOrderID });
		if (hvo.length == 0) {
			ExceptionUtils.wrappBusinessException("请检查该销售订单是否已删除");
		}
		SaleOrderHVO newHvo = new SaleOrderHVO();
		newHvo.setCsaleorderid(saleOrderID);
		newHvo.setVbillcode(vbillcode);
		// 将历史的代理协议号记录到def17
		newHvo.setVdef17(hvo[0].getVbillcode());
		// 更新销售订单的数据
		update.update(new SaleOrderHVO[] { newHvo }, new String[] { "vbillcode","vdef17" });
	}

}
