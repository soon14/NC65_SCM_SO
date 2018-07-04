package nc.ui.so.m38.billui.pub;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m38trantype.IM38TranTypeService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.so.pub.editable.SOCardEditableSetter;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38trantype.entity.M38TranTypeVO;
import nc.vo.so.pub.enumeration.AskPriceRule;
import nc.vo.so.pub.keyvalue.IKeyValue;

public class PreOrderCardEditSetter {

  // 表头前缀
  private static final String HEAD_PRE = "h_";

  private static final String[] REVISE_BODY_EDITKEY = new String[] {
    // 物料
    PreOrderBVO.CMATERIALVID,
    // 数量
    PreOrderBVO.NNUM,
    PreOrderBVO.NASTNUM,
    PreOrderBVO.NQTUNITNUM,
    // 金额
    PreOrderBVO.NORIGTAXMNY,
    PreOrderBVO.NORIGMNY,

    // TODO 刘景 2012-2-17 删除原币税额字段
    // PreOrderBVO.NORIGTAX,
    // 税额
    PreOrderBVO.NTAX,

    // 主单位原币
    PreOrderBVO.NORIGTAXPRICE,
    PreOrderBVO.NORIGPRICE,
    PreOrderBVO.NORIGTAXNETPRICE,
    PreOrderBVO.NORIGNETPRICE,
    // 原币
    PreOrderBVO.NQTORIGTAXPRICE,
    PreOrderBVO.NQTORIGPRICE,
    PreOrderBVO.NQTORIGTAXNETPRC,
    PreOrderBVO.NQTORIGNETPRICE,

    // 收货日期
    PreOrderBVO.DSENDDATE,
    PreOrderBVO.DRECEIVEDATE,
    // 收货客户
    PreOrderBVO.CRECEIVECUSTID, PreOrderBVO.CRECEIVEADDRID,
    PreOrderBVO.CRECEIVEAREAID,
    PreOrderBVO.CRECEIVESITEID,
    // 发货库存组织、结算财务组织
    PreOrderBVO.CSENDSTOCKORGVID, PreOrderBVO.CSETTLEORGVID,
    PreOrderBVO.CTRAFFICORGVID, PreOrderBVO.CARORGVID,
    PreOrderBVO.CPROFITCENTERVID,

    // 备注
    PreOrderBVO.VROWNOTE

  };

  // 修订表头可编辑字段
  private static final String[] REVISE_HEAD_EDITKEY = new String[] {
    PreOrderHVO.DABATEDATE, PreOrderHVO.NDISCOUNTRATE, PreOrderHVO.VNOTE
  };

  // 缓存原始编辑性
  private Map<String, Boolean> hmEditEnable;

  public void cacheEditEnable(BillCardPanel card) {
    this.hmEditEnable = new HashMap<String, Boolean>();
    for (BillItem headitem : card.getHeadShowItems()) {
      String headkey = this.getHeadKey(headitem);
      this.hmEditEnable.put(headkey, Boolean.valueOf(headitem.isEdit()));
    }

    for (BillItem bodyitem : card.getBodyItems()) {
      this.hmEditEnable.put(bodyitem.getKey(),
          Boolean.valueOf(bodyitem.isEdit()));
    }
  }

  public void setEditEnable(BillCardPanel card) {
    // 设置表头编辑性
    for (BillItem headitem : card.getHeadShowItems()) {
      String headkey = this.getHeadKey(headitem);
      if (this.hmEditEnable.containsKey(headkey)) {
        headitem.setEdit(this.hmEditEnable.get(headkey).booleanValue());
      }
    }
    // 设置表体编辑性
    for (BillItem bodyitem : card.getBodyItems()) {
      String bodykey = bodyitem.getKey();
      if (this.hmEditEnable.containsKey(bodykey)) {
        bodyitem.setEdit(this.hmEditEnable.get(bodykey).booleanValue());
      }
    }
    this.setEditByTranType(card);

  }

