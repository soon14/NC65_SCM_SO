package nc.ui.so.m30.pub;

import java.util.HashMap;
import java.util.Map;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.enumeration.Largesstype;
import nc.vo.so.m30.util.SpecialBusiUtil;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 卡片界面初始化的时候缓存卡片编辑性
 * 订单冲抵标志发生改变时控制卡片编辑性
 * 
 * @since 6.0
 * @version 2010-12-10 下午12:14:04
 * @author 么贵敬
 */
public class CardEditSetter {

  /**
   * 订单收款后，表头不能编辑的字段
   */
  public static final  String[] HEADNOTEDITFIELDS = {
    // 开票客户、客户、币种
    SaleOrderHVO.CINVOICECUSTID, SaleOrderHVO.CCUSTOMERID,
    SaleOrderHVO.CFREECUSTID, SaleOrderHVO.CORIGCURRENCYID
  };

  /**
   * 订单收款后，表体不能编辑的字段
   */
  public  static final  String[] BODYNOTEDITFIELDS = {
    // 物料
    SaleOrderBVO.CMATERIALVID,
    // 财务组织、财务组织版本
    SaleOrderBVO.CSETTLEORGID, SaleOrderBVO.CSETTLEORGVID,
    // 财务组织
    SaleOrderBVO.CSETTLEORGID, SaleOrderBVO.CSETTLEORGVID,
    // 应收组织
    SaleOrderBVO.CARORGID, SaleOrderBVO.CARORGVID,
    // 库存组织
    SaleOrderBVO.CSENDSTOCKORGID, SaleOrderBVO.CSENDSTOCKORGVID
  };

  /** 固定不可编辑的表体字段 */
  private static final String[] BODYFIXFALSEKEY = new String[] {
    SaleOrderBVO.PK_GROUP
  };

  /** 需要控制编辑性的表体字段 */
  private static final String[] BODYKEY = new String[] {
    // 物料、单位
    SaleOrderBVO.CMATERIALVID,
    SaleOrderBVO.CASTUNITID,
    // 数量、主数量
    SaleOrderBVO.NASTNUM,
    SaleOrderBVO.NNUM,
    // 换算率、报价数量
    SaleOrderBVO.VCHANGERATE,
    SaleOrderBVO.NQTUNITNUM,
    // 报价单位、主单位
    SaleOrderBVO.CQTUNITID,
    SaleOrderBVO.CUNITID,
    // 赠品、报价换算率
    SaleOrderBVO.BLARGESSFLAG,
    SaleOrderBVO.VQTUNITRATE,
    // 主含税单价、主无税单价
    SaleOrderBVO.NORIGTAXPRICE,
    SaleOrderBVO.NORIGPRICE,
    // 主含税净价、主无税净价
    SaleOrderBVO.NORIGTAXNETPRICE,
    SaleOrderBVO.NORIGNETPRICE,
    // 含税单价、无税单价
    SaleOrderBVO.NQTORIGTAXPRICE,
    SaleOrderBVO.NQTORIGPRICE,
    // 含税净价、无税净价
    SaleOrderBVO.NQTORIGTAXNETPRC,
    SaleOrderBVO.NQTORIGNETPRICE,
    // 税额、无税金额
    SaleOrderBVO.NORIGMNY,
    // 价税合计、折扣额
    SaleOrderBVO.NORIGTAXMNY,
    SaleOrderBVO.NORIGDISCOUNT,
    // 财务组织、财务组织版本
    SaleOrderBVO.CSETTLEORGID,
    SaleOrderBVO.CSETTLEORGVID,
    // 利润中心、质量等级
    SaleOrderBVO.CPROFITCENTERID,
    SaleOrderBVO.CQUALITYLEVELID,
    // 收货地区
    SaleOrderBVO.CRECEIVEAREAID, SaleOrderBVO.CPRICEITEMID,
    SaleOrderBVO.VFREE1, SaleOrderBVO.VFREE2, SaleOrderBVO.VFREE3,
    SaleOrderBVO.VFREE4, SaleOrderBVO.VFREE5, SaleOrderBVO.VFREE6,
    SaleOrderBVO.VFREE7, SaleOrderBVO.VFREE8, SaleOrderBVO.VFREE9,
    SaleOrderBVO.VFREE10
  };

  /** 固定不可编辑的表头字段 */
  private static final String[] HEADFIXFALSEKEY = new String[] {
    SaleOrderHVO.BOFFSETFLAG
  };

