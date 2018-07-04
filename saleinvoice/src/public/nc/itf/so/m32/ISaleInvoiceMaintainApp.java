package nc.itf.so.m32;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.page.PageQueryVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * 销售发票分页查询服务接口
 * 
 * @since 6.36
 * @author zhangby5
 * @version 2015-4-10
 */
public interface ISaleInvoiceMaintainApp {
	/**
	 * 销售发票分页通过QueryScheme查询
	 * 
	 * @param scheme
	 *            UI端组织的查询方案
	 * @return 按照单据号进行排序的单据分页。懒加载形式，只有第一页的第一张单据 才有表体数据。没有查询到数据时返回零长度的数组
	 * @throws BusinessException
	 */
	PageQueryVO queryM32App(IQueryScheme scheme, Boolean isLazyLoad)
			throws BusinessException;

	/**
	 * 销售发票分页通过IDs查询
	 * 
	 * @param ids
	 *            单据主键数组
	 * @return 懒加载形式，只有第一张单据才有表体数据。
	 * @throws BusinessException
	 */
	SaleInvoiceVO[] queryM32App(String[] ids, Boolean isLazyLoad)
			throws BusinessException;
	
	public boolean searchDownData(String pk) throws BusinessException;
}
