package nc.ui.so.salequotation.pub;

import java.util.HashSet;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.calculator.data.BillCardPanelDataSet;
import nc.ui.pubapp.pub.scale.UIScaleUtils;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.pub.util.SOSysParaInitUtil;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;

public class SalequoCalculator {

  /**
   * 继承数量单价金额计算的公共卡片模板数据集
   */
  private static class SalequoCardDataSet extends BillCardPanelDataSet {

    public SalequoCardDataSet(BillCardPanel cardPanel, int row,
        IRelationForItems item) {
      super(cardPanel, row, item);
    }

    /** 获得原币币种 */
    @Override
    public String getCorigcurrencyid() {
      String value =
          (String) this.getBillCardPanel()
              .getHeadTailItem(SalequotationHVO.PK_CURRTYPE).getValueObject();
      return value;
    }

    /** 获得集团 */
    @Override
    public String getPk_group() {
      String value =
          (String) this.getBillCardPanel()
              .getHeadTailItem(SalequotationHVO.PK_GROUP).getValueObject();
      return value;
    }

    @Override
    public String getCcurrencyid() {
      // 由于单价金额算法取到本位币为空，会强行取组织本位币，导致计算出的本币金额错误
      // 因此这里应uap陈玉良要求，返回一个非null的值。
      String ret = "unreachable";
      return ret;
    }

    @Override
    public boolean hasItemKey(String key) {
      boolean retValue = true;
      if (SalequotationHVO.PK_CURRTYPE.equals(key)) {
        retValue = true;
      }
      else if (SalequotationHVO.PK_GROUP.equals(key)) {
        retValue = true;
      }
      else {
        retValue = super.hasItemKey(key);
      }
      return retValue;
    }
  }

  private static final String[] STRNEEDCALKEY = new String[] {
    // 数量、主数量、换算率、报价换算率
    SalequotationBVO.NASSISTNUM,
    SalequotationBVO.NNUM,
    SalequotationBVO.NCHANGERATE,
    SalequotationBVO.NQTCHANGERATE,
    // 单位、主单位、报价单位
    SalequotationBVO.CASTUNITID,
    SalequotationBVO.PK_UNIT,
    SalequotationBVO.CQTUNITID,
    // 报价单位数量、报价换算率、税率
    SalequotationBVO.NQTNUM,
    SalequotationBVO.NCHANGERATE,
    SalequotationBVO.NTAXRATE,
    // 整单折扣、单品折扣、主含税单价、主无税单价
    SalequotationBVO.NDISCOUNTRATE, SalequotationBVO.NITEMDISCOUNTRATE,
    SalequotationBVO.NORIGTAXPRICE,
    SalequotationBVO.NORIGPRICE,
    // 主含税净价、主无税净价、含税单价
    SalequotationBVO.NORIGTAXNETPRICE, SalequotationBVO.NORIGNETPRICE,
    SalequotationBVO.NQTORIGTAXPRICE,
    // 无税单价、含税净价、无税净价
    SalequotationBVO.NQTORIGPRICE, SalequotationBVO.NQTORIGTAXNETPRC,
    SalequotationBVO.NQTORIGNETPRICE,
    // 无税金额、价税合计
    /*--去掉税额字段，因为报价单没有本币字段，报价单税额取原币税额，不可编辑 wangtwa2012-03-01-*/
    SalequotationBVO.NORIGMNY, SalequotationBVO.NORIGTAXMNY,
    // 折扣额、税率
    SalequotationBVO.NORIGDISCOUNT, SalequotationBVO.NTAXRATE,
    // 扣税类别
    SalequotationBVO.FTAXTYPEFLAG
  };

  private boolean bSCM13 = true;

  private final BillCardPanel cardpanel;

  private Set<String> hsNeedCalKey;

  private boolean isChgPriceOrDiscount;

  private boolean isfix;

  private boolean isqtfix;

  private boolean origCurToGlobalMoney;

  public SalequoCalculator(BillCardPanel cardpanel) {
    this.cardpanel = cardpanel;
  }

