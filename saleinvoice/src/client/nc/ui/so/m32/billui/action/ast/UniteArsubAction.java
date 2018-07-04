package nc.ui.so.m32.billui.action.ast;

import java.awt.event.ActionEvent;
import java.util.Map;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.enumeration.OpposeFlag;
import nc.vo.so.m32.paravo.CombinCacheVO;
import nc.vo.so.m32.rule.UniteArsubRule;
import nc.vo.so.m35.entity.ArsubViewVO;
import nc.vo.so.m35.paravo.ItfParaVO;
import nc.vo.so.m35.paravo.OffsetParaVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.rule.OffsetUtil;
import nc.vo.so.util.OffsetDefaltSqlUtil;
import nc.vo.trade.checkrule.VOChecker;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.pub.locator.NCUILocator;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m32.billui.model.SaleInvoiceManageModel;
import nc.ui.so.m32.billui.pub.CardPanelCalculator;
import nc.ui.so.m32.billui.pub.CardVATCalculator;
import nc.ui.so.m32.billui.rule.HeadSumMny;
import nc.ui.so.m32.billui.view.SaleInvoiceEditor;
import nc.ui.so.m35.service.so.m32.itf.IDlgArsubToM32Ctr;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.model.AbstractAppModel;

/**
 * 销售发票合并开票功能实现
 * 
 * @since 6.0
 * @version 2010-12-10 下午02:34:10
 * @author 么贵敬
 */
public class UniteArsubAction extends NCAction {

  /** Version */
  private static final long serialVersionUID = -5212633567393359840L;

  /** 取消合并按钮 */
  private CancelUniteAction cancelUniteBtn;

  /** 发票卡控件 */
  private SaleInvoiceEditor editor;

  /** 发票管理应用模型 */
  private AbstractAppModel model;

