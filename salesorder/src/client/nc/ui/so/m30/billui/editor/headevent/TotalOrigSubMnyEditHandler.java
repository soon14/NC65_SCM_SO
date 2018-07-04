package nc.ui.so.m30.billui.editor.headevent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30.util.OffsetItfVOUtil;
import nc.vo.so.m35.paravo.OffsetParaVO;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.rule.OffsetUtil;
import nc.vo.so.util.OffsetDefaltSqlUtil;
import nc.vo.trade.checkrule.VOChecker;
import nc.vo.uif2.LoginContext;

import nc.desktop.ui.WorkbenchEnvironment;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.CardEditSetter;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.ShowStatusBarMsgUtil;

public class TotalOrigSubMnyEditHandler {

  private SaleOrderBillForm billform;

  public void afterEdit(CardHeadTailAfterEditEvent e) {

    String title =
        NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0256")/*确认取消抵冲抵*/;
    String question =
        NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0257")/*取消冲抵将把以前所有的冲抵关系解除，您确定要取消冲抵吗？*/;
    BillCardPanel cardPanel = e.getBillCardPanel();
    CardKeyValue keyValue = new CardKeyValue(cardPanel);
    UFDouble nowtotalsubmny =
        keyValue.getHeadUFDoubleValue(SaleOrderHVO.NTOTALORIGSUBMNY);
    // 如果为null或者0则取消冲抵
    if (MathTool.isZero(nowtotalsubmny)) {
      if (UIDialog.ID_YES == MessageDialog.showYesNoDlg(WorkbenchEnvironment
          .getInstance().getWorkbench().getParent(), title, question)) {
        // 取消费用冲抵
        this.cancelOffset(cardPanel);
      }
    }
    else {
      // 取消费用冲抵
      this.cancelOffset(cardPanel);
      // 重新冲抵
      LoginContext contex = e.getContext();
      this.redoOffset(cardPanel, contex, nowtotalsubmny);

    }

  }

  public SaleOrderBillForm getBillform() {
    return this.billform;
  }

  public void setBillform(SaleOrderBillForm billform) {
    this.billform = billform;
  }

  private void cancelOffset(BillCardPanel cardPanel) {
    // 恢复订单数据
    List<Integer> offsetrows = this.resetBillVO(cardPanel);
    this.doCancelOffsetafter(cardPanel, offsetrows);
  }

  /**
   * 取消冲抵后动作
   * 
   * @param cardPanel
   * @param keyValue
   * @param offsetrows
   * @param ordereditor
   */
  private void doCancelOffsetafter(BillCardPanel cardPanel,
      List<Integer> offsetrows) {

    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 重算数量单价金额开始
    int[] changerows = new int[offsetrows.size()];
    for (int i = 0; i < offsetrows.size(); i++) {
      changerows[i] = offsetrows.get(i).intValue();
    }

    this.processDisAfter(cardPanel, changerows);

    OffsetTempVO tempvo = new OffsetTempVO();
    tempvo.setIsCancelOffset(true);
    this.billform.setTempvo(tempvo);
    // 是否做过冲抵
    keyValue.setHeadValue(SaleOrderHVO.BOFFSETFLAG, UFBoolean.FALSE);
    keyValue.setHeadValue(SaleOrderHVO.NTOTALORIGSUBMNY, UFDouble.ZERO_DBL);

  }

  /**
   * 冲抵分配结束后处理
   * 
   * @param changerows
   * @param ordereditor
   * @param tempvo
   * @param interfacerule
   */
  private void doOffsetafter(BillCardPanel cardPanel, Integer[] changerows,
      OffsetTempVO tempvo, OffsetUtil interfacerule) {
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int[] intchangerows = new int[changerows.length];
    for (int i = 0; i < changerows.length; i++) {
      intchangerows[i] = changerows[i].intValue();
    }
    // 设置标志位
    keyValue.setHeadValue(SaleOrderHVO.BOFFSETFLAG, UFBoolean.TRUE);
    this.processDisAfter(cardPanel, intchangerows);

    // 得到新的合并开票关系将冲抵关系newhmrelation,set进缓存
    Map<String, UFDouble> hmrelation = tempvo.getHmArsubRelation();
    Map<String, UFDouble> newhmrelation =
        interfacerule.getNewRelation(hmrelation);
    tempvo.setHmArsubRelation(newhmrelation);
    this.billform.setTempvo(tempvo);

  }

