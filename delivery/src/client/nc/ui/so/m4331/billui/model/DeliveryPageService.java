package nc.ui.so.m4331.billui.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m4331.IDeliveryMaintainApp;
import nc.ui.pubapp.uif2app.actions.pagination.IPaginationInitQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.scmpub.page.model.IBillPageQuery;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pagination.PaginationQueryVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.page.PageQueryVO;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * 发货单分页显示服务类
 * 
 * @since 6.36
 * @author zhangby5
 * @version 2015-4-10
 */
public class DeliveryPageService implements IBillPageQuery,
		IPaginationInitQuery {

	@Override
	public Object[] queryObjectByPks(String[] pks) throws BusinessException {
		IDeliveryMaintainApp service = NCLocator.getInstance().lookup(
				IDeliveryMaintainApp.class);
		DeliveryVO[] bills = null;
		try {
			bills = service.queryM4331App(pks);
		} catch (BusinessException ex) {
			ExceptionUtils.wrappException(ex);
		}
		return bills;
	}

	@Override
	public Integer getPageSize() {
		return Integer.valueOf(10);
	}

	@Override
	public PaginationQueryVO queryPaginationQueryVO(String condition,
			Integer billcount, Object... userobj) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageQueryVO query(IQueryScheme scheme) {
		IDeliveryMaintainApp service = NCLocator.getInstance().lookup(
				IDeliveryMaintainApp.class);
		PageQueryVO page = null;
		try {
			page = service.queryM4331App(scheme);
		} catch (BusinessException ex) {
			ExceptionUtils.wrappException(ex);
		}
		return page;
	}

}
