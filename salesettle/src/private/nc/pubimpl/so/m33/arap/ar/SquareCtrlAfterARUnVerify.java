package nc.pubimpl.so.m33.arap.ar;

import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.itf.arap.forso.IDataFromVerifyForM33;
import nc.pubitf.arap.pub.GetSODataByArapUtils;
import nc.vo.arap.dataforso.DataFromVerifyForM33;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 销售结算监听财务反核销接口实现类
 * 主要负责财务反核销回写销售发票、销售出库待结算单的累计原币财务核销金额
 * 
 * @since 6.0
 * @version 2011-9-2 上午10:22:33
 * @author zhangcheng
 */
public class SquareCtrlAfterARUnVerify implements IBusinessListener {

  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
    IDataFromVerifyForM33[] datas =
        new GetSODataByArapUtils().getIDataFromVerifyForM33(event);

    // 非来源与销售订单的应收单和收款单核销过滤掉
    if (datas.length == 0) {
      return;
    }

    // 反核销金额接口中的正负号与核销时相同，所以这里取反
    DataFromVerifyForM33[] unVerifyDatas =
        new DataFromVerifyForM33[datas.length];
    int i = 0;
    for (IDataFromVerifyForM33 idata : datas) {
      unVerifyDatas[i] = new DataFromVerifyForM33();
      unVerifyDatas[i].setSrcID(idata.getSrcID());
      unVerifyDatas[i].setSrcBID(idata.getSrcBID());
      unVerifyDatas[i].setSrcBillType(idata.getSrcBillType());
      unVerifyDatas[i].setPaybillmny(MathTool.oppose(idata.getPayBillmny()));
      i++;
    }

    try {
      new SquareCtrlAfterARVerifyPubProcess().doAction(unVerifyDatas);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

}
