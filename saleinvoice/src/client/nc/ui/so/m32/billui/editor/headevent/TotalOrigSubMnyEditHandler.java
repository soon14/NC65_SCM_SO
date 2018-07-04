package nc.ui.so.m32.billui.editor.headevent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.paravo.CombinCacheVO;
import nc.vo.so.m32.rule.UniteArsubRule;
import nc.vo.so.m35.paravo.OffsetParaVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.rule.OffsetUtil;
import nc.vo.so.util.OffsetDefaltSqlUtil;
import nc.vo.trade.checkrule.VOChecker;

import nc.desktop.ui.WorkbenchEnvironment;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.so.m32.billui.model.SaleInvoiceManageModel;
import nc.ui.so.m32.billui.pub.CardPanelCalculator;
import nc.ui.so.m32.billui.pub.CardVATCalculator;
import nc.ui.so.m32.billui.rule.HeadSumMny;
import nc.ui.so.m32.billui.view.SaleInvoiceEditor;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.ShowStatusBarMsgUtil;

/**
 * 发票表头冲抵金额编辑事件
 * 
 * @since 6.0
 * @version 2011-4-21 下午06:33:17
 * @author 么贵敬
 */
public class TotalOrigSubMnyEditHandler {

  /**
   * 冲抵金额编辑后事件处理
   * 
   * @param e
   */
  public void afterEdit(CardHeadTailAfterEditEvent e, SaleInvoiceEditor editor,
      BillManageModel model) {
    NCLangRes res = NCLangRes.getInstance();
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    SaleInvoiceEditor invoiceditor = editor;
    SaleInvoiceManageModel invoicemodel = (SaleInvoiceManageModel) model;
    CombinCacheVO cachevo = invoicemodel.getCombinCacheVO();

    String title = res.getStrByID("4006008_0", "04006008-0075")/*确认取消抵冲抵*/;
    /*取消合并将把以前所有的冲抵关系解除，您确定要取消冲抵吗？*/
    String question = res.getStrByID("4006008_0", "04006008-0083");

    UFDouble nowtotalsubmny =
        keyValue.getHeadUFDoubleValue(SaleInvoiceHVO.NTOTALORIGSUBMNY);
    // 如果为null或者0则取消冲抵
    if (MathTool.isZero(nowtotalsubmny)) {
      if (UIDialog.ID_YES == MessageDialog.showYesNoDlg(WorkbenchEnvironment
          .getInstance().getWorkbench().getParent(), title, question)) {
        // 取消费用冲抵
        this.cancelOffset(keyValue, invoiceditor);
      }
    }
    else {
      if (null != cachevo && cachevo.getBcombinflag()) {
        keyValue.setHeadValue(SaleInvoiceHVO.NTOTALORIGSUBMNY, e.getOldValue());
        ExceptionUtils.wrappBusinessException(res.getStrByID("4006008_0",
            "04006008-0141")/*汇总状态下不允许费用冲抵*/);
        return;
      }
      // 取消费用冲抵
      this.cancelOffset(keyValue, invoiceditor);
      // 重新冲抵
      this.redoOffset(keyValue, invoiceditor, model, nowtotalsubmny);

    }
  }

  private void cancelOffset(CardKeyValue keyValue,
      SaleInvoiceEditor invoiceeditor) {
    // 恢复订单数据
    List<Integer> offsetrows = this.resetBillVO(keyValue);
    this.doCancelOffsetafter(keyValue, offsetrows, invoiceeditor);
  }

  /**
   * 取消冲抵后动作
   * 
   * @param cardPanel
   * @param keyValue
   * @param offsetrows
   * @param invoiceeditor
   */
  private void doCancelOffsetafter(CardKeyValue keyValue,
      List<Integer> offsetrows, SaleInvoiceEditor invoiceeditor) {

    // 重算数量单价金额开始
    int[] changerows = new int[offsetrows.size()];
    for (int i = 0; i < offsetrows.size(); i++) {
      changerows[i] = offsetrows.get(i).intValue();
    }
    // 标志位
    keyValue.setHeadValue(SaleInvoiceHVO.BSUBUNITFLAG, UFBoolean.FALSE);

    this.processDisAfter(changerows, invoiceeditor);

    OffsetTempVO tempvo = new OffsetTempVO();
    tempvo.setIsCancelOffset(true);
    invoiceeditor.setTempvo(tempvo);
    // 是否做过合并开票
    keyValue.setHeadValue(SaleInvoiceHVO.BSUBUNITFLAG, UFBoolean.FALSE);
    keyValue.setHeadValue(SaleInvoiceHVO.NTOTALORIGSUBMNY, UFDouble.ZERO_DBL);

  }

