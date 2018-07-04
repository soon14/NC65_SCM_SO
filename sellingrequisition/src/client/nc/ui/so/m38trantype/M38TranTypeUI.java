package nc.ui.so.m38trantype;

import java.awt.BorderLayout;
import java.awt.Component;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m38trantype.IM38TranTypeSelfService;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillEditListener;
import nc.ui.pub.transtype.EditorContext;
import nc.ui.pub.transtype.ITranstypeEditor;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.Log;
import nc.vo.so.m38trantype.entity.M38TranTypeVO;
import nc.vo.so.pub.enumeration.AskPriceRule;

/**
 * <b> 预订单交易类型UI类</b>
 * 
 * @since 6.0
 * @version 2010-03-30 上午09:21:05
 * @author 刘志伟
 */
public class M38TranTypeUI implements ITranstypeEditor {

  private BillCardPanel billCardPanel;

  private UIPanel ui = new UIPanel();

  @Override
  public void doAction(EditorContext ec) throws BusinessException {
    switch (ec.getEventtype()) {
      case EditorContext.TYPE_BROWSE:
        M38TranTypeVO vo = this.queryTranstypeExtProp(ec);
        this.showTranstypeExtObj(vo);
        // 不可编辑
        this.setEditable(false);
        break;
      case EditorContext.TYPE_NEW:
        // 新增时将编辑器界面清空，状态为可编辑
        this.newTranstypeExtProp();
        this.setEditable4AskedqtRule();
        break;
      case EditorContext.TYPE_EDIT:
        this.setEditable(true);
        this.setEditable4AskedqtRule();
        break;
      case EditorContext.TYPE_CLEAR:
        this.clearEditorPane();
        this.setEditable(false);
        break;
      case EditorContext.TYPE_CANCEL:
        this.setEditable(false);
        break;
      default:
        break;
    }

  }

  @Override
  public void doButtonAction(ButtonObject bo) throws BusinessException {
    // TODO Auto-generated method stub

  }

  @Override
  public Component getEditorPane() {
    this.init();
    return this.ui;
  }

  @Override
  public ButtonObject[] getExtButtonObjects() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * <b> 返回交易类型扩展属性对象 </b>
   * 
   * @param EditorContext
   * @author 刘志伟
   */
  @Override
  public Object getTransTypeExtObj(EditorContext context)
      throws BusinessException {
    M38TranTypeVO vo =
        (M38TranTypeVO) this.billCardPanel.getBillData().getHeaderValueVO(
            M38TranTypeVO.class.getName());
    vo.setCtrantypeid(context.getTranstype().getPk_billtypeid());
    return vo;
  }

  /**
   * 清除交易类型扩展界面值
   * 
   * @author 刘志伟
   */
  private void clearEditorPane() {
    this.billCardPanel.getHeadItem(M38TranTypeVO.BMODIFYDISCOUNT).setValue(
        Boolean.FALSE);
  }

  private void init() {
    if (this.billCardPanel != null) {
      return;
    }
    this.billCardPanel = new BillCardPanel();
    this.billCardPanel.loadTemplet("38trantype", null, null, "@@@@");
    this.billCardPanel.setEnabled(false);
    this.billCardPanel.addBillEditListenerHeadTail(new BillEditListener() {
      @Override
      public void afterEdit(BillEditEvent e) {
        // 编辑"询价规则 "
        if (e.getKey().equals(M38TranTypeVO.FASKQTRULE)) {
          M38TranTypeUI.this.setEditable4AskedqtRule();
        }
      }

      @Override
      public void bodyRowChange(BillEditEvent e) {
        // 行改变事件.

      }

    });
    this.ui.setLayout(new BorderLayout());
    this.ui.add(this.billCardPanel);
  }

  /**
   * <b> 新增预订单交易类型 </b>
   * 
   * @author 刘志伟
   */
  private void newTranstypeExtProp() {
    this.billCardPanel.addNew();
    this.billCardPanel.updateValue();
    this.setEditable(true);
    this.setDefaultVaule();
  }

  /**
   * <b> 查询交易类型扩展属性VO </b>
   * 
   * @param EditorContext
   * @author 刘志伟
   */
  private M38TranTypeVO queryTranstypeExtProp(EditorContext ec) {
    if (ec.getTranstype() == null
        || ec.getTranstype().getPk_billtypeid() == null) {
      return new M38TranTypeVO();
    }
    String billtypeid = ec.getTranstype().getPk_billtypeid();
    M38TranTypeVO returnVos = null;
    try {
      IM38TranTypeSelfService service =
          NCLocator.getInstance().lookup(IM38TranTypeSelfService.class);
      returnVos = service.queryTranTypeVO(billtypeid);
    }
    catch (Exception e) {
      Log.info(e);
      ExceptionUtils.wrappException(e);
    }
    return returnVos;
  }

  /**
   * <b> 设置预订单交易类型默认值 </b>
   * 
   * @author 刘志伟
   */
  private void setDefaultVaule() {
    // 允许修改折扣(默认是)
    this.billCardPanel.getHeadItem(M38TranTypeVO.BMODIFYDISCOUNT).setValue(
        Boolean.TRUE);
  }

  /**
   * <b> 设置单据数据编辑状态. </b>
   * 
   * @param boolean
   * @author 刘志伟
   */
  private void setEditable(boolean isEdit) {
    this.billCardPanel.setEnabled(isEdit);
  }

  /**
   * <b> 设置交易类型表头,表尾数据 </b>
   * 
   * @param M38TranTypeVO
   * @author 刘志伟
   */
  private void showTranstypeExtObj(M38TranTypeVO vo) {
    this.billCardPanel.getBillData().setHeaderValueVO(vo);
  }

  /**
   * 根据询价规则设置“询到价格是否可改、未询到价格是否可改”的可编辑性
   * 
   * @author 刘志伟
   */
  protected void setEditable4AskedqtRule() {
    Integer value =
        (Integer) this.billCardPanel.getHeadItem(M38TranTypeVO.FASKQTRULE)
            .getValueObject();
    // "null、不询价"时——>"询到价格是否可改、未询到价格是否可改"不可以编辑
    if (null == value || AskPriceRule.ASKPRICE_NO.equalsValue(value)) {
      this.billCardPanel.getHeadItem(M38TranTypeVO.BMODIFYASKEDQT).setEnabled(
          false);
      this.billCardPanel.getHeadItem(M38TranTypeVO.BMODIFYUNASKEDQT)
          .setEnabled(false);
      // 确保值为null
      this.billCardPanel.getHeadItem(M38TranTypeVO.BMODIFYASKEDQT).setValue(
          null);
      this.billCardPanel.getHeadItem(M38TranTypeVO.BMODIFYUNASKEDQT).setValue(
          null);
    }
    // "正常询价、询非促销价"时——>"询到价格是否可改、未询到价格是否可改"可以编辑
    else if (AskPriceRule.ASKPRICE_NORMAL.equalsValue(value)
        || AskPriceRule.ASKPRICE_UNSPROMOTION.equalsValue(value)) {
      this.billCardPanel.getHeadItem(M38TranTypeVO.BMODIFYASKEDQT).setEnabled(
          true);
      this.billCardPanel.getHeadItem(M38TranTypeVO.BMODIFYUNASKEDQT)
          .setEnabled(true);
    }
  }

}
