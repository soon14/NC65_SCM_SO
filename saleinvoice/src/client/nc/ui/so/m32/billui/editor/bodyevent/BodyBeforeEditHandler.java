package nc.ui.so.m32.billui.editor.bodyevent;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.so.m32.billui.model.SaleInvoiceManageModel;
import nc.ui.so.m32.billui.rule.IsEditableCheckRule;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.keyvalue.IKeyValue;

/**
 * 
 * <p>
 * <b>本类主要完成以下功能：</b>
 * 
 * <ul>
 * <li>销售发票表体编辑后事件处理
 * </ul>
 * 
 * <p>
 * 
 * @version 本版本号 6.0
 * @since
 * @author fengjb
 * @time 2010-8-10 上午10:20:22
 */
public class BodyBeforeEditHandler implements
    IAppEventHandler<CardBodyBeforeEditEvent> {

  private SaleInvoiceManageModel model;

  /**
   * 获得model
   * 
   * @return SaleInvoiceManageModel
   */
  public SaleInvoiceManageModel getModel() {
    return this.model;
  }

  @Override
  public void handleAppEvent(CardBodyBeforeEditEvent e) {
    String editkey = e.getKey();
    // add by wangshu6 for 636 2015-01-09 销售发票修订支持修改 
    // 判断状态，审批中修改
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    Integer status = keyValue.getHeadIntegerValue(SaleInvoiceHVO.FSTATUSFLAG);
    boolean isEditable = Boolean.FALSE;
    if (BillStatus.AUDITING.equalsValue(status)) {
      // 校验当前编辑字段是否可修改；
      isEditable =
          new IsEditableCheckRule().check(e.getBillCardPanel(), e.getRow(),
              e.getKey());
      if (!isEditable) {
        e.setReturnValue(Boolean.FALSE);
      }
    }
    // end 
    
    // 默认设为可编辑
    e.setReturnValue(Boolean.TRUE);
    this.setEditByCombin(e);

    // 物料
    if (SaleInvoiceBVO.CMATERIALVID.equals(editkey)) {
      MaterialEditHandler handler = new MaterialEditHandler();
      handler.beforeEdit(e);
    }
    // 税码
    else if (SaleInvoiceBVO.CTAXCODEID.equals(editkey)) {
      TaxCodeEditHandler handler = new TaxCodeEditHandler();
      handler.beforeEdit(e);
    }
    // 业务单位
    else if (SaleInvoiceBVO.CASTUNITID.equals(editkey)) {
      AstUnitEditHandler handler = new AstUnitEditHandler();
      handler.beforeEdit(e);
    }
    // 换算率
    else if (SaleInvoiceBVO.VCHANGERATE.equals(editkey)) {
      AstChangeRateEditHandler handler = new AstChangeRateEditHandler();
      handler.beforeEdit(e);
    }
    // 报价单位
    else if (SaleInvoiceBVO.CQTUNITID.equals(editkey)) {
      QtUnitEditHandler handler = new QtUnitEditHandler();
      handler.beforeEdit(e);
    }
    // 报价换算率
    else if (SaleInvoiceBVO.VQTUNITRATE.equals(editkey)) {
      QtChangeRateHandler handler = new QtChangeRateHandler();
      handler.beforeEdit(e);
    }
    // 赠品
    else if (SaleInvoiceBVO.BLARGESSFLAG.equals(editkey)) {
      LargessFlagEditHandler handler = new LargessFlagEditHandler();
      handler.beforeEdit(e);
    }
    // 销售部门
    else if (SaleInvoiceBVO.CDEPTVID.equals(editkey)) {
      DeptEditHandler handler = new DeptEditHandler();
      handler.beforeEdit(e);
    }
    // 销售业务员
    else if (SaleInvoiceBVO.CEMPLOYEEID.equals(editkey)) {
      EmployeeEditHandler handler = new EmployeeEditHandler();
      handler.beforeEdit(e);
    }
    // 计税金额
    else if (SaleInvoiceBVO.NCALTAXMNY.equals(editkey)) {
      CalTaxMnyEditHandler handler = new CalTaxMnyEditHandler();
      handler.beforeEdit(e);
    }
    // 税额
    else if (SaleInvoiceBVO.NTAX.equals(editkey)) {
      NtaxEditHandler handler = new NtaxEditHandler();
      handler.beforeEdit(e);
    }
    // 收货地址
    else if (SaleInvoiceBVO.CRECEIVEADDRID.equals(editkey)) {
      ReceAddrEditHandler handler = new ReceAddrEditHandler();
      handler.beforeEdit(e);
    }

  }

  private void setEditByCombin(CardBodyBeforeEditEvent e) {
    BillCardPanel cardPanel = e.getBillCardPanel();
    IKeyValue keyValue = new CardKeyValue(cardPanel);
    int row = e.getRow();
    String csaleinvoicebid =
        keyValue.getBodyStringValue(row, SaleInvoiceBVO.CSALEINVOICEBID);
    if ("isnull".equals(csaleinvoicebid)) {
      e.setReturnValue(false);
      ExceptionUtils.wrappBusinessException(NCLangRes.getInstance().getStrByID(
          "4006008_0", "04006008-0153")/*非末级物料类不允许编辑*/);
    }
  }

  /**
   * 设置model
   * 
   * @param model
   */
  public void setModel(SaleInvoiceManageModel model) {
    this.model = model;
  }

}
