package nc.itf.so.m30.self;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 销售订单操作 用于业务日志配置的接口
 * <p>
 * <b>本类主要完成以下功能：</b>
 * 
 * <ul>
 * <li>新增、修改、删除操作。
 * </ul>
 * 
 * <p>
 * 
 * @author buxh
 * 
 */

public interface ISaleOrderScriptMaintain {

  /**
   * 方法功能描述：销售订单修改保存功能。
   */
  SaleOrderVO[] saleOrderUpdate(SaleOrderVO[] vos, PfUserObject userObj,
      SaleOrderVO[] originBills) throws BusinessException;

  /**
   * 方法功能描述：销售订单新增保存功能。
   */
  SaleOrderVO[] saleOrderInsert(SaleOrderVO[] vos, PfUserObject userObj)
      throws BusinessException;

  /**
   * 方法功能描述：销售订单单删除功能。
   */
  SaleOrderVO[] saleOrderDelete(SaleOrderVO[] vos, PfUserObject userObj)
      throws BusinessException;

}
