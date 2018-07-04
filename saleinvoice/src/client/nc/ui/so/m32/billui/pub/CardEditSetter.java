package nc.ui.so.m32.billui.pub;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.enumeration.OpposeFlag;
import nc.vo.so.m32.paravo.CombinCacheVO;
import nc.vo.so.pub.res.ParameterList;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.so.pub.keyvalue.CardKeyValue;

/**
 * 卡片界面初始化的时候缓存卡片编辑性
 * 订单冲抵标志发生改变时控制卡片编辑性
 * 
 * @since 6.0
 * @version 2010-12-10 下午01:03:01
 * @author 么贵敬
 */
public class CardEditSetter {

  /**
   * 合并显示允许编辑的字段
   */
  private static final String[] BODY_COMBINCANEDITKEY = new String[] {
    // 数量、主数量、报价数量
    SaleInvoiceBVO.NASTNUM, SaleInvoiceBVO.NNUM, SaleInvoiceBVO.NQTUNITNUM,
    // 价税合计、无税金额
    SaleInvoiceBVO.NORIGTAXMNY, SaleInvoiceBVO.NORIGMNY,
    // 税额折扣额
    SaleInvoiceBVO.NTAX, SaleInvoiceBVO.NORIGDISCOUNT
  };

  /** 需要控制编辑性的表体字段 */
  private static final String[] BODY_EDITCHGKEY = new String[] {
    // 物料、单位
    SaleInvoiceBVO.CMATERIALVID, SaleInvoiceBVO.CASTUNITID,
    // 数量、主数量
    SaleInvoiceBVO.NASTNUM, SaleInvoiceBVO.NNUM,
    // 换算率、报价数量
    SaleInvoiceBVO.VCHANGERATE, SaleInvoiceBVO.NQTUNITNUM,
    // 报价单位、报价换算率
    SaleInvoiceBVO.CQTUNITID, SaleInvoiceBVO.VQTUNITRATE,
    // 赠品、发票折扣
    SaleInvoiceBVO.BLARGESSFLAG, SaleInvoiceBVO.NINVOICEDISRATE,
    // 主含税单价、主无税单价
    SaleInvoiceBVO.NORIGTAXPRICE, SaleInvoiceBVO.NORIGPRICE,
    // 主含税净价、主无税净价
    SaleInvoiceBVO.NORIGTAXNETPRICE, SaleInvoiceBVO.NORIGNETPRICE,
    // 含税单价、无税单价
    SaleInvoiceBVO.NQTORIGTAXPRICE, SaleInvoiceBVO.NQTORIGPRICE,
    // 含税净价、无税净价
    SaleInvoiceBVO.NQTORIGTAXNETPRC, SaleInvoiceBVO.NQTORIGNETPRICE,
    // 税额、无税金额
    SaleInvoiceBVO.NORIGMNY,
    // 价税合计、折扣额
    SaleInvoiceBVO.NORIGTAXMNY, SaleInvoiceBVO.NORIGDISCOUNT,
    // 利润中心、税率
    SaleInvoiceBVO.CPROFITCENTERID, SaleInvoiceBVO.NTAXRATE
  };

  /** 对冲允许编辑的字段 */
  private static final String[] OPPHEAD_CANEDITKEY = new String[] {
    // 金税号、备注、发票号
    SaleInvoiceHVO.VGOLDTAXCODE,
    SaleInvoiceHVO.VNOTE,
    SaleInvoiceHVO.VBILLCODE,
    // 表头自定义字段
    SaleInvoiceHVO.VDEF1, SaleInvoiceHVO.VDEF2, SaleInvoiceHVO.VDEF3,
    SaleInvoiceHVO.VDEF4, SaleInvoiceHVO.VDEF5, SaleInvoiceHVO.VDEF6,
    SaleInvoiceHVO.VDEF7, SaleInvoiceHVO.VDEF8, SaleInvoiceHVO.VDEF9,
    SaleInvoiceHVO.VDEF10, SaleInvoiceHVO.VDEF11, SaleInvoiceHVO.VDEF12,
    SaleInvoiceHVO.VDEF13, SaleInvoiceHVO.VDEF14, SaleInvoiceHVO.VDEF15,
    SaleInvoiceHVO.VDEF16, SaleInvoiceHVO.VDEF17, SaleInvoiceHVO.VDEF18,
    SaleInvoiceHVO.VDEF19, SaleInvoiceHVO.VDEF20

  };

