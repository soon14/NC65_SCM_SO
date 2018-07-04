package nc.ui.so.m32trantype;

import java.awt.BorderLayout;
import java.awt.Component;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.Log;
import nc.vo.so.m32trantype.entity.M32TranTypeVO;
import nc.vo.trade.checkrule.VOChecker;

import nc.itf.so.m32trantype.IM32TranTypeService;

import nc.bs.framework.common.NCLocator;

import nc.ui.pub.ButtonObject;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.transtype.EditorContext;
import nc.ui.pub.transtype.ITranstypeEditor;

/**
 * 销售发票前台ui界面
 * 
 * @since 6.3
 * @version 2012-12-21 下午01:04:51
 * @author yaogj
 */
public class M32TransTypeUI implements ITranstypeEditor {

  private BillCardPanel billCardPanel;

  private String pk_group = AppContext.getInstance().getPkGroup();

  private String templet = "32trantype";

  private UIPanel uipanel = new UIPanel();
  private  M32TranTypeVO bill=null;

  @Override
  public void doAction(EditorContext context) throws BusinessException {

    switch (context.getEventtype()) {
      case EditorContext.TYPE_BROWSE:

       bill = this.queryTranstypeExtProp(context);
        this.setTranType(bill);
        // 不可编辑
        this.setEditable(false);
        break;
      case EditorContext.TYPE_NEW:
        // 新增时将编辑器界面清空，状态为可编辑
        this.addNewTranstype();
        break;
      case EditorContext.TYPE_EDIT:

        this.setEditable(true);

        break;
      case EditorContext.TYPE_CLEAR:
        this.clearEditorPane();
        this.setEditable(false);
        break;
      case EditorContext.TYPE_CANCEL:
        this.setEditable(false);
        this.setTranType(bill);
        break;
      default:
        break;
    }

  }

  @Override
  public void doButtonAction(ButtonObject bo) throws BusinessException {
    // TODO 自动生成方法存根

  }

  @Override
  public Component getEditorPane() {
    this.initilize();
    return this.uipanel;
  }

  @Override
  public ButtonObject[] getExtButtonObjects() {
    // TODO 自动生成方法存根
    return null;
  }

  @Override
  public Object getTransTypeExtObj(EditorContext context)
      throws BusinessException {
    M32TranTypeVO vo =
        (M32TranTypeVO) this.billCardPanel.getBillData().getHeaderValueVO(
            M32TranTypeVO.class.getName());
    vo.setVtrantypecode(context.getTranstype().getPk_billtypecode());
    vo.setPk_group(this.pk_group);
    return vo;
  }

  private void addNewTranstype() {
    this.billCardPanel.addNew();
    this.billCardPanel.updateValue();
    this.setEditable(true);
  }

  private void clearEditorPane() {
    // TODO 自动生成方法存根

  }

  private void initilize() {
    if (null != this.billCardPanel) {
      return;
    }
    this.billCardPanel = new BillCardPanel();
    this.billCardPanel.loadTemplet(this.templet, null, null, "@@@@");
    this.billCardPanel.setEnabled(false);
    this.uipanel.setLayout(new BorderLayout());
    this.uipanel.add(this.billCardPanel);

  }

  /**
   * 方法功能描述：查询发票交易类型 。
   * <p>
   * <b>参数说明</b>
   * 
   * @param context
   * @return <p>
   * @author 冯加滨
   * @time 2010-1-25 下午04:42:53
   */
  private M32TranTypeVO queryTranstypeExtProp(EditorContext context) {

    if (VOChecker.isEmpty(context.getTranstype())
        || VOChecker.isEmpty(context.getTranstype().getPk_billtypecode())) {
      return new M32TranTypeVO();
    }
    String billtypecode = context.getTranstype().getPk_billtypecode();
    M32TranTypeVO returnVos = null;
    try {
      IM32TranTypeService service =
          NCLocator.getInstance().lookup(IM32TranTypeService.class);
      returnVos = service.queryTranType(this.pk_group, billtypecode);
    }
    catch (Exception e) {
      Log.info(e);
      ExceptionUtils.wrappException(e);
    }
    return returnVos;

  }

  private void setEditable(boolean isEdit) {
    this.billCardPanel.setEnabled(isEdit);
  }

  private void setTranType(M32TranTypeVO bill) {
    this.billCardPanel.getBillData().setHeaderValueVO(bill);
  }

}
