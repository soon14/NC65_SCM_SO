package nc.pubitf.so.m32.ic.mnreport;

import nc.vo.ic.icreport.param.mncommon.MNReportQueryParam;
import nc.vo.ic.icreport.param.mncommon.MNRptTempTableWrapperParam;
import nc.vo.pub.BusinessException;

/**
 * 销售发票为国际化报表取数
 *
 * @since 6.1
 * @version 2012-02-21 下午15:00:56
 * @author 王天文
 *
 */
public interface ISaleInvoiceQueryForMN {

	/**
	 * 从销售发票取数，插入到国际报表的临时表中
	 *
	 * @param reportQueryParam 查询参数
	 * @param rptTableWrapper 临时表对象
	 * @throws BusinessException
	 */
	public MNRptTempTableWrapperParam querySendAndInvoice(
			MNReportQueryParam reportQueryParam)
	throws BusinessException;

}
