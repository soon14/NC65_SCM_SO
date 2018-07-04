package nc.ui.so.m33.pub.view;

import nc.vo.pubapp.scale.TableScaleProcessor;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;

import nc.desktop.ui.WorkbenchEnvironment;

import nc.ui.pubapp.scale.BillModelScaleProcessor;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;

public abstract class AbstractM33BillPubListView extends ShowUpableBillListView {

  private static final long serialVersionUID = -63336330577496381L;

  /**
   * 当前查询结算类型：QueryFlag中的常量
   */
  private int queryFlag;

  public int getQueryFlag() {
    return this.queryFlag;
  }

  public void setQueryFlag(int queryFlag) {
    this.queryFlag = queryFlag;
  }

  protected abstract String[] getNoEditEnableKey();

  @Override
  protected void setBillListPanelProp() {

    super.setBillListPanelProp();

    // 设置精度
    this.initDataDigit();

    // 设置字段编辑性
    this.initEditEnable();

  }

  private void initDataDigit() {
    // 集团本币金额
    String[] groupmnykeys = new String[] {
      SquareOutBVO.NGROUPTAXMNY, SquareOutBVO.NGROUPMNY
    };

    // 全局本币金额
    String[] globalmnykeys = new String[] {
      SquareOutBVO.NGLOBALTAXMNY, SquareOutBVO.NGLOBALMNY
    };

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
    
    String[] netpricekeys=new String[]{ SquareOutBVO.NTAXPRICE, SquareOutBVO.NPRICE,
        SquareOutBVO.NTAXNETPRICE, SquareOutBVO.NNETPRICE,};

    // 数量
    String[] astnumkeys = new String[] {
      SquareOutBVO.NASTNUM
    };

    // 主数量
    String[] numkeys =
        new String[] {
          SquareOutBVO.NNUM, SquareOutBVO.NTHISNUM,
          SquareOutBVO.NTOTALSQUARENUM, SquareOutBVO.NRUSHNUM
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

    TableScaleProcessor scaleprocess =
        new BillModelScaleProcessor(WorkbenchEnvironment.getInstance()
            .getGroupVO().getPk_group(), this.billListPanel.getBodyBillModel());

    // 全局本币金额
    scaleprocess.setGlobalLocMnyCtlInfo(globalmnykeys);
    // 集团本币金额
    scaleprocess.setGroupLocMnyCtlInfo(groupmnykeys);
    // 本币金额
    scaleprocess.setMnyCtlInfo(mnykeys, SquareOutBVO.CCURRENCYID);
    // 换算率精度
    scaleprocess.setHslCtlInfo(hslkeys);
    // 单价
    scaleprocess.setPriceCtlInfo(pricekeys,SquareOutBVO.CORIGCURRENCYID);
    
    // 本币单价
    scaleprocess.setPriceCtlInfo(netpricekeys,SquareOutBVO.CCURRENCYID);
    
    // 数量
    scaleprocess.setNumCtlInfo(astnumkeys, SquareOutBVO.CASTUNITID);
    // 主数量
    scaleprocess.setNumCtlInfo(numkeys, SquareOutBVO.CUNITID);
    // 原币金额
    scaleprocess.setMnyCtlInfo(origmnykeys, SquareOutBVO.CORIGCURRENCYID);

    // 折扣
    scaleprocess.setSaleDiscountCtlInfo(itemdiscountratekeys);

    // 税率
    scaleprocess.setTaxRateCtlInfo(taxratekeys);

    scaleprocess.process();

  }

  private void initEditEnable() {
    String[] itemKeys = this.getNoEditEnableKey();
    for (String key : itemKeys) {
      this.billListPanel.getBodyBillModel().getItemByKey(key).setEdit(false);
    }
  }

}
