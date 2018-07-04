package nc.ui.so.m30.billui.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30.ISaleOrgPubService;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.AbstractBodyTableExtendAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.util.CardPanelValueUtils;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.NCAction;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.enumeration.Fretexchange;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 销售订单换货功能
 * 
 * @since 6.0
 * @version 2011-7-19 下午08:08:19
 * @author fengjb
 */
public class SaleOrderExchangeAction extends NCAction {

  private static final long serialVersionUID = -7702885293551172926L;

  private SaleOrderBillForm editor;

  private AbstractBodyTableExtendAction lineaction;

  private BillManageModel model;

  public SaleOrderExchangeAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_EXCHANGE);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    // 退货行序号
    int retindex = this.getCardPanel().getBillTable().getSelectedRow();
    if (retindex == -1) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006011_0", "04006011-0236")/*请先选择进行换货的退货行。*/);
    }
    BillCardPanel cardPanel = this.getCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 校验换货行合法性
    if (!this.checkExchangeVality(keyValue, retindex)) {
      return;
    }
    // 退货行ID获取
    this.fillRetRowID(keyValue, retindex);

    CardPanelValueUtils util = new CardPanelValueUtils(this.getCardPanel());
    SaleOrderBVO selectbvo = util.getBodyValueVO(retindex, SaleOrderBVO.class);
    // 行动作
    this.getLineaction().actionPerformed(null);

    // 换货行数据
    int exchangeindex = this.getCardPanel().getBillTable().getSelectedRow();
    SaleOrderBVO exchangebvo =
        util.getBodyValueVO(exchangeindex, SaleOrderBVO.class);
    exchangebvo = this.getExchangeVO(selectbvo, exchangebvo);
    util.setBodyValueVO(exchangeindex, exchangebvo);
    for (int i = 0; i <keyValue.getBodyCount(); i++) {
      this.getCardPanel().getBillModel().execLoadFormulaByRow(i);
    }
  }

  public SaleOrderBillForm getEditor() {
    return this.editor;
  }

  public AbstractBodyTableExtendAction getLineaction() {
    return this.lineaction;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public void setEditor(SaleOrderBillForm editor) {
    this.editor = editor;
  }

  public void setLineaction(AbstractBodyTableExtendAction lineaction) {
    this.lineaction = lineaction;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {

    AppUiState uistate = this.getModel().getAppUiState();
    return AppUiState.EDIT == uistate || AppUiState.ADD == uistate
        || AppUiState.COPY_ADD == uistate
        || AppUiState.TRANSFERBILL_ADD == uistate;

  }

  private boolean checkExchangeVality(IKeyValue keyValue, int retrow) {
    // 动作合法性
    String marvid =
        keyValue.getBodyStringValue(retrow, SaleOrderBVO.CMATERIALVID);
    UFDouble num = keyValue.getBodyUFDoubleValue(retrow, SaleOrderBVO.NNUM);

    StringBuilder errmsg = new StringBuilder();

    if (PubAppTool.isNull(marvid)) {
      errmsg.append(NCLangRes.getInstance().getStrByID("4006011_0",
          "04006011-0237")/*进行换货的退货行物料不允许为空。\n\r*/);
    }

    if (null == num || MathTool.compareTo(num, UFDouble.ZERO_DBL) >= 0) {
      errmsg.append(NCLangRes.getInstance().getStrByID("4006011_0",
          "04006011-0238")/*进行换货的退货行数量不允许为空或者大于等于0。*/);
    }
    if (errmsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(errmsg.toString());
    }
    return true;
  }

  private void fillRetRowID(IKeyValue keyValue, int retrow) {
    String orderbid =
        keyValue.getBodyStringValue(retrow, SaleOrderBVO.CSALEORDERBID);
    if (!PubAppTool.isNull(orderbid)) {
      return;
    }

    try {
      ISaleOrgPubService saleorgservice =
          NCLocator.getInstance().lookup(ISaleOrgPubService.class);
      String[] ids = saleorgservice.getOIDArray(1);
      orderbid = ids[0];
      // 界面值同步
      keyValue.setBodyValue(retrow, SaleOrderBVO.CSALEORDERBID, orderbid);
    }
    catch (BusinessException be) {
      ExceptionUtils.wrappException(be);
    }
  }

  private BillCardPanel getCardPanel() {
    return this.getEditor().getBillCardPanel();
  }

  private SaleOrderBVO getExchangeVO(SaleOrderBVO srcretvo,
      SaleOrderBVO exchangevo) {

    String[] copyitemkeys =
        new String[] {
          SaleOrderBVO.PK_ORG, SaleOrderBVO.DBILLDATE, SaleOrderBVO.PK_GROUP,
          SaleOrderBVO.CMATERIALVID, SaleOrderBVO.CMATERIALID,
          SaleOrderBVO.CPRODUCTORID, SaleOrderBVO.CMATERIALID,
          SaleOrderBVO.CVENDORID, SaleOrderBVO.CFACTORYID,
          SaleOrderBVO.CQUALITYLEVELID, SaleOrderBVO.CPROJECTID,
          SaleOrderBVO.CUNITID, SaleOrderBVO.CASTUNITID,
          SaleOrderBVO.VCHANGERATE, SaleOrderBVO.CQTUNITID,
          SaleOrderBVO.VQTUNITRATE, SaleOrderBVO.NTAXRATE,
          SaleOrderBVO.NITEMDISCOUNTRATE, SaleOrderBVO.NDISCOUNTRATE,
          SaleOrderBVO.CCURRENCYID, SaleOrderBVO.NEXCHANGERATE,
          SaleOrderBVO.NQTORIGTAXPRICE, SaleOrderBVO.NQTORIGPRICE,
          SaleOrderBVO.NQTORIGTAXNETPRC, SaleOrderBVO.NQTORIGNETPRICE,
          SaleOrderBVO.NORIGPRICE, SaleOrderBVO.NORIGTAXPRICE,
          SaleOrderBVO.NORIGNETPRICE, SaleOrderBVO.NORIGTAXNETPRICE,
          SaleOrderBVO.NQTTAXNETPRICE, SaleOrderBVO.NQTNETPRICE,
          SaleOrderBVO.NQTTAXPRICE, SaleOrderBVO.NQTPRICE, SaleOrderBVO.NPRICE,
          SaleOrderBVO.NTAXPRICE, SaleOrderBVO.NNETPRICE,
          SaleOrderBVO.NTAXNETPRICE, SaleOrderBVO.NGROUPEXCHGRATE,
          SaleOrderBVO.NGLOBALEXCHGRATE, SaleOrderBVO.BLARGESSFLAG,
          SaleOrderBVO.CPRODLINEID, SaleOrderBVO.BLABORFLAG,
          SaleOrderBVO.BDISCOUNTFLAG, SaleOrderBVO.VBATCHCODE,
          SaleOrderBVO.PK_BATCHCODE, SaleOrderBVO.CRECEIVECUSTID,
          SaleOrderBVO.CRECEIVEAREAID, SaleOrderBVO.CRECEIVEADDRID,
          SaleOrderBVO.CRECEIVEADDDOCID, SaleOrderBVO.CSENDSTOCKORGVID,
          SaleOrderBVO.CSENDSTOCKORGID, SaleOrderBVO.CSENDSTORDOCID,
          SaleOrderBVO.CSETTLEORGVID, SaleOrderBVO.CSETTLEORGID,
          SaleOrderBVO.CARORGVID, SaleOrderBVO.CARORGID,
          SaleOrderBVO.CTRAFFICORGVID, SaleOrderBVO.CTRAFFICORGID,
          SaleOrderBVO.CPROFITCENTERVID, SaleOrderBVO.CPROFITCENTERID,
          SaleOrderBVO.BJCZXSFLAG, SaleOrderBVO.VFREE1, SaleOrderBVO.VFREE2,
          SaleOrderBVO.VFREE3, SaleOrderBVO.VFREE4, SaleOrderBVO.VFREE5,
          SaleOrderBVO.VFREE6, SaleOrderBVO.VFREE7, SaleOrderBVO.VFREE8,
          SaleOrderBVO.VFREE9, SaleOrderBVO.VFREE10, SaleOrderBVO.VBDEF1,
          SaleOrderBVO.VBDEF2, SaleOrderBVO.VBDEF3, SaleOrderBVO.VBDEF4,
          SaleOrderBVO.VBDEF5, SaleOrderBVO.VBDEF6, SaleOrderBVO.VBDEF7,
          SaleOrderBVO.VBDEF8, SaleOrderBVO.VBDEF9, SaleOrderBVO.VBDEF10,
          SaleOrderBVO.VBDEF11, SaleOrderBVO.VBDEF12, SaleOrderBVO.VBDEF13,
          SaleOrderBVO.VBDEF14, SaleOrderBVO.VBDEF15, SaleOrderBVO.VBDEF16,
          SaleOrderBVO.VBDEF17, SaleOrderBVO.VBDEF18, SaleOrderBVO.VBDEF19,
          SaleOrderBVO.VBDEF20, SaleOrderBVO.CRECECOUNTRYID,
          SaleOrderBVO.CSENDCOUNTRYID, SaleOrderBVO.FBUYSELLFLAG,
          SaleOrderBVO.BTRIATRADEFLAG, SaleOrderBVO.CTAXCODEID,
          SaleOrderBVO.FTAXTYPEFLAG, SaleOrderBVO.CORIGCOUNTRYID,
          SaleOrderBVO.CORIGAREAID, SaleOrderBVO.CTAXCOUNTRYID,
          SaleOrderBVO.CSPROFITCENTERID,SaleOrderBVO.CSPROFITCENTERVID,
          SaleOrderBVO.DSENDDATE,SaleOrderBVO.DRECEIVEDATE
        };

    String[] oppitemkeys =
        new String[] {
          SaleOrderBVO.NNUM, SaleOrderBVO.NASTNUM, SaleOrderBVO.NQTUNITNUM,
          SaleOrderBVO.NORIGMNY, SaleOrderBVO.NORIGTAXMNY,
          SaleOrderBVO.NORIGDISCOUNT, SaleOrderBVO.NTAX,
          SaleOrderBVO.NCALTAXMNY, SaleOrderBVO.NMNY, SaleOrderBVO.NTAXMNY,
          SaleOrderBVO.NDISCOUNT, SaleOrderBVO.NWEIGHT, SaleOrderBVO.NVOLUME,
          SaleOrderBVO.NPIECE, SaleOrderBVO.NGROUPMNY,
          SaleOrderBVO.NGROUPTAXMNY, SaleOrderBVO.NGLOBALMNY,
          SaleOrderBVO.NGLOBALTAXMNY
        };
    for (String key : copyitemkeys) {
      exchangevo.setAttributeValue(key, srcretvo.getAttributeValue(key));
    }
    for (String key : oppitemkeys) {
      UFDouble ufvalue = (UFDouble) srcretvo.getAttributeValue(key);
      exchangevo.setAttributeValue(key, MathTool.oppose(ufvalue));
    }
    // CEXCHANGESRCRETID
    exchangevo.setCexchangesrcretid(srcretvo.getPrimaryKey());

    // 换货标记
    exchangevo.setFretexchange(Fretexchange.EXCHANGE.getIntegerValue());

    return exchangevo;
  }
}
