package nc.itf.so.m38;

import nc.vo.pub.BusinessException;

/**
 * 销售预订单迁移至电子销售中心预订单
 * @author liylr
 */
public interface IPreOrderMigrate {
	/**
	 * 预订单迁移
	 * @throws BusinessException
	 */
	public void migratePreOrder() throws BusinessException;
}
