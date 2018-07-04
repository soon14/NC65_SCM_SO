package nc.ui.so.m30.billui.action;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletLinkEvent;
import nc.funcnode.ui.FuncletLinkListener;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.uap.bbd.func.IFuncRegisterQueryService;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.util.CardPanelValueUtils;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.CardEditSetter;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.so.m30.cashsale.CashSaleData;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderUserObject;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.sobalance.util.GatherbillUtil;
import nc.vo.so.m30.sobalance.util.SoBalanceUtil;
import nc.vo.so.m30.util.CashSaleUtil;
import nc.vo.so.m30.util.OffsetItfVOUtil;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m35.entity.ArsubViewVO;
import nc.vo.so.m35.paravo.OffsetParaVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.util.SOSysParaInitUtil;
import nc.vo.so.rule.OffsetUtil;

public class SaleOrderCashSaleAction extends NCAction {

  private class CashSaleLinkListener implements FuncletLinkListener {

    public CashSaleLinkListener() {
      // TODO Auto-generated constructor stub
    }

    /**
     * 监听内做现销处理的后续工作
     */
    @Override
    public void dealLinkEvent(FuncletLinkEvent event) {
      SaleOrderUserObject userObject =
          (SaleOrderUserObject) event.getUserObject();
      SaleOrderCashSaleAction.this.setCashSaleResult(userObject);
    }
  }

  private static final long serialVersionUID = 6584570365797299304L;

  private SaleOrderBillForm editor;

  private AbstractAppModel model;

  private OffsetItfVOUtil offsetItfUtil;

  private OffsetUtil offsetUtil;

