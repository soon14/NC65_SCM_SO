package nc.pubimpl.so.m30.api;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.pubimpl.so.m30.api.check.SaleOrderValidator;
import nc.pubimpl.so.m30.api.fill.SaleOrderSaveDefValue;
import nc.pubitf.so.m30.api.ISaleOrderMaintainAPI;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.check.billvalidate.BillVOsCheckRule;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.util.AggVOUtil;

/**
 * @description
 * 销售订单持久化服务实现1
 * @scene
 * 
 * @param
 * 无
 *
 *
 * @since 6.5
 * @version 2015-10-20 下午1:52:34
 * @author 刘景
 */
public class SaleOrderMaintainAPIImpl implements ISaleOrderMaintainAPI {

  @Override
  public SaleOrderVO[] insertBills(SaleOrderVO[] vos) throws BusinessException {
    SaleOrderVO[] fillvos = vos;

    // 1、基本空校验
    BillVOsCheckRule checker =
        new BillVOsCheckRule(new SaleOrderValidator());
    checker.check(fillvos);

    // 2、强制默认值填充
    SaleOrderSaveDefValue filldatarule = new SaleOrderSaveDefValue();
    filldatarule.setDefultValue(fillvos);

    // 3、有值不覆盖
    SaleOrderVO[] combinBillVOs =
        (SaleOrderVO[]) AggVOUtil.combinBillVO(fillvos, vos);

    // 3、保存
    SaleOrderVO[] retvos =
        (SaleOrderVO[]) PfServiceScmUtil.processBatch(SOConstant.WRITE,
            SOBillType.Order.getCode(), combinBillVOs, null, null);
    return retvos;
  }

  @Override
  public void deleteBillsByID(String[] ids) throws BusinessException {
    BillQuery<SaleOrderVO> query = new BillQuery<>(SaleOrderVO.class);
    SaleOrderVO[] deletevos = query.query(ids);
    PfServiceScmUtil.processBatch(SOConstant.DELETE,
        SOBillType.Order.getCode(), deletevos, null, null);
  }

}
