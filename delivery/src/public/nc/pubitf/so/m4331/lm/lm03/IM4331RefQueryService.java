package nc.pubitf.so.m4331.lm.lm03;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * <p>本类主要实现功能：</p>
 *
 * <li>>查询上游单据 发货单维护 服务接口</li>
 * 
 * @author lyw
 * @version 6.5
 * @time 2017年3月1日 上午8:52:51
 */
public interface IM4331RefQueryService {
	public DeliveryVO[] queryM4331ForLM03(IQueryScheme queryScheme)
			throws BusinessException;

}
