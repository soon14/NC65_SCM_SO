package nc.ui.so.m30trantype;

import java.awt.BorderLayout;
import java.awt.Component;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30trantype.IM30TranTypeSelfService;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillEditListener;
import nc.ui.pub.transtype.EditorContext;
import nc.ui.pub.transtype.ITranstypeEditor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m30trantype.enumeration.LargessDisType;
import nc.vo.so.m30trantype.enumeration.SaleMode;
import nc.vo.so.pub.enumeration.AskPriceRule;

/**
 * 销售订单交易类型UI类
 * 
 * @since 6.0
 * @version 2011-12-2 上午08:49:49
 * @author fengjb
 */
public class M30TranTypeUI implements ITranstypeEditor {

  private BillCardPanel billCardPanel;

  private UIPanel ui = new UIPanel();

  private M30TranTypeVO vo;

  @Override
  public void doAction(EditorContext ec) throws BusinessException {

    switch (ec.getEventtype()) {
      case EditorContext.TYPE_BROWSE:
        vo = this.queryTranstypeExtProp(ec);
        this.showTranstypeExtObj(vo);
        // 不可编辑
        this.setEditable(false);
        break;
      case EditorContext.TYPE_NEW:
        // 新增时将编辑器界面清空，状态为可编辑
        this.newTranstypeExtProp();
        this.setEditable4AskedqtRule();
        this.setEditable4BlrgcashRule();
        break;
      case EditorContext.TYPE_EDIT:
        this.setEditable(true);
        this.setEditable4AskedqtRule();
        this.setEditable4BlrgcashRule();
        this.setEditable4ChgOutRule();
        this.setEditable4NaccPriRule();
        this.setEditable4BlrgessPriNORule();
        break;
      case EditorContext.TYPE_CLEAR:
        this.clearEditorPane();
        this.setEditable(false);
        break;
      case EditorContext.TYPE_CANCEL:
        this.setEditable(false);
        this.showTranstypeExtObj(vo);
        break;
      default:
        break;
    }

  }

  @Override
  public void doButtonAction(ButtonObject bo) throws BusinessException {
    // TODO
  }

  @Override
  public Component getEditorPane() {
    this.init();
    return this.ui;
  }

  @Override
  public ButtonObject[] getExtButtonObjects() {
    return null;
  }

  @Override
  public Object getTransTypeExtObj(EditorContext context)
      throws BusinessException {
    M30TranTypeVO vo =
        (M30TranTypeVO) this.billCardPanel.getBillData().getHeaderValueVO(
            M30TranTypeVO.class.getName());
    vo.setVtrantypecode(context.getTranstype().getPk_billtypecode());
    String pk_group = AppContext.getInstance().getPkGroup();
    vo.setPk_group(pk_group);
    return vo;
  }

  /**
   * 根据询价规则设置“询到价格是否可改、未询到价格是否可改”的可编辑性
   * 
   * @version 6.0
   * @author 刘志伟
   */
  public void setEditable4AskedqtRule() {

    Integer value =
        (Integer) this.billCardPanel.getHeadItem(M30TranTypeVO.FASKQTRULE)
            .getValueObject();
    // "空、不询价"时"询到价格是否可改、未询到价格是否可改、未询到价格是否提示"不可以编辑
    if (null == value || AskPriceRule.ASKPRICE_NO.equalsValue(value)) {
      this.billCardPanel.getHeadItem(M30TranTypeVO.BMODIFYASKEDQT).setEnabled(
          false);
      this.billCardPanel.getHeadItem(M30TranTypeVO.BMODIFYUNASKEDQT)
          .setEnabled(false);
      this.billCardPanel.getHeadItem(M30TranTypeVO.BNOFINDPRICEHIT).setEnabled(
          false);
      // "正常询价、询非促销价"切换"空、不询价"确保值为空
      this.billCardPanel.getHeadItem(M30TranTypeVO.BMODIFYASKEDQT).setValue(
          UFBoolean.FALSE);
      this.billCardPanel.getHeadItem(M30TranTypeVO.BMODIFYUNASKEDQT).setValue(
          UFBoolean.FALSE);
      this.billCardPanel.getHeadItem(M30TranTypeVO.BNOFINDPRICEHIT).setValue(
          UFBoolean.FALSE);
    }
    // "正常询价、询非促销价"时"询到价格是否可改、未询到价格是否可改"可以编辑
    else if (AskPriceRule.ASKPRICE_NORMAL.equalsValue(value)
        || AskPriceRule.ASKPRICE_UNSPROMOTION.equalsValue(value)) {
      this.billCardPanel.getHeadItem(M30TranTypeVO.BMODIFYASKEDQT).setEnabled(
          true);
      this.billCardPanel.getHeadItem(M30TranTypeVO.BMODIFYUNASKEDQT)
          .setEnabled(true);
      this.billCardPanel.getHeadItem(M30TranTypeVO.BNOFINDPRICEHIT).setEnabled(
          true);
    }
  }