  private void processDisAfter(BillCardPanel cardPanel, int[] intchangerows) {
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 重算数量单价金额开始
    if (!VOChecker.isEmpty(intchangerows)) {
      SaleOrderCalculator calcultor = new SaleOrderCalculator(cardPanel);
      calcultor.calculate(intchangerows, SaleOrderBVO.NORIGTAXMNY);
    }
    // 重算数量单价金额结束
    // 计算表头价税合计、冲抵金额
    HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
    totalrule.calculateHeadTotal();

    // 设置编辑性

    UFBoolean oldboffsetflag =
        keyValue.getHeadUFBooleanValue(SaleOrderHVO.BOFFSETFLAG);
    keyValue.setHeadValue(SaleOrderHVO.BOFFSETFLAG, UFBoolean.TRUE);
    if (!oldboffsetflag.booleanValue()) {
      CardEditSetter editset = new CardEditSetter(this.billform);
      editset.setEditEnable();
    }
  }

  private void redoOffset(BillCardPanel cardPanel, LoginContext contex,
      UFDouble nowtotalsubmny) {
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    OffsetItfVOUtil voutil = new OffsetItfVOUtil(keyValue);
    // 检查是否有可冲抵的行
    Map<Integer, OffsetParaVO> itfvo = voutil.getinterfaceData();
    // 检查是否有可冲抵的行
    if (null == itfvo || itfvo.size() == 0) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0030")/*@res "赠品、折扣和劳务行不做冲抵，过滤后没有可冲抵的行"*/);
    }
    // 获取销售订单原先冲抵关系
    OffsetTempVO tempvo = this.billform.getTempvo();
    if (null == tempvo) {
      tempvo = new OffsetTempVO();
    }
    String pk_group = keyValue.getHeadStringValue(SaleOrderHVO.PK_GROUP);
    OffsetDefaltSqlUtil sqlutil = new OffsetDefaltSqlUtil();
    String defaultwhere = sqlutil.getOrderDefaultSql(pk_group, itfvo);
    OffsetUtil interfacerule = new OffsetUtil(pk_group, itfvo);

    String billid = keyValue.getHeadStringValue(SaleOrderHVO.CSALEORDERID);
    // 分配金额
    Map<Integer, UFDouble> dismap =
        interfacerule.autoOffsetArsub(defaultwhere, nowtotalsubmny, tempvo,
            billid);
    voutil.distributeMapToVO(dismap);

    if (null != dismap && dismap.size() > 0) {
      Integer[] changerows = dismap.keySet().toArray(new Integer[0]);
      this.doOffsetafter(cardPanel, changerows, tempvo, interfacerule);
      ShowStatusBarMsgUtil.showStatusBarMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
              "04006011-0031")/*@res "费用冲抵成功"*/, contex);
    }
    else {
      ShowStatusBarMsgUtil.showStatusBarMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
              "04006011-0469")/*@res "当前没有可匹配的费用冲抵单。"*/, contex);
    }

  }

  /**
   * 恢复冲抵前数据
   * 
   * @param keyValue
   * @return
   */
  private List<Integer> resetBillVO(BillCardPanel cardPanel) {
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 原表头合并开票总金额
    UFDouble ntotalorigsubmny =
        keyValue.getHeadUFDoubleValue(SaleOrderHVO.NTOTALORIGSUBMNY);
    // 价税合计
    UFDouble ntotalorigmny =
        keyValue.getHeadUFDoubleValue(SaleOrderHVO.NTOTALORIGMNY);
    ntotalorigmny = MathTool.add(ntotalorigmny, ntotalorigsubmny);
    keyValue.setHeadValue(SaleOrderHVO.NTOTALORIGMNY, ntotalorigmny);
    keyValue.setHeadValue(SaleOrderHVO.NTOTALORIGMNY, null);
    keyValue.setHeadValue(SaleOrderHVO.BOFFSETFLAG, UFBoolean.FALSE);

    List<Integer> offsetrows = new ArrayList<Integer>();
    for (int i = 0; i < keyValue.getBodyCount(); i++) {
      // 价税合计
      UFDouble origtaxmny =
          keyValue.getBodyUFDoubleValue(i, SaleOrderBVO.NORIGTAXMNY);
      // 合并开票金额
      UFDouble submny =
          keyValue.getBodyUFDoubleValue(i, SaleOrderBVO.NORIGSUBMNY);
      if (null == submny || submny.compareTo(UFDouble.ZERO_DBL) == 0) {
        continue;
      }
      origtaxmny = MathTool.add(origtaxmny, submny);
      keyValue.setBodyValue(i, SaleOrderBVO.NORIGTAXMNY, origtaxmny);
      keyValue.setBodyValue(i, SaleOrderBVO.NORIGSUBMNY, null);

      offsetrows.add(Integer.valueOf(i));
    }
    return offsetrows;
  }
}