  /** 需要控制编辑性的表头字段 */
  private static final String[] HEADKEY = new String[] {
    // 开票客户、客户
    SaleOrderHVO.CINVOICECUSTID, SaleOrderHVO.CCUSTOMERID,
    SaleOrderHVO.CFREECUSTID,
    // 订单类型、整单折扣
    SaleOrderHVO.CTRANTYPEID, SaleOrderHVO.VTRANTYPECODE,
    // 折扣、渠道类型
    SaleOrderHVO.NDISCOUNTRATE, SaleOrderHVO.CCHANNELTYPEID,
    // 币种 单据日期
    SaleOrderHVO.CORIGCURRENCYID, SaleOrderHVO.DBILLDATE,
    // 结算方式\运输方式
    SaleOrderHVO.CBALANCETYPEID, SaleOrderHVO.CTRANSPORTTYPEID,
    // 单据日期
    SaleOrderHVO.DBILLDATE
  };

  private static final String[] LargessApportionKey = new String[] {
    // 主含税单价、主无税单价
    SaleOrderBVO.NORIGTAXPRICE, SaleOrderBVO.NORIGPRICE,
    // 主含税净价、主无税净价
    SaleOrderBVO.NORIGTAXNETPRICE, SaleOrderBVO.NORIGNETPRICE,
    // 含税单价、无税单价
    SaleOrderBVO.NQTORIGTAXPRICE, SaleOrderBVO.NQTORIGPRICE,
    // 含税净价、无税净价
    SaleOrderBVO.NQTORIGTAXNETPRC, SaleOrderBVO.NQTORIGNETPRICE
  };

  /** 缓存的编辑性 */
  private Map<String, Boolean> hmEditEnable;

  private SaleOrderBillForm billform;

  private BillCardPanel cardpanel;

  /**
   * CardEditSetter 的构造子
   * 
   * @param billform
   */
  public CardEditSetter(SaleOrderBillForm billform) {

    this.billform = billform;
    this.cardpanel = this.billform.getBillCardPanel();
  }

  /**
   * 缓存原始卡片界面上项目的编辑性,界面初始化的时候调用
   * 
   */
  public void cacheEditEnable() {
    this.billform.setHmEditEnable(new HashMap<String, Boolean>(
        CardEditSetter.HEADKEY.length + CardEditSetter.BODYKEY.length));
    for (String key : CardEditSetter.HEADKEY) {
      if (null != this.cardpanel.getHeadItem(key)) {
        this.billform.getHmEditEnable().put(key,
            Boolean.valueOf(this.cardpanel.getHeadItem(key).isEdit()));
      }
    }

    for (String key : CardEditSetter.BODYKEY) {
      if (null != this.cardpanel.getBodyItem(key)) {
        this.billform.getHmEditEnable().put(key,
            Boolean.valueOf(this.cardpanel.getBodyItem(key).isEdit()));
      }
    }
  }

  /**
   * 设置初始卡片强制编辑性
   * 
   */
  public void setCardFixEditFalse() {
    for (String key : CardEditSetter.HEADFIXFALSEKEY) {
      if (null != this.cardpanel.getHeadItem(key)) {
        this.cardpanel.getHeadItem(key).setEdit(false);
      }
    }
    for (String key : CardEditSetter.BODYFIXFALSEKEY) {
      if (null != this.cardpanel.getBodyItem(key)) {
        this.cardpanel.getBodyItem(key).setEdit(false);
      }
    }
  }

  /**
   * 根据表头费用冲抵标志设置卡片界面编辑性
   * 
   */
  public void setEditEnable() {
    if (this.isSaleOrderOffset()) {
      this.setOffsetEdit();
    }
    else {
        //==== lijj 因特殊要求 销售订单在未被审批的前提下会有下游进口合同，故在有下游进口合同后需进行必要编辑性限制 ===
        SpecialBusiUtil busiUtil = new SpecialBusiUtil();
        IKeyValue keyvalue = new CardKeyValue(this.cardpanel);
        String saleorderId = keyvalue.getHeadStringValue(SaleOrderHVO.CSALEORDERID);
        if(busiUtil.hasLowerBill(saleorderId)){
        	setHasLowerBillEdit();
        }
        else{
        	setOriginalEdit();
        }
        //==== lijj 因特殊要求 销售订单在未被审批的前提下会有下游进口合同，故在有下游进口合同后需进行必要编辑性限制 ===
    	
      //this.setOriginalEdit();
    }
    


  }

  
  /** 需要特殊控制编辑性的字段 */
  private static final String[] MYBODYKEY = new String[] {
    // 主含税单价、主无税单价
    SaleOrderBVO.NORIGTAXPRICE,
    SaleOrderBVO.NORIGPRICE,
    // 主含税净价、主无税净价
    SaleOrderBVO.NORIGTAXNETPRICE,
    SaleOrderBVO.NORIGNETPRICE,
    // 含税单价、无税单价
    SaleOrderBVO.NQTORIGTAXPRICE,
    SaleOrderBVO.NQTORIGPRICE,
    // 含税净价、无税净价
    SaleOrderBVO.NQTORIGTAXNETPRC,
    SaleOrderBVO.NQTORIGNETPRICE,
    // 税额、无税金额
    SaleOrderBVO.NORIGMNY,
    // 价税合计、折扣额
    SaleOrderBVO.NORIGTAXMNY,
    SaleOrderBVO.NORIGDISCOUNT

  };
  
