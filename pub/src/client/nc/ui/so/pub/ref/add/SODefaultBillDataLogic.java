package nc.ui.so.pub.ref.add;

import nc.ui.pubapp.billref.dest.DefaultBillDataLogic;
import nc.ui.so.pub.editable.SOCardEditableSetter;

/**
 * 销售管理公共的转单后默认处理
 * 
 * @since 6.0
 * @version 2011-10-19 下午03:54:22
 * @author 么贵敬
 */
public class SODefaultBillDataLogic extends DefaultBillDataLogic {

  @Override
  public void doTransferAddLogic(Object selectedData) {
    super.doTransferAddLogic(selectedData);

    // 转单后控制界面交易类型编辑性
    new SOCardEditableSetter().setHeadEditForRef(this.getBillForm()
        .getBillCardPanel());
  }

}
