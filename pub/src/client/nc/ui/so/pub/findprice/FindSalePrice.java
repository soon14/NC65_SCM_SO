package nc.ui.so.pub.findprice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.framework.common.NCLocator;
import nc.itf.price.pub.service.IFindSalePrice;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.pub.scale.UIScaleUtils;
import nc.ui.so.pub.editable.SOCardEditableSetter;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.price.pub.PriceSysParamContext;
import nc.vo.price.pub.entity.FindPPLimitPriceResultVO;
import nc.vo.price.pub.entity.FindPriceParaVO;
import nc.vo.price.pub.entity.FindPriceResultVO;
import nc.vo.price.pub.entity.PromoteLimitPara;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.scmpub.parameter.SCMParameterUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.enumeration.Fretexchange;
import nc.vo.so.m30.enumeration.Largesstype;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.enumeration.AskPriceRule;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.FretexchangeRule;
import nc.vo.so.pub.util.SOSysParaInitUtil;

/**
 * 销售管理询价处理类
 * 
 * @since 6.0
 * @version 2011-6-13 下午02:14:11
 * @author fengjb
 */
public class FindSalePrice {

  // 卡片模板
  private BillCardPanel cardPanel;

  // 配置文件
  private IFindPriceConfig config;

  // jilu for 是否销售订单询价
  private boolean isSaleorder = false;

  // 询价失败行
  private int[] failrows;

  /***
   * 赠品分摊行
   */
  private int[] flargessrows;

  // 询价成功行
  private Map<Integer, FindPriceResultVO> hmSucess =
      new HashMap<Integer, FindPriceResultVO>();

  private IKeyValue keyValue;

  /**
   * 构造子
   * 
   * @param billCardPanel
   * @param config
   */
  public FindSalePrice(BillCardPanel billCardPanel, IFindPriceConfig config) {
    this.cardPanel = billCardPanel;
    this.keyValue = new CardKeyValue(this.cardPanel);
    this.config = config;
  }

  /**
   * 询主记账单价
   * 
   * @param rows
   * @param editkey
   */
  public void forceFindAccPrice(int[] rows, String editkey) {
    // 判定价格是否启用
    if (!SysInitGroupQuery.isPRICEEnabled()) {
      return;
    }
    // 参数合法性检查
    if (null == rows || rows.length == 0) {
      return;
    }
    // 1.询价策略,判定是否询价
    Integer askrule = this.config.getAskPriceRule();
    if (AskPriceRule.ASKPRICE_NO.equalsValue(askrule)) {
      return;
    }
    // 2.编辑字段是否触发询价
    if (!this.isTrigFindPrice(editkey)) {
      return;
    }
    // 3.过滤出询价行
    int[] askrows = this.filterFindRows(rows);
    if (askrows.length == 0) {
      return;
    }
    // 4.组织询价VOs
    FindPriceParaVO[] paraVOs = this.getFindPriceParaVOs(askrows);

    // 5.询价
    String pk_org = this.keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    FindPriceResultVO[] resultVOs = this.getFindPriceResultVOs(paraVOs, pk_org);
    if (null == resultVOs || resultVOs.length == 0) {
      return;
    }
    // 6.分离询价成功行与失败行
    this.splitFindResult(resultVOs, askrows);
    // 7.成功行：设值并单价金额计算
    this.setSucessResult();
    // 10.失败行：抛错误提示信息
    this.showFailMsg();
  }

  private void setSucessResult() {
    for (Entry<Integer, FindPriceResultVO> entry : this.hmSucess.entrySet()) {
      int row = entry.getKey().intValue();
      FindPriceResultVO resultVO = entry.getValue();

      UFDouble price = resultVO.getPrice();
      String vchangerate =
          this.keyValue.getBodyStringValue(row, SOItemKey.VCHANGERATE);
      // 主单价=附单价*换算率
      UFDouble naccprice = HslParseUtil.hslMultiplyUFDouble(vchangerate, price);
      // 主记账单价
      this.keyValue.setBodyValue(row, SOItemKey.NACCPRICE, naccprice);
    }
  }

  /**
   * 编辑字段后询价算法
   * 
   * @param row
   * @param editkey
   */
  public void findPriceAfterEdit(int row, String editkey) {
    int[] rows = new int[] {
      row
    };
    this.findPrice(rows, editkey, false);
  }

