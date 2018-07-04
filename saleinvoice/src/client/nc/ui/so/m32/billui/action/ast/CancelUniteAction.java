package nc.ui.so.m32.billui.action.ast;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.enumeration.OpposeFlag;
import nc.vo.so.m32.paravo.CombinCacheVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m32.billui.model.SaleInvoiceManageModel;
import nc.ui.so.m32.billui.pub.CardPanelCalculator;
import nc.ui.so.m32.billui.pub.CardVATCalculator;
import nc.ui.so.m32.billui.rule.HeadSumMny;
import nc.ui.so.m32.billui.view.SaleInvoiceEditor;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.AbstractAppModel;

/**
 * 取消冲抵按钮响应类
 * 
 * @since 6.0
 * @version 2010-12-10 下午02:38:09
 * @author 么贵敬
 */
public class CancelUniteAction extends NCAction {

  /**
   * VersionUID
   */
  private static final long serialVersionUID = -3447700512314859547L;

  /** 发票卡控件 */
  private SaleInvoiceEditor editor;

  /** 发票管理应用模型 */
  private AbstractAppModel model;

  /**
   * CancelUniteAction 的构造子
   */
  public CancelUniteAction() {
    super();
    this.initializeAction();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    NCLangRes nclangres = NCLangRes.getInstance();
    String title = nclangres.getStrByID("4006008_0", "04006008-0075")/*确认取消抵冲抵*/;
    /*取消费用冲抵将把以前所有的冲抵关系解除，您确定要取消冲抵吗？*/
    String question = nclangres.getStrByID("4006008_0", "04006008-0076");
    if (UIDialog.ID_YES == MessageDialog.showYesNoDlg(this.getModel()
        .getContext().getEntranceUI(), title, question, UIDialog.ID_NO)) {
      BillCardPanel cardPanel = this.getEditor().getBillCardPanel();
      IKeyValue keyValue = new CardKeyValue(cardPanel);
      // 恢复订单数据
      List<Integer> offsetrows = this.resetBillVO();

      this.doafter(offsetrows);

      OffsetTempVO tempvo = new OffsetTempVO();
      tempvo.setIsCancelOffset(true);
      this.getEditor().setTempvo(tempvo);
      // 是否做过合并开票
      keyValue.setHeadValue(SaleInvoiceHVO.BSUBUNITFLAG, UFBoolean.FALSE);
      keyValue.setHeadValue(SaleInvoiceHVO.NTOTALORIGSUBMNY, UFDouble.ZERO_DBL);

      this.setEnabled(false);
    }
  }

  /**
   * 卡控件
   * 
   * @return 卡控件
   */
  public SaleInvoiceEditor getEditor() {
    return this.editor;
  }

  /**
   * 方法功能描述：返回model属性。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author fengjb
   * @time 2010-6-21 上午09:46:05
   */
  public AbstractAppModel getModel() {
    return this.model;
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
   * 方法功能描述：设置model属性。
   * <p>
   * <b>参数说明</b>
   * 
   * @param model
   *          <p>
   * @author fengjb
   * @time 2010-6-21 上午09:46:19
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    // 合并编辑下不允许做费用冲抵
    SaleInvoiceManageModel invoicemodel =
        (SaleInvoiceManageModel) this.getModel();
    CombinCacheVO cachevo = invoicemodel.getCombinCacheVO();
    if (null != cachevo && cachevo.getBcombinflag()) {
      return false;
    }

    // 选中的销售发票
    SaleInvoiceVO selInvoice = (SaleInvoiceVO) this.editor.getValue();
    UFBoolean isUnit = null;

    if (null != selInvoice) {
      isUnit = selInvoice.getParentVO().getBsubunitflag();
      // 对冲生成不能
      if (OpposeFlag.GENERAL.equalsValue(selInvoice.getParentVO()
          .getFopposeflag())) {
        return false;
      }

    }
    return isUnit == null ? false : isUnit.booleanValue();
  }

  private void doafter(List<Integer> offsetrows) {
    BillCardPanel cardPanel = this.getEditor().getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    // 重算数量单价金额开始
    int[] changerows = new int[offsetrows.size()];
    for (int i = 0; i < offsetrows.size(); i++) {
      changerows[i] = offsetrows.get(i).intValue();
    }
    CardPanelCalculator calculator =
        new CardPanelCalculator(this.getEditor().getBillCardPanel());
    calculator.calculate(changerows, SaleInvoiceBVO.NORIGTAXMNY);
    // 重算数量单价金额结束

    // 重新计算VAT信息开始
    CardVATCalculator vatcal = new CardVATCalculator(cardPanel);
    for (int row : changerows) {
      vatcal.calculateVatWhenEditNoVat(row, SaleInvoiceBVO.NORIGTAXMNY);
    }
    // 重新计算VAT信息结束

    // 计算表头价税合计、合并开票金额
    HeadSumMny hsmrule = new HeadSumMny();
    hsmrule.process(cardPanel);

    // 标志位
    keyValue.setHeadValue(SaleInvoiceHVO.BSUBUNITFLAG, UFBoolean.FALSE);
    // 设置编辑性
    this.editor.setCardEditEnable();

  }

  /**
   * 方法功能描述：初始化“取消合并”按钮。
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @author fengjb
   * @time 2010-6-21 上午09:32:44
   */
  private void initializeAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_UNOFFSET);
  }

  private List<Integer> resetBillVO() {
    BillCardPanel cardPanel = this.getEditor().getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 原表头合并开票总金额
    UFDouble ntotalorigsubmny =
        keyValue.getHeadUFDoubleValue(SaleInvoiceHVO.NTOTALORIGSUBMNY);
    // 价税合计
    UFDouble ntotalorigmny =
        keyValue.getHeadUFDoubleValue(SaleInvoiceHVO.NTOTALORIGMNY);
    ntotalorigmny = MathTool.add(ntotalorigmny, ntotalorigsubmny);
    keyValue.setHeadValue(SaleInvoiceHVO.NTOTALORIGMNY, ntotalorigmny);
    keyValue.setHeadValue(SaleInvoiceHVO.NTOTALORIGMNY, null);
    keyValue.setHeadValue(SaleInvoiceHVO.BSUBUNITFLAG, UFBoolean.FALSE);

    List<Integer> offsetrows = new ArrayList<Integer>();
    for (int i = 0; i < keyValue.getBodyCount(); i++) {
      // 价税合计
      UFDouble origtaxmny =
          keyValue.getBodyUFDoubleValue(i, SaleInvoiceBVO.NORIGTAXMNY);
      // 合并开票金额
      UFDouble submny =
          keyValue.getBodyUFDoubleValue(i, SaleInvoiceBVO.NORIGSUBMNY);
      if (null == submny || submny.compareTo(UFDouble.ZERO_DBL) == 0) {
        continue;
      }
      origtaxmny = MathTool.add(origtaxmny, submny);
      keyValue.setBodyValue(i, SaleInvoiceBVO.NORIGTAXMNY, origtaxmny);
      keyValue.setBodyValue(i, SaleInvoiceBVO.NORIGSUBMNY, null);

      offsetrows.add(Integer.valueOf(i));
    }
    return offsetrows;
  }

}
