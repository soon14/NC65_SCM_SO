package nc.bs.so.m30.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;
import nc.vo.so.pub.enumeration.SOComponent;

/**
 * 订单BP的插入点定义
 */
public enum BP30PlugInPoint implements IPluginPoint {

	/**
	 * 订单删除
	 */
	DeleteBP("nc.vo.so.m30.plugin.BPPlugInPoint.Delete"),

	/**
	 * 订单新增保存
	 */
	InsertBP("nc.vo.so.m30.plugin.BPPlugInPoint.Insert"),

	/**
	 * 订单修订插入历史版本
	 */
	ReviseInsertBP("nc.bs.so.m30.revise.InsertSaleOrderHistoryBP"),

	/**
	 * 订单修订更新销售订单
	 */
	ReviseUpdateSOBP("nc.bs.so.m30.revise.SaleOrderHistoryUpdateSaleOrderBP"),

	/**
	 * 销售订单修订审批通过更新订单
	 */
	ReviseUpdateBP("nc.bs.so.m30.revise.UpdateSaleOrderBP"),

	/**
	 * 订单修订保存
	 */
	ReviseSaveBP("nc.bs.so.m30.revise.SaveReviseSaleOrderBP"),
	/**
	 * @author 王梓懿
	 * @date 2018-05-30 为销售订单修订增加修改保存功能 订单修改保存
	 */
	ReviseSaveBaseBP("nc.bs.so.m30.revise.SaveBaseReviseSaleOrderBP"),
	/**
	 * @author 王梓懿
	 * @date 2018-06-06 为销售订单修订增加删除功能
	 */
	ReviseDeleteBP("nc.bs.so.m30.revise.ReviseSaleOrderDeleteBP"),

	/**
	 * 销售订单修订提交送审
	 */
	ReviseSendBP("nc.vo.so.m30.plugin.BPPlugInPoint.ReviseSendBP"),

	/**
	 * 提交送审
	 */
	SendBP("nc.vo.so.m30.plugin.BPPlugInPoint.Send"),

	/**
	 * 订单收款核销删除
	 */
	SOBalanceDeleteBP("nc.vo.so.sobalance.plugin.BPPlugInPoint.Delete"),

	/**
	 * 订单收款核销新增保存
	 */
	SOBalanceInsertBP("nc.vo.so.sobalance.plugin.BPPlugInPoint.Insert"),

	/**
	 * 订单收款核销修改保存
	 */
	SOBalanceUpdateBP("nc.vo.so.sobalance.plugin.BPPlugInPoint.Update"),

	/**
	 * 订单收回
	 */
	UnSendBP("nc.vo.so.m30.plugin.BPPlugInPoint.UnSend"),

	/**
	 * 订单修改保存
	 */
	UpdateBP("nc.vo.so.m30.plugin.BPPlugInPoint.Update");

	// 插入点
	private String point;

	private BP30PlugInPoint(String point) {
		this.point = point;
	}

	@Override
	public String getComponent() {
		return SOComponent.Order.getComponent();
	}

	@Override
	public String getModule() {
		return NCModule.SO.getName();
	}

	@Override
	public String getPoint() {
		return this.point;
	}
}