  /**
   * 编辑字段后询价算法
   * 
   * @param rows
   * @param editkey
   */
  public void findPriceAfterEdit(int[] rows, String editkey) {
    this.findPrice(rows, editkey, false);
  }

  /**
   * 点击询价按钮，强制询价方法
   * 
   * @param rows
   */
  public void forceFindPrice(int[] rows) {
    this.findPrice(rows, null, true);
  }

  /**
   * 价格组成编辑——根据询价resultVO设值并单价金额计算
   * 
   * @param row
   *          询价结果设置行
   * @param resultVO
   *          询价结果VO
   * 
   */
  public void setResultAfterPriceFormEdit(int row, FindPriceResultVO resultVO) {
    // 1.设置成功行
    this.hmSucess.put(Integer.valueOf(row), resultVO);
    // 2.卡片设值
    this.setSucessResult(true);
  }

  private void clearFailRow() {

    String[] clearitems =
        new String[] {
          SOItemKey.NORIGTAXPRICE, SOItemKey.NORIGTAXNETPRICE,
          SOItemKey.NORIGPRICE, SOItemKey.NORIGNETPRICE, SOItemKey.NORIGTAX,
          SOItemKey.NORIGTAXMNY, SOItemKey.NORIGMNY, SOItemKey.NORIGDISCOUNT,

          SOItemKey.NTAXPRICE, SOItemKey.NTAXNETPRICE, SOItemKey.NPRICE,
          SOItemKey.NNETPRICE, SOItemKey.NTAX, SOItemKey.NTAXMNY,
          SOItemKey.NMNY, SOItemKey.NDISCOUNT,

          SOItemKey.NQTORIGTAXPRICE, SOItemKey.NQTORIGTAXNETPRC,
          SOItemKey.NQTORIGPRICE, SOItemKey.NQTORIGNETPRICE,

          SOItemKey.NQTTAXPRICE, SOItemKey.NQTTAXNETPRICE, SOItemKey.NQTPRICE,
          SOItemKey.NQTNETPRICE,

          SOItemKey.NASKQTORIGPRICE, SOItemKey.NASKQTORIGNETPRICE,
          SOItemKey.NASKQTORIGTAXPRC, SOItemKey.NASKQTORIGTXNTPRC,
          SOItemKey.NGLOBALMNY, SOItemKey.NGLOBALTAXMNY, SOItemKey.NGROUPMNY,
          SOItemKey.NGROUPTAXMNY,

          SOItemKey.CPRICEPOLICYID, SOItemKey.CPRICEITEMID,
          SOItemKey.CPRICEITEMTABLEID, SOItemKey.CPRICEFORMID,
          SOItemKey.NCALTAXMNY

        };
    for (int row : this.failrows) {
      for (String key : clearitems) {
        this.keyValue.setBodyValue(row, key, null);
      }
    }

    this.config.processAskFailRows(this.failrows);

  }

