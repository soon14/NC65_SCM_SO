package nc.ui.so.m32.billui.action.ast;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.enumeration.OpposeFlag;
import nc.vo.so.pub.keyvalue.IKeyValue;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;

import nc.pubitf.ic.m4c.I4CQueryPubService;

import nc.bs.framework.common.NCLocator;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m32.billui.pub.CardPanelCalculator;
import nc.ui.so.m32.billui.pub.CardVATCalculator;
import nc.ui.so.m32.billui.view.SaleInvoiceEditor;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.model.AbstractAppModel;

public class FetchCostAction extends NCAction {

  /**
     *
     */
  private static final long serialVersionUID = -2901080254817551490L;

  private SaleInvoiceEditor editor;

  private AbstractAppModel model;

  public FetchCostAction() {
    super();
    this.initializeAction();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (!SysInitGroupQuery.isICEnabled()) {
      return;
    }
    BillCardPanel cardPanel = this.getEditor().getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 没有表体的时候不用处理
    if (keyValue.getBodyCount() == 0) {
      return;
    }
    Set<String> hsOuts = new HashSet<String>();
    int rowcount = keyValue.getBodyCount();
    for (int i = 0; i < rowcount; i++) {
      String srctype = keyValue.getBodyStringValue(i, SaleInvoiceBVO.VSRCTYPE);
      if (ICBillType.SaleOut.isEqual(srctype)) {
        hsOuts.add(keyValue.getBodyStringValue(i, SaleInvoiceBVO.CSRCBID));
      }
    }
    Map<String, UFDouble> hmPrice = null;
    if (hsOuts.size() > 0) {
      String[] outbids = hsOuts.toArray(new String[0]);
      I4CQueryPubService querysrv =
          NCLocator.getInstance().lookup(I4CQueryPubService.class);
      hmPrice = querysrv.queryCostPrice(outbids);
    }
    // 没有取到成本价的发票行
    List<String> errRows = new ArrayList<String>();
    // 价格发生改变的发票行
    List<Integer> chgRows = new ArrayList<Integer>();
    for (int i = 0; i < rowcount; i++) {
      // 物料为空行不做校验
      if (StringUtil.isEmptyWithTrim(keyValue.getBodyStringValue(i,
          SaleInvoiceBVO.CMATERIALID))) {
        continue;
      }
      String outbid = keyValue.getBodyStringValue(i, SaleInvoiceBVO.CSRCBID);
      // 能够取到成本单价
      if (null != hmPrice && null != hmPrice.get(outbid)) {
        keyValue.setBodyValue(i, SaleInvoiceBVO.NNETPRICE, hmPrice.get(outbid));
        chgRows.add(Integer.valueOf(i));
      }
      else {
        errRows.add(keyValue.getBodyStringValue(i, SaleInvoiceBVO.CROWNO));
      }
    }
    // 取到成本单价的发票行重新进行数量单价金额运算
    if (chgRows.size() > 0) {
      int size = chgRows.size();
      int[] iCalcRows = new int[size];
      for (int i = 0; i < size; i++) {
        iCalcRows[i] = chgRows.get(i).intValue();
      }
      CardPanelCalculator culator = new CardPanelCalculator(cardPanel);
      culator.calculate(iCalcRows, SaleInvoiceBVO.NNETPRICE);
      // 重新计算VAT信息开始
      CardVATCalculator vatcal = new CardVATCalculator(cardPanel);
      for (int row : iCalcRows) {
        vatcal.calculateVatWhenEditNoVat(row, SaleInvoiceBVO.NORIGTAXMNY);
      }
      //取成本价后会重新计算发票折扣，表头未更新，所以在此特殊处理。
      keyValue.setHeadValue(SaleInvoiceHVO.NHVOICEDISRATE,
          keyValue.getBodyValue(0, SaleInvoiceBVO.NINVOICEDISRATE));
      
      // 重新计算VAT信息结束
      ShowStatusBarMsgUtil
          .showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006008_0", "04006008-0003")/* @res "取成本价成功" */,
              this.getModel().getContext());
    }
    // 没有取到成本单价的发票行给出错误提示
    if (errRows.size() > 0) {
      StringBuilder errmsg = new StringBuilder();
      /* 多语设置 */
      StringBuilder errmsgRowno = new StringBuilder();
      for (String rowno : errRows) {
        errmsgRowno.append("[").append(rowno).append("]\n");
      }
      errmsg.append(NCLangRes.getInstance().getStrByID("4006008_0",
          "04006008-0115", null, new String[] {
            errmsgRowno.toString()
          })/* 销售发票下列行:{0}未取到成本单价 */);
      // fengjb 2012.03.05 UE规范
      ExceptionUtils.wrappBusinessException(errmsg.toString());
    }
  }

  public SaleInvoiceEditor getEditor() {
    return this.editor;
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setEditor(SaleInvoiceEditor editor) {
    this.editor = editor;
  }

  @Override
  public boolean isActionEnable() {
    BillCardPanel cardPanel = this.getEditor().getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    // 没有表体的时候不用处理
    if (keyValue.getBodyCount() == 0) {
      return false;
    }
    // 对冲发票不允许使用此功能
    Integer oppose = keyValue.getHeadIntegerValue(SaleInvoiceHVO.FOPPOSEFLAG);
    if (OpposeFlag.GENERAL.value().equals(oppose)) {
      return false;
    }
    return true;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  private void initializeAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_FETCHCOST);
  }
}