  private void setHasLowerBillEdit(){
	  //不允许修改表头属性
	  for (String key : CardEditSetter.HEADKEY) {
		  if(SaleOrderHVO.DBILLDATE.equals(key)){
			  continue;
		  }
		  
	      if (null != this.cardpanel.getHeadItem(key)) {
	        this.cardpanel.getHeadItem(key).setEdit(false);
	      }
	  }
	  
	//不允许修改表体属性
	 for (String key : CardEditSetter.BODYKEY) {
        if (null != this.cardpanel.getBodyItem(key)) {
          this.cardpanel.getBodyItem(key).setEdit(false);
        }
	 }
	 
	 //仅允许修改表体部分
	 for (String key : CardEditSetter.MYBODYKEY) {
		 if (null != this.cardpanel.getBodyItem(key)) {
			 this.cardpanel.getBodyItem(key).setEdit(true);
		 }
	 }
  
  }
  
  /**
   * 费用冲抵时设置字段编辑性
   * 
   */
  public void setOffsetEdit() {
    for (String key : CardEditSetter.HEADKEY) {
      if (null != this.cardpanel.getHeadItem(key)) {
        this.cardpanel.getHeadItem(key).setEdit(false);
      }
    }
    for (String key : CardEditSetter.BODYKEY) {
      if (null != this.cardpanel.getBodyItem(key)) {
        this.cardpanel.getBodyItem(key).setEdit(false);
      }
    }
  }

  /**
   * 恢复字段原始编辑性
   * 
   */
  public void setOriginalEdit() {
    for (String key : CardEditSetter.HEADKEY) {
      if (null != this.cardpanel.getHeadItem(key)) {
        this.cardpanel.getHeadItem(key).setEdit(
            this.billform.getHmEditEnable().get(key).booleanValue());
      }
    }
    for (String key : CardEditSetter.BODYKEY) {
      if (null != this.cardpanel.getBodyItem(key)) {
        this.cardpanel.getBodyItem(key).setEdit(
            this.billform.getHmEditEnable().get(key).booleanValue());
      }
    }
  }

  /**
   * 根据赠品价格分摊标志设置卡片界面编辑性
   * 
   */
  public void setEditEnableByFlargessTypeFlag() {

    boolean isSaleOrderApportion = this.isSaleOrderApportion(this.cardpanel);
    // 做过费用冲抵的不能再根据赠品价格分摊标志设置卡片界面编辑性，否则单价和净价编辑性控制错误
    if (!isSaleOrderOffset() && isSaleOrderApportion) {
      this.setOffsetEditByFlargessTypeFlag();
    }
    else if (!isSaleOrderOffset() && !isSaleOrderApportion) {
      this.setOriginalEditByFlargessTypeFlag();
    }

  }

  /**
   * 赠品价格分摊时设置字段编辑性
   * 
   */
  public void setOffsetEditByFlargessTypeFlag() {
    for (String key : CardEditSetter.LargessApportionKey) {
      if (null != this.cardpanel.getBodyItem(key)) {
        this.cardpanel.getBodyItem(key).setEdit(false);
      }
    }
  }

  /**
   * 恢复字段原始编辑性（赠品价格分摊）
   * 
   */
  public void setOriginalEditByFlargessTypeFlag() {
    for (String key : CardEditSetter.LargessApportionKey) {
      if (null != this.cardpanel.getBodyItem(key)) {
        this.cardpanel.getBodyItem(key).setEdit(
            this.billform.getHmEditEnable().get(key).booleanValue());
      }
    }
  }

  /**
   * 根据表体行判断订单是否进行了赠品价格分摊
   * 
   * @param saleordervo
   * @return
   */
  private boolean isSaleOrderApportion(BillCardPanel cardPanel) {
    int bodycount = cardPanel.getRowCount();
    for (int i = 0; i < bodycount; i++) {
      Integer largesstypeflag =
          ValueUtils.getInteger(cardPanel.getBodyValueAt(i,
              SaleOrderBVO.FLARGESSTYPEFLAG));
      if (Largesstype.APPORTIONMATERIAL.equalsValue(largesstypeflag)
          || Largesstype.APPORTIONLARGESS.equalsValue(largesstypeflag)) {
        return true;
      }
    }
    return false;
  }
  
  private boolean isSaleOrderOffset() {
    IKeyValue keyvalue = new CardKeyValue(this.cardpanel);
    UFBoolean offsetfalg =
        keyvalue.getHeadUFBooleanValue(SaleOrderHVO.BOFFSETFLAG);
    return offsetfalg == null ? false : offsetfalg.booleanValue();
  }

}