  public void calculate(int[] rows, String itemKey) {
    // 如果编辑字段不会影响到数量单价金额，不进行计算
    if (!this.getNeedCalKey().contains(itemKey)) {
      return;
    }
    // 1.创建数据映射实例 获得数据项之间的映射关系
    RelationItemForCal item = new RelationItemForCal();
    // item.setNqtorignetpriceKey("nqtorignetprice");
    item.setNchangerateKey(SalequotationBVO.NCHANGERATE);
    item.setNqtunitrateKey(SalequotationBVO.NQTCHANGERATE);
    item.setCunitidKey(SalequotationBVO.PK_UNIT);
    item.setNqtunitnumKey(SalequotationBVO.NQTNUM);
    item.setPk_org(SalequotationHVO.PK_ORG);
    item.setNassistnumKey(SalequotationBVO.NASSISTNUM);
    for (int row : rows) {

      // 2.创建数据集实例 初始化数据关系计算用的数据集
      IDataSetForCal data = new SalequoCardDataSet(this.cardpanel, row, item);
      ScaleUtils scale = UIScaleUtils.getScaleUtils();
      Calculator tool = new Calculator(data, scale);

      // 创建参数实例，在计算的时候用来获得参数条件：是否含税优先等
      Condition cond = new Condition();
      Integer buysellflag =
          (Integer) this.cardpanel.getBodyValueAt(row,
              SalequotationBVO.FBUYSELLFLAG);
      Boolean international = BuySellFlagEnum.OUTPUT.equalsValue(buysellflag);
      cond.setInternational(international);
      this.initPara(row);

      // 设置是否进行本币换算
      cond.setIsCalLocalCurr(false);
      // 设置调单价方式调折扣
      cond.setIsChgPriceOrDiscount(this.isChgPriceOrDiscount());
      // 设置含税优先还是无税优先
      cond.setIsTaxOrNet(this.bSCM13);
      // 设置是否固定换算率
      cond.setIsFixNchangerate(this.isfix);
      // 设置是否固定报价换算率
      cond.setIsFixNqtunitrate(this.isqtfix);
      // 设置NC001参数
      cond.setGroupLocalCurrencyEnable(false);
      cond.setOrigCurToGroupMoney(false);
      // 设置NC002参数
      cond.setGlobalLocalCurrencyEnable(false);
      cond.setOrigCurToGlobalMoney(false);

      // 两个参数 cond 为计算时的参数条件
      tool.calculate(cond, itemKey);
    }

  }

  private Set<String> getNeedCalKey() {
    if (null == this.hsNeedCalKey) {
      this.hsNeedCalKey = new HashSet<String>();
      for (String key : SalequoCalculator.STRNEEDCALKEY) {
        this.hsNeedCalKey.add(key);
      }
    }
    return this.hsNeedCalKey;
  }

  /**
   * 初始Condition要用到的参数
   */
  private void initPara(int row) {
    String pk_group =
        (String) this.cardpanel.getHeadItem(SalequotationHVO.PK_GROUP)
            .getValueObject();

    // 1.参数：SO23:是否含税优先
    UFBoolean scm13 = SOSysParaInitUtil.getSO23(pk_group);
    if (null == scm13) {
      scm13 = UFBoolean.FALSE;
    }
    this.bSCM13 = scm13.booleanValue();
    // 3.是否固定单位换算率
    String cmaterialvid =
        (String) this.cardpanel.getBodyValueAt(row,
            SalequotationBVO.PK_MATERIAL_V);
    String cunitid =
        (String) this.cardpanel.getBodyValueAt(row, SalequotationBVO.PK_UNIT);
    String castunitid =
        (String) this.cardpanel
            .getBodyValueAt(row, SalequotationBVO.CASTUNITID);
    String cqtunitid =
        (String) this.cardpanel.getBodyValueAt(row, SalequotationBVO.CQTUNITID);
    if (!PubAppTool.isNull(cmaterialvid) && !PubAppTool.isNull(castunitid)
        && !PubAppTool.isNull(cunitid)) {
      this.isfix =
          MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(cmaterialvid,
              cunitid, castunitid);
    }
    // 4.是否固定报价单位换算率
    if (!PubAppTool.isNull(cmaterialvid) && !PubAppTool.isNull(cqtunitid)
        && !PubAppTool.isNull(cunitid)) {
      this.isqtfix =
          MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(cmaterialvid,
              cunitid, cqtunitid);
    }

  }

  public boolean isChgPriceOrDiscount() {
    return this.isChgPriceOrDiscount;
  }

  public void setIsChgPriceOrDiscount(boolean isChgPriceOrDiscount) {
    this.isChgPriceOrDiscount = isChgPriceOrDiscount;
  }
}