  /**
   * 过滤需要询价行
   * 
   * @param rows
   * @return
   */
  private int[] filterFindRows(int[] rows) {
    boolean isLarAsk = this.config.isLargessAskPrice();
    List<Integer> alfindrow = new ArrayList<Integer>();
    List<Integer> flargessrowlist = new ArrayList<Integer>();
    for (int row : rows) {
      // --物料空不询价
      String marid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CMATERIALID);
      if (PubAppTool.isNull(marid)) {
        continue;
      }
      // --根据参数赠品是否询价过滤
      UFBoolean blargess =
          this.keyValue.getBodyUFBooleanValue(row, SOItemKey.BLARGESSFLAG);
      if (null != blargess && blargess.booleanValue() && !isLarAsk) {
        continue;
      }
      // --赠品分摊的不询价
      Integer flargesstypeflag =
          this.keyValue.getBodyIntegerValue(row, SOItemKey.FLARGESSTYPEFLAG);
      if (flargesstypeflag != null
          && (Largesstype.APPORTIONMATERIAL.getIntegerValue().equals(
              flargesstypeflag) || Largesstype.APPORTIONLARGESS
              .getIntegerValue().equals(flargesstypeflag))) {
        flargessrowlist.add(Integer.valueOf(row));
        continue;
      }
      alfindrow.add(Integer.valueOf(row));
    }
    this.flargessrows = this.changeIntegerListToIntArray(flargessrowlist);
    return changeIntegerListToIntArray(alfindrow);
  }

  /**
   * 
   * 
   * @param rows
   * @param editkey
   */
  private void findPrice(int[] rows, String editkey, boolean isforce) {
    // 判定价格是否启用
    if (!SysInitGroupQuery.isPRICEEnabled()) {
      return;
    }
    // 参数合法性检查
    if (null == rows || rows.length == 0) {
      return;
    }
    // 1.询价策略,判定是否询价
    Integer askrule = this.config.getAskPriceRule();
    if (AskPriceRule.ASKPRICE_NO.equalsValue(askrule)) {
      return;
    }
    // 2.编辑字段是否触发询价
    if (!isforce && !this.isTrigFindPrice(editkey)) {
      return;
    }
    // 3.过滤出询价行
    int[] askrows = this.filterFindRows(rows);
    if (askrows.length == 0) {
      this.showFlargessMsg();
      return;
    }
    // 4.组织询价VOs
    FindPriceParaVO[] paraVOs = this.getFindPriceParaVOs(askrows);

    // 限量促销询价参数 jilu for 恒安
    PromoteLimitPara[] promotelimitparas = this.getPromotelimitparas(askrows);
    // 5.询价
    String pk_org = this.keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    FindPPLimitPriceResultVO resultVO =
        this.getFindPriceResultVOs(paraVOs, promotelimitparas, pk_org);
    if (null == resultVO.getResultvos() || resultVO.getResultvos().length == 0) {
      return;
    }
    // 6.分离询价成功行与失败行
    // this.splitFindResult(resultVOs, askrows);
    this.splitFindResult(resultVO.getResultvos(), askrows);
    // 7.成功行：设值并单价金额计算
    this.setSucessResult(false);
    // 7.1成功行：提示信息 jilu for 恒安
    this.showSucessMsg(resultVO);
    // 8.失败行：清空单价金额字段
    this.clearFailRow();
    // 9.询价后根据交易类型属性设置编辑性
    this.setFindRowEdit();
    // 10.失败行：抛错误提示信息
    this.showFailMsg();
  }

  private void showFlargessMsg() {
    if (this.flargessrows == null || flargessrows.length == 0) {
      return;
    }
    StringBuilder flargessMsg = new StringBuilder();
    for (int row : this.flargessrows) {
      String crowno = this.keyValue.getBodyStringValue(row, SOItemKey.CROWNO);
      flargessMsg.append("[");
      flargessMsg.append(crowno);
      flargessMsg.append("]");
      flargessMsg.append(NCLangRes.getInstance().getStrByID("4006004_0",
          "04006004-0206")/* 、 */);
    }
    flargessMsg.deleteCharAt(flargessMsg.length() - 1);
    String flargessMsgForTranslate =
        NCLangRes.getInstance().getStrByID("4006004_0", "04006004-0243", null,
            new String[] {
              flargessMsg.toString()
            })/* 第 {0} 进行了赠品分摊，不能进行询价操作！ */;
    MessageDialog.showHintDlg(this.cardPanel, NCLangRes.getInstance()
        .getStrByID("4006004_0", "04006004-0019")/* 提示 */,
        flargessMsgForTranslate);

  }

  private void showSucessMsg(FindPPLimitPriceResultVO resultVO) {
    if (!this.isSaleorder) {
      return;
    }
    UFBoolean Errorflag = resultVO.getHasErrorMsg();
    if (Errorflag != null && Errorflag.booleanValue()) {
      MessageDialog.showHintDlg(this.cardPanel, NCLangRes.getInstance()
          .getStrByID("4006004_0", "04006004-0019")/* 提示 */, resultVO
          .getErrorMsg());
    }
  }

  private PromoteLimitPara[] getPromotelimitparas(int[] askrows) {
    PromoteLimitPara[] promotelimitparas = new PromoteLimitPara[askrows.length];
    int i = 0;
    for (int row : askrows) {
      promotelimitparas[i] = new PromoteLimitPara();
      // 询价行ID
      String saleorderbid =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.CSALEORDERBID);
      promotelimitparas[i].setFindpricebillbid(saleorderbid);
      // 单据类型编码
      promotelimitparas[i].setBilltypecode(SOBillType.Order.getCode());
      // 询价行号
      String rowno = this.keyValue.getBodyStringValue(row, SaleOrderBVO.CROWNO);
      promotelimitparas[i].setFindpricerowNo(rowno);
      // 询价行的来源行id
      String srcbid =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.CSRCBID);
      promotelimitparas[i].setFindpricesrcbillbid(srcbid);
      // 询价行的来源单据类型编码
      String srctype =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.VSRCTYPE);
      promotelimitparas[i].setSrcbilltypecode(srctype);
      i++;
    }

    return promotelimitparas;
  }

  /**
   * 方法功能描述：获得需要询价参数VOs
   * 
   * @param askrows
   * @author 刘志伟
   * @time 2010-5-31 下午01:42:30
   */
  private FindPriceParaVO[] getFindPriceParaVOs(int[] askrows) {

    FindPriceParaVO[] paraVOs = new FindPriceParaVO[askrows.length];
    // 集团
    String pk_group = AppUiContext.getInstance().getPkGroup();
    // 销售组织
    String saleorg = this.keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    // 客户
    String customer = this.keyValue.getHeadStringValue(SOItemKey.CCUSTOMERID);
    // 结算方式
    String balancetype =
        this.keyValue.getHeadStringValue(SOItemKey.CBALANCETYPEID);
    // 渠道类型
    String channeltype =
        this.keyValue.getHeadStringValue(SOItemKey.CCHANNELTYPEID);
    // 原币币种
    String currtype =
        this.keyValue.getHeadStringValue(SOItemKey.CORIGCURRENCYID);
    // 单据日期
    UFDate billdate = this.keyValue.getHeadUFDateValue(SOItemKey.DBILLDATE);
    UFDateTime datetime = null;
    if (null != billdate) {
      datetime = new UFDateTime(billdate.toString());
    }
    // 是否询促销价
    UFBoolean ispromote = this.isFindPromotePrice();
    // 运输方式
    String tranporttype =
        this.keyValue.getHeadStringValue(SOItemKey.CTRANSPORTTYPEID);
    // 交易类型
    String trantypeid = this.keyValue.getHeadStringValue(SOItemKey.CTRANTYPEID);

    // jilu for 恒安
    String trantypecode =
        this.keyValue.getHeadStringValue(SOItemKey.VTRANTYPECODE);
    String[] billtype = trantypecode.split("-");
    if (SOBillType.Order.getCode().equals(billtype[0])) {
      this.isSaleorder = true;
    }
    // end

    UFBoolean bSA09 = this.getPriceSysPara(saleorg);
    AggregatedValueObject bill = null;
    if (bSA09.booleanValue()) {
      bill = this.config.getBillVO();
    }
    int i = 0;
    for (int row : askrows) {
      paraVOs[i] = new FindPriceParaVO();
      // 专为参数SA09（传递单据所有数据项进行询价）使用的字段，二次开发可使用这个字段传递单据
      if (bSA09.booleanValue()) {
        paraVOs[i].setBill(bill);
      }
      paraVOs[i].setPk_group(pk_group);
      paraVOs[i].setPk_org(saleorg);
      paraVOs[i].setPk_customer(customer);
      paraVOs[i].setPk_balatype(balancetype);
      paraVOs[i].setPk_channeltype(channeltype);
      paraVOs[i].setPk_currtype(currtype);
      paraVOs[i].setTpricedate(datetime);

      UFDouble num =
          this.keyValue.getBodyUFDoubleValue(row, SOItemKey.NQTUNITNUM);
      paraVOs[i].setNnum(null == num ? UFDouble.ONE_DBL : num);

      String materialid =
          this.keyValue.getBodyStringValue(row, SOItemKey.CMATERIALID);
      paraVOs[i].setPk_material(materialid);

      String unit = this.keyValue.getBodyStringValue(row, SOItemKey.CQTUNITID);
      paraVOs[i].setPk_unit(unit);
      // 正常询价即询促销价
      paraVOs[i].setIsFindPromotePrice(ispromote);

      // 是否询限量促销价
      paraVOs[i].setIsPromoteLimit(isPromoteLimit(row));

      // 运输方式
      paraVOs[i].setPk_sendtype(tranporttype);
      // 订单交易类型
      paraVOs[i].setVsaleorgtype(trantypeid);
      // 质量等级
      String qualityleve =
          this.keyValue.getBodyStringValue(row, SOItemKey.CQUALITYLEVELID);
      paraVOs[i].setPk_qualitylevel(qualityleve);
      // 收货地区
      String receivearea =
          this.keyValue.getBodyStringValue(row, SOItemKey.CRECEIVEAREAID);
      paraVOs[i].setPk_areacl(receivearea);
      // 价格项
      String priceitem =
          this.keyValue.getBodyStringValue(row, SOItemKey.CPRICEITEMID);
      paraVOs[i].setPk_pricetype(priceitem);

      // 自由辅助属性
      String vfree1 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE1);
      paraVOs[i].setVfree1(vfree1);
      String vfree2 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE2);
      paraVOs[i].setVfree2(vfree2);
      String vfree3 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE3);
      paraVOs[i].setVfree3(vfree3);
      String vfree4 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE4);
      paraVOs[i].setVfree4(vfree4);
      String vfree5 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE5);
      paraVOs[i].setVfree5(vfree5);
      String vfree6 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE6);
      paraVOs[i].setVfree6(vfree6);
      String vfree7 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE7);
      paraVOs[i].setVfree7(vfree7);
      String vfree8 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE8);
      paraVOs[i].setVfree8(vfree8);
      String vfree9 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE9);
      paraVOs[i].setVfree9(vfree9);
      String vfree10 = this.keyValue.getBodyStringValue(row, SOItemKey.VFREE10);
      paraVOs[i].setVfree10(vfree10);
      i++;
    }

    return paraVOs;
  }

  private UFBoolean isPromoteLimit(int row) {
    // 如果不是销售订单则不询限量促销
    if (!this.isSaleorder) {
      return UFBoolean.FALSE;
    }
    UFBoolean blargessflag =
        this.keyValue.getBodyUFBooleanValue(row, SOItemKey.BLARGESSFLAG);
    // 获取退换货标记
    FretexchangeRule fretexrule = new FretexchangeRule(this.keyValue);
    Integer fretexchange = fretexrule.getFretexchange(row);
    // 赠品、 退货、换货、订单交易类型为赠品兑付、订单交易类型销售模式为退换货
    if ((blargessflag != null && blargessflag.booleanValue())
        || Fretexchange.WITHDRAW.equalsValue(fretexchange)
        || Fretexchange.EXCHANGE.equalsValue(fretexchange)
        ||this.config.isblrgcashflag()||this.config.getSalemode()!=1) {
      return UFBoolean.FALSE;
    }
    return UFBoolean.TRUE;
  }

  private FindPriceResultVO[] getFindPriceResultVOs(FindPriceParaVO[] paraVOs,
      String saleOrg) {

    IFindSalePrice findpricesrv =
        NCLocator.getInstance().lookup(IFindSalePrice.class);
    FindPriceResultVO[] result = null;

    try {
      result = findpricesrv.findPrice(paraVOs, saleOrg);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return result;
    /**
     * PluginExecutor<ISOFindPrice> executor = new
     * PluginExecutor<ISOFindPrice>(ISOFindPrice.class);
     * FindPricePluginExecDelegate delegate = new
     * FindPricePluginExecDelegate(paraVOs, saleOrg); try {
     * executor.exec(delegate); } catch (Exception e) {
     * ExceptionUtils.wrappException(e); } return
     * delegate.getFindPriceResultVOs();
     **/
  }

  private FindPPLimitPriceResultVO getFindPriceResultVOs(
      FindPriceParaVO[] paraVOs, PromoteLimitPara[] promotelimitparas,
      String saleOrg) {

    IFindSalePrice findpricesrv =
        NCLocator.getInstance().lookup(IFindSalePrice.class);
    FindPPLimitPriceResultVO ppLimitresult = null;

    try {
      if (this.isSaleorder) {
        ppLimitresult =
            findpricesrv.findPriceForSO(paraVOs, promotelimitparas, saleOrg);
      }
      else {
        FindPriceResultVO[] results = findpricesrv.findPrice(paraVOs, saleOrg);
        ppLimitresult = new FindPPLimitPriceResultVO();
        ppLimitresult.setResultvos(results);
      }

    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return ppLimitresult;
  }

  private boolean getIsTax() {

    String pk_group = AppUiContext.getInstance().getPkGroup();
    UFBoolean so23 = null;

    so23 = SCMParameterUtils.getSCM13(pk_group);

    if (null == so23) {
      return false;
    }

    return so23.booleanValue();
  }

  private UFBoolean getPriceSysPara(String saleorg) {
    UFBoolean bSA09 = PriceSysParamContext.getSA09(saleorg);
    return bSA09 == null ? UFBoolean.FALSE : bSA09;
  }

  private int[] getSuccessRows() {
    int[] sucessrows = new int[this.hmSucess.size()];
    int i = 0;
    for (Entry<Integer, FindPriceResultVO> entry : this.hmSucess.entrySet()) {
      sucessrows[i] = entry.getKey().intValue();
      i++;
    }

    return sucessrows;
  }

  private UFBoolean isFindPromotePrice() {

    Integer askqtrule = this.config.getAskPriceRule();
    if (AskPriceRule.ASKPRICE_NORMAL.equalsValue(askqtrule)) {
      return UFBoolean.TRUE;
    }
    return UFBoolean.FALSE;
  }

  private boolean isTrigFindPrice(String editkey) {
    // 判断如果是价格项 就触发询价
    if (editkey.equals(SOItemKey.CPRICEITEMID)) {
      return true;
    }
    // 销售询价触发条件，判定是否询价
    String pk_org = this.keyValue.getHeadStringValue(SOItemKey.PK_ORG);
    String[] so21 = null;

    so21 = SOSysParaInitUtil.getSO21(pk_org);

    // 询价触发条件为空
    if (null == so21 || so21.length == 0) {
      return false;
    }
    for (String condition : so21) {
      if (editkey.equals(condition)) {
        return true;
      }
    }
    return false;
  }

  private void setFindRowEdit() {
    SOCardEditableSetter cardeditseter = new SOCardEditableSetter();
    int[] successRows = this.getSuccessRows();
    if (!this.config.isModifyAskSucess()) {
      cardeditseter.setBodyPriceMnyEdit(this.cardPanel, successRows, false);
    }
    if (!this.config.isModifyAskFail()) {
      cardeditseter.setBodyPriceMnyEdit(this.cardPanel, this.failrows, false);
    }

  }

  /**
   * 将询到的销售价格设置到卡片上,包括：
   * <ul>
   * <li>含税单价
   * <li>单品折扣
   * <li>定价策略
   * <li>价格项
   * <li>价目表
   * <li>价格组成
   * <li>价格促销类型
   * </ul>
   * 
   * @author 刘志伟
   * @time 2010-5-31 下午01:42:30
   */
  private void setSucessResult(boolean ispriceformedit) {
    // 询价成功行设置并计算单价金额
    boolean istax = this.getIsTax();
    // 含税/无税单价
    String pricekey =
        istax ? SOItemKey.NQTORIGTAXPRICE : SOItemKey.NQTORIGPRICE;
    String askpricekey =
        istax ? SOItemKey.NASKQTORIGTAXPRC : SOItemKey.NASKQTORIGPRICE;

    // 含税/无税净价
    String netpricekey =
        istax ? SOItemKey.NQTORIGTAXNETPRC : SOItemKey.NQTORIGNETPRICE;
    String asknetpricekey =
        istax ? SOItemKey.NASKQTORIGTXNTPRC : SOItemKey.NASKQTORIGNETPRICE;

    ScaleUtils scale = UIScaleUtils.getScaleUtils();
    String corigcurrencyid =
        this.keyValue.getHeadStringValue(SOItemKey.CORIGCURRENCYID);
    for (Entry<Integer, FindPriceResultVO> entry : this.hmSucess.entrySet()) {
      int row = entry.getKey().intValue();
      FindPriceResultVO resultVO = entry.getValue();
      UFDouble dPrice = resultVO.getPrice();
      // 考虑到整单折扣影响 报价单位净价 = 询价得到净价 * 整单折扣
      UFDouble netprice = resultVO.getNetPrice();
      UFDouble discountrate =
          this.keyValue.getBodyUFDoubleValue(row, SOItemKey.NDISCOUNTRATE);
      if (null != netprice) {
        if (null == discountrate) {
          discountrate = SOConstant.ONEHUNDRED;
        }
      }
      UFDouble nmffileprice =
          this.keyValue.getBodyUFDoubleValue(row, SOItemKey.NMFFILEPRICE);
      netprice = netprice.multiply(discountrate).div(SOConstant.ONEHUNDRED);
      netprice = MathTool.add(netprice, nmffileprice);
      netprice = scale.adjustSoPuPriceScale(netprice, corigcurrencyid);
      this.keyValue.setBodyValue(row, netpricekey, netprice);

      dPrice = scale.adjustSoPuPriceScale(dPrice, corigcurrencyid);
      this.keyValue.setBodyValue(row, pricekey, dPrice);
      // 手工编辑价格组成不改变询价信息
      if (!ispriceformedit) {

        // 询价原币含税/无税单价
        dPrice = scale.adjustSoPuPriceScale(dPrice, corigcurrencyid);
        this.keyValue.setBodyValue(row, askpricekey, dPrice);
        // 询价原币含税/无税净价
        this.keyValue.setBodyValue(row, asknetpricekey, netprice);
        // 单品折扣
        this.keyValue.setBodyValue(row, SOItemKey.NITEMDISCOUNTRATE,
            resultVO.getDiscount());
        // 定价策略
        this.keyValue.setBodyValue(row, SOItemKey.CPRICEPOLICYID,
            resultVO.getPricePolicy());
        // 价格项
        this.keyValue.setBodyValue(row, SOItemKey.CPRICEITEMID,
            resultVO.getPriceType());
        // 价目表
        this.keyValue.setBodyValue(row, SOItemKey.CPRICEITEMTABLEID,
            resultVO.getTariff());
        // 价格组成
        this.keyValue.setBodyValue(row, SOItemKey.CPRICEFORMID,
            resultVO.getPk_priceform());
        // 价格促销类型 jilu for 恒安
        if (this.isSaleorder) {
          // 价格促销类型
          this.keyValue.setBodyValue(row, SaleOrderBVO.CPRCPROMOTTYPEID,
              resultVO.getPromottype());
          // 促销价格表行ID
          this.keyValue.setBodyValue(row, SaleOrderBVO.CPROMOTPRICEID,
              resultVO.getPromotePriceID());
        }
        // end
        // 价格促销活动
        this.keyValue.setBodyValue(row, SaleOrderBVO.CPRICEPROMTACTID,
            resultVO.getMarketact());
      }
    }
    int[] sucessrows = this.getSuccessRows();
    this.config.processAskSucessRows(sucessrows, netpricekey);
  }

  /**
   * 是否有询价失败行
   * 
   * @author 刘志伟
   * @time 2010-5-31 下午01:42:30
   */
  private void showFailMsg() {
    if (!this.config.isShowMsgAskFail() || this.failrows.length == 0) {
      return;
    }

    StringBuilder failMsg = new StringBuilder();

    for (int row : this.failrows) {
      String crowno = this.keyValue.getBodyStringValue(row, SOItemKey.CROWNO);
      failMsg.append("[");
      failMsg.append(crowno);
      failMsg.append("]");
      failMsg.append(NCLangRes.getInstance().getStrByID("4006004_0",
          "04006004-0206")/* 、 */);
    }
    failMsg.deleteCharAt(failMsg.length() - 1);
    String failMsgForTranslate =
        NCLangRes.getInstance().getStrByID("4006004_0", "04006004-0018", null,
            new String[] {
              failMsg.toString()
            })/* 第 {0} 行询价失败！ */;
    MessageDialog
        .showHintDlg(
            this.cardPanel,
            NCLangRes.getInstance().getStrByID("4006004_0", "04006004-0019")/* 提示 */,
            failMsgForTranslate);

  }

  /**
   * 根据询价结果VO分类
   * <ul>
   * <li>VO != null : 成功行
   * <li>VO = null : 失败行
   * </ul>
   * <p>
   * <b>前提SCM询价公共方法已保证询价得到VOs顺序为置入询价VOs顺序相同，询价和设置结果VO已行号为相应匹配规则</b>
   * </p>
   * 
   * @param resultVOs
   * @param rows
   * @author 刘志伟
   * @time 2010-5-31 下午01:42:30
   */
  private void splitFindResult(FindPriceResultVO[] resultVOs, int[] rows) {
    int i = 0;
    ArrayList<Integer> alFailRow = new ArrayList<Integer>();
    for (int row : rows) {
      if (null == resultVOs[i]) {
        alFailRow.add(Integer.valueOf(row));
      }
      else {
        this.hmSucess.put(Integer.valueOf(row), resultVOs[i]);
      }
      i++;
    }
    this.failrows = this.changeIntegerListToIntArray(alFailRow);
  }

  /**
   * 将Integer的list转成int数组
   * 
   * @param integerList
   * @return
   */
  private int[] changeIntegerListToIntArray(List<Integer> integerList) {
    int[] rows = new int[integerList.size()];
    int i = 0;
    for (Integer row : integerList) {
      rows[i] = row.intValue();
      i++;
    }
    return rows;
  }
}
