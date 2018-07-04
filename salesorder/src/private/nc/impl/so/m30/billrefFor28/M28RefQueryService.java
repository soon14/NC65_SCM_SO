package nc.impl.so.m30.billrefFor28;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.so.m30.billrefFor28.IM28RefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.it.m5801.entity.ContractVO;
import nc.vo.lm.lsdlxy.AggLsxywtHVO;
import nc.vo.pp.m28.entity.PriceAuditVO;
import nc.vo.pp.m28.entity.PriceAuditViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.bill.CombineBill;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * @author wangzym
 * @version 2017年6月6日 上午9:42:36
 */
public class M28RefQueryService implements IM28RefQueryService {

	/*
	 * （非 Javadoc）
	 * 
	 * @see nc.itf.so.m30.billrefFor28.IM28RefQueryService#query28For30(nc.ui.
	 * querytemplate.querytree.IQueryScheme)
	 */
	@Override
	public PriceAuditVO[] query28For30(IQueryScheme queryScheme)
			throws BusinessException {
		String sqlStr = queryScheme.getTableJoinFromWhereSQL().getWhere();
		PriceAuditVO[] b = this.queryDetailByViewQuery(sqlStr);

		// TODO 自动生成的方法存根
		return b;
	}

	/**
	 * 根据Sql查询价格审批单(使用viewQuery可过滤到行)
	 * 
	 * @param sqlStr
	 *            将scheme中的where条件拿到整合成完整sql语句
	 * @return PriceAuditVO[]
	 * @throws BusinessException
	 * @author wangzym
	 */
	public PriceAuditVO[] queryDetailByViewQuery(String sqlStr)
			throws BusinessException {
		PriceAuditVO[] rets = null;
		String getSql = this.getSql();
		if (sqlStr.equals("") || sqlStr == null) {
			getSql = getSql + sqlStr;
		} else {
			getSql = getSql + " and " + sqlStr;
		}

		try {
			DataAccessUtils utils = new DataAccessUtils();
			IRowSet set = utils.query(getSql);
			if (set.size() == 0) {
				return new PriceAuditVO[0];
			}
			String[] ids = set.toOneDimensionStringArray();
			ViewQuery<PriceAuditViewVO> query = new ViewQuery<PriceAuditViewVO>(
					PriceAuditViewVO.class);
			PriceAuditViewVO[] views = query.query(ids);

			if (null != views && views.length > 0) {
				int len = views.length;
				PriceAuditVO[] bills = new PriceAuditVO[len];
				for (int i = 0; i < len; i++) {
					bills[i] = views[i].changeToBill();
				}
				CombineBill<PriceAuditVO> combine = new CombineBill<PriceAuditVO>();
				IVOMeta headMeta = bills[0].getMetaData().getParent();
				String headItemKey = headMeta.getPrimaryAttribute().getName();
				combine.appendKey(headItemKey);
				rets = combine.combine(bills);
			}
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return rets;
	}

	private String getSql() {
		// TODO 自动生成的方法存根
		SqlBuilder sqlb = new SqlBuilder();

		sqlb.append("select purp_priceaudit_b.pk_priceaudit_b from purp_priceaudit inner join purp_priceaudit_b on purp_priceaudit.pk_priceaudit=purp_priceaudit_b.pk_priceaudit where purp_priceaudit.fbillstatus='3' and nvl(purp_priceaudit_b.hasnordastnum,0)<nvl(purp_priceaudit_b.nordastnum,0)");
		// sqlb.append("where 条件");
		String sql = sqlb.toString();
		return sql;
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see
	 * nc.itf.so.m30.billrefFor28.IM28RefQueryService#queryForSaleOrderDel(java
	 * .lang.String)
	 */
	@Override
	public HashMap<String, Integer> queryForSaleOrderDel(String pk_Head[])
			throws BusinessException {
		// TODO 自动生成的方法存根
		Map<String, Integer> map = new HashMap<String, Integer>();

		for (int i = 0; i < pk_Head.length; i++) {
			String pk = pk_Head[i];
			String sql = "select count(*) from it_contract_b  where it_contract_b.csrcid='"
					+ pk + "'";
			int count = 0;
			try {
				DataAccessUtils utils = new DataAccessUtils();
				IRowSet set = utils.query(sql);

				String[] ids = set.toOneDimensionStringArray();
				count = Integer.parseInt(ids[0]);

			} catch (Exception e) {
				ExceptionUtils.marsh(e);
			}
			map.put(pk, count);

		}

		return (HashMap<String, Integer>) map;

	}

	/*
	 * (non Javadoc)
	 * 
	 * @Title: queryLS41For30
	 * 
	 * @Description: TODO
	 * 
	 * @param queryScheme
	 * 
	 * @return
	 * 
	 * @throws BusinessException
	 * 
	 * @see nc.itf.so.m30.billrefFor28.IM28RefQueryService#queryLS41For30(nc.ui.
	 * querytemplate.querytree.IQueryScheme)
	 */
	@Override
	public AggLsxywtHVO[] queryLS41For30(IQueryScheme queryScheme)
			throws BusinessException {
		// TODO 自动生成的方法存根
		String sql = "";
		String whereSql = queryScheme.getTableJoinFromWhereSQL().getWhere();
		if (whereSql != null) {
			sql = buildSqlForLS41(whereSql);
		}
		AggLsxywtHVO[] Hvos = queryLS41ByViewQuery(sql);

		return Hvos;
	}

	/**
	 * @Title: buildSqlForLS41
	 * @Description: TODO
	 * @param whereSql
	 * @return
	 * @return: String
	 */
	private String buildSqlForLS41(String whereSql) {
		// TODO 自动生成的方法存根
		//and lm_lsdlxy.fstatusflag='1'注掉，不考虑审批状态来进行拉单
		String baseSql = "select lm_lsdlxyb.pk_lsdlxy_b from lm_lsdlxy lm_lsdlxy inner join lm_lsdlxyb on lm_lsdlxy.pk_lsdlxy=lm_lsdlxyb.pk_lsdlxy where lm_lsdlxy.dr<>'1' and lm_lsdlxyb.dr<>'1' and (lm_lsdlxyb.bdef1='0' or lm_lsdlxyb.bdef1='~') ";
		if (whereSql == null) {
			return baseSql;
		} else {
			return baseSql + " and " + whereSql;
		}
	}

	/**
	 * 根据Sql查询价格审批单(使用viewQuery可过滤到行)
	 * 
	 * @param sqlStr
	 *            将scheme中的where条件拿到整合成完整sql语句
	 * @return PriceAuditVO[]
	 * @throws BusinessException
	 * @author wangzym
	 */
	public AggLsxywtHVO[] queryLS41ByViewQuery(String sqlStr)
			throws BusinessException {
		AggLsxywtHVO[] rets = null;

		try {
			DataAccessUtils utils = new DataAccessUtils();
			IRowSet set = utils.query(sqlStr);
			if (set.size() == 0) {
				return new AggLsxywtHVO[0];
			}
			String[] ids = set.toOneDimensionStringArray();
			ViewQuery<nc.vo.lm.lsdlxy.LsxywtViewVO> query = new ViewQuery<nc.vo.lm.lsdlxy.LsxywtViewVO>(
					nc.vo.lm.lsdlxy.LsxywtViewVO.class);
			nc.vo.lm.lsdlxy.LsxywtViewVO[] views = query.query(ids);

			if (null != views && views.length > 0) {
				int len = views.length;
				AggLsxywtHVO[] bills = new AggLsxywtHVO[len];
				for (int i = 0; i < len; i++) {
					bills[i] = views[i].changeToBill();
				}
				CombineBill<AggLsxywtHVO> combine = new CombineBill<AggLsxywtHVO>();
				IVOMeta headMeta = bills[0].getMetaData().getParent();
				String headItemKey = headMeta.getPrimaryAttribute().getName();
				combine.appendKey(headItemKey);
				rets = combine.combine(bills);
			}
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return rets;
	}

}
