package nc.itf.so.m32;

import java.util.Map;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.et.fgjsdld.AggFgjsdldHeadVO;
import nc.vo.et.gcdljsd.AggGcdljsdHeadVO;
import nc.vo.it.ctjsd.AggCtjsdHeadVO;
import nc.vo.it.yljsd.AggYljsd;
import nc.vo.lm.jkyldlbzdzb1.AggJkyldlbzdzb1HeadVO;
import nc.vo.lm.yffyjsd.AggYffyjsdHeadVO;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.goldtax.GoldTaxVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.entity.SaleInvoiceViewVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售发票操作接口：
 * <li>销售发票查询
 * <li>销售发票新增保存
 * <li>销售发票修改保存
 * <li>销售发票批量删除
 * <li>销售发票批量审核
 * <li>销售发票批量 弃审
 * </ul>
 * 
 * @version 本版本号6.0
 * @author fengjb
 * @time 2010-1-19 上午09:31:26
 */
public interface ISaleInvoiceMaintain {

	/**
	 * 方法功能描述：销售发票批量审核功能。
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param appvos
	 *            待审核的销售发票聚合VO数组
	 * @return 审核后轻量级VO数组，包含主键、单据状态、时间戳等
	 * @throws BusinessException
	 *             <p>
	 * @author fengjb
	 * @time 2010-1-19 上午09:38:53
	 */
	SaleInvoiceVO[] approveSaleInvoice(SaleInvoiceVO[] appvos)
			throws BusinessException;

	/**
	 * 方法功能描述：销售发票批量删除功能。
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param delvos
	 *            待删除的轻量级发票VO数组
	 * @throws BusinessException
	 *             <p>
	 * @author fengjb
	 * @time 2010-1-19 上午09:37:16
	 */
	void deleteSaleInvoice(SaleInvoiceVO[] delvos) throws BusinessException;

	/**
	 * 执行传金税的VO交换
	 * 
	 * @param hids
	 *            销售发票hid
	 * @return 金税vo
	 * @throws BusinessException
	 */
	GoldTaxVO[] executeVOChangeTogtax(String[] hids) throws BusinessException;

	/**
	 * 根据物料内码查询物料ID
	 * 
	 * @param innercode
	 * @return key innercode value 为物料id
	 * 
	 * @throws BusinessException
	 */
	Map<String, String> getCmaterialids(String[] innercode)
			throws BusinessException;

	/**
	 * 根据物料查询内码
	 * 
	 * @param cmaterialids
	 * @return key 为物料id value innercode
	 * @throws BusinessException
	 */
	Map<String, String> getInnercodemaps(String[] cmaterialids)
			throws BusinessException;

	/**
	 * 方法功能描述：销售发票新增保存功能。
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param newvos
	 *            待新增保存的销售发票聚合VO
	 * @return 保存后的轻量级VO，包含主键、单据号、时间戳等
	 * @throws BusinessException
	 *             <p>
	 * @author fengjb
	 * @time 2010-1-19 上午09:34:38
	 */
	SaleInvoiceVO[] insertSaleInvoice(SaleInvoiceVO[] newvos)
			throws BusinessException;

	/**
	 * 方法功能描述：销售发票查询功能。
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param whereSql
	 *            查询使用的过滤条件
	 * @return 符合要求的发票聚合VO
	 * @throws BusinessException
	 *             <p>
	 * @author fengjb
	 * @time 2010-1-19 上午09:33:12
	 */
	SaleInvoiceVO[] querySaleInvoice(String whereSql) throws BusinessException;

	/**
	 * 销售发票提供给库存的转单查询
	 * 
	 * @param queryScheme
	 * @return 发票vo
	 * @throws BusinessException
	 */
	SaleInvoiceVO[] querySaleInvoiceFor4C(IQueryScheme queryScheme)
			throws BusinessException;

	/**
	 * 
	 * @param sql
	 * @return 销售发票试图vo
	 * @throws BusinessException
	 */
	SaleInvoiceViewVO[] queryViewvo(String sql) throws BusinessException;

	/**
	 * 方法功能描述：销售发票批量弃审功能。
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param unappvos
	 *            待弃审的销售发票聚合VO数组
	 * @return 弃审后轻量级VO数组，包含主键、单据状态、时间戳等
	 * @throws BusinessException
	 *             <p>
	 * @author fengjb
	 * @time 2010-1-19 上午09:47:30
	 */
	SaleInvoiceVO[] unapproveSaleInvoice(SaleInvoiceVO[] unappvos)
			throws BusinessException;

	/**
	 * 方法功能描述：导入金税票号时更新数据库销售发票金税票号。
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param voHeads
	 * @return 发票表头vo
	 * @throws BusinessException
	 *             <p>
	 * @author fengjb
	 * @time 2010-8-24 下午07:05:03
	 */
	SaleInvoiceHVO[] updateGoldTaxCode(SaleInvoiceHVO[] voHeads)
			throws BusinessException;

	/**
	 * 方法功能描述：销售发票修改保存功能。
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param updatevos
	 *            待修改保存的销售发票聚合VO
	 * @return 保存后的轻量级VO，包含主键、单据号、时间戳等
	 * @throws BusinessException
	 *             <p>
	 * @author fengjb
	 * @time 2010-1-19 上午09:35:59
	 */
	SaleInvoiceVO[] updateSaleInvoice(SaleInvoiceVO[] updatevos)
			throws BusinessException;

	/**
	 * 方法功能描述：销售发票传金税时更新后台数据库信息。
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param voHeads
	 * @return 发票表头vo
	 * @throws BusinessException
	 *             <p>
	 * @author fengjb
	 * @time 2010-8-24 下午03:13:22
	 */
	SaleInvoiceHVO[] updateWhenExportGoldTax(SaleInvoiceHVO[] voHeads)
			throws BusinessException;

	/**
	 * 方法功能描述：销售发票查询功能。
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param hids
	 *            根据主表ID查出VO
	 * @return 符合要求的发票聚合VO
	 * @throws BusinessException
	 *             <p>
	 * @author fengjb
	 * @time 2010-1-19 上午09:33:12
	 */
	SaleInvoiceVO[] querySaleInvoice(String[] hids) throws BusinessException;
	
	//原料销售结算单拉单
	public AggYljsd[] queryIT02ForM32(IQueryScheme queryScheme) throws BusinessException;
	//成套销售结算单拉单
	public AggCtjsdHeadVO[] queryIT01ForM32(IQueryScheme queryScheme) throws BusinessException;
	//钢材代理结算单
	public AggGcdljsdHeadVO[] queryET03ForM32(IQueryScheme queryScheme) throws BusinessException;
	//非钢代理结算单
	public AggFgjsdldHeadVO[] queryET02ForM32(IQueryScheme queryScheme) throws BusinessException;
	//进口原料报账单
	public AggJkyldlbzdzb1HeadVO[] queryLM40ForM32(IQueryScheme queryScheme) throws BusinessException;
	//应付费用结算单
	public AggYffyjsdHeadVO[] queryLM21ForM32(IQueryScheme queryScheme) throws BusinessException;
	
	void deteleysd(String pk);
	
	void updatexsfp(String pk);
}