  public SaleOrderCashSaleAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_CASHPROCESS);
  }

  /**
   * 准备现销处理需要的数据
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
	  
    if (!SysInitGroupQuery.isAREnabled()) {
	         ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
		          "4006005_0", "04006005-0028")/*应收模块未启用！*/);
    }
	  
    
    CardPanelValueUtils util = new CardPanelValueUtils(this.getCardPanel());
    CardKeyValue keyValue = new CardKeyValue(this.getCardPanel());
    String tranTypeCode =
            keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    if (PubAppTool.isNull(tranTypeCode)) {
      ExceptionUtils
      .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006011_0", "04006011-0029")/*@res "请先录入交易类型"*/);
    }
    
    SaleOrderClientContext ordercontex = this.getEditor().getM30ClientContext();
    String pk_group = AppContext.getInstance().getPkGroup();
    M30TranTypeVO trantypevo = ordercontex.getTransType(tranTypeCode, pk_group);
    UFBoolean blrgcashflag = trantypevo.getBlrgcashflag();
    UFBoolean offsetflag =
        keyValue.getHeadUFBooleanValue(SaleOrderHVO.BOFFSETFLAG);
    UFDouble receivedmny =
        keyValue.getHeadUFDoubleValue(SaleOrderHVO.NRECEIVEDMNY);
    if ((offsetflag != null && offsetflag.booleanValue())
        || MathTool.compareTo(receivedmny, UFDouble.ZERO_DBL) > 0
    		||(blrgcashflag!=null&&blrgcashflag.booleanValue())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4006011_0", "04006011-0008")
      /*@res "销售订单交易类型为赠品兑付或者已做过费用冲抵或者订单收款核销，不允许再作现销处理!"*/);
    }

    SaleOrderVO bill = util.getBillValueVO(SaleOrderVO.class);
    this.offsetItfUtil = new OffsetItfVOUtil(keyValue);
    Map<Integer, OffsetParaVO> offsetparamap =
        this.offsetItfUtil.getinterfaceData();
    this.offsetUtil =
        new OffsetUtil(bill.getParentVO().getPk_group(), offsetparamap);
    GatherbillUtil.getInstance().checkBeforeGathering(bill);
    if (null == bill.getChildrenVO() || bill.getChildrenVO().length == 0) {
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006011_0", "04006011-0364")/*请先录入销售订单没有表体数据*/);
    }
    UFBoolean ufbSO30 =
        SOSysParaInitUtil.getSO30(bill.getChildrenVO()[0].getCarorgid());
    boolean bSO30 = ufbSO30 == null ? false : ufbSO30.booleanValue();
    // "销处理是否弹出详细核销界面"为是
    if (bSO30) {
      this.openCashSaleFuncNodeDialog(offsetparamap, bill);
    }
    // "销处理是否弹出详细核销界面"为否
    else {
      this.processCashSaleKeepIn(offsetparamap, bill);
    }
    ShowStatusBarMsgUtil.showStatusBarMsg(
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
            "04006011-0014")/*@res "操作成功"*/, this.getModel().getContext());
  }

  public SaleOrderBillForm getEditor() {
    return this.editor;
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public OffsetItfVOUtil getOffsetItfUtil() {
    return this.offsetItfUtil;
  }

  public OffsetUtil getOffsetUtil() {
    return this.offsetUtil;
  }

  public void setCashSaleResult(SaleOrderUserObject userObject) {
    CardKeyValue keyValue =
        new CardKeyValue(this.getEditor().getBillCardPanel());
    /**
     * 现销费用冲抵和费用冲抵代码一样，需要抽!!!!!
     * */
    // 1.费用冲抵:单价金额计算、设置进缓存
    ArsubViewVO[] arsubViews = userObject.getArsubViews();
    if (arsubViews != null && arsubViews.length > 0) {
      Map<Integer, UFDouble> dismap =
          this.getOffsetUtil().manualoffsetArsub(arsubViews);
      this.getOffsetItfUtil().distributeMapToVO(dismap);
      if (null != dismap && dismap.size() > 0) {
        Integer[] changerows = dismap.keySet().toArray(new Integer[0]);
        this.doafter(changerows);
        // 得到新的冲抵关系将冲抵关系newhmrelation,set进缓存
        Map<String, UFDouble> newhmrelation =
            this.getOffsetUtil().getNewRelation(null);
        OffsetTempVO tempvo = new OffsetTempVO();
        tempvo.setHmArsubRelation(newhmrelation);
        this.getEditor().setTempvo(tempvo);
      }
    }
    // 2.收款核销：计算收款核销金额并设置、设置进缓存
    SoBalanceVO soBalanceVO = userObject.getSoBalanceVO();
    if (soBalanceVO != null) {
      UFDouble receivemny = UFDouble.ZERO_DBL;
      SoBalanceUtil sobalanceutil = SoBalanceUtil.getInstance();
      receivemny =
          sobalanceutil.caculateSoBalanceTotalBodymny(soBalanceVO,
              SoBalanceBVO.NORIGTHISBALMNY);
      keyValue
          .setHeadValue(SaleOrderHVO.NRECEIVEDMNY, MathTool.add(
              keyValue.getHeadUFDoubleValue(SaleOrderHVO.NRECEIVEDMNY),
              receivemny));
      this.getEditor().setSobalancevo(soBalanceVO);
    }
    // 3.订单收款：设置订单界面收款金额、设置进缓存
    UFDouble thisGatheringMny = userObject.getThisGatheringMny();
    if (thisGatheringMny != null
        && MathTool.absCompareTo(thisGatheringMny, UFDouble.ZERO_DBL) > 0) {
      keyValue.setHeadValue(SaleOrderHVO.NTHISRECEIVEMNY, thisGatheringMny);
      this.getEditor().setThisGatheringMny(thisGatheringMny);
    }
    // 4.设置做过现销处理：供"费用冲抵"控制可用性
    this.getEditor().setIsCashSale(true);
  }

  public void setEditor(SaleOrderBillForm editor) {
    this.editor = editor;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  public void setOffsetItfUtil(OffsetItfVOUtil offsetItfUtil) {
    this.offsetItfUtil = offsetItfUtil;
  }

  public void setOffsetUtil(OffsetUtil offsetUtil) {
    this.offsetUtil = offsetUtil;
  }

  @Override
  protected boolean isActionEnable() {
    return this.model.getUiState() == UIState.ADD
        || this.model.getUiState() == UIState.EDIT;
  }

  private void doafter(Integer[] changerows) {
    BillCardPanel cardPanel = this.getEditor().getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    // 重算数量单价金额开始
    int[] intchangerows = new int[changerows.length];
    for (int i = 0; i < changerows.length; i++) {
      intchangerows[i] = changerows[i].intValue();
    }

    String tranTypeCode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    SaleOrderClientContext ordercontex = this.getEditor().getM30ClientContext();
    String pk_group = AppContext.getInstance().getPkGroup();
    M30TranTypeVO trantypevo = ordercontex.getTransType(tranTypeCode, pk_group);
    SaleOrderCalculator calcutor = new SaleOrderCalculator(cardPanel);
    calcutor.setTranTypeVO(trantypevo);
    calcutor.calculate(intchangerows, SaleOrderBVO.NORIGTAXMNY);

    // 重算数量单价金额结束

    // 计算表头价税合计、冲抵金额
    HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
    totalrule.calculateHeadTotal();

    // 设置编辑性
    UFBoolean oldboffsetflag =
        keyValue.getHeadUFBooleanValue(SaleOrderHVO.BOFFSETFLAG);
    keyValue.setHeadValue(SaleOrderHVO.BOFFSETFLAG, UFBoolean.TRUE);
    if (!oldboffsetflag.booleanValue()) {

      CardEditSetter editset = new CardEditSetter(this.getEditor());
      editset.setEditEnable();
    }

  }

  private BillCardPanel getCardPanel() {
    return this.getEditor().getBillCardPanel();
  }

  private IFuncRegisterQueryService getFuncRegisterQueryService() {
    return NCLocator.getInstance().lookup(IFuncRegisterQueryService.class);
  }

  private void openCashSaleFuncNodeDialog(
      Map<Integer, OffsetParaVO> offsetparamap, SaleOrderVO bill) {
    FuncRegisterVO frVO = null;
    try {
      frVO = this.getFuncRegisterQueryService().queryFunctionByCode("40060399");
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    Dimension d = new Dimension(800, 600);
    FuncletInitData initData = new FuncletInitData();
    // initData.setInitType(168);
    CashSaleData initdata = new CashSaleData();
    initdata.setOrdervo(bill);
    // initdata.setSobalancevo(sobalancevo);
    initData.setInitData(initdata);
    initdata.setOffparamap(offsetparamap);

    CashSaleLinkListener linkListener = new CashSaleLinkListener();
    FuncletWindowLauncher.openFuncNodeDialog(this.getEditor(), frVO, initData,
        linkListener, true, false, d);
  }

  private void processCashSaleKeepIn(Map<Integer, OffsetParaVO> offsetparamap,
      SaleOrderVO bill) {
    CashSaleUtil cashSaleUtil = new CashSaleUtil();
    SaleOrderUserObject userOject =
        cashSaleUtil.processCashSaleKeepIn(offsetparamap, bill);
    this.setCashSaleResult(userOject);
  }

}
