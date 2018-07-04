package nc.ui.so.salequotation.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.salequotation.ISalequotationMaintainApp;
import nc.ui.pubapp.uif2app.actions.pagination.IPaginationInitQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.scmpub.page.model.IBillPageQuery;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pagination.PaginationQueryVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.page.PageQueryVO;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;

/**
 * 
 * @since 6.36
 * @author zhangby5
 * @version 2015-4-11
 */
public class SalequoPageService implements IBillPageQuery, IPaginationInitQuery {

	@Override
	public Object[] queryObjectByPks(String[] pks) throws BusinessException {
		ISalequotationMaintainApp service = NCLocator.getInstance().lookup(
				ISalequotationMaintainApp.class);
		AggSalequotationHVO[] bills = null;
		try {
			bills = service.queryM4310App(pks);
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
		return null;
	}

	@Override
	public PageQueryVO query(IQueryScheme scheme) {
		ISalequotationMaintainApp service = NCLocator.getInstance().lookup(
				ISalequotationMaintainApp.class);
		PageQueryVO page = null;
		try {
			page = service.queryM4310App(scheme);
		} catch (BusinessException ex) {
			ExceptionUtils.wrappException(ex);
		}
		return page;
	}

}
