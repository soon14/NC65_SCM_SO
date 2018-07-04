/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018年6月6日 上午10:26:52 
 * @version: V6.5   
 */
package nc.bs.so.m30.revise.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;

/**
 * @Description: TODO
 * @author: wangzy
 * @date: 2018年6月6日 上午10:26:52
 */
public class ReWriteSaleOrderForRevise implements IRule<SaleOrderVO> {
	// 销售订单修订删除后增加对销售订单的回写
	@Override
	public void process(SaleOrderVO[] vos) {
		Map<String, String> data = new HashMap<String, String>();
		for (SaleOrderVO saleOrderVO : vos) {
			SaleOrderHistoryVO his = (SaleOrderHistoryVO) saleOrderVO;
			// 获得销售订单的主键
			String csaleorderid = his.getParentVO().getCsaleorderid();
			// 查询上一个版本的销售订单修订记录，并获取其单据号
			Integer version = saleOrderVO.getParentVO().getIversion() - 1;
			if (version < 0) {
				ExceptionUtils
						.wrappBusinessException("当前数据错误，导致无法将上个版本的销售订单号回写给销售订单，无法删除");
			}
			String SaleOrderNo = getLastSaleOrderNo(csaleorderid, version);
			// 说明没有找到上一个版本
			if (SaleOrderNo == null) {
				ExceptionUtils.wrappBusinessException("上一个版本的销售订单修订中 销售订单号为空");
			}
			data.put(csaleorderid, SaleOrderNo);
		}
		updateSaleOrderBillNo(data);
	}

	/**
	 * @Title: updateSaleOrderBillNo
	 * @Description: 更新销售订单的单据号，也就是回滚到上一个版本的单据号
	 * @param data
	 * @return: void
	 */
	private void updateSaleOrderBillNo(Map<String, String> data) {
		// TODO 自动生成的方法存根
		List<SaleOrderHVO> vos = new ArrayList<SaleOrderHVO>();
		for (Map.Entry<String, String> entry : data.entrySet()) {
			SaleOrderHVO vo = new SaleOrderHVO();

			vo.setPrimaryKey(entry.getKey());
			vo.setVbillcode(entry.getValue());
			vos.add(vo);
		}
		VOUpdate<SaleOrderHVO> update = new VOUpdate<SaleOrderHVO>();
		update.update(vos.toArray(new SaleOrderHVO[vos.size()]),
				new String[] { "vbillcode" });

	}

	private String getLastSaleOrderNo(String csaleorderid, Integer version) {
		// TODO 自动生成的方法存根
		SqlBuilder sql = new SqlBuilder();
		sql.append("select vbillcode from so_orderhistory where csaleorderid ='");
		sql.append(csaleorderid);
		sql.append("' and iversion ='");
		sql.append(version);
		sql.append("' and dr<>1");
		DataAccessUtils dao = new DataAccessUtils();
		IRowSet set = dao.query(sql.toString());
		if (set.size() == 0) {
			return null;
		}
		String[] ids = set.toOneDimensionStringArray();
		return ids[0];
	}

}
