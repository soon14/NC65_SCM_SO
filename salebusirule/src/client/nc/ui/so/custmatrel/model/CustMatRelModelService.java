package nc.ui.so.custmatrel.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.custmatrel.ICustMatRelMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.custmatrel.entity.CustMatRelVO;
import nc.vo.uif2.LoginContext;

public class CustMatRelModelService implements IAppModelService, IQueryService {

	/**
	 * 删除
	 */
	@Override
	public void delete(Object object) {
		ICustMatRelMaintain service = NCLocator.getInstance().lookup(
				ICustMatRelMaintain.class);
		try {
			service.delete((CustMatRelVO) object);
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
	}

	/**
	 * 新增
	 */
	@Override
	public CustMatRelVO insert(Object object) {
		ICustMatRelMaintain service = NCLocator.getInstance().lookup(
				ICustMatRelMaintain.class);
		CustMatRelVO retvo = null;
		try {
			retvo = service.insert((CustMatRelVO) object);
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
		return retvo;
	}

	/**
	 * 修改保存
	 */
	@Override
	public CustMatRelVO update(Object object) {
		ICustMatRelMaintain service = NCLocator.getInstance().lookup(
				ICustMatRelMaintain.class);
		CustMatRelVO retvo = null;
		try {
			retvo = service.update((CustMatRelVO) object);
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
		return retvo;
	}

	@Override
	public CustMatRelVO[] queryByDataVisibilitySetting(LoginContext context) {
		CustMatRelVO[] rets = null;
		ICustMatRelMaintain service = NCLocator.getInstance().lookup(
				ICustMatRelMaintain.class);
		try {
			CustMatRelVO vo = service.queryByOrg(context.getPk_org());
			if (vo != null) {
				rets = new CustMatRelVO[] { vo };
			}
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
		return rets;
	}

	/**
	 * 根据where条件查询
	 * 
	 * @param whereSql
	 * @return
	 * @throws Exception
	 */
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		CustMatRelVO[] retbills = null;
		ICustMatRelMaintain service = NCLocator.getInstance().lookup(
				ICustMatRelMaintain.class);
		try {
			retbills = service.queryCustMatRel(queryScheme);
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
		return retbills;
	}

}
