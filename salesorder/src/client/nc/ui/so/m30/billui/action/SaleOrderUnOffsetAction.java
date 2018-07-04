package nc.ui.so.m30.billui.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.CardEditSetter;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.AbstractAppModel;

/**
 * 取消费用冲抵的按钮相应类
 * 将缓存中的Map清空，并把是否取消费用冲抵,并进行一次数量单价金额允许和控制界面编辑性
 * 
 * @since 6.0
 * @version 2010-12-10 上午11:42:13
 * @author 么贵敬
 */
public class SaleOrderUnOffsetAction extends NCAction {

  /**
   * Version
   */
  private static final long serialVersionUID = 6323774075578177967L;

  /** 订单卡控件 */
  private SaleOrderBillForm editor;

  /** 订单管理应用模型 */
  private AbstractAppModel model;

  public SaleOrderUnOffsetAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_UNOFFSET);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    BillCardPanel cardPanel = this.getEditor().getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    boolean flag =
        keyValue.getHeadUFBooleanValue(SaleOrderHVO.BOFFSETFLAG).booleanValue();
    if (!flag) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0022")/*@res "单据未做过费用冲抵"*/);
    }
    String title =
        NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0246")/*确认取消冲抵*/;
    String question =
        NCLangRes.getInstance().getStrByID("4006011_0", "04006011-0247")
    /*请确认是否要取消当前数据冲抵情况？*/;
    if (UIDialog.ID_YES == MessageDialog.showYesNoDlg(this.getModel()
        .getContext().getEntranceUI(), title, question, UIDialog.ID_NO)) {
      // 恢复订单数据
      List<Integer> offsetrows = this.resetBillVO();

      this.doafter(offsetrows);

      OffsetTempVO tempvo = new OffsetTempVO();
      tempvo.setIsCancelOffset(true);
      this.getEditor().setTempvo(tempvo);
      // 是否做过冲抵
      keyValue.setHeadValue(SaleOrderHVO.BOFFSETFLAG, UFBoolean.FALSE);
    }
  }

  /**
   * 订单卡控件getter
   * 
   * @return 订单卡控件
   */
  public SaleOrderBillForm getEditor() {
    return this.editor;
  }

  /**
   * 订单应用模型getter
   * 
   * @return 应用关系模型
   */
  public AbstractAppModel getModel() {
    return this.model;
  }

  /**
   * 订单卡控件setter
   * 
   * @param editor 卡控件
   */
  public void setEditor(SaleOrderBillForm editor) {
    this.editor = editor;
  }

  /**
   * 订单卡控件setter
   * 
   * @param model 应有管理模型
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    // 选中的销售订单
    BillCardPanel cardPanel = this.getEditor().getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    UFBoolean isoffset =
        keyValue.getHeadUFBooleanValue(SaleOrderHVO.BOFFSETFLAG);
    if (null == isoffset) {
      isoffset = UFBoolean.FALSE;
    }
    return isoffset.booleanValue();
  }

  /**
   * 做一些分配结束后的业务规则（重算数量单价金额、计算表头价税合计、冲抵金额）控制界面编辑性
   * 
   * @param offsetrows
   */
  private void doafter(List<Integer> offsetrows) {
    BillCardPanel cardPanel = this.getEditor().getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);

    // 重算数量单价金额开始
    int[] changerows = new int[offsetrows.size()];
    for (int i = 0; i < offsetrows.size(); i++) {
      changerows[i] = offsetrows.get(i).intValue();
    }
    String tranTypeCode =
        keyValue.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
    SaleOrderClientContext ordercontex = this.getEditor().getM30ClientContext();
    String pk_group = AppContext.getInstance().getPkGroup();
    M30TranTypeVO trantypevo = ordercontex.getTransType(tranTypeCode, pk_group);
    SaleOrderCalculator calcutor = new SaleOrderCalculator(cardPanel);
    calcutor.setTranTypeVO(trantypevo);
    calcutor.calculate(changerows, SaleOrderBVO.NORIGTAXMNY);

    // 重算数量单价金额结束

    // 计算表头价税合计、冲抵金额
    HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
    totalrule.calculateHeadTotal();

    // 设置编辑性
    UFBoolean oldboffsetflag =
        keyValue.getHeadUFBooleanValue(SaleOrderHVO.BOFFSETFLAG);
    keyValue.setHeadValue(SaleOrderHVO.BOFFSETFLAG, UFBoolean.TRUE);
    if (!oldboffsetflag.booleanValue()) {
      CardEditSetter editset = new CardEditSetter(this.editor);
      editset.setOriginalEdit();

    }
  }

  /**
   * 取消费用冲抵恢复订单数据。
   * 
   * @return 变化的行索引集合
   */
  private List<Integer> resetBillVO() {
    BillCardPanel cardPanel = this.getEditor().getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 原表头冲抵总金额
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
      // 冲抵金额
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
