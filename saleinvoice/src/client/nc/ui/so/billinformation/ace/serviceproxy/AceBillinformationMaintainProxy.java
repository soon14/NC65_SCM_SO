package nc.ui.so.billinformation.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.IBillinformationMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceBillinformationMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IBillinformationMaintain query = NCLocator.getInstance().lookup(
				IBillinformationMaintain.class);
		return query.query(queryScheme);
	}

}