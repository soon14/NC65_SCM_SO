package nc.ui.so.m33.mansquare.view;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.so.m33.pub.view.AbstractM33BillPubListView;
import nc.ui.so.pub.util.SOSelectedRowTotalUtil;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;

public class M33BillListView extends AbstractM33BillPubListView {

  private static final long serialVersionUID = -63336330577496381L;

  @Override
  protected String[] getNoEditEnableKey() {
    /*    String[] itemKeys =
            new String[] {
              SquareOutBVO.DBILLDATE, SquareOutBVO.DBIZDATE, SquareOutBVO.NASTNUM,
              SquareOutBVO.NORIGTAX, SquareOutBVO.NEXCHANGERATE,
              SquareOutBVO.VCHANGERATE, SquareOutBVO.NTAXMNY, SquareOutBVO.NMNY,
              SquareOutBVO.NTAX, SquareOutBVO.NGROUPEXCHGRATE,
              SquareOutBVO.NGROUPMNY, SquareOutBVO.NGROUPTAXMNY,
              SquareOutBVO.NGLOBALEXCHGRATE, SquareOutBVO.NGLOBALMNY,
              SquareOutBVO.NGLOBALTAXMNY, SquareOutBVO.NTOTALSQUARENUM,
              SquareOutBVO.BLARGESSFLAG, SquareOutBVO.PK_ORG,
              SquareOutHVO.PK_ORG_V, SquareOutBVO.PK_GROUP, SquareOutHVO.VBILLCODE,
              SquareOutBVO.VFIRSTCODE, SquareOutBVO.VSRCCODE,
              SquareOutBVO.VFIRSTTYPE, SquareOutBVO.VFIRSTTRANTYPE,
              SquareOutBVO.CMATERIALVID, SquareOutBVO.VBATCHCODE,
              SquareOutBVO.NARRUSHNUM, SquareOutBVO.NRUSHNUM,
              SquareOutBVO.NTOTALSQUARENUM, SquareOutBVO.CORIGCURRENCYID,
              SquareOutBVO.NTAXRATE
            };*/

    List<String> list = new ArrayList<String>();
    BillItem[] items = super.billListPanel.getBodyBillModel().getBodyItems();
    for (BillItem item : items) {
      if (!SquareOutBVO.NTHISNUM.equals(item.getKey())) {
        list.add(item.getKey());
      }
    }
    return list.toArray(new String[list.size()]);
  }

  @Override
  protected void setBillListPanelProp() {
    /***** 代码生成工具 ********************************/
    super.setBillListPanelProp();
    super.billListPanel.setEnabled(true);
  }

  @Override
  public void initUI() {
    super.initUI();
    BillListPanel listPanel = this.getBillListPanel();
    SOSelectedRowTotalUtil.selectedRowTotalProcess(listPanel,
        SquareOutViewVO.class.getName());
  }
}
