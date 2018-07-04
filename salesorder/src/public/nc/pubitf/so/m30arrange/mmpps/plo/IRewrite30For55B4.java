package nc.pubitf.so.m30arrange.mmpps.plo;

import nc.pubitf.so.m30arrange.Rewrite30ArrangePara;
import nc.vo.pub.BusinessException;

/**
 * 计划订单回写销售订单服务接口
 * 
 * @since 6.1
 * @version 2011-12-08 14:03
 * @author 王天文
 *
 */

public interface IRewrite30For55B4 {
	
	/**
	 * 回写订单累计生成计划订单主数量
	 * 
	 * @param paras 计划订单回写销售订单的回写参数类
	 * @throws BusinessException
	 */
	void rewrite30npolnumFor55B4(Rewrite30ArrangePara[] paras) throws BusinessException;
	
}

