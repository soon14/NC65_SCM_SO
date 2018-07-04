package nc.ui.so.m30.revise.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30.revise.ISaleOrderReviseMaintainApp;
import nc.ui.pubapp.uif2app.actions.pagination.IPaginationInitQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.scmpub.page.model.IBillPageQuery;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pagination.PaginationQueryVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.page.PageQueryVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;

/**
 * 销售订单修订分页显示服务类
 * 
 * @since 6.36
 * @author zhangby5
 * @version 2015-4-10
 */
public class SaleOrderRevisePageService implements IBillPageQuery,
		IPaginationInitQuery {

	@Override
	public Object[] queryObjectByPks(String[] pks) throws BusinessException {
		ISaleOrderReviseMaintainApp service = NCLocator.getInstance().lookup(
				ISaleOrderReviseMaintainApp.class);
		SaleOrderHistoryVO[] bills = null;
		try {
			bills = service.queryM30ReviseApp(pks);
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
		ISaleOrderReviseMaintainApp service = NCLocator.getInstance().lookup(
				ISaleOrderReviseMaintainApp.class);
		PageQueryVO page = null;
		try {
			page = service.queryM30ReviseApp(scheme);
		} catch (BusinessException ex) {
			ExceptionUtils.wrappException(ex);
		}
		return page;
	}

}
