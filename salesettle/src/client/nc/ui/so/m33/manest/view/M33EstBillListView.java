package nc.ui.so.m33.manest.view;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.so.m33.pub.view.AbstractM33BillPubListView;
import nc.ui.so.pub.util.SOSelectedRowTotalUtil;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;

public class M33EstBillListView extends AbstractM33BillPubListView {

  private static final long serialVersionUID = 8256470730974230121L;

  @Override
  protected String[] getNoEditEnableKey() {
    String[] itemKeys =
        new String[] {
          SquareOutBVO.DBILLDATE,
          SquareOutBVO.DBIZDATE,
          SquareOutBVO.NNUM,
          SquareOutBVO.NASTNUM,
          // TODO 2012.02.16 fbinly v61É¾³ýÔ­±ÒË°¶î×Ö¶Î
          // SquareOutBVO.NORIGTAX,
          SquareOutBVO.NEXCHANGERATE, SquareOutBVO.VCHANGERATE,
          SquareOutBVO.NTAXMNY, SquareOutBVO.NMNY, SquareOutBVO.NTAX,
          SquareOutBVO.NGROUPEXCHGRATE, SquareOutBVO.NGROUPMNY,
          SquareOutBVO.NGROUPTAXMNY, SquareOutBVO.NGLOBALEXCHGRATE,
          SquareOutBVO.NGLOBALMNY, SquareOutBVO.NGLOBALTAXMNY,
          SquareOutBVO.NTOTALSQUARENUM,
          // 2012.02.16 fbinly v61ÐÂÔö×Ö¶Î
          SquareOutBVO.NCALTAXMNY
        };
    return itemKeys;
  }

  @Override
  public void initUI() {
    super.initUI();
    BillListPanel listPanel = this.getBillListPanel();
    SOSelectedRowTotalUtil.selectedRowTotalProcess(listPanel,
        SquareOutViewVO.class.getName());
  }

}
