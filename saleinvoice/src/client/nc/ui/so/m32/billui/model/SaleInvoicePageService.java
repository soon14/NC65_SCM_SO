package nc.ui.so.m32.billui.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m32.ISaleInvoiceMaintainApp;
import nc.ui.pubapp.uif2app.actions.pagination.IPaginationInitQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.scmpub.page.model.IBillPageQuery;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pagination.PaginationQueryVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.page.PageQueryVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.paravo.CombinCacheVO;

public class SaleInvoicePageService implements IBillPageQuery,
		IPaginationInitQuery {

	/***
	 * œ˙ €∑¢∆±Model
	 */
	private SaleInvoiceManageModel model;

	@Override
	public Object[] queryObjectByPks(String[] pks) throws BusinessException {
		ISaleInvoiceMaintainApp service = NCLocator.getInstance().lookup(
				ISaleInvoiceMaintainApp.class);
		SaleInvoiceVO[] bills = null;
		try {
			CombinCacheVO cachevo = this.getModel().getCombinCacheVO();
			bills = service.queryM32App(pks, !cachevo.getBcombinflag());
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
		ISaleInvoiceMaintainApp service = NCLocator.getInstance().lookup(
				ISaleInvoiceMaintainApp.class);
		PageQueryVO page = null;
		try {
			CombinCacheVO cachevo = this.getModel().getCombinCacheVO();
			page = service.queryM32App(scheme, !cachevo.getBcombinflag());
		} catch (BusinessException ex) {
			ExceptionUtils.wrappException(ex);
		}
		return page;
	}

	public SaleInvoiceManageModel getModel() {
		return model;
	}

	public void setModel(SaleInvoiceManageModel model) {
		this.model = model;
	}
}
