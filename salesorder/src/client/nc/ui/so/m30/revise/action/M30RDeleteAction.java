/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018年6月6日 上午10:01:42 
 * @version: V6.5   
 */
package nc.ui.so.m30.revise.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.pubapp.uif2app.actions.pflow.DeleteScriptAction;
import nc.ui.uif2.UIState;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * @Description: 销售订单修订的删除按钮类
 * @author: wangzy
 * @date: 2018年6月6日 上午10:01:42
 */
public class M30RDeleteAction extends DeleteScriptAction {
	public M30RDeleteAction() {
		super();
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO 自动生成的方法存根

		super.doAction(e);
	}

	/**
	 * 可用性控制：理论上能修改就能删除，所以复用修改的逻辑
	 */
	@Override
	protected boolean isActionEnable() {
		boolean iseidtable = (this.model.getUiState() == UIState.NOT_EDIT)
				&& (this.model.getSelectedData() != null);
		if (iseidtable) {
			SaleOrderVO vo = (SaleOrderVO) this.getModel().getSelectedData();
			// 未提交的可修改,还应该只能修改版本号最新的销售订单
			Integer fstatusflag = vo.getParentVO().getFstatusflag();
			Integer iVersion = vo.getParentVO().getIversion();
			boolean lastestVersion = isLastestVersion(vo.getParentVO()
					.getCsaleorderid(), iVersion);
			// 根据销售模块规范，1 为自由态，7为审批过程中 ，2为审批通过状态
			if (lastestVersion
					&& nc.vo.so.pub.enumeration.BillStatus.FREE
							.equalsValue(fstatusflag)) {
				return true;
			}
		}
		return false;
	}

	private boolean isLastestVersion(String primaryKey, Integer iVersion) {
		// TODO 自动生成的方法存根

		String sql = "select max(iversion) from so_orderhistory where csaleorderid ='"
				+ primaryKey + "' and dr =0";
		IUAPQueryBS service = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		ArrayList<Object[]> rs = new ArrayList<Object[]>();
		try {
			rs = (ArrayList<Object[]>) service.executeQuery(sql,
					new ArrayListProcessor());
		} catch (BusinessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		if (rs == null || rs.get(0) == null) {
			return false;
		}
		int version = rs.get(0)[0] == null ? 0 : (Integer) rs.get(0)[0];
		// 不是最新版本或者就没修订过不可以修改
		if (Integer.valueOf(version) == 0
				|| Integer.valueOf(version) != iVersion) {
			return false;
		}
		return true;
	}

}
