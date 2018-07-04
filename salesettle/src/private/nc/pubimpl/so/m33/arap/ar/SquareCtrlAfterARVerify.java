package nc.pubimpl.so.m33.arap.ar;

import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.itf.arap.forso.IDataFromVerifyForM33;
import nc.pubitf.arap.pub.GetSODataByArapUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 销售结算监听财务核销接口实现类
 * 主要负责财务核销回写销售发票、销售出库待结算单的累计原币财务核销金额
 * 
 * @since 6.0
 * @version 2011-9-2 上午10:20:32
 * @author zhangcheng
 */
public class SquareCtrlAfterARVerify implements IBusinessListener {

  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
    IDataFromVerifyForM33[] datas =
        new GetSODataByArapUtils().getIDataFromVerifyForM33(event);
    // 非来源与销售订单的应收单和收款单核销过滤掉
    if (datas.length == 0) {
      return;
    }

    try {
      new SquareCtrlAfterARVerifyPubProcess().doAction(datas);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

  }

}
