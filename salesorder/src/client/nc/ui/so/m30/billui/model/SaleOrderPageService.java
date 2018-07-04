package nc.ui.so.m30.billui.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30.self.ISaleOrderMaintainApp;
import nc.ui.pubapp.uif2app.actions.pagination.IPaginationInitQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.scmpub.page.model.IBillPageQuery;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pagination.PaginationQueryVO;
import nc.vo.scmpub.page.PageQueryVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 销售订单分页显示服务类
 * 
 * @author zhangby5
 * 
 */
public class SaleOrderPageService implements IBillPageQuery,
		IPaginationInitQuery {

	@Override
	public Object[] queryObjectByPks(String[] pks) throws BusinessException {
		ISaleOrderMaintainApp service = NCLocator.getInstance().lookup(
				ISaleOrderMaintainApp.class);
		SaleOrderVO[] bills = null;
		try {
			bills = service.queryM30App(pks);
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
		ISaleOrderMaintainApp service = NCLocator.getInstance().lookup(
				ISaleOrderMaintainApp.class);
		PageQueryVO page = null;
		try {
			page = service.queryM30App(scheme);
		} catch (BusinessException ex) {
			ExceptionUtils.wrappException(ex);
		}
		return page;
	}

}
