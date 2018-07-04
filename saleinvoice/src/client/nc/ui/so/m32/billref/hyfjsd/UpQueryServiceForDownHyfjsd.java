package nc.ui.so.m32.billref.hyfjsd;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m32.IUpRefQueyServiceHyfjsd;
import nc.ui.pubapp.uif2app.query2.model.IRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.lm.hyfjsd.AggSeasettHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class UpQueryServiceForDownHyfjsd implements IRefQueryService{
	
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		AggSeasettHVO[] retbills = null;
		IUpRefQueyServiceHyfjsd service = NCLocator.getInstance().lookup(
				IUpRefQueyServiceHyfjsd.class);
		try {
			retbills = service.queryUpForDownHyfjsd(queryScheme);
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