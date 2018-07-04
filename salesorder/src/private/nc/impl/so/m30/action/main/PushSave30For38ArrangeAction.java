package nc.impl.so.m30.action.main;

import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.impl.pubapp.env.BSContext;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pubapp.bill.pf.BillSplitUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.pubapp.pflow.SplitParameter;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.SOParameterVO;

public class PushSave30For38ArrangeAction {

  public SaleOrderVO[] pushSave(SOParameterVO paravo) {
    TimeLog.logStart();

    TimeLog.info("订单保存检查标识设置"); /*-=notranslate=-*/
    TimeLog.logStart();
    // 分单合单
    SaleOrderVO[] vos = this.billSplitCombine((SaleOrderVO[]) paravo.getVos());
    TimeLog.info("分单合单"); /*-=notranslate=-*/
    TimeLog.logStart();

    // 处理整单折扣
    for (SaleOrderVO vo : vos) {
      SaleOrderBVO[] bvos = vo.getChildrenVO();
      vo.getParentVO().setNdiscountrate(bvos[0].getNdiscountrate());
    }

    PfUserObject[] userobjs = new PfUserObject[vos.length];
    for (int i = 0, iloop = vos.length; i < iloop; i++) {
      userobjs[i] = new PfUserObject();
      userobjs[i].setBusinessCheckMap(paravo.getBusinessCheckMap());
    }
    // 调用销售订单保存脚本
    return (SaleOrderVO[]) PfServiceScmUtil.processBatch("WRITE",
        SOBillType.Order.getCode(), vos, userobjs, null);
  }

  /**
   * 安排处理需要在保存前进行分单合单，根据vo对照上的分单条件进行分单合单
   * 
   * @param bill
   * @return
   */
  private SaleOrderVO[] billSplitCombine(SaleOrderVO[] bill) {
    // 转化view
    BillToViewConvertor<SaleOrderVO, SaleOrderViewVO> convertor =
        new BillToViewConvertor<SaleOrderVO, SaleOrderViewVO>(
            SaleOrderViewVO.class);
    SaleOrderViewVO[] retViews = convertor.convert(bill);
    int len = retViews.length;
    SplitParameter[] splitParameters = new SplitParameter[len];
    for (int i = 0; i < len; i++) {
      SaleOrderHVO head = retViews[i].getHead();
      SaleOrderBVO body = retViews[i].getBody();
      splitParameters[i] = new SplitParameter();
      SaleOrderVO svo = new SaleOrderVO();
      svo.setParentVO(head);
      svo.setChildrenVO(new SaleOrderBVO[] {
        body
      });
      splitParameters[i].setData(svo);
      splitParameters[i].setSrcTrantype(body.getVsrctrantype());
      splitParameters[i].setDestTrantype(head.getCtrantypeid());
    }
    // 调用公共方法根据vo对照上的分单条件进行分单合单
    String pk_group = BSContext.getInstance().getGroupID();
    SaleOrderVO[] vos =
        (SaleOrderVO[]) BillSplitUtils.splitBills(
            SOBillType.PreOrder.getCode(), SOBillType.Order.getCode(),
            splitParameters, pk_group);
    return vos;
  }
}
