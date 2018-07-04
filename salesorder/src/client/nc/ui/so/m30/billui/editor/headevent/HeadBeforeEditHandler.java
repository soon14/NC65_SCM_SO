package nc.ui.so.m30.billui.editor.headevent;

import java.util.HashSet;
import java.util.Set;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.so.m30.billui.rule.IsEditableCheckRule;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.CardEditSetter;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 订单表头编辑前事件派发类
 * 
 * @since 6.0
 * @version 2011-6-8 上午11:19:58
 * @author fengjb
 */
public class HeadBeforeEditHandler implements
    IAppEventHandler<CardHeadTailBeforeEditEvent> {
  
  private SaleOrderBillForm billform;

  public SaleOrderBillForm getBillform() {
    return this.billform;
  }

  public void setBillform(SaleOrderBillForm billform) {
    this.billform = billform;
  }
  

  private final static Set<String> bodyfiedl = new HashSet<String>();

  static {
    for (String key : CardEditSetter.HEADNOTEDITFIELDS) {
      bodyfiedl.add(key);
    }
  }

  @Override
  public void handleAppEvent(CardHeadTailBeforeEditEvent e) {

    String editkey = e.getKey();
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    String trantypeid = keyValue.getHeadStringValue(SaleOrderHVO.CTRANTYPEID);
    if (PubAppTool.isNull(trantypeid)
        && SaleOrderHVO.CORIGCURRENCYID.equals(editkey)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006011_0", "04006011-0029")/*@res "请先录入交易类型"*/);
    }
    // 编辑性控制
    notEdit(e);

    // 交易类型
    if (SaleOrderHVO.CTRANTYPEID.equals(editkey)) {
      TranTypeEditHandler handler = new TranTypeEditHandler();
      handler.beforeEdit(e);
    }
    // 赠品兑付类型
    else if (SaleOrderHVO.CARSUBTYPEID.equals(editkey)) {
      ArsubTypeEditHandler handler = new ArsubTypeEditHandler();
      handler.beforeEdit(e,this.billform);
    }
    // 客户
    else if (SaleOrderHVO.CCUSTOMERID.equals(editkey)) {
      CustomerEditHandler handler = new CustomerEditHandler();
      handler.beforeEdit(e);
    }

    // 客户收货地址( by liylr)
    else if (SaleOrderHVO.CHRECEIVEADDID.equals(editkey)) {
      HeadReceiveaddrEditHandler handler = new HeadReceiveaddrEditHandler();
      handler.beforeEdit(e);
    }

    // 业务员
    else if (SaleOrderHVO.CEMPLOYEEID.equals(editkey)) {
      EmployeeEditHandler handler = new EmployeeEditHandler();
      handler.beforeEdit(e);
    }
    else if (SaleOrderHVO.CDEPTVID.equals(editkey)) {
      DeptEdithandler handler = new DeptEdithandler();
      handler.beforeEdit(e);
    }
    // 币种
    else if (SaleOrderHVO.CORIGCURRENCYID.equals(editkey)) {
      OrigCurrencyEditHandler handler = new OrigCurrencyEditHandler();
      handler.beforeEdit(e);
    }
    // 散户
    else if (SaleOrderHVO.CFREECUSTID.equals(editkey)) {
      FreeCustEditHandler handler = new FreeCustEditHandler();
      handler.beforeEdit(e);
    }
    // 开户银行帐户
    else if (SaleOrderHVO.CCUSTBANKACCID.equals(editkey)) {
      CustBankAccEditHandler handler = new CustBankAccEditHandler();
      handler.beforeEdit(e);
    }
    // 收款协议
    else if (SaleOrderHVO.CPAYTERMID.equals(editkey)) {
      PayTermEditHandler handler = new PayTermEditHandler();
      handler.beforeEdit(e);
    }
    else if (SaleOrderHVO.CINVOICECUSTID.equals(editkey)) {
      InvoiceCustEditHandler handler = new InvoiceCustEditHandler();
      handler.beforeEdit(e);
    }

  }

  private void notEdit(CardHeadTailBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    Integer status = keyValue.getHeadIntegerValue(SaleOrderHVO.FSTATUSFLAG);

    // 单据状态编辑性空
    boolean isEditable =
        new IsEditableCheckRule().check(e.getBillCardPanel(), -1, e.getKey());
    if (BillStatus.AUDITING.equalsValue(status) && !isEditable) {
      e.setReturnValue(Boolean.FALSE);
    }

    // 收款后，不能编辑的字段
    UFDouble nreceivedmny =
        keyValue.getHeadUFDoubleValue(SaleOrderHVO.NRECEIVEDMNY);
    if (!MathTool.isZero(nreceivedmny) && bodyfiedl.contains(e.getKey())) {
      e.setReturnValue(Boolean.FALSE);
    }
  }

}