  /** 对冲允许编辑的字段 */
  private static final String[] OPPBODY_CANEDITKEY = new String[] {
    // 表体自定义字段
    SaleInvoiceBVO.VBDEF1, SaleInvoiceBVO.VBDEF2, SaleInvoiceBVO.VBDEF3,
    SaleInvoiceBVO.VBDEF4, SaleInvoiceBVO.VBDEF5, SaleInvoiceBVO.VBDEF6,
    SaleInvoiceBVO.VBDEF7, SaleInvoiceBVO.VBDEF8, SaleInvoiceBVO.VBDEF9,
    SaleInvoiceBVO.VBDEF10, SaleInvoiceBVO.VBDEF11, SaleInvoiceBVO.VBDEF12,
    SaleInvoiceBVO.VBDEF13, SaleInvoiceBVO.VBDEF14, SaleInvoiceBVO.VBDEF15,
    SaleInvoiceBVO.VBDEF16, SaleInvoiceBVO.VBDEF17, SaleInvoiceBVO.VBDEF18,
    SaleInvoiceBVO.VBDEF19, SaleInvoiceBVO.VBDEF20
  };

  /** 缓存的表体编辑性 */
  private Map<String, Boolean> bodyEditCache;

  /** 缓存的表体cell的编辑性 */
  private Set<String> bodycellEditCache = new HashSet<String>();

  /** 缓存的表体cell的编辑性 */
  private Set<Integer> bodycellrows = new HashSet<Integer>();

  /** 缓存的表头编辑性 */
  private Map<String, Boolean> headEditCache;

  /**
   * 缓存原始卡片界面上项目的编辑性
   * 
   * @param card
   */
  public void cacheEditEnable(BillCardPanel card) {
    // 缓存表头编辑性
    this.headEditCache = new HashMap<String, Boolean>();

    for (BillItem headitem : card.getHeadItems()) {
      this.headEditCache.put(headitem.getKey(),
          Boolean.valueOf(headitem.isEdit()));
    }
    // 缓存表体编辑性
    this.bodyEditCache = new HashMap<String, Boolean>();
    for (BillItem bodyitem : card.getBodyItems()) {
      String key = bodyitem.getKey();
      this.bodyEditCache.put(key, Boolean.valueOf(bodyitem.isEdit()));
    }
  }

  /**
   * 根据表头对冲标志设置和合并显示标志设置卡片界面编辑性
   * 
   * @param card
   * @param cachecomvo
   */
  public void setEditEnable(BillCardPanel card, CombinCacheVO cachecomvo) {
    // 设置合并显示时的编辑性
    this.setComEditEnable(card, cachecomvo);
    // 设置表头编辑性
    this.setEditEnable(card);

  }

  private boolean isArsub(BillCardPanel card) {
    CardKeyValue keyvalue = new CardKeyValue(card);
    // 只有未冲抵的发票可以编辑，冲抵标志为是时，不可以编辑
    UFBoolean arsubflag =
        keyvalue.getHeadUFBooleanValue(SaleInvoiceHVO.BSUBUNITFLAG);
    return null != arsubflag && arsubflag.booleanValue();
  }

  private boolean isOppose(BillCardPanel card) {
    CardKeyValue keyvalue = new CardKeyValue(card);
    // 对冲标志为正常的才可以编辑，对冲已完成和对冲生成的都不可以编辑
    Integer oppflag = keyvalue.getHeadIntegerValue(SaleInvoiceHVO.FOPPOSEFLAG);
    return OpposeFlag.FINSH.equalsValue(oppflag)
        || OpposeFlag.GENERAL.equalsValue(oppflag);
  }

  /**
   * 恢复表体字段原始编辑性
   * 
   * @param card
   */
  private void resumeBodyEdit(BillCardPanel card) {

    for (BillItem bodyitem : card.getBodyShowItems()) {
      String key = bodyitem.getKey();
      bodyitem.setEdit(this.bodyEditCache.get(key).booleanValue());
    }

  }

  /**
   * 显示明细时调用
   * 
   * @param card
   */
  private void resumeBodyCellEdit(BillCardPanel card) {
    if (card.getRowCount() <= 0) {
      return;
    }
    BillItem[] bodyitems = card.getBodyShowItems();
    for (Integer i : this.bodycellrows) {
      if (i.intValue() <= card.getRowCount()) {
        break;
      }
      for (BillItem bodyitem : bodyitems) {
        String key = bodyitem.getKey();
        if (this.bodycellEditCache.contains(key)) {
          card.setCellEditable(i.intValue(), key, true);
        }
      }
    }
    this.bodycellEditCache.clear();
    this.bodycellrows.clear();
  }