  /**
   * 根据销售模式设置“赠品兑付”字段的可编辑性
   * 
   * @version 6.35
   * @author dongli2
   */
  public void setEditable4BlrgcashRule() {
    Integer value =
        (Integer) this.billCardPanel.getHeadItem(M30TranTypeVO.FSALEMODE)
            .getValueObject();
    // 销售模式为“退货、退换货、普通+退货、普通+退换货”，不允许“赠品兑付”打钩
    if (null == value || SaleMode.MODE_COMMONRETURN.equalsValue(value)
        || SaleMode.MODE_COMMONRETURNCHANGE.equalsValue(value)
        || SaleMode.MODE_RETURN.equalsValue(value)
        || SaleMode.MODE_RETURNCHANGE.equalsValue(value)) {
      this.billCardPanel.getHeadItem(M30TranTypeVO.BLRGCASHFLAG).setEnabled(
          false);
      // 销售模式为“退货、退换货、普通+退货、普通+退换货”,确保“赠品兑付”值为否
      this.billCardPanel.getHeadItem(M30TranTypeVO.BLRGCASHFLAG).setValue(
          UFBoolean.FALSE);
    }
    // 销售模式为“普通”，允许“赠品兑付”打钩
    if (SaleMode.MODE_COMMON.equalsValue(value)) {
      this.billCardPanel.getHeadItem(M30TranTypeVO.BLRGCASHFLAG).setEnabled(
          true);
    }

  }

  /**
   * 根据退换货模式设置“退货入库之后才能换货出库”的可编辑性
   * 
   * @version 6.36
   * @author wangshu6
   */
  public void setEditable4ChgOutRule() {
    Integer value =
        (Integer) this.billCardPanel.getHeadItem(M30TranTypeVO.FSALEMODE)
            .getValueObject();
    // 销售模式为“退货、退换货、普通+退货、普通+退换货”，“退货入库之后才能换货出库”才可用
    if (null == value || SaleMode.MODE_COMMONRETURN.equalsValue(value)
        || SaleMode.MODE_COMMONRETURNCHANGE.equalsValue(value)
        || SaleMode.MODE_RETURN.equalsValue(value)
        || SaleMode.MODE_RETURNCHANGE.equalsValue(value)) {
      this.billCardPanel.getHeadItem(M30TranTypeVO.BCANCHANGEOUT).setEnabled(
          true);
    }
    // 销售模式为“普通”，“退货入库之后才能换货出库”不可用
    if (SaleMode.MODE_COMMON.equalsValue(value)) {
      this.billCardPanel.getHeadItem(M30TranTypeVO.BCANCHANGEOUT).setEnabled(
          false);
    }
  }

  /**
   * 控制"主记账单价取价规则"的可编辑性
   * 
   * @version 6.36
   * @author wangshu6
   */
  public void setEditable4NaccPriRule() {
    Boolean flag =
         (Boolean) this.billCardPanel.getHeadItem(M30TranTypeVO.BLRGCASHFLAG)
        .getValueObject();
    // 仅当赠品兑付才可维护 “主记账单价取价规则”才可用
    if (UFBoolean.TRUE.equals(UFBoolean.valueOf(flag))) {
      this.billCardPanel.getHeadItem(M30TranTypeVO.NACCPRICERULE).setEnabled(
          true);
    }else{
      this.billCardPanel.getHeadItem(M30TranTypeVO.NACCPRICERULE).setEnabled(
          false);
    }
  }