  /**
   * 冲抵分配结束后处理
   * 
   * @param changerows
   * @param invoiceeditor
   * @param tempvo
   * @param interfacerule
   */
  private void doOffsetafter(Integer[] changerows,
      SaleInvoiceEditor invoiceeditor, OffsetTempVO tempvo,
      OffsetUtil interfacerule) {
    BillCardPanel cardPanel = invoiceeditor.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    int[] intchangerows = new int[changerows.length];
    for (int i = 0; i < changerows.length; i++) {
      intchangerows[i] = changerows[i].intValue();
    }
    // 设置标志位
    keyValue.setHeadValue(SaleInvoiceHVO.BSUBUNITFLAG, UFBoolean.TRUE);
    this.processDisAfter(intchangerows, invoiceeditor);

    // 得到新的合并开票关系将冲抵关系newhmrelation,set进缓存
    Map<String, UFDouble> hmrelation = tempvo.getHmArsubRelation();
    Map<String, UFDouble> newhmrelation =
        interfacerule.getNewRelation(hmrelation);
    tempvo.setHmArsubRelation(newhmrelation);
    invoiceeditor.setTempvo(tempvo);

  }

  private void processDisAfter(int[] intchangerows,
      SaleInvoiceEditor invoiceeditor) {
    BillCardPanel cardPanel = invoiceeditor.getBillCardPanel();
    // 重算数量单价金额
    if (!VOChecker.isEmpty(intchangerows)) {
      CardPanelCalculator calculator = new CardPanelCalculator(cardPanel);
      calculator.calculate(intchangerows, SaleInvoiceBVO.NORIGTAXMNY);
      // 重算VAT信息
      CardVATCalculator vatcal = new CardVATCalculator(cardPanel);
      for (int row : intchangerows) {
        vatcal.calculateVatWhenEditNoVat(row, SaleInvoiceBVO.NORIGTAXMNY);
      }
    }
    // 计算表头价税合计、合并开票金额
    HeadSumMny hsmrule = new HeadSumMny();
    hsmrule.process(cardPanel);
    // 设置编辑性
    invoiceeditor.setCardEditEnable();
  }

  private void redoOffset(CardKeyValue keyValue,
      SaleInvoiceEditor invoiceeditor, BillManageModel model,
      UFDouble nowtotalsubmny) {
    UniteArsubRule uniteRule = new UniteArsubRule(keyValue);
    // 检查是否有可冲抵的行
    Map<Integer, OffsetParaVO> itfvo = uniteRule.getinterfaceDatas();
    // 检查是否有可冲抵的行
    if (null == itfvo || itfvo.size() == 0) {
      ExceptionUtils
          .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID(
              "4006008_0", "04006008-0005")/*@res "赠品、折扣和劳务行不做冲抵，过滤后没有可冲抵的行"*/);
    }
    // 获取销售订单原先冲抵关系
    OffsetTempVO tempvo = invoiceeditor.getTempvo();
    if (null == tempvo) {
      tempvo = new OffsetTempVO();
    }
    String pk_group = keyValue.getHeadStringValue(SaleInvoiceHVO.PK_GROUP);
    OffsetDefaltSqlUtil sqlutil = new OffsetDefaltSqlUtil();
    String defaultwhere = sqlutil.getInvoiceDefaultSql(pk_group, itfvo);
    OffsetUtil interfacerule = new OffsetUtil(pk_group, itfvo);

    String billid = keyValue.getHeadStringValue(SaleInvoiceHVO.CSALEINVOICEID);
    // 分配金额
    Map<Integer, UFDouble> dismap =
        interfacerule.autoOffsetArsub(defaultwhere, nowtotalsubmny, tempvo,
            billid);
    uniteRule.distributeMapToVO(dismap);

    if (null != dismap && dismap.size() > 0) {
      Integer[] changerows = dismap.keySet().toArray(new Integer[0]);
      this.doOffsetafter(changerows, invoiceeditor, tempvo, interfacerule);
      ShowStatusBarMsgUtil.showStatusBarMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006008_0",
              "04006008-0006")/*@res "费用冲抵成功"*/, model.getContext());
    }
    else {
      ShowStatusBarMsgUtil.showStatusBarMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006008_0",
              "04006008-0150")/*@res "没有可冲抵的销售费用单!"*/, model.getContext());
    }
  }

  /**
   * 恢复冲抵前数据
   * 
   * @param keyValue
   * @return
   */
  private List<Integer> resetBillVO(CardKeyValue keyValue) {
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
