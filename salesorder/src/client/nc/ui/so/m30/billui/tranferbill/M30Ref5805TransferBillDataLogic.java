package nc.ui.so.m30.billui.tranferbill;

import nc.ui.pubapp.billref.dest.DefaultBillDataLogic;

/**
 * 
 * 销售订单参照进口明细转单后前台数据处理类
 * 
 * @since 6.5
 * @date 2018-3-15
 * @author zhangjjs
 */
public class M30Ref5805TransferBillDataLogic extends DefaultBillDataLogic {

  @Override
  public void doTransferAddLogic(Object selectedData) {

    // 把数据设置到界面上
    super.doTransferAddLogic(selectedData);
    /*// 基于界面卡片填充值运算
    BillCardPanel cardPanel = this.getBillForm().getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);*/
  }

}