  /**
   * 根据赠品价格分摊方式设置“赠品行价格保持不变”的可编辑性
   * 
   * @version 6.36
   * @author wangshu6
   */
  public void setEditable4BlrgessPriNORule() {
    Integer value =
        (Integer) this.billCardPanel.getHeadItem(M30TranTypeVO.FLARGESSDISTYPE)
            .getValueObject();
    // 赠品价格分摊方式为“按买赠设置分摊、同物料分摊、整单分摊”，“赠品行价格保持不变”才可用
    if (null == value || LargessDisType.DISPARTBYLARGESS.equalsValue(value)
        || LargessDisType.DISPARTBYINV.equalsValue(value)
        || LargessDisType.DISPARTONE.equalsValue(value)) {
      this.billCardPanel.getHeadItem(M30TranTypeVO.BLARGESSPRICENO).setEnabled(
          true);
    }
    // 赠品价格分摊方式为“不分摊”，“赠品行价格保持不变”不可用
    if (LargessDisType.NODISPART.equalsValue(value)) {
      this.billCardPanel.getHeadItem(M30TranTypeVO.BLARGESSPRICENO).setEnabled(
          false);
    }
  }

  private void clearEditorPane() {
    this.billCardPanel.getHeadItem(M30TranTypeVO.BMODIFYDISCOUNT).setValue(
        Boolean.FALSE);

  }

  private void init() {
    if (this.billCardPanel != null) {
      return;
    }
    this.billCardPanel = new BillCardPanel();
    this.billCardPanel.loadTemplet("30trantype", null, null, "@@@@");
    this.billCardPanel.setEnabled(false);
    this.billCardPanel.addBillEditListenerHeadTail(new BillEditListener() {

      @Override
      public void afterEdit(BillEditEvent e) {
        if (e.getKey().equals(M30TranTypeVO.FASKQTRULE)) {
          M30TranTypeUI.this.setEditable4AskedqtRule();
        }
        if (e.getKey().equals(M30TranTypeVO.FSALEMODE)) {
          M30TranTypeUI.this.setEditable4BlrgcashRule();
          M30TranTypeUI.this.setEditable4ChgOutRule();
        }if(e.getKey().equals(M30TranTypeVO.BLRGCASHFLAG)){
          M30TranTypeUI.this.setEditable4NaccPriRule();
        }if(e.getKey().equals(M30TranTypeVO.FLARGESSDISTYPE)){
          M30TranTypeUI.this.setEditable4BlrgessPriNORule();
        }
      }

      @Override
      public void bodyRowChange(BillEditEvent e) {
        // 表体行变化
      }

    });
    this.ui.setLayout(new BorderLayout());
    this.ui.add(this.billCardPanel);

  }

  private void newTranstypeExtProp() {
    this.billCardPanel.addNew();
    this.billCardPanel.updateValue();
    this.setEditable(true);
    this.setDefaultVaule();
  }

  private M30TranTypeVO queryTranstypeExtProp(EditorContext ec) {

    if (ec.getTranstype() == null
        || ec.getTranstype().getPk_billtypeid() == null) {
      return new M30TranTypeVO();
    }

    String ctrantypeid = ec.getTranstype().getPk_billtypeid();
    M30TranTypeVO returnVos = null;
    try {
      IM30TranTypeSelfService service =
          NCLocator.getInstance().lookup(IM30TranTypeSelfService.class);
      returnVos = service.queryTranTypeVO(ctrantypeid);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return returnVos;
  }

  private void setDefaultVaule() {
    this.billCardPanel.getHeadItem(M30TranTypeVO.BMODIFYDISCOUNT).setValue(
        Boolean.TRUE);
  }

  private void setEditable(boolean isEdit) {
    this.billCardPanel.setEnabled(isEdit);
  }

  private void showTranstypeExtObj(M30TranTypeVO vo) {
    this.billCardPanel.getBillData().setHeaderValueVO(vo);
  }
}
