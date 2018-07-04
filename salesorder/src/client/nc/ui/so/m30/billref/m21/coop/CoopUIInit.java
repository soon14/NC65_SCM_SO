package nc.ui.so.m30.billref.m21.coop;

import nc.ui.pubapp.billref.src.RefUIInit;
import nc.ui.pubapp.billref.src.view.RefListPanel;
import nc.ui.so.m30.pub.SaleOrderPrecision;
import nc.vo.so.m30.entity.SaleOrderHVO;

/**
 * 转单界面精度设置
 * 
 * @since 6.0
 * @version 2010-11-26 上午10:52:28
 * @author 祝会征
 */
public class CoopUIInit extends RefUIInit {

  @Override
  public void initBillListPanel(RefListPanel listpane) {
    // 集团
    String pk_group =
        this.getRefContext().getRefInfo().getBillSrcVar().getPk_group();
    SaleOrderPrecision.getInstance().setListPrecision(pk_group, listpane);
    listpane.getHeadBillModel().sortByColumn(SaleOrderHVO.VBILLCODE, true);
  }
}
