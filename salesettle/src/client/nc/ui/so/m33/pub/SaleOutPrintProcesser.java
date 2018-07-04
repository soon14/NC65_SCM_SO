package nc.ui.so.m33.pub;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.pub.print.IDigitProcessor;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;

public class SaleOutPrintProcesser implements IDigitProcessor {

  private AbstractAppModel model;

  /**
   * @return model
   */
  public AbstractAppModel getModel() {
    return this.model;
  }

  /**
   * @param model
   *          要设置的 model
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
  }

  @Override
  public void cardpanelDigitProcess(BillCardPanel cardpanel) throws Exception {

    // // 集团本币金额
    // String[] groupmnykeys = new String[] {
    // SquareOutBVO.NGROUPTAXMNY, SquareOutBVO.NGROUPMNY
    // };
    //
    // // 全局本币金额
    // String[] globalmnykeys = new String[] {
    // SquareOutBVO.NGLOBALTAXMNY, SquareOutBVO.NGLOBALMNY
    // };

    // 本币金额
    String[] mnykeys =
        new String[] {
          SquareOutBVO.NTAX, SquareOutBVO.NMNY, SquareOutBVO.NTAXMNY,
          SquareOutBVO.NDISCOUNT,
          // 2012.02.16 fbinly v61新增字段
          SquareOutBVO.NCALTAXMNY
        };

    // 换算率精度
    String[] hslkeys = new String[] {
      SquareOutBVO.VCHANGERATE
    };

    // 单价
    String[] pricekeys =
        new String[] {
          SquareOutBVO.NORIGTAXPRICE, SquareOutBVO.NORIGPRICE,
          SquareOutBVO.NORIGTAXNETPRICE, SquareOutBVO.NORIGNETPRICE,
        
        };
    String[] netpricekeys=new String[]{
        SquareOutBVO.NTAXPRICE, SquareOutBVO.NPRICE,
        SquareOutBVO.NTAXNETPRICE, SquareOutBVO.NNETPRICE,
    };

    // 数量
    String[] astnumkeys = new String[] {
      SquareOutBVO.NASTNUM
    };

    // 主数量
    String[] numkeys = new String[] {
      SquareOutBVO.NNUM, SquareOutBVO.NTHISNUM, SquareOutBVO.NTOTALSQUARENUM,SquareOutBVO.NRUSHNUM
    };

    // 原币金额
    String[] origmnykeys =
        new String[] {
          // TODO 2012.02.16 fbinly v61删除原币税额字段
          // SquareOutBVO.NORIGTAX,
          SquareOutBVO.NORIGMNY, SquareOutBVO.NORIGTAXMNY,
          SquareOutBVO.NORIGDISCOUNT,
        };

    // 单品折扣
    String[] itemdiscountratekeys = new String[] {
      SquareOutBVO.NITEMDISCOUNTRATE
    };

    // 税率
    String[] taxratekeys = new String[] {
      SquareOutBVO.NTAXRATE
    };

    BillScaleProcessor scaleprocess =
        new CardPaneScaleProcessor(this.getModel().getContext().getPk_group(),
            cardpanel);
    // 本币金额
    scaleprocess.setMnyCtlInfo(mnykeys, PosEnum.body, null,
        SquareOutBVO.CCURRENCYID, PosEnum.body, null);
    // 换算率精度
    scaleprocess.setHslCtlInfo(hslkeys, PosEnum.body, null);
    // 单价
    scaleprocess.setPriceCtlInfo(pricekeys, PosEnum.body, null, SquareOutBVO.CORIGCURRENCYID,PosEnum.body,null);
    
    // 本位币单价
    scaleprocess.setPriceCtlInfo(netpricekeys, PosEnum.body, null, SquareOutBVO.CCURRENCYID,PosEnum.body,null);
    // 数量
    scaleprocess.setNumCtlInfo(astnumkeys, PosEnum.body, null,
        SquareOutBVO.CASTUNITID, PosEnum.body, null);
    // 主数量
    scaleprocess.setNumCtlInfo(numkeys, PosEnum.body, null,
        SquareOutBVO.CUNITID, PosEnum.body, null);
    // 原币金额
    scaleprocess.setMnyCtlInfo(origmnykeys, PosEnum.body, null,
        SquareOutBVO.CORIGCURRENCYID, PosEnum.body, null);

    // 折扣
    scaleprocess.setSaleDiscountCtlInfo(itemdiscountratekeys, PosEnum.body,
        null);

    // 税率
    scaleprocess.setTaxRateCtlInfo(taxratekeys, PosEnum.body, null);

    scaleprocess.process();
  }

}
