package nc.ui.so.m33.manreg.view;

import nc.ui.so.m33.pub.view.AbstractM33BillPubListView;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;

public class M33REGBillListView extends AbstractM33BillPubListView {

  private static final long serialVersionUID = -63336330577496381L;

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

}
