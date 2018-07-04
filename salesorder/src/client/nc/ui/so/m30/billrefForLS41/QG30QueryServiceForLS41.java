package nc.ui.so.m30.billrefForLS41;

import nc.bs.framework.common.NCLocator;
import nc.ui.pubapp.uif2app.query2.model.IRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.lm.lsdlxy.AggLsxywtHVO;
import nc.vo.pp.m28.entity.PriceAuditVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.itf.so.m30.billrefFor28.IM28RefQueryService;

/*上游单据查询服务*/

public class QG30QueryServiceForLS41 implements IRefQueryService {

	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		AggLsxywtHVO[] retbills = null;
		IM28RefQueryService service = NCLocator.getInstance().lookup(
				IM28RefQueryService.class);
		try {
			retbills = service.queryLS41For30(queryScheme);
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
		return retbills;
	}

	// IRefQueryService不集成IQueryService时可以删除
	@Override
	public Object[] queryByWhereSql(String whereSql) throws Exception {
		return null;
	}
}