  /**
   * 构造方法
   */
  public UniteArsubAction() {
    super();
    this.initializeAction();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {

    BillCardPanel cardPanel = this.getEditor().getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    UniteArsubRule uniteRule = new UniteArsubRule(keyValue);

    // 检查是否有可冲抵的行
    Map<Integer, OffsetParaVO> itfvo = uniteRule.getinterfaceDatas();
    // 检查是否有可冲抵的行
    if (null == itfvo || itfvo.size() == 0) {
      ExceptionUtils
          .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID(
              "4006008_0", "04006008-0005")/*@res "赠品、折扣和劳务行不做冲抵，过滤后没有可冲抵的行"*/);
    }

    // 获取销售订单原先合并开票关系
    OffsetTempVO tempvo = this.getEditor().getTempvo();
    if (null == tempvo) {
      tempvo = new OffsetTempVO();
    }
    // 查询并加载显示销售费用单
    IDlgArsubToM32Ctr control =
        NCUILocator.getInstance().lookup(IDlgArsubToM32Ctr.class,
            NCModule.SO.getName());
    String pk_group = keyValue.getHeadStringValue(SaleInvoiceHVO.PK_GROUP);
    String billid = keyValue.getHeadStringValue(SaleInvoiceHVO.CSALEINVOICEID);

    OffsetDefaltSqlUtil sqlutil = new OffsetDefaltSqlUtil();
    String defaultwhere = sqlutil.getInvoiceDefaultSql(pk_group, itfvo);

    ItfParaVO itfparavo = new ItfParaVO();
    itfparavo.setBillid(billid);
    itfparavo.setDefaultwhere(defaultwhere);
    itfparavo.setItfvomap(itfvo);
    itfparavo.setOffsettempvo(tempvo);
    control.queryAndLoadArsub(this.getEditor().getBillCardPanel(),
        this.getModel(), itfparavo);
    ArsubViewVO[] selectviewvos = control.getSelectedVOs();
    Map<Integer, OffsetParaVO> newitfvo = uniteRule.getinterfaceDatas();
    OffsetUtil interfacerule = new OffsetUtil(pk_group, newitfvo);
    // 分配金额
    Map<Integer, UFDouble> dismap =
        interfacerule.manualoffsetArsub(selectviewvos);
    uniteRule.distributeMapToVO(dismap);

    if (null != dismap && dismap.size() > 0) {
      Integer[] changerows = dismap.keySet().toArray(new Integer[0]);
      this.doafter(changerows);
      // 得到新的合并开票关系将冲抵关系newhmrelation,set进缓存
      Map<String, UFDouble> hmrelation = tempvo.getHmArsubRelation();
      Map<String, UFDouble> newhmrelation =
          interfacerule.getNewRelation(hmrelation);
      tempvo.setHmArsubRelation(newhmrelation);
      this.getEditor().setTempvo(tempvo);
    }

    this.getCancelUniteBtn().setEnabled(true);
    ShowStatusBarMsgUtil.showStatusBarMsg(
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006008_0",
            "04006008-0006")/*@res "费用冲抵成功"*/, this.getModel().getContext());
  }

  /**
   * 
   * 取消合并开票的按钮
   * 
   * @return 取消合并开票的按钮
   */
  public CancelUniteAction getCancelUniteBtn() {
    return this.cancelUniteBtn;
  }

  /**
   * 发票卡控件
   * 
   * @return 发票卡控件
   */
  public SaleInvoiceEditor getEditor() {
    return this.editor;
  }

  /**
   * 应用模型
   * 
   * @return 应用模型
   */
  public AbstractAppModel getModel() {
    return this.model;
  }

  /**
   * 取消冲抵按钮
   * 
   * @param cancelUniteBtn 取消冲抵按钮
   */
  public void setCancelUniteBtn(CancelUniteAction cancelUniteBtn) {
    this.cancelUniteBtn = cancelUniteBtn;
  }

  /**
   * 卡控件
   * 
   * @param editor 卡控件
   */
  public void setEditor(SaleInvoiceEditor editor) {
    this.editor = editor;
  }

  /**
   * 应有管理模型
   * 
   * @param model 应有管理模型
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    BillCardPanel cardPanel = this.getEditor().getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 没有表体的时候不用处理
    if (keyValue.getBodyCount() == 0) {
      return false;
    }

    SaleInvoiceManageModel invoicemodel =
        (SaleInvoiceManageModel) this.getModel();
    CombinCacheVO cachevo = invoicemodel.getCombinCacheVO();
    if (null != cachevo && cachevo.getBcombinflag()) {
      return false;
    }
    SaleInvoiceVO selInvoice = (SaleInvoiceVO) this.editor.getValue();
    if (null != selInvoice) {
      // 对冲生成不能
      if (OpposeFlag.GENERAL.equalsValue(selInvoice.getParentVO()
          .getFopposeflag())) {
        return false;
      }

    }
    return super.isActionEnable();
  }

  private void doafter(Integer[] changerows) {
    BillCardPanel cardPanel = this.getEditor().getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int[] intchangerows = new int[changerows.length];
    for (int i = 0; i < changerows.length; i++) {
      intchangerows[i] = changerows[i].intValue();
    }
    if (!VOChecker.isEmpty(intchangerows)) {
      // 重算数量单价金额
      CardPanelCalculator calculator =
          new CardPanelCalculator(this.getEditor().getBillCardPanel());
      calculator.calculate(intchangerows, SaleInvoiceBVO.NORIGTAXMNY);
      // 重新计算VAT信息
      CardVATCalculator vatcal = new CardVATCalculator(cardPanel);
      for (int row : intchangerows) {
        vatcal.calculateVatWhenEditNoVat(row, SaleInvoiceBVO.NORIGTAXMNY);
      }
    }

    // 计算表头价税合计、合并开票金额
    HeadSumMny hsmrule = new HeadSumMny();
    hsmrule.process(cardPanel);

    // 设置标志位
    keyValue.setHeadValue(SaleInvoiceHVO.BSUBUNITFLAG, UFBoolean.TRUE);
    // 设置编辑性
    this.editor.setCardEditEnable();

  }

  private void initializeAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_OFFSET);
  }

}