  /**
   * 恢复编辑下
   * 
   * @param card cardPanel
   */
  public void resumeEdit(BillCardPanel card) {

    this.resumeBodyEdit(card);

  }

  private void setCombinEdit(BillCardPanel card, Set<String> setGroupKeys) {
    int row = card.getRowCount();
    for (BillItem bodyitem : card.getBodyShowItems()) {
      String key = bodyitem.getKey();
      if (setGroupKeys.contains(key)) {
        bodyitem.setEdit(this.bodyEditCache.get(key).booleanValue());
      }
      else {
        if (this.bodyEditCache.get(key).booleanValue()) {
          this.bodycellEditCache.add(key);
        }
        for (int i = 0; i < row; i++) {
          if (!this.bodycellrows.contains(Integer.valueOf(i))) {
            this.bodycellrows.add(Integer.valueOf(i));
          }
          card.setCellEditable(i, key, false);
        }
      }
    }

  }

  private void setComEditEnable(BillCardPanel card, CombinCacheVO cachecomvo) {
    // 合并标志
    boolean comflag = cachecomvo.getBcombinflag();
    Set<String> setGroupKeys = null;
    if (comflag) {
      CardKeyValue keyvalue = new CardKeyValue(card);
      String pk_org = keyvalue.getHeadStringValue(SaleInvoiceHVO.PK_ORG);
      if (null != pk_org) {
        setGroupKeys = new HashSet<String>();
        String groupstr =
            cachecomvo.getGroupKeys(pk_org).split(ParameterList.BIGSPLITKEY)[0];
        String[] groups = groupstr.split(ParameterList.SPLITKEY);
        for (String key : groups) {
          setGroupKeys.add(key);
        }
        for (String key : CardEditSetter.BODY_COMBINCANEDITKEY) {
          setGroupKeys.add(key);
        }
        // 设置编辑性
        this.setCombinEdit(card, setGroupKeys);
      }
    }
    else {
      this.resumeBodyCellEdit(card);
    }
  }

  /**
   * 对冲和冲抵编辑性控制
   * 
   * @param card
   */
  private void setEditEnable(BillCardPanel card) {
    boolean oppflag = this.isOppose(card);
    boolean subflag = this.isArsub(card);
    if (!subflag && !oppflag && null != this.headEditCache) {
      for (BillItem headitem : card.getHeadShowItems()) {
        headitem.setEdit(this.headEditCache.get(headitem.getKey())
            .booleanValue());
      }
      this.resumeBodyEdit(card);
    }
    if (subflag) {
      this.setSubEdit(card);
    }

    if (oppflag) {
      this.setOppEdit(card);
    }
  }

  /**
   * 方法功能描述：对冲发票时设置字段编辑性。
   * <p>
   * <b>参数说明</b>
   * 
   * @param card
   *          <p>
   * @author fengjb
   * @time 2010-5-24 下午03:58:55
   */
  private void setOppEdit(BillCardPanel card) {
    Set<String> setEdit = new HashSet<String>();
    for (String key : CardEditSetter.OPPHEAD_CANEDITKEY) {
      setEdit.add(key);
    }
    Set<String> setBodyEdit = new HashSet<String>();
    for (String key : CardEditSetter.OPPBODY_CANEDITKEY) {
      setBodyEdit.add(key);
    }

    for (BillItem headitem : card.getHeadShowItems()) {
      String key = headitem.getKey();
      if (setEdit.contains(key)) {
        headitem.setEdit(headitem.isEdit());
      }
      else {
        headitem.setEdit(false);
      }
    }

    for (BillItem bodyitem : card.getBodyShowItems()) {
      String bodyKey = bodyitem.getKey();
      if (setBodyEdit.contains(bodyKey)) {
        bodyitem.setEdit(bodyitem.isEdit());
      }
      else {
        bodyitem.setEdit(false);
      }
    }
  }

  /**
   * 冲抵编辑性控制
   * 
   * @param card
   */
  private void setSubEdit(BillCardPanel card) {
    Set<String> setNoEdit = new HashSet<String>();
    for (String key : CardEditSetter.BODY_EDITCHGKEY) {
      setNoEdit.add(key);
    }

    for (BillItem bodyitem : card.getBodyShowItems()) {
      String key = bodyitem.getKey();
      if (setNoEdit.contains(key)) {
        bodyitem.setEdit(false);
      }
    }
  }
}