  public void setReviseCardEdit(BillCardPanel card) {

    for (BillItem headitem : card.getHeadShowItems()) {
      headitem.setEdit(false);
    }
    for (BillItem bodyitem : card.getBodyShowItems()) {
      bodyitem.setEdit(false);
    }
    for (String key : PreOrderCardEditSetter.REVISE_HEAD_EDITKEY) {
      BillItem headitem = card.getHeadItem(key);
      String headkey = this.getHeadKey(key);
      if (this.hmEditEnable.containsKey(headkey)) {
        headitem.setEdit(this.hmEditEnable.get(headkey).booleanValue());
      }
    }
    for (String key : PreOrderCardEditSetter.REVISE_BODY_EDITKEY) {

      BillItem bodyitem = card.getBodyItem(key);

      if (this.hmEditEnable.containsKey(key)) {
        bodyitem.setEdit(this.hmEditEnable.get(key).booleanValue());
      }

    }
    this.setEditByTranType(card);
  }

  private String getHeadKey(BillItem headitem) {
    return PreOrderCardEditSetter.HEAD_PRE + headitem.getKey();
  }

  private String getHeadKey(String headkey) {
    return PreOrderCardEditSetter.HEAD_PRE + headkey;
  }

  private M38TranTypeVO getTranTypeVO(BillCardPanel card) {
    IKeyValue keyValue = new CardKeyValue(card);
    String ctrantypeid = keyValue.getHeadStringValue(PreOrderHVO.CTRANTYPEID);
    if (PubAppTool.isNull(ctrantypeid)) {
      return null;
    }
    IM38TranTypeService srv =
        NCLocator.getInstance().lookup(IM38TranTypeService.class);
    M38TranTypeVO trantypevo = null;
    try {
      trantypevo = srv.queryTranTypeVO(ctrantypeid);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return trantypevo;
  }

  private boolean isAskPrice(IKeyValue keyValue, int i) {
    UFDouble nasktaxnetprice =
        keyValue.getBodyUFDoubleValue(i, PreOrderBVO.NASKQTORIGTXNTPRC);
    UFDouble nasknetprice =
        keyValue.getBodyUFDoubleValue(i, PreOrderBVO.NASKQTORIGNETPRICE);
    return null != nasktaxnetprice || null != nasknetprice;
  }

  private void setEditByTranType(BillCardPanel card) {
    // 设置表体编辑性
    M38TranTypeVO tranvo = this.getTranTypeVO(card);
    if (null == tranvo) {
      return;
    }
    UFBoolean modifydis =
        tranvo.getBmodifydiscount() == null ? UFBoolean.FALSE : tranvo
            .getBmodifydiscount();

    BillItem disrateitem = card.getBodyItem(PreOrderBVO.NITEMDISCOUNTRATE);
    if (disrateitem.isShow()) {
      boolean isedit = disrateitem.isEdit() && modifydis.booleanValue();
      disrateitem.setEdit(isedit);
    }
    Integer askrule = tranvo.getFaskqtrule();
    // 2.询价时需根据每行是否询价成功设置
    if (AskPriceRule.ASKPRICE_NORMAL.equalsValue(askrule)
        || AskPriceRule.ASKPRICE_UNSPROMOTION.equalsValue(askrule)) {
      SOCardEditableSetter soeditsetter = new SOCardEditableSetter();
      IKeyValue keyValue = new CardKeyValue(card);
      UFBoolean modifyask =
          tranvo.getBmodifyaskedqt() == null ? UFBoolean.FALSE : tranvo
              .getBmodifyaskedqt();
      UFBoolean modifyunask =
          tranvo.getBmodifyunaskedqt() == null ? UFBoolean.FALSE : tranvo
              .getBmodifyunaskedqt();

      int bodycount = keyValue.getBodyCount();
      for (int i = 0; i < bodycount; i++) {
        // 本行是否询价成功
        if (this.isAskPrice(keyValue, i)) {
          soeditsetter.setBodyPriceMnyEdit(card, i, modifyask.booleanValue());
        }
        else {
          soeditsetter.setBodyPriceMnyEdit(card, i, modifyunask.booleanValue());
        }
      }
    }
  }

}
