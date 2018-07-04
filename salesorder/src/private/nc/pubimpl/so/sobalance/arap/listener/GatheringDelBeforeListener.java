package nc.pubimpl.so.sobalance.arap.listener;

import java.util.HashMap;
import java.util.Map;

import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.bs.ml.NCLangResOnserver;
import nc.itf.arap.forso.IDataFromF2ForM30;
import nc.pubitf.arap.pub.GetSODataByArapUtils;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceViewVO;

/**
 * 收款单-删除前-订单收款核销监听
 * 
 * <p>注册：开发平台-开发配置工具-业务插件注册-应收管理-客户收款单-删除前</p>
 * <p>相应表：pub_eventlistener</p>
 * 
 * @since 6.0
 * @version 2011-3-31 下午04:37:49
 * @author 刘志伟
 */
public class GatheringDelBeforeListener implements IBusinessListener {

  /**
   * 已建立订单收款核销关系的收款单不允许删除
   */
  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
    // 1.解析收款单数据
    IDataFromF2ForM30[] datas =
        new GetSODataByArapUtils().getIDataFromF2ForM30(event);

    // 2.按不同来源销售订单ID组织数据(过滤掉源头不是销售订单的)
    Map<String, IDataFromF2ForM30> dataMap = this.organizeDataMap(datas);

    // 3.根据源头订单ID查询收款核销表中的关系VO
    String[] payBillRowIDs =
        dataMap.keySet().toArray(new String[dataMap.keySet().size()]);
    if (payBillRowIDs == null || payBillRowIDs.length == 0) {
      return;
    }
    SoBalanceViewVO[] views =
        ArListenerUtils.getInstance().querySoBalanceViewByGatheringBillBodyIDs(
            payBillRowIDs);

    // 4. 组织收款单行与销售订单关系：Map<收款单bid, 销售订单号>
    Map<String, SoBalanceViewVO> balanceMap = this.organizeBalanceMap(views);

    // 5.检查建立过收款核销关系的不允许删除
    this.checkDatas(datas, balanceMap);
  }

  private void checkDatas(IDataFromF2ForM30[] datas,
      Map<String, SoBalanceViewVO> balanceMap) throws BusinessException {
    StringBuffer errMsg = new StringBuffer();
    for (IDataFromF2ForM30 data : datas) {
      if (balanceMap.containsKey(data.getPayBillRowID())) {
        String payBillNo = data.getPayBillNo();
        String billRowID =
            balanceMap.get(data.getPayBillRowID()).getHead().getVbillcode();
        errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006011_0",
            "04006011-0363", null, new String[] {
              payBillNo, billRowID
            })/*收款单[{0}]与销售订单[{1}]，已建立订单收款核销关系,不允许删除！*/);
        errMsg.append("\n");
      }
    }
    if (errMsg.length() > 0) {
      throw new BusinessException(errMsg.toString());
    }
  }

  private Map<String, SoBalanceViewVO> organizeBalanceMap(
      SoBalanceViewVO[] views) {
    Map<String, SoBalanceViewVO> balanceMap =
        new HashMap<String, SoBalanceViewVO>();
    if (views != null && views.length > 0) {
      for (SoBalanceViewVO view : views) {
        SoBalanceBVO body = view.getBody();
        balanceMap.put(body.getCpaybillrowid(), view);
      }
    }
    return balanceMap;
  }

  private Map<String, IDataFromF2ForM30> organizeDataMap(
      IDataFromF2ForM30[] datas) {
    Map<String, IDataFromF2ForM30> retMap =
        new HashMap<String, IDataFromF2ForM30>();
    for (IDataFromF2ForM30 data : datas) {
      retMap.put(data.getPayBillRowID(), data);
    }
    return retMap;
  }
}
