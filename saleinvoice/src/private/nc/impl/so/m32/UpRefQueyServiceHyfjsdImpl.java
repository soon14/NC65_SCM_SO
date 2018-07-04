package nc.impl.so.m32;

import nc.itf.so.m32.IUpRefQueyServiceHyfjsd;
import nc.ui.querytemplate.querytree.FromWhereSQLImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.querytemplate.querytree.QueryScheme;
import nc.vo.lm.hyfjsd.AggSeasettHVO;
import nc.vo.lm.hyfjsd.SeasettHVO;
import nc.vo.lm.hyfjsd.SeasettbBVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.SchemeViewQuery;

import org.apache.commons.lang.ArrayUtils;

import nc.vo.pubapp.util.CombineViewToAggUtil;
import nc.vo.so.m32.entity.UpHyfjsdViewVO;

public class UpRefQueyServiceHyfjsdImpl implements IUpRefQueyServiceHyfjsd{

	@Override
	public AggSeasettHVO[] queryUpForDownHyfjsd(IQueryScheme queryScheme)
			throws BusinessException {
		String ordersql = this.createOrderSql(queryScheme);
		AggSeasettHVO[] bills = null;
		bills = this.queryDetailVOForSource(queryScheme, ordersql);
		return bills;
	}
	
	private AggSeasettHVO[] queryDetailVOForSource(IQueryScheme scheme, String order) {
	    SchemeViewQuery<UpHyfjsdViewVO> query =
	        new SchemeViewQuery<UpHyfjsdViewVO>(UpHyfjsdViewVO.class);
	    //==============================================================
	    QueryScheme tempQuery = (QueryScheme) scheme;
	    FromWhereSQLImpl tempquery = (FromWhereSQLImpl) tempQuery.getTableJoinFromWhereSQL();
		String whereStr = tempquery.getWhere();
		if (null == whereStr || "".equals(whereStr)){
			whereStr = " LM_SEASETT.dr = 0 and LM_SEASETT.billstatus = 1 and LM_SEASETT.brec_pay='2' and LM_SEASETT.is_next='2'";
		}else{
			whereStr = whereStr + " and LM_SEASETT.dr = 0 and LM_SEASETT.billstatus = 1 and LM_SEASETT.brec_pay='2' and LM_SEASETT.is_next='2'";
		}
		tempquery.setWhere(whereStr);  
	    //==============================================================
	    UpHyfjsdViewVO[] views = query.query(scheme, order);
	    if (ArrayUtils.isEmpty(views)) {
	      return null;
	    }
	    for (UpHyfjsdViewVO view : views) {
	      SeasettHVO headvo = (SeasettHVO) view.getVO(SeasettHVO.class);
	      SeasettbBVO bodyvo = (SeasettbBVO) view.getVO(SeasettbBVO.class);
	      headvo.setAttributeValue("pk_org", view.getAttributeValue("pk_org"));
	      bodyvo.setAttributeValue("pk_org", view.getAttributeValue("pk_org"));
	      headvo.setAttributeValue("pk_group", view.getAttributeValue("pk_group"));
	    }
	    AggSeasettHVO[] queryVos =
	        new CombineViewToAggUtil<AggSeasettHVO>(AggSeasettHVO.class, SeasettHVO.class,
	        		SeasettbBVO.class).combineViewToAgg(views, "pk_seasett");

	    return queryVos;
	  }
	
	private String createOrderSql(IQueryScheme queryScheme) {
	    // 根据单据号、行号排序  order by it_detail.vbillcode,it_detail_b.crowno
	    SqlBuilder order = new SqlBuilder();
//	    QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);
//	    order.append(" order by ");
//	    String tableName = processor.getMainTableAlias();
//	    order.append(tableName);
//	    order.append(".");
//	    order.append(DetailHVO.VBILLCODE);
//	    order.append(",");
//	    String bodytableName =
//	        processor.getTableAliasOfAttribute("pk_detail_b.pk_detail_b");
//	    order.append(bodytableName);
//	    order.append(".");
//	    order.append(DetailBVO.CROWNO);
	    return order.toString();

	  }


}
