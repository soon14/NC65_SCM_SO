package nc.pubimpl.so.m4331.lm.lm03;

import org.apache.commons.lang.ArrayUtils;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.impl.pubapp.pattern.data.view.SchemeViewQuery;
import nc.md.data.access.NCObject;
import nc.md.persist.framework.MDPersistenceService;
import nc.pubitf.so.m4331.lm.lm03.IM4331RefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.pubapp.util.CombineViewToAggUtil;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.m4331.entity.DeliveryViewVO;

/**
 * <p>本类主要实现功能：</p>
 *
 * <li>功能 描述</li>
 * 
 * @author lyw
 * @version 6.5
 * @time 2017年3月1日 下午8:53:00
 */
public class M4331RefQueryServiceImpl implements IM4331RefQueryService {

	/* （非 Javadoc）
	 * @see nc.pubitf.so.m4331.lm.lm03.IM4331RefQueryService#queryM4331ForLM03(nc.ui.querytemplate.querytree.IQueryScheme)
	 */
	@Override
	public DeliveryVO[] queryM4331ForLM03(IQueryScheme queryScheme)
			throws BusinessException {
		// TODO 自动生成的方法存根
		//过滤掉非审批通过的信息
		//只有审批通过后的数据可以拉单，
		//同时需要滤掉已经完成的数量 有审批通过后可以拉单，同时需要滤掉已经完成的数量 =需要出 库的数量单据
		String sql = queryScheme.getTableJoinFromWhereSQL().getWhere();//.getWhereSQLOnly();
		sql = sql + " and fstatusflag = 2 and dr = 0 ";

		NCObject[] obs = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond(DeliveryHVO.class, sql, false);
		if(obs == null) return null;
		DeliveryVO[] bills = new DeliveryVO[obs.length];
		for(int i= 0; i<obs.length;i++){
			bills[i] = (DeliveryVO)obs[i].getContainmentObject();
		}
		
		return bills;

		
/*		QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);
		String maintablename = processor.getMainTableAlias();
		SqlBuilder sqlbuild = new SqlBuilder();
		//拼接whereSql必要条件
		sqlbuild.append(" and ");
		String pk_group = InvocationInfoProxy.getInstance().getGroupId();
		sqlbuild.append(maintablename + ".pk_group",pk_group);
		//上游单据发货单-单据状态为审批通过
		sqlbuild.append(" and ");
		sqlbuild.append(maintablename + ".fstatusflag",2);
*/		
		//String chidtable = processor.getTableAliasOfAttribute("et_ckspzbb.cmaterialvid");
		//使用过的数量
		//sqlbuild.append(" and ");
		//cktzdnum(暂定),上游单据 出库审批单元数据暂未设计
		//sqlbuild.append(" abs(isnull(" + chidtable +".outnum,0)) > abs(isnull(" + chidtable +".cktzdnum,0))");
		//processor.appendWhere(sqlbuild.toString());
		//processor.appendRefTrantypeWhere("4331", "LM03", "tran_code");
		//拼接排序sql
/*		String ordersql = this.createOrderSql(queryScheme);
		SchemeViewQuery<DeliveryViewVO> query =
				new SchemeViewQuery<DeliveryViewVO>(DeliveryViewVO.class);
		nc.vo.so.m4331.entity.DeliveryViewVO[] views = query.query(queryScheme, ordersql);
		if (ArrayUtils.isEmpty(views)) {
			return null;
		}
		for (DeliveryViewVO view : views) {
			DeliveryHVO headvo = view.getHead();
			DeliveryBVO bodyvo = view.getItem();
			String pk_org = (String) bodyvo.getAttributeValue("pk_org");
			String pk_org_v = (String) bodyvo.getAttributeValue("pk_org_v");
			headvo.setAttributeValue("pk_org", pk_org);
			headvo.setAttributeValue("pk_org_v", pk_org_v);
		}
		
		DeliveryVO[] queryVos = 
				new CombineViewToAggUtil<DeliveryVO>(
						DeliveryVO.class,DeliveryHVO.class,DeliveryBVO.class)
						.combineViewToAgg(views, "cdeliveryid");
		return queryVos;
*/	}
	
	/**
	 * 拼接排序sql默认方法（单据号，行号排序）
	 * @param queryScheme
	 * @return
	 */
	private String createOrderSql(IQueryScheme queryScheme) {
		// TODO 自动生成的方法存根
		//根据单据号、行号排序
		SqlBuilder order = new SqlBuilder();
		QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);
		order.append(" order by ");
		String tableName = processor.getTableAliasOfAttribute(DeliveryHVO.class,"vbillcode");
		order.append(tableName);
		order.append(".");
		order.append("vbillcode");
		order.append(",");
		tableName = processor.getTableAliasOfAttribute(DeliveryBVO.class,"crowno");
		order.append(tableName);
		order.append(".");
		order.append("crowno");
		return order.toString();
	}

}
