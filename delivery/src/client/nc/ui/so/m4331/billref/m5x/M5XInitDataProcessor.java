package nc.ui.so.m4331.billref.m5x;

import nc.bs.pf.pub.PfDataCache;
import nc.funcnode.ui.FuncletInitData;
import nc.itf.uap.pf.metadata.IFlowBizItf;
import nc.uap.pf.metadata.PfMetadataTools;
import nc.ui.pubapp.billref.dest.TransferViewProcessor;
import nc.ui.pubapp.billref.push.NodeOpenBillInitData;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener.IInitDataProcessor;
import nc.ui.so.m4331.billui.util.DeliveryPrecision;
import nc.ui.uif2.editor.BillForm;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pflow.PfServiceUtil;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.util.TimeUtils;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryVO;

/**
 * 调拨订单参照发货单初始化数据处理
 * 
 * @since 6.3
 * @version 2013-03-05 12:30:56
 * @author liujingn
 */
public class M5XInitDataProcessor implements IInitDataProcessor {

  private TransferViewProcessor transferProcessor;

  /**
   * 
   * @return M5XInitDataProcessor
   */
  public TransferViewProcessor getTransferProcessor() {
    return this.transferProcessor;
  }

  @Override
  public void process(FuncletInitData data) {
    NodeOpenBillInitData initData = null;
    if (data instanceof NodeOpenBillInitData) {
      initData = (NodeOpenBillInitData) data;
    }
    if (initData == null) {
      return;
    }
    DeliveryVO[] bills = (DeliveryVO[]) initData.getInitData();
    // 调拨订单发货安排时 取得流程设置的交易类型
    this.setDesttrantype(bills);
    // 设置默认值
    this.setDefValue(bills);
    BillForm editor = this.transferProcessor.getBillForm();
    // 设置精度
    // 单表精度处理
    DeliveryPrecision.getInstance().setScaleForSingleTable(
        new CardPaneScaleProcessor(
            editor.getModel().getContext().getPk_group(), editor
                .getBillCardPanel()));

    editor.getModel().initModel(null);
    this.transferProcessor.processBillTransfer(bills);
  }

  /**
   * 
   * @param transferProcessor
   */
  public void setTransferProcessor(TransferViewProcessor transferProcessor) {
    this.transferProcessor = transferProcessor;
  }

  private void setDefValue(DeliveryVO[] bills) {
    for (DeliveryVO bill : bills) {
      DeliveryBVO[] items = bill.getChildrenVO();
      for (DeliveryBVO item : items) {
        item.setDsenddate(TimeUtils.getsrvBaseDate().asLocalEnd());
      }
    }
  }

  /**
   * 设置目的交易类型
   * 
   */
  private void setDesttrantype(AggregatedValueObject[] bills) {
    for (AggregatedValueObject bill : bills) {

      IFlowBizItf fbi = PfMetadataTools.getBizItfImpl(bill, IFlowBizItf.class);
      if (fbi.getTranstype() != null) {
        continue;
      }
      String busitype = fbi.getBusitype();
      String destTrantype =
          PfServiceUtil.findDestTrantype(busitype, SOBillType.Delivery
              .getCode(), AppContext.getInstance().getPkGroup());
      BilltypeVO vo = PfDataCache.getBillType(destTrantype);
      fbi.setTranstypePk(vo.getPk_billtypeid());
      fbi.setTranstype(destTrantype);
    }
  }

}
