package nc.impl.so.m4331.action.maintain.rule.approve;

import java.util.ArrayList;
import java.util.List;
import nc.bs.busilog.util.BusinessLogServiceUtil;
import nc.bs.busilog.vo.BusinessLogContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.md.MDQueryFacade;
import nc.md.model.IBean;
import nc.vo.pub.BusinessException;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m4331.entity.DeliveryVO;

public class BusiLog implements IRule<DeliveryVO>{

	@Override
	public void process(DeliveryVO[] vos) {
		List<BusinessLogContext> list = new ArrayList<BusinessLogContext>();
	    IVOMeta meta = vos[0].getParentVO().getMetaData();
	    String beanname = meta.getEntityName();
	    IBean bean =  MDQueryFacade.getBeanByFullName(beanname);
	    for (DeliveryVO deliveryVO : vos) {
	      BusinessLogContext context = new BusinessLogContext();
	      context.setPk_busiobj(deliveryVO.getPrimaryKey());
	      context.setBusiobjcode(deliveryVO.getParentVO().getVbillcode());
	      //context.setBusiobjname(aggCtSaleVO.getParentVO()..getCtname());
	      context.setTypepk_busiobj(bean.getID());
	      context.setPk_operation("8bcd7cb4-ab98-4d80-9e90-26cadde16a58");
	      context.setHasjudged(true);
	      context.setOldbusiobjvo(deliveryVO);
	      context.setOrgpk_busiobj(vos[0].getParentVO().getPk_org());
	      list.add(context);
	    }
	    try {
	      BusinessLogServiceUtil.insertBatchBusilogInTransaction(list);
	    }
	    catch (BusinessException e) {
	      ExceptionUtils.wrappException(e);
	    }

		
	}

}
