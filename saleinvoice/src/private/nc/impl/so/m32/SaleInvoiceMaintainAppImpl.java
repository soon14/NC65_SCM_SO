package nc.impl.so.m32;

import java.sql.ResultSet;
import java.sql.SQLException;

import nc.bs.dao.BaseDAO;
import nc.bs.scmpub.page.ApproveBillFilter;
import nc.bs.scmpub.page.BillPageLazyQuery;
import nc.bs.scmpub.page.IBillFilter;
import nc.bs.scmpub.tool.SchemeAppendCondition;
import nc.bs.scmpub.tool.SchemeFixCondition;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.vo.SchemeVOQuery;
import nc.itf.so.m32.ISaleInvoiceMaintainApp;
import nc.jdbc.framework.SQLParameter;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.page.PageQueryVO;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.query.SOQueryApproveUtil;

/**
 * 销售发票分页查询服务实现1
 * 
 * @since 6.36
 * @author zhangby5
 * @version 2015-4-11
 */
public class SaleInvoiceMaintainAppImpl implements ISaleInvoiceMaintainApp {

  @Override
  public PageQueryVO queryM32App(IQueryScheme scheme, Boolean isLazyLoad)
      throws BusinessException {
    SchemeAppendCondition condition = new SchemeAppendCondition(scheme);
    SchemeFixCondition tool = new SchemeFixCondition(scheme);
    boolean flag = tool.getBoolean(SOQueryApproveUtil.BISAPPROVING);
    IBillFilter filter = null;
    if (flag) {
      // 过滤审批通过的单据
      condition.appendHeadNot(SaleInvoiceHVO.FSTATUSFLAG, BillStatus.I_AUDIT);
      condition.appendHeadNot(SaleInvoiceHVO.FSTATUSFLAG, BillStatus.I_CLOSED);
      // 增加待审批单据的过滤
      filter = new ApproveBillFilter(SaleInvoiceVO.class, SOBillType.Invoice);
    }
    // 增加集团和主组织条件
    condition.appendPermission();
    // 是否进行懒加载
    if (isLazyLoad) {
      BillPageLazyQuery<SaleInvoiceVO> query =
          new BillPageLazyQuery<SaleInvoiceVO>(SaleInvoiceVO.class, filter);
      PageQueryVO page = null;
      try {
        query.addHeadOrder(SaleInvoiceHVO.VBILLCODE);
        page = query.query(scheme);
      }
      catch (Exception ex) {
        ExceptionUtils.marsh(ex);
      }
      return page;
    }
    else {
      return this.queryM32PageVO(scheme, filter);
    }
  }

  private PageQueryVO queryM32PageVO(IQueryScheme scheme, IBillFilter filter) {
    SchemeVOQuery<SaleInvoiceHVO> query =
        new SchemeVOQuery<SaleInvoiceHVO>(SaleInvoiceHVO.class, new String[] {
          SaleInvoiceHVO.CSALEINVOICEID, SaleInvoiceHVO.VBILLCODE
        });
    // 排序
    QuerySchemeProcessor processor = new QuerySchemeProcessor(scheme);
    String headTableName = processor.getMainTableAlias();
    SqlBuilder order = new SqlBuilder();
    order.append("order by ");
    order.append(headTableName);
    order.append(".vbillcode");
    query.setUsedistinct(true);
    ISuperVO[] parents = query.query(scheme, order.toString());
    int length = parents.length;
    if (length == 0) {
      return new PageQueryVO(new String[0], Constructor.construct(
          SaleInvoiceVO.class, 0));
    }

    String[] pks = new String[length];
    for (int i = 0; i < length; i++) {
      pks[i] = parents[i].getPrimaryKey();
    }
    // 根据一定的业务规则过滤单据主键
    if (filter != null && pks.length > 0) {
      pks = filter.filter(pks);
    }

    if (pks == null || pks.length == 0) {
      return new PageQueryVO(new String[0], Constructor.construct(
          SaleInvoiceVO.class, 0));
    }
    int recordInPage =
        ValueUtils.getInt(scheme.get(PageQueryVO.RECORD_IN_PAGE), -1);
    if (recordInPage != -1) {
      length = Math.min(recordInPage, pks.length);
    }
    else {
      length = pks.length;
    }

    String[] idsInFirstPage = new String[length];
    for (int i = 0; i < length; i++) {
      idsInFirstPage[i] = pks[i];
    }
    BillQuery<SaleInvoiceVO> billQuery =
        new BillQuery<SaleInvoiceVO>(SaleInvoiceVO.class);
    SaleInvoiceVO[] bills = billQuery.query(idsInFirstPage);
    PageQueryVO page = new PageQueryVO(pks, bills);
    return page;
  }

  @Override
  public SaleInvoiceVO[] queryM32App(String[] ids, Boolean isLazyLoad)
      throws BusinessException {
    // 是否进行懒加载
    if (isLazyLoad) {
      BillPageLazyQuery<SaleInvoiceVO> query =
          new BillPageLazyQuery<SaleInvoiceVO>(SaleInvoiceVO.class);
      return query.queryPageBills(ids);
    }
    else {
      BillQuery<SaleInvoiceVO> billQuery =
          new BillQuery<SaleInvoiceVO>(SaleInvoiceVO.class);
      return billQuery.query(ids);

    }
  }
  
  @Override
	public boolean searchDownData(String pk) throws BusinessException {
	  BaseDAO baseDao = new BaseDAO();
		String sqlStr = " SELECT count(pk_yljkdlbzd) FROM lm_jkyldlbzdzb1  "
				+ "  WHERE dr = 0 and  pk_yljkdlbzd=?  ";
		SQLParameter sp = new SQLParameter();
		sp.addParam(pk);
		int count = 0;
		try {
			count = (Integer) baseDao.executeQuery(sqlStr, sp,
					new ResultSetProcessor() {
						@Override
						public Integer handleResultSet(ResultSet rs)
								throws SQLException {
							rs.next();
							return rs.getInt(1);
						}
					});
			if(count>0) return false; else return true;
		} catch (Exception e) {
			return false;
			//ExceptionUtils.wrappBusinessException("过滤参照数据时没有找到主键，请联系系统管理员!");
		}
	}

}
